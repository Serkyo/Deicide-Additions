package com.serkyo.deicideadditions.mixin.spells;

import com.llamalad7.mixinextras.sugar.Local;
import com.serkyo.deicideadditions.DeicideAdditions;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.spells.holy.HealSpell;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyDamageUtil;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyPartEnum;
import sfiomn.legendarysurvivaloverhaul.config.Config;

import java.util.Map;

@Mixin(HealSpell.class)
public class HealSpellMixin {
    @Inject(method = "onCast", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;heal(F)V"))
    private void bodyPartScaling(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData, CallbackInfo ci, @Local float healAmount) {
        if (entity instanceof Player player) {
            Map<BodyPartEnum, Float> healthPercentages = Map.of(
                    BodyPartEnum.HEAD, (float) Config.Baked.headPartHealth,
                    BodyPartEnum.CHEST, (float) Config.Baked.chestPartHealth,
                    BodyPartEnum.LEFT_ARM, (float) Config.Baked.armsPartHealth,
                    BodyPartEnum.RIGHT_ARM, (float) Config.Baked.armsPartHealth,
                    BodyPartEnum.LEFT_LEG, (float) Config.Baked.legsPartHealth,
                    BodyPartEnum.RIGHT_LEG, (float) Config.Baked.legsPartHealth,
                    BodyPartEnum.LEFT_FOOT, (float) Config.Baked.feetPartHealth,
                    BodyPartEnum.RIGHT_FOOT, (float) Config.Baked.feetPartHealth
            );
            for(BodyPartEnum part : BodyPartEnum.values()) {
                float newHealAmount = healAmount / 2 * healthPercentages.get(part);
                BodyDamageUtil.healBodyPart(player, part, newHealAmount);
                DeicideAdditions.LOGGER.debug("Healed {} of {} by {} from the Heal spell", part.name(), player.getName().getString(), newHealAmount);
            }
        }
    }
}
