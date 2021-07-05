package creoii.hallows.common.world.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import creoii.hallows.common.block.base.PostBlock;
import creoii.hallows.core.registry.FeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class BranchTreeDecorator extends TreeDecorator {
    public static final Codec<BranchTreeDecorator> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(BlockState.CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((config) -> {
            return config.probability;
        }), Codec.INT.fieldOf("maxLength").forGetter((config) -> {
            return config.maxLength;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("branchDensity").forGetter((config) -> {
            return config.branchDensity;
        })).apply(p_236568_0_, BranchTreeDecorator::new);
    });
    private final BlockState state;
    private final float probability;
    private final int maxLength;
    private final float branchDensity;

    public BranchTreeDecorator(BlockState state, float probabilityIn, int maxLength, float branchDensity) {
        this.state = state;
        this.probability = probabilityIn;
        this.maxLength = maxLength;
        this.branchDensity = branchDensity;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return FeatureRegistry.BRANCH_DECORATOR;
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        if (!(random.nextFloat() >= this.probability) && state.getBlock() instanceof PostBlock) {
            for (BlockPos pos : logPositions) {
                BlockPos.Mutable blockpos$mutable = pos.mutableCopy();
                Direction direction = Direction.Type.HORIZONTAL.random(random);
                for (int length = random.nextInt(maxLength) + 1; length > 0; --length) {
                    blockpos$mutable.set(blockpos$mutable, direction);
                    if (Feature.isAir(world, blockpos$mutable) && Feature.isAir(world, blockpos$mutable.down()) && random.nextFloat() <= branchDensity) replacer.accept(blockpos$mutable, this.state.with(Properties.AXIS, direction.getAxis()));
                    else break;
                }
            }
        }
    }
}