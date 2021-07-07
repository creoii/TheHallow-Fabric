package creoii.hallows.common.item.material;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class StygianMaterial {
    public static class Tool implements ToolMaterial {
        @Override
        public int getDurability() {
            return 2231;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 9.5F;
        }

        @Override
        public float getAttackDamage() {
            return 4.5F;
        }

        @Override
        public int getMiningLevel() {
            return 4;
        }

        @Override
        public int getEnchantability() {
            return 18;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    }

    public static class Armor implements ArmorMaterial {
        @Override
        public int getDurability(EquipmentSlot slot) {
            return new int[]{13, 15, 16, 11}[slot.getEntitySlotId()] * 40;
        }

        @Override
        public int getProtectionAmount(EquipmentSlot slot) {
            return new int[]{4, 7, 9, 4}[slot.getEntitySlotId()];
        }

        @Override
        public int getEnchantability() {
            return 18;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }

        @Override
        public String getName() {
            return "stygian";
        }

        @Override
        public float getToughness() {
            return 3.5F;
        }

        @Override
        public float getKnockbackResistance() {
            return 0.0F;
        }
    }
}