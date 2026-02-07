/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityEnderEye
/*     */   extends Entity
/*     */ {
/*     */   private double targetX;
/*     */   private double targetY;
/*     */   private double targetZ;
/*     */   private int despawnTimer;
/*     */   private boolean shatterOrDrop;
/*     */   
/*     */   public EntityEnderEye(World par1World) {
/*  18 */     super(par1World);
/*  19 */     setSize(0.25F, 0.25F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInRangeToRenderDist(double par1) {
/*  30 */     double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/*  31 */     var3 *= 64.0D;
/*  32 */     return (par1 < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityEnderEye(World par1World, double par2, double par4, double par6) {
/*  37 */     super(par1World);
/*  38 */     this.despawnTimer = 0;
/*  39 */     setSize(0.25F, 0.25F);
/*  40 */     setPosition(par2, par4, par6);
/*  41 */     this.yOffset = 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveTowards(double par1, int par3, double par4) {
/*  50 */     double var6 = par1 - this.posX;
/*  51 */     double var8 = par4 - this.posZ;
/*  52 */     float var10 = MathHelper.sqrt_double(var6 * var6 + var8 * var8);
/*     */     
/*  54 */     if (var10 > 12.0F) {
/*     */       
/*  56 */       this.targetX = this.posX + var6 / var10 * 12.0D;
/*  57 */       this.targetZ = this.posZ + var8 / var10 * 12.0D;
/*  58 */       this.targetY = this.posY + 8.0D;
/*     */     }
/*     */     else {
/*     */       
/*  62 */       this.targetX = par1;
/*  63 */       this.targetY = par3;
/*  64 */       this.targetZ = par4;
/*     */     } 
/*     */     
/*  67 */     this.despawnTimer = 0;
/*  68 */     this.shatterOrDrop = (this.rand.nextInt(5) > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double par1, double par3, double par5) {
/*  76 */     this.motionX = par1;
/*  77 */     this.motionY = par3;
/*  78 */     this.motionZ = par5;
/*     */     
/*  80 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/*  82 */       float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
/*  83 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/*  84 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, var7) * 180.0D / Math.PI);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*  93 */     this.lastTickPosX = this.posX;
/*  94 */     this.lastTickPosY = this.posY;
/*  95 */     this.lastTickPosZ = this.posZ;
/*  96 */     super.onUpdate();
/*  97 */     this.posX += this.motionX;
/*  98 */     this.posY += this.motionY;
/*  99 */     this.posZ += this.motionZ;
/* 100 */     float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 101 */     this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */     
/* 103 */     for (this.rotationPitch = (float)(Math.atan2(this.motionY, var1) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */     {
/* 110 */       this.prevRotationPitch += 360.0F;
/*     */     }
/*     */     
/* 113 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */     {
/* 115 */       this.prevRotationYaw -= 360.0F;
/*     */     }
/*     */     
/* 118 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */     {
/* 120 */       this.prevRotationYaw += 360.0F;
/*     */     }
/*     */     
/* 123 */     this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 124 */     this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/*     */     
/* 126 */     if (!this.worldObj.isRemote) {
/*     */       
/* 128 */       double var2 = this.targetX - this.posX;
/* 129 */       double var4 = this.targetZ - this.posZ;
/* 130 */       float var6 = (float)Math.sqrt(var2 * var2 + var4 * var4);
/* 131 */       float var7 = (float)Math.atan2(var4, var2);
/* 132 */       double var8 = var1 + (var6 - var1) * 0.0025D;
/*     */       
/* 134 */       if (var6 < 1.0F) {
/*     */         
/* 136 */         var8 *= 0.8D;
/* 137 */         this.motionY *= 0.8D;
/*     */       } 
/*     */       
/* 140 */       this.motionX = Math.cos(var7) * var8;
/* 141 */       this.motionZ = Math.sin(var7) * var8;
/*     */       
/* 143 */       if (this.posY < this.targetY) {
/*     */         
/* 145 */         this.motionY += (1.0D - this.motionY) * 0.014999999664723873D;
/*     */       }
/*     */       else {
/*     */         
/* 149 */         this.motionY += (-1.0D - this.motionY) * 0.014999999664723873D;
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     float var10 = 0.25F;
/*     */     
/* 155 */     if (isInWater()) {
/*     */       
/* 157 */       for (int var3 = 0; var3 < 4; var3++)
/*     */       {
/*     */         
/* 160 */         this.worldObj.spawnParticle(EnumParticle.bubble, this.posX - this.motionX * var10, this.posY - this.motionY * var10, this.posZ - this.motionZ * var10, this.motionX, this.motionY, this.motionZ);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 166 */       this.worldObj.spawnParticle(EnumParticle.portal_underworld, this.posX - this.motionX * var10 + this.rand.nextDouble() * 0.6D - 0.3D, this.posY - this.motionY * var10 - 0.5D, this.posZ - this.motionZ * var10 + this.rand.nextDouble() * 0.6D - 0.3D, this.motionX, this.motionY, this.motionZ);
/*     */     } 
/*     */     
/* 169 */     if (!this.worldObj.isRemote) {
/*     */       
/* 171 */       setPosition(this.posX, this.posY, this.posZ);
/* 172 */       this.despawnTimer++;
/*     */       
/* 174 */       if (this.despawnTimer > 80 && !this.worldObj.isRemote) {
/*     */         
/* 176 */         setDead();
/*     */         
/* 178 */         if (this.shatterOrDrop) {
/*     */           
/* 180 */           this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(Item.eyeOfEnder)));
/*     */         }
/*     */         else {
/*     */           
/* 184 */           this.worldObj.playAuxSFX(2003, (int)Math.round(this.posX), (int)Math.round(this.posY), (int)Math.round(this.posZ), 0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 202 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/* 210 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/* 215 */     return 15728880;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canAttackWithItem() {
/* 223 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 228 */     return Item.eyeOfEnder;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityEnderEye.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */