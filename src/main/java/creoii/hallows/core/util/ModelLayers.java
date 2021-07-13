package creoii.hallows.core.util;

import com.google.common.collect.Maps;
import creoii.hallows.client.model.GhostEntityModel;
import creoii.hallows.client.model.MagusEntityModel;
import creoii.hallows.core.Hallows;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ModelLayers {
    public static final Map<EntityModelLayer, TexturedModelData> ENTRIES = Maps.newHashMap();

    public static final EntityModelLayer GHOST = register(new Identifier(Hallows.MOD_ID, "ghost"), "main", GhostEntityModel.getTexturedModelData());
    public static final EntityModelLayer MAGUS = register(new Identifier(Hallows.MOD_ID, "magus"), "main", MagusEntityModel.getTexturedModelData());

    public static EntityModelLayer register(Identifier id, String layer, TexturedModelData data) {
        EntityModelLayer entityModelLayer = new EntityModelLayer(id, layer);
        if (!EntityModelLayers.LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            ENTRIES.put(entityModelLayer, data);
            return entityModelLayer;
        }
    }
}