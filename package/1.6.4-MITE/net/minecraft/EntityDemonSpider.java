/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityDemonSpider
/*     */   extends EntityArachnid
/*     */ {
/*     */   public EntityDemonSpider(World par1World) {
/*   9 */     super(par1World, 1.0F);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  14 */     this.tasks.addTask(4, new EntityAIGetOutOfLava(this, 1.0F));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyEntityAttributes() {
/*  19 */     super.applyEntityAttributes();
/*     */     
/*  21 */     setEntityAttribute(SharedMonsterAttributes.maxHealth, 18.0D);
/*  22 */     setEntityAttribute(SharedMonsterAttributes.attackDamage, 5.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getLivingSound() {
/*  27 */     return "imported.mob.demonspider.say";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getHurtSound() {
/*  35 */     return "imported.mob.demonspider.hurt";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDeathSound() {
/*  43 */     return "imported.mob.demonspider.death";
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean peacefulDuringDay() {
/*  48 */     return false;
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
/*     */   public EntityDamageResult attackEntityAsMob(Entity target) {
/*  89 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*     */     
/*  91 */     if (result == null || result.entityWasDestroyed()) {
/*  92 */       return result;
/*     */     }
/*  94 */     if (result.entityLostHealth() && target instanceof EntityLivingBase) {
/*     */       
/*  96 */       target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.poison.id, 480, 0));
/*  97 */       target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 50, 5));
/*     */     } 
/*     */     
/* 100 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExperienceValue() {
/* 105 */     return super.getExperienceValue() * 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByFire() {
/* 110 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHarmedByLava() {
/* 115 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSpawnInShallowWater() {
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityDemonSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */