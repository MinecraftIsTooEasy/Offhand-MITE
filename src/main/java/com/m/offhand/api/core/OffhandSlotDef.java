package com.m.offhand.api.core;

import net.minecraft.EntityPlayer;
import net.minecraft.InventoryPlayer;

public final class OffhandSlotDef {

    private OffhandSlotDef() {
    }

    public static boolean isValidSwitchSlot(EntityPlayer player, int slot) {
        int offhand = ((IOffhandInventory) player.inventory).getOffhandSlot();
        return (slot >= 0 && slot < InventoryPlayer.getHotbarSize()) || slot == offhand;
    }
}

