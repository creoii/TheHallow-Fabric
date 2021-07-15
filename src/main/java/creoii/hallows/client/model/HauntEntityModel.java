package creoii.hallows.client.model;

import creoii.hallows.common.entity.HauntEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class HauntEntityModel extends EntityModel<HauntEntity> {
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    private final ModelPart head;
    private final ModelPart rightLeg;
    private final ModelPart leftLeg;

    public HauntEntityModel(ModelPart root) {
        body = root.getChild("body");
        leftArm = root.getChild("left_arm");
        rightArm = root.getChild("right_arm");
        head = this.body.getChild("head");
        rightLeg = root.getChild("right_leg");
        leftLeg = root.getChild("left_leg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData base = new ModelData();
        ModelPartData root = base.getRoot();
        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-9.5F, -30.0F, -3.0F, 18.0F, 18.0F, 10.0F), ModelTransform.pivot(0.0F, 24.0F, 4.0F));
        root.addChild("right_arm", ModelPartBuilder.create().uv(0, 48).cuboid(-5.0F, -2.0F, -5.0F, 4.0F, 24.0F, 4.0F), ModelTransform.pivot(-8.5F, -2.0F, 2.0F));
        root.addChild("left_arm", ModelPartBuilder.create().uv(0, 48).cuboid(-5.0F, -2.0F, -5.0F, 4.0F, 24.0F, 4.0F), ModelTransform.pivot(13.5F, -2.0F, 2.0F));
        ModelPartData head = body.addChild("head", ModelPartBuilder.create().uv(0, 28).cuboid(3.5F, -10.0F, -2.0F, 10.0F, 10.0F, 10.0F), ModelTransform.pivot(-9.0F, -27.0F, -6.0F));
        head.addChild("head1", ModelPartBuilder.create().uv(30, 28).cuboid(3.5F, 0.0F, -2.0F, 10.0F, 6.0F, 4.0F), ModelTransform.NONE);
        root.addChild("right_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-6.5F, -14.0F, 0.0F, 4.0F, 14.0F, 4.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
        root.addChild("left_leg", ModelPartBuilder.create().uv(16, 48).cuboid(-4.5F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F), ModelTransform.pivot(7.0F, 12.0F, 2.0F));
        return TexturedModelData.of(base,128,128);
    }

    @Override
    public void setAngles(HauntEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.body.pitch = 0.2618F;

        this.rightArm.pitch = MathHelper.cos(limbAngle * 0.1681F + (float)Math.PI) * limbDistance * 0.25F;
        this.leftArm.pitch = MathHelper.cos(limbAngle * 0.1681F) * limbDistance * 0.25F;
        this.rightArm.roll = MathHelper.cos(limbAngle * 0.1681F + (float)Math.PI) * limbDistance * 0.2F;
        this.leftArm.roll = MathHelper.cos(limbAngle * 0.1681F) * limbDistance * 0.2F;

        this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.1681F) * 0.3F * limbDistance;
        this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.1681F + (float)Math.PI) * 0.3F * limbDistance;

        if (entity.getAttacking() != null) {
            this.rightArm.pitch = 20.0F + MathHelper.cos(limbAngle * 0.3331F + (float)Math.PI) * limbDistance * 0.25F;
            this.leftArm.pitch = 20.0F + MathHelper.cos(limbAngle * 0.3331F) * limbDistance * 0.25F;
            this.rightArm.roll = 15.0F + MathHelper.cos(limbAngle * 0.3331F + (float)Math.PI) * limbDistance * 0.2F;
            this.leftArm.roll = -15.0F - MathHelper.cos(limbAngle * 0.3331F) * limbDistance * 0.2F;
        }
    }

    @Override
    public void animateModel(HauntEntity entity, float limbAngle, float limbDistance, float tickDelta) {
        if (entity.isAngry()) {
            this.rightArm.pitch = 20.0F + MathHelper.cos(limbAngle * 0.3331F + (float)Math.PI) * limbDistance * 0.25F;
            this.leftArm.pitch = 20.0F + MathHelper.cos(limbAngle * 0.3331F) * limbDistance * 0.25F;
        }
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        rightLeg.render(matrices, vertices, light, overlay);
        leftLeg.render(matrices, vertices, light, overlay);
        body.render(matrices, vertices, light, overlay);
        rightArm.render(matrices, vertices, light, overlay);
        leftArm.render(matrices, vertices, light, overlay);
    }
}