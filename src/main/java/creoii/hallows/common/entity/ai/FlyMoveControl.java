package creoii.hallows.common.entity.ai;

import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class FlyMoveControl extends MoveControl {
    public FlyMoveControl(MobEntity entity) {
        super(entity);
    }

    public void tick() {
        if (this.state == State.MOVE_TO) {
            Vec3d vec3d = new Vec3d(this.targetX - entity.getX(), this.targetY - entity.getY(), this.targetZ - entity.getZ());
            double d = vec3d.length();
            if (d < entity.getBoundingBox().getAverageSideLength()) {
                this.state = State.WAIT;
                entity.setVelocity(entity.getVelocity().multiply(0.5D));
            } else {
                entity.setVelocity(entity.getVelocity().add(vec3d.multiply(this.speed * 0.05D / d)));
                if (entity.getTarget() == null) {
                    Vec3d vec3d2 = entity.getVelocity();
                    entity.setYaw(-((float) MathHelper.atan2(vec3d2.x, vec3d2.z)) * 57.295776F);
                    entity.bodyYaw = entity.getYaw();
                } else {
                    double e = entity.getTarget().getX() - entity.getX();
                    double f = entity.getTarget().getZ() - entity.getZ();
                    entity.setYaw(-((float)MathHelper.atan2(e, f)) * 57.295776F);
                    entity.bodyYaw = entity.getYaw();
                }
            }
        }
    }
}