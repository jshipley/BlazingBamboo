package net.paddedshaman.blazingbamboo.client.fabric;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.paddedshaman.blazingbamboo.BlazingBambooClient;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public final class BlazingBambooFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlazingBambooClient.onInitializeClient();

        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.DEAD_BAMBOO.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.DEAD_BAMBOO_SAPLING.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.POTTED_BLAZING_BAMBOO.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO_DOOR.get(), RenderType.cutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get(), RenderType.cutout());
    }
}
