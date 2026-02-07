/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityOcelot
/*     */   extends EntityTameable
/*     */ {
/*     */   private EntityAITempt aiTempt;
/*     */   
/*     */   public EntityOcelot(World par1World) {
/*  12 */     super(par1World);
/*  13 */     setSize(0.6F, 0.8F);
/*  14 */     getNavigator().setAvoidsWater(true);
/*  15 */     this.tasks.addTask(1, new EntityAISwimming(this));
/*  16 */     this.tasks.addTask(2, this.aiSit);
/*  17 */     this.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6D, Item.fishRaw.itemID, true));
/*  18 */     this.tasks.addTask(3, this.aiTempt = new EntityAITempt(this, 0.6D, Item.fishLargeRaw.itemID, true));
/*  19 */     this.tasks.addTask(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
/*  20 */     this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 5.0F));
/*  21 */     this.tasks.addTask(6, new EntityAIOcelotSit(this, 1.33D));
/*  22 */     this.tasks.addTask(7, new EntityAILeapAtTarget(this, 0.3F));
/*  23 */     this.tasks.addTask(8, new EntityAIOcelotAttack(this));
/*  24 */     this.tasks.addTask(9, new EntityAIMate(this, 0.8D));
/*  25 */     this.tasks.addTask(10, new EntityAIWander(this, 0.8D));
/*  26 */     this.tasks.addTask(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
/*  27 */     this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityChicken.class, 750, false));
/*  28 */     this.targetTasks.addTask(1, new EntityAITargetNonTamed(this, EntityBat.class, 750, false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  33 */     super.entityInit();
/*  34 */     this.dataWatcher.addObject(18, Byte.valueOf((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateAITick() {
/*  42 */     if (getMoveHelper().isUpdating()) {
/*     */       
/*  44 */       double var1 = getMoveHelper().getSpeed();
/*     */       
/*  46 */       if (var1 == 0.6D)
/*     */       {
/*  48 */         setSneaking(true);
/*  49 */         setSprinting(false);
/*     */       }
/*  51 */       else if (var1 == 1.33D)
/*     */       {
/*  53 */         setSneaking(false);
/*  54 */         setSprinting(true);
/*     */       }
/*     */       else
/*     */       {
/*  58 */         setSneaking(false);
/*  59 */         setSprinting(false);
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  64 */       setSneaking(false);
/*  65 */       setSprinting(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/*  76 */     if (isTamed() || this.ticksExisted <= 2400) {
/*  77 */       return false;
/*     */     }
/*  79 */     return super.canDespawn();
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
/*     */   protected void applyEntityAttributes() {
/*  92 */     super.applyEntityAttributes();
/*  93 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(10.0D);
/*  94 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.30000001192092896D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fall(float par1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 107 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 108 */     par1NBTTagCompound.setInteger("CatType", getTameSkin());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 116 */     super.readEntityFromNBT(par1NBTTagCompound);
/* 117 */     setTameSkin(par1NBTTagCompound.getInteger("CatType"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 126 */     return isTamed() ? (isInLove() ? "mob.cat.purr" : ((this.rand.nextInt(4) == 0) ? "mob.cat.purreow" : "mob.cat.meow")) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 134 */     return "mob.cat.hitt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 142 */     return "mob.cat.hitt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 151 */     return 0.4F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 159 */     return Item.leather.itemID;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 169 */     return target.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this), 3.0F));
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
/*     */   public EntityDamageResult attackEntityFrom(Damage damage) {
/* 190 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */ 
/*     */ 
/*     */     
/* 194 */     if (result == null) {
/* 195 */       return result;
/*     */     }
/* 197 */     if (this.aiSit.isSitting()) {
/*     */       
/* 199 */       this.aiSit.setSitting(false);
/* 200 */       result.setEntityWasAffected();
/*     */     } 
/*     */     
/* 203 */     return result;
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
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getTamingOutcome(EntityPlayer player) {
/* 273 */     return (this.rand.nextInt(3) == 0) ? 1 : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/* 281 */     if (isTamed()) {
/*     */       
/* 283 */       if (super.onEntityRightClicked(player, item_stack)) {
/* 284 */         return true;
/*     */       }
/* 286 */       if (player.ownsEntity(this)) {
/*     */         
/* 288 */         if (player.onServer())
/*     */         {
/* 290 */           this.aiSit.setSitting(!isSitting());
/*     */         }
/* 292 */         return true;
/*     */       } 
/*     */       
/* 295 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 299 */     if (this.aiTempt.isRunning() && isFoodItem(item_stack) && player.getDistanceSqToEntity(this) < 9.0D) {
/*     */       
/* 301 */       if (player.onServer() && this.taming_cooldown == 0) {
/*     */         
/* 303 */         int taming_outcome = getTamingOutcome(player);
/*     */         
/* 305 */         if (taming_outcome <= 0) {
/*     */           
/* 307 */           playTameEffect(false);
/* 308 */           this.worldObj.setEntityState(this, EnumEntityState.tame_failure);
/* 309 */           this.taming_cooldown = 400;
/*     */         }
/*     */         else {
/*     */           
/* 313 */           setTamed(true);
/* 314 */           setTameSkin(1 + this.rand.nextInt(3));
/* 315 */           setOwner(player.getCommandSenderName());
/* 316 */           playTameEffect(true);
/* 317 */           this.aiSit.setSitting(true);
/* 318 */           this.worldObj.setEntityState(this, EnumEntityState.tame_success);
/*     */         } 
/*     */         
/* 321 */         if (!player.inCreativeMode()) {
/* 322 */           player.convertOneOfHeldItem((ItemStack)null);
/*     */         }
/*     */       } 
/* 325 */       return true;
/*     */     } 
/*     */     
/* 328 */     return super.onEntityRightClicked(player, item_stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityOcelot spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/* 336 */     EntityOcelot var2 = new EntityOcelot(this.worldObj);
/*     */     
/* 338 */     if (isTamed()) {
/*     */       
/* 340 */       var2.setOwner(getOwnerName());
/* 341 */       var2.setTamed(true);
/* 342 */       var2.setTameSkin(getTameSkin());
/*     */     } 
/*     */     
/* 345 */     return var2;
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
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 361 */     if (item_stack == null || !(item_stack.getItem() instanceof ItemFood)) {
/* 362 */       return false;
/*     */     }
/* 364 */     ItemFood food = (ItemFood)item_stack.getItem();
/*     */     
/* 366 */     return (food == Item.fishRaw || food == Item.fishLargeRaw);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canMateWith(EntityAnimal par1EntityAnimal) {
/* 374 */     if (par1EntityAnimal == this)
/*     */     {
/* 376 */       return false;
/*     */     }
/* 378 */     if (!isTamed())
/*     */     {
/* 380 */       return false;
/*     */     }
/* 382 */     if (!(par1EntityAnimal instanceof EntityOcelot))
/*     */     {
/* 384 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 388 */     EntityOcelot var2 = (EntityOcelot)par1EntityAnimal;
/* 389 */     return !var2.isTamed() ? false : ((isInLove() && var2.isInLove()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTameSkin() {
/* 395 */     return this.dataWatcher.getWatchableObjectByte(18);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTameSkin(int par1) {
/* 400 */     this.dataWatcher.updateObject(18, Byte.valueOf((byte)par1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanSpawnHere(boolean perform_light_check) {
/* 409 */     if (this.worldObj.rand.nextInt(3) == 0)
/*     */     {
/* 411 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 415 */     if (this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox)) {
/*     */       
/* 417 */       int var1 = MathHelper.floor_double(this.posX);
/* 418 */       int var2 = MathHelper.floor_double(this.boundingBox.minY);
/* 419 */       int var3 = MathHelper.floor_double(this.posZ);
/*     */       
/* 421 */       if (var2 < 63)
/*     */       {
/* 423 */         return false;
/*     */       }
/*     */       
/* 426 */       int var4 = this.worldObj.getBlockId(var1, var2 - 1, var3);
/*     */       
/* 428 */       if (var4 == Block.grass.blockID || var4 == Block.leaves.blockID)
/*     */       {
/* 430 */         return true;
/*     */       }
/*     */     } 
/*     */     
/* 434 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getEntityName() {
/* 443 */     return hasCustomNameTag() ? getCustomNameTag() : (isTamed() ? "entity.Cat.name" : super.getEntityName());
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 448 */     par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
/*     */     
/* 450 */     if (this.worldObj.rand.nextInt(7) == 0)
/*     */     {
/* 452 */       for (int var2 = 0; var2 < 2; var2++) {
/*     */         
/* 454 */         EntityOcelot var3 = new EntityOcelot(this.worldObj);
/* 455 */         var3.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, 0.0F);
/*     */         
/* 457 */         var3.setGrowingAgeToNewborn();
/* 458 */         this.worldObj.spawnEntityInWorld(var3);
/*     */       } 
/*     */     }
/*     */     
/* 462 */     return par1EntityLivingData;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 467 */     return spawnBabyAnimal(par1EntityAgeable);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityOcelot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */