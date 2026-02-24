package com.m.offhand.api.core;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;

public interface IOffhandInventory {

    ItemStack getOffhandItem();

    void setOffhandItem(ItemStack itemStack);

    int getOffhandSlot();

    static boolean isValidSwitch(int id, EntityPlayer player) {
        return player != null && OffhandSlotDef.isValidSwitchSlot(player, id);
    }
}
