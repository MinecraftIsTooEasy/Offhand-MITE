/*     */ package net.minecraft;
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
/*     */ public class EntityFishHook
/*     */   extends Entity
/*     */ {
/*     */   private int xTile;
/*     */   private int yTile;
/*     */   private int zTile;
/*     */   private int inTile;
/*     */   private boolean inGround;
/*     */   public int shake;
/*     */   public EntityPlayer angler;
/*     */   private int ticksInGround;
/*     */   private int ticksInAir;
/*     */   private int ticksCatchable;
/*     */   public Entity bobber;
/*     */   private int fishPosRotationIncrements;
/*     */   private double fishX;
/*     */   private double fishY;
/*     */   private double fishZ;
/*     */   private double fishYaw;
/*     */   private double fishPitch;
/*     */   private double velocityX;
/*     */   private double velocityY;
/*     */   private double velocityZ;
/*     */   private Material material;
/*     */   
/*     */   public EntityFishHook(World par1World) {
/*  44 */     super(par1World);
/*  45 */     this.xTile = -1;
/*  46 */     this.yTile = -1;
/*  47 */     this.zTile = -1;
/*  48 */     setSize(0.25F, 0.25F);
/*  49 */     this.ignoreFrustumCheck = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFishHook(World par1World, double par2, double par4, double par6, EntityPlayer par8EntityPlayer) {
/*  54 */     this(par1World);
/*  55 */     setPosition(par2, par4, par6);
/*  56 */     this.ignoreFrustumCheck = true;
/*  57 */     this.angler = par8EntityPlayer;
/*  58 */     par8EntityPlayer.fishEntity = this;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     this.material = getHookMaterialFromPlayer(par8EntityPlayer);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityFishHook(World par1World, EntityPlayer par2EntityPlayer) {
/*  69 */     super(par1World);
/*  70 */     this.xTile = -1;
/*  71 */     this.yTile = -1;
/*  72 */     this.zTile = -1;
/*  73 */     this.ignoreFrustumCheck = true;
/*  74 */     this.angler = par2EntityPlayer;
/*  75 */     this.angler.fishEntity = this;
/*  76 */     setSize(0.25F, 0.25F);
/*  77 */     setLocationAndAngles(par2EntityPlayer.posX, par2EntityPlayer.posY + 1.62D - par2EntityPlayer.yOffset, par2EntityPlayer.posZ, par2EntityPlayer.rotationYaw, par2EntityPlayer.rotationPitch);
/*  78 */     this.posX -= (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/*  79 */     this.posY -= 0.10000000149011612D;
/*  80 */     this.posZ -= (MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
/*  81 */     setPosition(this.posX, this.posY, this.posZ);
/*  82 */     this.yOffset = 0.0F;
/*  83 */     float var3 = 0.4F;
/*  84 */     this.motionX = (-MathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/*  85 */     this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/*  86 */     this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F) * var3);
/*  87 */     calculateVelocity(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  93 */     this.material = getHookMaterialFromPlayer(par2EntityPlayer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Material getHookMaterialFromPlayer(EntityPlayer player) {
/*  99 */     Material material = (player == null) ? null : ((player.getHeldItem() instanceof ItemFishingRod) ? ((ItemFishingRod)player.getHeldItem()).getHookMaterial() : null);
/*     */     
/* 101 */     if (material == null) {
/* 102 */       Minecraft.setErrorMessage("getHookMaterialFromPlayer: was not able to determine hook material");
/*     */     }
/* 104 */     return material;
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
/* 115 */     double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
/* 116 */     var3 *= 64.0D;
/* 117 */     return (par1 < var3 * var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void calculateVelocity(double par1, double par3, double par5, float par7, float par8) {
/* 122 */     float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
/* 123 */     par1 /= var9;
/* 124 */     par3 /= var9;
/* 125 */     par5 /= var9;
/* 126 */     par1 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
/* 127 */     par3 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
/* 128 */     par5 += this.rand.nextGaussian() * 0.007499999832361937D * par8;
/* 129 */     par1 *= par7;
/* 130 */     par3 *= par7;
/* 131 */     par5 *= par7;
/* 132 */     this.motionX = par1;
/* 133 */     this.motionY = par3;
/* 134 */     this.motionZ = par5;
/* 135 */     float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
/* 136 */     this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(par1, par5) * 180.0D / Math.PI);
/* 137 */     this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(par3, var10) * 180.0D / Math.PI);
/* 138 */     this.ticksInGround = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9) {
/* 147 */     this.fishX = par1;
/* 148 */     this.fishY = par3;
/* 149 */     this.fishZ = par5;
/* 150 */     this.fishYaw = par7;
/* 151 */     this.fishPitch = par8;
/* 152 */     this.fishPosRotationIncrements = par9;
/* 153 */     this.motionX = this.velocityX;
/* 154 */     this.motionY = this.velocityY;
/* 155 */     this.motionZ = this.velocityZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVelocity(double par1, double par3, double par5) {
/* 163 */     this.velocityX = this.motionX = par1;
/* 164 */     this.velocityY = this.motionY = par3;
/* 165 */     this.velocityZ = this.motionZ = par5;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isFishInhabitedWaterBlock(int x, int y, int z) {
/* 170 */     if (BlockFluid.isFullWaterBlock(this.worldObj, x, y, z, false)) {
/* 171 */       return true;
/*     */     }
/* 173 */     return (this.worldObj.getBlock(x, y, z) == Block.waterStill && this.worldObj.getBlockMetadata(x, y, z) == 8);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean checkForBite() {
/* 178 */     int x = MathHelper.floor_double(this.posX);
/* 179 */     int y = MathHelper.floor_double(this.posY - 0.20000000298023224D);
/* 180 */     int z = MathHelper.floor_double(this.posZ);
/*     */     
/* 182 */     if (!BlockFluid.isFullWaterBlock(this.worldObj, x, y, z, false) || !this.worldObj.isAirBlock(x, y + 1, z)) {
/* 183 */       return false;
/*     */     }
/* 185 */     int dx = this.rand.nextInt(7) - 3;
/* 186 */     int dy = -this.rand.nextInt(4);
/* 187 */     int dz = this.rand.nextInt(7) - 3;
/*     */ 
/*     */     
/* 190 */     if (!isFishInhabitedWaterBlock(x + dx, y + dy, z + dz)) {
/* 191 */       return false;
/*     */     }
/* 193 */     Vec3 fish_hook_position = this.worldObj.getVec3((x + 0.5F), (y + 0.5F), (z + 0.5F));
/* 194 */     Vec3 fish_position = this.worldObj.getVec3(((x + dx) + 0.5F), ((y + dy) + 0.5F), ((z + dz) + 0.5F));
/*     */     
/* 196 */     if (!this.worldObj.checkForLineOfPhysicalReach(fish_hook_position, fish_position)) {
/* 197 */       return false;
/*     */     }
/* 199 */     int time_of_day = this.worldObj.getAdjustedTimeOfDay();
/*     */     
/* 201 */     float time_factor = Math.min(Math.abs(time_of_day - 5500), Math.abs(time_of_day - 17500)) / 600.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 206 */     int chance_in = this.worldObj.isBlueMoon(true) ? 600 : MathHelper.clamp_int((int)(600.0F * time_factor), 600, 2400);
/*     */     
/* 208 */     if (this.worldObj.canLightningStrikeAt(x, y + 1, z)) {
/* 209 */       chance_in /= 2;
/*     */     }
/* 211 */     if (this.worldObj.areSkillsEnabled() && !this.angler.hasSkill(Skill.FISHING)) {
/* 212 */       chance_in *= 2;
/*     */     }
/* 214 */     int fortune = EnchantmentHelper.getFishingFortuneModifier(this.angler);
/*     */     
/* 216 */     for (int i = 0; i < fortune; i++) {
/* 217 */       chance_in = chance_in * 9 / 10;
/*     */     }
/* 219 */     if (this.angler.inventory.getHotbarSlotContainItem(Item.wormRaw) >= 0) {
/* 220 */       chance_in = (int)(chance_in * 0.5F);
/*     */     }
/*     */ 
/*     */     
/* 224 */     return (this.rand.nextInt(chance_in) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean cannotRaycastCollideWith(Entity entity) {
/* 229 */     if (entity == this.angler && this.ticksInAir < 5) {
/* 230 */       return true;
/*     */     }
/* 232 */     if (entity instanceof EntityBoat) {
/* 233 */       return true;
/*     */     }
/* 235 */     if (entity == this.angler.ridingEntity) {
/* 236 */       return true;
/*     */     }
/* 238 */     return super.cannotRaycastCollideWith(entity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 246 */     super.onUpdate();
/*     */     
/* 248 */     if (this.fishPosRotationIncrements > 0) {
/*     */       
/* 250 */       double var21 = this.posX + (this.fishX - this.posX) / this.fishPosRotationIncrements;
/* 251 */       double var22 = this.posY + (this.fishY - this.posY) / this.fishPosRotationIncrements;
/* 252 */       double var23 = this.posZ + (this.fishZ - this.posZ) / this.fishPosRotationIncrements;
/* 253 */       double var7 = MathHelper.wrapAngleTo180_double(this.fishYaw - this.rotationYaw);
/* 254 */       this.rotationYaw = (float)(this.rotationYaw + var7 / this.fishPosRotationIncrements);
/* 255 */       this.rotationPitch = (float)(this.rotationPitch + (this.fishPitch - this.rotationPitch) / this.fishPosRotationIncrements);
/* 256 */       this.fishPosRotationIncrements--;
/* 257 */       setPosition(var21, var22, var23);
/* 258 */       setRotation(this.rotationYaw, this.rotationPitch);
/*     */     }
/*     */     else {
/*     */       
/* 262 */       if (!this.worldObj.isRemote) {
/*     */ 
/*     */         
/* 265 */         ItemStack var1 = this.angler.getHeldItemStack();
/*     */ 
/*     */         
/* 268 */         if (this.angler.isDead || !this.angler.isEntityAlive() || var1 == null || !(var1.getItem() instanceof ItemFishingRod) || getDistanceSqToEntity(this.angler) > 1024.0D) {
/*     */           
/* 270 */           setDead();
/* 271 */           this.angler.fishEntity = null;
/*     */           
/*     */           return;
/*     */         } 
/* 275 */         if (this.bobber != null) {
/*     */           
/* 277 */           if (!this.bobber.isDead) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 283 */             setPosition(this.bobber.posX, this.bobber.boundingBox.minY + this.bobber.height * 0.8D, this.bobber.posZ);
/* 284 */             setVelocity(this.bobber.motionX, this.bobber.motionY, this.bobber.motionZ);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/* 289 */           this.bobber = null;
/*     */         } 
/*     */       } 
/*     */       
/* 293 */       if (this.shake > 0)
/*     */       {
/* 295 */         this.shake--;
/*     */       }
/*     */       
/* 298 */       if (this.inGround) {
/*     */         
/* 300 */         int var19 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
/*     */         
/* 302 */         if (var19 == this.inTile) {
/*     */           
/* 304 */           this.ticksInGround++;
/*     */           
/* 306 */           if (this.ticksInGround == 1200)
/*     */           {
/* 308 */             setDead();
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 314 */         this.inGround = false;
/* 315 */         this.motionX *= (this.rand.nextFloat() * 0.2F);
/* 316 */         this.motionY *= (this.rand.nextFloat() * 0.2F);
/* 317 */         this.motionZ *= (this.rand.nextFloat() * 0.2F);
/* 318 */         this.ticksInGround = 0;
/* 319 */         this.ticksInAir = 0;
/*     */       }
/*     */       else {
/*     */         
/* 323 */         this.ticksInAir++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 328 */       Vec3 current_pos = this.worldObj.getVec3(this.posX, this.posY, this.posZ);
/* 329 */       Vec3 future_pos = this.worldObj.getVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
/*     */       
/* 331 */       Raycast raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setOriginator(this).setForPhysicalReach().performVsBlocks();
/* 332 */       RaycastCollision var3 = raycast.getBlockCollision();
/*     */       
/* 334 */       if (var3 != null) {
/* 335 */         raycast.setLimitToBlockCollisionPoint();
/*     */       }
/* 337 */       if (raycast.performVsEntities().hasEntityCollisions())
/*     */       {
/* 339 */         var3 = raycast.getNearestCollision();
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
/* 414 */       if (var3 != null) {
/*     */         
/* 416 */         if (var3.isEntity()) {
/*     */           
/* 418 */           this.bobber = var3.getEntityHit();
/*     */           
/* 420 */           if (onServer() && this.bobber instanceof EntityLivingBase) {
/* 421 */             this.bobber.attackEntityFrom(new Damage(DamageSource.causeThrownDamage(this, this.angler), 1.0F));
/*     */           
/*     */           }
/*     */         
/*     */         }
/*     */         else {
/*     */           
/* 428 */           this.inGround = true;
/*     */         } 
/*     */         
/* 431 */         if (onServer()) {
/* 432 */           sendPacketToAllPlayersTrackingEntity((new Packet85SimpleSignal(EnumSignal.fish_hook_in_entity)).setInteger(this.inGround ? -1 : this.bobber.entityId).setEntityID(this));
/*     */         }
/*     */       } 
/* 435 */       if (!this.inGround) {
/*     */ 
/*     */ 
/*     */         
/* 439 */         if (this.bobber == null) {
/*     */           
/* 441 */           moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 447 */           setPosition(this.bobber.posX, this.bobber.boundingBox.minY + this.bobber.height * 0.8D, this.bobber.posZ);
/* 448 */           setVelocity(this.bobber.motionX, this.bobber.motionY, this.bobber.motionZ);
/*     */         } 
/*     */         
/* 451 */         float var24 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 452 */         this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);
/*     */         
/* 454 */         for (this.rotationPitch = (float)(Math.atan2(this.motionY, var24) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 459 */         while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
/*     */         {
/* 461 */           this.prevRotationPitch += 360.0F;
/*     */         }
/*     */         
/* 464 */         while (this.rotationYaw - this.prevRotationYaw < -180.0F)
/*     */         {
/* 466 */           this.prevRotationYaw -= 360.0F;
/*     */         }
/*     */         
/* 469 */         while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
/*     */         {
/* 471 */           this.prevRotationYaw += 360.0F;
/*     */         }
/*     */         
/* 474 */         this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
/* 475 */         this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
/* 476 */         float var25 = 0.92F;
/*     */         
/* 478 */         if (this.onGround || this.isCollidedHorizontally)
/*     */         {
/* 480 */           var25 = 0.5F;
/*     */         }
/*     */         
/* 483 */         byte var27 = 5;
/* 484 */         double var26 = 0.0D;
/*     */         
/* 486 */         for (int var29 = 0; var29 < var27; var29++) {
/*     */           
/* 488 */           double var14 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (var29 + 0) / var27 - 0.125D + 0.125D;
/* 489 */           double var16 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (var29 + 1) / var27 - 0.125D + 0.125D;
/* 490 */           AxisAlignedBB var18 = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, var14, this.boundingBox.minZ, this.boundingBox.maxX, var16, this.boundingBox.maxZ);
/*     */ 
/*     */           
/* 493 */           if (this.worldObj.isAABBInMaterial(var18, Material.water) || this.worldObj.isAABBInMaterial(var18, Material.lava))
/*     */           {
/* 495 */             var26 += 1.0D / var27;
/*     */           }
/*     */         } 
/*     */         
/* 499 */         if (var26 > 0.0D)
/*     */         {
/* 501 */           if (this.ticksCatchable > 0) {
/*     */             
/* 503 */             this.ticksCatchable--;
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
/*     */           }
/* 515 */           else if (checkForBite()) {
/*     */             
/* 517 */             this.ticksCatchable = this.rand.nextInt(30) + 10;
/* 518 */             this.ticksCatchable += 20;
/* 519 */             this.motionY -= 0.20000000298023224D;
/* 520 */             playSound("random.splash", 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/* 521 */             float var30 = MathHelper.floor_double(this.boundingBox.minY);
/*     */ 
/*     */             
/*     */             int var15;
/*     */             
/* 526 */             for (var15 = 0; var15 < 1.0F + this.width * 20.0F; var15++) {
/*     */               
/* 528 */               float var31 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 529 */               float var17 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/*     */               
/* 531 */               this.worldObj.spawnParticle(EnumParticle.bubble, this.posX + var31, (var30 + 1.0F), this.posZ + var17, this.motionX, this.motionY - (this.rand.nextFloat() * 0.2F), this.motionZ);
/*     */             } 
/*     */             
/* 534 */             for (var15 = 0; var15 < 1.0F + this.width * 20.0F; var15++) {
/*     */               
/* 536 */               float var31 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/* 537 */               float var17 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width;
/*     */               
/* 539 */               this.worldObj.spawnParticle(EnumParticle.splash, this.posX + var31, (var30 + 1.0F), this.posZ + var17, this.motionX, this.motionY, this.motionZ);
/*     */             } 
/*     */           } 
/*     */         }
/*     */ 
/*     */         
/* 545 */         if (this.ticksCatchable > 0)
/*     */         {
/* 547 */           this.motionY -= (this.rand.nextFloat() * this.rand.nextFloat() * this.rand.nextFloat()) * 0.2D;
/*     */         }
/*     */ 
/*     */         
/* 551 */         double var13 = var26 * 2.0D - 1.0D;
/* 552 */         this.motionY += 0.03999999910593033D * var13;
/*     */         
/* 554 */         if (var26 > 0.0D) {
/*     */           
/* 556 */           var25 = (float)(var25 * 0.9D);
/* 557 */           this.motionY *= 0.8D;
/*     */         } 
/*     */         
/* 560 */         this.motionX *= var25;
/* 561 */         this.motionY *= var25;
/* 562 */         this.motionZ *= var25;
/* 563 */         setPosition(this.posX, this.posY, this.posZ);
/*     */       } 
/*     */       
/* 566 */       if (isBurning())
/*     */       {
/* 568 */         if (!this.worldObj.isRemote && this.ticksExisted % 5 == 0) {
/* 569 */           this.angler.getHeldItemStack().tryDamageItem(DamageSource.inFire, 1, this.angler);
/*     */         }
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 579 */     par1NBTTagCompound.setShort("xTile", (short)this.xTile);
/* 580 */     par1NBTTagCompound.setShort("yTile", (short)this.yTile);
/* 581 */     par1NBTTagCompound.setShort("zTile", (short)this.zTile);
/* 582 */     par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
/* 583 */     par1NBTTagCompound.setByte("shake", (byte)this.shake);
/* 584 */     par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 592 */     this.xTile = par1NBTTagCompound.getShort("xTile");
/* 593 */     this.yTile = par1NBTTagCompound.getShort("yTile");
/* 594 */     this.zTile = par1NBTTagCompound.getShort("zTile");
/* 595 */     this.inTile = par1NBTTagCompound.getByte("inTile") & 0xFF;
/* 596 */     this.shake = par1NBTTagCompound.getByte("shake") & 0xFF;
/* 597 */     this.inGround = (par1NBTTagCompound.getByte("inGround") == 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getShadowSize() {
/* 602 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getFishType() {
/* 607 */     if (this.rand.nextFloat() < 0.8F) {
/* 608 */       return Item.fishRaw;
/*     */     }
/* 610 */     int x = MathHelper.floor_double(this.posX);
/* 611 */     int y = MathHelper.floor_double(this.posY - 0.20000000298023224D);
/* 612 */     int z = MathHelper.floor_double(this.posZ);
/*     */     
/* 614 */     if (this.worldObj.getBiomeGenForCoords(x, z) != BiomeGenBase.ocean) {
/* 615 */       return Item.fishRaw;
/*     */     }
/* 617 */     for (int dx = -16; dx <= 16; dx++) {
/*     */       
/* 619 */       for (int dz = -16; dz <= 16; dz++) {
/*     */         
/* 621 */         for (int dy = -3; dy <= 0; dy++) {
/*     */           
/* 623 */           Block block = this.worldObj.getBlock(x + dx, y + dy, z + dz);
/*     */           
/* 625 */           if (block == Block.dirt || block == Block.grass || block == Block.sand) {
/* 626 */             return Item.fishRaw;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 631 */     return Item.fishLargeRaw;
/*     */   }
/*     */ 
/*     */   
/*     */   public int catchFish() {
/* 636 */     if (this.worldObj.isRemote)
/*     */     {
/* 638 */       return 0;
/*     */     }
/*     */ 
/*     */     
/* 642 */     byte var1 = 0;
/*     */     
/* 644 */     if (this.bobber != null) {
/*     */       
/* 646 */       double var2 = this.angler.posX - this.posX;
/* 647 */       double var4 = this.angler.posY - this.posY;
/* 648 */       double var6 = this.angler.posZ - this.posZ;
/* 649 */       double var8 = MathHelper.sqrt_double(var2 * var2 + var4 * var4 + var6 * var6);
/* 650 */       double var10 = 0.1D;
/* 651 */       this.bobber.motionX += var2 * var10;
/* 652 */       this.bobber.motionY += var4 * var10 + MathHelper.sqrt_double(var8) * 0.08D;
/* 653 */       this.bobber.motionZ += var6 * var10;
/* 654 */       var1 = 3;
/*     */     }
/* 656 */     else if (this.ticksCatchable > 0) {
/*     */ 
/*     */       
/* 659 */       EntityItem var13 = new EntityItem(this.worldObj, this.posX, this.posY, this.posZ, new ItemStack(getFishType()));
/* 660 */       double var3 = this.angler.posX - this.posX;
/* 661 */       double var5 = this.angler.posY - this.posY;
/* 662 */       double var7 = this.angler.posZ - this.posZ;
/* 663 */       double var9 = MathHelper.sqrt_double(var3 * var3 + var5 * var5 + var7 * var7);
/* 664 */       double var11 = 0.1D;
/* 665 */       var13.motionX = var3 * var11;
/* 666 */       var13.motionY = var5 * var11 + MathHelper.sqrt_double(var9) * 0.08D;
/* 667 */       var13.motionZ = var7 * var11;
/* 668 */       this.worldObj.spawnEntityInWorld(var13);
/* 669 */       this.angler.addStat(StatList.fishCaughtStat, 1);
/* 670 */       this.angler.worldObj.spawnEntityInWorld(new EntityXPOrb(this.angler.worldObj, this.angler.posX, this.angler.posY + 0.5D, this.angler.posZ + 0.5D, this.rand.nextInt(6) + 1));
/* 671 */       var1 = 1;
/*     */       
/* 673 */       if (DedicatedServer.tournament_type == EnumTournamentType.score) {
/*     */         
/* 675 */         (DedicatedServer.getOrCreateTournamentStanding(this.angler)).caught_a_fish = true;
/* 676 */         DedicatedServer.updateTournamentScoreOnClient(this.angler, true);
/*     */       } 
/*     */       
/* 679 */       int worm_index = this.angler.inventory.getHotbarSlotContainItem(Item.wormRaw);
/*     */       
/* 681 */       if (worm_index >= 0) {
/* 682 */         this.angler.inventory.decrementSlotStackSize(worm_index);
/*     */       }
/*     */     } 
/* 685 */     if (this.inGround)
/*     */     {
/* 687 */       var1 = 2;
/*     */     }
/*     */     
/* 690 */     setDead();
/* 691 */     this.angler.fishEntity = null;
/* 692 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 701 */     super.setDead();
/*     */     
/* 703 */     if (this.angler != null)
/*     */     {
/* 705 */       this.angler.fishEntity = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canCatchFire() {
/* 711 */     return isHarmedByLava();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 716 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 722 */     return (this.material == null || this.material.isHarmedByLava());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 727 */     if (damage.isLavaDamage())
/*     */     {
/* 729 */       return null;
/*     */     }
/* 731 */     return super.attackEntityFrom(damage);
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
/*     */   public boolean handleLavaMovement() {
/* 753 */     return this.worldObj.isMaterialInBB(this.boundingBox.expand(0.0D, 0.0D, 0.0D), Material.lava);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFishHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */