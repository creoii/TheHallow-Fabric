package creoii.hallows.common.world.trunkplacer;

import com.google.common.collect.ImmutableList;
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

public class SlantedTrunkPlacer extends TrunkPlacer {
    public static final Codec<SlantedTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return fillTrunkPlacerFields(instance).and(instance.group(IntProvider.createValidatingCodec(1, 64).fieldOf("slant_intensity").forGetter((placer) -> {
            return placer.slantIntensity;
        }), IntProvider.createValidatingCodec(1, 64).fieldOf("slant_offset").forGetter((placer) -> {
            return placer.slantOffset;
        }))).apply(instance, SlantedTrunkPlacer::new);
    });
    private final IntProvider slantIntensity;
    private final IntProvider slantOffset;

    public SlantedTrunkPlacer(int i, int j, int k, IntProvider slantIntensity, IntProvider slantOffset) {
        super(i, j, k);
        this.slantIntensity = slantIntensity;
        this.slantOffset = slantOffset;
    }

    protected TrunkPlacerType<?> getType() {
        return FeatureRegistry.SLANTED_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> list = Lists.newArrayList();

        Direction direction = Direction.random(random);
        int offset = slantOffset.get(random);
        for(int i = 0; i < offset; ++i) {
            getAndSetState(world, replacer, random, startPos.up(i), config);
        }

        int intensity = slantIntensity.get(random);
        BlockPos current = startPos.up(offset);
        for (int i = offset; i <= height; ++i) {
            getAndSetState(world, replacer, random, current.up(i), config);
            if (i % intensity == 0) current = current.offset(direction, 1);
        }

        return ImmutableList.of(new FoliagePlacer.TreeNode(new BlockPos(current.getX(), height, current.getZ()), 0, false));
    }
}
