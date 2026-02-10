package com.m.offhand.network;

import com.m.offhand.OffhandMod;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.core.OffhandStateManager;
import com.m.offhand.util.OffhandConstants;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandUtils;
import com.m.offhand.util.OffhandValidator;
import moddedmite.rustedironcore.network.Packet;
import moddedmite.rustedironcore.network.PacketByteBuf;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ResourceLocation;

public class SwapOffhandC2SPacket implements Packet {
    public static final ResourceLocation CHANNEL = new ResourceLocation(OffhandMod.NameSpace, "swap_offhand");

    public SwapOffhandC2SPacket() {
    }

    public SwapOffhandC2SPacket(PacketByteBuf buf) {
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

        if (!OffhandConfig.enableOffhand.get()) {
            OffhandLog.debug("[OFFHAND] Swap failed: offhand is disabled in config");
            return;
        }

        if (OffhandUtils.isPlayerBusy(entityPlayer, offhandAccess)) {
            OffhandLog.debug("[OFFHAND] Swap failed: player is busy");
            return;
        }

        if (OffhandConfig.isStrictValidationEnabled()) {
            if (!OffhandValidator.validateSwap(entityPlayer, offhandAccess)) {
                OffhandLog.warn("[OFFHAND] Swap failed: validation failed");
                
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

        offhandAccess.miteassistant$setOffhandStack(main);
        entityPlayer.inventory.setInventorySlotContents(currentSlot, off);

        OffhandStateManager.syncFromMixin(entityPlayer);

        if (entityPlayer instanceof net.minecraft.ServerPlayer serverPlayer) {
            syncWithRetry(serverPlayer, offhandAccess.miteassistant$getOffhandStack());
        }
    }
    
    private void syncWithRetry(net.minecraft.ServerPlayer serverPlayer, ItemStack offhand) {
        int maxRetries = OffhandConfig.getSyncRetries();
        long retryDelay = OffhandConstants.SYNC_RETRY_DELAY_MS;
        
        for (int attempt = 0; attempt <= maxRetries; attempt++) {
            try {
                OffhandPacketHandler.sendToClient(serverPlayer, new SyncOffhandS2CPacket(offhand));
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

