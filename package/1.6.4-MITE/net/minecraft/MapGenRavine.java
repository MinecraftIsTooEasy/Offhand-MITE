/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapGenRavine
/*     */   extends MapGenBase
/*     */ {
/*  10 */   private float[] field_75046_d = new float[1024];
/*     */ 
/*     */ 
/*     */   
/*  14 */   private static final byte grass_block_id = (byte)Block.grass.blockID;
/*  15 */   private static final byte stone_block_id = (byte)Block.stone.blockID;
/*  16 */   private static final byte dirt_block_id = (byte)Block.dirt.blockID;
/*  17 */   private static final byte water_moving_block_id = (byte)Block.waterMoving.blockID;
/*  18 */   private static final byte water_still_block_id = (byte)Block.waterStill.blockID;
/*  19 */   private static final byte lava_moving_block_id = (byte)Block.lavaMoving.blockID;
/*     */   
/*  21 */   private static final byte sand_block_id = (byte)Block.sand.blockID;
/*  22 */   private static final byte sand_stone_block_id = (byte)Block.sandStone.blockID;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateRavine(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17) {
/*  28 */     Random var19 = new Random(par1);
/*  29 */     double var20 = (par3 * 16 + 8);
/*  30 */     double var22 = (par4 * 16 + 8);
/*  31 */     float var24 = 0.0F;
/*  32 */     float var25 = 0.0F;
/*     */     
/*  34 */     if (par16 <= 0) {
/*     */       
/*  36 */       int var26 = this.range * 16 - 16;
/*  37 */       par16 = var26 - var19.nextInt(var26 / 4);
/*     */     } 
/*     */     
/*  40 */     boolean var54 = false;
/*     */     
/*  42 */     if (par15 == -1) {
/*     */       
/*  44 */       par15 = par16 / 2;
/*  45 */       var54 = true;
/*     */     } 
/*     */     
/*  48 */     float var27 = 1.0F;
/*     */     
/*  50 */     for (int var28 = 0; var28 < 128; var28++) {
/*     */       
/*  52 */       if (var28 == 0 || var19.nextInt(3) == 0)
/*     */       {
/*  54 */         var27 = 1.0F + var19.nextFloat() * var19.nextFloat() * 1.0F;
/*     */       }
/*     */       
/*  57 */       this.field_75046_d[var28] = var27 * var27;
/*     */     } 
/*     */     
/*  60 */     for (; par15 < par16; par15++) {
/*     */       
/*  62 */       double var53 = 1.5D + (MathHelper.sin(par15 * 3.1415927F / par16) * par12 * 1.0F);
/*  63 */       double var30 = var53 * par17;
/*  64 */       var53 *= var19.nextFloat() * 0.25D + 0.75D;
/*  65 */       var30 *= var19.nextFloat() * 0.25D + 0.75D;
/*  66 */       float var32 = MathHelper.cos(par14);
/*  67 */       float var33 = MathHelper.sin(par14);
/*  68 */       par6 += (MathHelper.cos(par13) * var32);
/*  69 */       par8 += var33;
/*  70 */       par10 += (MathHelper.sin(par13) * var32);
/*  71 */       par14 *= 0.7F;
/*  72 */       par14 += var25 * 0.05F;
/*  73 */       par13 += var24 * 0.05F;
/*  74 */       var25 *= 0.8F;
/*  75 */       var24 *= 0.5F;
/*  76 */       var25 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 2.0F;
/*  77 */       var24 += (var19.nextFloat() - var19.nextFloat()) * var19.nextFloat() * 4.0F;
/*     */       
/*  79 */       if (var54 || var19.nextInt(4) != 0) {
/*     */         
/*  81 */         double var34 = par6 - var20;
/*  82 */         double var36 = par10 - var22;
/*  83 */         double var38 = (par16 - par15);
/*  84 */         double var40 = (par12 + 2.0F + 16.0F);
/*     */         
/*  86 */         if (var34 * var34 + var36 * var36 - var38 * var38 > var40 * var40) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/*  91 */         if (par6 >= var20 - 16.0D - var53 * 2.0D && par10 >= var22 - 16.0D - var53 * 2.0D && par6 <= var20 + 16.0D + var53 * 2.0D && par10 <= var22 + 16.0D + var53 * 2.0D) {
/*     */           
/*  93 */           int var56 = MathHelper.floor_double(par6 - var53) - par3 * 16 - 1;
/*  94 */           int var35 = MathHelper.floor_double(par6 + var53) - par3 * 16 + 1;
/*  95 */           int var55 = MathHelper.floor_double(par8 - var30) - 1;
/*  96 */           int var37 = MathHelper.floor_double(par8 + var30) + 1;
/*  97 */           int var57 = MathHelper.floor_double(par10 - var53) - par4 * 16 - 1;
/*  98 */           int var39 = MathHelper.floor_double(par10 + var53) - par4 * 16 + 1;
/*     */           
/* 100 */           if (var56 < 0)
/*     */           {
/* 102 */             var56 = 0;
/*     */           }
/*     */           
/* 105 */           if (var35 > 16)
/*     */           {
/* 107 */             var35 = 16;
/*     */           }
/*     */           
/* 110 */           if (var55 < 1)
/*     */           {
/* 112 */             var55 = 1;
/*     */           }
/*     */           
/* 115 */           if (var37 > 120)
/*     */           {
/* 117 */             var37 = 120;
/*     */           }
/*     */           
/* 120 */           if (var57 < 0)
/*     */           {
/* 122 */             var57 = 0;
/*     */           }
/*     */           
/* 125 */           if (var39 > 16)
/*     */           {
/* 127 */             var39 = 16;
/*     */           }
/*     */           
/* 130 */           boolean var58 = false;
/*     */           
/*     */           int var41;
/*     */           
/* 134 */           for (var41 = var56; !var58 && var41 < var35; var41++) {
/*     */             
/* 136 */             for (int var42 = var57; !var58 && var42 < var39; var42++) {
/*     */               
/* 138 */               for (int var43 = var37 + 1; !var58 && var43 >= var55 - 1; var43--) {
/*     */                 
/* 140 */                 int var44 = (var41 * 16 + var42) * 128 + var43;
/*     */                 
/* 142 */                 if (var43 >= 0 && var43 < 128) {
/*     */                   
/* 144 */                   if (par5ArrayOfByte[var44] == Block.waterMoving.blockID || par5ArrayOfByte[var44] == Block.waterStill.blockID)
/*     */                   {
/* 146 */                     var58 = true;
/*     */                   }
/*     */                   
/* 149 */                   if (var43 != var55 - 1 && var41 != var56 && var41 != var35 - 1 && var42 != var57 && var42 != var39 - 1)
/*     */                   {
/* 151 */                     var43 = var55;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 158 */           if (!var58) {
/*     */             
/* 160 */             for (var41 = var56; var41 < var35; var41++) {
/*     */               
/* 162 */               double var59 = ((var41 + par3 * 16) + 0.5D - par6) / var53;
/*     */               
/* 164 */               for (int var44 = var57; var44 < var39; var44++) {
/*     */                 
/* 166 */                 double var45 = ((var44 + par4 * 16) + 0.5D - par10) / var53;
/* 167 */                 int var47 = (var41 * 16 + var44) * 128 + var37;
/* 168 */                 boolean var48 = false;
/*     */                 
/* 170 */                 if (var59 * var59 + var45 * var45 < 1.0D)
/*     */                 {
/* 172 */                   for (int var49 = var37 - 1; var49 >= var55; var49--) {
/*     */                     
/* 174 */                     double var50 = (var49 + 0.5D - par8) / var30;
/*     */                     
/* 176 */                     if ((var59 * var59 + var45 * var45) * this.field_75046_d[var49] + var50 * var50 / 6.0D < 1.0D) {
/*     */                       
/* 178 */                       byte var52 = par5ArrayOfByte[var47];
/*     */                       
/* 180 */                       if (var52 == Block.grass.blockID)
/*     */                       {
/* 182 */                         var48 = true;
/*     */                       }
/*     */ 
/*     */                       
/* 186 */                       if (var52 == stone_block_id || var52 == dirt_block_id || var52 == grass_block_id || var52 == sand_block_id || var52 == sand_stone_block_id)
/*     */                       {
/* 188 */                         if (var49 < 10) {
/*     */                           
/* 190 */                           par5ArrayOfByte[var47] = (byte)Block.lavaMoving.blockID;
/*     */                         }
/*     */                         else {
/*     */                           
/* 194 */                           par5ArrayOfByte[var47] = 0;
/*     */                           
/* 196 */                           if (var48 && par5ArrayOfByte[var47 - 1] == Block.dirt.blockID) {
/*     */                             
/* 198 */                             par5ArrayOfByte[var47 - 1] = (this.worldObj.getBiomeGenForCoords(var41 + par3 * 16, var44 + par4 * 16)).topBlock;
/*     */                           }
/* 200 */                           else if (par5ArrayOfByte[var47 + 1] == sand_block_id) {
/*     */                             
/* 202 */                             int index = var47 + 1;
/*     */                             
/* 204 */                             par5ArrayOfByte[index] = 0;
/*     */                             
/* 206 */                             while (par5ArrayOfByte[++index] == sand_block_id) {
/* 207 */                               par5ArrayOfByte[index] = 0;
/*     */                             }
/*     */                           } 
/*     */                         } 
/*     */                       }
/*     */                     } 
/* 213 */                     var47--;
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/*     */             
/* 219 */             if (var54) {
/*     */               break;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void recursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {
/* 234 */     boolean force_generation = false;
/*     */     
/* 236 */     if (par1World.getSeed() == 1L && par2 == -6 && par3 == 31) {
/* 237 */       force_generation = true;
/*     */     }
/*     */     
/* 240 */     if (this.rand.nextInt((this.worldObj.worldInfo.getEarliestMITEReleaseRunIn() >= 139) ? 100 : 50) == 0 || force_generation) {
/*     */ 
/*     */ 
/*     */       
/* 244 */       double var7 = (par2 * 16 + this.rand.nextInt(16));
/* 245 */       double var9 = (this.rand.nextInt(this.rand.nextInt(40) + 8) + 20);
/* 246 */       double var11 = (par3 * 16 + this.rand.nextInt(16));
/* 247 */       byte var13 = 1;
/*     */       
/* 249 */       for (int var14 = 0; var14 < var13; var14++) {
/*     */         
/* 251 */         float var15 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 252 */         float var16 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 253 */         float var17 = (this.rand.nextFloat() * 2.0F + this.rand.nextFloat()) * 2.0F;
/* 254 */         generateRavine(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var7, var9, var11, var17, var15, var16, 0, 0, 3.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGenAllowedInBiome(BiomeGenBase biome) {
/* 261 */     return (biome != BiomeGenBase.ocean);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenRavine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */