package com.serkyo.deicideadditions.item;

import net.donne431.ice_and_fire_delight.init.IceAndFireDelightModMobEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sfiomn.legendarysurvivaloverhaul.api.thirst.ThirstUtil;

import java.util.List;

public class IceDragonCanteen extends DragonPurifyingCanteenItem {
    public IceDragonCanteen(Properties properties) {
        super(properties);
    }
    @Override
    public @NotNull String getDescriptionId(ItemStack stack) {
        if (ThirstUtil.getCapacityTag(stack) == 0) {
            return "item.deicideadditions.ice_dragon_canteen_empty";
        } else {
            return "item.deicideadditions.ice_dragon_canteen";
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.deicideadditions.ice_dragon_canteen.description").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, Level level, @NotNull LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(IceAndFireDelightModMobEffects.ICE_ASPECT.get(), 3600, 0, false, true, true));
        }
        return super.finishUsingItem(stack, level, entity);
    }
}
