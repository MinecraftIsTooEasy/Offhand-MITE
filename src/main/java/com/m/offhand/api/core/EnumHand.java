package com.m.offhand.api.core;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;

public enum EnumHand {

    MAIN_HAND,
    OFF_HAND;

    public ItemStack getItem(EntityPlayer player) {
        switch (this) {
            case MAIN_HAND:
                return OffhandUtils.isUsingOffhand(player) ? ((IOffhandPlayer) player).getMainhandItem() : player.inventory.getCurrentItemStack();
            case OFF_HAND:
                return OffhandUtils.getOffhandItem(player);
            default:
                return null;
        }
    }

    public static final EnumHand[] HANDS = { EnumHand.MAIN_HAND, EnumHand.OFF_HAND };

    public static final EnumHand[] HANDS_REV = { EnumHand.OFF_HAND, EnumHand.MAIN_HAND };
}
