package org.jimbo.lock;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class KeyItem extends Item {
	
	public KeyItem() {
		super();
		
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(LockContainerMod.MODID + ":key");
		this.setMaxStackSize(1);
		this.setUnlocalizedName("keyItem");
	}
}
