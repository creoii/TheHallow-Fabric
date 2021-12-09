package creoii.hallows.core.registry;

import creoii.hallows.core.Hallows;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class DimensionRegistry {
    private static final RegistryKey<DimensionOptions> DIMENSION_KEY = RegistryKey.of(Registry.DIMENSION_KEY, new Identifier(Hallows.MOD_ID, "the_hallow"));
    private static RegistryKey<World> WORLD_KEY;
    private static RegistryKey<DimensionType> TYPE_KEY;

    public static void register() {
        WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, DIMENSION_KEY.getValue());
        TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, DIMENSION_KEY.getValue());
    }
}