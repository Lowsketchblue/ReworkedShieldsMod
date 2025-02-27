package net.lowsketch.reworkedshieldsmod.util;

import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.lowsketch.reworkedshieldsmod.enchantments.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

public class EnchantsManager {

    public static int getLevel(PlayerEntity player){

        if (player.getActiveItem().hasEnchantments()) {
            if (EnchantmentHelper.getLevel(ModEnchantments.QUICK_RECOVERY, player.getActiveItem()) > 0) {
                return EnchantmentHelper.getLevel(ModEnchantments.QUICK_RECOVERY, player.getActiveItem());
            }
        }
        return 0;
    }
    public static boolean isBulwark(PlayerEntity player){
        if (player.getActiveItem().hasEnchantments()) {
            return EnchantmentHelper.getLevel(ModEnchantments.BULWARK, player.getActiveItem()) > 0;
        }
        return false;
    }
    public static void thorns(PlayerEntity player, DamageSource source)
    {
        if (player.getActiveItem().hasEnchantments()) {
            if (EnchantmentHelper.getLevel(ModEnchantments.BULWARK, player.getActiveItem()) > 0) {
                Entity attacker = source.getAttacker();
                if (attacker instanceof LivingEntity livingAttacker) {
                    float reflectedDamage = EnchantmentHelper.getLevel(ModEnchantments.BULWARK, player.getActiveItem()) * 3;
                    if(reflectedDamage < 4){reflectedDamage = 4;}
                    DamageSources damageSources = player.getWorld().getDamageSources();
                    livingAttacker.damage(damageSources.playerAttack(player), reflectedDamage);

                }
            }
        }
    }

    public static void ParryMeele(PlayerEntity player)
    {

        if (player.getActiveItem().hasEnchantments()) {
            if (EnchantmentHelper.getLevel(ModEnchantments.BULWARK, player.getActiveItem()) > 0) {
                player.getEntityWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 0.25f, 1f);

                int level = EnchantmentHelper.getLevel(ModEnchantments.BULWARK, player.getActiveItem());
                int extendEffect = level * 15;

                player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10 + extendEffect, 1));

            } else if (EnchantmentHelper.getLevel(ModEnchantments.RETRIBUTION, player.getActiveItem()) > 0) {
                player.getEntityWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 0.25f, 1f);

                int level = EnchantmentHelper.getLevel(ModEnchantments.RETRIBUTION, player.getActiveItem());
                int extendEffect = level * 5;
                int amplifier = level > 2 ? 1 : 0;
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 18 + extendEffect, amplifier));
            }
        }
    }
    public static void ParryProjectile(PlayerEntity player)
    {
        if (player.getActiveItem().hasEnchantments()) {

            if (EnchantmentHelper.getLevel(ModEnchantments.WINDSWEPT, player.getActiveItem()) > 0) {

                int level = EnchantmentHelper.getLevel(ModEnchantments.WINDSWEPT, player.getActiveItem());
                int extendEffect = level * 5;
                int amplifier = level > 2 ? 2 : 1;
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 20 + extendEffect, amplifier));

            } else if (EnchantmentHelper.getLevel(ModEnchantments.HEARTBOUND, player.getActiveItem()) > 0) {

                float currentAbsorption = player.getAbsorptionAmount();
                player.setAbsorptionAmount(currentAbsorption + 2.0F);
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 30, 3));
            }
        }
    }
}
