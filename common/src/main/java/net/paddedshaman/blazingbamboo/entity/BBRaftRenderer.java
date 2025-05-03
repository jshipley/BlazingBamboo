package net.paddedshaman.blazingbamboo.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBRaftRenderer extends RaftRenderer {
    public static final ModelLayerLocation BLAZING_BAMBOO_RAFT_LAYER = new ModelLayerLocation(
            BlazingBamboo.id("raft/blazing_bamboo"), "main");
    public static final ModelLayerLocation BLAZING_BAMBOO_CHEST_RAFT_LAYER = new ModelLayerLocation(
            BlazingBamboo.id("chest_raft/blazing_bamboo"), "main");

    public BBRaftRenderer(EntityRendererProvider.Context context, boolean pChestBoat) {
        super(context, pChestBoat ? BLAZING_BAMBOO_CHEST_RAFT_LAYER : BLAZING_BAMBOO_RAFT_LAYER);
    }
}
