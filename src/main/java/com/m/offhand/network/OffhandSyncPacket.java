package com.m.offhand.network;

import com.m.offhand.api.core.IOffhandInventory;
import com.m.offhand.api.core.OffhandUtils;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;

public class OffhandSyncPacket implements Packet {

    private final boolean compatible;
    private int entityId;
    private ItemStack offhandStack;

    public static final ResourceLocation CHANNEL = new ResourceLocation("offhand", "sync");

    public OffhandSyncPacket(PacketByteBuf buf) {
        int protocolVersion = buf.readInt();
        this.compatible = OffhandPacketHandler.isCompatible(protocolVersion);
        if (!this.compatible) {
            this.entityId = -1;
            this.offhandStack = null;
            OffhandPacketHandler.warnProtocolMismatch(CHANNEL.toString(), protocolVersion);
            return;
        }
        this.entityId = buf.readInt();
        this.offhandStack = buf.readItemStack();
    }

    public OffhandSyncPacket(int entityId, ItemStack offhandStack) {
        this.compatible = true;
        this.entityId = entityId;
        this.offhandStack = offhandStack;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeInt(OffhandPacketHandler.PROTOCOL_VERSION);
        buf.writeInt(this.entityId);
        buf.writeItemStack(this.offhandStack);
    }

    @Override
    public void apply(EntityPlayer player) {
        if (!this.compatible) {
            return;
        }
        Entity target = player.worldObj.getEntityByID(this.entityId);
        if (target instanceof EntityPlayer) {
            ((IOffhandInventory) ((EntityPlayer) target).inventory).setOffhandItem(this.offhandStack);
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }

    @Environment(EnvType.SERVER)
    public static void sendToClient(ServerPlayer player, EntityPlayer target) {
        ItemStack offhandStack = OffhandUtils.getOffhandItem(target);
        OffhandNetDispatch.sendToPlayer(player, new OffhandSyncPacket(target.entityId, offhandStack));
    }

    @Environment(EnvType.SERVER)
    public static void sendToTracking(EntityPlayer target) {
        ItemStack offhandStack = OffhandUtils.getOffhandItem(target);
        OffhandNetDispatch.sendToTracking(target, new OffhandSyncPacket(target.entityId, offhandStack));
    }

    @Environment(EnvType.SERVER)
    public static void sendToAll(EntityPlayer target) {
        sendToTracking(target);
    }
}
