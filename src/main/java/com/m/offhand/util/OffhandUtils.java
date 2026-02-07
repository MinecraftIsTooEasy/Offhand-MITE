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
        if (player instanceof OffhandAccess access) {
            return access;
        }
        return null;
    }

    public static boolean isValidOffhand(ItemStack offhand) {
        return offhand != null && offhand.stackSize > 0;
    }

    public static boolean isBlock(ItemStack stack) {
        return stack != null && stack.getItem() instanceof ItemBlock;
    }

    public static boolean isPlayerBusy(EntityPlayer player, OffhandAccess access) {
        return access.miteassistant$isUsingOffhand() || player.isUsingItem();
    }

    public static ItemStack[] copyHotbar(InventoryPlayer inventory) {
        ItemStack[] hotbar = new ItemStack[OffhandConstants.HOTBAR_SIZE];
        for (int i = 0; i < OffhandConstants.HOTBAR_SIZE; i++) {
            hotbar[i] = inventory.mainInventory[i];
        }
        return hotbar;
    }
}
