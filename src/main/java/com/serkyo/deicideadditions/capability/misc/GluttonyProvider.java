package com.serkyo.deicideadditions.capability.misc;

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

public class GluttonyProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<Gluttony> GLUTTONY = CapabilityManager.get(new CapabilityToken<Gluttony>() { });

    private Gluttony gluttony = null;
    private final LazyOptional<Gluttony> optional = LazyOptional.of(this::createGluttony);

    private Gluttony createGluttony() {
        if (gluttony == null) {
            gluttony = new Gluttony();
        }
        return gluttony;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == GLUTTONY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createGluttony().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createGluttony().loadNBTData(nbt);
    }
}
