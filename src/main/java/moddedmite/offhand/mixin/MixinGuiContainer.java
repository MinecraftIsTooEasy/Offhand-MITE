package moddedmite.offhand.mixin;

import moddedmite.offhand.api.core.IOffhandInventory;
import moddedmite.offhand.client.OffhandKeyHandler;
import moddedmite.offhand.network.OffhandContainerSwapPacket;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.Container;
import net.minecraft.EntityPlayer;
import net.minecraft.GuiContainer;
import net.minecraft.GuiContainerCreative;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.Slot;
import org.lwjgl.input.Mouse;
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

    @Shadow
    public abstract Slot getSlotThatMouseIsOver(int mouseX, int mouseY);

    @Inject(method = "keyTyped", at = @At("HEAD"), cancellable = true)
    private void offhand$swapHoveredSlotToOffhand(char typedChar, int keyCode, CallbackInfo ci) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.thePlayer == null) {
            return;
        }

        if (!OffhandKeyHandler.SWAP_KEY.matches(keyCode)) {
            return;
        }

        Slot hoveredSlot = this.offhand$getHoveredSlot(mc);
        if (hoveredSlot == null || !hoveredSlot.func_111238_b()) {
            return;
        }

        EntityPlayer player = mc.thePlayer;
        if (player.inventory.getItemStack() != null) {
            return;
        }

        if ((Object) this instanceof GuiContainerCreative) {
            if (this.offhand$swapCreativeInventorySlot(player, hoveredSlot)) {
                ci.cancel();
            }
            return;
        }

        if (this.inventorySlots == null
            || hoveredSlot.slotNumber < 0
            || hoveredSlot.slotNumber >= this.inventorySlots.inventorySlots.size()) {
            return;
        }

        ItemStack hoveredStack = hoveredSlot.getStack();
        ItemStack offhandStack = ((IOffhandInventory) player.inventory).getOffhandItem();
        if (hoveredStack == null && offhandStack == null) {
            ci.cancel();
            return;
        }

        Network.sendToServer(new OffhandContainerSwapPacket(
            this.inventorySlots.windowId,
            hoveredSlot.slotNumber));
        ci.cancel();
    }

    @Unique
    private Slot offhand$getHoveredSlot(Minecraft mc) {
        GuiContainer screen = (GuiContainer) (Object) this;
        if (mc.displayWidth <= 0 || mc.displayHeight <= 0) {
            return this.theSlot;
        }

        int mouseX = Mouse.getX() * screen.width / mc.displayWidth;
        int mouseY = screen.height - Mouse.getY() * screen.height / mc.displayHeight - 1;
        return this.getSlotThatMouseIsOver(mouseX, mouseY);
    }

    @Unique
    private boolean offhand$swapCreativeInventorySlot(EntityPlayer player, Slot hoveredSlot) {
        if (hoveredSlot.inventory != player.inventory) {
            return false;
        }

        int offhandInventorySlot = ((IOffhandInventory) player.inventory).getOffhandSlot();
        Slot offhandSlot = this.offhand$findOffhandContainerSlot(player, offhandInventorySlot);
        if (offhandSlot == null) {
            return false;
        }

        if (hoveredSlot.slotNumber == offhandSlot.slotNumber) {
            return true;
        }

        ItemStack hoveredStack = hoveredSlot.getStack();
        ItemStack offhandStack = offhandSlot.getStack();
        boolean hoveredHasItem = hoveredStack != null && hoveredStack.stackSize > 0;
        boolean offhandHasItem = offhandStack != null && offhandStack.stackSize > 0;

        if (!hoveredHasItem && !offhandHasItem) {
            return true;
        }

        if (hoveredHasItem) {
            if (!offhandSlot.isItemValid(hoveredStack) || !hoveredSlot.canTakeStack(player)) {
                return true;
            }
        }

        if (offhandHasItem) {
            if (!hoveredSlot.isItemValid(offhandStack) || !offhandSlot.canTakeStack(player)) {
                return true;
            }
        }

        this.handleMouseClick(offhandSlot, offhandSlot.slotNumber, 0, 0);
        this.handleMouseClick(hoveredSlot, hoveredSlot.slotNumber, 0, 0);
        if (player.inventory.getItemStack() != null) {
            this.handleMouseClick(offhandSlot, offhandSlot.slotNumber, 0, 0);
        }
        return true;
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
