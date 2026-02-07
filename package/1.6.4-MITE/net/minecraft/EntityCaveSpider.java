/*    */ package net.minecraft;
/*    */ 
/*    */ public class EntityCaveSpider
/*    */   extends EntityArachnid
/*    */ {
/*    */   public EntityCaveSpider(World par1World) {
/*  7 */     super(par1World, 0.7F);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void applyEntityAttributes() {
/* 14 */     super.applyEntityAttributes();
/* 15 */     getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(16.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean peacefulDuringDay() {
/* 20 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public EntityDamageResult attackEntityAsMob(Entity target) {
/* 60 */     EntityDamageResult result = super.attackEntityAsMob(target);
/*    */     
/* 62 */     if (result == null || result.entityWasDestroyed()) {
/* 63 */       return result;
/*    */     }
/* 65 */     if (result.entityLostHealth() && target instanceof EntityLivingBase) {
/* 66 */       target.getAsEntityLivingBase().addPotionEffect(new PotionEffect(Potion.poison.id, 480, 0));
/*    */     }
/* 68 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getExperienceValue() {
/* 78 */     return super.getExperienceValue() * 2;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityCaveSpider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */