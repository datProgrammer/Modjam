package org.jimbo.lock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

public class Util {

	public static Vec3 getBlockLookingAt(EntityPlayer player) {
		if(player.rayTrace(5, 1.0F) != null) {
			int blockX = player.rayTrace(5, 1.0F).blockX;
			int blockY = player.rayTrace(5, 1.0F).blockY;
			int blockZ = player.rayTrace(5, 1.0F).blockZ;
			
			return Vec3.createVectorHelper(blockX, blockY, blockZ);
		} else {
			return null;
		}
	}
}
