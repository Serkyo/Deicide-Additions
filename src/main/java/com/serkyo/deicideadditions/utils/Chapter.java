package com.serkyo.deicideadditions.utils;

import java.util.HashSet;
import java.util.Set;

public class Chapter {
    private final String id;
    private final Set<String> intermediaryBosses;
    private final String finalBossId;

    public Chapter(String id, Set<String> intermediaryBosses, String finalBossId) {
        this.id = id;
        this.intermediaryBosses = new HashSet<>(intermediaryBosses);
        this.finalBossId = finalBossId;
    }

    public String getId() {
        return id;
    }

    public Set<String> getIntermediaryBosses() {
        return intermediaryBosses;
    }

    public String getFinalBossId() {
        return finalBossId;
    }
}
