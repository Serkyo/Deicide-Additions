package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DeicideCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeicideAdditions.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DEICIDE_TAB = CREATIVE_MODE_TABS.register("deicide_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(DeicideItems.DIVINITY_FRAGMENT.get()))
                    .title(Component.translatable("creativetab.deicide_tab"))
                    .displayItems((pParameters,pOutput) -> {
                        pOutput.accept(DeicideItems.DIVINITY_FRAGMENT.get());
                        pOutput.accept(DeicideItems.DIAMOND_SHEET.get());
                        pOutput.accept(DeicideItems.ROUGH_DIAMOND.get());
                        pOutput.accept(DeicideItems.PUTRID_HIDE.get());
                        pOutput.accept(DeicideItems.LEATHER_STRAP.get());
                        pOutput.accept(DeicideItems.END_WARRIOR_SMITHING_TEMPLATE.get());
                        pOutput.accept(DeicideItems.ENDERMITE_CORE.get());
                        pOutput.accept(DeicideItems.STABILIZED_ENERGY_CONDUIT.get());
                        pOutput.accept(DeicideItems.FIRE_DRAGONSTEEL_SMITHING_TEMPLATE.get());
                        pOutput.accept(DeicideItems.ICE_DRAGONSTEEL_SMITHING_TEMPLATE.get());
                        pOutput.accept(DeicideItems.LIGHTNING_DRAGONSTEEL_SMITHING_TEMPLATE.get());
                        pOutput.accept(DeicideItems.DREADSTEEL_SMITHING_TEMPLATE.get());
                        pOutput.accept(DeicideItems.FIRE_DRACONIC_SIGIL.get());
                        pOutput.accept(DeicideItems.ICE_DRACONIC_SIGIL.get());
                        pOutput.accept(DeicideItems.LIGHTNING_DRACONIC_SIGIL.get());
                        pOutput.accept(DeicideItems.OBLIVION_EFFIGY.get());
                        pOutput.accept(DeicideItems.FIRE_DRAGON_CANTEEN.get());
                        pOutput.accept(DeicideItems.ICE_DRAGON_CANTEEN.get());
                        pOutput.accept(DeicideItems.LIGHTNING_DRAGON_CANTEEN.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
