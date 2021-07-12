package creoii.hallows.core.registry;

import creoii.hallows.client.render.GhostEntityRenderer;
import creoii.hallows.common.entity.GhostEntity;
import creoii.hallows.core.Hallows;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    public static EntityType<GhostEntity> GHOST;

    public static void register() {
        GHOST = Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "ghost"), FabricEntityTypeBuilder.<GhostEntity>create(SpawnGroup.CREATURE, GhostEntity::new).dimensions(EntityDimensions.fixed(0.5F, 1.3F)).build());

        FabricDefaultAttributeRegistry.register(GHOST, GhostEntity.createGhostAttributes());
    }

    public static void registerClient() {
        EntityRendererRegistry.INSTANCE.register(GHOST, GhostEntityRenderer::new);
    }
}