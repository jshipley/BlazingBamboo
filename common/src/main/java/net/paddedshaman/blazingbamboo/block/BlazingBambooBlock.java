package net.paddedshaman.blazingbamboo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FarmBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.material.FluidState;
import net.paddedshaman.blazingbamboo.config.BBConfig;
import net.paddedshaman.blazingbamboo.util.BBDamageTypes;
import net.paddedshaman.blazingbamboo.util.BBTags;

import org.jetbrains.annotations.Nullable;

public class BlazingBambooBlock extends BambooStalkBlock {
    public BlazingBambooBlock(BlockBehaviour.Properties blockProperties) {
        super(blockProperties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidState = context.getLevel().getFluidState(context.getClickedPos());
        if (!fluidState.isEmpty())
            return null;
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos().below());
        if (blockState.is(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON)) {
            if (blockState.is(BBBlocks.BLAZING_BAMBOO_SAPLING))
                return defaultBlockState().setValue(AGE, 0);
            if (blockState.is(BBBlocks.BLAZING_BAMBOO)) {
                return defaultBlockState().setValue(AGE, (blockState.getValue(AGE) > 0) ? 1 : 0);
            }
            BlockState blockState1 = context.getLevel().getBlockState(context.getClickedPos().above());
            return blockState1.is(BBBlocks.BLAZING_BAMBOO)
                    ? defaultBlockState().setValue(AGE, blockState1.getValue(AGE))
                    : BBBlocks.BLAZING_BAMBOO_SAPLING.get().defaultBlockState();
        }
        return null;
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos blockPos) {
        return level.getBlockState(blockPos.below()).is(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON)
                || level.getBlockState(blockPos.below()).is(BBBlocks.DEAD_BAMBOO);
    }

    @SuppressWarnings("deprecation")
    public static void blazeHurtEntity(Level level, Entity entity, float damage) {
        if (!(entity.isSteppingCarefully() && BBConfig.sneakSafely()) && entity instanceof LivingEntity) {
            entity.hurt(level.damageSources().source(BBDamageTypes.BLAZING_HOT), damage);
        }
    }

