package creoii.hallows.common.world;

import creoii.hallows.core.Hallows;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

public class HallowsDimension {
    public static final RegistryKey<World> HALLOWS_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(Hallows.MOD_ID, "the_hallows"));
    public static final RegistryKey<DimensionType> TYPE = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier(Hallows.MOD_ID, "the_hallows"));
}