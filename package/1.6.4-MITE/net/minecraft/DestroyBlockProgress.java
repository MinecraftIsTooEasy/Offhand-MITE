/*    */ package net.minecraft;
/*    */ 
/*    */ public class DestroyBlockProgress {
/*    */   private final int miningPlayerEntId;
/*    */   private final int partialBlockX;
/*    */   private final int partialBlockY;
/*    */   private final int partialBlockZ;
/*    */   private int partialBlockProgress;
/*    */   private int createdAtCloudUpdateTick;
/*    */   
/*    */   public DestroyBlockProgress(int i, int j, int k, int l) {
/* 12 */     this.miningPlayerEntId = i;
/* 13 */     this.partialBlockX = j;
/* 14 */     this.partialBlockY = k;
/* 15 */     this.partialBlockZ = l;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getPartialBlockX() {
/* 23 */     return this.partialBlockX;
/*    */   }
/*    */   
/*    */   public int getPartialBlockY() {
/* 27 */     return this.partialBlockY;
/*    */   }
/*    */   
/*    */   public int getPartialBlockZ() {
/* 31 */     return this.partialBlockZ;
/*    */   }
/*    */   
/*    */   public void setPartialBlockDamage(int i) {
/* 35 */     if (i > 10) {
/* 36 */       i = 10;
/*    */     }
/* 38 */     this.partialBlockProgress = i;
/*    */   }
/*    */   
/*    */   public int getPartialBlockDamage() {
/* 42 */     return this.partialBlockProgress;
/*    */   }
/*    */   
/*    */   public void setCloudUpdateTick(int i) {
/* 46 */     this.createdAtCloudUpdateTick = i;
/*    */   }
/*    */   
/*    */   public int getCreationCloudUpdateTick() {
/* 50 */     return this.createdAtCloudUpdateTick;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\DestroyBlockProgress.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */