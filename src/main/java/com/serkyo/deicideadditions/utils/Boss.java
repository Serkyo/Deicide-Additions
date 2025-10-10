package com.serkyo.deicideadditions.utils;

import net.minecraft.resources.ResourceLocation;

public class Boss {
    private final ResourceLocation id;
    private final Integer checkLength;
    private final Integer checkHeight;

    public Boss(String id, Integer checkLength, Integer checkHeight) {
        this.id = ResourceLocation.parse(id);
        this.checkLength = checkLength;
        this.checkHeight = checkHeight;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Integer getCheckLength() {
        return checkLength;
    }

    public Integer getCheckHeight() {
        return checkHeight;
    }
}
