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
import sfiomn.legendarysurvivaloverhaul.config.Config;

import java.util.Map;

@Mixin(MobEffect.class)
public class MobEffectMixin {
    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V", ordinal = 0))
    public float modifyRegeneration(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
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
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
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
            Map<BodyPartEnum, Float> healthPercentages = Map.of(
                    BodyPartEnum.HEAD, (float) Config.Baked.headPartHealth,
                    BodyPartEnum.CHEST, (float) Config.Baked.chestPartHealth,
                    BodyPartEnum.LEFT_ARM, (float) Config.Baked.armsPartHealth,
                    BodyPartEnum.RIGHT_ARM, (float) Config.Baked.armsPartHealth,
                    BodyPartEnum.LEFT_LEG, (float) Config.Baked.legsPartHealth,
                    BodyPartEnum.RIGHT_LEG, (float) Config.Baked.legsPartHealth,
                    BodyPartEnum.LEFT_FOOT, (float) Config.Baked.feetPartHealth,
                    BodyPartEnum.RIGHT_FOOT, (float) Config.Baked.feetPartHealth
            );
            for(BodyPartEnum part : BodyPartEnum.values()) {
                float defaultValuePart = defaultValue / healthPercentages.get(part);
                float baseMaxHealth = 20 / healthPercentages.get(part);
                float newBodyAmount = (float) Math.max(defaultValuePart, Math.ceil(defaultValuePart / baseMaxHealth * BodyDamageUtil.getMaxHealth(player, part)));
                BodyDamageUtil.healBodyPart(player, part, newBodyAmount);
            }
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the healing received by {} from Instant Health to {} and healed their limbs", player.getName().getString(), newAmount);
            }
            else {
                DeicideAdditions.LOGGER.debug("Healed the libs of {} since they were affected by Instant Health", player.getName().getString());
            }
            return newAmount;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyInstantenousEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
    public float modifyInstantDamage2(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth()));
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
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth()));
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
            Map<BodyPartEnum, Float> healthPercentages = Map.of(
                    BodyPartEnum.HEAD, (float) Config.Baked.headPartHealth,
                    BodyPartEnum.CHEST, (float) Config.Baked.chestPartHealth,
                    BodyPartEnum.LEFT_ARM, (float) Config.Baked.armsPartHealth,
                    BodyPartEnum.RIGHT_ARM, (float) Config.Baked.armsPartHealth,
                    BodyPartEnum.LEFT_LEG, (float) Config.Baked.legsPartHealth,
                    BodyPartEnum.RIGHT_LEG, (float) Config.Baked.legsPartHealth,
                    BodyPartEnum.LEFT_FOOT, (float) Config.Baked.feetPartHealth,
                    BodyPartEnum.RIGHT_FOOT, (float) Config.Baked.feetPartHealth
            );
            for(BodyPartEnum part : BodyPartEnum.values()) {
                float defaultValuePart = defaultValue / healthPercentages.get(part);
                float baseMaxHealth = 20 / healthPercentages.get(part);
                float newBodyAmount = (float) Math.max(defaultValuePart, Math.ceil(defaultValuePart / baseMaxHealth * BodyDamageUtil.getMaxHealth(player, part)));
                BodyDamageUtil.healBodyPart(player, part, newBodyAmount);
            }
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the healing received by {} from Instant Health to {} and healed their limbs", player.getName().getString(), newAmount);
            }
            else {
                DeicideAdditions.LOGGER.debug("Healed the libs of {} since they were affected by Instant Health", player.getName().getString());
            }            return newAmount;
        }
        return defaultValue;
    }
}
