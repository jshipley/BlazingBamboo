package net.paddedshaman.blazingbamboo;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.item.BBCreativeModeTabs;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.worldgen.BlazingBambooFeature;

public class BlazingBamboo implements ModInitializer {
	public static final String MOD_ID = "blazingbamboo";

	@Override
	public void onInitialize() {
		BBCreativeModeTabs.registerCreativeModeTabs();
		BBItems.registerItems();
		BBBlocks.registerBlocks();
		BBBlockEntities.registerBlockEntities();
		BBEntities.registerEntities();

		FuelRegistry.INSTANCE.add(BBItems.BLAZING_BAMBOO_ITEM, 800);
		FuelRegistry.INSTANCE.add(BBBlocks.BLAZING_BAMBOO_BUNDLE, 8000);

		// Because of Minecraft's 'planks' and 'wooden_slabs' tags, these two items inherit a burn time that needs to be nullified manually.
		FuelRegistry.INSTANCE.add(BBBlocks.BLAZING_BAMBOO_PLANKS, 0);
		FuelRegistry.INSTANCE.add(BBBlocks.BLAZING_BAMBOO_SLAB, 0);

		StrippableBlockRegistry.register(BBBlocks.BLAZING_BAMBOO_BUNDLE, BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE);

		// The entirety of worldgen
		Registry.register(BuiltInRegistries.FEATURE, ResourceLocation.fromNamespaceAndPath(MOD_ID, "blazing_bamboo"),
				new BlazingBambooFeature(RandomPatchConfiguration.CODEC));
		BiomeModifications.addFeature(BiomeSelectors.includeByKey(Biomes.CRIMSON_FOREST), GenerationStep.Decoration.VEGETAL_DECORATION,
				ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, "blazing_bamboo_placed")));
	}
}