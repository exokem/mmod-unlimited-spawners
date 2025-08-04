package com.xokem.unlimited_spawners;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


@Mod(com.xokem.unlimited_spawners.UnlimitedSpawners.MODID)
public class UnlimitedSpawners
{
    public static final String MODID = "unlimited_spawners";

    public UnlimitedSpawners()
    {
        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
