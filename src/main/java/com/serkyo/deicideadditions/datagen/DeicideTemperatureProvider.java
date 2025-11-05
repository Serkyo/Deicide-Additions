package com.serkyo.deicideadditions.datagen;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import sfiomn.legendarysurvivaloverhaul.api.data.providers.TemperatureDataProvider;
import sfiomn.legendarysurvivaloverhaul.api.temperature.TemporaryModifierGroupEnum;

import java.util.concurrent.CompletableFuture;

public class DeicideTemperatureProvider extends TemperatureDataProvider {
    public DeicideTemperatureProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper fileHelper) {
        super(DeicideAdditions.MOD_ID, output, lookupProvider, fileHelper);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        this.consumable(DeicideItems.FIRE_DRAGON_CANTEEN.get()).addTemperature(this.temperatureConsumable(TemporaryModifierGroupEnum.DRINK).temperatureLevel(4).duration(3600));
        this.consumable(DeicideItems.ICE_DRAGON_CANTEEN.get()).addTemperature(this.temperatureConsumable(TemporaryModifierGroupEnum.DRINK).temperatureLevel(-4).duration(3600));
    }
}
