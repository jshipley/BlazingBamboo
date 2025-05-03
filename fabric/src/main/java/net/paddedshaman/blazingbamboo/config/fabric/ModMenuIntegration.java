package net.paddedshaman.blazingbamboo.config.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.paddedshaman.blazingbamboo.config.BBConfig;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> BBConfig.createConfig(parentScreen);
    }
    
}
