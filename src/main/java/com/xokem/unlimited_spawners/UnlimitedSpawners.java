package com.xokem.unlimited_spawners;


import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(com.xokem.unlimited_spawners.UnlimitedSpawners.MODID)
public class UnlimitedSpawners
{
    public static final String MODID = "unlimited_spawners";

    public UnlimitedSpawners()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
