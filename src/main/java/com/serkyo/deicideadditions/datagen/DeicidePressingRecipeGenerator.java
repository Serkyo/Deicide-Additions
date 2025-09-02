package com.serkyo.deicideadditions.datagen;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideItems;
import com.simibubi.create.api.data.recipe.PressingRecipeGen;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

public class DeicidePressingRecipeGenerator extends PressingRecipeGen {
    public DeicidePressingRecipeGenerator(PackOutput output) {
        super(output, DeicideAdditions.MOD_ID);
    }

    GeneratedRecipe DIAMOND_SHEET = create("diamond_sheet", b -> b
            .require(Items.DIAMOND)
            .output(DeicideItems.DIAMOND_SHEET.get()));
    GeneratedRecipe LEATHER = create("leather_from_pressing", b -> b
            .require(DeicideItems.PUTRID_HIDE.get())
            .output(Items.LEATHER));
}
