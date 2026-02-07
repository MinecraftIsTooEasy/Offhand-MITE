/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class WorldGenBigMushroom
/*     */   extends WorldGenerator
/*     */ {
/*   8 */   private int mushroomType = -1;
/*     */ 
/*     */   
/*     */   public WorldGenBigMushroom(int par1) {
/*  12 */     super(true);
/*  13 */     this.mushroomType = par1;
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldGenBigMushroom() {
/*  18 */     super(false);
/*     */     
/*  20 */     this.mushroomType = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5) {
/*  25 */     int var6 = par2Random.nextInt(2);
/*     */     
/*  27 */     if (this.mushroomType >= 0)
/*     */     {
/*  29 */       var6 = this.mushroomType;
/*     */     }
/*     */     
/*  32 */     int var7 = par2Random.nextInt(3) + 4;
/*  33 */     boolean var8 = true;
/*     */     
/*  35 */     if (par4 >= 1 && par4 + var7 + 1 < 256) {
/*     */       int var9;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  42 */       for (var9 = par4; var9 <= par4 + 1 + var7; var9++) {
/*     */         
/*  44 */         byte var10 = 3;
/*     */         
/*  46 */         if (var9 <= par4 + 3)
/*     */         {
/*  48 */           var10 = 0;
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  57 */         for (int var11 = par3 - var10; var11 <= par3 + var10 && var8; var11++) {
/*     */           
/*  59 */           for (int var12 = par5 - var10; var12 <= par5 + var10 && var8; var12++) {
/*     */             
/*  61 */             boolean extreme_x = (var11 == par3 - var10 || var11 == par3 + var10);
/*  62 */             boolean extreme_z = (var12 == par5 - var10 || var12 == par5 + var10);
/*     */             
/*  64 */             if (!extreme_x || !extreme_z || var10 <= 0)
/*     */             {
/*     */               
/*  67 */               if (var9 >= 0 && var9 < 256) {
/*     */                 
/*  69 */                 int var13 = par1World.getBlockId(var11, var9, var12);
/*     */                 
/*  71 */                 if (var13 != 0 && var13 != Block.leaves.blockID)
/*     */                 {
/*     */                   
/*  74 */                   var8 = false;
/*     */                 }
/*     */               }
/*     */               else {
/*     */                 
/*  79 */                 var8 = false;
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*  85 */       if (!var8)
/*     */       {
/*  87 */         return false;
/*     */       }
/*     */ 
/*     */       
/*  91 */       var9 = par1World.getBlockId(par3, par4 - 1, par5);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       if (var9 == Block.dirt.blockID || var9 == Block.grass.blockID || var9 == Block.mycelium.blockID) {
/*     */         
/* 100 */         int var16 = par4 + var7;
/*     */         
/* 102 */         if (var6 == 1)
/*     */         {
/* 104 */           var16 = par4 + var7 - 3;
/*     */         }
/*     */         int var11;
/* 107 */         for (var11 = var16; var11 <= par4 + var7; var11++) {
/*     */           
/* 109 */           int var12 = 1;
/*     */           
/* 111 */           if (var11 < par4 + var7)
/*     */           {
/* 113 */             var12++;
/*     */           }
/*     */           
/* 116 */           if (var6 == 0)
/*     */           {
/* 118 */             var12 = 3;
/*     */           }
/*     */           
/* 121 */           for (int var13 = par3 - var12; var13 <= par3 + var12; var13++) {
/*     */             
/* 123 */             for (int var14 = par5 - var12; var14 <= par5 + var12; var14++) {
/*     */               
/* 125 */               int var15 = 5;
/*     */               
/* 127 */               if (var13 == par3 - var12)
/*     */               {
/* 129 */                 var15--;
/*     */               }
/*     */               
/* 132 */               if (var13 == par3 + var12)
/*     */               {
/* 134 */                 var15++;
/*     */               }
/*     */               
/* 137 */               if (var14 == par5 - var12)
/*     */               {
/* 139 */                 var15 -= 3;
/*     */               }
/*     */               
/* 142 */               if (var14 == par5 + var12)
/*     */               {
/* 144 */                 var15 += 3;
/*     */               }
/*     */               
/* 147 */               if (var6 == 0 || var11 < par4 + var7) {
/*     */                 
/* 149 */                 if ((var13 == par3 - var12 || var13 == par3 + var12) && (var14 == par5 - var12 || var14 == par5 + var12)) {
/*     */                   continue;
/*     */                 }
/*     */ 
/*     */                 
/* 154 */                 if (var13 == par3 - var12 - 1 && var14 == par5 - var12)
/*     */                 {
/* 156 */                   var15 = 1;
/*     */                 }
/*     */                 
/* 159 */                 if (var13 == par3 - var12 && var14 == par5 - var12 - 1)
/*     */                 {
/* 161 */                   var15 = 1;
/*     */                 }
/*     */                 
/* 164 */                 if (var13 == par3 + var12 - 1 && var14 == par5 - var12)
/*     */                 {
/* 166 */                   var15 = 3;
/*     */                 }
/*     */                 
/* 169 */                 if (var13 == par3 + var12 && var14 == par5 - var12 - 1)
/*     */                 {
/* 171 */                   var15 = 3;
/*     */                 }
/*     */                 
/* 174 */                 if (var13 == par3 - var12 - 1 && var14 == par5 + var12)
/*     */                 {
/* 176 */                   var15 = 7;
/*     */                 }
/*     */                 
/* 179 */                 if (var13 == par3 - var12 && var14 == par5 + var12 - 1)
/*     */                 {
/* 181 */                   var15 = 7;
/*     */                 }
/*     */                 
/* 184 */                 if (var13 == par3 + var12 - 1 && var14 == par5 + var12)
/*     */                 {
/* 186 */                   var15 = 9;
/*     */                 }
/*     */                 
/* 189 */                 if (var13 == par3 + var12 && var14 == par5 + var12 - 1)
/*     */                 {
/* 191 */                   var15 = 9;
/*     */                 }
/*     */               } 
/*     */               
/* 195 */               if (var15 == 5 && var11 < par4 + var7)
/*     */               {
/* 197 */                 var15 = 0;
/*     */               }
/*     */               
/* 200 */               if ((var15 != 0 || par4 >= par4 + var7 - 1) && !Block.opaqueCubeLookup[par1World.getBlockId(var13, var11, var14)])
/*     */               {
/* 202 */                 setBlockAndMetadata(par1World, var13, var11, var14, Block.mushroomCapBrown.blockID + var6, var15);
/*     */               }
/*     */               continue;
/*     */             } 
/*     */           } 
/*     */         } 
/* 208 */         for (var11 = 0; var11 < var7; var11++) {
/*     */           
/* 210 */           int var12 = par1World.getBlockId(par3, par4 + var11, par5);
/*     */           
/* 212 */           if (!Block.opaqueCubeLookup[var12])
/*     */           {
/* 214 */             setBlockAndMetadata(par1World, par3, par4 + var11, par5, Block.mushroomCapBrown.blockID + var6, 10);
/*     */           }
/*     */         } 
/*     */         
/* 218 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 222 */       return false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldGenBigMushroom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */