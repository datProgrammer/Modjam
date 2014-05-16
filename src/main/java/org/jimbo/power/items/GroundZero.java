package org.jimbo.power.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.jimbo.power.PowerItems;
import org.jimbo.power.entity.EntityGroundZero;

public class GroundZero extends Item {

	public GroundZero() {
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(PowerItems.NAME + ":ground_zero");
		this.setUnlocalizedName("groundZero");
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		int blockX = player.rayTrace(100, 1.0F).blockX;
		int blockY = player.rayTrace(100, 1.0F).blockY + 75;
		int blockZ = player.rayTrace(100, 1.0F).blockZ;
		
		EntityChicken chicken = new EntityChicken(world);
		
		chicken.setPosition(blockX, blockY, blockZ);
		
		world.spawnEntityInWorld(new EntityGroundZero(world, blockX, blockY, blockZ, player));
		
		stack.stackSize = 0;
		
		return stack;
	}
}
