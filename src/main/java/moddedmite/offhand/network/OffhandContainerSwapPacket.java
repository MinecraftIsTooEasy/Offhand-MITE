package moddedmite.offhand.network;

import moddedmite.offhand.Offhand;
import moddedmite.offhand.api.compat.OffhandCompatRegistry;
import moddedmite.offhand.api.core.IOffhandPlayer;
import moddedmite.offhand.api.core.OffhandUtils;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.Container;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;
import net.minecraft.Slot;

public final class OffhandContainerSwapPacket implements Packet {

    public static final ResourceLocation CHANNEL = new ResourceLocation("offhand", "container_swap");

    private final boolean compatible;
    private int windowId;
    private int slotNumber;

    public OffhandContainerSwapPacket(PacketByteBuf buf) {
        int protocolVersion = buf.readInt();
        this.compatible = OffhandPacketHandler.isCompatible(protocolVersion);
        if (!this.compatible) {
            this.windowId = -1;
            this.slotNumber = -1;
            OffhandPacketHandler.warnProtocolMismatch(CHANNEL.toString(), protocolVersion);
            return;
        }

        this.windowId = buf.readInt();
        this.slotNumber = buf.readInt();
    }

    public OffhandContainerSwapPacket(int windowId, int slotNumber) {
        this.compatible = true;
        this.windowId = windowId;
        this.slotNumber = slotNumber;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(OffhandPacketHandler.PROTOCOL_VERSION);
        buf.writeInt(this.windowId);
        buf.writeInt(this.slotNumber);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (!this.compatible
            || !(entityPlayer instanceof ServerPlayer player)
            || player.worldObj == null
            || player.worldObj.isRemote
            || player.inventory == null) {
            return;
        }

        Container container = player.openContainer;
        if (container == null
            || container.windowId != this.windowId
            || this.slotNumber < 0
            || this.slotNumber >= container.inventorySlots.size()
            || !container.isPlayerNotUsingContainer(player)
            || !container.canInteractWith(player)
            || player.inventory.getItemStack() != null) {
            return;
        }

        Slot targetSlot = container.getSlot(this.slotNumber);
        if (targetSlot == null
            || targetSlot.slotNumber != this.slotNumber
            || !targetSlot.func_111238_b()
            || this.isOffhandSlot(player, targetSlot)) {
            return;
        }

        ItemStack targetStack = targetSlot.getStack();
        ItemStack offhandStack = OffhandUtils.getOffhandItem(player);
        if (targetStack == null && offhandStack == null) {
            return;
        }

        if (Offhand.isOffhandBlacklisted(targetStack)
            || Offhand.isOffhandBlacklisted(offhandStack)
            || targetStack != null && (targetStack.stackSize <= 0 || targetStack.stackSize > 64)
            || offhandStack != null && offhandStack.stackSize <= 0) {
            Offhand.debug("[Offhand-Server] Rejected invalid container/offhand swap");
            OffhandCompatRegistry.syncOffhandItemToPlayer(player, player);
            return;
        }

        if (OffhandCompatRegistry.getActionFilter().cancelOffhandSwapPacket(
                player,
                player.inventory.getCurrentItemStack(),
                offhandStack)) {
            return;
        }

        if (targetStack != null && !targetSlot.canTakeStack(player)) {
            return;
        }

        if (offhandStack != null
            && (!targetSlot.isItemValid(offhandStack)
                || offhandStack.stackSize > targetSlot.getSlotStackLimit())) {
            return;
        }

        IOffhandPlayer offhandPlayer = (IOffhandPlayer) (EntityPlayer) player;
        boolean wasUsingItem = player.isUsingItem() || offhandPlayer.isOffhandItemInUse();
        offhandPlayer.cancelActiveItemUse();
        if (wasUsingItem) {
            OffhandCancelUsage.sendToClient(player);
        }

        if (targetStack == null) {
            OffhandUtils.setPlayerOffhandItem(player, null);
            targetSlot.putStack(offhandStack);
        } else {
            ItemStack removedStack = targetSlot.decrStackSize(targetStack.stackSize);
            if (removedStack == null || removedStack.stackSize <= 0) {
                return;
            }

            targetSlot.putStack(offhandStack);
            OffhandUtils.setPlayerOffhandItem(player, removedStack);
            targetSlot.onPickupFromSlot(player, removedStack);
        }

        container.detectAndSendChanges();
        OffhandCompatRegistry.syncOffhandItem(player);
        OffhandCompatRegistry.syncOffhandItemToPlayer(player, player);
    }

    private boolean isOffhandSlot(ServerPlayer player, Slot slot) {
        return slot.isSlotInInventory(player.inventory, OffhandUtils.getOffhandSlot(player));
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}
