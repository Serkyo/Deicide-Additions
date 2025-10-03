package com.serkyo.deicideadditions.utils;

import java.util.HashSet;
import java.util.Set;

public class Chapter {
    private final String id;
    private final Set<Boss> intermediaryBosses;
    private final Boss finalBoss;
    private final Boss secondaryFinalBoss;

    public Chapter(String id, Set<Boss> intermediaryBosses, Boss finalBoss, Boss secondaryFinalBoss) {
        this.id = id;
        this.intermediaryBosses = new HashSet<>(intermediaryBosses);
        this.finalBoss = finalBoss;
        this.secondaryFinalBoss = secondaryFinalBoss;
    }

    public Chapter(String id, Set<Boss> intermediaryBosses, Boss finalBoss) {
        this(id, intermediaryBosses, finalBoss, null);
    }

    public String getId() {
        return id;
    }

    public Set<Boss> getIntermediaryBosses() {
        return intermediaryBosses;
    }

    public Boss getFinalBoss() {
        return finalBoss;
    }

    public Boss getSecondaryFinalBoss() {
        return secondaryFinalBoss;
    }
}
