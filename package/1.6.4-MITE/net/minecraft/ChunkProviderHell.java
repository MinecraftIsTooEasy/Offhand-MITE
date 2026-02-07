/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChunkProviderHell
/*     */   implements IChunkProvider
/*     */ {
/*     */   private Random hellRNG;
/*     */   private NoiseGeneratorOctaves netherNoiseGen1;
/*     */   private NoiseGeneratorOctaves netherNoiseGen2;
/*     */   private NoiseGeneratorOctaves netherNoiseGen3;
/*     */   private NoiseGeneratorOctaves slowsandGravelNoiseGen;
/*     */   private NoiseGeneratorOctaves netherrackExculsivityNoiseGen;
/*     */   public NoiseGeneratorOctaves netherNoiseGen6;
/*     */   public NoiseGeneratorOctaves netherNoiseGen7;
/*     */   private World worldObj;
/*     */   private double[] noiseField;
/*  28 */   public MapGenNetherBridge genNetherBridge = new MapGenNetherBridge();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  33 */   private double[] slowsandNoise = new double[256];
/*  34 */   private double[] gravelNoise = new double[256];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private double[] netherrackExclusivityNoise = new double[256];
/*  40 */   private MapGenBase netherCaveGenerator = new MapGenCavesHell();
/*     */   
/*     */   double[] noiseData1;
/*     */   double[] noiseData2;
/*     */   double[] noiseData3;
/*     */   double[] noiseData4;
/*     */   double[] noiseData5;
/*     */   
/*     */   public ChunkProviderHell(World par1World, long par2) {
/*  49 */     this.worldObj = par1World;
/*  50 */     this.hellRNG = new Random(par2);
/*  51 */     this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*  52 */     this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*  53 */     this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
/*  54 */     this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  55 */     this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  56 */     this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
/*  57 */     this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNetherTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
/*  65 */     byte var4 = 4;
/*  66 */     byte var5 = 32;
/*  67 */     int var6 = var4 + 1;
/*  68 */     byte var7 = 17;
/*  69 */     int var8 = var4 + 1;
/*  70 */     this.noiseField = initializeNoiseField(this.noiseField, par1 * var4, 0, par2 * var4, var6, var7, var8);
/*     */     
/*  72 */     for (int var9 = 0; var9 < var4; var9++) {
/*     */       
/*  74 */       for (int var10 = 0; var10 < var4; var10++) {
/*     */         
/*  76 */         for (int var11 = 0; var11 < 16; var11++) {
/*     */           
/*  78 */           double var12 = 0.125D;
/*  79 */           double var14 = this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
/*  80 */           double var16 = this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
/*  81 */           double var18 = this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
/*  82 */           double var20 = this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
/*  83 */           double var22 = (this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
/*  84 */           double var24 = (this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
/*  85 */           double var26 = (this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
/*  86 */           double var28 = (this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;
/*     */           
/*  88 */           for (int var30 = 0; var30 < 8; var30++) {
/*     */             
/*  90 */             double var31 = 0.25D;
/*  91 */             double var33 = var14;
/*  92 */             double var35 = var16;
/*  93 */             double var37 = (var18 - var14) * var31;
/*  94 */             double var39 = (var20 - var16) * var31;
/*     */             
/*  96 */             for (int var41 = 0; var41 < 4; var41++) {
/*     */               
/*  98 */               int var42 = var41 + var9 * 4 << 11 | 0 + var10 * 4 << 7 | var11 * 8 + var30;
/*  99 */               short var43 = 128;
/* 100 */               double var44 = 0.25D;
/* 101 */               double var46 = var33;
/* 102 */               double var48 = (var35 - var33) * var44;
/*     */               
/* 104 */               for (int var50 = 0; var50 < 4; var50++) {
/*     */                 
/* 106 */                 int var51 = 0;
/*     */                 
/* 108 */                 if (var11 * 8 + var30 < var5)
/*     */                 {
/* 110 */                   var51 = Block.lavaStill.blockID;
/*     */                 }
/*     */                 
/* 113 */                 if (var46 > 0.0D)
/*     */                 {
/* 115 */                   var51 = Block.netherrack.blockID;
/*     */                 }
/*     */                 
/* 118 */                 par3ArrayOfByte[var42] = (byte)var51;
/* 119 */                 var42 += var43;
/* 120 */                 var46 += var48;
/*     */               } 
/*     */               
/* 123 */               var33 += var37;
/* 124 */               var35 += var39;
/*     */             } 
/*     */             
/* 127 */             var14 += var22;
/* 128 */             var16 += var24;
/* 129 */             var18 += var26;
/* 130 */             var20 += var28;
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
/*     */   public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte) {
/* 142 */     byte var4 = 64;
/* 143 */     double var5 = 0.03125D;
/* 144 */     this.slowsandNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.slowsandNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var5, var5, 1.0D);
/* 145 */     this.gravelNoise = this.slowsandGravelNoiseGen.generateNoiseOctaves(this.gravelNoise, par1 * 16, 109, par2 * 16, 16, 1, 16, var5, 1.0D, var5);
/* 146 */     this.netherrackExclusivityNoise = this.netherrackExculsivityNoiseGen.generateNoiseOctaves(this.netherrackExclusivityNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var5 * 2.0D, var5 * 2.0D, var5 * 2.0D);
/*     */     
/* 148 */     for (int var7 = 0; var7 < 16; var7++) {
/*     */       
/* 150 */       for (int var8 = 0; var8 < 16; var8++) {
/*     */         
/* 152 */         boolean var9 = (this.slowsandNoise[var7 + var8 * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D);
/* 153 */         boolean var10 = (this.gravelNoise[var7 + var8 * 16] + this.hellRNG.nextDouble() * 0.2D > 0.0D);
/* 154 */         int var11 = (int)(this.netherrackExclusivityNoise[var7 + var8 * 16] / 3.0D + 3.0D + this.hellRNG.nextDouble() * 0.25D);
/* 155 */         int var12 = -1;
/* 156 */         byte var13 = (byte)Block.netherrack.blockID;
/* 157 */         byte var14 = (byte)Block.netherrack.blockID;
/*     */         
/* 159 */         for (int var15 = 127; var15 >= 0; var15--) {
/*     */           
/* 161 */           int var16 = (var8 * 16 + var7) * 128 + var15;
/*     */ 
/*     */           
/* 164 */           if (var15 < 127 - this.hellRNG.nextInt(5) && var15 > 0) {
/*     */             
/* 166 */             byte var17 = par3ArrayOfByte[var16];
/*     */             
/* 168 */             if (var17 == 0)
/*     */             {
/* 170 */               var12 = -1;
/*     */             }
/* 172 */             else if (var17 == Block.netherrack.blockID)
/*     */             {
/* 174 */               if (var12 == -1) {
/*     */                 
/* 176 */                 if (var11 <= 0) {
/*     */                   
/* 178 */                   var13 = 0;
/* 179 */                   var14 = (byte)Block.netherrack.blockID;
/*     */                 }
/* 181 */                 else if (var15 >= var4 - 4 && var15 <= var4 + 1) {
/*     */                   
/* 183 */                   var13 = (byte)Block.netherrack.blockID;
/* 184 */                   var14 = (byte)Block.netherrack.blockID;
/*     */                   
/* 186 */                   if (var10)
/*     */                   {
/* 188 */                     var13 = (byte)Block.gravel.blockID;
/*     */                   }
/*     */                   
/* 191 */                   if (var10)
/*     */                   {
/* 193 */                     var14 = (byte)Block.netherrack.blockID;
/*     */                   }
/*     */                   
/* 196 */                   if (var9)
/*     */                   {
/* 198 */                     var13 = (byte)Block.slowSand.blockID;
/*     */                   }
/*     */                   
/* 201 */                   if (var9)
/*     */                   {
/* 203 */                     var14 = (byte)Block.slowSand.blockID;
/*     */                   }
/*     */                 } 
/*     */                 
/* 207 */                 if (var15 < var4 && var13 == 0)
/*     */                 {
/* 209 */                   var13 = (byte)Block.lavaStill.blockID;
/*     */                 }
/*     */                 
/* 212 */                 var12 = var11;
/*     */                 
/* 214 */                 if (var15 >= var4 - 1)
/*     */                 {
/* 216 */                   par3ArrayOfByte[var16] = var13;
/*     */                 }
/*     */                 else
/*     */                 {
/* 220 */                   par3ArrayOfByte[var16] = var14;
/*     */                 }
/*     */               
/* 223 */               } else if (var12 > 0) {
/*     */                 
/* 225 */                 var12--;
/* 226 */                 par3ArrayOfByte[var16] = var14;
/*     */               }
/*     */             
/*     */             }
/*     */           
/*     */           } else {
/*     */             
/* 233 */             par3ArrayOfByte[var16] = (byte)Block.mantleOrCore.blockID;
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
/*     */   public Chunk loadChunk(int par1, int par2) {
/* 245 */     return provideChunk(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int par1, int par2) {
/* 254 */     if (!this.worldObj.isChunkWithinBlockDomain(par1, par2)) {
/*     */       
/* 256 */       Chunk chunk = new Chunk(this.worldObj, par1, par2);
/*     */       
/* 258 */       chunk.generateHeightMap(false);
/*     */       
/* 260 */       return chunk;
/*     */     } 
/*     */     
/* 263 */     this.hellRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
/* 264 */     byte[] var3 = new byte[32768];
/* 265 */     generateNetherTerrain(par1, par2, var3);
/* 266 */     replaceBlocksForBiome(par1, par2, var3);
/* 267 */     this.netherCaveGenerator.generate(this, this.worldObj, par1, par2, var3);
/* 268 */     this.genNetherBridge.generate(this, this.worldObj, par1, par2, var3);
/* 269 */     Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
/* 270 */     BiomeGenBase[] var5 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
/* 271 */     byte[] var6 = var4.getBiomeArray();
/*     */     
/* 273 */     for (int var7 = 0; var7 < var6.length; var7++)
/*     */     {
/* 275 */       var6[var7] = (byte)(var5[var7]).biomeID;
/*     */     }
/*     */     
/* 278 */     var4.generateHeightMap(false);
/*     */     
/* 280 */     var4.resetRelightChecks();
/* 281 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
/* 290 */     if (par1ArrayOfDouble == null)
/*     */     {
/* 292 */       par1ArrayOfDouble = new double[par5 * par6 * par7];
/*     */     }
/*     */     
/* 295 */     double var8 = 684.412D;
/* 296 */     double var10 = 2053.236D;
/* 297 */     this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
/* 298 */     this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
/* 299 */     this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 60.0D, var8 / 80.0D);
/* 300 */     this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
/* 301 */     this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
/* 302 */     int var12 = 0;
/* 303 */     int var13 = 0;
/* 304 */     double[] var14 = new double[par6];
/*     */     
/*     */     int var15;
/* 307 */     for (var15 = 0; var15 < par6; var15++) {
/*     */       
/* 309 */       var14[var15] = Math.cos(var15 * Math.PI * 6.0D / par6) * 2.0D;
/* 310 */       double var16 = var15;
/*     */       
/* 312 */       if (var15 > par6 / 2)
/*     */       {
/* 314 */         var16 = (par6 - 1 - var15);
/*     */       }
/*     */       
/* 317 */       if (var16 < 4.0D) {
/*     */         
/* 319 */         var16 = 4.0D - var16;
/* 320 */         var14[var15] = var14[var15] - var16 * var16 * var16 * 10.0D;
/*     */       } 
/*     */     } 
/*     */     
/* 324 */     for (var15 = 0; var15 < par5; var15++) {
/*     */       
/* 326 */       for (int var36 = 0; var36 < par7; var36++) {
/*     */         
/* 328 */         double var17 = (this.noiseData4[var13] + 256.0D) / 512.0D;
/*     */         
/* 330 */         if (var17 > 1.0D)
/*     */         {
/* 332 */           var17 = 1.0D;
/*     */         }
/*     */         
/* 335 */         double var19 = 0.0D;
/* 336 */         double var21 = this.noiseData5[var13] / 8000.0D;
/*     */         
/* 338 */         if (var21 < 0.0D)
/*     */         {
/* 340 */           var21 = -var21;
/*     */         }
/*     */         
/* 343 */         var21 = var21 * 3.0D - 3.0D;
/*     */         
/* 345 */         if (var21 < 0.0D) {
/*     */           
/* 347 */           var21 /= 2.0D;
/*     */           
/* 349 */           if (var21 < -1.0D)
/*     */           {
/* 351 */             var21 = -1.0D;
/*     */           }
/*     */           
/* 354 */           var21 /= 1.4D;
/* 355 */           var21 /= 2.0D;
/* 356 */           var17 = 0.0D;
/*     */         }
/*     */         else {
/*     */           
/* 360 */           if (var21 > 1.0D)
/*     */           {
/* 362 */             var21 = 1.0D;
/*     */           }
/*     */           
/* 365 */           var21 /= 6.0D;
/*     */         } 
/*     */         
/* 368 */         var17 += 0.5D;
/* 369 */         var21 = var21 * par6 / 16.0D;
/* 370 */         var13++;
/*     */         
/* 372 */         for (int var23 = 0; var23 < par6; var23++) {
/*     */           
/* 374 */           double var24 = 0.0D;
/* 375 */           double var26 = var14[var23];
/* 376 */           double var28 = this.noiseData2[var12] / 512.0D;
/* 377 */           double var30 = this.noiseData3[var12] / 512.0D;
/* 378 */           double var32 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;
/*     */           
/* 380 */           if (var32 < 0.0D) {
/*     */             
/* 382 */             var24 = var28;
/*     */           }
/* 384 */           else if (var32 > 1.0D) {
/*     */             
/* 386 */             var24 = var30;
/*     */           }
/*     */           else {
/*     */             
/* 390 */             var24 = var28 + (var30 - var28) * var32;
/*     */           } 
/*     */           
/* 393 */           var24 -= var26;
/*     */ 
/*     */           
/* 396 */           if (var23 > par6 - 4) {
/*     */             
/* 398 */             double var34 = ((var23 - par6 - 4) / 3.0F);
/* 399 */             var24 = var24 * (1.0D - var34) + -10.0D * var34;
/*     */           } 
/*     */           
/* 402 */           if (var23 < var19) {
/*     */             
/* 404 */             double var34 = (var19 - var23) / 4.0D;
/*     */             
/* 406 */             if (var34 < 0.0D)
/*     */             {
/* 408 */               var34 = 0.0D;
/*     */             }
/*     */             
/* 411 */             if (var34 > 1.0D)
/*     */             {
/* 413 */               var34 = 1.0D;
/*     */             }
/*     */             
/* 416 */             var24 = var24 * (1.0D - var34) + -10.0D * var34;
/*     */           } 
/*     */           
/* 419 */           par1ArrayOfDouble[var12] = var24;
/* 420 */           var12++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 425 */     return par1ArrayOfDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int par1, int par2) {
/* 433 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/* 438 */     Minecraft.setErrorMessage("getChunkIfItExists: called for ChunkProviderHell");
/* 439 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
/* 448 */     BlockFalling.fallInstantly = true;
/* 449 */     int var4 = par2 * 16;
/* 450 */     int var5 = par3 * 16;
/* 451 */     this.genNetherBridge.generateStructuresInChunk(this.worldObj, this.hellRNG, par2, par3);
/*     */ 
/*     */     
/*     */     int var6;
/*     */ 
/*     */     
/* 457 */     for (var6 = 0; var6 < 8; var6++) {
/*     */       
/* 459 */       int j = var4 + this.hellRNG.nextInt(16) + 8;
/* 460 */       int k = this.hellRNG.nextInt(120) + 4;
/* 461 */       int var9 = var5 + this.hellRNG.nextInt(16) + 8;
/* 462 */       (new WorldGenHellLava(Block.lavaMoving.blockID, false)).generate(this.worldObj, this.hellRNG, j, k, var9);
/*     */     } 
/*     */     
/* 465 */     var6 = this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1) + 1;
/*     */     
/*     */     int var7;
/* 468 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/* 470 */       int j = var4 + this.hellRNG.nextInt(16) + 8;
/* 471 */       int var9 = this.hellRNG.nextInt(120) + 4;
/* 472 */       int var10 = var5 + this.hellRNG.nextInt(16) + 8;
/* 473 */       (new WorldGenFire()).generate(this.worldObj, this.hellRNG, j, var9, var10);
/*     */     } 
/*     */     
/* 476 */     var6 = this.hellRNG.nextInt(this.hellRNG.nextInt(10) + 1);
/*     */     
/* 478 */     for (var7 = 0; var7 < var6; var7++) {
/*     */       
/* 480 */       int j = var4 + this.hellRNG.nextInt(16) + 8;
/* 481 */       int var9 = this.hellRNG.nextInt(120) + 4;
/* 482 */       int var10 = var5 + this.hellRNG.nextInt(16) + 8;
/* 483 */       (new WorldGenGlowStone1()).generate(this.worldObj, this.hellRNG, j, var9, var10);
/*     */     } 
/*     */     
/* 486 */     for (var7 = 0; var7 < 10; var7++) {
/*     */       
/* 488 */       int j = var4 + this.hellRNG.nextInt(16) + 8;
/* 489 */       int var9 = this.hellRNG.nextInt(128);
/* 490 */       int var10 = var5 + this.hellRNG.nextInt(16) + 8;
/* 491 */       (new WorldGenGlowStone2()).generate(this.worldObj, this.hellRNG, j, var9, var10);
/*     */     } 
/*     */     
/* 494 */     if (this.hellRNG.nextInt(1) == 0) {
/*     */       
/* 496 */       var7 = var4 + this.hellRNG.nextInt(16) + 8;
/* 497 */       int j = this.hellRNG.nextInt(128);
/* 498 */       int var9 = var5 + this.hellRNG.nextInt(16) + 8;
/* 499 */       (new WorldGenFlowers(Block.mushroomBrown.blockID)).generate(this.worldObj, this.hellRNG, var7, j, var9);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 510 */     for (int i = 0; i < 16; i++) {
/*     */       
/* 512 */       var7 = var4 + this.hellRNG.nextInt(16) + 8;
/* 513 */       int j = this.hellRNG.nextInt(128);
/* 514 */       int var9 = var5 + this.hellRNG.nextInt(16) + 8;
/* 515 */       (new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.worldObj, this.hellRNG, var7, j, var9);
/*     */     } 
/*     */     
/* 518 */     WorldGenMinable var12 = new WorldGenMinable(Block.oreNetherQuartz.blockID, 13, Block.netherrack.blockID);
/*     */     
/*     */     int var8;
/* 521 */     for (var8 = 0; var8 < 16; var8++) {
/*     */       
/* 523 */       int var9 = var4 + this.hellRNG.nextInt(16);
/* 524 */       int var10 = this.hellRNG.nextInt(108) + 10;
/* 525 */       int var11 = var5 + this.hellRNG.nextInt(16);
/* 526 */       var12.generate(this.worldObj, this.hellRNG, var9, var10, var11);
/*     */     } 
/*     */     
/* 529 */     if (this.worldObj.worldInfo.getEarliestMITEReleaseRunIn() >= 139) {
/*     */       
/* 531 */       var12 = (new WorldGenMinable(Block.oreGold.blockID, 8, Block.netherrack.blockID)).setMinableBlockMetadata(2);
/*     */       
/* 533 */       for (var8 = 0; var8 < 2; var8++) {
/*     */         
/* 535 */         int var9 = var4 + this.hellRNG.nextInt(16);
/* 536 */         int var10 = this.hellRNG.nextInt(108) + 10;
/* 537 */         int var11 = var5 + this.hellRNG.nextInt(16);
/* 538 */         var12.generate(this.worldObj, this.hellRNG, var9, var10, var11);
/*     */       } 
/*     */     } 
/*     */     
/* 542 */     if (this.worldObj.worldInfo.getEarliestMITEReleaseRunIn() >= 0) {
/*     */       
/* 544 */       var12 = (new WorldGenMinable(Block.silverfish.blockID, 8, Block.netherrack.blockID)).setMinableBlockMetadata(3);
/*     */       
/* 546 */       int num_veins = this.hellRNG.nextInt(7) + 2;
/*     */       
/* 548 */       for (var8 = 0; var8 < num_veins; var8++) {
/*     */         
/* 550 */         int var9 = var4 + this.hellRNG.nextInt(16);
/* 551 */         int var10 = this.hellRNG.nextInt(108) + 10;
/* 552 */         int var11 = var5 + this.hellRNG.nextInt(16);
/* 553 */         var12.generate(this.worldObj, this.hellRNG, var9, var10, var11);
/*     */       } 
/*     */     } 
/*     */     
/* 557 */     for (var8 = 0; var8 < 16; var8++) {
/*     */       
/* 559 */       int var9 = var4 + this.hellRNG.nextInt(16);
/* 560 */       int var10 = this.hellRNG.nextInt(108) + 10;
/* 561 */       int var11 = var5 + this.hellRNG.nextInt(16);
/* 562 */       (new WorldGenHellLava(Block.lavaMoving.blockID, true)).generate(this.worldObj, this.hellRNG, var9, var10, var11);
/*     */     } 
/*     */     
/* 565 */     BlockFalling.fallInstantly = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
/* 574 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraData() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unloadQueuedChunks() {
/* 588 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 596 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 604 */     return "HellRandomLevelSource";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/* 612 */     if (par1EnumCreatureType == EnumCreatureType.monster) {
/*     */       
/* 614 */       if (this.genNetherBridge.hasStructureAt(par2, par3, par4))
/*     */       {
/* 616 */         return this.genNetherBridge.getSpawnList();
/*     */       }
/*     */       
/* 619 */       if (this.genNetherBridge.func_142038_b(par2, par3, par4) && this.worldObj.getBlockId(par2, par3 - 1, par4) == Block.netherBrick.blockID)
/*     */       {
/* 621 */         return this.genNetherBridge.getSpawnList();
/*     */       }
/*     */     } 
/*     */     
/* 625 */     BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
/* 626 */     return (var5 == null) ? null : var5.getSpawnableList(par1EnumCreatureType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
/* 634 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 639 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(int par1, int par2) {
/* 644 */     this.genNetherBridge.generate(this, this.worldObj, par1, par2, (byte[])null);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkProviderHell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */