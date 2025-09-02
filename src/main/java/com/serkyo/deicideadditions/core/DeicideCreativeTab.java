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
                        pOutput.accept(DeicideItems.LEATHER_SCRAP.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
