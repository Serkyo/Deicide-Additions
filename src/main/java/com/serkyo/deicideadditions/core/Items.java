package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DeicideAdditions.MOD_ID);

    public static final RegistryObject<Item> DIVINITY_FRAGMENT = ITEMS.register("divinity_fragment",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DIAMOND_SHEET = ITEMS.register("diamond_sheet",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ROUGH_DIAMOND = ITEMS.register("rough_diamond",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEATHER_CHUNK = ITEMS.register("leather_chunk",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> LEATHER_SCRAP = ITEMS.register("leather_scrap",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
