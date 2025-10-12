package com.serkyo.deicideadditions.mixin;

import com.github.sculkhorde.common.blockentity.SculkAncientNodeBlockEntity;
import com.serkyo.deicideadditions.core.DeicideRegistry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockEntity.class)
public class BlockEntityMixin {
    private boolean registered = false;

    @SuppressWarnings("ConditionAlwaysFalse")
    @Inject(method = "setLevel", at = @At("TAIL"))
    private void onAddedToWorld(Level pLevel, CallbackInfo ci) {
        if (!registered && (Object) this instanceof SculkAncientNodeBlockEntity node) {
            DeicideRegistry.registerAncientNode(node);
        }
    }

    @Inject(method = "setRemoved", at = @At("HEAD"))
    private void onRemoved(CallbackInfo ci) {
        if (registered && (Object) this instanceof SculkAncientNodeBlockEntity node) {
            DeicideRegistry.unregisterAncientNode(node);
            registered = false;
        }
    }
}
