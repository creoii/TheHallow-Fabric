package creoii.hallows.client.render;

import creoii.hallows.client.model.MagusEntityModel;
import creoii.hallows.common.entity.MagusEntity;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.util.ModelLayers;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

@Environment(EnvType.CLIENT)
public class MagusEntityRenderer extends MobEntityRenderer<MagusEntity, MagusEntityModel> {
    private static final Identifier TEXTURE = new Identifier(Hallows.MOD_ID, "textures/entity/magus.png");

    public MagusEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new MagusEntityModel(context.getPart(ModelLayers.MAGUS)), 1.2F);
    }

    protected int getBlockLight(MagusEntity magus, BlockPos blockPos) {
        return 15;
    }

    @Override
    protected RenderLayer getRenderLayer(MagusEntity magus, boolean showBody, boolean translucent, boolean showOutline) {
        return RenderLayer.getEntityTranslucent(this.getTexture(magus));
    }

    public Identifier getTexture(MagusEntity magus) {
        return TEXTURE;
    }
}