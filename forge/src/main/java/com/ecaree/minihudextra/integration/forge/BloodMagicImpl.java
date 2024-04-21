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

    public static String getOrbTierName(PlayerEntity player) {
        return I18n.translate("desc.minihudextra.orb_tier_" + getOrbTier(player));
    }
}