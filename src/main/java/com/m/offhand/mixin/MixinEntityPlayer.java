package com.m.offhand.mixin;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandUtils;
import net.minecraft.DamageSource;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemDamageResult;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.ServerPlayer;
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
    private int offhand$mainhandSlot = 0;

    @Shadow
    protected ItemStack itemInUse;

    @Override
    public void swingOffItem() {
        if (!this.offhand$isOffHandSwingInProgress
            || this.offhand$offHandSwingProgressInt >= this.offhand$getArmSwingAnimationEnd() / 2
            || this.offhand$offHandSwingProgressInt < 0) {
            this.offhand$offHandSwingProgressInt = -1;
            this.offhand$isOffHandSwingInProgress = true;

            EntityPlayer player = (EntityPlayer) (Object) this;
            if (!player.worldObj.isRemote && player instanceof ServerPlayer) {
                OffhandCompatRegistry.getSyncStrategy().syncOffhandAnimation(player);
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
    }

    @Override
    public void setOffhandItemInUse(boolean usingOffhand) {
        this.offhand$isOffhandItemInUse = usingOffhand;
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

    @Inject(method = "setHeldItemInUse", at = @At("RETURN"))
    private void offhand$onSetHeldItemInUse(CallbackInfoReturnable<Boolean> cir) {
        if (!Boolean.TRUE.equals(cir.getReturnValue())) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        boolean usingOffhand = player.inventory.currentItem == offhandSlot;

        if (this.offhand$isOffhandItemInUse == usingOffhand) {
            return;
        }

        this.offhand$isOffhandItemInUse = usingOffhand;

        if (usingOffhand && !player.worldObj.isRemote && player instanceof ServerPlayer) {
            OffhandCompatRegistry.getSyncStrategy().syncOffhandUseState(player, true);
        }
    }

    @Inject(method = "clearItemInUse", at = @At("TAIL"))
    private void offhand$onClearItemInUse(CallbackInfo ci) {
        if (!this.offhand$isOffhandItemInUse) {
            return;
        }

        this.offhand$isOffhandItemInUse = false;
        EntityPlayer player = (EntityPlayer) (Object) this;
        if (!player.worldObj.isRemote && player instanceof ServerPlayer) {
            OffhandCompatRegistry.getSyncStrategy().syncOffhandUseState(player, false);
        }
    }

    @Inject(method = "getItemInUseCount", at = @At("HEAD"), cancellable = true)
    private void offhand$suppressMainhandUseAnimationDuringOffhandRender(CallbackInfoReturnable<Integer> cir) {
        if (!OffhandUtils.isClientOffhandRenderContext()) {
            return;
        }

        // First-person offhand render mirrors current slot for drawing.
        // If only mainhand is using food/drink, do not reuse mainhand use-count
        // for offhand transforms, otherwise both hands show ingestion animation.
        if (!this.offhand$isOffhandItemInUse) {
            cir.setReturnValue(0);
        }
    }

    @Inject(method = "setHeldItemStack", at = @At("HEAD"), cancellable = true)
    private void offhand$setHeldItemStackToOffhandWhenUsing(ItemStack stack, CallbackInfo ci) {
        if (!this.offhand$isOffhandItemInUse) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot >= 0 && offhandSlot < player.inventory.mainInventory.length) {
            player.inventory.setInventorySlotContents(offhandSlot, stack);
            ci.cancel();
        }
    }

    @Inject(method = "convertOneOfHeldItem", at = @At("HEAD"), cancellable = true)
    private void offhand$convertOneOfOffhandItem(ItemStack createdItemStack, CallbackInfo ci) {
        if (!this.offhand$isOffhandItemInUse) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        int oldSlot = player.inventory.currentItem;
        try {
            player.inventory.currentItem = offhandSlot;
            player.inventory.convertOneOfCurrentItem(createdItemStack);
        } finally {
            player.inventory.currentItem = oldSlot;
        }
        ci.cancel();
    }

    @Inject(method = "tryDamageHeldItem", at = @At("HEAD"), cancellable = true)
    private void offhand$tryDamageOffhandHeldItem(DamageSource damageSource, int amount, CallbackInfoReturnable<ItemDamageResult> cir) {
        if (!this.offhand$isOffhandItemInUse) {
            return;
        }

        EntityPlayer player = (EntityPlayer) (Object) this;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return;
        }

        if (player.inCreativeMode()) {
            cir.setReturnValue(null);
            return;
        }

        ItemStack offhandStack = player.inventory.mainInventory[offhandSlot];
        if (offhandStack == null) {
            cir.setReturnValue(null);
            return;
        }

        int oldSlot = player.inventory.currentItem;
        ItemDamageResult result;
        try {
            player.inventory.currentItem = offhandSlot;
            result = offhandStack.tryDamageItem(damageSource, amount, player);
        } finally {
            player.inventory.currentItem = oldSlot;
        }

        cir.setReturnValue(result);
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
        if (!OffhandUtils.isClientOffhandUseContext()) {
            player.swingArm();
        }
    }
}
