package com.m.offhand.network;

import com.m.offhand.Offhand;
import moddedmite.rustedironcore.network.PacketReader;

public final class OffhandPacketHandler {

    public static final int PROTOCOL_VERSION = 1;

    public static boolean isCompatible(int protocolVersion) {
        return protocolVersion == PROTOCOL_VERSION;
    }

    public static void warnProtocolMismatch(String channel, int protocolVersion) {
        Offhand.LOGGER.warn(
            "Protocol mismatch on channel {}: remote={}, local={}",
            channel,
            protocolVersion,
            PROTOCOL_VERSION);
    }

    public static void init() {
        PacketReader.registerServerPacketReader(OffhandSwapRequestPacket.CHANNEL, OffhandSwapRequestPacket::new);
        PacketReader.registerClientPacketReader(OffhandSyncPacket.CHANNEL, OffhandSyncPacket::new);
        PacketReader.registerClientPacketReader(OffhandAnimationPacket.CHANNEL, OffhandAnimationPacket::new);
        PacketReader.registerClientPacketReader(OffhandSyncOffhandUse.CHANNEL, OffhandSyncOffhandUse::new);
        PacketReader.registerClientPacketReader(OffhandCancelUsage.CHANNEL, OffhandCancelUsage::new);
    }
}
