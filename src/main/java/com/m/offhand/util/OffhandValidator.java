package com.m.offhand.util;

import com.m.offhand.api.OffhandAccess;
import com.m.offhand.core.OffhandStateManager;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ServerPlayer;

public final class OffhandValidator {
    
    public static boolean validateOffhandState(EntityPlayer player, OffhandAccess access) {
        if (!validateBasicState(player, access)) {
            return false;
        }
        
        OffhandStateManager.OffhandState state = OffhandStateManager.getState(player);
        if (state != null && !state.validate()) {
            OffhandLog.warn("[OFFHAND] State manager validation failed");
            return false;
        }
        
        if (access.miteassistant$isUsingOffhand()) {
            return validateUsingOffhandState(player, access);
        }
        
        return true;
    }
    
    private static boolean validateBasicState(EntityPlayer player, OffhandAccess access) {
        if (player == null || access == null) {
            OffhandLog.warn("[OFFHAND] Validation failed: player or access is null");
            return false;
        }
        return true;
    }
    
    private static boolean validateUsingOffhandState(EntityPlayer player, OffhandAccess access) {
        ItemStack currentItem = player.inventory.getCurrentItemStack();
        if (!isValidItemStack(currentItem)) {
            OffhandLog.warn("[OFFHAND] Validation failed: using offhand but main hand is empty");
            return false;
        }
        
        ItemStack originalMainhand = access.miteassistant$getOriginalMainhand();
        if (originalMainhand == null) {
            OffhandLog.warn("[OFFHAND] Validation failed: using offhand but no original mainhand saved");
            return false;
        }
        
        int originalSlot = access.miteassistant$getOriginalSlot();
        if (!isValidHotbarSlot(originalSlot)) {
            OffhandLog.warn("[OFFHAND] Validation failed: invalid original slot {}", originalSlot);
            return false;
        }
        
        return true;
    }
    
    public static boolean validateSlot(EntityPlayer player, int slot) {
        if (slot < 0 || slot >= player.inventory.mainInventory.length) {
            OffhandLog.warn("[OFFHAND] Validation failed: invalid slot {}", slot);
            return false;
        }
        return true;
    }
    
