package creoii.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.PotionUtil;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.text.LiteralText;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Random;

public class GiantCauldronBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;
    public static final EnumProperty<Liquid> LIQUID = EnumProperty.of("liquid", Liquid.class);
    private static final VoxelShape SOUTH = VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D), Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape SOUTH_FOOT = Block.createCuboidShape(12.0D, 0.0D, 12.0D, 16.0D, 4.0D, 16.0D);
    private static final VoxelShape NORTH = VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.createCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D));
    private static final VoxelShape NORTH_FOOT = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 4.0D, 4.0D);
    private static final VoxelShape EAST = VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.createCuboidShape(4.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape EAST_FOOT = Block.createCuboidShape(0.0D, 0.0D, 12.0D, 4.0D, 4.0D, 16.0D);
    private static final VoxelShape WEST = VoxelShapes.union(Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D), Block.createCuboidShape(12.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape WEST_FOOT = Block.createCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 4.0D, 4.0D);
    private static final VoxelShape BOTTOM = Block.createCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    private static final VoxelShape LIQUID_SHAPE = Block.createCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 14.0D, 16.0D);
    private List<StatusEffectInstance> storedEffects;

    public GiantCauldronBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(HALF, BlockHalf.BOTTOM).with(LIQUID, Liquid.EMPTY));
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getStackInHand(hand);

        if (held.getItem() == this.asItem()) return ActionResult.PASS;

        if (state.get(LIQUID) == Liquid.EMPTY) {
            if (held.getItem() == Items.FLINT_AND_STEEL) {
                world.setBlockState(pos, state.with(LIQUID, Liquid.PORTAL), 3);
            } else if (held.getItem() instanceof PotionItem) {
                this.storedEffects = PotionUtil.getPotionEffects(held);
            } else if (held.getItem() == Items.WATER_BUCKET) {
                world.setBlockState(pos, state.with(LIQUID, Liquid.WATER), 3);
            } else if (held.getItem() == Items.LAVA_BUCKET) {
                world.setBlockState(pos, state.with(LIQUID, Liquid.LAVA), 3);
            } else if (held.getItem() == Items.POWDER_SNOW_BUCKET) {
                world.setBlockState(pos, state.with(LIQUID, Liquid.SNOW), 3);
            } else if (held.getItem() == Items.BUCKET) {
                if (state.get(LIQUID) == Liquid.WATER) {
                    player.setStackInHand(hand, new ItemStack(Items.WATER_BUCKET));
                } else if (state.get(LIQUID) == Liquid.LAVA) {
                    player.setStackInHand(hand, new ItemStack(Items.LAVA_BUCKET));
                } else if (state.get(LIQUID) == Liquid.SNOW) {
                    player.setStackInHand(hand, new ItemStack(Items.POWDER_SNOW_BUCKET));
                }
                world.setBlockState(pos, state.with(LIQUID, Liquid.EMPTY), 3);
            } else if (held.getItem() == Items.GLASS_BOTTLE) {
                ItemStack potion = PotionUtil.setCustomPotionEffects(new ItemStack(Items.POTION), storedEffects);
                potion.setCustomName(new LiteralText("Mixed Potion"));
                player.setStackInHand(hand, potion);
            }
            return ActionResult.success(world.isClient);
        } else return ActionResult.PASS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return switch (state.get(FACING)) {
            default -> VoxelShapes.union(SOUTH, BOTTOM, SOUTH_FOOT);
            case SOUTH -> state.get(HALF) == BlockHalf.BOTTOM ? VoxelShapes.union(SOUTH, BOTTOM, SOUTH_FOOT) : state.get(LIQUID) != Liquid.EMPTY ? VoxelShapes.union(SOUTH, LIQUID_SHAPE) : SOUTH;
            case NORTH -> state.get(HALF) == BlockHalf.BOTTOM ? VoxelShapes.union(NORTH, BOTTOM, NORTH_FOOT) : state.get(LIQUID) != Liquid.EMPTY ? VoxelShapes.union(NORTH, LIQUID_SHAPE) : NORTH;
            case WEST -> state.get(HALF) == BlockHalf.BOTTOM ? VoxelShapes.union(WEST, BOTTOM, WEST_FOOT) : state.get(LIQUID) != Liquid.EMPTY ? VoxelShapes.union(WEST, LIQUID_SHAPE) : WEST;
            case EAST -> state.get(HALF) == BlockHalf.BOTTOM ? VoxelShapes.union(EAST, BOTTOM, EAST_FOOT) : state.get(LIQUID) != Liquid.EMPTY ? VoxelShapes.union(EAST, LIQUID_SHAPE) : EAST;
        };
    }

    protected static Direction getFacing(ItemPlacementContext context) {
        float f = MathHelper.wrapDegrees(context.getPlayerYaw()) / 45.0F;

        if (f > -2.0F && f <= 0.0F) {
            return Direction.NORTH;
        } else if (f > 0.0F && f <= 2.0F) {
            return Direction.WEST;
        } else if (f > 2.0F) {
            return Direction.SOUTH;
        } else {
            return Direction.EAST;
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        Direction.Axis axis = state.get(FACING).getAxis();
        if (mirror != BlockMirror.NONE) {
            if ((mirror == BlockMirror.FRONT_BACK && axis == Direction.Axis.X) || (mirror == BlockMirror.LEFT_RIGHT && axis == Direction.Axis.Z)) {
                return state.rotate(BlockRotation.COUNTERCLOCKWISE_90);
            }
            else return state.rotate(BlockRotation.CLOCKWISE_90);
        } else return state;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos pos = ctx.getBlockPos();
        BlockState down = ctx.getWorld().getBlockState(pos.down());
        BlockState up = ctx.getWorld().getBlockState(pos.up());

        if (down.getBlock() instanceof GiantCauldronBlock && down.get(HALF) == BlockHalf.BOTTOM) {
            return this.getDefaultState().with(FACING, down.get(FACING)).with(HALF, BlockHalf.TOP);
        } else if (up.getBlock() instanceof GiantCauldronBlock && up.get(HALF) == BlockHalf.TOP) {
            return this.getDefaultState().with(FACING, up.get(FACING)).with(HALF, BlockHalf.BOTTOM);
        }

        return this.getDefaultState().with(FACING, getFacing(ctx)).with(HALF, MathHelper.sin(ctx.getPlayer().getPitch(1.0F) * ((float) Math.PI / 180.0F)) > 0.0F ? BlockHalf.BOTTOM : BlockHalf.TOP);

    }

    @Override
    @SuppressWarnings("deprecation")
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isClient) {
            if (entityIn.isOnFire() && state.get(LIQUID) == Liquid.WATER) entityIn.extinguish();

            else if (state.get(LIQUID) == Liquid.LAVA) entityIn.setOnFireFor(8);

            else if (state.get(LIQUID) == Liquid.POTION && entityIn instanceof LivingEntity) {
                for (StatusEffectInstance effect : storedEffects) {
                    if (effect.getEffectType().isInstant()) {
                        effect.getEffectType().applyInstantEffect(entityIn, entityIn, ((LivingEntity)entityIn), effect.getAmplifier(), 1.0D);
                    } else {
                        ((LivingEntity)entityIn).addStatusEffect(new StatusEffectInstance(effect));
                    }
                }
            }
        }
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIQUID) == Liquid.PORTAL) {
            if (random.nextInt(100) == 0) {
                world.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
            }

            for (int i = 0; i < 4; ++i) {
                double d0 = (double) pos.getX() + random.nextDouble();
                double d1 = (double) pos.getY() + random.nextDouble();
                double d2 = (double) pos.getZ() + random.nextDouble();
                double d3 = ((double) random.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double) random.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double) random.nextFloat() - 0.5D) * 0.5D;
                int j = random.nextInt(2) * 2 - 1;
                if (!world.getBlockState(pos.west()).isOf(this) && !world.getBlockState(pos.east()).isOf(this)) {
                    d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                    d3 = random.nextFloat() * 2.0F * (float) j;
                } else {
                    d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                    d5 = random.nextFloat() * 2.0F * (float) j;
                }

                world.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, LIQUID);
    }

    public enum Liquid implements StringIdentifiable {
        EMPTY("empty"),
        WATER("water"),
        PORTAL("portal"),
        LAVA("lava"),
        SNOW("snow"),
        POTION("potion");

        private final String name;

        Liquid(String name) {
            this.name = name;
        }

        public String asString() {
            return this.name;
        }
    }
}