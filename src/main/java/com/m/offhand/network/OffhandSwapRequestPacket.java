package com.m.offhand.network;

import com.m.offhand.Offhand;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;

public class OffhandSwapRequestPacket implements Packet {

    public static final ResourceLocation CHANNEL = new ResourceLocation("offhand", "swap_request");
    private final boolean compatible;

    public OffhandSwapRequestPacket(PacketByteBuf buf) {
        int protocolVersion = buf.readInt();
        this.compatible = OffhandPacketHandler.isCompatible(protocolVersion);
        if (!this.compatible) {
            OffhandPacketHandler.warnProtocolMismatch(CHANNEL.toString(), protocolVersion);
        }
    }

    public OffhandSwapRequestPacket() {
        this.compatible = true;
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeInt(OffhandPacketHandler.PROTOCOL_VERSION);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (!this.compatible) {
            return;
        }

        if (entityPlayer == null || entityPlayer.worldObj == null || entityPlayer.worldObj.isRemote) {
            return;
        }
        Offhand.swapOffhand(entityPlayer);
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}
