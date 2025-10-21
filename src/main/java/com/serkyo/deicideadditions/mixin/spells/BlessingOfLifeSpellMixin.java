package com.serkyo.deicideadditions.mixin.spells;

import com.serkyo.deicideadditions.DeicideAdditions;
import io.redspace.ironsspellbooks.api.magic.MagicData;
import io.redspace.ironsspellbooks.api.spells.CastSource;
import io.redspace.ironsspellbooks.capabilities.magic.TargetEntityCastData;
import io.redspace.ironsspellbooks.spells.holy.BlessingOfLifeSpell;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sfiomn.legendarysurvivaloverhaul.registry.MobEffectRegistry;

@Mixin(BlessingOfLifeSpell.class)
public class BlessingOfLifeSpellMixin {
    @Inject(method = "onCast", at = @At(value = "TAIL"), remap = false)
    private void painkillerEffect(Level world, int spellLevel, LivingEntity entity, CastSource castSource, MagicData playerMagicData, CallbackInfo ci) {
        if (playerMagicData.getAdditionalCastData() instanceof TargetEntityCastData healTargetingData) {
            var targetEntity = healTargetingData.getTarget((ServerLevel) world);
            if (targetEntity instanceof Player player) {
                targetEntity.addEffect(new MobEffectInstance(MobEffectRegistry.PAINKILLER.get(), spellLevel * 20, spellLevel / 3));
                DeicideAdditions.LOGGER.debug("Added the Painkiller effect to {} because they were targeted by the spell Blessing of Life", player.getName().getString());
            }
        }
    }
}