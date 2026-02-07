/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityWight
/*     */   extends EntityMob
/*     */ {
/*     */   public EntityWight(World par1World) {
/*  12 */     super(par1World);
/*     */     
/*  14 */     getNavigator().setBreakDoors(true);
/*     */     
/*  16 */     this.tasks.addTask(0, new EntityAISwimming(this));
/*  17 */     this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
/*  18 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
/*  19 */     this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
/*  20 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  21 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  22 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  23 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*  24 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  31 */     super.applyEntityAttributes();
/*  32 */     getEntityAttribute(SharedMonsterAttributes.followRange).setAttribute(40.0D);
/*  33 */     getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25D);
/*  34 */     getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(5.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isImmuneTo(DamageSource damage_source) {
/*  44 */     if (damage_source.hasFireAspect() || damage_source.isLavaDamage() || damage_source.hasSilverAspect() || damage_source.hasMagicAspect()) {
/*  45 */       return false;
/*     */     }
/*  47 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isAIEnabled() {
/*  52 */     return true;
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
/*     */   public void onUpdate() {
/*  78 */     super.onUpdate();
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 101 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/* 103 */     if (result == null || result.entityWasDestroyed()) {
/* 104 */       return result;
/*     */     }
/* 106 */     if (result.entityLostHealth() && target instanceof EntityPlayer && getRNG().nextFloat() < 0.4F) {
/*     */ 
/*     */ 
/*     */       
/* 110 */       int drain = Math.max((target.getAsPlayer().getExperienceLevel() + 1) * 10, 20);
/* 111 */       target.getAsPlayer().addExperience(-target.getAsPlayer().getDrainAfterResistance(drain));
/*     */     } 
/*     */     
/* 114 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/* 119 */     return "imported.mob.wight.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/* 124 */     return "imported.mob.wight.hurt";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/* 129 */     return "imported.mob.wight.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected void playStepSound(int par1, int par2, int par3, int par4) {
/* 134 */     makeSound("mob.zombie.step", 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getDropItemId() {
/* 139 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
/* 144 */     if (this.rand.nextFloat() < (recently_hit_by_player ? 0.5F : 0.25F)) {
/* 145 */       dropItem(Item.rottenFlesh.itemID, 1);
/*     */     }
/* 147 */     if (recently_hit_by_player && !this.has_taken_massive_fall_damage)
/*     */     {
/* 149 */       if (this.rand.nextInt(getBaseChanceOfRareDrop()) < 5 + damage_source.getLootingModifier() * 2)
/*     */       {
/* 151 */         switch (this.rand.nextInt(4)) {
/*     */           
/*     */           case 0:
/* 154 */             dropItem(Item.copperNugget);
/*     */             break;
/*     */           case 1:
/* 157 */             dropItem(Item.silverNugget);
/*     */             break;
/*     */           case 2:
/* 160 */             dropItem(Item.goldNugget);
/*     */             break;
/*     */           case 3:
/* 163 */             dropItem(Item.ironNugget);
/*     */             break;
/*     */         } 
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumCreatureAttribute getCreatureAttribute() {
/* 172 */     return EnumCreatureAttribute.UNDEAD;
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
/*     */   public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
/* 196 */     super.writeEntityToNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
/* 204 */     super.readEntityFromNBT(par1NBTTagCompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onKillEntity(EntityLivingBase par1EntityLivingBase) {
/* 209 */     super.onKillEntity(par1EntityLivingBase);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData) {
/* 214 */     return super.onSpawnWithEgg(par1EntityLivingData);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 219 */     return super.getExperienceValue() * 2;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityWight.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */