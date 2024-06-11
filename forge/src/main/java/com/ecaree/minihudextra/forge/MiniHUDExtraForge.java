package com.ecaree.minihudextra.forge;

import com.ecaree.minihudextra.InitHandler;
import com.ecaree.minihudextra.MiniHUDExtra;
import com.ecaree.minihudextra.config.GuiConfigs;
import fi.dy.masa.malilib.event.InitializationHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.thinkingstudio.mafglib.util.ForgePlatformUtils;

@Mod(MiniHUDExtra.MOD_ID)
public class MiniHUDExtraForge {
    public MiniHUDExtraForge() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
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