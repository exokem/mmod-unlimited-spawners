package com.xokem.unlimited_spawners.mixins;

import com.xokem.unlimited_spawners.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BaseSpawner.class)
public class BaseSpawnerMixin
{
    private boolean isUnlimitedSpawningAllowed(Level level, BlockPos position)
    {
        return Config.blocks.stream().anyMatch(block -> level.getBlockState(position.above()).is(block));
    }

    @Redirect(method = {"clientTick", "serverTick"}, at = @At(value = "INVOKE", target ="Lnet/minecraft/world/level/BaseSpawner;isNearPlayer(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Z"))
    private boolean isNearPlayerProxy(BaseSpawner caller, Level level, BlockPos position)
    {
        return isUnlimitedSpawningAllowed(level, position) || ((BaseSpawnerAccessor)caller).callIsNearPlayer(level, position);
    }
}
