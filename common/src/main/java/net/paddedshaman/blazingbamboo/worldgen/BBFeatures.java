package net.paddedshaman.blazingbamboo.worldgen;

import dev.architectury.registry.level.biome.BiomeModifications;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BBFeatures {

    public static void registerFeatures() {
        BlazingBamboo.REGISTRY_MANAGER.get().get(Registries.FEATURE).register(
            BlazingBamboo.id("blazing_bamboo"),
            () -> new BlazingBambooFeature(RandomPatchConfiguration.CODEC));
        // Also using neoforge data to do this
        // see https://github.com/architectury/architectury-api/issues/480
        BiomeModifications.addProperties((biomeContext, mutable) -> {
            if (biomeContext.hasTag(BBTags.Biomes.HAS_FEATURE_BLAZING_BAMBOO)) {
                mutable.getGenerationProperties().addFeature(
                    GenerationStep.Decoration.VEGETAL_DECORATION,
                    ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_placed")));
            }
        });
    }
}