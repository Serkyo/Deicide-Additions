package com.serkyo.deicideadditions.event;

import com.github.sculkhorde.core.ModSavedData;
import com.lion.graveyard.entities.LichEntity;
import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.progression.ChapterProgressProvider;
import com.serkyo.deicideadditions.core.DeicideEffects;
import com.serkyo.deicideadditions.core.DeicideSavedData;
import com.serkyo.deicideadditions.utils.Chapter;
import com.serkyo.deicideadditions.utils.ChapterRegistry;
import daripher.autoleveling.saveddata.GlobalLevelingData;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import dev.ftb.mods.ftbteams.api.TeamManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import sfiomn.legendarysurvivaloverhaul.api.ModDamageTypes;

import java.util.*;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntity();

        if (entity instanceof Player) {

            if (source.is(DamageTypes.STARVE) ||
                    source.is(DamageTypes.IN_WALL) ||
                    source.is(DamageTypes.DROWN) ||
                    source.is(DamageTypes.ON_FIRE) ||
                    source.is(DamageTypes.FREEZE) ||
                    source.is(DamageTypes.HOT_FLOOR) ||
                    source.is(DamageTypes.LAVA) ||
                    source.is(DamageTypes.IN_FIRE) ||
                    source.is(ModDamageTypes.DEHYDRATION) || 
                    source.is(ModDamageTypes.HYPERTHERMIA) ||
                    source.is(ModDamageTypes.HYPOTHERMIA)) {
                event.setAmount((float) Math.max(event.getAmount(), Math.ceil(event.getAmount() / 20 * entity.getMaxHealth() / 3)));
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (!event.getEntity().level().isClientSide) {
            if (entity.getType().is(Tags.EntityTypes.BOSSES)) {
                String bossId = entity.getType().toString();
                MinecraftServer server = entity.getServer();
                DeicideSavedData deicideSavedData = DeicideSavedData.get(server);
                GlobalLevelingData globalLevelingData = GlobalLevelingData.get(server);

                if (!deicideSavedData.isBossDefeated(bossId)) {
                    deicideSavedData.markBossDefeated(bossId);
                    globalLevelingData.setLevel(globalLevelingData.getLevelBonus() + 5);
                }

                Entity source = event.getSource().getEntity();

                if (source instanceof ServerPlayer player) {
                    FTBTeamsAPI.API FTBTeamsAPI = dev.ftb.mods.ftbteams.api.FTBTeamsAPI.api();
                    TeamManager FTBTeamsManager = FTBTeamsAPI.getManager();
                    Optional<Team> optionalTeam = FTBTeamsManager.getTeamForPlayer(player);

                    if (optionalTeam.isPresent()) {
                        Team team = optionalTeam.get();
                        Set<UUID> teamMembers = team.getMembers();
                        AABB boundingBox = entity.getBoundingBox().inflate(64);
                        List<Player> nearbyPlayers = entity.level().getEntitiesOfClass(Player.class, boundingBox);

                        for (Player playerNearby : nearbyPlayers) {
                            playerNearby.getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
                                if (teamMembers.contains(playerNearby.getUUID())) {
                                    Chapter currentChapter = chapterProgress.getCurrentChapter();
                                    if (currentChapter != null) {
                                        if (currentChapter.getIntermediaryBosses().contains(bossId) || currentChapter.getFinalBossId().equals(bossId)) {
                                            chapterProgress.addDefeatedBoss(bossId);
                                        }
                                    }
                                }
                            });
                        }
                    }
                    else {
                        player.getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
                            Chapter currentChapter = chapterProgress.getCurrentChapter();
                            if (currentChapter != null) {
                                if (currentChapter.getIntermediaryBosses().contains(bossId) || currentChapter.getFinalBossId().equals(bossId)) {
                                    chapterProgress.addDefeatedBoss(bossId);
                                }
                            }
                        });
                    }
                }

                if (entity instanceof LichEntity) {
                    ModSavedData.getSaveData().setHordeState(ModSavedData.HordeState.ACTIVE);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END && event.side == LogicalSide.SERVER) {
            Player player = event.player;

            if (player.tickCount % 80 == 0) {
                player.getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
                    Chapter currentChapter = chapterProgress.getCurrentChapter();
                    AABB boundingBox = player.getBoundingBox().inflate(64);
                    List<LivingEntity> nearbyBosses = player.level().getEntitiesOfClass(LivingEntity.class, boundingBox, e -> e.getType().is(Tags.EntityTypes.BOSSES));

                    if (!nearbyBosses.isEmpty()) {
                        player.addEffect(new MobEffectInstance(DeicideEffects.CORRUPTING_PRESENCE_EFFECT.get(), 200, 0, true, true));
                    }

                    if (currentChapter != null) {
                        for (LivingEntity boss : nearbyBosses) {
                            boolean shouldApplyDespair = false;

                            if (boss.getType().toString().equals(currentChapter.getFinalBossId())) {
                                boolean allIntermediaryDefeated = chapterProgress.getDefeatedBosses().containsAll(currentChapter.getIntermediaryBosses());
                                if (!allIntermediaryDefeated){
                                    shouldApplyDespair = true;
                                }
                            }
                            else {
                                for(Chapter chapter : ChapterRegistry.CHAPTERS) {
                                    if (!chapter.getId().equals(currentChapter.getId()) && !chapterProgress.getCompletedChaptersId().contains(chapter.getId())) {
                                        if (chapter.getIntermediaryBosses().contains(boss.getType().toString()) || chapter.getFinalBossId().equals(boss.getType().toString())) {
                                            shouldApplyDespair = true;
                                        }
                                    }
                                }
                            }

                            if (shouldApplyDespair) {
                                player.addEffect(new MobEffectInstance(DeicideEffects.DESPAIR_EFFECT.get(), 200, 0, true, true));
                            }
                        }
                    }
                });
            }
        }
    }
}
