package com.ecaree.minihudextra.neoforge;

import com.ecaree.minihudextra.InitHandler;
import com.ecaree.minihudextra.MiniHUDExtra;
import com.ecaree.minihudextra.config.GuiConfigs;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import org.thinkingstudio.mafglib.util.ForgePlatformUtils;

@Mod(MiniHUDExtra.MOD_ID)
public class MiniHUDExtraNeoForge {
    public MiniHUDExtraNeoForge(IEventBus modEventBus) {
        modEventBus.addListener(this::onInitializeClient);
        MiniHUDExtra.init();
    }

    public void onInitializeClient(FMLClientSetupEvent event) {
        ForgePlatformUtils.getInstance().getClientModIgnoredServerOnly();
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
        ForgePlatformUtils.getInstance().registerModConfigScreen(MiniHUDExtra.MOD_ID, screen -> {
            GuiConfigs gui = new GuiConfigs();
            gui.setParent(screen);
            return gui;
        });
    }
}