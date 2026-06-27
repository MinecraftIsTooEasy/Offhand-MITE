package com.m.offhand.mixin;

import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.api.core.OffhandSlot;
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

        // Move offhand into real player inventory slots only. Exclude the offhand slot itself.
        if (!this.offhand$mergeIntoPlayerInventory(player, source)) {
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

    @Unique
    private boolean offhand$mergeIntoPlayerInventory(EntityPlayer player, ItemStack source) {
        if (player == null || player.inventory == null || source == null || source.stackSize <= 0) {
            return false;
        }

        boolean changed = false;
        int offhandInventorySlot = ((IOffhandInventory) player.inventory).getOffhandSlot();

        changed |= this.offhand$mergeIntoExistingPlayerStacks(player, source, offhandInventorySlot);
        if (source.stackSize > 0) {
            changed |= this.offhand$moveIntoEmptyPlayerSlot(player, source, offhandInventorySlot);
        }

        return changed;
    }

    @Unique
    private boolean offhand$mergeIntoExistingPlayerStacks(EntityPlayer player, ItemStack source, int offhandInventorySlot) {
        boolean changed = false;

        for (Object raw : this.inventorySlots) {
            if (source.stackSize <= 0) {
                break;
            }
            if (!(raw instanceof Slot targetSlot)) {
                continue;
            }
            if (!this.offhand$isMainInventorySlot(player, targetSlot, offhandInventorySlot)) {
                continue;
            }

            ItemStack targetStack = targetSlot.getStack();
            if (!this.offhand$canMergeStacks(source, targetStack) || !targetSlot.isItemValid(source)) {
                continue;
            }

            int limit = Math.min(targetStack.getMaxStackSize(), targetSlot.getSlotStackLimit());
            int room = limit - targetStack.stackSize;
            if (room <= 0) {
                continue;
            }

            int moved = Math.min(source.stackSize, room);
            targetStack.stackSize += moved;
            source.stackSize -= moved;
            targetSlot.onSlotChanged();
            changed = true;
        }

        return changed;
    }

    @Unique
    private boolean offhand$moveIntoEmptyPlayerSlot(EntityPlayer player, ItemStack source, int offhandInventorySlot) {
        for (Object raw : this.inventorySlots) {
            if (source.stackSize <= 0) {
                break;
            }
            if (!(raw instanceof Slot targetSlot)) {
                continue;
            }
            if (!this.offhand$isMainInventorySlot(player, targetSlot, offhandInventorySlot)) {
                continue;
            }
            if (targetSlot.getHasStack() || !targetSlot.isItemValid(source)) {
                continue;
            }

            int moved = Math.min(source.stackSize, Math.min(source.getMaxStackSize(), targetSlot.getSlotStackLimit()));
            ItemStack movedStack = source.copy();
            movedStack.stackSize = moved;
            targetSlot.putStack(movedStack);
            targetSlot.onSlotChanged();
            source.stackSize -= moved;
            return true;
        }

        return false;
    }

    @Unique
    private boolean offhand$isMainInventorySlot(EntityPlayer player, Slot slot, int offhandInventorySlot) {
        if (slot == null || slot.inventory != player.inventory || player.inventory.mainInventory == null) {
            return false;
        }

        for (int inventorySlot = 0; inventorySlot < player.inventory.mainInventory.length; inventorySlot++) {
            if (inventorySlot == offhandInventorySlot) {
                continue;
            }
            if (slot.isSlotInInventory(player.inventory, inventorySlot)) {
                return true;
            }
        }

        return false;
    }

    @Unique
    private boolean offhand$canMergeStacks(ItemStack source, ItemStack target) {
        return source != null
            && target != null
            && target.itemID == source.itemID
            && (!source.getHasSubtypes() || target.getItemDamage() == source.getItemDamage())
            && ItemStack.areItemStackTagsEqual(source, target);
    }
}
