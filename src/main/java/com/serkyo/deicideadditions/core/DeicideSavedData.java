package com.serkyo.deicideadditions.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;

public class DeicideSavedData extends SavedData {
    private static final String DATA_NAME = "deicide_data";
    private final ArrayList<String> bossesKilledServerWide = new ArrayList<String>();

    public void markBossDefeated(String bossId) {
        if (!isBossDefeated(bossId)) {
            bossesKilledServerWide.add(bossId);
            setDirty();
        }
    }

    public boolean isBossDefeated(String bossId) {
        return bossesKilledServerWide.contains(bossId);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (String boss : bossesKilledServerWide) {
            CompoundTag bossTag = new CompoundTag();
            bossTag.putString("id", boss);
            list.add(bossTag);
        }
        tag.put("BOSSES_KILLED_SERVER_WIDE", list);
        return tag;
    }

    public static DeicideSavedData load(CompoundTag tag) {
        DeicideSavedData data = new DeicideSavedData();
        ListTag list = tag.getList("BOSSES_KILLED_SERVER_WIDE", 10);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag bossTag = list.getCompound(i);
            data.bossesKilledServerWide.add(bossTag.getString("id"));
        }
        return data;
    }

    public static DeicideSavedData get(MinecraftServer server) {
        return server.overworld()
                .getDataStorage()
                .computeIfAbsent(DeicideSavedData::load, DeicideSavedData::new, DATA_NAME);
    }
}
