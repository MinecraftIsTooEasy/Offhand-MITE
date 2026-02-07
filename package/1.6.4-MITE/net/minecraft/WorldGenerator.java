/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class WorldGenerator
/*    */ {
/*    */   private final boolean doBlockNotify;
/*    */   
/*    */   public WorldGenerator() {
/* 16 */     this.doBlockNotify = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenerator(boolean par1) {
/* 21 */     this.doBlockNotify = par1;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract boolean generate(World paramWorld, Random paramRandom, int paramInt1, int paramInt2, int paramInt3);
/*    */ 
/*    */ 
/*    */   
/*    */   public void setScale(double par1, double par3, double par5) {}
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setBlock(World par1World, int par2, int par3, int par4, int par5) {
/* 36 */     setBlockAndMetadata(par1World, par2, par3, par4, par5, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected void setBlockAndMetadata(World par1World, int par2, int par3, int par4, int par5, int par6) {
/* 44 */     if (this.doBlockNotify) {
/*    */       
/* 46 */       par1World.setBlock(par2, par3, par4, par5, par6, 3);
/*    */     }
/*    */     else {
/*    */       
/* 50 */       par1World.setBlock(par2, par3, par4, par5, par6, 2);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */