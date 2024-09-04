package net.paddedshaman.blazingbamboo.block.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

import java.util.function.Supplier;

public class BBBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, BlazingBamboo.MOD_ID);

    public static final Supplier<BlockEntityType<BBSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(BBSignBlockEntity::new,
                            BBBlocks.BLAZING_BAMBOO_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_SIGN.get()).build(null));
    public static final Supplier<BlockEntityType<BBHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(BBHangingSignBlockEntity::new,
                            BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
