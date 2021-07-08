package creoii.hallows.common.world.structure;

import com.google.common.collect.ImmutableMap;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.registry.BlockRegistry;
import creoii.hallows.core.registry.StructureRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Map;
import java.util.Random;

public class PetrifiedPyramidGenerator {
    private static final Identifier PYRAMID_PIECE = new Identifier(Hallows.MOD_ID, "petrified_pyramid/pyramid");
    private static final Identifier INNER_PIECE = new Identifier(Hallows.MOD_ID, "petrified_pyramid/inner");
    private static final Identifier TALL_PYRAMID_PIECE = new Identifier(Hallows.MOD_ID, "petrified_pyramid/tall_pyramid");
    private static final Identifier TALL_INNER_PIECE = new Identifier(Hallows.MOD_ID, "petrified_pyramid/tall_inner");
    private static final Identifier TOMB_PIECE = new Identifier(Hallows.MOD_ID, "petrified_pyramid/tomb");
    private static final Map<Identifier, BlockPos> OFFSETS_FROM_TOP = ImmutableMap.of(PYRAMID_PIECE, BlockPos.ORIGIN, INNER_PIECE, BlockPos.ORIGIN, TALL_PYRAMID_PIECE, BlockPos.ORIGIN, TALL_INNER_PIECE, BlockPos.ORIGIN, TOMB_PIECE, new BlockPos(0, -5, 0));

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder pieces, Random random) {
        if (random.nextBoolean()) {
            pieces.addPiece(new Piece(manager, PYRAMID_PIECE, pos, rotation, 0));
            pieces.addPiece(new Piece(manager, INNER_PIECE, pos, rotation, 0));
        } else {
            pieces.addPiece(new Piece(manager, TALL_PYRAMID_PIECE, pos, rotation, 0));
            pieces.addPiece(new Piece(manager, TALL_INNER_PIECE, pos, rotation, 0));
        }
    }

    public static class Piece extends SimpleStructurePiece {
        public Piece(StructureManager manager, Identifier identifier, BlockPos pos, BlockRotation rotation, int yOffset) {
            super(StructureRegistry.PETRIFIED_PYRAMID_PIECE, 0, manager, identifier, identifier.toString(), createPlacementData(rotation, identifier), getPosOffset(identifier, pos, yOffset));
        }

        public Piece(ServerWorld world, NbtCompound nbt) {
            super(StructureRegistry.PETRIFIED_PYRAMID_PIECE, nbt, world, (identifier) -> {
                return createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")), identifier);
            });
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation, Identifier identifier) {
            return (new StructurePlacementData()).setRotation(rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
        }

        private static BlockPos getPosOffset(Identifier identifier, BlockPos pos, int yOffset) {
            return pos.add(OFFSETS_FROM_TOP.get(identifier)).down(yOffset);
        }

        protected void writeNbt(ServerWorld world, NbtCompound nbt) {
            super.writeNbt(world, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {
            if ("chest".equals(metadata)) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
                BlockEntity blockEntity = world.getBlockEntity(pos.down());
                if (blockEntity instanceof ChestBlockEntity) {
                    ((ChestBlockEntity)blockEntity).setLootTable(LootTables.IGLOO_CHEST_CHEST, random.nextLong());
                }
            }
        }

        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            Identifier identifier = new Identifier(this.identifier);
            StructurePlacementData structurePlacementData = createPlacementData(this.placementData.getRotation(), identifier);
            BlockPos blockPos = OFFSETS_FROM_TOP.get(identifier);
            BlockPos blockPos2 = this.pos.add(Structure.transform(structurePlacementData, new BlockPos(3 - blockPos.getX(), 0, -blockPos.getZ())));
            int i = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockPos2.getX(), blockPos2.getZ());
            BlockPos blockPos3 = this.pos;
            this.pos = this.pos.add(0, i - 90 - 1, 0);
            boolean bl = super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pos);
            this.pos = blockPos3;
            return bl;
        }
    }
}