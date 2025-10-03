package com.serkyo.deicideadditions.effect;

import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import vectorwing.farmersdelight.common.registry.ModEffects;

public class CorruptingPresenceEffect extends DeicideMobEffect {
    public CorruptingPresenceEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.hasEffect(ModEffects.COMFORT.get())) {
            pLivingEntity.removeEffect(ModEffects.COMFORT.get());
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
