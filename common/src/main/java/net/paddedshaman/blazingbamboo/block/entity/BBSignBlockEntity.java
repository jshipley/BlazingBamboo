package net.paddedshaman.blazingbamboo.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BBSignBlockEntity extends SignBlockEntity {
    public BBSignBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BBBlockEntities.MOD_SIGN.get(), blockPos, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return BBBlockEntities.MOD_SIGN.get();
    }
}
