package com.serkyo.deicideadditions.event;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.Effects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sfiomn.legendarysurvivaloverhaul.api.ModDamageTypes;

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
                    source.is(ModDamageTypes.HYPOTHERMIA)) {;
                event.setAmount((float) Math.max(event.getAmount(), Math.ceil(event.getAmount() / 20 * entity.getMaxHealth() / 3)));
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Player player = event.player;
            if (player.tickCount % 40 == 0) {
                boolean nearBoss = !player.level().getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(64), e -> e.getType().is(Tags.EntityTypes.BOSSES)).isEmpty();
                if (nearBoss) {
                    player.addEffect(new MobEffectInstance(Effects.CORRUPTING_PRESENCE_EFFECT.get(), 100, 0, true, true));
                }
            }
        }
    }
}
