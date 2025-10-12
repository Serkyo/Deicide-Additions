package com.serkyo.deicideadditions.effect;

import io.redspace.ironsspellbooks.api.registry.AttributeRegistry;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.TickEvent;
import net.projectile_damage.api.EntityAttributes_ProjectileDamage;

public class DespairEffect extends DeicideMobEffect {
    public DespairEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.addAttributeModifier(
            Attributes.ARMOR,
            "f3e035d6-ae0e-467b-9014-750dc14ee584",
            -0.95D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
            Attributes.ARMOR_TOUGHNESS,
            "e3f1ea42-525c-4770-8e96-1f27141647aa",
            -0.95D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
}
