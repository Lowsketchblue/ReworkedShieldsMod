
package net.lowsketch.reworkedshieldsmod.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import item.ModItems;
import net.fabricmc.loader.api.FabricLoader;
import net.lowsketch.reworkedshieldsmod.ReworkedShieldsMod;

import net.lowsketch.reworkedshieldsmod.config.ConfigManager;
//import net.lowsketch.reworkedshieldsmod.util.EnchantsManager;
import net.lowsketch.reworkedshieldsmod.util.ModTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.DamageTypeTags;

import net.minecraft.sound.SoundCategory;

import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ShieldOverhaulMixin {

    private int tickCountdown = -1;
    private float shieldDurabilityDamage = 0;
    private int parryTicks = 0;
    private int preventSpamTicks = -1;
    private boolean loweredShield = true;

    @ModifyVariable(method = "damage", at = @At("LOAD"), ordinal = 1)
    private float modifyDamage(float amount, DamageSource source) {
        this.shieldDurabilityDamage = amount;

        if ((Object) this instanceof PlayerEntity player) { //If the current object is a player
            if (player.isUsingItem() && verifyShieldType(player)) { //and is using an item, and that item a shield

                if(!player.blockedByShield(source)){ //and if by minecraft standards it has blocked damage
                    ReworkedShieldsMod.LOGGER.info("Player tried to block damage but couldn't!");
                    return amount;
                }
                Entity attacker = source.getAttacker();
                if (attacker instanceof LivingEntity livingEntity) {
                    ItemStack heldItem = livingEntity.getMainHandStack();

                    if (heldItem.getItem() instanceof AxeItem) {
                        this.tickCountdown = 3;
                        ReworkedShieldsMod.LOGGER.info("Shield disabled by axe!");
                    }
                }

                Entity entity = source.getSource();
                if(!source.isIn(DamageTypeTags.IS_PROJECTILE))
                {
                    if(source.isIn(DamageTypeTags.IS_EXPLOSION)){return 0;}

                    if (entity instanceof LivingEntity) {
                        LivingEntity livingEntity = (LivingEntity)entity;
                        livingEntity.takeKnockback((double)0.5F,  player.getX()- livingEntity.getX(), player.getZ() - livingEntity.getZ());
                        if(parryTicks >= 0){
                            //EnchantsManager.ParryMeele(player);
                            //EnchantsManager.thorns(player, source);
                        }
                    }
                }else{
                    ReworkedShieldsMod.LOGGER.info(String.valueOf(parryTicks));
                    if(parryTicks >= 0){

                        ProjectileEntity projectile = (ProjectileEntity) entity;
                        Entity shooter = projectile.getOwner();
                        if(shooter != null && projectile instanceof ArrowEntity arrow){
                            arrow.setDamage(arrow.getDamage() * 1.7);

                            ReworkedShieldsMod.LOGGER.info(String.valueOf(arrow.getDamage()));
                            arrow.setOwner(player);
                            arrow.setNoClip(false);
                            arrow.age = -40;

                            Vec3d shooterPos = shooter.getPos();
                            Vec3d projectilePos = projectile.getPos();

                            Vec3d direction = projectilePos.subtract(shooterPos.getX(), (shooter.getY()+1), shooterPos.getZ()).normalize();

                            projectile.setVelocity(direction.x, direction.y, direction.z, 40F, 0.0F);
                            //projectile.setVelocity(projectile.getX()- shooter.getX(), projectile.getY()- (shooter.getY()+1), projectile.getZ()- shooter.getZ(), 40, 0);

                            if(arrow.distanceTo(shooter) > 4.8f){
                                player.getEntityWorld().playSound(null, player.getBlockPos(), SoundEvents.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 0.25f, 1f);
                                //EnchantsManager.ParryProjectile(player);
                            }
                        }
                    }
                }
                player.timeUntilRegen = 8;
                return 0;
            }
        }
        return amount;
    }

    @ModifyReturnValue(method = "blockedByShield", at = @At("RETURN"))
    private boolean blocked(boolean original, DamageSource source) { //Replaces the original block detection function.
        if ((Object) this instanceof PlayerEntity player) {
            Entity entity = source.getSource();
            boolean bl = false;
            if (entity instanceof PersistentProjectileEntity persistentProjectileEntity) {
                if (persistentProjectileEntity.getPierceLevel() > 0) {
                    bl = true;
                }
            }

            if (player.isBlocking() && !bl) {
                Vec3d vec3d = source.getPosition();
                if (vec3d != null) {
                    Vec3d vec3d2 = player.getRotationVec(1.0F);
                    Vec3d vec3d3 = vec3d.relativize(player.getPos()).normalize();
                    vec3d3 = new Vec3d(vec3d3.x, (double) 0.0F, vec3d3.z);
                    if (vec3d3.dotProduct(vec3d2) < (double) 0.0F) {
                        if (source.getAttacker() instanceof LivingEntity attacker && attacker.getMainHandStack().getItem() instanceof AxeItem) {
                            lowerShield(true);
                        }
                        this.tickCountdown = 3;
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    //Allows for compatibility with Combatify
    @Inject(method = "damage", at = @At("RETURN"))
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        //if(!FabricLoader.getInstance().isModLoaded("combatify")){return;    }

        if ((Object) this instanceof PlayerEntity player) {
            if(player.isUsingItem() && verifyShieldType(player)) {
                this.tickCountdown = 3;
                ReworkedShieldsMod.LOGGER.info("Combatify version used!");
            }
        }
    }

    //This function makes it so it doesn't immediately lowers the shield to prevent ghost hits
    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo info) {
        if (this.tickCountdown > 0) {
            tickCountdown--;
            if (tickCountdown == 0) {
                lowerShield(false);
            }
        }
        parryTicks--;
        preventSpamTicks--;
        if ((Object) this instanceof PlayerEntity player) {
            if(player.isUsingItem() && verifyShieldType(player)) { //All of this mumbo jumbo to prevent shield spamming.
                if(preventSpamTicks < 0 && loweredShield){
                    parryTicks = ConfigManager.getIntConfig("parry_ticks", 6);
                }
                loweredShield = false;
            }else{
                if (!loweredShield){
                    if(ConfigManager.getBooleanConfig("prevent_shield_spamming", true)){
                        preventSpamTicks = 11;
                    }
                    loweredShield = true;
                }

            }
        }
    }
    private void lowerShield(boolean byAxe){
        if ((Object) this instanceof PlayerEntity player) {

            if (player.isUsingItem() && verifyShieldType(player)) {

                //float qR = 1.0f - (EnchantsManager.getLevel(player) * 0.11f); //11%

                float qR = 1.0f;
                float aC = byAxe ? 2 : 1;

                int w= ConfigManager.getIntConfig("wooden_shield_cooldown", 56);
                int i= ConfigManager.getIntConfig("iron_shield_cooldown", 44);
                int g= ConfigManager.getIntConfig("gold_shield_cooldown", 24);
                int d= ConfigManager.getIntConfig("diamond_shield_cooldown", 36);
                int n= ConfigManager.getIntConfig("netherite_shield_cooldown", 28);

                player.getItemCooldownManager().set(ModItems.NETHERITE_SHIELD, (int) (n * qR * aC)); // 1.4 seconds
                player.getItemCooldownManager().set(ModItems.DIAMOND_SHIELD, (int) (d * qR * aC)); // 1.8 seconds
                player.getItemCooldownManager().set(ModItems.GOLD_SHIELD, (int) (g * qR * aC)); // 1.2 seconds
                player.getItemCooldownManager().set(Items.SHIELD, (int) (i * qR * aC)); // 2.2 seconds
                player.getItemCooldownManager().set(ModItems.WOODEN_SHIELD, (int) (w * qR * aC)); // 3 seconds

                damageShield(player);

                player.clearActiveItem();
                //player.getWorld().sendEntityStatus(this, (byte)30);

                ReworkedShieldsMod.LOGGER.info("Player has blocked damage!");

            }

        }
    }

    private void damageShield(PlayerEntity player){
        ItemStack activeShield = player.getActiveItem();
        if(activeShield.isOf(Items.SHIELD)){return;}

        Hand activeHand = player.getActiveHand();
        EquipmentSlot slot = (activeHand == Hand.MAIN_HAND) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

        if(shieldDurabilityDamage >= 2.0F){
            int i = 1 + MathHelper.floor(shieldDurabilityDamage);
            activeShield.damage(i, player, slot);
        }
    }

    private boolean verifyShieldType(PlayerEntity player){
        //return player.getActiveItem().isIn(ModTags.Items.IS_SHIELD_ITEM);
        return player.getActiveItem().isOf(ModItems.WOODEN_SHIELD) ||
                player.getActiveItem().isOf(Items.SHIELD) ||
                player.getActiveItem().isOf(ModItems.GOLD_SHIELD) ||
                player.getActiveItem().isOf(ModItems.DIAMOND_SHIELD) ||
                player.getActiveItem().isOf(ModItems.NETHERITE_SHIELD);
    }

    //Credit to Knoqx Quplet  on github for the NoShieldDelay mod.
    @ModifyConstant(method = "isBlocking", constant = @Constant(intValue = 5))
    private int setShieldUseDelay(int constant) {
        return 0;
    }
}