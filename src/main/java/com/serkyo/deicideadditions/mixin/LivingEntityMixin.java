package com.serkyo.deicideadditions.mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    /**
     * @author Serkyo
     * @reason Too broken
     */
    @Overwrite(remap = false)
    public boolean curePotionEffects(ItemStack curativeItem) {
        return true;
    }
}
