package creoii.hallows.common.entity;

import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class HauntEntity extends HostileEntity {
    private static final TrackedData<Boolean> ANGRY = DataTracker.registerData(HauntEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public HauntEntity(EntityType<? extends HauntEntity> type, World world) {
        super(type, world);
        this.experiencePoints = 4;
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new MeleeAttackGoal(this, this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED), false));
        this.targetSelector.add(0, new HauntEntity.TeleportOrMoveTowardsPlayerGoal(this));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, .8D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.9D);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGRY, false);
    }

    @Nullable
    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(ItemRegistry.HAUNT_SPAWN_EGG);
    }

    public boolean tryAttack(Entity target) {
        this.world.sendEntityStatus(this, (byte) 4);
        float f = (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float g = (int) f > 0 ? f / 2.0F + (float) this.random.nextInt((int) f) : f;
        boolean bl = target.damage(DamageSource.mob(this), g);
        if (bl) {
            target.setVelocity(target.getVelocity().add(0.0D, 0.25D, 0.0D));
            this.applyDamageEffects(this, target);
        }

        return bl;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 2.75F;
    }

    public boolean isAngry() {
        return this.dataTracker.get(ANGRY);
    }

    public void setAngry(boolean angry) {
        this.dataTracker.set(ANGRY, angry);
    }

    public static boolean canSpawn(EntityType<? extends HauntEntity> type, WorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.down()).isSolidBlock(world, pos.down());
    }

    private void teleportTo(Entity entity) {
        Vec3d vec3d = new Vec3d(this.getX() - entity.getX(), this.getBodyY(0.5D) - entity.getEyeY(), this.getZ() - entity.getZ());
        vec3d = vec3d.normalize();
        double e = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
        double f = this.getY() + (double)(this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
        double g = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
        this.teleportTo(e, f, g);
    }

    private void teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

        while(mutable.getY() > this.world.getBottomY() && !this.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }

        BlockState blockState = this.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (bl && !bl2) {
            this.teleport(x, y, z, true);
        }
    }

    private boolean isPlayerStaring(PlayerEntity player) {
        ItemStack itemStack = player.getInventory().armor.get(3);
        if (itemStack.isOf(Blocks.CARVED_PUMPKIN.asItem())) {
            return false;
        } else {
            Vec3d vec3d = player.getRotationVec(1.0F).normalize();
            Vec3d vec3d2 = new Vec3d(this.getX() - player.getX(), this.getY() - player.getY(), this.getZ() - player.getZ());
            double d = vec3d2.length();
            vec3d2 = vec3d2.normalize();
            return vec3d.dotProduct(vec3d2) > .5d / d && player.canSee(this);
        }
    }

    private static class TeleportOrMoveTowardsPlayerGoal extends ActiveTargetGoal<PlayerEntity> {
        private final HauntEntity haunt;
        private int timeSinceLookedAway;

        public TeleportOrMoveTowardsPlayerGoal(HauntEntity haunt) {
            super(haunt, PlayerEntity.class, false);
            this.haunt = haunt;
        }

        public boolean canStart() {
            this.findClosestTarget();
            return this.targetEntity != null;
        }

        public boolean shouldContinue() {
            if (this.targetEntity != null) {
                this.haunt.lookAtEntity(this.targetEntity, 10.0F, 10.0F);
                return true;
            } else {
                return super.shouldContinue();
            }
        }

        @Override
        public void stop() {
            this.haunt.getNavigation().stop();
            super.stop();
        }

        public void tick() {
            if (targetEntity != null) {
                if (!this.haunt.hasVehicle()) {
                    if (!haunt.isPlayerStaring((PlayerEntity) targetEntity)) {
                        if (this.targetEntity.distanceTo(this.haunt) < 48d && ++timeSinceLookedAway >= this.haunt.random.nextInt(75) + 25) {
                            this.haunt.teleportTo(targetEntity);
                            timeSinceLookedAway = 0;
                        }
                    } else {
                        timeSinceLookedAway = 0;
                        if (this.haunt.getHealth() < this.haunt.getMaxHealth() / 2f && !this.haunt.isAngry()) {
                            this.haunt.setAngry(true);
                        }

                        if (this.haunt.isAngry()) {
                            if (this.haunt.squaredDistanceTo(targetEntity) < 2D) {
                                this.haunt.getNavigation().stop();
                            } else {
                                this.haunt.getNavigation().startMovingTo(targetEntity, 1.75f);
                            }
                        } else {
                            if (this.haunt.squaredDistanceTo(targetEntity) < 2D) {
                                this.haunt.getNavigation().stop();
                            } else {
                                this.haunt.getNavigation().startMovingTo(targetEntity, .75f);
                            }
                        }
                    }
                }
                super.tick();
            }
        }
    }
}