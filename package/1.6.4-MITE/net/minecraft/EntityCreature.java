/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.UUID;
/*     */ 
/*     */ public abstract class EntityCreature
/*     */   extends EntityLiving {
/*   7 */   public static final UUID field_110179_h = UUID.fromString("E199AD21-BA8A-4C53-8D13-6182D5C69D3A");
/*   8 */   public static final AttributeModifier field_110181_i = (new AttributeModifier(field_110179_h, "Fleeing speed bonus", 2.0D, 2)).setSaved(false);
/*     */ 
/*     */   
/*     */   private PathEntity pathToEntity;
/*     */ 
/*     */   
/*     */   protected Entity entityToAttack;
/*     */ 
/*     */   
/*     */   protected boolean hasAttacked;
/*     */ 
/*     */   
/*     */   protected int fleeingTick;
/*     */   
/*  22 */   private ChunkCoordinates homePosition = new ChunkCoordinates(0, 0, 0);
/*     */ 
/*     */   
/*  25 */   private float maximumHomeDistance = -1.0F;
/*  26 */   private EntityAIBase field_110178_bs = new EntityAIMoveTowardsRestriction(this, 1.0D);
/*     */ 
/*     */   
/*     */   private boolean field_110180_bt;
/*     */ 
/*     */   
/*     */   public EntityCreature(World par1World) {
/*  33 */     super(par1World);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isMovementCeased() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean avoidsPathingThroughWater() {
/*  47 */     return getNavigator().getAvoidsWater();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateEntityActionState() {
/*  52 */     this.worldObj.theProfiler.startSection("ai");
/*     */     
/*  54 */     if (this.fleeingTick > 0 && --this.fleeingTick == 0) {
/*     */       
/*  56 */       AttributeInstance var1 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/*  57 */       var1.removeModifier(field_110181_i);
/*     */     } 
/*     */     
/*  60 */     this.hasAttacked = isMovementCeased();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     float var21 = getMaxTargettingRange();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     boolean use_R100_pathing_adjustments = true;
/*     */     
/*  73 */     if (use_R100_pathing_adjustments && var21 > 24.0F) {
/*  74 */       var21 = 24.0F;
/*     */     }
/*  76 */     if (this.fleeing) {
/*     */       
/*  78 */       if (this.pathToEntity == null || considerStopFleeing())
/*     */       {
/*     */         
/*  81 */         this.fleeing = false;
/*  82 */         this.pathToEntity = null;
/*     */       }
/*     */     
/*  85 */     } else if (this.has_decided_to_flee && !considerStopFleeing()) {
/*     */       
/*  87 */       Entity last_attacking_entity = getLastHarmingEntity();
/*     */ 
/*     */       
/*  90 */       PathEntity path = findPathAwayFromXYZ(MathHelper.floor_double(last_attacking_entity.posX), MathHelper.floor_double(last_attacking_entity.posY), MathHelper.floor_double(last_attacking_entity.posZ), 16, 48, false);
/*     */       
/*  92 */       if (path != null) {
/*     */         
/*  94 */         this.fleeing = true;
/*  95 */         setEntityToAttack((Entity)null);
/*  96 */         this.pathToEntity = path;
/*  97 */         onFleeing();
/*     */       } 
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
/* 124 */     int ticks_existed_with_offset = getTicksExistedWithOffset();
/*     */     
/* 126 */     if (this.target_entity_item != null && this.target_entity_item.isDead) {
/* 127 */       this.target_entity_item = null;
/*     */     }
/* 129 */     if (!this.fleeing) {
/*     */       
/* 131 */       if (this.entityToAttack != null)
/*     */       {
/* 133 */         if (this.entityToAttack.isDead || (this.entityToAttack.isEntityLivingBase() && this.entityToAttack.getAsEntityLivingBase().getHealth() <= 0.0F)) {
/* 134 */           this.entityToAttack = null;
/*     */         }
/*     */       }
/* 137 */       if (this.entityToAttack == null) {
/*     */ 
/*     */         
/* 140 */         if (this instanceof EntityEnderman || ticks_existed_with_offset % 10 == 0) {
/* 141 */           this.entityToAttack = findPlayerToAttack(var21);
/*     */         }
/* 143 */         if (this.entityToAttack != null) {
/*     */ 
/*     */           
/* 146 */           this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.entityToAttack, var21, true, false, avoidsPathingThroughWater(), true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 167 */         else if (this.rand.nextFloat() < 0.01F) {
/*     */           
/* 169 */           this.entityToAttack = findNonPlayerToAttack(var21);
/*     */         } 
/*     */ 
/*     */         
/* 173 */         if (this.entityToAttack == null && (this.target_entity_item == null || this.target_entity_item.isDead || this.pathToEntity == null || this.pathToEntity.isFinished()) && this.food_or_repair_item_pickup_cooldown == 0 && ticks_existed_with_offset % 20 == 0)
/*     */         {
/* 175 */           this.target_entity_item = findTargetEntityItem(var21 * 0.75F);
/*     */           
/* 177 */           if (this.target_entity_item != null)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 183 */             float max_path_length = Math.min(getDistanceToEntity(this.target_entity_item) * 2.0F, var21 * 0.75F);
/*     */             
/* 185 */             if (max_path_length < 4.0F) {
/* 186 */               max_path_length = 4.0F;
/*     */             }
/* 188 */             if (ticks_existed_with_offset % 100 == 0) {
/* 189 */               max_path_length = var21 * 0.75F;
/*     */             }
/* 191 */             boolean can_pass_open_wooden_doors = true;
/* 192 */             boolean can_path_through_closed_wooden_doors = false;
/* 193 */             boolean avoid_water = avoidsPathingThroughWater();
/* 194 */             boolean entity_can_swim = true;
/*     */             
/* 196 */             this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.target_entity_item, max_path_length, can_pass_open_wooden_doors, can_path_through_closed_wooden_doors, avoid_water, entity_can_swim);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 214 */       else if (this.entityToAttack.isEntityAlive()) {
/*     */ 
/*     */ 
/*     */         
/* 218 */         float var2 = this.entityToAttack.getDistanceToEntity(this);
/*     */         
/* 220 */         boolean line_of_strike_override = false;
/*     */         
/* 222 */         if (this instanceof EntityBlaze) {
/*     */           
/* 224 */           Raycast raycast = (new Raycast(this.worldObj)).setForPiercingProjectile(null);
/*     */           
/* 226 */           if (raycast.checkForNoBlockCollision(getPrimaryPointOfAttack(), this.entityToAttack.getCenterPoint())) {
/* 227 */             line_of_strike_override = true;
/*     */           }
/*     */         } 
/*     */         
/* 231 */         if (line_of_strike_override || hasLineOfStrike(this.entityToAttack))
/*     */         {
/* 233 */           attackEntity(this.entityToAttack, var2);
/*     */         }
/*     */       }
/*     */       else {
/*     */         
/* 238 */         this.entityToAttack = null;
/*     */       } 
/*     */     } 
/*     */     
/* 242 */     this.worldObj.theProfiler.endSection();
/*     */ 
/*     */ 
/*     */     
/* 246 */     if (!this.hasAttacked && this.entityToAttack != null && ticks_existed_with_offset % 8 == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 258 */       float max_path_length = Math.min(getDistanceToEntity(this.entityToAttack) * 2.0F, var21);
/*     */       
/* 260 */       if (max_path_length < 4.0F) {
/* 261 */         max_path_length = 4.0F;
/*     */       }
/* 263 */       if (ticks_existed_with_offset % 40 == 0) {
/* 264 */         max_path_length = var21;
/*     */       }
/*     */ 
/*     */       
/* 268 */       PathEntity path = this.worldObj.getPathEntityToEntity(this, this.entityToAttack, max_path_length, true, false, avoidsPathingThroughWater(), true);
/*     */       
/* 270 */       if (path != null)
/*     */       {
/* 272 */         this.pathToEntity = path;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 301 */     else if (!this.fleeing && !this.hasAttacked && this.pathToEntity == null && this.entityToAttack == null && this.rand.nextInt(150) == 0) {
/*     */       
/* 303 */       updateWanderPath();
/*     */     }
/*     */     else {
/*     */       
/* 307 */       long ticks_since_harmed_by_cactus = (this.last_tick_harmed_by_cactus < 1L) ? -1L : (this.worldObj.getTotalWorldTime() - this.last_tick_harmed_by_cactus);
/*     */       
/* 309 */       boolean prompted_by_cactus = (ticks_since_harmed_by_cactus >= 0L && ticks_since_harmed_by_cactus < 10L && (this.pathToEntity == null || this.pathToEntity.isFinished()));
/*     */       
/* 311 */       if (prompted_by_cactus) {
/* 312 */         updateWanderPath();
/*     */       }
/*     */     } 
/*     */     
/* 316 */     boolean var3 = isInWater();
/* 317 */     boolean var4 = handleLavaMovement();
/* 318 */     this.rotationPitch = 0.0F;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 323 */     this.isJumping = false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 351 */     if (this.pathToEntity != null && this.rand.nextInt(this.fleeing ? 1000 : 100) > 0 && (!use_R100_pathing_adjustments || !this.pathToEntity.isFinished())) {
/*     */       
/* 353 */       this.worldObj.theProfiler.startSection("followpath");
/* 354 */       Vec3 var5 = this.pathToEntity.getPosition(this);
/* 355 */       double var6 = (this.width * 2.0F);
/*     */       
/* 357 */       while (var5 != null && var5.squareDistanceTo(this.posX, var5.yCoord, this.posZ) < var6 * var6) {
/*     */         
/* 359 */         this.pathToEntity.incrementPathIndex();
/*     */         
/* 361 */         if (this.pathToEntity.isFinished()) {
/*     */           
/* 363 */           if (!this.pathToEntity.include_last_point) {
/* 364 */             var5 = null;
/*     */           }
/*     */           
/* 367 */           this.pathToEntity = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 375 */           if (this.entityToAttack != null) {
/*     */             
/* 377 */             float max_path_length = Math.min(getDistanceToEntity(this.entityToAttack) * 2.0F, var21);
/*     */             
/* 379 */             if (max_path_length < 4.0F) {
/* 380 */               max_path_length = 4.0F;
/*     */             }
/* 382 */             this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.entityToAttack, max_path_length, true, false, avoidsPathingThroughWater(), true); break;
/*     */           } 
/* 384 */           if (this.target_entity_item != null && !this.target_entity_item.isDead && getDistanceToEntity(this.target_entity_item) <= 4.0D && (!isItemWithinPickupDistance(this.target_entity_item) || !this.target_entity_item.canRaycastToEntity(this))) {
/*     */             
/* 386 */             this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.target_entity_item, 4.0F, true, false, avoidsPathingThroughWater(), true);
/*     */ 
/*     */ 
/*     */             
/* 390 */             if (this.pathToEntity != null) {
/* 391 */               this.pathToEntity.include_last_point = true;
/*     */             }
/*     */           } 
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 398 */         var5 = this.pathToEntity.getPosition(this);
/*     */       } 
/*     */ 
/*     */       
/* 402 */       this.isJumping = false;
/*     */       
/* 404 */       if (var5 != null) {
/*     */         
/* 406 */         double var8 = var5.xCoord - this.posX;
/* 407 */         double var10 = var5.zCoord - this.posZ;
/*     */         
/* 409 */         double var12 = this.worldObj.getBlockCollisionTopY(var5.getBlockX(), var5.getBlockY(), var5.getBlockZ(), this) - this.boundingBox.minY;
/* 410 */         float var14 = (float)(Math.atan2(var10, var8) * 180.0D / Math.PI) - 90.0F;
/* 411 */         float var15 = MathHelper.wrapAngleTo180_float(var14 - this.rotationYaw);
/* 412 */         this.moveForward = (float)getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
/*     */         
/* 414 */         if (isFrenzied()) {
/* 415 */           this.moveForward *= 1.2F;
/*     */         }
/* 417 */         if (var15 > 30.0F)
/*     */         {
/* 419 */           var15 = 30.0F;
/*     */         }
/*     */         
/* 422 */         if (var15 < -30.0F)
/*     */         {
/* 424 */           var15 = -30.0F;
/*     */         }
/*     */         
/* 427 */         this.rotationYaw += var15;
/*     */         
/* 429 */         this.rotationYaw = (float)MathHelper.getYawInDegrees(this.posX, this.posZ, var5.xCoord, var5.zCoord);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 447 */         if (this.isCollidedHorizontally && var12 > this.stepHeight)
/*     */         {
/* 449 */           this.isJumping = true;
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 458 */       if (this.entityToAttack != null && this instanceof EntityArachnid && ((EntityArachnid)this).canClimbWalls()) {
/*     */         
/* 460 */         faceEntity(this.entityToAttack, 30.0F, 30.0F);
/*     */         
/* 462 */         if (isInsideOfMaterial(Material.tree_leaves) && this.entityToAttack.boundingBox.minY > this.boundingBox.minY) {
/* 463 */           this.isJumping = true;
/*     */         }
/*     */       } 
/* 466 */       if (this.isCollidedHorizontally && !hasPath())
/*     */       {
/* 468 */         this.isJumping = true;
/*     */       }
/*     */       
/* 471 */       if (this.rand.nextFloat() < 0.8F && (var3 || var4))
/*     */       {
/* 473 */         this.isJumping = true;
/*     */       }
/*     */       
/* 476 */       this.worldObj.theProfiler.endSection();
/*     */     }
/*     */     else {
/*     */       
/* 480 */       if (this.pathToEntity != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 487 */         this.entityToAttack = findPlayerToAttack(var21);
/*     */         
/* 489 */         if (this.entityToAttack == null) {
/* 490 */           this.entityToAttack = findNonPlayerToAttack(var21);
/*     */         }
/* 492 */         if (this.entityToAttack != null) {
/*     */           
/* 494 */           this.pathToEntity = this.worldObj.getPathEntityToEntity(this, this.entityToAttack, var21, true, false, avoidsPathingThroughWater(), true);
/*     */           
/* 496 */           super.updateEntityActionState();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 501 */       super.updateEntityActionState();
/* 502 */       this.pathToEntity = null;
/*     */     } 
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
/*     */   protected void updateWanderPath() {
/* 546 */     this.worldObj.theProfiler.startSection("stroll");
/*     */     
/* 548 */     PathEntity path_entity = null;
/* 549 */     float heaviest_weight = -100.0F;
/*     */     
/* 551 */     int entity_x = MathHelper.floor_double(this.posX);
/* 552 */     int entity_y = MathHelper.floor_double(this.posY);
/* 553 */     int entity_z = MathHelper.floor_double(this.posZ);
/*     */     
/* 555 */     int selected_block_x = entity_x;
/* 556 */     int selected_block_y = entity_y;
/* 557 */     int selected_block_z = entity_z;
/*     */     
/* 559 */     int attempts = 10;
/* 560 */     int horizontal_range = 6;
/* 561 */     int vertical_range = 3;
/*     */     
/* 563 */     boolean intensive_search = false;
/* 564 */     float max_path_distance = 10.0F;
/*     */     
/* 566 */     for (int var6 = 0; var6 < attempts; var6++) {
/*     */       
/* 568 */       int x = entity_x + this.rand.nextInt(horizontal_range * 2 + 1) - horizontal_range;
/* 569 */       int y = entity_y + this.rand.nextInt(vertical_range * 2 + 1) - vertical_range;
/* 570 */       int z = entity_z + this.rand.nextInt(horizontal_range * 2 + 1) - horizontal_range;
/*     */       
/* 572 */       float weight = getBlockPathWeight(x, y, z);
/*     */       
/* 574 */       if (path_entity == null || weight > heaviest_weight)
/*     */       {
/* 576 */         if (intensive_search) {
/*     */ 
/*     */           
/* 579 */           path_entity = this.worldObj.getEntityPathToXYZ(this, x, y, z, max_path_distance, true, false, avoidsPathingThroughWater(), true);
/*     */           
/* 581 */           if (path_entity != null) {
/* 582 */             heaviest_weight = weight;
/*     */           }
/*     */         } else {
/*     */           
/* 586 */           heaviest_weight = weight;
/*     */           
/* 588 */           selected_block_x = x;
/* 589 */           selected_block_y = y;
/* 590 */           selected_block_z = z;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 595 */     if (path_entity != null) {
/* 596 */       this.pathToEntity = path_entity;
/* 597 */     } else if (!intensive_search && heaviest_weight > -100.0F) {
/*     */       
/* 599 */       this.pathToEntity = this.worldObj.getEntityPathToXYZ(this, selected_block_x, selected_block_y, selected_block_z, max_path_distance, true, false, avoidsPathingThroughWater(), true);
/*     */     } 
/* 601 */     this.worldObj.theProfiler.endSection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void attackEntity(Entity par1Entity, float par2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(int par1, int par2, int par3) {
/* 616 */     return 0.0F;
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
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 640 */     int var1 = MathHelper.floor_double(this.posX);
/* 641 */     int var2 = MathHelper.floor_double(this.boundingBox.minY);
/* 642 */     int var3 = MathHelper.floor_double(this.posZ);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 648 */     return (super.getCanSpawnHere(perform_light_check) && getBlockPathWeight(var1, var2, var3) >= 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPath() {
/* 656 */     return (this.pathToEntity != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPathToEntity(PathEntity par1PathEntity) {
/* 664 */     this.pathToEntity = par1PathEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public PathEntity getPathToEntity() {
/* 669 */     return this.pathToEntity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getEntityToAttack() {
/* 677 */     if (isAIEnabled()) {
/* 678 */       Minecraft.setErrorMessage("Why is getEntityToAttack() being called for " + getEntityName());
/*     */     }
/* 680 */     if (this.entityToAttack != null && this.entityToAttack.isDead) {
/* 681 */       setEntityToAttack((Entity)null);
/*     */     }
/* 683 */     return this.entityToAttack;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEntityToAttack(Entity par1Entity) {
/* 692 */     if (isAIEnabled()) {
/* 693 */       Minecraft.setErrorMessage("Why is setEntityToAttack() being called for " + getEntityName());
/*     */     }
/* 695 */     if (par1Entity != null && par1Entity.isDead) {
/* 696 */       par1Entity = null;
/*     */     }
/* 698 */     this.entityToAttack = par1Entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_110173_bK() {
/* 703 */     return func_110176_b(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean func_110176_b(int par1, int par2, int par3) {
/* 708 */     return (this.maximumHomeDistance == -1.0F) ? true : ((this.homePosition.getDistanceSquared(par1, par2, par3) < this.maximumHomeDistance * this.maximumHomeDistance));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHomeArea(int par1, int par2, int par3, int par4) {
/* 713 */     this.homePosition.set(par1, par2, par3);
/* 714 */     this.maximumHomeDistance = par4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkCoordinates getHomePosition() {
/* 722 */     return this.homePosition;
/*     */   }
/*     */ 
/*     */   
/*     */   public float func_110174_bM() {
/* 727 */     return this.maximumHomeDistance;
/*     */   }
/*     */ 
/*     */   
/*     */   public void detachHome() {
/* 732 */     this.maximumHomeDistance = -1.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasHome() {
/* 740 */     return (this.maximumHomeDistance != -1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_110159_bB() {
/* 745 */     super.func_110159_bB();
/*     */     
/* 747 */     if (getLeashed() && getLeashedToEntity() != null && (getLeashedToEntity()).worldObj == this.worldObj) {
/*     */       
/* 749 */       Entity var1 = getLeashedToEntity();
/* 750 */       setHomeArea((int)var1.posX, (int)var1.posY, (int)var1.posZ, 5);
/* 751 */       float var2 = getDistanceToEntity(var1);
/*     */       
/* 753 */       if (this instanceof EntityTameable && ((EntityTameable)this).isSitting()) {
/*     */         
/* 755 */         if (var2 > 10.0F) {
/*     */           
/* 757 */           clearLeashed(true, true);
/* 758 */           this.worldObj.playSoundAtEntity(var1, "random.pop", 0.2F, 0.7F);
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 764 */       if (!this.field_110180_bt) {
/*     */         
/* 766 */         this.tasks.addTask(2, this.field_110178_bs);
/* 767 */         getNavigator().setAvoidsWater(false);
/* 768 */         this.field_110180_bt = true;
/*     */       } 
/*     */       
/* 771 */       func_142017_o(var2);
/*     */       
/* 773 */       if (var2 > 4.0F)
/*     */       {
/* 775 */         getNavigator().tryMoveToEntityLiving(var1, 1.0D);
/*     */       }
/*     */       
/* 778 */       if (var2 > 6.0F) {
/*     */         
/* 780 */         double var3 = (var1.posX - this.posX) / var2;
/* 781 */         double var5 = (var1.posY - this.posY) / var2;
/* 782 */         double var7 = (var1.posZ - this.posZ) / var2;
/* 783 */         this.motionX += var3 * Math.abs(var3) * 0.4D;
/* 784 */         this.motionY += var5 * Math.abs(var5) * 0.4D;
/* 785 */         this.motionZ += var7 * Math.abs(var7) * 0.4D;
/*     */       } 
/*     */       
/* 788 */       if (var2 > 10.0F)
/*     */       {
/* 790 */         clearLeashed(true, true);
/* 791 */         this.worldObj.playSoundAtEntity(var1, "random.pop", 0.2F, 0.7F);
/*     */       }
/*     */     
/* 794 */     } else if (!getLeashed() && this.field_110180_bt) {
/*     */       
/* 796 */       this.field_110180_bt = false;
/* 797 */       this.tasks.removeTask(this.field_110178_bs);
/* 798 */       getNavigator().setAvoidsWater(true);
/* 799 */       detachHome();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void func_142017_o(float par1) {}
/*     */   
/*     */   public EntityItem findTargetEntityItem(float max_distance) {
/* 807 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCreature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */