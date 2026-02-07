/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class EntityHellhound
/*     */   extends EntityWolf
/*     */   implements IMob
/*     */ {
/*     */   public EntityHellhound(World par1World) {
/*   9 */     super(par1World);
/*     */     
/*  11 */     setSize(0.6F, 0.8F);
/*     */ 
/*     */ 
/*     */     
/*  15 */     getNavigator().setAvoidsWater(true);
/*     */     
/*  17 */     this.tasks.clear();
/*  18 */     this.targetTasks.clear();
/*     */     
/*  20 */     this.tasks.addTask(1, new EntityAISwimming(this));
/*  21 */     this.tasks.addTask(2, new EntityAILeapAtTarget(this, 0.4F));
/*  22 */     this.tasks.addTask(3, new EntityAIAttackOnCollide(this, 1.0D, true));
/*  23 */     this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
/*  24 */     this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
/*  25 */     this.tasks.addTask(7, new EntityAILookIdle(this));
/*  26 */     this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
/*     */     
/*  28 */     this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
/*  29 */     this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 20, true));
/*     */     
/*  31 */     this.tasks.addTask(4, new EntityAIGetOutOfWater(this, 1.0F));
/*  32 */     this.tasks.addTask(4, new EntityAIGetOutOfLava(this, 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  37 */     super.applyEntityAttributes();
/*     */     
/*  39 */     setEntityAttribute(SharedMonsterAttributes.movementSpeed, 0.4000000059604645D);
/*  40 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 20.0D);
/*  41 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  46 */     return "imported.mob.hellhound.say";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  51 */     return "imported.mob.hellhound.hurt";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  56 */     return "imported.mob.hellhound.death";
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLongDistanceLivingSound() {
/*  61 */     return null;
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/*  87 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/*  89 */     if (result == null || result.entityWasDestroyed()) {
/*  90 */       return result;
/*     */     }
/*  92 */     if (result.entityWasNegativelyAffected() && getRNG().nextFloat() < 0.4F) {
/*     */       
/*  94 */       makeSound("imported.mob.hellhound.breath", 4.0F, 1.0F);
/*  95 */       target.setFire(1 + this.rand.nextInt(8));
/*     */     } 
/*     */     
/*  98 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityPlayer par1EntityPlayer) {
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHostileToPlayers() {
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canMateWith(EntityAnimal par1EntityAnimal) {
/* 113 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/* 118 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBlockPathWeight(int par1, int par2, int par3) {
/* 125 */     if (this.worldObj.isDaytime() && this.worldObj.canBlockSeeTheSky(par1, par2, par3)) {
/* 126 */       return -0.5F;
/*     */     }
/* 128 */     return 10.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEntityBiologicallyAlive() {
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isFoodItem(ItemStack item_stack) {
/* 138 */     return false;
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
/*     */   protected boolean isValidLightLevel() {
/* 172 */     return EntityMob.isValidLightLevel(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 177 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 182 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 187 */     return super.getExperienceValue() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 192 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityHellhound.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */