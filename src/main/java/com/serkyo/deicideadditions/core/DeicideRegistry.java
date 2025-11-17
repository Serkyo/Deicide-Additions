package com.serkyo.deicideadditions.core;

import com.github.sculkhorde.common.blockentity.SculkAncientNodeBlockEntity;
import com.serkyo.deicideadditions.utils.Boss;
import com.serkyo.deicideadditions.utils.Chapter;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DeicideRegistry {
    public static final List<Chapter> CHAPTERS = new ArrayList<>();
    public static final List<Boss> BOSSES = new ArrayList<>();
    public static final List<SculkAncientNodeBlockEntity> REGISTERED_NODES = new ArrayList<>();

    public static void register() {
        DeicideRegistry.initialiseChapters();
        DeicideRegistry.buildBossRegistry();
    }

    private static void initialiseChapters() {
        CHAPTERS.add(new Chapter(
                "chapter1",
                new LinkedHashSet<>(List.of(
                        new Boss("galosphere:berserker", 16, 8, "Berserker"),
                        new Boss("legendary_monsters:ancient_guardian", 24, 8, "Ancient Guardian"),
                        new Boss("legendary_monsters:frostbitten_golem", 24, 8, "Frostbitten Golem"),
                        new Boss("legendary_monsters:overgrown_colossus", 24, 8, "Overgrown Colossus"),
                        new Boss("minecraft:elder_guardian", 16, 8, "Elder Guardian"),
                        new Boss("legendary_monsters:posessed_paladin", 24, 8, "Posessed Paladin"),
                        new Boss("mowziesmobs:ferrous_wroughtnaut", 8, 4, "Ferrous Wroughtnaut"),
                        new Boss("dungeonnowloading:chaos_spawner", 24, 8, "Chaos Spawner"),
                        new Boss("bosses_of_mass_destruction:lich", 64, 32, "Night Lich"),
                        new Boss("mowziesmobs:umvuthi", 32, 8, "Umvuthi")
                        )),
                new Boss("soulsweapons:draugr_boss", 24, 8, "Old Champion's Remains", Set.of("soulsweapons:night_shade"), true),
                1));
        CHAPTERS.add(new Chapter(
                "chapter2",
                new LinkedHashSet<>(List.of(
                        new Boss("bosses_of_mass_destruction:void_blossom", 32, 16, "Void Blossom"),
                        new Boss("legendary_monsters:withered_abomination", 24, 8, "Withered Abomination"),
                        new Boss("irons_spellbooks:dead_king", 24, 16, "Dead King"),
                        new Boss("block_factorys_bosses:yeti", 32, 8, "Yeti"),
                        new Boss("aquamirae:captain_cornelia", 32, 8, "Captain Cornelia"),
                        new Boss("born_in_chaos_v1:lord_pumpkinhead", 32, 8, "Lord Pumpkinhead", Set.of("born_in_chaos_v1:lord_pumpkinhead_head", "born_in_chaos_v1:lord_pumpkinhead_withouta_horse", "born_in_chaos_v1:lord_the_headless", "born_in_chaos_v1:lords_felsteed"), true),
                        new Boss("soulsweapons:accursed_lord_boss", 24, 16, "The Decaying King"),
                        new Boss("hadean_breathe:hadean_enforcer", 24, 8, "Hadean Enforcer"),
                        new Boss("legendary_monsters:cloud_golem", 32, 8, "Cloud Golem"),
                        new Boss("block_factorys_bosses:sandworm", 32, 16, "Sandworm")
                        )),
                new Boss("graveyard:lich", 24, 8, "Corrupted Champion"),
                1));
        CHAPTERS.add(new Chapter(
                "chapter3",
                new LinkedHashSet<>(List.of(
                        new Boss("block_factorys_bosses:infernal_dragon", 32, 16, "Infernal Dragon"),
                        new Boss("soulsweapons:returning_knight", 24, 8, "Returning Knight"),
                        new Boss("block_factorys_bosses:underworld_knight", 24, 8, "Underworld Knight"),
                        new Boss("cataclysm:netherite_monstrosity", 32, 8, "Netherite Monstrosity"),
                        new Boss("mowziesmobs:frostmaw", 24, 8, "Frostmaw"),
                        new Boss("bosses_of_mass_destruction:gauntlet", 48, 16, "Nether Gauntlet"),
                        new Boss("dungeonnowloading:fairkeeper_boros", 32, 16, "Fairkeepers", Set.of("dungeonnowloading:fairkeeper_ouros"), false),
                        new Boss("goety:vizier", 24, 8, "Vizier"),
                        new Boss("alexscaves:luxtructosaurus", 64, 32, "Luxtructosaurus")
                )),
                new Boss("minecraft:wither", 64, 32, "Wither"),
                2));
        CHAPTERS.add(new Chapter(
                "chapter4",
                new LinkedHashSet<>(List.of(
                        new Boss("minecraft:ender_dragon", 64, 64, "Ender Dragon"),
                        new Boss("bosses_of_mass_destruction:obsidilith", 24, 8, "Obsidilith"),
                        new Boss("mofus_better_end_:endermite_queen", 24, 8, "Endermite Queen"),
                        new Boss("mofus_better_end_:eye_guardian", 24, 8, "Eye Guardian"),
                        new Boss("mofus_better_end_:forgotten_litch", 32, 8, "Forgotten Litch", Set.of("mofus_better_end_:reborn_litch"), true),
                        new Boss("alexsmobs:void_worm", 64, 32, "Void Worm"),
                        new Boss("soulsweapons:chaos_monarch", 32, 8, "Monarch Of Chaos")
                )),
                new Boss("cataclysm:ender_guardian", 24, 8, "Ender Guardian"),
                2));
        CHAPTERS.add(new Chapter(
                "chapter5",
                new LinkedHashSet<>(List.of(
                        new Boss("cataclysm:the_harbinger", 32, 16, "The Harbinger"),
                        new Boss("cataclysm:ancient_remnant", 48, 16, "Ancient Remnant"),
                        new Boss("soulsweapons:moonknight", 32, 8, "Fallen Icon"),
                        new Boss("goety:apostle", 32, 8, "Apostle")
                )),
                new Boss("soulsweapons:day_stalker", 32, 32, "Day Stalker & Night Prowler", Set.of("soulsweapons:night_prowler"), true),
                3));
        CHAPTERS.add(new Chapter(
                "chapter6",
                new LinkedHashSet<>(List.of(
                        new Boss("cataclysm:maledictus", 32, 8, "Maledictus"),
                        new Boss("cataclysm:the_leviathan", 32, 32, "The Leviathan"),
                        new Boss("cataclysm:scylla", 48, 8, "Scylla"),
                        new Boss("cataclysm:ignis", 48, 8, "Ignis")
                )),
                new Boss("traveloptics:the_nightwarden", 48, 16, "The Nightwarden"),
                3));
        CHAPTERS.add(new Chapter(
                "chapter7",
                new LinkedHashSet<>(List.of(
                        new Boss("macabre:baal", 32, 16, "Baal"),
                        new Boss("macabre:gomoria", 32, 16, "Gomoria"),
                        new Boss("macabre:valamon", 32, 16, "Valamon"),
                        new Boss("macabre:gargamaw", 32, 16, "Gargamaw"),
                        new Boss("macabre:morphegor", 32, 16, "Morphegor")
                )),
                new Boss("darkdoppelganger:dark_doppelganger", 32, 8, "Dark Doppelganger"),
                4));
    }

    public static void buildBossRegistry() {
        for (Chapter chapter : CHAPTERS) {
            BOSSES.addAll(chapter.getIntermediaryBosses());
            BOSSES.add(chapter.getFinalBoss());
        }
    }

    public static Boss getBoss(ResourceLocation bossId) {
        return BOSSES.stream()
                .filter(boss -> boss.getId().equals(bossId) || boss.getSubparts().contains(bossId))
                .findFirst()
                .orElse(null);
    }

    public static void registerAncientNode(SculkAncientNodeBlockEntity node) {
        REGISTERED_NODES.add(node);
    }

    public static void unregisterAncientNode(SculkAncientNodeBlockEntity node) {
        REGISTERED_NODES.remove(node);
    }

    public static List<SculkAncientNodeBlockEntity> getRegisteredNodes() {
        return REGISTERED_NODES;
    }

    public static void clearRegisteredNodes() {
        REGISTERED_NODES.clear();
    }
}
