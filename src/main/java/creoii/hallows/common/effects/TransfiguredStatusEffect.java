package creoii.hallows.common.effects;


import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PigEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.passive.PigEntity;

public class TransfiguredStatusEffect extends StatusEffect {
    private PigEntity pig;
    private final MinecraftClient client = MinecraftClient.getInstance();

    public TransfiguredStatusEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x98D982);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.setInvisible(true);
        PigEntityRenderer pigRenderer = new PigEntityRenderer(new EntityRendererFactory.Context(client.getEntityRenderDispatcher(), client.getItemRenderer(), client.getResourceManager(), client.getEntityModelLoader(), client.textRenderer));
        this.pig = EntityType.PIG.create(entity.world);
        pigRenderer.render(pig, entity.getYaw(), client.getTickDelta(), new MatrixStack(), client.getBufferBuilders().getEntityVertexConsumers(), client.getEntityRenderDispatcher().getLight(pig, client.getTickDelta()));
        super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        entity.setInvisible(false);
        if (this.pig != null) this.pig.kill();
        super.onRemoved(entity, attributes, amplifier);
    }
}