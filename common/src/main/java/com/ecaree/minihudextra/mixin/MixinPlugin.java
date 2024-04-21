package com.ecaree.minihudextra.mixin;

import com.ecaree.minihudextra.util.ModLoadedHelper;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.*;
import java.util.function.BooleanSupplier;

public class MixinPlugin implements IMixinConfigPlugin {
    private static final Map<String, BooleanSupplier> MIXIN_CONFIGS = new LinkedHashMap<>();

    @Override
    public void onLoad(String mixinPackage) {
        addMixinConfig("com.ecaree.minihudextra.mixin.bloodmagic.MixinRenderHandler", "bloodmagic");
        addMixinConfig("com.ecaree.minihudextra.mixin.deepresonance.MixinRenderHandler", "deepresonance");
        addMixinConfig("com.ecaree.minihudextra.mixin.mek.MixinRenderHandler", "mekanism");
        addMixinConfig("com.ecaree.minihudextra.mixin.naturesaura.MixinRenderHandler", "naturesaura");
        addMixinConfig("com.ecaree.minihudextra.mixin.sereneseasons.MixinRenderHandler", "sereneseasons");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return MIXIN_CONFIGS.getOrDefault(mixinClassName, () -> true).getAsBoolean();
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {
    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
    }

    private static void addMixinConfig(final String mixinConfig, final String modID) {
        MIXIN_CONFIGS.put(mixinConfig, () -> ModLoadedHelper.isModLoaded(modID));
    }
}