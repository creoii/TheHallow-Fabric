package creoii.hallows.common.block;

import creoii.hallows.core.registry.FeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.MossBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class RedMossBlock extends MossBlock {
    public RedMossBlock(Settings settings) {
        super(settings);
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        Feature.VEGETATION_PATCH.generate(new FeatureContext(world, world.getChunkManager().getChunkGenerator(), random, pos.up(), FeatureRegistry.RED_MOSS_PATCH_BONEMEAL.getConfig()));
    }
}