package net.paddedshaman.blazingbamboo.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.Heightmap;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.util.BBDamageTypes;
import net.paddedshaman.blazingbamboo.util.BBTags;
import org.jetbrains.annotations.NotNull;

public class BlazingBambooSapling extends BambooSaplingBlock {
    public BlazingBambooSapling(BlockBehaviour.Properties properties) {
        super(properties);
    }

    public boolean canSurvive(BlockState pState, LevelReader pLevel, BlockPos pPos) {
        return pLevel.getBlockState(pPos.below()).is(BBTags.Blocks.BLAZING_BAMBOO_PLANTABLE_ON);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity) {
            pEntity.hurt(pLevel.damageSources().source(BBDamageTypes.BLAZING_HOT), 1.0F);
        }
    }

    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (!pState.canSurvive(pLevel, pCurrentPos))
            return Blocks.AIR.defaultBlockState();
        if (pFacing == Direction.UP && pFacingState.is(BBBlocks.BLAZING_BAMBOO))
            pLevel.setBlock(pCurrentPos, BBBlocks.BLAZING_BAMBOO.defaultBlockState(), 2);
        return super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @NotNull
    public ItemStack getCloneItemStack(BlockGetter pLevel, BlockPos pPos, BlockState pState) {
        return new ItemStack(BBItems.BLAZING_BAMBOO_ITEM);
    }

    private boolean isRainingOnThis(ServerLevel pLevel, BlockPos pPos) {
        Biome biome = pLevel.getBiome(pPos).value();
        if (!biome.hasPrecipitation()) {
            return false;
        } else if (pLevel.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, pPos).getY() > pPos.getY()) {
            return false;
        } else {
            return pLevel.getRainLevel(1.0F) > 0.2;
        }
    }

    private static final int[][] nearbyPositions = {
            {1, 1},
            {-1, -1},
            {-1, 1},
            {1, -1},
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };
    private boolean isHydrated(ServerLevel pLevel, BlockPos pPos) {
        BlockPos.MutableBlockPos mutablePos = pPos.mutable();
        BlockState mutableState, mutableStateAbove;
        for(int[] row : nearbyPositions) {
            mutablePos.setWithOffset(pPos, row[0], 0, row[1]);
            mutableState = pLevel.getBlockState(mutablePos);
            mutableStateAbove = pLevel.getBlockState(mutablePos.above());
            if (mutableState.is(Blocks.WATER) || mutableStateAbove.is(Blocks.WATER)) { return true; }
            if (mutableState.hasProperty(BlockStateProperties.WATERLOGGED)) {
                if (mutableState.getValue(BlockStateProperties.WATERLOGGED)) { return true; }
            }
            if (mutableStateAbove.hasProperty(BlockStateProperties.WATERLOGGED)) {
                if (mutableStateAbove.getValue(BlockStateProperties.WATERLOGGED)) { return true; }
            }
        }
        return false;
    }
    private boolean isFrozen(ServerLevel pLevel, BlockPos pPos) {
        BlockState iceCheck = pLevel.getBlockState(pPos.below());
        return iceCheck.is(Blocks.ICE) || iceCheck.is(Blocks.PACKED_ICE) || iceCheck.is(Blocks.BLUE_ICE);
    }

    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        BlockPos baseBlockPos = pPos.below();

        if (isRainingOnThis(pLevel, pPos.above()) || isHydrated(pLevel, baseBlockPos)) {
            this.extinguishBamboo(pLevel, baseBlockPos);

        } else if (!isFrozen(pLevel, baseBlockPos) && pLevel.isEmptyBlock(pPos.above()) && pRandom.nextInt(3) == 0) {
            this.growBamboo(pLevel, pPos);

        }
    }

    protected void extinguishBamboo(ServerLevel pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos.above(), BBBlocks.DEAD_BAMBOO_SAPLING.defaultBlockState(), 3);
    }

    protected void growBamboo(Level pLevel, BlockPos pPos) {
        pLevel.setBlock(pPos.above(),
                BBBlocks.BLAZING_BAMBOO.defaultBlockState().setValue((Property) BambooStalkBlock.LEAVES, (Comparable) BambooLeaves.SMALL), 3);
    }
}