    public static boolean validateItem(ItemStack stack) {
        if (stack == null) {
            return true;
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
    
    public static boolean validateSwap(EntityPlayer player, OffhandAccess access) {
        if (!validateOffhandState(player, access)) {
            return false;
        }
        
        int currentSlot = player.inventory.currentItem;
        if (!validateSlot(player, currentSlot)) {
            return false;
        }
        
        ItemStack mainhand = player.inventory.getStackInSlot(currentSlot);
        if (!validateItem(mainhand)) {
            return false;
        }
        
        ItemStack offhand = access.miteassistant$getOffhandStack();
        if (!validateItem(offhand)) {
            return false;
        }
        
        return true;
    }
    
    public static boolean validateUse(EntityPlayer player, OffhandAccess access) {
        if (!validateOffhandState(player, access)) {
            return false;
        }
        
        if (OffhandUtils.isPlayerBusy(player, access)) {
            OffhandLog.warn("[OFFHAND] Validation failed: player is busy");
            return false;
        }
        
        ItemStack offhand = access.miteassistant$getOffhandStack();
        if (!OffhandUtils.isValidOffhand(offhand)) {
            OffhandLog.warn("[OFFHAND] Validation failed: offhand is empty or invalid");
            return false;
        }
        
        return true;
    }
    
    public static boolean recoverState(EntityPlayer player, OffhandAccess access) {
        OffhandLog.warn("[OFFHAND] Attempting to recover inconsistent state for player {}", player.getEntityName());
        
        boolean recovered = false;
        
        try {
            if (access.miteassistant$isUsingOffhand()) {
                recovered = recoverUsingOffhandState(player, access);
            }
            
            recovered = recovered || recoverInvalidOffhand(access);
            
            if (recovered) {
                OffhandStateManager.syncFromMixin(player);
                
                if (player instanceof ServerPlayer serverPlayer) {
                    OffhandNetworkHelper.syncOffhandToClient(serverPlayer, access.miteassistant$getOffhandStack());
                }
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
    
    private static boolean recoverUsingOffhandState(EntityPlayer player, OffhandAccess access) {
        ItemStack currentItem = player.inventory.getCurrentItemStack();
        ItemStack originalMainhand = access.miteassistant$getOriginalMainhand();
        int originalSlot = access.miteassistant$getOriginalSlot();
        int currentSlot = player.inventory.currentItem;
        
        OffhandLog.info("[OFFHAND] Recovery state - originalSlot: {}, currentSlot: {}, originalMainhand: {}, currentItem: {}", 
            originalSlot, currentSlot, 
            getItemDisplayName(originalMainhand),
            getItemDisplayName(currentItem));
        
        if (originalMainhand == null) {
            return recoverWithNullOriginalMainhand(player, access, currentItem, currentSlot);
        }
        
        if (!isValidItemStack(currentItem) || !isValidHotbarSlot(originalSlot)) {
            return recoverWithInvalidState(player, access, originalMainhand, originalSlot);
        }
        
        return recoverNormalState(player, access, currentItem, originalMainhand, originalSlot);
    }
    
    private static boolean recoverWithNullOriginalMainhand(EntityPlayer player, OffhandAccess access, ItemStack currentItem, int currentSlot) {
        OffhandLog.warn("[OFFHAND] Original mainhand is null, treating as empty slot");
        
        ItemStack newOffhand = (currentItem == null || currentItem.stackSize <= 0) ? null : currentItem;
        access.miteassistant$setOffhandStack(newOffhand);
        player.inventory.mainInventory[currentSlot] = null;
        
        clearOffhandState(access);
        return true;
    }
    
    private static boolean recoverWithInvalidState(EntityPlayer player, OffhandAccess access, ItemStack originalMainhand, int originalSlot) {
        OffhandLog.warn("[OFFHAND] Force stopping offhand use due to invalid state");
        access.miteassistant$setUsingOffhand(false);
        
        if (originalMainhand != null && validateSlot(player, originalSlot)) {
            player.inventory.mainInventory[originalSlot] = originalMainhand;
        }
        
        access.miteassistant$setOffhandStack(null);
        clearOffhandState(access);
        return true;
    }
    
    private static boolean recoverNormalState(EntityPlayer player, OffhandAccess access, ItemStack currentItem, ItemStack originalMainhand, int originalSlot) {
        OffhandLog.info("[OFFHAND] Attempting normal recovery with originalSlot: {}", originalSlot);
        
        ItemStack newOffhand = (currentItem == null || currentItem.stackSize <= 0) ? null : currentItem;
        access.miteassistant$setOffhandStack(newOffhand);
        
        if (originalMainhand != null && validateSlot(player, originalSlot)) {
            player.inventory.mainInventory[originalSlot] = originalMainhand;
        }
        
        clearOffhandState(access);
        return true;
    }
    
    private static boolean recoverInvalidOffhand(OffhandAccess access) {
        ItemStack offhand = access.miteassistant$getOffhandStack();
        if (offhand != null && offhand.stackSize <= 0) {
            OffhandLog.warn("[OFFHAND] Clearing invalid offhand item");
            access.miteassistant$setOffhandStack(null);
            return true;
        }
        return false;
    }
    
    private static void clearOffhandState(OffhandAccess access) {
        access.miteassistant$setUsingOffhand(false);
        access.miteassistant$setOriginalMainhand(null);
        access.miteassistant$setOriginalSlot(-1);
    }
    
    private static boolean isValidItemStack(ItemStack stack) {
        return stack != null && stack.stackSize > 0;
    }
    
    private static boolean isValidHotbarSlot(int slot) {
        return slot >= 0 && slot < OffhandConstants.HOTBAR_SIZE;
    }
    
    private static String getItemDisplayName(ItemStack stack) {
        return (stack != null && stack.getItem() != null) ? stack.getItem().getItemDisplayName(stack) : "null";
    }
}