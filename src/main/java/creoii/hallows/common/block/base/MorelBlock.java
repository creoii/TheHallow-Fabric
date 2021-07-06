package creoii.hallows.common.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public abstract class MorelBlock extends Block implements Fertilizable {
    protected static final VoxelShape BIG_CAP = Block.createCuboidShape(5.0D, 3.0D, 5.0D, 11.0D, 13.0D, 11.0D);
    protected static final VoxelShape BIG_STEM = Block.createCuboidShape(7.0D, 0.0D, 7.0D, 9.0D, 3.0D, 9.0D);
    protected static final VoxelShape NORMAL_CAP = Block.createCuboidShape(6.0D, 2.0D, 6.0D, 10.0D, 9.0D, 10.0D);
    protected static final VoxelShape SMALL_CAP = Block.createCuboidShape(7.0D, 1.0D, 7.0D, 10.0D, 6.0D, 10.0D);
    protected static final VoxelShape SMALL_STEM = Block.createCuboidShape(7.0D, 0.0D, 8.0D, 8.0D, 1.0D, 9.0D);
    private static final VoxelShape BIG_SHAPE = VoxelShapes.union(BIG_CAP, BIG_STEM);
    private static final VoxelShape NORMAL_SHAPE = VoxelShapes.union(NORMAL_CAP, BIG_STEM);
    private static final VoxelShape SMALL_SHAPE = VoxelShapes.union(SMALL_CAP, SMALL_STEM);
    public static final EnumProperty<Size> SIZE = EnumProperty.of("size", Size.class);

    public MorelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(getDefaultState().with(SIZE, Size.NORMAL));
    }

    public abstract void disturb(World world, BlockState state, BlockPos pos);

    @Override
    public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        disturb(world, state, pos);
        super.onLandedUpon(world, state, pos, entity, fallDistance);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
        disturb(world, state, hit.getBlockPos());
        super.onProjectileHit(world, state, hit, projectile);
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(SIZE) == Size.BIG ? BIG_SHAPE : state.get(SIZE) == Size.NORMAL ? NORMAL_SHAPE : SMALL_SHAPE;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return state.get(SIZE) == Size.SMALL ? SMALL_STEM : super.getCollisionShape(state, world, pos, context);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(SIZE);
    }

    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return state.get(SIZE) != Size.BIG;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.get(SIZE) == Size.SMALL ? state.with(SIZE, Size.NORMAL) : state.with(SIZE, Size.BIG));
    }

    public enum Size implements StringIdentifiable {
        BIG,
        NORMAL,
        SMALL;

        @Override
        public String asString() {
            return this == BIG ? "big" : this == NORMAL ? "normal" : "small";
        }
    }
}