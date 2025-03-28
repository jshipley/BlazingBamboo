package net.paddedshaman.blazingbamboo.item;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.lang3.function.TriFunction;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.entity.BBEntities;

public class BBItems {
        public static final Item BLAZING_BAMBOO_ITEM = registerBlockItem("blazing_bamboo_item", BlockItem::new, BBBlocks.BLAZING_BAMBOO, new Item.Properties().fireResistant());
        
        public static final Item BLAZING_BAMBOO_BUNDLE = registerBlockItem("blazing_bamboo_bundle", BlockItem::new, BBBlocks.BLAZING_BAMBOO_BUNDLE, new Item.Properties().fireResistant());
        public static final Item STRIPPED_BLAZING_BAMBOO_BUNDLE = registerBlockItem("stripped_blazing_bamboo_bundle", BlockItem::new, BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE, new Item.Properties().fireResistant());

        public static final Item BLAZING_BAMBOO_PLANKS = registerBlockItem("blazing_bamboo_planks", BlockItem::new, BBBlocks.BLAZING_BAMBOO_PLANKS, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_SLAB = registerBlockItem("blazing_bamboo_slab", BlockItem::new, BBBlocks.BLAZING_BAMBOO_SLAB, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_STAIRS = registerBlockItem("blazing_bamboo_stairs", BlockItem::new, BBBlocks.BLAZING_BAMBOO_STAIRS, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_MOSAIC = registerBlockItem("blazing_bamboo_mosaic", BlockItem::new, BBBlocks.BLAZING_BAMBOO_MOSAIC, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_MOSAIC_SLAB = registerBlockItem("blazing_bamboo_mosaic_slab", BlockItem::new, BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_MOSAIC_STAIRS = registerBlockItem("blazing_bamboo_mosaic_stairs", BlockItem::new, BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_BUTTON = registerBlockItem("blazing_bamboo_button", BlockItem::new, BBBlocks.BLAZING_BAMBOO_BUTTON, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_PRESSURE_PLATE = registerBlockItem("blazing_bamboo_pressure_plate", BlockItem::new, BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_FENCE = registerBlockItem("blazing_bamboo_fence", BlockItem::new, BBBlocks.BLAZING_BAMBOO_FENCE, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_FENCE_GATE = registerBlockItem("blazing_bamboo_fence_gate", BlockItem::new, BBBlocks.BLAZING_BAMBOO_FENCE_GATE, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_DOOR = registerBlockItem("blazing_bamboo_door", BlockItem::new, BBBlocks.BLAZING_BAMBOO_DOOR, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_TRAPDOOR = registerBlockItem("blazing_bamboo_trapdoor", BlockItem::new, BBBlocks.BLAZING_BAMBOO_TRAPDOOR, new Item.Properties().fireResistant());
        public static final Item BLAZING_BAMBOO_SIGN = registerSignBlockItem("blazing_bamboo_sign", SignItem::new, BBBlocks.BLAZING_BAMBOO_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_SIGN, new Item.Properties().fireResistant().stacksTo(16));
        public static final Item BLAZING_BAMBOO_HANGING_SIGN = registerSignBlockItem("blazing_bamboo_hanging_sign", HangingSignItem::new, BBBlocks.BLAZING_BAMBOO_HANGING_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN, new Item.Properties().fireResistant().stacksTo(16));

        public static final Item BLAZING_STONE = registerBlockItem("blazing_stone", BlockItem::new, BBBlocks.BLAZING_STONE, new Item.Properties().fireResistant());
        public static final Item BLAZING_STONE_SLAB = registerBlockItem("blazing_stone_slab", BlockItem::new, BBBlocks.BLAZING_STONE_SLAB, new Item.Properties().fireResistant());
        public static final Item BLAZING_STONE_STAIRS = registerBlockItem("blazing_stone_stairs", BlockItem::new, BBBlocks.BLAZING_STONE_STAIRS, new Item.Properties().fireResistant());
        public static final Item BLAZING_STONE_BUTTON = registerBlockItem("blazing_stone_button", BlockItem::new, BBBlocks.BLAZING_STONE_BUTTON, new Item.Properties().fireResistant());
        public static final Item BLAZING_STONE_PRESSURE_PLATE = registerBlockItem("blazing_stone_pressure_plate", BlockItem::new, BBBlocks.BLAZING_STONE_PRESSURE_PLATE, new Item.Properties().fireResistant());
        public static final Item BLAZING_BRICKS = registerBlockItem("blazing_bricks", BlockItem::new, BBBlocks.BLAZING_BRICKS, new Item.Properties().fireResistant());
        public static final Item BLAZING_BRICK_SLAB = registerBlockItem("blazing_brick_slab", BlockItem::new, BBBlocks.BLAZING_BRICK_SLAB, new Item.Properties().fireResistant());
        public static final Item BLAZING_BRICK_STAIRS = registerBlockItem("blazing_brick_stairs", BlockItem::new, BBBlocks.BLAZING_BRICK_STAIRS, new Item.Properties().fireResistant());
        public static final Item BLAZING_BRICK_WALL = registerBlockItem("blazing_brick_wall", BlockItem::new, BBBlocks.BLAZING_BRICK_WALL, new Item.Properties().fireResistant());
        public static final Item BLAZING_BRICKS_CHISELED = registerBlockItem("blazing_bricks_chiseled", BlockItem::new, BBBlocks.BLAZING_BRICKS_CHISELED, new Item.Properties().fireResistant());

        public static final Item BLAZING_BAMBOO_RAFT = registerItem("blazing_bamboo_raft", (props) -> new BoatItem(BBEntities.BB_RAFT, props), new Item.Properties().fireResistant().stacksTo(1));
        public static final Item BLAZING_BAMBOO_CHEST_RAFT = registerItem("blazing_bamboo_chest_raft", (props) -> new BoatItem(BBEntities.BB_CHEST_RAFT, props), new Item.Properties().fireResistant().stacksTo(1));

        public static Item registerBlockItem(String name, BiFunction<Block, Item.Properties, Item> constructor, Block block, Item.Properties properties) {
                return registerItem(name, (props) -> constructor.apply(block, props), properties.useBlockDescriptionPrefix());
        }

        public static Item registerSignBlockItem(String name, TriFunction<Block, Block, Item.Properties, Item> constructor, Block block, Block wallBlock, Item.Properties properties) {
                return registerItem(name, (props) -> constructor.apply(block, wallBlock, props), properties.useBlockDescriptionPrefix());
        }

        public static Item registerItem(String name, Function<Item.Properties, Item> constructor, Item.Properties properties) {
                ResourceLocation id = ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name);
                ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
                return Registry.register(BuiltInRegistries.ITEM, key, constructor.apply(properties.setId(key)));
        }

    public static void registerItems() {}
}
