/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenSand
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int sandID;
/*    */   private int radius;
/*    */   
/*    */   public WorldGenSand(int par1, int par2) {
/* 15 */     this.sandID = par2;
/* 16 */     this.radius = par1;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/* 21 */     if (par1World.getBlockMaterial(par3, par4, par5) != Material.water)
/*    */     {
/* 23 */       return false;
/*    */     }
/*    */ 
/*    */     
/* 27 */     int var6 = par2Random.nextInt(this.radius - 2) + 2;
/* 28 */     byte var7 = 2;
/*    */     
/* 30 */     for (int var8 = par3 - var6; var8 <= par3 + var6; var8++) {
/*    */       
/* 32 */       for (int var9 = par5 - var6; var9 <= par5 + var6; var9++) {
/*    */         
/* 34 */         int var10 = var8 - par3;
/* 35 */         int var11 = var9 - par5;
/*    */         
/* 37 */         if (var10 * var10 + var11 * var11 <= var6 * var6)
/*    */         {
/* 39 */           for (int var12 = par4 - var7; var12 <= par4 + var7; var12++) {
/*    */             
/* 41 */             int var13 = par1World.getBlockId(var8, var12, var9);
/*    */             
/* 43 */             if (var13 == Block.dirt.blockID || var13 == Block.grass.blockID) {
/*    */               
/* 45 */               par1World.setBlock(var8, var12, var9, this.sandID, 0, 2);
/*    */               
/* 47 */               Block block_above = par1World.getBlock(var8, var12 + 1, var9);
/*    */               
/* 49 */               if (block_above != null) {
/*    */                 
/* 51 */                 int x = var8;
/* 52 */                 int y = var12;
/* 53 */                 int z = var9;
/*    */                 
/* 55 */                 int block_above_metadata = par1World.getBlockMetadata(x, y + 1, z);
/*    */                 
/* 57 */                 Block block = par1World.getBlock(x, y, z);
/* 58 */                 int metadata = par1World.getBlockMetadata(x, y, z);
/*    */                 
/* 60 */                 if (!block_above.isLegalOn(block_above_metadata, block, metadata)) {
/* 61 */                   par1World.setBlockToAir(x, y + 1, z, 2);
/*    */                 }
/*    */               } 
/*    */             } 
/*    */           } 
/*    */         }
/*    */       } 
/*    */     } 
/* 69 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenSand.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */