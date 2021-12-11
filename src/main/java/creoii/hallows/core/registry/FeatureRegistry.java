package creoii.hallows.core.registry;

import creoii.hallows.common.world.decorator.BranchTreeDecorator;
import creoii.hallows.common.world.decorator.HangingLeavesTreeDecorator;
import creoii.hallows.common.world.decorator.JackOLanternTreeDecorator;
import creoii.hallows.common.world.feature.*;
import creoii.hallows.common.world.feature.config.BlockSpikeFeatureConfig;
import creoii.hallows.common.world.feature.config.HugePumpkinFeatureConfig;
import creoii.hallows.common.world.feature.config.RockFeatureConfig;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.mixin.TreeDecoratorTypeMixin;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.Arrays;

public class FeatureRegistry {
    public static final Feature<HugePumpkinFeatureConfig> HUGE_PUMPKIN = new HugePumpkinFeature(HugePumpkinFeatureConfig.CODEC);
    public static final Feature<RockFeatureConfig> ROCK = new RockFeature(RockFeatureConfig.CODEC);
    public static final Feature<BlockSpikeFeatureConfig> BLOCK_SPIKE = new BlockSpikeFeature(BlockSpikeFeatureConfig.CODEC);
    public static final Feature<SingleStateFeatureConfig> BLOCK_PILLAR = new BlockPillarFeature(SingleStateFeatureConfig.CODEC);

    public static TreeDecoratorType<?> BRANCH_DECORATOR;
    public static TreeDecoratorType<?> HANGING_LEAVES_DECORATOR;
    public static TreeDecoratorType<?> JACK_O_LANTERN_DECORATOR;

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "block_spike"), BLOCK_SPIKE);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "block_pillar"), BLOCK_PILLAR);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "huge_pumpkin"), HUGE_PUMPKIN);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "rock"), ROCK);

        BRANCH_DECORATOR = TreeDecoratorTypeMixin.callRegister("branch", BranchTreeDecorator.CODEC);
        HANGING_LEAVES_DECORATOR = TreeDecoratorTypeMixin.callRegister("hanging_leaves", HangingLeavesTreeDecorator.CODEC);
        JACK_O_LANTERN_DECORATOR = TreeDecoratorTypeMixin.callRegister("jack_o_lantern", JackOLanternTreeDecorator.CODEC);
    }
}