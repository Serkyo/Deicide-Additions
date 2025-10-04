package com.serkyo.deicideadditions.datagen;

import com.github.alexthe666.iceandfire.datagen.tags.IafItemTags;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sculkhorde.core.ModItems;
import com.serkyo.deicideadditions.core.DeicideItems;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.mcreator.mofusbetterend.init.MofusBetterEndModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class DeicideRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public DeicideRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.SCULK, 8)
                .define('#', Items.MOSS_BLOCK)
                .define('X', ModItems.SCULK_RESIN.get())
                .pattern("###")
                .pattern("#X#")
                .pattern("###")
                .unlockedBy("has_sculk_resin", inventoryTrigger(ItemPredicate.Builder.item().of(ModItems.SCULK_RESIN.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.ENDERMITE_CORE.get())
                .define('#', MofusBetterEndModItems.ENDERMITE_GUARDIAN_HORN.get())
                .define('X', MofusBetterEndModItems.ENDERMITE_SWARMER_WING.get())
                .define('Y', MofusBetterEndModItems.ENDERMITE_HORNED_EYE.get())
                .define('Z', MofusBetterEndModItems.ENDERMITE_QUEEN_CLAW.get())
                .define('O', MofusBetterEndModItems.ENDERMITE_QUEEN_SCALE.get())
                .pattern("X#X")
                .pattern("ZYZ")
                .pattern("OOO")
                .unlockedBy("has_endermite_heart", inventoryTrigger(ItemPredicate.Builder.item().of(MofusBetterEndModItems.ENDERMITE_HORNED_EYE.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.STABILIZED_ENERGY_CONDUIT.get())
                .define('#', MofusBetterEndModItems.GUARDIAN_FRAGMENT.get())
                .define('X', MofusBetterEndModItems.END_ENERGY_CUBE.get())
                .define('Y', MofusBetterEndModItems.ENGINE_CORE.get())
                .pattern("X#X")
                .pattern("#Y#")
                .pattern("X#X")
                .unlockedBy("has_engine_core", inventoryTrigger(ItemPredicate.Builder.item().of(MofusBetterEndModItems.ENGINE_CORE.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.FIRE_DRACONIC_SIGIL.get())
                .define('#', IafItemTags.SCALES_DRAGON_FIRE)
                .define('X', ItemRegistry.FIRE_RUNE.get())
                .define('Y', IafItemRegistry.DRAGONSTEEL_FIRE_INGOT.get())
                .pattern("#Y#")
                .pattern("YXY")
                .pattern("#Y#")
                .unlockedBy("has_fire_dragonsteel_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(IafItemRegistry.DRAGONSTEEL_FIRE_INGOT.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.ICE_DRACONIC_SIGIL.get())
                .define('#', IafItemTags.SCALES_DRAGON_ICE)
                .define('X', ItemRegistry.ICE_RUNE.get())
                .define('Y', IafItemRegistry.DRAGONSTEEL_ICE_INGOT.get())
                .pattern("#Y#")
                .pattern("YXY")
                .pattern("#Y#")
                .unlockedBy("has_ice_dragonsteel_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(IafItemRegistry.DRAGONSTEEL_ICE_INGOT.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.LIGHTNING_DRACONIC_SIGIL.get())
                .define('#', IafItemTags.SCALES_DRAGON_LIGHTNING)
                .define('X', ItemRegistry.LIGHTNING_RUNE.get())
                .define('Y', IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT.get())
                .pattern("#Y#")
                .pattern("YXY")
                .pattern("#Y#")
                .unlockedBy("has_fire_dragonsteel_ingot", inventoryTrigger(ItemPredicate.Builder.item().of(IafItemRegistry.DRAGONSTEEL_LIGHTNING_INGOT.get()).build()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, DeicideItems.LEATHER_SCRAP.get(), 3)
                .requires(Items.LEATHER)
                .unlockedBy("has_leather", inventoryTrigger(ItemPredicate.Builder.item().of(Items.LEATHER).build()))
                .save(pWriter);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.ROTTEN_FLESH), RecipeCategory.MISC, DeicideItems.PUTRID_HIDE.get(), 0.1F, 200)
                .unlockedBy("has_rotten_flesh", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.ROTTEN_FLESH).build()))
                .save(pWriter);
    }
}
