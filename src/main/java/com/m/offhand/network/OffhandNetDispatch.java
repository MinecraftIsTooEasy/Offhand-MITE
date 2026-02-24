package com.m.offhand.network;

import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.Packet;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Entity;
import net.minecraft.ServerPlayer;
import net.minecraft.WorldServer;

public final class OffhandNetDispatch {

    private OffhandNetDispatch() {
    }

    @Environment(EnvType.SERVER)
    public static void sendToPlayer(ServerPlayer player, Packet packet) {
        if (player == null || packet == null) {
            return;
        }
        Network.sendToClient(player, packet);
    }

    @Environment(EnvType.SERVER)
    public static void sendToTracking(Entity target, Packet packet) {
        if (target == null || packet == null || !(target.worldObj instanceof WorldServer world)) {
            return;
        }
        world.sendPacketToAllPlayersTrackingEntity(target, packet.toVanilla());
    }

    @Environment(EnvType.SERVER)
    public static void sendToAssociated(Entity target, Packet packet) {
        if (target == null || packet == null || !(target.worldObj instanceof WorldServer world)) {
            return;
        }
        world.sendPacketToAllAssociatedPlayers(target, packet.toVanilla());
    }
}
