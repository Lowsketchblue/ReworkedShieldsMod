package net.lowsketch.reworkedshieldsmod.enchantment.enchantments;

//import net.minecraft.enchantment.EnchantmentTarget;

/*
public class WindsweptEnchantment extends Enchantment{

    public WindsweptEnchantment() {
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

    //                                   base      level   level mult
    public int getMinPower(int level) {
        return 1 + (level - 1) * 4;
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
        return super.canAccept(other) && other != ModEnchantments.HEARTBOUND;
        //return super.canAccept(other) && other != ModEnchantments.ANOTHER_ENCHANTMENT && other != ModEnchantments.YET_ANOTHER_ENCHANTMENT;
    }
}
*/