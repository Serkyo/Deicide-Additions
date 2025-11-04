package com.serkyo.deicideadditions.datagen;

import com.github.alexthe666.iceandfire.datagen.tags.IafItemTags;
import com.github.alexthe666.iceandfire.item.IafItemRegistry;
import com.github.sculkhorde.core.ModItems;
import com.serkyo.deicideadditions.core.DeicideItems;
import com.serkyo.deicideadditions.core.DeicideTags;
import io.redspace.ironsspellbooks.registries.ItemRegistry;
import net.mcreator.mofusbetterend.init.MofusBetterEndModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import vectorwing.farmersdelight.common.tag.ForgeTags;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.FIRE_DRAGONSTEEL_SMITHING_TEMPLATE.get(), 2)
                .define('#', DeicideItems.FIRE_DRAGONSTEEL_SMITHING_TEMPLATE.get())
                .define('X', IafItemRegistry.DRAGON_SKULL_FIRE.get())
                .define('Y', IafItemRegistry.FIRE_DRAGON_BLOOD.get())
                .pattern("Y#Y")
                .pattern("YXY")
                .pattern("YYY")
                .unlockedBy("has_fire_dragon_skull", inventoryTrigger(ItemPredicate.Builder.item().of(IafItemRegistry.DRAGON_SKULL_FIRE.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.ICE_DRAGONSTEEL_SMITHING_TEMPLATE.get(), 2)
                .define('#', DeicideItems.ICE_DRAGONSTEEL_SMITHING_TEMPLATE.get())
                .define('X', IafItemRegistry.DRAGON_SKULL_ICE.get())
                .define('Y', IafItemRegistry.ICE_DRAGON_BLOOD.get())
                .pattern("Y#Y")
                .pattern("YXY")
                .pattern("YYY")
                .unlockedBy("has_ice_dragon_skull", inventoryTrigger(ItemPredicate.Builder.item().of(IafItemRegistry.DRAGON_SKULL_ICE.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.LIGHTNING_DRAGONSTEEL_SMITHING_TEMPLATE.get(), 2)
                .define('#', DeicideItems.LIGHTNING_DRAGONSTEEL_SMITHING_TEMPLATE.get())
                .define('X', IafItemRegistry.DRAGON_SKULL_LIGHTNING.get())
                .define('Y', IafItemRegistry.LIGHTNING_DRAGON_BLOOD.get())
                .pattern("Y#Y")
                .pattern("YXY")
                .pattern("YYY")
                .unlockedBy("has_lightning_dragon_skull", inventoryTrigger(ItemPredicate.Builder.item().of(IafItemRegistry.DRAGON_SKULL_LIGHTNING.get()).build()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, DeicideItems.DREADSTEEL_SMITHING_TEMPLATE.get(), 2)
                .define('#', DeicideItems.DREADSTEEL_SMITHING_TEMPLATE.get())
                .define('X', IafItemTags.DRAGON_HEARTS)
                .define('Y', DeicideTags.Items.DRAGONSTEEL_INGOTS)
                .pattern("Y#Y")
                .pattern("YXY")
                .pattern("YYY")
                .unlockedBy("has_dragon_heart", inventoryTrigger(ItemPredicate.Builder.item().of(IafItemTags.DRAGON_HEARTS).build()))
                .save(pWriter);
        CuttingBoardRecipeBuilder.cuttingRecipe(
                Ingredient.of(new ItemLike[]{Items.LEATHER}),
                Ingredient.of(ForgeTags.TOOLS_KNIVES),
                (ItemLike) DeicideItems.LEATHER_STRAP.get(),
                3)
                .build(pWriter);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Items.ROTTEN_FLESH), RecipeCategory.MISC, DeicideItems.PUTRID_HIDE.get(), 0.1F, 200)
                .unlockedBy("has_rotten_flesh", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.ROTTEN_FLESH).build()))
                .save(pWriter);
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DeicideItems.FIRE_DRAGONSTEEL_SMITHING_TEMPLATE.get()), Ingredient.of(sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry.LARGE_CANTEEN.get()) ,Ingredient.of(DeicideItems.FIRE_DRACONIC_SIGIL.get()), RecipeCategory.MISC, DeicideItems.FIRE_DRAGON_CANTEEN.get())
                .unlocks("has_large_canteen", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry.LARGE_CANTEEN.get()).build()))
                .save(pWriter, DeicideItems.FIRE_DRAGON_CANTEEN.getId() + "_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DeicideItems.ICE_DRAGONSTEEL_SMITHING_TEMPLATE.get()), Ingredient.of(sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry.LARGE_CANTEEN.get()) ,Ingredient.of(DeicideItems.ICE_DRACONIC_SIGIL.get()), RecipeCategory.MISC, DeicideItems.ICE_DRAGON_CANTEEN.get())
                .unlocks("has_large_canteen", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry.LARGE_CANTEEN.get()).build()))
                .save(pWriter, DeicideItems.ICE_DRAGON_CANTEEN.getId() + "_smithing");
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DeicideItems.LIGHTNING_DRAGONSTEEL_SMITHING_TEMPLATE.get()), Ingredient.of(sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry.LARGE_CANTEEN.get()) ,Ingredient.of(DeicideItems.LIGHTNING_DRACONIC_SIGIL.get()), RecipeCategory.MISC, DeicideItems.LIGHTNING_DRAGON_CANTEEN.get())
                .unlocks("has_large_canteen", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(sfiomn.legendarysurvivaloverhaul.registry.ItemRegistry.LARGE_CANTEEN.get()).build()))
                .save(pWriter, DeicideItems.LIGHTNING_DRAGON_CANTEEN.getId() + "_smithing");
    }
}
