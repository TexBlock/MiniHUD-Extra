package com.ecaree.minihudextra.integration.forge;

import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.player.PlayerEntity;
import wayoftime.bloodmagic.core.data.SoulNetwork;
import wayoftime.bloodmagic.util.helper.NetworkHelper;

public class BloodMagicImpl {
    public static int getLP(PlayerEntity player) {
        SoulNetwork soulNet = NetworkHelper.getSoulNetwork(player);
        return soulNet.getCurrentEssence();
    }

    public static int getOrbTier(PlayerEntity player) {
        SoulNetwork soulNet = NetworkHelper.getSoulNetwork(player);
        return soulNet.getOrbTier();
    }

    public static String getOrbTierStr(PlayerEntity player) {
        return switch (getOrbTier(player)) {
            case 1 -> I18n.translate("desc.minihudextra.orb_tier_1");
            case 2 -> I18n.translate("desc.minihudextra.orb_tier_2");
            case 3 -> I18n.translate("desc.minihudextra.orb_tier_3");
            case 4 -> I18n.translate("desc.minihudextra.orb_tier_4");
            case 5 -> I18n.translate("desc.minihudextra.orb_tier_5");
            default -> null;
        };
    }
}