/*     */ package net.minecraft;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CaveNetworkStub
/*     */ {
/*     */   private final int origin_chunk_x;
/*     */   private final int origin_chunk_z;
/*     */   private final int size_x;
/*     */   private final int size_y;
/*     */   private final int size_z;
/*     */   private final long seed;
/*     */   private long legacy_seed;
/*     */   private final boolean has_mycelium;
/*     */   private final boolean allows_water;
/*     */   private final boolean allows_lava;
/*     */   
/*     */   public CaveNetworkStub(int origin_chunk_x, int origin_chunk_z, int size_x, int size_y, int size_z, long seed, boolean has_mycelium, boolean allows_water, boolean allows_lava) {
/*  19 */     this.origin_chunk_x = origin_chunk_x;
/*  20 */     this.origin_chunk_z = origin_chunk_z;
/*     */     
/*  22 */     this.size_x = size_x;
/*  23 */     this.size_y = size_y;
/*  24 */     this.size_z = size_z;
/*     */     
/*  26 */     this.seed = seed;
/*     */     
/*  28 */     if (seed == 2617667064333438329L && origin_chunk_x == -14 && origin_chunk_z == 29) {
/*     */       
/*  30 */       has_mycelium = true;
/*  31 */       setLegacySeed(2375913967323326907L);
/*     */     } 
/*     */     
/*  34 */     this.has_mycelium = has_mycelium;
/*     */     
/*  36 */     if (has_mycelium) {
/*     */       
/*  38 */       allows_water = false;
/*  39 */       allows_lava = false;
/*     */     } 
/*     */     
/*  42 */     this.allows_water = allows_water;
/*  43 */     this.allows_lava = allows_lava;
/*     */   }
/*     */ 
/*     */   
/*     */   public CaveNetworkStub setLegacySeed(long legacy_seed) {
/*  48 */     this.legacy_seed = legacy_seed;
/*  49 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasLegacySeed() {
/*  54 */     return (getLegacySeed() != 0L);
/*     */   }
/*     */ 
/*     */   
/*     */   public long getLegacySeed() {
/*  59 */     return this.legacy_seed;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOriginChunkX() {
/*  64 */     return this.origin_chunk_x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getOriginChunkZ() {
/*  69 */     return this.origin_chunk_z;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOriginChunkCoordsHash() {
/*  75 */     return Chunk.getChunkCoordsHash(getOriginChunkX(), getOriginChunkZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSizeX() {
/*  80 */     return this.size_x;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSizeY() {
/*  85 */     return this.size_y;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSizeZ() {
/*  90 */     return this.size_z;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSeed() {
/*  95 */     return this.seed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMycelium() {
/* 100 */     return this.has_mycelium;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowsWater() {
/* 105 */     return this.allows_water;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowsLava() {
/* 110 */     return this.allows_lava;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean preventsAllLiquids() {
/* 115 */     return (!allowsWater() && !allowsLava());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 120 */     return "CN Stub: [" + this.origin_chunk_x + "," + this.origin_chunk_z + "], M=" + StringHelper.getBooleanAsLetter(hasMycelium()) + ", W=" + StringHelper.getBooleanAsLetter(allowsWater()) + ", L=" + StringHelper.getBooleanAsLetter(allowsLava()) + ", seed=" + this.seed + (hasLegacySeed() ? " (legacy)" : "");
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\CaveNetworkStub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */