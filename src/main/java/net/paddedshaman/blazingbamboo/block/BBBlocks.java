package net.paddedshaman.blazingbamboo.block;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.custom.*;

public class BBBlocks {
        public static final WoodType BLAZING_BAMBOO_TYPE = WoodType.register(new WoodType(BlazingBamboo.MOD_ID + ":blazing_bamboo", BlockSetType.BAMBOO));

        public static final Block BLAZING_BAMBOO = registerBlock("blazing_bamboo", BlazingBambooBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).forceSolidOn().randomTicks().instabreak().strength(1.0F, 6.0F).sound(SoundType.BAMBOO).lightLevel(pLightEmission -> 8).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
        public static final Block DEAD_BAMBOO = registerBlock("dead_bamboo", DeadBambooBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instabreak().strength(0.0F, 6.0F).sound(SoundType.HANGING_ROOTS).noOcclusion().dynamicShape().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
        
        public static final Block BLAZING_BAMBOO_SAPLING = registerBlock("blazing_bamboo_sapling", BlazingBambooSapling::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).forceSolidOn().randomTicks().instabreak().noCollission().strength(0.0F, 6.0F).sound(SoundType.BAMBOO_SAPLING).lightLevel(pLightEmission -> 5).noOcclusion().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
        public static final Block DEAD_BAMBOO_SAPLING = registerBlock("dead_bamboo_sapling", DeadBambooSapling::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instabreak().noCollission().strength(0.0F, 6.0F).sound(SoundType.HANGING_ROOTS).noOcclusion().offsetType(BlockBehaviour.OffsetType.XZ).pushReaction(PushReaction.DESTROY));
        public static final Block POTTED_BLAZING_BAMBOO = registerBlock("potted_blazing_bamboo", (props) -> new BlazingFlowerPotBlock(BBBlocks.BLAZING_BAMBOO, props), BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_BAMBOO).strength(0.0F, 6.0F).lightLevel(pLightEmission -> 6).noOcclusion());

