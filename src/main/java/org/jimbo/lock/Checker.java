package org.jimbo.lock;

import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;

public class Checker {
	
	public boolean isLocked(Vec3 block) {		
		return Util.containValue(LockContainerMod.lockFile, block);
	}
	
	public long getKey(Vec3 block) {
		if(isLocked(block))
			return Util.getValue(LockContainerMod.lockFile, block);
		else
			return 666L;
	}
	
	public long getKey(ItemStack stack) {
		return Util.getValue(LockContainerMod.lockFile, stack);
	}
}
