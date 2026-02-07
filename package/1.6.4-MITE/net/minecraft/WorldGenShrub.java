/*    */ package net.minecraft;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WorldGenShrub
/*    */   extends WorldGenerator
/*    */ {
/*    */   private int field_76527_a;
/*    */   private int field_76526_b;
/*    */   
/*    */   public WorldGenShrub(int par1, int par2) {
/* 12 */     this.field_76526_b = par1;
/* 13 */     this.field_76527_a = par2;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*    */     int var15;
/* 20 */     for (boolean var6 = false; ((var15 = par1World.getBlockId(par3, par4, par5)) == 0 || var15 == Block.leaves.blockID) && par4 > 0; par4--);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 25 */     int var7 = par1World.getBlockId(par3, par4, par5);
/*    */     
/* 27 */     if (var7 == Block.dirt.blockID || var7 == Block.grass.blockID) {
/*    */       
/* 29 */       par4++;
/* 30 */       setBlockAndMetadata(par1World, par3, par4, par5, Block.wood.blockID, this.field_76526_b);
/*    */       
/* 32 */       for (int var8 = par4; var8 <= par4 + 2; var8++) {
/*    */         
/* 34 */         int var9 = var8 - par4;
/* 35 */         int var10 = 2 - var9;
/*    */         
/* 37 */         for (int var11 = par3 - var10; var11 <= par3 + var10; var11++) {
/*    */           
/* 39 */           int var12 = var11 - par3;
/*    */           
/* 41 */           for (int var13 = par5 - var10; var13 <= par5 + var10; var13++) {
/*    */             
/* 43 */             int var14 = var13 - par5;
/*    */             
/* 45 */             if ((Math.abs(var12) != var10 || Math.abs(var14) != var10 || par2Random.nextInt(2) != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var11, var8, var13)])
/*    */             {
/* 47 */               setBlockAndMetadata(par1World, var11, var8, var13, Block.leaves.blockID, this.field_76527_a);
/*    */             }
/*    */           } 
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenShrub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */