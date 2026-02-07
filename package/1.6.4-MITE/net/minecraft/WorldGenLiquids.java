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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenLiquids
/*    */ {
/*    */   public static boolean generate(World par1World, Random par2Random, int liquid_block_id, int par3, int par4, int par5) {
/* 19 */     if (par1World.getBlockId(par3, par4 + 1, par5) != Block.stone.blockID)
/*    */     {
/* 21 */       return false;
/*    */     }
/* 23 */     if (par1World.getBlockId(par3, par4 - 1, par5) != Block.stone.blockID)
/*    */     {
/* 25 */       return false;
/*    */     }
/* 27 */     if (par1World.getBlockId(par3, par4, par5) != 0 && par1World.getBlockId(par3, par4, par5) != Block.stone.blockID)
/*    */     {
/* 29 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 33 */     int var6 = 0;
/*    */     
/* 35 */     if (par1World.getBlockId(par3 - 1, par4, par5) == Block.stone.blockID)
/*    */     {
/* 37 */       var6++;
/*    */     }
/*    */     
/* 40 */     if (par1World.getBlockId(par3 + 1, par4, par5) == Block.stone.blockID)
/*    */     {
/* 42 */       var6++;
/*    */     }
/*    */     
/* 45 */     if (par1World.getBlockId(par3, par4, par5 - 1) == Block.stone.blockID)
/*    */     {
/* 47 */       var6++;
/*    */     }
/*    */     
/* 50 */     if (par1World.getBlockId(par3, par4, par5 + 1) == Block.stone.blockID)
/*    */     {
/* 52 */       var6++;
/*    */     }
/*    */     
/* 55 */     int var7 = 0;
/*    */     
/* 57 */     if (par1World.isAirBlock(par3 - 1, par4, par5))
/*    */     {
/* 59 */       var7++;
/*    */     }
/*    */     
/* 62 */     if (par1World.isAirBlock(par3 + 1, par4, par5))
/*    */     {
/* 64 */       var7++;
/*    */     }
/*    */     
/* 67 */     if (par1World.isAirBlock(par3, par4, par5 - 1))
/*    */     {
/* 69 */       var7++;
/*    */     }
/*    */     
/* 72 */     if (par1World.isAirBlock(par3, par4, par5 + 1))
/*    */     {
/* 74 */       var7++;
/*    */     }
/*    */     
/* 77 */     if (var6 == 3 && var7 == 1) {
/*    */ 
/*    */       
/* 80 */       par1World.setBlock(par3, par4, par5, liquid_block_id, 0, 2);
/*    */ 
/*    */       
/* 83 */       Block.blocksList[liquid_block_id].updateTick(par1World, par3, par4, par5, par2Random);
/*    */     } 
/*    */ 
/*    */     
/* 87 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenLiquids.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */