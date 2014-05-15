package org.jimbo.lock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
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
	
	public static String convert(Vec3 value) {
		return value.xCoord + "-" + value.yCoord + "-" + value.zCoord;
	}
}
