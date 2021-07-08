package creoii.hallows.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.FeatureConfig;

public class HugePumpkinFeatureConfig implements FeatureConfig {
    public static final Codec<HugePumpkinFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.intRange(0, 8).fieldOf("radius").forGetter((config) -> {
            return config.radius;
        })).apply(instance, HugePumpkinFeatureConfig::new);
    });
    public final int radius;

    public HugePumpkinFeatureConfig(int radius) {
        this.radius = radius;
    }
}