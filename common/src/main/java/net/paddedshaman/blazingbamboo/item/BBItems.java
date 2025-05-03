package net.paddedshaman.blazingbamboo.item;

import java.util.function.Function;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BoatItem;
import net.minecraft.world.item.Item;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.entity.BBEntities;

public class BBItems {
    public static final Registrar<Item> ITEMS = BlazingBamboo.REGISTRY_MANAGER.get().get(Registries.ITEM);

    // Most block items are registered with the blocks, and can be referenced with Block.asItem()
    // Anywhere that accepts an ItemLike can take the block directly

    public static final RegistrySupplier<Item> BLAZING_BAMBOO_RAFT = registerItem(
            "blazing_bamboo_raft",
            (props) -> new BoatItem(BBEntities.BB_RAFT.get(), props),
            new Item.Properties().fireResistant().stacksTo(1));
    public static final RegistrySupplier<Item> BLAZING_BAMBOO_CHEST_RAFT = registerItem(
            "blazing_bamboo_chest_raft",
            (props) -> new BoatItem(BBEntities.BB_CHEST_RAFT.get(), props),
            new Item.Properties().fireResistant().stacksTo(1));

    // Pass in the item properties separately so it's easier to add the item id to it without adding a bunch more static variables
    public static RegistrySupplier<Item> registerItem(String name, Function<Item.Properties, Item> constructor, Item.Properties properties) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);
        return ITEMS.register(id, () -> constructor.apply(properties.setId(key)));
    }

    public static void registerItems() {
    }
}
