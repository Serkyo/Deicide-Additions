package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.misc.Gluttony;
import com.serkyo.deicideadditions.capability.misc.GluttonyProvider;
import com.serkyo.deicideadditions.capability.progression.ChapterProgress;
import com.serkyo.deicideadditions.capability.progression.ChapterProgressProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID)
public class DeicideCapabilities {
    public static final ResourceLocation CHAPTER_PROGRESS = ResourceLocation.fromNamespaceAndPath(DeicideAdditions.MOD_ID, "chapter_progress");
    public static final ResourceLocation GLUTTONY = ResourceLocation.fromNamespaceAndPath(DeicideAdditions.MOD_ID, "gluttony");

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).isPresent()) {
                event.addCapability(CHAPTER_PROGRESS, new ChapterProgressProvider());
            }
            if (!event.getObject().getCapability(GluttonyProvider.GLUTTONY).isPresent()) {
                event.addCapability(GLUTTONY, new GluttonyProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(oldStore -> {
                event.getOriginal().getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(GluttonyProvider.GLUTTONY).ifPresent(oldStore -> {
                event.getOriginal().getCapability(GluttonyProvider.GLUTTONY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ChapterProgress.class);
        event.register(Gluttony.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side.isServer()) {
            Player player = event.player;

            if (!shouldSkipTick(player)) {
                player.getCapability(GluttonyProvider.GLUTTONY).ifPresent(gluttony -> {
                    gluttony.tick(player);
                });
            }
        }
    }

    protected static boolean shouldSkipTick(Player player) {
        return player.isCreative() || player.isSpectator();
    }
}
