package com.serkyo.deicideadditions.datagen;

import com.github.sculkhorde.core.ModItems;
import com.serkyo.deicideadditions.core.DeicideItems;
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
