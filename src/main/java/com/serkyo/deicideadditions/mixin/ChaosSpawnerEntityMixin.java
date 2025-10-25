package com.serkyo.deicideadditions.mixin;

import dev.hexnowloading.dungeonnowloading.entity.boss.ChaosSpawnerEntity;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChaosSpawnerEntity.class)
public class ChaosSpawnerEntityMixin {
    @Inject(method = "die", at = @At(value = "HEAD"))
    public void die(DamageSource damageSource, CallbackInfo ci) {
        ChaosSpawnerEntity self = (ChaosSpawnerEntity)(Object)this;
        net.minecraftforge.common.ForgeHooks.onLivingDeath(self, damageSource);
    }
}
