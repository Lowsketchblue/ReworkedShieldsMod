package net.lowsketch.reworkedshieldsmod.enchantment;

import com.mojang.serialization.MapCodec;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.lowsketch.reworkedshieldsmod.enchantment.enchantments.LightningStrikerEnchantmentEffect;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;

public class ModEnchantments {

    public static final RegistryKey<Enchantment> LIGHTNING_STRIKER =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(ReworkedShieldsMod.MOD_ID, "lightning_striker"));

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        register(registerable, LIGHTNING_STRIKER, Enchantment.builder(Enchantment.definition(
                        items.getOrThrow(ItemTags.WEAPON_ENCHANTABLE),
                        items.getOrThrow(ItemTags.SWORD_ENCHANTABLE),
                        5,
                        2,
                        Enchantment.leveledCost(5, 7),
                        Enchantment.leveledCost(25, 9),
                        2,
                        AttributeModifierSlot.MAINHAND))
                .exclusiveSet(enchantments.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE_SET))
                .addEffect(EnchantmentEffectComponentTypes.POST_ATTACK,
                        EnchantmentEffectTarget.ATTACKER, EnchantmentEffectTarget.VICTIM,
                        new LightningStrikerEnchantmentEffect()));
    }


    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }



    //public static final RegistryKey<Enchantment> QUICK_RECOVERY = of("quick_recovery");
    //public static final RegistryKey<Enchantment> WINDSWEPT = of("windswept");
    //public static final RegistryKey<Enchantment> BULWARK = of("bulwark");
    //public static final RegistryKey<Enchantment> HEARTBOUND = of("heartbound");
    //public static final RegistryKey<Enchantment> RETRIBUTION = of("retribution");

}