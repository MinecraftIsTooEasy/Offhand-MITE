/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class WorldGenTaiga2
/*     */   extends WorldGenerator
/*     */ {
/*     */   public WorldGenTaiga2(boolean par1) {
/*   9 */     super(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  14 */     int var6 = par2Random.nextInt(4) + 6;
/*  15 */     int var7 = 1 + par2Random.nextInt(2);
/*  16 */     int var8 = var6 - var7;
/*  17 */     int var9 = 2 + par2Random.nextInt(2);
/*  18 */     boolean var10 = true;
/*     */     
/*  20 */     if (par4 >= 1 && par4 + var6 + 1 <= 256) {
/*     */       int var11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  27 */       for (var11 = par4; var11 <= par4 + 1 + var6 && var10; var11++) {
/*     */         int var21;
/*  29 */         boolean var12 = true;
/*     */         
/*  31 */         if (var11 - par4 < var7) {
/*     */           
/*  33 */           var21 = 0;
/*     */         }
/*     */         else {
/*     */           
/*  37 */           var21 = var9;
/*     */         } 
/*     */         
/*  40 */         for (int var13 = par3 - var21; var13 <= par3 + var21 && var10; var13++) {
/*     */           
/*  42 */           for (int var14 = par5 - var21; var14 <= par5 + var21 && var10; var14++) {
/*     */             
/*  44 */             if (var11 >= 0 && var11 < 256) {
/*     */               
/*  46 */               int var15 = par1World.getBlockId(var13, var11, var14);
/*     */               
/*  48 */               if (var15 != 0 && var15 != Block.leaves.blockID)
/*     */               {
/*  50 */                 var10 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  55 */               var10 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  61 */       if (!var10)
/*     */       {
/*  63 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  67 */       int radius = 0;
/*     */       
/*  69 */       var11 = par1World.getBlockId(par3, par4 - 1, par5);
/*     */       
/*  71 */       if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID) && par4 < 256 - var6 - 1) {
/*     */         
/*  73 */         setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
/*  74 */         int var21 = par2Random.nextInt(2);
/*  75 */         int var13 = 1;
/*  76 */         byte var22 = 0;
/*     */         
/*     */         int var15;
/*     */         
/*  80 */         for (var15 = 0; var15 <= var8; var15++) {
/*     */           
/*  82 */           int i = par4 + var6 - var15;
/*     */           
/*  84 */           for (int var17 = par3 - var21; var17 <= par3 + var21; var17++) {
/*     */             
/*  86 */             int var18 = var17 - par3;
/*     */             
/*  88 */             for (int var19 = par5 - var21; var19 <= par5 + var21; var19++) {
/*     */               
/*  90 */               int var20 = var19 - par5;
/*     */               
/*  92 */               if ((Math.abs(var18) != var21 || Math.abs(var20) != var21 || var21 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var17, i, var19)]) {
/*     */                 
/*  94 */                 setBlockAndMetadata(par1World, var17, i, var19, Block.leaves.blockID, 1);
/*     */                 
/*  96 */                 if (var21 > radius) {
/*  97 */                   radius = var21;
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/* 102 */           if (var21 >= var13) {
/*     */             
/* 104 */             var21 = var22;
/* 105 */             var22 = 1;
/* 106 */             var13++;
/*     */             
/* 108 */             if (var13 > var9)
/*     */             {
/* 110 */               var13 = var9;
/*     */             }
/*     */           }
/*     */           else {
/*     */             
/* 115 */             var21++;
/*     */           } 
/*     */         } 
/*     */         
/* 119 */         var15 = par2Random.nextInt(3);
/*     */         
/* 121 */         for (int var16 = 0; var16 < var6 - var15; var16++) {
/*     */           
/* 123 */           int var17 = par1World.getBlockId(par3, par4 + var16, par5);
/*     */           
/* 125 */           if (var17 == 0 || var17 == Block.leaves.blockID)
/*     */           {
/* 127 */             setBlockAndMetadata(par1World, par3, par4 + var16, par5, Block.wood.blockID, 1);
/*     */           }
/*     */         } 
/*     */         
/* 131 */         if (par1World.decorating) {
/* 132 */           par1World.placeNaturallyOccurringSnow(par3 - radius, par5 - radius, par3 + radius, par5 + radius);
/*     */         }
/* 134 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 138 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 144 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenTaiga2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */