/*    */ package net.minecraft;
/*    */ 
/*    */ public class GenLayerSwampRivers
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerSwampRivers(long par1, GenLayer par3GenLayer) {
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
/* 29 */         if ((var9 != BiomeGenBase.swampland.biomeID || nextInt(6) != 0) && ((var9 != BiomeGenBase.jungle.biomeID && var9 != BiomeGenBase.jungleHills.biomeID) || nextInt(8) != 0)) {
/*    */           
/* 31 */           var6[var8 + var7 * par3] = var9;
/*    */         }
/* 33 */         else if (var9 == BiomeGenBase.jungle.biomeID || var9 == BiomeGenBase.jungleHills.biomeID) {
/*    */           
/* 35 */           var6[var8 + var7 * par3] = BiomeGenBase.jungleRiver.biomeID;
/*    */         }
/* 37 */         else if (var9 == BiomeGenBase.swampland.biomeID) {
/*    */           
/* 39 */           var6[var8 + var7 * par3] = BiomeGenBase.swampRiver.biomeID;
/*    */         }
/*    */         else {
/*    */           
/* 43 */           var6[var8 + var7 * par3] = BiomeGenBase.river.biomeID;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 48 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerSwampRivers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */