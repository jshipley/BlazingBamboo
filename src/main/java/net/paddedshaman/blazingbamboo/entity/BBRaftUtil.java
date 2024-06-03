package net.paddedshaman.blazingbamboo.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Arrays;
import java.util.List;

public class BBRaftUtil {
    private static final List<Boat.Status> DAMAGE = Arrays.asList(Boat.Status.UNDER_WATER, Boat.Status.UNDER_FLOWING_WATER);

    public static boolean isImmune(Entity entity) {
        if (entity.isPassenger()) {
            if (entity.getVehicle() instanceof BBRaftEntity boat) {
                return !DAMAGE.contains(boat.getStatus());
            } else if (entity.getVehicle() instanceof BBChestRaftEntity boat) {
                return !DAMAGE.contains(boat.getStatus());
            }
        }
        return false;
    }
}
