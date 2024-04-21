package com.ecaree.minihudextra.integration.fabric;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;

public class MekRadiationImpl {
    public static String getRadiationString(PlayerEntity player) {
        return I18n.translate("desc.minihudextra.not_available");
    }

    public static String getDecayTime(PlayerEntity player) {
        return I18n.translate("desc.minihudextra.not_available");
    }
}