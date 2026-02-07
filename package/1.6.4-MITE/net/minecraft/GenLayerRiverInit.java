/*    */ package net.minecraft;
/*    */ 
/*    */ public class GenLayerRiverInit
/*    */   extends GenLayer
/*    */ {
/*    */   public GenLayerRiverInit(long par1, GenLayer par3GenLayer) {
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
/* 19 */     int[] var5 = this.parent.getInts(par1, par2, par3, par4, z);
/* 20 */     int[] var6 = IntCache.getIntCache(par3 * par4);
/*    */     
/* 22 */     for (int var7 = 0; var7 < par4; var7++) {
/*    */       
/* 24 */       for (int var8 = 0; var8 < par3; var8++) {
/*    */         
/* 26 */         initChunkSeed((var8 + par1), (var7 + par2));
/* 27 */         var6[var8 + var7 * par3] = (var5[var8 + var7 * par3] > 0) ? (nextInt(2) + 2) : 0;
/*    */       } 
/*    */     } 
/*    */     
/* 31 */     return var6;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\GenLayerRiverInit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */