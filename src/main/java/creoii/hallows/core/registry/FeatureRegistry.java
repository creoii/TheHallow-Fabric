package creoii.hallows.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import creoii.hallows.common.world.decorator.BranchTreeDecorator;
import creoii.hallows.common.world.decorator.HangingLeavesTreeDecorator;
import creoii.hallows.common.world.decorator.JackOLanternTreeDecorator;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.mixin.TreeDecoratorTypeMixin;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.placer.DoublePlantPlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.Arrays;

public class FeatureRegistry {
    public static final TreeDecoratorType<?> BRANCH_DECORATOR = TreeDecoratorTypeMixin.callRegister("branch_decorator", BranchTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> HANGING_LEAVES_DECORATOR = TreeDecoratorTypeMixin.callRegister("hanging_leaves_decorator", HangingLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> JACK_O_LANTERN_DECORATOR = TreeDecoratorTypeMixin.callRegister("jack_o_lantern_decorator", JackOLanternTreeDecorator.CODEC);

    public static final ConfiguredFeature<?, ?> EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), new SimpleBlockStateProvider(BlockRegistry.EBONY_LEAVES.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.EBONY_SAPLING.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 2, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_POST.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(new SimpleBlockStateProvider(BlockRegistry.HALLOWED_DIRT.getDefaultState())).forceDirt().build()).spreadHorizontally().applyChance(1);
    public static final ConfiguredFeature<?, ?> BLOOD_EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), new SimpleBlockStateProvider(BlockRegistry.BLOOD_EBONY_LEAVES.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.BLOOD_EBONY_SAPLING.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 2, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_POST.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_BLOOD_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(new SimpleBlockStateProvider(BlockRegistry.HALLOWED_DIRT.getDefaultState())).forceDirt().build()).spreadHorizontally().applyChance(4);
    public static final ConfiguredFeature<?, ?> TREES_EBONY = EBONY.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally()).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));
    public static final ConfiguredFeature<?, ?> TREES_BLOOD_EBONY = BLOOD_EBONY.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally()).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));
    public static final ConfiguredFeature<?, ?> PATCH_TALL_GRASS_NO_DIRT = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.TALL_GRASS.getDefaultState()), new DoublePlantPlacer()).blacklist(ImmutableSet.of(Blocks.COARSE_DIRT.getDefaultState())).tries(64).cannotProject().build()).decorate(Decorator.SPREAD_32_ABOVE.configure(NopeDecoratorConfig.INSTANCE)).decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING))).spreadHorizontally().decorate(Decorator.COUNT_NOISE.configure(new CountNoiseDecoratorConfig(-0.8D, 0, 7)));
    public static final ConfiguredFeature<?, ?> ASPHODEL = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_LOG.getDefaultState()), new ForkingTrunkPlacer(5, 2, 2), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_SAPLING.getDefaultState()), new AcaciaFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(4, 3, 3)).build());
    public static final ConfiguredFeature<?, ?> LARGE_ASPHODEL = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_LOG.getDefaultState()), new LargeOakTrunkPlacer(9, 5, 5), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_SAPLING.getDefaultState()), new AcaciaFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(4, 3, 3)).build());
    public static final ConfiguredFeature<?, ?> TREES_ASPHODEL = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(LARGE_ASPHODEL.withChance(0.1F)), ASPHODEL)).decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally().decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.8F, 1))));
    public static final ConfiguredFeature<?, ?> PATCH_NECROFIRE = Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.NECROFIRE.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(BlockRegistry.HALLSTONE, BlockRegistry.HALLOWED_DIRT, BlockRegistry.PETRIFIED_SAND, BlockRegistry.PETRIFIED_SANDSTONE)).cannotProject().build()).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.belowTop(4)))).spreadHorizontally()).repeatRandomly(5);
    public static final ConfiguredFeature<?, ?> PATCH_DEADROOT = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.DEADROOT.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(32).build()).decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).repeat(2);
    public static final ConfiguredFeature<?, ?> TENEBRITE_BLOBS = Feature.NETHERRACK_REPLACE_BLOBS.configure(new ReplaceBlobsFeatureConfig(BlockRegistry.HALLSTONE.getDefaultState(), BlockRegistry.TENEBRITE.getDefaultState(), UniformIntProvider.create(3, 7))).range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.getTop()))).spreadHorizontally().repeat(25);

    public static void register() {
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "ebony"), EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "blood_ebony"), BLOOD_EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "trees_ebony"), TREES_EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "trees_blood_ebony"), TREES_BLOOD_EBONY);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "patch_tall_grass_no_dirt"), PATCH_TALL_GRASS_NO_DIRT);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "asphodel"), ASPHODEL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "large_asphodel"), LARGE_ASPHODEL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "trees_asphodel"), TREES_ASPHODEL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "patch_necrofire"), PATCH_NECROFIRE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "patch_deadroot"), PATCH_DEADROOT);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "tenebrite_blobs"), TENEBRITE_BLOBS);
    }
}