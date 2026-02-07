/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityShadow
/*     */   extends EntityMob
/*     */ {
/*     */   public EntityShadow(World par1World) {
/*   9 */     super(par1World);
/*     */     
/*  11 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  12 */     this.tasks.addTask(2, new EntityAIRestrictSun(this));
/*  13 */     this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
/*  14 */     this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  15 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  16 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  17 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  18 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  19 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  20 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  21 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */     
/*  23 */     this.tasks.addTask(3, new EntityAISeekLitTorch(this, 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  28 */     super.applyEntityAttributes();
/*  29 */     getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
/*  30 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.23000000417232513D);
/*  31 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/*  36 */     if (damage_source.hasSilverAspect() || damage_source.hasMagicAspect() || damage_source.isSunlight()) {
/*  37 */       return false;
/*     */     }
/*  39 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/*  44 */     return true;
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
/*     */   public void onLivingUpdate() {
/*  75 */     if (!this.worldObj.isRemote)
/*     */     {
/*  77 */       if (isInSunlight()) {
/*     */ 
/*     */ 
/*     */         
/*  81 */         attackEntityFrom(new Damage(DamageSource.sunlight, 1000.0F));
/*     */       
/*     */       }
/*  84 */       else if (this.ticksExisted % 40 == 0) {
/*     */         
/*  86 */         float brightness = getBrightness(1.0F);
/*  87 */         int amount_to_heal = (int)((0.4F - brightness) * 10.0F);
/*     */         
/*  89 */         if (amount_to_heal > 0) {
/*  90 */           heal(amount_to_heal);
/*     */         }
/*     */       } 
/*     */     }
/*  94 */     tryDisableNearbyLightSource();
/*     */     
/*  96 */     super.onLivingUpdate();
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 132 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/* 134 */     if (result == null) {
/* 135 */       return result;
/*     */     }
/* 137 */     if (result.entityWasNegativelyAffected() && target instanceof EntityPlayer) {
/* 138 */       (target.getAsPlayer()).vision_dimming += target.getAsEntityLivingBase().getAmountAfterResistance(2.0F, 4);
/*     */     }
/* 140 */     if (result.entityLostHealth() && target instanceof EntityLivingBase) {
/* 141 */       target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.weakness.id, 600, 0));
/*     */     }
/* 143 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 148 */     return "imported.mob.shadow.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 153 */     return "imported.mob.shadow.hurt";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 158 */     return "imported.mob.shadow.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {}
/*     */   
/*     */   protected float getSoundVolume(String sound) {
/* 165 */     return 0.2F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 170 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 175 */     return EnumCreatureAttribute.UNDEAD;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 182 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 190 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {
/* 195 */     super.onKillEntity(par1EntityLivingBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 200 */     return super.onSpawnWithEgg(par1EntityLivingData);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 210 */     return super.getExperienceValue() * 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 215 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 220 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityShadow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */