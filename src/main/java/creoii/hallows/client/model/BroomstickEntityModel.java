package creoii.hallows.client.model;

import creoii.hallows.common.entity.BroomstickEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;

@Environment(EnvType.CLIENT)
public class BroomstickEntityModel extends SinglePartEntityModel<BroomstickEntity> {
	private final ModelPart stick;
	private final ModelPart brush1;
	private final ModelPart brush2;
	private final ModelPart brush3;
	private final ModelPart brush4;
	private final ModelPart brush5;
	private final ModelPart brush6;

	public BroomstickEntityModel(ModelPart root) {
		this.stick = root.getChild("stick");
		this.brush1 = stick.getChild("brush1");
		this.brush2 = stick.getChild("brush2");
		this.brush3 = stick.getChild("brush3");
		this.brush4 = stick.getChild("brush4");
		this.brush5 = stick.getChild("brush5");
		this.brush6 = stick.getChild("brush6");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();
		ModelPartData stick = root.addChild("stick", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -9.0F, -8.0F, 2.0F, 2.0F, 32.0F), ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		stick.addChild("brush1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -14.0F, 20.0F, 0.0F, 12.0F, 14.0F), ModelTransform.pivot(0.0F, -8.0F, 20.0F));
		stick.addChild("brush2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -9.0F, -8.0F, 2.0F, 2.0F, 32.0F), ModelTransform.of(0.0F, -8.0F, 20.0F, 0.0F, 0.1745F, 2.3562F));
		stick.addChild("brush3", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -9.0F, -8.0F, 2.0F, 2.0F, 32.0F), ModelTransform.of(0.0F, -8.0F, 20.0F, 0.0F, 0.1745F, 0.7854F));
		stick.addChild("brush4", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -9.0F, -8.0F, 2.0F, 2.0F, 32.0F), ModelTransform.of(0.0F, -8.0F, 20.0F, 0.0F, -0.1745F, 2.3562F));
		stick.addChild("brush5", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -9.0F, -8.0F, 2.0F, 2.0F, 32.0F), ModelTransform.of(0.0F, -8.0F, 20.0F, 0.0F, -0.1745F, 0.7854F));
		stick.addChild("brush6", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -9.0F, -8.0F, 2.0F, 2.0F, 32.0F), ModelTransform.of(0.0F, -8.0F, 20.0F, 0.0F, 0.0F, 1.5708F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public ModelPart getPart() {
		return this.stick;
	}

	@Override
	public void setAngles(BroomstickEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	}
}