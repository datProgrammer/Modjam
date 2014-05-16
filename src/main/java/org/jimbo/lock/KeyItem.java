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
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
		if(stack.stackTagCompound != null) {
			Random rand = new Random();
			
			rand.setSeed(world.getSeed());
			
			if(stack.stackTagCompound.hasKey("Owner"))
				stack.stackTagCompound.setString("Owner", player.getDisplayName());
			
			if(stack.stackTagCompound.hasKey("Key"))
				stack.stackTagCompound.setLong("Key", rand.nextLong());
			
			return true;
		}
		
		return false;
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
		} else {
			stack.stackTagCompound = new NBTTagCompound();
		}
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz) {
		if(world.getTileEntity(x, y, z) != null) {
			NBTTagCompound tag = new NBTTagCompound();
			
			String owner = "";
			long key = 0L;
			
			if(stack.stackTagCompound != null) {
				owner = stack.stackTagCompound.getString("Owner");
				key = stack.stackTagCompound.getLong("Key");
			}
			
			tag.setString("Owner", owner);
			tag.setLong("Key", key);
			
			world.getTileEntity(x, y, z).writeToNBT(tag);
		}
		
		System.out.println("LOCKED CONTAINER");
		
		return false;
	}
}
