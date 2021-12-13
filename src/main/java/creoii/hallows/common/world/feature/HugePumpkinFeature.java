package creoii.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import creoii.hallows.common.world.feature.config.HugePumpkinFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class HugePumpkinFeature extends Feature<HugePumpkinFeatureConfig> {
    public HugePumpkinFeature(Codec<HugePumpkinFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<HugePumpkinFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        HugePumpkinFeatureConfig config = context.getConfig();
        Random random = context.getRandom();
        BlockPos.iterate(pos, pos.add(config.xzSize().get(random) - 1, config.height().get(random) - 1, config.xzSize().get(random) - 1)).forEach((pos1 -> {
            context.getWorld().setBlockState(pos1, Blocks.PUMPKIN.getDefaultState(), 2);
        }));
        return true;
    }
}