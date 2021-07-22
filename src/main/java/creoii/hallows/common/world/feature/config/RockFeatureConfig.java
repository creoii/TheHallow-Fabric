package creoii.hallows.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;

public class RockFeatureConfig implements FeatureConfig {
    public static final Codec<RockFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), IntProvider.createValidatingCodec(1, 10).fieldOf("height").forGetter((config) -> {
            return config.size;
        })).apply(instance, RockFeatureConfig::new);
    });
    public final BlockState state;
    public final IntProvider size;

    public RockFeatureConfig(BlockState state, IntProvider size) {
        this.state = state;
        this.size = size;
    }
}