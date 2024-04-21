package com.ecaree.minihudextra.config;

import com.ecaree.minihudextra.MiniHUDExtra;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;

import java.io.File;
import java.util.List;

public class Configs implements IConfigHandler {
    private static final String CONFIG_FILE_NAME = MiniHUDExtra.MOD_ID + ".json";
    public static class Generic {
        public static final ConfigHotkey OPEN_CONFIG_GUI = new ConfigHotkey("openConfigGui", "Z,C", "A hotkey to open the in-game Config GUI");
        public static final ConfigBoolean MODIFY_COLORS = new ConfigBoolean("modifyColors", true, "Whether or not applying the color changes");
        public static final ConfigBoolean TEXT_OUTLINE = new ConfigBoolean("textOutline", false, "Whether or not applying the text outline");
        public static final ConfigBoolean AUTO_OUTLINE_COLOR = new ConfigBoolean("autoOutlineColor", false, "When enabled, the text outline color will automatically change based on the text color and outline color brightness");
        public static final ConfigDouble OUTLINE_COLOR_BRIGHTNESS = new ConfigDouble("outlineColorBrightness", 0.4, 0, 1, "The brightness that applys to the outline color\n0.4 is the effect of glow ink sac");
        public static final ConfigBoolean FTB_ULTIMINE_SUPPORT = new ConfigBoolean("ftbUltimineSupport", true, "Automatically disables MiniHUD when FTB Ultimine is active, preventing the overlap between the two HUDs\nRequires FTB Ultimine to be loaded");
        public static final ConfigBoolean MINIHUD_I18N = new ConfigBoolean("minihudI18n", true, "Whether or not applying i18n support for text displayed by MiniHUD\nForge only, for Fabric, use Masa Gadget instead");
        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(OPEN_CONFIG_GUI, MODIFY_COLORS, TEXT_OUTLINE, AUTO_OUTLINE_COLOR, OUTLINE_COLOR_BRIGHTNESS, FTB_ULTIMINE_SUPPORT, MINIHUD_I18N);
        public static final List<IHotkey> HOTKEY_LIST = ImmutableList.of(OPEN_CONFIG_GUI);
    }

