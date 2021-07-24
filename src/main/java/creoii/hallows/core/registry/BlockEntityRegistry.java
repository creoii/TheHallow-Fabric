package creoii.hallows.core.registry;

import creoii.hallows.client.render.CandleSkullBlockEntityRenderer;
import creoii.hallows.common.blockentity.CandleSkullBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

public class BlockEntityRegistry {
    public static final BlockEntityType<CandleSkullBlockEntity> CANDLE_SKULL = FabricBlockEntityTypeBuilder.create(CandleSkullBlockEntity::new, BlockRegistry.CANDLE_SKULL).build(null);

    public static void register() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, "candle_skull", CANDLE_SKULL);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        BlockEntityRendererRegistry.INSTANCE.register(CANDLE_SKULL, CandleSkullBlockEntityRenderer::new);
    }
}