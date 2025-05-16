package net.paddedshaman.blazingbamboo.block.custom;

import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.paddedshaman.blazingbamboo.util.BBDamageTypes;
import static net.paddedshaman.blazingbamboo.block.BlazingBambooBlock.blazeHurtEntity;

public class BlazingFlowerPotBlock extends FlowerPotBlock {
    public BlazingFlowerPotBlock(@Nullable Supplier<FlowerPotBlock> emptyPot, Supplier<? extends Block> p_53528_, Properties properties) {
        super(emptyPot, p_53528_, properties);
    }

    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);
    }
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        blazeHurtEntity(level, entity, 1.0f);

        super.stepOn(level, pos, state, entity);
    }
}
