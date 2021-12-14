package creoii.hallows.core.registry;

import creoii.hallows.common.world.decorator.BranchTreeDecorator;
import creoii.hallows.common.world.decorator.HangingLeavesTreeDecorator;
import creoii.hallows.common.world.decorator.JackOLanternTreeDecorator;
import creoii.hallows.common.world.feature.*;
import creoii.hallows.common.world.feature.config.BlockSpikeFeatureConfig;
import creoii.hallows.common.world.feature.config.HugePumpkinFeatureConfig;
import creoii.hallows.common.world.feature.config.RockFeatureConfig;
import creoii.hallows.common.world.feature.config.RockPillarFeatureConfig;
import creoii.hallows.common.world.trunkplacer.SlantedTrunkPlacer;
import creoii.hallows.common.world.trunkplacer.WickedTrunkPlacer;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.mixin.TreeDecoratorTypeMixin;
import creoii.hallows.core.mixin.TrunkPlacerTypeMixin;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.RandomPatchFeature;
import net.minecraft.world.gen.feature.RandomPatchFeatureConfig;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class FeatureRegistry {
    public static final Feature<HugePumpkinFeatureConfig> HUGE_PUMPKIN = new HugePumpkinFeature(HugePumpkinFeatureConfig.CODEC);
    public static final Feature<RockFeatureConfig> ROCK = new RockFeature(RockFeatureConfig.CODEC);
    public static final Feature<RockPillarFeatureConfig> ROCK_PILLAR = new RockPillarFeature(RockPillarFeatureConfig.CODEC);
    public static final Feature<BlockSpikeFeatureConfig> BLOCK_SPIKE = new BlockSpikeFeature(BlockSpikeFeatureConfig.CODEC);
    public static final Feature<SingleStateFeatureConfig> BLOCK_PILLAR = new BlockPillarFeature(SingleStateFeatureConfig.CODEC);
    public static final Feature<RandomPatchFeatureConfig> CORN_RANDOM_PATCH = new CornRandomPatchFeature(RandomPatchFeatureConfig.CODEC);

    public static TreeDecoratorType<?> BRANCH_DECORATOR;
    public static TreeDecoratorType<?> HANGING_LEAVES_DECORATOR;
    public static TreeDecoratorType<?> JACK_O_LANTERN_DECORATOR;

    public static TrunkPlacerType<WickedTrunkPlacer> WICKED_TRUNK_PLACER;
    public static TrunkPlacerType<SlantedTrunkPlacer> SLANTED_TRUNK_PLACER;

    public static void register() {
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "huge_pumpkin"), HUGE_PUMPKIN);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "rock"), ROCK);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "rock_pillar"), ROCK_PILLAR);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "block_spike"), BLOCK_SPIKE);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "block_pillar"), BLOCK_PILLAR);
        Registry.register(Registry.FEATURE, new Identifier(Hallows.MOD_ID, "corn_random_patch"), CORN_RANDOM_PATCH);

        BRANCH_DECORATOR = TreeDecoratorTypeMixin.callRegister("branch", BranchTreeDecorator.CODEC);
        HANGING_LEAVES_DECORATOR = TreeDecoratorTypeMixin.callRegister("hanging_leaves", HangingLeavesTreeDecorator.CODEC);
        JACK_O_LANTERN_DECORATOR = TreeDecoratorTypeMixin.callRegister("jack_o_lantern", JackOLanternTreeDecorator.CODEC);

        WICKED_TRUNK_PLACER = TrunkPlacerTypeMixin.callRegister("wicked_trunk_placer", WickedTrunkPlacer.CODEC);
        SLANTED_TRUNK_PLACER = TrunkPlacerTypeMixin.callRegister("slanted_trunk_placer", SlantedTrunkPlacer.CODEC);
    }
}