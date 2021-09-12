package creoii.hallows.common.item.base;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.Vanishable;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RelicItem extends ToolItem implements Vanishable {
    public static final String CHARGES_LEFT_KEY = "ChargesLeft";
    private final int maxCharges;
    private int charges;

    public RelicItem(ToolMaterial material, Settings settings, int maxCharges) {
        super(material, settings);
        this.maxCharges = maxCharges;
        this.charges = maxCharges;
    }

    public int getMaxCharges() {
        return maxCharges;
    }

    public int getCharges() {
        return charges;
    }

    public void reduceCharge() {
        if (charges > 0) {
            --charges;
        }
    }

    public void reduceCharge(int amt) {
        if (charges > 0) {
            charges -= amt;
            if (charges < 0) charges = 0;
        }
    }

    public void resetCharge() {
        charges = maxCharges;
    }

    public boolean isOutOfCharges() {
        return charges <= 0;
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        MutableText text = new TranslatableText("relic.charges.left");
        tooltip.add(text.append(": " + getCharges()).formatted(Formatting.GRAY));
    }

    @Override
    public boolean isNbtSynced() {
        return true;
    }
}