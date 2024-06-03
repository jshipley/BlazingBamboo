package net.paddedshaman.blazingbamboo.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BBSignBlockEntity extends SignBlockEntity {
    public BBSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BBBlockEntities.MOD_SIGN, pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BBBlockEntities.MOD_SIGN;
    }
}
