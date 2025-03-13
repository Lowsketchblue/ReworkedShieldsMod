package net.lowsketch.reworkedshieldsmod.mixin;

import item.ModItems;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useRubyStaffModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {

        if (stack.isOf(ModItems.NETHERITE_SHIELD)) {
            ItemRendererAccessor accessor = (ItemRendererAccessor) this;

            boolean isBlocking = false;
            if (MinecraftClient.getInstance().player != null) {
                isBlocking = MinecraftClient.getInstance().player.isUsingItem() && MinecraftClient.getInstance().player.getActiveItem().isOf(ModItems.NETHERITE_SHIELD);
            }
            //if ((renderMode == ModelTransformationMode.FIRST_PERSON_LEFT_HAND || renderMode == ModelTransformationMode.FIRST_PERSON_RIGHT_HAND) && isBlocking) {
            //    return accessor.mccourse$getModels().getModelManager().getModel(new ModelIdentifier(ReworkedShieldsMod.MOD_ID, "netherite_shield_blocking", "inventory"));
            //}
            if(isBlocking){
                return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "netherite_shield_blocking"));
            }
            return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "netherite_shield"));
        }
        if (stack.isOf(ModItems.DIAMOND_SHIELD)) {
            ItemRendererAccessor accessor = (ItemRendererAccessor) this;

            boolean isBlocking = false;
            if (MinecraftClient.getInstance().player != null) {
                isBlocking = MinecraftClient.getInstance().player.isUsingItem() && MinecraftClient.getInstance().player.getActiveItem().isOf(ModItems.DIAMOND_SHIELD);
            }
            if(isBlocking){
                return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "diamond_shield_blocking"));
            }
            return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "diamond_shield"));
        }
        if (stack.isOf(ModItems.GOLD_SHIELD)) {
            ItemRendererAccessor accessor = (ItemRendererAccessor) this;

            boolean isBlocking = false;
            if (MinecraftClient.getInstance().player != null) {
                isBlocking = MinecraftClient.getInstance().player.isUsingItem() && MinecraftClient.getInstance().player.getActiveItem().isOf(ModItems.GOLD_SHIELD);
            }

            if(isBlocking){
                return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "gold_shield_blocking"));
            }
            return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "gold_shield"));
        }
        if (stack.isOf(ModItems.WOODEN_SHIELD)) {
            ItemRendererAccessor accessor = (ItemRendererAccessor) this;

            boolean isBlocking = false;
            if (MinecraftClient.getInstance().player != null) {
                isBlocking = MinecraftClient.getInstance().player.isUsingItem() && MinecraftClient.getInstance().player.getActiveItem().isOf(ModItems.WOODEN_SHIELD);
            }
            if(isBlocking){
                return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "wooden_shield_blocking"));
            }
            return accessor.mccourse$getModels().getModelManager().getModel(Identifier.of(ReworkedShieldsMod.MOD_ID, "wooden_shield"));
        }
        return value;
    }

}