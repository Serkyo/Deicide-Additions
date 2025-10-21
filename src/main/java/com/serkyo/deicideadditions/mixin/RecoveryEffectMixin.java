package com.serkyo.deicideadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.serkyo.deicideadditions.DeicideAdditions;
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
        if (entity instanceof Player player){
            float newAmount = (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 3));
            if (defaultValue != newAmount) {
                DeicideAdditions.LOGGER.debug("Increased the healing received by {} from Recovery to {}", player.getName().getString(), newAmount);
            }
            return newAmount;
        }
        return defaultValue;
    }
}
