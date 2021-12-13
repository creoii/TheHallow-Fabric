package creoii.hallows.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public record HugePumpkinFeatureConfig(IntProvider xzSize, IntProvider height) implements FeatureConfig {
    public static final Codec<HugePumpkinFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(IntProvider.POSITIVE_CODEC.fieldOf("xz_size").forGetter((config) -> {
            return config.xzSize;
        }), IntProvider.POSITIVE_CODEC.fieldOf("height").forGetter((config) -> {
            return config.height;
        })).apply(instance, HugePumpkinFeatureConfig::new);
    });

}