package com.serkyo.deicideadditions.core;

import com.serkyo.deicideadditions.DeicideAdditions;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rosemarythyme.simplymore.registry.ModItemsRegistry;
import net.sweenus.simplyswords.registry.ItemsRegistry;

import java.util.List;

@Mod.EventBusSubscriber(modid = DeicideAdditions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class DeicideVillagerTrades {
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.WEAPONSMITH) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            int villagerLevel = 5;
            ItemStack itemsUsed = new ItemStack(ItemsRegistry.EMPOWERED_REMNANT.get(), 3);

            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.BRIMSTONE_CLAYMORE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.WATCHER_CLAYMORE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.STORMS_EDGE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.STORMBRINGER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.BRAMBLETHORN.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.WATCHING_WARGLAIVE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.TOXIC_LONGSWORD.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.EMBERBLADE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.HEARTHFLAME.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.SOULKEEPER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.TWISTED_BLADE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.SOULSTEALER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.SOULRENDER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.SOULPYRE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.FROSTFALL.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.MOLTEN_EDGE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.LIVYATAN.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.ICEWHISPER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.ARCANETHYST.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.THUNDERBRAND.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.MJOLNIR.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.SLUMBERING_LICHBLADE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.SHADOWSTING.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.DORMANT_RELIC.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.WHISPERWIND.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.EMBERLASH.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.WAXWEAVER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.HIVEHEART.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.STARS_EDGE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.WICKPIERCER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.TEMPEST.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.FLAMEWIND.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.RIBBONCLEAVER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.DECAYING_RELIC.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.ENIGMA.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ItemsRegistry.CAELESTIS.get()), 1, 0, 0f
            )));

            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.GREAT_SLITHER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.MOLTEN_FLARE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.GRANDFROST.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.MIMICRY.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.GLIMMERSTEP.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.THEBLOODHARVESTER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.MYRMEDGE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.BLACK_PEARL.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.THEVESSELBREACH.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.BLADEOFTHEGROTESQUE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.VIPERSCALL.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.TIMEKEEPER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.MATTERBANE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.SMOULDERING_RUIN.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.STASIS.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.TIDEBREAKER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.RUYI_JINGU_BANG.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.RUPTURED_IDOL.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.BOAS_FANG.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.EARTHSHATTER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.SOUL_FORESEER.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.SERPENTINE_VALOUR.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.LUSTROUS_MOXIE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.BRASSTURN.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.CINDERGORGE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.DEATHS_EYRIE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.PERFORISCUS.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.REVVENGINE.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.EXEDRILL.get()), 1, 0, 0f
            )));
            trades.get(villagerLevel).add(((pTrader, pRandom) -> new MerchantOffer(
                    itemsUsed, new ItemStack(ModItemsRegistry.CULTEREX.get()), 1, 0, 0f
            )));
        }
    }
}
