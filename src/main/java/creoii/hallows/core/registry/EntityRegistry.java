package creoii.hallows.core.registry;

import creoii.hallows.client.render.*;
import creoii.hallows.common.entity.*;
import creoii.hallows.common.entity.base.BoatEntity;
import creoii.hallows.core.Hallows;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.client.render.entity.ItemEntityRenderer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {
    public static final EntityType<GhostEntity> GHOST = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GhostEntity::new).dimensions(EntityDimensions.fixed(0.6F, 1.4F)).build();
    public static final EntityType<MagusEntity> MAGUS = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MagusEntity::new).dimensions(EntityDimensions.fixed(0.8F, 2.0F)).build();
    public static final EntityType<HauntEntity> HAUNT = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, HauntEntity::new).dimensions(EntityDimensions.fixed(1.4F, 3.0F)).build();
    public static final EntityType<BroomstickEntity> BROOMSTICK = FabricEntityTypeBuilder.<BroomstickEntity>create(SpawnGroup.MISC, BroomstickEntity::new).dimensions(EntityDimensions.fixed(2.4F, 0.3F)).build();
    public static final EntityType<BoatEntity> BOAT = FabricEntityTypeBuilder.<BoatEntity>create(SpawnGroup.MISC, BoatEntity::new).dimensions(EntityDimensions.fixed(1.375F, 0.5625F)).trackedUpdateRate(10).build();
    public static final EntityType<NecroflameBottleEntity> NECROFLAME_BOTTLE = FabricEntityTypeBuilder.<NecroflameBottleEntity>create(SpawnGroup.MISC, NecroflameBottleEntity::new).dimensions(EntityDimensions.fixed(.25f, .25f)).trackRangeBlocks(4).trackedUpdateRate(10).build();

    public static void register() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "ghost"), GHOST);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "magus"), MAGUS);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "haunt"), HAUNT);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "broomstick"), BROOMSTICK);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "boat"), BOAT);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(Hallows.MOD_ID, "necroflame_bottle"), NECROFLAME_BOTTLE);

        FabricDefaultAttributeRegistry.register(GHOST, GhostEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(MAGUS, MagusEntity.createAttributes());
        FabricDefaultAttributeRegistry.register(HAUNT, HauntEntity.createAttributes());
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        EntityRendererRegistry.register(GHOST, GhostEntityRenderer::new);
        EntityRendererRegistry.register(MAGUS, MagusEntityRenderer::new);
        EntityRendererRegistry.register(HAUNT, HauntEntityRenderer::new);
        EntityRendererRegistry.register(BROOMSTICK, BroomstickEntityRenderer::new);
        EntityRendererRegistry.register(BOAT, BoatEntityRenderer::new);
        EntityRendererRegistry.register(NECROFLAME_BOTTLE, FlyingItemEntityRenderer::new);
    }
}