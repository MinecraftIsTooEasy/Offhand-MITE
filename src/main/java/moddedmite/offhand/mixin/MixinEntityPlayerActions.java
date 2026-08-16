package moddedmite.offhand.mixin;

import moddedmite.offhand.config.OffhandManyLibConfig;
import moddedmite.offhand.api.core.IOffhandActionState;
import moddedmite.offhand.api.core.OffhandActionHelper;
import moddedmite.offhand.api.core.OffhandDamageCalculator;
import moddedmite.offhand.api.core.OffhandUtils;
import net.minecraft.Block;
import net.minecraft.Damage;
import net.minecraft.EnchantmentDamage;
import net.minecraft.Entity;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EntitySkeleton;
import net.minecraft.Item;
import net.minecraft.ItemCudgel;
import net.minecraft.ItemStack;
import net.minecraft.ItemWarHammer;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayerActions implements IOffhandActionState {

    @Unique
    private boolean offhand$calculatingMiningStrength;

    @Unique
    private Entity offhand$lastAttackTarget;

    @Unique
    private ItemStack offhand$lastAttackStack;

    @Unique
    private boolean offhand$lastAttackWasEffective;

    @Unique
    private OffhandActionHelper.DualMiningBlock offhand$dualMiningBlock;

    @Override
    public OffhandActionHelper.DualMiningBlock getDualMiningBlock() {
        return this.offhand$dualMiningBlock;
    }

    @Override
    public void setDualMiningBlock(OffhandActionHelper.DualMiningBlock block) {
        this.offhand$dualMiningBlock = block;
    }

    @Inject(method = "getCurrentPlayerStrVsBlock", at = @At("RETURN"), cancellable = true)
    private void offhand$addOffhandToolMiningStrength(
        int x,
        int y,
        int z,
        boolean applyHeldItem,
        CallbackInfoReturnable<Float> cir) {
        if (this.offhand$calculatingMiningStrength || !applyHeldItem) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.worldObj == null || player.inventory == null) {
            return;
        }

        if (!OffhandManyLibConfig.OFFHAND_BREAK_BLOCKS.getBooleanValue()) {
            return;
        }

        Float currentReturnValue = cir.getReturnValue();
        if (currentReturnValue == null || currentReturnValue.floatValue() <= 0.0F) {
            return;
        }

        float mainStrength = currentReturnValue.floatValue();
        Block block = player.worldObj.getBlock(x, y, z);
        if (block == null) {
            return;
        }

        int metadata = player.worldObj.getBlockMetadata(x, y, z);
        ItemStack mainhand = player.inventory.getCurrentItemStack();
        ItemStack offhand = OffhandUtils.getOffhandItem(player);
        if (!OffhandActionHelper.canDualMineWithTools(player, block, metadata, mainhand, offhand)) {
            return;
        }

        this.offhand$calculatingMiningStrength = true;
        try {
            OffhandUtils.useOffhandItem(player, false, () -> {
                float offhandStrength = player.getCurrentPlayerStrVsBlock(x, y, z, true);
                if (offhandStrength > 0.0F) {
                    if (!player.worldObj.isRemote) {
                        OffhandActionHelper.recordDualMiningBlock(
                            player,
                            x,
                            y,
                            z,
                            block,
                            metadata,
                            mainhand,
                            offhand);
                    }
                    cir.setReturnValue(Float.valueOf(mainStrength + offhandStrength));
                }
            });
        } finally {
            this.offhand$calculatingMiningStrength = false;
        }
    }

    @Inject(method = "calcRawMeleeDamageVs(Lnet/minecraft/Entity;ZZ)F", at = @At("RETURN"), cancellable = true)
    private void offhand$addOffhandAttackDamage(
        Entity target,
        boolean critical,
        boolean suspendedInLiquid,
        CallbackInfoReturnable<Float> cir) {
        if (!OffhandManyLibConfig.OFFHAND_ATTACK.getBooleanValue()) {
            return;
        }

        Float currentReturnValue = cir.getReturnValue();
        if (currentReturnValue == null || currentReturnValue.floatValue() <= 0.0F) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.worldObj == null || player.worldObj.isRemote || player.inventory == null) {
            return;
        }

        ItemStack mainhand = player.inventory.getCurrentItemStack();
        ItemStack offhand = OffhandUtils.getOffhandItem(player);
        if (!OffhandActionHelper.canOffhandAttack(player, mainhand, offhand)) {
            return;
        }

        float offhandBonus = this.offhand$getAttackBonus(offhand, target, critical, suspendedInLiquid);
        if (offhandBonus > 0.0F) {
            this.offhand$lastAttackTarget = target;
            this.offhand$lastAttackStack = offhand;
            cir.setReturnValue(Float.valueOf(currentReturnValue.floatValue() + offhandBonus));
        }
    }

    @Unique
    private float offhand$getAttackBonus(
        ItemStack offhand,
        Entity target,
        boolean critical,
        boolean suspendedInLiquid) {
        Item item = offhand.getItem();
        float enchantmentDamage = 0.0F;
        boolean silverAgainstUndead = false;
        boolean crushingAgainstSkeleton = false;

        if (target instanceof EntityLivingBase livingTarget) {
            enchantmentDamage = EnchantmentDamage.getDamageModifiers(offhand, livingTarget);
            silverAgainstUndead = livingTarget.isEntityUndead() && item.hasMaterial(Material.silver);
            crushingAgainstSkeleton = target instanceof EntitySkeleton
                && (item instanceof ItemCudgel || item instanceof ItemWarHammer);
        }

        return OffhandDamageCalculator.calculateBonus(
            offhand.getMeleeDamageBonus(),
            enchantmentDamage,
            critical,
            suspendedInLiquid,
            silverAgainstUndead,
            crushingAgainstSkeleton);
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("HEAD"))
    private void offhand$beginAttack(Entity target, CallbackInfo ci) {
        this.offhand$clearLastAttack();
    }

    @Redirect(
        method = "attackTargetEntityWithCurrentItem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/Entity;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private EntityDamageResult offhand$captureAttackResult(Entity target, Damage damage) {
        EntityDamageResult result = target.attackEntityFrom(damage);
        this.offhand$lastAttackWasEffective = result != null && result.entityWasNegativelyAffected();
        return result;
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("RETURN"))
    private void offhand$damageOffhandAfterAttack(Entity target, CallbackInfo ci) {
        if (target == null || target != this.offhand$lastAttackTarget) {
            this.offhand$clearLastAttack();
            return;
        }

        ItemStack offhand = this.offhand$lastAttackStack;
        boolean attackWasEffective = this.offhand$lastAttackWasEffective;
        this.offhand$clearLastAttack();

        if (!(target instanceof EntityLivingBase)
            || !attackWasEffective
            || !OffhandActionHelper.hasUsableStack(offhand)) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.worldObj == null || player.worldObj.isRemote) {
            return;
        }

        OffhandUtils.useOffhandItem(player, false, () -> {
            offhand.hitEntity((EntityLivingBase) target, player);
        });
    }

    @Unique
    private void offhand$clearLastAttack() {
        this.offhand$lastAttackTarget = null;
        this.offhand$lastAttackStack = null;
        this.offhand$lastAttackWasEffective = false;
    }
}
