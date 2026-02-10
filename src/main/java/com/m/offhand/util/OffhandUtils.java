package com.m.offhand.util;

import com.m.offhand.api.OffhandAccess;
import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
import org.jetbrains.annotations.Nullable;

public final class OffhandUtils {
    private OffhandUtils() {
    }

    @Nullable
    public static OffhandAccess asOffhandAccess(EntityPlayer player) {
        return player instanceof OffhandAccess access ? access : null;
    }

    public static boolean isValidOffhand(ItemStack offhand) {
        return offhand != null && offhand.stackSize > 0;
    }

    public static boolean isBlock(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemBlock;
    }

    public static boolean isPlayerBusy(EntityPlayer player, OffhandAccess access) {
        return access != null && (access.miteassistant$isUsingOffhand() || player.isUsingItem());
    }

    public static ItemStack[] copyHotbar(InventoryPlayer inventory) {
        if (inventory == null || inventory.mainInventory == null) {
            return new ItemStack[OffhandConstants.HOTBAR_SIZE];
        }
        
        ItemStack[] hotbar = new ItemStack[OffhandConstants.HOTBAR_SIZE];
        int length = Math.min(hotbar.length, inventory.mainInventory.length);
        System.arraycopy(inventory.mainInventory, 0, hotbar, 0, length);
        return hotbar;
    }

    public static boolean canStackWithOffhand(ItemStack itemStack, ItemStack offhand) {
        if (itemStack == null || offhand == null) {
            return false;
        }
        
        if (itemStack.itemID != offhand.itemID) {
            return false;
        }
        
        if (itemStack.getItemDamage() != offhand.getItemDamage()) {
            return false;
        }
        
        return offhand.stackSize < 64;
    }
}
