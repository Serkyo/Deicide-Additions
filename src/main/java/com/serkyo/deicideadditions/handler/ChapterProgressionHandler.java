package com.serkyo.deicideadditions.handler;

import com.github.sculkhorde.common.blockentity.SculkAncientNodeBlockEntity;
import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.ProgressionSystem;
import com.serkyo.deicideadditions.capability.ProgressionSystemProvider;
import com.serkyo.deicideadditions.core.DeicideRegistry;
import com.serkyo.deicideadditions.utils.Chapter;
import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import dev.ftb.mods.ftbteams.api.TeamManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ChapterProgressionHandler {
    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (!event.getEntity().level().isClientSide && entity.getType().is(Tags.EntityTypes.BOSSES)) {
            handleBossDeath(entity, event.getSource().getEntity());
        }
    }

    private static void handleBossDeath(LivingEntity boss, Entity killer) {
        ResourceLocation bossId = EntityType.getKey(boss.getType());

        handlePlayerBossProgression(boss, bossId, killer);
        handleSpecialBossCases(boss, killer);
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
            playerNearby.getCapability(ProgressionSystemProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
                if (teamMembers.contains(playerNearby.getUUID())) {
                    updateChapterProgressForBoss(chapterProgress, bossId);
                    logProgressionUpdate(playerNearby, bossId);
                }
            });
        }
    }

    private static void handleSoloBossDefeat(ResourceLocation bossId, ServerPlayer player) {
        player.getCapability(ProgressionSystemProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
            updateChapterProgressForBoss(chapterProgress, bossId);
            logProgressionUpdate(player, bossId);
        });
    }

    private static void updateChapterProgressForBoss(ProgressionSystem progressionSystem, ResourceLocation bossId) {
        Chapter currentChapter = progressionSystem.getCurrentChapter();
        if (currentChapter != null && isBossInChapter(currentChapter, bossId)) {
            progressionSystem.addDefeatedBoss(bossId);
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

    private static void handleSpecialBossCases(LivingEntity boss, Entity killer) {
        if (boss instanceof WitherBoss && killer instanceof Player) {
            List<SculkAncientNodeBlockEntity> nodes = DeicideRegistry.getRegisteredNodes();
            for (SculkAncientNodeBlockEntity node : nodes) {
                SculkAncientNodeBlockEntity.tryInitializeHorde(boss.level(), node.getBlockPos(), node.getBlockState(), node);
                DeicideAdditions.LOGGER.info("Triggered Sculk Horde after Wither has been slain");
            }
        }
    }

    private static void logProgressionUpdate(Player player, ResourceLocation bossId) {
        DeicideAdditions.LOGGER.info("Updating boss progression for " + player.getName().getString() + " who killed " + bossId);
    }
}
