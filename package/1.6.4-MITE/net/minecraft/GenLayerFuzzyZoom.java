/*     */ package net.minecraft;
/*     */ 
/*     */ public class GenLayerFuzzyZoom
/*     */   extends GenLayer
/*     */ {
/*     */   public GenLayerFuzzyZoom(long par1, GenLayer par3GenLayer) {
/*   7 */     super(par1);
/*   8 */     this.parent = par3GenLayer;
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
/*     */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/*  59 */     int var5 = par1 >> 1;
/*  60 */     int var6 = par2 >> 1;
/*  61 */     int var7 = (par3 >> 1) + 3;
/*  62 */     int var8 = (par4 >> 1) + 3;
/*     */     
/*  64 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8, z);
/*  65 */     int[] var10 = IntCache.getIntCache(var7 * 2 * var8 * 2);
/*  66 */     int var11 = var7 << 1;
/*     */ 
/*     */     
/*  69 */     for (int var12 = 0; var12 < var8 - 1; var12++) {
/*     */       
/*  71 */       int i = var12 << 1;
/*  72 */       int var14 = i * var11;
/*  73 */       int var15 = var9[0 + (var12 + 0) * var7];
/*  74 */       int var16 = var9[0 + (var12 + 1) * var7];
/*     */       
/*  76 */       for (int var17 = 0; var17 < var7 - 1; var17++) {
/*     */ 
/*     */ 
/*     */         
/*  80 */         long par1_1 = (var17 + var5 << 1);
/*  81 */         long par3_1 = (var12 + var6 << 1);
/*  82 */         this.chunkSeed = this.worldGenSeed;
/*  83 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  84 */         this.chunkSeed += par1_1;
/*  85 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  86 */         this.chunkSeed += par3_1;
/*  87 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  88 */         this.chunkSeed += par1_1;
/*  89 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/*  90 */         this.chunkSeed += par3_1;
/*     */         
/*  92 */         int var18 = var9[var17 + 1 + (var12 + 0) * var7];
/*  93 */         int var19 = var9[var17 + 1 + (var12 + 1) * var7];
/*  94 */         var10[var14] = var15;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  99 */         var10[var14++ + var11] = ((this.chunkSeed >> 24L & 0x1L) == 0L) ? var15 : var16;
/*     */         
/* 101 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 102 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 104 */         var10[var14] = ((this.chunkSeed >> 24L & 0x1L) == 0L) ? var15 : var18;
/*     */         
/* 106 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 107 */         this.chunkSeed += this.worldGenSeed;
/*     */         
/* 109 */         int int_4 = (int)(this.chunkSeed >> 24L & 0x3L);
/*     */         
/* 111 */         this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
/* 112 */         this.chunkSeed += this.worldGenSeed;
/*     */ 
/*     */         
/* 115 */         var10[var14++ + var11] = (int_4 == 0) ? var15 : ((int_4 == 1) ? var18 : ((int_4 == 2) ? var16 : var19));
/*     */ 
/*     */         
/* 118 */         var15 = var18;
/* 119 */         var16 = var19;
/*     */       } 
/*     */     } 
/*     */     
/* 123 */     int[] var20 = IntCache.getIntCache(par3 * par4);
/*     */     
/* 125 */     for (int var13 = 0; var13 < par4; var13++)
/*     */     {
/* 127 */       System.arraycopy(var10, (var13 + (par2 & 0x1)) * (var7 << 1) + (par1 & 0x1), var20, var13 * par3, par3);
/*     */     }
/*     */     
/* 130 */     return var20;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int choose(int par1, int par2) {
/* 138 */     return (nextInt(2) == 0) ? par1 : par2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int choose(int par1, int par2, int par3, int par4) {
/* 146 */     int var5 = nextInt(4);
/* 147 */     return (var5 == 0) ? par1 : ((var5 == 1) ? par2 : ((var5 == 2) ? par3 : par4));
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerFuzzyZoom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */