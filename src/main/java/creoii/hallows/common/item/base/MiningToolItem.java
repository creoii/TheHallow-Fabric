package creoii.hallows.common.item.base;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.tag.Tag;

public class MiningToolItem extends net.minecraft.item.MiningToolItem {
    public MiningToolItem(float attackDamage, float attackSpeed, ToolMaterial material, Tag<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }
}