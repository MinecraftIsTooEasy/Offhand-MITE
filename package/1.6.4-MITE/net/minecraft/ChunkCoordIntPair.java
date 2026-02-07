/*    */ package net.minecraft;
/*    */ 
/*    */ public class ChunkCoordIntPair
/*    */ {
/*    */   public final int chunkXPos;
/*    */   public final int chunkZPos;
/*    */   
/*    */   public ChunkCoordIntPair(int i, int j) {
/*  9 */     this.chunkXPos = i;
/* 10 */     this.chunkZPos = j;
/*    */   }
/*    */   
/*    */   public static long chunkXZ2Int(int i, int j) {
/* 14 */     return i & 0xFFFFFFFFL | (j & 0xFFFFFFFFL) << 32L;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 18 */     long l = chunkXZ2Int(this.chunkXPos, this.chunkZPos);
/* 19 */     int i = (int)l;
/* 20 */     int j = (int)(l >> 32L);
/* 21 */     return i ^ j;
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 25 */     ChunkCoordIntPair chunkCoordIntPair = (ChunkCoordIntPair)object;
/* 26 */     return (chunkCoordIntPair.chunkXPos == this.chunkXPos && chunkCoordIntPair.chunkZPos == this.chunkZPos);
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
/*    */   public int getCenterXPos() {
/* 40 */     return (this.chunkXPos << 4) + 8;
/*    */   }
/*    */   
/*    */   public int getCenterZPosition() {
/* 44 */     return (this.chunkZPos << 4) + 8;
/*    */   }
/*    */   
/*    */   public ChunkPosition getChunkPosition(int i) {
/* 48 */     return new ChunkPosition(getCenterXPos(), i, getCenterZPosition());
/*    */   }
/*    */   
/*    */   public String toString() {
/* 52 */     return "[" + this.chunkXPos + ", " + this.chunkZPos + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkCoordIntPair.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */