package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.effect.CorruptingPresenceEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Effects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DeicideAdditions.MOD_ID);

    public static final RegistryObject<MobEffect> CORRUPTING_PRESENCE_EFFECT = MOB_EFFECTS.register("corrupting_presence",
            () -> new CorruptingPresenceEffect(MobEffectCategory.HARMFUL, 0));

    public static void register(IEventBus eventBus) { MOB_EFFECTS.register(eventBus); }
}
