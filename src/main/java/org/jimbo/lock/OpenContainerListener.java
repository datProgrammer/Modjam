package org.jimbo.lock;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
						
			if(LockContainerMod.checker.getKey(player.getHeldItem()) == key) {
				e.setCanceled(false);
				return;
			} else {
				e.setCanceled(true);
			}
		} else if(player.getHeldItem().getItem() == LockContainerMod.keyItem
				&& !LockContainerMod.checker.isLocked(Util.getBlockLookingAt(player))) {
			ItemStack stack = player.getHeldItem();
			
			NBTTagCompound tag = stack.getTagCompound();
			
			if(tag.getString("ID") == null) {
				Random rand = new Random();
				
				rand.setSeed(player.worldObj.getSeed());
				
				long key = rand.nextLong();
				long id = rand.nextLong();
				
				tag.setLong("Key", key);
				tag.setString("ID", Long.toString(id));
			}
		}
	}
}
