package creoii.hallows.common.world.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import creoii.hallows.common.block.HangingLeavesBlock;
import creoii.hallows.core.registry.FeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class HangingLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesTreeDecorator> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), Codec.intRange(0, 10).fieldOf("min_length").forGetter((config) -> {
            return config.minLength;
        }), Codec.intRange(0, 10).fieldOf("max_length").forGetter((config) -> {
            return config.maxLength;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((config) -> {
            return config.probability;
        })).apply(p_236568_0_, HangingLeavesTreeDecorator::new);
    });
    private final BlockStateProvider state;
    private final int minLength;
    private final int maxLength;
    private final float probability;

    public HangingLeavesTreeDecorator(BlockStateProvider state, int minLength, int maxLength, float probability) {
        this.state = state;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.probability = probability;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return FeatureRegistry.HANGING_LEAVES_DECORATOR;
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        for (BlockPos pos : leavesPositions) {
            if (!(random.nextFloat() >= this.probability)) {
                BlockState state1 = state.getBlockState(random, pos);
                if (Feature.isAir(world, pos.down()) && state1.getBlock() instanceof HangingLeavesBlock) {
                    BlockState blockstate = state1.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.LARGE);
                    int length = random.nextInt(maxLength) + minLength;
                    for (int j = pos.down().getY(); j >= pos.down().getY() - length; --j) {
                        if (j == pos.down().getY() - length)
                            blockstate = blockstate.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.SMALL);
                        BlockPos place = new BlockPos(pos.getX(), j, pos.getZ());
                        if (Feature.isAir(world, place)) replacer.accept(place, blockstate);
                    }
                }
            }
        }
    }
}