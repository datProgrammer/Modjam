package org.jimbo.power.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityAirStrike extends Entity {

	public EntityLivingBase placer;
	
	public int amountComplete;
	
	public EntityAirStrike(World arg0) {
		super(arg0);
		this.setSize(10.0F, 10.0F);
		this.preventEntitySpawning = true;
		this.yOffset = (this.height / 2);
	}
	
	public EntityAirStrike(World world, EntityLivingBase entity, double x, double y, double z) {
		this(world);
		
		this.setPosition(x, y, z);
		
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.placer = entity;
		this.amountComplete = 100;
		this.motionZ = -1D;
	}

	@Override
	protected void entityInit() {}
	
	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionZ = -1D;
		
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		
		if(this.amountComplete-- <= 0) {
			this.setDead();
		} else {
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
			
			this.worldObj.spawnEntityInWorld(new EntityTNTPrimed(worldObj, this.posX, this.posY - 3, this.posZ, this.placer));
		}
	}
		
	@Override
	protected boolean canTriggerWalking() {
		return false;
	}
	
	@Override
	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound arg0) {
		this.amountComplete = arg0.getInteger("Progress");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound arg0) {
		arg0.setInteger("Progress", this.amountComplete);
	}
	
	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}
}
