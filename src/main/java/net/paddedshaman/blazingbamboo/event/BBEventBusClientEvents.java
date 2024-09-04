package net.paddedshaman.blazingbamboo.event;

import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBRaftRenderer;

@EventBusSubscriber(modid = BlazingBamboo.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BBEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BBRaftRenderer.BLAZING_BAMBOO_RAFT_LAYER, RaftModel::createBodyModel);
        event.registerLayerDefinition(BBRaftRenderer.BLAZING_BAMBOO_CHEST_RAFT_LAYER, ChestRaftModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(BBBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(BBBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
