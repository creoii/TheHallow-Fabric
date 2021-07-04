package creoii.hallows.core.registry;

import creoii.hallows.common.world.surface.GlacierSurfaceBuilder;
import creoii.hallows.common.world.surface.HallowedSurfaceBuilder;
import creoii.hallows.core.Hallows;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public class SurfaceRegistry {
    public static final TernarySurfaceConfig HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(BlockRegistry.HALLOWED_DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(Blocks.COARSE_DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig GRASS_DIRT_HALLOWED_DIRT_CONFIG = new TernarySurfaceConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), BlockRegistry.HALLOWED_DIRT.getDefaultState());
    public static final TernarySurfaceConfig PETRIFIED_SAND_CONFIG = new TernarySurfaceConfig(BlockRegistry.PETRIFIED_SAND.getDefaultState(), BlockRegistry.PETRIFIED_SANDSTONE.getDefaultState(), BlockRegistry.PETRIFIED_SANDSTONE.getDefaultState());

    public static final SurfaceBuilder<TernarySurfaceConfig> HALLOWED_SURFACE = new HallowedSurfaceBuilder(TernarySurfaceConfig.CODEC);
    public static final SurfaceBuilder<TernarySurfaceConfig> GLACIER_SURFACE = new GlacierSurfaceBuilder(TernarySurfaceConfig.CODEC);

    public static final RegistryKey<SurfaceBuilder<?>> HALLOWED_SURFACE_KEY = RegistryKey.of(Registry.SURFACE_BUILD_KEY, new Identifier(Hallows.MOD_ID, "hallowed"));
    public static final RegistryKey<SurfaceBuilder<?>> GLACIER_SURFACE_KEY = RegistryKey.of(Registry.SURFACE_BUILD_KEY, new Identifier(Hallows.MOD_ID, "glacier"));

    public static ConfiguredSurfaceBuilder<?> HALLOWED;
    public static ConfiguredSurfaceBuilder<?> GLACIER;
    public static ConfiguredSurfaceBuilder<?> PETRIFIED;

    public static void register() {
        Registry.register(Registry.SURFACE_BUILDER, HALLOWED_SURFACE_KEY.getValue(), HALLOWED_SURFACE);
        Registry.register(Registry.SURFACE_BUILDER, GLACIER_SURFACE_KEY.getValue(), GLACIER_SURFACE);

        HALLOWED = HALLOWED_SURFACE.withConfig(HALLOWED_DIRT_CONFIG);
        GLACIER = GLACIER_SURFACE.withConfig(SurfaceBuilder.SAND_CONFIG);
        PETRIFIED = SurfaceBuilder.DEFAULT.withConfig(PETRIFIED_SAND_CONFIG);
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(Hallows.MOD_ID, "hallowed"), HALLOWED);
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(Hallows.MOD_ID, "glacier"), GLACIER);
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier(Hallows.MOD_ID, "petrified"), PETRIFIED);
    }
}