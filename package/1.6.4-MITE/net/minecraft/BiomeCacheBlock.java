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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeCacheBlock
/*    */ {
/* 19 */   public float[] temperatureValues = new float[256];
/* 20 */   public float[] rainfallValues = new float[256];
/* 21 */   public BiomeGenBase[] biomes = new BiomeGenBase[256];
/*    */   
/*    */   public int xPosition;
/*    */   
/*    */   public BiomeCacheBlock(BiomeCache biomeCache, int i, int j) {
/* 26 */     this.xPosition = i;
/* 27 */     this.zPosition = j;
/* 28 */     BiomeCache.getChunkManager(biomeCache).getTemperatures(this.temperatureValues, i << 4, j << 4, 16, 16);
/* 29 */     BiomeCache.getChunkManager(biomeCache).getRainfall(this.rainfallValues, i << 4, j << 4, 16, 16);
/* 30 */     BiomeCache.getChunkManager(biomeCache).getBiomeGenAt(this.biomes, i << 4, j << 4, 16, 16, false);
/*    */   }
/*    */   public int zPosition; public long lastAccessTime;
/*    */   public BiomeGenBase getBiomeGenAt(int i, int j) {
/* 34 */     return this.biomes[i & 0xF | (j & 0xF) << 4];
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeCacheBlock.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */