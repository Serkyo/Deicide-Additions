package com.serkyo.deicideadditions.items;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.puffish.skillsmod.api.Category;
import net.puffish.skillsmod.api.SkillsAPI;

import java.util.Optional;

public class OblivionEffigy extends Item {
    public OblivionEffigy(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);

        pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1);
        if (!pLevel.isClientSide) {
            Optional<Category> skillTree = SkillsAPI.getCategory(ResourceLocation.fromNamespaceAndPath("deicideadditions", "epic_fight"));
            if (skillTree.isPresent()) {
                stack.shrink(1);
                skillTree.get().resetSkills((ServerPlayer) pPlayer);
                if (pLevel instanceof ServerLevel serverLevel) {
                    serverLevel.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, pPlayer.getX(), pPlayer.getY() + 1, pPlayer.getZ(), 50, 0.5, 1, 0.5, 0.1);
                }
            }
        }
        else {
            triggerTotemAnimation(stack);
        }

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide);
    }

    private void triggerTotemAnimation(ItemStack stack) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.level != null) {
            mc.gameRenderer.displayItemActivation(stack);
        }
    }
}
