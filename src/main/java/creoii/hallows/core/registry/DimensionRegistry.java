package creoii.hallows.core.registry;

import creoii.hallows.core.Hallows;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;

public class DimensionRegistry {
    private static final RegistryKey<DimensionOptions> HALLOW_KEY = RegistryKey.of(Registry.DIMENSION_KEY, new Identifier(Hallows.MOD_ID, "the_hallow"));
    private static RegistryKey<World> HALLOW_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, HALLOW_KEY.getValue());
    private static final RegistryKey<DimensionType> HALLOW_TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier(Hallows.MOD_ID, "the_hallow"));

    public static void register() {
        HALLOW_WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(Hallows.MOD_ID, "the_hallow"));
    }
}