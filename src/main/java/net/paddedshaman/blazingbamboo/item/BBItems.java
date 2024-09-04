package net.paddedshaman.blazingbamboo.item;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

import java.util.function.Supplier;

public class BBItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(BuiltInRegistries.ITEM, BlazingBamboo.MOD_ID);

    public static final Supplier<Item> BLAZING_BAMBOO_ITEM = ITEMS.register("blazing_bamboo_item",
            () -> new BlockItem(BBBlocks.BLAZING_BAMBOO.get(), (new Item.Properties().fireResistant())));
    public static final Supplier<Item> BLAZING_BAMBOO_BUNDLE = ITEMS.register("blazing_bamboo_bundle",
            () -> new BlockItem(BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), (new Item.Properties().fireResistant())));
    public static final Supplier<Item> BLAZING_BAMBOO_SIGN = ITEMS.register("blazing_bamboo_sign",
            () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), BBBlocks.BLAZING_BAMBOO_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_SIGN.get()));
    public static final Supplier<Item> BLAZING_BAMBOO_HANGING_SIGN = ITEMS.register("blazing_bamboo_hanging_sign",
            () -> new HangingSignItem(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN.get(),
                    new Item.Properties().fireResistant().stacksTo(16)));
    public static final Supplier<Item> BLAZING_BAMBOO_RAFT = ITEMS.register("blazing_bamboo_raft",
            () -> new BBRaftItem(false, Boat.Type.BAMBOO, new Item.Properties().fireResistant().stacksTo(1)));
    public static final Supplier<Item> BLAZING_BAMBOO_CHEST_RAFT = ITEMS.register("blazing_bamboo_chest_raft",
            () -> new BBRaftItem(true, Boat.Type.BAMBOO, new Item.Properties().fireResistant().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
