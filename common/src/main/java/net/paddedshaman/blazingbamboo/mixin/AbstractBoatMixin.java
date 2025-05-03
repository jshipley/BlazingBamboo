package net.paddedshaman.blazingbamboo.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.paddedshaman.blazingbamboo.config.BBConfig;

@Mixin(AbstractBoat.class)
public class AbstractBoatMixin {

    @ModifyVariable(method = "floatBoat", at = @At(value = "STORE"))
    private float friction(float f) {
        // f is almost the only float used in floatBoat and is used as a friction multiplier.
        // Setting it to a lower number will make the boat slower. Default multiplier in water is 0.9.
        // Nothing else is being set to 0.9, replacing 0.9 should be safe.
        return Float.compare(f, 0.9f) == 0 ? BBConfig.raftSpeedMultiplier() : f;
    }
}
