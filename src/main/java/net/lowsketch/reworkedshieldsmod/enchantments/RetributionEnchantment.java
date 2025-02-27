package net.lowsketch.reworkedshieldsmod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class RetributionEnchantment extends Enchantment{

    public RetributionEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentTarget.BREAKABLE,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    //                                   base      level   level mult
    public int getMinPower(int level) {
        return 8 + (level - 1) * 12;
    }

    public int getMaxPower(int level) {
        return super.getMinPower(level) + 25;
    }


    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem;
    }
    @Override
    public boolean isAvailableForRandomSelection() {
        return true;
    }
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != ModEnchantments.BULWARK;
    }
}
