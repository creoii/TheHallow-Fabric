package creoii.hallows.core.util;

import creoii.hallows.core.Hallows;
import net.minecraft.stat.StatFormatter;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Stats {
    public static final Identifier INTERACT_WITH_ANOINTMENT_TABLE = new Identifier(Hallows.MOD_ID, "interact_with_anointment_table");

    public static void register() {
        Registry.register(Registry.CUSTOM_STAT, "interact_with_anointment_table", INTERACT_WITH_ANOINTMENT_TABLE);
        net.minecraft.stat.Stats.CUSTOM.getOrCreateStat(INTERACT_WITH_ANOINTMENT_TABLE, StatFormatter.DEFAULT);
    }
}