package creoii.hallows.core.util;

import creoii.hallows.core.Hallows;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Tags {
    public static class Blocks {
        public static final Tag.Identified<Block> BASE_STONE_HALLOW = TagRegistry.create(new Identifier(Hallows.MOD_ID, "base_stone_hallow"), BlockTags::getTagGroup);
        public static final Tag.Identified<Block> INFINIBURN_HALLOW = TagRegistry.create(new Identifier(Hallows.MOD_ID, "infiniburn_hallow"), BlockTags::getTagGroup);
        public static final Tag.Identified<Block> ASPHODEL_LOGS = TagRegistry.create(new Identifier(Hallows.MOD_ID, "asphodel_logs"), BlockTags::getTagGroup);
        public static final Tag.Identified<Block> EBONY_LOGS = TagRegistry.create(new Identifier(Hallows.MOD_ID, "ebony_logs"), BlockTags::getTagGroup);
        public static final Tag.Identified<Block> NECROFIRE_BASE_BLOCKS = TagRegistry.create(new Identifier(Hallows.MOD_ID, "necrofire_base_blocks"), BlockTags::getTagGroup);
        public static final Tag.Identified<Block> RED_MOSS_REPLACEABLE = TagRegistry.create(new Identifier(Hallows.MOD_ID, "red_moss_replaceable"), BlockTags::getTagGroup);
    }

    public static class Items {
        public static final Tag.Identified<Item> ASPHODEL_LOGS = TagRegistry.create(new Identifier(Hallows.MOD_ID, "asphodel_logs"), ItemTags::getTagGroup);
        public static final Tag.Identified<Item> EBONY_LOGS = TagRegistry.create(new Identifier(Hallows.MOD_ID, "ebony_logs"), ItemTags::getTagGroup);
        public static final Tag.Identified<Item> NECROFIRE_BASE_BLOCKS = TagRegistry.create(new Identifier(Hallows.MOD_ID, "necrofire_base_blocks"), ItemTags::getTagGroup);
    }
}