package com.serkyo.deicideadditions.mixin;

import com.serkyo.deicideadditions.core.DeicideEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.CampfireBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vectorwing.farmersdelight.common.registry.ModEffects;

import java.util.List;

@Mixin(CampfireBlockEntity.class)
public class CampfireBlockEntityMixin {
    @Inject(method = "cookTick", at = @At(value = "HEAD"))
    private static void campfireEffect(Level level, BlockPos pos, BlockState state, CampfireBlockEntity blockEntity, CallbackInfo ci) {
        if (level.getGameTime() % 40 == 0) {
            AABB bounding_box = new AABB(pos).inflate(3);
            List<Player> nearby_players = level.getEntitiesOfClass(Player.class, bounding_box);

            for (Player player : nearby_players) {
                if (!player.hasEffect(DeicideEffects.CORRUPTING_PRESENCE_EFFECT.get())) {
                    player.addEffect(new MobEffectInstance(ModEffects.COMFORT.get(), 100, 0, true, true));
                }
            }
        }
    }
}
