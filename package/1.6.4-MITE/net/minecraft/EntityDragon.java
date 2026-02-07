/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDragon
/*     */   extends EntityLiving
/*     */   implements IBossDisplayData, IEntityMultiPart, IMob
/*     */ {
/*     */   public double targetX;
/*     */   public double targetY;
/*     */   public double targetZ;
/*  15 */   public double[][] ringBuffer = new double[64][3];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  20 */   public int ringBufferIndex = -1;
/*     */ 
/*     */   
/*     */   public EntityDragonPart[] dragonPartArray;
/*     */ 
/*     */   
/*     */   public EntityDragonPart dragonPartHead;
/*     */ 
/*     */   
/*     */   public EntityDragonPart dragonPartBody;
/*     */ 
/*     */   
/*     */   public EntityDragonPart dragonPartTail1;
/*     */ 
/*     */   
/*     */   public EntityDragonPart dragonPartTail2;
/*     */   
/*     */   public EntityDragonPart dragonPartTail3;
/*     */   
/*     */   public EntityDragonPart dragonPartWing1;
/*     */   
/*     */   public EntityDragonPart dragonPartWing2;
/*     */   
/*     */   public float prevAnimTime;
/*     */   
/*     */   public float animTime;
/*     */   
/*     */   public boolean forceNewTarget;
/*     */   
/*     */   public boolean slowed;
/*     */   
/*     */   private Entity target;
/*     */   
/*     */   public int deathTicks;
/*     */   
/*     */   public EntityEnderCrystal healingEnderCrystal;
/*     */ 
/*     */   
/*     */   public EntityDragon(World par1World) {
/*  59 */     super(par1World);
/*  60 */     this.dragonPartArray = new EntityDragonPart[] { this.dragonPartHead = new EntityDragonPart(this, "head", 6.0F, 6.0F), this.dragonPartBody = new EntityDragonPart(this, "body", 8.0F, 8.0F), this.dragonPartTail1 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail2 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartTail3 = new EntityDragonPart(this, "tail", 4.0F, 4.0F), this.dragonPartWing1 = new EntityDragonPart(this, "wing", 4.0F, 4.0F), this.dragonPartWing2 = new EntityDragonPart(this, "wing", 4.0F, 4.0F) };
/*  61 */     setHealth(getMaxHealth());
/*  62 */     setSize(16.0F, 8.0F);
/*  63 */     this.noClip = true;
/*     */     
/*  65 */     this.targetY = 100.0D;
/*  66 */     this.ignoreFrustumCheck = true;
/*     */     
/*  68 */     this.renderDistanceWeight = 10.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  73 */     super.applyEntityAttributes();
/*  74 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(200.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  79 */     super.entityInit();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double[] getMovementOffsets(int par1, float par2) {
/*  88 */     if (getHealth() <= 0.0F)
/*     */     {
/*  90 */       par2 = 0.0F;
/*     */     }
/*     */     
/*  93 */     par2 = 1.0F - par2;
/*  94 */     int var3 = this.ringBufferIndex - par1 * 1 & 0x3F;
/*  95 */     int var4 = this.ringBufferIndex - par1 * 1 - 1 & 0x3F;
/*  96 */     double[] var5 = new double[3];
/*  97 */     double var6 = this.ringBuffer[var3][0];
/*  98 */     double var8 = MathHelper.wrapAngleTo180_double(this.ringBuffer[var4][0] - var6);
/*  99 */     var5[0] = var6 + var8 * par2;
/* 100 */     var6 = this.ringBuffer[var3][1];
/* 101 */     var8 = this.ringBuffer[var4][1] - var6;
/* 102 */     var5[1] = var6 + var8 * par2;
/* 103 */     var5[2] = this.ringBuffer[var3][2] + (this.ringBuffer[var4][2] - this.ringBuffer[var3][2]) * par2;
/* 104 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 113 */     if (onServer() && this.worldObj.provider instanceof WorldProviderEnd && ((WorldProviderEnd)this.worldObj.provider).heal_ender_dragon) {
/*     */       
/* 115 */       healByPercentage(0.5F);
/* 116 */       ((WorldProviderEnd)this.worldObj.provider).heal_ender_dragon = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 122 */     if (this.worldObj.isRemote) {
/*     */       
/* 124 */       float var1 = MathHelper.cos(this.animTime * 3.1415927F * 2.0F);
/* 125 */       float var2 = MathHelper.cos(this.prevAnimTime * 3.1415927F * 2.0F);
/*     */       
/* 127 */       if (var2 <= -0.3F && var1 >= -0.3F)
/*     */       {
/* 129 */         this.worldObj.playSound(this.posX, this.posY, this.posZ, "mob.enderdragon.wings", 5.0F, 0.8F + this.rand.nextFloat() * 0.3F, false);
/*     */       }
/*     */     } 
/*     */     
/* 133 */     this.prevAnimTime = this.animTime;
/*     */ 
/*     */     
/* 136 */     if (getHealth() <= 0.0F) {
/*     */       
/* 138 */       float var1 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/* 139 */       float var2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
/* 140 */       float var3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/*     */       
/* 142 */       this.worldObj.spawnParticle(EnumParticle.largeexplode, this.posX + var1, this.posY + 2.0D + var2, this.posZ + var3, 0.0D, 0.0D, 0.0D);
/*     */     }
/*     */     else {
/*     */       
/* 146 */       updateDragonEnderCrystal();
/* 147 */       float var1 = 0.2F / (MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 10.0F + 1.0F);
/* 148 */       var1 *= (float)Math.pow(2.0D, this.motionY);
/*     */       
/* 150 */       if (this.slowed) {
/*     */         
/* 152 */         this.animTime += var1 * 0.5F;
/*     */       }
/*     */       else {
/*     */         
/* 156 */         this.animTime += var1;
/*     */       } 
/*     */       
/* 159 */       this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
/*     */       
/* 161 */       if (this.ringBufferIndex < 0)
/*     */       {
/* 163 */         for (int var25 = 0; var25 < this.ringBuffer.length; var25++) {
/*     */           
/* 165 */           this.ringBuffer[var25][0] = this.rotationYaw;
/* 166 */           this.ringBuffer[var25][1] = this.posY;
/*     */         } 
/*     */       }
/*     */       
/* 170 */       if (++this.ringBufferIndex == this.ringBuffer.length)
/*     */       {
/* 172 */         this.ringBufferIndex = 0;
/*     */       }
/*     */       
/* 175 */       this.ringBuffer[this.ringBufferIndex][0] = this.rotationYaw;
/* 176 */       this.ringBuffer[this.ringBufferIndex][1] = this.posY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 183 */       if (this.worldObj.isRemote) {
/*     */         
/* 185 */         if (this.newPosRotationIncrements > 0)
/*     */         {
/* 187 */           double var26 = this.posX + (this.newPosX - this.posX) / this.newPosRotationIncrements;
/* 188 */           double var4 = this.posY + (this.newPosY - this.posY) / this.newPosRotationIncrements;
/* 189 */           double var6 = this.posZ + (this.newPosZ - this.posZ) / this.newPosRotationIncrements;
/* 190 */           double var8 = MathHelper.wrapAngleTo180_double(this.newRotationYaw - this.rotationYaw);
/* 191 */           this.rotationYaw = (float)(this.rotationYaw + var8 / this.newPosRotationIncrements);
/* 192 */           this.rotationPitch = (float)(this.rotationPitch + (this.newRotationPitch - this.rotationPitch) / this.newPosRotationIncrements);
/* 193 */           this.newPosRotationIncrements--;
/* 194 */           setPosition(var26, var4, var6);
/* 195 */           setRotation(this.rotationYaw, this.rotationPitch);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 200 */         double var26 = this.targetX - this.posX;
/* 201 */         double var4 = this.targetY - this.posY;
/* 202 */         double var6 = this.targetZ - this.posZ;
/* 203 */         double var8 = var26 * var26 + var4 * var4 + var6 * var6;
/*     */         
/* 205 */         if (this.target != null) {
/*     */           
/* 207 */           this.targetX = this.target.posX;
/* 208 */           this.targetZ = this.target.posZ;
/* 209 */           double var10 = this.targetX - this.posX;
/* 210 */           double var12 = this.targetZ - this.posZ;
/* 211 */           double var14 = Math.sqrt(var10 * var10 + var12 * var12);
/* 212 */           double var16 = 0.4000000059604645D + var14 / 80.0D - 1.0D;
/*     */           
/* 214 */           if (var16 > 10.0D)
/*     */           {
/* 216 */             var16 = 10.0D;
/*     */           }
/*     */           
/* 219 */           this.targetY = this.target.boundingBox.minY + var16;
/*     */         }
/*     */         else {
/*     */           
/* 223 */           this.targetX += this.rand.nextGaussian() * 2.0D;
/* 224 */           this.targetZ += this.rand.nextGaussian() * 2.0D;
/*     */         } 
/*     */         
/* 227 */         if (this.forceNewTarget || var8 < 100.0D || var8 > 22500.0D || this.isCollidedHorizontally || this.isCollidedVertically)
/*     */         {
/* 229 */           setNewTarget();
/*     */         }
/*     */         
/* 232 */         var4 /= MathHelper.sqrt_double(var26 * var26 + var6 * var6);
/* 233 */         float f1 = 0.6F;
/*     */         
/* 235 */         if (var4 < -f1)
/*     */         {
/* 237 */           var4 = -f1;
/*     */         }
/*     */         
/* 240 */         if (var4 > f1)
/*     */         {
/* 242 */           var4 = f1;
/*     */         }
/*     */         
/* 245 */         this.motionY += var4 * 0.10000000149011612D;
/* 246 */         this.rotationYaw = MathHelper.wrapAngleTo180_float(this.rotationYaw);
/* 247 */         double var11 = 180.0D - Math.atan2(var26, var6) * 180.0D / Math.PI;
/* 248 */         double var13 = MathHelper.wrapAngleTo180_double(var11 - this.rotationYaw);
/*     */         
/* 250 */         if (var13 > 50.0D)
/*     */         {
/* 252 */           var13 = 50.0D;
/*     */         }
/*     */         
/* 255 */         if (var13 < -50.0D)
/*     */         {
/* 257 */           var13 = -50.0D;
/*     */         }
/*     */         
/* 260 */         Vec3 var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.targetX - this.posX, this.targetY - this.posY, this.targetZ - this.posZ).normalize();
/* 261 */         Vec3 var40 = this.worldObj.getWorldVec3Pool().getVecFromPool(MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F), this.motionY, -MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F)).normalize();
/* 262 */         float var17 = (float)(var40.dotProduct(var15) + 0.5D) / 1.5F;
/*     */         
/* 264 */         if (var17 < 0.0F)
/*     */         {
/* 266 */           var17 = 0.0F;
/*     */         }
/*     */         
/* 269 */         this.randomYawVelocity *= 0.8F;
/* 270 */         float var18 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0F + 1.0F;
/* 271 */         double var19 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ) * 1.0D + 1.0D;
/*     */         
/* 273 */         if (var19 > 40.0D)
/*     */         {
/* 275 */           var19 = 40.0D;
/*     */         }
/*     */         
/* 278 */         this.randomYawVelocity = (float)(this.randomYawVelocity + var13 * 0.699999988079071D / var19 / var18);
/* 279 */         this.rotationYaw += this.randomYawVelocity * 0.1F;
/* 280 */         float var21 = (float)(2.0D / (var19 + 1.0D));
/* 281 */         float var22 = 0.06F;
/* 282 */         moveFlying(0.0F, -1.0F, var22 * (var17 * var21 + 1.0F - var21));
/*     */         
/* 284 */         if (this.slowed) {
/*     */           
/* 286 */           moveEntity(this.motionX * 0.800000011920929D, this.motionY * 0.800000011920929D, this.motionZ * 0.800000011920929D);
/*     */         }
/*     */         else {
/*     */           
/* 290 */           moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */         } 
/*     */         
/* 293 */         Vec3 var23 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.motionX, this.motionY, this.motionZ).normalize();
/* 294 */         float var24 = (float)(var23.dotProduct(var40) + 1.0D) / 2.0F;
/* 295 */         var24 = 0.8F + 0.15F * var24;
/* 296 */         this.motionX *= var24;
/* 297 */         this.motionZ *= var24;
/* 298 */         this.motionY *= 0.9100000262260437D;
/*     */       } 
/*     */       
/* 301 */       this.renderYawOffset = this.rotationYaw;
/* 302 */       this.dragonPartHead.width = this.dragonPartHead.height = 3.0F;
/* 303 */       this.dragonPartTail1.width = this.dragonPartTail1.height = 2.0F;
/* 304 */       this.dragonPartTail2.width = this.dragonPartTail2.height = 2.0F;
/* 305 */       this.dragonPartTail3.width = this.dragonPartTail3.height = 2.0F;
/* 306 */       this.dragonPartBody.height = 3.0F;
/* 307 */       this.dragonPartBody.width = 5.0F;
/* 308 */       this.dragonPartWing1.height = 2.0F;
/* 309 */       this.dragonPartWing1.width = 4.0F;
/* 310 */       this.dragonPartWing2.height = 3.0F;
/* 311 */       this.dragonPartWing2.width = 4.0F;
/* 312 */       float var2 = (float)(getMovementOffsets(5, 1.0F)[1] - getMovementOffsets(10, 1.0F)[1]) * 10.0F / 180.0F * 3.1415927F;
/* 313 */       float var3 = MathHelper.cos(var2);
/* 314 */       float var28 = -MathHelper.sin(var2);
/* 315 */       float var5 = this.rotationYaw * 3.1415927F / 180.0F;
/* 316 */       float var27 = MathHelper.sin(var5);
/* 317 */       float var7 = MathHelper.cos(var5);
/* 318 */       this.dragonPartBody.onUpdate();
/* 319 */       this.dragonPartBody.setLocationAndAngles(this.posX + (var27 * 0.5F), this.posY, this.posZ - (var7 * 0.5F), 0.0F, 0.0F);
/* 320 */       this.dragonPartWing1.onUpdate();
/* 321 */       this.dragonPartWing1.setLocationAndAngles(this.posX + (var7 * 4.5F), this.posY + 2.0D, this.posZ + (var27 * 4.5F), 0.0F, 0.0F);
/* 322 */       this.dragonPartWing2.onUpdate();
/* 323 */       this.dragonPartWing2.setLocationAndAngles(this.posX - (var7 * 4.5F), this.posY + 2.0D, this.posZ - (var27 * 4.5F), 0.0F, 0.0F);
/*     */       
/* 325 */       if (!this.worldObj.isRemote && this.hurtTime == 0) {
/*     */         
/* 327 */         collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing1.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
/* 328 */         collideWithEntities(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartWing2.boundingBox.expand(4.0D, 2.0D, 4.0D).offset(0.0D, -2.0D, 0.0D)));
/* 329 */         attackEntitiesInList(this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.dragonPartHead.boundingBox.expand(1.0D, 1.0D, 1.0D)));
/*     */       } 
/*     */       
/* 332 */       double[] var29 = getMovementOffsets(5, 1.0F);
/* 333 */       double[] var9 = getMovementOffsets(0, 1.0F);
/* 334 */       float var33 = MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F - this.randomYawVelocity * 0.01F);
/* 335 */       float var32 = MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F - this.randomYawVelocity * 0.01F);
/* 336 */       this.dragonPartHead.onUpdate();
/* 337 */       this.dragonPartHead.setLocationAndAngles(this.posX + (var33 * 5.5F * var3), this.posY + (var9[1] - var29[1]) * 1.0D + (var28 * 5.5F), this.posZ - (var32 * 5.5F * var3), 0.0F, 0.0F);
/*     */       
/* 339 */       for (int var30 = 0; var30 < 3; var30++) {
/*     */         
/* 341 */         EntityDragonPart var31 = null;
/*     */         
/* 343 */         if (var30 == 0)
/*     */         {
/* 345 */           var31 = this.dragonPartTail1;
/*     */         }
/*     */         
/* 348 */         if (var30 == 1)
/*     */         {
/* 350 */           var31 = this.dragonPartTail2;
/*     */         }
/*     */         
/* 353 */         if (var30 == 2)
/*     */         {
/* 355 */           var31 = this.dragonPartTail3;
/*     */         }
/*     */         
/* 358 */         double[] var35 = getMovementOffsets(12 + var30 * 2, 1.0F);
/* 359 */         float var34 = this.rotationYaw * 3.1415927F / 180.0F + simplifyAngle(var35[0] - var29[0]) * 3.1415927F / 180.0F * 1.0F;
/* 360 */         float var38 = MathHelper.sin(var34);
/* 361 */         float var37 = MathHelper.cos(var34);
/* 362 */         float var36 = 1.5F;
/* 363 */         float var39 = (var30 + 1) * 2.0F;
/* 364 */         var31.onUpdate();
/* 365 */         var31.setLocationAndAngles(this.posX - ((var27 * var36 + var38 * var39) * var3), this.posY + (var35[1] - var29[1]) * 1.0D - ((var39 + var36) * var28) + 1.5D, this.posZ + ((var7 * var36 + var37 * var39) * var3), 0.0F, 0.0F);
/*     */       } 
/*     */       
/* 368 */       if (!this.worldObj.isRemote)
/*     */       {
/* 370 */         this.slowed = destroyBlocksInAABB(this.dragonPartHead.boundingBox) | destroyBlocksInAABB(this.dragonPartBody.boundingBox);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateDragonEnderCrystal() {
/* 380 */     if (this.healingEnderCrystal != null)
/*     */     {
/* 382 */       if (this.healingEnderCrystal.isDead) {
/*     */         
/* 384 */         if (!this.worldObj.isRemote)
/*     */         {
/*     */           
/* 387 */           attackEntityFromPart(this.dragonPartHead, new Damage(DamageSource.setExplosionSource((Explosion)null), 10.0F));
/*     */         }
/*     */         
/* 390 */         this.healingEnderCrystal = null;
/*     */       }
/* 392 */       else if (this.ticksExisted % 10 == 0 && getHealth() < getMaxHealth()) {
/*     */         
/* 394 */         setHealth(getHealth() + 1.0F);
/*     */       } 
/*     */     }
/*     */     
/* 398 */     if (this.rand.nextInt(10) == 0) {
/*     */       
/* 400 */       float var1 = 32.0F;
/* 401 */       List var2 = this.worldObj.getEntitiesWithinAABB(EntityEnderCrystal.class, this.boundingBox.expand(var1, var1, var1));
/* 402 */       EntityEnderCrystal var3 = null;
/* 403 */       double var4 = Double.MAX_VALUE;
/* 404 */       Iterator<EntityEnderCrystal> var6 = var2.iterator();
/*     */       
/* 406 */       while (var6.hasNext()) {
/*     */         
/* 408 */         EntityEnderCrystal var7 = var6.next();
/* 409 */         double var8 = var7.getDistanceSqToEntity(this);
/*     */         
/* 411 */         if (var8 < var4) {
/*     */           
/* 413 */           var4 = var8;
/* 414 */           var3 = var7;
/*     */         } 
/*     */       } 
/*     */       
/* 418 */       this.healingEnderCrystal = var3;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void collideWithEntities(List par1List) {
/* 427 */     double var2 = (this.dragonPartBody.boundingBox.minX + this.dragonPartBody.boundingBox.maxX) / 2.0D;
/* 428 */     double var4 = (this.dragonPartBody.boundingBox.minZ + this.dragonPartBody.boundingBox.maxZ) / 2.0D;
/* 429 */     Iterator<Entity> var6 = par1List.iterator();
/*     */     
/* 431 */     while (var6.hasNext()) {
/*     */       
/* 433 */       Entity var7 = var6.next();
/*     */       
/* 435 */       if (var7 instanceof EntityLivingBase) {
/*     */         
/* 437 */         double var8 = var7.posX - var2;
/* 438 */         double var10 = var7.posZ - var4;
/* 439 */         double var12 = var8 * var8 + var10 * var10;
/* 440 */         var7.addVelocity(var8 / var12 * 4.0D, 0.20000000298023224D, var10 / var12 * 4.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void attackEntitiesInList(List<Entity> par1List) {
/* 450 */     for (int var2 = 0; var2 < par1List.size(); var2++) {
/*     */       
/* 452 */       Entity var3 = par1List.get(var2);
/*     */       
/* 454 */       if (var3 instanceof EntityLivingBase)
/*     */       {
/*     */         
/* 457 */         var3.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this), 15.0F));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNewTarget() {
/* 467 */     this.forceNewTarget = false;
/*     */     
/* 469 */     if (this.rand.nextInt(2) == 0 && !this.worldObj.playerEntities.isEmpty()) {
/*     */       
/* 471 */       this.target = this.worldObj.playerEntities.get(this.rand.nextInt(this.worldObj.playerEntities.size()));
/*     */     }
/*     */     else {
/*     */       
/* 475 */       boolean var1 = false;
/*     */ 
/*     */       
/*     */       do {
/* 479 */         this.targetX = 0.0D;
/* 480 */         this.targetY = (70.0F + this.rand.nextFloat() * 50.0F);
/* 481 */         this.targetZ = 0.0D;
/* 482 */         this.targetX += (this.rand.nextFloat() * 120.0F - 60.0F);
/* 483 */         this.targetZ += (this.rand.nextFloat() * 120.0F - 60.0F);
/* 484 */         double var2 = this.posX - this.targetX;
/* 485 */         double var4 = this.posY - this.targetY;
/* 486 */         double var6 = this.posZ - this.targetZ;
/* 487 */         var1 = (var2 * var2 + var4 * var4 + var6 * var6 > 100.0D);
/*     */       }
/* 489 */       while (!var1);
/*     */       
/* 491 */       this.target = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private float simplifyAngle(double par1) {
/* 500 */     return (float)MathHelper.wrapAngleTo180_double(par1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean destroyBlocksInAABB(AxisAlignedBB par1AxisAlignedBB) {
/* 508 */     int var2 = MathHelper.floor_double(par1AxisAlignedBB.minX);
/* 509 */     int var3 = MathHelper.floor_double(par1AxisAlignedBB.minY);
/* 510 */     int var4 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
/* 511 */     int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxX);
/* 512 */     int var6 = MathHelper.floor_double(par1AxisAlignedBB.maxY);
/* 513 */     int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxZ);
/* 514 */     boolean var8 = false;
/* 515 */     boolean var9 = false;
/*     */     
/* 517 */     for (int var10 = var2; var10 <= var5; var10++) {
/*     */       
/* 519 */       for (int var11 = var3; var11 <= var6; var11++) {
/*     */         
/* 521 */         for (int var12 = var4; var12 <= var7; var12++) {
/*     */           
/* 523 */           int var13 = this.worldObj.getBlockId(var10, var11, var12);
/*     */           
/* 525 */           if (var13 != 0)
/*     */           {
/* 527 */             if (var13 != Block.obsidian.blockID && var13 != Block.whiteStone.blockID && var13 != Block.bedrock.blockID && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
/*     */               
/* 529 */               var9 = (this.worldObj.setBlockToAir(var10, var11, var12) || var9);
/*     */             }
/*     */             else {
/*     */               
/* 533 */               var8 = true;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 540 */     if (var9) {
/*     */       
/* 542 */       double var16 = par1AxisAlignedBB.minX + (par1AxisAlignedBB.maxX - par1AxisAlignedBB.minX) * this.rand.nextFloat();
/* 543 */       double var17 = par1AxisAlignedBB.minY + (par1AxisAlignedBB.maxY - par1AxisAlignedBB.minY) * this.rand.nextFloat();
/* 544 */       double var14 = par1AxisAlignedBB.minZ + (par1AxisAlignedBB.maxZ - par1AxisAlignedBB.minZ) * this.rand.nextFloat();
/*     */       
/* 546 */       this.worldObj.spawnParticle(EnumParticle.largeexplode, var16, var17, var14, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */     
/* 549 */     return var8;
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
/*     */   public EntityDamageResult attackEntityFromPart(EntityDragonPart par1EntityDragonPart, Damage damage) {
/* 581 */     if (par1EntityDragonPart != this.dragonPartHead && damage.getAmount() > 1.0F) {
/* 582 */       damage.scaleAmount(0.25F, 1.0F);
/*     */     }
/* 584 */     float var4 = this.rotationYaw * 3.1415927F / 180.0F;
/* 585 */     float var5 = MathHelper.sin(var4);
/* 586 */     float var6 = MathHelper.cos(var4);
/* 587 */     this.targetX = this.posX + (var5 * 5.0F) + ((this.rand.nextFloat() - 0.5F) * 2.0F);
/* 588 */     this.targetY = this.posY + (this.rand.nextFloat() * 3.0F) + 1.0D;
/* 589 */     this.targetZ = this.posZ - (var6 * 5.0F) + ((this.rand.nextFloat() - 0.5F) * 2.0F);
/* 590 */     this.target = null;
/*     */ 
/*     */     
/* 593 */     if (damage.getResponsibleEntity() instanceof EntityPlayer || damage.isExplosion())
/*     */     {
/* 595 */       return func_82195_e(damage);
/*     */     }
/*     */ 
/*     */     
/* 599 */     return null;
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 613 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected EntityDamageResult func_82195_e(Damage damage) {
/* 623 */     return super.attackEntityFrom(damage);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDeathUpdate() {
/* 631 */     this.deathTicks++;
/*     */     
/* 633 */     if (this.deathTicks >= 180 && this.deathTicks <= 200) {
/*     */       
/* 635 */       float var1 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/* 636 */       float var2 = (this.rand.nextFloat() - 0.5F) * 4.0F;
/* 637 */       float var3 = (this.rand.nextFloat() - 0.5F) * 8.0F;
/*     */       
/* 639 */       this.worldObj.spawnParticle(EnumParticle.hugeexplosion, this.posX + var1, this.posY + 2.0D + var2, this.posZ + var3, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 645 */     if (!this.worldObj.isRemote) {
/*     */       
/* 647 */       if (this.deathTicks > 150 && this.deathTicks % 5 == 0) {
/*     */ 
/*     */         
/* 650 */         int var4 = 100;
/*     */         
/* 652 */         while (var4 > 0) {
/*     */           
/* 654 */           int var5 = EntityXPOrb.getXPSplit(var4);
/* 655 */           var4 -= var5;
/* 656 */           this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
/*     */         } 
/*     */       } 
/*     */       
/* 660 */       if (this.deathTicks == 1)
/*     */       {
/* 662 */         this.worldObj.func_82739_e(1018, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */       }
/*     */     } 
/*     */     
/* 666 */     moveEntity(0.0D, 0.10000000149011612D, 0.0D);
/* 667 */     this.renderYawOffset = this.rotationYaw += 20.0F;
/*     */     
/* 669 */     if (this.deathTicks == 200 && !this.worldObj.isRemote) {
/*     */ 
/*     */       
/* 672 */       int var4 = 200;
/*     */       
/* 674 */       while (var4 > 0) {
/*     */         
/* 676 */         int var5 = EntityXPOrb.getXPSplit(var4);
/* 677 */         var4 -= var5;
/* 678 */         this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, var5));
/*     */       } 
/*     */       
/* 681 */       createEnderPortal(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
/* 682 */       setDead();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void createEnderPortal(int par1, int par2) {
/* 691 */     byte var3 = 64;
/* 692 */     BlockEndPortal.bossDefeated = true;
/* 693 */     byte var4 = 4;
/*     */     
/* 695 */     for (int var5 = var3 - 1; var5 <= var3 + 32; var5++) {
/*     */       
/* 697 */       for (int var6 = par1 - var4; var6 <= par1 + var4; var6++) {
/*     */         
/* 699 */         for (int var7 = par2 - var4; var7 <= par2 + var4; var7++) {
/*     */           
/* 701 */           double var8 = (var6 - par1);
/* 702 */           double var10 = (var7 - par2);
/* 703 */           double var12 = var8 * var8 + var10 * var10;
/*     */           
/* 705 */           if (var12 <= (var4 - 0.5D) * (var4 - 0.5D))
/*     */           {
/* 707 */             if (var5 < var3) {
/*     */               
/* 709 */               if (var12 <= ((var4 - 1) - 0.5D) * ((var4 - 1) - 0.5D))
/*     */               {
/* 711 */                 this.worldObj.setBlock(var6, var5, var7, Block.bedrock.blockID);
/*     */               }
/*     */             }
/* 714 */             else if (var5 > var3) {
/*     */               
/* 716 */               this.worldObj.setBlock(var6, var5, var7, 0);
/*     */             }
/* 718 */             else if (var12 > ((var4 - 1) - 0.5D) * ((var4 - 1) - 0.5D)) {
/*     */               
/* 720 */               this.worldObj.setBlock(var6, var5, var7, Block.bedrock.blockID);
/*     */             }
/*     */             else {
/*     */               
/* 724 */               this.worldObj.setBlock(var6, var5, var7, Block.endPortal.blockID);
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 731 */     this.worldObj.setBlock(par1, var3 + 0, par2, Block.bedrock.blockID);
/* 732 */     this.worldObj.setBlock(par1, var3 + 1, par2, Block.bedrock.blockID);
/* 733 */     this.worldObj.setBlock(par1, var3 + 2, par2, Block.bedrock.blockID);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 739 */     this.worldObj.setBlock(par1 - 1, var3 + 2, par2, Block.torchWood.blockID, Block.torchWood.getMetadataForDirectionFacing(0, EnumDirection.WEST), 3);
/* 740 */     this.worldObj.setBlock(par1 + 1, var3 + 2, par2, Block.torchWood.blockID, Block.torchWood.getMetadataForDirectionFacing(0, EnumDirection.EAST), 3);
/* 741 */     this.worldObj.setBlock(par1, var3 + 2, par2 - 1, Block.torchWood.blockID, Block.torchWood.getMetadataForDirectionFacing(0, EnumDirection.NORTH), 3);
/* 742 */     this.worldObj.setBlock(par1, var3 + 2, par2 + 1, Block.torchWood.blockID, Block.torchWood.getMetadataForDirectionFacing(0, EnumDirection.SOUTH), 3);
/*     */     
/* 744 */     this.worldObj.setBlock(par1, var3 + 3, par2, Block.bedrock.blockID);
/* 745 */     this.worldObj.setBlock(par1, var3 + 4, par2, Block.dragonEgg.blockID);
/* 746 */     BlockEndPortal.bossDefeated = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void tryDespawnEntity() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity[] getParts() {
/* 759 */     return (Entity[])this.dragonPartArray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 767 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public World func_82194_d() {
/* 772 */     return this.worldObj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 780 */     return "mob.enderdragon.growl";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 788 */     return "mob.enderdragon.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 797 */     return 5.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 802 */     return super.getExperienceValue() * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 807 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 812 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 817 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDragon.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */