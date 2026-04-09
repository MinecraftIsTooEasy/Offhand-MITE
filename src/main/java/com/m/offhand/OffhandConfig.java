package com.m.offhand;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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
            JsonElement rootElement = GSON.fromJson(reader, JsonElement.class);
            if (rootElement == null || !rootElement.isJsonObject()) {
                return null;
            }

            JsonObject root = rootElement.getAsJsonObject();
            if (root.has("Values") && root.get("Values").isJsonObject()) {
                return loadManyLibStyle(root.getAsJsonObject("Values"));
            }

            ConfigData flat = GSON.fromJson(root, ConfigData.class);
            if (flat != null && flat.offhandBlacklist == null) {
                flat.offhandBlacklist = new ArrayList<>();
            }
            return flat;
        } catch (Exception e) {
            Offhand.LOGGER.warn("Failed to load config {}, using defaults", CONFIG_PATH, e);
            return null;
        }
    }

    private static ConfigData loadManyLibStyle(JsonObject values) {
        ConfigData data = new ConfigData();
        if (values == null) {
            return data;
        }

        data.offhandAttack = readBooleanValue(values, "offhand.offhandAttack", data.offhandAttack);
        data.emptyOffhand = readBooleanValue(values, "offhand.emptyOffhand", data.emptyOffhand);
        data.offhandBreakBlocks = readBooleanValue(values, "offhand.offhandBreakBlocks", data.offhandBreakBlocks);
        data.offhandPickup = readBooleanValue(values, "offhand.offhandPickup", data.offhandPickup);
        data.debugLogging = readBooleanValue(values, "offhand.debugLogging", data.debugLogging);
        data.offhandBlacklist = readStringListValue(values, "offhand.offhandBlacklist");
        return data;
    }

    private static boolean readBooleanValue(JsonObject values, String key, boolean defaultValue) {
        if (values == null || !values.has(key)) {
            return defaultValue;
        }

        JsonElement entry = values.get(key);
        if (entry == null || entry.isJsonNull()) {
            return defaultValue;
        }

        JsonElement valueElement = entry;
        if (entry.isJsonObject()) {
            JsonObject entryObject = entry.getAsJsonObject();
            if (!entryObject.has("value")) {
                return defaultValue;
            }
            valueElement = entryObject.get("value");
        }

        if (valueElement == null || valueElement.isJsonNull()) {
            return defaultValue;
        }

        if (valueElement.isJsonPrimitive()) {
            try {
                return valueElement.getAsBoolean();
            } catch (Exception ignored) {
                return defaultValue;
            }
        }

        return defaultValue;
    }

    private static List<String> readStringListValue(JsonObject values, String key) {
        List<String> result = new ArrayList<>();
        if (values == null || !values.has(key)) {
            return result;
        }

        JsonElement entry = values.get(key);
        if (entry == null || entry.isJsonNull()) {
            return result;
        }

        JsonElement valueElement = entry;
        if (entry.isJsonObject()) {
            JsonObject entryObject = entry.getAsJsonObject();
            if (!entryObject.has("value")) {
                return result;
            }
            valueElement = entryObject.get("value");
        }

        if (valueElement == null || valueElement.isJsonNull()) {
            return result;
        }

        if (valueElement.isJsonArray()) {
            JsonArray array = valueElement.getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                JsonElement element = array.get(i);
                if (element != null && element.isJsonPrimitive()) {
                    String value = element.getAsString();
                    if (value != null && !value.isBlank()) {
                        result.add(value);
                    }
                }
            }
            return result;
        }

        if (valueElement.isJsonPrimitive()) {
            String value = valueElement.getAsString();
            if (value != null && !value.isBlank()) {
                result.add(value);
            }
        }

        return result;
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
                GSON.toJson(snapshotForManyLibStyle(), writer);
            }
        } catch (IOException e) {
            Offhand.LOGGER.warn("Failed to save config {}", CONFIG_PATH, e);
        }
    }

    private static JsonObject snapshotForManyLibStyle() {
        ConfigData data = snapshot();

        JsonObject root = new JsonObject();
        JsonObject values = new JsonObject();
        root.add("Values", values);

        addBooleanValue(values,
            "offhand.offhandAttack",
            data.offhandAttack,
            "是否允许副手参与攻击逻辑（预留项，当前版本建议保持 false）");

        addBooleanValue(values,
            "offhand.emptyOffhand",
            data.emptyOffhand,
            "是否启用空副手限制逻辑（预留项，当前版本建议保持 false）");

        addBooleanValue(values,
            "offhand.offhandBreakBlocks",
            data.offhandBreakBlocks,
            "是否允许副手参与挖掘方块逻辑（预留项，当前版本建议保持 false）");

        addBooleanValue(values,
            "offhand.offhandPickup",
            data.offhandPickup,
            "是否允许地面拾取时把物品放入副手槽（false 更稳，避免背包满时误塞副手）");

        addBooleanValue(values,
            "offhand.debugLogging",
            data.debugLogging,
            "是否输出调试日志（排查问题时开启，平时建议关闭）");

        JsonArray blacklistArray = new JsonArray();
        for (String value : data.offhandBlacklist) {
            if (value != null && !value.isBlank()) {
                blacklistArray.add(value);
            }
        }
        addArrayValue(values,
            "offhand.offhandBlacklist",
            blacklistArray,
            "副手黑名单（填写物品 unlocalizedName，例如 item.bow）");

        return root;
    }

    private static void addBooleanValue(JsonObject values, String key, boolean value, String comment) {
        JsonObject entry = new JsonObject();
        entry.addProperty("value", value);
        entry.addProperty("comment", comment);
        values.add(key, entry);
    }

    private static void addArrayValue(JsonObject values, String key, JsonArray value, String comment) {
        JsonObject entry = new JsonObject();
        entry.add("value", value);
        entry.addProperty("comment", comment);
        values.add(key, entry);
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
