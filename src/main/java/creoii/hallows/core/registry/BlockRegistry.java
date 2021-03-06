package creoii.hallows.core.registry;

import creoii.hallows.common.block.*;
import creoii.hallows.common.block.base.DoorBlock;
import creoii.hallows.common.block.base.PressurePlateBlock;
import creoii.hallows.common.block.base.SaplingBlock;
import creoii.hallows.common.block.base.StairsBlock;
import creoii.hallows.common.block.base.TorchBlock;
import creoii.hallows.common.block.base.TrapdoorBlock;
import creoii.hallows.common.block.base.WallTorchBlock;
import creoii.hallows.common.block.base.*;
import creoii.hallows.common.world.sapling.AsphodelSaplingGenerator;
import creoii.hallows.common.world.sapling.BloodEbonySaplingGenerator;
import creoii.hallows.common.world.sapling.EbonySaplingGenerator;
import creoii.hallows.core.Hallows;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class BlockRegistry {
    public static final Block HALLSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.DIRT_BROWN).strength(1.5F, 7.5F).sounds(BlockSoundGroup.STONE));
    public static final Block HALLSTONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(HALLSTONE));
    public static final Block HALLSTONE_STAIRS = new StairsBlock(HALLSTONE.getDefaultState(), AbstractBlock.Settings.copy(HALLSTONE));
    public static final Block HALLSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(HALLSTONE));
    public static final Block POLISHED_HALLSTONE = new Block(FabricBlockSettings.copy(HALLSTONE));
    public static final Block POLISHED_HALLSTONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(POLISHED_HALLSTONE));
    public static final Block POLISHED_HALLSTONE_STAIRS = new StairsBlock(POLISHED_HALLSTONE.getDefaultState(), AbstractBlock.Settings.copy(POLISHED_HALLSTONE));
    public static final Block POLISHED_HALLSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(POLISHED_HALLSTONE));
    public static final Block HALLSTONE_BRICKS = new Block(FabricBlockSettings.of(Material.STONE, MapColor.DIRT_BROWN).strength(1.5F, 7.5F).sounds(BlockSoundGroup.STONE));
    public static final Block HALLSTONE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(HALLSTONE_BRICKS));
    public static final Block HALLSTONE_BRICK_STAIRS = new StairsBlock(HALLSTONE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(HALLSTONE_BRICKS));
    public static final Block HALLSTONE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(HALLSTONE_BRICKS));

    public static final Block HALLOWED_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.SPRUCE_BROWN).strength(0.5F).sounds(BlockSoundGroup.ROOTED_DIRT));
    public static final Block WICKED_DIRT = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.SPRUCE_BROWN).strength(0.5F).sounds(BlockSoundGroup.ROOTED_DIRT));
    public static Block DEADROOT;
    public static Block WICKED_WEED;
    public static Block BLOODBELL;
    public static Block DEATHBELL;
    public static Block ROSE;
    public static Block BLOOD_LILY;
    public static Block CRYSTALLIZED_BLOOD_LILY;
    public static Block CANDYSHROOM;

    public static Block HALLSTONE_EMERALD_ORE;
    public static Block TENEBRITE_EMERALD_ORE;
    public static Block SILVER_ORE;
    public static Block TENEBRITE_SILVER_ORE;
    public static Block OPAL_ORE;
    public static Block TENEBRITE_OPAL_ORE;
    public static Block STYGIAN_RUIN;
    public static Block STYGIAN_BLOCK;

    public static final Block DAWN_MORTIS = new Block(FabricBlockSettings.of(Material.STONE, MapColor.PALE_PURPLE).strength(1.5F).sounds(BlockSoundGroup.STONE));
    public static final Block DAWN_MORTIS_SLAB = new SlabBlock(AbstractBlock.Settings.copy(DAWN_MORTIS));
    public static final Block DAWN_MORTIS_STAIRS = new StairsBlock(DAWN_MORTIS.getDefaultState(), AbstractBlock.Settings.copy(DAWN_MORTIS));
    public static final Block DAWN_MORTIS_WALL = new WallBlock(AbstractBlock.Settings.copy(DAWN_MORTIS));
    public static final Block DAWN_MORTIS_BRICKS = new Block(FabricBlockSettings.copy(DAWN_MORTIS).strength(1.6F));
    public static final Block DAWN_MORTIS_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(DAWN_MORTIS_BRICKS));
    public static final Block DAWN_MORTIS_BRICK_STAIRS = new StairsBlock(DAWN_MORTIS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(DAWN_MORTIS_BRICKS));
    public static final Block DAWN_MORTIS_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(DAWN_MORTIS_BRICKS));
    public static final Block NOON_MORTIS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.DULL_PINK).strength(1.5F).sounds(BlockSoundGroup.STONE));
    public static final Block NOON_MORTIS_SLAB = new SlabBlock(AbstractBlock.Settings.copy(NOON_MORTIS));
    public static final Block NOON_MORTIS_STAIRS = new StairsBlock(NOON_MORTIS.getDefaultState(), AbstractBlock.Settings.copy(NOON_MORTIS));
    public static final Block NOON_MORTIS_WALL = new WallBlock(AbstractBlock.Settings.copy(NOON_MORTIS));
    public static final Block NOON_MORTIS_BRICKS = new Block(FabricBlockSettings.copy(NOON_MORTIS).strength(1.6F));
    public static final Block NOON_MORTIS_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(NOON_MORTIS_BRICKS));
    public static final Block NOON_MORTIS_BRICK_STAIRS = new StairsBlock(NOON_MORTIS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(NOON_MORTIS_BRICKS));
    public static final Block NOON_MORTIS_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(NOON_MORTIS_BRICKS));
    public static final Block DUSK_MORTIS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.MAGENTA).strength(1.5F).sounds(BlockSoundGroup.STONE));
    public static final Block DUSK_MORTIS_SLAB = new SlabBlock(AbstractBlock.Settings.copy(DUSK_MORTIS));
    public static final Block DUSK_MORTIS_STAIRS = new StairsBlock(DUSK_MORTIS.getDefaultState(), AbstractBlock.Settings.copy(DUSK_MORTIS));
    public static final Block DUSK_MORTIS_WALL = new WallBlock(AbstractBlock.Settings.copy(DUSK_MORTIS));
    public static final Block DUSK_MORTIS_BRICKS = new Block(FabricBlockSettings.copy(DUSK_MORTIS).strength(1.6F));
    public static final Block DUSK_MORTIS_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(DUSK_MORTIS_BRICKS));
    public static final Block DUSK_MORTIS_BRICK_STAIRS = new StairsBlock(DUSK_MORTIS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(DUSK_MORTIS_BRICKS));
    public static final Block DUSK_MORTIS_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(DUSK_MORTIS_BRICKS));
    public static final Block MIDNIGHT_MORTIS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.TERRACOTTA_PURPLE).strength(1.5F).sounds(BlockSoundGroup.STONE));
    public static final Block MIDNIGHT_MORTIS_SLAB = new SlabBlock(AbstractBlock.Settings.copy(MIDNIGHT_MORTIS));
    public static final Block MIDNIGHT_MORTIS_STAIRS = new StairsBlock(MIDNIGHT_MORTIS.getDefaultState(), AbstractBlock.Settings.copy(MIDNIGHT_MORTIS));
    public static final Block MIDNIGHT_MORTIS_WALL = new WallBlock(AbstractBlock.Settings.copy(MIDNIGHT_MORTIS));
    public static final Block MIDNIGHT_MORTIS_BRICKS = new Block(FabricBlockSettings.copy(MIDNIGHT_MORTIS).strength(1.6F));
    public static final Block MIDNIGHT_MORTIS_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(MIDNIGHT_MORTIS_BRICKS));
    public static final Block MIDNIGHT_MORTIS_BRICK_STAIRS = new StairsBlock(MIDNIGHT_MORTIS_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(MIDNIGHT_MORTIS_BRICKS));
    public static final Block MIDNIGHT_MORTIS_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(MIDNIGHT_MORTIS_BRICKS));

    public static Block NECROFIRE;
    public static final Block NECROFIRE_CAMPFIRE = new CampfireBlock(false, 1, AbstractBlock.Settings.copy(Blocks.SOUL_CAMPFIRE));
    public static final Block NECROFIRE_LANTERN = new LanternBlock(AbstractBlock.Settings.copy(Blocks.LANTERN));
    public static final Block NECROFIRE_TORCH = new TorchBlock(AbstractBlock.Settings.copy(Blocks.TORCH), ParticleRegistry.NECROFLAME);
    public static final Block NECROFIRE_WALL_TORCH = new WallTorchBlock(AbstractBlock.Settings.copy(Blocks.WALL_TORCH), ParticleRegistry.NECROFLAME);

    public static final Block STRIPPED_ASPHODEL_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block ASPHODEL_LOG = new LogBlock(STRIPPED_ASPHODEL_LOG, FabricBlockSettings.of(Material.WOOD, MapColor.IRON_GRAY).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block ASPHODEL_WOOD = new PillarBlock(AbstractBlock.Settings.copy(ASPHODEL_LOG));
    public static final Block STRIPPED_ASPHODEL_WOOD = new PillarBlock(AbstractBlock.Settings.copy(STRIPPED_ASPHODEL_LOG));
    public static final Block ASPHODEL_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MapColor.WHITE_GRAY).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block ASPHODEL_SLAB = new SlabBlock(AbstractBlock.Settings.copy(ASPHODEL_PLANKS));
    public static final Block ASPHODEL_STAIRS = new StairsBlock(ASPHODEL_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(ASPHODEL_PLANKS));
    public static final Block ASPHODEL_FENCE = new FenceBlock(AbstractBlock.Settings.copy(ASPHODEL_PLANKS));
    public static final Block ASPHODEL_FENCE_GATE = new FenceGateBlock(AbstractBlock.Settings.copy(ASPHODEL_PLANKS));;
    public static final Block ASPHODEL_BUTTON = new ButtonBlock(true, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON));
    public static final Block ASPHODEL_PRESSURE_PLATE = new PressurePlateBlock(net.minecraft.block.PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE));
    public static final Block ASPHODEL_SAPLING = new SaplingBlock(new AsphodelSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_ASPHODEL_SAPLING = new FlowerPotBlock(ASPHODEL_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT));
    public static Block ASPHODEL_DOOR;
    public static Block ASPHODEL_TRAPDOOR;

    public static final Block STRIPPED_EBONY_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block EBONY_LOG = new LogBlock(STRIPPED_EBONY_LOG, FabricBlockSettings.of(Material.WOOD, MapColor.TERRACOTTA_BLACK).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static Block EBONY_BRANCH;
    public static Block STRIPPED_EBONY_BRANCH;
    public static final Block EBONY_WOOD = new PillarBlock(AbstractBlock.Settings.copy(EBONY_LOG));
    public static final Block STRIPPED_EBONY_WOOD = new PillarBlock(AbstractBlock.Settings.copy(STRIPPED_EBONY_LOG));
    public static final Block EBONY_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD, MapColor.BLACK).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block EBONY_SLAB = new SlabBlock(AbstractBlock.Settings.copy(EBONY_PLANKS));
    public static final Block EBONY_STAIRS = new StairsBlock(EBONY_PLANKS.getDefaultState(), AbstractBlock.Settings.copy(EBONY_PLANKS));
    public static final Block EBONY_FENCE = new FenceBlock(AbstractBlock.Settings.copy(ASPHODEL_PLANKS));
    public static final Block EBONY_FENCE_GATE = new FenceGateBlock(AbstractBlock.Settings.copy(ASPHODEL_PLANKS));;
    public static final Block EBONY_BUTTON = new ButtonBlock(true, 30, AbstractBlock.Settings.copy(Blocks.OAK_BUTTON));
    public static final Block EBONY_PRESSURE_PLATE = new PressurePlateBlock(net.minecraft.block.PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE));
    public static Block EBONY_LEAVES;
    public static final Block EBONY_SAPLING = new SaplingBlock(new EbonySaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_EBONY_SAPLING = new FlowerPotBlock(EBONY_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT));
    public static Block BLOOD_EBONY_LEAVES;
    public static final Block BLOOD_EBONY_SAPLING = new SaplingBlock(new BloodEbonySaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING));
    public static final Block POTTED_BLOOD_EBONY_SAPLING = new FlowerPotBlock(BLOOD_EBONY_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT));
    public static Block HANGING_EBONY_LEAVES;
    public static Block HANGING_BLOOD_EBONY_LEAVES;
    public static Block EBONY_DOOR;
    public static Block EBONY_TRAPDOOR;

    public static final Block PETRIFIED_SAND = new SandBlock(6377538, FabricBlockSettings.of(Material.AGGREGATE, MapColor.SPRUCE_BROWN).strength(0.5F).sounds(BlockSoundGroup.SAND));
    public static final Block PETRIFIED_SANDSTONE = new Block(FabricBlockSettings.of(Material.STONE, MapColor.SPRUCE_BROWN).strength(1.0F));
    public static final Block PETRIFIED_SANDSTONE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(PETRIFIED_SANDSTONE));
    public static final Block PETRIFIED_SANDSTONE_STAIRS = new StairsBlock(PETRIFIED_SANDSTONE.getDefaultState(), AbstractBlock.Settings.copy(PETRIFIED_SANDSTONE));
    public static final Block PETRIFIED_SANDSTONE_WALL = new WallBlock(AbstractBlock.Settings.copy(PETRIFIED_SANDSTONE));

    public static final Block TENEBRITE = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.GRAY).strength(1.75F, 10.0F).sounds(BlockSoundGroup.STONE));
    public static final Block TENEBRITE_SLAB = new SlabBlock(AbstractBlock.Settings.copy(TENEBRITE));
    public static final Block TENEBRITE_STAIRS = new StairsBlock(TENEBRITE.getDefaultState(), AbstractBlock.Settings.copy(TENEBRITE));
    public static final Block TENEBRITE_WALL = new WallBlock(AbstractBlock.Settings.copy(TENEBRITE));
    public static final Block TENEBRITE_BRICKS = new Block(FabricBlockSettings.of(Material.SOIL, MapColor.GRAY).strength(1.5F, 15.0F).sounds(BlockSoundGroup.STONE));
    public static final Block TENEBRITE_BRICK_SLAB = new SlabBlock(AbstractBlock.Settings.copy(TENEBRITE_BRICKS));
    public static final Block TENEBRITE_BRICK_STAIRS = new StairsBlock(TENEBRITE_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(TENEBRITE_BRICKS));
    public static final Block TENEBRITE_BRICK_WALL = new WallBlock(AbstractBlock.Settings.copy(TENEBRITE_BRICKS));
    public static final Block CHISELED_TENEBRITE_BRICKS = new Block(FabricBlockSettings.copy(TENEBRITE_BRICKS));
    public static Block SMOOTH_TENEBRITE;
    public static Block GILDED_TENEBRITE;
    public static Block SKULLISH_TENEBRITE;

    public static Block RED_MOSS_BLOCK;
    public static Block RED_MOSS_CARPET;
    public static Block BLOOD_MOREL;
    public static Block DEATH_MOREL;

    public static final Block TALL_CANDLE = new TallCandleBlock(AbstractBlock.Settings.copy(Blocks.CANDLE));
    public static final Block CANDLE_SKULL = new CandleSkullBlock(AbstractBlock.Settings.copy(Blocks.SKELETON_SKULL).luminance((state) -> 12));

    public static Block PUMPKIN_PLANKS;

    public static Block COBWEB_CARPET;

    public static Block ANOINTMENT_TABLE;
    public static Block GIANT_CAULDRON = new GiantCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON));

    public static Block IGNIS_CRYSTAL_BLOCK;

    public static Block CORN;

    public static void register() {
        createBlock("hallstone", HALLSTONE, ItemGroup.BUILDING_BLOCKS);
        createBlock("hallstone_slab", HALLSTONE_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("hallstone_stairs", HALLSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("hallstone_wall", HALLSTONE_WALL, ItemGroup.DECORATIONS);
        createBlock("polished_hallstone", POLISHED_HALLSTONE, ItemGroup.BUILDING_BLOCKS);
        createBlock("polished_hallstone_slab", POLISHED_HALLSTONE_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("polished_hallstone_stairs", POLISHED_HALLSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("polished_hallstone_wall", POLISHED_HALLSTONE_WALL, ItemGroup.DECORATIONS);
        createBlock("hallstone_bricks", HALLSTONE_BRICKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("hallstone_brick_slab", HALLSTONE_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("hallstone_brick_stairs", HALLSTONE_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("hallstone_brick_wall", HALLSTONE_BRICK_WALL, ItemGroup.DECORATIONS);

        createBlock("hallowed_dirt", HALLOWED_DIRT, ItemGroup.BUILDING_BLOCKS);
        createBlock("wicked_dirt", WICKED_DIRT, ItemGroup.BUILDING_BLOCKS);
        DEADROOT = createBlock("deadroot", new DeadrootBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)), ItemGroup.DECORATIONS);
        WICKED_WEED = createBlock("wicked_weed", new DeadrootBlock(FabricBlockSettings.copy(Blocks.CRIMSON_ROOTS)), ItemGroup.DECORATIONS);
        DEATHBELL = createBlock("deathbell", new FlowerBlock(StatusEffects.WITHER, 4, FabricBlockSettings.copy(Blocks.POPPY)), ItemGroup.DECORATIONS);
        BLOODBELL = createBlock("bloodbell", new FlowerBlock(StatusEffects.REGENERATION, 8, FabricBlockSettings.copy(Blocks.POPPY)), ItemGroup.DECORATIONS);
        ROSE = createBlock("rose", new FlowerBlock(StatusEffects.NIGHT_VISION, 8, FabricBlockSettings.copy(Blocks.POPPY)), ItemGroup.DECORATIONS);
        BLOOD_LILY = createBlock("blood_lily", new LilyBlock(StatusEffects.NIGHT_VISION, 8, FabricBlockSettings.copy(Blocks.POPPY)), ItemGroup.DECORATIONS);
        CRYSTALLIZED_BLOOD_LILY = createBlock("crystallized_blood_lily", new CrystallizedLilyBlock(StatusEffects.NIGHT_VISION, 8, FabricBlockSettings.copy(Blocks.POPPY)), ItemGroup.DECORATIONS);
        CANDYSHROOM = createBlock("candyshroom", new FlowerBlock(StatusEffects.NIGHT_VISION, 8, FabricBlockSettings.copy(Blocks.BROWN_MUSHROOM)), ItemGroup.DECORATIONS);

        NECROFIRE = createBlock("necrofire", new NecrofireBlock(FabricBlockSettings.copy(Blocks.FIRE)), null);
        createBlock("necrofire_campfire", NECROFIRE_CAMPFIRE, ItemGroup.DECORATIONS);
        createBlock("necrofire_lantern", NECROFIRE_LANTERN, ItemGroup.DECORATIONS);
        createBlock("necrofire_torch", NECROFIRE_TORCH, ItemGroup.DECORATIONS);
        createBlock("necrofire_wall_torch", NECROFIRE_WALL_TORCH, null);

        createBlock("dawn_mortis", DAWN_MORTIS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dawn_mortis_slab", DAWN_MORTIS_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("dawn_mortis_stairs", DAWN_MORTIS_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dawn_mortis_wall", DAWN_MORTIS_WALL, ItemGroup.DECORATIONS);
        createBlock("dawn_mortis_bricks", DAWN_MORTIS_BRICKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dawn_mortis_brick_slab", DAWN_MORTIS_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("dawn_mortis_brick_stairs", DAWN_MORTIS_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dawn_mortis_brick_wall", DAWN_MORTIS_BRICK_WALL, ItemGroup.DECORATIONS);
        createBlock("noon_mortis", NOON_MORTIS, ItemGroup.BUILDING_BLOCKS);
        createBlock("noon_mortis_slab", NOON_MORTIS_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("noon_mortis_stairs",NOON_MORTIS_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("noon_mortis_wall", NOON_MORTIS_WALL, ItemGroup.DECORATIONS);
        createBlock("noon_mortis_bricks", NOON_MORTIS_BRICKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("noon_mortis_brick_slab", NOON_MORTIS_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("noon_mortis_brick_stairs",NOON_MORTIS_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("noon_mortis_brick_wall", NOON_MORTIS_BRICK_WALL, ItemGroup.DECORATIONS);
        createBlock("dusk_mortis", DUSK_MORTIS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dusk_mortis_slab", DUSK_MORTIS_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("dusk_mortis_stairs", DUSK_MORTIS_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dusk_mortis_wall", DUSK_MORTIS_WALL, ItemGroup.DECORATIONS);
        createBlock("dusk_mortis_bricks", DUSK_MORTIS_BRICKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dusk_mortis_brick_slab", DUSK_MORTIS_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("dusk_mortis_brick_stairs", DUSK_MORTIS_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("dusk_mortis_brick_wall", DUSK_MORTIS_BRICK_WALL, ItemGroup.DECORATIONS);
        createBlock("midnight_mortis", MIDNIGHT_MORTIS, ItemGroup.BUILDING_BLOCKS);
        createBlock("midnight_mortis_slab", MIDNIGHT_MORTIS_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("midnight_mortis_stairs", MIDNIGHT_MORTIS_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("midnight_mortis_wall", MIDNIGHT_MORTIS_WALL, ItemGroup.DECORATIONS);
        createBlock("midnight_mortis_bricks", MIDNIGHT_MORTIS_BRICKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("midnight_mortis_brick_slab", MIDNIGHT_MORTIS_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("midnight_mortis_brick_stairs", MIDNIGHT_MORTIS_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("midnight_mortis_brick_wall", MIDNIGHT_MORTIS_BRICK_WALL, ItemGroup.DECORATIONS);

        createBlock("stripped_asphodel_log", STRIPPED_ASPHODEL_LOG, ItemGroup.BUILDING_BLOCKS);
        createBlock("asphodel_log", ASPHODEL_LOG, ItemGroup.BUILDING_BLOCKS);
        createBlock("asphodel_wood", ASPHODEL_WOOD, ItemGroup.BUILDING_BLOCKS);
        createBlock("stripped_asphodel_wood", STRIPPED_ASPHODEL_WOOD, ItemGroup.BUILDING_BLOCKS);
        createBlock("asphodel_planks", ASPHODEL_PLANKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("asphodel_slab", ASPHODEL_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("asphodel_stairs", ASPHODEL_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("asphodel_fence", ASPHODEL_FENCE, ItemGroup.DECORATIONS);
        createBlock("asphodel_fence_gate", ASPHODEL_FENCE_GATE, ItemGroup.REDSTONE);
        createBlock("asphodel_button", ASPHODEL_BUTTON, ItemGroup.REDSTONE);
        createBlock("asphodel_pressure_plate", ASPHODEL_PRESSURE_PLATE, ItemGroup.REDSTONE);
        ASPHODEL_DOOR = createBlock("asphodel_door", new DoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
        ASPHODEL_TRAPDOOR = createBlock("asphodel_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
        createBlock("asphodel_sapling", ASPHODEL_SAPLING, ItemGroup.DECORATIONS);
        createBlock("potted_asphodel_sapling", POTTED_ASPHODEL_SAPLING, null);

        createBlock("stripped_ebony_log", STRIPPED_EBONY_LOG, ItemGroup.BUILDING_BLOCKS);
        createBlock("ebony_log", EBONY_LOG, ItemGroup.BUILDING_BLOCKS);
        STRIPPED_EBONY_BRANCH = createBlock("stripped_ebony_branch", new PostBlock(null, FabricBlockSettings.of(Material.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);
        EBONY_BRANCH = createBlock("ebony_branch", new PostBlock(STRIPPED_EBONY_BRANCH, FabricBlockSettings.copy(STRIPPED_EBONY_BRANCH)), ItemGroup.DECORATIONS);
        createBlock("ebony_wood", EBONY_WOOD, ItemGroup.BUILDING_BLOCKS);
        createBlock("stripped_ebony_wood", STRIPPED_EBONY_WOOD, ItemGroup.BUILDING_BLOCKS);
        createBlock("ebony_planks", EBONY_PLANKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("ebony_slab", EBONY_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("ebony_stairs", EBONY_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("ebony_fence", EBONY_FENCE, ItemGroup.DECORATIONS);
        createBlock("ebony_fence_gate", EBONY_FENCE_GATE, ItemGroup.REDSTONE);
        createBlock("ebony_button", EBONY_BUTTON, ItemGroup.REDSTONE);
        createBlock("ebony_pressure_plate", EBONY_PRESSURE_PLATE, ItemGroup.REDSTONE);
        EBONY_DOOR = createBlock("ebony_door", new DoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
        EBONY_TRAPDOOR = createBlock("ebony_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR)), ItemGroup.REDSTONE);
        EBONY_LEAVES = createBlock("ebony_leaves", new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque().strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).allowsSpawning(BlockRegistry::canSpawnOnLeaves).suffocates(BlockRegistry::never).blockVision(BlockRegistry::never)), ItemGroup.DECORATIONS);
        BLOOD_EBONY_LEAVES = createBlock("blood_ebony_leaves", new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque().strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).allowsSpawning(BlockRegistry::canSpawnOnLeaves).suffocates(BlockRegistry::never).blockVision(BlockRegistry::never)), ItemGroup.DECORATIONS);
        createBlock("ebony_sapling", EBONY_SAPLING, ItemGroup.DECORATIONS);
        createBlock("potted_ebony_sapling", POTTED_EBONY_SAPLING, null);
        createBlock("blood_ebony_sapling", BLOOD_EBONY_SAPLING, ItemGroup.DECORATIONS);
        createBlock("potted_blood_ebony_sapling", POTTED_BLOOD_EBONY_SAPLING, null);
        HANGING_EBONY_LEAVES = createBlock("hanging_ebony_leaves", new HangingLeavesBlock(FabricBlockSettings.copy(EBONY_LEAVES)), ItemGroup.BUILDING_BLOCKS);
        HANGING_BLOOD_EBONY_LEAVES = createBlock("hanging_blood_ebony_leaves", new HangingLeavesBlock(FabricBlockSettings.copy(BLOOD_EBONY_LEAVES)), ItemGroup.BUILDING_BLOCKS);

        createBlock("petrified_sand", PETRIFIED_SAND, ItemGroup.BUILDING_BLOCKS);
        createBlock("petrified_sandstone", PETRIFIED_SANDSTONE, ItemGroup.BUILDING_BLOCKS);
        createBlock("petrified_sandstone_slab", PETRIFIED_SANDSTONE_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("petrified_sandstone_stairs", PETRIFIED_SANDSTONE_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("petrified_sandstone_wall", PETRIFIED_SANDSTONE_WALL, ItemGroup.DECORATIONS);

        createBlock("tenebrite", TENEBRITE, ItemGroup.BUILDING_BLOCKS);
        createBlock("tenebrite_slab", TENEBRITE_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("tenebrite_stairs", TENEBRITE_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("tenebrite_wall", TENEBRITE_WALL, ItemGroup.DECORATIONS);
        createBlock("tenebrite_bricks", TENEBRITE_BRICKS, ItemGroup.BUILDING_BLOCKS);
        createBlock("tenebrite_brick_slab", TENEBRITE_BRICK_SLAB, ItemGroup.BUILDING_BLOCKS);
        createBlock("tenebrite_brick_stairs", TENEBRITE_BRICK_STAIRS, ItemGroup.BUILDING_BLOCKS);
        createBlock("tenebrite_brick_wall", TENEBRITE_BRICK_WALL, ItemGroup.DECORATIONS);
        createBlock("chiseled_tenebrite_bricks", CHISELED_TENEBRITE_BRICKS, ItemGroup.BUILDING_BLOCKS);
        SMOOTH_TENEBRITE = createBlock("smooth_tenebrite", new Block(FabricBlockSettings.copy(BlockRegistry.TENEBRITE)), ItemGroup.BUILDING_BLOCKS);
        GILDED_TENEBRITE = createBlock("gilded_tenebrite", new Block(FabricBlockSettings.copy(BlockRegistry.TENEBRITE)), ItemGroup.BUILDING_BLOCKS);
        SKULLISH_TENEBRITE = createBlock("skullish_tenebrite", new Block(FabricBlockSettings.copy(BlockRegistry.TENEBRITE)), ItemGroup.BUILDING_BLOCKS);

        RED_MOSS_CARPET = createBlock("red_moss_carpet", new CarpetBlock(AbstractBlock.Settings.of(Material.PLANT, MapColor.DARK_RED).strength(0.1F).sounds(BlockSoundGroup.MOSS_CARPET)), ItemGroup.DECORATIONS);
        RED_MOSS_BLOCK = createBlock("red_moss_block", new RedMossBlock(AbstractBlock.Settings.of(Material.MOSS_BLOCK, MapColor.DARK_RED).strength(0.1F).sounds(BlockSoundGroup.MOSS_BLOCK)), ItemGroup.DECORATIONS);
        BLOOD_MOREL = createBlock("blood_morel", new BloodMorelBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).strength(0.2F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);
        DEATH_MOREL = createBlock("death_morel", new DeathMorelBlock(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).strength(0.2F).sounds(BlockSoundGroup.WOOD)), ItemGroup.DECORATIONS);

        HALLSTONE_EMERALD_ORE = createBlock("hallstone_emerald_ore", new OreBlock(FabricBlockSettings.copy(HALLSTONE).requiresTool().strength(3.0F, 7.5F), UniformIntProvider.create(3, 7)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_EMERALD_ORE = createBlock("tenebrite_emerald_ore", new OreBlock(FabricBlockSettings.copy(TENEBRITE).requiresTool().strength(3.0F, 7.5F), UniformIntProvider.create(3, 7)), ItemGroup.BUILDING_BLOCKS);
        SILVER_ORE = createBlock("silver_ore", new OreBlock(FabricBlockSettings.copy(HALLSTONE).requiresTool().strength(3.0F, 7.5F)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_SILVER_ORE = createBlock("tenebrite_silver_ore", new OreBlock(FabricBlockSettings.copy(TENEBRITE).requiresTool().strength(3.0F, 7.5F)), ItemGroup.BUILDING_BLOCKS);
        OPAL_ORE = createBlock("opal_ore", new OreBlock(FabricBlockSettings.copy(HALLSTONE).requiresTool().strength(3.0F, 7.5F), UniformIntProvider.create(3, 7)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_OPAL_ORE = createBlock("tenebrite_opal_ore", new OreBlock(FabricBlockSettings.copy(TENEBRITE).requiresTool().strength(3.0F, 7.5F), UniformIntProvider.create(3, 7)), ItemGroup.BUILDING_BLOCKS);
        STYGIAN_RUIN = createBlock("stygian_ruin", new Block(FabricBlockSettings.copy(Blocks.ANCIENT_DEBRIS).requiresTool()), ItemGroup.BUILDING_BLOCKS);
        STYGIAN_BLOCK = createBlock("stygian_block", new Block(FabricBlockSettings.copy(Blocks.NETHERITE_BLOCK).strength(40.0F, 160.0F)), ItemGroup.BUILDING_BLOCKS);

        createBlock("tall_candle", TALL_CANDLE, ItemGroup.DECORATIONS);
        createBlock("candle_skull", CANDLE_SKULL, ItemGroup.DECORATIONS);

        COBWEB_CARPET = createBlock("cobweb_carpet", new CobwebCarpetBlock(AbstractBlock.Settings.of(Material.CARPET, MapColor.WHITE).noCollision().requiresTool().breakInstantly()), ItemGroup.DECORATIONS);

        ANOINTMENT_TABLE = createBlock("anointment_table", new AnointmentTableBlock(AbstractBlock.Settings.copy(Blocks.ENCHANTING_TABLE)), ItemGroup.DECORATIONS);
        createBlock("giant_cauldron", GIANT_CAULDRON, ItemGroup.MISC);

        IGNIS_CRYSTAL_BLOCK = createBlock("ignis_crystal_block", new Block(FabricBlockSettings.copy(Blocks.AMETHYST_BLOCK)), ItemGroup.BUILDING_BLOCKS);

        CORN = createBlock("corn", new CornBlock(FabricBlockSettings.copy(Blocks.SUGAR_CANE)), null);

        flammables();
        compostables();
        fuels();
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        renderLayers();
        ColorProviderRegistry.BLOCK.register((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColors.getGrassColor(world, pos) : GrassColors.getColor(0.5D, 1.0D), CORN, DEATHBELL, BLOODBELL);
    }

    @Environment(EnvType.CLIENT)
    private static void renderLayers() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutoutMipped(),
                EBONY_LEAVES,
                BLOOD_EBONY_LEAVES
        );
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                DEADROOT,
                WICKED_WEED,
                BLOODBELL,
                DEATHBELL,
                ROSE,
                BLOOD_LILY,
                CRYSTALLIZED_BLOOD_LILY,
                CANDYSHROOM,
                ASPHODEL_SAPLING,
                POTTED_ASPHODEL_SAPLING,
                EBONY_DOOR,
                EBONY_SAPLING,
                POTTED_EBONY_SAPLING,
                BLOOD_EBONY_SAPLING,
                POTTED_BLOOD_EBONY_SAPLING,
                HANGING_EBONY_LEAVES,
                HANGING_BLOOD_EBONY_LEAVES,
                COBWEB_CARPET,
                NECROFIRE,
                NECROFIRE_CAMPFIRE,
                NECROFIRE_LANTERN,
                NECROFIRE_TORCH,
                NECROFIRE_WALL_TORCH,
                CORN
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
        FlammableBlockRegistry.getDefaultInstance().add(COBWEB_CARPET, 30, 60);
    }

    private static void compostables() {
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(DEADROOT.asItem(), 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ASPHODEL_SAPLING.asItem(), 0.2F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(EBONY_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_EBONY_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(EBONY_SAPLING.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLOOD_EBONY_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(HANGING_BLOOD_EBONY_LEAVES.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLOOD_EBONY_SAPLING.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(RED_MOSS_CARPET.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(RED_MOSS_BLOCK.asItem(), 0.65F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(BLOOD_MOREL.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(DEATH_MOREL.asItem(), 0.3F);
        ComposterBlock.ITEM_TO_LEVEL_INCREASE_CHANCE.put(ItemRegistry.CORN, .65f);
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