        public static final Block BLAZING_BAMBOO_BUNDLE = registerBlock("blazing_bamboo_bundle", BBRotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 6.0F).lightLevel(pLightEmission -> 10).sound(SoundType.BAMBOO_WOOD));
        public static final Block STRIPPED_BLAZING_BAMBOO_BUNDLE = registerBlock("stripped_blazing_bamboo_bundle", BBRotatedPillarBlock::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 6.0F).sound(SoundType.BAMBOO_WOOD));

        // Wood derivatives
        public static final Block BLAZING_BAMBOO_PLANKS = registerBlock("blazing_bamboo_planks", Block::new, BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS).strength(2.0F, 6.0F).sound(SoundType.BAMBOO_WOOD));
        public static final Block BLAZING_BAMBOO_SLAB = registerBlock("blazing_bamboo_slab", SlabBlock::new,BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS));
        public static final Block BLAZING_BAMBOO_STAIRS = registerBlock("blazing_bamboo_stairs", (props) -> new StairBlock(BBBlocks.BLAZING_BAMBOO_PLANKS.defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS));
        public static final Block BLAZING_BAMBOO_MOSAIC = registerBlock("blazing_bamboo_mosaic", Block::new, BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS));
        public static final Block BLAZING_BAMBOO_MOSAIC_SLAB = registerBlock("blazing_bamboo_mosaic_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS));
        public static final Block BLAZING_BAMBOO_MOSAIC_STAIRS = registerBlock("blazing_bamboo_mosaic_stairs", (props) -> new StairBlock(BBBlocks.BLAZING_BAMBOO_PLANKS.defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS));
        public static final Block BLAZING_BAMBOO_BUTTON = registerBlock("blazing_bamboo_button", (props) -> new ButtonBlock(BlockSetType.BAMBOO, 30, props), BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_BUTTON).strength(0.5F, 6.0F).sound(SoundType.BAMBOO_WOOD).noCollission());
        public static final Block BLAZING_BAMBOO_PRESSURE_PLATE = registerBlock("blazing_bamboo_pressure_plate", (props) -> new PressurePlateBlock(BlockSetType.BAMBOO, props), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).sound(SoundType.BAMBOO_WOOD).noCollission().strength(0.5F, 6.0F).pushReaction(PushReaction.DESTROY));
        public static final Block BLAZING_BAMBOO_FENCE = registerBlock("blazing_bamboo_fence", FenceBlock::new, BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS).forceSolidOn());
        public static final Block BLAZING_BAMBOO_FENCE_GATE = registerBlock("blazing_bamboo_fence_gate", (props) -> new FenceGateBlock(BLAZING_BAMBOO_TYPE, props), BlockBehaviour.Properties.ofFullCopy(BBBlocks.BLAZING_BAMBOO_PLANKS));
        public static final Block BLAZING_BAMBOO_DOOR = registerBlock("blazing_bamboo_door", (props) -> new DoorBlock(BlockSetType.BAMBOO, props), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BASS).strength(3.0F, 6.0F).noOcclusion().pushReaction(PushReaction.DESTROY));
        public static final Block BLAZING_BAMBOO_TRAPDOOR = registerBlock("blazing_bamboo_trapdoor", (props) -> new TrapDoorBlock(BlockSetType.BAMBOO, props), BlockBehaviour.Properties.ofFullCopy(Blocks.CRIMSON_TRAPDOOR).sound(SoundType.BAMBOO_WOOD).strength(3.0F, 6.0F));
        public static final Block BLAZING_BAMBOO_SIGN = registerBlock("blazing_bamboo_sign", (props) -> new BBStandingSignBlock(BLAZING_BAMBOO_TYPE, props), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F, 6.0F));
        public static final Block BLAZING_BAMBOO_WALL_SIGN = registerBlock("blazing_bamboo_wall_sign", (props) -> new BBWallSignBlock(BLAZING_BAMBOO_TYPE, props), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F, 6.0F));
        public static final Block BLAZING_BAMBOO_HANGING_SIGN = registerBlock("blazing_bamboo_hanging_sign", (props) -> new BBHangingSignBlock(BLAZING_BAMBOO_TYPE, props), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F, 6.0F));
        public static final Block BLAZING_BAMBOO_WALL_HANGING_SIGN = registerBlock("blazing_bamboo_wall_hanging_sign", (props) -> new BBWallHangingSignBlock(BLAZING_BAMBOO_TYPE, props), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).forceSolidOn().instrument(NoteBlockInstrument.BASS).noCollission().strength(1.0F, 6.0F));

        // Stone derivatives
        public static final Block BLAZING_STONE = registerBlock("blazing_stone", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE));
        public static final Block BLAZING_STONE_SLAB = registerBlock("blazing_stone_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_SLAB).sound(SoundType.STONE));
        public static final Block BLAZING_STONE_STAIRS = registerBlock("blazing_stone_stairs", (props) -> new StairBlock(BBBlocks.BLAZING_STONE.defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_STAIRS).sound(SoundType.STONE));
        public static final Block BLAZING_STONE_BUTTON = registerBlock("blazing_stone_button", (props) -> new ButtonBlock(BlockSetType.STONE, 20, props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BUTTON).sound(SoundType.STONE).noCollission());
        public static final Block BLAZING_STONE_PRESSURE_PLATE = registerBlock("blazing_stone_pressure_plate", (props) -> new PressurePlateBlock(BlockSetType.STONE, props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).sound(SoundType.STONE).noCollission());
        public static final Block BLAZING_BRICKS = registerBlock("blazing_bricks", Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS).sound(SoundType.NETHER_BRICKS));
        public static final Block BLAZING_BRICK_SLAB = registerBlock("blazing_brick_slab", SlabBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB).sound(SoundType.NETHER_BRICKS));
        public static final Block BLAZING_BRICK_STAIRS = registerBlock("blazing_brick_stairs", (props) -> new StairBlock(BBBlocks.BLAZING_BRICKS.defaultBlockState(), props), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS).sound(SoundType.NETHER_BRICKS));
        public static final Block BLAZING_BRICK_WALL = registerBlock("blazing_brick_wall", WallBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_WALL).sound(SoundType.NETHER_BRICKS));
        public static final Block BLAZING_BRICKS_CHISELED = registerBlock("blazing_bricks_chiseled", RotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CHISELED_STONE_BRICKS).sound(SoundType.NETHER_BRICKS));

        private static Block registerBlock(String name, Function<Block.Properties, Block> constructor, Block.Properties properties) {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name);
                ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, id);

                return Registry.register(BuiltInRegistries.BLOCK, key, constructor.apply(properties.setId(key)));
        }

        public static void registerBlocks() {
        }
}
