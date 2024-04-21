package com.ecaree.minihudextra.mixin.bloodmagic;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.integration.BloodMagic;
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
            if (Configs.ModIntegration.BLOOD_MAGIC.getBooleanValue() &&
                    toggle.getIntegerValue() == Configs.ModIntegration.BLOOD_MAGIC_LINE_POSITION.getIntegerValue()) {
                try {
                    String str = Configs.ModIntegration.BLOOD_MAGIC_FORMAT.getStringValue();

                    str = str.replace("{LP}", String.format("%d", BloodMagic.getLP(player)));
                    str = str.replace("{ORB_TIER}", String.format("%d", BloodMagic.getOrbTier(player)));
                    str = str.replace("{ORB_TIER_NAME}", String.format("%s", BloodMagic.getOrbTierName(player)));
                    this.addLine(str);
                } catch (Exception e) {
                    if (world.isClient) {
                        this.addLine("Blood Magic Format Failed, MHEx is may not loaded on the server!"); // 经测试，灵魂网络应该是一个服务端事件。
                    } else {
                        this.addLine("Blood Magic Format Failed");
                    }
                }
            }
        }
    }
}