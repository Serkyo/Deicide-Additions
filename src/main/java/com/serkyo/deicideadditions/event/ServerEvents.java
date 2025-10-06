package com.serkyo.deicideadditions.event;

import com.github.sculkhorde.core.ModSavedData;
import com.lion.graveyard.entities.LichEntity;
import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.progression.ChapterProgress;
import com.serkyo.deicideadditions.capability.progression.ChapterProgressProvider;
import com.serkyo.deicideadditions.core.DeicideEffects;
import com.serkyo.deicideadditions.core.DeicideSavedData;
import com.serkyo.deicideadditions.utils.Chapter;
import daripher.autoleveling.saveddata.GlobalLevelingData;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import dev.ftb.mods.ftbteams.api.TeamManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sfiomn.legendarysurvivaloverhaul.api.ModDamageTypes;

import java.util.*;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    @SubscribeEvent
    public static void onLivingAttacked(LivingAttackEvent event) {
        LivingEntity entity = event.getEntity();
        Entity entitySource = event.getSource().getEntity();

        if (!entity.level().isClientSide) {
            if (entitySource instanceof Player && entity instanceof Player) {
                TeamManager teamManager = FTBTeamsAPI.api().getManager();
                MinecraftServer server = teamManager.getServer();

                Optional<Team> team = teamManager.getTeamForPlayer((ServerPlayer) entitySource);

                if (team.isPresent()) {
                    String vanillaTeamId = "ftb_" + team.get().getId();
                    PlayerTeam vanillaTeam = server.getScoreboard().getPlayerTeam(vanillaTeamId);
                    if (vanillaTeam != null && teamManager.arePlayersInSameTeam(entity.getUUID(), entitySource.getUUID()) && !vanillaTeam.isAllowFriendlyFire()) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntity();

        if (!entity.level().isClientSide) {
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
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();

        if (!event.getEntity().level().isClientSide && entity.getType().is(Tags.EntityTypes.BOSSES)) {
            handleBossDeath(entity, event.getSource().getEntity());
        }
    }

    private static void handleBossDeath(LivingEntity boss, Entity killer) {
        ResourceLocation bossId = EntityType.getKey(boss.getType());

        handleGlobalBossProgression(bossId, boss.getServer());
        handlePlayerBossProgression(boss, bossId, killer);
        handleSpecialBossCases(boss);
    }

    private static void handleGlobalBossProgression(ResourceLocation bossId, MinecraftServer server) {
        DeicideSavedData deicideSavedData = DeicideSavedData.get(server);
        GlobalLevelingData globalLevelingData = GlobalLevelingData.get(server);

        if (!deicideSavedData.isBossDefeated(bossId)) {
            deicideSavedData.markBossDefeated(bossId);
            globalLevelingData.setLevel(globalLevelingData.getLevelBonus() + 5);
        }
    }

    private static void handlePlayerBossProgression(LivingEntity boss, ResourceLocation bossId, Entity killer) {
        if (killer instanceof ServerPlayer player) {
            FTBTeamsAPI.API ftbTeamsAPI = dev.ftb.mods.ftbteams.api.FTBTeamsAPI.api();
            TeamManager teamManager = ftbTeamsAPI.getManager();
            Optional<Team> optionalTeam = teamManager.getTeamForPlayer(player);

            if (optionalTeam.isPresent()) {
                handleTeamBossDefeat(boss, bossId, optionalTeam.get());
            } else {
                handleSoloBossDefeat(bossId, player);
            }
        }
    }

    private static void handleTeamBossDefeat(LivingEntity boss, ResourceLocation bossId, Team team) {
        Set<UUID> teamMembers = team.getMembers();
        AABB boundingBox = boss.getBoundingBox().inflate(64);
        List<Player> nearbyPlayers = boss.level().getEntitiesOfClass(Player.class, boundingBox);

        for (Player playerNearby : nearbyPlayers) {
            playerNearby.getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
                if (teamMembers.contains(playerNearby.getUUID())) {
                    updateChapterProgressForBoss(chapterProgress, bossId);
                }
            });
        }
    }

    private static void handleSoloBossDefeat(ResourceLocation bossId, ServerPlayer player) {
        player.getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
            updateChapterProgressForBoss(chapterProgress, bossId);
        });
    }

    private static void updateChapterProgressForBoss(ChapterProgress chapterProgress, ResourceLocation bossId) {
        Chapter currentChapter = chapterProgress.getCurrentChapter();
        if (currentChapter != null && isBossInChapter(currentChapter, bossId)) {
            chapterProgress.addDefeatedBoss(bossId);
        }
    }

    private static boolean isBossInChapter(Chapter chapter, ResourceLocation bossId) {
        boolean isIntermediaryBoss = chapter.getIntermediaryBosses().stream()
                .anyMatch(boss -> boss.getId().equals(bossId));
        boolean isFinalBoss = chapter.getFinalBoss() != null &&
                chapter.getFinalBoss().getId().equals(bossId);
        boolean isSecondaryFinalBoss = chapter.getSecondaryFinalBoss() != null &&
                chapter.getSecondaryFinalBoss().getId().equals(bossId);

        return isIntermediaryBoss || isFinalBoss || isSecondaryFinalBoss;
    }

    private static void handleSpecialBossCases(LivingEntity boss) {
        if (boss instanceof LichEntity) {
            // Needs refactoring to use the method SculkAncientNodeBlockEntity.tryInitializeHorde()
            ModSavedData.getSaveData().setHordeState(ModSavedData.HordeState.ACTIVE);
        }
    }}
