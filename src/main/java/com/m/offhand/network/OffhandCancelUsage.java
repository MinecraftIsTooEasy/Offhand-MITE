package com.m.offhand.network;

import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;

public class OffhandCancelUsage implements Packet {

    public static final ResourceLocation CHANNEL = new ResourceLocation("offhand", "cancel_usage");
    private final boolean compatible;

    public OffhandCancelUsage(PacketByteBuf buf) {
        int protocolVersion = buf.readInt();
        this.compatible = OffhandPacketHandler.isCompatible(protocolVersion);
        if (!this.compatible) {
            OffhandPacketHandler.warnProtocolMismatch(CHANNEL.toString(), protocolVersion);
        }
    }

    public OffhandCancelUsage() {
        this.compatible = true;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(OffhandPacketHandler.PROTOCOL_VERSION);
    }

    @Override
    public void apply(EntityPlayer player) {
        if (!this.compatible) {
            return;
        }
        player.stopUsingItem();
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }

    @Environment(EnvType.SERVER)
    public static void sendToClient(ServerPlayer player) {
        OffhandNetDispatch.sendToPlayer(player, new OffhandCancelUsage());
    }
}
