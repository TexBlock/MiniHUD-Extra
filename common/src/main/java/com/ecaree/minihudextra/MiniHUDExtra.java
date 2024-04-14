package com.ecaree.minihudextra;

import com.ecaree.minihudextra.util.UltimineMiniHUDHandler;
import dev.architectury.event.events.client.ClientRawInputEvent;
import fi.dy.masa.malilib.event.InitializationHandler;

public class MiniHUDExtra {
    public static final String MOD_ID = "minihudextra";
    public static final String MOD_NAME = "MiniHUDExtra";
    public static void init() {
        InitializationHandler.getInstance().registerInitializationHandler(new InitHandler());
        ClientRawInputEvent.KEY_PRESSED.register(UltimineMiniHUDHandler::onKeyPress);
    }
}