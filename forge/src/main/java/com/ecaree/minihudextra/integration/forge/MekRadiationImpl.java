package com.ecaree.minihudextra.integration.forge;

import mekanism.api.MekanismAPI;
import mekanism.common.lib.radiation.RadiationManager;
import mekanism.common.util.UnitDisplayUtils;
import net.minecraft.entity.player.PlayerEntity;

public class MekRadiationImpl {
    public static String getRadiationString(PlayerEntity player) {
        double magnitude = MekanismAPI.getRadiationManager().getRadiationLevel(player);
        String colorCode = RadiationManager.RadiationScale.getSeverityColor(magnitude).code;
        return colorCode + UnitDisplayUtils.getDisplayShort(magnitude, UnitDisplayUtils.RadiationUnit.SVH, 3).asString();
    }
}