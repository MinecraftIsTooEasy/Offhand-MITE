/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenHugeTrees
/*     */   extends WorldGenerator
/*     */ {
/*     */   private final int baseHeight;
/*     */   private final int woodMetadata;
/*     */   private final int leavesMetadata;
/*     */   
/*     */   public WorldGenHugeTrees(boolean par1, int par2, int par3, int par4) {
/*  18 */     super(par1);
/*  19 */     this.baseHeight = par2;
/*  20 */     this.woodMetadata = par3;
/*  21 */     this.leavesMetadata = par4;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  26 */     int var6 = par2Random.nextInt(3) + this.baseHeight;
/*  27 */     boolean var7 = true;
/*     */     
/*  29 */     if (par4 >= 1 && par4 + var6 + 1 <= 256) {
/*     */       int var8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  36 */       for (var8 = par4; var8 <= par4 + 1 + var6; var8++) {
/*     */         
/*  38 */         byte var9 = 2;
/*     */         
/*  40 */         if (var8 == par4)
/*     */         {
/*  42 */           var9 = 1;
/*     */         }
/*     */         
/*  45 */         if (var8 >= par4 + 1 + var6 - 2)
/*     */         {
/*  47 */           var9 = 2;
/*     */         }
/*     */         
/*  50 */         for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; var10++) {
/*     */           
/*  52 */           for (int var11 = par5 - var9; var11 <= par5 + var9 && var7; var11++) {
/*     */             
/*  54 */             if (var8 >= 0 && var8 < 256) {
/*     */               
/*  56 */               int var12 = par1World.getBlockId(var10, var8, var11);
/*     */               
/*  58 */               if (var12 != 0 && var12 != Block.leaves.blockID && var12 != Block.grass.blockID && var12 != Block.dirt.blockID && var12 != Block.wood.blockID && var12 != Block.sapling.blockID)
/*     */               {
/*  60 */                 var7 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  65 */               var7 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  71 */       if (!var7)
/*     */       {
/*  73 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  77 */       var8 = par1World.getBlockId(par3, par4 - 1, par5);
/*     */       
/*  79 */       if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 256 - var6 - 1) {
/*     */         
/*  81 */         par1World.setBlock(par3, par4 - 1, par5, Block.dirt.blockID, 0, 2);
/*  82 */         par1World.setBlock(par3 + 1, par4 - 1, par5, Block.dirt.blockID, 0, 2);
/*  83 */         par1World.setBlock(par3, par4 - 1, par5 + 1, Block.dirt.blockID, 0, 2);
/*  84 */         par1World.setBlock(par3 + 1, par4 - 1, par5 + 1, Block.dirt.blockID, 0, 2);
/*  85 */         growLeaves(par1World, par3, par5, par4 + var6, 2, par2Random);
/*     */         int var14;
/*  87 */         for (var14 = par4 + var6 - 2 - par2Random.nextInt(4); var14 > par4 + var6 / 2; var14 -= 2 + par2Random.nextInt(4)) {
/*     */           
/*  89 */           float var15 = par2Random.nextFloat() * 3.1415927F * 2.0F;
/*  90 */           int var11 = par3 + (int)(0.5F + MathHelper.cos(var15) * 4.0F);
/*  91 */           int var12 = par5 + (int)(0.5F + MathHelper.sin(var15) * 4.0F);
/*  92 */           growLeaves(par1World, var11, var12, var14, 0, par2Random);
/*     */           
/*  94 */           for (int var13 = 0; var13 < 5; var13++) {
/*     */             
/*  96 */             var11 = par3 + (int)(1.5F + MathHelper.cos(var15) * var13);
/*  97 */             var12 = par5 + (int)(1.5F + MathHelper.sin(var15) * var13);
/*  98 */             setBlockAndMetadata(par1World, var11, var14 - 3 + var13 / 2, var12, Block.wood.blockID, this.woodMetadata);
/*     */           } 
/*     */         } 
/*     */         
/* 102 */         for (int var10 = 0; var10 < var6; var10++) {
/*     */           
/* 104 */           int var11 = par1World.getBlockId(par3, par4 + var10, par5);
/*     */           
/* 106 */           if (var11 == 0 || var11 == Block.leaves.blockID) {
/*     */             
/* 108 */             setBlockAndMetadata(par1World, par3, par4 + var10, par5, Block.wood.blockID, this.woodMetadata);
/*     */             
/* 110 */             if (var10 > 0) {
/*     */               
/* 112 */               if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var10, par5))
/*     */               {
/* 114 */                 setBlockAndMetadata(par1World, par3 - 1, par4 + var10, par5, Block.vine.blockID, 8);
/*     */               }
/*     */               
/* 117 */               if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var10, par5 - 1))
/*     */               {
/* 119 */                 setBlockAndMetadata(par1World, par3, par4 + var10, par5 - 1, Block.vine.blockID, 1);
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 124 */           if (var10 < var6 - 1) {
/*     */             
/* 126 */             var11 = par1World.getBlockId(par3 + 1, par4 + var10, par5);
/*     */             
/* 128 */             if (var11 == 0 || var11 == Block.leaves.blockID) {
/*     */               
/* 130 */               setBlockAndMetadata(par1World, par3 + 1, par4 + var10, par5, Block.wood.blockID, this.woodMetadata);
/*     */               
/* 132 */               if (var10 > 0) {
/*     */                 
/* 134 */                 if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 2, par4 + var10, par5))
/*     */                 {
/* 136 */                   setBlockAndMetadata(par1World, par3 + 2, par4 + var10, par5, Block.vine.blockID, 2);
/*     */                 }
/*     */                 
/* 139 */                 if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var10, par5 - 1))
/*     */                 {
/* 141 */                   setBlockAndMetadata(par1World, par3 + 1, par4 + var10, par5 - 1, Block.vine.blockID, 1);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 146 */             var11 = par1World.getBlockId(par3 + 1, par4 + var10, par5 + 1);
/*     */             
/* 148 */             if (var11 == 0 || var11 == Block.leaves.blockID) {
/*     */               
/* 150 */               setBlockAndMetadata(par1World, par3 + 1, par4 + var10, par5 + 1, Block.wood.blockID, this.woodMetadata);
/*     */               
/* 152 */               if (var10 > 0) {
/*     */                 
/* 154 */                 if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 2, par4 + var10, par5 + 1))
/*     */                 {
/* 156 */                   setBlockAndMetadata(par1World, par3 + 2, par4 + var10, par5 + 1, Block.vine.blockID, 2);
/*     */                 }
/*     */                 
/* 159 */                 if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var10, par5 + 2))
/*     */                 {
/* 161 */                   setBlockAndMetadata(par1World, par3 + 1, par4 + var10, par5 + 2, Block.vine.blockID, 4);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 166 */             var11 = par1World.getBlockId(par3, par4 + var10, par5 + 1);
/*     */             
/* 168 */             if (var11 == 0 || var11 == Block.leaves.blockID) {
/*     */               
/* 170 */               setBlockAndMetadata(par1World, par3, par4 + var10, par5 + 1, Block.wood.blockID, this.woodMetadata);
/*     */               
/* 172 */               if (var10 > 0) {
/*     */                 
/* 174 */                 if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var10, par5 + 1))
/*     */                 {
/* 176 */                   setBlockAndMetadata(par1World, par3 - 1, par4 + var10, par5 + 1, Block.vine.blockID, 8);
/*     */                 }
/*     */                 
/* 179 */                 if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var10, par5 + 2))
/*     */                 {
/* 181 */                   setBlockAndMetadata(par1World, par3, par4 + var10, par5 + 2, Block.vine.blockID, 4);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 188 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 192 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void growLeaves(World par1World, int par2, int par3, int par4, int par5, Random par6Random) {
/* 204 */     byte var7 = 2;
/*     */     
/* 206 */     for (int var8 = par4 - var7; var8 <= par4; var8++) {
/*     */       
/* 208 */       int var9 = var8 - par4;
/* 209 */       int var10 = par5 + 1 - var9;
/*     */ 
/*     */       
/* 212 */       for (int var11 = par2 - var10; var11 <= par2 + var10; var11++) {
/*     */         
/* 214 */         int var12 = var11 - par2;
/*     */ 
/*     */         
/* 217 */         for (int var13 = par3 - var10; var13 <= par3 + var10; var13++) {
/*     */           
/* 219 */           int var14 = var13 - par3;
/*     */           
/* 221 */           if ((var12 >= 0 || var14 >= 0 || var12 * var12 + var14 * var14 <= var10 * var10) && ((var12 <= 0 && var14 <= 0) || var12 * var12 + var14 * var14 <= (var10 + 1) * (var10 + 1)) && (par6Random.nextInt(4) != 0 || var12 * var12 + var14 * var14 <= (var10 - 1) * (var10 - 1))) {
/*     */             
/* 223 */             int var15 = par1World.getBlockId(var11, var8, var13);
/*     */             
/* 225 */             if (var15 == 0 || var15 == Block.leaves.blockID)
/*     */             {
/* 227 */               setBlockAndMetadata(par1World, var11, var8, var13, Block.leaves.blockID, this.leavesMetadata);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenHugeTrees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */