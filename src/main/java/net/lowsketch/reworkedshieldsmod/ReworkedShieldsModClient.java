package net.lowsketch.reworkedshieldsmod;

import item.ModItems;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry;

import net.lowsketch.reworkedshieldsmod.util.ModModelPredicates;
import net.minecraft.util.Identifier;


public class ReworkedShieldsModClient implements  ClientModInitializer{

    @Override
    public void onInitializeClient() {
        ModModelPredicates.registerModelPredicateProviders();
    }
}
