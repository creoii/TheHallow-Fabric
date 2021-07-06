package creoii.hallows.core.registry;

import creoii.hallows.common.world.carver.NecromantleCrackCarver;
import creoii.hallows.core.Hallows;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.floatprovider.ConstantFloatProvider;
import net.minecraft.util.math.floatprovider.UniformFloatProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverDebugConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.RavineCarverConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class CarverRegistry {
    public static final Carver<RavineCarverConfig> NECROMANTLE_CRACK = new NecromantleCrackCarver(RavineCarverConfig.RAVINE_CODEC);
    public static final ConfiguredCarver<RavineCarverConfig> NECROMANTLE_CRACK_CONFIGURED = NECROMANTLE_CRACK.configure(new RavineCarverConfig(0.02F, UniformHeightProvider.create(YOffset.fixed(25), YOffset.fixed(80)), ConstantFloatProvider.create(3.0F), YOffset.aboveBottom(10), false, CarverDebugConfig.create(false, Blocks.WARPED_BUTTON.getDefaultState()), UniformFloatProvider.create(-0.5F, 0.5F), new RavineCarverConfig.Shape(UniformFloatProvider.create(0.75F, 1.0F), UniformFloatProvider.create(0.0F, 1.0F), 5, UniformFloatProvider.create(0.5F, 1.0F), 0.5F, 2.0F)));

    public static void register() {
        Registry.register(Registry.CARVER, new Identifier(Hallows.MOD_ID, "necromantle_crack"), NECROMANTLE_CRACK);
        Registry.register(BuiltinRegistries.CONFIGURED_CARVER, new Identifier(Hallows.MOD_ID, "necromantle_crack"), NECROMANTLE_CRACK_CONFIGURED);
    }
}