package creoii.hallows.core;

import creoii.hallows.common.world.HallowsEffects;
import creoii.hallows.core.mixin.DimensionEffectsMixin;
import creoii.hallows.core.registry.*;
import creoii.hallows.core.util.Events;
import creoii.hallows.core.util.Stats;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.render.DimensionEffects;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.dimension.DimensionOptions;

public class Hallows implements ModInitializer, ClientModInitializer {
	public static final String MOD_ID = "hallows";
	public static final RegistryKey<DimensionOptions> HALLOW = RegistryKey.of(Registry.DIMENSION_KEY, new Identifier("overworld"));

	@Override
	public void onInitialize() {
		BlockRegistry.register();
		ItemRegistry.register();
		BlockEntityRegistry.register();
		ContainerRegistry.register();
		EntityRegistry.register();
		CarverRegistry.register();
		FeatureRegistry.register();
		StructureRegistry.register();
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
		DimensionEffectsMixin.getBY_IDENTIFIER().put(new Identifier(MOD_ID, "the_hallow"), new HallowsEffects());
	}
}
