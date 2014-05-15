package org.jimbo.lock;

import java.io.File;

import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "lockcontainers", name = "Lock Containers", version = "1.0")
public class LockContainerMod {
	
	public static final String MODID = "lockcontainers";
	public static final String NAME = "Lock Containers";
	public static final String VERSION = "1.0";
	
	public boolean enabled = true;
	
	public static Item keyItem = new KeyItem();
	
	public static Checker checker;
	
	public static File lockFile;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		config.load();
		
		enabled = config.get(Configuration.CATEGORY_GENERAL, "EnableMod", true).getBoolean(true);
		
		config.save();
		
		checker = new Checker();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		FMLCommonHandler.instance().bus().register(new OpenContainerListener());
	}
}
