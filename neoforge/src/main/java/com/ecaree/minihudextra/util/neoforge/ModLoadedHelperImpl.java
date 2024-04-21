package com.ecaree.minihudextra.util.neoforge;

import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

@SuppressWarnings("unused")
public class ModLoadedHelperImpl {
    public static boolean isModLoaded(String modID) {
        if (ModList.get() == null) {
            // 此时的 ModList 还未初始化
            return FMLLoader.getLoadingModList().getModFileById(modID) != null;
        }
        return ModList.get().isLoaded(modID);
    }
}