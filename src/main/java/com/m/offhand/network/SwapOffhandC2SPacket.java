package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.util.OffhandConstants;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import com.m.offhand.util.OffhandValidator;
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
        if (offhandAccess == null) {
            OffhandLog.warn("[OFFHAND] Swap failed: player does not implement OffhandAccess");
            return;
        }

        // 检查配置是否启用
        if (!OffhandConfig.enableOffhand.get()) {
            OffhandLog.debug("[OFFHAND] Swap failed: offhand is disabled in config");
            return;
        }

        // 如果正在使用副手物品，不允许交换（防止物品丢失）
        if (OffhandUtils.isPlayerBusy(entityPlayer, offhandAccess)) {
            OffhandLog.debug("[OFFHAND] Swap failed: player is busy");
            return;
        }

        // 严格验证模式下的状态检查
        if (OffhandConfig.isStrictValidationEnabled()) {
            if (!OffhandValidator.validateSwap(entityPlayer, offhandAccess)) {
                OffhandLog.warn("[OFFHAND] Swap failed: validation failed");
                
                // 尝试恢复状态
                if (OffhandConfig.isAutoRecoveryEnabled()) {
                    OffhandValidator.recoverState(entityPlayer, offhandAccess);
                }
                return;
            }
        }

        int currentSlot = entityPlayer.inventory.currentItem;
        ItemStack main = entityPlayer.inventory.getStackInSlot(currentSlot);
        ItemStack off = offhandAccess.miteassistant$getOffhandStack();

        OffhandLog.debug("[OFFHAND] Swapping mainhand {} with offhand {}", 
            main != null ? main.getItem().getItemDisplayName(main) : "null",
            off != null ? off.getItem().getItemDisplayName(off) : "null");

        // 交换（即使其中一个为 null 也可以交换）
        offhandAccess.miteassistant$setOffhandStack(main);
        entityPlayer.inventory.setInventorySlotContents(currentSlot, off);

        // Server -> Client: 立即同步副手，避免快速切换/滚轮导致客户端显示不同步
        if (entityPlayer instanceof net.minecraft.ServerPlayer serverPlayer) {
            syncWithRetry(serverPlayer, offhandAccess.miteassistant$getOffhandStack());
        }
    }
    
    /**
     * 带重试机制的同步
     */
    private void syncWithRetry(net.minecraft.ServerPlayer serverPlayer, ItemStack offhand) {
        int maxRetries = OffhandConfig.getSyncRetries();
        long retryDelay = OffhandConstants.SYNC_RETRY_DELAY_MS;
        
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, offhand);
                OffhandLog.debug("[OFFHAND] Sync attempt {} successful", attempt + 1);
                return;
            } catch (Exception e) {
                OffhandLog.warn("[OFFHAND] Sync attempt {} failed: {}", attempt + 1, e.getMessage());
                
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(retryDelay);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
        
        OffhandLog.error("[OFFHAND] Failed to sync offhand after {} attempts", maxRetries + 1);
    }

    @Override
    public ResourceLocation getChannel() {
        return CHANNEL;
    }
}

