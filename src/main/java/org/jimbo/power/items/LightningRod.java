package org.jimbo.power.items;

import org.jimbo.power.PowerItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class LightningRod extends Item {

	public LightningRod() {
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(PowerItems.MODID + ":rod_lightning");
		this.setUnlocalizedName("lightningRod");
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		int blockX = player.rayTrace(100, 1.0F).blockX;
		int blockY = player.rayTrace(100, 1.0F).blockY;
		int blockZ = player.rayTrace(100, 1.0F).blockZ;
		
		world.spawnEntityInWorld(new EntityLightningBolt(world, blockX, blockY, blockZ));
		
		stack.stackSize = 0;
		
		return stack;
	}
}
