package creoii.hallows.common.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

public class FlyRandomGoal extends Goal {
    private final MobEntity entity;
    private final int probability;

    public FlyRandomGoal(MobEntity entity, int probability) {
        this.entity = entity;
        this.probability = probability;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public boolean canStart() {
        return entity.getRandom().nextInt(this.probability) == 0;
    }

    public boolean shouldContinue() {
        return false;
    }

    public void tick() {
        BlockPos blockpos = entity.getBlockPos();
        for (int i = 0; i < 3; ++i) {
            BlockPos pos = blockpos.add(entity.getRandom().nextInt(15) - 7, entity.getRandom().nextInt(11) - 5, entity.getRandom().nextInt(15) - 7);
            if (entity.world.isAir(pos)) {
                entity.getMoveControl().moveTo((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 0.25D);
                if (entity.getAttacking() == null) {
                    entity.getLookControl().lookAt((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 180.0F, 20.0F);
                }
                break;
            }
        }
    }
}