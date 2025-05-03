package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BBItemTagProvider extends FabricTagProvider<Item> {

    public BBItemTagProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(dataOutput, Registries.ITEM, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registryLookup) {
        getOrCreateTagBuilder(BBTags.Items.BLAZING_BAMBOO_BLOCKS)
            .add(BBBlocks.BLAZING_BAMBOO_BUNDLE.get().asItem(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get().asItem());

        getOrCreateTagBuilder(ItemTags.BOATS).add(BBItems.BLAZING_BAMBOO_RAFT.get());
        getOrCreateTagBuilder(ItemTags.CHEST_BOATS).add(BBItems.BLAZING_BAMBOO_CHEST_RAFT.get());
        getOrCreateTagBuilder(ItemTags.DOORS).add(BBBlocks.BLAZING_BAMBOO_DOOR.get().asItem());
        getOrCreateTagBuilder(ItemTags.FENCE_GATES).add(BBBlocks.BLAZING_BAMBOO_FENCE_GATE.get().asItem());
        getOrCreateTagBuilder(ItemTags.FENCES).add(BBBlocks.BLAZING_BAMBOO_FENCE.get().asItem());
        getOrCreateTagBuilder(ItemTags.HANGING_SIGNS).add(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get().asItem());
        getOrCreateTagBuilder(ItemTags.PLANKS).add(BBBlocks.BLAZING_BAMBOO_PLANKS.get().asItem());
        getOrCreateTagBuilder(ItemTags.SIGNS).add(BBBlocks.BLAZING_BAMBOO_SIGN.get().asItem());
        getOrCreateTagBuilder(ItemTags.SLABS).add(BBBlocks.BLAZING_STONE_SLAB.get().asItem(), BBBlocks.BLAZING_BRICK_SLAB.get().asItem());
        getOrCreateTagBuilder(ItemTags.STAIRS).add(BBBlocks.BLAZING_STONE_STAIRS.get().asItem(), BBBlocks.BLAZING_BRICK_STAIRS.get().asItem());
        getOrCreateTagBuilder(ItemTags.STONE_BUTTONS).add(BBBlocks.BLAZING_STONE_BUTTON.get().asItem());
        getOrCreateTagBuilder(ItemTags.TRAPDOORS).add(BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get().asItem());
        getOrCreateTagBuilder(ItemTags.WALLS).add(BBBlocks.BLAZING_BRICK_WALL.get().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS).add(BBBlocks.BLAZING_BAMBOO_BUTTON.get().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS).add(BBBlocks.BLAZING_BAMBOO_SLAB.get().asItem(), BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS).add(BBBlocks.BLAZING_BAMBOO_STAIRS.get().asItem(), BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get().asItem());
        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS).add(BBBlocks.BLAZING_BAMBOO_TRAPDOOR.get().asItem());
    }    
}
