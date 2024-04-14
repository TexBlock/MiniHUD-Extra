package com.ecaree.minihudextra.network;

import dev.architectury.event.EventResult;
import dev.architectury.platform.Platform;
import dev.ftb.mods.ftbultimine.client.FTBUltimineClient;
import fi.dy.masa.minihud.config.Configs;
import net.minecraft.client.MinecraftClient;

public class UltimineMiniHUDHandler {
    private static boolean wasEnabledInitially = false;

    public static void interactionEvents() {
        if (!Platform.isModLoaded("ftbultimine")) return;

        if (FTBUltimineClient.keyBinding.wasPressed()) {
            if (!wasEnabledInitially) {
                wasEnabledInitially = Configs.Generic.MAIN_RENDERING_TOGGLE.getBooleanValue();
                Configs.Generic.MAIN_RENDERING_TOGGLE.setBooleanValue(false);
            }
        } else {
            if (wasEnabledInitially) {
                Configs.Generic.MAIN_RENDERING_TOGGLE.setBooleanValue(true);
                wasEnabledInitially = false;
            }
        }
    }

    public static EventResult onKeyPress(MinecraftClient client, int keyCode, int scanCode, int action, int modifiers) {
        if (FTBUltimineClient.keyBinding.matchesKey(keyCode, scanCode) &&
                com.ecaree.minihudextra.config.Configs.Generic.FTB_ULTIMINE_SUPPORT.getBooleanValue()) {
            interactionEvents();
        }
        return EventResult.pass();
    }
}