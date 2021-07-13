package creoii.hallows.client.render;

import creoii.hallows.client.model.HauntEntityModel;
import creoii.hallows.common.entity.HauntEntity;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.util.ModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HauntEntityRenderer extends MobEntityRenderer<HauntEntity, HauntEntityModel> {
    private static final Identifier TEXTURE = new Identifier(Hallows.MOD_ID, "textures/entity/haunt.png");

    public HauntEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new HauntEntityModel(context.getPart(ModelLayers.HAUNT)), 1.2F);
    }

    @Override
    protected RenderLayer getRenderLayer(HauntEntity entity, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(this.getTexture(entity));
    }

    public Identifier getTexture(HauntEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(HauntEntity entity, MatrixStack matrices, float amount) {
        matrices.scale(1.4F, 1.5F, 1.4F);
    }
}