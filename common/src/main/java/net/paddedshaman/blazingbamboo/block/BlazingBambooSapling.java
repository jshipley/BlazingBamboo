package net.paddedshaman.blazingbamboo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.paddedshaman.blazingbamboo.config.BBConfig;
import net.paddedshaman.blazingbamboo.util.BBTags;

import org.jetbrains.annotations.NotNull;

public class BlazingBambooSapling extends BambooSaplingBlock {
    public BlazingBambooSapling(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState blockState, LevelReader level, BlockPos blockPos) {
        return level.getBlockState(blockPos.below()).is(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON);
    }

    @Override
    public void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier) {
        BlazingBambooBlock.blazeHurtEntity(level, entity, 1.0f);
    }

    @Override
    public BlockState updateShape(BlockState blockState, LevelReader level, ScheduledTickAccess scheduledTickAccess,
            BlockPos blockPos, Direction facing, BlockPos facingBlockPos, BlockState facingBlockState, RandomSource random) {
        if (!blockState.canSurvive(level, blockPos))
            return Blocks.AIR.defaultBlockState();
        if (facing == Direction.UP && facingBlockState.is(BBBlocks.BLAZING_BAMBOO))
            return BBBlocks.BLAZING_BAMBOO.get().defaultBlockState();
        return super.updateShape(blockState, level, scheduledTickAccess, blockPos, facing, facingBlockPos, facingBlockState, random);
    }

    @Override
    @NotNull
    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return new ItemStack(BBBlocks.BLAZING_BAMBOO.get());
    }

    @Override
    public void handlePrecipitation(BlockState blockState, Level level, BlockPos blockPos, Biome.Precipitation precipitation) {
        if (level instanceof ServerLevel serverLevel && serverLevel.getRandom().nextFloat() < BBConfig.bambooExtinguishChance())
            this.extinguishBamboo(serverLevel, blockPos.below());
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        BlockPos baseBlockPos = blockPos.below();

        if (BlazingBambooBlock.isHydrated(level, baseBlockPos)) {
            this.extinguishBamboo(level, baseBlockPos);

        } else if (!BlazingBambooBlock.isFrozen(level, baseBlockPos) && level.isEmptyBlock(blockPos.above()) && random.nextInt(3) == 0) {
            this.growBamboo(level, blockPos);

        }
    }

    protected void extinguishBamboo(ServerLevel level, BlockPos blockPos) {
        level.setBlock(blockPos.above(), BBBlocks.DEAD_BAMBOO_SAPLING.get().defaultBlockState(), 3);
    }

    protected void growBamboo(Level level, BlockPos blockPos) {
        level.setBlock(blockPos.above(),
                BBBlocks.BLAZING_BAMBOO.get().defaultBlockState().setValue(BambooStalkBlock.LEAVES, BambooLeaves.SMALL), 3);
    }
}
