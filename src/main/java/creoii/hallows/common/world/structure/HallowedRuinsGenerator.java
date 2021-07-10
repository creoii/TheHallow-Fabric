package creoii.hallows.common.world.structure;

import creoii.hallows.core.Hallows;
import creoii.hallows.core.registry.StructureRegistry;
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

import java.util.Random;

public class HallowedRuinsGenerator {
    private static final Identifier[] TOWERS = new Identifier[]{new Identifier(Hallows.MOD_ID, "hallowed_ruins/towers/tower_0")};
    private static final Identifier[] BRIDGES = new Identifier[]{new Identifier(Hallows.MOD_ID, "hallowed_ruins/bridge/bridge_0")};
    private static final Identifier[] BRIDGE_DECOR = new Identifier[]{new Identifier(Hallows.MOD_ID, "hallowed_ruins/decor/bridge/railing_0")};

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder pieces, Random random) {
        pieces.addPiece(new HallowedRuinsGenerator.Piece(manager, TOWERS[random.nextInt(TOWERS.length)], rotation, pos));
        BlockPos bridgePos = pos.add(3, -10, 12);
        pieces.addPiece(new HallowedRuinsGenerator.Piece(manager, BRIDGES[random.nextInt(BRIDGES.length)], rotation, bridgePos));
        pieces.addPiece(new HallowedRuinsGenerator.Piece(manager, BRIDGE_DECOR[random.nextInt(BRIDGE_DECOR.length)], rotation, bridgePos.up(10)));
    }

    public static class Piece extends SimpleStructurePiece {
        public Piece(StructureManager manager, Identifier identifier, BlockRotation rotation, BlockPos offset) {
            super(StructureRegistry.HALLOWED_RUINS_PIECE, 0, manager, identifier, identifier.toString(), createPlacementData(rotation, identifier), BlockPos.ORIGIN.add(offset));
        }

        public Piece(ServerWorld world, NbtCompound nbt) {
            super(StructureRegistry.HALLOWED_RUINS_PIECE, nbt, world, (identifier) -> {
                return createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")), identifier);
            });
        }

        private static StructurePlacementData createPlacementData(BlockRotation rotation, Identifier identifier) {
            return new StructurePlacementData().setRotation(rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
        }

        protected void writeNbt(ServerWorld world, NbtCompound nbt) {
            super.writeNbt(world, nbt);
            nbt.putString("Rot", this.placementData.getRotation().name());
        }

        @Override
        protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {
        }

        public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            Identifier identifier = new Identifier(this.identifier);
            StructurePlacementData structurePlacementData = createPlacementData(this.placementData.getRotation(), identifier);
            BlockPos blockPos = BlockPos.ORIGIN;
            BlockPos blockPos2 = this.pos.add(Structure.transform(structurePlacementData, new BlockPos(3 - blockPos.getX(), 0, -blockPos.getZ())));
            BlockPos blockPos3 = this.pos;
            this.pos = this.pos.add(0, world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockPos2.getX(), blockPos2.getZ()) - 91, 0);
            boolean bl = super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pos);
            this.pos = blockPos3;
            return bl;
        }
    }
}