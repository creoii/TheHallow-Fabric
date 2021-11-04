package creoii.hallows.common.world.structure;

import creoii.hallows.core.Hallows;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePiecesHolder;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Random;

public class PetrifiedPyramidGenerator {
    private static final Identifier TOMB_PIECE = new Identifier(Hallows.MOD_ID, "petrified_pyramid/tomb");

    private static final Identifier[] TOPS = new Identifier[]{new Identifier(Hallows.MOD_ID, "petrified_pyramid/top/tall_corners"), new Identifier(Hallows.MOD_ID, "petrified_pyramid/top/corners"), new Identifier(Hallows.MOD_ID, "petrified_pyramid/top/short_corners")};
    private static final Identifier[] PYRAMIDS = new Identifier[]{new Identifier(Hallows.MOD_ID, "petrified_pyramid/pyramid"), new Identifier(Hallows.MOD_ID, "petrified_pyramid/tall_pyramid")};
    private static final Identifier[] INNERS = new Identifier[]{new Identifier(Hallows.MOD_ID, "petrified_pyramid/inner"), new Identifier(Hallows.MOD_ID, "petrified_pyramid/tall_inner")};

    public static void addPieces(StructureManager manager, BlockPos pos, BlockRotation rotation, StructurePiecesHolder pieces, Random random) {
        //int index = random.nextInt(PYRAMIDS.length);
        //pieces.addPiece(new Piece(manager, PYRAMIDS[index], rotation, pos));
        //pieces.addPiece(new Piece(manager, INNERS[index], rotation, pos));
//
        //BlockPos topOffset = index == 0 ? new BlockPos(8, 9, -8) : new BlockPos(-5, 12, -5);
        ////TODO: Fix offset!
        //pieces.addPiece(new Piece(manager, Util.getRandom(TOPS, random), rotation, pos.add(topOffset)));
    }

    //public static class Piece extends SimpleStructurePiece {
    //    public Piece(StructureManager manager, Identifier identifier, BlockRotation rotation, BlockPos offset) {
    //        super(StructureRegistry.PETRIFIED_PYRAMID_PIECE, 0, manager, identifier, identifier.toString(), createPlacementData(rotation, identifier), BlockPos.ORIGIN.add(offset));
    //    }
//
    //    public Piece(ServerWorld world, NbtCompound nbt) {
    //        super(StructureRegistry.PETRIFIED_PYRAMID_PIECE, nbt, world, (identifier) -> {
    //            return createPlacementData(BlockRotation.valueOf(nbt.getString("Rot")), identifier);
    //        });
    //    }
//
    //    private static StructurePlacementData createPlacementData(BlockRotation rotation, Identifier identifier) {
    //        return new StructurePlacementData().setRotation(rotation).setMirror(BlockMirror.NONE).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
    //    }
//
    //    protected void writeNbt(ServerWorld world, NbtCompound nbt) {
    //        super.writeNbt(world, nbt);
    //        nbt.putString("Rot", this.placementData.getRotation().name());
    //    }
//
    //    @Override
    //    protected void handleMetadata(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox) {
    //    }
//
    //    public boolean generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
    //        Identifier identifier = new Identifier(this.identifier);
    //        StructurePlacementData structurePlacementData = createPlacementData(this.placementData.getRotation(), identifier);
    //        BlockPos blockPos = BlockPos.ORIGIN;
    //        BlockPos blockPos2 = this.pos.add(Structure.transform(structurePlacementData, new BlockPos(3 - blockPos.getX(), 0, -blockPos.getZ())));
    //        BlockPos blockPos3 = this.pos;
    //        this.pos = this.pos.add(0, world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, blockPos2.getX(), blockPos2.getZ()) - 91, 0);
    //        boolean bl = super.generate(world, structureAccessor, chunkGenerator, random, boundingBox, chunkPos, pos);
    //        this.pos = blockPos3;
    //        return bl;
    //    }
    //}
}