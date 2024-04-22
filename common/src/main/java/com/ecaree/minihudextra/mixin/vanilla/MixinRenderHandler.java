package com.ecaree.minihudextra.mixin.vanilla;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.config.MHExInfoToggle;
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
            if (toggle.getName().equals(MHExInfoToggle.WEATHER.getName())) {
                try {
                    String str = Configs.Vanilla.WEATHER_FORMAT.getStringValue();

                    boolean isRaining = world.isRaining();
                    boolean isThundering = world.isThundering();
                    String weather = isRaining
                            ? isThundering ? I18n.translate("desc.minihudextra.weather_t") : I18n.translate("desc.minihudextra.weather_r")
                            : I18n.translate("desc.minihudextra.weather_c");
                    String weatherAbbr = isRaining
                            ? isThundering ? "T" : "R"
                            : "C";
                    str = str.replace("{IS_RAINING}", String.format("%b", isRaining));
                    str = str.replace("{IS_THUNDERING}", String.format("%b", isThundering));
                    str = str.replace("{WEATHER}", String.format("%s", weather));
                    str = str.replace("{WEATHER_ABBR}", String.format("%s", weatherAbbr));
                    this.addLine(str);
                } catch (Exception e) {
                    this.addLine("Weather Format Failed");
                }
            }
        }
    }
}