package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;

/**
 * Client -> Server: request swapping mainhand and offhand.
 */
public class SwapOffhandC2SPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(OffhandMod.NameSpace, "swap_offhand");

    public SwapOffhandC2SPacket() {
    }

    public SwapOffhandC2SPacket(PacketByteBuf ignored) {
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(entityPlayer);
        if (offhandAccess == null) return;

        // 如果正在使用副手物品，不允许交换（防止物品丢失）
        if (OffhandUtils.isPlayerBusy(entityPlayer, offhandAccess)) return;

        int currentSlot = entityPlayer.inventory.currentItem;
        ItemStack main = entityPlayer.inventory.getStackInSlot(currentSlot); // 使用 getStackInSlot 更安全
        ItemStack off = offhandAccess.miteassistant$getOffhandStack();

        // 交换（即使其中一个为 null 也可以交换）
        offhandAccess.miteassistant$setOffhandStack(main);
        entityPlayer.inventory.setInventorySlotContents(currentSlot, off);

        // Server -> Client: 立即同步副手，避免快速切换/滚轮导致客户端显示不同步
        if (entityPlayer instanceof net.minecraft.ServerPlayer serverPlayer) {
            OffhandNetworkHelper.syncOffhandToClient(serverPlayer, offhandAccess.miteassistant$getOffhandStack());
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}

