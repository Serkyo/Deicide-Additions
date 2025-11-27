package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.Gluttony;
import com.serkyo.deicideadditions.capability.GluttonyProvider;
import com.serkyo.deicideadditions.capability.ProgressionSystem;
import com.serkyo.deicideadditions.capability.ProgressionSystemProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DeicideCapabilities {
    public static final ResourceLocation PROGRESSION_SYSTEM = ResourceLocation.fromNamespaceAndPath(DeicideAdditions.MOD_ID, "chapter_progress");
    public static final ResourceLocation GLUTTONY = ResourceLocation.fromNamespaceAndPath(DeicideAdditions.MOD_ID, "gluttony");

    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(ProgressionSystemProvider.PROGRESSION_SYSTEM).isPresent()) {
                event.addCapability(PROGRESSION_SYSTEM, new ProgressionSystemProvider());
            }
            if (!event.getObject().getCapability(GluttonyProvider.GLUTTONY).isPresent()) {
                event.addCapability(GLUTTONY, new GluttonyProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getEntity().reviveCaps();

            event.getOriginal().getCapability(ProgressionSystemProvider.PROGRESSION_SYSTEM).ifPresent(oldStore -> {
                event.getEntity().getCapability(ProgressionSystemProvider.PROGRESSION_SYSTEM).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().getCapability(GluttonyProvider.GLUTTONY).ifPresent(oldStore -> {
                event.getEntity().getCapability(GluttonyProvider.GLUTTONY).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });

            event.getOriginal().invalidateCaps();
        }
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ProgressionSystem.class);
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
