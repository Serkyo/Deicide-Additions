package com.serkyo.deicideadditions.handler;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.ProgressionSystemProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
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
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.CLIENT && event.phase == TickEvent.Phase.START) {
            Player player = event.player;
            player.getCapability(ProgressionSystemProvider.PROGRESSION_SYSTEM).ifPresent(progressionSystem -> {
                float difficulty = progressionSystem.getDifficultyLevelScaled(player.getX(), player.getY(), player.getZ(), player.level().dimension().location());
                System.out.println(difficulty);
            });
        }
    }
}
