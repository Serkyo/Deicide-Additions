package com.serkyo.deicideadditions.utils;

import net.minecraft.resources.ResourceLocation;

import java.util.Set;
import java.util.stream.Collectors;

public class Boss {
    private final ResourceLocation id;
    private final Integer checkLength;
    private final Integer checkHeight;
    private final String name;
    private final Set<ResourceLocation> subparts;
    private final boolean mandatorySubparts;

    public Boss(String id, Integer checkLength, Integer checkHeight, String name, Set<String> subparts, boolean mandatorySubparts) {
        this.id = ResourceLocation.parse(id);
        this.checkLength = checkLength;
        this.checkHeight = checkHeight;
        this.name = name;
        this.subparts = subparts.stream()
                .map(ResourceLocation::parse)
                .collect(Collectors.toUnmodifiableSet());
        this.mandatorySubparts = mandatorySubparts;
    }
    public Boss(String id, Integer checkLength, Integer checkHeight, String name) {
        this.id = ResourceLocation.parse(id);
        this.checkLength = checkLength;
        this.checkHeight = checkHeight;
        this.name = name;
        this.subparts = Set.of();
        this.mandatorySubparts = false;
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

    public String getName() {
        return name;
    }

    public Set<ResourceLocation> getSubparts() {
        return subparts;
    }

    public boolean requireAllSubparts() {
        return mandatorySubparts;
    }
}
