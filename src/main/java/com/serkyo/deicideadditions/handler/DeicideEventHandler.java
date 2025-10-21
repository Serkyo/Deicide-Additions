package com.serkyo.deicideadditions.handler;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.misc.Gluttony;
import com.serkyo.deicideadditions.capability.misc.GluttonyProvider;
import com.serkyo.deicideadditions.capability.progression.ChapterProgress;
import com.serkyo.deicideadditions.capability.progression.ChapterProgressProvider;
import com.serkyo.deicideadditions.core.DeicideRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID)
public class DeicideEventHandler {
    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        DeicideRegistry.clearRegisteredNodes();
    }
}
