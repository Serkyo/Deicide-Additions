package com.serkyo.deicideadditions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.serkyo.deicideadditions.capability.progression.ChapterProgressProvider;
import com.serkyo.deicideadditions.utils.Boss;
import com.serkyo.deicideadditions.utils.Chapter;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

import java.util.Set;

public class ProgressionCommand {
    public ProgressionCommand(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("deicide").then(Commands.literal("chapter").executes(this::execute)));
    }

    private int execute(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayer();
        StringBuilder message = new StringBuilder();

        if (player != null) {
            player.getCapability(ChapterProgressProvider.CHAPTER_PROGRESS).ifPresent(chapterProgress -> {
                Chapter currentChapter = chapterProgress.getCurrentChapter();

                if (currentChapter != null) {
                    message.append("Current chapter : ").append(currentChapter.getId());
                    Set<Boss> intermediaryBosses = currentChapter.getIntermediaryBosses();
                    Set<ResourceLocation> defeatedBosses = chapterProgress.getDefeatedBosses();

                    for (Boss boss : intermediaryBosses) {
                        ResourceLocation bossId = boss.getId();

                        if (defeatedBosses.contains(bossId.toString())) {
                            message.append("\n[x] ").append(bossId);
                        }
                        else {
                            message.append("\n[ ] ").append(bossId);
                        }
                    }
                    message.append("\n Final boss : ").append(currentChapter.getFinalBoss().getId());

                    Boss secondaryFinalBoss = currentChapter.getSecondaryFinalBoss();
                    if (secondaryFinalBoss != null) {
                        message.append("\n Secondary final boss : ").append(secondaryFinalBoss.getId());
                    }
                }
                else {
                    message.append("All chapters have been completed");
                }
            });
            context.getSource().sendSuccess(() -> Component.literal(message.toString()), false);
            return 1;
        }
        else {
            message.append("This command can only be used as a player");
            context.getSource().sendFailure(Component.literal(message.toString()));
            return -1;
        }
    }
}
