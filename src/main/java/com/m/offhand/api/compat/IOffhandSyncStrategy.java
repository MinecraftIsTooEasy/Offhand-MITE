package com.m.offhand.api.compat;

import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;

public interface IOffhandSyncStrategy {

    void syncOffhandItem(EntityPlayer target);

    void syncOffhandUseState(EntityPlayer target, boolean usingOffhand);

    void syncOffhandAnimation(EntityPlayer target);

    void syncOffhandItemToPlayer(ServerPlayer receiver, EntityPlayer target);
}
