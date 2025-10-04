package com.serkyo.deicideadditions.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class FireDragonsteelSmithingTemplate extends SmithingTemplateItem {
    public FireDragonsteelSmithingTemplate() {
        super(
                Component.translatable("deicideadditions.smithing.fire_dragonsteel.applies_to").withStyle(ChatFormatting.BLUE),
                Component.translatable("deicideadditions.smithing.fire_dragonsteel.ingredients").withStyle(ChatFormatting.BLUE),
                Component.translatable("deicideadditions.smithing.fire_dragonsteel.upgrade").withStyle(ChatFormatting.GRAY),
                Component.translatable("deicideadditions.smithing.fire_dragonsteel.base_slot").withStyle(ChatFormatting.GRAY),
                Component.translatable("deicideadditions.smithing.fire_dragonsteel.additions_slot").withStyle(ChatFormatting.GRAY),
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
