package creoii.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.BiConsumer;

public class MultiLayerTreeFeature extends TreeFeature {
    public MultiLayerTreeFeature(Codec<TreeFeatureConfig> codec) {
        super(codec);
    }

    private boolean generate(StructureWorldAccess world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> trunkReplacer, BiConsumer<BlockPos, BlockState> foliageReplacer, TreeFeatureConfig config) {
        int i = config.trunkPlacer.getHeight(random);
        int j = config.foliagePlacer.getRandomHeight(random, i, config);
        int k = i - j;
        int l = config.foliagePlacer.getRandomRadius(random, k);
        if (pos.getY() >= world.getBottomY() + 1 && pos.getY() + i + 1 <= world.getTopY()) {
            if (!config.saplingProvider.getBlockState(random, pos).canPlaceAt(world, pos)) {
                return false;
            } else {
                OptionalInt optionalInt = config.minimumSize.getMinClippedHeight();
                if (optionalInt.isPresent()) {
                    List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, trunkReplacer, random, i, pos, config);
                    list.forEach((treeNode) -> {
                        config.foliagePlacer.generate(world, foliageReplacer, random, config, i, treeNode, j, l);
                    });
                    return true;
                } else return false;
            }
        } else return false;
    }
}