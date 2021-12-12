package creoii.hallows.common.world.trunkplacer;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import creoii.hallows.core.registry.FeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class WickedTrunkPlacer extends TrunkPlacer {
    public static final Codec<WickedTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return fillTrunkPlacerFields(instance).and(instance.group(IntProvider.createValidatingCodec(1, 64).fieldOf("twists").forGetter((placer) -> {
            return placer.twists;
        }), IntProvider.createValidatingCodec(1, 64).fieldOf("horizontal_length").forGetter((placer) -> {
            return placer.horizontalLength;
        }), IntProvider.createValidatingCodec(1, 64).fieldOf("vertical_length").forGetter((placer) -> {
            return placer.verticalLength;
        }))).apply(instance, WickedTrunkPlacer::new);
    });
    private final IntProvider twists;
    private final IntProvider horizontalLength;
    private final IntProvider verticalLength;

    public WickedTrunkPlacer(int i, int j, int k, IntProvider twists, IntProvider horizontalLength, IntProvider verticalLength) {
        super(i, j, k);
        this.twists = twists;
        this.horizontalLength = horizontalLength;
        this.verticalLength = verticalLength;
    }

    protected TrunkPlacerType<?> getType() {
        return FeatureRegistry.WICKED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();

        for(int i = 0; i <= height; ++i) {
            getAndSetState(world, replacer, random, startPos.up(i), config);
        }

        BlockPos current = startPos.up(height);
        boolean flag = false;
        for(int i = 0; i <= twists.get(random); ++i) {
            Direction direction = flag ? Direction.UP : Direction.Type.HORIZONTAL.random(random);
            int length;
            if (direction == Direction.UP) {
                length = verticalLength.get(random);
                for (int j = 0; j <= length; ++j) {
                    getAndSetState(world, replacer, random, current.offset(direction, j), config);
                }
            } else {
                length = horizontalLength.get(random);
                for (int j = 0; j <= length; ++j) {
                    getAndSetState(world, replacer, random, current.offset(direction, j), config);
                }
            }
            current = current.offset(direction, length);
            list.add(new FoliagePlacer.TreeNode(current, 0, false));
            flag = !flag;
        }

        return list;
    }
}
