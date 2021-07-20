package creoii.hallows.core.util;

import creoii.hallows.core.registry.ItemRegistry;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.condition.RandomChanceWithLootingLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class Events {
    public static void register() {
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (EntityType.WITCH.getLootTableId().equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder().rolls(ConstantLootNumberProvider.create(1)).with(ItemEntry.builder(ItemRegistry.WITCH_BREW).conditionally(RandomChanceWithLootingLootCondition.builder(0.075F, 0.01F)).apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F))));
                table.pool(poolBuilder);
            }
        });
    }
}