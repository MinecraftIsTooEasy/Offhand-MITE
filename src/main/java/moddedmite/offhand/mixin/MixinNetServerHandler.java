package moddedmite.offhand.mixin;

import moddedmite.offhand.Offhand;
import moddedmite.offhand.api.compat.OffhandCompatRegistry;
import moddedmite.offhand.api.core.IOffhandInventory;
import moddedmite.offhand.api.core.IOffhandPlayer;
import moddedmite.offhand.api.core.OffhandUtils;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.NetServerHandler;
import net.minecraft.Packet107CreativeSetSlot;
import net.minecraft.Packet16BlockItemSwitch;
import net.minecraft.Packet81RightClick;
import net.minecraft.RaycastCollision;
import net.minecraft.RightClickFilter;
import net.minecraft.ServerPlayer;
import net.minecraft.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetServerHandler.class)
public abstract class MixinNetServerHandler {

    @Unique
    private boolean offhand$handlingOffhandRightClick;

    @Unique
    private int offhand$rightClickMainhandSlot = -1;

    @Unique
    private ItemStack offhand$rightClickMainhandStack;

    @Unique
    private ItemStack offhand$rightClickOffhandStack;

    @Shadow
    public ServerPlayer playerEntity;

    @Inject(method = "handleRightClick", at = @At("HEAD"), cancellable = true)
    private void offhand$selectOffhandForRightClick(Packet81RightClick packet, CallbackInfo ci) {
        this.offhand$restoreMainhandAfterRightClick();

        ServerPlayer player = this.playerEntity;
        if (packet == null || player == null || player.inventory == null) {
            return;
        }

        int offhandSlot = OffhandUtils.getOffhandSlot(player);
        if (packet.slot_index != offhandSlot) {
            return;
        }

        ItemStack offhandStack = OffhandUtils.getOffhandItem(player);
        if (offhandStack == null
            || offhandStack.stackSize <= 0
            || offhandStack.getItem() == null
            || packet.item_id != offhandStack.itemID
            || Offhand.isOffhandBlacklisted(offhandStack)) {
            Offhand.debug("[Offhand-Server] Rejected invalid offhand right-click packet");
            ci.cancel();
            return;
        }

        int mainhandSlot = player.inventory.currentItem;
        if (mainhandSlot < 0 || mainhandSlot >= InventoryPlayer.getHotbarSize()) {
            Offhand.LOGGER.warn(
                "[Offhand-Server] Rejected offhand right-click with invalid mainhand slot: {}",
                Integer.valueOf(mainhandSlot));
            ci.cancel();
            return;
        }

        ItemStack mainhandStack = player.inventory.getCurrentItemStack();
        if (OffhandCompatRegistry.getActionFilter().cancelOffhandRightClick(
                player,
                mainhandStack,
                offhandStack)) {
            ci.cancel();
            return;
        }

        this.offhand$handlingOffhandRightClick = true;
        this.offhand$rightClickMainhandSlot = mainhandSlot;
        this.offhand$rightClickMainhandStack = mainhandStack;
        this.offhand$rightClickOffhandStack = offhandStack;
        ((IOffhandPlayer) (EntityPlayer) player).setMainhandSlot(mainhandSlot);
        player.inventory.currentItem = offhandSlot;
    }

