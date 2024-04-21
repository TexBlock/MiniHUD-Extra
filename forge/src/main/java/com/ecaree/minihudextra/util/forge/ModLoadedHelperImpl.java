package com.ecaree.minihudextra.util.forge;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ModLoadedHelperImpl {
    public static boolean isModLoaded(String modID) {
        if (ModList.get() == null) {
            // 此时的 ModList 还未初始化
            return FMLLoader.getLoadingModList().getModFileById(modID) != null;
        }
        return ModList.get().isLoaded(modID);
    }
}