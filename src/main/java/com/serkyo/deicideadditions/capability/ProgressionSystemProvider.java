package com.serkyo.deicideadditions.capability;

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

public class ProgressionSystemProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<ProgressionSystem> CHAPTER_PROGRESS = CapabilityManager.get(new CapabilityToken<>() {});

    private ProgressionSystem progressionSystem = null;
    private final LazyOptional<ProgressionSystem> optional = LazyOptional.of(this::createChapterProgress);

    private ProgressionSystem createChapterProgress() {
        if (this.progressionSystem == null) {
            this.progressionSystem = new ProgressionSystem();
        }
        return this.progressionSystem;
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
