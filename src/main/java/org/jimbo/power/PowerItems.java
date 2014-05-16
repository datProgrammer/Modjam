package org.jimbo.power;

import org.jimbo.power.init.Items;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PowerItems.MODID, name = PowerItems.NAME, version = PowerItems.VERSION)
public class PowerItems {
	
	public static final String MODID = "poweritems";
	public static final String NAME = "Power Items";
	public static final String VERSION = "1.0";
	
	@Instance("poweritems")
	public static PowerItems instance;
	
	public boolean enabled = true;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		
		config.load();
		
		enabled = config.get(Configuration.CATEGORY_GENERAL, "EnableMod", true).getBoolean(true);
		
		config.save();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		if(enabled) {
			Items.init();
		}
	}
}
