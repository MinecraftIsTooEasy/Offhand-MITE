/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public abstract class EntityFireball
/*     */   extends Entity
/*     */ {
/*   7 */   private int xTile = -1;
/*   8 */   private int yTile = -1;
/*   9 */   private int zTile = -1;
/*     */   
/*     */   private int inTile;
/*     */   private boolean inGround;
/*     */   public EntityLivingBase shootingEntity;
/*     */   private int ticksAlive;
/*     */   private int ticksInAir;
/*     */   public double accelerationX;
/*     */   public double accelerationY;
/*     */   public double accelerationZ;
/*     */   
/*     */   public EntityFireball(World par1World) {
/*  21 */     super(par1World);
/*  22 */     setSize(1.0F, 1.0F);
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
/*  33 */     double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/*  34 */     var3 *= 64.0D;
/*  35 */     return (par1 < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFireball(World par1World, double par2, double par4, double par6, double par8, double par10, double par12) {
/*  40 */     super(par1World);
/*  41 */     setSize(1.0F, 1.0F);
/*  42 */     setLocationAndAngles(par2, par4, par6, this.rotationYaw, this.rotationPitch);
/*  43 */     setPosition(par2, par4, par6);
/*  44 */     double var14 = MathHelper.sqrt_double(par8 * par8 + par10 * par10 + par12 * par12);
/*  45 */     this.accelerationX = par8 / var14 * 0.1D;
/*  46 */     this.accelerationY = par10 / var14 * 0.1D;
/*  47 */     this.accelerationZ = par12 / var14 * 0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFireball(World par1World, EntityLivingBase par2EntityLivingBase, double par3, double par5, double par7) {
/*  52 */     super(par1World);
/*  53 */     this.shootingEntity = par2EntityLivingBase;
/*  54 */     setSize(1.0F, 1.0F);
/*  55 */     setLocationAndAngles(par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ, par2EntityLivingBase.rotationYaw, par2EntityLivingBase.rotationPitch);
/*  56 */     setPosition(this.posX, this.posY, this.posZ);
/*  57 */     this.yOffset = 0.0F;
/*  58 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*  59 */     par3 += this.rand.nextGaussian() * 0.4D;
/*  60 */     par5 += this.rand.nextGaussian() * 0.4D;
/*  61 */     par7 += this.rand.nextGaussian() * 0.4D;
/*  62 */     double var9 = MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
/*  63 */     this.accelerationX = par3 / var9 * 0.1D;
/*  64 */     this.accelerationY = par5 / var9 * 0.1D;
/*  65 */     this.accelerationZ = par7 / var9 * 0.1D;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityFireball(World world, EntityLivingBase shooter, Vec3 target, float initial_distance) {
/*  71 */     this(world, shooter, shooter.getCenterPoint(), target, initial_distance);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityFireball(World world, EntityLivingBase shooter, Vec3 origin, Vec3 target, float initial_distance) {
/*  77 */     super(world);
/*     */     
/*  79 */     this.shootingEntity = shooter;
/*  80 */     setSize(1.0F, 1.0F);
/*     */     
/*  82 */     float yaw = (float)MathHelper.getYawInDegrees(origin, target);
/*  83 */     float pitch = (float)MathHelper.getPitchInDegrees(origin, target);
/*     */     
/*  85 */     setLocationAndAngles(origin.xCoord, origin.yCoord, origin.zCoord, yaw, pitch);
/*     */     
/*  87 */     this.yOffset = 0.0F;
/*  88 */     this.motionX = this.motionY = this.motionZ = 0.0D;
/*     */     
/*  90 */     setTarget(target, initial_distance, 0.1F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTarget(Vec3 target, float initial_distance, float wander) {
/*  95 */     setTrajectory(Vec3.getDifference(getCenterPoint(), target), initial_distance, wander);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrajectory(Vec3 trajectory, float initial_distance, float wander) {
/* 101 */     if (wander > 0.0F) {
/*     */       
/* 103 */       trajectory = trajectory.copy();
/*     */       
/* 105 */       trajectory.xCoord *= (1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * wander);
/* 106 */       trajectory.yCoord *= (1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * wander);
/* 107 */       trajectory.zCoord *= (1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * wander);
/*     */     } 
/*     */     
/* 110 */     trajectory = trajectory.normalize();
/*     */     
/* 112 */     this.accelerationX = trajectory.xCoord * 0.1D;
/* 113 */     this.accelerationY = trajectory.yCoord * 0.1D;
/* 114 */     this.accelerationZ = trajectory.zCoord * 0.1D;
/*     */     
/* 116 */     this.posX += trajectory.xCoord * initial_distance;
/* 117 */     this.posY += trajectory.yCoord * initial_distance;
/* 118 */     this.posZ += trajectory.zCoord * initial_distance;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cannotRaycastCollideWith(Entity entity) {
/* 123 */     if (entity.isEntityEqual(this.shootingEntity) && this.ticksInAir < 25) {
/* 124 */       return true;
/*     */     }
/* 126 */     return super.cannotRaycastCollideWith(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 135 */     if (!this.worldObj.isRemote && ((this.shootingEntity != null && this.shootingEntity.isDead) || !this.worldObj.blockExists(getBlockPosX(), MathHelper.floor_double(this.posY), getBlockPosZ()))) {
/*     */       
/* 137 */       setDead();
/*     */     }
/*     */     else {
/*     */       
/* 141 */       super.onUpdate();
/* 142 */       setFire(1);
/*     */       
/* 144 */       if (this.inGround) {
/*     */         
/* 146 */         int var1 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
/*     */         
/* 148 */         if (var1 == this.inTile) {
/*     */           
/* 150 */           this.ticksAlive++;
/*     */           
/* 152 */           if (this.ticksAlive == 600)
/*     */           {
/* 154 */             setDead();
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 160 */         this.inGround = false;
/* 161 */         this.motionX *= (this.rand.nextFloat() * 0.2F);
/* 162 */         this.motionY *= (this.rand.nextFloat() * 0.2F);
/* 163 */         this.motionZ *= (this.rand.nextFloat() * 0.2F);
/* 164 */         this.ticksAlive = 0;
/* 165 */         this.ticksInAir = 0;
/*     */       }
/*     */       else {
/*     */         
/* 169 */         this.ticksInAir++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 174 */       Vec3 current_pos = this.worldObj.getVec3(this.posX, this.posY, this.posZ);
/* 175 */       Vec3 future_pos = this.worldObj.getVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */       
/* 177 */       Raycast raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setOriginator(this);
/*     */       
/* 179 */       setCollisionPolicies(raycast);
/*     */       
/* 181 */       raycast.performVsBlocks();
/*     */       
/* 183 */       RaycastCollision var3 = raycast.getBlockCollision();
/*     */       
/* 185 */       if (var3 != null) {
/* 186 */         raycast.setLimitToBlockCollisionPoint();
/*     */       }
/* 188 */       if (raycast.performVsEntities().hasEntityCollisions())
/*     */       {
/* 190 */         var3 = raycast.getNearestCollision();
/*     */       }
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
/* 246 */       if (var3 != null)
/*     */       {
/* 248 */         onImpact(var3);
/*     */       }
/*     */       
/* 251 */       this.posX += this.motionX;
/* 252 */       this.posY += this.motionY;
/* 253 */       this.posZ += this.motionZ;
/* 254 */       float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 255 */       this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;
/*     */       
/* 257 */       for (this.rotationPitch = (float)(Math.atan2(var16, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 262 */       while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */       {
/* 264 */         this.prevRotationPitch += 360.0F;
/*     */       }
/*     */       
/* 267 */       while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */       {
/* 269 */         this.prevRotationYaw -= 360.0F;
/*     */       }
/*     */       
/* 272 */       while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */       {
/* 274 */         this.prevRotationYaw += 360.0F;
/*     */       }
/*     */       
/* 277 */       this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 278 */       this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/* 279 */       float var17 = getMotionFactor();
/*     */       
/* 281 */       if (isInWater()) {
/*     */         
/* 283 */         for (int var19 = 0; var19 < 4; var19++) {
/*     */           
/* 285 */           float var18 = 0.25F;
/*     */           
/* 287 */           this.worldObj.spawnParticle(EnumParticle.bubble, this.posX - this.motionX * var18, this.posY - this.motionY * var18, this.posZ - this.motionZ * var18, this.motionX, this.motionY, this.motionZ);
/*     */         } 
/*     */         
/* 290 */         var17 = 0.8F;
/*     */       } 
/*     */       
/* 293 */       this.motionX += this.accelerationX;
/* 294 */       this.motionY += this.accelerationY;
/* 295 */       this.motionZ += this.accelerationZ;
/* 296 */       this.motionX *= var17;
/* 297 */       this.motionY *= var17;
/* 298 */       this.motionZ *= var17;
/*     */       
/* 300 */       this.worldObj.spawnParticle(EnumParticle.smoke, this.posX, this.posY + 0.5D, this.posZ, 0.0D, 0.0D, 0.0D);
/* 301 */       setPosition(this.posX, this.posY, this.posZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getMotionFactor() {
/* 310 */     return 0.95F;
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
/* 323 */     par1NBTTagCompound.setShort("xTile", (short)this.xTile);
/* 324 */     par1NBTTagCompound.setShort("yTile", (short)this.yTile);
/* 325 */     par1NBTTagCompound.setShort("zTile", (short)this.zTile);
/* 326 */     par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
/* 327 */     par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/* 328 */     par1NBTTagCompound.setTag("direction", newDoubleNBTList(new double[] { this.motionX, this.motionY, this.motionZ }));
/*     */     
/* 330 */     par1NBTTagCompound.setTag("acceleration", newDoubleNBTList(new double[] { this.accelerationX, this.accelerationY, this.accelerationZ }));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 338 */     this.xTile = par1NBTTagCompound.getShort("xTile");
/* 339 */     this.yTile = par1NBTTagCompound.getShort("yTile");
/* 340 */     this.zTile = par1NBTTagCompound.getShort("zTile");
/* 341 */     this.inTile = par1NBTTagCompound.getByte("inTile") & 0xFF;
/* 342 */     this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
/*     */     
/* 344 */     if (par1NBTTagCompound.hasKey("direction")) {
/*     */       
/* 346 */       NBTTagList var2 = par1NBTTagCompound.getTagList("direction");
/* 347 */       this.motionX = ((NBTTagDouble)var2.tagAt(0)).data;
/* 348 */       this.motionY = ((NBTTagDouble)var2.tagAt(1)).data;
/* 349 */       this.motionZ = ((NBTTagDouble)var2.tagAt(2)).data;
/*     */       
/* 351 */       var2 = par1NBTTagCompound.getTagList("acceleration");
/* 352 */       this.accelerationX = ((NBTTagDouble)var2.tagAt(0)).data;
/* 353 */       this.accelerationY = ((NBTTagDouble)var2.tagAt(1)).data;
/* 354 */       this.accelerationZ = ((NBTTagDouble)var2.tagAt(2)).data;
/*     */     }
/*     */     else {
/*     */       
/* 358 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 367 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCollisionBorderSize(Entity for_raycast_from_this_entity) {
/* 377 */     return 1.0F;
/*     */   }
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 430 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 435 */     if (result == null || result.entityWasDestroyed()) {
/* 436 */       return null;
/*     */     }
/* 438 */     Entity responsible_entity = damage.getResponsibleEntity();
/*     */     
/* 440 */     if (responsible_entity instanceof EntityLivingBase) {
/*     */       
/* 442 */       Vec3 var3 = responsible_entity.getLookVec();
/*     */       
/* 444 */       if (var3 != null) {
/*     */         
/* 446 */         this.motionX = var3.xCoord;
/* 447 */         this.motionY = var3.yCoord;
/* 448 */         this.motionZ = var3.zCoord;
/* 449 */         this.accelerationX = this.motionX * 0.1D;
/* 450 */         this.accelerationY = this.motionY * 0.1D;
/* 451 */         this.accelerationZ = this.motionZ * 0.1D;
/*     */         
/* 453 */         this.shootingEntity = (EntityLivingBase)responsible_entity;
/*     */         
/* 455 */         setBeenAttacked();
/*     */         
/* 457 */         result.setEntityWasAffected();
/*     */         
/* 459 */         sendPacketToAllPlayersTrackingEntity((new Packet85SimpleSignal(EnumSignal.fireball_reversal)).setEntityID(this).setApproxPosition(this.motionX, this.motionY, this.motionZ));
/*     */       } 
/*     */     } 
/*     */     
/* 463 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 468 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBrightness(float par1) {
/* 476 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getBrightnessForRender(float par1) {
/* 481 */     return 15728880;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 486 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 491 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 496 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void setCollisionPolicies(Raycast paramRaycast);
/*     */   
/*     */   public float adjustPlayerReachForAttacking(EntityPlayer player, float reach) {
/* 503 */     return super.adjustPlayerReachForAttacking(player, reach) + 2.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFireball.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */