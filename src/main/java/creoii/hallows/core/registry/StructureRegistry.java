package creoii.hallows.core.registry;

import creoii.hallows.common.world.structure.HallowedRuinsGenerator;
import creoii.hallows.common.world.structure.HallowedRuinsStructure;
import creoii.hallows.common.world.structure.PetrifiedPyramidGenerator;
import creoii.hallows.common.world.structure.PetrifiedPyramidStructure;
import creoii.hallows.core.Hallows;
import net.fabricmc.fabric.api.structure.v1.FabricStructureBuilder;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.ConfiguredStructureFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

public class StructureRegistry {
    public static final StructurePieceType PETRIFIED_PYRAMID_PIECE = PetrifiedPyramidGenerator.Piece::new;
    public static final StructurePieceType HALLOWED_RUINS_PIECE = HallowedRuinsGenerator.Piece::new;

    public static final StructureFeature<DefaultFeatureConfig> PETRIFIED_PYRAMID_STRUCT = new PetrifiedPyramidStructure(DefaultFeatureConfig.CODEC);
    public static final StructureFeature<DefaultFeatureConfig> HALLOWED_RUINS_STRUCT = new HallowedRuinsStructure(DefaultFeatureConfig.CODEC);

    public static final ConfiguredStructureFeature<?, ?> PETRIFIED_PYRAMID = PETRIFIED_PYRAMID_STRUCT.configure(DefaultFeatureConfig.DEFAULT);
    public static final ConfiguredStructureFeature<?, ?> HALLOWED_RUINS = HALLOWED_RUINS_STRUCT.configure(DefaultFeatureConfig.DEFAULT);

    public static void register() {
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Hallows.MOD_ID, "prmd"), PETRIFIED_PYRAMID_PIECE);
        Registry.register(Registry.STRUCTURE_PIECE, new Identifier(Hallows.MOD_ID, "hr"), HALLOWED_RUINS_PIECE);

        FabricStructureBuilder.create(new Identifier(Hallows.MOD_ID, "petrified_pyramid"), PETRIFIED_PYRAMID_STRUCT).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(32, 8, 71853).adjustsSurface().register();
        FabricStructureBuilder.create(new Identifier(Hallows.MOD_ID, "hallowed_ruins"), HALLOWED_RUINS_STRUCT).step(GenerationStep.Feature.SURFACE_STRUCTURES).defaultConfig(48, 12, 81475).adjustsSurface().register();

        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(Hallows.MOD_ID, "petrified_pyramid"), PETRIFIED_PYRAMID);
        BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_STRUCTURE_FEATURE, new Identifier(Hallows.MOD_ID, "hallowed_ruins"), HALLOWED_RUINS);
    }
}