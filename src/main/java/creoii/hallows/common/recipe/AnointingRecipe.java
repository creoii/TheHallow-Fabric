package creoii.hallows.common.recipe;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import creoii.hallows.core.registry.RecipeRegistry;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class AnointingRecipe implements Recipe<Inventory> {
    private final Identifier id;
    private final Ingredient base;
    private final Ingredient anointment;
    private final Ingredient brew;
    private final ItemStack result;
    private final EntityAttribute attribute;

    public AnointingRecipe(Identifier id, Ingredient base, Ingredient anointment, Ingredient brew, ItemStack result, EntityAttribute attribute) {
        this.id = id;
        this.base = base;
        this.anointment = anointment;
        this.brew = brew;
        this.result = result;
        this.attribute = attribute;
    }

    public Ingredient getBase() {
        return this.base;
    }

    public EntityAttribute getAttribute() {
        return this.attribute;
    }

    @Override
    public ItemStack getOutput() {
        return this.result;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public boolean matches(Inventory inventory, World world) {
        if (inventory.size() < 3) return false;
        return base.test(inventory.getStack(0)) && anointment.test(inventory.getStack(1)) && brew.test(inventory.getStack(2));
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.getOutput().copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeRegistry.ANOINTING_TYPE;
    }

    public boolean testAnointment(ItemStack stack) {
        return this.anointment.test(stack);
    }

    public static class Type implements RecipeType<AnointingRecipe> {
    }

    public static class Serializer implements RecipeSerializer<AnointingRecipe> {
        public Serializer() {
        }

        public AnointingRecipe read(Identifier identifier, JsonObject json) {
            Ingredient base = Ingredient.fromJson(JsonHelper.getObject(json, "base"));
            Ingredient anointment = Ingredient.fromJson(JsonHelper.getObject(json, "anointment"));
            ItemStack itemStack = ShapedRecipe.outputFromJson(JsonHelper.getObject(json, "result"));
            EntityAttribute attribute = deserializeAttribute(JsonHelper.getObject(json, "attribute"));
            return new AnointingRecipe(identifier, base, anointment, Ingredient.ofStacks(new ItemStack(Items.HONEY_BOTTLE)), itemStack, attribute);
        }

        public AnointingRecipe read(Identifier identifier, PacketByteBuf buffer) {
            Ingredient base = Ingredient.fromPacket(buffer);
            Ingredient anointment = Ingredient.fromPacket(buffer);
            ItemStack itemStack = buffer.readItemStack();
            EntityAttribute attribute = Registry.ATTRIBUTE.get(new Identifier(buffer.readString()));
            return new AnointingRecipe(identifier, base, anointment, Ingredient.ofStacks(new ItemStack(Items.HONEY_BOTTLE)), itemStack, attribute);
        }

        public void write(PacketByteBuf packetByteBuf, AnointingRecipe recipe) {
            recipe.base.write(packetByteBuf);
            recipe.anointment.write(packetByteBuf);
            packetByteBuf.writeItemStack(recipe.result);
        }

        public static EntityAttribute deserializeAttribute(JsonObject json) {
            String s = JsonHelper.getString(json, "attribute");

            EntityAttribute attribute = Registry.ATTRIBUTE.getOrEmpty(new Identifier(s)).orElseThrow(() -> new JsonSyntaxException("Unknown attribute '" + s + "'"));

            if (attribute == null)
                throw new JsonSyntaxException("Unknown attribute '" + s + "'");

            else return attribute;
        }
    }
}