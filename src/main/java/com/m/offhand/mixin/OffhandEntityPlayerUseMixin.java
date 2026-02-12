package com.m.offhand.mixin;

import com.m.offhand.api.Hand;
import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
import com.m.offhand.core.OffhandStateManager;
import com.m.offhand.util.OffhandLog;
import com.m.offhand.util.OffhandNetworkHelper;
import com.m.offhand.util.OffhandUtils;
import com.m.offhand.util.OffhandValidator;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class OffhandEntityPlayerUseMixin {
    
    @Shadow
    public InventoryPlayer inventory;
    
    @Inject(method = "onItemUseFinish", at = @At("TAIL"))
    protected void miteassistant$afterItemUseFinish(CallbackInfo ci) {
        Object thisPlayer = this;
        if (!(thisPlayer instanceof EntityPlayer player)) return;
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        if (!player.worldObj.isRemote && offhandAccess.miteassistant$isUsingOffhand()) {
            OffhandLog.info("[OFFHAND] 副手物品使用完成");
            handleOffhandUseEnd(player, offhandAccess);
        }
    }
    
    @Inject(method = "stopUsingItem(Z)V", at = @At("TAIL"))
    protected void miteassistant$afterStopUsingItem(boolean inform_server, CallbackInfo ci) {
        Object thisPlayer = this;
        if (!(thisPlayer instanceof EntityPlayer player)) return;
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        if (!player.worldObj.isRemote && offhandAccess.miteassistant$isUsingOffhand()) {
            OffhandLog.info("[OFFHAND] 副手物品停止使用（中途停止）");
            handleOffhandUseEnd(player, offhandAccess);
        }
    }
    
    private void handleOffhandUseEnd(EntityPlayer player, OffhandAccess offhandAccess) {
        if (OffhandConfig.isStrictValidationEnabled() && !OffhandValidator.validateOffhandState(player, offhandAccess)) {
            OffhandLog.warn("[OFFHAND] State validation failed, attempting recovery");
            OffhandValidator.recoverState(player, offhandAccess);
            return;
        }
        
        try {
            restoreAfterOffhandUse(player, offhandAccess);
        } catch (Exception e) {
            OffhandLog.error("[OFFHAND] Error during restoreAfterOffhandUse", e);
            if (OffhandConfig.isAutoRecoveryEnabled()) {
                OffhandLog.info("[OFFHAND] Attempting emergency recovery");
                OffhandValidator.recoverState(player, offhandAccess);
            }
        }
    }
    
    private void restoreAfterOffhandUse(EntityPlayer player, OffhandAccess offhandAccess) {
        int originalSlot = offhandAccess.miteassistant$getOriginalSlot();
        int currentSlot = this.inventory.currentItem;
        
        if (!validateAndFixSlots(player, offhandAccess, originalSlot, currentSlot)) {
            return;
        }
        
        originalSlot = offhandAccess.miteassistant$getOriginalSlot();
        ItemStack usedItem = this.inventory.mainInventory[originalSlot];
        ItemStack originalMainhand = offhandAccess.miteassistant$getOriginalMainhand();
        
        if (!OffhandValidator.validateItem(usedItem)) {
            usedItem = null;
        }
        
        if (!OffhandValidator.validateItem(originalMainhand)) {
            originalMainhand = null;
        }
        
        boolean slotChanged = (originalSlot != currentSlot);
        ItemStack newOffhand = (usedItem == null || usedItem.stackSize <= 0) ? null : usedItem;
        
        if (slotChanged) {
            handleSlotChanged(player, offhandAccess, originalSlot, currentSlot, newOffhand, originalMainhand);
        } else {
            handleNormalRestore(player, offhandAccess, originalSlot, newOffhand, originalMainhand);
        }
        
        clearOffhandState(offhandAccess);
        OffhandStateManager.syncFromMixin(player);
    }
    
    private boolean validateAndFixSlots(EntityPlayer player, OffhandAccess offhandAccess, int originalSlot, int currentSlot) {
        if (originalSlot < 0 || originalSlot >= this.inventory.mainInventory.length) {
            originalSlot = currentSlot;
            offhandAccess.miteassistant$setOriginalSlot(currentSlot);
            OffhandLog.warn("[OFFHAND] Invalid original slot, using current slot {}", currentSlot);
        }
        
        if (!OffhandValidator.validateSlot(player, originalSlot)) {
            OffhandLog.error("[OFFHAND] Original slot validation failed, using current slot");
            originalSlot = currentSlot;
            offhandAccess.miteassistant$setOriginalSlot(currentSlot);
        }
        
        if (!OffhandValidator.validateSlot(player, currentSlot)) {
            OffhandLog.error("[OFFHAND] Current slot validation failed, aborting restore");
            if (OffhandConfig.isAutoRecoveryEnabled()) {
                OffhandValidator.recoverState(player, offhandAccess);
            }
            return false;
        }
        
        return true;
    }
    
    private void handleSlotChanged(EntityPlayer player, OffhandAccess offhandAccess, int originalSlot, int currentSlot, ItemStack newOffhand, ItemStack originalMainhand) {
        OffhandLog.info("[OFFHAND] Player scrolled from slot {} to {} during offhand use", originalSlot, currentSlot);
        
        offhandAccess.miteassistant$setOffhandStack(newOffhand);
        this.inventory.mainInventory[currentSlot] = originalMainhand;
        this.inventory.mainInventory[originalSlot] = null;
        
        if (!player.worldObj.isRemote && player instanceof ServerPlayer serverPlayer) {
            OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
            OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, originalMainhand);
            OffhandNetworkHelper.syncInventorySlot(serverPlayer, originalSlot, null);
        }
        
        OffhandLog.info("[OFFHAND] 恢复完成（槽位已改变），副手物品: {}, 原始主手恢复到槽位 {}", 
            getItemDisplayName(newOffhand), currentSlot);
    }
    
    private void handleNormalRestore(EntityPlayer player, OffhandAccess offhandAccess, int originalSlot, ItemStack newOffhand, ItemStack originalMainhand) {
        this.inventory.mainInventory[originalSlot] = originalMainhand;
        offhandAccess.miteassistant$setOffhandStack(newOffhand);
        
        if (!player.worldObj.isRemote && player instanceof ServerPlayer serverPlayer) {
            OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
            OffhandNetworkHelper.syncInventorySlot(serverPlayer, originalSlot, originalMainhand);
        }
        
        OffhandLog.info("[OFFHAND] 恢复完成，副手物品: {}", getItemDisplayName(newOffhand));
    }
    
    private void clearOffhandState(OffhandAccess offhandAccess) {
        offhandAccess.miteassistant$setUsingOffhand(false);
        offhandAccess.miteassistant$setActiveHand(Hand.MAIN_HAND);
        offhandAccess.miteassistant$setOriginalMainhand(null);
        offhandAccess.miteassistant$setOriginalSlot(-1);
    }
    
    private String getItemDisplayName(ItemStack stack) {
        return (stack != null && stack.getItem() != null) ? stack.getItem().getItemDisplayName(stack) : "null";
    }
}
