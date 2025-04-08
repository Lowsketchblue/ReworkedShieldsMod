package net.lowsketch.reworkedshieldsmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.lowsketch.reworkedshieldsmod.datagen.ModRegistryDataGenerator;
import net.lowsketch.reworkedshieldsmod.enchantment.ModEnchantmentEffects;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class ReworkedShieldsModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModRegistryDataGenerator::new);
	}
	@Override
	public void	buildRegistry(RegistryBuilder registryBuilder)
	{
		//registryBuilder.addRegistry(RegistryKeys.ENCHANTMENT, ModEnchantmentEffects::registerEffects);
	}
}
