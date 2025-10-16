package com.serkyo.deicideadditions.capability.progression;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ChapterProgressProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ChapterProgress> CHAPTER_PROGRESS = CapabilityManager.get(new CapabilityToken<>() {});

    private ChapterProgress chapterProgress = null;
    private final LazyOptional<ChapterProgress> optional = LazyOptional.of(this::createChapterProgress);

    private ChapterProgress createChapterProgress() {
        if (this.chapterProgress == null) {
            this.chapterProgress = new ChapterProgress();
        }
        return this.chapterProgress;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CHAPTER_PROGRESS) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createChapterProgress().saveNBTDate(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createChapterProgress().loadNBTData(nbt);
    }
}
