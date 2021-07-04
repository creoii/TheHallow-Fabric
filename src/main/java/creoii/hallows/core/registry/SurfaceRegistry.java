package creoii.hallows.core.registry;

import creoii.hallows.common.world.surface.GlacierSurfaceBuilder;
import creoii.hallows.common.world.surface.HallowedSurfaceBuilder;
import creoii.hallows.core.Hallows;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class SurfaceRegistry {
    public static final TernarySurfaceConfig HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(BlockRegistry.HALLOWED_DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(Blocks.COARSE_DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig GRASS_DIRT_HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig PETRIFIED_SAND_CONFIG = new TernarySurfaceConfig(BlockRegistry.PETRIFIED_SAND.getDefaultState(), BlockRegistry.PETRIFIED_SANDSTONE.getDefaultState(), BlockRegistry.PETRIFIED_SANDSTONE.getDefaultState());

    public static SurfaceBuilder<TernarySurfaceConfig> HALLOWED_SURFACE;
    public static SurfaceBuilder<TernarySurfaceConfig> GLACIER_SURFACE;

    public static ConfiguredSurfaceBuilder<?> HALLOWED;
    public static ConfiguredSurfaceBuilder<?> GLACIER;
    public static ConfiguredSurfaceBuilder<?> PETRIFIED;

    public static void register() {
        HALLOWED_SURFACE = Registry.register(Registry.SURFACE_BUILDER, new Identifier(Hallows.MOD_ID, "hallowed"), new HallowedSurfaceBuilder(TernarySurfaceConfig.CODEC));
        GLACIER_SURFACE = Registry.register(Registry.SURFACE_BUILDER, new Identifier(Hallows.MOD_ID, "glacier"), new GlacierSurfaceBuilder(TernarySurfaceConfig.CODEC));

        HALLOWED = HALLOWED_SURFACE.withConfig(HALLOWED_DIRT_CONFIG);
        GLACIER = GLACIER_SURFACE.withConfig(SurfaceBuilder.SAND_CONFIG);
        PETRIFIED = SurfaceBuilder.DEFAULT.withConfig(PETRIFIED_SAND_CONFIG);
    }
}