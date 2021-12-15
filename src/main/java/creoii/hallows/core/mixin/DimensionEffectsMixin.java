package creoii.hallows.core.mixin;

import it.unimi.dsi.fastutil.objects.Object2ObjectMap;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DimensionEffects.class)
public interface DimensionEffectsMixin {
    @Accessor("BY_IDENTIFIER")
    static Object2ObjectMap<Identifier, DimensionEffects> getBY_IDENTIFIER() {
        throw new UnsupportedOperationException();
    }
}
