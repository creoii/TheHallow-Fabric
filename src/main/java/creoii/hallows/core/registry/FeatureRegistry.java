package creoii.hallows.core.registry;

import com.google.common.collect.ImmutableList;
import creoii.hallows.common.world.decorator.BranchTreeDecorator;
import creoii.hallows.common.world.decorator.HangingLeavesTreeDecorator;
import creoii.hallows.common.world.decorator.JackOLanternTreeDecorator;
import creoii.hallows.common.world.feature.HugePumpkinFeature;
import creoii.hallows.common.world.feature.MultiLayerTreeFeature;
import creoii.hallows.common.world.feature.RockFeature;
import creoii.hallows.common.world.feature.config.HugePumpkinFeatureConfig;
import creoii.hallows.common.world.feature.config.RockFeatureConfig;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.mixin.TreeDecoratorTypeMixin;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
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
    public static final Feature<TreeFeatureConfig> MUTLILAYER_TREE = new MultiLayerTreeFeature(TreeFeatureConfig.CODEC);

    public static final ConfiguredFeature<TreeFeatureConfig, ?> EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), SimpleBlockStateProvider.of(BlockRegistry.EBONY_LEAVES.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 2, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_BRANCH.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(SimpleBlockStateProvider.of(BlockRegistry.HALLOWED_DIRT.getDefaultState())).forceDirt().build());
    public static final ConfiguredFeature<TreeFeatureConfig, ?> BLOOD_EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), SimpleBlockStateProvider.of(BlockRegistry.BLOOD_EBONY_LEAVES.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 2, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_BRANCH.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_BLOOD_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(SimpleBlockStateProvider.of(BlockRegistry.HALLOWED_DIRT.getDefaultState())).forceDirt().build());
    public static final ConfiguredFeature<TreeFeatureConfig, ?> ASPHODEL = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(BlockRegistry.ASPHODEL_LOG.getDefaultState()), new ForkingTrunkPlacer(5, 2, 2), SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()), new AcaciaFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(4, 3, 3)).build());
    public static final ConfiguredFeature<TreeFeatureConfig, ?> LARGE_ASPHODEL = Feature.TREE.configure(new TreeFeatureConfig.Builder(SimpleBlockStateProvider.of(BlockRegistry.ASPHODEL_LOG.getDefaultState()), new LargeOakTrunkPlacer(9, 5, 5), SimpleBlockStateProvider.of(Blocks.AIR.getDefaultState()), new AcaciaFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(4, 3, 3)).build());

    public static final TreeDecoratorType<?> BRANCH_DECORATOR = TreeDecoratorTypeMixin.callRegister("branch_decorator", BranchTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> HANGING_LEAVES_DECORATOR = TreeDecoratorTypeMixin.callRegister("hanging_leaves_decorator", HangingLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> JACK_O_LANTERN_DECORATOR = TreeDecoratorTypeMixin.callRegister("jack_o_lantern_decorator", JackOLanternTreeDecorator.CODEC);

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "huge_pumpkin"), HUGE_PUMPKIN);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "rock"), ROCK);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "multilayer_tree"), MUTLILAYER_TREE);

        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "ebony"), EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "blood_ebony"), BLOOD_EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "asphodel"), ASPHODEL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "large_asphodel"), LARGE_ASPHODEL);
    }
}