package com.serkyo.deicideadditions.utils;

import net.minecraft.resources.ResourceLocation;

public class Boss {
    private final ResourceLocation id;
    private final Integer checkRange;

    public Boss(String id, Integer checkRange) {
        this.id = ResourceLocation.parse(id);
        this.checkRange = checkRange;
    }

    public Boss(String id) {
        this(id, 64);
    }

    public ResourceLocation getId() {
        return id;
    }

    public Integer getCheckRange() {
        return checkRange;
    }
}
