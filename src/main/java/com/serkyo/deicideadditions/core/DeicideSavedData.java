package com.serkyo.deicideadditions.core;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.saveddata.SavedData;

import java.util.ArrayList;

public class DeicideSavedData extends SavedData {
    private static final String DATA_NAME = "deicide_data";
    private final ArrayList<ResourceLocation> bossesKilledServerWide = new ArrayList<>();

    public void markBossDefeated(ResourceLocation bossId) {
        if (!isBossDefeated(bossId)) {
            bossesKilledServerWide.add(bossId);
            setDirty();
        }
    }

    public boolean isBossDefeated(ResourceLocation bossId) {
        return bossesKilledServerWide.contains(bossId);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        ListTag list = new ListTag();
        for (ResourceLocation boss : bossesKilledServerWide) {
            CompoundTag bossTag = new CompoundTag();
            bossTag.putString("id", boss.toString());
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
            data.bossesKilledServerWide.add(ResourceLocation.parse(bossTag.getString("id")));
        }
        return data;
    }

    public static DeicideSavedData get(MinecraftServer server) {
        return server.overworld()
                .getDataStorage()
                .computeIfAbsent(DeicideSavedData::load, DeicideSavedData::new, DATA_NAME);
    }
}
