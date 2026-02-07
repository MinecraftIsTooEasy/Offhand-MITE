/*    */ package net.minecraft;
/*    */ 
/*    */ public class GenLayerIsland
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerIsland(long par1) {
/*  7 */     super(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getInts(int par1, int par2, int par3, int par4, int z) {
/* 17 */     int[] var5 = IntCache.getIntCache(par3 * par4);
/*    */     
/* 19 */     for (int var6 = 0; var6 < par4; var6++) {
/*    */       
/* 21 */       for (int var7 = 0; var7 < par3; var7++) {
/*    */         
/* 23 */         initChunkSeed((par1 + var7), (par2 + var6));
/* 24 */         var5[var7 + var6 * par3] = (nextInt(10) == 0) ? 1 : 0;
/*    */       } 
/*    */     } 
/*    */     
/* 28 */     if (par1 > -par3 && par1 <= 0 && par2 > -par4 && par2 <= 0)
/*    */     {
/* 30 */       var5[-par1 + -par2 * par3] = 1;
/*    */     }
/*    */     
/* 33 */     return var5;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerIsland.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */