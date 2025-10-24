package com.serkyo.deicideadditions.effect;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideEffects;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GraceEffect extends DeicideMobEffect {
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
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (entity instanceof Player player && !player.level().isClientSide) {
            if (player.swinging || player.isUsingItem()) {
                player.removeEffect(this);
                DeicideAdditions.LOGGER.debug("Removed Grace from {} since they interacted with something", player.getName().getString());
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onEffectApplied(MobEffectEvent.Added event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player && !player.level().isClientSide) {
            if (event.getEffectInstance().getEffect().equals(DeicideEffects.GRACE_EFFECT.get())) {
                List<Mob> mobsAround = player.level().getEntitiesOfClass(Mob.class, entity.getBoundingBox().inflate(64));
                for (Mob mob : mobsAround) {
                    if (mob.getTarget() == player) {
                        mob.setTarget(null);
                    }
                }
                DeicideAdditions.LOGGER.debug("Prevented all mobs around {} from targeting them since they were applied Grace", player.getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onLivingAttacked(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player && !player.level().isClientSide) {
            if (player.hasEffect(DeicideEffects.GRACE_EFFECT.get())) {
                event.setCanceled(true);
                DeicideAdditions.LOGGER.debug("Cancelled attack received by {} since they are afflicted by Grace", player.getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onLivingHealed(LivingHealEvent event) {
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player player && !player.level().isClientSide) {
            if (player.hasEffect(DeicideEffects.GRACE_EFFECT.get())) {
                event.setCanceled(true);
                DeicideAdditions.LOGGER.debug("Cancelled healing received by {} since they are afflicted by Grace", player.getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onLivingChangeTarget(LivingChangeTargetEvent event) {
        LivingEntity targetedEntity = event.getNewTarget();

        if (targetedEntity instanceof Player player && !player.level().isClientSide) {
            if (player.hasEffect(DeicideEffects.GRACE_EFFECT.get())) {
                event.setCanceled(true);
                DeicideAdditions.LOGGER.debug("Prevented a mob from targeting {} since they are afflicted by Grace", player.getName().getString());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (!player.level().isClientSide && !event.isEndConquered()) {
            player.addEffect(new MobEffectInstance(DeicideEffects.GRACE_EFFECT.get(), 1200, 0, false, true, true));
            DeicideAdditions.LOGGER.debug("Added Grace effect to {} since they just respawned", player.getName().getString());
        }
    }
}
