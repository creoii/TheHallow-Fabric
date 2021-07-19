package creoii.hallows.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CandleBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class TallCandleBlock extends CandleBlock {
    private static final VoxelShape ONE_CANDLE_SHAPE = Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 12.0D, 9.0D);
    private static final VoxelShape TWO_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 6.0D, 11.0D, 12.0D, 9.0D);
    private static final VoxelShape THREE_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 6.0D, 10.0D, 12.0D, 11.0D);
    private static final VoxelShape FOUR_CANDLES_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 5.0D, 11.0D, 12.0D, 10.0D);

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
}