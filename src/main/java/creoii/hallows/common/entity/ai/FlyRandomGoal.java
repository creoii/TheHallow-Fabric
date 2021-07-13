package creoii.hallows.common.entity.ai;

import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class FlyRandomGoal extends Goal {
    private final LivingEntity entity;

    public FlyRandomGoal(LivingEntity entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    public boolean canStart() {
        return entity.getRandom().nextInt(7) == 0;
    }

    public boolean shouldContinue() {
        return false;
    }

    public void tick() {
        BlockPos blockpos = entity.getBlockPos();
        for (int i = 0; i < 3; ++i) {
            BlockPos pos = blockpos.add(entity.getRandom().nextInt(15) - 7, entity.getRandom().nextInt(11) - 5, entity.getRandom().nextInt(15) - 7);
            if (entity.world.isAir(pos)) {
                entity.move(MovementType.SELF, new Vec3d((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D));
                if (entity.getAttacking() == null) {
                    entity.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, new Vec3d((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D));
                }
                break;
            }
        }
    }
}