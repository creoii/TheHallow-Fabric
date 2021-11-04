package creoii.hallows.core.registry;

import creoii.hallows.common.world.structure.village.RuinsVillageData;
import creoii.hallows.core.Hallows;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;

public class StructureRegistry {
    //public static final StructurePieceType PETRIFIED_PYRAMID_PIECE = PetrifiedPyramidGenerator.Piece::new;
    //public static final StructurePieceType HALLOWED_RUINS_PIECE = HallowedRuinsGenerator.Piece::new;

    //public static final StructureFeature<DefaultFeatureConfig> PETRIFIED_PYRAMID_STRUCTURE = new PetrifiedPyramidStructure(DefaultFeatureConfig.CODEC);
    //public static final StructureFeature<DefaultFeatureConfig> HALLOWED_RUINS_STRUCTURE = new HallowedRuinsStructure(DefaultFeatureConfig.CODEC);

    //public static final ConfiguredStructureFeature<?, ?> PETRIFIED_PYRAMID = PETRIFIED_PYRAMID_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
    //public static final ConfiguredStructureFeature<?, ?> HALLOWED_RUINS = HALLOWED_RUINS_STRUCTURE.configure(DefaultFeatureConfig.DEFAULT);
    public static final ConfiguredStructureFeature<StructurePoolFeatureConfig, ? extends StructureFeature<StructurePoolFeatureConfig>> VILLAGE_RUINS = StructureFeature.VILLAGE.configure(new StructurePoolFeatureConfig(() -> {
        return RuinsVillageData.STRUCTURE_POOLS;
    }, 5));

    public static void register() {
        RuinsVillageData.init();

        //Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Hallows.MOD_ID, "prmd"), PETRIFIED_PYRAMID_PIECE);
        //Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Hallows.MOD_ID, "hr"), HALLOWED_RUINS_PIECE);

        //FabricStructureBuilder.create(new Identifier(Hallows.MOD_ID, "petrified_pyramid"), PETRIFIED_PYRAMID_STRUCTURE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(32, 8, 71853).adjustsSurface().register();
        //FabricStructureBuilder.create(new Identifier(Hallows.MOD_ID, "hallowed_ruins"), HALLOWED_RUINS_STRUCTURE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(48, 12, 29484).adjustsSurface().register();
        //FabricStructureBuilder.create(new Identifier(Hallows.MOD_ID, "village_ruins"), VILLAGE_RUINS_STRUCTURE).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(48, 12, 81475).adjustsSurface().register();

        //BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(Hallows.MOD_ID, "petrified_pyramid"), PETRIFIED_PYRAMID);
        //BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(Hallows.MOD_ID, "hallowed_ruins"), HALLOWED_RUINS);
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(Hallows.MOD_ID, "village_ruins"), VILLAGE_RUINS);
    }
}