package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.paddedshaman.blazingbamboo.entity.BBEntities;

public class BBEntityTypeTagProvider extends FabricTagProvider<EntityType<?>> {

    public BBEntityTypeTagProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, Registries.ENTITY_TYPE, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        getOrCreateTagBuilder(EntityTypeTags.BOAT).add(BBEntities.BB_RAFT.get(), BBEntities.BB_CHEST_RAFT.get());
    }
}