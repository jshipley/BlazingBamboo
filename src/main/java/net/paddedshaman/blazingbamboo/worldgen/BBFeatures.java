package net.paddedshaman.blazingbamboo.worldgen;

import java.util.function.Supplier;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(BuiltInRegistries.FEATURE, BlazingBamboo.MOD_ID);

    public static final Supplier<Feature<RandomPatchConfiguration>> BLAZING_BAMBOO = FEATURES.register(
            "blazing_bamboo", () -> new BlazingBambooFeature(RandomPatchConfiguration.CODEC));
}
