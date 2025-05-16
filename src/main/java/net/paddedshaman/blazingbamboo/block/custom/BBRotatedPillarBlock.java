package net.paddedshaman.blazingbamboo.block.custom;

import static net.paddedshaman.blazingbamboo.block.BlazingBambooBlock.blazeHurtEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BBRotatedPillarBlock extends RotatedPillarBlock {
    public BBRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        blazeHurtEntity(level, entity, 2.0f);

        super.stepOn(level, pos, state, entity);
    }
}
