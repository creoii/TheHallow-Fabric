package creoii.hallows.common.world;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class HallowsEffects extends DimensionEffects {
    public HallowsEffects() {
        super(0f, true, SkyType.NORMAL, false, true);
    }

    @Override
    public Vec3d adjustFogColor(Vec3d color, float sunHeight) {
        return color;
    }

    @Override
    public boolean useThickFog(int camX, int camY) {
        return true;
    }
}
