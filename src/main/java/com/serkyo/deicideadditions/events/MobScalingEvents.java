package com.serkyo.deicideadditions.events;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.ProgressionSystemProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.projectile_damage.api.EntityAttributes_ProjectileDamage;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobScalingEvents {
    @SubscribeEvent
    public static void onMobSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (!entity.level().isClientSide && entity instanceof LivingEntity livingEntity && !(livingEntity instanceof Player)) {
            if (!livingEntity.getType().is(Tags.EntityTypes.BOSSES)) {
                if (livingEntity.getPersistentData().contains("Level")) {
                    applyAttributeBonuses(livingEntity);
                }
                else {
                    float entityDifficultyLevel = getNearbyPlayerDifficultyLevel(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
                    livingEntity.getPersistentData().putFloat("Level", entityDifficultyLevel);
                    applyAttributeBonuses(livingEntity);
                }
            }
        }
    }

    private static void applyAttributeBonuses(LivingEntity livingEntity) {
        float entityDifficultyLevel = livingEntity.getPersistentData().getFloat("Level");

        AttributeInstance maxHealth = livingEntity.getAttribute(Attributes.MAX_HEALTH);
        if (maxHealth != null) {
            maxHealth.setBaseValue(maxHealth.getBaseValue() * (1 + entityDifficultyLevel * 0.1));
            livingEntity.setHealth(livingEntity.getMaxHealth());
        }
        AttributeInstance armor = livingEntity.getAttribute(Attributes.ARMOR);
        if (armor != null) {
            armor.setBaseValue(armor.getBaseValue() * (1 + entityDifficultyLevel * 0.1));
        }
        AttributeInstance attackDamage = livingEntity.getAttribute(Attributes.ATTACK_DAMAGE);
        if (attackDamage != null) {
            attackDamage.setBaseValue(attackDamage.getBaseValue() * (1 + entityDifficultyLevel * 0.1));
        }
        AttributeInstance projectileDamage = livingEntity.getAttribute(EntityAttributes_ProjectileDamage.GENERIC_PROJECTILE_DAMAGE);
        if (projectileDamage != null) {
            projectileDamage.setBaseValue(projectileDamage.getBaseValue() * (1 + entityDifficultyLevel * 0.1));
        }
        AttributeInstance speed = livingEntity.getAttribute(Attributes.MOVEMENT_SPEED);
        if (speed != null) {
            speed.setBaseValue(speed.getBaseValue() * (1 + entityDifficultyLevel * 0.001));
        }
        AttributeInstance flyingSpeed = livingEntity.getAttribute(Attributes.FLYING_SPEED);
        if (flyingSpeed != null) {
            flyingSpeed.setBaseValue(flyingSpeed.getBaseValue() * (1 + entityDifficultyLevel * 0.001));
        }
        AttributeInstance swimSpeed = livingEntity.getAttribute(ForgeMod.SWIM_SPEED.get());
        if (swimSpeed != null) {
            swimSpeed.setBaseValue(swimSpeed.getBaseValue() * (1 + entityDifficultyLevel * 0.001));
        }
    }

    private static float getNearbyPlayerDifficultyLevel(Level level, double xCoord, double yCoord, double zCoord) {
        List<Player> nearbyPlayers = level.getEntitiesOfClass(Player.class, new AABB(
                xCoord - 256, yCoord - 256, zCoord - 256,
                xCoord + 256, yCoord + 256, zCoord + 256
        ));

        if (nearbyPlayers.isEmpty()) {
            return 1;
        }

        List<Float> playerDifficultyLevels = new ArrayList<>();

        for (Player player : nearbyPlayers) {
            player.getCapability(ProgressionSystemProvider.PROGRESSION_SYSTEM).ifPresent(progressionSystem -> {
                playerDifficultyLevels.add(progressionSystem.getDifficultyLevelScaled(player.getX(), player.getY(), player.getZ(), level.dimension().location()));
            });
        }

        float playerDifficultyLevelsSum = 0;
        for (float difficultyLevel : playerDifficultyLevels) {
            playerDifficultyLevelsSum += difficultyLevel;
        }

        return playerDifficultyLevelsSum / playerDifficultyLevels.size();
    }
}
