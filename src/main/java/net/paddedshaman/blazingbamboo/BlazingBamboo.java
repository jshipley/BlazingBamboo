package net.paddedshaman.blazingbamboo;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.entity.BBRaftRenderer;
import net.paddedshaman.blazingbamboo.event.FurnaceFuelBurnTimeEventHandler;
import net.paddedshaman.blazingbamboo.item.BBCreativeModeTabs;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.util.BBWoodTypes;
import net.paddedshaman.blazingbamboo.worldgen.BBFeatures;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(BlazingBamboo.MOD_ID)
@EventBusSubscriber(modid = BlazingBamboo.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BlazingBamboo {
    public static final String MOD_ID = "blazingbamboo";

    public BlazingBamboo(IEventBus modEventBus) {
        BBBlocks.BLOCKS.register(modEventBus);

        BBCreativeModeTabs.register(modEventBus);

        BBItems.ITEMS.register(modEventBus);
        BBFeatures.FEATURES.register(modEventBus);
        BBEntities.register(modEventBus);
        BBBlockEntities.register(modEventBus);
    }

    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
//        NeoForge.EVENT_BUS.register(FurnaceFuelBurnTimeEventHandler.instance);
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BuiltInRegistries.BLOCK.getKey(BBBlocks.BLAZING_BAMBOO.get()), BBBlocks.POTTED_BLAZING_BAMBOO);
        });
    }

    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(BBWoodTypes.BLAZING_BAMBOO);
            EntityRenderers.register(BBEntities.BB_RAFT.get(), pContext -> new BBRaftRenderer(pContext, false));
            EntityRenderers.register(BBEntities.BB_CHEST_RAFT.get(), pContext -> new BBRaftRenderer(pContext, true));
        }
    }
}