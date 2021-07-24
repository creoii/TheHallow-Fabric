package creoii.hallows.client.render;

import creoii.hallows.common.blockentity.CandleSkullBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.client.render.entity.model.SkullEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class CandleSkullBlockEntityRenderer implements BlockEntityRenderer<CandleSkullBlockEntity> {
    private final EntityModelLoader loader;

    public CandleSkullBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        loader = ctx.getLayerRenderDispatcher();
    }

    @Override
    public void render(CandleSkullBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        BlockState state = entity.getCachedState();
        float yaw = 22.5F * (float) state.get(SkullBlock.ROTATION);
        SkullBlockEntityModel model = new SkullEntityModel(loader.getModelPart(EntityModelLayers.SKELETON_SKULL));

        matrices.push();
        matrices.translate(0.5D, 0.0D, 0.5D);
        matrices.scale(-1.0F, -1.0F, 1.0F);
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutoutNoCullZOffset(new Identifier("textures/entity/skeleton/skeleton.png")));
        model.setHeadRotation(0.0F, yaw, 0.0F);
        model.render(matrices, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrices.pop();
    }
}