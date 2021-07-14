package creoii.hallows.common.entity;

import creoii.hallows.core.registry.EntityRegistry;
import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.function.Predicate;

public class HauntEntity extends HostileEntity implements Angerable {
    private static final TrackedData<Boolean> ANGRY = DataTracker.registerData(HauntEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int angerTime;
    private UUID targetUuid;
    private int attackTicksLeft;

    public HauntEntity(EntityType<? extends HauntEntity> type, World world) {
        super(type, world);
        this.experiencePoints = 4;
    }

    public HauntEntity(World world) {
        super(EntityRegistry.GHOST, world);
        this.experiencePoints = 4;
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, LivingEntity.class, 3.0F, 1.0F));
        this.targetSelector.add(1, new FollowTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new TeleportTowardsPlayerGoal(this, this::shouldAngerAt));
        this.targetSelector.add(3, new ChargePlayerGoal(this, 30));
        this.targetSelector.add(4, new UniversalAngerGoal(this, false));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.125D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 75.0D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 1.0D);
    }

    @Nullable
    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(ItemRegistry.HAUNT_SPAWN_EGG);
    }

    public void setTarget(@Nullable LivingEntity target) {
        super.setTarget(target);
        if (target == null) this.dataTracker.set(ANGRY, false);
        else this.dataTracker.set(ANGRY, true);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(ANGRY, false);
    }

    public boolean isAngry() {
        return this.dataTracker.get(ANGRY);
    }

    @Override
    public int getAngerTime() {
        return this.angerTime;
    }

    @Override
    public void setAngerTime(int ticks) {
        this.angerTime = ticks;
    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return this.targetUuid;
    }

    @Override
    public void setAngryAt(@Nullable UUID uuid) {
        this.targetUuid = uuid;
    }

    @Override
    public void chooseRandomAngerTime() {
    }

    public void tickMovement() {
        super.tickMovement();
        if (this.attackTicksLeft > 0) --this.attackTicksLeft;
    }

    public int getAttackTicksLeft() {
        return this.attackTicksLeft;
    }

    public boolean tryAttack(Entity target) {
        this.attackTicksLeft = 20;
        this.world.sendEntityStatus(this, (byte)4);
        float f = (float) this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
        float g = (int)f > 0 ? f / 2.0F + (float)this.random.nextInt((int)f) : f;
        boolean bl = target.damage(DamageSource.mob(this), g);
        if (bl) {
            target.setVelocity(target.getVelocity().add(0.0D, 0.2D, 0.0D));
            this.applyDamageEffects(this, target);
        }

        return bl;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 2.55F;
    }

    protected void teleportRandomly() {
        if (!this.world.isClient() && this.isAlive()) {
            double d = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double e = this.getY() + (double)(this.random.nextInt(64) - 32);
            double f = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            this.teleportTo(d, e, f);
        }
    }

    private boolean teleportTo(Entity entity) {
        Vec3d vec3d = new Vec3d(this.getX() - entity.getX(), this.getBodyY(0.5D) - entity.getEyeY(), this.getZ() - entity.getZ());
        vec3d = vec3d.normalize();
        double e = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.x * 16.0D;
        double f = this.getY() + (double)(this.random.nextInt(16) - 8) - vec3d.y * 16.0D;
        double g = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vec3d.z * 16.0D;
        return this.teleportTo(e, f, g);
    }

    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, y, z);

        while(mutable.getY() > this.world.getBottomY() && !this.world.getBlockState(mutable).getMaterial().blocksMovement()) {
            mutable.move(Direction.DOWN);
        }

        BlockState blockState = this.world.getBlockState(mutable);
        boolean bl = blockState.getMaterial().blocksMovement();
        boolean bl2 = blockState.getFluidState().isIn(FluidTags.WATER);
        if (bl && !bl2) {
            boolean bl3 = this.teleport(x, y, z, true);
            if (bl3 && !this.isSilent()) {
                this.world.playSound(null, this.prevX, this.prevY, this.prevZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }
            return bl3;
        } else return false;
    }

    private static class TeleportTowardsPlayerGoal extends FollowTargetGoal<PlayerEntity> {
        private final HauntEntity haunt;
        private PlayerEntity targetPlayer;
        private int lookAtPlayerWarmup;
        private int ticksSinceUnseenTeleport;
        private final TargetPredicate staringPlayerPredicate;
        private final TargetPredicate validTargetPredicate = TargetPredicate.createAttackable();

        public TeleportTowardsPlayerGoal(HauntEntity haunt, @Nullable Predicate<LivingEntity> predicate) {
            super(haunt, PlayerEntity.class, 10, false, false, predicate);
            this.haunt = haunt;
            this.staringPlayerPredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.getFollowRange()).setPredicate((playerEntity) -> !playerEntity.canSee(this.haunt));
        }

        public boolean canStart() {
            this.targetPlayer = this.haunt.world.getClosestPlayer(this.staringPlayerPredicate, this.haunt);
            return this.targetPlayer != null;
        }

        public void start() {
            this.lookAtPlayerWarmup = 5;
            this.ticksSinceUnseenTeleport = 0;
            this.haunt.setAngryAt(targetPlayer.getUuid());
        }

        public void stop() {
            this.targetPlayer = null;
            super.stop();
        }

        public boolean shouldContinue() {
            if (this.targetPlayer != null) {
                if (targetPlayer.canSee(this.haunt)) return false;
                else {
                    this.haunt.lookAtEntity(this.targetPlayer, 10.0F, 10.0F);
                    return true;
                }
            } else return this.targetEntity != null && this.validTargetPredicate.test(this.haunt, this.targetEntity) || super.shouldContinue();
        }

        public void tick() {
            if (this.haunt.getTarget() == null) super.setTargetEntity(null);

            if (this.targetPlayer != null) {
                if (--this.lookAtPlayerWarmup <= 0) {
                    this.targetEntity = this.targetPlayer;
                    this.targetPlayer = null;
                    super.start();
                }
            } else {
                if (this.targetEntity != null && !this.haunt.hasVehicle()) {
                    System.out.println("TEST A");
                    if (!targetEntity.canSee(this.haunt)) {
                        System.out.println("CANT SEE YOU");
                        if (this.targetEntity.squaredDistanceTo(this.haunt) <= 24.0D) {
                            System.out.println("TELEPORTED");
                            this.haunt.teleportRandomly();
                        }

                        this.ticksSinceUnseenTeleport = 0;
                    } else if (this.targetEntity.squaredDistanceTo(this.haunt) > 256.0D && ++this.ticksSinceUnseenTeleport >= 150 && this.haunt.teleportTo(this.targetEntity)) {
                        this.ticksSinceUnseenTeleport = 0;
                    }
                }
                super.tick();
            }
        }
    }

    private static class ChargePlayerGoal extends FollowTargetGoal<PlayerEntity> {
        private final HauntEntity haunt;
        private final int probability;
        private int ticksBeenCharging;

        public ChargePlayerGoal(HauntEntity haunt, int probability) {
            super(haunt, PlayerEntity.class, false);
            this.haunt = haunt;
            this.probability = probability;
        }

        @Override
        public boolean canStart() {
            if (this.haunt.hasVehicle() || this.targetEntity == null) return false;
            return this.haunt.getRandom().nextInt(probability) == 0 && (this.targetEntity.squaredDistanceTo(this.haunt) >= 12.0D || super.canStart());
        }

        @Override
        public boolean shouldContinue() {
            return this.targetEntity != null && (this.targetEntity.squaredDistanceTo(this.haunt) < 1.0D || ticksBeenCharging >= 120);
        }

        @Override
        public void start() {
            ticksBeenCharging = 0;
            this.haunt.setAngryAt(targetEntity.getUuid());
            super.start();
        }

        @Override
        public void tick() {
            System.out.println("TEST B");
            if (this.haunt.getTarget() == null) super.setTargetEntity(null);

            if (this.targetEntity != null && !this.haunt.hasVehicle()) {
                System.out.println("CHARGING");
                BlockPos pos = target.getBlockPos();
                haunt.getMoveControl().moveTo((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, 0.333D);
                haunt.getLookControl().lookAt(targetEntity);
                if (++ticksBeenCharging < 120) ticksBeenCharging = 0;
            }
            super.tick();
        }
    }
}