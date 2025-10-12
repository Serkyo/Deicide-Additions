package com.serkyo.deicideadditions.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Inventory.class)
public class InventoryMixin {
    @ModifyArg(method = "hurtArmor", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Ljava/util/function/Consumer;)V"))
    public int armorDamageCap(int pAmount, @Local ItemStack itemStack) {
        int maxDurabilityDamage = (int) Math.ceil(itemStack.getMaxDamage() * 0.005F);
        System.out.println("Lowered damage amount from " + pAmount + " to " + maxDurabilityDamage);
        return Math.min(pAmount, maxDurabilityDamage);
    }
}
