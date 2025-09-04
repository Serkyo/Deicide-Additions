package com.serkyo.deicideadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sfiomn.legendarysurvivaloverhaul.common.effects.RecoveryEffect;

@Mixin(RecoveryEffect.class)
public class RecoveryEffectMixin {
    @ModifyArg(method = "applyEffectTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
    public float modifyRecovery(float defaultValue, @Local(argsOnly = true) LivingEntity entity) {
        if (entity instanceof Player){
            return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * entity.getMaxHealth() / 3));
        }
        return defaultValue;
    }
}
