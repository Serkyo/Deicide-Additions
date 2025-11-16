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

    public Set<String> getCompletedChaptersId() {
        return completedChaptersId;
    }

    public Set<ResourceLocation> getDefeatedBosses() { return defeatedBosses; }

    public void addDefeatedBoss(ResourceLocation bossId) {
        defeatedBosses.add(bossId);

        Chapter currentChapter = getCurrentChapter();

        if (currentChapter != null) {
            Set<Boss> bossList = new HashSet<>(currentChapter.getIntermediaryBosses());
            bossList.add(currentChapter.getFinalBoss());
            bossList.add(currentChapter.getSecondaryFinalBoss());
            boolean chapterComplete = bossList.stream().allMatch(this::hasDefeatedBoss);

            if (chapterComplete) {
                addCompletedChapterId(currentChapter.getId());
            }
        }

    }

    private void addCompletedChapterId(String chapterId) {
        completedChaptersId.add(chapterId);
    }

    public Boolean hasDefeatedBoss(Boss boss) {
        return defeatedBosses.contains(boss.getId());
    }

    public Chapter getCurrentChapter() {
        for (Chapter chapter : DeicideRegistry.CHAPTERS) {
            if (!completedChaptersId.contains(chapter.getId())) {
                return chapter;
            }
        }
        return null;
    }

    public void copyFrom(ProgressionSystem source) {
        this.defeatedBosses = source.defeatedBosses;
        this.completedChaptersId = source.completedChaptersId;
    }

    public void saveNBTDate(CompoundTag nbt) {
        ListTag defeatedBossesList = new ListTag();
        for (ResourceLocation boss : defeatedBosses) {
            defeatedBossesList.add(StringTag.valueOf(boss.toString()));
        }
        nbt.put("DefeatedBosses", defeatedBossesList);

        ListTag completedChaptersList = new ListTag();
        for (String chapterId : completedChaptersId) {
            completedChaptersList.add(StringTag.valueOf(chapterId));
        }
        nbt.put("CompletedChapters", completedChaptersList);
    }

    public void loadNBTData(CompoundTag nbt) {
        defeatedBosses.clear();
        completedChaptersId.clear();

        ListTag defeatedBossesList = nbt.getList("DefeatedBosses", 8);
        for (int i = 0; i < defeatedBossesList.size(); i++) {
            defeatedBosses.add(ResourceLocation.parse(defeatedBossesList.getString(i)));
        }

        ListTag completedChaptersList = nbt.getList("CompletedChapters", 8);
        for (int i = 0; i < completedChaptersList.size(); i++) {
            completedChaptersId.add(completedChaptersList.getString(i));
        }
    }
}
