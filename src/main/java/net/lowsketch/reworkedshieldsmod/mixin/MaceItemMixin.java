package net.lowsketch.reworkedshieldsmod.mixin;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.attribute.EntityAttributeModifier.Operation;
import net.minecraft.util.Identifier;

import static net.minecraft.item.Item.BASE_ATTACK_DAMAGE_MODIFIER_ID;
import static net.minecraft.item.Item.BASE_ATTACK_SPEED_MODIFIER_ID;


@Mixin(MaceItem.class)
public class MaceItemMixin {
    @Inject(method = "createAttributeModifiers", at = @At("RETURN"), cancellable = true)
    private static void modifyAttributes(CallbackInfoReturnable<AttributeModifiersComponent> cir) {
        // Construye una nueva versión con tus atributos personalizados
        AttributeModifiersComponent.Builder builder = AttributeModifiersComponent.builder();

        builder.add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 10.0, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND);

        builder.add(EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.9, EntityAttributeModifier.Operation.ADD_VALUE),
                AttributeModifierSlot.MAINHAND);

        // Retorna tu versión modificada
        cir.setReturnValue(builder.build());
    }
    @Inject(method = "postHit", at = @At("HEAD"))
    private void onMaceHit(ItemStack stack, LivingEntity target, LivingEntity attacker, CallbackInfoReturnable<Boolean> cir) {
        // Verifica que esté en el lado del servidor y que el atacante sea un jugador
        if (!attacker.getWorld().isClient() && attacker instanceof PlayerEntity player) {

            player.getWorld().playSound(null, player.getBlockPos(),

                    ReworkedShieldsMod.MACE_HIT_SOUND_EVENT,
                    player.getSoundCategory(),
                    1.0F,
                    (0.9F  + player.getWorld().random.nextFloat() * 0.2F)
            );
        }
    }
}
