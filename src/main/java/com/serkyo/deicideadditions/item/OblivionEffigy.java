//package com.serkyo.deicideadditions.item;
//
//import net.minecraft.ChatFormatting;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.gui.screens.Screen;
//import net.minecraft.core.particles.ParticleTypes;
//import net.minecraft.network.chat.Component;
//import net.minecraft.resources.ResourceLocation;
//import net.minecraft.server.level.ServerLevel;
//import net.minecraft.server.level.ServerPlayer;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.sounds.SoundSource;
//import net.minecraft.world.InteractionHand;
//import net.minecraft.world.InteractionResultHolder;
//import net.minecraft.world.effect.MobEffectInstance;
//import net.minecraft.world.effect.MobEffects;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.item.Item;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Rarity;
//import net.minecraft.world.item.TooltipFlag;
//import net.minecraft.world.level.Level;
//import net.puffish.skillsmod.api.Category;
//import net.puffish.skillsmod.api.SkillsAPI;
//import org.jetbrains.annotations.Nullable;
//
//import java.util.List;
//import java.util.Optional;
//
//public class OblivionEffigy extends Item {
//    public OblivionEffigy(Properties pProperties) {
//        super(pProperties
//                .stacksTo(1)
//                .rarity(Rarity.EPIC));
//    }
//
//    @Override
//    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
//        ItemStack stack = player.getItemInHand(usedHand);
//
//        level.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1);
//        if (!level.isClientSide) {
//            ResourceLocation skillTreeResourceLocation = ResourceLocation.fromNamespaceAndPath("deicideadditions", "epic_fight");
//            Optional<Category> skillTreeOptional = SkillsAPI.getCategory(skillTreeResourceLocation);
//
//            if (skillTreeOptional.isPresent()) {
//                Category skilltree = skillTreeOptional.get();
//                stack.shrink(1);
//                skilltree.resetSkills((ServerPlayer) player);
//                skilltree.setPoints((ServerPlayer) player, skillTreeResourceLocation, skilltree.getPoints((ServerPlayer) player, skillTreeResourceLocation) / 8);
//                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0, false, true));
//                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 100, 0, false, true));
//                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0, false, true));
//
//                if (level instanceof ServerLevel serverLevel) {
//                    serverLevel.sendParticles(ParticleTypes.TOTEM_OF_UNDYING, player.getX(), player.getY() + 1, player.getZ(), 50, 0.5, 1, 0.5, 0.1);
//                }
//            }
//        }
//        else {
//            triggerTotemAnimation(stack);
//        }
//
//        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
//    }
//
//    private void triggerTotemAnimation(ItemStack stack) {
//        Minecraft mc = Minecraft.getInstance();
//
//        if (mc.level != null) {
//            mc.gameRenderer.displayItemActivation(stack);
//        }
//    }
//
//    @Override
//    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag isAdvanced) {
//        tooltip.add(Component.translatable("item.deicideadditions.oblivion_effigy.description").withStyle(ChatFormatting.GRAY));
//        tooltip.add(Component.empty());
//        if (Screen.hasShiftDown()) {
//            tooltip.add(Component.translatable( "item.deicideadditions.oblivion_effigy.lore").withStyle(ChatFormatting.BLUE, ChatFormatting.ITALIC));
//        }
//        else {
//            tooltip.add(Component.translatable("item.deicideadditions.showlore").withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
//        }
//    }
//}
