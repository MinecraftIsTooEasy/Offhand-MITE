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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class GenLayer
/*     */ {
/*     */   protected long worldGenSeed;
/*     */   protected GenLayer parent;
/*     */   protected long chunkSeed;
/*     */   private long baseSeed;
/*     */   
/*     */   public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType) {
/*  28 */     GenLayerIsland var3 = new GenLayerIsland(1L);
/*  29 */     GenLayerFuzzyZoom var9 = new GenLayerFuzzyZoom(2000L, var3);
/*  30 */     GenLayerAddIsland var10 = new GenLayerAddIsland(1L, var9);
/*  31 */     GenLayerZoom var11 = new GenLayerZoom(2001L, var10);
/*  32 */     var10 = new GenLayerAddIsland(2L, var11);
/*  33 */     GenLayerAddSnow var12 = new GenLayerAddSnow(2L, var10);
/*  34 */     var11 = new GenLayerZoom(2002L, var12);
/*  35 */     var10 = new GenLayerAddIsland(3L, var11);
/*  36 */     var11 = new GenLayerZoom(2003L, var10);
/*  37 */     var10 = new GenLayerAddIsland(4L, var11);
/*  38 */     GenLayerAddMushroomIsland var16 = new GenLayerAddMushroomIsland(5L, var10);
/*  39 */     byte var4 = 4;
/*     */     
/*  41 */     if (par2WorldType == WorldType.LARGE_BIOMES)
/*     */     {
/*  43 */       var4 = 6;
/*     */     }
/*     */     
/*  46 */     GenLayer var5 = GenLayerZoom.magnify(1000L, var16, 0);
/*  47 */     GenLayerRiverInit var13 = new GenLayerRiverInit(100L, var5);
/*  48 */     var5 = GenLayerZoom.magnify(1000L, var13, var4 + 2);
/*  49 */     GenLayerRiver var14 = new GenLayerRiver(1L, var5);
/*  50 */     GenLayerSmooth var15 = new GenLayerSmooth(1000L, var14);
/*  51 */     GenLayer var6 = GenLayerZoom.magnify(1000L, var16, 0);
/*  52 */     GenLayerBiome var17 = new GenLayerBiome(200L, var6, par2WorldType);
/*  53 */     var6 = GenLayerZoom.magnify(1000L, var17, 2);
/*  54 */     Object var18 = new GenLayerHills(1000L, var6);
/*     */     
/*  56 */     for (int var7 = 0; var7 < var4; var7++) {
/*     */       
/*  58 */       var18 = new GenLayerZoom((1000 + var7), (GenLayer)var18);
/*     */       
/*  60 */       if (var7 == 0)
/*     */       {
/*  62 */         var18 = new GenLayerAddIsland(3L, (GenLayer)var18);
/*     */       }
/*     */       
/*  65 */       if (var7 == 1)
/*     */       {
/*  67 */         var18 = new GenLayerShore(1000L, (GenLayer)var18);
/*     */       }
/*     */       
/*  70 */       if (var7 == 1)
/*     */       {
/*  72 */         var18 = new GenLayerSwampRivers(1000L, (GenLayer)var18);
/*     */       }
/*     */     } 
/*     */     
/*  76 */     GenLayerSmooth var19 = new GenLayerSmooth(1000L, (GenLayer)var18);
/*  77 */     GenLayerRiverMix var20 = new GenLayerRiverMix(100L, var19, var15);
/*  78 */     GenLayerVoronoiZoom var8 = new GenLayerVoronoiZoom(10L, var20);
/*  79 */     var20.initWorldGenSeed(par0);
/*  80 */     var8.initWorldGenSeed(par0);
/*  81 */     return new GenLayer[] { var20, var8, var20 };
/*     */   }
/*     */ 
/*     */   
/*     */   public GenLayer(long par1) {
/*  86 */     this.baseSeed = par1;
/*  87 */     this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
/*  88 */     this.baseSeed += par1;
/*  89 */     this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
/*  90 */     this.baseSeed += par1;
/*  91 */     this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
/*  92 */     this.baseSeed += par1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initWorldGenSeed(long par1) {
/* 101 */     this.worldGenSeed = par1;
/*     */     
/* 103 */     if (this.parent != null)
/*     */     {
/* 105 */       this.parent.initWorldGenSeed(par1);
/*     */     }
/*     */     
/* 108 */     this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
/* 109 */     this.worldGenSeed += this.baseSeed;
/* 110 */     this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
/* 111 */     this.worldGenSeed += this.baseSeed;
/* 112 */     this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
/* 113 */     this.worldGenSeed += this.baseSeed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initChunkSeed(long par1, long par3) {
/* 121 */     this.chunkSeed = this.worldGenSeed;
/* 122 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 123 */     this.chunkSeed += par1;
/* 124 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 125 */     this.chunkSeed += par3;
/* 126 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 127 */     this.chunkSeed += par1;
/* 128 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 129 */     this.chunkSeed += par3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int nextInt(int par1) {
/* 137 */     int var2 = (int)((this.chunkSeed >> 24L) % par1);
/*     */     
/* 139 */     if (var2 < 0)
/*     */     {
/* 141 */       var2 += par1;
/*     */     }
/*     */     
/* 144 */     this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 145 */     this.chunkSeed += this.worldGenSeed;
/* 146 */     return var2;
/*     */   }
/*     */   
/*     */   public abstract int[] getInts(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */