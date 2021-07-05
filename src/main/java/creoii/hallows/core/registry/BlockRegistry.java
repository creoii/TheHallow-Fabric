package creoii.hallows.core.registry;

import creoii.hallows.common.block.*;
import creoii.hallows.common.block.base.FernBlock;
import creoii.hallows.common.block.base.LogBlock;
import creoii.hallows.common.block.base.PostBlock;
import creoii.hallows.common.block.base.SaplingBlock;
import creoii.hallows.common.world.sapling.AsphodelSaplingGenerator;
import creoii.hallows.common.world.sapling.BloodEbonySaplingGenerator;
import creoii.hallows.common.world.sapling.EbonySaplingGenerator;
import creoii.hallows.core.Hallows;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class BlockRegistry {
    public static Block HALLSTONE;
    public static Block HALLSTONE_SLAB;
    public static Block HALLSTONE_STAIRS;
    public static Block HALLSTONE_WALL;
    public static Block POLISHED_HALLSTONE;
    public static Block POLISHED_HALLSTONE_SLAB;
    public static Block POLISHED_HALLSTONE_STAIRS;
    public static Block POLISHED_HALLSTONE_WALL;
    public static Block HALLOWED_DIRT;
    public static Block DEADROOT;

    public static Block SILVER_ORE;
    public static Block OPAL_ORE;
    public static Block HALLSTONE_EMERALD_ORE;
    public static Block STYGIAN_RUIN;

    public static Block DAWN_MORTIS;
    public static Block DAWN_MORTIS_SLAB;
    public static Block DAWN_MORTIS_STAIRS;
    public static Block DAWN_MORTIS_WALL;
    public static Block DAWN_MORTIS_BRICKS;
    public static Block DAWN_MORTIS_BRICK_SLAB;
    public static Block DAWN_MORTIS_BRICK_STAIRS;
    public static Block DAWN_MORTIS_BRICK_WALL;
    public static Block NOON_MORTIS;
    public static Block NOON_MORTIS_SLAB;
    public static Block NOON_MORTIS_STAIRS;
    public static Block NOON_MORTIS_WALL;
    public static Block NOON_MORTIS_BRICKS;
    public static Block NOON_MORTIS_BRICK_SLAB;
    public static Block NOON_MORTIS_BRICK_STAIRS;
    public static Block NOON_MORTIS_BRICK_WALL;
    public static Block DUSK_MORTIS;
    public static Block DUSK_MORTIS_SLAB;
    public static Block DUSK_MORTIS_STAIRS;
    public static Block DUSK_MORTIS_WALL;
    public static Block DUSK_MORTIS_BRICKS;
    public static Block DUSK_MORTIS_BRICK_SLAB;
    public static Block DUSK_MORTIS_BRICK_STAIRS;
    public static Block DUSK_MORTIS_BRICK_WALL;
    public static Block MIDNIGHT_MORTIS;
    public static Block MIDNIGHT_MORTIS_SLAB;
    public static Block MIDNIGHT_MORTIS_STAIRS;
    public static Block MIDNIGHT_MORTIS_WALL;
    public static Block MIDNIGHT_MORTIS_BRICKS;
    public static Block MIDNIGHT_MORTIS_BRICK_SLAB;
    public static Block MIDNIGHT_MORTIS_BRICK_STAIRS;
    public static Block MIDNIGHT_MORTIS_BRICK_WALL;

    public static Block NECROFIRE;
    public static Block NECROFIRE_CAMPFIRE;
    public static Block NECROFIRE_LANTERN;
    public static Block NECROFIRE_TORCH;
    public static Block NECROFIRE_WALL_TORCH;

    public static Block ASPHODEL_LOG;
    public static Block STRIPPED_ASPHODEL_LOG;
    public static Block ASPHODEL_WOOD;
    public static Block STRIPPED_ASPHODEL_WOOD;
    public static Block ASPHODEL_PLANKS;
    public static Block ASPHODEL_SLAB;
    public static Block ASPHODEL_STAIRS;
    public static Block ASPHODEL_FENCE;
    public static Block ASPHODEL_FENCE_GATE;
    public static Block ASPHODEL_BUTTON;
    public static Block ASPHODEL_PRESSURE_PLATE;
    public static Block ASPHODEL_SAPLING;
    public static Block POTTED_ASPHODEL_SAPLING;
    public static Block ASPHODEL_DOOR;
    public static Block ASPHODEL_TRAPDOOR;

    public static Block EBONY_LOG;
    public static Block STRIPPED_EBONY_LOG;
    public static Block EBONY_POST;
    public static Block STRIPPED_EBONY_POST;
    public static Block EBONY_WOOD;
    public static Block STRIPPED_EBONY_WOOD;
    public static Block EBONY_PLANKS;
    public static Block EBONY_SLAB;
    public static Block EBONY_STAIRS;
    public static Block EBONY_FENCE;
    public static Block EBONY_FENCE_GATE;
    public static Block EBONY_BUTTON;
    public static Block EBONY_PRESSURE_PLATE;
    public static Block EBONY_LEAVES;
    public static Block EBONY_SAPLING;
    public static Block POTTED_EBONY_SAPLING;
    public static Block BLOOD_EBONY_LEAVES;
    public static Block BLOOD_EBONY_SAPLING;
    public static Block POTTED_BLOOD_EBONY_SAPLING;
    public static Block HANGING_EBONY_LEAVES;
    public static Block HANGING_BLOOD_EBONY_LEAVES;
    public static Block EBONY_DOOR;
    public static Block EBONY_TRAPDOOR;

    public static Block PETRIFIED_SAND;
    public static Block PETRIFIED_SANDSTONE;
    public static Block PETRIFIED_SANDSTONE_SLAB;
    public static Block PETRIFIED_SANDSTONE_STAIRS;
    public static Block PETRIFIED_SANDSTONE_WALL;

    public static Block TENEBRITE;
    public static Block TENEBRITE_SLAB;
    public static Block TENEBRITE_STAIRS;
    public static Block TENEBRITE_WALL;
    public static Block TENEBRITE_BRICKS;
    public static Block TENEBRITE_BRICK_SLAB;
    public static Block TENEBRITE_BRICK_STAIRS;
    public static Block TENEBRITE_BRICK_WALL;
    public static Block GILDED_TENEBRITE;
    public static Block SKULLISH_TENEBRITE;

    public static void register() {
        HALLSTONE = createBlock("hallstone", new Block(FabricBlockSettings.of(Material.STONE, MapColor.DIRT_BROWN).strength(1.6F, 7.0F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE = createBlock("polished_hallstone", new Block(FabricBlockSettings.copy(HALLSTONE)), ItemGroup.BUILDING_BLOCKS);

        HALLOWED_DIRT = createBlock("hallowed_dirt", new Block(FabricBlockSettings.of(Material.SOIL, MapColor.SPRUCE_BROWN).strength(0.5F).sounds(BlockSoundGroup.ROOTED_DIRT)), ItemGroup.BUILDING_BLOCKS);
        DEADROOT = createBlock("deadroot", new FernBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)), ItemGroup.BUILDING_BLOCKS);

        NECROFIRE = createBlock("necrofire", new NecrofireBlock(FabricBlockSettings.copy(Blocks.FIRE)), null);

        DAWN_MORTIS = createBlock("dawn_mortis", new Block(FabricBlockSettings.of(Material.STONE, MapColor.PALE_PURPLE).strength(1.5F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS = createBlock("noon_mortis", new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DULL_PINK).strength(1.5F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS = createBlock("dusk_mortis", new Block(FabricBlockSettings.of(Material.SOIL, MapColor.MAGENTA).strength(1.5F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS = createBlock("midnight_mortis", new Block(FabricBlockSettings.of(Material.SOIL, MapColor.TERRACOTTA_PURPLE).strength(1.5F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);

        STRIPPED_ASPHODEL_LOG = createBlock("stripped_asphodel_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_LOG = createBlock("asphodel_log", new LogBlock(STRIPPED_ASPHODEL_LOG, FabricBlockSettings.of(Material.WOOD, MapColor.IRON_GRAY).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_PLANKS = createBlock("asphodel_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_SAPLING = createBlock("asphodel_sapling", new SaplingBlock(new AsphodelSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);

        STRIPPED_EBONY_LOG = createBlock("stripped_ebony_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LOG = createBlock("ebony_log", new LogBlock(STRIPPED_EBONY_LOG, FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_EBONY_POST = createBlock("stripped_ebony_post", new PostBlock(null, FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);
        EBONY_POST = createBlock("ebony_post", new PostBlock(STRIPPED_EBONY_POST, FabricBlockSettings.copy(STRIPPED_EBONY_POST)), ItemGroup.DECORATIONS);
        EBONY_PLANKS = createBlock("ebony_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LEAVES = createBlock("ebony_leaves", new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).allowsSpawning(BlockRegistry::canSpawnOnLeaves).suffocates(BlockRegistry::never).blockVision(BlockRegistry::never)), ItemGroup.BUILDING_BLOCKS);
        BLOOD_EBONY_LEAVES = createBlock("blood_ebony_leaves", new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).allowsSpawning(BlockRegistry::canSpawnOnLeaves).suffocates(BlockRegistry::never).blockVision(BlockRegistry::never)), ItemGroup.BUILDING_BLOCKS);
        EBONY_SAPLING = createBlock("ebony_sapling", new SaplingBlock(new EbonySaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
        BLOOD_EBONY_SAPLING = createBlock("blood_ebony_sapling", new SaplingBlock(new BloodEbonySaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
        HANGING_EBONY_LEAVES = createBlock("hanging_ebony_leaves", new HangingLeavesBlock(FabricBlockSettings.copy(EBONY_LEAVES)), ItemGroup.BUILDING_BLOCKS);
        HANGING_BLOOD_EBONY_LEAVES = createBlock("hanging_blood_ebony_leaves", new HangingLeavesBlock(FabricBlockSettings.copy(BLOOD_EBONY_LEAVES)), ItemGroup.BUILDING_BLOCKS);

        PETRIFIED_SAND = createBlock("petrified_sand", new Block(FabricBlockSettings.of(Material.AGGREGATE, MapColor.SPRUCE_BROWN).strength(0.5F).sounds(BlockSoundGroup.SAND)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE = createBlock("petrified_sandstone", new Block(FabricBlockSettings.of(Material.STONE, MapColor.SPRUCE_BROWN).strength(1.0F)), ItemGroup.BUILDING_BLOCKS);

        TENEBRITE = createBlock("tenebrite", new Block(FabricBlockSettings.of(Material.SOIL, MapColor.GRAY).strength(1.5F, 10.0F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_BRICKS = createBlock("tenebrite_bricks", new Block(FabricBlockSettings.of(Material.SOIL, MapColor.GRAY).strength(1.5F, 15.0F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);

        flammables();
        compostables();
        fuels();
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        renderLayers();
    }

    @Environment(EnvType.CLIENT)
    private static void renderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                DEADROOT,
                EBONY_LEAVES,
                BLOOD_EBONY_LEAVES,
                EBONY_SAPLING,
                BLOOD_EBONY_SAPLING,
                HANGING_EBONY_LEAVES,
                HANGING_BLOOD_EBONY_LEAVES
        );
    }

    private static void flammables() {
    }

    private static void compostables() {
    }

    private static void fuels() {
    }

    private static <B extends Block> Block createBlock(String name, B block, ItemGroup group) {
        Registry.register(Registry.BLOCK, new Identifier(Hallows.MOD_ID, name), block);
        if (group != null) Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, name), new BlockItem(block, new Item.Settings().group(group)));
        return block;
    }

    private static boolean never(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
}