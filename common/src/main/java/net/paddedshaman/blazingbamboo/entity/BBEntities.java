package net.paddedshaman.blazingbamboo.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.MobCategory;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

public class BBEntities {
        private static final Registrar<EntityType<?>> ENTITY_TYPES = BlazingBamboo.REGISTRY_MANAGER.get()
                        .get(Registries.ENTITY_TYPE);
        public static final RegistrySupplier<EntityType<BBRaftEntity>> BB_RAFT = ENTITY_TYPES.register(
                        BlazingBamboo.id("bb_raft"),
                        () -> EntityType.Builder.<BBRaftEntity>of(getRaftEntityFactory(), MobCategory.MISC)
                                        .sized(1.375F, 0.5625F)
                                        .fireImmune()
                                        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation
                                                        .fromNamespaceAndPath(BlazingBamboo.MOD_ID, "bb_raft"))));
        public static final RegistrySupplier<EntityType<BBChestRaftEntity>> BB_CHEST_RAFT = ENTITY_TYPES.register(
                        BlazingBamboo.id("bb_chest_raft"),
                        () -> EntityType.Builder.<BBChestRaftEntity>of(getChestRaftEntityFactory(), MobCategory.MISC)
                                        .sized(1.375F, 0.5625F)
                                        .fireImmune()
                                        .build(ResourceKey.create(Registries.ENTITY_TYPE, ResourceLocation
                                                        .fromNamespaceAndPath(BlazingBamboo.MOD_ID, "bb_chest_raft"))));
        
        @ExpectPlatform
        public static EntityFactory<BBRaftEntity> getRaftEntityFactory() {
                throw new AssertionError();
        }

        @ExpectPlatform
        public static EntityFactory<BBChestRaftEntity> getChestRaftEntityFactory() {
                throw new AssertionError();
        }

        public static void registerEntities() {
        }
}
