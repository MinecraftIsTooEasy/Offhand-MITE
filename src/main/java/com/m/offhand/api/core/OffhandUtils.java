package com.m.offhand.api.core;

import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.PlayerControllerMP;
import net.minecraft.ServerPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;

public final class OffhandUtils {

    public static final List<Class<? extends Item>> offhandPriorityItems = new ArrayList<Class<? extends Item>>();
    public static final List<Class<? extends Item>> deprioritizedMainhand = new ArrayList<Class<? extends Item>>();
    private static final ThreadLocal<Boolean> CLIENT_OFFHAND_USE_CONTEXT = new ThreadLocal<Boolean>();
    private static final ThreadLocal<Boolean> CLIENT_OFFHAND_RENDER_CONTEXT = new ThreadLocal<Boolean>();
    private static int clientSuppressMainhandRenderUntilTick = -1;

    private OffhandUtils() {
    }

    public static void setPlayerOffhandItem(EntityPlayer player, ItemStack stack) {
        ((IOffhandInventory) player.inventory).setOffhandItem(stack);
    }

    public static ItemStack getOffhandItem(EntityPlayer player) {
        return ((IOffhandInventory) player.inventory).getOffhandItem();
    }

    public static void useOffhandItem(EntityPlayer player, Runnable action) {
        useOffhandItem(player, true, action);
    }

    public static void useOffhandItem(EntityPlayer player, boolean syncSlot, Runnable action) {
        useOffhandItem(player, syncSlot, new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() {
                action.run();
                return true;
            }
        });
    }

    public static boolean useOffhandItem(EntityPlayer player, BooleanSupplier action) {
        return useOffhandItem(player, true, action);
    }

    public static boolean useOffhandItem(EntityPlayer player, boolean syncSlot, BooleanSupplier action) {
        if (player == null || action == null) {
            return false;
        }

        int offhandSlot = getOffhandSlot(player);
        if (offhandSlot < 0 || offhandSlot >= player.inventory.mainInventory.length) {
            return false;
        }

        int oldSlot = player.inventory.currentItem;
        if (oldSlot == offhandSlot) {
            return action.getAsBoolean();
        }

        PlayerControllerMP controller = null;
        if (syncSlot && player.worldObj != null && player.worldObj.isRemote) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc != null) {
                controller = mc.playerController;
            }
        }

        player.inventory.currentItem = offhandSlot;
        if (controller != null) {
            controller.syncCurrentPlayItem();
        }

        try {
            return action.getAsBoolean();
        } finally {
            player.inventory.currentItem = oldSlot;
            if (controller != null) {
                controller.syncCurrentPlayItem();
            }
        }
    }

    public static boolean isUsingOffhand(EntityPlayer player) {
        return ((IOffhandPlayer) player).isUsingOffhand();
    }

    public static int getOffhandSlot(EntityPlayer player) {
        return ((IOffhandInventory) player.inventory).getOffhandSlot();
    }

    @SafeVarargs
    public static void addOffhandPriorityItem(Class<? extends Item>... itemClass) {
        offhandPriorityItems.addAll(Arrays.asList(itemClass));
    }

    @SafeVarargs
    public static void addDeprioritizedMainhandItem(Class<? extends Item>... itemClass) {
        deprioritizedMainhand.addAll(Arrays.asList(itemClass));
    }

    public static boolean isValidPlayer(Entity entity) {
        if (entity instanceof ServerPlayer) {
            ServerPlayer serverPlayer = (ServerPlayer) entity;
            return serverPlayer.playerNetServerHandler != null;
        }
        return false;
    }

    public static int swapToOffhand(EntityPlayer player) {
        int heldItemTemp = player.inventory.currentItem;
        player.inventory.currentItem = getOffhandSlot(player);
        return heldItemTemp;
    }

    public static void swapBack(EntityPlayer player, int heldItemTemp) {
        player.inventory.currentItem = heldItemTemp;
    }

    public static void beginClientOffhandUseContext() {
        CLIENT_OFFHAND_USE_CONTEXT.set(Boolean.TRUE);
    }

    public static void endClientOffhandUseContext() {
        CLIENT_OFFHAND_USE_CONTEXT.remove();
    }

    public static boolean isClientOffhandUseContext() {
        Boolean value = CLIENT_OFFHAND_USE_CONTEXT.get();
        return value != null && value.booleanValue();
    }

    public static void beginClientOffhandRenderContext() {
        CLIENT_OFFHAND_RENDER_CONTEXT.set(Boolean.TRUE);
    }

    public static void endClientOffhandRenderContext() {
        CLIENT_OFFHAND_RENDER_CONTEXT.remove();
    }

    public static boolean isClientOffhandRenderContext() {
        Boolean value = CLIENT_OFFHAND_RENDER_CONTEXT.get();
        return value != null && value.booleanValue();
    }

    public static void markClientSuppressMainhandRender(EntityPlayer player, int ticks) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return;
        }
        int safeTicks = ticks < 0 ? 0 : ticks;
        clientSuppressMainhandRenderUntilTick = player.ticksExisted + safeTicks;
    }

    public static boolean shouldSuppressClientMainhandRender(EntityPlayer player) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return false;
        }
        return player.ticksExisted <= clientSuppressMainhandRenderUntilTick;
    }
}
