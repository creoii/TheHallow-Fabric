package creoii.hallows.common.world.decorator;

import com.mojang.serialization.Codec;
import creoii.hallows.core.registry.FeatureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class JackOLanternTreeDecorator extends TreeDecorator {
    public static final Codec<JackOLanternTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(JackOLanternTreeDecorator::new, (p_236865_0_) -> {
        return p_236865_0_.probability;
    }).codec();
    private final float probability;

    public JackOLanternTreeDecorator(float probabilityIn) {
        this.probability = probabilityIn;
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return FeatureRegistry.JACK_O_LANTERN_DECORATOR;
    }

    @Override
    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions) {
        if (!(random.nextFloat() >= this.probability)) {
            Direction direction = Util.getRandom(new Direction[]{Direction.WEST, Direction.EAST, Direction.SOUTH, Direction.NORTH}, random);
            int i = !leavesPositions.isEmpty() ? Math.max(leavesPositions.get(0).getY() - 1, logPositions.get(0).getY()) : Math.min(logPositions.get(0).getY() + 1 + random.nextInt(3), logPositions.get(logPositions.size() - 1).getY());
            List<BlockPos> list = logPositions.stream().filter((pos) -> {
                return pos.getY() == i;
            }).collect(Collectors.toList());
            if (!list.isEmpty()) {
                BlockPos blockpos = list.get(random.nextInt(list.size()));
                BlockPos blockpos1 = blockpos.offset(direction);
                if (Feature.isAir(world, blockpos1) && Feature.isAir(world, blockpos1.offset(Direction.SOUTH))) replacer.accept(blockpos1, Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, direction));
            }
        }
    }
}