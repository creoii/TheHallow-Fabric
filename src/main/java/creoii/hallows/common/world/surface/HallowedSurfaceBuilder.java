package creoii.hallows.common.world.surface;

import com.mojang.serialization.Codec;
import creoii.hallows.core.registry.SurfaceRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;

public class HallowedSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
    public HallowedSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
        super(codec);
    }

    @Override
    public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int i, long seed, TernarySurfaceConfig surfaceConfig) {
        if (noise > 1.75D) {
            SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, i, seed, SurfaceRegistry.HALLOWED_DIRT_CONFIG);
        } else if (noise > -0.95D) {
            SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, i, seed, SurfaceRegistry.COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, i, seed, SurfaceRegistry.GRASS_DIRT_HALLOWED_DIRT_CONFIG);
        }
    }
}