    public static class Colors {
        public static final ConfigColor OUTLINE_COLOR = new ConfigColor("outlineColor", "#FF000000", "The outline color for the text when auto outline color is disabled");
        public static final ConfigColor LINE_ONE = new ConfigColor("lineOne", "#FFE0E0E0", "Color for line 1");
        public static final ConfigColor LINE_TWO = new ConfigColor("lineTwo", "#FFE0E0E0", "Color for line 2");
        public static final ConfigColor LINE_THREE = new ConfigColor("lineThree", "#FFE0E0E0", "Color for line 3");
        public static final ConfigColor LINE_FOUR = new ConfigColor("lineFour", "#FFE0E0E0", "Color for line 4");
        public static final ConfigColor LINE_FIVE = new ConfigColor("lineFive", "#FFE0E0E0", "Color for line 5");
        public static final ConfigColor LINE_SIX = new ConfigColor("lineSix", "#FFE0E0E0", "Color for line 6");
        public static final ConfigColor LINE_SEVEN = new ConfigColor("lineSeven", "#FFE0E0E0", "Color for line 7");
        public static final ConfigColor LINE_EIGHT = new ConfigColor("lineEight", "#FFE0E0E0", "Color for line 8");
        public static final ConfigColor LINE_NINE = new ConfigColor("lineNine", "#FFE0E0E0", "Color for line 9");
        public static final ConfigColor LINE_TEN = new ConfigColor("lineTen", "#FFE0E0E0", "Color for line 10");
        public static final ConfigColor LINE_ELEVEN = new ConfigColor("lineEleven", "#FFE0E0E0", "Color for line 11");
        public static final ConfigColor LINE_TWELVE = new ConfigColor("lineTwelve", "#FFE0E0E0", "Color for line 12");
        public static final ConfigColor LINE_THIRTEEN = new ConfigColor("lineThirteen", "#FFE0E0E0", "Color for line 13");
        public static final ConfigColor LINE_FOURTEEN = new ConfigColor("lineFourteen", "#FFE0E0E0", "Color for line 14");
        public static final ConfigColor LINE_FIFTEEN = new ConfigColor("lineFifteen", "#FFE0E0E0", "Color for line 15");
        public static final ConfigColor LINE_SIXTEEN = new ConfigColor("lineSixteen", "#FFE0E0E0", "Color for line 16");
        public static final ConfigColor LINE_SEVENTEEN = new ConfigColor("lineSeventeen", "#FFE0E0E0", "Color for line 17");
        public static final ConfigColor LINE_EIGHTEEN = new ConfigColor("lineEighteen", "#FFE0E0E0", "Color for line 18");
        public static final ConfigColor LINE_NINETEEN = new ConfigColor("lineNineteen", "#FFE0E0E0", "Color for line 19");
        public static final ConfigColor LINE_TWENTY = new ConfigColor("lineTwenty", "#FFE0E0E0", "Color for line 20");
        public static final ConfigColor LINE_TWENTYONE = new ConfigColor("lineTwentyOne", "#FFE0E0E0", "Color for line 21");
        public static final ConfigColor LINE_TWENTYTWO = new ConfigColor("lineTwentyTwo", "#FFE0E0E0", "Color for line 22");
        public static final ConfigColor LINE_TWENTYTHREE = new ConfigColor("lineTwentyThree", "#FFE0E0E0", "Color for line 23");
        public static final ConfigColor LINE_TWENTYFOUR = new ConfigColor("lineTwentyFour", "#FFE0E0E0", "Color for line 24");
        public static final ConfigColor LINE_TWENTYFIVE = new ConfigColor("lineTwentyFive", "#FFE0E0E0", "Color for line 25");
        public static final ConfigColor LINE_TWENTYSIX = new ConfigColor("lineTwentySix", "#FFE0E0E0", "Color for line 26");
        public static final ConfigColor LINE_TWENTYSEVEN = new ConfigColor("lineTwentySeven", "#FFE0E0E0", "Color for line 27");
        public static final ConfigColor LINE_TWENTYEIGHT = new ConfigColor("lineTwentyEight", "#FFE0E0E0", "Color for line 28");
        public static final ConfigColor LINE_TWENTYNINE = new ConfigColor("lineTwentyNine", "#FFE0E0E0", "Color for line 29");
        public static final ConfigColor LINE_THIRTY = new ConfigColor("lineThirty", "#FFE0E0E0", "Color for line 30");
        public static final ConfigColor LINE_THIRTYONE = new ConfigColor("lineThirtyOne", "#FFE0E0E0", "Color for line 31");
        public static final ConfigColor LINE_THIRTYTWO = new ConfigColor("lineThirtyTwo", "#FFE0E0E0", "Color for line 32");
        public static final ConfigColor LINE_THIRTYTHREE = new ConfigColor("lineThirtyThree", "#FFE0E0E0", "Color for line 33");
        public static final ConfigColor LINE_THIRTYFOUR = new ConfigColor("lineThirtyFour", "#FFE0E0E0", "Color for line 34");
        public static final ConfigColor LINE_THIRTYFIVE = new ConfigColor("lineThirtyFive", "#FFE0E0E0", "Color for line 35");
        public static final ConfigColor LINE_THIRTYSIX = new ConfigColor("lineThirtySix", "#FFE0E0E0", "Color for line 36");
        public static final ConfigColor LINE_THIRTYSEVEN = new ConfigColor("lineThirtySeven", "#FFE0E0E0", "Color for line 37");
        public static final ConfigColor LINE_THIRTYEIGHT = new ConfigColor("lineThirtyEight", "#FFE0E0E0", "Color for line 38");
        public static final ConfigColor LINE_THIRTYNINE = new ConfigColor("lineThirtyNine", "#FFE0E0E0", "Color for line 39");
        public static final ConfigColor LINE_FORTY = new ConfigColor("lineForty", "#FFE0E0E0", "Color for line 40");
        public static final ConfigColor LINE_FORTYONE = new ConfigColor("lineFortyOne", "#FFE0E0E0", "Color for line 41");
        public static final ConfigColor LINE_FORTYTWO = new ConfigColor("lineFortyTwo", "#FFE0E0E0", "Color for line 42");
        public static final ConfigColor LINE_FORTYTHREE = new ConfigColor("lineFortyThree", "#FFE0E0E0", "Color for line 43");
        public static final ConfigColor LINE_FORTYFOUR = new ConfigColor("lineFortyFour", "#FFE0E0E0", "Color for line 44");
        public static final ConfigColor LINE_FORTYFIVE = new ConfigColor("lineFortyFive", "#FFE0E0E0", "Color for line 45");
        public static final ConfigColor LINE_FORTYSIX = new ConfigColor("lineFortySix", "#FFE0E0E0", "Color for line 46");
        public static final ConfigColor LINE_FORTYSEVEN = new ConfigColor("lineFortySeven", "#FFE0E0E0", "Color for line 47");
        public static final ConfigColor LINE_FORTYEIGHT = new ConfigColor("lineFortyEight", "#FFE0E0E0", "Color for line 48");
        public static final ConfigColor LINE_FORTYNINE = new ConfigColor("lineFortyNine", "#FFE0E0E0", "Color for line 49");
        public static final ConfigColor LINE_FIFTY = new ConfigColor("lineFifty", "#FFE0E0E0", "Color for line 50");

