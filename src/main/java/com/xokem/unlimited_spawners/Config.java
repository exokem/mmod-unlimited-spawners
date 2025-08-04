package com.xokem.unlimited_spawners;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@EventBusSubscriber(modid = UnlimitedSpawners.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.ConfigValue<List<? extends String>> BLOCK_NAMES = BUILDER
            .comment("A list of blocks that enable unlimited spawning when placed on top of a spawner.")
            .define("blocks", List.of("minecraft:netherite_block"), Config::validateBlockName);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static Set<Block> blocks;

    private static boolean validateBlockName(final Object obj)
    {
        return obj instanceof String itemName && BuiltInRegistries.BLOCK.containsKey(ResourceLocation.tryParse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        // convert the list of strings into a set of items
        blocks = BLOCK_NAMES.get().stream()
                .map(itemName -> BuiltInRegistries.BLOCK.get(ResourceLocation.tryParse(itemName)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Holder.Reference::value)
                .collect(Collectors.toSet());
    }
}
