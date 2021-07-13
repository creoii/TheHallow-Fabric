package creoii.hallows.common.entity;

import creoii.hallows.common.entity.ai.FlyMoveControl;
import creoii.hallows.common.entity.ai.FlyRandomGoal;
import creoii.hallows.core.registry.EntityRegistry;
import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MagusEntity extends FlyingEntity {
    public MagusEntity(EntityType<MagusEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlyMoveControl(this);
        this.experiencePoints = 5;
    }

    public MagusEntity(World world) {
        super(EntityRegistry.MAGUS, world);
        this.moveControl = new FlyMoveControl(this);
        this.experiencePoints = 5;
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new FlyRandomGoal(this, 20));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D).add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D);
    }

    @Nullable
    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(ItemRegistry.MAGUS_SPAWN_EGG);
    }
}