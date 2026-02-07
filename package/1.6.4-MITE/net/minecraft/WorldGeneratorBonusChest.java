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
/*    */ public class WorldGeneratorBonusChest
/*    */   extends WorldGenerator
/*    */ {
/*    */   private final WeightedRandomChestContent[] theBonusChestGenerator;
/*    */   private final int itemsToGenerateInBonusChest;
/*    */   
/*    */   public WorldGeneratorBonusChest(WeightedRandomChestContent[] par1ArrayOfWeightedRandomChestContent, int par2) {
/* 19 */     this.theBonusChestGenerator = par1ArrayOfWeightedRandomChestContent;
/* 20 */     this.itemsToGenerateInBonusChest = par2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*    */     int var12;
/* 27 */     for (boolean var6 = false; ((var12 = par1World.getBlockId(par3, par4, par5)) == 0 || var12 == Block.leaves.blockID) && par4 > 1; par4--);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 32 */     if (par4 < 1)
/*    */     {
/* 34 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 38 */     par4++;
/*    */     
/* 40 */     for (int var7 = 0; var7 < 4; var7++) {
/*    */       
/* 42 */       int var8 = par3 + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 43 */       int var9 = par4 + par2Random.nextInt(3) - par2Random.nextInt(3);
/* 44 */       int var10 = par5 + par2Random.nextInt(4) - par2Random.nextInt(4);
/*    */       
/* 46 */       if (par1World.isAirBlock(var8, var9, var10) && par1World.isBlockTopFlatAndSolid(var8, var9 - 1, var10)) {
/*    */         
/* 48 */         par1World.setBlock(var8, var9, var10, Block.chest.blockID, 0, 2);
/* 49 */         TileEntityChest var11 = (TileEntityChest)par1World.getBlockTileEntity(var8, var9, var10);
/*    */         
/* 51 */         if (var11 != null && var11 != null)
/*    */         {
/*    */           
/* 54 */           WeightedRandomChestContent.generateChestContents(par1World, var9, par2Random, this.theBonusChestGenerator, var11, this.itemsToGenerateInBonusChest, (float[])null);
/*    */         }
/*    */         
/* 57 */         if (par1World.isAirBlock(var8 - 1, var9, var10) && par1World.isBlockTopFlatAndSolid(var8 - 1, var9 - 1, var10))
/*    */         {
/* 59 */           par1World.setBlock(var8 - 1, var9, var10, Block.torchWood.blockID, 0, 2);
/*    */         }
/*    */         
/* 62 */         if (par1World.isAirBlock(var8 + 1, var9, var10) && par1World.isBlockTopFlatAndSolid(var8 - 1, var9 - 1, var10))
/*    */         {
/* 64 */           par1World.setBlock(var8 + 1, var9, var10, Block.torchWood.blockID, 0, 2);
/*    */         }
/*    */         
/* 67 */         if (par1World.isAirBlock(var8, var9, var10 - 1) && par1World.isBlockTopFlatAndSolid(var8 - 1, var9 - 1, var10))
/*    */         {
/* 69 */           par1World.setBlock(var8, var9, var10 - 1, Block.torchWood.blockID, 0, 2);
/*    */         }
/*    */         
/* 72 */         if (par1World.isAirBlock(var8, var9, var10 + 1) && par1World.isBlockTopFlatAndSolid(var8 - 1, var9 - 1, var10))
/*    */         {
/* 74 */           par1World.setBlock(var8, var9, var10 + 1, Block.torchWood.blockID, 0, 2);
/*    */         }
/*    */         
/* 77 */         return true;
/*    */       } 
/*    */     } 
/*    */     
/* 81 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGeneratorBonusChest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */