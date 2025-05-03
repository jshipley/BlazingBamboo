package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.util.List;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.minecraft.data.BlockFamily;

public class BBDataGenerator implements DataGeneratorEntrypoint {

    // used for model and recipe generation
    public static final BlockFamily BLAZING_PLANK_FAMILY = new BlockFamily.Builder(BBBlocks.BLAZING_BAMBOO_PLANKS.get())
        .button(BBBlocks.BLAZING_BAMBOO_BUTTON.get())
        .customFence(BBBlocks.BLAZING_BAMBOO_FENCE.get())
        .customFenceGate(BBBlocks.BLAZING_BAMBOO_FENCE_GATE.get())
        .door(BBBlocks.BLAZING_BAMBOO_DOOR.get())
        .mosaic(BBBlocks.BLAZING_BAMBOO_MOSAIC.get())
        .pressurePlate(BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE.get())
        .sign(BBBlocks.BLAZING_BAMBOO_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_SIGN.get())
        .slab(BBBlocks.BLAZING_BAMBOO_SLAB.get())
        .stairs(BBBlocks.BLAZING_BAMBOO_STAIRS.get())
        .trapdoor(BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get())
        .getFamily();
    public static final BlockFamily BLAZING_MOSAIC_FAMILY = new BlockFamily.Builder(BBBlocks.BLAZING_BAMBOO_MOSAIC.get())
        .slab(BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get())
        .stairs(BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get())
        .getFamily();
    public static final BlockFamily BLAZING_STONE_FAMILY = new BlockFamily.Builder(BBBlocks.BLAZING_STONE.get())
        .button(BBBlocks.BLAZING_STONE_BUTTON.get())
        .pressurePlate(BBBlocks.BLAZING_STONE_PRESSURE_PLATE.get())
        .slab(BBBlocks.BLAZING_STONE_SLAB.get())
        .stairs(BBBlocks.BLAZING_STONE_STAIRS.get())
        .getFamily();
    public static final BlockFamily BLAZING_BRICK_FAMILY = new BlockFamily.Builder(BBBlocks.BLAZING_BRICKS.get())
        .slab(BBBlocks.BLAZING_BRICK_SLAB.get())
        .stairs(BBBlocks.BLAZING_BRICK_STAIRS.get())
        .wall(BBBlocks.BLAZING_BRICK_WALL.get())
        .getFamily();
    public static final List<BlockFamily> BLAZING_BLOCK_FAMILIES = List.of(BLAZING_PLANK_FAMILY, BLAZING_MOSAIC_FAMILY, BLAZING_STONE_FAMILY, BLAZING_BRICK_FAMILY);

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(BBBiomeTagProvider::new);
        pack.addProvider(BBBlockTagProvider::new);
        pack.addProvider(BBEntityTypeTagProvider::new);
        pack.addProvider(BBItemTagProvider::new);
        pack.addProvider(BBLootTableProvider::new);
        pack.addProvider(BBModelProvider::new);
        pack.addProvider(BBRecipeProvider::new);
    }
}
