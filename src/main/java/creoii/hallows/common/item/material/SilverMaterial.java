package creoii.hallows.common.item.material;

import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class SilverMaterial {
    public static class Tool implements ToolMaterial {
        @Override
        public int getDurability() {
            return 185;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 12.0F;
        }

        @Override
        public float getAttackDamage() {
            return 3.0F;
        }

        @Override
        public int getMiningLevel() {
            return 1;
        }

        @Override
        public int getEnchantability() {
            return 12;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ItemRegistry.SILVER_INGOT);
        }
    }

    public static class Armor implements ArmorMaterial {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getEntitySlotId()] * 10;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return new int[]{2, 5, 6, 2}[slot.getEntitySlotId()];
        }

        @Override
        public int getEnchantability() {
            return 27;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_GOLD;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(ItemRegistry.SILVER_INGOT);
        }

        @Override
        public String getName() {
            return "silver";
        }

        @Override
        public float getToughness() {
            return 0.0F;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.0F;
        }
    }
}