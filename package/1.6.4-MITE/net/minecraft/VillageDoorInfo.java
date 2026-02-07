/*    */ package net.minecraft;
/*    */ 
/*    */ public class VillageDoorInfo {
/*    */   public VillageDoorInfo(int i, int j, int k, int l, int m, int n) {
/*  5 */     this.posX = i;
/*  6 */     this.posY = j;
/*  7 */     this.posZ = k;
/*  8 */     this.insideDirectionX = l;
/*  9 */     this.insideDirectionZ = m;
/* 10 */     this.lastActivityTimestamp = n;
/*    */   }
/*    */ 
/*    */   
/*    */   public final int posX;
/*    */   
/*    */   public final int posY;
/*    */   
/*    */   public final int posZ;
/*    */   
/*    */   public final int insideDirectionX;
/*    */   public final int insideDirectionZ;
/*    */   public int lastActivityTimestamp;
/*    */   public boolean isDetachedFromVillageFlag;
/*    */   private int doorOpeningRestrictionCounter;
/*    */   
/*    */   public int getDistanceSquared(int i, int j, int k) {
/* 27 */     int m = i - this.posX;
/* 28 */     int n = j - this.posY;
/* 29 */     int i1 = k - this.posZ;
/* 30 */     return m * m + n * n + i1 * i1;
/*    */   }
/*    */   
/*    */   public int getInsideDistanceSquare(int i, int j, int k) {
/* 34 */     int m = i - this.posX - this.insideDirectionX;
/* 35 */     int n = j - this.posY;
/* 36 */     int i1 = k - this.posZ - this.insideDirectionZ;
/* 37 */     return m * m + n * n + i1 * i1;
/*    */   }
/*    */   
/*    */   public int getInsidePosX() {
/* 41 */     return this.posX + this.insideDirectionX;
/*    */   }
/*    */   
/*    */   public int getInsidePosY() {
/* 45 */     return this.posY;
/*    */   }
/*    */   
/*    */   public int getInsidePosZ() {
/* 49 */     return this.posZ + this.insideDirectionZ;
/*    */   }
/*    */   
/*    */   public boolean isInside(int i, int j) {
/* 53 */     int k = i - this.posX;
/* 54 */     int m = j - this.posZ;
/* 55 */     return (k * this.insideDirectionX + m * this.insideDirectionZ >= 0);
/*    */   }
/*    */   
/*    */   public void resetDoorOpeningRestrictionCounter() {
/* 59 */     this.doorOpeningRestrictionCounter = 0;
/*    */   }
/*    */   
/*    */   public void incrementDoorOpeningRestrictionCounter() {
/* 63 */     this.doorOpeningRestrictionCounter++;
/*    */   }
/*    */   
/*    */   public int getDoorOpeningRestrictionCounter() {
/* 67 */     return this.doorOpeningRestrictionCounter;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\VillageDoorInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */