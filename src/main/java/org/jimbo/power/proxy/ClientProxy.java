package org.jimbo.power.proxy;

import org.jimbo.power.entity.EntityAirStrike;
import org.jimbo.power.entity.ModelAirStrike;
import org.jimbo.power.render.RenderAirStrike;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityAirStrike.class, new RenderAirStrike(new ModelAirStrike(), 0));
	}
}
