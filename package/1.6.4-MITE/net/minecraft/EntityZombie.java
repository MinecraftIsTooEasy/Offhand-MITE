/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ public class EntityZombie
/*     */   extends EntityAnimalWatcher
/*     */ {
/*  11 */   protected static final Attribute field_110186_bp = (new RangedAttribute("zombie.spawnReinforcements", 0.0D, 0.0D, 1.0D)).func_111117_a("Spawn Reinforcements Chance");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int conversionTime;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   Item[] rare_drops_standard = new Item[] { Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget };
/*  23 */   Item[] rare_drops_villager = new Item[] { Item.seeds, Item.pumpkinSeeds, Item.melonSeeds, Item.carrot, Item.potato, Item.onion };
/*     */ 
/*     */   
/*     */   private boolean is_smart;
/*     */ 
/*     */   
/*     */   private int profession;
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityZombie(World par1World) {
/*  34 */     super(par1World);
/*  35 */     getNavigator().setBreakDoors(true);
/*  36 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  37 */     this.tasks.addTask(1, new EntityAIBreakDoor(this));
/*  38 */     this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  39 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  40 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  41 */     this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
/*  42 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  43 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  44 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  45 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  46 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  47 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     this.tasks.addTask(2, new EntityAIMoveToFoodItem(this, 1.0F, true));
/*  53 */     this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0D, true));
/*  54 */     this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 10, true));
/*     */     
/*  56 */     this.tasks.addTask(3, new EntityAIMoveToTree(this, 1.0F));
/*     */ 
/*     */ 
/*     */     
/*  60 */     this.is_smart = (isRevenant() || this.rand.nextInt(8) == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  65 */     super.applyEntityAttributes();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  71 */     setEntityAttribute(SharedMonsterAttributes.followRange, 40.0D);
/*  72 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.23000000417232513D);
/*  73 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
/*  74 */     setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  83 */     super.entityInit();
/*  84 */     getDataWatcher().addObject(12, Byte.valueOf((byte)0));
/*  85 */     getDataWatcher().addObject(13, Byte.valueOf((byte)0));
/*  86 */     getDataWatcher().addObject(14, Byte.valueOf((byte)0));
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
/*     */   protected boolean isAIEnabled() {
/* 114 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isChild() {
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChild(boolean par1) {
/* 131 */     par1 = false;
/*     */     
/* 133 */     getDataWatcher().updateObject(12, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */     
/* 135 */     if (this.worldObj != null && !this.worldObj.isRemote)
/*     */     {
/* 137 */       AttributeInstance var2 = getEntityAttribute(SharedMonsterAttributes.movementSpeed);
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
/*     */   public boolean isVillager() {
/* 152 */     return (getDataWatcher().getWatchableObjectByte(13) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVillager(boolean par1, int profession) {
/* 161 */     if (Minecraft.isInTournamentMode()) {
/* 162 */       par1 = false;
/*     */     }
/* 164 */     this.profession = par1 ? profession : 0;
/*     */     
/* 166 */     if (par1 && !isVillager() && wasVillagerThatWasGeneratedWithVillage()) {
/*     */       
/* 168 */       this.tasks.addTask(1, new EntityAIRestrictSun(this));
/* 169 */       this.tasks.addTask(2, new EntityAIFleeSun(this, 1.0D));
/*     */     } 
/*     */     
/* 172 */     getDataWatcher().updateObject(13, Byte.valueOf((byte)(par1 ? 1 : 0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRevenant() {
/* 177 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 280 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*     */     
/* 282 */     if (result == null || result.entityWasDestroyed()) {
/* 283 */       return result;
/*     */     }
/* 285 */     if (result.entityWasNegativelyAffected() && damage.wasCausedByPlayer()) {
/* 286 */       this.is_smart = true;
/*     */     }
/* 288 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onUpdate() {
/* 296 */     if (!this.worldObj.isRemote && isConverting()) {
/*     */       
/* 298 */       int var1 = getConversionTimeBoost();
/* 299 */       this.conversionTime -= var1;
/*     */       
/* 301 */       if (this.conversionTime <= 0)
/*     */       {
/* 303 */         convertToVillager();
/*     */       }
/*     */     } 
/*     */     
/* 307 */     super.onUpdate();
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
/*     */   protected String getLivingSound() {
/* 327 */     return "mob.zombie.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 335 */     return "mob.zombie.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 343 */     return "mob.zombie.death";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 352 */     makeSound("mob.zombie.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 361 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 366 */     if (this.rand.nextFloat() < (recently_hit_by_player ? 0.5F : 0.25F)) {
/* 367 */       dropItem(Item.rottenFlesh.itemID, 1);
/*     */     }
/* 369 */     if (recently_hit_by_player && !this.has_taken_massive_fall_damage)
/*     */     {
/* 371 */       if (this.rand.nextInt(getBaseChanceOfRareDrop()) < 5 + damage_source.getLootingModifier() * 2) {
/*     */         
/* 373 */         Item[] rare_drops = isVillager() ? this.rare_drops_villager : this.rare_drops_standard;
/* 374 */         dropItem((rare_drops[this.rand.nextInt(rare_drops.length)]).itemID, 1);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 384 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getBaseChanceOfRareDrop() {
/* 389 */     return (isVillager() || isRevenant()) ? 50 : 200;
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
/*     */   public void addRandomWeapon() {
/* 414 */     if (this.rand.nextFloat() < (isVillager() ? 0.2F : 0.05F)) {
/*     */       
/* 416 */       List<RandomItemListEntry> items = new ArrayList();
/*     */       
/* 418 */       items.add(new RandomItemListEntry(Item.shovelWood, 1));
/* 419 */       items.add(new RandomItemListEntry(Item.shovelRustedIron, 2));
/*     */       
/* 421 */       if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
/* 422 */         items.add(new RandomItemListEntry(Item.hatchetRustedIron, 1));
/*     */       }
/* 424 */       if (isVillager()) {
/*     */         
/* 426 */         items.add(new RandomItemListEntry(Item.shearsRustedIron, 1));
/* 427 */         items.add(new RandomItemListEntry(Item.scytheRustedIron, 1));
/*     */         
/* 429 */         if (this.worldObj.getDayOfWorld() >= 10) {
/*     */           
/* 431 */           items.add(new RandomItemListEntry(Item.hoeRustedIron, 1));
/* 432 */           items.add(new RandomItemListEntry(Item.mattockRustedIron, 1));
/*     */         } 
/*     */         
/* 435 */         if (this.worldObj.getDayOfWorld() >= 20 && !Minecraft.isInTournamentMode()) {
/* 436 */           items.add(new RandomItemListEntry(Item.pickaxeRustedIron, 1));
/*     */         }
/*     */       } else {
/*     */         
/* 440 */         items.add(new RandomItemListEntry(Item.cudgelWood, 1));
/* 441 */         items.add(new RandomItemListEntry(Item.clubWood, 1));
/* 442 */         items.add(new RandomItemListEntry(Item.swordRustedIron, 1));
/* 443 */         items.add(new RandomItemListEntry(Item.daggerRustedIron, 1));
/*     */       } 
/*     */       
/* 446 */       RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
/*     */       
/* 448 */       setCurrentItemOrArmor(0, (new ItemStack(entry.item)).randomizeForMob(this, true));
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
/*     */   protected void addRandomEquipment() {
/* 476 */     addRandomWeapon();
/* 477 */     addRandomArmor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 485 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */     
/* 487 */     if (isChild())
/*     */     {
/* 489 */       par1NBTTagCompound.setBoolean("IsBaby", true);
/*     */     }
/*     */     
/* 492 */     if (isVillager()) {
/*     */       
/* 494 */       par1NBTTagCompound.setBoolean("IsVillager", true);
/* 495 */       par1NBTTagCompound.setInteger("Profession", this.profession);
/*     */     } 
/*     */     
/* 498 */     par1NBTTagCompound.setInteger("ConversionTime", isConverting() ? this.conversionTime : -1);
/*     */     
/* 500 */     par1NBTTagCompound.setBoolean("is_smart", this.is_smart);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 508 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */     
/* 510 */     if (par1NBTTagCompound.getBoolean("IsBaby"))
/*     */     {
/* 512 */       setChild(true);
/*     */     }
/*     */     
/* 515 */     if (par1NBTTagCompound.getBoolean("IsVillager"))
/*     */     {
/*     */       
/* 518 */       setVillager(true, par1NBTTagCompound.getInteger("Profession"));
/*     */     }
/*     */     
/* 521 */     if (par1NBTTagCompound.hasKey("ConversionTime") && par1NBTTagCompound.getInteger("ConversionTime") > -1)
/*     */     {
/* 523 */       startConversion(par1NBTTagCompound.getInteger("ConversionTime"));
/*     */     }
/*     */     
/* 526 */     this.is_smart = par1NBTTagCompound.getBoolean("is_smart");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {
/* 534 */     super.onKillEntity(par1EntityLivingBase);
/*     */     
/* 536 */     if (this.worldObj.difficultySetting >= 2 && par1EntityLivingBase instanceof EntityVillager) {
/*     */       
/* 538 */       if (getHeldItem() instanceof ItemTool) {
/*     */         return;
/*     */       }
/* 541 */       if (this.worldObj.difficultySetting == 2 && this.rand.nextBoolean()) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 546 */       EntityZombie var2 = new EntityZombie(this.worldObj);
/* 547 */       var2.copyLocationAndAnglesFrom(par1EntityLivingBase);
/* 548 */       this.worldObj.removeEntity(par1EntityLivingBase);
/* 549 */       var2.onSpawnWithEgg((EntityLivingData)null);
/*     */       
/* 551 */       for (int i = 0; i < 5; i++) {
/* 552 */         setCurrentItemOrArmor(i, (ItemStack)null);
/*     */       }
/* 554 */       var2.setVillager(true, ((EntityVillager)par1EntityLivingBase).getProfession());
/*     */       
/* 556 */       if (par1EntityLivingBase.isChild())
/*     */       {
/* 558 */         var2.setChild(true);
/*     */       }
/*     */       
/* 561 */       this.worldObj.spawnEntityInWorld(var2);
/* 562 */       this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1016, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 568 */     Object par1EntityLivingData1 = super.onSpawnWithEgg(par1EntityLivingData);
/* 569 */     float var2 = this.worldObj.getLocationTensionFactor(this.posX, this.posY, this.posZ);
/*     */     
/* 571 */     setCanPickUpLoot(true);
/*     */     
/* 573 */     if (par1EntityLivingData1 == null)
/*     */     {
/* 575 */       par1EntityLivingData1 = new EntityZombieGroupData(this, (this.worldObj.rand.nextFloat() < 0.05F), (this.worldObj.rand.nextFloat() < 0.05F), (EntityZombieINNER1)null);
/*     */     }
/*     */ 
/*     */     
/* 579 */     if (par1EntityLivingData1 instanceof EntityZombieGroupData && !isRevenant()) {
/*     */       
/* 581 */       EntityZombieGroupData var3 = (EntityZombieGroupData)par1EntityLivingData1;
/*     */       
/* 583 */       if (var3.field_142046_b)
/*     */       {
/* 585 */         setVillager(true, 0);
/*     */       }
/*     */       
/* 588 */       if (var3.field_142048_a)
/*     */       {
/* 590 */         setChild(true);
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 597 */     addRandomEquipment();
/*     */     
/* 599 */     if (getCurrentItemOrArmor(4) == null) {
/*     */       
/* 601 */       Calendar var5 = this.worldObj.getCurrentDate();
/*     */       
/* 603 */       if (var5.get(2) + 1 == 10 && var5.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
/*     */         
/* 605 */         setCurrentItemOrArmor(4, new ItemStack((this.rand.nextFloat() < 0.1F) ? Block.pumpkinLantern : Block.pumpkin));
/* 606 */         this.equipmentDropChances[4] = 0.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 610 */     getEntityAttribute(SharedMonsterAttributes.knockbackResistance).applyModifier(new AttributeModifier("Random spawn bonus", this.rand.nextDouble() * 0.05000000074505806D, 0));
/*     */ 
/*     */     
/* 613 */     if (this.rand.nextFloat() < var2 * 0.05F) {
/*     */       
/* 615 */       getEntityAttribute(field_110186_bp).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 0.25D + 0.5D, 0));
/* 616 */       getEntityAttribute(SharedMonsterAttributes.maxHealth).applyModifier(new AttributeModifier("Leader zombie bonus", this.rand.nextDouble() * 3.0D + 1.0D, 2));
/*     */     } 
/*     */     
/* 619 */     return (EntityLivingData)par1EntityLivingData1;
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
/*     */   protected void startConversion(int par1) {
/* 661 */     this.conversionTime = par1;
/* 662 */     getDataWatcher().updateObject(14, Byte.valueOf((byte)1));
/* 663 */     removePotionEffect(Potion.weakness.id);
/* 664 */     addPotionEffect(new PotionEffect(Potion.damageBoost.id, par1, Math.min(this.worldObj.difficultySetting - 1, 0)));
/*     */     
/* 666 */     this.worldObj.setEntityState(this, EnumEntityState.zombie_conversion);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleHealthUpdate(EnumEntityState par1) {
/* 673 */     if (par1 == EnumEntityState.zombie_conversion) {
/*     */       
/* 675 */       this.worldObj.playSound(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "mob.zombie.remedy", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
/*     */     }
/*     */     else {
/*     */       
/* 679 */       super.handleHealthUpdate(par1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean wasVillagerThatWasGeneratedWithVillage() {
/* 686 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean canDespawn() {
/* 697 */     if (isConverting() || wasVillagerThatWasGeneratedWithVillage()) {
/* 698 */       return false;
/*     */     }
/* 700 */     return super.canDespawn();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConverting() {
/* 708 */     return (getDataWatcher().getWatchableObjectByte(14) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void convertToVillager() {
/* 716 */     EntityVillager var1 = new EntityVillager(this.worldObj);
/* 717 */     var1.copyLocationAndAnglesFrom(this);
/* 718 */     var1.onSpawnWithEgg((EntityLivingData)null);
/* 719 */     var1.setProfession(this.profession);
/* 720 */     var1.func_82187_q();
/*     */     
/* 722 */     if (isChild())
/*     */     {
/*     */       
/* 725 */       var1.setGrowingAgeToNewborn();
/*     */     }
/*     */     
/* 728 */     this.worldObj.removeEntity(this);
/* 729 */     this.worldObj.spawnEntityInWorld(var1);
/* 730 */     var1.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 0));
/* 731 */     this.worldObj.playAuxSFXAtEntity((EntityPlayer)null, 1017, (int)this.posX, (int)this.posY, (int)this.posZ, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int getConversionTimeBoost() {
/* 739 */     int var1 = 1;
/*     */     
/* 741 */     if (this.rand.nextFloat() < 0.01F) {
/*     */       
/* 743 */       int var2 = 0;
/*     */       
/* 745 */       for (int var3 = (int)this.posX - 4; var3 < (int)this.posX + 4 && var2 < 14; var3++) {
/*     */         
/* 747 */         for (int var4 = (int)this.posY - 4; var4 < (int)this.posY + 4 && var2 < 14; var4++) {
/*     */           
/* 749 */           for (int var5 = (int)this.posZ - 4; var5 < (int)this.posZ + 4 && var2 < 14; var5++) {
/*     */             
/* 751 */             int var6 = this.worldObj.getBlockId(var3, var4, var5);
/*     */             
/* 753 */             if (var6 == Block.fenceIron.blockID || var6 == Block.bed.blockID) {
/*     */               
/* 755 */               if (this.rand.nextFloat() < 0.3F)
/*     */               {
/* 757 */                 var1++;
/*     */               }
/*     */               
/* 760 */               var2++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 767 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean preysUpon(Entity entity) {
/* 772 */     return entity instanceof EntityAnimal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 782 */     return (item_stack != null && item_stack.getItem() instanceof ItemMeat);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDiggingEnabled() {
/* 787 */     if (isHoldingItemThatPreventsDigging()) {
/* 788 */       return false;
/*     */     }
/* 790 */     if (this.is_smart || isFrenzied()) {
/* 791 */       return true;
/*     */     }
/* 793 */     return getHeldItem() instanceof ItemTool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawBackFaces() {
/* 804 */     return isWearingItems(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean isHarmedByPepsin() {
/* 809 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityZombie.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */