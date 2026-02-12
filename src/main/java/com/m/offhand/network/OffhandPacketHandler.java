package com.m.offhand.network;

import moddedmite.rustedironcore.network.Network;
import moddedmite.rustedironcore.network.PacketReader;

public final class OffhandPacketHandler {
    
    private OffhandPacketHandler() {
    }
    
    public static void register() {
        PacketReader.registerServerPacketReader(SwapOffhandC2SPacket.CHANNEL, buf -> new SwapOffhandC2SPacket(buf));
        PacketReader.registerServerPacketReader(UseOffhandC2SPacket.CHANNEL, buf -> new UseOffhandC2SPacket(buf));
        PacketReader.registerClientPacketReader(SyncOffhandS2CPacket.CHANNEL, buf -> new SyncOffhandS2CPacket(buf));
    }
    
    public static void sendToServer(moddedmite.rustedironcore.network.Packet packet) {
        Network.sendToServer(packet);
    }
}
