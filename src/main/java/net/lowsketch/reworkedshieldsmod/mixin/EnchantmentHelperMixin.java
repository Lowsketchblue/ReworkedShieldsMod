package net.lowsketch.reworkedshieldsmod.mixin;

import java.util.ArrayList;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;


@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

    // Fragile. cir.setReturnValue(VALUE) breaks mc with "Failed to handle packet" and enchants dont show
    // clicking or clicking off and back shows them and works
    /**
     * Get list of possible enchantments of an item at an enchantment table.
     */
    @Inject(method = "getPossibleEntries", at = @At("RETURN"))
    private static void enchantableShearsMixin_EnchantmentHelper_getPossibleEntries(
            int power, ItemStack stack, boolean treasureAllowed,
            CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir) {

        // list of enchantments, and level
        List<EnchantmentLevelEntry> list = cir.getReturnValue(); // MUTATED LATER
        ArrayList<Enchantment> newEnchantments = new ArrayList<>(2);

        if ((stack.getItem() instanceof ShieldItem)) {
           // newEnchantments.add(Enchantments.UNBREAKING);
            //newEnchantments.add(ModEnchantments.WINDSWEPT);
            //newEnchantments.add(ModEnchantments.BULWARK);
            //newEnchantments.add(ModEnchantments.HEARTBOUND);
            //newEnchantments.add(ModEnchantments.QUICK_RECOVERY);
            //newEnchantments.add(ModEnchantments.RETRIBUTION);
        }

        // copied and adapted from EnchantmentHelper.getPossibleEntries()
        block0: for (Enchantment enchantment : newEnchantments) {

            // limit max unbreaking level of shields to 2
            int max = enchantment.getMaxLevel();
            /*if ((stack.getItem() instanceof ShieldItem) && (enchantment == Enchantments.UNBREAKING)) {
                max = 2;
            }*/

            for (int i = max; i > enchantment.getMinLevel() - 1; --i) {
                //checks to see if the
                if (power < enchantment.getMinPower(i) || power > enchantment.getMaxPower(i))
                    continue;
                //list.add(new EnchantmentLevelEntry(enchantment, i));
                continue block0;
            }
        }
    }
}