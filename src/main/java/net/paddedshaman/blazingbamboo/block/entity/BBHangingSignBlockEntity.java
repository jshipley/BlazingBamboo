package net.paddedshaman.blazingbamboo.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BBHangingSignBlockEntity extends SignBlockEntity {
    public BBHangingSignBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(BBBlockEntities.MOD_HANGING_SIGN.get(), pPos, pBlockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BBBlockEntities.MOD_HANGING_SIGN.get();
    }
}
