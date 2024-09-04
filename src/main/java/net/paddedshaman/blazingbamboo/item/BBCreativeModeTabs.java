package net.paddedshaman.blazingbamboo.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;

import java.util.function.Supplier;

public class BBCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BlazingBamboo.MOD_ID);

    public static final Supplier<CreativeModeTab> BLAZING_BAMBOO_TAB = CREATIVE_MODE_TABS.register("blazing_bamboo_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(BBItems.BLAZING_BAMBOO_ITEM.get()))
                    .title(Component.translatable("creativetab.blazing_bamboo_tab"))
                    .displayItems((itemDisplayParameters, output) -> {

                        output.accept(BBItems.BLAZING_BAMBOO_BUNDLE.get());
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
                        output.accept(BBItems.BLAZING_BAMBOO_ITEM.get());

                        output.accept(BBItems.BLAZING_BAMBOO_SIGN.get());
                        output.accept(BBItems.BLAZING_BAMBOO_HANGING_SIGN.get());

                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
