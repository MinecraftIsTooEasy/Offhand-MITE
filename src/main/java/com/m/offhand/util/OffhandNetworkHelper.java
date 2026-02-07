package com.m.offhand.util;

import com.m.offhand.network.SyncOffhandS2CPacket;
import moddedmite.rustedironcore.network.Network;
import net.minecraft.ItemStack;
import net.minecraft.Packet5PlayerInventory;
import net.minecraft.ServerPlayer;

public final class OffhandNetworkHelper {
    private OffhandNetworkHelper() {
    }

    public static void syncOffhandToClient(ServerPlayer player, ItemStack offhand) {
        syncOffhandToClient(player, offhand, false);
    }

    public static void syncOffhandToClient(ServerPlayer player, ItemStack offhand, boolean isUsingOffhand) {
        Network.sendToClient(player, new SyncOffhandS2CPacket(offhand, isUsingOffhand));
    }

    public static void syncOffhandToClient(ServerPlayer player, ItemStack offhand, boolean isUsingOffhand, ItemStack originalMainhand) {
        Network.sendToClient(player, new SyncOffhandS2CPacket(offhand, isUsingOffhand, originalMainhand));
    }

    public static void syncInventorySlot(ServerPlayer player, int slot, ItemStack stack) {
        player.playerNetServerHandler.sendPacketToPlayer(
                new Packet5PlayerInventory(player.entityId, slot, stack).setFullInventory()
        );
    }
}
