package org.jimbo.lock;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class KeyCast extends Item {

	public KeyCast() {
		super();
		
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(LockContainerMod.MODID + ":cast");
		this.setMaxStackSize(1);
		this.setUnlocalizedName("keyCast");
	}
}
