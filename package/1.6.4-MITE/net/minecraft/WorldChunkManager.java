/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldChunkManager
/*     */ {
/*     */   private GenLayer genBiomes;
/*     */   private GenLayer biomeIndexLayer;
/*     */   private BiomeCache biomeCache;
/*     */   private List biomesToSpawnIn;
/*     */   
/*     */   protected WorldChunkManager() {
/*  22 */     this.biomeCache = new BiomeCache(this);
/*  23 */     this.biomesToSpawnIn = new ArrayList();
/*  24 */     this.biomesToSpawnIn.add(BiomeGenBase.forest);
/*  25 */     this.biomesToSpawnIn.add(BiomeGenBase.plains);
/*  26 */     this.biomesToSpawnIn.add(BiomeGenBase.taiga);
/*  27 */     this.biomesToSpawnIn.add(BiomeGenBase.taigaHills);
/*  28 */     this.biomesToSpawnIn.add(BiomeGenBase.forestHills);
/*  29 */     this.biomesToSpawnIn.add(BiomeGenBase.jungle);
/*  30 */     this.biomesToSpawnIn.add(BiomeGenBase.jungleHills);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldChunkManager(long par1, WorldType par3WorldType) {
/*  35 */     this();
/*  36 */     GenLayer[] var4 = GenLayer.initializeAllBiomeGenerators(par1, par3WorldType);
/*  37 */     this.genBiomes = var4[0];
/*  38 */     this.biomeIndexLayer = var4[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldChunkManager(World par1World) {
/*  43 */     this(par1World.getSeed(), par1World.getWorldInfo().getTerrainType());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getBiomesToSpawnIn() {
/*  51 */     return this.biomesToSpawnIn;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase getBiomeGenAt(int par1, int par2) {
/*  59 */     return this.biomeCache.getBiomeGenAt(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getRainfall(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
/*  67 */     IntCache.resetIntCache();
/*     */     
/*  69 */     if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
/*     */     {
/*  71 */       par1ArrayOfFloat = new float[par4 * par5];
/*     */     }
/*     */ 
/*     */     
/*  75 */     int[] var6 = this.biomeIndexLayer.getInts(par2, par3, par4, par5, par3);
/*     */     
/*  77 */     for (int var7 = 0; var7 < par4 * par5; var7++) {
/*     */       
/*  79 */       if (var7 < 0) {
/*  80 */         System.out.println("var7 was an invalid index: " + var7);
/*     */       }
/*  82 */       if (var6[var7] < 0) {
/*     */         
/*  84 */         System.out.println("var6[var7] was an invalid index: " + var6[var7]);
/*  85 */         System.out.println("block range: " + par2 + "," + par3 + " to " + (par2 + par4 - 1) + "," + (par3 + par5 - 1));
/*     */       } 
/*     */       
/*  88 */       float var8 = BiomeGenBase.biomeList[var6[var7]].getIntRainfall() / 65536.0F;
/*     */       
/*  90 */       if (var8 > 1.0F)
/*     */       {
/*  92 */         var8 = 1.0F;
/*     */       }
/*     */       
/*  95 */       par1ArrayOfFloat[var7] = var8;
/*     */     } 
/*     */     
/*  98 */     return par1ArrayOfFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTemperatureAtHeight(float par1, int par2) {
/* 106 */     return par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getTemperatures(float[] par1ArrayOfFloat, int par2, int par3, int par4, int par5) {
/* 114 */     IntCache.resetIntCache();
/*     */     
/* 116 */     if (par1ArrayOfFloat == null || par1ArrayOfFloat.length < par4 * par5)
/*     */     {
/* 118 */       par1ArrayOfFloat = new float[par4 * par5];
/*     */     }
/*     */ 
/*     */     
/* 122 */     int[] var6 = this.biomeIndexLayer.getInts(par2, par3, par4, par5, par3);
/*     */     
/* 124 */     for (int var7 = 0; var7 < par4 * par5; var7++) {
/*     */       
/* 126 */       float var8 = BiomeGenBase.biomeList[var6[var7]].getIntTemperature() / 65536.0F;
/*     */       
/* 128 */       if (var8 > 1.0F)
/*     */       {
/* 130 */         var8 = 1.0F;
/*     */       }
/*     */       
/* 133 */       par1ArrayOfFloat[var7] = var8;
/*     */     } 
/*     */     
/* 136 */     return par1ArrayOfFloat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
/* 144 */     IntCache.resetIntCache();
/*     */     
/* 146 */     if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
/*     */     {
/* 148 */       par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
/*     */     }
/*     */ 
/*     */     
/* 152 */     int[] var6 = this.genBiomes.getInts(par2, par3, par4, par5, (par3 + 2) * 4);
/*     */     
/* 154 */     for (int var7 = 0; var7 < par4 * par5; var7++)
/*     */     {
/* 156 */       par1ArrayOfBiomeGenBase[var7] = BiomeGenBase.biomeList[var6[var7]];
/*     */     }
/*     */     
/* 159 */     return par1ArrayOfBiomeGenBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
/* 168 */     return getBiomeGenAt(par1ArrayOfBiomeGenBase, par2, par3, par4, par5, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5, boolean par6) {
/* 177 */     IntCache.resetIntCache();
/*     */     
/* 179 */     if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5)
/*     */     {
/* 181 */       par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
/*     */     }
/*     */     
/* 184 */     if (par6 && par4 == 16 && par5 == 16 && (par2 & 0xF) == 0 && (par3 & 0xF) == 0) {
/*     */       
/* 186 */       BiomeGenBase[] var9 = this.biomeCache.getCachedBiomes(par2, par3);
/* 187 */       System.arraycopy(var9, 0, par1ArrayOfBiomeGenBase, 0, par4 * par5);
/* 188 */       return par1ArrayOfBiomeGenBase;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 193 */     int[] var7 = this.biomeIndexLayer.getInts(par2, par3, par4, par5, par3);
/*     */     
/* 195 */     for (int var8 = 0; var8 < par4 * par5; var8++)
/*     */     {
/* 197 */       par1ArrayOfBiomeGenBase[var8] = BiomeGenBase.biomeList[var7[var8]];
/*     */     }
/*     */     
/* 200 */     return par1ArrayOfBiomeGenBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean areBiomesViable(int par1, int par2, int par3, List par4List) {
/* 209 */     IntCache.resetIntCache();
/* 210 */     int var5 = par1 - par3 >> 2;
/* 211 */     int var6 = par2 - par3 >> 2;
/* 212 */     int var7 = par1 + par3 >> 2;
/* 213 */     int var8 = par2 + par3 >> 2;
/* 214 */     int var9 = var7 - var5 + 1;
/* 215 */     int var10 = var8 - var6 + 1;
/*     */ 
/*     */     
/* 218 */     int[] var11 = this.genBiomes.getInts(var5, var6, var9, var10, par2);
/*     */     
/* 220 */     for (int var12 = 0; var12 < var9 * var10; var12++) {
/*     */       
/* 222 */       if (var11[var12] < 0) {
/*     */         
/* 224 */         System.out.println("MITE: prevented rare out of bounds bug in areBiomesViable()");
/* 225 */         return false;
/*     */       } 
/*     */       
/* 228 */       BiomeGenBase var13 = BiomeGenBase.biomeList[var11[var12]];
/*     */       
/* 230 */       if (!par4List.contains(var13))
/*     */       {
/* 232 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 236 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition findBiomePosition(int par1, int par2, int par3, List par4List, Random par5Random) {
/* 245 */     IntCache.resetIntCache();
/* 246 */     int var6 = par1 - par3 >> 2;
/* 247 */     int var7 = par2 - par3 >> 2;
/* 248 */     int var8 = par1 + par3 >> 2;
/* 249 */     int var9 = par2 + par3 >> 2;
/* 250 */     int var10 = var8 - var6 + 1;
/* 251 */     int var11 = var9 - var7 + 1;
/*     */     
/* 253 */     int[] var12 = this.genBiomes.getInts(var6, var7, var10, var11, par2);
/* 254 */     ChunkPosition var13 = null;
/* 255 */     int var14 = 0;
/*     */     
/* 257 */     for (int var15 = 0; var15 < var10 * var11; var15++) {
/*     */       
/* 259 */       int var16 = var6 + var15 % var10 << 2;
/* 260 */       int var17 = var7 + var15 / var10 << 2;
/* 261 */       BiomeGenBase var18 = BiomeGenBase.biomeList[var12[var15]];
/*     */       
/* 263 */       if (par4List.contains(var18) && (var13 == null || par5Random.nextInt(var14 + 1) == 0)) {
/*     */         
/* 265 */         var13 = new ChunkPosition(var16, 0, var17);
/* 266 */         var14++;
/*     */       } 
/*     */     } 
/*     */     
/* 270 */     return var13;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cleanupCache() {
/* 278 */     this.biomeCache.cleanupCache();
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldChunkManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */