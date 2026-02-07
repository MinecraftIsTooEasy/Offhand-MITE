/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public class WorldGenSwamp
/*     */   extends WorldGenerator
/*     */ {
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*     */     int var6;
/*  11 */     for (var6 = par2Random.nextInt(4) + 5; par1World.getBlockMaterial(par3, par4 - 1, par5) == Material.water; par4--);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  16 */     boolean var7 = true;
/*     */     
/*  18 */     if (par4 >= 1 && par4 + var6 + 1 <= 128) {
/*     */       int var8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  25 */       for (var8 = par4; var8 <= par4 + 1 + var6; var8++) {
/*     */         
/*  27 */         byte var9 = 1;
/*     */         
/*  29 */         if (var8 == par4)
/*     */         {
/*  31 */           var9 = 0;
/*     */         }
/*     */         
/*  34 */         if (var8 >= par4 + 1 + var6 - 2)
/*     */         {
/*  36 */           var9 = 3;
/*     */         }
/*     */         
/*  39 */         for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; var10++) {
/*     */           
/*  41 */           for (int var11 = par5 - var9; var11 <= par5 + var9 && var7; var11++) {
/*     */             
/*  43 */             if (var8 >= 0 && var8 < 128) {
/*     */               
/*  45 */               int var12 = par1World.getBlockId(var10, var8, var11);
/*     */               
/*  47 */               if (var12 != 0 && var12 != Block.leaves.blockID)
/*     */               {
/*  49 */                 if (var12 != Block.waterStill.blockID && var12 != Block.waterMoving.blockID)
/*     */                 {
/*  51 */                   var7 = false;
/*     */                 }
/*  53 */                 else if (var8 > par4)
/*     */                 {
/*  55 */                   var7 = false;
/*     */                 }
/*     */               
/*     */               }
/*     */             } else {
/*     */               
/*  61 */               var7 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  67 */       if (!var7)
/*     */       {
/*  69 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  73 */       var8 = par1World.getBlockId(par3, par4 - 1, par5);
/*     */       
/*  75 */       if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 128 - var6 - 1) {
/*     */         
/*  77 */         setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
/*     */         
/*     */         int var16;
/*     */         
/*  81 */         for (var16 = par4 - 3 + var6; var16 <= par4 + var6; var16++) {
/*     */           
/*  83 */           int var10 = var16 - par4 + var6;
/*  84 */           int var11 = 2 - var10 / 2;
/*     */           
/*  86 */           for (int var12 = par3 - var11; var12 <= par3 + var11; var12++) {
/*     */             
/*  88 */             int var13 = var12 - par3;
/*     */             
/*  90 */             for (int var14 = par5 - var11; var14 <= par5 + var11; var14++) {
/*     */               
/*  92 */               int var15 = var14 - par5;
/*     */               
/*  94 */               if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || (par2Random.nextInt(2) != 0 && var10 != 0)) && !Block.opaqueCubeLookup[par1World.getBlockId(var12, var16, var14)])
/*     */               {
/*  96 */                 setBlock(par1World, var12, var16, var14, Block.leaves.blockID);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 102 */         for (var16 = 0; var16 < var6; var16++) {
/*     */           
/* 104 */           int var10 = par1World.getBlockId(par3, par4 + var16, par5);
/*     */           
/* 106 */           if (var10 == 0 || var10 == Block.leaves.blockID || var10 == Block.waterMoving.blockID || var10 == Block.waterStill.blockID)
/*     */           {
/* 108 */             setBlock(par1World, par3, par4 + var16, par5, Block.wood.blockID);
/*     */           }
/*     */         } 
/*     */         
/* 112 */         for (var16 = par4 - 3 + var6; var16 <= par4 + var6; var16++) {
/*     */           
/* 114 */           int var10 = var16 - par4 + var6;
/* 115 */           int var11 = 2 - var10 / 2;
/*     */           
/* 117 */           for (int var12 = par3 - var11; var12 <= par3 + var11; var12++) {
/*     */             
/* 119 */             for (int var13 = par5 - var11; var13 <= par5 + var11; var13++) {
/*     */               
/* 121 */               if (par1World.getBlockId(var12, var16, var13) == Block.leaves.blockID) {
/*     */                 
/* 123 */                 if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var12 - 1, var16, var13) == 0)
/*     */                 {
/* 125 */                   generateVines(par1World, var12 - 1, var16, var13, 8);
/*     */                 }
/*     */                 
/* 128 */                 if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var12 + 1, var16, var13) == 0)
/*     */                 {
/* 130 */                   generateVines(par1World, var12 + 1, var16, var13, 2);
/*     */                 }
/*     */                 
/* 133 */                 if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var12, var16, var13 - 1) == 0)
/*     */                 {
/* 135 */                   generateVines(par1World, var12, var16, var13 - 1, 1);
/*     */                 }
/*     */                 
/* 138 */                 if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var12, var16, var13 + 1) == 0)
/*     */                 {
/* 140 */                   generateVines(par1World, var12, var16, var13 + 1, 4);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 147 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 151 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void generateVines(World par1World, int par2, int par3, int par4, int par5) {
/* 166 */     setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
/* 167 */     int var6 = 4;
/*     */ 
/*     */     
/*     */     while (true) {
/* 171 */       par3--;
/*     */       
/* 173 */       if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 178 */       setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
/* 179 */       var6--;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenSwamp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */