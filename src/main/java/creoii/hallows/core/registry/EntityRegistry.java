package creoii.hallows.core.registry;

import creoii.hallows.client.render.BoatEntityRenderer;
import creoii.hallows.client.render.BroomstickEntityRenderer;
import creoii.hallows.client.render.GhostEntityRenderer;
import creoii.hallows.client.render.HauntEntityRenderer;
import creoii.hallows.client.render.MagusEntityRenderer;
import creoii.hallows.common.entity.BroomstickEntity;
import creoii.hallows.common.entity.GhostEntity;
import creoii.hallows.common.entity.HauntEntity;
import creoii.hallows.common.entity.MagusEntity;
import creoii.hallows.common.entity.base.BoatEntity;
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
    public static final EntityType<GhostEntity> GHOST = FabricEntityTypeBuilder.<GhostEntity>create(SpawnGroup.CREATURE, GhostEntity::new).dimensions(EntityDimensions.fixed(0.5F, 1.3F)).build();
    public static final EntityType<MagusEntity> MAGUS = FabricEntityTypeBuilder.<MagusEntity>create(SpawnGroup.CREATURE, MagusEntity::new).dimensions(EntityDimensions.fixed(0.8F, 2.0F)).build();
    public static final EntityType<HauntEntity> HAUNT = FabricEntityTypeBuilder.<HauntEntity>create(SpawnGroup.CREATURE, HauntEntity::new).dimensions(EntityDimensions.fixed(0.8F, 2.0F)).build();
    public static final EntityType<BroomstickEntity> BROOMSTICK = FabricEntityTypeBuilder.<BroomstickEntity>create(SpawnGroup.CREATURE, BroomstickEntity::new).dimensions(EntityDimensions.fixed(2.4F, 0.3F)).build();
    public static final EntityType<BoatEntity> BOAT = FabricEntityTypeBuilder.<BoatEntity>create(SpawnGroup.CREATURE, BoatEntity::new).dimensions(EntityDimensions.fixed(1.375F, 0.5625F)).trackedUpdateRate(10).build();

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "ghost"), GHOST);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "magus"), MAGUS);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "haunt"), HAUNT);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "broomstick"), BROOMSTICK);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "boat"), BOAT);

        FabricDefaultAttributeRegistry.register(GHOST, GhostEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MAGUS, MagusEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(HAUNT, HauntEntity.createAttributes());
    }

    public static void registerClient() {
        EntityRendererRegistry.INSTANCE.register(GHOST, GhostEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(MAGUS, MagusEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(HAUNT, HauntEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(BROOMSTICK, BroomstickEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(BOAT, BoatEntityRenderer::new);
    }
}