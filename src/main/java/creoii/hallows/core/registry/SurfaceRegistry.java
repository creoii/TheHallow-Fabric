package creoii.hallows.core.registry;

import creoii.hallows.common.world.surface.GlacierSurfaceBuilder;
import creoii.hallows.common.world.surface.HallowedSurfaceBuilder;
import creoii.hallows.core.Hallows;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class SurfaceRegistry {
    public static final TernarySurfaceConfig HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(BlockRegistry.HALLOWED_DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(Blocks.COARSE_DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig GRASS_DIRT_HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig PETRIFIED_SAND_CONFIG = new TernarySurfaceConfig(BlockRegistry.PETRIFIED_SAND.getDefaultState(), BlockRegistry.PETRIFIED_SANDSTONE.getDefaultState(), BlockRegistry.PETRIFIED_SANDSTONE.getDefaultState());

    public static SurfaceBuilder<TernarySurfaceConfig> HALLOWED_SURFACE;
    public static SurfaceBuilder<TernarySurfaceConfig> GLACIER_SURFACE;

    public static ConfiguredFeature<?, ?> HALLOWED;
    public static ConfiguredFeature<?, ?> GLACIER;
    public static ConfiguredFeature<?, ?> PETRIFIED;
    public static ConfiguredFeature<?, ?> SWAMP;

    public static void register() {
        HALLOWED_SURFACE = Registry.register(Registry.SURFACE_BUILDER, new Identifier(Hallows.MOD_ID, "hallowed"), new HallowedSurfaceBuilder(TernarySurfaceConfig.CODEC));
        GLACIER_SURFACE = Registry.register(Registry.SURFACE_BUILDER, new Identifier(Hallows.MOD_ID, "glacier"), new GlacierSurfaceBuilder(TernarySurfaceConfig.CODEC));
    }
}