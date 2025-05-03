package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import dev.architectury.registry.registries.RegistrySupplier;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBLootTableProvider extends FabricBlockLootTableProvider {

    protected BBLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, registriesFuture);
    }

    @Override
    public void generate() {
        // Use reflection to get all of the BBBlocks, then generate loot tables for any of them that drop themselves
        Arrays.stream(BBBlocks.class.getDeclaredFields())
            .filter(field -> Modifier.isPublic(field.getModifiers())
                && Modifier.isStatic(field.getModifiers())
                && Modifier.isFinal(field.getModifiers()))
            .forEach(field -> {
                try {
                    if (field.get(null) instanceof RegistrySupplier registryBlock) {
                        if (((Block)registryBlock.get()).asItem() == Items.AIR) {
                            LOGGER.warn("Block {} does not have an item, not generating dropSelf loot table", (Block)registryBlock.get());
                        } else {
                            Block block = (Block)registryBlock.get();
                            if (block instanceof SlabBlock slabBlock) {
                                add(slabBlock, createSlabItemTable(slabBlock));
                            } else if (block instanceof DoorBlock doorBlock) {
                                add(doorBlock, createDoorTable(doorBlock));
                            } else {
                                dropSelf((Block)registryBlock.get());
                            }
                        }
                    }
                } catch (IllegalAccessException e) {
                    BlazingBamboo.LOGGER.error("Failed to generate block loot for {}: {}", field, e);
                    throw new RuntimeException("Unable to generate drop tables");
                }
            });
        
        // Take care of the rest of the blocks
        dropOther(BBBlocks.DEAD_BAMBOO.get(), Items.GUNPOWDER);
        dropOther(BBBlocks.DEAD_BAMBOO.get(), Items.GUNPOWDER);
        dropOther(BBBlocks.BLAZING_BAMBOO_SAPLING.get(), BBBlocks.BLAZING_BAMBOO.get());
        dropPottedContents(BBBlocks.POTTED_BLAZING_BAMBOO.get());
    }    
}
