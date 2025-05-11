package net.paddedshaman.blazingbamboo;

import java.util.function.Supplier;

import org.slf4j.Logger;

import com.google.common.base.Suppliers;
import com.mojang.logging.LogUtils;

import dev.architectury.registry.fuel.FuelRegistry;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.core.dispenser.BoatDispenseItemBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.DispenserBlock;
import net.paddedshaman.blazingbamboo.block.BBBlocks;
import net.paddedshaman.blazingbamboo.block.entity.BBBlockEntities;
import net.paddedshaman.blazingbamboo.config.BBConfig;
import net.paddedshaman.blazingbamboo.entity.BBEntities;
import net.paddedshaman.blazingbamboo.item.BBCreativeModeTabs;
import net.paddedshaman.blazingbamboo.item.BBItems;
import net.paddedshaman.blazingbamboo.worldgen.BBFeatures;

public class BlazingBamboo {
	public static final String MOD_ID = "blazingbamboo";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final Supplier<RegistrarManager> REGISTRY_MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

	public static void init() {
		BBConfig.HANDLER.load();
		
		BBCreativeModeTabs.registerCreativeModeTabs();
		BBItems.registerItems();
		BBBlocks.registerBlocks();
		BBBlockEntities.registerBlockEntities();
		BBEntities.registerEntities();
		BBFeatures.registerFeatures();

		BBBlocks.BLAZING_BAMBOO.listen((blockItem) -> FuelRegistry.register(800, blockItem));
		BBBlocks.BLAZING_BAMBOO_BUNDLE.listen((blockItem) -> FuelRegistry.register(8000, blockItem));

		// Because of Minecraft's 'planks' and 'wooden_slabs' tags, these two items inherit a burn time that needs to be nullified manually.
		BBBlocks.BLAZING_BAMBOO_PLANKS.listen((blockItem) -> FuelRegistry.register(0, blockItem));
		BBBlocks.BLAZING_BAMBOO_SLAB.listen((blockItem) -> FuelRegistry.register(0, blockItem));

		BBItems.BLAZING_BAMBOO_RAFT.listen((raft) -> DispenserBlock.registerBehavior(raft, new BoatDispenseItemBehavior(BBEntities.BB_RAFT.get())));
		BBItems.BLAZING_BAMBOO_CHEST_RAFT.listen((raft) -> DispenserBlock.registerBehavior(raft, new BoatDispenseItemBehavior(BBEntities.BB_CHEST_RAFT.get())));
	}

	public static ResourceLocation id(String path) {
		return ResourceLocation.fromNamespaceAndPath(BlazingBamboo.MOD_ID, path);
	}
}