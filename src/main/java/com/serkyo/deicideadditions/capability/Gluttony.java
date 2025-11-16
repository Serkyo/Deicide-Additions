package com.serkyo.deicideadditions.capability;

import com.serkyo.deicideadditions.DeicideAdditions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class Gluttony {
    private int overeatingCount;
    private boolean wasFullBeforeConsume;
    private int ticksSinceLastEat;

    public int getOvereatingCount() {
        return overeatingCount;
    }

    public void resetOvereatingCount() {
        overeatingCount = 0;
    }

    public void incrementOvereatingCount() {
        overeatingCount += 1;
    }

    public boolean wasFullBeforeConsume() {
        return wasFullBeforeConsume;
    }

    public void setWasFullBeforeConsume(boolean wasFullBeforeConsume) {
        this.wasFullBeforeConsume = wasFullBeforeConsume;
    }

    public void tick(Player player) {
        ticksSinceLastEat++;
        if (ticksSinceLastEat >= 1200) {
            ticksSinceLastEat = 0;
            if (overeatingCount > 0) {
                overeatingCount--;
                DeicideAdditions.LOGGER.debug("Gluttony count decaying to {} for {}", overeatingCount, player.getName().getString());
            }
        }
    }

    public void copyFrom(Gluttony source) {
        overeatingCount = source.overeatingCount;
        wasFullBeforeConsume = source.wasFullBeforeConsume;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("overeatingCount", overeatingCount);
        nbt.putBoolean("wasFullBeforeConsume", wasFullBeforeConsume);
    }

    public void loadNBTData(CompoundTag nbt) {
        overeatingCount = nbt.getInt("overeatingCount");
        wasFullBeforeConsume = nbt.getBoolean("wasFullBeforeConsume");
    }
}
