package com.m.offhand.mixin;

import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.api.core.OffhandSlot;
import com.m.offhand.api.core.OffhandSlotDef;
import net.minecraft.Container;
import net.minecraft.ContainerPlayer;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ContainerPlayer.class)
public abstract class MixinContainerPlayer extends Container {

    @Unique
    private int offhand$containerSlot = -1;

    protected MixinContainerPlayer(EntityPlayer player) {
        super(player);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void offhand$appendOffhandSlot(EntityPlayer player, CallbackInfo ci) {
        int inventorySlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        Slot slot = this.addSlotToContainer(new OffhandSlot(player.inventory, inventorySlot, 80, 65));
        this.offhand$containerSlot = slot.slotNumber;
    }

    @Inject(method = "transferStackInSlot", at = @At("HEAD"), cancellable = true)
    private void offhand$transferOffhandSlot(EntityPlayer player, int slotIndex, CallbackInfoReturnable<ItemStack> cir) {
        if (slotIndex != this.offhand$containerSlot) {
            return;
        }

        if (slotIndex < 0 || slotIndex >= this.inventorySlots.size()) {
            cir.setReturnValue(null);
            return;
        }

        Slot slot = (Slot) this.inventorySlots.get(slotIndex);
        if (slot == null || !slot.getHasStack()) {
            cir.setReturnValue(null);
            return;
        }

        ItemStack source = slot.getStack();
        ItemStack copy = source.copy();

        // Move offhand -> player inventory + hotbar only. Exclude offhand container slot.
        if (!this.mergeItemStack(source, OffhandSlotDef.PLAYER_INV_START, this.offhand$containerSlot, false)) {
            cir.setReturnValue(null);
            return;
        }

        if (source.stackSize == 0) {
            slot.putStack((ItemStack) null);
        } else {
            slot.onSlotChanged();
        }

        if (source.stackSize == copy.stackSize) {
            cir.setReturnValue(null);
            return;
        }

        slot.onPickupFromSlot(player, source);
        cir.setReturnValue(copy);
    }
}
