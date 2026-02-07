/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ final class EntitySelectorEntityLiving
/*    */   implements IEntitySelector
/*    */ {
/*    */   boolean must_be_alive;
/*    */   boolean must_use_new_AI;
/*    */   
/*    */   public EntitySelectorEntityLiving(boolean must_be_alive, boolean must_use_new_AI) {
/* 12 */     this.must_be_alive = must_be_alive;
/* 13 */     this.must_use_new_AI = must_use_new_AI;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEntityApplicable(Entity entity) {
/* 18 */     if (entity.isDead) {
/* 19 */       return false;
/*    */     }
/* 21 */     if (!(entity instanceof EntityLiving)) {
/* 22 */       return false;
/*    */     }
/* 24 */     EntityLiving entity_living = entity.getAsEntityLiving();
/*    */     
/* 26 */     if (this.must_be_alive && entity_living.getHealth() <= 0.0F) {
/* 27 */       return false;
/*    */     }
/* 29 */     if (this.must_use_new_AI && !entity_living.isAIEnabled()) {
/* 30 */       return false;
/*    */     }
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntitySelectorEntityLiving.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */