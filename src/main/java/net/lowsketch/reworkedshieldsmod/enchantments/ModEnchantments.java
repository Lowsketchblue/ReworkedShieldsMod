package net.lowsketch.reworkedshieldsmod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final Enchantment QUICK_RECOVERY = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("reworkedshieldsmod", "quick_recovery"),
            new QuickRecoveryEnchantment()
    );
    public static final Enchantment WINDSWEPT = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("reworkedshieldsmod", "windswept"),
            new WindsweptEnchantment()
    );
    public static final Enchantment BULWARK = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("reworkedshieldsmod", "bulwark"),
            new BulwarkEnchantment()
    );
    public static final Enchantment HEARTBOUND = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("reworkedshieldsmod", "heartbound"),
            new HeartboundEnchantment()
    );
    public static final Enchantment RETRIBUTION = Registry.register(
            Registries.ENCHANTMENT,
            new Identifier("reworkedshieldsmod", "retribution"),
            new RetributionEnchantment()
    );

    public static void registerModEnchantments() {
        System.out.println("Registering Mod Enchantments");
    }

}