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
    public float modifyRegeneration(float defaultValue, @Local(argsOnly = true) LivingEntity entity){
        if (entity instanceof Player){
            float temp = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * entity.getMaxHealth() / 2));
            System.out.println("regenerated : " + temp);
            return temp;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 0))
    public float modifyPoison(float defaultValue, @Local(argsOnly = true) LivingEntity entity){
        if (entity instanceof Player){
            float temp = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * entity.getHealth()));
            System.out.println("dealt poison dmg : " + temp);
            return temp;
        }
        return defaultValue;
    }

    @ModifyArg(method = "applyEffectTick" ,at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z", ordinal = 1))
    public float modifyWither(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player){
            float temp = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * entity.getMaxHealth() / 2));
            System.out.println("dealt wither dmg : " + temp);
            return temp;
        }
        return defaultValue;
    }
}
