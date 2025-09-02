package com.serkyo.deicideadditions.datagen;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideItems;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import net.mcreator.borninchaosv.init.BornInChaosV1ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

public class DeicideMixingRecipeGenerator extends MixingRecipeGen {
    public DeicideMixingRecipeGenerator(PackOutput output) {
        super(output, DeicideAdditions.MOD_ID);
    }

    GeneratedRecipe DIAMOND = create("diamond_from_mixing", b -> b
            .require(BornInChaosV1ModItems.DIAMOND_TERMITE_SHARD.get())
            .require(DeicideItems.ROUGH_DIAMOND.get())
            .require(DeicideItems.ROUGH_DIAMOND.get())
            .output(Items.DIAMOND, 3));
}
