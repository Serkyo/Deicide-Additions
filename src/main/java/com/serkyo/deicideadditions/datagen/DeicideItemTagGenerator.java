package com.serkyo.deicideadditions.datagen;

import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sculkhorde.core.ModBlocks;
import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideTags;
import net.acetheeldritchking.ice_and_fire_spellbooks.registries.ItemRegistries;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class DeicideItemTagGenerator extends ItemTagsProvider {
    public DeicideItemTagGenerator(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, DeicideAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(DeicideTags.Items.SCULK_INFESTED)
                .add(ModBlocks.INFESTED_CRYING_OBSIDIAN.get().asItem())
                .add(ModBlocks.CALCITE_ORE.get().asItem())
                .add(ModBlocks.SCULK_ANCIENT_NODE_BLOCK.get().asItem())
                .add(ModBlocks.SCULK_NODE_BLOCK.get().asItem())
                .add(ModBlocks.SCULK_ARACHNOID.get().asItem())
                .add(ModBlocks.SCULK_DURA_MATTER.get().asItem())
                .add(ModBlocks.BEE_COLONY_CORE_BLOCK.get().asItem())
                .add(ModBlocks.SCULK_BEE_NEST_BLOCK.get().asItem())
                .add(ModBlocks.SCULK_BEE_NEST_CELL_BLOCK.get().asItem())
                .add(ModBlocks.SCULK_LIVING_ROCK_ROOT_BLOCK.get().asItem())
                .add(ModBlocks.SCULK_LIVING_ROCK_BLOCK.get().asItem())
                .add(ModBlocks.SOULITE_CORE_BLOCK.get().asItem())
                .add(ModBlocks.BUDDING_SOULITE_BLOCK.get().asItem())
                .add(ModBlocks.SOULITE_BLOCK.get().asItem())
                .add(ModBlocks.DEPLETED_SOULITE_BLOCK.get().asItem())
                .add(ModBlocks.FUNGAL_SHROOM_CORE_BLOCK.get().asItem())
                .add(ModBlocks.FUNGAL_SCULK_BLOCK.get().asItem())
                .add(ModBlocks.FUNGAL_SCULK_STEM_BLOCK.get().asItem())
                .add(ModBlocks.TENDRIL_CORE_BLOCK.get().asItem())
                .add(ModBlocks.TENDRILS.get().asItem())
                .add(ModBlocks.DISEASED_KELP_BLOCK.get().asItem())
                .add(ModBlocks.INFESTED_LOG.get().asItem())
                .add(ModBlocks.INFESTED_SAND.get().asItem())
                .add(ModBlocks.INFESTED_RED_SAND.get().asItem())
                .add(ModBlocks.INFESTED_SANDSTONE.get().asItem())
                .add(ModBlocks.INFESTED_SANDSTONE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_SANDSTONE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_SANDSTONE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_GRAVEL.get().asItem())
                .add(ModBlocks.INFESTED_CLAY.get().asItem())
                .add(ModBlocks.INFESTED_STONE.get().asItem())
                .add(ModBlocks.INFESTED_STONE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_STONE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_COBBLESTONE.get().asItem())
                .add(ModBlocks.INFESTED_COBBLESTONE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_COBBLESTONE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_COBBLESTONE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_COBBLESTONE.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_COBBLESTONE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_COBBLESTONE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_COBBLESTONE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_DEEPSLATE.get().asItem())
                .add(ModBlocks.INFESTED_COBBLED_DEEPSLATE.get().asItem())
                .add(ModBlocks.INFESTED_COBBLED_DEEPSLATE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_COBBLED_DEEPSLATE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_COBBLED_DEEPSLATE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_ANDESITE.get().asItem())
                .add(ModBlocks.INFESTED_ANDESITE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_ANDESITE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_ANDESITE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_DIORITE.get().asItem())
                .add(ModBlocks.INFESTED_DIORITE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_DIORITE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_DIORITE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_GRANITE.get().asItem())
                .add(ModBlocks.INFESTED_GRANITE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_GRANITE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_GRANITE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_TUFF.get().asItem())
                .add(ModBlocks.INFESTED_CALCITE.get().asItem())
                .add(ModBlocks.INFESTED_SNOW.get().asItem())
                .add(ModBlocks.INFESTED_MOSS.get().asItem())
                .add(ModBlocks.INFESTED_MUD.get().asItem())
                .add(ModBlocks.INFESTED_PACKED_MUD.get().asItem())
                .add(ModBlocks.INFESTED_MUD_BRICKS.get().asItem())
                .add(ModBlocks.INFESTED_MUD_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_MUD_BRICK_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_MUD_BRICK_WALL.get().asItem())
                .add(ModBlocks.INFESTED_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_BLACK_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_BLUE_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_BROWN_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_CYAN_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_GRAY_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_GREEN_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_LIGHT_BLUE_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_LIGHT_GRAY_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_LIME_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_MAGENTA_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_ORANGE_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_PINK_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_PURPLE_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_RED_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_WHITE_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_YELLOW_TERRACOTTA.get().asItem())
                .add(ModBlocks.INFESTED_NETHERRACK.get().asItem())
                .add(ModBlocks.INFESTED_CRIMSON_NYLIUM.get().asItem())
                .add(ModBlocks.INFESTED_WARPED_NYLIUM.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE_WALL.get().asItem())
                .add(ModBlocks.INFESTED_BASALT.get().asItem())
                .add(ModBlocks.INFESTED_SMOOTH_BASALT.get().asItem())
                .add(ModBlocks.INFESTED_ENDSTONE.get().asItem())
                .add(ModBlocks.INFESTED_STONE_BRICKS.get().asItem())
                .add(ModBlocks.INFESTED_STONE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_STONE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_STONE_BRICK_WALL.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_STONE_BRICKS.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_STONE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_STONE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_MOSSY_STONE_BRICK_WALL.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE_BRICKS.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE_BRICK_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE_BRICK_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_BLACKSTONE_BRICK_WALL.get().asItem())
                .add(ModBlocks.INFESTED_WOOD_MASS.get().asItem())
                .add(ModBlocks.INFESTED_WOOD_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_WOOD_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_WOOD_FENCE.get().asItem())
                .add(ModBlocks.INFESTED_CRUMPLED_MASS.get().asItem())
                .add(ModBlocks.INFESTED_CRUMBLING_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_CRUMBLING_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_CRUMBLING_WALL.get().asItem())
                .add(ModBlocks.INFESTED_STURDY_MASS.get().asItem())
                .add(ModBlocks.INFESTED_STURDY_STAIRS.get().asItem())
                .add(ModBlocks.INFESTED_STURDY_SLAB.get().asItem())
                .add(ModBlocks.INFESTED_STURDY_WALL.get().asItem())
                .add(ModBlocks.INFESTED_STURDY_FENCE.get().asItem())
                .add(ModBlocks.INFESTED_WOOD_FENCE_GATE.get().asItem())
                .add(ModBlocks.INFESTED_STURDY_FENCE_GATE.get().asItem());

        this.tag(DeicideTags.Items.DRAGONSTEEL_INGOTS)
                .add(IafItemRegistry.DRAGONSTEEL_FIRE_INGOT.get())
                .add(IafItemRegistry.DRAGONSTEEL_ICE_INGOT.get())
                .add(IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT.get());

        this.tag(DeicideTags.Items.DRAGONSTEEL_HELMET)
                .add(IafItemRegistry.DRAGONSTEEL_FIRE_HELMET.get())
                .add(IafItemRegistry.DRAGONSTEEL_ICE_HELMET.get())
                .add(IafItemRegistry.DRAGONSTEEL_LIGHTNING_HELMET.get());

        this.tag(DeicideTags.Items.DRAGONSTEEL_CHESTPLATE)
                .add(IafItemRegistry.DRAGONSTEEL_FIRE_CHESTPLATE.get())
                .add(IafItemRegistry.DRAGONSTEEL_ICE_CHESTPLATE.get())
                .add(IafItemRegistry.DRAGONSTEEL_LIGHTNING_CHESTPLATE.get());

        this.tag(DeicideTags.Items.DRAGONSTEEL_LEGGINGS)
                .add(IafItemRegistry.DRAGONSTEEL_FIRE_LEGGINGS.get())
                .add(IafItemRegistry.DRAGONSTEEL_ICE_LEGGINGS.get())
                .add(IafItemRegistry.DRAGONSTEEL_LIGHTNING_LEGGINGS.get());

        this.tag(DeicideTags.Items.DRAGONSTEEL_BOOTS)
                .add(IafItemRegistry.DRAGONSTEEL_FIRE_BOOTS.get())
                .add(IafItemRegistry.DRAGONSTEEL_ICE_BOOTS.get())
                .add(IafItemRegistry.DRAGONSTEEL_LIGHTNING_BOOTS.get());

        this.tag(DeicideTags.Items.DRAGON_PRIEST_HELMET)
                .add(ItemRegistries.FIRE_DRAGON_PRIEST_HELMET.get())
                .add(ItemRegistries.ICE_DRAGON_PRIEST_HELMET.get())
                .add(ItemRegistries.LIGHTNING_DRAGON_PRIEST_HELMET.get());

        this.tag(DeicideTags.Items.DRAGON_PRIEST_CHESTPLATE)
                .add(ItemRegistries.FIRE_DRAGON_PRIEST_CHESTPLATE.get())
                .add(ItemRegistries.ICE_DRAGON_PRIEST_CHESTPLATE.get())
                .add(ItemRegistries.LIGHTNING_DRAGON_PRIEST_CHESTPLATE.get());

        this.tag(DeicideTags.Items.DRAGON_PRIEST_LEGGINGS)
                .add(ItemRegistries.FIRE_DRAGON_PRIEST_LEGGINGS.get())
                .add(ItemRegistries.ICE_DRAGON_PRIEST_LEGGINGS.get())
                .add(ItemRegistries.LIGHTNING_DRAGON_PRIEST_LEGGINGS.get());

        this.tag(DeicideTags.Items.DRAGON_PRIEST_BOOTS)
                .add(ItemRegistries.FIRE_DRAGON_PRIEST_BOOTS.get())
                .add(ItemRegistries.ICE_DRAGON_PRIEST_BOOTS.get())
                .add(ItemRegistries.LIGHTNING_DRAGON_PRIEST_BOOTS.get());

        this.tag(DeicideTags.Items.DRAGON_BLOOD)
                .add(IafItemRegistry.FIRE_DRAGON_BLOOD.get())
                .add(IafItemRegistry.ICE_DRAGON_BLOOD.get())
                .add(IafItemRegistry.LIGHTNING_DRAGON_BLOOD.get());
    }
}
