package com.ecaree.minihudextra.mixin;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.integration.MekRadiation;
import com.ecaree.minihudextra.integration.SereneSeasons;
import dev.architectury.injectables.annotations.PlatformOnly;
import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.event.RenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 思路及本地化来自 https://github.com/plusls/MasaGadget
@Mixin(value = RenderHandler.class, remap = false)
public abstract class MixinRenderHandler {
    @Shadow @Final private MinecraftClient mc;

    @Shadow protected abstract void addLine(String text);

    private static final Pattern ARGS_PATTERN = Pattern.compile("%\\.\\d+f");
    private static final Pattern LOCALIZATION_PATTERN = Pattern.compile("([\\w ]+): ");

    @PlatformOnly(PlatformOnly.FORGE)
    @Redirect(
            method = "addLine(Lfi/dy/masa/minihud/config/InfoToggle;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;"
            )
    )
    private String onStringFormat(String format, Object[] args) {
        if (Configs.Generic.MINIHUD_I18N.getBooleanValue() && I18n.hasTranslation(format)) {
            if (isCustomCoordinateFormat(format)) {
                String customCoordinateFormat = fi.dy.masa.minihud.config.Configs.Generic.COORDINATE_FORMAT_STRING.getStringValue();
                return String.format(customCoordinateFormat, args);
            } else {
                Object[] preprocessedArgs = preprocessArgs(format, args);
                format = localizeString(format);
                return I18n.translate(format, preprocessedArgs);
            }
        } else {
            return String.format(format, args);
        }
    }

    @PlatformOnly(PlatformOnly.FORGE)
    @ModifyVariable(
            method = "addLine(Ljava/lang/String;)V",
            at = @At(
                    value = "HEAD"
            ),
            ordinal = 0
    )
    private String onAddLine(String string) {
        if (Configs.Generic.MINIHUD_I18N.getBooleanValue()) {
            string = localizeString(string);
        }
        return string;
    }

    // 自定义坐标格式化发生格式化错误处理
    private boolean isCustomCoordinateFormat(String format) {
        return format.equals(fi.dy.masa.minihud.config.Configs.Generic.COORDINATE_FORMAT_STRING.getStringValue());
    }

    // 格式化参数预处理
    private Object[] preprocessArgs(String format, Object[] args) {
        Matcher matcher = ARGS_PATTERN.matcher(format);
        List<String> formats = new ArrayList<>();
        while (matcher.find()) {
            formats.add(matcher.group());
        }

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof Double || args[i] instanceof Float) {
                String floatFormat;
                if (formats.size() > i) {
                    floatFormat = formats.get(i);
                } else {
                    // MSPT 根据默认保留一位小数处理
                    floatFormat = "%.1f";
                }
                args[i] = String.format(floatFormat, args[i]);
            }
        }
        return args;
    }

    // 拼接字符串本地化处理
    private String localizeString(String string) {
        Matcher matcher = LOCALIZATION_PATTERN.matcher(string);
        while (matcher.find()) {
            String key = matcher.group(1) + ": ";
            String localized = I18n.translate(key);
            if (!localized.equals(key)) {
                string = string.replace(key, localized);
            }
        }
        return string;
    }

//    @PlatformOnly(PlatformOnly.FORGE)
    @Inject(method = "addLine(Lfi/dy/masa/minihud/config/InfoToggle;)V", at = @At("TAIL"))
    private void onAddLine(InfoToggle toggle, CallbackInfo ci) {
        MinecraftClient mc = this.mc;
        Entity entity = mc.getCameraEntity();
        ClientWorld world = mc.world;
        ClientPlayerEntity player = mc.player;
        if (entity == null || world == null || player == null) return;

        double y = entity.getY();
        BlockPos pos = new BlockPos(entity.getX(), y, entity.getZ());
        boolean isChunkLoaded = world.isChunkLoaded(pos);
        if (isChunkLoaded) {
            if (Configs.ModIntegration.RADIATION_EXPOSURE.getBooleanValue() &&
                    toggle.getIntegerValue() == Configs.ModIntegration.RADIATION_EXPOSURE_LINE_POSITION.getIntegerValue()) { // 有无更好的实现方式？
                try {
                    StringBuilder str;
                    str = new StringBuilder(128);
                    String radiationFmtStr = Configs.ModIntegration.RADIATION_EXPOSURE_FORMAT.getStringValue();
                    str.append(String.format(radiationFmtStr, MekRadiation.getRadiationString(player)));
                    this.addLine(str.toString());
                } catch (Exception e) {
                    this.addLine("Radiation Exposure Format Error");
                }
            }

            if (Configs.ModIntegration.SERENE_SEASONS.getBooleanValue() &&
                    toggle.getIntegerValue() == Configs.ModIntegration.SERENE_SEASONS_LINE_POSITION.getIntegerValue()) {
                try {
                    String str = Configs.ModIntegration.SERENE_SEASONS_FORMAT.getStringValue();

                    // https://github.com/maruohon/minihud/blob/pre-rewrite/fabric/1.18.2/src/main/java/fi/dy/masa/minihud/event/RenderHandler.java#L336-L349
                    long timeDay = world.getTimeOfDay();
                    long day = (int) (timeDay / 24000);
                    int dayTicks = (int) (timeDay % 24000);
                    int hour = (int) ((dayTicks / 1000) + 6) % 24;
                    int min = (int) (dayTicks / 16.666666) % 60;
                    int sec = (int) (dayTicks / 0.277777) % 60;
                    str = str.replace("{DAY}",  String.format("%d", day));
                    str = str.replace("{DAY_1}",String.format("%d", day + 1));
                    str = str.replace("{HOUR}", String.format("%02d", hour));
                    str = str.replace("{MIN}",  String.format("%02d", min));
                    str = str.replace("{SEC}",  String.format("%02d", sec));

                    str = str.replace("{DAY_OF_SEASON}", String.format("%d", SereneSeasons.getDayOfSeason(world)));
                    str = str.replace("{SEASON_NAME}", String.format("%s", SereneSeasons.getSeasonName(world)));
                    str = str.replace("{SUB_SEASON_NAME}", String.format("%s", SereneSeasons.getSubSeasonName(world)));
                    str = str.replace("{TROPICAL_SEASON_NAME}", String.format("%s", SereneSeasons.getTropicalSeasonName(world)));
                    this.addLine(str);
                } catch (Exception e) {
                    this.addLine("Serene Seasons Format Error");
                }
            }
        }
    }
}