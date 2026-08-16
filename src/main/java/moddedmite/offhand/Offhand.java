package moddedmite.offhand;

import moddedmite.offhand.api.compat.OffhandCompatRegistry;
import moddedmite.offhand.api.core.IOffhandPlayer;
import moddedmite.offhand.api.core.OffhandUtils;
import moddedmite.offhand.client.OffhandKeyHandler;
import moddedmite.offhand.config.OffhandManyLibConfig;
import moddedmite.offhand.network.OffhandCancelUsage;
import moddedmite.offhand.network.OffhandPacketHandler;
import moddedmite.rustedironcore.api.event.Handlers;
import moddedmite.rustedironcore.api.util.FabricUtil;
import net.fabricmc.api.ModInitializer;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.ServerPlayer;
import net.xiaoyu233.fml.ModResourceManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Offhand implements ModInitializer {

    public static final String MODID = "offhand";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    @Override
    public void onInitialize() {
        ModResourceManager.addResourcePackDomain(MODID);
        OffhandManyLibConfig.init();
        OffhandPacketHandler.init();

        if (!FabricUtil.isServer()) {
            OffhandKeyHandler keyHandler = new OffhandKeyHandler();
            Handlers.Keybinding.register(event -> event.register(OffhandKeyHandler.SWAP_KEY));
            Handlers.Tick.register(keyHandler);
        }

        LOGGER.info("Offhand-MITE initialized");
    }

    public static boolean isOffhandBlacklisted(ItemStack stack) {
        return OffhandManyLibConfig.isOffhandBlacklisted(stack);
    }

    public static void debug(String message, Object... args) {
        if (OffhandManyLibConfig.DEBUG_LOGGING.getBooleanValue()) {
            LOGGER.info(message, args);
        }
    }

    public static void swapOffhand(EntityPlayer player) {
        if (player == null) return;

        int currentSlot = player.inventory.currentItem;
        int offhandSlot = OffhandUtils.getOffhandSlot(player);
        if (offhandSlot < 0
            || offhandSlot >= player.inventory.mainInventory.length
            || currentSlot < 0
            || currentSlot >= player.inventory.mainInventory.length
            || currentSlot == offhandSlot) {
            return;
        }

        ItemStack mainhandItem = player.inventory.getCurrentItemStack();
        ItemStack offhandItem = OffhandUtils.getOffhandItem(player);

        if (isOffhandBlacklisted(mainhandItem) || isOffhandBlacklisted(offhandItem)) {
            return;
        }

        IOffhandPlayer offhandPlayer = (IOffhandPlayer) player;
        boolean wasUsingItem = player.isUsingItem() || offhandPlayer.isOffhandItemInUse();
        offhandPlayer.cancelActiveItemUse();
        if (wasUsingItem && player instanceof ServerPlayer serverPlayer) {
            OffhandCancelUsage.sendToClient(serverPlayer);
        }

        player.inventory.setInventorySlotContents(offhandSlot, mainhandItem);
        player.inventory.setInventorySlotContents(currentSlot, offhandItem);

        if (player.inventoryContainer != null) {
            player.inventoryContainer.onCraftMatrixChanged(player.inventory);
        }

        OffhandCompatRegistry.syncOffhandItem(player);
    }
}
