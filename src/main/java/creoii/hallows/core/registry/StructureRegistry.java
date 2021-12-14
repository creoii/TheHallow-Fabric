package creoii.hallows.core.registry;

import creoii.hallows.common.world.structure.CursedRuinsStructure;
import creoii.hallows.common.world.structure.village.RuinsVillageData;
import creoii.hallows.core.Hallows;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.chunk.StructureConfig;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class StructureRegistry {
    //public static final StructurePieceType PETRIFIED_PYRAMID_PIECE = PetrifiedPyramidGenerator.Piece::new;
    //public static final StructurePieceType HALLOWED_RUINS_PIECE = HallowedRuinsGenerator.Piece::new;

    //public static final StructureFeature<DefaultFeatureConfig> PETRIFIED_PYRAMID_STRUCTURE = new PetrifiedPyramidStructure(DefaultFeatureConfig.CODEC);
    //public static final StructureFeature<DefaultFeatureConfig> HALLOWED_RUINS_STRUCTURE = new HallowedRuinsStructure(DefaultFeatureConfig.CODEC);
    public static StructureFeature<StructurePoolFeatureConfig> CURSED_RUINS = new CursedRuinsStructure(StructurePoolFeatureConfig.CODEC);

    //public static final ConfiguredStructureFeature<?, ?> PETRIFIED_PYRAMID = PETRIFIED_PYRAMID_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
    //public static final ConfiguredStructureFeature<?, ?> HALLOWED_RUINS = HALLOWED_RUINS_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGE_RUINS = StructureFeature.VILLAGE.configure(new StructurePoolFeatureConfig(() -> {
        return RuinsVillageData.STRUCTURE_POOLS;
    }, 5));

    public static void register() {
        FabricStructureBuilder.create(new Identifier(Hallows.MOD_ID, "cursed_ruins"), CURSED_RUINS).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(new StructureConfig(16, 8, 192875619)).adjustsSurface().register();
    }
}