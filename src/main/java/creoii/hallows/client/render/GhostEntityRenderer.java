package creoii.hallows.client.render;

import creoii.hallows.client.model.GhostEntityModel;
import creoii.hallows.common.entity.GhostEntity;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.util.ModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class GhostEntityRenderer extends MobEntityRenderer<GhostEntity, GhostEntityModel> {
    private static final Identifier TEXTURE = new Identifier(Hallows.MOD_ID, "textures/entity/ghost.png");

    public GhostEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new GhostEntityModel(context.getPart(ModelLayers.GHOST)), 0.9F);
    }

    protected int getBlockLight(GhostEntity ghost, BlockPos blockPos) {
        return 15;
    }

    @Override
    protected RenderLayer getRenderLayer(GhostEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(this.getTexture(entity));
    }

    public Identifier getTexture(GhostEntity ghost) {
        return TEXTURE;
    }

    protected void scale(GhostEntity ghost, MatrixStack matrixStack, float f) {
        matrixStack.scale(1.2F, 1.2F, 1.2F);
    }
}