    @Redirect(
        method = "handleRightClick",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/ServerPlayer;onPlayerRightClickChecked(Lnet/minecraft/RaycastCollision;Lnet/minecraft/RightClickFilter;FZ)Lnet/minecraft/RightClickFilter;"))
    private RightClickFilter offhand$applyServerInteractionPolicy(
        ServerPlayer player,
        RaycastCollision target,
        RightClickFilter filter,
        float partialTicks,
        boolean ctrlDown) {
        if (this.offhand$handlingOffhandRightClick
            && target != null
            && target.isBlock()
            && !OffhandCompatRegistry.getInteractionPolicy().canUseOffhandForBlockInteraction(
                player,
                this.offhand$rightClickMainhandStack,
                this.offhand$rightClickOffhandStack,
                target)) {
            return filter;
        }

        if (!this.offhand$handlingOffhandRightClick) {
            return player.onPlayerRightClickChecked(target, filter, partialTicks, ctrlDown);
        }

        final RightClickFilter[] result = new RightClickFilter[] {filter};
        OffhandUtils.useOffhandItem(player, false, () -> {
            result[0] = player.onPlayerRightClickChecked(target, filter, partialTicks, ctrlDown);
        });
        return result[0];
    }

    @Inject(method = "handleRightClick", at = @At("RETURN"))
    private void offhand$restoreMainhandAfterRightClick(Packet81RightClick packet, CallbackInfo ci) {
        this.offhand$restoreMainhandAfterRightClick();
    }

    @Unique
    private void offhand$restoreMainhandAfterRightClick() {
        if (this.offhand$handlingOffhandRightClick
            && this.playerEntity != null
            && this.playerEntity.inventory != null
            && this.offhand$rightClickMainhandSlot >= 0
            && this.offhand$rightClickMainhandSlot < InventoryPlayer.getHotbarSize()) {
            this.playerEntity.inventory.currentItem = this.offhand$rightClickMainhandSlot;
            ((IOffhandPlayer) (EntityPlayer) this.playerEntity).setMainhandSlot(
                this.offhand$rightClickMainhandSlot);
        }

        this.offhand$handlingOffhandRightClick = false;
        this.offhand$rightClickMainhandSlot = -1;
        this.offhand$rightClickMainhandStack = null;
        this.offhand$rightClickOffhandStack = null;
    }

    @Inject(method = "handleCreativeSetSlot", at = @At("HEAD"), cancellable = true)
    private void offhand$handleCreativeOffhandSlot(Packet107CreativeSetSlot packet, CallbackInfo ci) {
        ServerPlayer player = this.playerEntity;
        if (packet == null || player == null || player.inventory == null || player.inventoryContainer == null) {
            return;
        }

        Slot offhandSlot = this.offhand$findOffhandContainerSlot(player);
        if (offhandSlot == null || packet.slot != offhandSlot.slotNumber) {
            return;
        }

        // Vanilla rejects slot 45 because it only knows the original player-container range.
        // Handle the appended offhand slot atomically so a creative transfer cannot update the
        // destination while leaving the server-side offhand unchanged and duplicating the stack.
        ci.cancel();

        if (!player.theItemInWorldManager.isCreative()
            || !this.offhand$isValidCreativeOffhandStack(packet.itemStack, offhandSlot)) {
            Offhand.debug("[Offhand-Server] Rejected invalid creative offhand slot update");
            OffhandCompatRegistry.syncOffhandItemToPlayer(player, player);
            return;
        }

        ItemStack stack = packet.itemStack == null ? null : packet.itemStack.copy();
        offhandSlot.putStack(stack);
        player.inventoryContainer.setPlayerIsPresent(player, true);

        OffhandCompatRegistry.syncOffhandItem(player);
        OffhandCompatRegistry.syncOffhandItemToPlayer(player, player);
    }

    @Unique
    private Slot offhand$findOffhandContainerSlot(ServerPlayer player) {
        int offhandInventorySlot = OffhandUtils.getOffhandSlot(player);
        for (Object rawSlot : player.inventoryContainer.inventorySlots) {
            if (rawSlot instanceof Slot slot
                && slot.isSlotInInventory(player.inventory, offhandInventorySlot)) {
                return slot;
            }
        }
        return null;
    }

    @Unique
    private boolean offhand$isValidCreativeOffhandStack(ItemStack stack, Slot offhandSlot) {
        if (stack == null) {
            return true;
        }

        int itemId = stack.itemID;
        return itemId >= 0
            && itemId < Item.itemsList.length
            && Item.itemsList[itemId] != null
            && stack.getItemSubtype() >= 0
            && stack.getItemDamage() >= 0
            && stack.stackSize > 0
            && stack.stackSize <= 64
            && offhandSlot.isItemValid(stack)
            && !Offhand.isOffhandBlacklisted(stack);
    }

    @Inject(method = "handleBlockItemSwitch", at = @At("HEAD"), cancellable = true)
    private void offhand$handleBlockItemSwitch(Packet16BlockItemSwitch packet, CallbackInfo ci) {
        int slot = packet.id;
        EntityPlayer player = this.playerEntity;
        int offhandSlot = ((IOffhandInventory) player.inventory).getOffhandSlot();

        Offhand.debug(
            "[Offhand-Server] handleBlockItemSwitch: slot={}, offhandSlot={}, currentSlot={}",
            Integer.valueOf(slot),
            Integer.valueOf(offhandSlot),
            Integer.valueOf(player.inventory.currentItem));

        if (slot == offhandSlot) {
            Offhand.debug("[Offhand-Server] Ignored persistent switch to offhand slot {}", Integer.valueOf(slot));
            ci.cancel();
            return;
        }

        if (slot >= 0 && slot < InventoryPlayer.getHotbarSize()) {
            ((IOffhandPlayer) player).setMainhandSlot(slot);
        }
    }

}

