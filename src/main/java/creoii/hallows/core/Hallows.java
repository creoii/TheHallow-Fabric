package creoii.hallows.core;

import net.fabricmc.api.ModInitializer;

public class Hallows implements ModInitializer {
	public static final String MOD_ID = "hallows";

	@Override
	public void onInitialize() {
		BlockRegistry.register();
	}
}
