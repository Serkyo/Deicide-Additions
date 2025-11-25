package com.serkyo.deicideadditions.capability;

import com.serkyo.deicideadditions.utils.Boss;
import com.serkyo.deicideadditions.utils.Chapter;
import com.serkyo.deicideadditions.core.DeicideRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class ProgressionSystem {
    private Set<ResourceLocation> defeatedBosses = new HashSet<>();
    private Set<String> completedChaptersId = new HashSet<>();
    private int difficultyLevel = 1;

    public Set<String> getCompletedChaptersId() {
        return completedChaptersId;
    }

    public Set<ResourceLocation> getDefeatedBosses() {
        return defeatedBosses;
    }

    public float getDifficultyLevelScaled(double xCoord, double yCoord, double zCoord, ResourceLocation dimension) {
        float dimensionMultiplier;
        double seaLevel;
        switch (dimension.toString()) {
            case "minecraft:overworld": {
                dimensionMultiplier = 1;
                seaLevel = 62;
                break;
            }
            case "minecraft:the_nether": {
                dimensionMultiplier = 1.5F;
                seaLevel = 31;
                break;
            }
            case "macabre:the_pit": {
                dimensionMultiplier = 3F;
                seaLevel = 62;
                break;
            }
            default: {
                dimensionMultiplier = 2F;
                seaLevel = 0;
                break;
            }
        }
        float distanceFromOrigin = (float) Math.sqrt(Math.pow(xCoord, 2) + Math.pow(yCoord - seaLevel, 2) + Math.pow(zCoord, 2));
        return dimensionMultiplier * (difficultyLevel * (1 + 0.001F * distanceFromOrigin));
    }

    public Chapter getCurrentChapter() {
        for (Chapter chapter : DeicideRegistry.CHAPTERS) {
            if (!completedChaptersId.contains(chapter.getId())) {
                return chapter;
            }
        }
        return null;
    }

    public Boolean hasDefeatedBoss(Boss boss) {
        if (boss.requireAllSubparts()) {
            return defeatedBosses.contains(boss.getId()) && defeatedBosses.containsAll(boss.getSubparts());
        }
        return defeatedBosses.contains(boss.getId());
    }

    public boolean addDefeatedBoss(ResourceLocation bossId) {
        defeatedBosses.add(bossId);

        Chapter currentChapter = getCurrentChapter();

        if (currentChapter != null) {
            Set<Boss> bossList = new HashSet<>(currentChapter.getIntermediaryBosses());
            bossList.add(currentChapter.getFinalBoss());

            boolean chapterComplete = bossList.stream().allMatch(this::hasDefeatedBoss);

            if (chapterComplete) {
                addCompletedChapterId(currentChapter.getId());
                increaseDifficultyLevel(currentChapter.getDifficultyIncrement());
            }
            return chapterComplete;
        }
        return false;
    }

    public void increaseDifficultyLevel(int amount) {
        difficultyLevel += amount;
    }

    private void addCompletedChapterId(String chapterId) {
        completedChaptersId.add(chapterId);
    }

    public void copyFrom(ProgressionSystem source) {
        this.defeatedBosses = Set.copyOf(source.defeatedBosses);
        this.completedChaptersId = Set.copyOf(source.completedChaptersId);
        this.difficultyLevel = source.difficultyLevel;
    }

    public void saveNBTDate(CompoundTag nbt) {
        ListTag defeatedBossesList = new ListTag();
        for (ResourceLocation boss : defeatedBosses) {
            defeatedBossesList.add(StringTag.valueOf(boss.toString()));
        }
        nbt.put("defeatedBosses", defeatedBossesList);

        ListTag completedChaptersList = new ListTag();
        for (String chapterId : completedChaptersId) {
            completedChaptersList.add(StringTag.valueOf(chapterId));
        }
        nbt.put("completedChapters", completedChaptersList);
        nbt.putInt("difficultyLevel", difficultyLevel);
    }

    public void loadNBTData(CompoundTag nbt) {
        defeatedBosses.clear();
        completedChaptersId.clear();

        ListTag defeatedBossesList = nbt.getList("defeatedBosses", 8);
        for (int i = 0; i < defeatedBossesList.size(); i++) {
            defeatedBosses.add(ResourceLocation.parse(defeatedBossesList.getString(i)));
        }

        ListTag completedChaptersList = nbt.getList("completedChapters", 8);
        for (int i = 0; i < completedChaptersList.size(); i++) {
            completedChaptersId.add(completedChaptersList.getString(i));
        }

        difficultyLevel = nbt.getInt("difficultyLevel");
    }
}
