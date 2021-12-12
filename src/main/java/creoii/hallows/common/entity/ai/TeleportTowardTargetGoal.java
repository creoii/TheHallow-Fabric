package creoii.hallows.common.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.function.Predicate;

public class TeleportTowardTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    protected final Class<T> targetClass;
    private final MobEntity mob;
    private int ticksSinceUnseenTeleport;
    private final TargetPredicate staringPlayerPredicate;

    public TeleportTowardTargetGoal(MobEntity mob, Class<T> clazz, @Nullable Predicate<LivingEntity> predicate) {
        super(mob, clazz, 10, false, false, predicate);
        this.targetClass = clazz;
        this.mob = mob;
        this.staringPlayerPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.getFollowRange());
    }

    public boolean canStart() {
        this.targetEntity = this.mob.world.getClosestEntity(this.mob.world.getEntitiesByClass(this.targetClass, this.getSearchBox(this.getFollowRange()), (livingEntity) -> {
            return true;
        }), this.staringPlayerPredicate, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
        return this.targetEntity != null && !targetEntity.canSee(this.mob);
    }

    public void start() {
        this.ticksSinceUnseenTeleport = 0;
    }

    public boolean shouldContinue() {
        if (this.targetEntity != null) {
            if (!targetEntity.canSee(this.mob)) {
                this.mob.lookAtEntity(this.targetEntity, 10.0F, 10.0F);
                return true;
            }
        }
        return super.shouldContinue();
    }

    public void tick() {
        if (this.mob.getTarget() == null) super.setTargetEntity(null);

        if (this.targetEntity != null && !this.mob.hasVehicle()) {
            System.out.println("TEST A");
            if (!targetEntity.canSee(this.mob)) {
                System.out.println("CANT SEE YOU");
                if (this.targetEntity.squaredDistanceTo(this.mob) <= 24.0D) {
                    System.out.println("TELEPORTED");
                    teleportRandomly();
                }

                this.ticksSinceUnseenTeleport = 0;
            } else if (this.targetEntity.squaredDistanceTo(this.mob) > 256.0D && ++this.ticksSinceUnseenTeleport >= 150 && teleportTo(this.targetEntity)) {
                this.ticksSinceUnseenTeleport = 0;
            }
        }
        super.tick();
    }

    protected void teleportRandomly() {
        if (!mob.world.isClient() && mob.isAlive()) {
            double d = mob.getX() + (mob.getRandom().nextDouble() - 0.5D) * 64.0D;
            double e = mob.getY() + (double)(mob.getRandom().nextInt(64) - 32);
            double f = mob.getZ() + (mob.getRandom().nextDouble() - 0.5D) * 64.0D;
            this.teleportTo(d, e, f);
        }
    }

    private boolean teleportTo(Entity entity) {
        Vec3d vec3d = new Vec3d(mob.getX() - entity.getX(), mob.getBodyY(0.5D) - entity.getEyeY(), mob.getZ() - entity.getZ());
        vec3d = vec3d.normalize();
        double e = mob.getX() + (mob.getRandom().nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
        double f = mob.getY() + (double)(mob.getRandom().nextInt(16) - 8) - vec3d.y * 16.0D;
        double g = mob.getZ() + (mob.getRandom().nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
        return this.teleportTo(e, f, g);
    }

    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

        while(mutable.getY() > mob.world.getBottomY() && !mob.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }

        BlockState blockState = mob.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (bl && !bl2) {
            boolean bl3 = mob.teleport(x, y, z, true);
            if (bl3 && !mob.isSilent()) {
                mob.world.playSound(null, mob.prevX, mob.prevY, mob.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, mob.getSoundCategory(), 1.0F, 1.0F);
                mob.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
            return bl3;
        } else return false;
    }
}