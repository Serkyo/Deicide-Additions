package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class DeicideTags {
    public static class Items {
        public static final TagKey<Item> SCULK_INFESTED = tag("sculk_infested");
        public static final TagKey<Item> DRAGONSTEEL_INGOTS = tag("dragonsteel_ingots");
        public static final TagKey<Item> DRAGONSTEEL_HELMET = tag("dragonsteel_helmet");
        public static final TagKey<Item> DRAGONSTEEL_CHESTPLATE = tag("dragonsteel_chestplate");
        public static final TagKey<Item> DRAGONSTEEL_LEGGINGS = tag("dragonsteel_leggings");
        public static final TagKey<Item> DRAGONSTEEL_BOOTS = tag("dragonsteel_boots");
        public static final TagKey<Item> DRAGON_PRIEST_HELMET = tag("dragon_priest_helmet");
        public static final TagKey<Item> DRAGON_PRIEST_CHESTPLATE = tag("dragon_priest_chestplate");
        public static final TagKey<Item> DRAGON_PRIEST_LEGGINGS = tag("dragon_priest_leggings");
        public static final TagKey<Item> DRAGON_PRIEST_BOOTS = tag("dragon_priest_boots");
        public static final TagKey<Item> DRAGON_BLOOD = tag("dragon_blood");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(DeicideAdditions.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }
    }

    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(DeicideAdditions.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
        }
    }
}
