package com.serkyo.deicideadditions.event;

import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
    @SubscribeEvent
    public static void onLivingDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntity();
        if (entity instanceof Player){
            if (source.is(DamageTypes.STARVE) || source.is(DamageTypes.IN_WALL) || source.is(DamageTypes.DROWN) || source.is(DamageTypes.ON_FIRE) || source.is(DamageTypes.FREEZE) || source.is(DamageTypes.HOT_FLOOR) || source.is(DamageTypes.LAVA)) {;
                event.setAmount((float) Math.max(event.getAmount(), Math.ceil(event.getAmount() / 20 * entity.getMaxHealth() / 3)));
            }
        }
    }
}
