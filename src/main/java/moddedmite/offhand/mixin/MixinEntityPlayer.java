package moddedmite.offhand.mixin;

import moddedmite.offhand.api.compat.OffhandCompatRegistry;
import moddedmite.offhand.api.core.IOffhandClientState;
import moddedmite.offhand.api.core.IOffhandInventory;
import moddedmite.offhand.api.core.IOffhandPlayer;
import moddedmite.offhand.api.core.OffhandUtils;
import net.minecraft.DamageSource;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumItemInUseAction;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemDamageResult;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.ServerPlayer;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EntityPlayer.class)
public abstract class MixinEntityPlayer implements IOffhandPlayer, IOffhandClientState {

    @Unique
    private boolean offhand$isOffHandSwingInProgress = false;

    @Unique
    private int offhand$offHandSwingProgressInt = 0;

    @Unique
    private float offhand$offHandSwingProgress = 0.0F;

    @Unique
    private float offhand$prevOffHandSwingProgress = 0.0F;

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
    private int offhand$clientSuppressMainhandRenderUntilTick = -1;

    @Unique
    private int offhand$clientAttackAnimationUntilTick = -1;

    @Unique
    private int offhand$clientMiningAnimationUntilTick = -1;

    @Shadow
    protected ItemStack itemInUse;

    @Shadow
    protected int itemInUseCount;

    @Override
    public void swingOffItem() {
        if (!this.offhand$isOffHandSwingInProgress
            || this.offhand$offHandSwingProgressInt >= this.offhand$getArmSwingAnimationEnd() / 2
            || this.offhand$offHandSwingProgressInt < 0) {
            this.offhand$offHandSwingProgressInt = -1;
            this.offhand$isOffHandSwingInProgress = true;

            EntityPlayer player = (EntityPlayer) (Object) this;
            if (player.worldObj != null && !player.worldObj.isRemote && player instanceof ServerPlayer) {
                OffhandCompatRegistry.syncOffhandAnimation(player);
            }
        }
    }

    @Override
    public float getOffSwingProgress(float frame) {
        return this.offhand$prevOffHandSwingProgress
            + (this.offhand$offHandSwingProgress - this.offhand$prevOffHandSwingProgress) * frame;
    }

    @Inject(method = "onUpdate", at = @At(value = "TAIL"))
    private void offhand$updateOffhandSwing(CallbackInfo ci) {
        this.offhand$prevOffHandSwingProgress = this.offhand$offHandSwingProgress;

        if (this.offhand$isOffHandSwingInProgress) {
            ++this.offhand$offHandSwingProgressInt;
            if (this.offhand$offHandSwingProgressInt >= this.offhand$getArmSwingAnimationEnd()) {
                this.offhand$offHandSwingProgressInt = 0;
                this.offhand$isOffHandSwingInProgress = false;
            }
        } else {
            this.offhand$offHandSwingProgressInt = 0;
        }

        this.offhand$offHandSwingProgress =
            (float) this.offhand$offHandSwingProgressInt / (float) this.offhand$getArmSwingAnimationEnd();

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

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot >= 0 && offhandSlot < player.inventory.mainInventory.length) {
            ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
            if (offhandStack != null && this.itemInUse == offhandStack && this.itemInUseCount > 0) {
                return this.itemInUseCount;
            }
        }

        return 0;
    }

    @Override
    public void cancelActiveItemUse() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (this.itemInUse != null) {
            player.clearItemInUse();
        }

        this.offhand$itemInUse = null;
        this.offhand$itemInUseCount = 0;
        if (this.offhand$isOffhandItemInUse) {
            this.offhand$isOffhandItemInUse = false;
            OffhandCompatRegistry.syncOffhandUseState(player, false);
        }
    }

    @Override
    public void markMainhandRenderSuppressed(int ticks) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        this.offhand$clientSuppressMainhandRenderUntilTick = player.ticksExisted + Math.max(0, ticks);
    }

    @Override
    public boolean shouldSuppressMainhandRender() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        return player.ticksExisted <= this.offhand$clientSuppressMainhandRenderUntilTick;
    }

    @Override
    public void markOffhandAttackAnimation(int ticks) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        this.offhand$clientAttackAnimationUntilTick = player.ticksExisted + Math.max(0, ticks);
    }

    @Override
    public boolean shouldAnimateOffhandAttack() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        return player.ticksExisted <= this.offhand$clientAttackAnimationUntilTick;
    }

    @Override
    public void markOffhandMiningAnimation(int ticks) {
        EntityPlayer player = (EntityPlayer) (Object) this;
        this.offhand$clientMiningAnimationUntilTick = player.ticksExisted + Math.max(0, ticks);
    }

    @Override
    public boolean shouldAnimateOffhandMining() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        return player.ticksExisted <= this.offhand$clientMiningAnimationUntilTick;
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

        if (!this.offhand$shouldRouteHeldItemMutationToOffhand(player, offhandSlot)) {
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

        if (!this.offhand$shouldRouteHeldItemMutationToOffhand(player, offhandSlot)) {
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
    private boolean offhand$shouldRouteHeldItemMutationToOffhand(EntityPlayer player, int offhandSlot) {
        // Blocking tools can retain use state for 72000 ticks. Only the scoped call site may choose the offhand.
        return offhandSlot >= 0
            && offhandSlot < player.inventory.mainInventory.length
            && OffhandUtils.isOffhandItemUseContext(player);
    }

    @Redirect(
        method = "onItemUseFinish()V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/ItemStack;onItemUseFinish(Lnet/minecraft/World;Lnet/minecraft/EntityPlayer;)V"))
    private void offhand$finishItemInCorrectHand(ItemStack stack, World world, EntityPlayer player) {
        if (stack == OffhandUtils.getOffhandItem(player)) {
            OffhandUtils.useOffhandItem(player, false, () -> {
                stack.onItemUseFinish(world, player);
            });
            return;
        }

        stack.onItemUseFinish(world, player);
    }

    @Redirect(
        method = "stopUsingItem(Z)V",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/ItemStack;onPlayerStoppedUsing(Lnet/minecraft/World;Lnet/minecraft/EntityPlayer;I)V"))
    private void offhand$stopUsingItemInCorrectHand(
        ItemStack stack,
        World world,
        EntityPlayer player,
        int itemInUseCount) {
        if (stack == OffhandUtils.getOffhandItem(player)) {
            OffhandUtils.useOffhandItem(player, false, () -> {
                stack.onPlayerStoppedUsing(world, player, itemInUseCount);
            });
            return;
        }

        stack.onPlayerStoppedUsing(world, player, itemInUseCount);
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

    @Unique
    private int offhand$getArmSwingAnimationEnd() {
        EntityPlayer player = (EntityPlayer) (Object) this;
        int duration = 6;

        PotionEffect haste = player.getActivePotionEffect(Potion.digSpeed);
        if (haste != null) {
            duration = 6 - (haste.getAmplifier() + 1);
        } else {
            PotionEffect fatigue = player.getActivePotionEffect(Potion.digSlowdown);
            if (fatigue != null) {
                duration = 6 + (fatigue.getAmplifier() + 1) * 2;
            }
        }

        return duration < 1 ? 1 : duration;
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
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        boolean placingFromOffhand = OffhandUtils.isClientOffhandUseContext()
            || player.inventory.currentItem == offhandSlot;

        if (placingFromOffhand) {
            ((IOffhandPlayer) player).swingOffItem();
            return;
        }

        player.swingArm();
    }
}
