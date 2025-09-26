package com.serkyo.deicideadditions.effect;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GraceEffect extends MobEffect {
    public GraceEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "7ba71a7c-d0a8-42de-92f2-ed95ca89f7f5",
                0.5D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
                ForgeMod.ENTITY_GRAVITY.get(),
                "dbe01bd2-0ae3-44ce-bbb8-0ca656993f39",
                -0.5D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (pLivingEntity.swinging || pLivingEntity.isUsingItem()) {
            pLivingEntity.removeEffect(this);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onEntityHurt(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(DeicideEffects.GRACE_EFFECT.get())) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onEntityHeal(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity.hasEffect(DeicideEffects.GRACE_EFFECT.get())) {
            event.setCanceled(true);
        }
    }
}
