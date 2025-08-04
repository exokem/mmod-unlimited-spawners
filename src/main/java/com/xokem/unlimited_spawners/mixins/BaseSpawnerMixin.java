package com.xokem.unlimited_spawners.mixins;

import com.xokem.unlimited_spawners.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin
{
    @Unique
    private boolean unlimited_spawners$_isUnlimitedSpawningAllowed(Level level, BlockPos position)
    {
        return Config.blocks.contains(level.getBlockState(position.above()).getBlock());
    }

    @Redirect(method = {"clientTick", "serverTick"}, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/BaseSpawner;isNearPlayer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z"), remap = false)
    private boolean isNearPlayerProxy(BaseSpawner caller, Level level, BlockPos position)
    {
        return unlimited_spawners$_isUnlimitedSpawningAllowed(level, position) || ((BaseSpawnerAccessor)caller).callIsNearPlayer(level, position);
    }
}
