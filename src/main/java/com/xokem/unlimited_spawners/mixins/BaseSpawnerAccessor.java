package com.xokem.unlimited_spawners.mixins;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(BaseSpawner.class)
public interface BaseSpawnerAccessor
{
    @Invoker("isNearPlayer")
    boolean callIsNearPlayer(Level level, BlockPos position);
}
