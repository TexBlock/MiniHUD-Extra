package com.ecaree.minihudextra.util.fabric;

import net.fabricmc.loader.api.FabricLoader;

public class ModLoadedHelperImpl {
    public static boolean isModLoaded(String modID) {
        return FabricLoader.getInstance().isModLoaded(modID);
    }
}