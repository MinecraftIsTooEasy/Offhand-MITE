package com.m.offhand.network;

import com.m.offhand.api.core.IOffhandPlayer;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.ResourceLocation;

public class OffhandSyncOffhandUse implements Packet {

    private final boolean compatible;
    private int entityId;
    private boolean isUsingOffhand;

    public static final ResourceLocation CHANNEL = new ResourceLocation("offhand", "sync_use");

    public OffhandSyncOffhandUse(PacketByteBuf buf) {
        int protocolVersion = buf.readInt();
        this.compatible = OffhandPacketHandler.isCompatible(protocolVersion);
        if (!this.compatible) {
            this.entityId = -1;
            this.isUsingOffhand = false;
            OffhandPacketHandler.warnProtocolMismatch(CHANNEL.toString(), protocolVersion);
            return;
        }
        this.entityId = buf.readInt();
        this.isUsingOffhand = buf.readBoolean();
    }

    public OffhandSyncOffhandUse(int entityId, boolean isUsingOffhand) {
        this.compatible = true;
        this.entityId = entityId;
        this.isUsingOffhand = isUsingOffhand;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(OffhandPacketHandler.PROTOCOL_VERSION);
        buf.writeInt(this.entityId);
        buf.writeBoolean(this.isUsingOffhand);
    }

    @Override
    public void apply(EntityPlayer player) {
        if (!this.compatible) {
            return;
        }
        Entity target = player.worldObj.getEntityByID(this.entityId);
        if (target instanceof EntityPlayer) {
            ((IOffhandPlayer) target).setOffhandItemInUse(this.isUsingOffhand);
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }

    @Environment(EnvType.SERVER)
    public static void sendToTracking(EntityPlayer target, boolean isUsingOffhand) {
        OffhandNetDispatch.sendToTracking(target, new OffhandSyncOffhandUse(target.entityId, isUsingOffhand));
    }

    @Environment(EnvType.SERVER)
    public static void sendToAll(EntityPlayer target, boolean isUsingOffhand) {
        sendToTracking(target, isUsingOffhand);
    }
}
