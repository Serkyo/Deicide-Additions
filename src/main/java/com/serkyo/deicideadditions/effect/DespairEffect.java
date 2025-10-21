package com.serkyo.deicideadditions.effect;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
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

    @SubscribeEvent
    public static void onBossDamage(LivingDamageEvent event) {
        DamageSource source = event.getSource();
        LivingEntity entity = event.getEntity();

        if (entity.getType().is(Tags.EntityTypes.BOSSES) && source.getEntity() instanceof Player player) {
            if (player.hasEffect(DeicideEffects.DESPAIR_EFFECT.get())) {
                float defaultAmount = event.getAmount();
                float decreasedAmount = defaultAmount * 0.05F;
                event.setAmount(decreasedAmount);
                player.displayClientMessage(Component.translatable("event.deicideadditions.powerful_foe"), true);
                DeicideAdditions.LOGGER.debug("Reduced damage dealt by {} to {} from {} to {} since they are afflicted by Despair", player.getName().getString(), EntityType.getKey(entity.getType()), defaultAmount, decreasedAmount);
            }
        }
    }
}
