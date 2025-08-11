package com.serkyo.deicideadditions.mixin;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import vectorwing.farmersdelight.common.effect.ComfortEffect;

@Mixin(ComfortEffect.class)
public class ComfortEffectMixin {

    /**
     * @author Serkyo
     * @reason Natural healing disabled, so no need to check for saturation, and scaling on max health instead of a flat value
     */
    @Overwrite
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.hasEffect(MobEffects.REGENERATION)) {
            return;
        }
        if (entity.getHealth() < entity.getMaxHealth()) {
            if (entity instanceof Player) {
                entity.heal((float) Math.max(1.0, Math.ceil(0.05 * entity.getHealth() / 2)));
            } else {
                entity.heal(1.0F);
            }
        }
    }
}
