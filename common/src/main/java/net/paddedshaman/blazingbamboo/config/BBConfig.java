package net.paddedshaman.blazingbamboo.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class BBConfig {
    public static final BBConfig INSTANCE = new BBConfig();

    private int bambooMaxHeight = 13;
    private int bambooHeightVariance = 5;
    private int bambooExtinguishChance = 20;
    private boolean sneakSafely = true;
    private int raftSpeedMultiplier = 70;

    public static int bambooMaxHeight() {
        return INSTANCE.bambooMaxHeight;
    }

    public static int bambooHeightVariance() {
        return INSTANCE.bambooHeightVariance;
    }

    public static float bambooExtinguishChance() {
        return INSTANCE.bambooExtinguishChance / 100.0f;
    }

    public static boolean sneakSafely() {
        return INSTANCE.sneakSafely;
    }

    public static float raftSpeedMultiplier() {
        return INSTANCE.raftSpeedMultiplier / 100.0f;
    }

    public static Screen createConfig(Screen parentScreen) {
        return YetAnotherConfigLib.createBuilder()
            .title(Component.literal("Blazing Bamboo Continued"))
            .category(ConfigCategory.createBuilder()
                .name(Component.literal("Blazing Bamboo Continued"))
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.bamboo_max_height"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.bamboo_max_height")))
                    .binding(13, () -> INSTANCE.bambooMaxHeight, newVal -> INSTANCE.bambooMaxHeight = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 64)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.bamboo_max_height.format", val)))
                    .build())
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.height_variance"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.height_variance.desc")))
                    .binding(5, () -> INSTANCE.bambooHeightVariance, newVal -> INSTANCE.bambooHeightVariance = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 64)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.height_variance.format", val)))
                    .build())
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.bamboo_extinguish_chance"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.bamboo_extinguish_chance.desc")))
                    .binding(20, () -> INSTANCE.bambooExtinguishChance, newVal -> INSTANCE.bambooExtinguishChance = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 100)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.bamboo_extinguish_chance.format", val)))
                    .build())
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.raft_speed"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.raft_speed.desc")))
                    .binding(70, () -> INSTANCE.raftSpeedMultiplier, newVal -> INSTANCE.raftSpeedMultiplier = newVal)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 100)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.raft_speed.format", val)))
                    .build())
                .option(Option.<Boolean>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.sneak_safely"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.sneak_safely.desc")))
                    .binding(true, () -> INSTANCE.sneakSafely, newVal -> INSTANCE.sneakSafely = newVal)
                    .controller(TickBoxControllerBuilder::create)
                    .build())
                .build()
            ).build()
            .generateScreen(parentScreen);  
    }    
}
