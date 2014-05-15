package org.jimbo.lock;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
	
	public static boolean containValue(File file, Vec3 value) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line = reader.readLine()) != null) {
				if(line.startsWith(convert(value)))
					return true;
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static long getValue(File file, Vec3 value) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line = reader.readLine()) != null) {
				if(line.startsWith(convert(value)))
					return Long.parseLong(line.split("=")[1]);
			}
			
			reader.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return 666L;
	}
	
	public static long getValue(File file, ItemStack stack) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line = reader.readLine()) != null) {
				if(line.startsWith(convert(stack)))
					return Long.parseLong(line.split("=")[1]);
			}
			
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return 666L;
	}
	
	public static void setValue(File file, Vec3 value, long data) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String line;
			
			while((line = reader.readLine()) != null) {
				if(line.startsWith(convert(value)))
					return;
			}
			
			writer.newLine();
			writer.write(convert(value) + "=" + data);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String convert(Vec3 value) {
		return value.xCoord + "-" + value.yCoord + "-" + value.zCoord;
	}
	
	public static String convert(ItemStack value) {
		NBTTagCompound tag = value.getTagCompound();
		
		return tag.getString("ID");
	}
}
