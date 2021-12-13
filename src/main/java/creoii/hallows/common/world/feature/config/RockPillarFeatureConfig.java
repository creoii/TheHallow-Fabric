package creoii.hallows.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public record RockPillarFeatureConfig(BlockStateProvider state,
                                      IntProvider size, IntProvider rockCount) implements FeatureConfig {
    public static final Codec<RockPillarFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockStateProvider.TYPE_CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), IntProvider.createValidatingCodec(1, 10).fieldOf("size").forGetter((config) -> {
            return config.size;
        }), IntProvider.createValidatingCodec(1, 10).fieldOf("rock_count").forGetter((config) -> {
            return config.rockCount;
        })).apply(instance, RockPillarFeatureConfig::new);
    });

}