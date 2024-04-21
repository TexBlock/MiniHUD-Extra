package com.ecaree.minihudextra.integration.forge;

import mekanism.common.lib.radiation.RadiationManager;
import mekanism.common.util.UnitDisplayUtils;
import net.minecraft.entity.player.PlayerEntity;

public class MekRadiationImpl {
    public static String getRadiationString(PlayerEntity player) {
        RadiationManager.LevelAndMaxMagnitude levelAndMaxMagnitude = RadiationManager.get().getRadiationLevelAndMaxMagnitude(player);
        double magnitude = levelAndMaxMagnitude.level();
        String colorCode = RadiationManager.RadiationScale.getSeverityColor(magnitude).code;
        return colorCode + UnitDisplayUtils.getDisplayShort(magnitude, UnitDisplayUtils.RadiationUnit.SVH, 3).getString();
        // TODO
//        TextUtils.getHoursMinutes(RadiationManager.get().getDecayTime(levelAndMaxMagnitude.maxMagnitude(), true))
    }
}