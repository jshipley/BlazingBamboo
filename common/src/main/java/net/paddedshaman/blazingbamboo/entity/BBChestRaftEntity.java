package net.paddedshaman.blazingbamboo.entity;

import java.util.function.Supplier;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestRaft;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.paddedshaman.blazingbamboo.item.BBItems;

public class BBChestRaftEntity extends ChestRaft {

    public BBChestRaftEntity(EntityType<? extends BBChestRaftEntity> entityType, Level level, Supplier<Item> supplier) {
        super(entityType, level, supplier);
    }
    
    public BBChestRaftEntity(EntityType<? extends BBChestRaftEntity> entityType, Level level) {
        super(entityType, level, () -> BBItems.BLAZING_BAMBOO_CHEST_RAFT.get());
    }

    // Die in water
    @Override
    @SuppressWarnings("deprecation")
    public void tick() {
        super.tick();
 
        Status status = this.getStatus();
        if ((status == Status.IN_WATER || status == Status.UNDER_WATER || status == Status.UNDER_FLOWING_WATER)
                && BBRaftEntity.checkInWaterActual(this.level(), this.getBoundingBox())) {
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
        this.chestVehicleDestroyed(source, level, this);
    }
}
