package net.paddedshaman.blazingbamboo.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBDamageTypes {
    public static final ResourceKey<DamageType> BLAZING_HOT = register("blazing_hot");

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name));
    }
}
