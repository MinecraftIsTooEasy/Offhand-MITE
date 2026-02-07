/*    */ package net.minecraft;
/*    */ 
/*    */ public class GenLayerRiver
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerRiver(long par1, GenLayer par3GenLayer) {
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
/*    */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/* 18 */     int var5 = par1 - 1;
/* 19 */     int var6 = par2 - 1;
/* 20 */     int var7 = par3 + 2;
/* 21 */     int var8 = par4 + 2;
/*    */     
/* 23 */     int[] var9 = this.parent.getInts(var5, var6, var7, var8, z);
/* 24 */     int[] var10 = IntCache.getIntCache(par3 * par4);
/*    */     
/* 26 */     for (int var11 = 0; var11 < par4; var11++) {
/*    */       
/* 28 */       for (int var12 = 0; var12 < par3; var12++) {
/*    */         
/* 30 */         int var13 = var9[var12 + 0 + (var11 + 1) * var7];
/* 31 */         int var14 = var9[var12 + 2 + (var11 + 1) * var7];
/* 32 */         int var15 = var9[var12 + 1 + (var11 + 0) * var7];
/* 33 */         int var16 = var9[var12 + 1 + (var11 + 2) * var7];
/* 34 */         int var17 = var9[var12 + 1 + (var11 + 1) * var7];
/*    */         
/* 36 */         if (var17 != 0 && var13 != 0 && var14 != 0 && var15 != 0 && var16 != 0 && var17 == var13 && var17 == var15 && var17 == var14 && var17 == var16) {
/*    */           
/* 38 */           var10[var12 + var11 * par3] = -1;
/*    */         }
/*    */         else {
/*    */           
/* 42 */           var10[var12 + var11 * par3] = BiomeGenBase.river.biomeID;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     return var10;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerRiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */