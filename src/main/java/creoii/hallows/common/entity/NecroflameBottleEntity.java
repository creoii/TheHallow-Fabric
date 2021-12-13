package creoii.hallows.common.entity;

import creoii.hallows.core.registry.BlockRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class NecroflameBottleEntity extends ThrownItemEntity implements FlyingItemEntity {
    public NecroflameBottleEntity(EntityType<? extends NecroflameBottleEntity> entityType, World world) {
        super(entityType, world);
    }

    public NecroflameBottleEntity(World world, LivingEntity owner) {
        super(EntityType.POTION, owner, world);
    }

    public NecroflameBottleEntity(World world, double x, double y, double z) {
        super(EntityType.POTION, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.SPLASH_POTION;
    }

    protected float getGravity() {
        return 0.05F;
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.world.isClient) {
            Direction direction = blockHitResult.getSide();
            BlockPos blockPos = blockHitResult.getBlockPos();
            BlockPos blockPos2 = blockPos.offset(direction);
            this.createFire(blockPos2);
            this.createFire(blockPos2.offset(direction.getOpposite()));

            for (Direction direction2 : Direction.Type.HORIZONTAL) {
                this.createFire(blockPos2.offset(direction2));
                if (random.nextInt(3) == 0) this.createFire(blockPos2.offset(direction2, 2));
            }
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            this.setEntitiesAflame();
            this.discard();
        }
    }

    private void setEntitiesAflame() {
        Box box = this.getBoundingBox().expand(4.0D, 2.0D, 4.0D);
        List<LivingEntity> list = this.world.getEntitiesByClass(LivingEntity.class, box, this::canBeSetAflame);
        if (!list.isEmpty()) {
            for (LivingEntity livingEntity : list) {
                double d = this.squaredDistanceTo(livingEntity);
                if (d < 16.0D && livingEntity.hurtByWater()) {
                    livingEntity.setOnFireFor(200);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    private void createFire(BlockPos pos) {
        BlockState blockState = this.world.getBlockState(pos);
        if (Blocks.FIRE.canPlaceAt(blockState, this.world, pos)) {
            this.world.setBlockState(pos, Blocks.FIRE.getDefaultState());
        }
        if (BlockRegistry.NECROFIRE.canPlaceAt(blockState, this.world, pos)) {
            this.world.setBlockState(pos, BlockRegistry.NECROFIRE.getDefaultState());
        }
    }

    public boolean canBeSetAflame(LivingEntity entity) {
        return !entity.isInvulnerable() && entity.isPartOfGame() && !entity.isFireImmune();
    }
}