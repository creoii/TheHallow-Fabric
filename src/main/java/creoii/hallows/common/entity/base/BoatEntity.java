package creoii.hallows.common.entity.base;

import creoii.hallows.core.registry.BlockRegistry;
import creoii.hallows.core.registry.EntityRegistry;
import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;

public class BoatEntity extends net.minecraft.entity.vehicle.BoatEntity {
    private static final TrackedData<Integer> BOAT_TYPE = DataTracker.registerData(BoatEntity.class, TrackedDataHandlerRegistry.INTEGER);

    public BoatEntity(EntityType<? extends net.minecraft.entity.vehicle.BoatEntity> entityType, World world) {
        super(entityType, world);
    }

    public BoatEntity(World world, double x, double y, double z) {
        super(EntityRegistry.BOAT, world);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    public ItemStack getPickBlockStack() {
        return switch (getBoatType1()) {
            default -> new ItemStack(Items.OAK_BOAT);
            case ASPHODEL -> new ItemStack(ItemRegistry.ASPHODEL_BOAT);
            case EBONY -> new ItemStack(ItemRegistry.EBONY_BOAT);
        };
    }

    public Type getBoatType1() {
        return Type.getType(this.dataTracker.get(BOAT_TYPE));
    }

    public void setBoatType(Type boatBYGType) {
        this.dataTracker.set(BOAT_TYPE, boatBYGType.ordinal());
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(BOAT_TYPE, Type.ASPHODEL.ordinal());
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putString("Type1", this.getBoatType().getName());
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        if (nbt.contains("Type1", 8)) {
            this.setBoatType(Type.getType(nbt.getString("Type1")));
        }
    }

    public enum Type {
        ASPHODEL(BlockRegistry.ASPHODEL_PLANKS, "asphodel"),
        EBONY(BlockRegistry.EBONY_PLANKS, "ebony"),
        THORNWOOD(null, "thornwood"),
        TWISTED(null, "twisted");

        private final String name;
        private final Block baseBlock;

        Type(Block baseBlock, String name) {
            this.name = name;
            this.baseBlock = baseBlock;
        }

        public String getName() {
            return this.name;
        }

        public Block getBaseBlock() {
            return this.baseBlock;
        }

        public String toString() {
            return this.name;
        }

        public static BoatEntity.Type getType(int type) {
            BoatEntity.Type[] types = values();
            if (type < 0 || type >= types.length) {
                type = 0;
            }

            return types[type];
        }

        public static BoatEntity.Type getType(String name) {
            BoatEntity.Type[] types = values();

            for(int i = 0; i < types.length; ++i) {
                if (types[i].getName().equals(name)) {
                    return types[i];
                }
            }

            return types[0];
        }
    }
}