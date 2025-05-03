package net.paddedshaman.blazingbamboo.block.entity;

import java.util.Set;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBBlockEntities {

    private static Registrar<BlockEntityType<?>> BLOCK_ENTITY_TYPES = BlazingBamboo.REGISTRY_MANAGER.get().get(Registries.BLOCK_ENTITY_TYPE);

    public static final RegistrySupplier<BlockEntityType<BBSignBlockEntity>> MOD_SIGN = BLOCK_ENTITY_TYPES.register(
            BlazingBamboo.id("mod_sign"),
            () -> new BlockEntityType<BBSignBlockEntity>(BBSignBlockEntity::new,
                    Set.of(BBBlocks.BLAZING_BAMBOO_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_SIGN.get())));
    public static final RegistrySupplier<BlockEntityType<BBHangingSignBlockEntity>> MOD_HANGING_SIGN = BLOCK_ENTITY_TYPES
            .register(
                    BlazingBamboo.id("mod_hanging_sign"),
                    () -> new BlockEntityType<BBHangingSignBlockEntity>(BBHangingSignBlockEntity::new,
                            Set.of(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get(),
                                    BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN.get())));

    public static void registerBlockEntities() {
    }
}
