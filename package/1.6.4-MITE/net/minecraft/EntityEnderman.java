/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ 
/*     */ public class EntityEnderman
/*     */   extends EntityMob {
/*   9 */   private static final UUID attackingSpeedBoostModifierUUID = UUID.fromString("020E0DFB-87AE-4653-9556-831010E291A0");
/*  10 */   private static final AttributeModifier attackingSpeedBoostModifier = (new AttributeModifier(attackingSpeedBoostModifierUUID, "Attacking speed boost", 6.199999809265137D, 0)).setSaved(false);
/*  11 */   private static boolean[] carriableBlocks = new boolean[256];
/*     */ 
/*     */   
/*     */   private int teleportDelay;
/*     */ 
/*     */   
/*     */   private int stareTimer;
/*     */ 
/*     */   
/*     */   private Entity lastEntityToAttack;
/*     */ 
/*     */   
/*     */   private boolean isAggressive;
/*     */ 
/*     */   
/*     */   public EntityEnderman(World par1World) {
/*  27 */     super(par1World);
/*  28 */     setSize(0.6F, 2.9F);
/*  29 */     this.stepHeight = 1.0F;
/*     */     
/*  31 */     getNavigator().setAvoidsWater(true);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  36 */     super.applyEntityAttributes();
/*  37 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(40.0D);
/*  38 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);
/*     */     
/*  40 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(10.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  45 */     super.entityInit();
/*  46 */     this.dataWatcher.addObject(16, new Byte((byte)0));
/*  47 */     this.dataWatcher.addObject(17, new Byte((byte)0));
/*  48 */     this.dataWatcher.addObject(18, new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  56 */     super.writeEntityToNBT(par1NBTTagCompound);
/*  57 */     par1NBTTagCompound.setShort("carried", (short)getCarried());
/*  58 */     par1NBTTagCompound.setShort("carriedData", (short)getCarryingData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  66 */     super.readEntityFromNBT(par1NBTTagCompound);
/*  67 */     setCarried(par1NBTTagCompound.getShort("carried"));
/*  68 */     setCarryingData(par1NBTTagCompound.getShort("carriedData"));
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
/*     */   protected EntityPlayer findPlayerToAttack(float max_distance) {
/*  80 */     EntityPlayer var1 = super.findPlayerToAttack(64.0F);
/*     */     
/*  82 */     if (var1 != null)
/*     */     {
/*  84 */       if (shouldAttackPlayer(var1)) {
/*     */         
/*  86 */         this.isAggressive = true;
/*     */         
/*  88 */         if (this.stareTimer == 0)
/*     */         {
/*  90 */           this.worldObj.playSoundAtEntity(var1, "mob.endermen.stare", 1.0F, 1.0F);
/*     */         }
/*     */         
/*  93 */         if (this.stareTimer++ == 5)
/*     */         {
/*  95 */           this.stareTimer = 0;
/*  96 */           setScreaming(true);
/*  97 */           return var1;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 102 */         this.stareTimer = 0;
/*     */       } 
/*     */     }
/*     */     
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean shouldAttackPlayer(EntityPlayer par1EntityPlayer) {
/* 114 */     if (isDecoy()) {
/* 115 */       return false;
/*     */     }
/* 117 */     if (this.rand.nextInt(3) == 0 && par1EntityPlayer.hasCurse(Curse.endermen_aggro, true)) {
/* 118 */       return true;
/*     */     }
/* 120 */     if (isEnderPearlOrEye(par1EntityPlayer.getHeldItemStack())) {
/*     */       
/* 122 */       this.worldObj.playSoundAtEntity(par1EntityPlayer, "mob.endermen.stare", 1.0F, 1.0F);
/* 123 */       this.stareTimer = 5;
/*     */       
/* 125 */       return true;
/*     */     } 
/*     */     
/* 128 */     int num_ender_items = par1EntityPlayer.getNumItems(Item.enderPearl) + par1EntityPlayer.getNumItems(Item.eyeOfEnder);
/*     */     
/* 130 */     if (num_ender_items > 0 && this.rand.nextInt(2000) < num_ender_items) {
/*     */       
/* 132 */       this.worldObj.playSoundAtEntity(par1EntityPlayer, "mob.endermen.stare", 1.0F, 1.0F);
/* 133 */       this.stareTimer = 5;
/*     */       
/* 135 */       return true;
/*     */     } 
/*     */     
/* 138 */     ItemStack var2 = par1EntityPlayer.inventory.armorInventory[3];
/*     */     
/* 140 */     if (var2 != null && var2.itemID == Block.pumpkin.blockID)
/*     */     {
/* 142 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 146 */     Vec3 var3 = par1EntityPlayer.getLook(1.0F).normalize();
/* 147 */     Vec3 var4 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX - par1EntityPlayer.posX, this.boundingBox.minY + (this.height / 2.0F) - par1EntityPlayer.posY + par1EntityPlayer.getEyeHeight(), this.posZ - par1EntityPlayer.posZ);
/* 148 */     double var5 = var4.lengthVector();
/* 149 */     var4 = var4.normalize();
/* 150 */     double var7 = var3.dotProduct(var4);
/* 151 */     return (var7 > 1.0D - 0.025D / var5) ? par1EntityPlayer.canSeeEntity(this) : false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityItem findTargetEntityItem(float max_distance) {
/* 157 */     Iterator<EntityItem> i = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(max_distance, (max_distance * 0.25F), max_distance)).iterator();
/*     */     
/* 159 */     while (i.hasNext()) {
/*     */       
/* 161 */       EntityItem entity_item = i.next();
/*     */       
/* 163 */       if (entity_item.isWet() || (entity_item.isBurning() && isHarmedByFire())) {
/*     */         continue;
/*     */       }
/* 166 */       if (willPickupAsValuable(entity_item.getEntityItem())) {
/* 167 */         return entity_item;
/*     */       }
/*     */     } 
/* 170 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean isEnderPearlOrEye(ItemStack item_stack) {
/* 175 */     if (item_stack == null) {
/* 176 */       return false;
/*     */     }
/* 178 */     return (item_stack.getItem() == Item.enderPearl || item_stack.getItem() == Item.eyeOfEnder);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean willPickupAsValuable(ItemStack item_stack) {
/* 183 */     return (item_stack.getItemSubtype() == 0 && item_stack.getItemDamage() == 0 && isEnderPearlOrEye(item_stack));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/* 193 */     if (onServer() && isWet())
/*     */     {
/*     */       
/* 196 */       attackEntityFrom(new Damage(DamageSource.water, 1.0F));
/*     */     }
/*     */     
/* 199 */     if (this.lastEntityToAttack != this.entityToAttack) {
/*     */       
/* 201 */       AttributeInstance var1 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
/* 202 */       var1.removeModifier(attackingSpeedBoostModifier);
/*     */       
/* 204 */       if (this.entityToAttack != null)
/*     */       {
/* 206 */         var1.applyModifier(attackingSpeedBoostModifier);
/*     */       }
/*     */     } 
/*     */     
/* 210 */     this.lastEntityToAttack = this.entityToAttack;
/*     */ 
/*     */     
/* 213 */     if (!this.worldObj.isRemote && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing"))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 219 */       if (getCarried() == 0) {
/*     */         
/* 221 */         if (this.rand.nextInt(20) == 0) {
/*     */           
/* 223 */           int i = MathHelper.floor_double(this.posX - 2.0D + this.rand.nextDouble() * 4.0D);
/* 224 */           int var2 = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 3.0D);
/* 225 */           int var3 = MathHelper.floor_double(this.posZ - 2.0D + this.rand.nextDouble() * 4.0D);
/* 226 */           int var4 = this.worldObj.getBlockId(i, var2, var3);
/*     */           
/* 228 */           if (carriableBlocks[var4]) {
/*     */             
/* 230 */             setCarried(this.worldObj.getBlockId(i, var2, var3));
/* 231 */             setCarryingData(this.worldObj.getBlockMetadata(i, var2, var3));
/* 232 */             this.worldObj.setBlock(i, var2, var3, 0);
/*     */             
/* 234 */             Block block_above = this.worldObj.getBlock(i, var2 + 1, var3);
/*     */             
/* 236 */             if (block_above instanceof BlockUnderminable) {
/* 237 */               ((BlockUnderminable)block_above).tryToFall(this.worldObj, i, var2 + 1, var3);
/*     */             }
/*     */           } 
/*     */         } 
/* 241 */       } else if (this.rand.nextInt(2000) == 0) {
/*     */         
/* 243 */         int i = MathHelper.floor_double(this.posX - 1.0D + this.rand.nextDouble() * 2.0D);
/* 244 */         int var2 = MathHelper.floor_double(this.posY + this.rand.nextDouble() * 2.0D);
/* 245 */         int var3 = MathHelper.floor_double(this.posZ - 1.0D + this.rand.nextDouble() * 2.0D);
/* 246 */         int var4 = this.worldObj.getBlockId(i, var2, var3);
/* 247 */         int var5 = this.worldObj.getBlockId(i, var2 - 1, var3);
/*     */         
/* 249 */         if (var4 == 0 && var5 > 0 && Block.blocksList[var5].renderAsNormalBlock()) {
/*     */           
/* 251 */           this.worldObj.setBlock(i, var2, var3, getCarried(), getCarryingData(), 3);
/* 252 */           setCarried(0);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/* 257 */     for (int var6 = 0; var6 < 2; var6++)
/*     */     {
/*     */       
/* 260 */       this.worldObj.spawnParticle(EnumParticle.portal_underworld, this.posX + (this.rand.nextDouble() - 0.5D) * this.width, this.posY + this.rand.nextDouble() * this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
/*     */     }
/*     */     
/* 263 */     boolean has_teleported = false;
/*     */ 
/*     */     
/* 266 */     if (this.entityToAttack == null && this.worldObj.isDaytime() && !this.worldObj.isRemote && this.rand.nextInt(4) == 0) {
/*     */       
/* 268 */       float var7 = getBrightness(1.0F);
/*     */       
/* 270 */       if (var7 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)) && this.rand.nextFloat() * 30.0F < (var7 - 0.4F) * 2.0F) {
/*     */         
/* 272 */         this.entityToAttack = null;
/* 273 */         setScreaming(false);
/* 274 */         this.isAggressive = false;
/*     */ 
/*     */         
/* 277 */         if (teleportRandomly()) {
/* 278 */           has_teleported = true;
/*     */         }
/*     */       } 
/*     */     } 
/* 282 */     if (onServer() && !has_teleported && getTicksExistedWithOffset() % 20 == 0 && this.rand.nextInt(10) == 0 && tryTeleportToValuableItem()) {
/* 283 */       has_teleported = true;
/*     */     }
/*     */     
/* 286 */     if (onServer() && (isWet() || isBurning())) {
/*     */       
/* 288 */       this.entityToAttack = null;
/* 289 */       setScreaming(false);
/* 290 */       this.isAggressive = false;
/* 291 */       teleportRandomly();
/*     */     } 
/*     */     
/* 294 */     if (isScreaming() && !this.isAggressive && this.rand.nextInt(100) == 0)
/*     */     {
/* 296 */       setScreaming(false);
/*     */     }
/*     */     
/* 299 */     this.isJumping = false;
/*     */     
/* 301 */     if (this.entityToAttack != null)
/*     */     {
/* 303 */       faceEntity(this.entityToAttack, 100.0F, 100.0F);
/*     */     }
/*     */     
/* 306 */     if (!this.worldObj.isRemote && isEntityAlive())
/*     */     {
/* 308 */       if (this.entityToAttack != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 324 */         if (++this.teleportDelay > 30)
/*     */         {
/* 326 */           if (this.rand.nextInt(2) == 0)
/*     */           {
/* 328 */             if (this.rand.nextInt(3) == 0) {
/* 329 */               teleportRandomly();
/*     */             } else {
/* 331 */               teleportToEntity(this.entityToAttack);
/*     */             } 
/*     */           }
/* 334 */           this.teleportDelay = 0;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 339 */         setScreaming(false);
/* 340 */         this.teleportDelay = 0;
/*     */       } 
/*     */     }
/*     */     
/* 344 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   private EntityItem getNearestObtainableValuableItem() {
/* 349 */     EntityItem nearest_obtainable_valuable_item = null;
/* 350 */     double distance_sq_to_nearest_obtainable_valuable_item = 0.0D;
/*     */     
/* 352 */     List items = this.worldObj.getEntitiesWithinAABB(EntityItem.class, this.boundingBox.expand(16.0D, 8.0D, 16.0D));
/*     */     
/* 354 */     Iterator<EntityItem> i = items.iterator();
/*     */     
/* 356 */     while (i.hasNext()) {
/*     */       
/* 358 */       EntityItem entity_item = i.next();
/*     */       
/* 360 */       if (entity_item.isDead) {
/*     */         continue;
/*     */       }
/* 363 */       if (entity_item.isWet()) {
/*     */         continue;
/*     */       }
/* 366 */       if (entity_item.isBurning() && isHarmedByFire()) {
/*     */         continue;
/*     */       }
/* 369 */       ItemStack item_stack = entity_item.getEntityItem();
/*     */       
/* 371 */       if (!willPickupAsValuable(item_stack)) {
/*     */         continue;
/*     */       }
/* 374 */       int x = entity_item.getBlockPosX();
/* 375 */       int y = entity_item.getBlockPosY();
/* 376 */       int z = entity_item.getBlockPosZ();
/*     */       
/* 378 */       if (!this.worldObj.isAirOrPassableBlock(x, y + 1, z, false) || !this.worldObj.isAirOrPassableBlock(x, y + 2, z, false)) {
/*     */         continue;
/*     */       }
/* 381 */       double distance_sq = getDistanceSqToEntity(entity_item);
/*     */       
/* 383 */       if (nearest_obtainable_valuable_item == null || distance_sq < distance_sq_to_nearest_obtainable_valuable_item) {
/*     */         
/* 385 */         nearest_obtainable_valuable_item = entity_item;
/* 386 */         distance_sq_to_nearest_obtainable_valuable_item = distance_sq;
/*     */       } 
/*     */     } 
/*     */     
/* 390 */     return nearest_obtainable_valuable_item;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean tryTeleportToValuableItem() {
/* 396 */     if (onClient()) {
/* 397 */       Minecraft.setErrorMessage("tryTeleportToValuableItem: called on client");
/*     */     }
/* 399 */     if (isWet() || isBurning()) {
/* 400 */       return false;
/*     */     }
/* 402 */     EntityItem entity_item = getNearestObtainableValuableItem();
/*     */     
/* 404 */     if (entity_item == null) {
/* 405 */       return false;
/*     */     }
/* 407 */     int x = entity_item.getBlockPosX();
/* 408 */     int y = entity_item.getBlockPosY();
/* 409 */     int z = entity_item.getBlockPosZ();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 426 */     return teleportTo(x + 0.5D, this.worldObj.getBlockCollisionTopY(x, y, z, this), z + 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean teleportRandomly() {
/* 434 */     if (onClient()) {
/* 435 */       Minecraft.setErrorMessage("teleportRandomly: called on client");
/*     */     }
/* 437 */     if (isDecoy()) {
/* 438 */       return false;
/*     */     }
/* 440 */     if (tryTeleportToValuableItem()) {
/* 441 */       return true;
/*     */     }
/* 443 */     double var1 = this.posX + (this.rand.nextDouble() - 0.5D) * 64.0D;
/* 444 */     double var3 = this.posY + (this.rand.nextInt(64) - 32);
/* 445 */     double var5 = this.posZ + (this.rand.nextDouble() - 0.5D) * 64.0D;
/* 446 */     return teleportTo(var1, var3, var5);
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
/*     */   protected boolean teleportToEntity(Entity par1Entity) {
/* 465 */     int x = par1Entity.getBlockPosX() + this.rand.nextInt(7) - 3;
/* 466 */     int y = par1Entity.getBlockPosY() + 3;
/* 467 */     int z = par1Entity.getBlockPosZ() + this.rand.nextInt(7) - 3;
/*     */     
/* 469 */     for (int dy = 0; dy >= -6;) {
/*     */       
/* 471 */       if (this.worldObj.isAirOrPassableBlock(x, y - 1, z, false)) {
/* 472 */         y--;
/*     */         
/*     */         dy--;
/*     */       } 
/*     */     } 
/* 477 */     return teleportTo((x + 0.5F), (y + 0.1F), (z + 0.5F));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean teleportTo(double par1, double par3, double par5) {
/* 485 */     double var7 = this.posX;
/* 486 */     double var9 = this.posY;
/* 487 */     double var11 = this.posZ;
/* 488 */     this.posX = par1;
/* 489 */     this.posY = par3;
/* 490 */     this.posZ = par5;
/* 491 */     boolean var13 = false;
/* 492 */     int var14 = MathHelper.floor_double(this.posX);
/* 493 */     int var15 = MathHelper.floor_double(this.posY);
/* 494 */     int var16 = MathHelper.floor_double(this.posZ);
/*     */ 
/*     */     
/* 497 */     if (this.worldObj.blockExists(var14, var15, var16)) {
/*     */       
/* 499 */       boolean var17 = false;
/*     */       
/* 501 */       while (!var17 && var15 > 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 506 */         if (this.worldObj.isBlockSolid(var14, var15 - 1, var16)) {
/*     */           
/* 508 */           var17 = true;
/*     */           
/*     */           continue;
/*     */         } 
/* 512 */         this.posY--;
/* 513 */         var15--;
/*     */       } 
/*     */ 
/*     */       
/* 517 */       if (var17) {
/*     */         
/* 519 */         setPosition(this.posX, this.posY, this.posZ);
/*     */         
/* 521 */         if (this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox))
/*     */         {
/* 523 */           var13 = true;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 528 */     if (!var13) {
/*     */       
/* 530 */       setPosition(var7, var9, var11);
/* 531 */       return false;
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
/* 552 */     int x = MathHelper.floor_double(this.posX);
/* 553 */     int y = MathHelper.floor_double(this.posY);
/* 554 */     int z = MathHelper.floor_double(this.posZ);
/*     */     
/* 556 */     double distance = World.getDistanceFromDeltas(this.posX - var7, this.posY - var9, this.posZ - var11);
/*     */     
/* 558 */     this.worldObj.blockFX(EnumBlockFX.particle_trail, x, y, z, (new SignalData()).setByte(EnumParticle.portal_underworld.ordinal()).setShort((int)(8.0D * distance)).setApproxPosition(MathHelper.floor_double(var7), MathHelper.floor_double(var9), MathHelper.floor_double(var11)));
/* 559 */     this.worldObj.blockFX(EnumBlockFX.particle_trail, x, y + 1, z, (new SignalData()).setByte(EnumParticle.portal_underworld.ordinal()).setShort((int)(8.0D * distance)).setApproxPosition(MathHelper.floor_double(var7), MathHelper.floor_double(var9 + 1.0D), MathHelper.floor_double(var11)));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 564 */     this.worldObj.playSoundEffect(var7, var9, var11, "mob.endermen.portal", 1.0F, 1.0F);
/*     */     
/* 566 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 575 */     return isScreaming() ? "mob.endermen.scream" : "mob.endermen.idle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 583 */     return "mob.endermen.hit";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 591 */     return "mob.endermen.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 599 */     return Item.enderPearl.itemID;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 623 */     int item_id = getDropItemId();
/*     */     
/* 625 */     if (item_id > 0) {
/*     */       
/* 627 */       int num_drops = this.rand.nextInt(2 + damage_source.getLootingModifier());
/*     */       
/* 629 */       for (int i = 0; i < num_drops; i++) {
/* 630 */         dropItem(item_id, 1);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCarried(int par1) {
/* 639 */     this.dataWatcher.updateObject(16, Byte.valueOf((byte)(par1 & 0xFF)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCarried() {
/* 647 */     return this.dataWatcher.getWatchableObjectByte(16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCarryingData(int par1) {
/* 655 */     this.dataWatcher.updateObject(17, Byte.valueOf((byte)(par1 & 0xFF)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCarryingData() {
/* 663 */     return this.dataWatcher.getWatchableObjectByte(17);
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 711 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 716 */     if (result != null && result.entityWasNegativelyAffected()) {
/*     */       
/* 718 */       setScreaming(true);
/*     */       
/* 720 */       if (damage.getResponsibleEntity() instanceof EntityPlayer) {
/* 721 */         this.isAggressive = true;
/*     */       }
/* 723 */       if (damage.isIndirect()) {
/*     */         
/* 725 */         this.isAggressive = false;
/*     */         
/* 727 */         for (int var3 = 0; var3 < 64; var3++) {
/*     */           
/* 729 */           if (teleportRandomly()) {
/* 730 */             return result.setEntityWasAffected();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 735 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isScreaming() {
/* 740 */     return (this.dataWatcher.getWatchableObjectByte(18) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScreaming(boolean par1) {
/* 745 */     this.dataWatcher.updateObject(18, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityBiologicallyAlive() {
/* 750 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 755 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tryAddArrowToContainedItems(EntityArrow entity_arrow) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFrenzied() {
/* 767 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   static {
/* 772 */     carriableBlocks[Block.grass.blockID] = true;
/* 773 */     carriableBlocks[Block.dirt.blockID] = true;
/* 774 */     carriableBlocks[Block.sand.blockID] = true;
/* 775 */     carriableBlocks[Block.gravel.blockID] = true;
/* 776 */     carriableBlocks[Block.plantYellow.blockID] = true;
/* 777 */     carriableBlocks[Block.plantRed.blockID] = true;
/* 778 */     carriableBlocks[Block.mushroomBrown.blockID] = true;
/* 779 */     carriableBlocks[Block.mushroomRed.blockID] = true;
/* 780 */     carriableBlocks[Block.tnt.blockID] = true;
/* 781 */     carriableBlocks[Block.cactus.blockID] = true;
/* 782 */     carriableBlocks[Block.blockClay.blockID] = true;
/* 783 */     carriableBlocks[Block.pumpkin.blockID] = true;
/* 784 */     carriableBlocks[Block.melon.blockID] = true;
/* 785 */     carriableBlocks[Block.mycelium.blockID] = true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityEnderman.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */