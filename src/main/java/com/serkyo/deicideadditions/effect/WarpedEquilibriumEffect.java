package com.serkyo.deicideadditions.effect;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.ArrayList;
import java.util.List;

public class WarpedEquilibriumEffect extends DeicideMobEffect {
    public WarpedEquilibriumEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (entity instanceof ServerPlayer player) {
            if (player.hasEffect(ModEffects.COMFORT.get())) {
                player.removeEffect(ModEffects.COMFORT.get());
                DeicideAdditions.LOGGER.debug("Removed the comfort effect from {} since they have Warped Equilibrium", player.getName().getString());
            }

            if (player.tickCount % 400 == 0 && Math.random() < 0.5) {
                double reversalChance = player.getLuck() * 0.02;
                boolean isReversed = Math.random() < reversalChance;

                List<MobEffectInstance> effects = new ArrayList<>(player.getActiveEffects());
                int buffsChanged = 0;
                int debuffsChanged = 0;

                for (MobEffectInstance effect : effects) {
                    if (effect.getEffect() != DeicideEffects.DESPAIR_EFFECT.get() && effect.getEffect() != DeicideEffects.WARPED_EQUILIBRIUM_EFFECT.get()) {
                        player.removeEffect(effect.getEffect());
                        boolean isHarmful = effect.getEffect().getCategory() == MobEffectCategory.HARMFUL;
                        int durationChange;

                        if (isReversed) {
                            durationChange = isHarmful ? -200 : 200;
                        } else {
                            durationChange = isHarmful ? 200 : -200;
                        }
                        int newDuration = effect.getDuration() + durationChange;
                        if (newDuration > 0) {
                            player.addEffect(new MobEffectInstance(effect.getEffect(), newDuration, effect.getAmplifier(), effect.isAmbient(), effect.isVisible(), effect.showIcon()));
                            if (isHarmful) {
                                debuffsChanged++;
                            }
                            else {
                                buffsChanged++;
                            }
                        }
                    }
                }
                player.sendSystemMessage(Component.translatable("effect.deicideadditions.warped_equilibrium.effect_changed", buffsChanged, debuffsChanged));
                DeicideAdditions.LOGGER.debug("Changed the duration of debuffs and buffs on {}", player.getName().getString());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}
