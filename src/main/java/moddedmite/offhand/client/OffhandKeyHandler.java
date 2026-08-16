package moddedmite.offhand.client;

import moddedmite.offhand.api.compat.OffhandCompatRegistry;
import moddedmite.offhand.api.core.OffhandUtils;
import moddedmite.offhand.network.OffhandSwapRequestPacket;
import moddedmite.rustedironcore.api.event.listener.ITickListener;
import moddedmite.rustedironcore.api.keybinding.KeybindingV1;
import moddedmite.rustedironcore.network.Network;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.*;
import org.lwjgl.input.Keyboard;

@Environment(EnvType.CLIENT)
public class OffhandKeyHandler implements ITickListener {

    private static final int SWAP_ANIMATION_TICKS = 5;
    private static final int SWAP_REQUEST_TIMEOUT_TICKS = 40;

    private static int pendingMainhandContainerSlot = -1;
    private static int pendingOffhandContainerSlot = -1;
    private static int pendingMainhandInventorySlot = -1;
    private static int pendingOffhandInventorySlot = -1;
    private static int pendingCurrentHotbarSlot = -1;
    private static int pendingSwapTicks;
    private static int pendingMainhandAnimationTicks;
    private static int pendingOffhandAnimationTicks;
    private static boolean pendingMainhandConfirmed;
    private static boolean pendingOffhandConfirmed;
    private static ItemStack pendingMainhandStack;
    private static ItemStack pendingOffhandStack;

    public static final KeybindingV1 SWAP_KEY = new KeybindingV1(
            "key.offhand.swap",
            Keyboard.KEY_F,
            KeybindingV1.Category.INVENTORY);

    private boolean swapKeyDownLastTick = false;

    @Override
    public void onClientTick(Minecraft client) {
        if (client.thePlayer == null) {
            this.swapKeyDownLastTick = false;
            clearPendingSwapAnimation();
            return;
        }

        if (pendingSwapTicks > 0
            && this.pendingCurrentHotbarSlot >= 0
            && client.thePlayer.inventory.currentItem != this.pendingCurrentHotbarSlot) {
            clearPendingSwapAnimation();
        } else {
            applyPendingSwapAnimationsToInventory(client.thePlayer);
            tickPendingSwapAnimation();
        }

        boolean isSwapKeyDown = Keyboard.isKeyDown(SWAP_KEY.keyCode);
        if (client.currentScreen != null || client.imposed_gui_chat != null) {
            // Inventory GUI swaps are handled by GuiContainer.
            // Imposed chat can be active while currentScreen is still null, so block swap there too.
            this.swapKeyDownLastTick = isSwapKeyDown;
            return;
        }

        if (isSwapKeyDown && !this.swapKeyDownLastTick) {
            if (!OffhandCompatRegistry.getActionFilter().cancelOffhandSwapKey(
                    client.thePlayer,
                    client.thePlayer.inventory.getCurrentItemStack(),
                    OffhandUtils.getOffhandItem(client.thePlayer))) {
                requestSwap(client);
            }
        }
        this.swapKeyDownLastTick = isSwapKeyDown;
    }

    private void requestSwap(Minecraft client) {
        if (client.playerController != null) {
            // Preserve packet order: the server must know the selected hotbar slot before swapping.
            client.playerController.syncCurrentPlayItem();
        }
        prepareSwapAnimations(client.thePlayer);
        Network.sendToServer(new OffhandSwapRequestPacket());
    }

    public static void applyPendingSwapAnimations(Packet103SetSlot packet) {
        if (packet == null
            || pendingSwapTicks <= 0
            || packet.windowId != 0
            || packet.myItemStack == null) {
            return;
        }

        if (pendingMainhandStack != null
            && packet.itemSlot == pendingMainhandContainerSlot
            && ItemStack.areItemStacksEqual(pendingMainhandStack, packet.myItemStack)) {
            confirmMainhandAnimation(packet.myItemStack);
        }

        if (pendingOffhandStack != null
            && packet.itemSlot == pendingOffhandContainerSlot
            && ItemStack.areItemStacksEqual(pendingOffhandStack, packet.myItemStack)) {
            confirmOffhandAnimation(packet.myItemStack);
        }
    }