        public static List<IConfigBase> OPTIONS = ImmutableList.of(
                OUTLINE_COLOR,
                LINE_ONE, LINE_TWO, LINE_THREE, LINE_FOUR, LINE_FIVE, LINE_SIX, LINE_SEVEN, LINE_EIGHT, LINE_NINE, LINE_TEN,
                LINE_ELEVEN, LINE_TWELVE, LINE_THIRTEEN, LINE_FOURTEEN, LINE_FIFTEEN, LINE_SIXTEEN, LINE_SEVENTEEN, LINE_EIGHTEEN, LINE_NINETEEN, LINE_TWENTY,
                LINE_TWENTYONE, LINE_TWENTYTWO, LINE_TWENTYTHREE, LINE_TWENTYFOUR, LINE_TWENTYFIVE, LINE_TWENTYSIX, LINE_TWENTYSEVEN, LINE_TWENTYEIGHT, LINE_TWENTYNINE, LINE_THIRTY,
                LINE_THIRTYONE, LINE_THIRTYTWO, LINE_THIRTYTHREE, LINE_THIRTYFOUR, LINE_THIRTYFIVE, LINE_THIRTYSIX, LINE_THIRTYSEVEN, LINE_THIRTYEIGHT, LINE_THIRTYNINE, LINE_FORTY,
                LINE_FORTYONE, LINE_FORTYTWO, LINE_FORTYTHREE, LINE_FORTYFOUR, LINE_FORTYFIVE, LINE_FORTYSIX, LINE_FORTYSEVEN, LINE_FORTYEIGHT, LINE_FORTYNINE, LINE_FIFTY
        );
    }

    public static class ModIntegration {
        public static final ConfigBoolean MEK_RADIATION_EXPOSURE = new ConfigBoolean("infoMekRadiationExposure", false, "Show the player's radiation exposure in Mekanism\nRequires Mekanism to be loaded");
        public static final ConfigString MEK_RADIATION_EXPOSURE_FORMAT = new ConfigString("infoMekRadiationExposureFormat", "Radiation Exposure: %1$s", "The format string for the Mek radiation exposure line");
        public static final ConfigInteger MEK_RADIATION_EXPOSURE_LINE_POSITION = new ConfigInteger("infoMekRadiationExposureLinePosition", 7, 0, 100, "The line position for Mek radiation exposure info");
        public static final ConfigBoolean DEEP_RESONANCE_RADIATION = new ConfigBoolean("infoDeepResonanceRadiation", false, "Show the radiation around player in Deep Resonance\nRequires Deep Resonance to be loaded");
        public static final ConfigString DEEP_RESONANCE_RADIATION_FORMAT = new ConfigString("infoDeepResonanceRadiationFormat", "Deep Resonance Radiation: %.2f", "The format string for the Deep Resonance radiation line");
        public static final ConfigInteger DEEP_RESONANCE_RADIATION_LINE_POSITION = new ConfigInteger("infoDeepResonanceRadiationLinePosition", 8, 0, 100, "The line position for Deep Resonance radiation info");
        public static final ConfigBoolean SERENE_SEASONS = new ConfigBoolean("infoSereneSeasons", false, "Show the season info in Serene Seasons\nRequires Serene Seasons to be loaded");
        public static final ConfigString SERENE_SEASONS_FORMAT = new ConfigString("infoSereneSeasonsFormat", "{SUB_SEASON_NAME}, Day {DAY_OF_SUB_SEASON}, {HOUR}:{MIN}:{SEC}", "The format string for the Serene Seasons info line\nThe supported placeholders are: {DAY_OF_SEASON}, {DAY_OF_SUB_SEASON}, \n{SEASON_NAME}, {SUB_SEASON_NAME}, {TROPICAL_SEASON_NAME}\nand placeholders in MiniHUD dateFormatMinecraft");
        public static final ConfigInteger SERENE_SEASONS_LINE_POSITION = new ConfigInteger("infoSereneSeasonsLinePosition", 9, 0, 100, "The line position for Serene Seasons info");
        public static final ImmutableList<IConfigBase> OPTIONS = ImmutableList.of(
                MEK_RADIATION_EXPOSURE, MEK_RADIATION_EXPOSURE_FORMAT, MEK_RADIATION_EXPOSURE_LINE_POSITION,
                DEEP_RESONANCE_RADIATION, DEEP_RESONANCE_RADIATION_FORMAT, DEEP_RESONANCE_RADIATION_LINE_POSITION,
                SERENE_SEASONS, SERENE_SEASONS_FORMAT, SERENE_SEASONS_LINE_POSITION);
    }

    @Override
    public void load() {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);
        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);
            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();
                ConfigUtils.readConfigBase(root, "Generic", Configs.Generic.OPTIONS);
                ConfigUtils.readConfigBase(root, "Colors", Configs.Colors.OPTIONS);
                ConfigUtils.readConfigBase(root, "ModIntegration", Configs.ModIntegration.OPTIONS);
            }
        }
    }
    @Override
    public void save() {
        File dir = FileUtils.getConfigDirectory();
        if (dir.exists() && dir.isDirectory() || dir.mkdirs()) {
            JsonObject root = new JsonObject();
            ConfigUtils.writeConfigBase(root, "Generic", Configs.Generic.OPTIONS);
            ConfigUtils.writeConfigBase(root, "Colors", Configs.Colors.OPTIONS);
            ConfigUtils.writeConfigBase(root, "ModIntegration", Configs.ModIntegration.OPTIONS);
            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }
}