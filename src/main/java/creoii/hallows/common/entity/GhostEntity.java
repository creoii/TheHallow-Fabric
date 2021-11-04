package creoii.hallows.common.entity;

import creoii.hallows.common.entity.ai.FlyMoveControl;
import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class GhostEntity extends HostileEntity {
    protected static final TrackedData<Byte> GHOST_FLAGS = DataTracker.registerData(GhostEntity.class, TrackedDataHandlerRegistry.BYTE);
    private GhostEntity leader;

    public GhostEntity(EntityType<? extends GhostEntity> type, World world) {
        super(type, world);
        this.moveControl = new FlyMoveControl(this);
        this.experiencePoints = 2;
    }

    public void move(MovementType movementType, Vec3d movement) {
        super.move(movementType, movement);
        this.checkBlockCollision();
    }

    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);
    }

    protected void initGoals() {
        super.initGoals();
        //this.goalSelector.add(0, new MeleeAttackGoal(this, 1.0D, false));
        //this.goalSelector.add(3, new FlyRandomGoal(this, 8));
        this.goalSelector.add(4, new GhostEntity.ChargeTargetGoal());
        this.goalSelector.add(9, new LookAtEntityGoal(this, LivingEntity.class, 3.0F, 1.0F));
        this.targetSelector.add(1, new ActiveTargetGoal(this, PlayerEntity.class, true));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 30.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0D);
    }

    @Nullable
    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(ItemRegistry.GHOST_SPAWN_EGG);
    }

    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(GHOST_FLAGS, (byte)0);
    }

    private boolean areFlagsSet(int mask) {
        int i = this.dataTracker.get(GHOST_FLAGS);
        return (i & mask) != 0;
    }

    private void setGhostFlag(int mask, boolean value) {
        int i = this.dataTracker.get(GHOST_FLAGS);
        if (value) i = i | mask;
        else i = i & ~mask;

        this.dataTracker.set(GHOST_FLAGS, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.areFlagsSet(1);
    }

    public void setCharging(boolean charging) {
        this.setGhostFlag(1, charging);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VEX_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_GHAST_DEATH;
    }

    public float getBrightnessAtEyes() {
        return 1.0F;
    }

    public boolean tryAttack(Entity target) {
        boolean bl = super.tryAttack(target);
        if (bl && this.getMainHandStack().isEmpty() && target instanceof LivingEntity) {
            ((LivingEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 25), this);
        }

        return bl;
    }

    private class ChargeTargetGoal extends Goal {
        public ChargeTargetGoal() {
            this.setControls(EnumSet.of(Control.MOVE));
        }

        public boolean canStart() {
            if (GhostEntity.this.getTarget() != null && !GhostEntity.this.getMoveControl().isMoving() && GhostEntity.this.random.nextInt(7) == 0) {
                return GhostEntity.this.squaredDistanceTo(GhostEntity.this.getTarget()) > 5.0D;
            } else return false;
        }

        public boolean shouldContinue() {
            return GhostEntity.this.getMoveControl().isMoving() && GhostEntity.this.isCharging() && GhostEntity.this.getTarget() != null && GhostEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingEntity = GhostEntity.this.getTarget();
            Vec3d vec3d = livingEntity.getEyePos();
            GhostEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
            GhostEntity.this.setCharging(true);
            GhostEntity.this.playSound(SoundEvents.ENTITY_GHAST_SCREAM, 1.0F, 1.0F);
        }

        public void stop() {
            GhostEntity.this.setCharging(false);
        }

        public void tick() {
            LivingEntity livingEntity = GhostEntity.this.getTarget();
            if (GhostEntity.this.getBoundingBox().intersects(livingEntity.getBoundingBox())) {
                GhostEntity.this.tryAttack(livingEntity);
                GhostEntity.this.setCharging(false);
            } else {
                double d = GhostEntity.this.squaredDistanceTo(livingEntity);
                if (d < 9.0D) {
                    Vec3d vec3d = livingEntity.getEyePos();
                    GhostEntity.this.moveControl.moveTo(vec3d.x, vec3d.y, vec3d.z, 1.0D);
                }

                if (d < 4.5F) {
                    GhostEntity.this.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 20, 0, true, false));
                }
            }
        }
    }
}