    /**
     * MITE also sends full-inventory Packet5 updates when InventoryPlayer changes.
     * Those packets bypass the vanilla Container slot animation path.
     */
    public static void applyPendingSwapAnimations(Packet5PlayerInventory packet) {
        Minecraft client = Minecraft.getMinecraft();
        if (packet == null
            || client == null
            || client.thePlayer == null
            || pendingSwapTicks <= 0
            || packet.entityID != client.thePlayer.entityId
            || !packet.full_inventory
            || packet.getItemSlot() == null) {
            return;
        }

        if (pendingMainhandStack != null
            && packet.slot == pendingMainhandInventorySlot
            && ItemStack.areItemStacksEqual(pendingMainhandStack, packet.getItemSlot())) {
            confirmMainhandAnimation(packet.getItemSlot());
        }

        if (pendingOffhandStack != null
            && packet.slot == pendingOffhandInventorySlot
            && ItemStack.areItemStacksEqual(pendingOffhandStack, packet.getItemSlot())) {
            confirmOffhandAnimation(packet.getItemSlot());
        }
    }

    /**
     * A complete window resync can follow a slot update. Mark its final stacks too,
     * otherwise putStacksInSlots would replace the animated stack with a fresh copy.
     */
    public static void applyPendingSwapAnimations(Packet104WindowItems packet) {
        if (packet == null
            || pendingSwapTicks <= 0
            || packet.windowId != 0
            || packet.itemStack == null) {
            return;
        }

        if (pendingMainhandStack != null
            && pendingMainhandContainerSlot >= 0
            && pendingMainhandContainerSlot < packet.itemStack.length) {
            ItemStack stack = packet.itemStack[pendingMainhandContainerSlot];
            if (stack != null && ItemStack.areItemStacksEqual(pendingMainhandStack, stack)) {
                confirmMainhandAnimation(stack);
            }
        }

        if (pendingOffhandStack != null
            && pendingOffhandContainerSlot >= 0
            && pendingOffhandContainerSlot < packet.itemStack.length) {
            ItemStack stack = packet.itemStack[pendingOffhandContainerSlot];
            if (stack != null && ItemStack.areItemStacksEqual(pendingOffhandStack, stack)) {
                confirmOffhandAnimation(stack);
            }
        }
    }

    private static void applyPendingSwapAnimationsToInventory(EntityPlayer player) {
        if (player == null || pendingSwapTicks <= 0) {
            return;
        }

        if (pendingMainhandStack != null
            && pendingMainhandInventorySlot >= 0
            && pendingMainhandInventorySlot < player.inventory.mainInventory.length) {
            ItemStack stack = player.inventory.mainInventory[pendingMainhandInventorySlot];
            if (stack != null && ItemStack.areItemStacksEqual(pendingMainhandStack, stack)) {
                confirmMainhandAnimation(stack);
            }
        }

        if (pendingOffhandStack != null
            && pendingOffhandInventorySlot >= 0
            && pendingOffhandInventorySlot < player.inventory.mainInventory.length) {
            ItemStack stack = player.inventory.mainInventory[pendingOffhandInventorySlot];
            if (stack != null && ItemStack.areItemStacksEqual(pendingOffhandStack, stack)) {
                confirmOffhandAnimation(stack);
            }
        }
    }

    private static void confirmMainhandAnimation(ItemStack stack) {
        if (!pendingMainhandConfirmed) {
            pendingMainhandConfirmed = true;
            pendingMainhandAnimationTicks = SWAP_ANIMATION_TICKS;
            resetMainhandEquippedProgress();
        }
        if (stack != null && pendingMainhandAnimationTicks > 0) {
            stack.animationsToGo = Math.max(stack.animationsToGo, pendingMainhandAnimationTicks);
        }
    }

