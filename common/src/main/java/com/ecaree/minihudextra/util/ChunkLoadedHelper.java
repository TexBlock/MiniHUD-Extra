package com.ecaree.minihudextra.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ChunkLoadedHelper {
    // https://github.com/maruohon/minihud/blob/pre-rewrite/fabric/1.20.x/src/main/java/fi/dy/masa/minihud/event/RenderHandler.java#L266-L274
    @SuppressWarnings("deprecation")
    public static boolean isChunkLoaded(Entity entity, World world) {
        BlockPos pos = BlockPos.ofFloored(entity.getX(), entity.getY(), entity.getZ());
        return world.isChunkLoaded(pos);
    }
}