package com.serkyo.deicideadditions.mixin;

import com.serkyo.deicideadditions.DeicideAdditions;
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
        if (!entity.hasEffect(MobEffects.REGENERATION)) {
            if (entity.getHealth() < entity.getMaxHealth()) {
                if (entity instanceof Player player) {
                    float defaultAmount = 1.0F;
                    float newAmount = (float) Math.max(defaultAmount, Math.ceil(0.05 * player.getMaxHealth() / 2));
                    player.heal(newAmount);
                    if (defaultAmount != newAmount) {
                        DeicideAdditions.LOGGER.debug("Increased the healing received by {} from Comfort to {}", player.getName().getString(), newAmount);
                    }
                } else {
                    entity.heal(1.0F);
                }
            }
        }
    }
}
