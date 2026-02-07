/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenTallGrass
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int tallGrassID;
/*    */   private int tallGrassMetadata;
/*    */   private Block block;
/*    */   
/*    */   public WorldGenTallGrass(int par1, int par2) {
/* 15 */     this.tallGrassID = par1;
/* 16 */     this.tallGrassMetadata = par2;
/*    */     
/* 18 */     this.block = Block.getBlock(this.tallGrassID);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*    */     int var11;
/* 25 */     for (boolean var6 = false; ((var11 = par1World.getBlockId(par3, par4, par5)) == 0 || var11 == Block.leaves.blockID) && par4 > 0; par4--);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 30 */     for (int var7 = 0; var7 < 128; var7++) {
/*    */       
/* 32 */       int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
/* 33 */       int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 34 */       int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
/*    */ 
/*    */ 
/*    */       
/* 38 */       if (par1World.isAirBlock(var8, var9, var10) && this.block.canOccurAt(par1World, var8, var9, var10, this.tallGrassMetadata))
/*    */       {
/* 40 */         par1World.setBlock(var8, var9, var10, this.tallGrassID, this.tallGrassMetadata, 2);
/*    */       }
/*    */     } 
/*    */     
/* 44 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenTallGrass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */