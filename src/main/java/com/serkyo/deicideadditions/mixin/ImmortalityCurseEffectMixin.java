package com.serkyo.deicideadditions.mixin;

import com.redpxnda.respawnobelisks.registry.effect.ImmortalityCurseEffect;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ImmortalityCurseEffect.class)
public class ImmortalityCurseEffectMixin {
    @Inject(method = "<init>", at = @At("RETURN"))
    private void replaceModifier(CallbackInfo ci) {
        ImmortalityCurseEffect self = (ImmortalityCurseEffect)(Object)this;
        self.addAttributeModifier(
            Attributes.MAX_HEALTH,
            "69751bd7-0fe9-4794-b9d1-a6a71c4d9e0a",
            -0.15D,
            AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }
}
