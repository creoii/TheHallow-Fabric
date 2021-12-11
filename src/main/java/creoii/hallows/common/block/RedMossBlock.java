package creoii.hallows.common.block;

import creoii.hallows.core.registry.PlacedFeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.MossBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class RedMossBlock extends MossBlock {
    public RedMossBlock(Settings settings) {
        super(settings);
    }

    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        //PlacedFeatureRegistry.RED_MOSS_PATCH_BONEMEAL.generate(world, world.getChunkManager().getChunkGenerator(), random, pos.up());
    }
}