package creoii.hallows.common.block;

import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;

import java.util.Iterator;
import java.util.Random;

public class CornBlock extends Block {
    public static final IntProperty AGE = Properties.AGE_2;
    public static final IntProperty HEIGHT = IntProperty.of("height", 0, 2);

    public CornBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(AGE, 0).with(HEIGHT, 0));
    }

    public boolean hasRandomTicks(BlockState state) {
        return state.get(AGE) < 2;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = state.get(AGE);
            if (i < 2) {
                if (isWaterNearby(world, pos) && isTopOfAge(state)) {
                    if (random.nextInt(20) == 0) {
                        if (i == 0) {
                            world.setBlockState(pos, state.with(AGE, i + 1), 2);
                            world.setBlockState(pos.up(), state.with(AGE, i + 1).with(HEIGHT, 1), 2);
                        } else {
                            world.setBlockState(pos.down(), state.with(AGE, i + 1).with(HEIGHT, 0), 2);
                            world.setBlockState(pos, state.with(AGE, i + 1).with(HEIGHT, 1), 2);
                            world.setBlockState(pos.up(), state.with(AGE, i + 1).with(HEIGHT, 2), 2);
                        }
                    }
                }
            }
        }
    }

    private boolean isTopOfAge(BlockState state) {
        if (state.get(AGE) == 0) return true;
        else if (state.get(AGE) == 1) return state.get(HEIGHT) == 1;
        else return state.get(HEIGHT) == 2;
    }

    private static boolean isWaterNearby(WorldView world, BlockPos pos) {
        Iterator<BlockPos> var2 = BlockPos.iterate(pos.add(-5, -1, -5), pos.add(5, 1, 5)).iterator();

        BlockPos blockPos;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            blockPos = var2.next();
        } while(!world.getFluidState(blockPos).isIn(FluidTags.WATER));

        return true;
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return ItemRegistry.CORN.getDefaultStack();
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE, HEIGHT);
    }
}
