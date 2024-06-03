package net.paddedshaman.blazingbamboo.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBTags {
    public static class Blocks {
        public static final TagKey<Block> BLAZING_BAMBOO_PLANTABLE_ON = tag("blazing_bamboo_plantable_on");
        public static final TagKey<Block> BLAZING_BAMBOO_FAST_GROWTH = tag("blazing_bamboo_fast_growth");
        public static final TagKey<Block> BLAZING_BAMBOO_MID_GROWTH = tag("blazing_bamboo_mid_growth");
        public static final TagKey<Block> BLAZING_BAMBOO_SLOW_GROWTH = tag("blazing_bamboo_slow_growth");
        public static final TagKey<Block> BLAZING_BAMBOO_INVASIVE_GROWTH = tag("blazing_bamboo_invasive_growth");
        public static final TagKey<Block> BLAZING_BAMBOO_INVASIVE_REPLACEABLES = tag("blazing_bamboo_invasive_replaceables");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(BlazingBamboo.MOD_ID, name));
        }
    }
}
