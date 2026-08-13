package moddedMite.offhand.api.core;

import net.minecraft.Entity;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Minecraft;
import net.minecraft.PlayerControllerMP;
import net.minecraft.ServerPlayer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BooleanSupplier;

public final class OffhandUtils {

    private static final ThreadLocal<Boolean> CLIENT_OFFHAND_USE_CONTEXT = new ThreadLocal<Boolean>();
    private static final ThreadLocal<Boolean> CLIENT_OFFHAND_RENDER_CONTEXT = new ThreadLocal<Boolean>();
    private static final ThreadLocal<Deque<EntityPlayer>> OFFHAND_ITEM_USE_CONTEXT =
        new ThreadLocal<Deque<EntityPlayer>>();

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

        boolean offhandSelection = slot == getOffhandSlot(player);
        if (offhandSelection) {
            beginOffhandItemUseContext(player);
        }

        try {
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
        } finally {
            if (offhandSelection) {
                endOffhandItemUseContext(player);
            }
        }
    }

    public static boolean isOffhandItemUseContext(EntityPlayer player) {
        Deque<EntityPlayer> players = OFFHAND_ITEM_USE_CONTEXT.get();
        return player != null && players != null && players.peek() == player;
    }

    private static void beginOffhandItemUseContext(EntityPlayer player) {
        Deque<EntityPlayer> players = OFFHAND_ITEM_USE_CONTEXT.get();
        if (players == null) {
            players = new ArrayDeque<EntityPlayer>();
            OFFHAND_ITEM_USE_CONTEXT.set(players);
        }
        players.push(player);
    }

    private static void endOffhandItemUseContext(EntityPlayer player) {
        Deque<EntityPlayer> players = OFFHAND_ITEM_USE_CONTEXT.get();
        if (players == null || players.isEmpty()) {
            OFFHAND_ITEM_USE_CONTEXT.remove();
            return;
        }

        if (players.peek() == player) {
            players.pop();
        } else {
            players.removeFirstOccurrence(player);
        }

        if (players.isEmpty()) {
            OFFHAND_ITEM_USE_CONTEXT.remove();
        }
    }

    public static boolean isUsingOffhand(EntityPlayer player) {
        return ((IOffhandPlayer) player).isUsingOffhand();
    }

    public static int getOffhandSlot(EntityPlayer player) {
        return ((IOffhandInventory) player.inventory).getOffhandSlot();
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
        ((IOffhandClientState) player).markMainhandRenderSuppressed(ticks);
    }

    public static boolean shouldSuppressClientMainhandRender(EntityPlayer player) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return false;
        }
        return ((IOffhandClientState) player).shouldSuppressMainhandRender();
    }

    public static void markClientOffhandAttackAnimation(EntityPlayer player, int ticks) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return;
        }
        ((IOffhandClientState) player).markOffhandAttackAnimation(ticks);
    }

    public static boolean shouldAnimateClientOffhandAttack(EntityPlayer player) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return false;
        }
        return ((IOffhandClientState) player).shouldAnimateOffhandAttack();
    }

    public static void markClientOffhandMiningAnimation(EntityPlayer player, int ticks) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return;
        }
        ((IOffhandClientState) player).markOffhandMiningAnimation(ticks);
    }

    public static boolean shouldAnimateClientOffhandMining(EntityPlayer player) {
        if (player == null || player.worldObj == null || !player.worldObj.isRemote) {
            return false;
        }
        return ((IOffhandClientState) player).shouldAnimateOffhandMining();
    }

}
