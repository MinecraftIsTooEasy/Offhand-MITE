package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.Hand;
import com.m.offhand.api.OffhandAccess;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;

public class SyncOffhandS2CPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(OffhandMod.NameSpace, "sync_offhand");

    private final ItemStack offhand;
    private final boolean isUsingOffhand;
    private final ItemStack originalMainhand;
    private final Hand activeHand;

    public SyncOffhandS2CPacket(ItemStack offhand) {
        this(offhand, false, null, Hand.MAIN_HAND);
    }

    public SyncOffhandS2CPacket(ItemStack offhand, boolean isUsingOffhand, ItemStack originalMainhand, Hand activeHand) {
        this.offhand = offhand;
        this.isUsingOffhand = isUsingOffhand;
        this.originalMainhand = originalMainhand;
        this.activeHand = activeHand != null ? activeHand : Hand.MAIN_HAND;
    }

    public SyncOffhandS2CPacket(PacketByteBuf buf) {
        this.offhand = buf.readItemStack();
        this.isUsingOffhand = buf.readBoolean();
        this.originalMainhand = buf.readItemStack();
        int handOrdinal = buf.readByte();
        Hand[] hands = Hand.values();
        this.activeHand = (handOrdinal >= 0 && handOrdinal < hands.length) ? hands[handOrdinal] : Hand.MAIN_HAND;
    }

    @Override
    public void write(PacketByteBuf buf) {
        buf.writeItemStack(this.offhand);
        buf.writeBoolean(this.isUsingOffhand);
        buf.writeItemStack(this.originalMainhand);
        buf.writeByte(this.activeHand.ordinal());
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        OffhandAccess access = (OffhandAccess) entityPlayer;
        access.miteassistant$setOffhandStack(this.offhand);
        access.miteassistant$setUsingOffhand(this.isUsingOffhand);
        access.miteassistant$setOriginalMainhand(this.originalMainhand);
        access.miteassistant$setActiveHand(this.activeHand);
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}
