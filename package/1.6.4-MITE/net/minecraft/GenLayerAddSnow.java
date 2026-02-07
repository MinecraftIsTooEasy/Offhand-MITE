/*    */ package net.minecraft;
/*    */ 
/*    */ public class GenLayerAddSnow
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerAddSnow(long par1, GenLayer par3GenLayer) {
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
/* 30 */         int var13 = var9[var12 + 1 + (var11 + 1) * var7];
/* 31 */         initChunkSeed((var12 + par1), (var11 + par2));
/*    */         
/* 33 */         if (var13 == 0) {
/*    */           
/* 35 */           var10[var12 + var11 * par3] = 0;
/*    */         }
/*    */         else {
/*    */           
/* 39 */           int var14 = nextInt(5);
/*    */           
/* 41 */           if (var14 == 0) {
/*    */             
/* 43 */             var14 = BiomeGenBase.icePlains.biomeID;
/*    */           }
/*    */           else {
/*    */             
/* 47 */             var14 = 1;
/*    */           } 
/*    */           
/* 50 */           var10[var12 + var11 * par3] = var14;
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 55 */     return var10;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerAddSnow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */