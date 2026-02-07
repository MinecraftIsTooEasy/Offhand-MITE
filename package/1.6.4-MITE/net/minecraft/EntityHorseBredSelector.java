/*    */ package net.minecraft;
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
/*    */ final class EntityHorseBredSelector
/*    */   implements IEntitySelector
/*    */ {
/*    */   public boolean isEntityApplicable(Entity entity) {
/* 32 */     return (entity instanceof EntityHorse && ((EntityHorse)entity).func_110205_ce());
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityHorseBredSelector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */