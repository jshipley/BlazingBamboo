package net.paddedshaman.blazingbamboo.util;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBTags {
    public static class Blocks {
        public static final TagKey<Block> BLAZING_BAMBOO_PLANTABLE_ON = tag("blazing_bamboo_plantable_on");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(BuiltInRegistries.BLOCK.key(), new ResourceLocation(BlazingBamboo.MOD_ID, name));
        }
    }
}
