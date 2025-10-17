package com.serkyo.deicideadditions.mixin;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Player.class)
public class PlayerMixin {
    /**
     * @author Serkyo
     * @reason Allow players to eat regardless of their saturation
     */
    @Overwrite(remap = false)
    public boolean canEat(boolean pCanAlwaysEat) { return true; }
}
