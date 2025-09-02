package com.serkyo.deicideadditions.datagen;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class DeicideItemModelProvider extends ItemModelProvider {
    public DeicideItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, DeicideAdditions.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(DeicideItems.DIVINITY_FRAGMENT.get());
        basicItem(DeicideItems.DIAMOND_SHEET.get());
        basicItem(DeicideItems.ROUGH_DIAMOND.get());
        basicItem(DeicideItems.PUTRID_HIDE.get());
        basicItem(DeicideItems.LEATHER_SCRAP.get());
    }
}
