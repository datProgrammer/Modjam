package org.jimbo.lock;

import java.util.List;
import java.util.Random;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
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
		
		stack.stackTagCompound.setString("Owner", player.getDisplayName());
		stack.stackTagCompound.setInteger("Key", (int) (Math.random() * Integer.MAX_VALUE));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean var4) {
		if(stack.stackTagCompound != null) {
			String owner = stack.stackTagCompound.getString("Owner");
			int code = stack.stackTagCompound.getInteger("Key");
			
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
			String[] onlinePlayers = MinecraftServer.getServer().getAllUsernames();
			
			for(String playerName : onlinePlayers) {
				EntityPlayer otherPlayer = world.getPlayerEntityByName(playerName);
				
				NBTTagCompound tag = otherPlayer.getEntityData();
				
				if(tag.hasKey("LockedChests")) {
					int[] tagData = tag.getIntArray("LockedChests");
					
					for(int i = 0; i < tagData.length; i += 4) {
						int xCoord = tagData[i];
						int yCoord = tagData[i + 1];
						int zCoord = tagData[i + 2];
						int key = tagData[i + 3];
						
						if(xCoord == x && yCoord == y && zCoord == z) {
							
						}
					}
				}
			}
		}
		
		System.out.println("LOCKED CONTAINER");
		
		return false;
	}
}
