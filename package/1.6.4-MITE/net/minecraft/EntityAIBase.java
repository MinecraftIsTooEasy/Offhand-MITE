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
/*    */ public abstract class EntityAIBase
/*    */ {
/*    */   private int mutexBits;
/*    */   
/*    */   public abstract boolean shouldExecute();
/*    */   
/*    */   public boolean continueExecuting() {
/* 21 */     return shouldExecute();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInterruptible() {
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void startExecuting() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetTask() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void updateTask() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setMutexBits(int par1) {
/* 53 */     this.mutexBits = par1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addMutexBits(int par1) {
/* 58 */     this.mutexBits |= par1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getMutexBits() {
/* 67 */     return this.mutexBits;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\EntityAIBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */