package net.lowsketch.reworkedshieldsmod.util;

import item.ModItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class ModModelPredicates implements ClientModInitializer {
    public static void registerModelPredicateProviders() {
        ModelPredicateProviderRegistry.register(ModItems.WOODEN_SHIELD, Identifier.ofVanilla("blocking"),
                (stack, world, livingEntity, seed) -> {if (livingEntity == null) {return 0.0F;}return livingEntity.isBlocking() ? 1f : 0f;});
        ModelPredicateProviderRegistry.register(ModItems.GOLD_SHIELD, Identifier.ofVanilla("blocking"),
                (stack, world, livingEntity, seed) -> {if (livingEntity == null) {return 0.0F;}return livingEntity.isBlocking() ? 1f : 0f;});
        ModelPredicateProviderRegistry.register(ModItems.DIAMOND_SHIELD, Identifier.ofVanilla("blocking"),
                (stack, world, livingEntity, seed) -> {if (livingEntity == null) {return 0.0F;}return livingEntity.isBlocking() ? 1f : 0f;});
        ModelPredicateProviderRegistry.register(ModItems.NETHERITE_SHIELD, Identifier.ofVanilla("blocking"),
                (stack, world, livingEntity, seed) -> {if (livingEntity == null) {return 0.0F;}return livingEntity.isBlocking() ? 1f : 0f;});
        //Don't judge its late and im tired
    }

    @Override
    public void onInitializeClient() {
        registerModelPredicateProviders();
    }
}