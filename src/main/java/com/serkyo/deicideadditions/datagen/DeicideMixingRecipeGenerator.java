package com.serkyo.deicideadditions.datagen;

import com.Polarice3.Goety.common.items.ModItems;
import com.curseforge.macabre.init.MacabreModItems;
import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideItems;
import com.serkyo.deicideadditions.core.DeicideTags;
import com.simibubi.create.api.data.recipe.MixingRecipeGen;
import com.simibubi.create.content.processing.recipe.HeatCondition;
import dev.shadowsoffire.apotheosis.adventure.Adventure;
import net.mcreator.borninchaosv.init.BornInChaosV1ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import plus.dragons.createenchantmentindustry.entry.CeiFluids;

public class DeicideMixingRecipeGenerator extends MixingRecipeGen {
    public DeicideMixingRecipeGenerator(PackOutput output) {
        super(output, DeicideAdditions.MOD_ID);
    }

    GeneratedRecipe DIAMOND = create("diamond_from_mixing", b -> b
            .require(BornInChaosV1ModItems.DIAMOND_TERMITE_SHARD.get())
            .require(DeicideItems.ROUGH_DIAMOND.get())
            .require(DeicideItems.ROUGH_DIAMOND.get())
            .output(Items.DIAMOND, 3));
    GeneratedRecipe LIQUID_EXPERIENCE_FROM_SCULK = create("liquid_experience_from_mixing_sculk", b -> b
            .require(Fluids.WATER, 100)
            .require(Blocks.SCULK)
            .require(Blocks.SCULK)
            .require(Blocks.SCULK)
            .output(CeiFluids.EXPERIENCE.get(), 10));
    GeneratedRecipe LIQUID_EXPERIENCE_FROM_CATALYST = create("liquid_experience_from_mixing_sculk_catalyst", b -> b
            .require(Fluids.WATER, 100)
            .require(Blocks.SCULK_CATALYST)
            .output(CeiFluids.EXPERIENCE.get(), 10)
            .requiresHeat(HeatCondition.HEATED));
    GeneratedRecipe LIQUID_EXPERIENCE_FROM_SENSOR = create("liquid_experience_from_mixing_sculk_sensor", b -> b
            .require(Fluids.WATER, 100)
            .require(Blocks.SCULK_SENSOR)
            .output(CeiFluids.EXPERIENCE.get(), 10)
            .requiresHeat(HeatCondition.HEATED));
    GeneratedRecipe LIQUID_EXPERIENCE_FROM_SHRIEKER = create("liquid_experience_from_mixing_sculk_shrieker", b -> b
            .require(Fluids.WATER, 100)
            .require(Blocks.SCULK_SHRIEKER)
            .output(CeiFluids.EXPERIENCE.get(), 50)
            .requiresHeat(HeatCondition.HEATED));
    GeneratedRecipe LIQUID_EXPERIENCE_FROM_INFECTION = create("liquid_experience_from_mixing_infection", b -> b
            .require(Fluids.WATER, 100)
            .require(DeicideTags.Items.SCULK_INFESTED)
            .output(CeiFluids.EXPERIENCE.get(), 20)
            .requiresHeat(HeatCondition.HEATED));
    GeneratedRecipe UNCOMMON_MATERIAL = create("uncommon_material_mixing", b -> b
            .require(Adventure.Items.COMMON_MATERIAL.get())
            .require(Adventure.Items.COMMON_MATERIAL.get())
            .require(Adventure.Items.COMMON_MATERIAL.get())
            .require(Adventure.Items.COMMON_MATERIAL.get())
            .require(Adventure.Items.COMMON_MATERIAL.get())
            .require(CeiFluids.EXPERIENCE.get(), 300)
            .output(Adventure.Items.UNCOMMON_MATERIAL.get())
            .requiresHeat(HeatCondition.HEATED));
    GeneratedRecipe RARE_MATERIAL = create("rare_material_mixing", b -> b
            .require(Adventure.Items.UNCOMMON_MATERIAL.get())
            .require(Adventure.Items.UNCOMMON_MATERIAL.get())
            .require(Adventure.Items.UNCOMMON_MATERIAL.get())
            .require(Adventure.Items.UNCOMMON_MATERIAL.get())
            .require(Adventure.Items.UNCOMMON_MATERIAL.get())
            .require(Items.AMETHYST_SHARD)
            .require(Items.AMETHYST_SHARD)
            .require(CeiFluids.EXPERIENCE.get(), 500)
            .output(Adventure.Items.RARE_MATERIAL.get())
            .requiresHeat(HeatCondition.HEATED));
    GeneratedRecipe EPIC_MATERIAL = create("epic_material_mixing", b -> b
            .require(Adventure.Items.RARE_MATERIAL.get())
            .require(Adventure.Items.RARE_MATERIAL.get())
            .require(Adventure.Items.RARE_MATERIAL.get())
            .require(Adventure.Items.RARE_MATERIAL.get())
            .require(Items.GHAST_TEAR)
            .require(Items.GHAST_TEAR)
            .require(CeiFluids.HYPER_EXPERIENCE.get(), 70)
            .output(Adventure.Items.EPIC_MATERIAL.get())
            .requiresHeat(HeatCondition.SUPERHEATED));
    GeneratedRecipe MYTHIC_MATERIAL = create("mythic_material_mixing", b -> b
            .require(Adventure.Items.EPIC_MATERIAL.get())
            .require(Adventure.Items.EPIC_MATERIAL.get())
            .require(Adventure.Items.EPIC_MATERIAL.get())
            .require(ModItems.VOIDED_EYE.get())
            .require(ModItems.VOIDED_EYE.get())
            .require(CeiFluids.HYPER_EXPERIENCE.get(), 90)
            .output(Adventure.Items.MYTHIC_MATERIAL.get())
            .requiresHeat(HeatCondition.SUPERHEATED));
    GeneratedRecipe ANCIENT_MATERIAL = create("ancient_material_mixing", b -> b
            .require(Adventure.Items.MYTHIC_MATERIAL.get())
            .require(Adventure.Items.MYTHIC_MATERIAL.get())
            .require(Adventure.Items.MYTHIC_MATERIAL.get())
            .require(MacabreModItems.BLOOD_CLOT_INGOT.get())
            .require(MacabreModItems.BLOOD_CLOT_INGOT.get())
            .require(CeiFluids.HYPER_EXPERIENCE.get(), 120)
            .output(Adventure.Items.ANCIENT_MATERIAL.get())
            .requiresHeat(HeatCondition.SUPERHEATED));
}
