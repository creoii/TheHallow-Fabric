package creoii.hallows.core.registry;

import creoii.hallows.client.particle.LifestealParticle;
import creoii.hallows.core.Hallows;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ParticleRegistry {
    public static final DefaultParticleType NECROFLAME = FabricParticleTypes.simple(false);
    public static final DefaultParticleType LIFESTEAL = FabricParticleTypes.simple(false);

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Hallows.MOD_ID, "necroflame"), NECROFLAME);
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(Hallows.MOD_ID, "lifesteal"), LIFESTEAL);

        ParticleFactoryRegistry.getInstance().register(NECROFLAME, FlameParticle.Factory::new);
        ParticleFactoryRegistry.getInstance().register(LIFESTEAL, LifestealParticle.Factory::new);
    }
}