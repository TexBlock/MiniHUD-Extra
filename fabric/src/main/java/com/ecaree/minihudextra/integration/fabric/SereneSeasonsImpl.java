package com.ecaree.minihudextra.integration.fabric;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.world.World;
import sereneseasons.api.season.ISeasonState;
import sereneseasons.api.season.SeasonHelper;

public class SereneSeasonsImpl {
    public static int getDayOfSeason(World world) {
        ISeasonState state = SeasonHelper.getSeasonState(world);
        return state.getDay() % (state.getSeasonDuration() / state.getDayDuration());
    }

    public static int getDayOfSubSeason(World world) {
        ISeasonState state = SeasonHelper.getSeasonState(world);
        return state.getDay() % (state.getSubSeasonDuration() / state.getDayDuration());
    }

    public static String getSeasonName(World world) {
        return switch (SeasonHelper.getSeasonState(world).getSeason()) {
            case SPRING -> I18n.translate("desc.sereneseasons.spring");
            case SUMMER -> I18n.translate("desc.sereneseasons.summer");
            case AUTUMN -> I18n.translate("desc.sereneseasons.autumn");
            case WINTER -> I18n.translate("desc.sereneseasons.winter");
        };
    }

    public static String getSubSeasonName(World world) {
        return switch(SeasonHelper.getSeasonState(world).getSubSeason()) {
            case EARLY_SPRING -> I18n.translate("desc.minihudextra.early_spring");
            case MID_SPRING -> I18n.translate("desc.minihudextra.mid_spring");
            case LATE_SPRING -> I18n.translate("desc.minihudextra.late_spring");
            case EARLY_SUMMER -> I18n.translate("desc.minihudextra.early_summer");
            case MID_SUMMER -> I18n.translate("desc.minihudextra.mid_summer");
            case LATE_SUMMER -> I18n.translate("desc.minihudextra.late_summer");
            case EARLY_AUTUMN -> I18n.translate("desc.minihudextra.early_autumn");
            case MID_AUTUMN -> I18n.translate("desc.minihudextra.mid_autumn");
            case LATE_AUTUMN -> I18n.translate("desc.minihudextra.late_autumn");
            case EARLY_WINTER -> I18n.translate("desc.minihudextra.early_winter");
            case MID_WINTER -> I18n.translate("desc.minihudextra.mid_winter");
            case LATE_WINTER -> I18n.translate("desc.minihudextra.late_winter");
        };
    }

    public static String getTropicalSeasonName(World world) {
        return switch(SeasonHelper.getSeasonState(world).getTropicalSeason()) {
            case EARLY_WET -> I18n.translate("desc.minihudextra.early_wet");
            case MID_WET -> I18n.translate("desc.minihudextra.mid_wet");
            case LATE_WET -> I18n.translate("desc.minihudextra.late_wet");
            case EARLY_DRY -> I18n.translate("desc.minihudextra.early_dry");
            case MID_DRY -> I18n.translate("desc.minihudextra.mid_dry");
            case LATE_DRY -> I18n.translate("desc.minihudextra.late_dry");
        };
    }
}