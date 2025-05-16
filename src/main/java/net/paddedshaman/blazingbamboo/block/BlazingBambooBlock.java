package net.paddedshaman.blazingbamboo.block;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.FluidState;
import net.paddedshaman.blazingbamboo.util.BBDamageTypes;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BlazingBambooBlock extends BambooStalkBlock {
    public BlazingBambooBlock(BlockBehaviour.Properties p_48874_) {
        super(p_48874_);
    }

    private static final int MAX_HEIGHT = 13;
    private static final int HEIGHT_VARIANCE = 5;

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
        if (!fluidstate.isEmpty())
            return null;
        BlockState blockstate = pContext.getLevel().getBlockState(pContext.getClickedPos().below());
        if (blockstate.is(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON)) {
            if (blockstate.is(BBBlocks.BLAZING_BAMBOO_SAPLING))
                return defaultBlockState().setValue(AGE, 0);
            if (blockstate.is(BBBlocks.BLAZING_BAMBOO)) {
                int i = ((Integer) blockstate.getValue(AGE) > 0) ? 1 : 0;
                return defaultBlockState().setValue(AGE, i);
            }
            BlockState blockstate1 = pContext.getLevel().getBlockState(pContext.getClickedPos().above());
            return blockstate1.is(BBBlocks.BLAZING_BAMBOO)
                    ? defaultBlockState().setValue(AGE, blockstate1.getValue(AGE))
                    : BBBlocks.BLAZING_BAMBOO_SAPLING.defaultBlockState();
        }
        return null;
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).is(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON) || pLevel.getBlockState(pPos.below()).is(BBBlocks.DEAD_BAMBOO);
    }

    public static void blazeHurtEntity(Level level, Entity entity, float damage) {
        if (!entity.isSteppingCarefully() && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().source(BBDamageTypes.BLAZING_HOT), damage);
        }
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);

        super.stepOn(level, pos, state, entity);
    }

    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pPos, BlockPos pNeighborPos) {
        if (!pState.canSurvive(pLevel, pPos))
            pLevel.scheduleTick(pPos, this, 1);
        if (pDirection == Direction.UP && pNeighborState.is(BBBlocks.BLAZING_BAMBOO)
                && (Integer) pNeighborState.getValue(AGE)
                > ((Integer)pState.getValue(AGE)).intValue())
            pLevel.setBlock(pPos, pState.cycle(AGE), 2);
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pPos, pNeighborPos);
    }

    public static boolean isHydrated(ServerLevel level, BlockPos blockPos) {
        return BlockPos
            .findClosestMatch(blockPos, 1, 1, (pos) -> {
                BlockState searchBlockState = level.getBlockState(pos);
                return searchBlockState.getFluidState().is(FluidTags.WATER)
                    || (searchBlockState.is(Blocks.FARMLAND) && searchBlockState.getValue(FarmBlock.MOISTURE) > 0);
            })
            .isPresent();
    }

    public static boolean isFrozen(ServerLevel level, BlockPos blockPos) {
        return level.getBlockState(blockPos.below()).is(BlockTags.ICE);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        int height = this.getHeightBelowUpToMax(pLevel, pPos) + 1;
        BlockPos baseBlockPos = pPos.below(height);

        if (isHydrated(pLevel, baseBlockPos)) {
            this.extinguishBamboo(pLevel, baseBlockPos);

        } else if (height < MAX_HEIGHT && !isFrozen(pLevel, baseBlockPos) && pState.getValue(STAGE) == 0
                && pLevel.isEmptyBlock(pPos.above()) && pRandom.nextInt(3) == 0) {
            this.growBamboo(pState, pLevel, pPos, pRandom, height);

        }
    }

    protected void extinguishBamboo(Level pLevel, BlockPos baseBlockPos) {
        BlockState deadStalk = BBBlocks.DEAD_BAMBOO.defaultBlockState();
        int height = getHeightAboveUpToMax(pLevel, baseBlockPos);
        for (int i = 0; i < height; i++) {
            BlockPos currentPos = baseBlockPos.above(i + 1);
            BlockState currentState = pLevel.getBlockState(currentPos);
            BambooLeaves deadLeaves = BambooLeaves.NONE;
            int age = (Integer)currentState.getValue(AGE);
            if (currentState.is(BBBlocks.BLAZING_BAMBOO) && currentState.getValue(LEAVES) == BambooLeaves.LARGE) {
                deadLeaves = BambooLeaves.LARGE;
            } else if (currentState.is(BBBlocks.BLAZING_BAMBOO) && currentState.getValue(LEAVES) == BambooLeaves.SMALL) {
                deadLeaves = BambooLeaves.SMALL;
            }
            pLevel.setBlock(currentPos, deadStalk.setValue(AGE, age).setValue(LEAVES, deadLeaves).setValue(STAGE, 1), 3);
        }
    }

    protected void growBamboo(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRandom, int pAge) {
        BlockState blockstate = pLevel.getBlockState(pPos.below());
        BlockPos blockpos = pPos.below(2);
        BlockState blockstate1 = pLevel.getBlockState(blockpos);
        BambooLeaves bambooleaves = BambooLeaves.NONE;
        if (pAge >= 1) {
            if (blockstate.is(BBBlocks.BLAZING_BAMBOO) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
                if (blockstate.is(BBBlocks.BLAZING_BAMBOO) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
                    bambooleaves = BambooLeaves.LARGE;
                    if (blockstate1.is(BBBlocks.BLAZING_BAMBOO)) {
                        pLevel.setBlock(pPos.below(), blockstate.setValue(LEAVES, BambooLeaves.SMALL), 3);
                        pLevel.setBlock(blockpos, blockstate1.setValue(LEAVES, BambooLeaves.NONE), 3);
                    }
                }
            } else {
                bambooleaves = BambooLeaves.SMALL;
            }
        }
        int i = ((Integer) pState.getValue((Property) AGE) != 1 && !blockstate1.is(BBBlocks.BLAZING_BAMBOO)) ? 0 : 1;
        int j = ((pAge < (MAX_HEIGHT - HEIGHT_VARIANCE - 1) || pRandom.nextFloat() >= 0.25F) && pAge != (MAX_HEIGHT - 1)) ? 0 : 1;
        pLevel.setBlock(pPos.above(), defaultBlockState().setValue(AGE, i)
                .setValue((Property)LEAVES, (Comparable)bambooleaves).setValue(STAGE, j), 3);
    }

    @Override
    protected int getHeightAboveUpToMax(BlockGetter pLevel, BlockPos pPos) {
        int i;
        for (i = 0; i < MAX_HEIGHT && pLevel.getBlockState(pPos.above(i + 1)).is(BBBlocks.BLAZING_BAMBOO); i++);
        return i;
    }

    @Override
    protected int getHeightBelowUpToMax(BlockGetter pLevel, BlockPos pPos) {
        int i;
        for (i = 0; i < MAX_HEIGHT && pLevel.getBlockState(pPos.below(i + 1)).is(BBBlocks.BLAZING_BAMBOO); i++);
        return i;
    }

    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        int i = this.getHeightAboveUpToMax(pLevel, pPos);
        int j = this.getHeightBelowUpToMax(pLevel, pPos);
        return i + j + 1 < MAX_HEIGHT && pLevel.getBlockState(pPos.above(i)).getValue(STAGE) != 1;
    }

    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        int i = this.getHeightAboveUpToMax(pLevel, pPos);
        int j = this.getHeightBelowUpToMax(pLevel, pPos);
        int k = i + j + 1;
        BlockPos blockpos = pPos.above(i);
        BlockState blockstate = pLevel.getBlockState(blockpos);
        if (k >= MAX_HEIGHT || blockstate.getValue(STAGE) == 1 || !pLevel.isEmptyBlock(blockpos.above())) {
            return;
        }
        this.growBamboo(blockstate, pLevel, blockpos, pRandom, k);
    }
}