    private static void confirmOffhandAnimation(ItemStack stack) {
        if (!pendingOffhandConfirmed) {
            pendingOffhandConfirmed = true;
            pendingOffhandAnimationTicks = SWAP_ANIMATION_TICKS;
        }
        if (stack != null && pendingOffhandAnimationTicks > 0) {
            stack.animationsToGo = Math.max(stack.animationsToGo, pendingOffhandAnimationTicks);
        }
    }

    private static void prepareSwapAnimations(EntityPlayer player) {
        clearPendingSwapAnimation();
        if (player == null || player.inventory == null || player.inventoryContainer == null) {
            return;
        }

        ItemStack mainhandStack = player.inventory.getCurrentItemStack();
        ItemStack offhandStack = OffhandUtils.getOffhandItem(player);
        boolean animateMainhand = isRenderableStack(offhandStack);
        boolean animateOffhand = isRenderableStack(mainhandStack);
        if (!animateMainhand && !animateOffhand) {
            return;
        }

        int currentSlot = player.inventory.currentItem;
        int offhandSlot = OffhandUtils.getOffhandSlot(player);
        pendingCurrentHotbarSlot = currentSlot;
        for (Object rawSlot : player.inventoryContainer.inventorySlots) {
            if (!(rawSlot instanceof Slot slot)) {
                continue;
            }

            if (animateMainhand && slot.isSlotInInventory(player.inventory, currentSlot)) {
                pendingMainhandContainerSlot = slot.slotNumber;
                pendingMainhandInventorySlot = currentSlot;
                pendingMainhandStack = offhandStack.copy();
            } else if (animateOffhand && slot.isSlotInInventory(player.inventory, offhandSlot)) {
                pendingOffhandContainerSlot = slot.slotNumber;
                pendingOffhandInventorySlot = offhandSlot;
                pendingOffhandStack = mainhandStack.copy();
            }
        }

        if (pendingMainhandStack != null || pendingOffhandStack != null) {
            pendingSwapTicks = SWAP_REQUEST_TIMEOUT_TICKS;
        }
    }

    private static boolean isRenderableStack(ItemStack stack) {
        return stack != null && stack.stackSize > 0 && stack.getItem() != null;
    }

    private static void resetMainhandEquippedProgress() {
        Minecraft client = Minecraft.getMinecraft();
        if (client != null && client.entityRenderer != null && client.entityRenderer.itemRenderer != null) {
            client.entityRenderer.itemRenderer.resetEquippedProgress();
        }
    }

    private static void tickPendingSwapAnimation() {
        if (pendingSwapTicks > 0) {
            --pendingSwapTicks;
        }
        if (pendingMainhandAnimationTicks > 0) {
            --pendingMainhandAnimationTicks;
        }
        if (pendingOffhandAnimationTicks > 0) {
            --pendingOffhandAnimationTicks;
        }

        boolean mainhandComplete = pendingMainhandStack == null
            || pendingMainhandConfirmed && pendingMainhandAnimationTicks <= 0;
        boolean offhandComplete = pendingOffhandStack == null
            || pendingOffhandConfirmed && pendingOffhandAnimationTicks <= 0;
        if (pendingSwapTicks <= 0 || mainhandComplete && offhandComplete) {
            clearPendingSwapAnimation();
        }
    }

    private static void clearPendingSwapAnimation() {
        pendingMainhandContainerSlot = -1;
        pendingOffhandContainerSlot = -1;
        pendingMainhandInventorySlot = -1;
        pendingOffhandInventorySlot = -1;
        pendingCurrentHotbarSlot = -1;
        pendingSwapTicks = 0;
        pendingMainhandAnimationTicks = 0;
        pendingOffhandAnimationTicks = 0;
        pendingMainhandConfirmed = false;
        pendingOffhandConfirmed = false;
        pendingMainhandStack = null;
        pendingOffhandStack = null;
    }
}
