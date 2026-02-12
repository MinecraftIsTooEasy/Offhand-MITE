package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.Hand;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;
import net.minecraft.ServerPlayer;

public class SwapOffhandC2SPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(OffhandMod.MITEOFFHAND, "swap_offhand");

    public SwapOffhandC2SPacket() {
    }

    public SwapOffhandC2SPacket(PacketByteBuf buf) {
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (entityPlayer.worldObj.isRemote) return;
        
        OffhandAccess access = (OffhandAccess) entityPlayer;
        
        if (!OffhandConfig.enableOffhand.get()) return;
        
        if (OffhandUtils.isPlayerBusy(entityPlayer, access)) return;

        ItemStack main = access.miteassistant$getStackInHand(Hand.MAIN_HAND);
        ItemStack off = access.miteassistant$getStackInHand(Hand.OFF_HAND);

        access.miteassistant$setStackInHand(Hand.OFF_HAND, main);
        access.miteassistant$setStackInHand(Hand.MAIN_HAND, off);

        if (entityPlayer instanceof ServerPlayer serverPlayer) {
            OffhandNetworkHelper.syncOffhandToClient(serverPlayer, access.miteassistant$getOffhandStack());
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}
