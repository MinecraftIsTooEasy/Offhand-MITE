package com.m.offhand.api.core;

import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;

public final class OffhandSlotDef {

    private OffhandSlotDef() {
    }

    // ContainerPlayer in 1.6.4 MITE:
    // 0: result, 1-4: craft matrix, 5-8: armor, 9-35: player inv, 36-44: hotbar, 45: offhand
    public static final int PLAYER_INV_START = 9;
    public static final int PLAYER_HOTBAR_START = 36;
    public static final int PLAYER_HOTBAR_END = 45;

    public static boolean isValidSwitchSlot(EntityPlayer player, int slot) {
        int offhand = ((IOffhandInventory) player.inventory).getOffhandSlot();
        return (slot >= 0 && slot < InventoryPlayer.getHotbarSize()) || slot == offhand;
    }
}

