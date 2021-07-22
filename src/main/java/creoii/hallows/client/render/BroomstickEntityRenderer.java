package creoii.hallows.client.render;

import creoii.hallows.client.model.BroomstickEntityModel;
import creoii.hallows.common.entity.BroomstickEntity;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.util.ModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3f;

@Environment(EnvType.CLIENT)
public class BroomstickEntityRenderer extends EntityRenderer<BroomstickEntity> {
    private final EntityRendererFactory.Context context;
    private static final Identifier TEXTURE = new Identifier(Hallows.MOD_ID, "textures/entity/broomstick.png");

    public BroomstickEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.context = context;
        this.shadowRadius = 0.3F;
    }

    public void render(BroomstickEntity entity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        matrixStack.push();
        matrixStack.translate(0.0D, 0.375D, 0.0D);
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(180.0F - f));
        BroomstickEntityModel model = new BroomstickEntityModel(this.context.getPart(ModelLayers.BROOMSTICK));
        matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(90.0F));
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(TEXTURE));
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();
        super.render(entity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(BroomstickEntity entity) {
        return TEXTURE;
    }
}