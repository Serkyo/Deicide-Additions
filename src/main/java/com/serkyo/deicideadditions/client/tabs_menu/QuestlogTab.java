package com.serkyo.deicideadditions.client.tabs_menu;

import com.illusivesoulworks.diet.client.screen.DietScreen;
import lain.mods.cos.impl.client.gui.GuiCosArmorInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.infernalstudios.questlog.client.gui.screen.QuestlogScreen;
import sfiomn.legendarysurvivaloverhaul.client.screens.BodyHealthScreen;
import sfiomn.legendarytabs.LegendaryTabs;
import sfiomn.legendarytabs.api.tabs_menu.TabBase;
import sfiomn.legendarytabs.api.tabs_menu.TabsMenu;
import sfiomn.legendarytabs.utils.IntegrationUtils;
import top.theillusivec4.curios.client.gui.CuriosScreenV2;

public class QuestlogTab extends TabBase {
    private final ResourceLocation TAB_ICONS = ResourceLocation.fromNamespaceAndPath("legendarytabs", "textures/gui/tab_menu_buttons.png");
    private final int TAB_ICON_TEX_X = 27;
    private final int TAB_ICON_TEX_Y = 23;

    @Override
    public void openTargetScreen(Player player) {
        if (player.level().isClientSide) {
            Minecraft.getInstance().setScreen(new QuestlogScreen(Minecraft.getInstance().screen));
        }
    }

    @Override
    public boolean isEnabled(Player player) {
        return true;
    }

    @Override
    public void initTabOnScreens() {
        TabsMenu.addTabToScreen(this, InventoryScreen.class, (player) -> 176, (player) -> 166, 70);
        if (LegendaryTabs.legendarySurvivalOverhaulLoaded) {
            TabsMenu.addTabToScreen(this, BodyHealthScreen.class, (player) -> 176, (player) -> 183, 70);
        }

        if (LegendaryTabs.curiosLoaded) {
            TabsMenu.addTabToScreen(this, CuriosScreenV2.class, (player) -> 176, (player) -> 166, 70);
        }

        if (LegendaryTabs.cosmeticArmorLoaded) {
            TabsMenu.addTabToScreen(this, GuiCosArmorInventory.class, (player) -> 176, (player) -> 166, 70);
        }

        if (LegendaryTabs.dietLoaded) {
            TabsMenu.addTabToScreen(this, DietScreen.class, (player) -> 248, IntegrationUtils::getDietHeight, 70);
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int x, int y, boolean hover) {
        int texOffsetX = 0;
        if (hover) {
            texOffsetX = 54;
        }

        guiGraphics.blit(this.TAB_ICONS, x, y, 27 + texOffsetX, 23, 26, 22);
    }

    @Override
    public boolean isCurrentlyUsed(Screen screen) {
        return false;
    }

    @Override
    public Component getTooltip() {
        return Component.translatable("tooltip.legendarytabs.tab.ftb_quests.description");
    }
}
