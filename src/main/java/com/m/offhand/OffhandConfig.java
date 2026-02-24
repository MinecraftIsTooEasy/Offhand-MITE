package com.m.offhand;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.ItemStack;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OffhandConfig {

    public static boolean offhandAttack = false;
    public static boolean emptyOffhand = false;
    public static boolean offhandBreakBlocks = false;
    public static boolean offhandPickup = false;
    public static boolean debugLogging = false;

    private static final Set<String> offhandBlacklist = new HashSet<>();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = Paths.get("config", "Offhand-mite.json");

    public static synchronized void init() {
        offhandBlacklist.clear();
        ConfigData loaded = load();
        if (loaded != null) {
            apply(loaded);
        }
        save();
    }

    public static synchronized void addToBlacklist(String itemName) {
        if (itemName == null || itemName.isBlank()) {
            return;
        }
        if (offhandBlacklist.add(itemName)) {
            save();
        }
    }

    public static synchronized void removeFromBlacklist(String itemName) {
        if (itemName == null) {
            return;
        }
        if (offhandBlacklist.remove(itemName)) {
            save();
        }
    }

    public static boolean isOffhandBlacklisted(ItemStack stack) {
        if (stack == null || stack.getItem() == null) return false;
        String itemName = stack.getItem().getUnlocalizedName();
        return offhandBlacklist.contains(itemName);
    }

    public static boolean isBlacklisted(String itemName) {
        return offhandBlacklist.contains(itemName);
    }

    public static synchronized Set<String> getBlacklist() {
        return new HashSet<>(offhandBlacklist);
    }

    public static synchronized boolean isDebugLogging() {
        return debugLogging;
    }

    public static synchronized void setDebugLogging(boolean enabled) {
        if (debugLogging != enabled) {
            debugLogging = enabled;
            save();
        }
    }

    public static synchronized void setOffhandAttack(boolean enabled) {
        if (offhandAttack != enabled) {
            offhandAttack = enabled;
            save();
        }
    }

    public static synchronized void setEmptyOffhand(boolean enabled) {
        if (emptyOffhand != enabled) {
            emptyOffhand = enabled;
            save();
        }
    }

    public static synchronized void setOffhandBreakBlocks(boolean enabled) {
        if (offhandBreakBlocks != enabled) {
            offhandBreakBlocks = enabled;
            save();
        }
    }

    public static synchronized void setOffhandPickup(boolean enabled) {
        if (offhandPickup != enabled) {
            offhandPickup = enabled;
            save();
        }
    }

    private static ConfigData load() {
        if (!Files.exists(CONFIG_PATH)) {
            return null;
        }

        try (Reader reader = Files.newBufferedReader(CONFIG_PATH)) {
            return GSON.fromJson(reader, ConfigData.class);
        } catch (Exception e) {
            Offhand.LOGGER.warn("Failed to load config {}, using defaults", CONFIG_PATH, e);
            return null;
        }
    }

    private static void apply(ConfigData data) {
        if (data == null) {
            return;
        }

        offhandAttack = data.offhandAttack;
        emptyOffhand = data.emptyOffhand;
        offhandBreakBlocks = data.offhandBreakBlocks;
        offhandPickup = data.offhandPickup;
        debugLogging = data.debugLogging;

        offhandBlacklist.clear();
        if (data.offhandBlacklist != null) {
            for (String value : data.offhandBlacklist) {
                if (value != null && !value.isBlank()) {
                    offhandBlacklist.add(value);
                }
            }
        }
    }

    private static ConfigData snapshot() {
        ConfigData data = new ConfigData();
        data.offhandAttack = offhandAttack;
        data.emptyOffhand = emptyOffhand;
        data.offhandBreakBlocks = offhandBreakBlocks;
        data.offhandPickup = offhandPickup;
        data.debugLogging = debugLogging;
        data.offhandBlacklist = new ArrayList<>(offhandBlacklist);
        return data;
    }

    private static synchronized void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (Writer writer = Files.newBufferedWriter(CONFIG_PATH)) {
                GSON.toJson(snapshot(), writer);
            }
        } catch (IOException e) {
            Offhand.LOGGER.warn("Failed to save config {}", CONFIG_PATH, e);
        }
    }

    private static final class ConfigData {
        boolean offhandAttack = false;
        boolean emptyOffhand = false;
        boolean offhandBreakBlocks = false;
        boolean offhandPickup = false;
        boolean debugLogging = false;
        List<String> offhandBlacklist = new ArrayList<>();
    }
}
