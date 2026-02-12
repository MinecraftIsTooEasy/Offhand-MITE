package com.m.offhand.filter;

import com.m.offhand.config.OffhandConfig;
import net.minecraft.*;

import java.util.HashSet;
import java.util.Set;

public final class OffhandItemFilter {
    
    private static final Set<Item> DISABLED_ITEMS = new HashSet<>();
    private static boolean initialized = false;
    
    private OffhandItemFilter() {
    }
    
    private static void ensureInitialized() {
        if (initialized) return;
        initialized = true;
        
        try {
            String disabledList = OffhandConfig.disabledItems.get();
            if (disabledList == null || disabledList.isEmpty()) return;
            
            for (String item : disabledList.split(",")) {
                String trimmed = item.trim();
                if (trimmed.isEmpty()) continue;
                
                try {
                    if (trimmed.startsWith("item.")) {
                        String itemName = trimmed.substring(5);
                        for (Item itemObj : Item.itemsList) {
                            if (itemObj != null && itemObj.getUnlocalizedName().equalsIgnoreCase(itemName)) {
                                DISABLED_ITEMS.add(itemObj);
                                break;
                            }
                        }
                    } else {
                        int itemId = Integer.parseInt(trimmed);
                        if (itemId >= 0 && itemId < Item.itemsList.length && Item.itemsList[itemId] != null) {
                            DISABLED_ITEMS.add(Item.itemsList[itemId]);
                        }
                    }
                } catch (NumberFormatException ignored) {
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        } catch (Exception e) {
            // Item 类可能还未完全初始化，忽略错误
        }
    }
    
    public static boolean isItemDisabled(ItemStack stack) {
        ensureInitialized();
        
        if (stack == null || stack.getItem() == null) return true;
        if (!OffhandConfig.enableOffhand.get()) return true;
        
        Item item = stack.getItem();
        
        if (DISABLED_ITEMS.contains(item)) return true;
        
        try {
            if (item instanceof ItemFood && !OffhandConfig.enableFoodOffhand.get()) return true;
            if (item instanceof ItemBow && !OffhandConfig.enableBowOffhand.get()) return true;
            if (item instanceof ItemBlock && !OffhandConfig.enableBlockOffhand.get()) return true;
        } catch (Exception e) {
            // 忽略初始化错误
        }
        
        return false;
    }
    
    public static boolean canUseInOffhand(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return false;
        if (isItemDisabled(stack)) return false;
        
        Item item = stack.getItem();
        
        try {
            if (item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemHoe) return false;
            if (item instanceof ItemArmor) return false;
        } catch (Exception e) {
            // 忽略初始化错误
        }
        
        return true;
    }
    
    public static boolean canSwapToOffhand(ItemStack stack) {
        return canUseInOffhand(stack);
    }
}
