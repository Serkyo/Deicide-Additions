package com.serkyo.mysteriaadditions.item;

import com.serkyo.mysteriaadditions.MysteriaAdditions;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MysteriaAdditions.MOD_ID);

    public static final RegistryObject<Item> DIVINITY_FRAGMENT = ITEMS.register("divinity_fragment",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
