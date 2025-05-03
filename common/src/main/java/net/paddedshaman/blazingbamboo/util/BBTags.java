package net.paddedshaman.blazingbamboo.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBTags {
    public static class Blocks {
        public static final TagKey<Block> BLAZING_BAMBOO_PLANTABLE_ON = tag("blazing_bamboo_plantable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, BlazingBamboo.id(name));
        }
    }
    public static class Items {
        public static final TagKey<Item> BLAZING_BAMBOO_BLOCKS = tag("blazing_bamboo_blocks");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, BlazingBamboo.id(name));
        }
    }
    public static class Biomes {
        public static final TagKey<Biome> HAS_FEATURE_BLAZING_BAMBOO = tag("has_feature/blazing_bamboo");

        private static TagKey<Biome> tag(String name) {
            return TagKey.create(Registries.BIOME, BlazingBamboo.id(name));
        }
    }
}
