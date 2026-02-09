package com.m.offhand.mixin;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.config.OffhandConfig;
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

/**
 * MITE 副手物品使用 Mixin
 * 处理副手物品使用完成后的状态更新
 */
@Mixin(EntityPlayer.class)
public abstract class OffhandEntityPlayerUseMixin {
    
    @Shadow
    public InventoryPlayer inventory;
    
    /**
     * 在物品使用完成后，恢复副手物品状态
     */
    @Inject(method = "onItemUseFinish", at = @At("TAIL"))
    protected void miteassistant$afterItemUseFinish(CallbackInfo ci) {
        Object thisPlayer = this;
        if (!(thisPlayer instanceof EntityPlayer player)) return;
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        // 检查是否是副手物品使用完成（仅服务端处理，客户端等待服务端同步）
        if (!player.worldObj.isRemote && offhandAccess.miteassistant$isUsingOffhand()) {
            OffhandLog.info("[OFFHAND] 副手物品使用完成");
            
            // 验证状态
            if (OffhandConfig.isStrictValidationEnabled()) {
                if (!OffhandValidator.validateOffhandState(player, offhandAccess)) {
                    OffhandLog.warn("[OFFHAND] State validation failed after item use, attempting recovery");
                    OffhandValidator.recoverState(player, offhandAccess);
                    return;
                }
            }
            
            restoreAfterOffhandUse(player, offhandAccess);
        }
    }
    
    /**
     * 在玩家停止使用物品时（中途停止，如弓箭射击），恢复副手物品状态
     */
    @Inject(method = "stopUsingItem(Z)V", at = @At("TAIL"))
    protected void miteassistant$afterStopUsingItem(boolean inform_server, CallbackInfo ci) {
        Object thisPlayer = this;
        if (!(thisPlayer instanceof EntityPlayer player)) return;
        OffhandAccess offhandAccess = OffhandUtils.asOffhandAccess(player);
        if (offhandAccess == null) return;
        
        // 检查是否是副手物品停止使用（仅服务端处理，客户端等待服务端同步）
        if (!player.worldObj.isRemote && offhandAccess.miteassistant$isUsingOffhand()) {
            OffhandLog.info("[OFFHAND] 副手物品停止使用（中途停止）");
            
            // 验证状态
            if (OffhandConfig.isStrictValidationEnabled()) {
                if (!OffhandValidator.validateOffhandState(player, offhandAccess)) {
                    OffhandLog.warn("[OFFHAND] State validation failed after stop using, attempting recovery");
                    OffhandValidator.recoverState(player, offhandAccess);
                    return;
                }
            }
            
            restoreAfterOffhandUse(player, offhandAccess);
        }
    }
    
