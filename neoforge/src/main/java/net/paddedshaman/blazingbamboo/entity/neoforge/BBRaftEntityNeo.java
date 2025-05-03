package net.paddedshaman.blazingbamboo.entity.neoforge;

import java.util.function.Supplier;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.paddedshaman.blazingbamboo.entity.BBRaftEntity;
import net.paddedshaman.blazingbamboo.item.BBItems;

public class BBRaftEntityNeo extends BBRaftEntity {

    public BBRaftEntityNeo(EntityType<? extends BBRaftEntity> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }

    public BBRaftEntityNeo(EntityType<? extends BBRaftEntity> entityType, Level level) {
        super(entityType, level, () -> BBItems.BLAZING_BAMBOO_RAFT.get());
    }

    @Override
    public boolean canBoatInFluid(FluidState state) {
        return state.is(Fluids.LAVA);
    }

    @Override
    public boolean canBoatInFluid(FluidType type) {
        return type == NeoForgeMod.LAVA_TYPE.value();
    }
}
