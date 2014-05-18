package org.jimbo.power.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.jimbo.power.PowerItems;
import org.jimbo.power.entity.ModelAirStrike;
import org.lwjgl.opengl.GL11;

public class RenderAirStrike extends Render {

	public static final ResourceLocation texture = new ResourceLocation(PowerItems.MODID + ":textures/entity/bomber.png");
	
	public ModelBase model;
	
	public RenderAirStrike(ModelAirStrike model, float var) {
		super();
		
		this.model = model;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity arg0) {
		return texture;
	}

	@Override
	public void doRender(Entity arg0, double arg1, double arg2, double arg3, float arg4, float arg5) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) arg1, (float) arg2, (float) arg3);
		
		this.bindTexture(texture);
		this.model.render(arg0, 0.0F, arg5, arg5, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
	}
}
