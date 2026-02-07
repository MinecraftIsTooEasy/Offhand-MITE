/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BiomeGenHills
/*    */   extends BiomeGenBase
/*    */ {
/*    */   protected BiomeGenHills(int par1) {
/* 11 */     super(par1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void decorate(World par1World, Random par2Random, int par3, int par4) {
/* 19 */     super.decorate(par1World, par2Random, par3, par4);
/* 20 */     int var5 = 3 + par2Random.nextInt(6);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     for (int var6 = 0; var6 < var5; var6++) {
/*    */       
/* 27 */       int var7 = par3 + par2Random.nextInt(16);
/* 28 */       int var8 = par2Random.nextInt(28) + 4;
/* 29 */       int var9 = par4 + par2Random.nextInt(16);
/* 30 */       int var10 = par1World.getBlockId(var7, var8, var9);
/*    */       
/* 32 */       if (var10 == Block.stone.blockID)
/*    */       {
/* 34 */         par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenHills.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */