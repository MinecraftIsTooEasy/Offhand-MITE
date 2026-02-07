/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class WorldGenTaiga1
/*     */   extends WorldGenerator
/*     */ {
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*   9 */     int var6 = par2Random.nextInt(5) + 7;
/*  10 */     int var7 = var6 - par2Random.nextInt(2) - 3;
/*  11 */     int var8 = var6 - var7;
/*  12 */     int var9 = 1 + par2Random.nextInt(var8 + 1);
/*  13 */     boolean var10 = true;
/*     */     
/*  15 */     if (par4 >= 1 && par4 + var6 + 1 <= 128) {
/*     */       int var11;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  23 */       for (var11 = par4; var11 <= par4 + 1 + var6 && var10; var11++) {
/*     */         int var18;
/*  25 */         boolean var12 = true;
/*     */         
/*  27 */         if (var11 - par4 < var7) {
/*     */           
/*  29 */           var18 = 0;
/*     */         }
/*     */         else {
/*     */           
/*  33 */           var18 = var9;
/*     */         } 
/*     */         
/*  36 */         for (int var13 = par3 - var18; var13 <= par3 + var18 && var10; var13++) {
/*     */           
/*  38 */           for (int var14 = par5 - var18; var14 <= par5 + var18 && var10; var14++) {
/*     */             
/*  40 */             if (var11 >= 0 && var11 < 128) {
/*     */               
/*  42 */               int var15 = par1World.getBlockId(var13, var11, var14);
/*     */               
/*  44 */               if (var15 != 0 && var15 != Block.leaves.blockID)
/*     */               {
/*  46 */                 var10 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  51 */               var10 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  57 */       if (!var10)
/*     */       {
/*  59 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  63 */       var11 = par1World.getBlockId(par3, par4 - 1, par5);
/*     */       
/*  65 */       if ((var11 == Block.grass.blockID || var11 == Block.dirt.blockID) && par4 < 128 - var6 - 1) {
/*     */         
/*  67 */         setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
/*  68 */         int var18 = 0;
/*     */         int var13;
/*  70 */         for (var13 = par4 + var6; var13 >= par4 + var7; var13--) {
/*     */           
/*  72 */           for (int var14 = par3 - var18; var14 <= par3 + var18; var14++) {
/*     */             
/*  74 */             int var15 = var14 - par3;
/*     */             
/*  76 */             for (int var16 = par5 - var18; var16 <= par5 + var18; var16++) {
/*     */               
/*  78 */               int var17 = var16 - par5;
/*     */               
/*  80 */               if ((Math.abs(var15) != var18 || Math.abs(var17) != var18 || var18 <= 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var14, var13, var16)])
/*     */               {
/*  82 */                 setBlockAndMetadata(par1World, var14, var13, var16, Block.leaves.blockID, 1);
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/*  87 */           if (var18 >= 1 && var13 == par4 + var7 + 1) {
/*     */             
/*  89 */             var18--;
/*     */           }
/*  91 */           else if (var18 < var9) {
/*     */             
/*  93 */             var18++;
/*     */           } 
/*     */         } 
/*     */         
/*  97 */         for (var13 = 0; var13 < var6 - 1; var13++) {
/*     */           
/*  99 */           int var14 = par1World.getBlockId(par3, par4 + var13, par5);
/*     */           
/* 101 */           if (var14 == 0 || var14 == Block.leaves.blockID)
/*     */           {
/* 103 */             setBlockAndMetadata(par1World, par3, par4 + var13, par5, Block.wood.blockID, 1);
/*     */           }
/*     */         } 
/*     */         
/* 107 */         if (par1World.decorating) {
/* 108 */           par1World.placeNaturallyOccurringSnow(par3 - var18, par5 - var18, par3 + var18, par5 + var18);
/*     */         }
/* 110 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 114 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 120 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenTaiga1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */