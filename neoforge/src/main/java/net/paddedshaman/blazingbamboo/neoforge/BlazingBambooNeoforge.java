package net.paddedshaman.blazingbamboo.neoforge;

import java.util.Collections;
import java.util.HashMap;

import net.minecraft.client.model.RaftModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.AxeItem;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.BlazingBambooClient;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.config.BBConfig;
import net.paddedshaman.blazingbamboo.entity.BBRaftRenderer;

@Mod(BlazingBamboo.MOD_ID)
public final class BlazingBambooNeoforge {
    public BlazingBambooNeoforge(IEventBus modEventBus, ModContainer modContainer) {
        // Run our common setup.
        BlazingBamboo.init();
        modEventBus.addListener(this::commonSetup);

        DeferredRegister.create(BuiltInRegistries.ITEM, BlazingBamboo.MOD_ID).addAlias(BlazingBamboo.id("blazing_bamboo_item"), BlazingBamboo.id("blazing_bamboo"));

        ModLoadingContext.get().registerExtensionPoint(
            IConfigScreenFactory.class,
            () -> (client, parent) -> BBConfig.createConfig(parent));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
        AxeItem.STRIPPABLES.put(BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get());
        AxeItem.STRIPPABLES = Collections.unmodifiableMap(AxeItem.STRIPPABLES);
    }

    @SuppressWarnings("deprecation")
    @EventBusSubscriber(modid = BlazingBamboo.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            
            BlazingBambooClient.onInitializeClient();

            ItemBlockRenderTypes.setRenderLayer(BBBlocks.BLAZING_BAMBOO.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BBBlocks.BLAZING_BAMBOO_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BBBlocks.DEAD_BAMBOO.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BBBlocks.DEAD_BAMBOO_SAPLING.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BBBlocks.POTTED_BLAZING_BAMBOO.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BBBlocks.BLAZING_BAMBOO_DOOR.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get(), RenderType.cutout());
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(BBRaftRenderer.BLAZING_BAMBOO_RAFT_LAYER, RaftModel::createRaftModel);
            event.registerLayerDefinition(BBRaftRenderer.BLAZING_BAMBOO_CHEST_RAFT_LAYER, RaftModel::createChestRaftModel);
        }
    }
}
