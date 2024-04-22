package com.ecaree.minihudextra.mixin;

import com.ecaree.minihudextra.config.MHExInfoToggle;
import fi.dy.masa.minihud.config.InfoToggle;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mixin(value = InfoToggle.class, remap = false)
public class MixinInfoToggle {
    @Shadow @Final @Mutable private static InfoToggle[] $VALUES;

    @Invoker("<init>")
    public static InfoToggle invokeInit(String enumName, int enumOrdinal, String name, boolean defaultValue, int linePosition, String defaultHotkey, String comment) {
        throw new AssertionError();
    }

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lfi/dy/masa/minihud/config/InfoToggle;$VALUES:[Lfi/dy/masa/minihud/config/InfoToggle;", shift = At.Shift.AFTER))
    private static void addCustomInfo(CallbackInfo ci) {
        List<InfoToggle> infos = new ArrayList<>(Arrays.asList($VALUES));
        InfoToggle last = infos.get(infos.size() - 1);
        int i = 1;
        for (MHExInfoToggle info : MHExInfoToggle.values()) {
            infos.add(invokeInit(info.name(), last.ordinal() + i, info.getName(), info.getDefaultBooleanValue(), info.getLinePosition(), "", info.getComment()));
            i++;
        }
        $VALUES = infos.toArray(new InfoToggle[0]);
    }
}