package creoii.hallows.common.block.base;

import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.effect.StatusEffect;

public class LilyBlock extends FlowerBlock {
    public LilyBlock(StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }
}
