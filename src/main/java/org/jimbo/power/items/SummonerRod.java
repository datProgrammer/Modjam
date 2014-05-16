package org.jimbo.power.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import org.jimbo.power.PowerItems;

public class SummonerRod extends Item {

	public SummonerRod() {
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
		this.setTextureName(PowerItems.NAME + ":rod_summoner");
		this.setUnlocalizedName("summonerRod");
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		int blockX = player.rayTrace(100, 1.0F).blockX;
		int blockY = player.rayTrace(100, 1.0F).blockY + 1;
		int blockZ = player.rayTrace(100, 1.0F).blockZ;
		
		EntityLivingBase[] entities = new EntityLivingBase[4];
		
		ItemStack diamondSword = new ItemStack(Items.diamond_sword);
		ItemStack diamondHelmet = new ItemStack(Items.diamond_helmet);
		ItemStack diamondChestplate = new ItemStack(Items.diamond_chestplate);
		ItemStack bow = new ItemStack(Items.bow);
		
		diamondSword.addEnchantment(Enchantment.sharpness, 2);
		diamondSword.addEnchantment(Enchantment.fireAspect, 2);
		diamondHelmet.addEnchantment(Enchantment.protection, 2);
		diamondChestplate.addEnchantment(Enchantment.protection, 2);
		bow.addEnchantment(Enchantment.power, 4);
		
		entities[0] = new EntityZombie(world);
		entities[0].setPosition(blockX, blockY, blockZ);
		entities[0].setCurrentItemOrArmor(4, diamondHelmet);
		entities[0].setCurrentItemOrArmor(3, diamondChestplate);
		entities[0].setCurrentItemOrArmor(0, diamondSword);
		
		entities[1] = new EntitySkeleton(world);
		entities[1].setPosition(blockX, blockY, blockZ);
		entities[1].setCurrentItemOrArmor(4, diamondHelmet);
		entities[1].setCurrentItemOrArmor(3, diamondChestplate);
		entities[1].setCurrentItemOrArmor(0, bow);
		
		entities[2] = new EntitySpider(world);
		entities[2].setPosition(blockX, blockY, blockZ);
		
		entities[3] = new EntityBlaze(world);
		entities[3].setPosition(blockX, blockY, blockZ);
		
		world.spawnEntityInWorld(entities[0]);
		world.spawnEntityInWorld(entities[1]);
		world.spawnEntityInWorld(entities[2]);
		world.spawnEntityInWorld(entities[3]);
		
		stack.stackSize = 0;
		
		return stack;
	}
}
