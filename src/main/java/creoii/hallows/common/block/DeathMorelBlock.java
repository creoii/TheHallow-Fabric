package creoii.hallows.common.block;

import creoii.hallows.common.block.base.MorelBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potions;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DeathMorelBlock extends MorelBlock {
    public DeathMorelBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    public void disturb(World world, BlockState state, BlockPos pos) {
        AreaEffectCloudEntity aoe = new AreaEffectCloudEntity(world, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D);
        aoe.setRadius(0.75F);
        aoe.setRadiusOnUse(-0.25F);
        aoe.setWaitTime(4);
        aoe.setRadiusGrowth(-aoe.getRadius() / (float)aoe.getDuration());
        aoe.setPotion(Potions.HARMING);
        aoe.addEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 20, state.get(SIZE) == Size.SMALL ? 0 : state.get(SIZE) == Size.NORMAL ? 1 : 2));
        aoe.setColor(5913855);
        world.spawnEntity(aoe);
    }
}