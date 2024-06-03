package net.paddedshaman.blazingbamboo.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBWoodTypes {
    public static final WoodType BLAZING_BAMBOO = WoodType.register(new WoodType(BlazingBamboo.MOD_ID + ":blazing_bamboo", BlockSetType.BAMBOO));
}
