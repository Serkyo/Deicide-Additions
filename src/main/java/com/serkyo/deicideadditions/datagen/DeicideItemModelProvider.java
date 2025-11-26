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
        basicItem(DeicideItems.LEATHER_STRAP.get());
        basicItem(DeicideItems.END_WARRIOR_SMITHING_TEMPLATE.get());
        basicItem(DeicideItems.ENDERMITE_CORE.get());
        basicItem(DeicideItems.STABILIZED_ENERGY_CONDUIT.get());
        basicItem(DeicideItems.FIRE_DRAGONSTEEL_SMITHING_TEMPLATE.get());
        basicItem(DeicideItems.ICE_DRAGONSTEEL_SMITHING_TEMPLATE.get());
        basicItem(DeicideItems.LIGHTNING_DRAGONSTEEL_SMITHING_TEMPLATE.get());
        basicItem(DeicideItems.DREADSTEEL_SMITHING_TEMPLATE.get());
        basicItem(DeicideItems.FIRE_DRACONIC_SIGIL.get());
        basicItem(DeicideItems.ICE_DRACONIC_SIGIL.get());
        basicItem(DeicideItems.LIGHTNING_DRACONIC_SIGIL.get());
//        basicItem(DeicideItems.OBLIVION_EFFIGY.get());
        basicItem(DeicideItems.FIRE_DRAGON_CANTEEN.get());
        basicItem(DeicideItems.ICE_DRAGON_CANTEEN.get());
        basicItem(DeicideItems.LIGHTNING_DRAGON_CANTEEN.get());
    }
}
