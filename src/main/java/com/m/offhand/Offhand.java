package com.m.offhand;

import com.m.offhand.api.compat.OffhandCompatRegistry;
import com.m.offhand.api.core.OffhandUtils;
import com.m.offhand.client.OffhandKeyHandler;
import com.m.offhand.event.OffhandEventHandler;
import com.m.offhand.network.OffhandPacketHandler;
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
        OffhandConfig.init();
        OffhandPacketHandler.init();
        OffhandEventHandler.init();
        
        if (!FabricUtil.isServer()) {
            OffhandKeyHandler keyHandler = new OffhandKeyHandler();
            Handlers.Keybinding.register(keyHandler);
            Handlers.Tick.register(keyHandler);
        }
        
        LOGGER.info("Offhand-MITE initialized");
    }

    public static boolean isOffhandBlacklisted(ItemStack stack) {
        return OffhandConfig.isOffhandBlacklisted(stack);
    }

    public static void debug(String message, Object... args) {
        if (OffhandConfig.isDebugLogging()) {
            LOGGER.info(message, args);
        }
    }

    public static void swapOffhand(EntityPlayer player) {
        if (player == null) return;
        
        ItemStack mainhandItem = player.inventory.getCurrentItemStack();
        ItemStack offhandItem = OffhandUtils.getOffhandItem(player);
        
        if (isOffhandBlacklisted(mainhandItem) || isOffhandBlacklisted(offhandItem)) {
            return;
        }
        
        OffhandUtils.setPlayerOffhandItem(player, mainhandItem);
        player.inventory.mainInventory[player.inventory.currentItem] = offhandItem;
        
        if (player.inventoryContainer != null) {
            player.inventoryContainer.onCraftMatrixChanged(player.inventory);
        }
        
        if (!player.worldObj.isRemote && player instanceof ServerPlayer) {
            OffhandCompatRegistry.getSyncStrategy().syncOffhandItem(player);
        }
    }
}
