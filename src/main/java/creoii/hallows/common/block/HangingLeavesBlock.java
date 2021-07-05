package creoii.hallows.common.block;

import net.minecraft.block.Block;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.shape.VoxelShape;

public class HangingLeavesBlock extends Block {
    public static final EnumProperty<Half> HALF = EnumProperty.of("half", Half.class);
    protected static final VoxelShape LARGE_SHAPE = Block.createCuboidShape(1, 0, 1, 15, 16, 15);
    protected static final VoxelShape SMALL_SHAPE = Block.createCuboidShape(4, 0, 4, 12, 16, 12);

    public HangingLeavesBlock(Settings settings) {
        super(settings);
    }

    public enum Half implements StringIdentifiable {
        LARGE,
        SMALL;

        @Override
        public String asString() {
            return this == LARGE ? "large" : "small";
        }
    }
}