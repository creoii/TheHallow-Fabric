package creoii.hallows.client.model;

import creoii.hallows.common.entity.GhostEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class GhostEntityModel extends EntityModel<GhostEntity> {
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart head;

    public GhostEntityModel(ModelPart root) {
        body = root.getChild("body");
        leftArm = this.body.getChild("left_arm");
        leftArm.copyTransform(body);
        rightArm = this.body.getChild("right_arm");
        rightArm.copyTransform(body);
        head = root.getChild("head");
        head.pivotX = 0.0F;
        head.pivotY = 3.1416F;
        head.pivotZ = 0.0F;
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData base = new ModelData();
        ModelPartData root = base.getRoot();
        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(0,17).cuboid(-4.0F, -2.0F, -2.0F, 7.0F, 10.0F, 6.0F), ModelTransform.pivot(0.0F, 14.0F, -1.0F));
        ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0,0).cuboid(-4.0F, -9.0F, -2.0F, 9.0F, 9.0F, 8.0F), ModelTransform.pivot(0.0F, 12.0F, -1.0F));
        body.addChild("left_arm", ModelPartBuilder.create().uv(7, 20).cuboid(-3.0F, -1.5F, -1.5F, 3.0F, 8.0F, 3.0F), ModelTransform.pivot(-4.0F, 0.5F, 0.5F));
        body.addChild("right_arm", ModelPartBuilder.create().uv(7, 20).cuboid(0.0F, -1.5F, -1.5F, 3.0F, 8.0F, 3.0F), ModelTransform.pivot(3.0F, 0.5F, 0.5F));
        body.addChild("body_cloth", ModelPartBuilder.create().uv(26, 0).cuboid(-4.0F, 8.0F, -2.0F, 7.0F, 2.0F, 6.0F), ModelTransform.NONE);
        head.addChild("head_cloth", ModelPartBuilder.create().uv(18, 25).cuboid(-3.0F, 0.0F, -2.0F, 7.0F, 4.0F, 8.0F), ModelTransform.NONE);
        return TexturedModelData.of(base,64,64);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay);
        head.render(matrices, vertices, light, overlay);
    }

    @Override
    public void setAngles(GhostEntity ghost, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        CrossbowPosing.meleeAttack(this.leftArm, this.rightArm, true, this.handSwingProgress, animationProgress);

        this.rightArm.pivotX = MathHelper.cos(limbAngle * 0.3331F + (float)Math.PI) * limbDistance * 0.5F;
        this.leftArm.pivotX = MathHelper.cos(limbAngle * 0.3331F) * limbDistance * 0.5F;
        this.rightArm.pivotZ = MathHelper.cos(limbAngle * 0.3331F + (float)Math.PI) * limbDistance * 0.25F;
        this.leftArm.pivotZ = MathHelper.cos(limbAngle * 0.3331F) * limbDistance * 0.25F;

        if (ghost.isCharging()) {
            this.rightArm.pivotX = 37.5F + MathHelper.cos(limbAngle * 0.3331F + (float)Math.PI) * limbDistance * 0.375F;
            this.leftArm.pivotX = 37.5F + MathHelper.cos(limbAngle * 0.3331F) * limbDistance * 0.375F;
            this.rightArm.pivotZ = 17.5F + MathHelper.cos(limbAngle * 0.3331F + (float)Math.PI) * limbDistance * 0.375F;
            this.leftArm.pivotZ = -17.5F - MathHelper.cos(limbAngle * 0.3331F) * limbDistance * 0.375F;
        }
    }
}