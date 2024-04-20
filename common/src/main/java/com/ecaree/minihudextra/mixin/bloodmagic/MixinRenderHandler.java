package com.ecaree.minihudextra.mixin.bloodmagic;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.integration.BloodMagic;
import fi.dy.masa.minihud.config.InfoToggle;
import fi.dy.masa.minihud.event.RenderHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
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

        double y = entity.getY();
        BlockPos pos = new BlockPos(entity.getX(), y, entity.getZ());
        boolean isChunkLoaded = world.isChunkLoaded(pos);
        if (isChunkLoaded) {
            if (Configs.ModIntegration.BLOOD_MAGIC.getBooleanValue() &&
                    toggle.getIntegerValue() == Configs.ModIntegration.BLOOD_MAGIC_LINE_POSITION.getIntegerValue()) {
                try {
                    String str = Configs.ModIntegration.BLOOD_MAGIC_FORMAT.getStringValue();

                    str = str.replace("{LP}", String.format("%d", BloodMagic.getLP(player)));
                    str = str.replace("{ORB_TIER}", String.format("%d", BloodMagic.getOrbTier(player)));
                    str = str.replace("{ORB_TIER_STR}", String.format("%s", BloodMagic.getOrbTierStr(player)));
                    this.addLine(str);
                } catch (Exception e) {
                    this.addLine("Blood Magic Format Error");
                }
            }
        }
    }
}