package net.lowsketch.reworkedshieldsmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.lowsketch.reworkedshieldsmod.enchantment.effects.LightningEnchantmentEffect;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModEnchantmentEffects {
    public static final RegistryKey<Enchantment> THUNDERING = of("thundering");
    public static RegistryKey<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_EFFECT_KEY = key("lightning_effect");
    public static MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT;

    private static RegistryKey<Enchantment> of(String path) {
        Identifier id = Identifier.of(ReworkedShieldsMod.MOD_ID, path);
        return RegistryKey.of(RegistryKeys.ENCHANTMENT, id);
    }

    private static RegistryKey<MapCodec<? extends EnchantmentEntityEffect>> key(String name) {
        return RegistryKey.of(RegistryKeys.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(ReworkedShieldsMod.MOD_ID, name));
    }

    public static void registerEffects(Registry<MapCodec<? extends EnchantmentEntityEffect>> registry) {
        LIGHTNING_EFFECT = Registry.register(registry, LIGHTNING_EFFECT_KEY, LightningEnchantmentEffect.CODEC);
    }

    public static void registerModEnchantmentEffects() {
        ReworkedShieldsMod.LOGGER.info("Registering EnchantmentEffects for " + ReworkedShieldsMod.MOD_ID);
    }

}