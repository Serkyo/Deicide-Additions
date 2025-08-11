package com.serkyo.deicideadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(FoodData.class)
public class FoodDataMixin {

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V", ordinal = 0))
    public float modifyPassiveHealing1(float defaultValue, @Local(argsOnly = true) Player player){
        return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 3));
    }

    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;heal(F)V", ordinal = 1))
    public float modifyPassiveHealing2(float defaultValue, @Local(argsOnly = true) Player player){
        return (float) Math.max(defaultValue, Math.ceil(defaultValue / 20 * player.getMaxHealth() / 3));
    }
}
