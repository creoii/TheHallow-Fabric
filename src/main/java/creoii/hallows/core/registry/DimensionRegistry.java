package creoii.hallows.core.registry;

import creoii.hallows.core.Hallows;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.source.HorizontalVoronoiBiomeAccessType;
import net.minecraft.world.dimension.DimensionType;

import java.util.OptionalLong;

public class DimensionRegistry {
    public static DimensionType TYPE;
    public static ServerWorld WORLD;
    public static final RegistryKey<DimensionType> TYPE_KEY = RegistryKey.of(Registry.DIMENSION_TYPE_KEY, new Identifier(Hallows.MOD_ID, "the_hallows"));
    public static final RegistryKey<World> WORLD_KEY = RegistryKey.of(Registry.WORLD_KEY, new Identifier(Hallows.MOD_ID, "the_hallows"));

    public static void register() {
        TYPE = DimensionType.create(OptionalLong.empty(), true, false, false, true, 1.0D, false, false, true, false, true, 0, 256, 256, HorizontalVoronoiBiomeAccessType.INSTANCE, BlockTags.INFINIBURN_OVERWORLD.getId(), new Identifier(Hallows.MOD_ID, "the_hallows"), 0.0F);
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            TYPE = server.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).get(TYPE_KEY);
            WORLD = server.getWorld(WORLD_KEY);
        });
    }
}