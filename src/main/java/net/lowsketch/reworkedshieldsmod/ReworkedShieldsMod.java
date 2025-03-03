package net.lowsketch.reworkedshieldsmod;

import item.ModItems;
import item.ModItemsGroups;
import net.fabricmc.api.ModInitializer;

import net.lowsketch.reworkedshieldsmod.config.ConfigManager;
import net.lowsketch.reworkedshieldsmod.enchantments.ModEnchantments;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReworkedShieldsMod implements ModInitializer {
	public static final String MOD_ID = "reworkedshieldsmod";

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModItemsGroups.registerItemGroups();
		ModEnchantments.registerModEnchantments();
		ConfigManager.loadConfig();
	}
}