package org.jimbo.lock;

import net.minecraft.item.Item;

public class KeyItem extends Item {

	private long key;
	
	public KeyItem() {
		super();
	}
	
	public long getKey() {
		return this.key;
	}
}
