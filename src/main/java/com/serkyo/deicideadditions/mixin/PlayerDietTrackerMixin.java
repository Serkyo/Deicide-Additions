package com.serkyo.deicideadditions.mixin;

import com.illusivesoulworks.diet.common.capability.PlayerDietTracker;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerDietTracker.class)
public class PlayerDietTrackerMixin {
    @Shadow(remap = false) private int prevFood;

    @Redirect(method = "consume(Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;getFoodLevel()I"))
    private int alwaysApplyDiet1(FoodData instance) {
        return prevFood + 1;
    }

    @Redirect(method = "consume(Lnet/minecraft/world/item/ItemStack;IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;getFoodLevel()I"))
    private int alwaysApplyDiet2(FoodData instance) {
        return prevFood + 1;
    }

    @Redirect(method = "consume(Ljava/util/List;IF)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/food/FoodData;getFoodLevel()I"))
    private int alwaysApplyDiet3(FoodData instance) {
        return prevFood + 1;
    }
}

