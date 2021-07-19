package creoii.hallows.core.registry;

import creoii.hallows.common.effects.TransfiguredStatusEffect;
import creoii.hallows.core.Hallows;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class StatusEffectRegistry {
    public static final StatusEffect TRANSFIGURED = new TransfiguredStatusEffect();

    public static void register() {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(Hallows.MOD_ID, "transfigured"), TRANSFIGURED);
    }
}