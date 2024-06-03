package net.paddedshaman.blazingbamboo.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBCreativeModeTabs {
    public static final CreativeModeTab BLAZING_BAMBOO = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(BlazingBamboo.MOD_ID, "blazing_bamboo_tab"),
            CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("creativetab.blazing_bamboo_tab"))
                    .icon(() -> new ItemStack(BBItems.BLAZING_BAMBOO_ITEM)).displayItems((itemDisplayParameters, output) -> {

                        output.accept(BBItems.BLAZING_BAMBOO_BUNDLE);
                        output.accept(BBBlocks.BLAZING_STONE);
                        output.accept(BBBlocks.BLAZING_STONE_STAIRS);
                        output.accept(BBBlocks.BLAZING_STONE_SLAB);
                        output.accept(BBBlocks.BLAZING_BRICKS);
                        output.accept(BBBlocks.BLAZING_BRICK_STAIRS);
                        output.accept(BBBlocks.BLAZING_BRICK_SLAB);
                        output.accept(BBBlocks.BLAZING_BRICK_WALL);
                        output.accept(BBBlocks.BLAZING_BRICKS_CHISELED);

                        output.accept(BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE);
                        output.accept(BBBlocks.BLAZING_BAMBOO_PLANKS);
                        output.accept(BBBlocks.BLAZING_BAMBOO_STAIRS);
                        output.accept(BBBlocks.BLAZING_BAMBOO_SLAB);
                        output.accept(BBBlocks.BLAZING_BAMBOO_MOSAIC);
                        output.accept(BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS);
                        output.accept(BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB);
                        output.accept(BBBlocks.BLAZING_BAMBOO_FENCE);
                        output.accept(BBBlocks.BLAZING_BAMBOO_FENCE_GATE);

                        output.accept(BBItems.BLAZING_BAMBOO_RAFT);
                        output.accept(BBItems.BLAZING_BAMBOO_CHEST_RAFT);
                        output.accept(BBBlocks.BLAZING_BAMBOO_DOOR);
                        output.accept(BBBlocks.BLAZING_BAMBOO_TRAPDOOR);
                        output.accept(BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE);
                        output.accept(BBBlocks.BLAZING_BAMBOO_BUTTON);
                        output.accept(BBBlocks.BLAZING_STONE_PRESSURE_PLATE);
                        output.accept(BBBlocks.BLAZING_STONE_BUTTON);
                        output.accept(BBItems.BLAZING_BAMBOO_ITEM);

                        output.accept(BBItems.BLAZING_BAMBOO_SIGN);
                        output.accept(BBItems.BLAZING_BAMBOO_HANGING_SIGN);

                    }).build());

    public static void registerCreativeModeTabs() {}
}
