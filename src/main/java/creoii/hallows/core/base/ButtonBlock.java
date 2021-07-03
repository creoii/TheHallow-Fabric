package creoii.hallows.core.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class ButtonBlock extends net.minecraft.block.AbstractButtonBlock {
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
        world.setBlockState(pos, (BlockState)state.with(POWERED, true), 3);
        this.updateNeighbors(state, world, pos);
        world.getBlockTickScheduler().schedule(pos, this, this.getPressTicks());
    }

    private void tryPowerWithProjectiles(BlockState state, World world, BlockPos pos) {
        List<? extends Entity> list = world.getNonSpectatingEntities(PersistentProjectileEntity.class, state.getOutlineShape(world, pos).getBoundingBox().offset(pos));
        boolean bl = !list.isEmpty();
        boolean bl2 = (Boolean)state.get(POWERED);
        if (bl != bl2) {
            world.setBlockState(pos, (BlockState)state.with(POWERED, bl), 3);
            this.updateNeighbors(state, world, pos);
            this.playClickSound((PlayerEntity)null, world, pos, bl);
            world.emitGameEvent((Entity)list.stream().findFirst().orElse(null), bl ? GameEvent.BLOCK_PRESS : GameEvent.BLOCK_UNPRESS, pos);
        }

        if (bl) world.getBlockTickScheduler().schedule(new BlockPos(pos), this, this.getPressTicks());
    }

    private void updateNeighbors(BlockState state, World world, BlockPos pos) {
        world.updateNeighborsAlways(pos, this);
        world.updateNeighborsAlways(pos.offset(getDirection(state).getOpposite()), this);
    }
}