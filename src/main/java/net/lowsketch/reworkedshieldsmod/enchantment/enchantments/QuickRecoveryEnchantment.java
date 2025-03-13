package net.lowsketch.reworkedshieldsmod.enchantment.enchantments;

//import net.lowsketch.reworkedshieldsmod.item.ModShieldItem;
//import net.minecraft.enchantment.EnchantmentTarget;

/*
public class QuickRecoveryEnchantment extends Enchantment {

    public QuickRecoveryEnchantment() {
        super(
                Enchantment.Rarity.RARE,
                EnchantmentTarget.BREAKABLE,
                new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    //                                  base      level   levelmult
    public int getMinPower(int level) {
        return 3 + (level - 1) * 5;
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
        return super.canAccept(other);
        //return super.canAccept(other) && other != ModEnchantments.ANOTHER_ENCHANTMENT && other != ModEnchantments.YET_ANOTHER_ENCHANTMENT;
    }
}
*/