package creoii.hallows.core.registry;

import creoii.hallows.client.gui.AnointmentTableScreen;
import creoii.hallows.common.recipe.AnointingRecipe;
import creoii.hallows.common.recipe.AnointingScreenHandler;
import creoii.hallows.core.Hallows;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class RecipeRegistry {
    public static final RecipeSerializer<AnointingRecipe> ANOINTING_SERIALIZER = new AnointingRecipe.Serializer();

    public static final RecipeType<AnointingRecipe> ANOINTING_TYPE = new AnointingRecipe.Type();

    public static ScreenHandlerType<AnointingScreenHandler> ANOINTING_SCREEN;

    public static void register() {
        Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(Hallows.MOD_ID, "anointing"), ANOINTING_SERIALIZER);

        Registry.register(Registry.RECIPE_TYPE, new Identifier(Hallows.MOD_ID, "anointing"), ANOINTING_TYPE);

        ANOINTING_SCREEN = ScreenHandlerRegistry.registerSimple(new Identifier(Hallows.MOD_ID, "anointment_table"), AnointingScreenHandler::new);
        ScreenRegistry.register(ANOINTING_SCREEN, AnointmentTableScreen::new);
    }
}