package net.paddedshaman.blazingbamboo.block;

import static net.paddedshaman.blazingbamboo.block.BlazingBambooBlock.blazeHurtEntity;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BlazingBambooSapling extends BambooSaplingBlock {
    public BlazingBambooSapling(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).is(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON);
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos))
            return Blocks.AIR.defaultBlockState();
        if (pFacing == Direction.UP && pFacingState.is(BBBlocks.BLAZING_BAMBOO.get()))
            pLevel.setBlock(pCurrentPos, BBBlocks.BLAZING_BAMBOO.get().defaultBlockState(), 2);
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @NotNull
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(BBItems.BLAZING_BAMBOO_ITEM.get());
    }

    @Override
    public void handlePrecipitation(BlockState blockState, Level level, BlockPos blockPos, Biome.Precipitation precipitation) {
        if (level instanceof ServerLevel serverLevel && serverLevel.getRandom().nextFloat() < 0.2f)
            this.extinguishBamboo(serverLevel, blockPos.below());
    }

    public void randomTick(BlockState blockState, ServerLevel level, BlockPos blockPos, RandomSource random) {
        BlockPos baseBlockPos = blockPos.below();

        if (BlazingBambooBlock.isHydrated(level, baseBlockPos)) {
            this.extinguishBamboo(level, baseBlockPos);

        } else if (!BlazingBambooBlock.isFrozen(level, baseBlockPos) && level.isEmptyBlock(blockPos.above()) && random.nextInt(3) == 0) {
            this.growBamboo(level, blockPos);
        }
    }

    protected void extinguishBamboo(ServerLevel pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos.above(), BBBlocks.DEAD_BAMBOO_SAPLING.get().defaultBlockState(), 3);
    }

    protected void growBamboo(Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos.above(),
                BBBlocks.BLAZING_BAMBOO.get().defaultBlockState().setValue(BambooStalkBlock.LEAVES, BambooLeaves.SMALL), 3);
    }
}
