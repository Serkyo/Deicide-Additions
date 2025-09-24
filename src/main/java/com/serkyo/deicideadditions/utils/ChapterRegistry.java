package com.serkyo.deicideadditions.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ChapterRegistry {
    public static final List<Chapter> CHAPTERS = new ArrayList<>();

    static {
        // Placeholders right now, not the final order
        CHAPTERS.add(new Chapter("chapter1", Set.of("entity.minecraft.wither"), "entity.minecraft.ender_dragon"));
        CHAPTERS.add(new Chapter("chapter2", Set.of("entity.irons_spellbooks.dead_king"), "entity.graveyard.lich"));
    }
}
