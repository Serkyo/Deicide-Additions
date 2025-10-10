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
        this.addAttributeModifier(
            Attributes.ATTACK_DAMAGE,
            "5925ab3c-0597-4d1d-be28-69a25f46817a",
            -0.95D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
            AttributeRegistry.SPELL_POWER.get(),
            "be0db7a1-2ff6-4011-8916-bcf5fd1a447d",
            -0.95D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
            EntityAttributes_ProjectileDamage.GENERIC_PROJECTILE_DAMAGE,
            "8745f9b1-ecb3-46e7-8597-e460755540cc",
            -0.95D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
}
