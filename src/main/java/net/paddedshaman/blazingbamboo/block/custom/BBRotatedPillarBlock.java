package net.paddedshaman.blazingbamboo.block.custom;

import static net.paddedshaman.blazingbamboo.block.BlazingBambooBlock.blazeHurtEntity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBRotatedPillarBlock extends RotatedPillarBlock {
    public BBRotatedPillarBlock(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return false;
    }
    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ItemAbility itemAbility, boolean simulate) {
        if(context.getItemInHand().is(ItemTags.AXES)) {
            if(state.is(BBBlocks.BLAZING_BAMBOO_BUNDLE.get())) {
                return BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        blazeHurtEntity(level, entity, 2.0f);

        super.stepOn(level, pos, state, entity);
    }
}
