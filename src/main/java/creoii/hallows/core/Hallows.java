package creoii.hallows.core;

import creoii.hallows.core.registry.*;
import creoii.hallows.core.util.Tags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class Hallows implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "hallows";

	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		SurfaceRegistry.register();
		CarverRegistry.register();
		FeatureRegistry.register();
		StructureRegistry.register();
		BiomeRegistry.register();
		DimensionRegistry.register();
	}

	@Override
	public void onInitializeClient() {
		BlockRegistry.registerClient();
	}
}
