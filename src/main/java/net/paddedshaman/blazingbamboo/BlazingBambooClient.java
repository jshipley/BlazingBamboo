package net.paddedshaman.blazingbamboo;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.entity.BBRaftRenderer;

public class BlazingBambooClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.DEAD_BAMBOO, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.DEAD_BAMBOO_SAPLING, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.POTTED_BLAZING_BAMBOO, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO_DOOR, RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO_TRAPDOOR, RenderType.cutout());

        BlockEntityRenderers.register(BBBlockEntities.MOD_SIGN, SignRenderer::new);
        BlockEntityRenderers.register(BBBlockEntities.MOD_HANGING_SIGN, HangingSignRenderer::new);

        EntityRenderers.register(BBEntities.BB_RAFT, context -> new BBRaftRenderer(context, false));
        EntityRenderers.register(BBEntities.BB_CHEST_RAFT, context -> new BBRaftRenderer(context, true));
        EntityModelLayerRegistry.registerModelLayer(BBRaftRenderer.BLAZING_BAMBOO_RAFT_LAYER, RaftModel::createBodyModel);
        EntityModelLayerRegistry.registerModelLayer(BBRaftRenderer.BLAZING_BAMBOO_CHEST_RAFT_LAYER, ChestRaftModel::createBodyModel);
    }
}
