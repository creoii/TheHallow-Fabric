package creoii.hallows.common.world.surface;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import creoii.hallows.core.registry.BlockRegistry;
import creoii.hallows.core.registry.SurfaceRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkRandom;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class GlacierSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
    protected BlockState[] layerBlocks;
    protected long seed;
    protected OctaveSimplexNoiseSampler heightCutoffNoise;
    protected OctaveSimplexNoiseSampler heightNoise;
    protected OctaveSimplexNoiseSampler layerNoise;

    public GlacierSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
        super(codec);
    }

    @Override
    public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int a, long seed, TernarySurfaceConfig surfaceConfig) {
        if (noise > 1.0D) {
            SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, a, seed, SurfaceRegistry.HALLOWED_DIRT_CONFIG);
        } else if (noise > -0.6D) {
            SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, a, seed, SurfaceRegistry.COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG);
        } else {
            double d0 = 0.0D;
            double d1 = Math.min(Math.abs(noise), this.heightCutoffNoise.sample((double) x * 0.25D, (double) z * 0.25D, true) * 15.0D);
            if (d1 > 0.0D) {
                double d3 = Math.abs(this.heightNoise.sample((double) x * 0.001953125D, (double) z * 0.001953125D, true));
                d0 = d1 * d1 * 2.5D;
                double d4 = Math.ceil(d3 * 50.0D) + 14.0D;
                if (d0 > d4) {
                    d0 = d4;
                }

                d0 = d0 + 48.0D;
            }

            int i1 = x & 15;
            int i = z & 15;
            BlockState blockstate3 = BlockRegistry.HALLSTONE.getDefaultState();
            SurfaceConfig isurfacebuilderconfig = biome.getGenerationSettings().getSurfaceConfig();
            BlockState blockstate4 = isurfacebuilderconfig.getUnderMaterial();
            BlockState blockstate = isurfacebuilderconfig.getTopMaterial();
            BlockState blockstate1 = blockstate4;
            int j = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
            boolean flag = Math.cos(noise / 3.0D * Math.PI) > 0.0D;
            int k = -1;
            boolean flag1 = false;
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for (int l = Math.max(height, (int) d0 + 1); l >= 0; --l) {
                blockpos$mutable.set(i1, l, i);
                if (l < (int) d0) {
                    chunk.setBlockState(blockpos$mutable, defaultBlock, false);
                }

                BlockState blockstate2 = chunk.getBlockState(blockpos$mutable);
                if (blockstate2.isAir()) {
                    k = -1;
                } else if (blockstate2.isOf(defaultBlock.getBlock())) {
                    if (k == -1) {
                        flag1 = false;
                        if (j <= 0) {
                            blockstate3 = Blocks.AIR.getDefaultState();
                            blockstate1 = defaultBlock;
                        } else if (l >= seaLevel - 4 && l <= seaLevel + 1) {
                            blockstate3 = BlockRegistry.MIDNIGHT_MORTIS.getDefaultState();
                            blockstate1 = blockstate4;
                        }

                        if (l < seaLevel && blockstate3.isAir()) {
                            blockstate3 = defaultFluid;
                        }

                        k = j + Math.max(0, l - seaLevel);
                        if (l >= seaLevel - 1) {
                            if (l <= seaLevel + 3 + j) {
                                chunk.setBlockState(blockpos$mutable, blockstate, false);
                                flag1 = true;
                            } else {
                                BlockState blockstate5;
                                if (l >= 48 && l <= 127) {
                                    if (flag) {
                                        blockstate5 = BlockRegistry.MIDNIGHT_MORTIS.getDefaultState();
                                    } else {
                                        blockstate5 = this.calculateLayerBlockState(x, l, z);
                                    }
                                } else {
                                    blockstate5 = BlockRegistry.DUSK_MORTIS.getDefaultState();
                                }

                                chunk.setBlockState(blockpos$mutable, blockstate5, false);
                            }
                        } else if (l < seaLevel - 7 - j) {
                            blockstate1 = defaultBlock;
                            chunk.setBlockState(blockpos$mutable, BlockRegistry.NOON_MORTIS.getDefaultState(), false);
                        } else {
                            chunk.setBlockState(blockpos$mutable, blockstate1, false);
                            Block block = blockstate1.getBlock();
                            if (block == BlockRegistry.NOON_MORTIS || block == BlockRegistry.HALLSTONE || block == BlockRegistry.DAWN_MORTIS || block == BlockRegistry.DUSK_MORTIS || block == BlockRegistry.MIDNIGHT_MORTIS) {
                                chunk.setBlockState(blockpos$mutable, BlockRegistry.NOON_MORTIS.getDefaultState(), false);
                            }
                        }
                    } else if (k > 0) {
                        --k;
                        if (flag1) {
                            chunk.setBlockState(blockpos$mutable, BlockRegistry.NOON_MORTIS.getDefaultState(), false);
                        } else {
                            chunk.setBlockState(blockpos$mutable, this.calculateLayerBlockState(x, l, z), false);
                        }
                    }
                }
            }
        }
    }

    public void initSeed(long seed) {
        if (this.seed != seed || this.layerBlocks == null) {
            this.initLayerBlocks(seed);
        }

        if (this.seed != seed || this.heightCutoffNoise == null || this.heightNoise == null) {
            ChunkRandom chunkRandom = new ChunkRandom(seed);
            this.heightCutoffNoise = new OctaveSimplexNoiseSampler(chunkRandom, IntStream.rangeClosed(-3, 0));
            this.heightNoise = new OctaveSimplexNoiseSampler(chunkRandom, ImmutableList.of(0));
        }

        this.seed = seed;
    }

    protected void initLayerBlocks(long seed) {
        this.layerBlocks = new BlockState[48];
        Arrays.fill(this.layerBlocks, BlockRegistry.DAWN_MORTIS.getDefaultState());
        ChunkRandom sharedseedrandom = new ChunkRandom(seed);
        this.layerNoise = new OctaveSimplexNoiseSampler(sharedseedrandom, ImmutableList.of(0));

        for(int l1 = 0; l1 < 48; ++l1) {
            l1 += sharedseedrandom.nextInt(5) + 1;
            if (l1 < 48) {
                this.layerBlocks[l1] = BlockRegistry.DUSK_MORTIS.getDefaultState();
            }
        }

        int i2 = sharedseedrandom.nextInt(4) + 2;

        for(int i = 0; i < i2; ++i) {
            int j = sharedseedrandom.nextInt(3) + 1;
            int k = sharedseedrandom.nextInt(48);

            for(int l = 0; k + l < 48 && l < j; ++l) {
                this.layerBlocks[k + l] = BlockRegistry.NOON_MORTIS.getDefaultState();
            }
        }

        int j2 = sharedseedrandom.nextInt(4) + 2;

        for(int k2 = 0; k2 < j2; ++k2) {
            int i3 = sharedseedrandom.nextInt(3) + 2;
            int l3 = sharedseedrandom.nextInt(48);

            for(int i1 = 0; l3 + i1 < 48 && i1 < i3; ++i1) {
                this.layerBlocks[l3 + i1] = BlockRegistry.NOON_MORTIS.getDefaultState();
            }
        }

        int l2 = sharedseedrandom.nextInt(4) + 2;

        for(int j3 = 0; j3 < l2; ++j3) {
            int i4 = sharedseedrandom.nextInt(3) + 1;
            int k4 = sharedseedrandom.nextInt(48);

            for(int j1 = 0; k4 + j1 < 48 && j1 < i4; ++j1) {
                this.layerBlocks[k4 + j1] = BlockRegistry.DUSK_MORTIS.getDefaultState();
            }
        }

        int k3 = sharedseedrandom.nextInt(3) + 3;
        int j4 = 0;

        for(int l4 = 0; l4 < k3; ++l4) {
            j4 += sharedseedrandom.nextInt(16) + 4;

            for(int k1 = 0; j4 + k1 < 48 && k1 < 1; ++k1) {
                this.layerBlocks[j4 + k1] = BlockRegistry.DUSK_MORTIS.getDefaultState();
                if (j4 + k1 > 1 && sharedseedrandom.nextBoolean()) {
                    this.layerBlocks[j4 + k1 - 1] = BlockRegistry.MIDNIGHT_MORTIS.getDefaultState();
                }

                if (j4 + k1 < 47 && sharedseedrandom.nextBoolean()) {
                    this.layerBlocks[j4 + k1 + 1] = BlockRegistry.MIDNIGHT_MORTIS.getDefaultState();
                }
            }
        }
    }

    protected BlockState calculateLayerBlockState(int x, int y, int z) {
        int i = (int)Math.round(this.layerNoise.sample((double)x / 512.0D, (double)z / 512.0D, false) * 2.0D);
        return this.layerBlocks[(y + i + 48) % 48];
    }
}
