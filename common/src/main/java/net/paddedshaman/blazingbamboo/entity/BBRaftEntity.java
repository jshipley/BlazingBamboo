package net.paddedshaman.blazingbamboo.entity;

import java.util.function.Supplier;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Raft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.paddedshaman.blazingbamboo.item.BBItems;

public class BBRaftEntity extends Raft {

    public BBRaftEntity(EntityType<? extends BBRaftEntity> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }

    public BBRaftEntity(EntityType<? extends BBRaftEntity> entityType, Level level) {
        super(entityType, level, () -> BBItems.BLAZING_BAMBOO_RAFT.get());
    }

    // Die in water
    @Override
    @SuppressWarnings("deprecation")
    public void tick() {
        super.tick();
 
        Status status = this.getStatus();
        if ((status == Status.IN_WATER || status == Status.UNDER_WATER || status == Status.UNDER_FLOWING_WATER) && checkInWaterActual(this.level(), this.getBoundingBox())) {
            this.playSound(SoundEvents.FIRE_EXTINGUISH);
            this.hurt(damageSources().dryOut(), 10f);
        }
    }
 
    @Override
    public void destroy(ServerLevel level, DamageSource source) {
        if (source.is(DamageTypes.DRY_OUT)) {
            this.destroy(level, Items.GUNPOWDER);
        } else {
            this.destroy(level, this.getDropItem());
        }
    }

    // copy of AbstractBoat.checkInWater() that doesn't modify the boat's state
    // and won't get modified by the AbstractBoatMixin
    public static boolean checkInWaterActual(Level level, AABB boundingBox) {
        int i = Mth.floor(boundingBox.minX);
        int j = Mth.ceil(boundingBox.maxX);
        int k = Mth.floor(boundingBox.minY);
        int l = Mth.ceil(boundingBox.minY + 0.001);
        int m = Mth.floor(boundingBox.minZ);
        int n = Mth.ceil(boundingBox.maxZ);
        boolean bl = false;
        double waterLevel = -1.7976931348623157E308;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int o = i; o < j; ++o) {
            for (int p = k; p < l; ++p) {
                for (int q = m; q < n; ++q) {
                    mutableBlockPos.set(o, p, q);
                    FluidState fluidState = level.getFluidState(mutableBlockPos);
                    if (fluidState.is(FluidTags.WATER)) {
                        float f = (float) p + fluidState.getHeight(level, mutableBlockPos);
                        waterLevel = Math.max((double) f, waterLevel);
                        bl |= boundingBox.minY < (double) f;
                    }
                }
            }
        }

        return bl;
    }
}
