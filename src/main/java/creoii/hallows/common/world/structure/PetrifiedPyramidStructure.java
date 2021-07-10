package creoii.hallows.common.world.structure;

import com.mojang.serialization.Codec;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

public class PetrifiedPyramidStructure extends StructureFeature<DefaultFeatureConfig> {
    public PetrifiedPyramidStructure(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public StructureFeature.StructureStartFactory<DefaultFeatureConfig> getStructureStartFactory() {
        return Start::new;
    }

    public static class Start extends StructureStart<DefaultFeatureConfig> {

        public Start(StructureFeature<DefaultFeatureConfig> feature, ChunkPos pos, int references, long seed) {
            super(feature, pos, references, seed);
        }

        @Override
        public void init(DynamicRegistryManager registryManager, ChunkGenerator chunkGenerator, StructureManager manager, ChunkPos chunkPos, Biome biome, DefaultFeatureConfig config, HeightLimitView world) {
            BlockPos pos = new BlockPos(chunkPos.getStartX(), chunkGenerator.getHeightInGround(chunkPos.getStartX(), chunkPos.getStartZ(), Heightmap.Type.WORLD_SURFACE_WG, world), chunkPos.getStartZ());
            BlockRotation rotation = BlockRotation.random(this.random);
            PetrifiedPyramidGenerator.addPieces(manager, pos, rotation, this, this.random);
        }
    }
}