package com.m.offhand.api.compat;

import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.RaycastCollision;

public interface IOffhandInteractionPolicy {

    default boolean canUseOffhandForBlockInteraction(
        EntityPlayer player,
        ItemStack mainhandStack,
        ItemStack offhandStack,
        RaycastCollision target) {
        return true;
    }
}
