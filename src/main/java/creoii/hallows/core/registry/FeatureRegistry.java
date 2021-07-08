package creoii.hallows.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import creoii.hallows.common.block.base.MorelBlock;
import creoii.hallows.common.world.decorator.BranchTreeDecorator;
import creoii.hallows.common.world.decorator.HangingLeavesTreeDecorator;
import creoii.hallows.common.world.decorator.JackOLanternTreeDecorator;
import creoii.hallows.common.world.feature.HugePumpkinFeature;
import creoii.hallows.common.world.feature.config.HugePumpkinFeatureConfig;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.mixin.TreeDecoratorTypeMixin;
import creoii.hallows.core.util.Tags;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.VerticalSurfaceType;
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
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;

import java.util.Arrays;

public class FeatureRegistry {
    public static final Feature<HugePumpkinFeatureConfig> HUGE_PUMPKIN = new HugePumpkinFeature(HugePumpkinFeatureConfig.CODEC);

    public static final TreeDecoratorType<?> BRANCH_DECORATOR = TreeDecoratorTypeMixin.callRegister("branch_decorator", BranchTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> HANGING_LEAVES_DECORATOR = TreeDecoratorTypeMixin.callRegister("hanging_leaves_decorator", HangingLeavesTreeDecorator.CODEC);
    public static final TreeDecoratorType<?> JACK_O_LANTERN_DECORATOR = TreeDecoratorTypeMixin.callRegister("jack_o_lantern_decorator", JackOLanternTreeDecorator.CODEC);

    public static final ConfiguredFeature<?, ?> EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), new SimpleBlockStateProvider(BlockRegistry.EBONY_LEAVES.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.EBONY_SAPLING.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 2, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_BRANCH.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(new SimpleBlockStateProvider(BlockRegistry.HALLOWED_DIRT.getDefaultState())).forceDirt().build()).spreadHorizontally().applyChance(1);
    public static final ConfiguredFeature<?, ?> BLOOD_EBONY = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.EBONY_LOG.getDefaultState()), new LargeOakTrunkPlacer(11, 11, 5), new SimpleBlockStateProvider(BlockRegistry.BLOOD_EBONY_LEAVES.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.BLOOD_EBONY_SAPLING.getDefaultState()), new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(2), 1), new TwoLayersFeatureSize(4, 2, 3)).decorators(Arrays.asList(new BranchTreeDecorator(BlockRegistry.EBONY_BRANCH.getDefaultState(), 0.8F, 2, 0.4F), new HangingLeavesTreeDecorator(BlockRegistry.HANGING_BLOOD_EBONY_LEAVES.getDefaultState(), 2, 5, 0.5F), new JackOLanternTreeDecorator(0.1F))).dirtProvider(new SimpleBlockStateProvider(BlockRegistry.HALLOWED_DIRT.getDefaultState())).forceDirt().build()).spreadHorizontally().applyChance(4);
    public static final ConfiguredFeature<?, ?> TREES_EBONY = EBONY.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally()).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));
    public static final ConfiguredFeature<?, ?> TREES_BLOOD_EBONY = BLOOD_EBONY.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally()).decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(10, 0.1F, 1)));
    public static final ConfiguredFeature<?, ?> PATCH_TALL_GRASS_NO_DIRT = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.TALL_GRASS.getDefaultState()), new DoublePlantPlacer()).blacklist(ImmutableSet.of(Blocks.COARSE_DIRT.getDefaultState())).tries(64).cannotProject().build()).decorate(Decorator.SPREAD_32_ABOVE.configure(NopeDecoratorConfig.INSTANCE)).decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING))).spreadHorizontally().decorate(Decorator.COUNT_NOISE.configure(new CountNoiseDecoratorConfig(-0.8D, 0, 7)));
    public static final ConfiguredFeature<?, ?> ASPHODEL = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_LOG.getDefaultState()), new ForkingTrunkPlacer(5, 2, 2), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_SAPLING.getDefaultState()), new AcaciaFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(4, 3, 3)).build());
    public static final ConfiguredFeature<?, ?> LARGE_ASPHODEL = Feature.TREE.configure(new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_LOG.getDefaultState()), new LargeOakTrunkPlacer(9, 5, 5), new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()), new SimpleBlockStateProvider(BlockRegistry.ASPHODEL_SAPLING.getDefaultState()), new AcaciaFoliagePlacer(ConstantIntProvider.create(0), ConstantIntProvider.create(0)), new TwoLayersFeatureSize(4, 3, 3)).build());
    public static final ConfiguredFeature<?, ?> TREES_ASPHODEL = Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(ImmutableList.of(LARGE_ASPHODEL.withChance(0.1F)), ASPHODEL)).decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally().decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.8F, 1))));
    public static final ConfiguredFeature<?, ?> PATCH_NECROFIRE = Feature.RANDOM_PATCH.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.NECROFIRE.getDefaultState()), SimpleBlockPlacer.INSTANCE)).tries(64).whitelist(ImmutableSet.of(BlockRegistry.HALLSTONE, BlockRegistry.HALLOWED_DIRT, BlockRegistry.PETRIFIED_SAND, BlockRegistry.PETRIFIED_SANDSTONE)).cannotProject().build()).decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.aboveBottom(4), YOffset.belowTop(4)))).spreadHorizontally()).repeatRandomly(5);
    public static final ConfiguredFeature<?, ?> PATCH_DEADROOT = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(BlockRegistry.DEADROOT.getDefaultState()), SimpleBlockPlacer.INSTANCE).tries(32).build()).decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).repeat(2);
    public static final ConfiguredFeature<?, ?> TENEBRITE_BLOBS = Feature.NETHERRACK_REPLACE_BLOBS.configure(new ReplaceBlobsFeatureConfig(BlockRegistry.HALLSTONE.getDefaultState(), BlockRegistry.TENEBRITE.getDefaultState(), UniformIntProvider.create(3, 7))).range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(63)))).spreadHorizontally().repeat(25);
    public static final ConfiguredFeature<?, ?> PATCH_PUMPKIN_DENSE = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.PUMPKIN.getDefaultState()), new SimpleBlockPlacer()).whitelist(ImmutableSet.of(Blocks.GRASS_BLOCK, Blocks.COARSE_DIRT, BlockRegistry.HALLOWED_DIRT)).tries(64).cannotProject().build()).decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).spreadHorizontally().repeatRandomly(5);
    public static final ConfiguredFeature<?, ?> RED_MOSS_VEGETATION = Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(new WeightedBlockStateProvider(new DataPool.Builder<BlockState>().add(BlockRegistry.RED_MOSS_CARPET.getDefaultState(), 32).add(BlockRegistry.BLOOD_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.BIG), 1).add(BlockRegistry.BLOOD_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.NORMAL), 1).add(BlockRegistry.BLOOD_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.SMALL), 1).add(BlockRegistry.BLOOD_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.BIG), 1).add(BlockRegistry.BLOOD_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.NORMAL), 1).add(BlockRegistry.BLOOD_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.SMALL), 1).add(BlockRegistry.DEATH_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.BIG), 1).add(BlockRegistry.DEATH_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.NORMAL), 2).add(BlockRegistry.DEATH_MOREL.getDefaultState().with(MorelBlock.SIZE, MorelBlock.Size.SMALL), 1))));
    public static final ConfiguredFeature<?, ?> RED_MOSS_PATCH_BONEMEAL = Feature.VEGETATION_PATCH.configure(new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(), new SimpleBlockStateProvider(BlockRegistry.RED_MOSS_BLOCK.getDefaultState()), () -> RED_MOSS_VEGETATION, VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0.0F, 5, 0.4F, UniformIntProvider.create(1, 2), 0.75F));
    public static final ConfiguredFeature<?, ?> PATCH_CANDLES = Feature.RANDOM_PATCH.configure(new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.CANDLE.getDefaultState()), new SimpleBlockPlacer()).whitelist(ImmutableSet.of(BlockRegistry.HALLSTONE, BlockRegistry.TENEBRITE)).tries(32).cannotProject().build()).decorate(Decorator.HEIGHTMAP_SPREAD_DOUBLE.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).spreadHorizontally().repeatRandomly(5).applyChance(4);
    public static final ConfiguredFeature<?, ?> RED_MOSS_PATCH = Feature.VEGETATION_PATCH.configure(new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(), new SimpleBlockStateProvider(BlockRegistry.RED_MOSS_BLOCK.getDefaultState()), () -> RED_MOSS_VEGETATION, VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0.0F, 5, 0.8F, UniformIntProvider.create(4, 7), 0.3F));
    public static final ConfiguredFeature<?, ?> BLOOD_CAVES_VEGETATION = RED_MOSS_PATCH.decorate(Decorator.CAVE_SURFACE.configure(new CaveSurfaceDecoratorConfig(VerticalSurfaceType.FLOOR, 12))).range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(60)))).spreadHorizontally().repeat(40);
    public static final ConfiguredFeature<?, ?> RED_MOSS_PATCH_CEILING = Feature.VEGETATION_PATCH.configure(new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(), new SimpleBlockStateProvider(BlockRegistry.RED_MOSS_BLOCK.getDefaultState()), () -> ConfiguredFeatures.CAVE_VINE_IN_MOSS, VerticalSurfaceType.CEILING, UniformIntProvider.create(1, 2), 0.0F, 5, 0.0F, UniformIntProvider.create(4, 7), 0.3F));
    public static final ConfiguredFeature<?, ?> BLOOD_CAVES_CEILING_VEGETATION = RED_MOSS_PATCH_CEILING.decorate(Decorator.CAVE_SURFACE.configure(new CaveSurfaceDecoratorConfig(VerticalSurfaceType.CEILING, 12))).range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(60)))).spreadHorizontally().repeat(40);
    public static final ConfiguredFeature<?, ?> ORE_SILVER = Feature.SCATTERED_ORE.configure(new OreFeatureConfig(ImmutableList.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), BlockRegistry.HALLSTONE_EMERALD_ORE.getDefaultState())), 10)).triangleRange(YOffset.fixed(0), YOffset.fixed(128)).spreadHorizontally();
    public static final ConfiguredFeature<?, ?> ORE_OPAL = Feature.ORE.configure(new OreFeatureConfig(ImmutableList.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), BlockRegistry.HALLSTONE_EMERALD_ORE.getDefaultState())), 8)).uniformRange(YOffset.getBottom(), YOffset.fixed(96)).spreadHorizontally();
    public static final ConfiguredFeature<?, ?> ORE_HALLSTONE_EMERALD = Feature.ORE.configure(new OreFeatureConfig(ImmutableList.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), BlockRegistry.HALLSTONE_EMERALD_ORE.getDefaultState())), 4)).uniformRange(YOffset.getBottom(), YOffset.fixed(96)).spreadHorizontally();
    public static final ConfiguredFeature<?, ?> ORE_STYGIAN_RUIN = Feature.SCATTERED_ORE.configure(new OreFeatureConfig(ImmutableList.of(OreFeatureConfig.createTarget(new TagMatchRuleTest(BlockTags.BASE_STONE_OVERWORLD), BlockRegistry.HALLSTONE_EMERALD_ORE.getDefaultState())), 4, 0.5F)).uniformRange(YOffset.getBottom(), YOffset.fixed(24)).spreadHorizontally();
    public static final ConfiguredFeature<?, ?> LARGE_PUMPKIN = HUGE_PUMPKIN.configure(new HugePumpkinFeatureConfig(3)).decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally().decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.33F, 1))));
    public static final ConfiguredFeature<?, ?> SMALL_PUMPKIN = HUGE_PUMPKIN.configure(new HugePumpkinFeatureConfig(1)).decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR)).decorate(Decorator.WATER_DEPTH_THRESHOLD.configure(new WaterDepthThresholdDecoratorConfig(0))).spreadHorizontally().decorate(Decorator.COUNT_EXTRA.configure(new CountExtraDecoratorConfig(0, 0.33F, 1))));
    public static final ConfiguredFeature<?, ?> PETRIFIED_SANDSTONE_ROCK = Feature.FOREST_ROCK.configure(new SingleStateFeatureConfig(BlockRegistry.PETRIFIED_SANDSTONE.getDefaultState())).decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.MOTION_BLOCKING)).spreadHorizontally()).repeatRandomly(2);

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "huge_pumpkin"), HUGE_PUMPKIN);

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
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "patch_pumpkin_dense"), PATCH_PUMPKIN_DENSE);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "red_moss_vegetation"), RED_MOSS_VEGETATION);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "red_moss_patch_bonemeal"), RED_MOSS_PATCH_BONEMEAL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "patch_candles"), PATCH_CANDLES);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "red_moss_patch"), RED_MOSS_PATCH);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "blood_caves_vegetation"), BLOOD_CAVES_VEGETATION);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "red_moss_patch_ceiling"), RED_MOSS_PATCH_CEILING);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "blood_caves_ceiling_vegetation"), BLOOD_CAVES_CEILING_VEGETATION);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "ore_silver"), ORE_SILVER);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "ore_opal"), ORE_OPAL);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "ore_hallstone_emerald"), ORE_HALLSTONE_EMERALD);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "ore_stygian_ruin"), ORE_STYGIAN_RUIN);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "large_pumpkin"), LARGE_PUMPKIN);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "small_pumpkin"), SMALL_PUMPKIN);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier(Hallows.MOD_ID, "petrified_sandstone_rock"), PETRIFIED_SANDSTONE_ROCK);
    }
}