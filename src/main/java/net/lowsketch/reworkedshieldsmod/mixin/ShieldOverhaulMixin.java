package net.lowsketch.reworkedshieldsmod.mixin;

import item.ModItems;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;
import net.lowsketch.reworkedshieldsmod.item.ModShieldItem;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ShieldOverhaulMixin {

    private int tickCountdown = -1;

    //Registers the actual blocking of the shield
    @Inject(method = "damage", at = @At("RETURN"))
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        this.tickCountdown = 2;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        if (this.tickCountdown > 0) {
            tickCountdown--;
            if (tickCountdown == 0) {
                LowerShield();
            }
        }
    }

    private void LowerShield(){
        if ((Object) this instanceof PlayerEntity player) {
            //player.isBlocking() is for LivingEntity class mixin

            if (player.isUsingItem() && VerifyShield(player)) {
                player.getItemCooldownManager().set(Items.SHIELD, 100); // 20 ticks = 1 segundo
                player.getItemCooldownManager().set(ModItems.NETHERITE_SHIELD, 40); // 20 ticks = 1 segundo
                player.clearActiveItem();
                //player.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + player.getWorld().random.nextFloat() * 0.4F);
                ReworkedShieldsMod.LOGGER.info("Player has blocked damage!");
            }
        }
    }


    private boolean VerifyShield(PlayerEntity player){
        if(player.getActiveItem().isOf(Items.SHIELD)){
            ReworkedShieldsMod.LOGGER.info("Shield detected!");
            return true;
        } else if (player.getActiveItem().isOf(ModItems.NETHERITE_SHIELD)) {
            ReworkedShieldsMod.LOGGER.info("Custom shield detected!");
            return true;
        }else{
            ReworkedShieldsMod.LOGGER.info("Nothing detected.");
            return false;
        }
    }
}
