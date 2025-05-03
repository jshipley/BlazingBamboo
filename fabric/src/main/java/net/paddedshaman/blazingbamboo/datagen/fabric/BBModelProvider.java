package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiPartGenerator;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.item.BBItems;

public class BBModelProvider extends FabricModelProvider {

    public BBModelProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        for (var family : BBDataGenerator.BLAZING_BLOCK_FAMILIES) {
            blockStateModelGenerator.family(family.getBaseBlock()).generateFor(family);
        }

        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        blockStateModelGenerator.createRotatedPillarWithHorizontalVariant(BBBlocks.BLAZING_BRICKS_CHISELED.get(), TexturedModel.COLUMN, TexturedModel.COLUMN_HORIZONTAL);
        blockStateModelGenerator.createHangingSign(BBBlocks.BLAZING_BAMBOO_PLANKS.get(), BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN.get());

        blockStateModelGenerator.createNonTemplateModelBlock(BBBlocks.POTTED_BLAZING_BAMBOO.get());

        blockStateModelGenerator.createCrossBlockWithDefaultItem(
            BBBlocks.BLAZING_BAMBOO_SAPLING.get(),
            net.minecraft.client.data.models.BlockModelGenerators.PlantType.TINTED,
            TextureMapping.cross(TextureMapping.getBlockTexture(BBBlocks.BLAZING_BAMBOO.get(), "_sapling")));
        blockStateModelGenerator.blockStateOutput.accept(
            MultiPartGenerator.multiPart(BBBlocks.BLAZING_BAMBOO.get())
                .with(BlockModelGenerators.condition().term(BlockStateProperties.AGE_1, 0), createBlazingBambooModels(BBBlocks.BLAZING_BAMBOO.get(), 0))
                .with(BlockModelGenerators.condition().term(BlockStateProperties.AGE_1, 1), createBlazingBambooModels(BBBlocks.BLAZING_BAMBOO.get(), 1))
                .with(
                    BlockModelGenerators.condition().term(BlockStateProperties.BAMBOO_LEAVES, BambooLeaves.SMALL),
                    BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(BBBlocks.BLAZING_BAMBOO.get(),
                    "_small_leaves")))
                .with(
                    BlockModelGenerators.condition().term(BlockStateProperties.BAMBOO_LEAVES, BambooLeaves.LARGE),
                    BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(BBBlocks.BLAZING_BAMBOO.get(),
                    "_large_leaves"))));
        blockStateModelGenerator.createCrossBlockWithDefaultItem(
            BBBlocks.DEAD_BAMBOO_SAPLING.get(),
            net.minecraft.client.data.models.BlockModelGenerators.PlantType.TINTED,
            TextureMapping.cross(TextureMapping.getBlockTexture(BBBlocks.DEAD_BAMBOO.get(), "_sapling")));
        blockStateModelGenerator.blockStateOutput.accept(
            MultiPartGenerator.multiPart(BBBlocks.DEAD_BAMBOO.get())
                .with(BlockModelGenerators.condition().term(BlockStateProperties.AGE_1, 0), createBlazingBambooModels(BBBlocks.DEAD_BAMBOO.get(), 0))
                .with(BlockModelGenerators.condition().term(BlockStateProperties.AGE_1, 1), createBlazingBambooModels(BBBlocks.DEAD_BAMBOO.get(), 1))
                .with(
                    BlockModelGenerators.condition().term(BlockStateProperties.BAMBOO_LEAVES, BambooLeaves.SMALL),
                    BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(BBBlocks.DEAD_BAMBOO.get(),
                    "_small_leaves")))
                .with(
                    BlockModelGenerators.condition().term(BlockStateProperties.BAMBOO_LEAVES, BambooLeaves.LARGE),
                    BlockModelGenerators.plainVariant(ModelLocationUtils.getModelLocation(BBBlocks.DEAD_BAMBOO.get(),
                    "_large_leaves"))));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(BBItems.BLAZING_BAMBOO_RAFT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BBItems.BLAZING_BAMBOO_CHEST_RAFT.get(), ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(BBBlocks.BLAZING_BAMBOO.get().asItem(), ModelTemplates.FLAT_ITEM);
        
    }

    // same as BlockModelGenerators.createBambooModels(), but uses blazing bamboo instead of bamboo
    public static MultiVariant createBlazingBambooModels(Block modelBlock, int i) {
        String string = "_age" + i;
        return new MultiVariant(WeightedList.of((List<Weighted<Variant>>)IntStream.range(1, 5).mapToObj((ix) -> {
            return new Weighted<Variant>(BlockModelGenerators.plainModel(ModelLocationUtils.getModelLocation(modelBlock, "" + ix + string)), 1);
        }).collect(Collectors.toList())));
    }
}
