package com.serkyo.deicideadditions.datagen;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import sfiomn.legendarysurvivaloverhaul.api.data.builder.IThirstData;
import sfiomn.legendarysurvivaloverhaul.api.data.providers.ThirstDataProvider;
import sfiomn.legendarysurvivaloverhaul.api.thirst.HydrationEnum;
import java.util.concurrent.CompletableFuture;

import static sfiomn.legendarysurvivaloverhaul.util.internal.ThirstUtilInternal.HYDRATION_ENUM_TAG;

public class DeicideThirstProvider extends ThirstDataProvider {
    public DeicideThirstProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper fileHelper) {
        super(DeicideAdditions.MOD_ID, output, lookupProvider, fileHelper);
    }

    @Override
    public void generate(HolderLookup.Provider provider, ExistingFileHelper existingFileHelper) {
        IThirstData purifiedWaterDragonCanteen = thirstData(9, 2.25f)
                .addProperty(HYDRATION_ENUM_TAG, HydrationEnum.PURIFIED.getName());

        consumable(DeicideItems.FIRE_DRAGON_CANTEEN.get()).addThirst(purifiedWaterDragonCanteen);
        consumable(DeicideItems.ICE_DRAGON_CANTEEN.get()).addThirst(purifiedWaterDragonCanteen);
        consumable(DeicideItems.LIGHTNING_DRAGON_CANTEEN.get()).addThirst(purifiedWaterDragonCanteen);
    }
}
