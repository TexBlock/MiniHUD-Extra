package com.ecaree.minihudextra.integration.fabric;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.world.World;

public class SereneSeasonsImpl {
    public static int getDayOfSeason(World world) {
        return 0;
    }

    public static int getDayOfSubSeason(World world) {
        return 0;
    }

    public static String getSeasonName(World world) {
        return I18n.translate("desc.minihudextra.not_available");
    }

    public static String getSubSeasonName(World world) {
        return I18n.translate("desc.minihudextra.not_available");
    }

    public static String getTropicalSeasonName(World world) {
        return I18n.translate("desc.minihudextra.not_available");
    }
}