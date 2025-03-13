package net.lowsketch.reworkedshieldsmod.mixin;

import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;

import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;
/*
@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {
    //@Shadow
    //protected abstract void addModel(ModelIdentifier modelId);

    @Inject(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/model/ModelLoader;addModel(Lnet/minecraft/client/util/ModelIdentifier;)V", ordinal = 3, shift = At.Shift.AFTER))
    public void addShieldModel(BlockColors blockColors, Profiler profiler, Map<Identifier, JsonUnbakedModel> jsonUnbakedModels, Map<Identifier, List<Object>> blockStates, CallbackInfo ci)
    {

        jsonUnbakedModels.put(Identifier.of(ReworkedShieldsMod.MOD_ID, "netherite_shield"), new JsonUnbakedModel(...));

        this.addModel(new ModelIdentifier(Identifier.of(ReworkedShieldsMod.MOD_ID, "netherite_shield_blocking"), "inventory"));

        this.addModel(new ModelIdentifier(Identifier.of(ReworkedShieldsMod.MOD_ID, "diamond_shield"), "inventory"));
        this.addModel(new ModelIdentifier(Identifier.of(ReworkedShieldsMod.MOD_ID, "diamond_shield_blocking"), "inventory"));

        this.addModel(new ModelIdentifier(Identifier.of(ReworkedShieldsMod.MOD_ID, "gold_shield"), "inventory"));
        this.addModel(new ModelIdentifier(Identifier.of(ReworkedShieldsMod.MOD_ID, "gold_shield_blocking"), "inventory"));

        this.addModel(new ModelIdentifier(Identifier.of(ReworkedShieldsMod.MOD_ID, "wooden_shield"), "inventory"));
        this.addModel(new ModelIdentifier(Identifier.of(ReworkedShieldsMod.MOD_ID, "wooden_shield_blocking"), "inventory"));
    }
}*/