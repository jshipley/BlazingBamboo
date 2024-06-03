package net.paddedshaman.blazingbamboo.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BlazingBamboo.MOD_ID);

    public static final RegistryObject<EntityType<BBRaftEntity>> BB_RAFT = ENTITY_TYPES.register("bb_raft",
            () -> EntityType.Builder.<BBRaftEntity>of(BBRaftEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build("bb_raft"));
    public static final RegistryObject<EntityType<BBChestRaftEntity>> BB_CHEST_RAFT = ENTITY_TYPES.register("bb_chest_raft",
            () -> EntityType.Builder.<BBChestRaftEntity>of(BBChestRaftEntity::new, MobCategory.MISC).sized(1.375F, 0.5625F).build("bb_chest_raft"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
