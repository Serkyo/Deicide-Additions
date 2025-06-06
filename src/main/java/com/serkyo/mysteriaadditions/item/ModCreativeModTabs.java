package com.serkyo.mysteriaadditions.item;

import com.serkyo.mysteriaadditions.MysteriaAdditions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MysteriaAdditions.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MYSTERIA_TAB = CREATIVE_MODE_TABS.register("mysteria_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DIVINITY_FRAGMENT.get()))
                    .title(Component.translatable("creativetab.mysteria_tab"))
                    .displayItems((pParameters,pOutput) -> {
                        pOutput.accept(ModItems.DIVINITY_FRAGMENT.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
