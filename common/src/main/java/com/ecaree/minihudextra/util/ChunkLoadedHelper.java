package com.ecaree.minihudextra.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChunkLoadedHelper {
    // https://github.com/maruohon/minihud/blob/pre-rewrite/fabric/1.18.2/src/main/java/fi/dy/masa/minihud/event/RenderHandler.java#L280-L288
    @SuppressWarnings("deprecation")
    public static boolean isChunkLoaded(Entity entity, World world) {
        BlockPos pos = new BlockPos(entity.getX(), entity.getY(), entity.getZ());
        return world.isChunkLoaded(pos);
    }
}