package com.serkyo.deicideadditions.utils;

import java.util.LinkedHashSet;
import java.util.Set;

public class Chapter {
    private final String id;
    private final Set<Boss> intermediaryBosses;
    private final Boss finalBoss;
    private final int difficultyIncrement;

    public Chapter(String id, Set<Boss> intermediaryBosses, Boss finalBoss, int difficultyIncrement) {
        this.id = id;
        this.intermediaryBosses = new LinkedHashSet<>(intermediaryBosses);
        this.finalBoss = finalBoss;
        this.difficultyIncrement = difficultyIncrement;
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

    public int getDifficultyIncrement(){
        return difficultyIncrement;
    }
}
