package creoii.hallows.common.entity;

import creoii.hallows.common.entity.ai.TeleportTowardTargetGoal;
import creoii.hallows.core.registry.EntityRegistry;
import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.entity.*;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class HauntEntity extends HostileEntity implements Angerable {
    private static final TrackedData<Boolean> ANGRY = DataTracker.registerData(HauntEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private int angerTime;
    private UUID targetUuid;

    public HauntEntity(EntityType<? extends HauntEntity> type, World world) {
        super(type, world);
        this.experiencePoints = 4;
    }

    public HauntEntity(World world) {
        super(EntityRegistry.HAUNT, world);
        this.experiencePoints = 4;
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1.0D));
        this.goalSelector.add(3, new LookAtEntityGoal(this, LivingEntity.class, 3.0F, 1.0F));
        this.targetSelector.add(1, new FollowTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(2, new TeleportTowardTargetGoal<>(this, PlayerEntity.class, this::shouldAngerAt));
        this.targetSelector.add(3, new ChargeTargetGoal(this, 4));
        this.targetSelector.add(4, new UniversalAngerGoal<>(this, true));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.125D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 12.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 110.0D).add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 0.9D);
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

    public boolean tryAttack(Entity target) {
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

    private static class ChargeTargetGoal extends FollowTargetGoal<PlayerEntity> {
        private final HauntEntity haunt;
        private final int probability;
        private int ticksBeenCharging;

        public ChargeTargetGoal(HauntEntity haunt, int probability) {
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