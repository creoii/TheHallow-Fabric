package creoii.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import creoii.hallows.common.world.feature.config.RockFeatureConfig;
import creoii.hallows.common.world.feature.config.RockPillarFeatureConfig;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class RockPillarFeature extends Feature<RockPillarFeatureConfig> {
    public RockPillarFeature(Codec<RockPillarFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<RockPillarFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();

        RockPillarFeatureConfig config;
        for (config = context.getConfig(); blockPos.getY() > structureWorldAccess.getBottomY() + 3; blockPos = blockPos.down()) {
            if (!structureWorldAccess.isAir(blockPos.down())) {
                BlockState blockState = structureWorldAccess.getBlockState(blockPos.down());
                if (isSoil(blockState) || isStone(blockState) || blockState.isIn(BlockTags.SAND)) {
                    break;
                }
            }
        }

        BlockPos.Mutable mutable = blockPos.mutableCopy();
        for (int i = 0; i < config.rockCount().get(random); ++i) {
            mutable.set(blockPos.up(i * config.size().get(random)));
            generateRock(mutable, structureWorldAccess, random, config);
        }

        return true;
    }

    private void generateRock(BlockPos blockPos, StructureWorldAccess structureWorldAccess, Random random, RockPillarFeatureConfig config) {
        if (blockPos.getY() > structureWorldAccess.getBottomY() + 3) {
            for (int i = 0; i < 3; ++i) {
                int j = random.nextInt(config.size().get(random));
                int k = random.nextInt(config.size().get(random));
                int l = random.nextInt(config.size().get(random));
                float f = (float)(j + k + l) * 0.333F + 0.5F;

                for (BlockPos blockPos2 : BlockPos.iterate(blockPos.add(-j, -k, -l), blockPos.add(j, k, l))) {
                    if (blockPos2.getSquaredDistance(blockPos) <= (double) (f * f)) {
                        structureWorldAccess.setBlockState(blockPos2, config.state().getBlockState(random, blockPos2), 4);
                    }
                }

                blockPos = blockPos.add(-1 + random.nextInt(config.size().get(random)), -random.nextInt(config.size().get(random)), -1 + random.nextInt(config.size().get(random)));
            }
        }
    }
}