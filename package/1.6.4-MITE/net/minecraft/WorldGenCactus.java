/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WorldGenCactus
/*    */   extends WorldGenerator
/*    */ {
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  9 */     for (int var6 = 0; var6 < 10; var6++) {
/*    */       
/* 11 */       int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
/* 12 */       int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 13 */       int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
/*    */       
/* 15 */       if (par1World.isAirBlock(var7, var8, var9)) {
/*    */ 
/*    */         
/* 18 */         int var10 = BlockCactus.getMaxHeight(var7, var8 - 1, var9);
/*    */         
/* 20 */         for (int var11 = 0; var11 < var10; var11++) {
/*    */ 
/*    */ 
/*    */           
/* 24 */           if (Block.cactus.canOccurAt(par1World, var7, var8 + var11, var9, 0))
/*    */           {
/* 26 */             par1World.setBlock(var7, var8 + var11, var9, Block.cactus.blockID, 0, 2);
/*    */           }
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenCactus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */