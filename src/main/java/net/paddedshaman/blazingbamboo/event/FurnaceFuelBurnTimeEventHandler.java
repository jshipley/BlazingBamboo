package net.paddedshaman.blazingbamboo.event;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.item.BBItems;

public class FurnaceFuelBurnTimeEventHandler {
    public static final FurnaceFuelBurnTimeEventHandler instance = new FurnaceFuelBurnTimeEventHandler();
    private FurnaceFuelBurnTimeEventHandler() {};

    @SubscribeEvent
    public void onFurnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        ItemStack fuel = event.getItemStack();
        if (fuel.getItem() == BBItems.BLAZING_BAMBOO_ITEM.get()) { event.setBurnTime(800); }
        if (fuel.getItem() == BBItems.BLAZING_BAMBOO_BUNDLE.get()) { event.setBurnTime(8000); }

        if (fuel.getItem() == BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_PLANKS.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_MOSAIC.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_STAIRS.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_SLAB.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_BUTTON.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_PRESSURE_PLATE.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_FENCE.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_FENCE_GATE.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_DOOR.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get().asItem()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBItems.BLAZING_BAMBOO_SIGN.get()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBItems.BLAZING_BAMBOO_HANGING_SIGN.get()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBItems.BLAZING_BAMBOO_RAFT.get()) { event.setBurnTime(0); }
        if (fuel.getItem() == BBItems.BLAZING_BAMBOO_CHEST_RAFT.get()) { event.setBurnTime(0); }
    }
}
