package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.effect.SinOfGluttonyEffect;
import com.serkyo.deicideadditions.effect.WarpedEquilibriumEffect;
import com.serkyo.deicideadditions.effect.DespairEffect;
import com.serkyo.deicideadditions.effect.GraceEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DeicideEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, DeicideAdditions.MOD_ID);

    public static final RegistryObject<MobEffect> WARPED_EQUILIBRIUM_EFFECT = MOB_EFFECTS.register("warped_equilibrium",
            () -> new WarpedEquilibriumEffect(MobEffectCategory.HARMFUL, 0));
    public static final RegistryObject<MobEffect> DESPAIR_EFFECT = MOB_EFFECTS.register("despair",
            () -> new DespairEffect(MobEffectCategory.HARMFUL, 2894932));
    public static final RegistryObject<MobEffect> GRACE_EFFECT = MOB_EFFECTS.register("grace",
            () -> new GraceEffect(MobEffectCategory.BENEFICIAL, 16773257));
    public static final RegistryObject<MobEffect> SIN_OF_GLUTTONY_EFFECT = MOB_EFFECTS.register("sin_of_gluttony",
            () -> new SinOfGluttonyEffect(MobEffectCategory.HARMFUL, 0));

    public static void register(IEventBus eventBus) { MOB_EFFECTS.register(eventBus); }
}
