package net.lowsketch.reworkedshieldsmod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;

public class HeartboundEnchantment extends Enchantment{

    public HeartboundEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentTarget.BREAKABLE,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    //                                        base
    public int getMinPower(int level) {
        return 15;
    }

    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
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
        return super.canAccept(other) && other != ModEnchantments.WINDSWEPT;
        //return super.canAccept(other) && other != ModEnchantments.ANOTHER_ENCHANTMENT && other != ModEnchantments.YET_ANOTHER_ENCHANTMENT;
    }
}
