package net.lowsketch.reworkedshieldsmod;

import item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.lowsketch.reworkedshieldsmod.config.ConfigManager;
import net.lowsketch.reworkedshieldsmod.enchantment.ModEnchantmentEffects;
import net.lowsketch.reworkedshieldsmod.enchantment.ModEnchantments;
import net.lowsketch.reworkedshieldsmod.item.ModItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReworkedShieldsMod implements ModInitializer {
	public static final String MOD_ID = "reworkedshieldsmod";

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEnchantmentEffects.registerModEnchantments();
		ConfigManager.loadConfig();
	}
}