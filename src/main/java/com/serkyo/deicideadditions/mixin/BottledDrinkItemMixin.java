package com.serkyo.deicideadditions.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import sfiomn.legendarysurvivaloverhaul.common.items.drink.BottledDrinkItem;

@Mixin(BottledDrinkItem.class)
public class BottledDrinkItemMixin {
    @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/Item$Properties;stacksTo(I)Lnet/minecraft/world/item/Item$Properties;"))
    private static int changeStackAmount(int pMaxStackSize) {
        return 16;
    }
}
