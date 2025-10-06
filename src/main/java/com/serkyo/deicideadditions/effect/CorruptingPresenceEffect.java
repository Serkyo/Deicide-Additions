package com.serkyo.deicideadditions.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class CorruptingPresenceEffect extends DeicideMobEffect {
    public CorruptingPresenceEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (entity instanceof Player) {
            if (entity.hasEffect(ModEffects.COMFORT.get())) {
                entity.removeEffect(ModEffects.COMFORT.get());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
