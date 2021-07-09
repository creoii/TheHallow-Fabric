package creoii.hallows.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import creoii.hallows.common.recipe.AnointingScreenHandler;
import creoii.hallows.core.Hallows;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class AnointmentTableScreen extends HandledScreen<AnointingScreenHandler> implements ScreenHandlerListener {
    private static final Identifier TEXTURE = new Identifier(Hallows.MOD_ID,"textures/gui/container/anointing.png");

    public AnointmentTableScreen(AnointingScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title);
        this.titleX = 60;
        this.titleY = 10;
    }

    protected void setup() {
    }

    protected void init() {
        super.init();
        this.setup();
        this.handler.addListener(this);
    }

    public void removed() {
        super.removed();
        this.handler.removeListener(this);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        RenderSystem.disableBlend();
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.drawTexture(matrices, i + 59, j + 20, 0, this.backgroundHeight + (this.handler.getSlot(0).hasStack() ? 0 : 16), 110, 16);
        if (this.handler.getSlot(0).hasStack() || (this.handler.getSlot(1).hasStack()) && !this.handler.getSlot(2).hasStack()) {
            this.drawTexture(matrices, i + 99, j + 45, this.backgroundWidth, 0, 28, 21);
        }
    }

    public void onPropertyUpdate(ScreenHandler handler, int property, int value) {
    }

    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
    }

    protected void drawForeground(MatrixStack matrixStack, int x, int y) {
        RenderSystem.disableBlend();
        super.drawForeground(matrixStack, x, y);
    }
}