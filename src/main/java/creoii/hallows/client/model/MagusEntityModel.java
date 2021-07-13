package creoii.hallows.client.model;

import creoii.hallows.common.entity.MagusEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class MagusEntityModel extends EntityModel<MagusEntity> {
    private final ModelPart body;
    private final ModelPart leftArm;
    private final ModelPart rightArm;
    private final ModelPart head;
    private final ModelPart hat;

    public MagusEntityModel(ModelPart root) {
        body = root.getChild("body");
        leftArm = this.body.getChild("left_arm");
        rightArm = this.body.getChild("right_arm");
        head = root.getChild("head");
        hat = this.head.getChild("hat");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData base = new ModelData();
        ModelPartData root = base.getRoot();
        ModelPartData body = root.addChild("body", ModelPartBuilder.create().uv(48, 8).cuboid(-2.5F, 6.0F, -2.5F, 5.0F, 3.0F, 5.0F), ModelTransform.pivot(0.0F, 14.0F, -1.0F));
        ModelPartData body1 = body.addChild("body1", ModelPartBuilder.create().uv(48, 0).cuboid(-3.0F, 9.0F, -3.0F, 6.0F, 2.0F, 6.0F), ModelTransform.NONE);
        ModelPartData body2 = body1.addChild("body2", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, 11.0F, -2.0F, 4.0F, 5.0F, 4.0F), ModelTransform.NONE);
        body2.addChild("body3", ModelPartBuilder.create().uv(40, 50).cuboid(-1.0F, 16.0F, -1.0F, 2.0F, 8.0F, 2.0F), ModelTransform.NONE);
        ModelPartData leftArm = body.addChild("left_arm", ModelPartBuilder.create().uv(32, 50).cuboid(1.0F, 4.0F, -1.0F, 2.0F, 10.0F, 2.0F), ModelTransform.pivot(-3.0F, 4.0F, 0.0F));
        leftArm.addChild("left_arm1", ModelPartBuilder.create().uv(52, 50).cuboid(3.0F, 5.0F, 0.0F, 2.0F, 9.0F, 0.0F), ModelTransform.NONE);
        ModelPartData rightArm = body.addChild("right_arm", ModelPartBuilder.create().uv(48, 20).cuboid(-3.0F, 4.0F, -1.0F, 2.0F, 10.0F, 2.0F), ModelTransform.pivot(3.0F, 4.0F, 0.0F));
        rightArm.addChild("right_arm1", ModelPartBuilder.create().uv(48, 50).cuboid(-5.0F, 5.0F, 0.0F, 2.0F, 9.0F, 0.0F), ModelTransform.NONE);
        ModelPartData head = root.addChild("head", ModelPartBuilder.create().uv(0, 38).cuboid(-4.0F, -9.0F, -2.0F, 9.0F, 9.0F, 8.0F), ModelTransform.of(0.0F, 12.0F, -1.0F, 0.0F, 3.1416F, 0.0F));
        ModelPartData hat1 = head.addChild("hat", ModelPartBuilder.create().uv(0, 20).cuboid(-8.0F, -4.0F, -8.0F, 16.0F, 2.0F, 16.0F), ModelTransform.NONE);
        ModelPartData hat2 = hat1.addChild("hat2", ModelPartBuilder.create().uv(32, 38).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 4.0F, 8.0F), ModelTransform.NONE);
        ModelPartData hat3 = hat2.addChild("hat3", ModelPartBuilder.create().uv(0, 20).cuboid(-2.0F, -12.0F, -2.0F, 4.0F, 4.0F, 4.0F), ModelTransform.NONE);
        ModelPartData hat4 = hat3.addChild("hat4", ModelPartBuilder.create().uv(0, 9).cuboid(-1.0F, -15.0F, -1.0F, 2.0F, 3.0F, 2.0F), ModelTransform.NONE);
        hat4.addChild("hat5", ModelPartBuilder.create().uv(0, 0).cuboid(-8.0F, -2.0F, -8.0F, 16.0F, 4.0F, 16.0F), ModelTransform.NONE);
        return TexturedModelData.of(base,128,128);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        body.render(matrices, vertices, light, overlay);
        head.render(matrices, vertices, light, overlay);
    }

    @Override
    public void setAngles(MagusEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }
}