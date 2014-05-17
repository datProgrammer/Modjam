package org.jimbo.power.init;

import net.minecraft.item.Item;

import org.jimbo.power.items.AirStrike;
import org.jimbo.power.items.GroundZero;
import org.jimbo.power.items.LightningRod;
import org.jimbo.power.items.SummonerRod;

import cpw.mods.fml.common.registry.GameRegistry;

public class Items {

	public static Item lightningRod;
	public static Item summonerRod;
	public static Item groundZero;
	public static Item airStrike;
	
	public static void init() {
		lightningRod = new LightningRod();
		summonerRod = new SummonerRod();
		groundZero = new GroundZero();
		airStrike = new AirStrike();
		
		GameRegistry.registerItem(lightningRod, "lightningRod");
		GameRegistry.registerItem(summonerRod, "summonerRod");
		GameRegistry.registerItem(groundZero, "groundZero");
		GameRegistry.registerItem(airStrike, "airStrike");
	}
}
