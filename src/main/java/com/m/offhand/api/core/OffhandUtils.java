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
    private static int clientOffhandAttackAnimationUntilTick = -1;
    private static int clientOffhandMiningAnimationUntilTick = -1;

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
        return useSelectedSlot(player, offhandSlot, syncSlot, action);
    }

    public static boolean useSelectedSlot(EntityPlayer player, int slot, boolean syncSlot, BooleanSupplier action) {
        if (player == null || player.inventory == null || player.inventory.mainInventory == null || action == null) {
            return false;
        }

        if (!isValidInventorySlot(player, slot)) {
            return false;
        }

        int oldSlot = player.inventory.currentItem;
        if (oldSlot == slot) {
            return action.getAsBoolean();
        }

        PlayerControllerMP controller = null;
        if (syncSlot && player.worldObj != null && player.worldObj.isRemote) {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc != null) {
                controller = mc.playerController;
            }
        }

        rememberMainhandSlot(player, oldSlot);
        player.inventory.currentItem = slot;
        try {
            if (controller != null) {
                controller.syncCurrentPlayItem();
            }
            return action.getAsBoolean();
        } finally {
            restoreSelectedSlot(player, oldSlot, controller);
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
        int offhandSlot = getOffhandSlot(player);
        if (isValidInventorySlot(player, offhandSlot)) {
            rememberMainhandSlot(player, heldItemTemp);
            player.inventory.currentItem = offhandSlot;
        }
        return heldItemTemp;
    }

    public static void swapBack(EntityPlayer player, int heldItemTemp) {
        restoreSelectedSlot(player, heldItemTemp, null);
    }

    private static boolean isValidInventorySlot(EntityPlayer player, int slot) {
        return player != null
            && player.inventory != null
            && player.inventory.mainInventory != null
            && slot >= 0
            && slot < player.inventory.mainInventory.length;
    }

    private static void restoreSelectedSlot(EntityPlayer player, int oldSlot, PlayerControllerMP controller) {
        if (player == null || player.inventory == null || player.inventory.mainInventory == null) {
            return;
        }

        int restoredSlot = oldSlot;
        if (!isValidInventorySlot(player, restoredSlot)) {
            restoredSlot = player.inventory.mainInventory.length > 0 ? 0 : -1;
        }

        if (restoredSlot >= 0) {
            player.inventory.currentItem = restoredSlot;
            rememberMainhandSlot(player, restoredSlot);
            if (controller != null) {
                controller.syncCurrentPlayItem();
            }
        }
    }

    private static void rememberMainhandSlot(EntityPlayer player, int slot) {
        if (!isValidInventorySlot(player, slot)) {
            return;
        }

        int offhandSlot = getOffhandSlot(player);
        if (slot != offhandSlot) {
            ((IOffhandPlayer) player).setMainhandSlot(slot);
        }
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

    public static void markClientOffhandAttackAnimation(EntityPlayer player, int ticks) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return;
        }
        int safeTicks = ticks < 0 ? 0 : ticks;
        clientOffhandAttackAnimationUntilTick = player.ticksExisted + safeTicks;
    }

    public static boolean shouldAnimateClientOffhandAttack(EntityPlayer player) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return false;
        }
        return player.ticksExisted <= clientOffhandAttackAnimationUntilTick;
    }

    public static void markClientOffhandMiningAnimation(EntityPlayer player, int ticks) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return;
        }
        int safeTicks = ticks < 0 ? 0 : ticks;
        clientOffhandMiningAnimationUntilTick = player.ticksExisted + safeTicks;
    }

    public static boolean shouldAnimateClientOffhandMining(EntityPlayer player) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return false;
        }
        return player.ticksExisted <= clientOffhandMiningAnimationUntilTick;
    }
}
