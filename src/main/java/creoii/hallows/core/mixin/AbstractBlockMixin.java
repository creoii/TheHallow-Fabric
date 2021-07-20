package creoii.hallows.core.mixin;

import creoii.hallows.core.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(method = "onUse", at = @At("HEAD"), cancellable = true)
    private void convertCandleSkull(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> ci) {
        if (world.isClient) {
            if (state.getBlock() == Blocks.SKELETON_SKULL && player.getActiveItem().isIn(ItemTags.CANDLES) && hit.getSide() == Direction.UP) {
                world.setBlockState(pos, BlockRegistry.CANDLE_SKULL.getDefaultState(), 2);
                ci.setReturnValue(ActionResult.CONSUME);
            }
        }
        ci.setReturnValue(ActionResult.PASS);
    }
}