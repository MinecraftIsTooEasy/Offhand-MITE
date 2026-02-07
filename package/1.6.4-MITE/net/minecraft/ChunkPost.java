/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChunkPost
/*     */ {
/*     */   private final int chunk_x;
/*     */   private final int chunk_z;
/*     */   private final int chunk_base_x;
/*     */   private final int chunk_base_z;
/*     */   private final int local_x;
/*     */   private final int local_z;
/*     */   private final int x;
/*     */   private final int z;
/*     */   private final double pos_x;
/*     */   private final double pos_z;
/*     */   private final long seed;
/*     */   
/*     */   public ChunkPost(int chunk_x, int chunk_z, int local_x, int local_z, long seed) {
/*  26 */     this.chunk_x = chunk_x;
/*  27 */     this.chunk_z = chunk_z;
/*     */     
/*  29 */     this.chunk_base_x = chunk_x * 16;
/*  30 */     this.chunk_base_z = chunk_z * 16;
/*     */     
/*  32 */     this.local_x = local_x;
/*  33 */     this.local_z = local_z;
/*     */     
/*  35 */     this.x = this.chunk_base_x + local_x;
/*  36 */     this.z = this.chunk_base_z + local_z;
/*     */     
/*  38 */     this.pos_x = this.x + 0.5D;
/*  39 */     this.pos_z = this.z + 0.5D;
/*     */     
/*  41 */     this.seed = seed;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChunkX() {
/*  46 */     return this.chunk_x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChunkZ() {
/*  51 */     return this.chunk_z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNonLocalBlockPosX() {
/*  57 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNonLocalBlockPosZ() {
/*  63 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNonLocalPosX() {
/*  69 */     return this.pos_x;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getNonLocalPosZ() {
/*  75 */     return this.pos_z;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  80 */     return this.seed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistanceSqFromBlockCoords(int x, int z) {
/*  86 */     return World.getDistanceSqFromDeltas((this.x - x), (this.z - z));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistanceSqFromPosXZ(double pos_x, double pos_z) {
/*  92 */     return World.getDistanceSqFromDeltas(this.pos_x - pos_x, this.pos_z - pos_z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistanceFromBlockCoords(int x, int z) {
/*  98 */     return World.getDistanceFromDeltas((this.x - x), (this.z - z));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistanceFromPosXZ(double pos_x, double pos_z) {
/* 104 */     return World.getDistanceFromDeltas(this.pos_x - pos_x, this.pos_z - pos_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 109 */     return "[" + this.chunk_x + "," + this.chunk_z + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkPost.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */