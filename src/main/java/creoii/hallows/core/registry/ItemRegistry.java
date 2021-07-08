package creoii.hallows.core.registry;

import creoii.hallows.common.item.base.MiningToolItem;
import creoii.hallows.common.item.material.StygianMaterial;
import creoii.hallows.core.Hallows;
import creoii.hallows.core.util.Tags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static final Item STYGIAN_HELMET = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_CHESTPLATE = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_LEGGINGS = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_BOOTS = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_SWORD = new SwordItem(Materials.STYGIAN_TOOLS, 3, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_PICKAXE = new MiningToolItem(1.0F, -2.8F, Materials.STYGIAN_TOOLS, BlockTags.PICKAXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item STYGIAN_AXE = new MiningToolItem(5.0F, -3.0F, Materials.STYGIAN_TOOLS, BlockTags.PICKAXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item STYGIAN_SHOVEL = new MiningToolItem(1.5F, -3.0F, Materials.STYGIAN_TOOLS, BlockTags.PICKAXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item STYGIAN_HOE = new MiningToolItem(-4.0F, 0.0F, Materials.STYGIAN_TOOLS, BlockTags.PICKAXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));

    public static final Item WITCH_BREW = new Item(new Item.Settings().group(ItemGroup.BREWING));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_helmet"), STYGIAN_HELMET);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_chestplate"), STYGIAN_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_leggings"), STYGIAN_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_boots"), STYGIAN_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_sword"), STYGIAN_SWORD);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_pickaxe"), STYGIAN_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_axe"), STYGIAN_AXE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_shovel"), STYGIAN_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_hoe"), STYGIAN_HOE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "witch_brew"), WITCH_BREW);
    }

    public static class Materials {
        public static final ToolMaterial STYGIAN_TOOLS = new StygianMaterial.Tool();
        public static final ArmorMaterial STYGIAN_ARMOR = new StygianMaterial.Armor();
    }
}