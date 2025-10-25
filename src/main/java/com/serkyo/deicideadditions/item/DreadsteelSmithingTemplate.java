package com.serkyo.deicideadditions.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class DreadsteelSmithingTemplate extends SmithingTemplateItem {
    public DreadsteelSmithingTemplate() {
        super(
                Component.translatable("deicideadditions.smithing.dreadsteel.applies_to").withStyle(ChatFormatting.BLUE),
                Component.translatable("deicideadditions.smithing.dreadsteel.ingredients").withStyle(ChatFormatting.BLUE),
                Component.translatable("deicideadditions.smithing.dreadsteel.upgrade").withStyle(ChatFormatting.GRAY),
                Component.translatable("deicideadditions.smithing.dreadsteel.base_slot").withStyle(ChatFormatting.GRAY),
                Component.translatable("deicideadditions.smithing.dreadsteel.additions_slot").withStyle(ChatFormatting.GRAY),
                List.of(
                        ResourceLocation.parse( "item/empty_armor_slot_helmet" ),
                        ResourceLocation.parse( "item/empty_armor_slot_chestplate" ),
                        ResourceLocation.parse( "item/empty_armor_slot_leggings" ),
                        ResourceLocation.parse( "item/empty_armor_slot_boots" )
                ),
                List.of(
                        ResourceLocation.parse("item/empty_slot_ingot")
                ));
    }
}
