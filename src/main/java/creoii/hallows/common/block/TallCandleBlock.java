package creoii.hallows.common.block;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMaps;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

import java.util.List;

public class TallCandleBlock extends CandleBlock {
    private static final VoxelShape ONE_CANDLE_SHAPE = Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 12.0D, 9.0D);
    private static final VoxelShape TWO_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 6.0D, 11.0D, 12.0D, 9.0D);
    private static final VoxelShape THREE_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 6.0D, 10.0D, 12.0D, 11.0D);
    private static final VoxelShape FOUR_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 12.0D, 10.0D);
    private static final Int2ObjectMap<List<Vec3d>> CANDLES_TO_PARTICLE_OFFSETS = Util.make(() -> {
        Int2ObjectMap<List<Vec3d>> int2ObjectMap = new Int2ObjectOpenHashMap<>();
        int2ObjectMap.defaultReturnValue(ImmutableList.of());
        int2ObjectMap.put(1, ImmutableList.of(new Vec3d(0.5D, 0.875D, 0.5D)));
        int2ObjectMap.put(2, ImmutableList.of(new Vec3d(0.375D, 0.81D, 0.5D), new Vec3d(0.625D, 0.875D, 0.44D)));
        int2ObjectMap.put(3, ImmutableList.of(new Vec3d(0.5D, 0.688D, 0.625D), new Vec3d(0.375D, 0.81D, 0.5D), new Vec3d(0.56D, 0.875D, 0.44D)));
        int2ObjectMap.put(4, ImmutableList.of(new Vec3d(0.44D, 0.688D, 0.56D), new Vec3d(0.625D, 0.81D, 0.56D), new Vec3d(0.375D, 0.81D, 0.375D), new Vec3d(0.56D, 0.875D, 0.375D)));
        return Int2ObjectMaps.unmodifiable(int2ObjectMap);
    });

    public TallCandleBlock(Settings settings) {
        super(settings);
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(CANDLES)) {
            default -> ONE_CANDLE_SHAPE;
            case 2 -> TWO_CANDLES_SHAPE;
            case 3 -> THREE_CANDLES_SHAPE;
            case 4 -> FOUR_CANDLES_SHAPE;
        };
    }

    @Override
    protected Iterable<Vec3d> getParticleOffsets(BlockState state) {
        //0.375
        return CANDLES_TO_PARTICLE_OFFSETS.get(state.get(CANDLES));
    }
}