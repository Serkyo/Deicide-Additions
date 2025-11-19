package com.serkyo.deicideadditions.events;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.ProgressionSystem;
import com.serkyo.deicideadditions.capability.ProgressionSystemProvider;
import com.serkyo.deicideadditions.core.DeicideEffects;
import com.serkyo.deicideadditions.utils.Boss;
import com.serkyo.deicideadditions.utils.Chapter;
import com.serkyo.deicideadditions.core.DeicideRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BossCombatEvents {
    @SubscribeEvent
    public static void onWorldTick(TickEvent.LevelTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            if (event.level.getGameTime() % 80 == 0) {
                if (event.level instanceof ServerLevel serverLevel) {
                    for (Entity entity : serverLevel.getAllEntities()) {
                        if (entity instanceof LivingEntity living && living.getType().is(Tags.EntityTypes.BOSSES)) {
                            applyBossEffectToNearbyPlayers(living);
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onBossTargetChange(LivingChangeTargetEvent event) {
        LivingEntity boss = event.getEntity();

        if (boss.getType().is(Tags.EntityTypes.BOSSES)) {
            LivingEntity newTarget = event.getNewTarget();
            CompoundTag bossPersistentData = boss.getPersistentData();
            UUID lastTarget =  bossPersistentData.hasUUID("LastTarget") ? bossPersistentData.getUUID("LastTarget") : null;

            if (newTarget instanceof Player) {
                if (!newTarget.getUUID().equals(lastTarget)) {
                    scaleBossMaxHealth(boss, lastTarget == null, bossPersistentData);
                    boss.getPersistentData().putUUID("LastTarget", newTarget.getUUID());
                }
            }
        }
    }

    private static void applyBossEffectToNearbyPlayers(LivingEntity boss) {
        ResourceLocation bossId = EntityType.getKey(boss.getType());
        Boss bossData = DeicideRegistry.getBoss(bossId);

        if (bossData != null) {
            List<Player> nearbyPlayers = getPlayersNearBoss(boss, bossData);

            for (Player player : nearbyPlayers) {
                applyEffectsToPlayer(player, bossId);
            }
        }
    }

    private static void applyEffectsToPlayer(Player player, ResourceLocation bossId) {
        player.getCapability(ProgressionSystemProvider.PROGRESSION_SYSTEM).ifPresent(progressionSystem -> {
            Chapter currentChapter = progressionSystem.getCurrentChapter();
            player.addEffect(new MobEffectInstance(DeicideEffects.WARPED_EQUILIBRIUM_EFFECT.get(), 200, 0, true, true));
            DeicideAdditions.LOGGER.debug("Applied Warped Equilibrium to {} since they are standing next to {}", player.getName().getString(), bossId);

            if (currentChapter != null) {
                boolean shouldApplyDespair = checkDespairCondition(progressionSystem, currentChapter, bossId);

                if (shouldApplyDespair) {
                    player.addEffect(new MobEffectInstance(DeicideEffects.DESPAIR_EFFECT.get(), 200, 0, true, true));
                    DeicideAdditions.LOGGER.debug("Applied Despair to {} since they are standing next to {}", player.getName().getString(), bossId);
                }
            }
        });
    }

    private static boolean checkDespairCondition(ProgressionSystem progressionSystem, Chapter currentChapter, ResourceLocation bossId) {
        boolean isCurrentChapterFinalBoss = currentChapter.getFinalBoss().getId().equals(bossId) || currentChapter.getFinalBoss().getSubparts().contains(bossId);

        if (isCurrentChapterFinalBoss) {
            boolean allIntermediaryDefeated = currentChapter.getIntermediaryBosses().stream()
                    .allMatch(progressionSystem::hasDefeatedBoss);

            return !allIntermediaryDefeated;
        }
        else {
            for (Chapter chapter : DeicideRegistry.CHAPTERS) {
                if (!chapter.getId().equals(currentChapter.getId()) && !progressionSystem.getCompletedChaptersId().contains(chapter.getId())) {
                    boolean bossInFutureChapter = chapter.getIntermediaryBosses().stream()
                            .anyMatch(b -> b.getId().equals(bossId) || b.getSubparts().contains(bossId)) ||
                            chapter.getFinalBoss().getId().equals(bossId) || chapter.getFinalBoss().getSubparts().contains(bossId);

                    if (bossInFutureChapter) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static List<Player> getPlayersNearBoss(LivingEntity boss, Boss bossData) {
        AABB boundingBox = boss.getBoundingBox().inflate(bossData.getCheckLength(), bossData.getCheckHeight(), bossData.getCheckLength());
        return boss.level().getEntitiesOfClass(Player.class, boundingBox);
    }

    private static void scaleBossMaxHealth(LivingEntity boss, boolean shouldHeal, CompoundTag bossPersistentData) {
        Boss bossData = DeicideRegistry.getBoss(EntityType.getKey(boss.getType()));

        if (bossData != null) {
            List<Player> nearbyPlayers = getPlayersNearBoss(boss, bossData);
            int numberOfNearbyPlayers = nearbyPlayers.size();

            AttributeInstance maxHealth = boss.getAttribute(Attributes.MAX_HEALTH);

            if (maxHealth != null) {
                double baseMaxHP = bossPersistentData.getDouble("UnscaledMaxHealth");

                if (baseMaxHP == 0.0) {
                    bossPersistentData.putDouble("UnscaledMaxHealth", maxHealth.getBaseValue());
                    baseMaxHP = maxHealth.getBaseValue();
                }

                maxHealth.setBaseValue(baseMaxHP * (1 + (numberOfNearbyPlayers - 1) * 0.25));

                if (shouldHeal) {
                    boss.setHealth(boss.getMaxHealth());
                }

                DeicideAdditions.LOGGER.debug("Modified attributes of boss {} based on the amount of player nearby ({})", bossData.getName(), numberOfNearbyPlayers);
            }
        }
    }
}
