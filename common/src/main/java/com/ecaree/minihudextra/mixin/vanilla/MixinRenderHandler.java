package com.ecaree.minihudextra.mixin.vanilla;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.util.ChunkLoadedHelper;
import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.event.RenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.I18n;
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
            if (Configs.Vanilla.MOON_PHASE.getBooleanValue() &&
                    toggle.getIntegerValue() == Configs.Vanilla.MOON_PHASE_LINE_POSITION.getIntegerValue()) {
                try {
                    String str = Configs.Vanilla.MOON_PHASE_FORMAT.getStringValue();

                    int moonPhase = world.getMoonPhase();
                    int moonPhase1 = moonPhase + 1;
                    String moonPhaseStr = I18n.translate("desc.minihudextra.moon_phase_" + moonPhase1);
                    str = str.replace("{MOON_PHASE_1}", String.format("%d", moonPhase1));
                    str = str.replace("{MOON_PHASE}", String.format("%d", moonPhase));
                    str = str.replace("{MOON_PHASE_STR}", String.format("%s", moonPhaseStr));
                    this.addLine(str);
                } catch (Exception e) {
                    this.addLine("Moon Phase Format Error");
                }
            }


        }
    }
}