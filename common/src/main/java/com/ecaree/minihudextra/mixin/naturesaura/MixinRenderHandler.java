package com.ecaree.minihudextra.mixin.naturesaura;

import com.ecaree.minihudextra.config.Configs;
import com.ecaree.minihudextra.integration.NaturesAura;
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
            if (Configs.ModIntegration.NATURES_AURA.getBooleanValue() &&
                    toggle.getIntegerValue() == Configs.ModIntegration.NATURES_AURA_LINE_POSITION.getIntegerValue()) {
                try {
                    StringBuilder str;
                    str = new StringBuilder(128);
                    String auraFmtStr = Configs.ModIntegration.NATURES_AURA_FORMAT.getStringValue();
                    str.append(String.format(auraFmtStr, NaturesAura.getAura(world, player.getBlockPos())));
                    this.addLine(str.toString());
                } catch (Exception e) {
                    this.addLine("Natures Aura Format Error");
                }
            }
        }
    }
}