package com.m.offhand.api.compat;

import net.minecraft.EntityPlayer;

public interface IOffhandRenderPolicy {

    default boolean shouldRenderHotbarOffhand(EntityPlayer player) {
        return true;
    }

    default boolean shouldRenderFirstPersonOffhand(EntityPlayer player) {
        return true;
    }

    default boolean shouldRenderThirdPersonOffhand(EntityPlayer player) {
        return true;
    }
}
