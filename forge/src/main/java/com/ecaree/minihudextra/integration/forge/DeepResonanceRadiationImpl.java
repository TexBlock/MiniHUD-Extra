package com.ecaree.minihudextra.integration.forge;

import mcjty.deepresonance.modules.radiation.item.RadiationMonitorItem;
import net.minecraft.entity.player.PlayerEntity;

public class DeepResonanceRadiationImpl {
    public static float getRadiation(PlayerEntity player) {
        RadiationMonitorItem.fetchRadiation(player);
        return RadiationMonitorItem.radiationStrength;
    }
}