package net.paddedshaman.blazingbamboo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;

public class DeadBambooBlock extends BlazingBambooBlock {
    public DeadBambooBlock(Properties p_48874_) {
        super(p_48874_);
    }

    @Override
    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {}
    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {}

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (!pState.canSurvive(pLevel, pPos))
            pLevel.scheduleTick(pPos, this, 1);
        if (pDirection == Direction.UP && pNeighborState.is(BBBlocks.DEAD_BAMBOO)
                && (Integer) pNeighborState.getValue((Property) AGE)
                > ((Integer)pState.getValue((Property)AGE)).intValue())
            pLevel.setBlock(pPos, pState.cycle((Property)AGE), 2);
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return false;
    }
    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {}
    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) { return false; }
}
