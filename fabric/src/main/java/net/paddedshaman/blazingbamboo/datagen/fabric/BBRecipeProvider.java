package net.paddedshaman.blazingbamboo.datagen.fabric;

import java.util.concurrent.CompletableFuture;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Items;
import net.paddedshaman.blazingbamboo.BlazingBamboo;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.util.BBTags;

public class BBRecipeProvider extends FabricRecipeProvider { 

    public BBRecipeProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput output) {
        return new RecipeProvider(registryLookup, output) {
            @Override
            public void buildRecipes() {

                // bundle
                nineBlockStorageRecipes(RecipeCategory.MISC, BBBlocks.BLAZING_BAMBOO.get(), RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_BUNDLE.get());
                // planks
                planksFromLog(BBBlocks.BLAZING_BAMBOO_PLANKS.get(), BBTags.Items.BLAZING_BAMBOO_BLOCKS, 2);
                // stone
                threeByThreePacker(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_STONE.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get());
                // bricks
                polished(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICKS.get(), BBBlocks.BLAZING_STONE.get());

                // family recipes
                for (var family : BBDataGenerator.BLAZING_BLOCK_FAMILIES) {
                    generateRecipes(family, FeatureFlagSet.of(FeatureFlags.VANILLA));
                }
                hangingSign(BBBlocks.BLAZING_BAMBOO_HANGING_SIGN.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get());
                chiseled(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICKS_CHISELED.get(), BBBlocks.BLAZING_BRICK_SLAB.get());


                
                // blaze items
                oneToOneConversionRecipe(Items.BLAZE_POWDER, BBBlocks.BLAZING_BAMBOO.get(), null);
                ShapedRecipeBuilder.shaped(registryLookup.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, Items.BLAZE_ROD)
                    .pattern("B")
                    .pattern("B")
                    .define('B', BBBlocks.BLAZING_BAMBOO.get())
                    .unlockedBy(getHasName(BBBlocks.BLAZING_BAMBOO.get()), has(BBBlocks.BLAZING_BAMBOO_BUNDLE.get()))
                    .save(output, ResourceKey.create(Registries.RECIPE, BlazingBamboo.id("blaze_rod_from_blazing_bamboo")));
                
                // rafts
                ShapedRecipeBuilder.shaped(registryLookup.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, BBItems.BLAZING_BAMBOO_RAFT.get())
                    .group("boat")
                    .pattern("B B")
                    .pattern("BBB")
                    .define('B', BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get())
                    .unlockedBy(getHasName(BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get()), has(BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get()))
                    .save(output, ResourceKey.create(Registries.RECIPE, BlazingBamboo.id("blazing_bamboo_raft")));
                ShapelessRecipeBuilder.shapeless(registryLookup.lookupOrThrow(Registries.ITEM), RecipeCategory.MISC, BBItems.BLAZING_BAMBOO_CHEST_RAFT.get())
                    .group("chest_boat")
                    .requires(BBItems.BLAZING_BAMBOO_RAFT.get())
                    .requires(ConventionalItemTags.WOODEN_CHESTS)
                    .unlockedBy(getHasName(BBItems.BLAZING_BAMBOO_RAFT.get()), has(BBItems.BLAZING_BAMBOO_RAFT.get()))
                    .save(output, ResourceKey.create(Registries.RECIPE, BlazingBamboo.id("blazing_bamboo_chest_raft")));
                
                // stripping the bundle
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get());

                // blazing bamboo stonecutter
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_PLANKS.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_PLANKS.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_SLAB.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_SLAB.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_SLAB.get(), BBBlocks.BLAZING_BAMBOO_PLANKS.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_STAIRS.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_STAIRS.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_STAIRS.get(), BBBlocks.BLAZING_BAMBOO_PLANKS.get());
                
                // blazing mosaic stonecutter
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC.get(), BBBlocks.BLAZING_BAMBOO_PLANKS.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), 4);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get(), BBBlocks.BLAZING_BAMBOO_PLANKS.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_SLAB.get(), BBBlocks.BLAZING_BAMBOO_MOSAIC.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get(), BBBlocks.BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get(), BBBlocks.STRIPPED_BLAZING_BAMBOO_BUNDLE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get(), BBBlocks.BLAZING_BAMBOO_PLANKS.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BAMBOO_MOSAIC_STAIRS.get(), BBBlocks.BLAZING_BAMBOO_MOSAIC.get());

                // blazing stone stonecutter
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_STONE_SLAB.get(), BBBlocks.BLAZING_STONE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_STONE_STAIRS.get(), BBBlocks.BLAZING_STONE.get());

                // blazing bricks stonecutter
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICKS.get(), BBBlocks.BLAZING_STONE.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICKS_CHISELED.get(), BBBlocks.BLAZING_STONE.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICKS_CHISELED.get(), BBBlocks.BLAZING_BRICKS.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICK_SLAB.get(), BBBlocks.BLAZING_STONE.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICK_SLAB.get(), BBBlocks.BLAZING_BRICKS.get(), 2);
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICK_STAIRS.get(), BBBlocks.BLAZING_STONE.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICK_STAIRS.get(), BBBlocks.BLAZING_BRICKS.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICK_WALL.get(), BBBlocks.BLAZING_STONE.get());
                stonecutterResultFromBase(RecipeCategory.BUILDING_BLOCKS, BBBlocks.BLAZING_BRICK_WALL.get(), BBBlocks.BLAZING_BRICKS.get());
            }
        };
    }

    @Override
    public String getName() {
        return "[Blazing Bamboo Continued] BBRecipeProvider";
    }    
}
