package net.paddedshaman.blazingbamboo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.paddedshaman.blazingbamboo.block.entity.BBHangingSignBlockEntity;

public class BBWallHangingSignBlock extends WallHangingSignBlock {
    public BBWallHangingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new BBHangingSignBlockEntity(blockPos, blockState);
    }
}
