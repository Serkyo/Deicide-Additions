package com.serkyo.deicideadditions.handler;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.ProgressionSystemProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.projectile_damage.api.EntityAttributes_ProjectileDamage;
import sfiomn.legendarysurvivaloverhaul.api.ModDamageTypes;

import java.util.*;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntity();

        if (!entity.level().isClientSide) {
            if (entity instanceof Player player) {
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
                    float defaultAmount = event.getAmount();
                    float newAmount = (float) Math.max(defaultAmount, Math.ceil(defaultAmount / 20 * player.getMaxHealth() / 3));
                    if (newAmount != defaultAmount) {
                        event.setAmount(newAmount);
                        DeicideAdditions.LOGGER.debug("Increased the damage received by {} from {} to {}", player.getName().getString(), source.type().msgId(), newAmount);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onMobSpawn(EntityJoinLevelEvent event) {
        Entity entity = event.getEntity();
        if (!entity.level().isClientSide && entity instanceof LivingEntity livingEntity && (livingEntity instanceof Player)) {
            if (!livingEntity.getType().is(Tags.EntityTypes.BOSSES)) {
                float entityDifficultyLevel = getNearbyPlayerDifficultyLevel(livingEntity.level(), livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());

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

                DeicideAdditions.LOGGER.debug("Modified attributes of {} based on player level {}", EntityType.getKey(livingEntity.getType()), entityDifficultyLevel);
            }
        }
    }

    private static float getNearbyPlayerDifficultyLevel(Level level, double xCoord, double yCoord, double zCoord) {
        List<Player> nearbyPlayers = level.getEntitiesOfClass(Player.class, new AABB(
                xCoord - 128, yCoord - 128, zCoord - 128,
                xCoord + 128, yCoord + 128, zCoord + 128
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
