package org.jimbo.power.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import org.jimbo.power.explosion.ExplosionAdv;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGroundZero extends Entity {
	public int fuse;
	private EntityLivingBase tntPlacedBy;

	public EntityGroundZero(World paramWorld) {
		super(paramWorld);
		this.preventEntitySpawning = true;
		setSize(0.98F, 0.98F);
		this.yOffset = (this.height / 2.0F);
	}

	public EntityGroundZero(World paramWorld, double paramDouble1,
			double paramDouble2, double paramDouble3,
			EntityLivingBase paramEntityLivingBase) {
		this(paramWorld);

		setPosition(paramDouble1, paramDouble2, paramDouble3);

		float f = (float) (Math.random() * 3.141592741012573D * 2.0D);
		this.motionX = (-(float) Math.sin(f) * 0.02F);
		this.motionY = 0.2000000029802322D;
		this.motionZ = (-(float) Math.cos(f) * 0.02F);

		this.fuse = 80;

		this.prevPosX = paramDouble1;
		this.prevPosY = paramDouble2;
		this.prevPosZ = paramDouble3;
		this.tntPlacedBy = paramEntityLivingBase;
	}

	protected void entityInit() {
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		this.motionY -= 0.03999999910593033D;
		moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;
		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}
		if (this.fuse-- <= 0) {
			setDead();
			if (!this.worldObj.isRemote) {
				explode();
			}
		} else {
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 0.5D,
					this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}

	private void explode() {
		float f = 1000.0F;
		ExplosionAdv explosion = new ExplosionAdv(this.worldObj, (EntityPlayer) this.tntPlacedBy, this.posX, this.posY, this.posZ, f);
		
		for(int i = 0; i < 10; i++) {
			explosion.doExplosionA();
			explosion.doExplosionB(true);
			
			explosion = new ExplosionAdv(this.worldObj, (EntityPlayer) this.tntPlacedBy, this.posX + i, this.posY, this.posZ, f);
		}
		
		ExplosionAdv explosion2 = new ExplosionAdv(this.worldObj, (EntityPlayer) this.tntPlacedBy, this.posX, this.posY, this.posZ + 10, f);
		
		for(int i = 0; i < 10; i++) {
			explosion2.doExplosionA();
			explosion2.doExplosionB(true);
			
			explosion2 = new ExplosionAdv(this.worldObj, (EntityPlayer) this.tntPlacedBy, this.posX + i, this.posY, this.posZ + 10, f);
		}
	}

	protected void writeEntityToNBT(NBTTagCompound paramNBTTagCompound) {
		paramNBTTagCompound.setByte("Fuse", (byte) this.fuse);
	}

	protected void readEntityFromNBT(NBTTagCompound paramNBTTagCompound) {
		this.fuse = paramNBTTagCompound.getByte("Fuse");
	}

	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}

	public EntityLivingBase getTntPlacedBy() {
		return this.tntPlacedBy;
	}
}