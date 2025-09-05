package com.serkyo.deicideadditions.mixin.spells;

import com.llamalad7.mixinextras.sugar.Local;
import io.redspace.ironsspellbooks.entity.spells.HealingAoe;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyDamageUtil;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyPartEnum;

@Mixin(HealingAoe.class)
public class HealingAoeMixin {
    @Inject(method = "applyEffect", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V"), remap = false)
    private void bodyPartScaling(LivingEntity target, CallbackInfo ci, @Local float healAmount) {
        if (target instanceof Player player) {
            BodyPartEnum lowestHp = null;
            for(BodyPartEnum part : BodyPartEnum.values()) {
                if (lowestHp != null) {
                    if (BodyDamageUtil.getHealthRatio(player, part) < BodyDamageUtil.getHealthRatio(player, lowestHp)) {
                        lowestHp = part;
                    }
                } else {
                    lowestHp = part;
                }
            }
            BodyDamageUtil.healBodyPart(player, lowestHp, healAmount / 2);
        }
    }
}
