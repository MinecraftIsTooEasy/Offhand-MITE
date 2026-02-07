/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class WorldGenForest
/*     */   extends WorldGenerator
/*     */ {
/*     */   public WorldGenForest(boolean par1) {
/*   9 */     super(par1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  14 */     int var6 = par2Random.nextInt(3) + 5;
/*  15 */     boolean var7 = true;
/*     */     
/*  17 */     if (par4 >= 1 && par4 + var6 + 1 <= 256) {
/*     */       int var8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  24 */       for (var8 = par4; var8 <= par4 + 1 + var6; var8++) {
/*     */         
/*  26 */         byte var9 = 1;
/*     */         
/*  28 */         if (var8 == par4)
/*     */         {
/*  30 */           var9 = 0;
/*     */         }
/*     */         
/*  33 */         if (var8 >= par4 + 1 + var6 - 2)
/*     */         {
/*  35 */           var9 = 2;
/*     */         }
/*     */         
/*  38 */         for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; var10++) {
/*     */           
/*  40 */           for (int var11 = par5 - var9; var11 <= par5 + var9 && var7; var11++) {
/*     */             
/*  42 */             if (var8 >= 0 && var8 < 256) {
/*     */               
/*  44 */               int var12 = par1World.getBlockId(var10, var8, var11);
/*     */               
/*  46 */               if (var12 != 0 && var12 != Block.leaves.blockID)
/*     */               {
/*  48 */                 var7 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  53 */               var7 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  59 */       if (!var7)
/*     */       {
/*  61 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  65 */       var8 = par1World.getBlockId(par3, par4 - 1, par5);
/*     */       
/*  67 */       if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 256 - var6 - 1) {
/*     */         
/*  69 */         setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
/*     */         
/*     */         int var17;
/*  72 */         for (var17 = par4 - 3 + var6; var17 <= par4 + var6; var17++) {
/*     */           
/*  74 */           int var10 = var17 - par4 + var6;
/*  75 */           int var11 = 1 - var10 / 2;
/*     */           
/*  77 */           for (int var12 = par3 - var11; var12 <= par3 + var11; var12++) {
/*     */             
/*  79 */             int var13 = var12 - par3;
/*     */             
/*  81 */             for (int var14 = par5 - var11; var14 <= par5 + var11; var14++) {
/*     */               
/*  83 */               int var15 = var14 - par5;
/*     */               
/*  85 */               if (Math.abs(var13) != var11 || Math.abs(var15) != var11 || (par2Random.nextInt(2) != 0 && var10 != 0)) {
/*     */                 
/*  87 */                 int var16 = par1World.getBlockId(var12, var17, var14);
/*     */                 
/*  89 */                 if (var16 == 0 || var16 == Block.leaves.blockID)
/*     */                 {
/*  91 */                   setBlockAndMetadata(par1World, var12, var17, var14, Block.leaves.blockID, 2);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/*  98 */         for (var17 = 0; var17 < var6; var17++) {
/*     */           
/* 100 */           int var10 = par1World.getBlockId(par3, par4 + var17, par5);
/*     */           
/* 102 */           if (var10 == 0 || var10 == Block.leaves.blockID)
/*     */           {
/* 104 */             setBlockAndMetadata(par1World, par3, par4 + var17, par5, Block.wood.blockID, 2);
/*     */           }
/*     */         } 
/*     */         
/* 108 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 112 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenForest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */