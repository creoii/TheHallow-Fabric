package creoii.hallows.common.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LogBlock extends PillarBlock {
    private final Block stripped;

    public LogBlock(Block stripped, Settings settings) {
        super(settings);
        this.stripped = stripped;
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack held = player.getEquippedStack(hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);

        if (held.isEmpty() || stripped == null) return ActionResult.FAIL;
        else {
            world.playSound(player, pos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if(!world.isClient) {
                BlockState target = stripped.getDefaultState().with(PillarBlock.AXIS, state.get(PillarBlock.AXIS));
                world.setBlockState(pos, target);
                held.damage(1, player, consumedPlayer -> consumedPlayer.sendToolBreakStatus(hand));
            }
            return ActionResult.SUCCESS;
        }
    }
}
