package creoii.hallows.core;

import creoii.hallows.core.registry.*;
import creoii.hallows.core.util.Events;
import creoii.hallows.core.util.Stats;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;

public class Hallows implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "hallows";

	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		BlockEntityRegistry.register();
		ContainerRegistry.register();
		EntityRegistry.register();
		SurfaceRegistry.register();
		CarverRegistry.register();
		FeatureRegistry.register();
		StructureRegistry.register();
		BiomeRegistry.register();
		DimensionRegistry.register();
		StatusEffectRegistry.register();
		Stats.register();
		Events.register();
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void onInitializeClient() {
		BlockRegistry.registerClient();
		BlockEntityRegistry.registerClient();
		EntityRegistry.registerClient();
		ParticleRegistry.registerClient();
	}
}
