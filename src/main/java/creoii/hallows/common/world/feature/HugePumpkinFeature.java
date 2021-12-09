package creoii.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import creoii.hallows.common.world.feature.config.HugePumpkinFeatureConfig;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class HugePumpkinFeature extends Feature<HugePumpkinFeatureConfig> {
    public HugePumpkinFeature(Codec<HugePumpkinFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<HugePumpkinFeatureConfig> context) {
        BlockPos pos = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        int radius = context.getConfig().radius();
        BlockPos.Mutable mutable = pos.mutableCopy();
        int r2 = radius * radius;
        for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++) {
            int z2 = z * z;
            for (int y = pos.getY() - radius; y <= pos.getY() + radius; y++ ) {
                int y2 = y * y;
                for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++) {
                    int x2 = x * x;
                    if (x2 + y2 + z2 <= r2) {
                        mutable.set(x, y, z);
                        world.setBlockState(mutable, Blocks.PUMPKIN.getDefaultState(), 2);
                    }
                }
            }
        }
        return true;
    }
}