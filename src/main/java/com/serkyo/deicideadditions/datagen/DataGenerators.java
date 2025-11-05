package com.serkyo.deicideadditions.datagen;

import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(), new DeicideItemModelProvider(packOutput, existingFileHelper));

        generator.addProvider(event.includeServer(), new DeicideRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), new DeicideMixingRecipeGenerator(packOutput));
        generator.addProvider(event.includeServer(), new DeicidePressingRecipeGenerator(packOutput));
        BlockTagsProvider blockTagsProvider = new DeicideBlockTagGenerator(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTagsProvider);
        generator.addProvider(event.includeServer(), new DeicideItemTagGenerator(packOutput, lookupProvider, blockTagsProvider.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new DeicideThirstProvider(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new DeicideTemperatureProvider(packOutput, lookupProvider, existingFileHelper));
    }
}
