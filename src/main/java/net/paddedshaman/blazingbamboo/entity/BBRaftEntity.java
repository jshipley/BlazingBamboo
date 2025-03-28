package net.paddedshaman.blazingbamboo.entity;

import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Raft;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.AABB;
import net.paddedshaman.blazingbamboo.item.BBItems;

public class BBRaftEntity extends Raft {

    public BBRaftEntity(EntityType<? extends BBRaftEntity> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }

    public BBRaftEntity(EntityType<? extends BBRaftEntity> entityType, Level level) {
        super(entityType, level, () -> BBItems.BLAZING_BAMBOO_RAFT);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    // The following methods are copies of the methods from AbstractBoat, but use FluidTags.LAVA instead of FluidTags.WATER

    @Override
    public float getWaterLevelAbove() {
        AABB aABB = this.getBoundingBox();
        int i = Mth.floor(aABB.minX);
        int j = Mth.ceil(aABB.maxX);
        int k = Mth.floor(aABB.maxY);
        int l = Mth.ceil(aABB.maxY - this.lastYd);
        int m = Mth.floor(aABB.minZ);
        int n = Mth.ceil(aABB.maxZ);
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        label39: for (int o = k; o < l; ++o) {
            float f = 0.0F;

            for (int p = i; p < j; ++p) {
                for (int q = m; q < n; ++q) {
                    mutableBlockPos.set(p, o, q);
                    FluidState fluidState = this.level().getFluidState(mutableBlockPos);
                    if (fluidState.is(FluidTags.LAVA)) {
                        f = Math.max(f, fluidState.getHeight(this.level(), mutableBlockPos));
                    }

                    if (f >= 1.0F) {
                        continue label39;
                    }
                }
            }

            if (f < 1.0F) {
                return (float) mutableBlockPos.getY() + f;
            }
        }

        return (float) (l + 1);
    }

    @Override
    public boolean checkInWater() {
        AABB aABB = this.getBoundingBox();
        int i = Mth.floor(aABB.minX);
        int j = Mth.ceil(aABB.maxX);
        int k = Mth.floor(aABB.minY);
        int l = Mth.ceil(aABB.minY + 0.001);
        int m = Mth.floor(aABB.minZ);
        int n = Mth.ceil(aABB.maxZ);
        boolean bl = false;
        this.waterLevel = -1.7976931348623157E308;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int o = i; o < j; ++o) {
            for (int p = k; p < l; ++p) {
                for (int q = m; q < n; ++q) {
                    mutableBlockPos.set(o, p, q);
                    FluidState fluidState = this.level().getFluidState(mutableBlockPos);
                    if (fluidState.is(FluidTags.LAVA)) {
                        float f = (float) p + fluidState.getHeight(this.level(), mutableBlockPos);
                        this.waterLevel = Math.max((double) f, this.waterLevel);
                        bl |= aABB.minY < (double) f;
                    }
                }
            }
        }

        return bl;
    }

    @Nullable
    @Override
    public Status isUnderwater() {
        AABB aABB = this.getBoundingBox();
        double d = aABB.maxY + 0.001;
        int i = Mth.floor(aABB.minX);
        int j = Mth.ceil(aABB.maxX);
        int k = Mth.floor(aABB.maxY);
        int l = Mth.ceil(d);
        int m = Mth.floor(aABB.minZ);
        int n = Mth.ceil(aABB.maxZ);
        boolean bl = false;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int o = i; o < j; ++o) {
            for (int p = k; p < l; ++p) {
                for (int q = m; q < n; ++q) {
                    mutableBlockPos.set(o, p, q);
                    FluidState fluidState = this.level().getFluidState(mutableBlockPos);
                    if (fluidState.is(FluidTags.LAVA) && d < (double) ((float) mutableBlockPos.getY()
                            + fluidState.getHeight(this.level(), mutableBlockPos))) {
                        if (!fluidState.isSource()) {
                            return net.minecraft.world.entity.vehicle.AbstractBoat.Status.UNDER_FLOWING_WATER;
                        }

                        bl = true;
                    }
                }
            }
        }

        return bl ? net.minecraft.world.entity.vehicle.AbstractBoat.Status.UNDER_WATER : null;
    }

    @Override
    public void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        this.lastYd = this.getDeltaMovement().y;
        if (!this.isPassenger()) {
            if (onGround) {
                this.resetFallDistance();
            } else {
                FluidState fluidState = this.level().getFluidState(this.blockPosition().below());
                if (!fluidState.is(FluidTags.WATER) && !fluidState.is(FluidTags.LAVA) && y < 0.0)
                    this.fallDistance -= (double) ((float) y);
            }
        }
    }
}
