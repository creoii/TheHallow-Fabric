package creoii.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import creoii.hallows.common.block.CornBlock;
import creoii.hallows.core.registry.BlockRegistry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class CornRandomPatchFeature extends Feature<RandomPatchFeatureConfig> {
    public CornRandomPatchFeature(Codec<RandomPatchFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<RandomPatchFeatureConfig> context) {
        RandomPatchFeatureConfig randomPatchFeatureConfig = context.getConfig();
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int j = randomPatchFeatureConfig.xzSpread() + 1;
        int k = randomPatchFeatureConfig.ySpread() + 1;

        int height = random.nextInt(3) + 1;
        for(int l = 0; l < randomPatchFeatureConfig.tries(); ++l) {
            mutable.set(blockPos, random.nextInt(j) - random.nextInt(j), random.nextInt(k) - random.nextInt(k) + 1, random.nextInt(j) - random.nextInt(j));
            if (!Feature.isAir(world, mutable.down())) {
                switch (height) {
                    case 1:
                        world.setBlockState(mutable, BlockRegistry.CORN.getDefaultState(), 2);
                    case 2:
                        if (Feature.isAir(world, mutable.up())) {
                            world.setBlockState(mutable, BlockRegistry.CORN.getDefaultState().with(CornBlock.AGE, 1), 2);
                            world.setBlockState(mutable.up(), BlockRegistry.CORN.getDefaultState().with(CornBlock.AGE, 1).with(CornBlock.HEIGHT, 1), 2);
                        }
                    case 3:
                        if (Feature.isAir(world, mutable.up()) && Feature.isAir(world, mutable.up(2))) {
                            world.setBlockState(mutable, BlockRegistry.CORN.getDefaultState().with(CornBlock.AGE, 2), 2);
                            world.setBlockState(mutable.up(), BlockRegistry.CORN.getDefaultState().with(CornBlock.AGE, 2).with(CornBlock.HEIGHT, 1), 2);
                            world.setBlockState(mutable.up(2), BlockRegistry.CORN.getDefaultState().with(CornBlock.AGE, 2).with(CornBlock.HEIGHT, 2), 2);
                        }
                }
                height = random.nextInt(3) + 1;
            }
        }
        return true;
    }
}
