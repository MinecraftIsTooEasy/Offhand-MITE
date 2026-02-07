/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ public class ChunkCoordinates
/*     */   implements Comparable
/*     */ {
/*     */   public int posX;
/*     */   public int posY;
/*     */   public int posZ;
/*     */   
/*     */   public ChunkCoordinates() {}
/*     */   
/*     */   public ChunkCoordinates(int i, int j, int k) {
/*  14 */     this.posX = i;
/*  15 */     this.posY = j;
/*  16 */     this.posZ = k;
/*     */   }
/*     */   
/*     */   public ChunkCoordinates(ChunkCoordinates chunkCoordinates) {
/*  20 */     this.posX = chunkCoordinates.posX;
/*  21 */     this.posY = chunkCoordinates.posY;
/*  22 */     this.posZ = chunkCoordinates.posZ;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/*  27 */     if (!(object instanceof ChunkCoordinates)) {
/*  28 */       return false;
/*     */     }
/*     */     
/*  31 */     ChunkCoordinates chunkCoordinates = (ChunkCoordinates)object;
/*  32 */     return (this.posX == chunkCoordinates.posX && this.posY == chunkCoordinates.posY && this.posZ == chunkCoordinates.posZ);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  37 */     return this.posX + this.posZ << 8 + this.posY << 16;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareChunkCoordinate(ChunkCoordinates chunkCoordinates) {
/*  42 */     if (this.posY == chunkCoordinates.posY) {
/*  43 */       if (this.posZ == chunkCoordinates.posZ) {
/*  44 */         return this.posX - chunkCoordinates.posX;
/*     */       }
/*  46 */       return this.posZ - chunkCoordinates.posZ;
/*     */     } 
/*  48 */     return this.posY - chunkCoordinates.posY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void set(int i, int j, int k) {
/*  56 */     this.posX = i;
/*  57 */     this.posY = j;
/*  58 */     this.posZ = k;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getDistanceSquared(int i, int j, int k) {
/* 200 */     float f1 = (this.posX - i);
/* 201 */     float f2 = (this.posY - j);
/* 202 */     float f3 = (this.posZ - k);
/* 203 */     return f1 * f1 + f2 * f2 + f3 * f3;
/*     */   }
/*     */   
/*     */   public float getDistanceSquaredToChunkCoordinates(ChunkCoordinates chunkCoordinates) {
/* 207 */     return getDistanceSquared(chunkCoordinates.posX, chunkCoordinates.posY, chunkCoordinates.posZ);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkCoordinates.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */