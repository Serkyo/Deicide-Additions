package com.serkyo.deicideadditions.item;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import sfiomn.legendarysurvivaloverhaul.api.thirst.HydrationEnum;
import sfiomn.legendarysurvivaloverhaul.api.thirst.ThirstUtil;
import sfiomn.legendarysurvivaloverhaul.common.items.drink.CanteenItem;

public class DragonPurifyingCanteenItem extends CanteenItem {
    public DragonPurifyingCanteenItem(Properties properties) {
        super(properties.rarity(Rarity.EPIC));
    }

    @Override
    public int getMaxCapacity() {
        return 40;
    }

    @Override
    public void fill(ItemStack stack) {
        ThirstUtil.setCapacityTag(stack, Math.min(this.getMaxCapacity(), ThirstUtil.getCapacityTag(stack) + 1));
        ThirstUtil.setHydrationEnumTag(stack, HydrationEnum.PURIFIED);
    }
}