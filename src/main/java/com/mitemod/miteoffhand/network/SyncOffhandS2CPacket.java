package com.mitemod.miteoffhand.network;

import com.mitemod.miteoffhand.MITEOFFHANDMod;
import com.mitemod.miteoffhand.api.OffhandAccess;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;

/**
 服务端 → 客户端：同步副手物品和使用状态。
 */
public class SyncOffhandS2CPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(MITEOFFHANDMod.OffhandNameSpace, "sync_offhand");

    private final ItemStack offhand;
    private final boolean isUsingOffhand;

    public SyncOffhandS2CPacket(ItemStack offhand) {
        this(offhand, false);
    }

    public SyncOffhandS2CPacket(ItemStack offhand, boolean isUsingOffhand) {
        this.offhand = offhand;
        this.isUsingOffhand = isUsingOffhand;
    }

    public SyncOffhandS2CPacket(PacketByteBuf buf) {
        this.offhand = buf.readItemStack();
        this.isUsingOffhand = buf.readBoolean();
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeItemStack(this.offhand);
        packetByteBuf.writeBoolean(this.isUsingOffhand);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (!(entityPlayer instanceof OffhandAccess offhandAccess)) return;
        offhandAccess.miteassistant$setOffhandStack(this.offhand);
        offhandAccess.miteassistant$setUsingOffhand(this.isUsingOffhand);
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}

