package com.serkyo.deicideadditions.mixin.spells;

import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.spells.holy.GreaterHealSpell;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyDamageUtil;
import sfiomn.legendarysurvivaloverhaul.api.bodydamage.BodyPartEnum;

@Mixin(GreaterHealSpell.class)
public class GreaterHealSpellMixin {
    @Inject(method = "onCast", at = @At(value = "TAIL"), remap = false)
    private void bodyPartScaling(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData, CallbackInfo ci) {
        if (entity instanceof Player player) {
            for(BodyPartEnum part : BodyPartEnum.values()) {
                BodyDamageUtil.healBodyPart(player, part, BodyDamageUtil.getMaxHealth(player, part));
            }
        }
    }
}