    /**
     * 恢复副手物品使用后的状态
     */
    private void restoreAfterOffhandUse(EntityPlayer player, OffhandAccess offhandAccess) {
        try {
            // 获取保存的原始槽位和当前槽位
            int originalSlot = offhandAccess.miteassistant$getOriginalSlot();
            int currentSlot = this.inventory.currentItem;
            
            if (originalSlot < 0 || originalSlot >= this.inventory.mainInventory.length) {
                // 如果没有保存有效的槽位，使用当前槽位（兼容旧版本）
                originalSlot = currentSlot;
                OffhandLog.warn("[OFFHAND] Invalid original slot {}, using current slot {}", 
                    offhandAccess.miteassistant$getOriginalSlot(), currentSlot);
            }
            
            // 检查玩家是否在使用过程中滚动槽位
            boolean slotChanged = (originalSlot != currentSlot);
            if (slotChanged) {
                OffhandLog.info("[OFFHAND] Player scrolled from slot {} to {} during offhand use", originalSlot, currentSlot);
            }
            
            // 验证槽位
            if (!OffhandValidator.validateSlot(player, originalSlot)) {
                OffhandLog.error("[OFFHAND] Original slot validation failed, using current slot");
                originalSlot = currentSlot;
            }
            
            if (!OffhandValidator.validateSlot(player, currentSlot)) {
                OffhandLog.error("[OFFHAND] Current slot validation failed, aborting restore");
                if (OffhandConfig.isAutoRecoveryEnabled()) {
                    OffhandValidator.recoverState(player, offhandAccess);
                }
                return;
            }
            
            // 获取原始槽位的物品（可能已被消耗或改变）
            ItemStack usedItem = this.inventory.mainInventory[originalSlot];
            
            // 验证物品
            if (!OffhandValidator.validateItem(usedItem)) {
                OffhandLog.warn("[OFFHAND] Used item validation failed, treating as empty");
                usedItem = null;
            }
            
            // 恢复原始主手物品
            ItemStack originalMainhand = offhandAccess.miteassistant$getOriginalMainhand();
            if (!OffhandValidator.validateItem(originalMainhand)) {
                OffhandLog.warn("[OFFHAND] Original mainhand validation failed, treating as empty");
                originalMainhand = null;
            }
            
            // 如果槽位改变了，需要特殊处理
            if (slotChanged) {
                // 将使用后的物品放回副手
                ItemStack newOffhand = (usedItem == null || usedItem.stackSize <= 0) ? null : usedItem;
                offhandAccess.miteassistant$setOffhandStack(newOffhand);
                
                // 将原始主手物品恢复到当前槽位（而不是原始槽位）
                // 这样玩家滚动后，物品会跟随到新槽位
                // 如果 originalMainhand 为 null（滚动到了空槽位），则清空当前槽位
                this.inventory.mainInventory[currentSlot] = originalMainhand;
                
                // 清空原始槽位（因为使用后的物品已经放到副手了）
                this.inventory.mainInventory[originalSlot] = null;
                
                // 同步到客户端
                if (!player.worldObj.isRemote && player instanceof ServerPlayer serverPlayer) {
                    OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
                    OffhandNetworkHelper.syncInventorySlot(serverPlayer, currentSlot, originalMainhand);
                    OffhandNetworkHelper.syncInventorySlot(serverPlayer, originalSlot, null);
                }
                
                OffhandLog.info("[OFFHAND] 恢复完成（槽位已改变），副手物品: {}, 原始主手恢复到槽位 {} (originalMainhand: {})", 
                    newOffhand != null ? newOffhand.getItem().getItemDisplayName(newOffhand) : "null", 
                    currentSlot,
                    originalMainhand != null ? originalMainhand.getItem().getItemDisplayName(originalMainhand) : "null");
            } else {
                // 槽位未改变，正常恢复
                this.inventory.mainInventory[originalSlot] = originalMainhand;
                
                // 将使用后的物品放回副手（如果还有剩余）
                ItemStack newOffhand = (usedItem == null || usedItem.stackSize <= 0) ? null : usedItem;
                offhandAccess.miteassistant$setOffhandStack(newOffhand);
                
                // 同步到客户端
                if (!player.worldObj.isRemote && player instanceof ServerPlayer serverPlayer) {
                    OffhandNetworkHelper.syncOffhandToClient(serverPlayer, newOffhand);
                    OffhandNetworkHelper.syncInventorySlot(serverPlayer, originalSlot, originalMainhand);
                }
                
                OffhandLog.info("[OFFHAND] 恢复完成，副手物品: {}", 
                    newOffhand != null ? newOffhand.getItem().getItemDisplayName(newOffhand) : "null");
            }
            
            // 清除标记
            offhandAccess.miteassistant$setUsingOffhand(false);
            offhandAccess.miteassistant$setOriginalMainhand(null);
            offhandAccess.miteassistant$setOriginalSlot(-1);
                
        } catch (Exception e) {
            OffhandLog.error("[OFFHAND] Error during restoreAfterOffhandUse", e);
            
            // 尝试恢复状态
            if (OffhandConfig.isAutoRecoveryEnabled()) {
                OffhandLog.info("[OFFHAND] Attempting emergency recovery");
                OffhandValidator.recoverState(player, offhandAccess);
            }
        }
    }
}
