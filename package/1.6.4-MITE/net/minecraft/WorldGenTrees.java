/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldGenTrees
/*     */   extends WorldGenerator
/*     */ {
/*     */   private final int minTreeHeight;
/*     */   private final boolean vinesGrow;
/*     */   private final int metaWood;
/*     */   private final int metaLeaves;
/*     */   
/*     */   public WorldGenTrees(boolean par1) {
/*  21 */     this(par1, 4, 0, 0, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenTrees(boolean par1, int par2, int par3, int par4, boolean par5) {
/*  26 */     super(par1);
/*  27 */     this.minTreeHeight = par2;
/*  28 */     this.metaWood = par3;
/*  29 */     this.metaLeaves = par4;
/*  30 */     this.vinesGrow = par5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  37 */     int var6 = par2Random.nextInt(3) + this.minTreeHeight;
/*  38 */     boolean var7 = true;
/*     */     
/*  40 */     if (par4 >= 1 && par4 + var6 + 1 <= 256) {
/*     */       int var8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  47 */       for (var8 = par4; var8 <= par4 + 1 + var6; var8++) {
/*     */         
/*  49 */         byte var9 = 1;
/*     */         
/*  51 */         if (var8 == par4)
/*     */         {
/*  53 */           var9 = 0;
/*     */         }
/*     */         
/*  56 */         if (var8 >= par4 + 1 + var6 - 2)
/*     */         {
/*  58 */           var9 = 2;
/*     */         }
/*     */         
/*  61 */         for (int var10 = par3 - var9; var10 <= par3 + var9 && var7; var10++) {
/*     */           
/*  63 */           for (int var11 = par5 - var9; var11 <= par5 + var9 && var7; var11++) {
/*     */             
/*  65 */             if (var8 >= 0 && var8 < 256) {
/*     */               
/*  67 */               int var12 = par1World.getBlockId(var10, var8, var11);
/*     */               
/*  69 */               if (var12 != 0 && var12 != Block.leaves.blockID && var12 != Block.grass.blockID && var12 != Block.dirt.blockID && var12 != Block.wood.blockID)
/*     */               {
/*  71 */                 var7 = false;
/*     */               }
/*     */             }
/*     */             else {
/*     */               
/*  76 */               var7 = false;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/*  82 */       if (!var7)
/*     */       {
/*  84 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  88 */       int radius = 0;
/*     */       
/*  90 */       var8 = par1World.getBlockId(par3, par4 - 1, par5);
/*     */       
/*  92 */       if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 256 - var6 - 1) {
/*     */         
/*  94 */         setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
/*  95 */         byte var9 = 3;
/*  96 */         byte var19 = 0;
/*     */ 
/*     */         
/*     */         int var11;
/*     */         
/* 101 */         for (var11 = par4 - var9 + var6; var11 <= par4 + var6; var11++) {
/*     */           
/* 103 */           int var12 = var11 - par4 + var6;
/* 104 */           int var13 = var19 + 1 - var12 / 2;
/*     */           
/* 106 */           if (var13 > radius) {
/* 107 */             radius = var13;
/*     */           }
/* 109 */           for (int var14 = par3 - var13; var14 <= par3 + var13; var14++) {
/*     */             
/* 111 */             int var15 = var14 - par3;
/*     */             
/* 113 */             for (int var16 = par5 - var13; var16 <= par5 + var13; var16++) {
/*     */               
/* 115 */               int var17 = var16 - par5;
/*     */ 
/*     */ 
/*     */               
/* 119 */               if (var15 != var13 || -var15 != var13 || var17 != var13 || -var17 != var13 || (par2Random.nextInt(2) != 0 && var12 != 0)) {
/*     */                 
/* 121 */                 int var18 = par1World.getBlockId(var14, var11, var16);
/*     */                 
/* 123 */                 if (var18 == 0 || var18 == Block.leaves.blockID)
/*     */                 {
/* 125 */                   setBlockAndMetadata(par1World, var14, var11, var16, Block.leaves.blockID, this.metaLeaves);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 132 */         for (var11 = 0; var11 < var6; var11++) {
/*     */           
/* 134 */           int var12 = par1World.getBlockId(par3, par4 + var11, par5);
/*     */           
/* 136 */           if (var12 == 0 || var12 == Block.leaves.blockID) {
/*     */             
/* 138 */             setBlockAndMetadata(par1World, par3, par4 + var11, par5, Block.wood.blockID, this.metaWood);
/*     */             
/* 140 */             if (this.vinesGrow && var11 > 0) {
/*     */               
/* 142 */               if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 - 1, par4 + var11, par5))
/*     */               {
/*     */                 
/* 145 */                 setBlockAndMetadata(par1World, par3 - 1, par4 + var11, par5, Block.vine.blockID, 8);
/*     */               }
/*     */               
/* 148 */               if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3 + 1, par4 + var11, par5))
/*     */               {
/*     */                 
/* 151 */                 setBlockAndMetadata(par1World, par3 + 1, par4 + var11, par5, Block.vine.blockID, 2);
/*     */               }
/*     */               
/* 154 */               if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 - 1))
/*     */               {
/*     */                 
/* 157 */                 setBlockAndMetadata(par1World, par3, par4 + var11, par5 - 1, Block.vine.blockID, 1);
/*     */               }
/*     */               
/* 160 */               if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(par3, par4 + var11, par5 + 1))
/*     */               {
/*     */                 
/* 163 */                 setBlockAndMetadata(par1World, par3, par4 + var11, par5 + 1, Block.vine.blockID, 4);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 169 */         if (this.vinesGrow) {
/*     */           
/* 171 */           for (var11 = par4 - 3 + var6; var11 <= par4 + var6; var11++) {
/*     */             
/* 173 */             int var12 = var11 - par4 + var6;
/* 174 */             int var13 = 2 - var12 / 2;
/*     */             
/* 176 */             for (int var14 = par3 - var13; var14 <= par3 + var13; var14++) {
/*     */               
/* 178 */               for (int var15 = par5 - var13; var15 <= par5 + var13; var15++) {
/*     */                 
/* 180 */                 if (par1World.getBlockId(var14, var11, var15) == Block.leaves.blockID) {
/*     */                   
/* 182 */                   if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var11, var15) == 0)
/*     */                   {
/*     */                     
/* 185 */                     growVines(par1World, var14 - 1, var11, var15, 8);
/*     */                   }
/*     */                   
/* 188 */                   if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var11, var15) == 0)
/*     */                   {
/*     */                     
/* 191 */                     growVines(par1World, var14 + 1, var11, var15, 2);
/*     */                   }
/*     */                   
/* 194 */                   if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 - 1) == 0)
/*     */                   {
/*     */                     
/* 197 */                     growVines(par1World, var14, var11, var15 - 1, 1);
/*     */                   }
/*     */                   
/* 200 */                   if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 + 1) == 0)
/*     */                   {
/*     */                     
/* 203 */                     growVines(par1World, var14, var11, var15 + 1, 4);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 210 */           if (par2Random.nextInt(5) == 0 && var6 > 5)
/*     */           {
/*     */             
/* 213 */             for (var11 = 0; var11 < 2; var11++) {
/*     */               
/* 215 */               for (int var12 = 0; var12 < 4; var12++) {
/*     */                 
/* 217 */                 if (par2Random.nextInt(4 - var11) == 0) {
/*     */ 
/*     */                   
/* 220 */                   int var13 = par2Random.nextInt(3);
/*     */                   
/* 222 */                   setBlockAndMetadata(par1World, par3 + Direction.offsetX[Direction.rotateOpposite[var12]], par4 + var6 - 5 + var11, par5 + Direction.offsetZ[Direction.rotateOpposite[var12]], Block.cocoaPlant.blockID, var13 << 2 | var12);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */         
/* 229 */         if (par1World.decorating) {
/* 230 */           par1World.placeNaturallyOccurringSnow(par3 - radius, par5 - radius, par3 + radius, par5 + radius);
/*     */         }
/* 232 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 236 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 242 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void growVines(World par1World, int par2, int par3, int par4, int par5) {
/* 251 */     setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
/* 252 */     int var6 = 4;
/*     */ 
/*     */     
/*     */     while (true) {
/* 256 */       par3--;
/*     */       
/* 258 */       if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 263 */       setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
/* 264 */       var6--;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenTrees.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */