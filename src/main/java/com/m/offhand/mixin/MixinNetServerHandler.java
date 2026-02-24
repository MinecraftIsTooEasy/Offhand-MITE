package com.m.offhand.mixin;

import com.m.offhand.Offhand;
import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.api.core.IOffhandPlayer;
import com.m.offhand.api.core.OffhandSlotDef;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;
import net.minecraft.NetServerHandler;
import net.minecraft.Packet16BlockItemSwitch;
import net.minecraft.Packet81RightClick;
import net.minecraft.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetServerHandler.class)
public abstract class MixinNetServerHandler {

    @Shadow
    public ServerPlayer playerEntity;

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
            int oldSlot = player.inventory.currentItem;
            if (oldSlot >= 0 && oldSlot < InventoryPlayer.getHotbarSize()) {
                ((IOffhandPlayer) player).setMainhandSlot(oldSlot);
            }
            player.inventory.currentItem = slot;
            this.playerEntity.func_143004_u();
            ci.cancel();
            return;
        }

        if (slot >= 0 && slot < InventoryPlayer.getHotbarSize()) {
            ((IOffhandPlayer) player).setMainhandSlot(slot);
            player.inventory.currentItem = slot;
            this.playerEntity.func_143004_u();
            ci.cancel();
            return;
        }

        if (!OffhandSlotDef.isValidSwitchSlot(player, slot)) {
            Offhand.LOGGER.warn("[Offhand-Server] Rejected invalid slot switch: {}", Integer.valueOf(slot));
        }
    }

    @Inject(method = "handleRightClick", at = @At("TAIL"))
    private void offhand$handleRightClickPost(Packet81RightClick packet, CallbackInfo ci) {
        int offhandSlot = ((IOffhandInventory) this.playerEntity.inventory).getOffhandSlot();
        if (packet.slot_index == offhandSlot) {
            ((IOffhandPlayer) (EntityPlayer) this.playerEntity).swingOffItem();
        }
    }
}

