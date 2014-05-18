package org.jimbo.power;

import net.minecraft.item.ItemStack;

import org.jimbo.power.init.Items;
import org.jimbo.power.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = PowerItems.MODID, name = PowerItems.NAME, version = PowerItems.VERSION)
public class PowerItems {
	
	public static final String MODID = "power";
	public static final String NAME = "Power Items";
	public static final String VERSION = "1.0";
	
	@Instance("poweritems")
	public static PowerItems instance;
	
	@SidedProxy(clientSide = "org.jimbo.power.proxy.ClientProxy", serverSide = "org.jimbo.power.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.registerRenderers();
		
		Items.init();
		
		GameRegistry.addRecipe(new ItemStack(Items.lightningRod, 1), new Object[]{"N  ", " S ", "  S", 'N', net.minecraft.init.Items.nether_star, 'S', net.minecraft.init.Items.stick});
		GameRegistry.addRecipe(new ItemStack(Items.summonerRod, 1), new Object[]{"DD ", "DS ", "  S", 'D', net.minecraft.init.Blocks.diamond_block, 'S', net.minecraft.init.Items.stick});
		GameRegistry.addRecipe(new ItemStack(Items.groundZero, 1), new Object[]{"TTT", "TTT", "TTT", 'T', net.minecraft.init.Blocks.tnt});
		GameRegistry.addRecipe(new ItemStack(Items.airStrike, 1), new Object[]{"TIT", "TDT", "TIT", 'T', net.minecraft.init.Blocks.tnt, 'I', net.minecraft.init.Blocks.iron_block, 'D', net.minecraft.init.Blocks.diamond_block});
		
	}
}
