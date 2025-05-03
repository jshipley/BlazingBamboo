package net.paddedshaman.blazingbamboo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.block.BlazingBambooBlock;

public class BBRotatedPillarBlock extends RotatedPillarBlock {
    public BBRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        BlazingBambooBlock.blazeHurtEntity(level, entity, 2.0f);

        super.stepOn(level, blockPos, blockState, entity);
    }
}
