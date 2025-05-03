package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BBBiomeTagProvider extends FabricTagProvider<Biome> {

    public BBBiomeTagProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, Registries.BIOME, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        getOrCreateTagBuilder(BBTags.Biomes.HAS_FEATURE_BLAZING_BAMBOO)
            .add(Biomes.CRIMSON_FOREST);
    }
}
