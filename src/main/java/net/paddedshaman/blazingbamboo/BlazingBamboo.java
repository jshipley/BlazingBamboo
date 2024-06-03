package net.paddedshaman.blazingbamboo;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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
public class BlazingBamboo {
    public static final String MOD_ID = "blazingbamboo";

    public BlazingBamboo() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        BBBlocks.BLOCKS.register(modEventBus);

        BBCreativeModeTabs.register(modEventBus);

        BBItems.ITEMS.register(modEventBus);
        BBFeatures.FEATURES.register(modEventBus);
        BBEntities.register(modEventBus);
        BBBlockEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(FurnaceFuelBurnTimeEventHandler.instance);
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BBBlocks.BLAZING_BAMBOO.getId(), BBBlocks.POTTED_BLAZING_BAMBOO);
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(BBWoodTypes.BLAZING_BAMBOO);
            EntityRenderers.register(BBEntities.BB_RAFT.get(), pContext -> new BBRaftRenderer(pContext, false));
            EntityRenderers.register(BBEntities.BB_CHEST_RAFT.get(), pContext -> new BBRaftRenderer(pContext, true));
        }
    }
}