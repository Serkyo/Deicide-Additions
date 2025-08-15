package com.serkyo.deicideadditions.mixin;

import com.serkyo.deicideadditions.effect.DeicideMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MobEffects.class)
public class MobEffectsMixin {
    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;addAttributeModifier(Lnet/minecraft/world/entity/ai/attributes/Attribute;Ljava/lang/String;DLnet/minecraft/world/entity/ai/attributes/AttributeModifier$Operation;)Lnet/minecraft/world/effect/MobEffect;", ordinal = 0), index = 2)
    private static double modifySpeed(double pAmount){
        return 0.15D;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;addAttributeModifier(Lnet/minecraft/world/entity/ai/attributes/Attribute;Ljava/lang/String;DLnet/minecraft/world/entity/ai/attributes/AttributeModifier$Operation;)Lnet/minecraft/world/effect/MobEffect;", ordinal = 1), index = 2)
    private static double modifySlowness(double pAmount){
        return -0.2D;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;addAttributeModifier(Lnet/minecraft/world/entity/ai/attributes/Attribute;Ljava/lang/String;DLnet/minecraft/world/entity/ai/attributes/AttributeModifier$Operation;)Lnet/minecraft/world/effect/MobEffect;", ordinal = 2), index = 2)
    private static double modifyHaste(double pAmount){
        return 0.15;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;addAttributeModifier(Lnet/minecraft/world/entity/ai/attributes/Attribute;Ljava/lang/String;DLnet/minecraft/world/entity/ai/attributes/AttributeModifier$Operation;)Lnet/minecraft/world/effect/MobEffect;", ordinal = 3), index = 2)
    private static double modifyMiningFatigue(double pAmount){
        return -0.2D;
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffects;register(ILjava/lang/String;Lnet/minecraft/world/effect/MobEffect;)Lnet/minecraft/world/effect/MobEffect;", ordinal = 4), index = 2)
    private static MobEffect modifyStrength(MobEffect pEffect){
        return new DeicideMobEffect(MobEffectCategory.BENEFICIAL, 16762624).addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", 0.15D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

    @ModifyArg(method = "<clinit>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffects;register(ILjava/lang/String;Lnet/minecraft/world/effect/MobEffect;)Lnet/minecraft/world/effect/MobEffect;", ordinal = 17), index = 2)
    private static MobEffect modifyWeakness(MobEffect pEffect){
        return new DeicideMobEffect(MobEffectCategory.HARMFUL, 4738376).addAttributeModifier(Attributes.ATTACK_DAMAGE, "648D7064-6A60-4F59-8ABE-C2C23A6DD7A9", -0.2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }
}
