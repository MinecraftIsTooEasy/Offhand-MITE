package com.m.offhand.network;

import  com.m.offhand.OffhandMod;
import com.m.offhand.api.OffhandAccess;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;

/**
 * 服务端 → 客户端：同步副手物品、使用状态和原始主手物品。
 * 当副手物品正在使用时（拉弓、进食等），同时同步原始主手物品以便客户端正确渲染。
 */
public class SyncOffhandS2CPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(OffhandMod.NameSpace, "sync_offhand");

    private final ItemStack offhand;
    private final boolean isUsingOffhand;
    private final ItemStack originalMainhand;

    public SyncOffhandS2CPacket(ItemStack offhand) {
        this(offhand, false, null);
    }

    public SyncOffhandS2CPacket(ItemStack offhand, boolean isUsingOffhand) {
        this(offhand, isUsingOffhand, null);
    }

    public SyncOffhandS2CPacket(ItemStack offhand, boolean isUsingOffhand, ItemStack originalMainhand) {
        this.offhand = offhand;
        this.isUsingOffhand = isUsingOffhand;
        this.originalMainhand = originalMainhand;
    }

    public SyncOffhandS2CPacket(PacketByteBuf buf) {
        this.offhand = buf.readItemStack();
        this.isUsingOffhand = buf.readBoolean();
        this.originalMainhand = buf.readItemStack();
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
        packetByteBuf.writeItemStack(this.offhand);
        packetByteBuf.writeBoolean(this.isUsingOffhand);
        packetByteBuf.writeItemStack(this.originalMainhand);
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (!(entityPlayer instanceof OffhandAccess offhandAccess)) return;
        offhandAccess.miteassistant$setOffhandStack(this.offhand);
        offhandAccess.miteassistant$setUsingOffhand(this.isUsingOffhand);
        offhandAccess.miteassistant$setOriginalMainhand(this.originalMainhand);
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}

