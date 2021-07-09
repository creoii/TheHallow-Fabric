package creoii.hallows.core.registry;

import creoii.hallows.common.block.*;
import creoii.hallows.common.block.base.LogBlock;
import creoii.hallows.common.block.base.PostBlock;
import creoii.hallows.common.block.base.SaplingBlock;
import creoii.hallows.common.world.sapling.AsphodelSaplingGenerator;
import creoii.hallows.common.world.sapling.BloodEbonySaplingGenerator;
import creoii.hallows.common.world.sapling.EbonySaplingGenerator;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.util.Tags;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockView;

public class BlockRegistry {
    public static final Block HALLSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.DIRT_BROWN).strength(1.5F, 7.5F).sounds(BlockSoundGroup.STONE));
    public static Block HALLSTONE_SLAB;
    public static Block HALLSTONE_STAIRS;
    public static Block HALLSTONE_WALL;
    public static Block POLISHED_HALLSTONE;
    public static Block POLISHED_HALLSTONE_SLAB;
    public static Block POLISHED_HALLSTONE_STAIRS;
    public static Block POLISHED_HALLSTONE_WALL;

    public static Block HALLOWED_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.SPRUCE_BROWN).strength(0.5F).sounds(BlockSoundGroup.ROOTED_DIRT));
    public static Block DEADROOT;

    public static Block SILVER_ORE;
    public static Block OPAL_ORE;
    public static Block HALLSTONE_EMERALD_ORE;
    public static Block STYGIAN_RUIN;

    public static Block DAWN_MORTIS = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PALE_PURPLE).strength(1.5F).sounds(BlockSoundGroup.STONE));
    public static Block DAWN_MORTIS_SLAB;
    public static Block DAWN_MORTIS_STAIRS;
    public static Block DAWN_MORTIS_WALL;
    public static Block DAWN_MORTIS_BRICKS;
    public static Block DAWN_MORTIS_BRICK_SLAB;
    public static Block DAWN_MORTIS_BRICK_STAIRS;
    public static Block DAWN_MORTIS_BRICK_WALL;
    public static Block NOON_MORTIS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DULL_PINK).strength(1.5F).sounds(BlockSoundGroup.STONE));
    public static Block NOON_MORTIS_SLAB;
    public static Block NOON_MORTIS_STAIRS;
    public static Block NOON_MORTIS_WALL;
    public static Block NOON_MORTIS_BRICKS;
    public static Block NOON_MORTIS_BRICK_SLAB;
    public static Block NOON_MORTIS_BRICK_STAIRS;
    public static Block NOON_MORTIS_BRICK_WALL;
    public static Block DUSK_MORTIS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.MAGENTA).strength(1.5F).sounds(BlockSoundGroup.STONE));
    public static Block DUSK_MORTIS_SLAB;
    public static Block DUSK_MORTIS_STAIRS;
    public static Block DUSK_MORTIS_WALL;
    public static Block DUSK_MORTIS_BRICKS;
    public static Block DUSK_MORTIS_BRICK_SLAB;
    public static Block DUSK_MORTIS_BRICK_STAIRS;
    public static Block DUSK_MORTIS_BRICK_WALL;
    public static Block MIDNIGHT_MORTIS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.TERRACOTTA_PURPLE).strength(1.5F).sounds(BlockSoundGroup.STONE));
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
    public static Block EBONY_BRANCH;
    public static Block STRIPPED_EBONY_BRANCH;
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

    public static Block PETRIFIED_SAND = new SandBlock(6377538, FabricBlockSettings.of(Material.AGGREGATE, MapColor.SPRUCE_BROWN).strength(0.5F).sounds(BlockSoundGroup.SAND));
    public static Block PETRIFIED_SANDSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.SPRUCE_BROWN).strength(1.0F));
    public static Block PETRIFIED_SANDSTONE_SLAB;
    public static Block PETRIFIED_SANDSTONE_STAIRS;
    public static Block PETRIFIED_SANDSTONE_WALL;

    public static Block TENEBRITE = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.GRAY).strength(1.75F, 10.0F).sounds(BlockSoundGroup.STONE));
    public static Block TENEBRITE_SLAB;
    public static Block TENEBRITE_STAIRS;
    public static Block TENEBRITE_WALL;
    public static Block TENEBRITE_BRICKS;
    public static Block TENEBRITE_BRICK_SLAB;
    public static Block TENEBRITE_BRICK_STAIRS;
    public static Block TENEBRITE_BRICK_WALL;
    public static Block GILDED_TENEBRITE;
    public static Block SKULLISH_TENEBRITE;

    public static Block RED_MOSS_BLOCK;
    public static Block RED_MOSS_CARPET;
    public static Block BLOOD_MOREL;
    public static Block DEATH_MOREL;

    public static Block TALL_CANDLE;

    public static Block PUMPKIN_PLANKS;

    public static Block COBWEB_CARPET;

    public static Block ANOINTMENT_TABLE;

    public static void register() {
        createBlock("hallstone", HALLSTONE, ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE = createBlock("polished_hallstone", new Block(FabricBlockSettings.copy(HALLSTONE)), ItemGroup.BUILDING_BLOCKS);

        createBlock("hallowed_dirt", HALLOWED_DIRT, ItemGroup.BUILDING_BLOCKS);
        DEADROOT = createBlock("deadroot", new DeadrootBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)), ItemGroup.DECORATIONS);

        NECROFIRE = createBlock("necrofire", new NecrofireBlock(FabricBlockSettings.copy(Blocks.FIRE)), null);

        createBlock("dawn_mortis", DAWN_MORTIS, ItemGroup.BUILDING_BLOCKS);
        createBlock("noon_mortis", NOON_MORTIS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dusk_mortis", DUSK_MORTIS, ItemGroup.BUILDING_BLOCKS);
        createBlock("midnight_mortis", MIDNIGHT_MORTIS, ItemGroup.BUILDING_BLOCKS);

        STRIPPED_ASPHODEL_LOG = createBlock("stripped_asphodel_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_LOG = createBlock("asphodel_log", new LogBlock(STRIPPED_ASPHODEL_LOG, FabricBlockSettings.of(Material.WOOD, MapColor.IRON_GRAY).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_PLANKS = createBlock("asphodel_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_SAPLING = createBlock("asphodel_sapling", new SaplingBlock(new AsphodelSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);

        STRIPPED_EBONY_LOG = createBlock("stripped_ebony_log", new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LOG = createBlock("ebony_log", new LogBlock(STRIPPED_EBONY_LOG, FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_EBONY_BRANCH = createBlock("stripped_ebony_branch", new PostBlock(null, FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);
        EBONY_BRANCH = createBlock("ebony_branch", new PostBlock(STRIPPED_EBONY_BRANCH, FabricBlockSettings.copy(STRIPPED_EBONY_BRANCH)), ItemGroup.DECORATIONS);
        EBONY_PLANKS = createBlock("ebony_planks", new Block(FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LEAVES = createBlock("ebony_leaves", new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).allowsSpawning(BlockRegistry::canSpawnOnLeaves).suffocates(BlockRegistry::never).blockVision(BlockRegistry::never)), ItemGroup.BUILDING_BLOCKS);
        BLOOD_EBONY_LEAVES = createBlock("blood_ebony_leaves", new Block(FabricBlockSettings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).allowsSpawning(BlockRegistry::canSpawnOnLeaves).suffocates(BlockRegistry::never).blockVision(BlockRegistry::never)), ItemGroup.BUILDING_BLOCKS);
        EBONY_SAPLING = createBlock("ebony_sapling", new SaplingBlock(new EbonySaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
        BLOOD_EBONY_SAPLING = createBlock("blood_ebony_sapling", new SaplingBlock(new BloodEbonySaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), ItemGroup.DECORATIONS);
        HANGING_EBONY_LEAVES = createBlock("hanging_ebony_leaves", new HangingLeavesBlock(FabricBlockSettings.copy(EBONY_LEAVES)), ItemGroup.BUILDING_BLOCKS);
        HANGING_BLOOD_EBONY_LEAVES = createBlock("hanging_blood_ebony_leaves", new HangingLeavesBlock(FabricBlockSettings.copy(BLOOD_EBONY_LEAVES)), ItemGroup.BUILDING_BLOCKS);

        createBlock("petrified_sand", PETRIFIED_SAND, ItemGroup.BUILDING_BLOCKS);
        createBlock("petrified_sandstone", PETRIFIED_SANDSTONE, ItemGroup.BUILDING_BLOCKS);

        createBlock("tenebrite", TENEBRITE, ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_BRICKS = createBlock("tenebrite_bricks", new Block(FabricBlockSettings.of(Material.SOIL, MapColor.GRAY).strength(1.5F, 15.0F).sounds(BlockSoundGroup.STONE)), ItemGroup.BUILDING_BLOCKS);

        RED_MOSS_CARPET = createBlock("red_moss_carpet", new CarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.DARK_RED).strength(0.1F).sounds(BlockSoundGroup.MOSS_CARPET)), ItemGroup.DECORATIONS);
        RED_MOSS_BLOCK = createBlock("red_moss_block", new RedMossBlock(AbstractBlock.Settings.of(Material.MOSS_BLOCK, MapColor.DARK_RED).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK)), ItemGroup.DECORATIONS);
        BLOOD_MOREL = createBlock("blood_morel", new BloodMorelBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).strength(0.2F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);
        DEATH_MOREL = createBlock("death_morel", new DeathMorelBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).strength(0.2F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);

        SILVER_ORE = createBlock("silver_ore", new OreBlock(FabricBlockSettings.copy(HALLSTONE).requiresTool().strength(3.0F, 7.5F)), ItemGroup.BUILDING_BLOCKS);
        OPAL_ORE = createBlock("opal_ore", new OreBlock(FabricBlockSettings.copy(HALLSTONE).requiresTool().strength(3.0F, 7.5F), UniformIntProvider.create(3, 7)), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_EMERALD_ORE = createBlock("hallstone_emerald_ore", new OreBlock(FabricBlockSettings.copy(HALLSTONE).requiresTool().strength(3.0F, 7.5F), UniformIntProvider.create(3, 7)), ItemGroup.BUILDING_BLOCKS);
        STYGIAN_RUIN = createBlock("stygian_ruin", new Block(FabricBlockSettings.copy(Blocks.ANCIENT_DEBRIS).requiresTool()), ItemGroup.BUILDING_BLOCKS);

        COBWEB_CARPET = createBlock("cobweb_carpet", new CobwebCarpetBlock(AbstractBlock.Settings.copy(Blocks.WHITE_CARPET)), ItemGroup.DECORATIONS);

        ANOINTMENT_TABLE = createBlock("anointment_table", new AnointmentTableBlock(AbstractBlock.Settings.copy(Blocks.ENCHANTING_TABLE)), ItemGroup.DECORATIONS);

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
                ASPHODEL_SAPLING,
                EBONY_LEAVES,
                BLOOD_EBONY_LEAVES,
                EBONY_SAPLING,
                BLOOD_EBONY_SAPLING,
                HANGING_EBONY_LEAVES,
                HANGING_BLOOD_EBONY_LEAVES,
                COBWEB_CARPET
        );
    }

    private static void flammables() {
        FlammableBlockRegistry.getDefaultInstance().add(ASPHODEL_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_ASPHODEL_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ASPHODEL_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(EBONY_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(STRIPPED_EBONY_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(EBONY_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(EBONY_LEAVES, 30, 60);
        FlammableBlockRegistry.getDefaultInstance().add(BLOOD_EBONY_LEAVES, 30, 60);
    }

    private static void compostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(DEADROOT.asItem(), 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ASPHODEL_SAPLING.asItem(), 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(EBONY_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(EBONY_SAPLING.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLOOD_EBONY_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLOOD_EBONY_SAPLING.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(RED_MOSS_CARPET.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(RED_MOSS_BLOCK.asItem(), 0.65F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLOOD_MOREL.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(DEATH_MOREL.asItem(), 0.3F);
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