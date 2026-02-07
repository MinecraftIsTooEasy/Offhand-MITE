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
/*    */ final class FilterIMob
/*    */   implements IEntitySelector
/*    */ {
/*    */   public boolean isEntityApplicable(Entity entity) {
/* 16 */     return entity instanceof IMob;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\FilterIMob.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */