/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.MinecraftServer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeCache
/*    */ {
/*    */   private final WorldChunkManager chunkManager;
/*    */   private long lastCleanupTime;
/* 46 */   private LongHashMap cacheMap = new LongHashMap();
/* 47 */   private List cache = new ArrayList();
/*    */   
/*    */   public BiomeCache(WorldChunkManager worldChunkManager) {
/* 50 */     this.chunkManager = worldChunkManager;
/*    */   }
/*    */   
/*    */   public BiomeCacheBlock getBiomeCacheBlock(int i, int j) {
/* 54 */     i >>= 4;
/* 55 */     j >>= 4;
/* 56 */     long l = i & 0xFFFFFFFFL | (j & 0xFFFFFFFFL) << 32L;
/* 57 */     BiomeCacheBlock biomeCacheBlock = (BiomeCacheBlock)this.cacheMap.getValueByKey(l);
/* 58 */     if (biomeCacheBlock == null) {
/* 59 */       biomeCacheBlock = new BiomeCacheBlock(this, i, j);
/* 60 */       this.cacheMap.add(l, biomeCacheBlock);
/* 61 */       this.cache.add(biomeCacheBlock);
/*    */     } 
/* 63 */     biomeCacheBlock.lastAccessTime = MinecraftServer.getSystemTimeMillis();
/* 64 */     return biomeCacheBlock;
/*    */   }
/*    */   
/*    */   public BiomeGenBase getBiomeGenAt(int i, int j) {
/* 68 */     return getBiomeCacheBlock(i, j).getBiomeGenAt(i, j);
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
/*    */   public void cleanupCache() {
/* 80 */     long l1 = MinecraftServer.getSystemTimeMillis();
/* 81 */     long l2 = l1 - this.lastCleanupTime;
/* 82 */     if (l2 > 7500L || l2 < 0L) {
/* 83 */       this.lastCleanupTime = l1;
/*    */       
/* 85 */       for (byte b = 0; b < this.cache.size(); b++) {
/* 86 */         BiomeCacheBlock biomeCacheBlock = this.cache.get(b);
/* 87 */         long l = l1 - biomeCacheBlock.lastAccessTime;
/* 88 */         if (l > 30000L || l < 0L) {
/* 89 */           this.cache.remove(b--);
/* 90 */           long l3 = biomeCacheBlock.xPosition & 0xFFFFFFFFL | (biomeCacheBlock.zPosition & 0xFFFFFFFFL) << 32L;
/* 91 */           this.cacheMap.remove(l3);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public BiomeGenBase[] getCachedBiomes(int i, int j) {
/* 98 */     return (getBiomeCacheBlock(i, j)).biomes;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */