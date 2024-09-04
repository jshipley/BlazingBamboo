package net.paddedshaman.blazingbamboo.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

import java.util.function.Supplier;

public class BBEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, BlazingBamboo.MOD_ID);

    public static final Supplier<EntityType<BBRaftEntity>> BB_RAFT = ENTITY_TYPES.register("bb_raft",
            () -> EntityType.Builder.<BBRaftEntity>of(BBRaftEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build("bb_raft"));
    public static final Supplier<EntityType<BBChestRaftEntity>> BB_CHEST_RAFT = ENTITY_TYPES.register("bb_chest_raft",
            () -> EntityType.Builder.<BBChestRaftEntity>of(BBChestRaftEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build("bb_chest_raft"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
