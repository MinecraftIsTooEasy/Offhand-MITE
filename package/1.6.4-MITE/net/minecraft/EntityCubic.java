/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class EntityCubic
/*     */   extends EntityLiving
/*     */   implements IMob
/*     */ {
/*     */   public float squishAmount;
/*     */   public float squishFactor;
/*     */   public float prevSquishFactor;
/*     */   private int slimeJumpDelay;
/*     */   private EntityItem last_targetted_item;
/*     */   private EntityLivingBase last_targetted_non_player;
/*     */   
/*     */   public EntityCubic(World par1World) {
/*  22 */     super(par1World);
/*     */     
/*  24 */     getNavigator().setAvoidsWater(true);
/*     */     
/*  26 */     int var2 = 1 << this.rand.nextInt(3);
/*  27 */     this.yOffset = 0.0F;
/*     */     
/*  29 */     this.slimeJumpDelay = getJumpDelay((Entity)null);
/*  30 */     setSize(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  35 */     super.entityInit();
/*  36 */     this.dataWatcher.addObject(16, new Byte((byte)1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onSendToClient(Packet24MobSpawn packet) {
/*  41 */     if (onClient()) {
/*  42 */       updateSize();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setSize(int par1) {
/*  47 */     this.dataWatcher.updateObject(16, new Byte((byte)par1));
/*     */     
/*  49 */     updateSize();
/*  50 */     setPosition(this.posX, this.posY, this.posZ);
/*  51 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute((par1 * par1));
/*  52 */     setHealth(getMaxHealth());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateSize() {
/*  58 */     setSize(0.5F * getSize(), 0.5F * getSize());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  66 */     return this.dataWatcher.getWatchableObjectByte(16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  74 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  75 */     par1NBTTagCompound.setInteger("Size", getSize() - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  84 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  85 */     setSize(par1NBTTagCompound.getInteger("Size") + 1);
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
/*     */   public abstract EnumParticle getSquishParticle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getJumpSound() {
/* 110 */     return "mob.slime." + ((getSize() > 1) ? "big" : "small");
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
/*     */   public void onUpdate() {
/* 129 */     if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0 && getSize() > 0)
/*     */     {
/*     */       
/* 132 */       setDead();
/*     */     }
/*     */     
/* 135 */     this.prevSquishFactor = this.squishFactor;
/* 136 */     this.squishFactor += (this.squishAmount - this.squishFactor) * 0.5F;
/*     */     
/* 138 */     boolean var1 = this.onGround;
/* 139 */     super.onUpdate();
/*     */     
/* 141 */     if (isOoze() && var1) {
/*     */       
/* 143 */       this.squishAmount = 0.0F + (float)Math.sin((getTicksExistedWithOffset() / 3.0F)) * 0.1F;
/*     */       return;
/*     */     } 
/* 146 */     if (isGelatinousCube() && getAsEntityGelatinousCube().isFeeding()) {
/*     */       
/* 148 */       this.squishAmount = 0.0F + (float)Math.sin((getTicksExistedWithOffset() / 5.0F)) * 0.1F;
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 154 */     if (this.onGround && !var1) {
/*     */       
/* 156 */       int var2 = getSize();
/*     */       
/* 158 */       for (int var3 = 0; var3 < var2 * 8; var3++) {
/*     */         
/* 160 */         float var4 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 161 */         float var5 = this.rand.nextFloat() * 0.5F + 0.5F;
/* 162 */         float var6 = MathHelper.sin(var4) * var2 * 0.5F * var5;
/* 163 */         float var7 = MathHelper.cos(var4) * var2 * 0.5F * var5;
/* 164 */         this.worldObj.spawnParticle(getSquishParticle(), this.posX + var6, this.boundingBox.minY, this.posZ + var7, 0.0D, 0.0D, 0.0D);
/*     */       } 
/*     */       
/* 167 */       if (makesSoundOnLand())
/*     */       {
/*     */         
/* 170 */         playSound(getJumpSound(), getSoundVolume(getJumpSound()), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) / 0.8F);
/*     */       }
/*     */       
/* 173 */       this.squishAmount = -0.5F;
/*     */     }
/* 175 */     else if (!this.onGround && var1) {
/*     */       
/* 177 */       this.squishAmount = 1.0F;
/*     */     } 
/*     */     
/* 180 */     alterSquishAmount();
/*     */     
/* 182 */     if (this.worldObj.isRemote) {
/*     */       
/* 184 */       int var2 = getSize();
/*     */       
/* 186 */       updateSize();
/*     */     } 
/*     */   }
/*     */   
/*     */   public abstract boolean attacksAnimals();
/*     */   
/*     */   public abstract boolean attacksVillagers();
/*     */   
/*     */   public boolean seeksItems() {
/* 195 */     return isGelatinousCube();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final boolean preysUpon(Entity entity) {
/* 203 */     return ((attacksAnimals() && entity.isTrueAnimal()) || (attacksVillagers() && entity instanceof EntityVillager));
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
/*     */   public boolean triesToDamageOnContact(Entity entity) {
/* 216 */     return (entity instanceof EntityPlayer || preysUpon(entity));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityItem getClosestDissolvableEntityItem(double max_distance, boolean requires_line_of_sight, boolean requires_path) {
/* 221 */     List items = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(max_distance, max_distance, max_distance));
/*     */     
/* 223 */     if (items.isEmpty()) {
/* 224 */       return null;
/*     */     }
/* 226 */     EntityItem closest_item = null;
/* 227 */     float closest_distance = 0.0F;
/*     */     
/* 229 */     Iterator<EntityItem> i = items.iterator();
/*     */     
/* 231 */     while (i.hasNext()) {
/*     */       
/* 233 */       EntityItem entity_item = i.next();
/*     */       
/* 235 */       if (entity_item.isDead || entity_item.getHealth() <= 0 || !entity_item.getEntityItem().isHarmedBy(getAsEntityGelatinousCube().getDamageTypeVsItems())) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 240 */       int x = entity_item.getBlockPosX();
/* 241 */       int y = entity_item.getBlockPosY();
/* 242 */       int z = entity_item.getBlockPosZ();
/*     */       
/* 244 */       Block block = this.worldObj.getBlock(x, y, z);
/*     */       
/* 246 */       if (block != null && block.isSolid(this.worldObj.getBlockMetadata(x, y, z)) && getAsEntityGelatinousCube().getDissolvePeriod(block, x, y, z) == -1) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 251 */       if (requires_line_of_sight && !getEntitySenses().canSee(entity_item)) {
/*     */         continue;
/*     */       }
/* 254 */       if (requires_path && !canPathTo(entity_item.getBlockPosX(), entity_item.getBlockPosY(), entity_item.getBlockPosZ(), (int)max_distance)) {
/*     */         continue;
/*     */       }
/* 257 */       float distance = getDistanceToEntity(entity_item);
/*     */       
/* 259 */       if (closest_item == null || distance < closest_distance) {
/*     */         
/* 261 */         closest_item = entity_item;
/* 262 */         closest_distance = distance;
/*     */       } 
/*     */     } 
/*     */     
/* 266 */     return closest_item;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateEntityActionState() {
/* 271 */     tryDespawnEntity();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 278 */     Entity var1 = findPlayerToAttack(isOoze() ? 32.0F : 16.0F);
/*     */ 
/*     */ 
/*     */     
/* 282 */     if (var1 == null) {
/* 283 */       var1 = this.worldObj.getClosestVulnerablePlayer(this, 8.0D, false);
/*     */     }
/* 285 */     if (var1 == null && isGelatinousCube()) {
/*     */       
/* 287 */       EntityGelatinousCube gelatinous_cube = getAsEntityGelatinousCube();
/*     */       
/* 289 */       if (!gelatinous_cube.isBlockFeedingCountdownAboveZero()) {
/*     */         
/* 291 */         if (seeksItems()) {
/*     */           
/* 293 */           if (getTicksExistedWithOffset() % 20 == 0) {
/* 294 */             this.last_targetted_item = getClosestDissolvableEntityItem(8.0D, true, false);
/*     */           }
/* 296 */           if (this.last_targetted_item != null)
/*     */           {
/* 298 */             if (this.last_targetted_item.isDead || this.last_targetted_item.getHealth() <= 0.0F) {
/* 299 */               this.last_targetted_item = null;
/*     */             } else {
/* 301 */               var1 = this.last_targetted_item;
/*     */             } 
/*     */           }
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 319 */         if (var1 == null && !gelatinous_cube.isFeeding()) {
/*     */           
/* 321 */           if (getTicksExistedWithOffset() % 20 == 0) {
/* 322 */             this.last_targetted_non_player = this.worldObj.getClosestPrey(this, 8.0D, false, false);
/*     */           }
/* 324 */           if (this.last_targetted_non_player != null)
/*     */           {
/* 326 */             if (this.last_targetted_non_player.isDead || this.last_targetted_non_player.getHealth() <= 0.0F) {
/* 327 */               this.last_targetted_non_player = null;
/*     */             } else {
/* 329 */               var1 = this.last_targetted_non_player;
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 335 */     boolean move_like_an_ooze = (isOoze() || (isGelatinousCube() && var1 instanceof EntityItem && this.boundingBox.intersectsWith(var1.boundingBox.copy().scaleXZ(4.0D))));
/*     */     
/* 337 */     boolean move_slowly = false;
/*     */     
/* 339 */     if (!move_like_an_ooze && isGelatinousCube())
/*     */     {
/* 341 */       if (this.worldObj.doesBBIntersectWithBlockCollisionBounds(this.boundingBox.translateCopy(0.0D, 0.25D, 0.0D))) {
/*     */         
/* 343 */         move_like_an_ooze = true;
/* 344 */         move_slowly = true;
/*     */       } 
/*     */     }
/*     */     
/* 348 */     if (var1 != null)
/*     */     {
/* 350 */       faceEntity(var1, 10.0F, 20.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 358 */     if (isGelatinousCube())
/*     */     {
/* 360 */       if (var1 instanceof EntityPlayer) {
/*     */         
/* 362 */         getAsEntityGelatinousCube().setBlockFeedingCountdown(0);
/* 363 */         getAsEntityGelatinousCube().setItemFeedingCountdown(0);
/*     */       }
/*     */       else {
/*     */         
/* 367 */         boolean is_touching_target_item = (var1 instanceof EntityItem && this.boundingBox.intersectsWith(var1.boundingBox));
/*     */         
/* 369 */         if (is_touching_target_item || getAsEntityGelatinousCube().isFeeding()) {
/*     */           
/* 371 */           if (this.onGround) {
/*     */             
/* 373 */             this.moveStrafing = 0.0F;
/*     */             
/* 375 */             if (!is_touching_target_item || this.isCollidedHorizontally || this.boundingBox.copy().scaleXZ(0.5D).intersectsWith(var1.boundingBox)) {
/* 376 */               this.moveForward = 0.0F;
/*     */             }
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 396 */     if (var1 != null && this.slimeJumpDelay > getJumpDelay(var1)) {
/* 397 */       this.slimeJumpDelay = getJumpDelay(var1);
/*     */     }
/* 399 */     if (move_like_an_ooze) {
/*     */       
/* 401 */       this.moveStrafing = 0.0F;
/* 402 */       this.moveForward = (var1 == null) ? 0.15F : 0.25F;
/*     */       
/* 404 */       if (move_slowly) {
/* 405 */         this.moveForward *= 0.65F;
/*     */       }
/* 407 */     } else if (this.onGround && this.slimeJumpDelay-- <= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 416 */       this.slimeJumpDelay = getJumpDelay(var1);
/*     */       
/* 418 */       this.isJumping = true;
/*     */       
/* 420 */       if (makesSoundOnJump())
/*     */       {
/*     */         
/* 423 */         playSound(getJumpSound(), getSoundVolume(getJumpSound()), ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F) * 0.8F);
/*     */       }
/*     */       
/* 426 */       this.moveStrafing = 1.0F - this.rand.nextFloat() * 2.0F;
/* 427 */       this.moveForward = (1 * getSize());
/*     */     }
/*     */     else {
/*     */       
/* 431 */       this.isJumping = false;
/*     */       
/* 433 */       if (this.onGround)
/*     */       {
/* 435 */         this.moveStrafing = this.moveForward = 0.0F;
/*     */       }
/*     */     } 
/*     */     
/* 439 */     if (this.onGround && (isAcidic() || isMagmaCube())) {
/*     */       
/* 441 */       BlockInfo[] infos = getBlocksBelow();
/*     */       
/* 443 */       for (int i = 0; i < infos.length; i++) {
/*     */         
/* 445 */         BlockInfo info = infos[i];
/*     */         
/* 447 */         if (info != null) {
/*     */ 
/*     */           
/* 450 */           info.block.setBlockBoundsBasedOnStateAndNeighbors(this.worldObj, info.x, info.y, info.z);
/*     */           
/* 452 */           if (this.boundingBox.minY - info.y - info.block.getBlockBoundsMaxY(Minecraft.getThreadIndex()) <= 0.01D)
/*     */           {
/*     */             
/* 455 */             if (this.worldObj.getBlock(info.x, info.y + 1, info.z) != Block.snow)
/*     */             {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 464 */               info.block.onContactWithAcid(this.worldObj, info.x, info.y, info.z, EnumFace.TOP, false); }  } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void alterSquishAmount() {
/* 471 */     this.squishAmount *= 0.6F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getJumpDelay(Entity paramEntity);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract EntityCubic createInstance();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDead() {
/* 498 */     if (isBurning()) {
/*     */       
/* 500 */       super.setDead();
/*     */       
/*     */       return;
/*     */     } 
/* 504 */     int var1 = getSize();
/*     */     
/* 506 */     if (!this.worldObj.isRemote && var1 > 1 && getHealth() <= 0.0F) {
/*     */       
/* 508 */       int var2 = 2 + this.rand.nextInt(3);
/*     */       
/* 510 */       for (int var3 = 0; var3 < var2; var3++) {
/*     */         
/* 512 */         float var4 = ((var3 % 2) - 0.5F) * var1 / 4.0F;
/* 513 */         float var5 = ((var3 / 2) - 0.5F) * var1 / 4.0F;
/*     */         
/* 515 */         EntityCubic var6 = createInstance();
/* 516 */         var6.setSize(var1 / 2);
/* 517 */         var6.setLocationAndAngles(this.posX + var4, this.posY + 0.5D, this.posZ + var5, this.rand.nextFloat() * 360.0F, 0.0F);
/* 518 */         this.worldObj.spawnEntityInWorld(var6);
/*     */       } 
/*     */     } 
/*     */     
/* 522 */     super.setDead();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepelledByCollisionWithPlayer() {
/* 527 */     return false;
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
/*     */   private float getReachSquared() {
/* 548 */     int size = getSize();
/*     */     
/* 550 */     float reach = size * 0.6F;
/*     */     
/* 552 */     if (size == 1) {
/* 553 */       reach *= 1.1F;
/* 554 */     } else if (size == 2) {
/* 555 */       reach *= 0.9F;
/* 556 */     } else if (size >= 3) {
/* 557 */       reach *= 0.7F;
/*     */     } 
/* 559 */     return reach * reach;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onCollideWithPlayer(EntityPlayer player) {
/* 564 */     if (getDistanceSqToEntity(player) <= getReachSquared() && canSeeEntity(player)) {
/*     */       
/* 566 */       if (slowsPlayerOnContact()) {
/* 567 */         player.collided_with_gelatinous_cube = true;
/*     */       }
/* 569 */       if (onServer()) {
/* 570 */         attackEntityAsMob(player);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void collideWithEntity(Entity entity) {
/* 576 */     if (onServer() && triesToDamageOnContact(entity) && !entity.isEntityPlayer())
/*     */     {
/* 578 */       if (getDistanceSqToEntity(entity) <= getReachSquared() && canSeeEntity(entity)) {
/* 579 */         attackEntityAsMob(entity);
/*     */       }
/*     */     }
/* 582 */     super.collideWithEntity(entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 587 */     if (!triesToDamageOnContact(target)) {
/* 588 */       return null;
/*     */     }
/* 590 */     EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this), getAttackStrength()));
/*     */     
/* 592 */     if (result == null || result.entityWasDestroyed()) {
/* 593 */       return result;
/*     */     }
/* 595 */     if (result.entityWasNegativelyAffectedButNotDestroyed()) {
/*     */       
/* 597 */       if (isGelatinousCube()) {
/*     */         
/* 599 */         if (target.isEntityPlayer()) {
/* 600 */           target.getAsPlayer().dealDamageToInventory(getAsEntityGelatinousCube().getDamageTypeVsItems(), 0.05F * getSize(), getAttackStrength(), true);
/*     */         }
/* 602 */         if (isBlob() && result.entityLostHealth()) {
/* 603 */           target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
/*     */         }
/*     */       } 
/* 606 */       if (isMagmaCube() && this.rand.nextInt(10) < getSize() * 2) {
/* 607 */         target.setFire(getSize() * 3);
/*     */       }
/*     */     } 
/* 610 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean slowsPlayerOnContact() {
/* 616 */     return true;
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
/*     */   public abstract int getAttackStrengthMultiplierForType();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final int getAttackStrength() {
/* 637 */     return getSize() * getAttackStrengthMultiplierForType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 645 */     return "mob.slime." + ((getSize() > 1) ? "big" : "small");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 653 */     return "mob.slime." + ((getSize() > 1) ? "big" : "small");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 662 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 671 */     if (isGelatinousCube() && !isSlime()) {
/*     */       
/* 673 */       if (isAcidic() && getBlockBelow() != Block.stone) {
/* 674 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 684 */       return ((!perform_light_check || isValidLightLevel()) && super.getCanSpawnHere(perform_light_check));
/*     */     } 
/*     */     
/* 687 */     Chunk var1 = this.worldObj.getChunkFromBlockCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
/*     */     
/* 689 */     if (this.worldObj.getWorldInfo().getTerrainType() == WorldType.FLAT && this.rand.nextInt(4) != 1)
/*     */     {
/* 691 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 695 */     if (getSize() == 1 || this.worldObj.difficultySetting > 0) {
/*     */       
/* 697 */       BiomeGenBase var2 = this.worldObj.getBiomeGenForCoords(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posZ));
/*     */       
/* 699 */       if (var2 == BiomeGenBase.swampland && this.posY > 50.0D && this.posY < 70.0D && this.rand.nextFloat() < 0.5F && this.rand.nextFloat() < this.worldObj.getCurrentMoonPhaseFactor() && this.worldObj.getBlockLightValue(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) <= this.rand.nextInt(8))
/*     */       {
/*     */         
/* 702 */         return super.getCanSpawnHere(perform_light_check);
/*     */       }
/*     */       
/* 705 */       if (this.rand.nextInt(10) == 0 && var1.getRandomWithSeed(987234911L).nextInt(10) == 0 && this.posY < 40.0D)
/*     */       {
/*     */         
/* 708 */         return super.getCanSpawnHere(perform_light_check);
/*     */       }
/*     */     } 
/*     */     
/* 712 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 723 */     return 0.2F * getSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVerticalFaceSpeed() {
/* 732 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean makesSoundOnJump() {
/* 740 */     return (getSize() > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean makesSoundOnLand() {
/* 748 */     return (getSize() > 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean breathesAir() {
/* 753 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract int getExperienceValue();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 765 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 770 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeDamagedByCacti() {
/* 775 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isGelatinousCube() {
/* 780 */     return this instanceof EntityGelatinousCube;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityGelatinousCube getAsEntityGelatinousCube() {
/* 785 */     return (EntityGelatinousCube)this;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isSlime() {
/* 790 */     return this instanceof EntitySlime;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isJelly() {
/* 795 */     return this instanceof EntityJelly;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isBlob() {
/* 800 */     return this instanceof EntityBlob;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isOoze() {
/* 805 */     return this instanceof EntityOoze;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isPudding() {
/* 810 */     return this instanceof EntityPudding;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isMagmaCube() {
/* 815 */     return this instanceof EntityMagmaCube;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean hasPepsin() {
/* 821 */     return (isSlime() || isJelly() || isBlob());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final boolean isAcidic() {
/* 827 */     return (isOoze() || isPudding());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOnLadder() {
/* 832 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRenderSizeModifier() {
/* 837 */     return getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isHarmedByPepsin() {
/* 842 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isHarmedByAcid() {
/* 847 */     if (isPudding() || isMagmaCube()) {
/* 848 */       return false;
/*     */     }
/* 850 */     return super.isHarmedByAcid();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCubic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */