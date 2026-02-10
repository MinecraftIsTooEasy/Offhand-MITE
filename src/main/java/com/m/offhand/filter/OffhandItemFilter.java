package com.m.offhand.filter;

import com.m.offhand.config.OffhandConfig;
import com.m.offhand.util.OffhandLog;
import net.minecraft.*;

import java.util.HashSet;
import java.util.Set;

public final class OffhandItemFilter {
    
    private static final Set<Integer> DISABLED_ITEM_IDS = new HashSet<>();
    private static final Set<String> DISABLED_ITEM_NAMES = new HashSet<>();
    private static final Set<Item> DISABLED_ITEMS = new HashSet<>();
    
    private static boolean initialized = false;
    
    private OffhandItemFilter() {
    }
    
    private static void ensureInitialized() {
        if (initialized) {
            return;
        }
        
        initialized = true;
        
        DISABLED_ITEM_IDS.clear();
        DISABLED_ITEM_NAMES.clear();
        DISABLED_ITEMS.clear();
        
        String disabledList = OffhandConfig.disabledItems.get();
        if (disabledList == null || disabledList.isEmpty()) {
            return;
        }
        
        String[] items = disabledList.split(",");
        for (String item : items) {
            String trimmed = item.trim();
            if (trimmed.isEmpty()) {
                continue;
            }
            
            try {
                if (trimmed.startsWith("item.")) {
                    String itemName = trimmed.substring(5);
                    DISABLED_ITEM_NAMES.add(itemName);
                    
                    Item foundItem = null;
                    for (Item itemObj : Item.itemsList) {
                        if (itemObj != null && itemObj.getUnlocalizedName().equalsIgnoreCase(itemName)) {
                            foundItem = itemObj;
                            break;
                        }
                    }
                    if (foundItem != null) {
                        DISABLED_ITEMS.add(foundItem);
                        OffhandLog.debug("[OFFHAND] Disabled item by name: {}", itemName);
                    }
                } else {
                    int itemId = Integer.parseInt(trimmed);
                    if (itemId >= 0 && itemId < Item.itemsList.length) {
                        Item itemObj = Item.itemsList[itemId];
                        if (itemObj != null) {
                            DISABLED_ITEM_IDS.add(itemId);
                            DISABLED_ITEMS.add(itemObj);
                            OffhandLog.debug("[OFFHAND] Disabled item by ID: {} ({})", itemId, itemObj.getUnlocalizedName());
                        }
                    }
                }
            } catch (NumberFormatException e) {
                OffhandLog.warn("[OFFHAND] Invalid item ID in disabled list: {}", trimmed);
            } catch (Exception e) {
                OffhandLog.warn("[OFFHAND] Error processing disabled item: {}", trimmed, e);
            }
        }
        
        OffhandLog.info("[OFFHAND] Loaded {} disabled items", DISABLED_ITEMS.size());
    }
    
    public static void init() {
        ensureInitialized();
    }
    
    public static boolean isItemDisabled(ItemStack stack) {
        ensureInitialized();
        
        if (stack == null || stack.getItem() == null) {
            return false;
        }
        
        if (!OffhandConfig.enableOffhand.get()) {
            return true;
        }
        
        Item item = stack.getItem();
        
        if (DISABLED_ITEMS.contains(item)) {
            return true;
        }
        
        if (DISABLED_ITEM_IDS.contains(item.itemID)) {
            return true;
        }
        
        String itemName = item.getUnlocalizedName();
        if (DISABLED_ITEM_NAMES.contains(itemName)) {
            return true;
        }
        
        if (item instanceof ItemFood && !OffhandConfig.enableFoodOffhand.get()) {
            return true;
        }
        
        if (item instanceof ItemBow && !OffhandConfig.enableBowOffhand.get()) {
            return true;
        }
        
        if (item instanceof ItemBlock && !OffhandConfig.enableBlockOffhand.get()) {
            return true;
        }
        
        if (item instanceof ItemPotion) {
            return false;
        }
        
        if (item instanceof ItemBucket) {
            return false;
        }
        
        return false;
    }
    
    public static boolean canUseInOffhand(ItemStack stack) {
        ensureInitialized();
        
        if (stack == null || stack.getItem() == null) {
            return false;
        }
        
        if (isItemDisabled(stack)) {
            return false;
        }
        
        Item item = stack.getItem();
        
        if (item instanceof ItemTool || item instanceof ItemSword || item instanceof ItemHoe) {
            return false;
        }
        
        if (item instanceof ItemArmor) {
            return false;
        }
        
        return true;
    }
    
    public static boolean canSwapToOffhand(ItemStack stack) {
        ensureInitialized();
        
        if (stack == null || stack.getItem() == null) {
            return false;
        }
        
        if (isItemDisabled(stack)) {
            return false;
        }
        
        return canUseInOffhand(stack);
    }
    
    public static Set<Item> getDisabledItems() {
        return new HashSet<>(DISABLED_ITEMS);
    }
    
    public static int getDisabledItemCount() {
        return DISABLED_ITEMS.size();
    }
}
