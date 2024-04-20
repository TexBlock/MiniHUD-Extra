package com.ecaree.minihudextra.integration;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.World;

public class SereneSeasons {
    @ExpectPlatform
    public static int getDay(World world) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int getDayOfSeason(World world) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static String getSeasonName(World world) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static String getSubSeasonName(World world) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static String getTropicalSeasonName(World world) {
        throw new AssertionError();
    }
}