package com.m.offhand.util;

import com.m.offhand.api.OffhandAccess;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ServerPlayer;

public class OffhandValidator {
    
    /**
     * 验证玩家副手状态的一致性
     * @return true if state is valid, false otherwise
     */
    public static boolean validateOffhandState(EntityPlayer player, OffhandAccess access) {
        if (player == null || access == null) {
            OffhandLog.warn("[OFFHAND] Validation failed: player or access is null");
            return false;
        }
        
        ItemStack offhand = access.miteassistant$getOffhandStack();
        boolean isUsingOffhand = access.miteassistant$isUsingOffhand();
        
        // 如果正在使用副手，检查主手槽位是否有物品
        if (isUsingOffhand) {
            ItemStack currentItem = player.inventory.getCurrentItemStack();
            if (currentItem == null || currentItem.stackSize <= 0) {
                OffhandLog.warn("[OFFHAND] Validation failed: using offhand but main hand is empty");
                return false;
            }
            
            // 检查是否有原始主手物品保存
            ItemStack originalMainhand = access.miteassistant$getOriginalMainhand();
            if (originalMainhand == null) {
                OffhandLog.warn("[OFFHAND] Validation failed: using offhand but no original mainhand saved");
                return false;
            }
            
            // 检查原始槽位是否有效
            int originalSlot = access.miteassistant$getOriginalSlot();
            if (originalSlot < 0 || originalSlot >= OffhandConstants.HOTBAR_SIZE) {
                OffhandLog.warn("[OFFHAND] Validation failed: invalid original slot {}", originalSlot);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * 验证物品槽位的一致性
     * @return true if slot is valid, false otherwise
     */
    public static boolean validateSlot(EntityPlayer player, int slot) {
        if (slot < 0 || slot >= player.inventory.mainInventory.length) {
            OffhandLog.warn("[OFFHAND] Validation failed: invalid slot {}", slot);
            return false;
        }
        return true;
    }
    
    /**
     * 验证物品是否有效
     * @return true if item is valid, false otherwise
     */
    public static boolean validateItem(ItemStack stack) {
        if (stack == null) {
            return true; // null is valid (empty slot)
        }
        
        if (stack.stackSize <= 0) {
            OffhandLog.warn("[OFFHAND] Validation failed: item stack size is {}", stack.stackSize);
            return false;
        }
        
        if (stack.getItem() == null) {
            OffhandLog.warn("[OFFHAND] Validation failed: item has no item object");
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证交换操作的合法性
     * @return true if swap is valid, false otherwise
     */
    public static boolean validateSwap(EntityPlayer player, OffhandAccess access) {
        if (!validateOffhandState(player, access)) {
            return false;
        }
        
        // 检查当前槽位是否有效
        int currentSlot = player.inventory.currentItem;
        if (!validateSlot(player, currentSlot)) {
            return false;
        }
        
        // 检查主手物品是否有效
        ItemStack mainhand = player.inventory.getStackInSlot(currentSlot);
        if (!validateItem(mainhand)) {
            return false;
        }
        
        // 检查副手物品是否有效
        ItemStack offhand = access.miteassistant$getOffhandStack();
        if (!validateItem(offhand)) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证使用操作的合法性
     * @return true if use is valid, false otherwise
     */
    public static boolean validateUse(EntityPlayer player, OffhandAccess access) {
        if (!validateOffhandState(player, access)) {
            return false;
        }
        
        // 检查玩家是否忙碌
        if (OffhandUtils.isPlayerBusy(player, access)) {
            OffhandLog.warn("[OFFHAND] Validation failed: player is busy");
            return false;
        }
        
        // 检查副手物品是否有效
        ItemStack offhand = access.miteassistant$getOffhandStack();
        if (!OffhandUtils.isValidOffhand(offhand)) {
            OffhandLog.warn("[OFFHAND] Validation failed: offhand is empty or invalid");
            return false;
        }
        
        return true;
    }
    
    /**
     * 修复不一致的状态
     * @return true if recovery was successful, false otherwise
     */
    public static boolean recoverState(EntityPlayer player, OffhandAccess access) {
        OffhandLog.warn("[OFFHAND] Attempting to recover inconsistent state for player {}", player.getEntityName());
        
        boolean recovered = false;
        
        try {
            // 检查并修复正在使用副手的状态
            if (access.miteassistant$isUsingOffhand()) {
                ItemStack currentItem = player.inventory.getCurrentItemStack();
                ItemStack originalMainhand = access.miteassistant$getOriginalMainhand();
                int originalSlot = access.miteassistant$getOriginalSlot();
                int currentSlot = player.inventory.currentItem;
                
                OffhandLog.info("[OFFHAND] Recovery state - originalSlot: {}, currentSlot: {}, originalMainhand: {}, currentItem: {}", 
                    originalSlot, currentSlot, 
                    originalMainhand != null ? originalMainhand.getItem().getItemDisplayName(originalMainhand) : "null",
                    currentItem != null ? currentItem.getItem().getItemDisplayName(currentItem) : "null");
                
                // 检查原始主手是否为 null（可能是因为槽位滚动导致的）
                if (originalMainhand == null) {
                    // 如果原始主手为 null，说明玩家滚动到了空槽位
                    // 直接清空当前槽位，将当前物品放回副手
                    OffhandLog.warn("[OFFHAND] Original mainhand is null, treating as empty slot");
                    
                    // 将当前物品（正在使用的副手物品）放回副手
                    ItemStack newOffhand = (currentItem == null || currentItem.stackSize <= 0) ? null : currentItem;
                    access.miteassistant$setOffhandStack(newOffhand);
                    
                    // 清空当前槽位
                    player.inventory.mainInventory[currentSlot] = null;
                    
                    // 清除标记
                    access.miteassistant$setUsingOffhand(false);
                    access.miteassistant$setOriginalMainhand(null);
                    access.miteassistant$setOriginalSlot(-1);
                    
                    recovered = true;
                } else {
                    // 如果主手为空或原始槽位无效，强制停止使用
                    if (currentItem == null || currentItem.stackSize <= 0 || 
                        originalSlot < 0 || originalSlot >= player.inventory.mainInventory.length) {
                        OffhandLog.warn("[OFFHAND] Force stopping offhand use due to invalid state");
                        access.miteassistant$setUsingOffhand(false);
                        
                        // 尝试恢复原始主手
                        if (originalMainhand != null && validateSlot(player, originalSlot)) {
                            player.inventory.mainInventory[originalSlot] = originalMainhand;
                        }
                        
                        // 清除副手
                        access.miteassistant$setOffhandStack(null);
                        access.miteassistant$setOriginalMainhand(null);
                        access.miteassistant$setOriginalSlot(-1);
                        
                        recovered = true;
                    } else {
                        // 状态看起来有效，尝试正常恢复
                        OffhandLog.info("[OFFHAND] Attempting normal recovery with originalSlot: {}", originalSlot);
                        
                        // 将当前物品（正在使用的副手物品）放回副手
                        ItemStack usedItem = currentItem;
                        ItemStack newOffhand = (usedItem == null || usedItem.stackSize <= 0) ? null : usedItem;
                        access.miteassistant$setOffhandStack(newOffhand);
                        
                        // 恢复原始主手
                        if (originalMainhand != null && validateSlot(player, originalSlot)) {
                            player.inventory.mainInventory[originalSlot] = originalMainhand;
                        }
                        
                        // 清除标记
                        access.miteassistant$setUsingOffhand(false);
                        access.miteassistant$setOriginalMainhand(null);
                        access.miteassistant$setOriginalSlot(-1);
                        
                        recovered = true;
                    }
                }
            }
            
            // 验证副手物品
            ItemStack offhand = access.miteassistant$getOffhandStack();
            if (offhand != null && offhand.stackSize <= 0) {
                OffhandLog.warn("[OFFHAND] Clearing invalid offhand item");
                access.miteassistant$setOffhandStack(null);
                recovered = true;
            }
            
            // 同步到客户端
            if (recovered && player instanceof ServerPlayer serverPlayer) {
                OffhandNetworkHelper.syncOffhandToClient(serverPlayer, access.miteassistant$getOffhandStack());
            }
            
        } catch (Exception e) {
            OffhandLog.error("[OFFHAND] Error during state recovery", e);
            recovered = false;
        }
        
        if (recovered) {
            OffhandLog.info("[OFFHAND] State recovery successful for player {}", player.getEntityName());
        } else {
            OffhandLog.error("[OFFHAND] State recovery failed for player {}", player.getEntityName());
        }
        
        return recovered;
    }
}