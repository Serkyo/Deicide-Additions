package com.serkyo.deicideadditions.events;

import com.redpxnda.respawnobelisks.registry.ModRegistries;
import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sfiomn.legendarysurvivaloverhaul.api.ModDamageTypes;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEventHandler {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntity();

        if (entity instanceof ServerPlayer player) {
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

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            CompoundTag data = player.getPersistentData();
            if (!data.getBoolean("joinedBefore")) {
                data.putBoolean("joinedBefore", true);

                player.server.getCommands().performPrefixedCommand(
                    player.server.createCommandSourceStack().withPermission(4),
                    "epicfight skill add " + player.getName().getString() + " dodge wom:precise_roll"
                );

                player.server.getCommands().performPrefixedCommand(
                        player.server.createCommandSourceStack().withPermission(4),
                        "epicfight skill add " + player.getName().getString() + " guard epicfight:parrying"
                );
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            Player original = event.getOriginal();
            Player newPlayer = event.getEntity();

            if (original instanceof ServerPlayer serverPlayer) {
                System.out.println(serverPlayer.getRespawnPosition());
                BlockPos respawnPos = serverPlayer.getRespawnPosition();

                if (respawnPos == null) {
                    if (original.hasEffect(ModRegistries.immortalityCurse.get())) {
                        MobEffectInstance immortalityCurseEffect = original.getEffect(ModRegistries.immortalityCurse.get());
                        int amplifier = immortalityCurseEffect.getAmplifier();
                        System.out.println("Amplifier current : " + amplifier);

                        newPlayer.addEffect(new MobEffectInstance(ModRegistries.immortalityCurse.get(), 72000, amplifier < 4 ? amplifier + 1 : amplifier, false, true));
                    }
                    else {
                        newPlayer.addEffect(new MobEffectInstance(ModRegistries.immortalityCurse.get(), 72000, 0, false, true));
                    }
                }
            }
        }
    }
}
