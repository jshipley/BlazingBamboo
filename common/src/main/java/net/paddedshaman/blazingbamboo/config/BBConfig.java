package net.paddedshaman.blazingbamboo.config;

import com.google.gson.GsonBuilder;
import dev.architectury.platform.Platform;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.paddedshaman.blazingbamboo.BlazingBamboo;

@Accessors(fluent=true)
public class BBConfig {
    public static ConfigClassHandler<BBConfig> HANDLER = ConfigClassHandler.createBuilder(BBConfig.class)
        .id(BlazingBamboo.id("blazing_bamboo_continued_config"))
        .serializer(config -> GsonConfigSerializerBuilder.create(config)
            .setPath(Platform.getConfigFolder().resolve("blazing_bamboo_continued.json5"))
            .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
            .setJson5(true)
            .build())
        .build();

    public static final int defaultBambooMaxHeight = 13;
    @Getter @Setter @SerialEntry
    public static int bambooMaxHeight = defaultBambooMaxHeight;

    public static final int defaultBambooHeightVariance = 5;
    @Getter @Setter @SerialEntry
    public static int bambooHeightVariance = defaultBambooHeightVariance;

    public static final int defaultBambooExtinguishChance = 20;
    @Getter @Setter @SerialEntry
    public static int bambooExtinguishChance = defaultBambooExtinguishChance;

    public static final boolean defaultSneakSafely = true;
    @Getter @Setter @SerialEntry
    public static boolean sneakSafely = defaultSneakSafely;

    public static final int defaultRaftSpeedMultiplier = 70;
    @Getter @Setter @SerialEntry
    public static int raftSpeedMultiplier = defaultRaftSpeedMultiplier;

    public static Screen createConfig(Screen parentScreen) {
        return YetAnotherConfigLib.createBuilder()
            .save(() -> HANDLER.save())
            .title(Component.literal("Blazing Bamboo Continued"))
            .category(ConfigCategory.createBuilder()
                .name(Component.literal("Blazing Bamboo Continued"))
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.bamboo_max_height"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.bamboo_max_height.desc")))
                    .binding(defaultBambooMaxHeight, BBConfig::bambooMaxHeight, BBConfig::bambooMaxHeight)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 64)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.bamboo_max_height.format", val)))
                    .build())
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.height_variance"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.height_variance.desc")))
                    .binding(defaultBambooHeightVariance, BBConfig::bambooHeightVariance, BBConfig::bambooHeightVariance)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 64)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.height_variance.format", val)))
                    .build())
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.bamboo_extinguish_chance"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.bamboo_extinguish_chance.desc")))
                    .binding(defaultBambooExtinguishChance, BBConfig::bambooExtinguishChance, BBConfig::bambooExtinguishChance)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 100)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.bamboo_extinguish_chance.format", val)))
                    .build())
                .option(Option.<Integer>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.raft_speed"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.raft_speed.desc")))
                    .binding(defaultRaftSpeedMultiplier, BBConfig::raftSpeedMultiplier, BBConfig::raftSpeedMultiplier)
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(0, 100)
                        .step(1)
                        .valueFormatter(val -> Component.translatable("config.blazingbamboo.raft_speed.format", val)))
                    .build())
                .option(Option.<Boolean>createBuilder()
                    .name(Component.translatable("config.blazingbamboo.sneak_safely"))
                    .description(OptionDescription.of(Component.translatable("config.blazingbamboo.sneak_safely.desc")))
                    .binding(defaultSneakSafely, BBConfig::sneakSafely, BBConfig::sneakSafely)
                    .controller(TickBoxControllerBuilder::create)
                    .build())
                .build()
            ).build()
            .generateScreen(parentScreen);  
    }    
}
