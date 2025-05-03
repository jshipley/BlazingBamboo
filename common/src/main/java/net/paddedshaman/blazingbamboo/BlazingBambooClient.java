package net.paddedshaman.blazingbamboo;

import dev.architectury.registry.client.level.entity.EntityModelLayerRegistry;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.entity.BBRaftRenderer;

public class BlazingBambooClient {
    public static void onInitializeClient() {
        BlockEntityRenderers.register(BBBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        BlockEntityRenderers.register(BBBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);

        EntityRenderers.register(BBEntities.BB_RAFT.get(), context -> new BBRaftRenderer(context, false));
        EntityRenderers.register(BBEntities.BB_CHEST_RAFT.get(), context -> new BBRaftRenderer(context, true));

        // not working in neoforge?
        EntityModelLayerRegistry.register(BBRaftRenderer.BLAZING_BAMBOO_RAFT_LAYER, RaftModel::createRaftModel);
        EntityModelLayerRegistry.register(BBRaftRenderer.BLAZING_BAMBOO_CHEST_RAFT_LAYER, RaftModel::createChestRaftModel);
    }
}
