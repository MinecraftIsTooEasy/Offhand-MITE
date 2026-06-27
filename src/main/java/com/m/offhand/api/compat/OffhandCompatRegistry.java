package com.m.offhand.api.compat;

import com.m.offhand.network.OffhandSyncOffhandUse;
import com.m.offhand.network.OffhandSyncPacket;
import net.minecraft.EntityPlayer;
import net.minecraft.ServerPlayer;

public final class OffhandCompatRegistry {

    public static final int API_VERSION = 1;

    private static IOffhandInteractionPolicy interactionPolicy = new IOffhandInteractionPolicy() {
    };

    private static IOffhandRenderPolicy renderPolicy = new IOffhandRenderPolicy() {
    };

    private static IOffhandSyncStrategy syncStrategy = new DefaultSyncStrategy();

    private static IOffhandActionFilter actionFilter = new IOffhandActionFilter() {
    };

    private OffhandCompatRegistry() {
    }

    public static IOffhandInteractionPolicy getInteractionPolicy() {
        return interactionPolicy;
    }

    public static void setInteractionPolicy(IOffhandInteractionPolicy policy) {
        interactionPolicy = policy == null ? new IOffhandInteractionPolicy() {
        } : policy;
    }

    public static IOffhandRenderPolicy getRenderPolicy() {
        return renderPolicy;
    }

    public static void setRenderPolicy(IOffhandRenderPolicy policy) {
        renderPolicy = policy == null ? new IOffhandRenderPolicy() {
        } : policy;
    }

    public static IOffhandSyncStrategy getSyncStrategy() {
        return syncStrategy;
    }

    public static void setSyncStrategy(IOffhandSyncStrategy strategy) {
        syncStrategy = strategy == null ? new DefaultSyncStrategy() : strategy;
    }

    public static IOffhandActionFilter getActionFilter() {
        return actionFilter;
    }

    public static void setActionFilter(IOffhandActionFilter filter) {
        actionFilter = filter == null ? new IOffhandActionFilter() {
        } : filter;
    }

    public static void syncOffhandItem(EntityPlayer target) {
        if (target != null && target.worldObj != null && !target.worldObj.isRemote && target instanceof ServerPlayer) {
            syncStrategy.syncOffhandItem(target);
        }
    }

    public static void syncOffhandUseState(EntityPlayer target, boolean usingOffhand) {
        if (target != null && target.worldObj != null && !target.worldObj.isRemote && target instanceof ServerPlayer) {
            syncStrategy.syncOffhandUseState(target, usingOffhand);
        }
    }

    public static void syncOffhandAnimation(EntityPlayer target) {
        // Offhand swing animation is disabled; kept as a no-op for API compatibility.
    }

    public static void syncOffhandItemToPlayer(ServerPlayer receiver, EntityPlayer target) {
        if (receiver != null && target != null) {
            syncStrategy.syncOffhandItemToPlayer(receiver, target);
        }
    }

    private static final class DefaultSyncStrategy implements IOffhandSyncStrategy {

        @Override
        public void syncOffhandItem(EntityPlayer target) {
            OffhandSyncPacket.sendToTracking(target);
        }

        @Override
        public void syncOffhandUseState(EntityPlayer target, boolean usingOffhand) {
            OffhandSyncOffhandUse.sendToTracking(target, usingOffhand);
        }

        @Override
        public void syncOffhandAnimation(EntityPlayer target) {
            // No-op: offhand swing animation is disabled.
        }

        @Override
        public void syncOffhandItemToPlayer(ServerPlayer receiver, EntityPlayer target) {
            OffhandSyncPacket.sendToClient(receiver, target);
        }
    }
}
