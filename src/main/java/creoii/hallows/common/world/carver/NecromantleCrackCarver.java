package creoii.hallows.common.world.carver;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.carver.*;
import net.minecraft.world.gen.chunk.AquiferSampler;
import org.apache.commons.lang3.mutable.MutableBoolean;

import java.util.Random;
import java.util.function.Function;

public class NecromantleCrackCarver extends Carver<RavineCarverConfig> {
    public NecromantleCrackCarver(Codec<RavineCarverConfig> codec) {
        super(codec);
    }

    @Override
    public boolean shouldCarve(RavineCarverConfig config, Random random) {
        return random.nextFloat() <= config.probability;
    }

    @Override
    public boolean carve(CarverContext context, RavineCarverConfig config, Chunk chunk, Function<BlockPos, Biome> posToBiome, Random random, AquiferSampler sampler, ChunkPos chunkPos, CarvingMask mask) {
        int i = (this.getBranchFactor() * 2 - 1) * 16;
        double d = chunkPos.getOffsetX(random.nextInt(16));
        int j = config.y.get(random, context);
        double e = chunkPos.getOffsetZ(random.nextInt(16));
        float f = random.nextFloat() * 6.2831855F;
        float g = config.verticalRotation.get(random);
        double h = config.yScale.get(random);
        float k = config.shape.thickness.get(random);
        int l = (int)((float)i * config.shape.distanceFactor.get(random));
        this.carveRavine(context, config, chunk, posToBiome, random.nextLong(), sampler, d, (double)j, e, k, f, g, 0, l, h, mask);
        return true;
    }

    @Override
    protected boolean carveAtPoint(CarverContext context, RavineCarverConfig config, Chunk chunk, Function<BlockPos, Biome> posToBiome, CarvingMask mask, BlockPos.Mutable pos, BlockPos.Mutable downPos, AquiferSampler sampler, MutableBoolean foundSurface) {
        BlockState blockState = chunk.getBlockState(pos);
        BlockState blockState2 = chunk.getBlockState(downPos.set(pos, Direction.UP));
        if (blockState.isOf(Blocks.GRASS_BLOCK) || blockState.isOf(Blocks.MYCELIUM)) {
            foundSurface.setTrue();
        }

        if (!this.canAlwaysCarveBlock(blockState) && !this.canAlwaysCarveBlock(blockState2) && !config.debugConfig.isDebugMode()) {
            return false;
        } else {
            BlockState blockState3 = this.getState(context, config, pos, sampler);
            if (blockState3 == null) {
                return false;
            } else {
                chunk.setBlockState(pos, blockState3, false);
                if (foundSurface.isTrue()) {
                    downPos.set(pos, Direction.DOWN);
                    if (chunk.getBlockState(downPos).isOf(Blocks.DIRT)) {
                        context.method_39114(posToBiome, chunk, downPos, !blockState3.getFluidState().isEmpty()).ifPresent((fluid) -> {
                            chunk.setBlockState(downPos, fluid, false);
                        });
                    }
                }

                return true;
            }
        }
    }

    private BlockState getState(CarverContext context, RavineCarverConfig config, BlockPos pos, AquiferSampler sampler) {
        if (pos.getY() <= config.lavaLevel.getY(context)) {
            return LAVA.getBlockState();
        } else {
            BlockState blockState = sampler.apply(pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D);
            if (blockState == Blocks.STONE.getDefaultState()) {
                return config.debugConfig.isDebugMode() ? config.debugConfig.getBarrierState() : null;
            } else {
                return config.debugConfig.isDebugMode() ? getDebugState(config, blockState) : blockState;
            }
        }
    }

    private static BlockState getDebugState(CarverConfig config, BlockState state) {
        if (state.isOf(Blocks.AIR)) {
            return Blocks.EMERALD_BLOCK.getDefaultState();
        } else if (state.isOf(Blocks.WATER)) {
            BlockState blockState = config.debugConfig.getWaterState();
            return blockState.contains(Properties.WATERLOGGED) ? blockState.with(Properties.WATERLOGGED, true) : blockState;
        } else {
            return state.isOf(Blocks.LAVA) ? config.debugConfig.getLavaState() : state;
        }
    }

    private void carveRavine(CarverContext context, RavineCarverConfig config, Chunk chunk, Function<BlockPos, Biome> posToBiome, long seed, AquiferSampler sampler, double x, double y, double z, float width, float yaw, float pitch, int branchStartIndex, int branchCount, double yawPitchRatio, CarvingMask mask) {
        Random random = new Random(seed);
        float[] fs = this.createHorizontalStretchFactors(context, config, random);
        float f = 0.0F;
        float g = 0.0F;

        for(int i = branchStartIndex; i < branchCount; ++i) {
            double d = 1.5D + (double)(MathHelper.sin((float)i * 3.1415927F / (float)branchCount) * width);
            double e = d * yawPitchRatio;
            d *= config.shape.horizontalRadiusFactor.get(random);
            e = this.getVerticalScale(config, random, e, (float)branchCount, (float)i);
            float h = MathHelper.cos(pitch);
            float j = MathHelper.sin(pitch);
            x += (MathHelper.cos(yaw) * h);
            y += j;
            z += (MathHelper.sin(yaw) * h);
            pitch *= 0.7F;
            pitch += g * 0.05F;
            yaw += f * 0.05F;
            g *= 0.8F;
            f *= 0.5F;
            g += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f += (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (random.nextInt(4) != 0) {
                if (!canCarveBranch(chunk.getPos(), x, z, i, branchCount, width)) return;
                this.carveRegion(context, config, chunk, posToBiome, sampler, x, y, z, d, e, mask, (contextx, $$2, $$3, $$4, yx) -> {
                    return this.isPositionExcluded(contextx, fs, $$2, $$3, $$4, yx);
                });
            }
        }
    }

    private float[] createHorizontalStretchFactors(CarverContext context, RavineCarverConfig config, Random random) {
        int i = context.getHeight();
        float[] fs = new float[i];
        float f = 1.0F;

        for(int j = 0; j < i; ++j) {
            if (j == 0 || random.nextInt(config.shape.widthSmoothness) == 0) f = 1.0F + random.nextFloat() * random.nextFloat();
            fs[j] = f * f;
        }
        return fs;
    }

    private double getVerticalScale(RavineCarverConfig config, Random random, double pitch, float branchCount, float branchIndex) {
        float f = 1.0F - MathHelper.abs(0.5F - branchIndex / branchCount) * 2.0F;
        float g = config.shape.verticalRadiusDefaultFactor + config.shape.verticalRadiusCenterFactor * f;
        return (double)g * pitch * (double)MathHelper.nextBetween(random, 0.75F, 1.0F);
    }

    private boolean isPositionExcluded(CarverContext context, float[] horizontalStretchFactors, double scaledRelativeX, double scaledRelativeY, double scaledRelativeZ, int y) {
        return (scaledRelativeX * scaledRelativeX + scaledRelativeZ * scaledRelativeZ) * (double)horizontalStretchFactors[y - context.getMinY() - 1] + scaledRelativeY * scaledRelativeY / 6.0D >= 1.0D;
    }
}