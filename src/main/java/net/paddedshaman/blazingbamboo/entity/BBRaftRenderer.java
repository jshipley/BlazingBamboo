package net.paddedshaman.blazingbamboo.entity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.RaftRenderer;
import net.minecraft.resources.ResourceLocation;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBRaftRenderer extends RaftRenderer {
    private final ResourceLocation texture;

    public static final ModelLayerLocation BLAZING_BAMBOO_RAFT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "raft/blazing_bamboo"), "main");
    public static final ModelLayerLocation BLAZING_BAMBOO_CHEST_RAFT_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "chest_raft/blazing_bamboo"), "main");

    public BBRaftRenderer(EntityRendererProvider.Context context, boolean pChestBoat) {
        super(context, pChestBoat ? BLAZING_BAMBOO_CHEST_RAFT_LAYER : BLAZING_BAMBOO_RAFT_LAYER);
        if (pChestBoat) {
            this.texture = ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "textures/entity/chest_raft/blazing_bamboo.png");
        } else {
            this.texture = ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "textures/entity/raft/blazing_bamboo.png");
        }
    }
}
