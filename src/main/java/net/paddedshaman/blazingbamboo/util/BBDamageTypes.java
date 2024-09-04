package net.paddedshaman.blazingbamboo.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBDamageTypes {
    public static final ResourceKey<DamageType> BLAZING_HOT = register("blazing_hot");

    protected static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(BBDamageTypes.BLAZING_HOT, new DamageType("blazing_hot", 0.1F));
    }

    private static ResourceKey<DamageType> register(String name) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name));
    }
}
