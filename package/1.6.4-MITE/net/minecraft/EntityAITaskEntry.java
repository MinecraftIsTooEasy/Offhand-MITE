/*    */ package net.minecraft;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class EntityAITaskEntry
/*    */ {
/*    */   public EntityAIBase action;
/*    */   public int priority;
/*    */   
/*    */   public EntityAITaskEntry(EntityAITasks entityAITasks, int i, EntityAIBase entityAIBase) {
/* 14 */     this.priority = i;
/* 15 */     this.action = entityAIBase;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAITaskEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */