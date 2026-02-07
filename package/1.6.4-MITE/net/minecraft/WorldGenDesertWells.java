/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WorldGenDesertWells
/*    */   extends WorldGenerator
/*    */ {
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  9 */     while (par1World.isAirBlock(par3, par4, par5) && par4 > 2)
/*    */     {
/* 11 */       par4--;
/*    */     }
/*    */     
/* 14 */     int var6 = par1World.getBlockId(par3, par4, par5);
/*    */     
/* 16 */     if (var6 != Block.sand.blockID)
/*    */     {
/* 18 */       return false;
/*    */     }
/*    */ 
/*    */     
/*    */     int var7;
/*    */ 
/*    */     
/* 25 */     for (var7 = -2; var7 <= 2; var7++) {
/*    */       
/* 27 */       for (int var8 = -2; var8 <= 2; var8++) {
/*    */         
/* 29 */         if (par1World.isAirBlock(par3 + var7, par4 - 1, par5 + var8) && par1World.isAirBlock(par3 + var7, par4 - 2, par5 + var8))
/*    */         {
/* 31 */           return false;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 36 */     for (var7 = -1; var7 <= 0; var7++) {
/*    */       
/* 38 */       for (int var8 = -2; var8 <= 2; var8++) {
/*    */         
/* 40 */         for (int var9 = -2; var9 <= 2; var9++)
/*    */         {
/* 42 */           par1World.setBlock(par3 + var8, par4 + var7, par5 + var9, Block.sandStone.blockID, 0, 2);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     par1World.setBlock(par3, par4, par5, Block.waterMoving.blockID, 0, 2);
/* 48 */     par1World.setBlock(par3 - 1, par4, par5, Block.waterMoving.blockID, 0, 2);
/* 49 */     par1World.setBlock(par3 + 1, par4, par5, Block.waterMoving.blockID, 0, 2);
/* 50 */     par1World.setBlock(par3, par4, par5 - 1, Block.waterMoving.blockID, 0, 2);
/* 51 */     par1World.setBlock(par3, par4, par5 + 1, Block.waterMoving.blockID, 0, 2);
/*    */     
/* 53 */     for (var7 = -2; var7 <= 2; var7++) {
/*    */       
/* 55 */       for (int var8 = -2; var8 <= 2; var8++) {
/*    */         
/* 57 */         if (var7 == -2 || var7 == 2 || var8 == -2 || var8 == 2)
/*    */         {
/* 59 */           par1World.setBlock(par3 + var7, par4 + 1, par5 + var8, Block.sandStone.blockID, 0, 2);
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 64 */     par1World.setBlock(par3 + 2, par4 + 1, par5, Block.stoneSingleSlab.blockID, 1, 2);
/* 65 */     par1World.setBlock(par3 - 2, par4 + 1, par5, Block.stoneSingleSlab.blockID, 1, 2);
/* 66 */     par1World.setBlock(par3, par4 + 1, par5 + 2, Block.stoneSingleSlab.blockID, 1, 2);
/* 67 */     par1World.setBlock(par3, par4 + 1, par5 - 2, Block.stoneSingleSlab.blockID, 1, 2);
/*    */     
/* 69 */     for (var7 = -1; var7 <= 1; var7++) {
/*    */       
/* 71 */       for (int var8 = -1; var8 <= 1; var8++) {
/*    */         
/* 73 */         if (var7 == 0 && var8 == 0) {
/*    */           
/* 75 */           par1World.setBlock(par3 + var7, par4 + 4, par5 + var8, Block.sandStone.blockID, 0, 2);
/*    */         }
/*    */         else {
/*    */           
/* 79 */           par1World.setBlock(par3 + var7, par4 + 4, par5 + var8, Block.stoneSingleSlab.blockID, 1, 2);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 84 */     for (var7 = 1; var7 <= 3; var7++) {
/*    */       
/* 86 */       par1World.setBlock(par3 - 1, par4 + var7, par5 - 1, Block.sandStone.blockID, 0, 2);
/* 87 */       par1World.setBlock(par3 - 1, par4 + var7, par5 + 1, Block.sandStone.blockID, 0, 2);
/* 88 */       par1World.setBlock(par3 + 1, par4 + var7, par5 - 1, Block.sandStone.blockID, 0, 2);
/* 89 */       par1World.setBlock(par3 + 1, par4 + var7, par5 + 1, Block.sandStone.blockID, 0, 2);
/*    */     } 
/*    */     
/* 92 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenDesertWells.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */