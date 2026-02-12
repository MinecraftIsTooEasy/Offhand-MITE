package com.m.offhand.config;

import com.m.offhand.OffhandMod;
import net.xiaoyu233.fml.config.ConfigEntry;
import net.xiaoyu233.fml.config.ConfigRoot;
import net.xiaoyu233.fml.util.FieldReference;

import java.io.File;

public class OffhandConfig {
    public static final FieldReference<Boolean> enableOffhand = new FieldReference<>(true);
    public static final FieldReference<Boolean> enableFoodOffhand = new FieldReference<>(true);
    public static final FieldReference<Boolean> enableBowOffhand = new FieldReference<>(true);
    public static final FieldReference<Boolean> enableBlockOffhand = new FieldReference<>(true);
    public static final FieldReference<String> disabledItems = new FieldReference<>("item.fishingRod,item.egg");
    public static final FieldReference<Integer> swapCooldown = new FieldReference<>(300);
    public static final FieldReference<Integer> useCooldown = new FieldReference<>(250);

    public static final ConfigRoot ROOT = ConfigRoot.create(1)
            .addEntry(ConfigEntry.of("启用副手功能", enableOffhand)
                    .withComment("是否启用副手功能"))
            .addEntry(ConfigEntry.of("启用副手食物", enableFoodOffhand)
                    .withComment("是否允许在副手中使用食物"))
            .addEntry(ConfigEntry.of("启用副手弓箭", enableBowOffhand)
                    .withComment("是否允许在副手中使用弓箭"))
            .addEntry(ConfigEntry.of("启用副手方块", enableBlockOffhand)
                    .withComment("是否允许从副手放置方块"))
            .addEntry(ConfigEntry.of("禁用的物品", disabledItems)
                    .withComment("不能在副手中使用的物品ID列表，用逗号分隔"))
            .addEntry(ConfigEntry.of("交换冷却时间(ms)", swapCooldown)
                    .withComment("副手交换冷却时间（毫秒）"))
            .addEntry(ConfigEntry.of("使用冷却时间(ms)", useCooldown)
                    .withComment("副手使用冷却时间（毫秒）"));

    public static final File CONFIG_FILE = new File(OffhandMod.MOD_NAME + ".json");

    public static void init() {
        ROOT.readFromFile(CONFIG_FILE);
    }
    
    public static long getSwapCooldown() {
        return swapCooldown.get();
    }
    
    public static long getUseCooldown() {
        return useCooldown.get();
    }
}
