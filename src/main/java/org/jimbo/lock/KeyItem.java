package org.jimbo.lock;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class KeyItem extends Item {
	
	public KeyItem() {
		super();
		
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(LockContainerMod.MODID + ":key");
		this.setMaxStackSize(1);
		this.setUnlocalizedName("keyItem");
	}
	
	@Override
	public void onCreated(ItemStack stack, World world, EntityPlayer player) {
		stack.stackTagCompound = new NBTTagCompound();
		
		Random rand = new Random();
		
		rand.setSeed(world.getSeed());
		
		stack.stackTagCompound.setString("Owner", player.getDisplayName());
		stack.stackTagCompound.setLong("Key", rand.nextLong());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean var4) {
		if(stack.stackTagCompound != null) {
			String owner = stack.stackTagCompound.getString("Owner");
			long code = stack.stackTagCompound.getLong("Key");
			
			list.add("Owner: " + owner);
			
			if(owner.equals(player.getDisplayName()))
				list.add(EnumChatFormatting.GREEN + "KeyPass: " + code);
			else
				list.add(EnumChatFormatting.RED + "KeyPass: " + EnumChatFormatting.OBFUSCATED + code);
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
		
		System.out.println("LOCKED CONTAINER");
		
		return false;
	}
}
