package com.serkyo.deicideadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MobEffect.class)
public class MobEffectMixin {
    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V", ordinal = 0))
    public float modifyRegeneration(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player){
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 3));
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
    public float modifyPoison(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player){
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getHealth() / 2));
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 1))
    public float modifyWither(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player){
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 3));
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 2))
    public float modifyInstantDamage1(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V", ordinal = 1))
    public float modifyInstantHealth1(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth()));
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyInstantenousEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
    public float modifyInstantDamage2(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyInstantenousEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 1))
    public float modifyInstantDamage3(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 2));
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyInstantenousEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
    public float modifyInstantHealth2(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player player) {
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth()));
        }
        return defaultValue;
    }
}
