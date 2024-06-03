package net.paddedshaman.blazingbamboo.entity;

import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.ChestRaftModel;
import net.minecraft.client.model.RaftModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

import java.util.Map;

public class BBRaftRenderer extends BoatRenderer {
    public static final ModelLayerLocation BLAZING_BAMBOO_RAFT_LAYER = new ModelLayerLocation(
            new ResourceLocation(BlazingBamboo.MOD_ID, "raft/blazing_bamboo"), "main");
    public static final ModelLayerLocation BLAZING_BAMBOO_CHEST_RAFT_LAYER = new ModelLayerLocation(
            new ResourceLocation(BlazingBamboo.MOD_ID, "chest_raft/blazing_bamboo"), "main");
    private final ResourceLocation texture;
    public BBRaftRenderer(EntityRendererProvider.Context context, boolean pChestBoat) {
        super(context, pChestBoat);
        if (pChestBoat) {
            this.texture = new ResourceLocation(BlazingBamboo.MOD_ID, "textures/entity/chest_raft/blazing_bamboo.png");
            boatResources = Map.of(Boat.Type.BAMBOO, Pair.of(texture, new ChestRaftModel(context.bakeLayer(BLAZING_BAMBOO_CHEST_RAFT_LAYER))));
        } else {
            this.texture = new ResourceLocation(BlazingBamboo.MOD_ID, "textures/entity/raft/blazing_bamboo.png");
            boatResources = Map.of(Boat.Type.BAMBOO, Pair.of(texture, new RaftModel(context.bakeLayer(BLAZING_BAMBOO_RAFT_LAYER))));
        }
    }
    @Override
    public ResourceLocation getTextureLocation(Boat boat) {
        return texture;
    }
}
