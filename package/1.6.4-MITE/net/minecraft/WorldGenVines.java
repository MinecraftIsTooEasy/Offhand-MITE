/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WorldGenVines
/*    */   extends WorldGenerator
/*    */ {
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  9 */     int var6 = par3;
/*    */     
/* 11 */     for (int var7 = par5; par4 < 128; par4++) {
/*    */       
/* 13 */       if (par1World.isAirBlock(par3, par4, par5)) {
/*    */         
/* 15 */         for (int var8 = 2; var8 <= 5; var8++) {
/*    */ 
/*    */           
/* 18 */           if (Block.vine.canPlaceBlockOnSide(par1World, par3, par4, par5, EnumFace.get(var8))) {
/*    */             
/* 20 */             par1World.setBlock(par3, par4, par5, Block.vine.blockID, 1 << Direction.facingToDirection[Facing.oppositeSide[var8]], 2);
/*    */ 
/*    */             
/*    */             break;
/*    */           } 
/*    */         } 
/*    */       } else {
/* 27 */         par3 = var6 + par2Random.nextInt(4) - par2Random.nextInt(4);
/* 28 */         par5 = var7 + par2Random.nextInt(4) - par2Random.nextInt(4);
/*    */       } 
/*    */     } 
/*    */     
/* 32 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenVines.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */