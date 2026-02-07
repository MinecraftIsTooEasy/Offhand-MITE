/*    */ package net.minecraft;
/*    */ 
/*    */ public class BlockEventData
/*    */ {
/*    */   private int coordX;
/*    */   private int coordY;
/*    */   private int coordZ;
/*    */   
/*    */   public BlockEventData(int i, int j, int k, int l, int m, int n) {
/* 10 */     this.coordX = i;
/* 11 */     this.coordY = j;
/* 12 */     this.coordZ = k;
/* 13 */     this.eventID = m;
/* 14 */     this.eventParameter = n;
/* 15 */     this.blockID = l;
/*    */   }
/*    */   private int blockID; private int eventID; private int eventParameter;
/*    */   public int getX() {
/* 19 */     return this.coordX;
/*    */   }
/*    */   
/*    */   public int getY() {
/* 23 */     return this.coordY;
/*    */   }
/*    */   
/*    */   public int getZ() {
/* 27 */     return this.coordZ;
/*    */   }
/*    */   
/*    */   public int getEventID() {
/* 31 */     return this.eventID;
/*    */   }
/*    */   
/*    */   public int getEventParameter() {
/* 35 */     return this.eventParameter;
/*    */   }
/*    */   
/*    */   public int getBlockID() {
/* 39 */     return this.blockID;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 44 */     if (object instanceof BlockEventData) {
/* 45 */       BlockEventData blockEventData = (BlockEventData)object;
/* 46 */       return (this.coordX == blockEventData.coordX && this.coordY == blockEventData.coordY && this.coordZ == blockEventData.coordZ && this.eventID == blockEventData.eventID && this.eventParameter == blockEventData.eventParameter && this.blockID == blockEventData.blockID);
/*    */     } 
/* 48 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return "TE(" + this.coordX + "," + this.coordY + "," + this.coordZ + ")," + this.eventID + "," + this.eventParameter + "," + this.blockID;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BlockEventData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */