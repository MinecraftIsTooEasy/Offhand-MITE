package com.m.offhand;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.config.SimpleConfigs;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigStringList;
import net.minecraft.ItemStack;

import java.util.List;

public final class OffhandManyLibConfig extends SimpleConfigs {

    private static final String CONFIG_NAME = "Offhand";

    public static final ConfigBoolean OFFHAND_ATTACK = new ConfigBoolean(
        "offhand.offhandAttack",
        false,
        "Allows offhand weapons and melee tools to add damage and durability use during attacks.");

    public static final ConfigBoolean OFFHAND_BREAK_BLOCKS = new ConfigBoolean(
        "offhand.offhandBreakBlocks",
        false,
        "Adds offhand mining strength when both hands hold tools suitable for the target block.");

    public static final ConfigBoolean OFFHAND_PICKUP = new ConfigBoolean(
        "offhand.offhandPickup",
        false,
        "Allows picked-up items to be placed into the offhand slot.");

    public static final ConfigBoolean DEBUG_LOGGING = new ConfigBoolean(
        "offhand.debugLogging",
        false,
        "Enables debug logging.");

    public static final ConfigStringList OFFHAND_BLACKLIST = new ConfigStringList(
        "offhand.offhandBlacklist",
        List.of(),
        "Offhand item blacklist using item unlocalizedName values.");

    private static final List<ConfigBase<?>> VALUES = List.of(
        OFFHAND_ATTACK,
        OFFHAND_BREAK_BLOCKS,
        OFFHAND_PICKUP,
        DEBUG_LOGGING,
        OFFHAND_BLACKLIST);
    private static final OffhandManyLibConfig INSTANCE = new OffhandManyLibConfig();

    private OffhandManyLibConfig() {
        super(CONFIG_NAME, List.of(), VALUES, "Offhand configuration");
    }

    public static void init() {
        ConfigManager.getInstance().registerConfig(Offhand.MODID, INSTANCE);
        INSTANCE.load();
    }

    public static boolean isOffhandBlacklisted(ItemStack stack) {
        if (stack == null || stack.getItem() == null) {
            return false;
        }

        String itemName = stack.getItem().getUnlocalizedName();
        return itemName != null && OFFHAND_BLACKLIST.getStringListValue().contains(itemName.trim());
    }
}
