package creoii.hallows.core.registry;

import creoii.hallows.common.entity.base.BoatEntity;
import creoii.hallows.common.item.BroomstickItem;
import creoii.hallows.common.item.NecroflameBottleItem;
import creoii.hallows.common.item.NecromancersBladeItem;
import creoii.hallows.common.item.base.BoatItem;
import creoii.hallows.common.item.base.MiningToolItem;
import creoii.hallows.common.item.material.GhostlyMaterial;
import creoii.hallows.common.item.material.NecromancerMaterial;
import creoii.hallows.common.item.material.SilverMaterial;
import creoii.hallows.common.item.material.StygianMaterial;
import creoii.hallows.core.Hallows;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {
    public static final Item STYGIAN_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item STYGIAN_HELMET = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_CHESTPLATE = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_LEGGINGS = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_BOOTS = new ArmorItem(Materials.STYGIAN_ARMOR, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_SWORD = new SwordItem(Materials.STYGIAN_TOOLS, 3, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item STYGIAN_PICKAXE = new MiningToolItem(1.0F, -2.8F, Materials.STYGIAN_TOOLS, BlockTags.PICKAXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item STYGIAN_AXE = new MiningToolItem(5.0F, -3.0F, Materials.STYGIAN_TOOLS, BlockTags.AXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item STYGIAN_SHOVEL = new MiningToolItem(1.5F, -3.0F, Materials.STYGIAN_TOOLS, BlockTags.SHOVEL_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item STYGIAN_HOE = new MiningToolItem(-4.0F, 0.0F, Materials.STYGIAN_TOOLS, BlockTags.HOE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));

    public static final Item WITCH_BREW = new Item(new Item.Settings().group(ItemGroup.BREWING));

    public static final Item SILVER_INGOT = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item SILVER_HELMET = new ArmorItem(Materials.SILVER_ARMOR, EquipmentSlot.HEAD, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SILVER_CHESTPLATE = new ArmorItem(Materials.SILVER_ARMOR, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SILVER_LEGGINGS = new ArmorItem(Materials.SILVER_ARMOR, EquipmentSlot.LEGS, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SILVER_BOOTS = new ArmorItem(Materials.SILVER_ARMOR, EquipmentSlot.FEET, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SILVER_SWORD = new SwordItem(Materials.SILVER_TOOLS, 3, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item SILVER_PICKAXE = new MiningToolItem(1.0F, -2.8F, Materials.SILVER_TOOLS, BlockTags.PICKAXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item SILVER_AXE = new MiningToolItem(6.0F, -3.0F, Materials.SILVER_TOOLS, BlockTags.AXE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item SILVER_SHOVEL = new MiningToolItem(1.5F, -3.0F, Materials.SILVER_TOOLS, BlockTags.SHOVEL_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));
    public static final Item SILVER_HOE = new MiningToolItem(-1.0F, -2.0F, Materials.SILVER_TOOLS, BlockTags.HOE_MINEABLE, new Item.Settings().group(ItemGroup.TOOLS));

    public static final Item OPAL = new Item(new Item.Settings().group(ItemGroup.MATERIALS));

    public static final Item GHOST_CLOTH = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item GHOSTLY_DRAPE = new ArmorItem(Materials.GHOSTLY_ARMOR, EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT));
    public static final Item HAUNT_FUR = new Item(new Item.Settings().group(ItemGroup.MATERIALS));
    public static final Item MAGE_MAGIC = new Item(new Item.Settings().group(ItemGroup.MATERIALS).maxCount(16));

    public static final Item GHOST_SPAWN_EGG = new SpawnEggItem(EntityRegistry.GHOST, 13159139, 9079978, new Item.Settings().group(ItemGroup.MISC));
    public static final Item MAGUS_SPAWN_EGG = new SpawnEggItem(EntityRegistry.MAGUS, 12609675, 3293539, new Item.Settings().group(ItemGroup.MISC));
    public static final Item HAUNT_SPAWN_EGG = new SpawnEggItem(EntityRegistry.HAUNT, 4275275, 2827571, new Item.Settings().group(ItemGroup.MISC));
    public static final Item BROOMSTICK = new BroomstickItem(new Item.Settings().group(ItemGroup.MISC));

    public static final Item ASPHODEL_BOAT = new BoatItem(BoatEntity.Type.ASPHODEL, new Item.Settings().group(ItemGroup.TRANSPORTATION));
    public static final Item EBONY_BOAT = new BoatItem(BoatEntity.Type.EBONY, new Item.Settings().group(ItemGroup.TRANSPORTATION));

    public static final Item NECROMANCERS_BLADE = new NecromancersBladeItem(Materials.NECROMANCER_TOOLS, new Item.Settings().group(ItemGroup.COMBAT).maxCount(1).rarity(Rarity.UNCOMMON), 8);

    public static final Item CORN = new BlockItem(BlockRegistry.CORN, new Item.Settings().group(ItemGroup.FOOD));

    public static final Item NECROFLAME_BOTTLE = new NecroflameBottleItem(new Item.Settings().group(ItemGroup.COMBAT));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "stygian_ingot"), STYGIAN_INGOT);
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
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_ingot"), SILVER_INGOT);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_helmet"), SILVER_HELMET);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_chestplate"), SILVER_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_leggings"), SILVER_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_boots"), SILVER_BOOTS);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_sword"), SILVER_SWORD);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_pickaxe"), SILVER_PICKAXE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_axe"), SILVER_AXE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_shovel"), SILVER_SHOVEL);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "silver_hoe"), SILVER_HOE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "opal"), OPAL);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "ghost_cloth"), GHOST_CLOTH);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "ghostly_drape"), GHOSTLY_DRAPE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "haunt_fur"), HAUNT_FUR);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "mage_magic"), MAGE_MAGIC);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "ghost_spawn_egg"), GHOST_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "magus_spawn_egg"), MAGUS_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "haunt_spawn_egg"), HAUNT_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "broomstick"), BROOMSTICK);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "asphodel_boat"), ASPHODEL_BOAT);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "ebony_boat"), EBONY_BOAT);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "necromancers_blade"), NECROMANCERS_BLADE);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "corn"), CORN);
        Registry.register(Registry.ITEM, new Identifier(Hallows.MOD_ID, "necroflame_bottle"), NECROFLAME_BOTTLE);
    }

    public static class Materials {
        public static final ToolMaterial STYGIAN_TOOLS = new StygianMaterial.Tool();
        public static final ArmorMaterial STYGIAN_ARMOR = new StygianMaterial.Armor();
        public static final ToolMaterial SILVER_TOOLS = new SilverMaterial.Tool();
        public static final ArmorMaterial SILVER_ARMOR = new SilverMaterial.Armor();
        public static final ArmorMaterial GHOSTLY_ARMOR = new GhostlyMaterial.Armor();
        public static final ToolMaterial NECROMANCER_TOOLS = new NecromancerMaterial.Tool();
    }
}