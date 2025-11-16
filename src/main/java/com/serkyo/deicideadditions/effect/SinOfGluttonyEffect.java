package com.serkyo.deicideadditions.effect;

import com.serkyo.deicideadditions.DeicideAdditions;
import com.serkyo.deicideadditions.capability.GluttonyProvider;
import com.serkyo.deicideadditions.core.DeicideEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import sfiomn.legendarysurvivaloverhaul.common.capabilities.thirst.ThirstProvider;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SinOfGluttonyEffect extends DeicideMobEffect {
    public SinOfGluttonyEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
        this.addAttributeModifier(
                Attributes.MOVEMENT_SPEED,
                "bfb168e2-60e2-45df-94b8-a92cc11bc83b",
                -0.5D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
        this.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                "013b0043-5b26-4c0c-a4ae-5acce19f3937",
                -0.5D,
                AttributeModifier.Operation.MULTIPLY_TOTAL
        );
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int pAmplifier) {
        if (entity instanceof Player player && !player.level().isClientSide) {
            player.setSprinting(false);
        }
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }

    @SubscribeEvent
    public static void onPlayerJump(LivingEvent.LivingJumpEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (player.hasEffect(DeicideEffects.SIN_OF_GLUTTONY_EFFECT.get())) {
                player.setDeltaMovement(player.getDeltaMovement().multiply(1, 0.75, 1));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && player.hasEffect(DeicideEffects.SIN_OF_GLUTTONY_EFFECT.get())) {
            player.setSprinting(false);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        // Cancel the event if it's water or an eatable block
    }

    @SubscribeEvent
    public static void onPlayerStartEating(LivingEntityUseItemEvent.Start event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack item = event.getItem();
            boolean edibleItem = item.isEdible();
            boolean drinkableItem = item.getUseAnimation() == UseAnim.DRINK;

            if (edibleItem || drinkableItem) {
                if (player.hasEffect(DeicideEffects.SIN_OF_GLUTTONY_EFFECT.get())) {
                    event.setCanceled(true);
                    player.displayClientMessage(Component.translatable("event.deicideadditions.cancel_eat"), true);
                    DeicideAdditions.LOGGER.debug("Prevented {} from consuming food or drink since they are afflicted by Sin of Gluttony", player.getName().getString());
                    return;
                }
                FoodData foodData = player.getFoodData();

                player.getCapability(ThirstProvider.THIRST_CAPABILITY).ifPresent(thirstCapability -> {
                    boolean foodFull = foodData.getFoodLevel() >= 20 && foodData.getSaturationLevel() > 0;
                    boolean thirstFull = thirstCapability.getHydrationLevel() >= 20 && thirstCapability.getSaturationLevel() > 0;

                    player.getCapability(GluttonyProvider.GLUTTONY).ifPresent(gluttony -> {
                        gluttony.setWasFullBeforeConsume((foodFull && edibleItem) || (thirstFull && drinkableItem));
                    });
                });
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerFinishEating(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player && !player.level().isClientSide) {
            ItemStack item = event.getItem();
            boolean edibleItem = item.isEdible();
            boolean drinkableItem = item.getUseAnimation() == UseAnim.DRINK;

            if (edibleItem || drinkableItem) {

                player.getCapability(GluttonyProvider.GLUTTONY).ifPresent(gluttony -> {
                    if (gluttony.wasFullBeforeConsume()) {
                        gluttony.incrementOvereatingCount();
                        int overeatingCount = gluttony.getOvereatingCount();
                        DeicideAdditions.LOGGER.debug("Player {} ate while full (gluttony count = {})", player.getName().getString(), overeatingCount);

                        if (overeatingCount >= 3) {
                            player.addEffect(new MobEffectInstance(DeicideEffects.SIN_OF_GLUTTONY_EFFECT.get(), 1200, 0, false, false, true));
                            DeicideAdditions.LOGGER.info("Applied gluttony effect to {} since the gluttony count passed the threshold", player.getName().getString());
                            player.displayClientMessage(Component.translatable("event.deicideadditions.overeating"), true);
                            gluttony.resetOvereatingCount();
                        }
                    }
                });
            }
        }
    }
}
