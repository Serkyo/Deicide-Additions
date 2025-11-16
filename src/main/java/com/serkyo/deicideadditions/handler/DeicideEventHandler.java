package com.serkyo.deicideadditions.handler;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.core.DeicideRegistry;
import net.minecraftforge.event.server.ServerStoppedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID)
public class DeicideEventHandler {
    @SubscribeEvent
    public static void onServerStopped(ServerStoppedEvent event) {
        DeicideRegistry.clearRegisteredNodes();
        DeicideAdditions.LOGGER.info("Cleared registered Ancient Nodes");
    }
}
