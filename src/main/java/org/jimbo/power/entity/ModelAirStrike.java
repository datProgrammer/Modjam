package org.jimbo.power.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAirStrike extends ModelBase {
	// fields
	ModelRenderer Wing2;
	ModelRenderer Body;
	ModelRenderer Wing1;

	public ModelAirStrike() {
		textureWidth = 64;
		textureHeight = 32;

		Wing2 = new ModelRenderer(this, 0, 0);
		Wing2.addBox(0F, 0F, -6.2F, 20, 1, 6);
		Wing2.setRotationPoint(-21F, 0F, 21F);
		Wing2.setTextureSize(64, 32);
		Wing2.mirror = true;
		setRotation(Wing2, 0F, -2.356194F, 0F);
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(0F, 0F, 0F, 30, 1, 30);
		Body.setRotationPoint(0F, 0F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, -0.7853982F, 0F);
		Wing1 = new ModelRenderer(this, 0, 0);
		Wing1.addBox(0F, 0F, 0F, 20, 1, 6);
		Wing1.setRotationPoint(21F, 0F, 21F);
		Wing1.setTextureSize(64, 32);
		Wing1.mirror = true;
		setRotation(Wing1, 0F, -0.7853982F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Wing2.render(f5);
		Body.render(f5);
		Wing1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
