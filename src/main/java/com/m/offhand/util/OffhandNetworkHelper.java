package com.m.offhand.util;

import com.m.offhand.api.Hand;
import com.m.offhand.network.SyncOffhandS2CPacket;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.ItemStack;
import net.minecraft.Packet5PlayerInventory;
import net.minecraft.ServerPlayer;

public final class OffhandNetworkHelper {
    private OffhandNetworkHelper() {
    }

    public static void syncOffhandToClient(ServerPlayer player, ItemStack offhand) {
        syncOffhandToClient(player, offhand, false, null, Hand.MAIN_HAND);
    }

    public static void syncOffhandToClient(ServerPlayer player, ItemStack offhand, boolean isUsingOffhand) {
        syncOffhandToClient(player, offhand, isUsingOffhand, null, Hand.MAIN_HAND);
    }

    public static void syncOffhandToClient(ServerPlayer player, ItemStack offhand, boolean isUsingOffhand, ItemStack originalMainhand) {
        syncOffhandToClient(player, offhand, isUsingOffhand, originalMainhand, Hand.MAIN_HAND);
    }

    public static void syncOffhandToClient(ServerPlayer player, ItemStack offhand, boolean isUsingOffhand, ItemStack originalMainhand, Hand activeHand) {
        Network.sendToClient(player, new SyncOffhandS2CPacket(offhand, isUsingOffhand, originalMainhand, activeHand));
    }

    public static void syncInventorySlot(ServerPlayer player, int slot, ItemStack stack) {
        if (player == null || player.playerNetServerHandler == null) {
            return;
        }
        player.playerNetServerHandler.sendPacketToPlayer(
                new Packet5PlayerInventory(player.entityId, slot, stack).setFullInventory()
        );
    }
}
