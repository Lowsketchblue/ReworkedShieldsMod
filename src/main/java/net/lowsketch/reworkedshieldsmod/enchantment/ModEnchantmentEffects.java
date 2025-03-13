package net.lowsketch.reworkedshieldsmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.lowsketch.reworkedshieldsmod.enchantment.enchantments.LightningStrikerEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {

    public static final MapCodec<? extends EnchantmentEntityEffect> LIGHTNING_STRIKER =
            register("lightning_striker", LightningStrikerEnchantmentEffect.CODEC);

    //public static MapCodec<LightningEnchantmentEffect> LIGHTNING_EFFECT = register("lightning_effect", LightningEnchantmentEffect.CODEC);

    private static <T extends EnchantmentEntityEffect> MapCodec<T> register(String id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(ReworkedShieldsMod.MOD_ID, id), codec);
    }


    public static void registerModEnchantments() {
        ReworkedShieldsMod.LOGGER.info("Registering EnchantmentEffects for" + ReworkedShieldsMod.MOD_ID);
    }
}
