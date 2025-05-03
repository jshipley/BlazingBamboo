package net.paddedshaman.blazingbamboo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.block.BlazingBambooBlock;

public class BlazingFlowerPotBlock extends FlowerPotBlock {

    public BlazingFlowerPotBlock(Block content, Properties properties) {
        super(content, properties);
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        BlazingBambooBlock.blazeHurtEntity(level, entity, 1.0f);
  
        super.stepOn(level, blockPos, blockState, entity);
     }

    @Override
    protected void entityInside(BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier) {
        BlazingBambooBlock.blazeHurtEntity(level, entity, 1.0f);
    }
}
