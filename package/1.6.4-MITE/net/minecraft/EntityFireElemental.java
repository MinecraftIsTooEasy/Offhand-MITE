/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityFireElemental
/*     */   extends EntityMob
/*     */ {
/*     */   private int ticks_until_next_fire_sound;
/*     */   private int ticks_until_next_fizz_sound;
/*     */   
/*     */   public EntityFireElemental(World par1World) {
/*  15 */     super(par1World);
/*     */     
/*  17 */     getNavigator().setAvoidsWater(true);
/*     */     
/*  19 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  20 */     this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  21 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  22 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  23 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*     */ 
/*     */     
/*  26 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  27 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */     
/*  29 */     this.tasks.addTask(1, new EntityAIGetOutOfWater(this, 1.0F));
/*  30 */     this.tasks.addTask(2, new EntityAISeekShelterFromRain(this, 1.0F, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  38 */     super.applyEntityAttributes();
/*  39 */     getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
/*  40 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
/*  41 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void entityInit() {
/*  46 */     super.entityInit();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/*  51 */     if (damage_source.isWater() || damage_source.getImmediateEntity() instanceof EntitySnowball) {
/*  52 */       return false;
/*     */     }
/*  54 */     if (damage_source.hasMagicAspect())
/*     */     {
/*  56 */       if (damage_source.isArrowDamage()) {
/*     */         
/*  58 */         EntityArrow arrow = (EntityArrow)damage_source.getImmediateEntity();
/*     */         
/*  60 */         if (arrow.getLauncher() == null || !arrow.getLauncher().hasEnchantment(Enchantment.flame, true)) {
/*  61 */           return false;
/*     */         }
/*     */       } else {
/*     */         
/*  65 */         ItemStack item_stack = damage_source.getItemAttackedWith();
/*     */         
/*  67 */         if (item_stack == null || !item_stack.hasEnchantment(Enchantment.fireAspect, true)) {
/*  68 */           return false;
/*     */         }
/*     */       } 
/*     */     }
/*  72 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/*  77 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLivingUpdate() {
/*  82 */     if (this.worldObj.isRemote)
/*     */     {
/*  84 */       if (isWet()) {
/*  85 */         spawnSteamParticles(this.inWater ? 10 : 1);
/*     */       }
/*     */     }
/*  88 */     if (!this.worldObj.isRemote) {
/*     */       
/*  90 */       if (getTicksExistedWithOffset() % 40 == 0) {
/*  91 */         attackEntityFrom(new Damage(DamageSource.water, 1.0F));
/*     */       }
/*  93 */       if (isWet()) {
/*     */         
/*  95 */         if (--this.ticks_until_next_fizz_sound <= 0)
/*     */         {
/*  97 */           playSound("random.fizz", 0.7F, 1.6F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
/*  98 */           this.ticks_until_next_fizz_sound = this.rand.nextInt(7) + 2;
/*     */           
/* 100 */           if (this.rand.nextInt(this.inWater ? 1 : 4) == 0) {
/* 101 */             attackEntityFrom(new Damage(DamageSource.water, 1.0F));
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 106 */       else if (--this.ticks_until_next_fire_sound <= 0) {
/*     */         
/* 108 */         playSound("fire.fire", 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F);
/* 109 */         this.ticks_until_next_fire_sound = this.rand.nextInt(21) + 30;
/*     */         
/* 111 */         if (handleLavaMovement()) {
/* 112 */           heal(4.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     super.onLivingUpdate();
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
/*     */   public void onUpdate() {
/* 147 */     super.onUpdate();
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 165 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/* 167 */     if (result == null || result.entityWasDestroyed()) {
/* 168 */       return result;
/*     */     }
/* 170 */     if (result.entityLostHealth()) {
/* 171 */       target.setFire(6);
/*     */     }
/* 173 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 178 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 183 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 188 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {}
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 195 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 200 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {
/* 205 */     super.onKillEntity(par1EntityLivingBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 210 */     return super.onSpawnWithEgg(par1EntityLivingData);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBurning() {
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean healsWithTime() {
/* 220 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityBiologicallyAlive() {
/* 225 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 235 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isComfortableInLava() {
/* 240 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isValidLightLevel() {
/* 245 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 250 */     return super.getExperienceValue() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 255 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeDamageFromPlayerThrownSnowballs() {
/* 260 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityFireElemental.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */