/*     */ package net.minecraft;
/*     */ 
/*     */ public class GenLayerAddIsland
/*     */   extends GenLayer
/*     */ {
/*     */   public GenLayerAddIsland(long par1, GenLayer par3GenLayer) {
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
/*     */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/*  18 */     int var5 = par1 - 1;
/*  19 */     int var6 = par2 - 1;
/*  20 */     int var7 = par3 + 2;
/*  21 */     int var8 = par4 + 2;
/*     */     
/*  23 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8, z);
/*  24 */     int[] var10 = IntCache.getIntCache(par3 * par4);
/*     */     
/*  26 */     for (int var11 = 0; var11 < par4; var11++) {
/*     */       
/*  28 */       for (int var12 = 0; var12 < par3; var12++) {
/*     */         
/*  30 */         int var13 = var9[var12 + 0 + (var11 + 0) * var7];
/*  31 */         int var14 = var9[var12 + 2 + (var11 + 0) * var7];
/*  32 */         int var15 = var9[var12 + 0 + (var11 + 2) * var7];
/*  33 */         int var16 = var9[var12 + 2 + (var11 + 2) * var7];
/*  34 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/*  35 */         initChunkSeed((var12 + par1), (var11 + par2));
/*     */         
/*  37 */         if (var17 == 0 && (var13 != 0 || var14 != 0 || var15 != 0 || var16 != 0)) {
/*     */           
/*  39 */           int var18 = 1;
/*  40 */           int var19 = 1;
/*     */           
/*  42 */           if (var13 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  44 */             var19 = var13;
/*     */           }
/*     */           
/*  47 */           if (var14 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  49 */             var19 = var14;
/*     */           }
/*     */           
/*  52 */           if (var15 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  54 */             var19 = var15;
/*     */           }
/*     */           
/*  57 */           if (var16 != 0 && nextInt(var18++) == 0)
/*     */           {
/*  59 */             var19 = var16;
/*     */           }
/*     */           
/*  62 */           if (nextInt(3) == 0)
/*     */           {
/*  64 */             var10[var12 + var11 * par3] = var19;
/*     */           }
/*  66 */           else if (var19 == BiomeGenBase.icePlains.biomeID)
/*     */           {
/*  68 */             var10[var12 + var11 * par3] = BiomeGenBase.frozenOcean.biomeID;
/*     */           }
/*     */           else
/*     */           {
/*  72 */             var10[var12 + var11 * par3] = 0;
/*     */           }
/*     */         
/*  75 */         } else if (var17 > 0 && (var13 == 0 || var14 == 0 || var15 == 0 || var16 == 0)) {
/*     */           
/*  77 */           if (nextInt(5) == 0) {
/*     */             
/*  79 */             if (var17 == BiomeGenBase.icePlains.biomeID)
/*     */             {
/*  81 */               var10[var12 + var11 * par3] = BiomeGenBase.frozenOcean.biomeID;
/*     */             }
/*     */             else
/*     */             {
/*  85 */               var10[var12 + var11 * par3] = 0;
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/*  90 */             var10[var12 + var11 * par3] = var17;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/*  95 */           var10[var12 + var11 * par3] = var17;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 100 */     return var10;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerAddIsland.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */