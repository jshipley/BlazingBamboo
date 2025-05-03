package net.paddedshaman.blazingbamboo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.state.BlockState;

public class DeadBambooBlock extends BlazingBambooBlock {
    public DeadBambooBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier) {
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess scheduledTickAccess,
            BlockPos blockPos, Direction direction, BlockPos neighborBlockPos, BlockState neighborBlockState, RandomSource random) {
        if (!blockState.canSurvive(level, blockPos))
            scheduledTickAccess.scheduleTick(blockPos, this, 1);
        if (direction == Direction.UP && neighborBlockState.is(BBBlocks.DEAD_BAMBOO)
                && (Integer) neighborBlockState.getValue(AGE) > ((Integer) blockState.getValue(AGE)).intValue())
            return blockState.cycle(AGE);
        return super.updateShape(blockState, level, scheduledTickAccess, blockPos, direction, neighborBlockPos, neighborBlockState, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState blockState, boolean isClient) {
        return false;
    }
}
