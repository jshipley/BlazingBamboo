package net.paddedshaman.blazingbamboo.item;

import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.entity.BBRaftEntity;

public class BBItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BlazingBamboo.MOD_ID);

    public static final RegistryObject<Item> BLAZING_BAMBOO_ITEM = ITEMS.register("blazing_bamboo_item",
            () -> new BlockItem(BBBlocks.BLAZING_BAMBOO.get(), (new Item.Properties().fireResistant())));
    public static final RegistryObject<Item> BLAZING_BAMBOO_BUNDLE = ITEMS.register("blazing_bamboo_bundle",
            () -> new BlockItem(BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), (new Item.Properties().fireResistant())));
    public static final RegistryObject<Item> BLAZING_BAMBOO_SIGN = ITEMS.register("blazing_bamboo_sign",
            () -> new SignItem(new Item.Properties().fireResistant().stacksTo(16), BBBlocks.BLAZING_BAMBOO_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_SIGN.get()));
    public static final RegistryObject<Item> BLAZING_BAMBOO_HANGING_SIGN = ITEMS.register("blazing_bamboo_hanging_sign",
            () -> new HangingSignItem(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get(), BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN.get(),
                    new Item.Properties().fireResistant().stacksTo(16)));
    public static final RegistryObject<Item> BLAZING_BAMBOO_RAFT = ITEMS.register("blazing_bamboo_raft",
            () -> new BBRaftItem(false, Boat.Type.BAMBOO, new Item.Properties().fireResistant().stacksTo(1)));
    public static final RegistryObject<Item> BLAZING_BAMBOO_CHEST_RAFT = ITEMS.register("blazing_bamboo_chest_raft",
            () -> new BBRaftItem(true, Boat.Type.BAMBOO, new Item.Properties().fireResistant().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
