package creoii.hallows.common.block.base;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;
import java.util.Random;

public class ButtonBlock extends AbstractButtonBlock {
    private final boolean wooden;
    private final int ticks;

    public ButtonBlock(boolean wooden, int ticks, Settings settings) {
        super(wooden, settings);
        this.wooden = wooden;
        this.ticks = ticks;
    }

    @Override
    protected SoundEvent getClickSound(boolean powered) {
        if (powered) return wooden ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON : SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
        else return wooden ? SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF : SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
    }

    private int getPressTicks() {
        return ticks;
    }

    public void powerOn(BlockState state, World world, BlockPos pos) {
        world.setBlockState(pos, state.with(POWERED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.createAndScheduleBlockTick(pos, this, this.getPressTicks());
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(getDirection(state).getOpposite()), this);
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED)) {
            if (this.wooden) {
                this.tryPowerWithProjectiles(state, world, pos);
            } else {
                world.setBlockState(pos, state.with(POWERED, false), 3);
                this.updateNeighbors(state, world, pos);
                this.playClickSound(null, world, pos, false);
                world.emitGameEvent(GameEvent.BLOCK_UNPRESS, pos);
            }
        }
    }

    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if (!world.isClient && this.wooden && !(Boolean)state.get(POWERED)) {
            this.tryPowerWithProjectiles(state, world, pos);
        }
    }

    private void tryPowerWithProjectiles(BlockState state, World world, BlockPos pos) {
        List<? extends Entity> entities = world.getNonSpectatingEntities(PersistentProjectileEntity.class, state.getOutlineShape(world, pos).getBoundingBox().offset(pos));
        boolean $$4 = !entities.isEmpty();
        boolean powered = state.get(POWERED);
        if ($$4 != powered) {
            world.setBlockState(pos, state.with(POWERED, $$4), 3);
            this.updateNeighbors(state, world, pos);
            this.playClickSound(null, world, pos, $$4);
            world.emitGameEvent(entities.stream().findFirst().orElse(null), $$4 ? GameEvent.BLOCK_PRESS : GameEvent.BLOCK_UNPRESS, pos);
        }

        if ($$4) {
            world.createAndScheduleBlockTick(new BlockPos(pos), this, this.getPressTicks());
        }
    }
}