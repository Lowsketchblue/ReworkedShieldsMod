package net.lowsketch.reworkedshieldsmod;

import item.ModItems;
import net.fabricmc.api.ModInitializer;

import net.lowsketch.reworkedshieldsmod.config.ConfigManager;
import net.lowsketch.reworkedshieldsmod.enchantment.ModEnchantmentEffects;
import net.lowsketch.reworkedshieldsmod.item.ModItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReworkedShieldsMod implements ModInitializer {
	public static final String MOD_ID = "reworkedshieldsmod";
	public static final Identifier MACE_HIT_SOUND_ID = Identifier.of("reworkedshieldsmod", "mace_hit");
	public static final SoundEvent MACE_HIT_SOUND_EVENT = SoundEvent.of(MACE_HIT_SOUND_ID);

	// This logger is used to write text to the console and the log file.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		Registry.register(Registries.SOUND_EVENT, MACE_HIT_SOUND_ID, MACE_HIT_SOUND_EVENT);
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
		ModEnchantmentEffects.registerEffects(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE);
		ConfigManager.loadConfig();
	}
}