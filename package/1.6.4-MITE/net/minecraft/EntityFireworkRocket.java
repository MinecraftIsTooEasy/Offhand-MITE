/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFireworkRocket
/*     */   extends Entity
/*     */ {
/*     */   private int fireworkAge;
/*     */   private int lifetime;
/*     */   
/*     */   public EntityFireworkRocket(World par1World) {
/*  15 */     super(par1World);
/*  16 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  21 */     this.dataWatcher.addObjectByDataType(8, 5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double par1) {
/*  30 */     return (par1 < 4096.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFireworkRocket(World par1World, double par2, double par4, double par6, ItemStack par8ItemStack) {
/*  35 */     super(par1World);
/*  36 */     this.fireworkAge = 0;
/*  37 */     setSize(0.25F, 0.25F);
/*  38 */     setPosition(par2, par4, par6);
/*  39 */     this.yOffset = 0.0F;
/*  40 */     int var9 = 1;
/*     */     
/*  42 */     if (par8ItemStack != null && par8ItemStack.hasTagCompound()) {
/*     */       
/*  44 */       this.dataWatcher.updateObject(8, par8ItemStack);
/*  45 */       NBTTagCompound var10 = par8ItemStack.getTagCompound();
/*  46 */       NBTTagCompound var11 = var10.getCompoundTag("Fireworks");
/*     */       
/*  48 */       if (var11 != null)
/*     */       {
/*  50 */         var9 += var11.getByte("Flight");
/*     */       }
/*     */     } 
/*     */     
/*  54 */     this.motionX = this.rand.nextGaussian() * 0.001D;
/*  55 */     this.motionZ = this.rand.nextGaussian() * 0.001D;
/*  56 */     this.motionY = 0.05D;
/*  57 */     this.lifetime = 10 * var9 + this.rand.nextInt(6) + this.rand.nextInt(7);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double par1, double par3, double par5) {
/*  65 */     this.motionX = par1;
/*  66 */     this.motionY = par3;
/*  67 */     this.motionZ = par5;
/*     */     
/*  69 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/*  71 */       float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
/*  72 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/*  73 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, var7) * 180.0D / Math.PI);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  82 */     this.lastTickPosX = this.posX;
/*  83 */     this.lastTickPosY = this.posY;
/*  84 */     this.lastTickPosZ = this.posZ;
/*  85 */     super.onUpdate();
/*  86 */     this.motionX *= 1.15D;
/*  87 */     this.motionZ *= 1.15D;
/*  88 */     this.motionY += 0.04D;
/*  89 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*  90 */     float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*  91 */     this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */     
/*  93 */     for (this.rotationPitch = (float)(Math.atan2(this.motionY, var1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  98 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */     {
/* 100 */       this.prevRotationPitch += 360.0F;
/*     */     }
/*     */     
/* 103 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */     {
/* 105 */       this.prevRotationYaw -= 360.0F;
/*     */     }
/*     */     
/* 108 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */     {
/* 110 */       this.prevRotationYaw += 360.0F;
/*     */     }
/*     */     
/* 113 */     this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 114 */     this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/*     */     
/* 116 */     if (this.fireworkAge == 0)
/*     */     {
/* 118 */       this.worldObj.playSoundAtEntity(this, "fireworks.launch", 3.0F, 1.0F);
/*     */     }
/*     */     
/* 121 */     this.fireworkAge++;
/*     */     
/* 123 */     if (this.worldObj.isRemote && this.fireworkAge % 2 < 2)
/*     */     {
/*     */       
/* 126 */       this.worldObj.spawnParticle(EnumParticle.fireworkSpark, this.posX, this.posY - 0.3D, this.posZ, this.rand.nextGaussian() * 0.05D, -this.motionY * 0.5D, this.rand.nextGaussian() * 0.05D);
/*     */     }
/*     */     
/* 129 */     if (!this.worldObj.isRemote && this.fireworkAge > this.lifetime) {
/*     */ 
/*     */       
/* 132 */       this.worldObj.setEntityState(this, EnumEntityState.firework_ended);
/* 133 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 141 */     if (par1 == EnumEntityState.firework_ended && this.worldObj.isRemote) {
/*     */       
/* 143 */       ItemStack var2 = this.dataWatcher.getWatchableObjectItemStack(8);
/* 144 */       NBTTagCompound var3 = null;
/*     */       
/* 146 */       if (var2 != null && var2.hasTagCompound())
/*     */       {
/* 148 */         var3 = var2.getTagCompound().getCompoundTag("Fireworks");
/*     */       }
/*     */       
/* 151 */       this.worldObj.func_92088_a(this.posX, this.posY, this.posZ, this.motionX, this.motionY, this.motionZ, var3);
/*     */     } 
/*     */     
/* 154 */     super.handleHealthUpdate(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 162 */     par1NBTTagCompound.setInteger("Life", this.fireworkAge);
/* 163 */     par1NBTTagCompound.setInteger("LifeTime", this.lifetime);
/* 164 */     ItemStack var2 = this.dataWatcher.getWatchableObjectItemStack(8);
/*     */     
/* 166 */     if (var2 != null) {
/*     */       
/* 168 */       NBTTagCompound var3 = new NBTTagCompound();
/* 169 */       var2.writeToNBT(var3);
/* 170 */       par1NBTTagCompound.setCompoundTag("FireworksItem", var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 179 */     this.fireworkAge = par1NBTTagCompound.getInteger("Life");
/* 180 */     this.lifetime = par1NBTTagCompound.getInteger("LifeTime");
/* 181 */     NBTTagCompound var2 = par1NBTTagCompound.getCompoundTag("FireworksItem");
/*     */     
/* 183 */     if (var2 != null) {
/*     */       
/* 185 */       ItemStack var3 = ItemStack.loadItemStackFromNBT(var2);
/*     */       
/* 187 */       if (var3 != null)
/*     */       {
/* 189 */         this.dataWatcher.updateObject(8, var3);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 196 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/* 204 */     return super.getBrightness(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/* 209 */     return super.getBrightnessForRender(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 217 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 222 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 232 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFireworkRocket.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */