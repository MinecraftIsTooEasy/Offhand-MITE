/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class WorldChunkManagerHell
/*     */   extends WorldChunkManager {
/*     */   private BiomeGenBase biomeToUse;
/*     */   
/*     */   public WorldChunkManagerHell(BiomeGenBase biomeGenBase, float f, float g) {
/*  12 */     this.biomeToUse = biomeGenBase;
/*  13 */     this.hellTemperature = f;
/*  14 */     this.rainfall = g;
/*     */   }
/*     */ 
/*     */   
/*     */   private float hellTemperature;
/*     */   
/*     */   private float rainfall;
/*     */ 
/*     */   
/*     */   public BiomeGenBase getBiomeGenAt(int i, int j) {
/*  24 */     return this.biomeToUse;
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
/*     */   public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] biomeGenBases, int i, int j, int k, int l) {
/*  45 */     if (biomeGenBases == null || biomeGenBases.length < k * l) {
/*  46 */       biomeGenBases = new BiomeGenBase[k * l];
/*     */     }
/*     */     
/*  49 */     Arrays.fill((Object[])biomeGenBases, 0, k * l, this.biomeToUse);
/*     */     
/*  51 */     return biomeGenBases;
/*     */   }
/*     */ 
/*     */   
/*     */   public float[] getTemperatures(float[] fs, int i, int j, int k, int l) {
/*  56 */     if (fs == null || fs.length < k * l) {
/*  57 */       fs = new float[k * l];
/*     */     }
/*     */     
/*  60 */     Arrays.fill(fs, 0, k * l, this.hellTemperature);
/*  61 */     return fs;
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
/*     */   public float[] getRainfall(float[] fs, int i, int j, int k, int l) {
/*  80 */     if (fs == null || fs.length < k * l) {
/*  81 */       fs = new float[k * l];
/*     */     }
/*  83 */     Arrays.fill(fs, 0, k * l, this.rainfall);
/*     */     
/*  85 */     return fs;
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
/*     */   public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] biomeGenBases, int i, int j, int k, int l) {
/* 109 */     if (biomeGenBases == null || biomeGenBases.length < k * l) {
/* 110 */       biomeGenBases = new BiomeGenBase[k * l];
/*     */     }
/*     */     
/* 113 */     Arrays.fill((Object[])biomeGenBases, 0, k * l, this.biomeToUse);
/*     */     
/* 115 */     return biomeGenBases;
/*     */   }
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] biomeGenBases, int i, int j, int k, int l, boolean bl) {
/* 120 */     return loadBlockGeneratorData(biomeGenBases, i, j, k, l);
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
/*     */   public ChunkPosition findBiomePosition(int i, int j, int k, List list, Random random) {
/* 135 */     if (list.contains(this.biomeToUse)) {
/* 136 */       return new ChunkPosition(i - k + random.nextInt(k * 2 + 1), 0, j - k + random.nextInt(k * 2 + 1));
/*     */     }
/*     */     
/* 139 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areBiomesViable(int i, int j, int k, List list) {
/* 149 */     return list.contains(this.biomeToUse);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldChunkManagerHell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */