package creoii.hallows.core.util;

import creoii.hallows.core.Hallows;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class Tags {
    public static class Blocks {
        public static final Tag<Block> BASE_STONE_HALLOW = TagFactory.BLOCK.create(new Identifier(Hallows.MOD_ID, "base_stone_hallow"));
        public static final Tag<Block> INFINIBURN_HALLOW = TagFactory.BLOCK.create(new Identifier(Hallows.MOD_ID, "infiniburn_hallow"));
        public static final Tag<Block> ASPHODEL_LOGS = TagFactory.BLOCK.create(new Identifier(Hallows.MOD_ID, "asphodel_logs"));
        public static final Tag<Block> EBONY_LOGS = TagFactory.BLOCK.create(new Identifier(Hallows.MOD_ID, "ebony_logs"));
        public static final Tag<Block> NECROFIRE_BASE_BLOCKS = TagFactory.BLOCK.create(new Identifier(Hallows.MOD_ID, "necrofire_base_blocks"));
        public static final Tag<Block> RED_MOSS_REPLACEABLE = TagFactory.BLOCK.create(new Identifier(Hallows.MOD_ID, "red_moss_replaceable"));
    }

    public static class Items {
        public static final Tag<Item> ASPHODEL_LOGS = TagFactory.ITEM.create(new Identifier(Hallows.MOD_ID, "asphodel_logs"));
        public static final Tag<Item> EBONY_LOGS = TagFactory.ITEM.create(new Identifier(Hallows.MOD_ID, "ebony_logs"));
        public static final Tag<Item> NECROFIRE_BASE_BLOCKS = TagFactory.ITEM.create(new Identifier(Hallows.MOD_ID, "necrofire_base_blocks"));
    }
}