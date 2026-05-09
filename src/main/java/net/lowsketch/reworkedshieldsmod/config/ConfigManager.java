package net.lowsketch.reworkedshieldsmod.config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigManager {
    private static final Path CONFIG_PATH = Paths.get("config", "reworkedshieldsmod.config");
    private static Properties properties = new Properties();

    public static void loadConfig() {
        try {
            if (!Files.exists(CONFIG_PATH)) {
                Files.createDirectories(CONFIG_PATH.getParent());
                Files.createFile(CONFIG_PATH);
                setDefaultValues();
            }
            try (InputStream input = Files.newInputStream(CONFIG_PATH)) {
                properties.load(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setDefaultValues() {
        properties.setProperty("wooden_shield_cooldown", "62");
        properties.setProperty("iron_shield_cooldown", "44");
        properties.setProperty("gold_shield_cooldown", "24");
        properties.setProperty("diamond_shield_cooldown", "36");
        properties.setProperty("netherite_shield_cooldown", "28");
        properties.setProperty("parry_ticks", "6");
        properties.setProperty("prevent_shield_spamming", "true");
        saveConfig();
    }

    public static void saveConfig() {
        try (OutputStream output = Files.newOutputStream(CONFIG_PATH)) {
            properties.store(output, "Configuration for ReworkedShieldsMod!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigValue(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getIntConfig(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    public static boolean getBooleanConfig(String key, boolean defaultValue) {
        return Boolean.parseBoolean(properties.getProperty(key, String.valueOf(defaultValue)));
    }
}
