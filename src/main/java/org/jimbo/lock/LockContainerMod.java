package org.jimbo.lock;

import java.io.File;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "lock", name = "Lock Containers", version = "1.0")
public class LockContainerMod {
	
	public static final String MODID = "lock";
	public static final String NAME = "Lock Containers";
	public static final String VERSION = "1.0";
	
	public boolean enabled = true;
	
	public static Item keyItem;
	public static Item keyCast;
	
	public static File lockFile;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		config.load();
		
		enabled = config.get(Configuration.CATEGORY_GENERAL, "EnableMod", true).getBoolean(true);
		
		config.save();
		
		lockFile = new File(Minecraft.getMinecraft().mcDataDir + "/locks/locks.dat");
		
		if(!lockFile.exists()) {
			try {
				lockFile.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		keyItem = new KeyItem();
		keyCast = new KeyCast();
		
		GameRegistry.registerItem(keyItem, "keyItem");
		GameRegistry.registerItem(keyCast, "keyCast");
		GameRegistry.addRecipe(new ItemStack(keyCast, 1), new Object[]{"CCC", "GGG", "CCC", 'C', Blocks.stone, 'G', Items.gold_ingot});
		GameRegistry.addSmelting(keyCast, new ItemStack(keyItem, 1), 10.0F);
		
		//MinecraftForge.EVENT_BUS.register(new OpenContainerListener());
	}
}
