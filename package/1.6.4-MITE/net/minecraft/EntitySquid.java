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
/*     */ public class EntitySquid
/*     */   extends EntityWaterMob
/*     */ {
/*     */   public float squidPitch;
/*     */   public float prevSquidPitch;
/*     */   public float squidYaw;
/*     */   public float prevSquidYaw;
/*     */   public float squidRotation;
/*     */   public float prevSquidRotation;
/*     */   public float tentacleAngle;
/*     */   public float prevTentacleAngle;
/*     */   private float randomMotionSpeed;
/*     */   private float rotationVelocity;
/*     */   private float field_70871_bB;
/*     */   private float randomMotionVecX;
/*     */   private float randomMotionVecY;
/*     */   private float randomMotionVecZ;
/*     */   private EntityLivingBase target;
/*     */   
/*     */   public EntitySquid(World par1World) {
/*  36 */     super(par1World);
/*  37 */     setSize(0.95F, 0.95F);
/*  38 */     this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  43 */     super.applyEntityAttributes();
/*  44 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  52 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  60 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/*  77 */     return 0.4F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/*  85 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canTriggerWalking() {
/*  94 */     return false;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 119 */     if (recently_hit_by_player) {
/* 120 */       dropItemStack(new ItemStack(Item.dyePowder, 1, 0), 0.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInWater() {
/* 129 */     return this.worldObj.handleMaterialAcceleration(this.boundingBox.expand(0.0D, -0.6000000238418579D, 0.0D), Material.water, this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 138 */     super.onLivingUpdate();
/* 139 */     this.prevSquidPitch = this.squidPitch;
/* 140 */     this.prevSquidYaw = this.squidYaw;
/* 141 */     this.prevSquidRotation = this.squidRotation;
/* 142 */     this.prevTentacleAngle = this.tentacleAngle;
/* 143 */     this.squidRotation += this.rotationVelocity;
/*     */     
/* 145 */     if (this.squidRotation > 6.2831855F) {
/*     */       
/* 147 */       this.squidRotation -= 6.2831855F;
/*     */       
/* 149 */       if (this.rand.nextInt(10) == 0)
/*     */       {
/* 151 */         this.rotationVelocity = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
/*     */       }
/*     */     } 
/*     */     
/* 155 */     AxisAlignedBB bb = this.boundingBox.copy();
/*     */     
/* 157 */     bb.minY = bb.minY * 0.2D + bb.maxY * 0.8D;
/*     */ 
/*     */     
/* 160 */     if (this.worldObj.isAABBInMaterial(bb, Material.water)) {
/*     */ 
/*     */ 
/*     */       
/* 164 */       if (this.squidRotation < 3.1415927F) {
/*     */         
/* 166 */         float f = this.squidRotation / 3.1415927F;
/* 167 */         this.tentacleAngle = MathHelper.sin(f * f * 3.1415927F) * 3.1415927F * 0.25F;
/*     */         
/* 169 */         if (f > 0.75D)
/*     */         {
/* 171 */           this.randomMotionSpeed = 1.0F;
/* 172 */           this.field_70871_bB = 1.0F;
/*     */         }
/*     */         else
/*     */         {
/* 176 */           this.field_70871_bB *= 0.8F;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 181 */         this.tentacleAngle = 0.0F;
/* 182 */         this.randomMotionSpeed *= 0.9F;
/* 183 */         this.field_70871_bB *= 0.99F;
/*     */       } 
/*     */       
/* 186 */       if (!this.worldObj.isRemote) {
/*     */         
/* 188 */         this.motionX = (this.randomMotionVecX * this.randomMotionSpeed);
/* 189 */         this.motionY = (this.randomMotionVecY * this.randomMotionSpeed);
/* 190 */         this.motionZ = (this.randomMotionVecZ * this.randomMotionSpeed);
/*     */       } 
/*     */       
/* 193 */       float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
/* 194 */       this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / 3.1415927F - this.renderYawOffset) * 0.1F;
/* 195 */       this.rotationYaw = this.renderYawOffset;
/* 196 */       this.squidYaw += 3.1415927F * this.field_70871_bB * 1.5F;
/* 197 */       this.squidPitch += (-((float)Math.atan2(var1, this.motionY)) * 180.0F / 3.1415927F - this.squidPitch) * 0.1F;
/*     */     }
/*     */     else {
/*     */       
/* 201 */       this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * 3.1415927F * 0.25F;
/*     */       
/* 203 */       if (!this.worldObj.isRemote) {
/*     */         
/* 205 */         this.motionX = 0.0D;
/* 206 */         this.motionY -= 0.08D;
/* 207 */         this.motionY *= 0.9800000190734863D;
/* 208 */         this.motionZ = 0.0D;
/*     */       } 
/*     */       
/* 211 */       this.squidPitch = (float)(this.squidPitch + (-90.0F - this.squidPitch) * 0.02D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void moveEntityWithHeading(float par1, float par2) {
/* 220 */     moveEntity(this.motionX, this.motionY, this.motionZ);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isAPlayerThatIsNotInOrAboveDeepWater(Entity entity) {
/* 226 */     if (entity instanceof EntityPlayer && entity.ridingEntity == null) {
/*     */       
/* 228 */       EntityPlayer player = entity.getAsPlayer();
/* 229 */       World world = player.worldObj;
/*     */       
/* 231 */       int x = player.getBlockPosX();
/* 232 */       int y = player.getEyeBlockPosY();
/* 233 */       int z = player.getBlockPosZ();
/*     */       
/* 235 */       if (world.isWaterBlock(x, y, z))
/* 236 */         return false; 
/* 237 */       if (player.getBlockRestingOn3() != null) {
/* 238 */         return true;
/*     */       }
/* 240 */       if (world.isWaterBlock(x, --y, z)) {
/* 241 */         return !world.isWaterBlock(x, --y, z);
/*     */       }
/* 243 */       if (world.isWaterBlock(x, --y, z)) {
/* 244 */         return !world.isWaterBlock(x, --y, z);
/*     */       }
/*     */     } 
/* 247 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean preysUpon(Entity entity) {
/* 252 */     if (entity instanceof EntityWaterMob) {
/* 253 */       return false;
/*     */     }
/* 255 */     if (this.worldObj.isBlueMoonNight()) {
/* 256 */       return false;
/*     */     }
/* 258 */     if (entity instanceof EntityPlayer) {
/*     */       
/* 260 */       EntityPlayer player = entity.getAsPlayer();
/*     */ 
/*     */       
/* 263 */       if (player.inCreativeMode() || (player.ridingEntity instanceof EntityBoat && this.worldObj.isDaytime())) {
/* 264 */         return false;
/*     */       }
/* 266 */       if (isAPlayerThatIsNotInOrAboveDeepWater(player)) {
/* 267 */         return false;
/*     */       }
/* 269 */       return true;
/*     */     } 
/*     */     
/* 272 */     return entity.isTrueAnimal();
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean areOnlyFullWaterBlocksBetween(World worldObj, Vec3 origin, Vec3 limit) {
/* 277 */     int[] coords = Raycast.getFullBlockIntercepts(origin, limit);
/*     */     
/* 279 */     int num_blocks = coords.length / 3;
/*     */     
/* 281 */     for (int i = 0; i < num_blocks; i++) {
/*     */       boolean is_full_water_block;
/* 283 */       int offset = i * 3;
/*     */       
/* 285 */       int x = coords[offset];
/* 286 */       int y = coords[offset + 1];
/* 287 */       int z = coords[offset + 2];
/*     */       
/* 289 */       Block block = worldObj.getBlock(x, y, z);
/*     */ 
/*     */ 
/*     */       
/* 293 */       if (block == Block.waterStill) {
/*     */         
/* 295 */         int metadata = worldObj.getBlockMetadata(x, y, z);
/* 296 */         is_full_water_block = (metadata == 0 || metadata == 8);
/*     */       }
/* 298 */       else if (block == Block.waterMoving) {
/*     */         
/* 300 */         is_full_water_block = (worldObj.getBlockMetadata(x, y, z) == 0);
/*     */       }
/*     */       else {
/*     */         
/* 304 */         is_full_water_block = false;
/*     */       } 
/*     */       
/* 307 */       if (!is_full_water_block) {
/* 308 */         return false;
/*     */       }
/*     */     } 
/* 311 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void updateEntityActionState() {
/* 322 */     if (this.rand.nextInt(50) == 0 || !this.inWater || (this.randomMotionVecX == 0.0F && this.randomMotionVecY == 0.0F && this.randomMotionVecZ == 0.0F)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 331 */       if (isAPlayerThatIsNotInOrAboveDeepWater(this.target)) {
/* 332 */         this.target = null;
/*     */       }
/* 334 */       if (this.target == null || this.target.isDead || this.target.getHealth() <= 0.0F || this.rand.nextInt(10) == 0) {
/* 335 */         this.target = this.worldObj.getClosestPrey(this, 16.0D, true, false);
/*     */       }
/* 337 */       if (this.target != null && this.worldObj.isBlueMoonNight()) {
/* 338 */         this.target = null;
/*     */       }
/* 340 */       if (this.target != null) {
/*     */         
/* 342 */         Vec3 center_pos = getCenterPoint();
/* 343 */         Vec3 target_pos = this.target.getCenterPoint();
/*     */         
/* 345 */         if (!areOnlyFullWaterBlocksBetween(this.worldObj, center_pos, target_pos)) {
/*     */           
/* 347 */           target_pos.yCoord = this.target.boundingBox.minY + 0.01D;
/*     */           
/* 349 */           if (!areOnlyFullWaterBlocksBetween(this.worldObj, center_pos, target_pos)) {
/*     */             
/* 351 */             target_pos.yCoord = this.target.boundingBox.maxY - 0.01D;
/*     */             
/* 353 */             if (!areOnlyFullWaterBlocksBetween(this.worldObj, center_pos, target_pos)) {
/* 354 */               this.target = null;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/* 359 */       if (this.target == null) {
/*     */         
/* 361 */         float var1 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 362 */         this.randomMotionVecX = MathHelper.cos(var1) * 0.2F;
/* 363 */         this.randomMotionVecY = -0.1F + this.rand.nextFloat() * 0.2F;
/* 364 */         this.randomMotionVecZ = MathHelper.sin(var1) * 0.2F;
/*     */       }
/*     */       else {
/*     */         
/* 368 */         Vec3 eye_pos = getCenterPoint();
/* 369 */         Vec3 target_pos = this.target.getCenterPoint();
/*     */         
/* 371 */         Vec3 vec3 = this.worldObj.getVec3(target_pos.xCoord - eye_pos.xCoord, target_pos.yCoord - eye_pos.yCoord, target_pos.zCoord - eye_pos.zCoord).normalize();
/*     */         
/* 373 */         this.randomMotionVecX = (float)vec3.xCoord * 0.2F;
/* 374 */         this.randomMotionVecY = (float)vec3.yCoord * 0.2F;
/* 375 */         this.randomMotionVecZ = (float)vec3.zCoord * 0.2F;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 381 */     tryDespawnEntity();
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
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 394 */     if (this.posY <= 45.0D || this.posY >= 63.0D) {
/* 395 */       return false;
/*     */     }
/* 397 */     if (!this.worldObj.isOnlyWater(this.boundingBox.expand(2.0D, 2.0D, 2.0D))) {
/* 398 */       return false;
/*     */     }
/* 400 */     return super.getCanSpawnHere(perform_light_check);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 405 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer player) {
/* 410 */     if (!this.worldObj.isRemote && getDistanceToEntity(player) < 1.0F && !(player.ridingEntity instanceof EntityBoat)) {
/* 411 */       player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void collideWithEntity(Entity entity) {
/* 416 */     if (onServer() && preysUpon(entity) && entity.isEntityLiving() && hasLineOfStrike(entity)) {
/* 417 */       entity.getAsEntityLiving().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 200, 2));
/*     */     }
/* 419 */     super.collideWithEntity(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDestroyBoatOnCollision(EntityBoat entity_boat) {
/* 424 */     return (this.target == entity_boat.riddenByEntity);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySquid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */