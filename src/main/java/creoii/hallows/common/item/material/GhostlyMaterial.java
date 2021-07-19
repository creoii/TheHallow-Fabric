package creoii.hallows.common.item.material;

import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class GhostlyMaterial {
    public static class Armor implements ArmorMaterial {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return new int[]{9, 11, 12, 9}[slot.getEntitySlotId()] * 40;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return new int[]{2, 3, 4, 2}[slot.getEntitySlotId()];
        }

        @Override
        public int getEnchantability() {
            return 12;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ItemRegistry.GHOST_CLOTH);
        }

        @Override
        public String getName() {
            return "ghostly";
        }

        @Override
        public float getToughness() {
            return 0.0F;
        }

        @Override
        public float getKnockbackResistance() {
            return 1.0F;
        }
    }
}