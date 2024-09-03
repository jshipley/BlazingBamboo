package net.paddedshaman.blazingbamboo.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBItems {
    public static final Item BLAZING_BAMBOO_ITEM = registerItem("blazing_bamboo_item",
            new BlockItem(BBBlocks.BLAZING_BAMBOO, new Item.Properties().fireResistant()));
    public static final Item BLAZING_BAMBOO_BUNDLE = registerItem("blazing_bamboo_bundle",
            new BlockItem(BBBlocks.BLAZING_BAMBOO_BUNDLE, new Item.Properties().fireResistant()));
    public static final Item BLAZING_BAMBOO_SIGN = registerItem("blazing_bamboo_sign",
            new SignItem(new Item.Properties().fireResistant().stacksTo(16), BBBlocks.BLAZING_BAMBOO_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_SIGN));
    public static final Item BLAZING_BAMBOO_HANGING_SIGN = registerItem("blazing_bamboo_hanging_sign",
            new HangingSignItem(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN, BBBlocks.BLAZING_BAMBOO_WALL_HANGING_SIGN,
                    new Item.Properties().fireResistant().stacksTo(16)));
    public static final Item BLAZING_BAMBOO_RAFT = registerItem("blazing_bamboo_raft",
            new BBRaftItem(false, Boat.Type.BAMBOO, new Item.Properties().fireResistant().stacksTo(1)));
    public static final Item BLAZING_BAMBOO_CHEST_RAFT = registerItem("blazing_bamboo_chest_raft",
            new BBRaftItem(true, Boat.Type.BAMBOO, new Item.Properties().fireResistant().stacksTo(1)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, name), item);
    }
    public static void registerItems() {}
}
