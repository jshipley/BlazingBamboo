package net.paddedshaman.blazingbamboo.item;

import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

public class BBCreativeModeTabs {
    private static final Registrar<CreativeModeTab> CREATIVE_MODE_TABS = BlazingBamboo.REGISTRY_MANAGER.get().get(Registries.CREATIVE_MODE_TAB);
    public static final RegistrySupplier<CreativeModeTab> BLAZING_BAMBOO = CREATIVE_MODE_TABS.register(
            BlazingBamboo.id("blazing_bamboo_tab"),
            () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).title(Component.translatable("creativetab.blazing_bamboo_tab"))
                    .icon(() -> new ItemStack(BBBlocks.BLAZING_BAMBOO.get())).displayItems((itemDisplayParameters, output) -> {

                        output.accept(BBBlocks.BLAZING_BAMBOO_BUNDLE.get());
                        output.accept(BBBlocks.BLAZING_STONE.get());
                        output.accept(BBBlocks.BLAZING_STONE_STAIRS.get());
                        output.accept(BBBlocks.BLAZING_STONE_SLAB.get());
                        output.accept(BBBlocks.BLAZING_BRICKS.get());
                        output.accept(BBBlocks.BLAZING_BRICK_STAIRS.get());
                        output.accept(BBBlocks.BLAZING_BRICK_SLAB.get());
                        output.accept(BBBlocks.BLAZING_BRICK_WALL.get());
                        output.accept(BBBlocks.BLAZING_BRICKS_CHISELED.get());

                        output.accept(BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_PLANKS.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_STAIRS.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_SLAB.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_MOSAIC.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_FENCE.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_FENCE_GATE.get());

                        output.accept(BBItems.BLAZING_BAMBOO_RAFT.get());
                        output.accept(BBItems.BLAZING_BAMBOO_CHEST_RAFT.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_DOOR.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_BUTTON.get());
                        output.accept(BBBlocks.BLAZING_STONE_PRESSURE_PLATE.get());
                        output.accept(BBBlocks.BLAZING_STONE_BUTTON.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO.get());

                        output.accept(BBBlocks.BLAZING_BAMBOO_SIGN.get());
                        output.accept(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get());
                    }).build());

    public static void registerCreativeModeTabs() {}
}
