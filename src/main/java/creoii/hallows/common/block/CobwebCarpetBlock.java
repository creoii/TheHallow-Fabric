package creoii.hallows.common.block;

import net.minecraft.block.AbstractLichenBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class CobwebCarpetBlock extends AbstractLichenBlock {
    public CobwebCarpetBlock(Settings settings) {
        super(settings);
    }

    public boolean isTranslucent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }
}