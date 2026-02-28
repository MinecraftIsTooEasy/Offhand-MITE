package com.m.offhand.mixin;

import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.client.OffhandKeyHandler;
import net.minecraft.Container;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiContainer;
import net.minecraft.GuiContainerCreative;
import net.minecraft.GuiInventory;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(GuiContainer.class)
public abstract class MixinGuiContainer {

    @Shadow
    public Container inventorySlots;

    @Shadow
    private Slot theSlot;

    @Shadow
    protected abstract void handleMouseClick(Slot slot, int slotNumber, int button, int mode);

    @Inject(method = "keyTyped", at = @At("HEAD"), cancellable = true)
    private void offhand$swapHoveredSlotToOffhand(char typedChar, int keyCode, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.thePlayer == null) {
            return;
        }

        if (keyCode != OffhandKeyHandler.SWAP_KEY.keyCode) {
            return;
        }

        Object self = this;
        if (!(self instanceof GuiInventory) && !(self instanceof GuiContainerCreative)) {
            return;
        }

        if (this.theSlot == null || !this.theSlot.func_111238_b()) {
            return;
        }

        EntityPlayer player = mc.thePlayer;
        if (player.inventory.getItemStack() != null) {
            return;
        }

        if (this.theSlot.inventory != player.inventory) {
            return;
        }

        int offhandInventorySlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        Slot offhandSlot = this.offhand$findOffhandContainerSlot(player, offhandInventorySlot);
        if (offhandSlot == null) {
            return;
        }

        if (this.theSlot.slotNumber == offhandSlot.slotNumber) {
            ci.cancel();
            return;
        }

        ItemStack hoveredStack = this.theSlot.getStack();
        if (hoveredStack == null || hoveredStack.stackSize <= 0) {
            ci.cancel();
            return;
        }

        if (!offhandSlot.isItemValid(hoveredStack) || !this.theSlot.canTakeStack(player)) {
            ci.cancel();
            return;
        }

        ItemStack offhandStack = offhandSlot.getStack();
        if (offhandStack != null && !this.theSlot.isItemValid(offhandStack)) {
            ci.cancel();
            return;
        }

        this.handleMouseClick(offhandSlot, offhandSlot.slotNumber, 0, 0);
        this.handleMouseClick(this.theSlot, this.theSlot.slotNumber, 0, 0);
        this.handleMouseClick(offhandSlot, offhandSlot.slotNumber, 0, 0);
        ci.cancel();
    }

    @Unique
    private Slot offhand$findOffhandContainerSlot(EntityPlayer player, int offhandInventorySlot) {
        List slots = this.inventorySlots.inventorySlots;
        for (int i = 0; i < slots.size(); i++) {
            Object raw = slots.get(i);
            if (!(raw instanceof Slot)) {
                continue;
            }

            Slot slot = (Slot) raw;
            if (slot != null && slot.isSlotInInventory(player.inventory, offhandInventorySlot)) {
                return slot;
            }
        }
        return null;
    }
}
