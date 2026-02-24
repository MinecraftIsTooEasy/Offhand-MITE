package com.m.offhand.api.core;

import net.minecraft.ItemStack;

public interface IOffhandPlayer {

    void swingOffItem();

    float getOffSwingProgress(float frame);

    void setOffhandItemInUse(boolean usingOffhand);

    boolean isOffhandItemInUse();

    boolean isUsingOffhand();

    void setMainhandSlot(int slot);

    ItemStack getMainhandItem();
}
