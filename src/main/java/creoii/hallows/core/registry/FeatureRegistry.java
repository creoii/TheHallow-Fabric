package creoii.hallows.core.registry;

import com.google.common.collect.ImmutableList;
import creoii.hallows.common.world.decorator.BranchTreeDecorator;
import creoii.hallows.common.world.decorator.HangingLeavesTreeDecorator;
import creoii.hallows.common.world.decorator.JackOLanternTreeDecorator;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.mixin.TreeDecoratorTypeMixin;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.decorator.CountExtraDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.WaterDepthThresholdDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.Arrays;

public class FeatureRegistry {
    public static final TreeDecoratorType<?> BRANCH_DECORATOR = TreeDecoratorTypeMixin.callRegister("branch_decorator", BranchTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> HANGING_LEAVES_DECORATOR = TreeDecoratorTypeMixin.callRegister("hanging_leaves_decorator", HangingLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> JACK_O_LANTERN_DECORATOR = TreeDecoratorTypeMixin.callRegister("jack_o_lantern_decorator", JackOLanternTreeDecorator.CODEC);

    public static final ConfiguredFeature<?, ?> TREE_EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), new SimpleBlockStateProvider(BlockRegistry.EBONY_LEAVES.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.EBONY_SAPLING.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 3, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_POST.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(new SimpleBlockStateProvider(BlockRegistry.HALLOWED_DIRT.getDefaultState())).build()).spreadHorizontally().applyChance(4);
    public static final ConfiguredFeature<?, ?> TREE_BLOOD_EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), new SimpleBlockStateProvider(BlockRegistry.BLOOD_EBONY_LEAVES.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.BLOOD_EBONY_SAPLING.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 3, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_POST.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_BLOOD_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(new SimpleBlockStateProvider(BlockRegistry.HALLOWED_DIRT.getDefaultState())).build()).spreadHorizontally().applyChance(2);
    public static final ConfiguredFeature<?, ?> TREES_EBONY = TREE_EBONY.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally()).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));
    public static final ConfiguredFeature<?, ?> TREES_BLOOD_EBONY = TREE_BLOOD_EBONY.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally()).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));

    public static void register() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "tree_ebony"), TREE_EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "tree_blood_ebony"), TREE_BLOOD_EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "trees_ebony"), TREES_EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "trees_blood_ebony"), TREES_BLOOD_EBONY);
    }
}