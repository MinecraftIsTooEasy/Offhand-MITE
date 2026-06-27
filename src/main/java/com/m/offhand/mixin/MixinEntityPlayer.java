package com.m.offhand.mixin;

import com.m.offhand.OffhandManyLibConfig;
import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandActionHelper;
import com.m.offhand.api.core.OffhandUtils;
import net.minecraft.Block;
import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.EnchantmentDamage;
import net.minecraft.Entity;
import net.minecraft.EntityDamageResult;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EntitySkeleton;
import net.minecraft.EnumItemInUseAction;
import net.minecraft.InventoryPlayer;
import net.minecraft.Item;
import net.minecraft.ItemCudgel;
import net.minecraft.ItemDamageResult;
import net.minecraft.ItemStack;
import net.minecraft.ItemWarHammer;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer implements IOffhandPlayer {

    @Unique
    private boolean offhand$isOffhandItemInUse = false;

    @Unique
    private ItemStack offhand$itemInUse = null;

    @Unique
    private int offhand$itemInUseCount = 0;

    @Unique
    private int offhand$mainhandSlot = 0;

    @Unique
    private boolean offhand$clearMainItemWasOffhand = false;

    @Unique
    private boolean offhand$calculatingOffhandMiningStrength = false;

    @Unique
    private Entity offhand$lastAttackTarget = null;

    @Unique
    private ItemStack offhand$lastAttackStack = null;

    @Unique
    private boolean offhand$lastAttackWasEffective = false;

    @Shadow
    protected ItemStack itemInUse;

    @Override
    public void swingOffItem() {
        // Offhand swing animation is intentionally disabled.
    }

    @Override
    public float getOffSwingProgress(float frame) {
        return 0.0F;
    }

    @Inject(method = "getCurrentPlayerStrVsBlock", at = @At("RETURN"), cancellable = true)
    private void offhand$addOffhandToolMiningStrength(
        int x,
        int y,
        int z,
        boolean applyHeldItem,
        CallbackInfoReturnable<Float> cir) {
        if (this.offhand$calculatingOffhandMiningStrength
            || !applyHeldItem
            || !OffhandManyLibConfig.OFFHAND_BREAK_BLOCKS.getBooleanValue()) {
            return;
        }

        Float currentReturnValue = cir.getReturnValue();
        if (currentReturnValue == null) {
            return;
        }

        float mainStrength = currentReturnValue.floatValue();
        if (mainStrength <= 0.0F) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        if (player.worldObj == null || player.inventory == null) {
            return;
        }

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

        this.offhand$calculatingOffhandMiningStrength = true;
        try {
            OffhandUtils.useOffhandItem(player, false, () -> {
                float offhandStrength = player.getCurrentPlayerStrVsBlock(x, y, z, true);
                if (offhandStrength > 0.0F) {
                    OffhandActionHelper.recordDualMiningBlock(player, x, y, z, block, metadata, mainhand, offhand);
                    cir.setReturnValue(Float.valueOf(mainStrength + offhandStrength));
                }
            });
        } finally {
            this.offhand$calculatingOffhandMiningStrength = false;
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

        float offhandBonus = this.offhand$getOffhandAttackBonus(offhand, target);
        if (critical && offhandBonus > 0.0F) {
            offhandBonus *= 1.5F;
        }
        if (suspendedInLiquid && offhandBonus > 1.0F) {
            offhandBonus = 1.0F + (offhandBonus - 1.0F) * 0.5F;
        }

        if (offhandBonus > 0.0F) {
            this.offhand$lastAttackTarget = target;
            this.offhand$lastAttackStack = offhand;
            cir.setReturnValue(Float.valueOf(currentReturnValue.floatValue() + offhandBonus));
        }
    }

    @Unique
    private float offhand$getOffhandAttackBonus(ItemStack offhand, Entity target) {
        float bonus = offhand.getMeleeDamageBonus();
        Item item = offhand.getItem();

        if (target instanceof EntityLivingBase) {
            EntityLivingBase livingTarget = (EntityLivingBase) target;
            if (livingTarget.isEntityUndead() && item.hasMaterial(Material.silver)) {
                bonus *= 1.25F;
            }
            if (target instanceof EntitySkeleton && (item instanceof ItemCudgel || item instanceof ItemWarHammer)) {
                bonus *= 2.0F;
            }
            bonus += EnchantmentDamage.getDamageModifiers(offhand, livingTarget);
        }

        return bonus;
    }

    @Inject(method = "attackTargetEntityWithCurrentItem", at = @At("HEAD"))
    private void offhand$beginAttackTargetEntityWithCurrentItem(Entity target, CallbackInfo ci) {
        this.offhand$clearLastAttack();
        this.offhand$lastAttackWasEffective = false;
    }

    @Redirect(
        method = "attackTargetEntityWithCurrentItem",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/Entity;attackEntityFrom(Lnet/minecraft/Damage;)Lnet/minecraft/EntityDamageResult;"))
    private EntityDamageResult offhand$captureAttackResult(Entity target, Damage damage) {
        EntityDamageResult result = target.attackEntityFrom(damage);
        this.offhand$lastAttackWasEffective =
            result != null && result.entityWasNegativelyAffected();
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

    @Inject(method = "onUpdate", at = @At(value = "TAIL"))
    private void offhand$updateOffhandSwing(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (this.offhand$itemInUse != null) {
            int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
            ItemStack offhandStack =
                (offhandSlot >= 0 && offhandSlot < player.inventory.mainInventory.length)
                    ? player.inventory.mainInventory[offhandSlot]
                    : null;
            if (offhandStack == null || offhandStack != this.offhand$itemInUse || offhandStack.stackSize <= 0) {
                this.offhand$itemInUse = null;
                this.offhand$itemInUseCount = 0;
                if (this.offhand$isOffhandItemInUse) {
                    this.offhand$isOffhandItemInUse = false;
                    OffhandCompatRegistry.syncOffhandUseState(player, false);
                }
            } else if (--this.offhand$itemInUseCount <= 0) {
                OffhandUtils.useOffhandItem(player, false, () -> {
                    offhandStack.onItemUseFinish(player.worldObj, player);
                });
                this.offhand$itemInUse = null;
                this.offhand$itemInUseCount = 0;
                if (this.offhand$isOffhandItemInUse) {
                    this.offhand$isOffhandItemInUse = false;
                    OffhandCompatRegistry.syncOffhandUseState(player, false);
                }
            }
        }

        if (this.offhand$isOffhandItemInUse) {
            int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
            ItemStack offhandStack =
                (offhandSlot >= 0 && offhandSlot < player.inventory.mainInventory.length)
                    ? player.inventory.mainInventory[offhandSlot]
                    : null;

            boolean hasSecondaryOffhandUse =
                this.offhand$itemInUse != null
                    && this.offhand$itemInUseCount > 0
                    && this.offhand$itemInUse == offhandStack;
            boolean hasPrimaryOffhandUse = this.itemInUse != null && this.itemInUse == offhandStack;

            if (!hasSecondaryOffhandUse && !hasPrimaryOffhandUse && player.inventory.currentItem != offhandSlot) {
                this.offhand$isOffhandItemInUse = false;
                OffhandCompatRegistry.syncOffhandUseState(player, false);
            }
        }
    }

    @Override
    public void setOffhandItemInUse(boolean usingOffhand) {
        this.offhand$isOffhandItemInUse = usingOffhand;
        if (!usingOffhand) {
            this.offhand$itemInUse = null;
            this.offhand$itemInUseCount = 0;
        }
    }

    @Override
    public boolean isOffhandItemInUse() {
        return this.offhand$isOffhandItemInUse;
    }

    @Override
    public boolean isUsingOffhand() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        return player.inventory.currentItem == offhandSlot;
    }

    @Override
    public void setMainhandSlot(int slot) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int len = player.inventory.mainInventory.length;

        if (slot >= 0 && slot < len) {
            this.offhand$mainhandSlot = slot;
        } else if (player.inventory.currentItem >= 0 && player.inventory.currentItem < len) {
            this.offhand$mainhandSlot = player.inventory.currentItem;
        } else {
            this.offhand$mainhandSlot = 0;
        }
    }

    @Override
    public ItemStack getMainhandItem() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int len = player.inventory.mainInventory.length;
        if (this.offhand$mainhandSlot >= 0 && this.offhand$mainhandSlot < len) {
            return player.inventory.mainInventory[this.offhand$mainhandSlot];
        }
        return null;
    }

    @Override
    public boolean isOffhandBlocking() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        ItemStack stack = this.getOffhandItemInUse();
        int count = this.getOffhandItemInUseCount();
        return stack != null
            && count > 0
            && stack.getItemInUseAction(player) == EnumItemInUseAction.BLOCK;
    }

    @Override
    public ItemStack getOffhandItemInUse() {
        if (this.offhand$itemInUse != null && this.offhand$itemInUseCount > 0) {
            return this.offhand$itemInUse;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot >= 0 && offhandSlot < player.inventory.mainInventory.length) {
            ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
            if (offhandStack != null && this.itemInUse == offhandStack) {
                return offhandStack;
            }
        }

        return null;
    }

    @Override
    public int getOffhandItemInUseCount() {
        if (this.offhand$itemInUse != null && this.offhand$itemInUseCount > 0) {
            return this.offhand$itemInUseCount;
        }
        return 0;
    }

    @Inject(method = "setHeldItemInUse", at = @At("HEAD"), cancellable = true)
    private void offhand$startSecondaryOffhandUse(CallbackInfoReturnable<Boolean> cir) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (player.inventory.currentItem != offhandSlot) {
            return;
        }

        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (offhandStack == null || offhandStack.stackSize <= 0 || offhandStack.getItem() == null) {
            return;
        }

        if (offhandStack.getItemInUseAction(player) == null) {
            return;
        }

        if (this.itemInUse != null && this.itemInUse != offhandStack) {
            this.offhand$itemInUse = offhandStack;
            this.offhand$itemInUseCount = offhandStack.getMaxItemUseDuration();

            if (!this.offhand$isOffhandItemInUse) {
                this.offhand$isOffhandItemInUse = true;
                OffhandCompatRegistry.syncOffhandUseState(player, true);
            }

            cir.setReturnValue(true);
        }
    }

    @Inject(method = "setHeldItemInUse", at = @At("RETURN"))
    private void offhand$onSetHeldItemInUse(CallbackInfoReturnable<Boolean> cir) {
        if (!Boolean.TRUE.equals(cir.getReturnValue())) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        boolean usingOffhand = player.inventory.currentItem == offhandSlot;

        if (!usingOffhand && this.offhand$itemInUse != null) {
            return;
        }

        if (this.offhand$isOffhandItemInUse == usingOffhand) {
            return;
        }

        this.offhand$isOffhandItemInUse = usingOffhand;

        OffhandCompatRegistry.syncOffhandUseState(player, usingOffhand);
    }

    @Inject(method = "clearItemInUse", at = @At("HEAD"))
    private void offhand$captureClearItemInUseSource(CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        ItemStack offhandStack =
            (offhandSlot >= 0 && offhandSlot < player.inventory.mainInventory.length)
                ? player.inventory.mainInventory[offhandSlot]
                : null;
        this.offhand$clearMainItemWasOffhand = this.itemInUse != null && this.itemInUse == offhandStack;
    }

    @Inject(method = "clearItemInUse", at = @At("TAIL"))
    private void offhand$onClearItemInUse(CallbackInfo ci) {
        if (!this.offhand$clearMainItemWasOffhand) {
            return;
        }
        this.offhand$clearMainItemWasOffhand = false;

        if (!this.offhand$isOffhandItemInUse) {
            return;
        }

        this.offhand$isOffhandItemInUse = false;
        EntityPlayer player = (EntityPlayer) (Object) this;
        OffhandCompatRegistry.syncOffhandUseState(player, false);
    }

    @Inject(method = "stopUsingItem()V", at = @At("HEAD"))
    private void offhand$stopSecondaryOffhandUse(CallbackInfo ci) {
        if (this.offhand$itemInUse == null || this.offhand$itemInUseCount <= 0) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (offhandStack == null || offhandStack != this.offhand$itemInUse || offhandStack.stackSize <= 0) {
            this.offhand$itemInUse = null;
            this.offhand$itemInUseCount = 0;
            if (this.offhand$isOffhandItemInUse) {
                this.offhand$isOffhandItemInUse = false;
                OffhandCompatRegistry.syncOffhandUseState(player, false);
            }
            return;
        }

        OffhandUtils.useOffhandItem(player, false, () -> {
            offhandStack.onPlayerStoppedUsing(player.worldObj, player, this.offhand$itemInUseCount);
        });

        this.offhand$itemInUse = null;
        this.offhand$itemInUseCount = 0;
        if (this.offhand$isOffhandItemInUse) {
            this.offhand$isOffhandItemInUse = false;
            OffhandCompatRegistry.syncOffhandUseState(player, false);
        }
    }

    @Inject(method = "getItemInUseCount", at = @At("HEAD"), cancellable = true)
    private void offhand$suppressMainhandUseAnimationDuringOffhandRender(CallbackInfoReturnable<Integer> cir) {
        if (!OffhandUtils.isClientOffhandRenderContext()) {
            return;
        }

        if (!this.offhand$isOffhandItemInUse) {
            cir.setReturnValue(0);
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        ItemStack offhandStack =
            (offhandSlot >= 0 && offhandSlot < player.inventory.mainInventory.length)
                ? player.inventory.mainInventory[offhandSlot]
                : null;

        if (this.offhand$itemInUse != null && this.offhand$itemInUseCount > 0 && this.offhand$itemInUse == offhandStack) {
            cir.setReturnValue(this.offhand$itemInUseCount);
        } else if (this.itemInUse != offhandStack) {
            // Do not reuse mainhand use-count in offhand render path.
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "setHeldItemStack", at = @At("HEAD"), cancellable = true)
    private void offhand$setHeldItemStackToOffhandWhenUsing(ItemStack stack, CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (!this.offhand$shouldRouteHeldItemMutationToOffhand(player, offhandSlot, offhandStack)) {
            return;
        }

        player.inventory.setInventorySlotContents(offhandSlot, stack);
        ci.cancel();
    }

    @Inject(method = "convertOneOfHeldItem", at = @At("HEAD"), cancellable = true)
    private void offhand$convertOneOfOffhandItem(ItemStack createdItemStack, CallbackInfo ci) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (!this.offhand$shouldRouteHeldItemMutationToOffhand(player, offhandSlot, offhandStack)) {
            return;
        }

        OffhandUtils.useOffhandItem(player, false, () -> {
            ItemStack targetStack = player.inventory.getCurrentItemStack();

            if (targetStack == null || targetStack.stackSize <= 0) {
                // Prevent InventoryPlayer.convertOneItem NPE when target stack was already cleared.
                player.inventory.setInventorySlotContents(offhandSlot, createdItemStack);
            } else {
                player.inventory.convertOneOfCurrentItem(createdItemStack);
            }
        });
        ci.cancel();
    }

    @Unique
    private boolean offhand$shouldRouteHeldItemMutationToOffhand(EntityPlayer player, int offhandSlot, ItemStack offhandStack) {
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return false;
        }

        // Route held-item mutations while the offhand slot is explicitly selected in our scoped context.
        // Keep this true even when offhand stack just became null so convertOneOfHeldItem can apply its null-guard path.
        if (player.inventory.currentItem == offhandSlot) {
            return true;
        }

        // Vanilla onItemUseFinish fires convertOneOfHeldItem via the primary itemInUse while currentItem
        // has already been restored to the mainhand slot. Route to offhand when the active use targets it.
        if (offhandStack != null) {
            if (this.itemInUse == offhandStack) {
                return true;
            }
            if (this.offhand$itemInUse == offhandStack && this.offhand$itemInUseCount > 0) {
                return true;
            }
        }

        return false;
    }

    @Inject(method = "tryDamageHeldItem", at = @At("HEAD"), cancellable = true)
    private void offhand$tryDamageOffhandHeldItem(DamageSource damageSource, int amount, CallbackInfoReturnable<ItemDamageResult> cir) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (offhandStack == null) {
            return;
        }

        boolean hasPrimaryOffhandUse = this.itemInUse != null && this.itemInUse == offhandStack;
        boolean hasSecondaryOffhandUse =
            this.offhand$itemInUse != null
                && this.offhand$itemInUseCount > 0
                && this.offhand$itemInUse == offhandStack;

        if (!hasPrimaryOffhandUse && !hasSecondaryOffhandUse) {
            return;
        }

        if (player.inCreativeMode()) {
            cir.setReturnValue(null);
            return;
        }

        final ItemDamageResult[] result = new ItemDamageResult[1];
        if (OffhandUtils.useOffhandItem(player, false, () -> {
                result[0] = offhandStack.tryDamageItem(damageSource, amount, player);
                return true;
            })) {
            cir.setReturnValue(result[0]);
        }
    }

    @Inject(method = "isBlocking", at = @At("HEAD"), cancellable = true)
    private void offhand$allowSecondaryOffhandBlocking(CallbackInfoReturnable<Boolean> cir) {
        if (this.offhand$itemInUse == null || this.offhand$itemInUseCount <= 0) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (offhandStack == null || offhandStack != this.offhand$itemInUse || offhandStack.stackSize <= 0) {
            return;
        }

        if (offhandStack.getItemInUseAction(player) == EnumItemInUseAction.BLOCK) {
            cir.setReturnValue(true);
        }
    }

    @Redirect(
        method = "onUpdate",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/InventoryPlayer;getCurrentItemStack()Lnet/minecraft/ItemStack;"))
    private ItemStack offhand$keepUsingOffhandItemStack(InventoryPlayer inventory) {
        ItemStack current = inventory.getCurrentItemStack();
        if (!this.offhand$isOffhandItemInUse) {
            return current;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return current;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (this.itemInUse != null && offhandStack == this.itemInUse) {
            return offhandStack;
        }

        return current;
    }

    @Redirect(
        method = "checkForBlockActivation",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;swingArm()V"))
    private void offhand$redirectMainhandSwingOnBlockActivate(EntityPlayer player) {
        if (!OffhandUtils.isClientOffhandUseContext()) {
            player.swingArm();
        }
    }

    @Redirect(
        method = "checkForEntityInteraction",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;swingArm()V"))
    private void offhand$redirectMainhandSwingOnEntityInteract(EntityPlayer player) {
        if (!OffhandUtils.isClientOffhandUseContext()) {
            player.swingArm();
        }
    }

    @Redirect(
        method = "tryPlaceHeldItemAsBlock",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/EntityPlayer;swingArm()V"))
    private void offhand$redirectMainhandSwingOnPlaceBlock(EntityPlayer player) {
        if (!OffhandUtils.isClientOffhandUseContext()) {
            player.swingArm();
        }
    }
}
