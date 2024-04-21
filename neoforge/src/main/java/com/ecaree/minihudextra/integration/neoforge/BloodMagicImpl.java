package com.ecaree.minihudextra.integration.neoforge;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;

@SuppressWarnings("unused")
public class BloodMagicImpl {
    public static int getLP(PlayerEntity player) {
        return 0;
    }

    public static int getOrbTier(PlayerEntity player) {
        return 0;
    }

    public static String getOrbTierName(PlayerEntity player) {
        return I18n.translate("desc.minihudextra.not_available");
    }
}