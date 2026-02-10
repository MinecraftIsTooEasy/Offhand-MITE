package com.m.offhand.network;

import com.m.offhand.util.OffhandLog;
import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.PacketReader;
import moddedmite.rustedironcore.network.PacketSupplier;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;

import java.util.HashMap;
import java.util.Map;

public final class OffhandPacketHandler {
    
    private static final Map<ResourceLocation, PacketSupplier> PROCESSORS = new HashMap<>();
    
    private OffhandPacketHandler() {
    }
    
    public static void register() {
        PROCESSORS.clear();
        
        registerProcessor(SyncOffhandS2CPacket.CHANNEL, buf -> new SyncOffhandS2CPacket(buf));
        registerProcessor(SwapOffhandC2SPacket.CHANNEL, buf -> new SwapOffhandC2SPacket(buf));
        registerProcessor(UseOffhandC2SPacket.CHANNEL, buf -> new UseOffhandC2SPacket(buf));
        
        OffhandLog.info("[OFFHAND] Registered {} packet processors", PROCESSORS.size());
    }
    
    private static void registerProcessor(ResourceLocation channel, PacketSupplier processor) {
        PROCESSORS.put(channel, processor);
        PacketReader.registerServerPacketReader(channel, processor);
        PacketReader.registerClientPacketReader(channel, processor);
    }
    
    public static void sendToClient(ServerPlayer player, moddedmite.rustedironcore.network.Packet packet) {
        if (player == null || player.playerNetServerHandler == null) {
            OffhandLog.warn("[OFFHAND] Cannot send packet to null player or null network handler");
            return;
        }
        
        try {
            Network.sendToClient(player, packet);
            OffhandLog.debug("[OFFHAND] Sent packet {} to player {}", packet.getChannel(), player.getEntityName());
        } catch (Exception e) {
            OffhandLog.error("[OFFHAND] Error sending packet to client", e);
        }
    }
    
    public static void sendToServer(moddedmite.rustedironcore.network.Packet packet) {
        try {
            Network.sendToServer(packet);
            OffhandLog.debug("[OFFHAND] Sent packet {} to server", packet.getChannel());
        } catch (Exception e) {
            OffhandLog.error("[OFFHAND] Error sending packet to server", e);
        }
    }
}
