/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class MapGenCaves
/*     */   extends MapGenBase {
/*  10 */   private static final byte grass_block_id = (byte)Block.grass.blockID;
/*  11 */   private static final byte stone_block_id = (byte)Block.stone.blockID;
/*  12 */   private static final byte dirt_block_id = (byte)Block.dirt.blockID;
/*  13 */   private static final byte water_moving_block_id = (byte)Block.waterMoving.blockID;
/*  14 */   private static final byte water_still_block_id = (byte)Block.waterStill.blockID;
/*  15 */   private static final byte lava_moving_block_id = (byte)Block.lavaMoving.blockID;
/*     */   
/*  17 */   private static final byte sand_block_id = (byte)Block.sand.blockID;
/*  18 */   private static final byte sand_stone_block_id = (byte)Block.sandStone.blockID;
/*     */   
/*  20 */   private HashMap pending_sand_falls = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateLargeCaveNode(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10) {
/*  27 */     generateCaveNode(par1, par3, par4, par5ArrayOfByte, par6, par8, par10, 1.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void generateCaveNode(long par1, int par3, int par4, byte[] par5ArrayOfByte, double par6, double par8, double par10, float par12, float par13, float par14, int par15, int par16, double par17) {
/*  35 */     int par3_times_16 = par3 * 16;
/*  36 */     int par4_times_16 = par4 * 16;
/*     */ 
/*     */ 
/*     */     
/*  40 */     double var19 = (par3_times_16 + 8);
/*  41 */     double var21 = (par4_times_16 + 8);
/*  42 */     float var23 = 0.0F;
/*  43 */     float var24 = 0.0F;
/*  44 */     Random var25 = new Random(par1);
/*     */     
/*  46 */     if (par16 <= 0) {
/*     */       
/*  48 */       int var26 = this.range * 16 - 16;
/*  49 */       par16 = var26 - var25.nextInt(var26 / 4);
/*     */     } 
/*     */     
/*  52 */     boolean var54 = false;
/*     */     
/*  54 */     if (par15 == -1) {
/*     */       
/*  56 */       par15 = par16 / 2;
/*  57 */       var54 = true;
/*     */     } 
/*     */     
/*  60 */     int var27 = var25.nextInt(par16 / 2) + par16 / 4;
/*     */     
/*  62 */     for (boolean var28 = (var25.nextInt(6) == 0); par15 < par16; par15++) {
/*     */       
/*  64 */       double var29 = 1.5D + (MathHelper.sin(par15 * 3.1415927F / par16) * par12 * 1.0F);
/*  65 */       double var31 = var29 * par17;
/*  66 */       float var33 = MathHelper.cos(par14);
/*  67 */       float var34 = MathHelper.sin(par14);
/*  68 */       par6 += (MathHelper.cos(par13) * var33);
/*  69 */       par8 += var34;
/*  70 */       par10 += (MathHelper.sin(par13) * var33);
/*     */       
/*  72 */       if (var28) {
/*     */         
/*  74 */         par14 *= 0.92F;
/*     */       }
/*     */       else {
/*     */         
/*  78 */         par14 *= 0.7F;
/*     */       } 
/*     */       
/*  81 */       par14 += var24 * 0.1F;
/*  82 */       par13 += var23 * 0.1F;
/*  83 */       var24 *= 0.9F;
/*  84 */       var23 *= 0.75F;
/*  85 */       var24 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 2.0F;
/*  86 */       var23 += (var25.nextFloat() - var25.nextFloat()) * var25.nextFloat() * 4.0F;
/*     */       
/*  88 */       if (!var54 && par15 == var27 && par12 > 1.0F && par16 > 0) {
/*     */         
/*  90 */         generateCaveNode(var25.nextLong(), par3, par4, par5ArrayOfByte, par6, par8, par10, var25.nextFloat() * 0.5F + 0.5F, par13 - 1.5707964F, par14 / 3.0F, par15, par16, 1.0D);
/*  91 */         generateCaveNode(var25.nextLong(), par3, par4, par5ArrayOfByte, par6, par8, par10, var25.nextFloat() * 0.5F + 0.5F, par13 + 1.5707964F, par14 / 3.0F, par15, par16, 1.0D);
/*     */         
/*     */         return;
/*     */       } 
/*  95 */       if (var54 || var25.nextInt(4) != 0) {
/*     */         
/*  97 */         double var35 = par6 - var19;
/*  98 */         double var37 = par10 - var21;
/*  99 */         double var39 = (par16 - par15);
/* 100 */         double var41 = (par12 + 2.0F + 16.0F);
/*     */         
/* 102 */         if (var35 * var35 + var37 * var37 - var39 * var39 > var41 * var41) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 107 */         if (par6 >= var19 - 16.0D - var29 * 2.0D && par10 >= var21 - 16.0D - var29 * 2.0D && par6 <= var19 + 16.0D + var29 * 2.0D && par10 <= var21 + 16.0D + var29 * 2.0D) {
/*     */ 
/*     */ 
/*     */           
/* 111 */           int var55 = MathHelper.floor_double(par6 - var29) - par3_times_16 - 1;
/* 112 */           int var36 = MathHelper.floor_double(par6 + var29) - par3_times_16 + 1;
/* 113 */           int var57 = MathHelper.floor_double(par8 - var31) - 1;
/* 114 */           int var38 = MathHelper.floor_double(par8 + var31) + 1;
/*     */ 
/*     */           
/* 117 */           int var56 = MathHelper.floor_double(par10 - var29) - par4_times_16 - 1;
/* 118 */           int var40 = MathHelper.floor_double(par10 + var29) - par4_times_16 + 1;
/*     */           
/* 120 */           if (var55 < 0)
/*     */           {
/* 122 */             var55 = 0;
/*     */           }
/*     */           
/* 125 */           if (var36 > 16)
/*     */           {
/* 127 */             var36 = 16;
/*     */           }
/*     */           
/* 130 */           if (var57 < 1)
/*     */           {
/* 132 */             var57 = 1;
/*     */           }
/*     */           
/* 135 */           if (var38 > 120)
/*     */           {
/* 137 */             var38 = 120;
/*     */           }
/*     */           
/* 140 */           if (var56 < 0)
/*     */           {
/* 142 */             var56 = 0;
/*     */           }
/*     */           
/* 145 */           if (var40 > 16)
/*     */           {
/* 147 */             var40 = 16;
/*     */           }
/*     */           
/* 150 */           boolean var58 = false;
/*     */           
/*     */           int var42;
/*     */           
/* 154 */           for (var42 = var55; !var58 && var42 < var36; var42++) {
/*     */             
/* 156 */             int var42_times_16 = var42 * 16;
/*     */             
/* 158 */             for (int var43 = var56; !var58 && var43 < var40; var43++) {
/*     */               
/* 160 */               int var42_times_16_plus_var43_times_128 = (var42_times_16 + var43) * 128;
/*     */               
/* 162 */               for (int var44 = var38 + 1; !var58 && var44 >= var57 - 1; var44--) {
/*     */ 
/*     */                 
/* 165 */                 int var45 = var42_times_16_plus_var43_times_128 + var44;
/*     */                 
/* 167 */                 if (var44 >= 0 && var44 < 128) {
/*     */ 
/*     */                   
/* 170 */                   if (par5ArrayOfByte[var45] == water_moving_block_id || par5ArrayOfByte[var45] == water_still_block_id)
/*     */                   {
/* 172 */                     var58 = true;
/*     */                   }
/*     */                   
/* 175 */                   if (var44 != var57 - 1 && var42 != var55 && var42 != var36 - 1 && var43 != var56 && var43 != var40 - 1)
/*     */                   {
/* 177 */                     var44 = var57;
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 184 */           if (!var58) {
/*     */             
/* 186 */             this.pending_sand_falls.clear();
/*     */             
/* 188 */             for (var42 = var55; var42 < var36; var42++) {
/*     */ 
/*     */               
/* 191 */               double var59 = ((var42 + par3_times_16) + 0.5D - par6) / var29;
/*     */               
/* 193 */               for (int var45 = var56; var45 < var40; var45++) {
/*     */                 
/* 195 */                 byte top_block_id = (this.worldObj.getBiomeGenForCoords(var42 + par3_times_16, var45 + par4_times_16)).topBlock;
/*     */                 
/* 197 */                 double var46 = ((var45 + par4_times_16) + 0.5D - par10) / var29;
/* 198 */                 int var48 = (var42 * 16 + var45) * 128 + var38;
/* 199 */                 boolean var49 = false;
/*     */                 
/* 201 */                 if (var59 * var59 + var46 * var46 < 1.0D)
/*     */                 {
/* 203 */                   for (int var50 = var38 - 1; var50 >= var57; var50--) {
/*     */                     
/* 205 */                     double var51 = (var50 + 0.5D - par8) / var31;
/*     */                     
/* 207 */                     if (var51 > -0.7D && var59 * var59 + var51 * var51 + var46 * var46 < 1.0D) {
/*     */                       
/* 209 */                       byte var53 = par5ArrayOfByte[var48];
/*     */ 
/*     */                       
/* 212 */                       if (var53 == grass_block_id)
/*     */                       {
/* 214 */                         var49 = true;
/*     */                       }
/*     */ 
/*     */                       
/* 218 */                       if (var53 == stone_block_id || var53 == dirt_block_id || var53 == grass_block_id || var53 == sand_block_id || var53 == sand_stone_block_id)
/*     */                       {
/* 220 */                         if (var50 < 10) {
/*     */ 
/*     */                           
/* 223 */                           par5ArrayOfByte[var48] = lava_moving_block_id;
/*     */                         
/*     */                         }
/*     */                         else {
/*     */ 
/*     */                           
/* 229 */                           int index_of_block_above = var48 + 1;
/* 230 */                           int block_above_id = par5ArrayOfByte[index_of_block_above];
/*     */                           
/* 232 */                           if (block_above_id == sand_block_id) {
/*     */                             
/* 234 */                             boolean abort = false;
/*     */ 
/*     */                             
/*     */                             while (true) {
/* 238 */                               block_above_id = par5ArrayOfByte[++index_of_block_above];
/*     */                               
/* 240 */                               if (block_above_id == water_still_block_id) {
/*     */                                 
/* 242 */                                 abort = true;
/*     */                                 
/*     */                                 break;
/*     */                               } 
/* 246 */                               if (block_above_id == sand_block_id) {
/*     */                                 continue;
/*     */                               }
/*     */                               
/*     */                               break;
/*     */                             } 
/* 252 */                             if (abort) {
/*     */                               
/* 254 */                               var50 = 0;
/* 255 */                               var48--;
/*     */ 
/*     */                               
/*     */                               continue;
/*     */                             } 
/*     */                           } 
/*     */                           
/* 262 */                           if (var53 != sand_block_id) {
/* 263 */                             par5ArrayOfByte[var48] = 0;
/*     */                           }
/*     */                           
/* 266 */                           if (var49 && par5ArrayOfByte[var48 - 1] == dirt_block_id) {
/*     */ 
/*     */                             
/* 269 */                             par5ArrayOfByte[var48 - 1] = top_block_id;
/*     */                           }
/* 271 */                           else if (par5ArrayOfByte[var48 + 1] == sand_block_id) {
/*     */                             
/* 273 */                             int xz_index = var42 + var45 * 16;
/*     */                             
/* 275 */                             if (!this.pending_sand_falls.containsKey(Integer.valueOf(xz_index))) {
/* 276 */                               this.pending_sand_falls.put(Integer.valueOf(xz_index), Integer.valueOf(var50 + 2));
/*     */                             }
/*     */                           } 
/*     */                         } 
/*     */                       }
/*     */                     } 
/* 282 */                     var48--;
/*     */                     continue;
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */             } 
/* 288 */             if (!this.pending_sand_falls.isEmpty()) {
/*     */               
/* 290 */               Iterator<Map.Entry> i = this.pending_sand_falls.entrySet().iterator();
/*     */               
/* 292 */               while (i.hasNext()) {
/*     */                 
/* 294 */                 Map.Entry entry = i.next();
/*     */                 
/* 296 */                 int xz_index = ((Integer)entry.getKey()).intValue();
/* 297 */                 int y = ((Integer)entry.getValue()).intValue();
/*     */                 
/* 299 */                 int local_x = xz_index % 16;
/* 300 */                 int local_z = xz_index / 16;
/*     */                 
/* 302 */                 int index = (local_x * 16 + local_z) * 128 + y;
/*     */                 
/* 304 */                 while (y > 48 && (par5ArrayOfByte[index] != sand_block_id || par5ArrayOfByte[index - 1] != 0)) {
/*     */                   
/* 306 */                   index--;
/* 307 */                   y--;
/*     */                 } 
/*     */                 
/* 310 */                 if (par5ArrayOfByte[index] == sand_block_id) {
/*     */                   
/* 312 */                   if (this.worldObj.pending_sand_falls == null) {
/* 313 */                     this.worldObj.pending_sand_falls = new HashMap<Object, Object>();
/*     */                   }
/* 315 */                   this.worldObj.pending_sand_falls.put(Integer.valueOf(xz_index), Integer.valueOf(y));
/*     */                 } 
/*     */               } 
/*     */               
/* 319 */               this.pending_sand_falls.clear();
/*     */             } 
/*     */             
/* 322 */             if (var54) {
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
/*     */   
/*     */   protected void xrecursiveGenerate(World par1World, int par2, int par3, int par4, int par5, byte[] par6ArrayOfByte) {
/*     */     float frequency;
/* 339 */     BiomeGenBase biome = par1World.getBiomeGenForCoords(par2 * 16, par3 * 16);
/*     */     
/* 341 */     if (biome == BiomeGenBase.plains || biome == BiomeGenBase.swampland) {
/* 342 */       frequency = 0.75F;
/* 343 */     } else if (biome == BiomeGenBase.iceMountains) {
/* 344 */       frequency = 1.25F;
/* 345 */     } else if (biome == BiomeGenBase.extremeHills) {
/* 346 */       frequency = 1.5F;
/*     */     } else {
/* 348 */       frequency = 1.0F;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     if (this.rand.nextInt((int)(40.0F / frequency)) != 0) {
/*     */       return;
/*     */     }
/* 357 */     int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(40) + 1) + 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 364 */     var7 = (int)(var7 * frequency);
/*     */     
/* 366 */     for (int var8 = 0; var8 < var7; var8++) {
/*     */       
/* 368 */       double var11, var9 = (par2 * 16 + this.rand.nextInt(16));
/*     */       
/* 370 */       double var13 = (par3 * 16 + this.rand.nextInt(16));
/* 371 */       int var15 = 1;
/*     */ 
/*     */ 
/*     */       
/* 375 */       if (biome == BiomeGenBase.extremeHills || this.rand.nextInt(2) == 0) {
/* 376 */         var11 = this.rand.nextInt(this.rand.nextInt(120) + 8);
/*     */       } else {
/* 378 */         var11 = this.rand.nextInt(this.rand.nextInt(40) + 8);
/*     */       } 
/* 380 */       if (this.rand.nextInt(4) == 0) {
/*     */         
/* 382 */         generateLargeCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13);
/* 383 */         var15 += this.rand.nextInt(4);
/*     */       } 
/*     */       
/* 386 */       for (int var16 = 0; var16 < var15; var16++) {
/*     */         
/* 388 */         float var17 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 389 */         float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 390 */         float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
/*     */         
/* 392 */         if (this.rand.nextInt(10) == 0)
/*     */         {
/* 394 */           var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
/*     */         }
/*     */         
/* 397 */         generateCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
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
/*     */     float frequency;
/* 409 */     BiomeGenBase biome = par1World.getBiomeGenForCoords(par2 * 16, par3 * 16);
/*     */     
/* 411 */     if (biome == BiomeGenBase.plains || biome == BiomeGenBase.swampland) {
/* 412 */       frequency = 0.8F;
/* 413 */     } else if (biome == BiomeGenBase.iceMountains) {
/* 414 */       frequency = 1.2F;
/* 415 */     } else if (biome == BiomeGenBase.extremeHills) {
/* 416 */       frequency = 1.4F;
/*     */     } else {
/* 418 */       frequency = 1.0F;
/*     */     } 
/* 420 */     if (this.rand.nextInt((int)(15.0F / frequency)) != 0) {
/*     */       return;
/*     */     }
/*     */     
/* 424 */     int var7 = this.rand.nextInt(this.rand.nextInt(this.rand.nextInt(30) + 1) + 1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 431 */     var7 = (int)(var7 * frequency);
/*     */     
/* 433 */     boolean increase_frequency_of_larger_tunnels = (par1World.worldInfo.getEarliestMITEReleaseRunIn() >= 166);
/*     */     
/* 435 */     for (int var8 = 0; var8 < var7; var8++) {
/*     */       int rarity_of_large_tunnels;
/* 437 */       double var9 = (par2 * 16 + this.rand.nextInt(16));
/* 438 */       double var11 = this.rand.nextInt(this.rand.nextInt(120) + 8);
/* 439 */       double var13 = (par3 * 16 + this.rand.nextInt(16));
/* 440 */       int var15 = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 446 */       if (increase_frequency_of_larger_tunnels) {
/* 447 */         rarity_of_large_tunnels = (var11 > 23.0D && var11 < 33.0D) ? 2 : 10;
/*     */       } else {
/* 449 */         rarity_of_large_tunnels = 10;
/*     */       } 
/*     */ 
/*     */       
/* 453 */       if (this.rand.nextInt(4) == 0) {
/*     */         
/* 455 */         generateLargeCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13);
/* 456 */         var15 += this.rand.nextInt(4);
/*     */       } 
/*     */       
/* 459 */       for (int var16 = 0; var16 < var15; var16++) {
/*     */         
/* 461 */         float var17 = this.rand.nextFloat() * 3.1415927F * 2.0F;
/* 462 */         float var18 = (this.rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
/* 463 */         float var19 = this.rand.nextFloat() * 2.0F + this.rand.nextFloat();
/*     */ 
/*     */         
/* 466 */         if (this.rand.nextInt(rarity_of_large_tunnels) == 0) {
/*     */           
/* 468 */           var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 3.0F + 1.0F;
/*     */           
/* 470 */           if (rarity_of_large_tunnels < 10) {
/* 471 */             var19 *= 1.0F + this.rand.nextFloat() * 0.5F;
/*     */           }
/* 473 */         } else if (increase_frequency_of_larger_tunnels && var11 < 41.0D && var11 > 15.0D && this.rand.nextInt(2) == 0) {
/*     */           
/* 475 */           var19 *= this.rand.nextFloat() * this.rand.nextFloat() * 1.5F + 1.0F;
/*     */         } 
/*     */         
/* 478 */         generateCaveNode(this.rand.nextLong(), par4, par5, par6ArrayOfByte, var9, var11, var13, var19, var17, var18, 0, 0, 1.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenCaves.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */