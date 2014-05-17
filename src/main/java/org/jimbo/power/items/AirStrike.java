package org.jimbo.power.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.jimbo.power.PowerItems;

public class AirStrike extends Item {

	public AirStrike() {
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(PowerItems.MODID + ":air_strike");
		this.setUnlocalizedName("airStrike");
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		int blockX = player.rayTrace(100, 1.0F).blockX;
		int blockY = player.rayTrace(100, 1.0F).blockY;
		int blockZ = player.rayTrace(100, 1.0F).blockZ;
		
		for(int i = -50; i < 50; i++) {
			world.spawnEntityInWorld(new EntityTNTPrimed(world, blockX + i, blockY + 50, blockZ + i, player));
			
			for(int j = 50; j < -50; j--) {
				world.spawnEntityInWorld(new EntityTNTPrimed(world, blockX + i, blockY + 50, blockZ + j, player));
			}
		}
		
		stack.stackSize = 0;
		
		return stack;
	}
}
