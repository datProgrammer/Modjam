package org.jimbo.power.explosion;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class ExplosionAdv extends Explosion {
	
	public ExplosionAdv(World world, EntityPlayer player, double x, double y, double z, float size) {
		super(world, player, x, y, z, size);
	}
}