    @Override
    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier) {
        blazeHurtEntity(level, entity, 1.0f);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);

        super.stepOn(level, blockPos, blockState, entity);
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess scheduledTickAccess,
            BlockPos blockPos, Direction direction, BlockPos neighborBlockPos, BlockState neighborBlockState, RandomSource random) {
        if (!blockState.canSurvive(level, blockPos))
            scheduledTickAccess.scheduleTick(blockPos, this, 1);
        if (direction == Direction.UP && neighborBlockState.is(BBBlocks.BLAZING_BAMBOO)
                && (Integer) neighborBlockState.getValue(AGE) > ((Integer) blockState.getValue(AGE)).intValue())
            return blockState.cycle(AGE);
        return super.updateShape(blockState, level, scheduledTickAccess, blockPos, direction, neighborBlockPos, neighborBlockState, random);
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

    @Override
    public void handlePrecipitation(BlockState blockState, Level level, BlockPos blockPos, Biome.Precipitation precipitation) {
        if (level instanceof ServerLevel serverLevel && serverLevel.getRandom().nextFloat() <= BBConfig.bambooExtinguishChance())
            this.extinguishBamboo(serverLevel, blockPos.below(this.getHeightBelowUpToMax(serverLevel, blockPos) + 1));
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        int height = this.getHeightBelowUpToMax(level, blockPos) + 1;
        BlockPos baseBlockPos = blockPos.below(height);

        if (isHydrated(level, baseBlockPos)) {
            this.extinguishBamboo(level, baseBlockPos);

        } else if (height < BBConfig.bambooMaxHeight() && !isFrozen(level, baseBlockPos) && blockState.getValue(STAGE) == 0
                && level.isEmptyBlock(blockPos.above()) && random.nextInt(3) == 0) {
            this.growBamboo(blockState, level, blockPos, random, height);

        }
    }

    protected void extinguishBamboo(Level level, BlockPos baseBlockPos) {
        BlockState deadStalk = BBBlocks.DEAD_BAMBOO.get().defaultBlockState();
        int height = getHeightAboveUpToMax(level, baseBlockPos);
        for (int i = 0; i < height; i++) {
            BlockPos currentPos = baseBlockPos.above(i + 1);
            BlockState currentState = level.getBlockState(currentPos);
            BambooLeaves deadLeaves = BambooLeaves.NONE;
            int age = (Integer) currentState.getValue(AGE);
            if (currentState.is(BBBlocks.BLAZING_BAMBOO) && currentState.getValue(LEAVES) == BambooLeaves.LARGE) {
                deadLeaves = BambooLeaves.LARGE;
            } else if (currentState.is(BBBlocks.BLAZING_BAMBOO)
                    && currentState.getValue(LEAVES) == BambooLeaves.SMALL) {
                deadLeaves = BambooLeaves.SMALL;
            }
            level.setBlock(currentPos, deadStalk.setValue(AGE, age).setValue(LEAVES, deadLeaves).setValue(STAGE, 1), 3);
        }
    }

    @Override
    protected void growBamboo(BlockState blockState, Level level, BlockPos blockPos, RandomSource random, int age) {
        BlockState blockstate = level.getBlockState(blockPos.below());
        BlockPos blockpos = blockPos.below(2);
        BlockState blockstate1 = level.getBlockState(blockpos);
        BambooLeaves bambooleaves = BambooLeaves.NONE;
        if (age >= 1) {
            if (blockstate.is(BBBlocks.BLAZING_BAMBOO) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
                if (blockstate.is(BBBlocks.BLAZING_BAMBOO) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
                    bambooleaves = BambooLeaves.LARGE;
                    if (blockstate1.is(BBBlocks.BLAZING_BAMBOO)) {
                        level.setBlock(blockPos.below(), blockstate.setValue(LEAVES, BambooLeaves.SMALL), 3);
                        level.setBlock(blockpos, blockstate1.setValue(LEAVES, BambooLeaves.NONE), 3);
                    }
                }
            } else {
                bambooleaves = BambooLeaves.SMALL;
            }
        }
        int i = ((Integer) blockState.getValue(AGE) != 1 && !blockstate1.is(BBBlocks.BLAZING_BAMBOO)) ? 0 : 1;
        int j = ((age < (BBConfig.bambooMaxHeight() - BBConfig.bambooHeightVariance() - 1) || random.nextFloat() >= 0.25F)
                && age != (BBConfig.bambooMaxHeight() - 1)) ? 0 : 1;
        level.setBlock(blockPos.above(), defaultBlockState().setValue(AGE, i)
                .setValue(LEAVES, bambooleaves).setValue(STAGE, j), 3);
    }

    @Override
    protected int getHeightAboveUpToMax(BlockGetter level, BlockPos blockPos) {
        int i;
        for (i = 0; i < BBConfig.bambooMaxHeight() && level.getBlockState(blockPos.above(i + 1)).is(BBBlocks.BLAZING_BAMBOO); i++);
        return i;
    }

    @Override
    protected int getHeightBelowUpToMax(BlockGetter level, BlockPos blockPos) {
        int i;
        for (i = 0; i < BBConfig.bambooMaxHeight() && level.getBlockState(blockPos.below(i + 1)).is(BBBlocks.BLAZING_BAMBOO); i++);
        return i;
    }

    public boolean isValidBonemealTarget(LevelReader level, BlockPos blockPos, BlockState blockState, boolean pIsClient) {
        int i = this.getHeightAboveUpToMax(level, blockPos);
        int j = this.getHeightBelowUpToMax(level, blockPos);
        return i + j + 1 < BBConfig.bambooMaxHeight() && level.getBlockState(blockPos.above(i)).getValue(STAGE) != 1;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos blockPos, BlockState blockState) {
        int i = this.getHeightAboveUpToMax(level, blockPos);
        int j = this.getHeightBelowUpToMax(level, blockPos);
        int k = i + j + 1;
        BlockPos blockpos = blockPos.above(i);
        BlockState blockstate = level.getBlockState(blockpos);
        if (k >= BBConfig.bambooMaxHeight() || blockstate.getValue(STAGE) == 1 || !level.isEmptyBlock(blockpos.above())) {
            return;
        }
        this.growBamboo(blockstate, level, blockpos, random, k);
    }
}
