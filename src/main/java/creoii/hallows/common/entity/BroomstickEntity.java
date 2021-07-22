package creoii.hallows.common.entity;

import creoii.hallows.core.registry.EntityRegistry;
import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class BroomstickEntity extends Entity {
    private int field_7708;
    private float velocityDecay;
    private float yawVelocity;
    private double x;
    private double y;
    private double z;
    private double boatYaw;
    private double boatPitch;
    private boolean pressingLeft;
    private boolean pressingRight;
    private boolean pressingForward;
    private boolean pressingBack;

    public BroomstickEntity(EntityType<?> type, World world) {
        super(type, world);
    }

    public BroomstickEntity(World world, double x, double y, double z) {
        super(EntityRegistry.BROOMSTICK, world);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    public BroomstickEntity(World world) {
        super(EntityRegistry.BROOMSTICK, world);
    }

    protected MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    public boolean collidesWith(Entity other) {
        return (other.isCollidable() || other.isPushable()) && !this.isConnectedThroughVehicle(other);
    }

    public boolean isCollidable() {
        return true;
    }

    public boolean isPushable() {
        return true;
    }

    public double getMountedHeightOffset() {
        return -0.1D;
    }

    public boolean collides() {
        return !this.isRemoved();
    }

    public void updateTrackedPositionAndAngles(double x, double y, double z, float yaw, float pitch, int interpolationSteps, boolean interpolate) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.boatYaw = yaw;
        this.boatPitch = pitch;
        this.field_7708 = 10;
    }

    public Direction getMovementDirection() {
        return Direction.fromRotation(this.getYaw());
    }

    public boolean damage(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) return false;
        else if (!this.world.isClient && !this.isRemoved()) {
            this.scheduleVelocityUpdate();
            this.emitGameEvent(GameEvent.ENTITY_DAMAGED, source.getAttacker());
            boolean bl = source.getAttacker() instanceof PlayerEntity && ((PlayerEntity)source.getAttacker()).getAbilities().creativeMode;
            if (bl) {
                if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) this.dropItem(ItemRegistry.BROOMSTICK);
                this.discard();
            }

            return true;
        } else return true;
    }

    public void tick() {
        if (!this.world.isClient) this.removeAllPassengers();

        super.tick();
        this.method_7555();
        if (this.isLogicalSideForUpdatingMovement()) {
            this.updateVelocity();
            if (this.world.isClient) this.updatePaddles();
            this.move(MovementType.SELF, this.getVelocity());
        } else this.setVelocity(Vec3d.ZERO);

        this.checkBlockCollision();
        List<Entity> list = this.world.getOtherEntities(this, this.getBoundingBox().expand(0.20000000298023224D, -0.009999999776482582D, 0.20000000298023224D), EntityPredicates.canBePushedBy(this));
        if (!list.isEmpty()) {
            boolean bl = !this.world.isClient && !(this.getPrimaryPassenger() instanceof PlayerEntity);

            for (Entity e : list) {
                if (!e.hasPassenger(this)) {
                    if (bl && this.getPassengerList().size() < 2 && !e.hasVehicle() && e.getWidth() < this.getWidth() && e instanceof LivingEntity && !(e instanceof WaterCreatureEntity) && !(e instanceof PlayerEntity)) {
                        e.startRiding(this);
                    } else this.pushAwayFrom(e);
                }
            }
        }
    }

    private void method_7555() {
        if (this.isLogicalSideForUpdatingMovement()) {
            this.field_7708 = 0;
            this.updateTrackedPosition(this.getX(), this.getY(), this.getZ());
        }

        if (this.field_7708 > 0) {
            double d = this.getX() + (this.x - this.getX()) / (double)this.field_7708;
            double e = this.getY() + (this.y - this.getY()) / (double)this.field_7708;
            double f = this.getZ() + (this.z - this.getZ()) / (double)this.field_7708;
            double g = MathHelper.wrapDegrees(this.boatYaw - (double)this.getYaw());
            this.setYaw(this.getYaw() + (float)g / (float)this.field_7708);
            this.setPitch(this.getPitch() + (float)(this.boatPitch - (double)this.getPitch()) / (float)this.field_7708);
            --this.field_7708;
            this.setPosition(d, e, f);
            this.setRotation(this.getYaw(), this.getPitch());
        }
    }

    public void updateVelocity() {
        double e = this.hasNoGravity() ? 0.0D : -0.03999999910593033D;
        this.velocityDecay = 0.05F;
        double f = (this.getY()) / (double)this.getHeight();
        this.velocityDecay = 0.9F;
        Vec3d vec3d = this.getVelocity();
        this.setVelocity(vec3d.x * (double)this.velocityDecay, vec3d.y * (double)this.velocityDecay, vec3d.z * (double)this.velocityDecay);
        this.yawVelocity *= this.velocityDecay;
        if (f > 0.0D) {
            Vec3d vec3d2 = this.getVelocity();
            this.setVelocity(vec3d2.x, vec3d2.y, vec3d2.z);
        }
    }

    private void updatePaddles() {
        if (this.hasPassengers()) {
            float f = 0.0F;
            if (this.pressingLeft) --this.yawVelocity;
            if (this.pressingRight) ++this.yawVelocity;

            if (this.pressingRight != this.pressingLeft && !this.pressingForward && !this.pressingBack) f += 0.005F;
            this.setYaw(this.getYaw() + this.yawVelocity);

            if (this.pressingForward) f += 0.04F;
            if (this.pressingBack) f -= 0.02F;

            this.setVelocity(this.getVelocity().add(MathHelper.sin(-this.getYaw() * 0.017453292F) * f, this.getPitch() * 0.017453292F * f, MathHelper.cos(this.getYaw() * 0.017453292F) * f));
        }
    }

    public ActionResult interact(PlayerEntity player, Hand hand) {
        if (player.shouldCancelInteraction()) return ActionResult.PASS;

        if (!this.world.isClient) {
            return player.startRiding(this) ? ActionResult.CONSUME : ActionResult.PASS;
        } else return ActionResult.SUCCESS;
    }

    public void setInputs(boolean pressingLeft, boolean pressingRight, boolean pressingForward, boolean pressingBack) {
        this.pressingLeft = pressingLeft;
        this.pressingRight = pressingRight;
        this.pressingForward = pressingForward;
        this.pressingBack = pressingBack;
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    protected void initDataTracker() {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}