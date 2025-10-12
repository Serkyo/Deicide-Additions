package com.serkyo.deicideadditions.handler;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.progression.ChapterProgress;
import com.serkyo.deicideadditions.capability.progression.ChapterProgressProvider;
import com.serkyo.deicideadditions.core.DeicideEffects;
import com.serkyo.deicideadditions.utils.Boss;
import com.serkyo.deicideadditions.utils.Chapter;
import com.serkyo.deicideadditions.core.DeicideRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BossEffectEventHandler {
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

    private static void applyBossEffectToNearbyPlayers(LivingEntity boss) {
        ResourceLocation bossId = EntityType.getKey(boss.getType());
        Boss bossObject = DeicideRegistry.getBoss(bossId);
        if (bossObject != null) {
            AABB boundingBox = boss.getBoundingBox().inflate(bossObject.getCheckLength(), bossObject.getCheckHeight(), bossObject.getCheckLength());
            List<Player> nearbyPlayers = boss.level().getEntitiesOfClass(Player.class, boundingBox);

            for (Player player : nearbyPlayers) {
                applyEffectsToPlayer(player, bossId);
            }
        }
    }

    private static void applyEffectsToPlayer(Player player, ResourceLocation bossId) {
        player.getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
            Chapter currentChapter = chapterProgress.getCurrentChapter();
            player.addEffect(new MobEffectInstance(DeicideEffects.CORRUPTING_PRESENCE_EFFECT.get(), 200, 0, true, true));

            if (currentChapter != null) {
                boolean shouldApplyDespair = checkDespairCondition(chapterProgress, currentChapter, bossId);

                if (shouldApplyDespair) {
                    player.addEffect(new MobEffectInstance(DeicideEffects.DESPAIR_EFFECT.get(), 200, 0, true, true));
                }
            }
        });
    }

    private static boolean checkDespairCondition(ChapterProgress chapterProgress, Chapter currentChapter, ResourceLocation bossId) {
        boolean isCurrentChapterFinalBoss = currentChapter.getFinalBoss().getId().equals(bossId) ||
                (currentChapter.getSecondaryFinalBoss() != null && currentChapter.getSecondaryFinalBoss().getId().equals(bossId));

        if (isCurrentChapterFinalBoss) {
            boolean allIntermediaryDefeated = currentChapter.getIntermediaryBosses().stream()
                    .allMatch(chapterProgress::hasDefeatedBoss);

            return !allIntermediaryDefeated;
        }
        else {
            for (Chapter chapter : DeicideRegistry.CHAPTERS) {
                if (!chapter.getId().equals(currentChapter.getId()) && !chapterProgress.getCompletedChaptersId().contains(chapter.getId())) {
                    boolean bossInFutureChapter = chapter.getIntermediaryBosses().stream().anyMatch(b -> b.getId().equals(bossId)) ||
                            chapter.getFinalBoss().getId().equals(bossId) ||
                            (chapter.getSecondaryFinalBoss() != null && chapter.getSecondaryFinalBoss().getId().equals(bossId));
                    if (bossInFutureChapter) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
