package net.paddedshaman.blazingbamboo.worldgen;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, BlazingBamboo.MOD_ID);

    public static final RegistryObject<Feature<RandomPatchConfiguration>> BLAZING_BAMBOO = FEATURES.register(
            "blazing_bamboo", () -> new BlazingBambooFeature(RandomPatchConfiguration.CODEC));
}
