package com.serkyo.deicideadditions.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import sfiomn.legendarysurvivaloverhaul.common.capabilities.thirst.ThirstCapability;

@Mixin(ThirstCapability.class)
public class ThirstCapabilityMixin {
    /**
     * @author Serkyo
     * @reason Allow players to eat regardless of their saturation
     */
    @Overwrite(remap = false)
    public boolean isHydrationLevelAtMax() { return false; }
}
