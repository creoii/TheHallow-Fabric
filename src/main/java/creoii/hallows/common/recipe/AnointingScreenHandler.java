package creoii.hallows.common.recipe;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import creoii.hallows.core.registry.BlockRegistry;
import creoii.hallows.core.registry.ItemRegistry;
import creoii.hallows.core.registry.RecipeRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AnointingScreenHandler extends ScreenHandler {
    private final World world;
    private AnointingRecipe recipe;
    private final List<AnointingRecipe> recipes;
    protected final ScreenHandlerContext context;
    private final PlayerEntity player;
    protected final CraftingResultInventory output = new CraftingResultInventory();
    protected final Inventory input = new SimpleInventory(3) {
        public void markDirty() {
            super.markDirty();
            AnointingScreenHandler.this.onContentChanged(this);
        }
    };

    public AnointingScreenHandler(int id, PlayerInventory inventory) {
        this(id, inventory, ScreenHandlerContext.EMPTY);
    }

    public AnointingScreenHandler(int id, PlayerInventory inventory, ScreenHandlerContext context) {
        super(RecipeRegistry.ANOINTING_SCREEN, id);
        this.world = inventory.player.world;
        this.player = inventory.player;
        this.context = context;
        this.recipes = this.world.getRecipeManager().listAllOfType(RecipeRegistry.ANOINTING_TYPE);
        this.addSlot(new Slot(this.input, 0, 27, 39));
        this.addSlot(new Slot(this.input, 1, 76, 39));
        this.addSlot(new Slot(this.input, 2, 76, 59));
        this.addSlot(new Slot(this.output, 3, 134, 39) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public boolean canTakeItems(PlayerEntity playerEntity) {
                return AnointingScreenHandler.this.canTakeOutput();
            }

            public void onTakeItem(PlayerEntity player, ItemStack stack) {
                AnointingScreenHandler.this.onTakeOutput(player, stack);
            }
        });

        int k;
        for (k = 0; k < 3; ++k) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for (k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18, 142));
        }
    }

    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) this.updateResult();
    }

    public void close(PlayerEntity playerEntity) {
        super.close(playerEntity);
        this.context.run((world, blockPos) -> {
            this.dropInventory(playerEntity, this.input);
        });
    }

    public boolean canUse(PlayerEntity player) {
        return this.context.get((world, pos) -> world.getBlockState(pos).isOf(BlockRegistry.ANOINTMENT_TABLE) && player.squaredDistanceTo((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D, true);
    }

    protected boolean canTakeOutput() {
        return this.recipe != null && this.recipe.matches(this.input, this.world);
    }

    protected void onTakeOutput(PlayerEntity player, ItemStack stack) {
        stack.onCraft(player.world, player, stack.getCount());
        this.output.unlockLastRecipe(player);
        this.decrease(0);
        this.decrease(1);
        this.decrease(2);
        this.context.run((world, blockPos) -> {
            world.syncWorldEvent(1044, blockPos, 0);
        });
    }

    private void decrease(int index) {
        ItemStack itemstack = this.input.getStack(index);
        itemstack.decrement(1);
        this.input.setStack(index, itemstack);
    }

    public void updateResult() {
        List<AnointingRecipe> list = this.world.getRecipeManager().getAllMatches(RecipeRegistry.ANOINTING_TYPE, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            this.recipe = list.get(0);
            EntityAttribute attribute = this.recipe.getAttribute();
            if (attribute == EntityAttributes.GENERIC_FLYING_SPEED ||
                    attribute == EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS ||
                    attribute == EntityAttributes.HORSE_JUMP_STRENGTH ||
                    attribute == EntityAttributes.GENERIC_FOLLOW_RANGE) return;

            ItemStack stack = this.input.getStack(0);
            if (stack.getOrCreateTag().getBoolean("Anointed")) return;

            EquipmentSlot slot = MobEntity.getPreferredEquipmentSlot(stack);
            Multimap<EntityAttribute, EntityAttributeModifier> multimap = HashMultimap.create();
            double amount = 0;

            for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry : stack.getAttributeModifiers(slot).entries()) {
                if (entry.getKey().getTranslationKey().equals(attribute.getTranslationKey())) {
                    amount = entry.getValue().getValue();
                    amount += player.getAttributeValue(attribute);
                } else {
                    EntityAttributeModifier modifier = new EntityAttributeModifier(UUID.randomUUID(), "Anointment bonus", entry.getValue().getValue() + player.getAttributeValue(entry.getKey()), EntityAttributeModifier.Operation.ADDITION);
                    multimap.put(entry.getKey(), modifier);
                }
            }

            switch (attribute.getTranslationKey()) {
                case "attribute.name.generic.armor", "attribute.name.generic.max_health", "attribute.name.generic.armor_toughness" -> amount += 1.0D;
                case "attribute.name.generic.knockback_resistance", "attribute.name.generic.movement_speed" -> amount += 0.05D;
                case "attribute.name.generic.attack_speed" -> amount += 0.4D;
                case "attribute.name.generic.attack_knockback", "attribute.name.generic.attack_damage" -> amount += 0.5D;
                case "attribute.name.generic.luck" -> amount += 2.0D;
            }

            EntityAttributeModifier modifier = new EntityAttributeModifier(UUID.randomUUID(), "Anointment bonus", amount, EntityAttributeModifier.Operation.ADDITION);
            multimap.put(attribute, modifier);

            for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry : multimap.entries()) {
                for (Map.Entry<EntityAttribute, EntityAttributeModifier> entry1 : stack.getAttributeModifiers(slot).entries()) {
                    if (!entry.getKey().getTranslationKey().equals(entry1.getKey().getTranslationKey())) stack.addAttributeModifier(entry.getKey(), entry.getValue(), slot);
                }
            }

            stack.getOrCreateTag().putBoolean("Anointed", true);

            this.output.setLastRecipe(this.recipe);
            this.output.setStack(0, stack);
        }
    }

    protected boolean method_30025(ItemStack stack) {
        return this.recipes.stream().anyMatch((recipe) -> recipe.testAnointment(stack)) || stack.getItem() == ItemRegistry.WITCH_BREW;
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 2) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickTransfer(itemStack2, itemStack);
            } else if (index != 0 && index != 1) {
                if (index >= 3 && index < 39) {
                    int i = this.method_30025(itemStack) ? 1 : 0;
                    if (!this.insertItem(itemStack2, i, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(itemStack2, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) slot.setStack(ItemStack.EMPTY);
            else slot.markDirty();

            if (itemStack2.getCount() == itemStack.getCount()) return ItemStack.EMPTY;

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.output && super.canInsertIntoSlot(stack, slot);
    }
}