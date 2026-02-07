/*      */ package net.minecraft;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class EntityWolf
/*      */   extends EntityTameable
/*      */ {
/*      */   private float field_70926_e;
/*      */   private float field_70924_f;
/*      */   private boolean isShaking;
/*      */   private boolean field_70928_h;
/*      */   private float timeWolfIsShaking;
/*      */   private float prevTimeWolfIsShaking;
/*      */   protected int data_object_id_is_attacking;
/*      */   protected int data_object_id_hostile_to_players;
/*      */   private int target_countdown;
/*      */   protected boolean is_witch_ally;
/*      */   
/*      */   public EntityWolf(World par1World) {
/*   27 */     super(par1World);
/*   28 */     setSize(0.70000005F, 0.8F);
/*   29 */     getNavigator().setAvoidsWater(true);
/*   30 */     this.tasks.addTask(1, new EntityAISwimming(this));
/*   31 */     this.tasks.addTask(2, this.aiSit);
/*   32 */     this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
/*   33 */     this.tasks.addTask(4, new EntityAIAttackOnCollide(this, 1.0D, true));
/*   34 */     this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
/*   35 */     this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
/*   36 */     this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
/*   37 */     this.tasks.addTask(8, new EntityAIBeg(this, 8.0F));
/*   38 */     this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*   39 */     this.tasks.addTask(9, new EntityAILookIdle(this));
/*   40 */     this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
/*   41 */     this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
/*   42 */     this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
/*   43 */     this.targetTasks.addTask(4, new EntityAITargetNonTamed(this, EntityChicken.class, 200, false));
/*   44 */     this.targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntitySheep.class, 400, false));
/*   45 */     this.targetTasks.addTask(6, new EntityAITargetNonTamed(this, EntityPig.class, 600, false));
/*   46 */     this.targetTasks.addTask(7, new EntityAITargetNonTamed(this, EntityCow.class, 800, false));
/*      */     
/*   48 */     this.targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntityZombie.class, 3200, true));
/*   49 */     this.targetTasks.addTask(5, new EntityAITargetNonTamed(this, EntityGhoul.class, 3200, true));
/*      */     
/*   51 */     this.targetTasks.addTask(3, new EntityAITargetIfHostileToPlayers(this, EntityPlayer.class, 0, true));
/*      */ 
/*      */     
/*   54 */     this.tasks.addTask(1, new EntityAIFleeAttackerOrPanic(this, 1.0F, 0.0F, true));
/*   55 */     this.tasks.addTask(4, new EntityAIGetOutOfWater(this, 1.0F));
/*      */     
/*   57 */     setTamed(false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void applyEntityAttributes() {
/*   64 */     super.applyEntityAttributes();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   76 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.4000000059604645D);
/*   77 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, isTamed() ? 12.0D : 8.0D);
/*      */     
/*   79 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 3.0D);
/*      */     
/*   81 */     setEntityAttribute(SharedMonsterAttributes.followRange, isTamed() ? 32.0D : 16.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void updateAITick() {
/*  125 */     this.dataWatcher.updateObject(18, Float.valueOf(getHealth()));
/*      */   }
/*      */ 
/*      */   
/*      */   protected void entityInit() {
/*  130 */     super.entityInit();
/*  131 */     this.dataWatcher.addObject(18, new Float(getHealth()));
/*  132 */     this.dataWatcher.addObject(19, new Byte((byte)0));
/*  133 */     this.dataWatcher.addObject(20, new Byte((byte)BlockColored.getBlockFromDye(1)));
/*      */     
/*  135 */     this.data_object_id_is_attacking = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*      */     
/*  137 */     this.data_object_id_hostile_to_players = this.dataWatcher.addObject(this.dataWatcher.getNextAvailableId(), new Byte((byte)0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void setTargetCountdown(int value) {
/*  145 */     if (this.worldObj.isRemote) {
/*  146 */       Minecraft.setErrorMessage("setTargetCountdown: only meant to be called on server");
/*      */     } else {
/*  148 */       this.target_countdown = MathHelper.clamp_int(value, 0, 800);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getTargetCountdown() {
/*  155 */     if (this.worldObj.isRemote) {
/*  156 */       Minecraft.setErrorMessage("getTargetCountdown: only meant to be called on server");
/*      */     }
/*  158 */     return this.target_countdown;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void setHostileToPlayers(boolean value) {
/*  163 */     if (!this.worldObj.isRemote) {
/*  164 */       this.dataWatcher.updateObject(this.data_object_id_hostile_to_players, Byte.valueOf((byte)(value ? -1 : 0)));
/*      */     }
/*      */   }
/*      */   
/*      */   protected boolean isHostileToPlayers() {
/*  169 */     return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_hostile_to_players) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/*  178 */     makeSound("mob.wolf.step", 0.15F, 1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/*  186 */     super.writeEntityToNBT(par1NBTTagCompound);
/*      */     
/*  188 */     par1NBTTagCompound.setByte("CollarColor", (byte)getCollarColor());
/*  189 */     par1NBTTagCompound.setInteger("target_countdown", getTargetCountdown());
/*  190 */     par1NBTTagCompound.setBoolean("hostile_to_players", isHostileToPlayers());
/*  191 */     par1NBTTagCompound.setBoolean("is_witch_ally", this.is_witch_ally);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/*  199 */     super.readEntityFromNBT(par1NBTTagCompound);
/*      */ 
/*      */     
/*  202 */     if (par1NBTTagCompound.hasKey("CollarColor"))
/*      */     {
/*  204 */       setCollarColor(par1NBTTagCompound.getByte("CollarColor"));
/*      */     }
/*      */     
/*  207 */     setTargetCountdown(par1NBTTagCompound.getInteger("target_countdown"));
/*  208 */     setHostileToPlayers(par1NBTTagCompound.getBoolean("hostile_to_players"));
/*  209 */     this.is_witch_ally = par1NBTTagCompound.getBoolean("is_witch_ally");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLivingSound() {
/*  219 */     if (looksAngry())
/*      */     {
/*  221 */       return "mob.wolf.growl";
/*      */     }
/*  223 */     if (this.fleeing)
/*      */     {
/*  225 */       return "mob.wolf.whine";
/*      */     }
/*  227 */     if (isTamed()) {
/*      */ 
/*      */       
/*  230 */       if (getHealthFraction() < 0.5F) {
/*  231 */         return "mob.wolf.whine";
/*      */       }
/*  233 */       return "mob.wolf.panting";
/*      */     } 
/*      */ 
/*      */     
/*  237 */     return "mob.wolf.panting";
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getLongDistanceLivingSound() {
/*  243 */     if (!this.worldObj.isDaytime() && !isAttacking() && !this.fleeing && this.rand.nextFloat() < 0.04F && isOutdoors()) {
/*  244 */       return "imported.mob.wolf.howl";
/*      */     }
/*  246 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getHurtSound() {
/*  254 */     return "mob.wolf.hurt";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getDeathSound() {
/*  262 */     return "mob.wolf.death";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected float getSoundVolume(String sound) {
/*  271 */     return isChild() ? 0.2F : 0.4F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getDropItemId() {
/*  279 */     return -1;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/*  284 */     dropItem(Item.leather.itemID, 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onLivingUpdate() {
/*  293 */     super.onLivingUpdate();
/*      */     
/*  295 */     if (!this.worldObj.isRemote && this.isShaking && !this.field_70928_h && !hasPath() && this.onGround) {
/*      */       
/*  297 */       this.field_70928_h = true;
/*  298 */       this.timeWolfIsShaking = 0.0F;
/*  299 */       this.prevTimeWolfIsShaking = 0.0F;
/*      */       
/*  301 */       this.worldObj.setEntityState(this, EnumEntityState.wolf_shaking);
/*      */     } 
/*      */     
/*  304 */     if (!this.worldObj.isRemote)
/*      */     {
/*  306 */       if (this.is_witch_ally) {
/*  307 */         setHostileToPlayers(true);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onUpdate() {
/*  316 */     super.onUpdate();
/*      */     
/*  318 */     if (!this.worldObj.isRemote) {
/*      */       
/*  320 */       int target_countdown = getTargetCountdown();
/*      */       
/*  322 */       if (target_countdown > 0) {
/*  323 */         setTargetCountdown(--target_countdown);
/*      */       }
/*  325 */       if (target_countdown == 0) {
/*      */         
/*  327 */         if (getAttackTarget() != null) {
/*  328 */           setAttackTarget((EntityLivingBase)null);
/*      */         }
/*  330 */         setHostileToPlayers(false);
/*      */       } 
/*      */       
/*  333 */       if (getAttackTarget() == null) {
/*      */         
/*  335 */         if (isAttacking()) {
/*  336 */           setIsAttacking(false);
/*      */         
/*      */         }
/*      */       }
/*  340 */       else if (!isAttacking()) {
/*  341 */         setIsAttacking(true);
/*      */       } 
/*      */ 
/*      */       
/*  345 */       if (!isTamed() && this.worldObj.isBloodMoon(true)) {
/*  346 */         setHostileToPlayers(true);
/*      */       }
/*      */     } 
/*  349 */     this.field_70924_f = this.field_70926_e;
/*      */     
/*  351 */     if (func_70922_bv()) {
/*      */       
/*  353 */       this.field_70926_e += (1.0F - this.field_70926_e) * 0.4F;
/*      */     }
/*      */     else {
/*      */       
/*  357 */       this.field_70926_e += (0.0F - this.field_70926_e) * 0.4F;
/*      */     } 
/*      */     
/*  360 */     if (func_70922_bv())
/*      */     {
/*  362 */       this.numTicksToChaseTarget = 10;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  370 */     if (isWet()) {
/*      */ 
/*      */       
/*  373 */       this.isShaking = true;
/*  374 */       this.field_70928_h = false;
/*  375 */       this.timeWolfIsShaking = 0.0F;
/*  376 */       this.prevTimeWolfIsShaking = 0.0F;
/*      */     }
/*  378 */     else if ((this.isShaking || this.field_70928_h) && this.field_70928_h) {
/*      */       
/*  380 */       if (this.timeWolfIsShaking == 0.0F)
/*      */       {
/*      */         
/*  383 */         playSound("mob.wolf.shake", getSoundVolume("mob.wolf.shake"), (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
/*      */       }
/*      */       
/*  386 */       this.prevTimeWolfIsShaking = this.timeWolfIsShaking;
/*  387 */       this.timeWolfIsShaking += 0.05F;
/*      */       
/*  389 */       if (this.prevTimeWolfIsShaking >= 2.0F) {
/*      */         
/*  391 */         this.isShaking = false;
/*  392 */         this.field_70928_h = false;
/*  393 */         this.prevTimeWolfIsShaking = 0.0F;
/*  394 */         this.timeWolfIsShaking = 0.0F;
/*      */       } 
/*      */       
/*  397 */       if (this.timeWolfIsShaking > 0.4F) {
/*      */         
/*  399 */         float var1 = (float)this.boundingBox.minY;
/*  400 */         int var2 = (int)(MathHelper.sin((this.timeWolfIsShaking - 0.4F) * 3.1415927F) * 7.0F);
/*      */         
/*  402 */         for (int var3 = 0; var3 < var2; var3++) {
/*      */           
/*  404 */           float var4 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/*  405 */           float var5 = (this.rand.nextFloat() * 2.0F - 1.0F) * this.width * 0.5F;
/*      */           
/*  407 */           this.worldObj.spawnParticle(EnumParticle.splash, this.posX + var4, (var1 + 0.8F), this.posZ + var5, this.motionX, this.motionY, this.motionZ);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getWolfShaking() {
/*  415 */     return this.isShaking;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getShadingWhileShaking(float par1) {
/*  423 */     return 0.75F + (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1) / 2.0F * 0.25F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getShakeAngle(float par1, float par2) {
/*  428 */     float var3 = (this.prevTimeWolfIsShaking + (this.timeWolfIsShaking - this.prevTimeWolfIsShaking) * par1 + par2) / 1.8F;
/*      */     
/*  430 */     if (var3 < 0.0F) {
/*      */       
/*  432 */       var3 = 0.0F;
/*      */     }
/*  434 */     else if (var3 > 1.0F) {
/*      */       
/*  436 */       var3 = 1.0F;
/*      */     } 
/*      */     
/*  439 */     return MathHelper.sin(var3 * 3.1415927F) * MathHelper.sin(var3 * 3.1415927F * 11.0F) * 0.15F * 3.1415927F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getInterestedAngle(float par1) {
/*  444 */     return (this.field_70924_f + (this.field_70926_e - this.field_70924_f) * par1) * 0.15F * 3.1415927F;
/*      */   }
/*      */ 
/*      */   
/*      */   public float getEyeHeight() {
/*  449 */     return this.height * 0.8F;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getVerticalFaceSpeed() {
/*  458 */     return isSitting() ? 20 : super.getVerticalFaceSpeed();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityDamageResult attackEntityFrom(Damage damage) {
/*  487 */     Entity var3 = damage.getImmediateEntity();
/*      */     
/*  489 */     if (var3 != null && !var3.isEntityPlayer() && !var3.isArrow()) {
/*  490 */       damage.setAmount((damage.getAmount() + 1.0F) / 2.0F);
/*      */     }
/*  492 */     EntityDamageResult result = super.attackEntityFrom(damage);
/*      */     
/*  494 */     if (result == null) {
/*  495 */       return result;
/*      */     }
/*  497 */     if (result.entityWasNegativelyAffected()) {
/*  498 */       this.aiSit.setSitting(false);
/*      */     }
/*  500 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public void startTargetCountdown() {
/*  505 */     setTargetCountdown(800 + this.rand.nextInt(100) - this.rand.nextInt(100));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityDamageResult attackEntityAsMob(Entity target) {
/*  533 */     EntityDamageResult result = target.attackEntityFrom(new Damage(DamageSource.causeMobDamage(this), (float)getEntityAttributeValue(SharedMonsterAttributes.attackDamage)));
/*      */     
/*  535 */     if (result == null) {
/*  536 */       return result;
/*      */     }
/*  538 */     if (result.entityWasNegativelyAffected())
/*      */     {
/*  540 */       if (result.entityWasDestroyed()) {
/*  541 */         setAttackTarget((EntityLivingBase)null);
/*      */       } else {
/*  543 */         startTargetCountdown();
/*      */       } 
/*      */     }
/*  546 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAttackTarget(EntityLivingBase par1EntityLivingBase) {
/*  551 */     if (par1EntityLivingBase != null && par1EntityLivingBase == getOwner()) {
/*      */       return;
/*      */     }
/*  554 */     super.setAttackTarget(par1EntityLivingBase);
/*      */     
/*  556 */     if (getAttackTarget() != null) {
/*      */       
/*  558 */       startTargetCountdown();
/*      */       
/*  560 */       if (getAttackTarget() instanceof EntityPlayer && !isTamed()) {
/*  561 */         setHostileToPlayers(true);
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   public void setTamed(boolean par1) {
/*  567 */     super.setTamed(par1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  578 */     float max_health_before = (float)getEntityAttributeValue(SharedMonsterAttributes.maxHealth);
/*  579 */     applyEntityAttributes();
/*  580 */     float max_health_after = (float)getEntityAttributeValue(SharedMonsterAttributes.maxHealth);
/*      */     
/*  582 */     if (max_health_after > max_health_before) {
/*  583 */       setHealth(getHealth() + max_health_after - max_health_before);
/*  584 */     } else if (getHealth() > max_health_after) {
/*  585 */       setHealth(max_health_after);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected int getTamingOutcome(EntityPlayer player) {
/*  735 */     float roll = this.rand.nextFloat();
/*      */     
/*  737 */     if (roll < 0.05F)
/*  738 */       return -1; 
/*  739 */     if (roll < 0.1F)
/*  740 */       return 0; 
/*  741 */     if (roll > 0.9F) {
/*  742 */       return 1;
/*      */     }
/*  744 */     roll += this.rand.nextFloat() * player.getExperienceLevel() * 0.02F;
/*      */     
/*  746 */     return (roll < 0.2F) ? -1 : ((roll < 0.8F) ? 0 : 1);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean onEntityRightClicked(EntityPlayer player, ItemStack item_stack) {
/*  751 */     if (looksAngry() || isHostileToPlayers()) {
/*  752 */       return false;
/*      */     }
/*  754 */     if (item_stack != null) {
/*      */       
/*  756 */       Item item = item_stack.getItem();
/*      */       
/*  758 */       if (isTamed()) {
/*      */         
/*  760 */         if (item == Item.dyePowder) {
/*      */           
/*  762 */           int color = BlockColored.getBlockFromDye(item_stack.getItemSubtype());
/*      */           
/*  764 */           if (color != getCollarColor())
/*      */           {
/*  766 */             if (player.onServer()) {
/*      */               
/*  768 */               setCollarColor(color);
/*      */               
/*  770 */               if (!player.inCreativeMode()) {
/*  771 */                 player.convertOneOfHeldItem((ItemStack)null);
/*      */               }
/*      */             } 
/*  774 */             return true;
/*      */           }
/*      */         
/*  777 */         } else if (willEat(item_stack) && (getHealth() + 1.0F) < getEntityAttributeValue(SharedMonsterAttributes.maxHealth)) {
/*      */           
/*  779 */           if (player.onServer()) {
/*      */             
/*  781 */             heal(item_stack.getNutrition());
/*  782 */             makeSound("mob.wolf.bark");
/*      */             
/*  784 */             if (!player.inCreativeMode()) {
/*  785 */               player.convertOneOfHeldItem((ItemStack)null);
/*      */             }
/*      */           } 
/*  788 */           return true;
/*      */         
/*      */         }
/*      */       
/*      */       }
/*  793 */       else if (item == Item.bone && !isAttacking()) {
/*      */         
/*  795 */         if (player.onServer() && this.taming_cooldown == 0) {
/*      */           
/*  797 */           int taming_outcome = getTamingOutcome(player);
/*      */ 
/*      */           
/*  800 */           if (taming_outcome <= 0) {
/*      */             
/*  802 */             playTameEffect(false);
/*  803 */             this.worldObj.setEntityState(this, EnumEntityState.tame_failure);
/*  804 */             this.taming_cooldown = 100;
/*      */             
/*  806 */             if (taming_outcome < 0 && !this.worldObj.isBlueMoonNight())
/*      */             {
/*  808 */               setAttackTarget(player);
/*      */             }
/*      */           }
/*      */           else {
/*      */             
/*  813 */             setTamed(true);
/*  814 */             setPathToEntity((PathEntity)null);
/*  815 */             setAttackTarget((EntityLivingBase)null);
/*  816 */             this.aiSit.setSitting(true);
/*      */             
/*  818 */             setOwner(player.getCommandSenderName());
/*  819 */             playTameEffect(true);
/*  820 */             this.worldObj.setEntityState(this, EnumEntityState.tame_success);
/*  821 */             makeSound((this.rand.nextInt(2) == 0) ? "mob.wolf.bark" : "mob.wolf.panting");
/*      */           } 
/*      */           
/*  824 */           if (!player.inCreativeMode()) {
/*  825 */             player.convertOneOfHeldItem((ItemStack)null);
/*      */           }
/*      */         } 
/*  828 */         return true;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  833 */     if (super.onEntityRightClicked(player, item_stack)) {
/*  834 */       return true;
/*      */     }
/*  836 */     if (player.ownsEntity(this)) {
/*      */       
/*  838 */       if (player.onClient()) {
/*      */         
/*  840 */         if (player.isLocalClient()) {
/*  841 */           Minecraft.getClientPlayerController().setUseButtonDelayOverride(600);
/*      */         }
/*      */       } else {
/*      */         
/*  845 */         if (Math.random() < 0.5D) {
/*  846 */           makeSound(isSitting() ? "mob.wolf.bark" : "mob.wolf.panting");
/*      */         }
/*  848 */         this.aiSit.setSitting(!isSitting());
/*  849 */         this.isJumping = false;
/*  850 */         setPathToEntity((PathEntity)null);
/*  851 */         setTarget((EntityLivingBase)null);
/*      */       } 
/*      */       
/*  854 */       return true;
/*      */     } 
/*      */     
/*  857 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void handleHealthUpdate(EnumEntityState par1) {
/*  864 */     if (par1 == EnumEntityState.wolf_shaking) {
/*      */       
/*  866 */       this.field_70928_h = true;
/*  867 */       this.timeWolfIsShaking = 0.0F;
/*  868 */       this.prevTimeWolfIsShaking = 0.0F;
/*      */     }
/*      */     else {
/*      */       
/*  872 */       super.handleHealthUpdate(par1);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float getTailRotation() {
/*  879 */     return looksAngry() ? 1.5393804F : (isTamed() ? ((0.55F - (20.0F - this.dataWatcher.getWatchableObjectFloat(18)) * 0.02F) * 3.1415927F) : 0.62831855F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isFoodItem(ItemStack item_stack) {
/*  893 */     return (item_stack != null && item_stack.getItem() instanceof ItemMeat);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getMaxSpawnedInChunk() {
/*  901 */     return 8;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isAttacking() {
/*  914 */     return (this.dataWatcher.getWatchableObjectByte(this.data_object_id_is_attacking) != 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIsAttacking(boolean is_attacking) {
/*  952 */     if (this.worldObj.isRemote) {
/*      */       
/*  954 */       Minecraft.setErrorMessage("setIsAttacking: only meant to be called on server");
/*      */ 
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  963 */     if (is_attacking == isAttacking()) {
/*      */       return;
/*      */     }
/*  966 */     this.dataWatcher.updateObject(this.data_object_id_is_attacking, Byte.valueOf((byte)(is_attacking ? -1 : 0)));
/*      */     
/*  968 */     if (is_attacking) {
/*  969 */       makeSound("mob.wolf.growl");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getCollarColor() {
/*  977 */     return this.dataWatcher.getWatchableObjectByte(20) & 0xF;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCollarColor(int par1) {
/*  985 */     this.dataWatcher.updateObject(20, Byte.valueOf((byte)(par1 & 0xF)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityWolf spawnBabyAnimal(EntityAgeable par1EntityAgeable) {
/*      */     EntityWolf var2;
/*      */     try {
/* 1004 */       var2 = getClass().getConstructor(new Class[] { World.class }).newInstance(new Object[] { this.worldObj });
/*      */     }
/* 1006 */     catch (Exception e) {
/*      */       
/* 1008 */       e.printStackTrace();
/* 1009 */       return null;
/*      */     } 
/*      */     
/* 1012 */     String var3 = getOwnerName();
/*      */     
/* 1014 */     if (var3 != null && var3.trim().length() > 0) {
/*      */       
/* 1016 */       var2.setOwner(var3);
/* 1017 */       var2.setTamed(true);
/*      */     } 
/*      */     
/* 1020 */     return var2;
/*      */   }
/*      */ 
/*      */   
/*      */   public void func_70918_i(boolean par1) {
/* 1025 */     if (par1) {
/*      */       
/* 1027 */       this.dataWatcher.updateObject(19, Byte.valueOf((byte)1));
/*      */     }
/*      */     else {
/*      */       
/* 1031 */       this.dataWatcher.updateObject(19, Byte.valueOf((byte)0));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canMateWith(EntityAnimal par1EntityAnimal) {
/* 1040 */     if (par1EntityAnimal == this)
/*      */     {
/* 1042 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1049 */     if (par1EntityAnimal.getClass() != getClass())
/*      */     {
/* 1051 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1055 */     EntityWolf var2 = (EntityWolf)par1EntityAnimal;
/*      */     
/* 1057 */     return var2.isSitting() ? false : ((isInLove() && var2.isInLove()));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean func_70922_bv() {
/* 1063 */     return (this.dataWatcher.getWatchableObjectByte(19) == 1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected boolean canDespawn() {
/* 1078 */     return (!this.is_witch_ally && (this.ticksExisted > 2400 || this instanceof IMob) && !isTamed() && super.canDespawn());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean func_142018_a(EntityLivingBase par1EntityLivingBase, EntityLivingBase par2EntityLivingBase) {
/* 1083 */     if (!(par1EntityLivingBase instanceof EntityCreeper) && !(par1EntityLivingBase instanceof EntityGhast)) {
/*      */       
/* 1085 */       if (par1EntityLivingBase instanceof EntityWolf) {
/*      */         
/* 1087 */         EntityWolf var3 = (EntityWolf)par1EntityLivingBase;
/*      */         
/* 1089 */         if (var3.isTamed() && var3.func_130012_q() == par2EntityLivingBase)
/*      */         {
/* 1091 */           return false;
/*      */         }
/*      */       } 
/*      */       
/* 1095 */       return (par1EntityLivingBase instanceof EntityPlayer && par2EntityLivingBase instanceof EntityPlayer && !((EntityPlayer)par2EntityLivingBase).canAttackPlayer((EntityPlayer)par1EntityLivingBase)) ? false : ((!(par1EntityLivingBase instanceof EntityHorse) || !((EntityHorse)par1EntityLivingBase).isTame()));
/*      */     } 
/*      */ 
/*      */     
/* 1099 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EntityAgeable createChild(EntityAgeable par1EntityAgeable) {
/* 1105 */     return spawnBabyAnimal(par1EntityAgeable);
/*      */   }
/*      */ 
/*      */   
/*      */   protected boolean preysUpon(Entity entity) {
/* 1110 */     return (!isChild() && !isTamed() && entity instanceof EntityAnimal);
/*      */   }
/*      */ 
/*      */   
/*      */   public void onFleeing() {
/* 1115 */     setHostileToPlayers(false);
/* 1116 */     setAttackTarget((EntityLivingBase)null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean looksAngry() {
/* 1121 */     return ((isAttacking() && !isTamed()) || isHostileToPlayers());
/*      */   }
/*      */ 
/*      */   
/*      */   public void warnOwner() {
/* 1126 */     makeSound((this.rand.nextInt(8) == 0) ? "mob.wolf.growl" : "mob.wolf.bark");
/*      */   }
/*      */ 
/*      */   
/*      */   public void callToOwner() {
/* 1131 */     makeLongDistanceSound("mob.wolf.bark");
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean drawBackFaces() {
/* 1136 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setWitchAlly() {
/* 1141 */     this.is_witch_ally = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canBeAttackedBy(EntityLivingBase attacker) {
/* 1146 */     if (this.rand.nextInt(4) > 0 && attacker.hasCurse(Curse.fear_of_wolves, true)) {
/* 1147 */       return false;
/*      */     }
/* 1149 */     return super.canBeAttackedBy(attacker);
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWolf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */