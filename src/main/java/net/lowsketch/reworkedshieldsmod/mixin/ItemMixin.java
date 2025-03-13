package net.lowsketch.reworkedshieldsmod.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ShieldItem;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// tested with shears Enchantability of 15,10,5,3,2,1
// 1 gives ~30% eff 4, unb 3; ~40% eff 3, unb 3; rest junk
// 0 is unenchantable
// I would prefer eff 4 + unb 3 to be impossible but I have a life to get back to
/*
@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "getEnchantability()I", at = @At("RETURN"))
    private int getEnchantability(CallbackInfoReturnable<Integer> cir) {

        if((Item) (Object) this instanceof ShieldItem)
        {
            return 1;
        }else {
            return cir.getReturnValue();
        }

    }
}*/