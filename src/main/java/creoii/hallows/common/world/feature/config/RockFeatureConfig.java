package creoii.hallows.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record RockFeatureConfig(BlockStateProvider state,
                                IntProvider size) implements FeatureConfig {
    public static final Codec<RockFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), IntProvider.createValidatingCodec(1, 10).fieldOf("size").forGetter((config) -> {
            return config.size;
        })).apply(instance, RockFeatureConfig::new);
    });

}