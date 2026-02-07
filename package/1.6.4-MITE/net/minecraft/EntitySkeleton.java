/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Calendar;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ public class EntitySkeleton
/*     */   extends EntityMob
/*     */   implements IRangedAttackMob {
/*   9 */   private EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
/*  10 */   private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);
/*     */   
/*     */   private int frenzied_by_bone_lord_countdown;
/*     */   
/*     */   private int data_object_id_is_frenzied_by_bone_lord;
/*     */   
/*  16 */   public int forced_skeleton_type = -1;
/*     */ 
/*     */   
/*     */   public EntitySkeleton(World par1World) {
/*  20 */     super(par1World);
/*  21 */     this.tasks.addTask(1, new EntityAISwimming(this));
/*  22 */     this.tasks.addTask(2, new EntityAIRestrictSun(this));
/*  23 */     this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
/*     */     
/*  25 */     this.tasks.addTask(5, new EntityAIWander(this, 0.75D));
/*  26 */     this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  27 */     this.tasks.addTask(6, new EntityAILookIdle(this));
/*  28 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
/*  29 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */     
/*  31 */     this.tasks.addTask(4, new EntityAIMoveToRepairItem(this, 1.0F, true));
/*     */     
/*  33 */     if (par1World != null && !par1World.isRemote)
/*     */     {
/*  35 */       setCombatTask();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  41 */     super.applyEntityAttributes();
/*     */ 
/*     */     
/*  44 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 6.0D);
/*  45 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.30000001192092896D);
/*  46 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  51 */     super.entityInit();
/*  52 */     this.dataWatcher.addObject(13, new Byte((byte)0));
/*     */     
/*  54 */     this.data_object_id_is_frenzied_by_bone_lord = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setFrenziedByBoneLordCountdown(int frenzied_by_bone_lord_countdown) {
/*  60 */     boolean was_frenzied_by_bone_lord = (this.frenzied_by_bone_lord_countdown > 0);
/*  61 */     boolean is_frenzied_by_bone_lord = (frenzied_by_bone_lord_countdown > 0);
/*     */     
/*  63 */     this.frenzied_by_bone_lord_countdown = frenzied_by_bone_lord_countdown;
/*     */     
/*  65 */     if (is_frenzied_by_bone_lord != was_frenzied_by_bone_lord) {
/*  66 */       this.dataWatcher.updateObject(this.data_object_id_is_frenzied_by_bone_lord, Byte.valueOf((byte)(is_frenzied_by_bone_lord ? -1 : 0)));
/*     */     }
/*  68 */     return is_frenzied_by_bone_lord;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFrenziedByBoneLord() {
/*  73 */     if (this.worldObj.isRemote) {
/*  74 */       return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_frenzied_by_bone_lord) != 0);
/*     */     }
/*  76 */     return (this.frenzied_by_bone_lord_countdown > 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAIEnabled() {
/*  84 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  92 */     return "mob.skeleton.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 100 */     return "mob.skeleton.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 108 */     return "mob.skeleton.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 117 */     makeSound("mob.skeleton.step", 0.15F, 1.0F);
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 139 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/* 141 */     if (result == null || result.entityWasDestroyed()) {
/* 142 */       return result;
/*     */     }
/* 144 */     if (result.entityLostHealth() && getSkeletonType() == 1 && target instanceof EntityLivingBase) {
/* 145 */       target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.wither.id, 200));
/*     */     }
/* 147 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 155 */     return EnumCreatureAttribute.UNDEAD;
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
/*     */   public void onLivingUpdate() {
/* 206 */     if (this.worldObj.isRemote && getSkeletonType() == 1) {
/* 207 */       setSize(0.72F, 2.34F);
/*     */     }
/* 209 */     if (this.frenzied_by_bone_lord_countdown > 0) {
/* 210 */       setFrenziedByBoneLordCountdown(this.frenzied_by_bone_lord_countdown - 1);
/*     */     }
/* 212 */     super.onLivingUpdate();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateRidden() {
/* 220 */     super.updateRidden();
/*     */     
/* 222 */     if (this.ridingEntity instanceof EntityCreature) {
/*     */       
/* 224 */       EntityCreature var1 = (EntityCreature)this.ridingEntity;
/* 225 */       this.renderYawOffset = var1.renderYawOffset;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDeath(DamageSource par1DamageSource) {
/* 234 */     super.onDeath(par1DamageSource);
/*     */ 
/*     */     
/* 237 */     if (par1DamageSource.getImmediateEntity() instanceof EntityArrow && par1DamageSource.getResponsibleEntity() != null && par1DamageSource.getResponsibleEntity() instanceof EntityPlayer && getSkeletonType() == 0) {
/*     */ 
/*     */       
/* 240 */       EntityPlayer var2 = (EntityPlayer)par1DamageSource.getResponsibleEntity();
/* 241 */       double var3 = var2.posX - this.posX;
/* 242 */       double var5 = var2.posZ - this.posZ;
/*     */       
/* 244 */       if (var3 * var3 + var5 * var5 >= 2500.0D)
/*     */       {
/* 246 */         var2.triggerAchievement(AchievementList.snipeSkeleton);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 257 */     return Item.arrowRustedIron.itemID;
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
/*     */ 
/*     */ 
/*     */ 
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
/* 322 */     int looting = damage_source.getLootingModifier();
/*     */ 
/*     */ 
/*     */     
/* 326 */     if (getSkeletonType() == 1) {
/*     */       
/* 328 */       int j = this.rand.nextInt(3 + looting) - 1;
/*     */       
/* 330 */       if (j > 0 && !recently_hit_by_player) {
/* 331 */         j -= this.rand.nextInt(j + 1);
/*     */       }
/* 333 */       for (int k = 0; k < j; k++) {
/* 334 */         dropItem(Item.coal.itemID, 1);
/*     */       }
/* 336 */       if (recently_hit_by_player && !this.has_taken_massive_fall_damage)
/*     */       {
/* 338 */         if (this.rand.nextInt(getBaseChanceOfRareDrop()) < 5 + looting * 2) {
/* 339 */           dropItemStack(new ItemStack(Item.skull.itemID, 1, 1), 0.0F);
/*     */         }
/*     */       }
/* 342 */     } else if (getSkeletonType() != 2) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 348 */       int j = this.rand.nextInt(2 + looting);
/*     */       
/* 350 */       if (j > 0 && !recently_hit_by_player) {
/* 351 */         j -= this.rand.nextInt(j + 1);
/*     */       }
/* 353 */       if (isLongdead() && j > 0) {
/* 354 */         j = (this.rand.nextInt(3) == 0) ? 1 : 0;
/*     */       }
/* 356 */       for (int k = 0; k < j; k++) {
/* 357 */         dropItem(isLongdead() ? Item.arrowAncientMetal.itemID : Item.arrowRustedIron.itemID, 1);
/*     */       }
/*     */     } 
/* 360 */     int num_drops = this.rand.nextInt(3);
/*     */     
/* 362 */     if (num_drops > 0 && !recently_hit_by_player) {
/* 363 */       num_drops -= this.rand.nextInt(num_drops + 1);
/*     */     }
/* 365 */     for (int i = 0; i < num_drops; i++) {
/* 366 */       dropItem(Item.bone.itemID, 1);
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
/*     */   public void addRandomWeapon() {
/* 387 */     if (getSkeletonType() == 2 && this.rand.nextInt(20) == 0) {
/*     */       
/* 389 */       int day_of_world = MinecraftServer.getServer().getOverworld().getDayOfWorld();
/*     */       
/* 391 */       if (day_of_world >= 10) {
/*     */         
/* 393 */         setCurrentItemOrArmor(0, (new ItemStack((day_of_world < 20 || this.rand.nextBoolean()) ? Item.daggerRustedIron : Item.swordRustedIron)).randomizeForMob(this, false));
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 398 */     setCurrentItemOrArmor(0, (new ItemStack((getSkeletonType() == 2) ? Item.clubWood : Item.bow)).randomizeForMob(this, true));
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
/*     */   protected void addRandomEquipment() {
/* 417 */     addRandomWeapon();
/* 418 */     addRandomArmor();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRandomSkeletonType(World world) {
/* 424 */     if (world.isTheNether())
/* 425 */       return 1; 
/* 426 */     if (this.rand.nextFloat() < (isLongdead() ? 0.5D : 0.25D)) {
/* 427 */       return 2;
/*     */     }
/* 429 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 434 */     par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
/*     */     
/* 436 */     int skeleton_type = (this.forced_skeleton_type >= 0) ? this.forced_skeleton_type : getRandomSkeletonType(this.worldObj);
/*     */ 
/*     */     
/* 439 */     if (skeleton_type == 1) {
/*     */       
/* 441 */       this.tasks.addTask(4, this.aiAttackOnCollide);
/* 442 */       setSkeletonType(1);
/*     */       
/* 444 */       setCurrentItemOrArmor(0, (new ItemStack(Item.swordIron)).setQuality(EnumQuality.poor).randomizeForMob(this, false));
/* 445 */       getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(4.0D);
/*     */ 
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 452 */       if (skeleton_type == 2) {
/*     */         
/* 454 */         setSkeletonType(2);
/* 455 */         this.tasks.addTask(4, this.aiAttackOnCollide);
/*     */       
/*     */       }
/* 458 */       else if (skeleton_type == 0) {
/*     */         
/* 460 */         this.tasks.addTask(4, this.aiArrowAttack);
/*     */       }
/*     */       else {
/*     */         
/* 464 */         Minecraft.setErrorMessage("onSpawnWithEgg: Unrecognized skeleton type " + skeleton_type);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 470 */       addRandomEquipment();
/*     */     } 
/*     */ 
/*     */     
/* 474 */     setCanPickUpLoot(true);
/*     */     
/* 476 */     if (getCurrentItemOrArmor(4) == null) {
/*     */       
/* 478 */       Calendar var2 = this.worldObj.getCurrentDate();
/*     */       
/* 480 */       if (var2.get(2) + 1 == 10 && var2.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
/*     */         
/* 482 */         setCurrentItemOrArmor(4, new ItemStack((this.rand.nextFloat() < 0.1F) ? Block.pumpkinLantern : Block.pumpkin));
/* 483 */         this.equipmentDropChances[4] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 487 */     return par1EntityLivingData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCombatTask() {
/* 495 */     this.tasks.removeTask(this.aiAttackOnCollide);
/* 496 */     this.tasks.removeTask(this.aiArrowAttack);
/* 497 */     ItemStack var1 = getHeldItemStack();
/*     */ 
/*     */     
/* 500 */     if (var1 != null && var1.getItem() instanceof ItemBow) {
/*     */       
/* 502 */       this.tasks.addTask(4, this.aiArrowAttack);
/*     */       
/* 504 */       this.tasks.addTask(3, new EntityAISeekFiringPosition(this, 1.0F, true));
/*     */     }
/*     */     else {
/*     */       
/* 508 */       this.tasks.addTask(4, this.aiAttackOnCollide);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void attackEntityWithRangedAttack(EntityLivingBase par1EntityLivingBase, float par2) {
/* 519 */     EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (14 - this.worldObj.difficultySetting * 4), isLongdead() ? Item.arrowAncientMetal : Item.arrowRustedIron, false);
/* 520 */     int var4 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItemStack());
/* 521 */     int var5 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItemStack());
/*     */ 
/*     */     
/* 524 */     double damage = (par2 * 2.0F) + this.rand.nextGaussian() * 0.25D + (this.worldObj.difficultySetting * 0.11F);
/*     */     
/* 526 */     var3.setDamage(damage);
/*     */     
/* 528 */     if (var4 > 0)
/*     */     {
/* 530 */       var3.setDamage(var3.getDamage() + var4 * 0.5D + 0.5D);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 535 */     if (var5 > 0)
/*     */     {
/* 537 */       var3.setKnockbackStrength(var5);
/*     */     }
/*     */ 
/*     */     
/* 541 */     if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, getHeldItemStack()) > 0 || getSkeletonType() == 1 || (isBurning() && this.rand.nextInt(3) == 0))
/*     */     {
/* 543 */       var3.setFire(100);
/*     */     }
/*     */     
/* 546 */     playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
/* 547 */     this.worldObj.spawnEntityInWorld(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSkeletonType() {
/* 555 */     return this.dataWatcher.getWatchableObjectByte(13);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSkeletonType(int par1) {
/* 563 */     this.dataWatcher.updateObject(13, Byte.valueOf((byte)par1));
/*     */ 
/*     */     
/* 566 */     if (par1 == 1) {
/*     */       
/* 568 */       setSize(0.72F, 2.34F);
/*     */     }
/*     */     else {
/*     */       
/* 572 */       setSize(0.6F, 1.8F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 581 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 583 */     if (par1NBTTagCompound.hasKey("SkeletonType")) {
/*     */       
/* 585 */       byte var2 = par1NBTTagCompound.getByte("SkeletonType");
/* 586 */       setSkeletonType(var2);
/*     */     } 
/*     */     
/* 589 */     setCombatTask();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 597 */     super.writeEntityToNBT(par1NBTTagCompound);
/* 598 */     par1NBTTagCompound.setByte("SkeletonType", (byte)getSkeletonType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentItemOrArmor(int par1, ItemStack par2ItemStack) {
/* 606 */     super.setCurrentItemOrArmor(par1, par2ItemStack);
/*     */     
/* 608 */     if (!this.worldObj.isRemote && par1 == 0)
/*     */     {
/* 610 */       setCombatTask();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeldItemStack(ItemStack item_stack) {
/* 616 */     super.setHeldItemStack(item_stack);
/*     */     
/* 618 */     if (onServer()) {
/* 619 */       setCombatTask();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getYOffset() {
/* 627 */     return super.getYOffset() - 0.5D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBeDamagedByCacti() {
/* 632 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRepairItem(ItemStack item_stack) {
/* 637 */     return (item_stack != null && item_stack.getItem() == Item.bone);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 642 */     return (getSkeletonType() != 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 647 */     return (getSkeletonType() != 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumEntityFX getHealFX() {
/* 652 */     return EnumEntityFX.repair;
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
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/* 665 */     if (damage_source.isArrowDamage() && damage_source.getResponsibleEntity() instanceof EntitySkeleton) {
/* 666 */       return true;
/*     */     }
/* 668 */     return super.isImmuneTo(damage_source);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLongdead() {
/* 673 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLongdeadGuardian() {
/* 678 */     return this instanceof EntityLongdeadGuardian;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBoneLord() {
/* 683 */     return this instanceof EntityBoneLord;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAncientBoneLord() {
/* 688 */     return this instanceof EntityAncientBoneLord;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFrenziedByBoneLord(EntityLivingBase target) {
/* 693 */     setFrenziedByBoneLordCountdown(20);
/*     */     
/* 695 */     if (getTarget() == null) {
/* 696 */       setTarget(target);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isFrenzied() {
/* 701 */     return (isFrenziedByBoneLord() || super.isFrenzied());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean avoidsSunlight() {
/* 707 */     if (isWearingHelmet(true)) {
/* 708 */       return false;
/*     */     }
/* 710 */     EntityLivingBase target = getTarget();
/*     */     
/* 712 */     if (target != null && !target.isDead && target.getHealth() > 0.0F) {
/*     */       
/* 714 */       ItemStack held_item_stack = getHeldItemStack();
/*     */       
/* 716 */       if (held_item_stack == null || !(held_item_stack.getItem() instanceof ItemBow)) {
/* 717 */         return false;
/*     */       }
/*     */     } 
/* 720 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySkeleton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */