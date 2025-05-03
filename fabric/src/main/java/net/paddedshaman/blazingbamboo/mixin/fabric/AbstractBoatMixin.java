package net.paddedshaman.blazingbamboo.mixin.fabric;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.vehicle.AbstractBoat;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.paddedshaman.blazingbamboo.entity.BBChestRaftEntity;
import net.paddedshaman.blazingbamboo.entity.BBRaftEntity;

@Mixin(AbstractBoat.class)
public class AbstractBoatMixin {

    private boolean isBlazingRaft() {
        AbstractBoat boat = (AbstractBoat)(Object)this;
        return boat instanceof BBRaftEntity || boat instanceof BBChestRaftEntity;
    }

    @Redirect(
        method = {"getWaterLevelAbove", "checkInWater", "isUnderwater", "checkFallDamage", "canAddPassenger"},
        at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/material/FluidState;is(Lnet/minecraft/tags/TagKey;)Z"))
    public boolean isLavaOrWater(FluidState fluidState, TagKey<Fluid> tag) {
        return fluidState.is(tag) || (isBlazingRaft() && fluidState.is(FluidTags.LAVA));
    }
    
    
}
