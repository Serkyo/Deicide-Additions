package com.serkyo.deicideadditions.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sfiomn.legendarysurvivaloverhaul.api.thirst.ThirstUtil;

import java.util.List;

public class LightningDragonCanteen extends DragonPurifyingCanteenItem {
    public LightningDragonCanteen(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull String getDescriptionId(ItemStack stack) {
        if (ThirstUtil.getCapacityTag(stack) == 0) {
            return "item.deicideadditions.lightning_dragon_canteen_empty";
        } else {
            return "item.deicideadditions.lightning_dragon_canteen";
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("item.deicideadditions.lightning_dragon_canteen.description").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, Level level, @NotNull LivingEntity entity) {
        if (!level.isClientSide && entity instanceof Player player) {

        }
        return super.finishUsingItem(stack, level, entity);
    }

}
