/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityThrowable
/*     */   extends Entity
/*     */   implements IProjectile
/*     */ {
/*     */   private int xTile;
/*     */   private int yTile;
/*     */   private int zTile;
/*     */   private int inTile;
/*     */   protected boolean inGround;
/*     */   public int throwableShake;
/*     */   private EntityLivingBase thrower;
/*     */   private String throwerName;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   
/*     */   public EntityThrowable(World par1World) {
/*  24 */     super(par1World); this.xTile = -1; this.yTile = -1; this.zTile = -1;
/*  25 */     setSize(0.25F, 0.25F);
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
/*  36 */     double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/*  37 */     var3 *= 64.0D;
/*  38 */     return (par1 < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityThrowable(World par1World, EntityLivingBase par2EntityLivingBase) {
/*  43 */     super(par1World); float wander; this.xTile = -1; this.yTile = -1; this.zTile = -1;
/*  44 */     this.thrower = par2EntityLivingBase;
/*  45 */     setSize(0.25F, 0.25F);
/*  46 */     setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY + par2EntityLivingBase.getEyeHeight(), par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
/*  47 */     this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/*  48 */     this.posY -= 0.10000000149011612D;
/*  49 */     this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/*  50 */     setPosition(this.posX, this.posY, this.posZ);
/*  51 */     this.yOffset = 0.0F;
/*  52 */     float var3 = 0.4F;
/*  53 */     this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/*  54 */     this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/*  55 */     this.motionY = (-MathHelper.sin((this.rotationPitch + func_70183_g()) / 180.0F * 3.1415927F) * var3);
/*     */ 
/*     */ 
/*     */     
/*  59 */     if (par2EntityLivingBase instanceof EntityPlayer) {
/*     */       
/*  61 */       EntityPlayer player = (EntityPlayer)par2EntityLivingBase;
/*     */       
/*  63 */       int level = player.getExperienceLevel();
/*     */       
/*  65 */       if (level < 0) {
/*  66 */         wander = 5.0F + level * -0.5F;
/*     */       } else {
/*  68 */         wander = (float)(0.5D + 4.5D / Math.pow((0.8F + (level + 1) / 5.0F), 2.0D));
/*     */       } 
/*     */     } else {
/*     */       
/*  72 */       wander = 1.0F;
/*     */     } 
/*     */     
/*  75 */     if (par2EntityLivingBase.isSuspendedInLiquid()) {
/*  76 */       wander *= 2.0F;
/*     */     }
/*     */     
/*  79 */     setThrowableHeading(this.motionX, this.motionY, this.motionZ, func_70182_d(), wander);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityThrowable(World par1World, double par2, double par4, double par6) {
/*  84 */     super(par1World); this.xTile = -1; this.yTile = -1; this.zTile = -1;
/*  85 */     this.ticksInGround = 0;
/*  86 */     setSize(0.25F, 0.25F);
/*  87 */     setPosition(par2, par4, par6);
/*  88 */     this.yOffset = 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70182_d() {
/*  93 */     if (this instanceof EntityBrick || this instanceof EntityGelatinousSphere) {
/*  94 */       return 1.2F;
/*     */     }
/*  96 */     if (this instanceof EntityWeb) {
/*  97 */       return 0.8F;
/*     */     }
/*  99 */     return 1.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float func_70183_g() {
/* 104 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThrowableHeading(double par1, double par3, double par5, float par7, float par8) {
/* 112 */     float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
/* 113 */     par1 /= var9;
/* 114 */     par3 /= var9;
/* 115 */     par5 /= var9;
/* 116 */     par1 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
/* 117 */     par3 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
/* 118 */     par5 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
/* 119 */     par1 *= par7;
/* 120 */     par3 *= par7;
/* 121 */     par5 *= par7;
/* 122 */     this.motionX = par1;
/* 123 */     this.motionY = par3;
/* 124 */     this.motionZ = par5;
/* 125 */     float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
/* 126 */     this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/* 127 */     this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, var10) * 180.0D / Math.PI);
/* 128 */     this.ticksInGround = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double par1, double par3, double par5) {
/* 136 */     this.motionX = par1;
/* 137 */     this.motionY = par3;
/* 138 */     this.motionZ = par5;
/*     */     
/* 140 */     if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
/*     */       
/* 142 */       float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
/* 143 */       this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/* 144 */       this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, var7) * 180.0D / Math.PI);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cannotRaycastCollideWith(Entity entity) {
/* 150 */     if (entity == getThrower() && this.ticksInAir < 5) {
/* 151 */       return true;
/*     */     }
/* 153 */     return super.cannotRaycastCollideWith(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/*     */     Raycast raycast;
/* 162 */     this.lastTickPosX = this.posX;
/* 163 */     this.lastTickPosY = this.posY;
/* 164 */     this.lastTickPosZ = this.posZ;
/* 165 */     super.onUpdate();
/*     */     
/* 167 */     if (this.throwableShake > 0)
/*     */     {
/* 169 */       this.throwableShake--;
/*     */     }
/*     */     
/* 172 */     if (this.inGround) {
/*     */       
/* 174 */       int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
/*     */       
/* 176 */       if (var1 == this.inTile) {
/*     */         
/* 178 */         this.ticksInGround++;
/*     */         
/* 180 */         if (this.ticksInGround == 1200)
/*     */         {
/* 182 */           setDead();
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 188 */       this.inGround = false;
/* 189 */       this.motionX *= (this.rand.nextFloat() * 0.2F);
/* 190 */       this.motionY *= (this.rand.nextFloat() * 0.2F);
/* 191 */       this.motionZ *= (this.rand.nextFloat() * 0.2F);
/* 192 */       this.ticksInGround = 0;
/* 193 */       this.ticksInAir = 0;
/*     */     }
/*     */     else {
/*     */       
/* 197 */       this.ticksInAir++;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 202 */     Vec3 current_pos = this.worldObj.getVec3(this.posX, this.posY, this.posZ);
/* 203 */     Vec3 future_pos = this.worldObj.getVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     if (this instanceof EntityWeb) {
/* 212 */       raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setForThrownWeb(this).performVsBlocks();
/*     */     } else {
/* 214 */       raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setForBluntProjectile(this).performVsBlocks();
/*     */     } 
/*     */ 
/*     */     
/* 218 */     RaycastCollision var3 = raycast.getBlockCollision();
/*     */     
/* 220 */     if (var3 != null) {
/* 221 */       raycast.setLimitToBlockCollisionPoint();
/*     */     }
/* 223 */     if (onServer() && raycast.performVsEntities().hasEntityCollisions())
/*     */     {
/* 225 */       var3 = raycast.getNearestCollision();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 285 */     if (var3 != null)
/*     */     {
/*     */ 
/*     */       
/* 289 */       if (var3.isBlock() && var3.getBlockHit() == Block.portal) {
/*     */ 
/*     */         
/* 292 */         setInPortal(Block.portal.getDestinationDimensionID(this.worldObj, var3.block_hit_x, var3.block_hit_y, var3.block_hit_z));
/*     */       }
/*     */       else {
/*     */         
/* 296 */         onImpact(var3);
/*     */       } 
/*     */     }
/*     */     
/* 300 */     this.posX += this.motionX;
/* 301 */     this.posY += this.motionY;
/* 302 */     this.posZ += this.motionZ;
/* 303 */     float var17 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 304 */     this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */     
/* 306 */     for (this.rotationPitch = (float)(Math.atan2(this.motionY, var17) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */     {
/* 313 */       this.prevRotationPitch += 360.0F;
/*     */     }
/*     */     
/* 316 */     while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */     {
/* 318 */       this.prevRotationYaw -= 360.0F;
/*     */     }
/*     */     
/* 321 */     while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */     {
/* 323 */       this.prevRotationYaw += 360.0F;
/*     */     }
/*     */     
/* 326 */     this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 327 */     this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/* 328 */     float var18 = 0.99F;
/* 329 */     float var19 = getGravityVelocity();
/*     */     
/* 331 */     if (isInWater()) {
/*     */       
/* 333 */       for (int var7 = 0; var7 < 4; var7++) {
/*     */         
/* 335 */         float var20 = 0.25F;
/*     */         
/* 337 */         this.worldObj.spawnParticle(EnumParticle.bubble, this.posX - this.motionX * var20, this.posY - this.motionY * var20, this.posZ - this.motionZ * var20, this.motionX, this.motionY, this.motionZ);
/*     */       } 
/*     */       
/* 340 */       var18 = 0.8F;
/*     */     } 
/*     */     
/* 343 */     this.motionX *= var18;
/* 344 */     this.motionY *= var18;
/* 345 */     this.motionZ *= var18;
/* 346 */     this.motionY -= var19;
/* 347 */     setPosition(this.posX, this.posY, this.posZ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getGravityVelocity() {
/* 355 */     return 0.03F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void onImpact(RaycastCollision paramRaycastCollision);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 368 */     par1NBTTagCompound.setShort("xTile", (short)this.xTile);
/* 369 */     par1NBTTagCompound.setShort("yTile", (short)this.yTile);
/* 370 */     par1NBTTagCompound.setShort("zTile", (short)this.zTile);
/* 371 */     par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
/* 372 */     par1NBTTagCompound.setByte("shake", (byte)this.throwableShake);
/* 373 */     par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/*     */     
/* 375 */     if ((this.throwerName == null || this.throwerName.length() == 0) && this.thrower != null && this.thrower instanceof EntityPlayer)
/*     */     {
/* 377 */       this.throwerName = this.thrower.getEntityName();
/*     */     }
/*     */     
/* 380 */     par1NBTTagCompound.setString("ownerName", (this.throwerName == null) ? "" : this.throwerName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 388 */     this.xTile = par1NBTTagCompound.getShort("xTile");
/* 389 */     this.yTile = par1NBTTagCompound.getShort("yTile");
/* 390 */     this.zTile = par1NBTTagCompound.getShort("zTile");
/* 391 */     this.inTile = par1NBTTagCompound.getByte("inTile") & 0xFF;
/* 392 */     this.throwableShake = par1NBTTagCompound.getByte("shake") & 0xFF;
/* 393 */     this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
/* 394 */     this.throwerName = par1NBTTagCompound.getString("ownerName");
/*     */     
/* 396 */     if (this.throwerName != null && this.throwerName.length() == 0)
/*     */     {
/* 398 */       this.throwerName = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 404 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingBase getThrower() {
/* 409 */     if (this.thrower == null && this.throwerName != null && this.throwerName.length() > 0)
/*     */     {
/* 411 */       this.thrower = this.worldObj.getPlayerEntityByName(this.throwerName);
/*     */     }
/*     */     
/* 414 */     return this.thrower;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isMagical() {
/* 420 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final ItemStack getItemStack() {
/* 425 */     return new ItemStack(getModelItem());
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityThrowable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */