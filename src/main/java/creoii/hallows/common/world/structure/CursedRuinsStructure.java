package creoii.hallows.common.world.structure;

import com.mojang.serialization.Codec;
import creoii.hallows.core.Hallows;
import net.minecraft.block.BlockState;
import net.minecraft.structure.*;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.VerticalBlockSample;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

import java.util.Optional;
import java.util.Random;
import java.util.function.Predicate;

public class CursedRuinsStructure extends StructureFeature<StructurePoolFeatureConfig> {
    private static final Identifier[] ARCHES = new Identifier[]{new Identifier(Hallows.MOD_ID, "cursed_ruins/arches/arch_1"), new Identifier(Hallows.MOD_ID, "cursed_ruins/arches/arch_2"), new Identifier(Hallows.MOD_ID, "cursed_ruins/arches/arch_3"), new Identifier(Hallows.MOD_ID, "cursed_ruins/arches/arch_4")};
    //arches
    //bridges

    public CursedRuinsStructure(Codec<StructurePoolFeatureConfig> codec) {
        super(codec, (context) -> {
                    if (!CursedRuinsStructure.canGenerate(context)) return Optional.empty();
                    else return CursedRuinsStructure.createPiecesGenerator(context);
                }, PostPlacementProcessor.EMPTY);
    }

    private static boolean canGenerate(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        BlockPos spawnXZPosition = context.chunkPos().getCenterAtY(0);
        int landHeight = context.chunkGenerator().getHeightInGround(spawnXZPosition.getX(), spawnXZPosition.getZ(), Heightmap.Type.WORLD_SURFACE_WG, context.world());
        VerticalBlockSample sample = context.chunkGenerator().getColumnSample(spawnXZPosition.getX(), spawnXZPosition.getZ(), context.world());
        return sample.getState(landHeight).getFluidState().isEmpty();
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {
        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        //VerticalBlockSample blockView = context.chunkGenerator().getColumnSample(blockpos.getX(), blockpos.getZ(), context.world());

        StructurePoolFeatureConfig config = new StructurePoolFeatureConfig(() -> context.registryManager().get(Registry.STRUCTURE_POOL_KEY).get(new Identifier(Hallows.MOD_ID, "cursed_ruins/start_pool")), 5);
        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        new StructureGeneratorFactory.Context<>(
                                context.chunkGenerator(),
                                context.biomeSource(),
                                context.seed(),
                                context.chunkPos(),
                                context.config(),
                                context.world(),
                                context.validBiome(),
                                context.structureManager(),
                                context.registryManager()
                        ), PoolStructurePiece::new, blockpos, false, false);

        if(structurePiecesGenerator.isPresent()) {
            System.out.println("Cursed Ruins at " + blockpos);
        }
        return structurePiecesGenerator;
    }
}