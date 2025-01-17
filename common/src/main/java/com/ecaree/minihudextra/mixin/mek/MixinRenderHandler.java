package com.ecaree.minihudextra.mixin.mek;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.config.MHExInfoToggle;
import com.ecaree.minihudextra.integration.MekRadiation;
import com.ecaree.minihudextra.util.ChunkLoadedHelper;
import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.event.RenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderHandler.class, remap = false)
public abstract class MixinRenderHandler {
    @Shadow @Final private MinecraftClient mc;
    @Shadow protected abstract void addLine(String text);

    @Inject(method = "addLine(Lfi/dy/masa/minihud/config/InfoToggle;)V", at = @At("TAIL"))
    private void onAddLine(InfoToggle toggle, CallbackInfo ci) {
        MinecraftClient mc = this.mc;
        Entity entity = mc.getCameraEntity();
        ClientWorld world = mc.world;
        ClientPlayerEntity player = mc.player;
        if (entity == null || world == null || player == null) return;

        if (ChunkLoadedHelper.isChunkLoaded(entity, world)) {
            if (toggle.getName().equals(MHExInfoToggle.MEK_RADIATION.getName())) { // 有无更更好的实现方式？
                try {
                    String str = Configs.ModIntegration.MEK_RADIATION_FORMAT.getStringValue();
                    str = str.replace("{RADIATION_EXPOSURE}",  String.format("%s", MekRadiation.getRadiationString(player)));
                    str = str.replace("{DECAY_TIME}",String.format("%s", MekRadiation.getDecayTime(player)));
                    this.addLine(str);
                } catch (Exception e) {
                    this.addLine("Mek Radiation Format Failed");
                }
            }
        }
    }
}