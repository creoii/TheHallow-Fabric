package creoii.hallows.core;

import creoii.hallows.core.registry.*;
import creoii.hallows.core.util.Stats;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

public class Hallows implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "hallows";

	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		EntityRegistry.register();
		SurfaceRegistry.register();
		CarverRegistry.register();
		FeatureRegistry.register();
		StructureRegistry.register();
		BiomeRegistry.register();
		DimensionRegistry.register();
		StatusEffectRegistry.register();
		RecipeRegistry.register();
		Stats.register();
	}

	@Override
	public void onInitializeClient() {
		BlockRegistry.registerClient();
		EntityRegistry.registerClient();
	}
}
