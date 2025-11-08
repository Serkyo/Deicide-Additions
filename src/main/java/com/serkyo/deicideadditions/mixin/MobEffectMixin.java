package com.serkyo.deicideadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyDamageUtil;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyPartEnum;

@Mixin(MobEffect.class)
public class MobEffectMixin {
    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V", ordinal = 0))
    public float modifyRegeneration(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 3));
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the healing received by {} from Regeneration to {}", player.getName().getString(), newAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
    public float modifyPoison(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getHealth() / 2));
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the damage received by {} from Poison to {}", player.getName().getString(), newAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 1))
    public float modifyWither(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player){
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 3));
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the damage received by {} from Wither to {}", player.getName().getString(), newAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 2))
    public float modifyInstantDamage1(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the damage received by {} from Instant Damage to {}", player.getName().getString(), newAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V", ordinal = 1))
    public float modifyInstantHealth1(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth()));
            BodyPartEnum lowestHp = null;
            for(BodyPartEnum part : BodyPartEnum.values()) {
                if (lowestHp != null) {
                    if (BodyDamageUtil.getHealthRatio(player, part) < BodyDamageUtil.getHealthRatio(player, lowestHp)) {
                        lowestHp = part;
                    }
                } else {
                    lowestHp = part;
                }
            }
            float bodyHealingAmount = (newAmount / BodyDamageUtil.getHealthRatio(player, lowestHp));
            BodyDamageUtil.healBodyPart(player, lowestHp, bodyHealingAmount);
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the healing received by {} from Instant Health to {} and healed their {} for {}", player.getName().getString(), newAmount, lowestHp.getClass().getName(), bodyHealingAmount);
            }
            else {
                DeicideAdditions.LOGGER.debug("Healed {} of {} by {} since they were affected by Instant Health", lowestHp.toString().toLowerCase(), player.getName().getString(), bodyHealingAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyInstantenousEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
    public float modifyInstantDamage2(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the damage received by {} from Instant Damage to {}", player.getName().getString(), newAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyInstantenousEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 1))
    public float modifyInstantDamage3(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the damage received by {} from Instant Damage to {}", player.getName().getString(), newAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyInstantenousEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
    public float modifyInstantHealth2(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth()));
            BodyPartEnum lowestHp = null;
            for(BodyPartEnum part : BodyPartEnum.values()) {
                if (lowestHp != null) {
                    if (BodyDamageUtil.getHealthRatio(player, part) < BodyDamageUtil.getHealthRatio(player, lowestHp)) {
                        lowestHp = part;
                    }
                } else {
                    lowestHp = part;
                }
            }
            float bodyHealingAmount = (newAmount / BodyDamageUtil.getHealthRatio(player, lowestHp));
            BodyDamageUtil.healBodyPart(player, lowestHp, bodyHealingAmount);
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the healing received by {} from Instant Health to {} and healed their {} for {}", player.getName().getString(), newAmount, lowestHp.getClass().getName(), bodyHealingAmount);
            }
            else {
                DeicideAdditions.LOGGER.debug("Healed {} of {} by {} since they were affected by Instant Health", lowestHp.toString().toLowerCase(), player.getName().getString(), bodyHealingAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }
}
