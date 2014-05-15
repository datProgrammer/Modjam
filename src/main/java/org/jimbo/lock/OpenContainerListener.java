package org.jimbo.lock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.PlayerOpenContainerEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class OpenContainerListener {

	@SubscribeEvent
	public void openContainerEvent(PlayerOpenContainerEvent e) {
		EntityPlayer player = e.entityPlayer;

		if (player.getHeldItem().getItem() == LockContainerMod.keyItem
				&& LockContainerMod.checker.isLocked(Util.getBlockLookingAt(player))) {
			
			Vec3 block = Util.getBlockLookingAt(player);

			long key = LockContainerMod.checker.getKey(block);
			
			KeyItem item = (KeyItem) player.getHeldItem().getItem();
			
			if(item.getKey() == key) {
				e.setCanceled(false);
				return;
			} else {
				e.setCanceled(true);
			}
		} else if(player.getHeldItem().getItem() == LockContainerMod.keyItem
				&& !LockContainerMod.checker.isLocked(Util.getBlockLookingAt(player))) {
			
		}
	}
}
