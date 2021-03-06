package creoii.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.SingleStateFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class BlockPillarFeature extends Feature<SingleStateFeatureConfig> {
    public BlockPillarFeature(Codec<SingleStateFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<SingleStateFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();
        BlockState state = context.getConfig().state;
        if (structureWorldAccess.isAir(blockPos) && !structureWorldAccess.isAir(blockPos.up())) {
            BlockPos.Mutable mutable = blockPos.mutableCopy();
            BlockPos.Mutable mutable2 = blockPos.mutableCopy();
            boolean bl = true;
            boolean bl2 = true;
            boolean bl3 = true;
            boolean bl4 = true;

            while (structureWorldAccess.isAir(mutable)) {
                if (structureWorldAccess.isOutOfHeightLimit(mutable)) {
                    return true;
                }

                structureWorldAccess.setBlockState(mutable, state, 2);
                bl = bl && this.stopOrPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.NORTH), state);
                bl2 = bl2 && this.stopOrPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.SOUTH), state);
                bl3 = bl3 && this.stopOrPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.WEST), state);
                bl4 = bl4 && this.stopOrPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.EAST), state);
                mutable.move(Direction.DOWN);
            }

            mutable.move(Direction.UP);
            this.tryPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.NORTH), state);
            this.tryPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.SOUTH), state);
            this.tryPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.WEST), state);
            this.tryPlaceState(structureWorldAccess, random, mutable2.set(mutable, Direction.EAST), state);
            mutable.move(Direction.DOWN);
            BlockPos.Mutable mutable3 = new BlockPos.Mutable();

            for(int i = -3; i < 4; ++i) {
                for(int j = -3; j < 4; ++j) {
                    int k = MathHelper.abs(i) * MathHelper.abs(j);
                    if (random.nextInt(10) < 10 - k) {
                        mutable3.set(mutable.add(i, 0, j));
                        int l = 3;

                        while(structureWorldAccess.isAir(mutable2.set(mutable3, Direction.DOWN))) {
                            mutable3.move(Direction.DOWN);
                            --l;
                            if (l <= 0) break;
                        }

                        if (!structureWorldAccess.isAir(mutable2.set(mutable3, Direction.DOWN))) {
                            structureWorldAccess.setBlockState(mutable3, state, 2);
                        }
                    }
                }
            }

            return true;
        } else return false;
    }

    private void tryPlaceState(WorldAccess world, Random random, BlockPos pos, BlockState state) {
        if (random.nextBoolean()) world.setBlockState(pos, state, 2);
    }

    private boolean stopOrPlaceState(WorldAccess world, Random random, BlockPos pos, BlockState state) {
        if (random.nextInt(10) != 0) {
            world.setBlockState(pos, state, 2);
            return true;
        } else return false;
    }
}