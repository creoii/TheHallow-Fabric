package creoii.hallows.core.registry;

import creoii.hallows.core.Hallows;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarvers;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

public class BiomeRegistry {
    public static Biome HANGING_WOODS;
    public static Biome HEMLOCK_SWAMP;
    public static Biome NECROTIC_GLACIERS;
    public static Biome PERISHED_VALLEY;
    public static Biome PUMPKIN_VALLEY;
    public static Biome PETRIFIED_SANDS;
    public static Biome ROCKY_PETRIFIED_SANDS;
    public static Biome PETRIFIED_BONEYARD;

    public static final RegistryKey<Biome> HANGING_WOODS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "hanging_woods"));
    public static final RegistryKey<Biome> HEMLOCK_SWAMP_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "hemlock_swamp"));
    public static final RegistryKey<Biome> NECROTIC_GLACIERS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "necrotic_glaciers"));
    public static final RegistryKey<Biome> PERISHED_VALLEY_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "perished_valley"));
    public static final RegistryKey<Biome> PUMPKIN_VALLEY_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "pumpkin_valley"));
    public static final RegistryKey<Biome> PETRIFIED_SANDS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "petrified_sands"));
    public static final RegistryKey<Biome> ROCKY_PETRIFIED_SANDS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "rocky_petrified_sands"));
    public static final RegistryKey<Biome> PETRIFIED_BONEYARD_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(Hallows.MOD_ID, "petrified_boneyard"));

    public static void register() {
        HANGING_WOODS = createHangingWoods();
        HEMLOCK_SWAMP = createHemlockSwamp();
        NECROTIC_GLACIERS = createNecroticGlaciers();
        PERISHED_VALLEY = createPerishedValley(false);
        PUMPKIN_VALLEY = createPerishedValley(true);
        PETRIFIED_SANDS = createPetrifiedSands(false, false);
        ROCKY_PETRIFIED_SANDS = createPetrifiedSands(false, true);
        PETRIFIED_BONEYARD = createPetrifiedSands(true, false);
        Registry.register(BuiltinRegistries.BIOME, HANGING_WOODS_KEY.getValue(), HANGING_WOODS);
        Registry.register(BuiltinRegistries.BIOME, HEMLOCK_SWAMP_KEY.getValue(), HEMLOCK_SWAMP);
        Registry.register(BuiltinRegistries.BIOME, NECROTIC_GLACIERS_KEY.getValue(), NECROTIC_GLACIERS);
        Registry.register(BuiltinRegistries.BIOME, PERISHED_VALLEY_KEY.getValue(), PERISHED_VALLEY);
        Registry.register(BuiltinRegistries.BIOME, PUMPKIN_VALLEY_KEY.getValue(), PUMPKIN_VALLEY);
        Registry.register(BuiltinRegistries.BIOME, PETRIFIED_SANDS_KEY.getValue(), PETRIFIED_SANDS);
        Registry.register(BuiltinRegistries.BIOME, ROCKY_PETRIFIED_SANDS_KEY.getValue(), ROCKY_PETRIFIED_SANDS);
        Registry.register(BuiltinRegistries.BIOME, PETRIFIED_BONEYARD_KEY.getValue(), PETRIFIED_BONEYARD);
    }

    private static Biome createHangingWoods() {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        GenerationSettings.Builder generation = new GenerationSettings.Builder();
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_OPAL);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_SILVER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_STYGIAN_RUIN);
        generation.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, PlacedFeatureRegistry.TENEBRITE_BLOBS);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TREES_EBONY);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TREES_BLOOD_EBONY);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_NECROFIRE);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_DEADROOT);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_PUMPKIN);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_CANDLES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_CEILING_VEGETATION);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_VEGETATION);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).temperature(0.5F).downfall(0.5F).effects(new BiomeEffects.Builder().waterColor(5001581).waterFogColor(8620438).fogColor(0xc0d8ff).grassColor(9470298).foliageColor(10387802).skyColor(4210816).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.01F)).build()).spawnSettings(spawns.build()).generationSettings(generation.build()).build();
    }

    private static Biome createHemlockSwamp() {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        GenerationSettings.Builder generation = new GenerationSettings.Builder();
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
        generation.feature(GenerationStep.Feature.SURFACE_STRUCTURES, PlacedFeatureRegistry.WATER_DELTA);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_OPAL);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_SILVER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_STYGIAN_RUIN);
        generation.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, PlacedFeatureRegistry.TENEBRITE_BLOBS);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TREES_ASPHODEL);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_NECROFIRE);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_DEADROOT);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_PUMPKIN);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_CANDLES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_CEILING_VEGETATION);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_VEGETATION);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).temperature(0.5F).downfall(0.75F).effects(new BiomeEffects.Builder().waterColor(5001581).waterFogColor(8620438).fogColor(0xc0d8ff).grassColor(9470298).foliageColor(10387802).skyColor(4210816).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.01F)).build()).spawnSettings(spawns.build()).generationSettings(generation.build()).build();
    }

    private static Biome createNecroticGlaciers() {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        GenerationSettings.Builder generation = new GenerationSettings.Builder();
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_OPAL);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_SILVER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_HALLSTONE_EMERALD);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_STYGIAN_RUIN);
        generation.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, PlacedFeatureRegistry.TENEBRITE_BLOBS);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_NECROFIRE);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_DEADROOT);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_CANDLES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_CEILING_VEGETATION);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_VEGETATION);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).temperature(0.5F).downfall(0.5F).effects(new BiomeEffects.Builder().waterColor(5001581).waterFogColor(8620438).fogColor(0xc0d8ff).grassColor(9470298).foliageColor(10387802).skyColor(4210816).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.01F)).build()).spawnSettings(spawns.build()).generationSettings(generation.build()).build();
    }

    private static Biome createPerishedValley(boolean pumpkified) {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        GenerationSettings.Builder generation = new GenerationSettings.Builder();
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_OPAL);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_SILVER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_STYGIAN_RUIN);
        generation.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, PlacedFeatureRegistry.TENEBRITE_BLOBS);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.TREES_ASPHODEL);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_NECROFIRE);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_DEADROOT);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, pumpkified ? PlacedFeatureRegistry.PATCH_PUMPKIN_DENSE : VegetationPlacedFeatures.PATCH_PUMPKIN);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_CANDLES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_CEILING_VEGETATION);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_VEGETATION);
        if (pumpkified) {
            generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.LARGE_PUMPKIN);
            generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.SMALL_PUMPKIN);
        }
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).temperature(0.5F).downfall(0.5F).effects(new BiomeEffects.Builder().waterColor(5001581).waterFogColor(8620438).fogColor(0xc0d8ff).grassColor(9470298).foliageColor(10387802).skyColor(4210816).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.01F)).build()).spawnSettings(spawns.build()).generationSettings(generation.build()).build();
    }

    private static Biome createPetrifiedSands(boolean boneyard, boolean rocky) {
        SpawnSettings.Builder spawns = new SpawnSettings.Builder();
        GenerationSettings.Builder generation = new GenerationSettings.Builder();
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CAVE);
        generation.carver(GenerationStep.Carver.AIR, ConfiguredCarvers.CANYON);

        if (rocky) {
            generation.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedFeatureRegistry.DENSE_PETRIFIED_SANDSTONE_ROCK);
            generation.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedFeatureRegistry.BIG_PETRIFIED_SANDSTONE_ROCK);
        }
        else generation.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, PlacedFeatureRegistry.PETRIFIED_SANDSTONE_ROCK);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_OPAL);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_SILVER);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_HALLSTONE_EMERALD);
        generation.feature(GenerationStep.Feature.UNDERGROUND_ORES, PlacedFeatureRegistry.ORE_STYGIAN_RUIN);
        generation.feature(GenerationStep.Feature.UNDERGROUND_DECORATION, PlacedFeatureRegistry.TENEBRITE_BLOBS);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_NECROFIRE);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.PATCH_CANDLES);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_CEILING_VEGETATION);
        generation.feature(GenerationStep.Feature.VEGETAL_DECORATION, PlacedFeatureRegistry.BLOOD_CAVES_VEGETATION);
        return new Biome.Builder().precipitation(Biome.Precipitation.RAIN).category(Biome.Category.NONE).temperature(0.75F).downfall(0.0F).effects(new BiomeEffects.Builder().waterColor(5001581).waterFogColor(8620438).fogColor(0xc0d8ff).grassColor(9470298).foliageColor(10387802).skyColor(4210816).particleConfig(new BiomeParticleConfig(ParticleTypes.WHITE_ASH, 0.01F)).build()).spawnSettings(spawns.build()).generationSettings(generation.build()).build();
    }
}