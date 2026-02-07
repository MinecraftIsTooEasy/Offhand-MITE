/*    */ package net.minecraft;
/*    */ 
/*    */ public class GenLayerShore
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerShore(long par1, GenLayer par3GenLayer) {
/*  7 */     super(par1);
/*  8 */     this.parent = par3GenLayer;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/* 19 */     int[] var5 = this.parent.getInts(par1 - 1, par2 - 1, par3 + 2, par4 + 2, z);
/* 20 */     int[] var6 = IntCache.getIntCache(par3 * par4);
/*    */     
/* 22 */     for (int var7 = 0; var7 < par4; var7++) {
/*    */       
/* 24 */       for (int var8 = 0; var8 < par3; var8++) {
/*    */         
/* 26 */         initChunkSeed((var8 + par1), (var7 + par2));
/* 27 */         int var9 = var5[var8 + 1 + (var7 + 1) * (par3 + 2)];
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
/* 49 */         if (var9 != BiomeGenBase.ocean.biomeID && var9 != BiomeGenBase.river.biomeID && var9 != BiomeGenBase.swampland.biomeID && var9 != BiomeGenBase.extremeHills.biomeID) {
/*    */           
/* 51 */           int var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
/* 52 */           int var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
/* 53 */           int var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
/* 54 */           int var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];
/*    */           
/* 56 */           if (var10 != BiomeGenBase.ocean.biomeID && var11 != BiomeGenBase.ocean.biomeID && var12 != BiomeGenBase.ocean.biomeID && var13 != BiomeGenBase.ocean.biomeID)
/*    */           {
/* 58 */             var6[var8 + var7 * par3] = var9;
/*    */           }
/*    */           else
/*    */           {
/* 62 */             var6[var8 + var7 * par3] = BiomeGenBase.beach.biomeID;
/*    */           }
/*    */         
/* 65 */         } else if (var9 == BiomeGenBase.extremeHills.biomeID) {
/*    */           
/* 67 */           int var10 = var5[var8 + 1 + (var7 + 1 - 1) * (par3 + 2)];
/* 68 */           int var11 = var5[var8 + 1 + 1 + (var7 + 1) * (par3 + 2)];
/* 69 */           int var12 = var5[var8 + 1 - 1 + (var7 + 1) * (par3 + 2)];
/* 70 */           int var13 = var5[var8 + 1 + (var7 + 1 + 1) * (par3 + 2)];
/*    */           
/* 72 */           if (var10 == BiomeGenBase.extremeHills.biomeID && var11 == BiomeGenBase.extremeHills.biomeID && var12 == BiomeGenBase.extremeHills.biomeID && var13 == BiomeGenBase.extremeHills.biomeID)
/*    */           {
/* 74 */             var6[var8 + var7 * par3] = var9;
/*    */           }
/*    */           else
/*    */           {
/* 78 */             var6[var8 + var7 * par3] = BiomeGenBase.extremeHillsEdge.biomeID;
/*    */           }
/*    */         
/*    */         } else {
/*    */           
/* 83 */           var6[var8 + var7 * par3] = var9;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 88 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerShore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */