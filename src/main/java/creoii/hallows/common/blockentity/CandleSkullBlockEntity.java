package creoii.hallows.common.blockentity;

import creoii.hallows.core.registry.BlockEntityRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class CandleSkullBlockEntity extends BlockEntity {
    public CandleSkullBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.CANDLE_SKULL, pos, state);
    }
}