package net.paddedshaman.blazingbamboo.fabric;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.BlazingBambooBlock;
import net.paddedshaman.blazingbamboo.block.BlazingBambooSapling;

public final class BlazingBambooFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // Run our common setup.
        BlazingBamboo.init();

        BuiltInRegistries.ITEM.addAlias(BlazingBamboo.id("blazing_bamboo_item"), BlazingBamboo.id("blazing_bamboo"));

        StrippableBlockRegistry.register(BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get());

        // Add Simple Copper Pipes compatibility if it is loaded
        if (FabricLoader.getInstance().isModLoaded("copper_pipe")) {
            net.lunade.copper.block.entity.leaking.LeakingPipeDripBehaviors.register(BBBlocks.BLAZING_BAMBOO.get(), (lava, level, blockPos, blockState) -> {
                if (lava) return;
                if (blockState.getBlock() instanceof BlazingBambooBlock bambooBlock) {
                    bambooBlock.handlePrecipitation(blockState, level, blockPos, Precipitation.RAIN);
                } else if (blockState.getBlock() instanceof BlazingBambooSapling bambooSapling) {
                    bambooSapling.handlePrecipitation(blockState, level, blockPos, Precipitation.RAIN);
                }
            });          
        }
    }
}
