package com.ecaree.minihudextra.mixin.sereneseasons;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.integration.SereneSeasons;
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

    @SuppressWarnings("RedundantCast")
    @Inject(method = "addLine(Lfi/dy/masa/minihud/config/InfoToggle;)V", at = @At("TAIL"))
    private void onAddLine(InfoToggle toggle, CallbackInfo ci) {
        MinecraftClient mc = this.mc;
        Entity entity = mc.getCameraEntity();
        ClientWorld world = mc.world;
        ClientPlayerEntity player = mc.player;
        if (entity == null || world == null || player == null) return;

        if (ChunkLoadedHelper.isChunkLoaded(entity, world)) {
            if (Configs.ModIntegration.SERENE_SEASONS.getBooleanValue() &&
                    toggle.getIntegerValue() == Configs.ModIntegration.SERENE_SEASONS_LINE_POSITION.getIntegerValue()) {
                try {
                    String str = Configs.ModIntegration.SERENE_SEASONS_FORMAT.getStringValue();

                    // https://github.com/maruohon/minihud/blob/pre-rewrite/fabric/1.20.x/src/main/java/fi/dy/masa/minihud/event/RenderHandler.java#L322-L350
                    long timeDay = world.getTimeOfDay();
                    long day = (int) (timeDay / 24000);
                    int dayTicks = (int) (timeDay % 24000);
                    int hour = (int) ((dayTicks / 1000) + 6) % 24;
                    int min = (int) (dayTicks / 16.666666) % 60;
                    int sec = (int) (dayTicks / 0.277777) % 60;
                    String moon = "Invalid";
                    switch ((int) day % 8) {
                        case 0: moon = "Full moon"; break;
                        case 1: moon = "Waning gibbous"; break;
                        case 2: moon = "Last quarter"; break;
                        case 3: moon = "Waning crescent"; break;
                        case 4: moon = "New moon"; break;
                        case 5: moon = "Waxing crescent"; break;
                        case 6: moon = "First quarter"; break;
                        case 7: moon = "Waxing gibbous"; break;
                        default:
                    }
                    str = str.replace("{DAY}",  String.format("%d", day));
                    str = str.replace("{DAY_1}",String.format("%d", day + 1));
                    str = str.replace("{HOUR}", String.format("%02d", hour));
                    str = str.replace("{MIN}",  String.format("%02d", min));
                    str = str.replace("{SEC}",  String.format("%02d", sec));
                    str = str.replace("{MOON}",  String.format("%s", moon));

                    str = str.replace("{DAY_OF_SEASON}", String.format("%d", SereneSeasons.getDayOfSeason(world)));
                    str = str.replace("{DAY_OF_SUB_SEASON}", String.format("%d", SereneSeasons.getDayOfSubSeason(world)));
                    str = str.replace("{SEASON_NAME}", String.format("%s", SereneSeasons.getSeasonName(world)));
                    str = str.replace("{SUB_SEASON_NAME}", String.format("%s", SereneSeasons.getSubSeasonName(world)));
                    str = str.replace("{TROPICAL_SEASON_NAME}", String.format("%s", SereneSeasons.getTropicalSeasonName(world)));
                    this.addLine(str);
                } catch (Exception e) {
                    this.addLine("Serene Seasons Format Failed");
                }
            }
        }
    }
}