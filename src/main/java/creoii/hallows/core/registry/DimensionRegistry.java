package creoii.hallows.core.registry;

import creoii.hallows.core.Hallows;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;

import java.util.OptionalLong;

public class DimensionRegistry {
    public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(Hallows.MOD_ID, "the_hallows"));
    public static final RegistryKey<DimensionType> TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier(Hallows.MOD_ID, "the_hallows"));
    public static DimensionType TYPE;

    public static void register() {
        TYPE = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, 0, 256, 256, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(), new Identifier(Hallows.MOD_ID, "the_hallows"), 0.0F);
    }
}