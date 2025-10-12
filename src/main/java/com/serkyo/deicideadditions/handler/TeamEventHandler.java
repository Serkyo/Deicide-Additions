package com.serkyo.deicideadditions.handler;

import dev.ftb.mods.ftbteams.api.FTBTeamsAPI;
import dev.ftb.mods.ftbteams.api.Team;
import dev.ftb.mods.ftbteams.api.TeamManager;
import dev.ftb.mods.ftbteams.api.event.*;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextColor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerScoreboard;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.scores.PlayerTeam;

import java.util.Map;

import static net.minecraft.world.scores.Team.CollisionRule;

public class TeamEventHandler {
    public static void init() {
        TeamEvent.PLAYER_JOINED_PARTY.register(TeamEventHandler::onPlayerJoinedParty);
        TeamEvent.PLAYER_LEFT_PARTY.register(TeamEventHandler::onPlayerLeftParty);
        TeamEvent.CREATED.register(TeamEventHandler::onTeamCreated);
        TeamEvent.DELETED.register(TeamEventHandler::onTeamDeleted);
        TeamEvent.PROPERTIES_CHANGED.register(TeamEventHandler::onTeamPropertiesChanged);
    }

    private static void onPlayerJoinedParty(PlayerJoinedPartyTeamEvent event) {
        ServerScoreboard scoreboard = getScoreboard();

        Player player = event.getPlayer();
        String teamId = "ftb_" + event.getTeam().getId();
        PlayerTeam vanillaTeam = scoreboard.getPlayerTeam(teamId);

        if (vanillaTeam != null) {
            scoreboard.addPlayerToTeam(player.getName().getString(), vanillaTeam);
        }
        else {
            System.out.println("Couldn't find vanilla team with the following name : " + teamId);
        }
    }

    private static void onPlayerLeftParty(PlayerLeftPartyTeamEvent event) {
        ServerScoreboard scoreboard = getScoreboard();

        Player player = event.getPlayer();
        String teamId = "ftb_" + event.getTeam().getId();
        PlayerTeam vanillaTeam = scoreboard.getPlayerTeam(teamId);

        if (vanillaTeam != null) {
            scoreboard.removePlayerFromTeam(player.getName().getString(), vanillaTeam);
        }
        else {
            System.out.println("Couldn't find vanilla team with the following name : " + teamId);
        }
    }

    private static void onTeamCreated(TeamCreatedEvent event) {
        Team ftbTeam = event.getTeam();

        if (ftbTeam.isPartyTeam()) {
            ServerScoreboard scoreboard = getScoreboard();
            String teamName = createSafeTeamName(ftbTeam);

            PlayerTeam vanillaTeam = scoreboard.addPlayerTeam(teamName);
            configureVanillaTeam(vanillaTeam, ftbTeam);
        }
    }

    private static void onTeamDeleted(TeamEvent event) {
        ServerScoreboard scoreboard = getScoreboard();

        String deletedTeamId = "ftb_" + event.getTeam().getId();
        PlayerTeam vanillaTeam = scoreboard.getPlayerTeam(deletedTeamId);

        if (vanillaTeam != null) {
            scoreboard.removePlayerTeam(vanillaTeam);
        }
        else {
            System.out.println("Couldn't find vanilla team with the following name : " + deletedTeamId);
        }
    }

    private static void onTeamPropertiesChanged(TeamPropertiesChangedEvent event) {
        ServerScoreboard scoreboard = getScoreboard();
        Team ftbTeam = event.getTeam();

        String teamId = "ftb_" + event.getTeam().getId();
        PlayerTeam vanillaTeam = scoreboard.getPlayerTeam(teamId);

        if (vanillaTeam != null) {
            configureVanillaTeam(vanillaTeam, ftbTeam);
        }
        else {
            System.out.println("Couldn't find vanilla team with the following name : " + teamId);
        }
    }

    private static ServerScoreboard getScoreboard() {
        TeamManager teamManager = FTBTeamsAPI.api().getManager();
        MinecraftServer server = teamManager.getServer();
        return server.getScoreboard();
    }

    private static String createSafeTeamName(Team ftbTeam) {
        return "ftb_" + ftbTeam.getId();
    }

    private static void configureVanillaTeam(PlayerTeam vanillaTeam, Team ftbTeam) {
        vanillaTeam.setDisplayName(Component.literal(ftbTeam.getShortName()));
        vanillaTeam.setAllowFriendlyFire(false);
        vanillaTeam.setCollisionRule(CollisionRule.PUSH_OTHER_TEAMS);
        vanillaTeam.setSeeFriendlyInvisibles(true);
        TextColor color = ftbTeam.getColoredName().getStyle().getColor();
        if (color != null) {
            vanillaTeam.setColor(getNearestChatFormatting(color.getValue()));
        }
    }

    private static ChatFormatting getNearestChatFormatting(int rgbColor) {
        Map<ChatFormatting, Integer> colorMap = Map.ofEntries(
                Map.entry(ChatFormatting.BLACK, 0x000000),
                Map.entry(ChatFormatting.DARK_BLUE, 0x0000AA),
                Map.entry(ChatFormatting.DARK_GREEN, 0x00AA00),
                Map.entry(ChatFormatting.DARK_AQUA, 0x00AAAA),
                Map.entry(ChatFormatting.DARK_RED, 0xAA0000),
                Map.entry(ChatFormatting.DARK_PURPLE, 0xAA00AA),
                Map.entry(ChatFormatting.GOLD, 0xFFAA00),
                Map.entry(ChatFormatting.GRAY, 0xAAAAAA),
                Map.entry(ChatFormatting.DARK_GRAY, 0x555555),
                Map.entry(ChatFormatting.BLUE, 0x5555FF),
                Map.entry(ChatFormatting.GREEN, 0x55FF55),
                Map.entry(ChatFormatting.AQUA, 0x55FFFF),
                Map.entry(ChatFormatting.RED, 0xFF5555),
                Map.entry(ChatFormatting.LIGHT_PURPLE, 0xFF55FF),
                Map.entry(ChatFormatting.YELLOW, 0xFFFF55),
                Map.entry(ChatFormatting.WHITE, 0xFFFFFF)
        );

        return getClosestColor(rgbColor, colorMap);

    }

    private static ChatFormatting getClosestColor(int rgbColor, Map<ChatFormatting, Integer> colorMap) {
        ChatFormatting closestColor = ChatFormatting.WHITE;
        double minDistance = Double.MAX_VALUE;

        int r1 = (rgbColor >> 16) & 0xFF;
        int g1 = (rgbColor >> 8) & 0xFF;
        int b1 = rgbColor & 0xFF;

        for (Map.Entry<ChatFormatting, Integer> entry : colorMap.entrySet()) {
            int colorRGB = entry.getValue();
            int r2 = (colorRGB >> 16) & 0xFF;
            int g2 = (colorRGB >> 8) & 0xFF;
            int b2 = colorRGB & 0xFF;

            double distance = Math.sqrt(
                    Math.pow(r1 - r2, 2) + Math.pow(g1 - g2, 2) + Math.pow(b1 - b2, 2)
            );

            if (distance < minDistance) {
                minDistance = distance;
                closestColor = entry.getKey();
            }
        }
        return closestColor;
    }
}
