/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WorldGenReed
/*    */   extends WorldGenerator
/*    */ {
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  9 */     for (int var6 = 0; var6 < 20; var6++) {
/*    */       
/* 11 */       int var7 = par3 + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 12 */       int var8 = par4;
/* 13 */       int var9 = par5 + par2Random.nextInt(4) - par2Random.nextInt(4);
/*    */ 
/*    */ 
/*    */       
/* 17 */       if (par1World.isAirBlock(var7, par4, var9) && (par1World.getBlockMaterial(var7 - 1, par4 - 1, var9) == Material.water || par1World.getBlockMaterial(var7 + 1, par4 - 1, var9) == Material.water || par1World.getBlockMaterial(var7, par4 - 1, var9 - 1) == Material.water || par1World.getBlockMaterial(var7, par4 - 1, var9 + 1) == Material.water)) {
/*    */         
/* 19 */         int var10 = 2 + par2Random.nextInt(par2Random.nextInt(3) + 1);
/*    */         
/* 21 */         for (int var11 = 0; var11 < var10; var11++) {
/*    */ 
/*    */ 
/*    */           
/* 25 */           if (Block.reed.canOccurAt(par1World, var7, var8 + var11, var9, 0))
/*    */           {
/* 27 */             par1World.setBlock(var7, var8 + var11, var9, Block.reed.blockID, 0, 2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 33 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenReed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */