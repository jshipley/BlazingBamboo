package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BBBlockTagProvider extends FabricTagProvider<Block> {

    public BBBlockTagProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, Registries.BLOCK, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        getOrCreateTagBuilder(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON)
            .add(BBBlocks.BLAZING_BAMBOO.get(), BBBlocks.BLAZING_BAMBOO_SAPLING.get(), BBBlocks.BLAZING_STONE.get())
            .add(Blocks.CALCITE, Blocks.BEDROCK)
            .forceAddTag(BlockTags.BASE_STONE_NETHER)
            .forceAddTag(BlockTags.SOUL_FIRE_BASE_BLOCKS)
            .forceAddTag(BlockTags.NYLIUM)
            .forceAddTag(BlockTags.BASE_STONE_OVERWORLD)
            .forceAddTag(BlockTags.STONE_BRICKS)
            .forceAddTag(BlockTags.SAND)
            .forceAddTag(BlockTags.TERRACOTTA)
            .forceAddTag(ConventionalBlockTags.COBBLESTONES)
            .forceAddTag(ConventionalBlockTags.END_STONES)
            .forceAddTag(ConventionalBlockTags.SANDSTONE_BLOCKS)
            .forceAddTag(ConventionalBlockTags.RED_SANDSTONE_BLOCKS)
            .forceAddTag(ConventionalBlockTags.GRAVELS)
            .forceAddTag(ConventionalBlockTags.OBSIDIANS);
        
        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS).add(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get());
        getOrCreateTagBuilder(BlockTags.DOORS).add(BBBlocks.BLAZING_BAMBOO_DOOR.get());
        getOrCreateTagBuilder(BlockTags.FENCE_GATES).add(BBBlocks.BLAZING_BAMBOO_FENCE_GATE.get());
        getOrCreateTagBuilder(BlockTags.FLOWER_POTS).add(BBBlocks.POTTED_BLAZING_BAMBOO.get());
        getOrCreateTagBuilder(BlockTags.HOGLIN_REPELLENTS).add(BBBlocks.BLAZING_BAMBOO.get(), BBBlocks.BLAZING_BAMBOO_SAPLING.get());
        getOrCreateTagBuilder(BlockTags.INFINIBURN_OVERWORLD).add(BBBlocks.BLAZING_STONE.get());
        getOrCreateTagBuilder(BlockTags.PLANKS).add(BBBlocks.BLAZING_BAMBOO_PLANKS.get());
        getOrCreateTagBuilder(BlockTags.PRESSURE_PLATES).add(BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE.get(), BBBlocks.BLAZING_STONE_PRESSURE_PLATE.get());
        getOrCreateTagBuilder(BlockTags.SLABS).add(BBBlocks.BLAZING_BRICK_SLAB.get(), BBBlocks.BLAZING_STONE_SLAB.get());
        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS).add(BBBlocks.BLAZING_BAMBOO_SIGN.get());
        getOrCreateTagBuilder(BlockTags.STAIRS).add(BBBlocks.BLAZING_STONE_STAIRS.get(), BBBlocks.BLAZING_BRICK_STAIRS.get());
        getOrCreateTagBuilder(BlockTags.STONE_BUTTONS).add(BBBlocks.BLAZING_STONE_BUTTON.get());
        getOrCreateTagBuilder(BlockTags.STONE_PRESSURE_PLATES).add(BBBlocks.BLAZING_STONE_PRESSURE_PLATE.get());
        getOrCreateTagBuilder(BlockTags.SWORD_INSTANTLY_MINES).add(BBBlocks.BLAZING_BAMBOO.get(), BBBlocks.BLAZING_BAMBOO_SAPLING.get());
        getOrCreateTagBuilder(BlockTags.TRAPDOORS).add(BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get());
        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS).add(BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN.get());
        getOrCreateTagBuilder(BlockTags.WALL_SIGNS).add(BBBlocks.BLAZING_BAMBOO_WALL_SIGN.get());
        getOrCreateTagBuilder(BlockTags.WALLS).add(BBBlocks.BLAZING_BRICK_WALL.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS).add(BBBlocks.BLAZING_BAMBOO_BUTTON.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES).add(BBBlocks.BLAZING_BAMBOO_FENCE.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES).add(BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS).add(BBBlocks.BLAZING_BAMBOO_SLAB.get(), BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get());
        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS).add(BBBlocks.BLAZING_BAMBOO_STAIRS.get(), BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get());

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE)
            .add(
                BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(),
                BBBlocks.BLAZING_BAMBOO_BUTTON.get(), BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE.get(),
                BBBlocks.BLAZING_BAMBOO_DOOR.get(), BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get(),
                BBBlocks.BLAZING_BAMBOO_FENCE.get(), BBBlocks.BLAZING_BAMBOO_FENCE_GATE.get(),
                BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN.get(),
                BBBlocks.BLAZING_BAMBOO_PLANKS.get(), BBBlocks.BLAZING_BAMBOO_MOSAIC.get(),
                BBBlocks.BLAZING_BAMBOO_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_SIGN.get(),
                BBBlocks.BLAZING_BAMBOO_SLAB.get(), BBBlocks.BLAZING_BAMBOO_SLAB.get(),
                BBBlocks.BLAZING_BAMBOO_STAIRS.get(), BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get(),
                BBBlocks.BLAZING_BAMBOO.get());
        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
            .add(
                BBBlocks.BLAZING_BAMBOO.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get(),
                BBBlocks.BLAZING_BRICK_WALL.get(),
                BBBlocks.BLAZING_STONE_BUTTON.get(), BBBlocks.BLAZING_STONE_PRESSURE_PLATE.get(),
                BBBlocks.BLAZING_STONE_SLAB.get(), BBBlocks.BLAZING_BRICK_SLAB.get(),
                BBBlocks.BLAZING_STONE_STAIRS.get(), BBBlocks.BLAZING_BRICK_STAIRS.get(),
                BBBlocks.BLAZING_STONE.get(), BBBlocks.BLAZING_BRICKS.get(), BBBlocks.BLAZING_BRICKS_CHISELED.get());
    }
}
