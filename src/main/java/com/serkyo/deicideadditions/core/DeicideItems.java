package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DeicideItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DeicideAdditions.MOD_ID);

    public static final RegistryObject<Item> DIVINITY_FRAGMENT = ITEMS.register("divinity_fragment",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SHEET = ITEMS.register("diamond_sheet",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ROUGH_DIAMOND = ITEMS.register("rough_diamond",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PUTRID_HIDE = ITEMS.register("putrid_hide",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LEATHER_SCRAP = ITEMS.register("leather_scrap",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> END_WARRIOR_SMITHING_TEMPLATE = ITEMS.register("end_warrior_smithing_template",
            EndWarriorSmithingTemplate::new);
    public static final RegistryObject<Item> ENDERMITE_CORE = ITEMS.register("endermite_core",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STABILIZED_ENERGY_CONDUIT = ITEMS.register("stabilized_energy_conduit",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FIRE_DRAGONSTEEL_SMITHING_TEMPLATE = ITEMS.register("fire_dragonsteel_smithing_template",
            FireDragonsteelSmithingTemplate::new);
    public static final RegistryObject<Item> ICE_DRAGONSTEEL_SMITHING_TEMPLATE = ITEMS.register("ice_dragonsteel_smithing_template",
            IceDragonsteelSmithingTemplate::new);
    public static final RegistryObject<Item> LIGHTNING_DRAGONSTEEL_SMITHING_TEMPLATE = ITEMS.register("lightning_dragonsteel_smithing_template",
            LightningDragonsteelSmithingTemplate::new);
    public static final RegistryObject<Item> DREADSTEEL_SMITHING_TEMPLATE = ITEMS.register("dreadsteel_smithing_template",
            DreadsteelSmithingTemplate::new);
    public static final RegistryObject<Item> FIRE_DRACONIC_SIGIL = ITEMS.register("fire_draconic_sigil",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ICE_DRACONIC_SIGIL = ITEMS.register("ice_draconic_sigil",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> LIGHTNING_DRACONIC_SIGIL = ITEMS.register("lightning_draconic_sigil",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
