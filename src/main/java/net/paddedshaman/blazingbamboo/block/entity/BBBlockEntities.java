package net.paddedshaman.blazingbamboo.block.entity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBBlockEntities {
    public static final BlockEntityType<BBSignBlockEntity> MOD_SIGN = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(BlazingBamboo.MOD_ID, "mod_sign"),
            BlockEntityType.Builder.of(BBSignBlockEntity::new, BBBlocks.BLAZING_BAMBOO_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_SIGN).build(null));
    public static final BlockEntityType<BBHangingSignBlockEntity> MOD_HANGING_SIGN = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(BlazingBamboo.MOD_ID, "mod_hanging_sign"),
            BlockEntityType.Builder.of(BBHangingSignBlockEntity::new, BBBlocks.BLAZING_BAMBOO_HANGING_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN).build(null));

    public static void registerBlockEntities() {}
}
