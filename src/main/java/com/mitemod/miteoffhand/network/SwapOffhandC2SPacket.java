package com.mitemod.miteoffhand.network;

import com.mitemod.miteoffhand.MITEOFFHANDMod;
import com.mitemod.miteoffhand.api.OffhandAccess;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;

/**
 * Client -> Server: request swapping mainhand and offhand.
 */
public class SwapOffhandC2SPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(MITEOFFHANDMod.OffhandNameSpace, "swap_offhand");

    public SwapOffhandC2SPacket() {
    }

    public SwapOffhandC2SPacket(PacketByteBuf ignored) {
    }

    @Override
    public void write(PacketByteBuf packetByteBuf) {
    }

    @Override
    public void apply(EntityPlayer entityPlayer) {
        if (!(entityPlayer instanceof OffhandAccess offhandAccess)) return;

        // 如果正在使用副手物品，不允许交换（防止物品丢失）
        if (offhandAccess.miteassistant$isUsingOffhand()) {
            return;
        }
        
        // 如果玩家正在使用任何物品，也不允许交换
        if (entityPlayer.isUsingItem()) {
            return;
        }

        int currentSlot = entityPlayer.inventory.currentItem;
        ItemStack main = entityPlayer.inventory.getStackInSlot(currentSlot); // 使用 getStackInSlot 更安全
        ItemStack off = offhandAccess.miteassistant$getOffhandStack();

        // 交换（即使其中一个为 null 也可以交换）
        offhandAccess.miteassistant$setOffhandStack(main);
        entityPlayer.inventory.setInventorySlotContents(currentSlot, off);

        // Server -> Client: 立即同步副手，避免快速切换/滚轮导致客户端显示不同步
        if (entityPlayer instanceof net.minecraft.ServerPlayer serverPlayer) {
            moddedmite.rustedironcore.network.Network.sendToClient(
                    serverPlayer,
                    new com.mitemod.miteoffhand.network.SyncOffhandS2CPacket(offhandAccess.miteassistant$getOffhandStack())
            );
        }
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}

