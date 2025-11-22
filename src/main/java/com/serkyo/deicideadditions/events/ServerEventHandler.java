package com.serkyo.deicideadditions.events;

import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
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
}
