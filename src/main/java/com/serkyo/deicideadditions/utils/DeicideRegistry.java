package com.serkyo.deicideadditions.utils;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeicideRegistry {
    public static final List<Chapter> CHAPTERS = new ArrayList<>();
    public static final List<Boss> BOSSES = new ArrayList<>();

    static {
        DeicideRegistry.initialiseChapters();
        DeicideRegistry.buildBossRegistry();
    }

    private static void initialiseChapters() {
        CHAPTERS.add(new Chapter(
                "chapter1",
                Set.of(
                        new Boss("galosphere:berserker", 16, 8),
                        new Boss("legendary_monsters:skeletosaurus", 32, 8),
                        new Boss("legendary_monsters:warped_fungussus", 32, 8),
                        new Boss("legendary_monsters:lava_eater", 32, 8),
                        new Boss("legendary_monsters:withered_abomination", 24, 8),
                        new Boss("legendary_monsters:ancient_guardian", 24, 8),
                        new Boss("minecraft:elder_guardian", 16, 8),
                        new Boss("legendary_monsters:posessed_paladin", 24, 8),
                        new Boss("mowziesmobs:ferrous_wroughtnaut", 8, 4),
                        new Boss("dungeonnowloading:chaos_spawner", 24, 8)
                ),
                new Boss("soulsweapons:draugr_boss", 24, 8),
                new Boss("soulsweapons:night_shade", 32, 8)));
        CHAPTERS.add(new Chapter(
                "chapter2",
                Set.of(
                        new Boss("bosses_of_mass_destruction:void_blossom", 32, 16),
                        new Boss("irons_spellbooks:dead_king", 24, 16),
                        new Boss("block_factorys_bosses:yeti", 32, 8),
                        new Boss("legendary_monsters:frostbitten_golem", 24, 8),
                        new Boss("legendary_monsters:overgrown_colossus", 24, 8),
                        new Boss("aquamirae:captain_cornelia", 32, 8),
                        new Boss("mowziesmobs:umvuthi", 32, 8),
                        new Boss("bosses_of_mass_destruction:lich", 64, 32),
                        new Boss("born_in_chaos_v1:lord_pumpkinhead", 32, 8),
                        new Boss("soulsweapons:accursed_lord_boss", 24, 16),
                        new Boss("hadean_breathe:hadean_enforcer", 24, 8)
                ),
                new Boss("graveyard:lich", 24, 8)));
        CHAPTERS.add(new Chapter(
                "chapter3",
                Set.of(
                        new Boss("legendary_monsters:cloud_golem", 32, 8),
                        new Boss("block_factorys_bosses:infernal_dragon", 32, 16),
                        new Boss("soulsweapons:returning_knight", 24, 8),
                        new Boss("block_factorys_bosses:underworld_knight", 24, 8),
                        new Boss("block_factorys_bosses:sandworm", 32, 16),
                        new Boss("mowziesmobs:frostmaw", 24, 8),
                        new Boss("bosses_of_mass_destruction:gauntlet", 48, 16),
                        new Boss("dungeonnowloading:fairkeeper_boros", 32, 16),
                        new Boss("dungeonnowloading:fairkeeper_ouros", 32, 16),
                        new Boss("goety:vizier", 24, 8),
                        new Boss("alexscaves:luxtructosaurus", 64, 32)
                ),
                new Boss("minecraft:wither", 64, 32)));
        CHAPTERS.add(new Chapter(
                "chapter4",
                Set.of(
                        new Boss("minecraft:ender_dragon", 64, 64),
                        new Boss("bosses_of_mass_destruction:obsidilith", 24, 8),
                        new Boss("mofus_better_end_:endermite_queen", 24, 8),
                        new Boss("mofus_better_end_:eye_guardian", 24, 8),
                        new Boss("mofus_better_end_:forgotten_litch", 32, 8),
                        new Boss("mofus_better_end_:reborn_litch", 32, 8),
                        new Boss("alexsmobs:void_worm", 64, 32),
                        new Boss("cataclysm:netherite_monstrosity", 32, 8),
                        new Boss("soulsweapons:chaos_monarch", 32, 8),
                        new Boss("legendary_monsters:endersent", 24, 8),
                        new Boss("legendary_monsters:shulker_mimic", 24, 8)
                ),
                new Boss("cataclysm:ender_guardian", 24, 8)));
        CHAPTERS.add(new Chapter(
                "chapter5",
                Set.of(
                        new Boss("cataclysm:the_harbinger", 32, 16),
                        new Boss("cataclysm:ancient_remnant", 48, 16),
                        new Boss("soulsweapons:moonknight", 32, 8),
                        new Boss("goety:apostle", 32, 8)
                ),
                new Boss("soulsweapons:day_stalker", 32, 32),
                new Boss("soulsweapons:night_prowler", 32, 32)));
        CHAPTERS.add(new Chapter(
                "chapter6",
                Set.of(
                        new Boss("cataclysm:maledictus", 32, 8),
                        new Boss("cataclysm:the_leviathan", 32, 32),
                        new Boss("cataclysm:scylla", 48, 8),
                        new Boss("cataclysm:ignis", 48, 8)
                ),
                new Boss("traveloptics:the_nightwarden", 48, 16)));
        CHAPTERS.add(new Chapter(
                "chapter7",
                Set.of(
                        new Boss("macabre:baal", 32, 16),
                        new Boss("macabre:gomoria", 32, 16),
                        new Boss("macabre:valamon", 32, 16),
                        new Boss("macabre:gargamaw", 32, 16),
                        new Boss("macabre:morphegor", 32, 16)
                ),
                new Boss("darkdoppelganger:dark_doppelganger", 32, 8)));
    }

    public static void buildBossRegistry() {
        for (Chapter chapter : CHAPTERS) {
            BOSSES.addAll(chapter.getIntermediaryBosses());
            BOSSES.add(chapter.getFinalBoss());
            if (chapter.getSecondaryFinalBoss() != null) {
                BOSSES.add(chapter.getSecondaryFinalBoss());
            }
        }
    }

    public static Boss getBoss(ResourceLocation bossId) {
        return BOSSES.stream()
                .filter(boss -> boss.getId().equals(bossId))
                .findFirst()
                .orElse(null);
    }
}
