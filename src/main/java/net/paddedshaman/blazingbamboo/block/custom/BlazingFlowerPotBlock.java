package net.paddedshaman.blazingbamboo.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.util.BBDamageTypes;

public class BlazingFlowerPotBlock extends FlowerPotBlock {

    public BlazingFlowerPotBlock(Block content, Properties properties) {
        super(content, properties);
    }

    public void entityInside(BlockState pState, Level pLevel, BlockPos pPos, Entity pEntity) {
        if (pEntity instanceof LivingEntity) {
            pEntity.hurt(pLevel.damageSources().source(BBDamageTypes.BLAZING_HOT), 1.0F);
        }
    }
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (pEntity instanceof LivingEntity) {
            pEntity.hurt(pLevel.damageSources().source(BBDamageTypes.BLAZING_HOT), 1.0F);
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }
}
