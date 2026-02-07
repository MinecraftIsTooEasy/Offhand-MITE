/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityBoat
/*     */   extends Entity
/*     */ {
/*     */   private boolean field_70279_a;
/*     */   private double speedMultiplier;
/*     */   private int boatPosRotationIncrements;
/*     */   private double boatX;
/*     */   private double boatY;
/*     */   private double boatZ;
/*     */   private double boatYaw;
/*     */   private double boatPitch;
/*     */   private double velocityX;
/*     */   private double velocityY;
/*     */   private double velocityZ;
/*     */   private float phase_offset;
/*     */   private boolean has_made_splash_sound;
/*     */   private long last_bump_sound_time;
/*     */   private int recent_hits_from_squid;
/*     */   
/*     */   public EntityBoat(World par1World) {
/*  33 */     super(par1World);
/*  34 */     this.field_70279_a = true;
/*  35 */     this.speedMultiplier = 0.07D;
/*  36 */     this.preventEntitySpawning = true;
/*  37 */     setSize(1.5F, 0.6F);
/*  38 */     this.yOffset = this.height / 2.0F;
/*     */     
/*  40 */     this.phase_offset = (float)(this.rand.nextFloat() * Math.PI * 2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  49 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  54 */     this.dataWatcher.addObject(17, new Integer(0));
/*  55 */     this.dataWatcher.addObject(18, new Integer(1));
/*  56 */     this.dataWatcher.addObject(19, new Float(0.0F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getCollisionBox(Entity par1Entity) {
/*  65 */     return par1Entity.boundingBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AxisAlignedBB getBoundingBox() {
/*  73 */     return this.boundingBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBePushed() {
/*  81 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityBoat(World par1World, double par2, double par4, double par6) {
/*  86 */     this(par1World);
/*  87 */     setPosition(par2, par4 + this.yOffset, par6);
/*  88 */     this.motionX = 0.0D;
/*  89 */     this.motionY = 0.0D;
/*  90 */     this.motionZ = 0.0D;
/*  91 */     this.prevPosX = par2;
/*  92 */     this.prevPosY = par4;
/*  93 */     this.prevPosZ = par6;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getMountedYOffset() {
/* 101 */     return this.height * 0.0D - 0.30000001192092896D;
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 148 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     if (result == null) {
/* 157 */       return result;
/*     */     }
/*     */     
/* 160 */     float damage_taken = damage.wasCausedByPlayerInCreative() ? 1000.0F : 10.0F;
/*     */     
/* 162 */     if (damage_taken > 0.0F) {
/*     */       
/* 164 */       setForwardDirection(-getForwardDirection());
/* 165 */       setTimeSinceHit(10);
/*     */       
/* 167 */       result.setEntityWasAffected();
/*     */       
/* 169 */       result.startTrackingHealth(getDamageTaken());
/* 170 */       setDamageTaken(getDamageTaken() + damage_taken);
/* 171 */       result.finishTrackingHealth(getDamageTaken());
/*     */       
/* 173 */       setBeenAttacked();
/*     */       
/* 175 */       if (getDamageTaken() > 40.0F) {
/*     */         
/* 177 */         if (this.riddenByEntity != null) {
/* 178 */           this.riddenByEntity.mountEntity(this);
/*     */         }
/* 180 */         if (!damage.wasCausedByPlayerInCreative()) {
/* 181 */           dropItem(Item.boat.itemID, 1, 0.0F);
/*     */         }
/* 183 */         setDead();
/* 184 */         result.setEntityWasDestroyed();
/*     */       } 
/*     */     } 
/*     */     
/* 188 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void performHurtAnimation() {
/* 196 */     setForwardDirection(-getForwardDirection());
/* 197 */     setTimeSinceHit(10);
/* 198 */     setDamageTaken(getDamageTaken() * 11.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canBeCollidedWith() {
/* 206 */     return !this.isDead;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 215 */     if (this.field_70279_a) {
/*     */       
/* 217 */       this.boatPosRotationIncrements = par9 + 5;
/*     */     }
/*     */     else {
/*     */       
/* 221 */       double var10 = par1 - this.posX;
/* 222 */       double var12 = par3 - this.posY;
/* 223 */       double var14 = par5 - this.posZ;
/* 224 */       double var16 = var10 * var10 + var12 * var12 + var14 * var14;
/*     */ 
/*     */       
/* 227 */       if (var16 <= 0.25D) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 232 */       this.boatPosRotationIncrements = 3;
/*     */     } 
/*     */     
/* 235 */     this.boatX = par1;
/* 236 */     this.boatY = par3;
/* 237 */     this.boatZ = par5;
/* 238 */     this.boatYaw = par7;
/* 239 */     this.boatPitch = par8;
/* 240 */     this.motionX = this.velocityX;
/* 241 */     this.motionY = this.velocityY;
/* 242 */     this.motionZ = this.velocityZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double par1, double par3, double par5) {
/* 250 */     this.velocityX = this.motionX = par1;
/* 251 */     this.velocityY = this.motionY = par3;
/* 252 */     this.velocityZ = this.motionZ = par5;
/*     */   }
/*     */ 
/*     */   
/*     */   public void handlePacket89(Packet89PlaySoundOnServerAtEntity packet) {
/* 257 */     if (packet.sound == Packet89PlaySoundOnServerAtEntity.enum_sound.boat_bump) {
/* 258 */       playBumpSound(packet.volume, packet.pitch);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void playBumpSound(float volume, float pitch) {
/* 264 */     if (this.worldObj.getTotalWorldTime() < this.last_bump_sound_time + 20L) {
/*     */       return;
/*     */     }
/* 267 */     this.worldObj.playSoundEffect(this.posX, this.posY, this.posZ, Block.wood.stepSound.getPlaceSound(), volume, pitch);
/* 268 */     this.last_bump_sound_time = this.worldObj.getTotalWorldTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 276 */     super.onUpdate();
/*     */     
/* 278 */     if (onServer() && this.recent_hits_from_squid > 0 && getTicksExistedWithOffset() % 200 == 0) {
/* 279 */       this.recent_hits_from_squid--;
/*     */     }
/*     */     
/* 282 */     if (!this.has_made_splash_sound && !this.worldObj.isRemote && (isInWater() || (this.worldObj.getBlockMaterial(getBlockPosX(), MathHelper.floor_double(this.posY - 0.2D), getBlockPosZ()) == Material.water && getBlockRestingOn(0.1F) == null))) {
/*     */       
/* 284 */       playSound("random.splash", 0.1F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 285 */       this.has_made_splash_sound = true;
/*     */     } 
/*     */     
/* 288 */     if (getTimeSinceHit() > 0)
/*     */     {
/* 290 */       setTimeSinceHit(getTimeSinceHit() - 1);
/*     */     }
/*     */     
/* 293 */     if (getDamageTaken() > 0.0F)
/*     */     {
/* 295 */       setDamageTaken(getDamageTaken() - 1.0F);
/*     */     }
/*     */     
/* 298 */     this.prevPosX = this.posX;
/* 299 */     this.prevPosY = this.posY;
/* 300 */     this.prevPosZ = this.posZ;
/*     */     
/* 302 */     byte var1 = 10;
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
/* 319 */     float submergence = 0.0F;
/*     */     
/* 321 */     float dy = 0.2F / var1;
/* 322 */     float wave_height = (float)(Math.sin((this.ticksExisted * 0.1F + this.phase_offset)) * 0.05000000074505806D);
/*     */     
/* 324 */     for (int i = 0; i < var1; ) {
/*     */ 
/*     */       
/* 327 */       float offset_y = i * dy - 0.25F + wave_height;
/*     */       
/* 329 */       if (this.worldObj.getBlockMaterial(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + offset_y), MathHelper.floor_double(this.posZ)) == Material.water) {
/* 330 */         submergence += 1.0F / var1;
/*     */ 
/*     */         
/*     */         i++;
/*     */       } 
/*     */     } 
/*     */     
/* 337 */     double top_speed = 0.25D;
/*     */     
/* 339 */     double var23 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 344 */     if (var23 > top_speed * 0.75D) {
/*     */       
/* 346 */       double var6 = Math.cos(this.rotationYaw * Math.PI / 180.0D);
/* 347 */       double var8 = Math.sin(this.rotationYaw * Math.PI / 180.0D);
/*     */       
/* 349 */       for (int var10 = 0; var10 < 1.0D + var23 * 60.0D; var10++) {
/*     */         
/* 351 */         double var11 = (this.rand.nextFloat() * 2.0F - 1.0F);
/* 352 */         double var13 = (this.rand.nextInt(2) * 2 - 1) * 0.7D;
/*     */ 
/*     */ 
/*     */         
/* 356 */         if (this.rand.nextBoolean()) {
/*     */           
/* 358 */           double var15 = this.posX - var6 * var11 * 0.8D + var8 * var13;
/* 359 */           double var17 = this.posZ - var8 * var11 * 0.8D - var6 * var13;
/*     */           
/* 361 */           this.worldObj.spawnParticle(EnumParticle.splash, var15, this.posY - 0.125D, var17, this.motionX, this.motionY, this.motionZ);
/*     */         }
/*     */         else {
/*     */           
/* 365 */           double var15 = this.posX + var6 + var8 * var11 * 0.7D;
/* 366 */           double var17 = this.posZ + var8 - var6 * var11 * 0.7D;
/*     */           
/* 368 */           this.worldObj.spawnParticle(EnumParticle.splash, var15, this.posY - 0.125D, var17, this.motionX, this.motionY, this.motionZ);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     if (this.worldObj.isRemote && this.field_70279_a) {
/*     */       
/* 378 */       if (this.boatPosRotationIncrements > 0)
/*     */       {
/* 380 */         double var6 = this.posX + (this.boatX - this.posX) / this.boatPosRotationIncrements;
/* 381 */         double var8 = this.posY + (this.boatY - this.posY) / this.boatPosRotationIncrements;
/* 382 */         double var25 = this.posZ + (this.boatZ - this.posZ) / this.boatPosRotationIncrements;
/* 383 */         double var12 = MathHelper.wrapAngleTo180_double(this.boatYaw - this.rotationYaw);
/* 384 */         this.rotationYaw = (float)(this.rotationYaw + var12 / this.boatPosRotationIncrements);
/* 385 */         this.rotationPitch = (float)(this.rotationPitch + (this.boatPitch - this.rotationPitch) / this.boatPosRotationIncrements);
/* 386 */         this.boatPosRotationIncrements--;
/* 387 */         setPosition(var6, var8, var25);
/* 388 */         setRotation(this.rotationYaw, this.rotationPitch);
/*     */       }
/*     */       else
/*     */       {
/* 392 */         double var6 = this.posX + this.motionX;
/* 393 */         double var8 = this.posY + this.motionY;
/* 394 */         double var25 = this.posZ + this.motionZ;
/* 395 */         setPosition(var6, var8, var25);
/*     */         
/* 397 */         if (this.onGround) {
/*     */           
/* 399 */           this.motionX *= 0.5D;
/* 400 */           this.motionY *= 0.5D;
/* 401 */           this.motionZ *= 0.5D;
/*     */         } 
/*     */         
/* 404 */         this.motionX *= 0.9900000095367432D;
/* 405 */         this.motionY *= 0.949999988079071D;
/* 406 */         this.motionZ *= 0.9900000095367432D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 428 */       if (submergence < 0.05F) {
/* 429 */         this.motionY -= 0.019999999552965164D;
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 434 */         this.motionY += ((submergence - 0.6F) * 0.01F);
/*     */       } 
/*     */       
/* 437 */       this.motionY *= 0.949999988079071D;
/*     */       
/* 439 */       if (this.motionY < -0.15000000596046448D) {
/* 440 */         this.motionY = -0.15000000596046448D;
/* 441 */       } else if (this.motionY > 0.019999999552965164D) {
/* 442 */         this.motionY = 0.019999999552965164D;
/*     */       } 
/* 444 */       if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase) {
/*     */         
/* 446 */         double d = ((EntityLivingBase)this.riddenByEntity).moveForward;
/*     */         
/* 448 */         if (d > 0.0D) {
/*     */           
/* 450 */           double d1 = -Math.sin((this.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
/* 451 */           double d2 = Math.cos((this.riddenByEntity.rotationYaw * 3.1415927F / 180.0F));
/* 452 */           this.motionX += d1 * this.speedMultiplier * 0.05000000074505806D;
/* 453 */           this.motionZ += d2 * this.speedMultiplier * 0.05000000074505806D;
/*     */         }
/* 455 */         else if (d < 0.0D) {
/*     */           
/* 457 */           this.motionX *= 0.9800000190734863D;
/* 458 */           this.motionZ *= 0.9800000190734863D;
/*     */         } 
/*     */       } 
/*     */       
/* 462 */       double var6 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
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
/* 493 */       if (var6 > top_speed) {
/*     */         
/* 495 */         double d = top_speed / var6;
/* 496 */         this.motionX *= d;
/* 497 */         this.motionZ *= d;
/* 498 */         var6 = top_speed;
/*     */       } 
/*     */       
/* 501 */       if (var6 > var23 && this.speedMultiplier < top_speed) {
/*     */         
/* 503 */         this.speedMultiplier += (top_speed - this.speedMultiplier) / top_speed * 100.0D;
/*     */         
/* 505 */         if (this.speedMultiplier > top_speed)
/*     */         {
/* 507 */           this.speedMultiplier = top_speed;
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 512 */         this.speedMultiplier -= (this.speedMultiplier - 0.07D) / top_speed * 100.0D;
/*     */         
/* 514 */         if (this.speedMultiplier < 0.07D)
/*     */         {
/* 516 */           this.speedMultiplier = 0.07D;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 522 */       if (this.onGround) {
/*     */         
/* 524 */         this.motionX *= 0.5D;
/* 525 */         this.motionY *= 0.5D;
/* 526 */         this.motionZ *= 0.5D;
/*     */       } 
/*     */       
/* 529 */       if (this.boatPosRotationIncrements > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 535 */         this.motionX += (this.boatX - this.posX) / 32.0D;
/* 536 */         this.motionY += (this.boatY - this.posY) / 32.0D;
/* 537 */         this.motionZ += (this.boatZ - this.posZ) / 32.0D;
/*     */         
/* 539 */         this.boatPosRotationIncrements--;
/*     */       } 
/*     */       
/* 542 */       moveEntity(this.motionX, this.motionY, this.motionZ);
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
/* 571 */       if (this.isCollidedHorizontally) {
/*     */ 
/*     */         
/* 574 */         if (var23 > 0.004999999888241291D && this.worldObj.isRemote && this.last_bump_sound_time + 20L < this.worldObj.getTotalWorldTime()) {
/*     */ 
/*     */ 
/*     */           
/* 578 */           Minecraft.theMinecraft.thePlayer.sendQueue.addToSendQueue(new Packet89PlaySoundOnServerAtEntity(Packet89PlaySoundOnServerAtEntity.enum_sound.boat_bump, this, (Block.wood.stepSound.getVolume() + 0.5F) * (float)var23, Block.wood.stepSound.getPitch() * 0.8F));
/* 579 */           this.last_bump_sound_time = this.worldObj.getTotalWorldTime();
/*     */         } 
/*     */         
/* 582 */         if (var23 > 0.30000001192092896D)
/*     */         {
/* 584 */           if (!this.worldObj.isRemote && !this.isDead)
/*     */           {
/* 586 */             playSound("random.break", 0.8F, 0.8F + this.worldObj.rand.nextFloat() * 0.4F);
/*     */             
/* 588 */             setDead();
/*     */             
/*     */             int var24;
/* 591 */             for (var24 = 0; var24 < 3; var24++)
/*     */             {
/* 593 */               dropItem(Block.planks.blockID, 1, 0.0F);
/*     */             }
/*     */             
/* 596 */             for (var24 = 0; var24 < 2; var24++)
/*     */             {
/* 598 */               dropItem(Item.stick.itemID, 1, 0.0F);
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       } else {
/*     */         
/* 605 */         this.motionX *= 0.9900000095367432D;
/* 606 */         this.motionY *= 0.949999988079071D;
/* 607 */         this.motionZ *= 0.9900000095367432D;
/*     */       } 
/*     */       
/* 610 */       this.rotationPitch = 0.0F;
/* 611 */       double var8 = this.rotationYaw;
/* 612 */       double var25 = this.prevPosX - this.posX;
/* 613 */       double var12 = this.prevPosZ - this.posZ;
/*     */       
/* 615 */       if (var25 * var25 + var12 * var12 > 0.001D)
/*     */       {
/* 617 */         var8 = (float)(Math.atan2(var12, var25) * 180.0D / Math.PI);
/*     */       }
/*     */       
/* 620 */       double var14 = MathHelper.wrapAngleTo180_double(var8 - this.rotationYaw);
/*     */       
/* 622 */       if (var14 > 20.0D)
/*     */       {
/* 624 */         var14 = 20.0D;
/*     */       }
/*     */       
/* 627 */       if (var14 < -20.0D)
/*     */       {
/* 629 */         var14 = -20.0D;
/*     */       }
/*     */       
/* 632 */       this.rotationYaw = (float)(this.rotationYaw + var14);
/* 633 */       setRotation(this.rotationYaw, this.rotationPitch);
/*     */       
/* 635 */       if (!this.worldObj.isRemote) {
/*     */         
/* 637 */         List<Entity> var16 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));
/*     */ 
/*     */         
/* 640 */         if (var16 != null && !var16.isEmpty())
/*     */         {
/* 642 */           for (int j = 0; j < var16.size(); j++) {
/*     */             
/* 644 */             Entity var18 = var16.get(j);
/*     */             
/* 646 */             if (var18 != this.riddenByEntity && var18.canBePushed() && var18 instanceof EntityBoat)
/*     */             {
/* 648 */               var18.applyEntityCollision(this);
/*     */             }
/*     */           } 
/*     */         }
/*     */         
/* 653 */         for (int var26 = 0; var26 < 4; var26++) {
/*     */           
/* 655 */           int var27 = MathHelper.floor_double(this.posX + ((var26 % 2) - 0.5D) * 0.8D);
/* 656 */           int var19 = MathHelper.floor_double(this.posZ + ((var26 / 2) - 0.5D) * 0.8D);
/*     */           
/* 658 */           for (int var20 = 0; var20 < 2; var20++) {
/*     */             
/* 660 */             int var21 = MathHelper.floor_double(this.posY) + var20;
/* 661 */             int var22 = this.worldObj.getBlockId(var27, var21, var19);
/*     */             
/* 663 */             if (var22 == Block.snow.blockID) {
/*     */               
/* 665 */               this.worldObj.setBlockToAir(var27, var21, var19);
/*     */             }
/* 667 */             else if (var22 == Block.waterlily.blockID) {
/*     */ 
/*     */               
/* 670 */               this.worldObj.destroyBlock((new BlockBreakInfo(this.worldObj, var27, var21, var19)).setCollidedWith(this), true);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 675 */         if (this.riddenByEntity != null && this.riddenByEntity.isDead)
/*     */         {
/* 677 */           this.riddenByEntity = null;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 682 */     if (this.riddenByEntity == null) {
/*     */       
/* 684 */       this.motionX *= 0.949999988079071D;
/* 685 */       this.motionZ *= 0.949999988079071D;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateRiderPosition() {
/* 691 */     if (this.riddenByEntity != null) {
/*     */       
/* 693 */       double var1 = Math.cos(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
/* 694 */       double var3 = Math.sin(this.rotationYaw * Math.PI / 180.0D) * 0.4D;
/* 695 */       this.riddenByEntity.setPosition(this.posX + var1, this.posY + getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 706 */     par1NBTTagCompound.setBoolean("has_made_splash_sound", this.has_made_splash_sound);
/*     */     
/* 708 */     par1NBTTagCompound.setByte("recent_hits_from_squid", (byte)this.recent_hits_from_squid);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 718 */     this.has_made_splash_sound = par1NBTTagCompound.getBoolean("has_made_splash_sound");
/*     */     
/* 720 */     this.recent_hits_from_squid = par1NBTTagCompound.getByte("recent_hits_from_squid");
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 725 */     return 0.0F;
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
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 750 */     if (this.riddenByEntity != null) {
/* 751 */       return super.onEntityRightClicked(player, item_stack);
/*     */     }
/* 753 */     if (player.onServer()) {
/* 754 */       player.mountEntity(this);
/*     */     }
/* 756 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageTaken(float par1) {
/* 764 */     this.dataWatcher.updateObject(19, Float.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDamageTaken() {
/* 772 */     return this.dataWatcher.getWatchableObjectFloat(19);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTimeSinceHit(int par1) {
/* 780 */     this.dataWatcher.updateObject(17, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTimeSinceHit() {
/* 788 */     return this.dataWatcher.getWatchableObjectInt(17);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setForwardDirection(int par1) {
/* 796 */     this.dataWatcher.updateObject(18, Integer.valueOf(par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getForwardDirection() {
/* 804 */     return this.dataWatcher.getWatchableObjectInt(18);
/*     */   }
/*     */ 
/*     */   
/*     */   public void func_70270_d(boolean par1) {
/* 809 */     this.field_70279_a = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getModelItem() {
/* 814 */     return Item.boat;
/*     */   }
/*     */ 
/*     */   
/*     */   public float adjustPlayerReachForInteraction(EntityPlayer player, float reach) {
/* 819 */     return 2.5F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void applyEntityCollision(Entity entity) {
/* 824 */     if (onServer() && !this.isDead && entity instanceof EntitySquid) {
/*     */       
/* 826 */       EntitySquid entity_squid = (EntitySquid)entity;
/*     */       
/* 828 */       if (entity_squid.canDestroyBoatOnCollision(this))
/*     */       {
/* 830 */         if (++this.recent_hits_from_squid < 6) {
/*     */           
/* 832 */           this.worldObj.playAuxSFX(1010, getBlockPosX(), getBlockPosY(), getBlockPosZ(), 0);
/*     */         }
/*     */         else {
/*     */           
/* 836 */           this.worldObj.playAuxSFX(1012, getBlockPosX(), getBlockPosY(), getBlockPosZ(), 0);
/*     */ 
/*     */ 
/*     */           
/* 840 */           setDead();
/*     */           
/*     */           int var24;
/* 843 */           for (var24 = 0; var24 < 3; var24++)
/*     */           {
/* 845 */             dropItem(Block.planks.blockID, 1, 0.0F);
/*     */           }
/*     */           
/* 848 */           for (var24 = 0; var24 < 2; var24++)
/*     */           {
/* 850 */             dropItem(Item.stick.itemID, 1, 0.0F);
/*     */           }
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 860 */     super.applyEntityCollision(entity);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityBoat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */