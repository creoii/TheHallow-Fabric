package creoii.hallows.common.world.sapling;

import creoii.hallows.core.registry.FeatureRegistry;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class BloodEbonySaplingGenerator extends SaplingGenerator {
    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bees) {
        return (ConfiguredFeature<TreeFeatureConfig, ?>) FeatureRegistry.BLOOD_EBONY;
    }
}