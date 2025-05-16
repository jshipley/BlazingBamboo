package net.paddedshaman.blazingbamboo.block.custom;

import static net.paddedshaman.blazingbamboo.block.BlazingBambooBlock.blazeHurtEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlazingFlowerPotBlock extends FlowerPotBlock {

    public BlazingFlowerPotBlock(Block content, Properties properties) {
        super(content, properties);
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);
    }

    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);

        super.stepOn(level, pos, state, entity);
    }
}
