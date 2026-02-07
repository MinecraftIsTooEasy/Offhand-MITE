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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChunkProviderUnderworld
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
/*  42 */   private MapGenBase netherCaveGenerator = new MapGenCavesHell();
/*     */   
/*     */   double[] noiseData1;
/*     */   
/*     */   double[] noiseData2;
/*     */   
/*     */   double[] noiseData3;
/*     */   
/*     */   double[] noiseData4;
/*     */   
/*     */   double[] noiseData5;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_1a;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_1b;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_2;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_3;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_4;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_1a_bump;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_1b_bump;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_1c_bump;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_2_bump;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_3_bump;
/*     */   public static NoiseGeneratorOctaves noise_gen_bedrock_strata_4_bump;
/*  64 */   public static double[] bedrock_strata_1a_noise = new double[256];
/*  65 */   public static double[] bedrock_strata_1b_noise = new double[256];
/*  66 */   public static double[] bedrock_strata_2_noise = new double[256];
/*  67 */   public static double[] bedrock_strata_3_noise = new double[256];
/*  68 */   public static double[] bedrock_strata_4_noise = new double[256];
/*     */   
/*  70 */   public static double[] bedrock_strata_1a_bump_noise = new double[256];
/*  71 */   public static double[] bedrock_strata_1b_bump_noise = new double[256];
/*  72 */   public static double[] bedrock_strata_1c_bump_noise = new double[256];
/*  73 */   public static double[] bedrock_strata_2_bump_noise = new double[256];
/*  74 */   public static double[] bedrock_strata_3_bump_noise = new double[256];
/*  75 */   public static double[] bedrock_strata_4_bump_noise = new double[256];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkProviderUnderworld(World par1World, long par2) {
/*  81 */     this.worldObj = par1World;
/*  82 */     this.hellRNG = new Random(par2);
/*  83 */     this.netherNoiseGen1 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*  84 */     this.netherNoiseGen2 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*  85 */     this.netherNoiseGen3 = new NoiseGeneratorOctaves(this.hellRNG, 8);
/*  86 */     this.slowsandGravelNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  87 */     this.netherrackExculsivityNoiseGen = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  88 */     this.netherNoiseGen6 = new NoiseGeneratorOctaves(this.hellRNG, 10);
/*  89 */     this.netherNoiseGen7 = new NoiseGeneratorOctaves(this.hellRNG, 16);
/*     */ 
/*     */ 
/*     */     
/*  93 */     this; noise_gen_bedrock_strata_1a = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  94 */     this; noise_gen_bedrock_strata_1b = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  95 */     this; noise_gen_bedrock_strata_2 = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  96 */     this; noise_gen_bedrock_strata_3 = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*  97 */     this; noise_gen_bedrock_strata_4 = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*     */     
/*  99 */     this; noise_gen_bedrock_strata_1a_bump = new NoiseGeneratorOctaves(this.hellRNG, 4);
/* 100 */     this; noise_gen_bedrock_strata_1b_bump = new NoiseGeneratorOctaves(this.hellRNG, 4);
/* 101 */     this; noise_gen_bedrock_strata_1c_bump = new NoiseGeneratorOctaves(this.hellRNG, 4);
/* 102 */     this; noise_gen_bedrock_strata_2_bump = new NoiseGeneratorOctaves(this.hellRNG, 4);
/* 103 */     this; noise_gen_bedrock_strata_3_bump = new NoiseGeneratorOctaves(this.hellRNG, 4);
/* 104 */     this; noise_gen_bedrock_strata_4_bump = new NoiseGeneratorOctaves(this.hellRNG, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void generateNetherTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
/* 114 */     byte var4 = 4;
/* 115 */     byte var5 = 32;
/* 116 */     int var6 = var4 + 1;
/* 117 */     byte var7 = 17;
/* 118 */     int var8 = var4 + 1;
/* 119 */     this.noiseField = initializeNoiseField(this.noiseField, par1 * var4, 0, par2 * var4, var6, var7, var8);
/*     */     
/* 121 */     for (int var9 = 0; var9 < var4; var9++) {
/*     */       
/* 123 */       for (int var10 = 0; var10 < var4; var10++) {
/*     */         
/* 125 */         for (int var11 = 0; var11 < 16; var11++) {
/*     */           
/* 127 */           double var12 = 0.125D;
/* 128 */           double var14 = this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
/* 129 */           double var16 = this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
/* 130 */           double var18 = this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
/* 131 */           double var20 = this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
/* 132 */           double var22 = (this.noiseField[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
/* 133 */           double var24 = (this.noiseField[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
/* 134 */           double var26 = (this.noiseField[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
/* 135 */           double var28 = (this.noiseField[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;
/*     */           
/* 137 */           for (int var30 = 0; var30 < 8; var30++) {
/*     */             
/* 139 */             double var31 = 0.25D;
/* 140 */             double var33 = var14;
/* 141 */             double var35 = var16;
/* 142 */             double var37 = (var18 - var14) * var31;
/* 143 */             double var39 = (var20 - var16) * var31;
/*     */             
/* 145 */             for (int var41 = 0; var41 < 4; var41++) {
/*     */               
/* 147 */               int var42 = var41 + var9 * 4 << 11 | 0 + var10 * 4 << 7 | var11 * 8 + var30;
/* 148 */               short var43 = 128;
/* 149 */               double var44 = 0.25D;
/* 150 */               double var46 = var33;
/* 151 */               double var48 = (var35 - var33) * var44;
/*     */               
/* 153 */               for (int var50 = 0; var50 < 4; var50++) {
/*     */                 
/* 155 */                 int var51 = 0;
/*     */ 
/*     */                 
/* 158 */                 if (var11 * 8 + var30 < var5 - 8)
/*     */                 {
/*     */ 
/*     */                   
/* 162 */                   var51 = Block.waterStill.blockID;
/*     */                 }
/*     */                 
/* 165 */                 if (var46 > 0.0D)
/*     */                 {
/*     */                   
/* 168 */                   var51 = Block.stone.blockID;
/*     */                 }
/*     */                 
/* 171 */                 par3ArrayOfByte[var42] = (byte)var51;
/* 172 */                 var42 += var43;
/* 173 */                 var46 += var48;
/*     */               } 
/*     */               
/* 176 */               var33 += var37;
/* 177 */               var35 += var39;
/*     */             } 
/*     */             
/* 180 */             var14 += var22;
/* 181 */             var16 += var24;
/* 182 */             var18 += var26;
/* 183 */             var20 += var28;
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
/* 195 */     if (this.worldObj.underworld_y_offset != 0) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     for (int var7 = 0; var7 < 16; var7++) {
/*     */       
/* 207 */       for (int var8 = 0; var8 < 16; var8++) {
/*     */         
/* 209 */         this.hellRNG.nextDouble();
/* 210 */         this.hellRNG.nextDouble();
/* 211 */         this.hellRNG.nextDouble();
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
/*     */         
/* 223 */         for (int var15 = 127; var15 >= 0; var15--) {
/*     */           
/* 225 */           int var16 = (var8 * 16 + var7) * 128 + var15;
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
/* 236 */           if (var15 >= 127 - this.hellRNG.nextInt(5) || var15 <= 0 + this.hellRNG.nextInt(5))
/*     */           {
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 313 */             par3ArrayOfByte[var16] = (byte)Block.bedrock.blockID;
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
/*     */   public Chunk loadChunk(int par1, int par2) {
/* 326 */     return provideChunk(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int par1, int par2) {
/* 335 */     if (!this.worldObj.isChunkWithinBlockDomain(par1, par2)) {
/*     */       
/* 337 */       Chunk chunk = new Chunk(this.worldObj, par1, par2);
/*     */       
/* 339 */       chunk.generateHeightMap(false);
/*     */       
/* 341 */       return chunk;
/*     */     } 
/*     */     
/* 344 */     this.hellRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
/* 345 */     byte[] var3 = new byte[32768];
/* 346 */     generateNetherTerrain(par1, par2, var3);
/* 347 */     replaceBlocksForBiome(par1, par2, var3);
/*     */ 
/*     */ 
/*     */     
/* 351 */     ChunkProviderGenerate.placeRandomCobwebs(par1, par2, var3, this.hellRNG);
/*     */     
/* 353 */     Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
/* 354 */     BiomeGenBase[] var5 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
/* 355 */     byte[] var6 = var4.getBiomeArray();
/*     */     
/* 357 */     for (int var7 = 0; var7 < var6.length; var7++)
/*     */     {
/* 359 */       var6[var7] = (byte)(var5[var7]).biomeID;
/*     */     }
/*     */     
/* 362 */     var4.generateHeightMap(false);
/*     */     
/* 364 */     var4.resetRelightChecks();
/* 365 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
/* 374 */     if (par1ArrayOfDouble == null)
/*     */     {
/* 376 */       par1ArrayOfDouble = new double[par5 * par6 * par7];
/*     */     }
/*     */     
/* 379 */     double var8 = 684.412D;
/* 380 */     double var10 = 2053.236D;
/* 381 */     this.noiseData4 = this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, par2, par3, par4, par5, 1, par7, 1.0D, 0.0D, 1.0D);
/* 382 */     this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, par2, par3, par4, par5, 1, par7, 100.0D, 0.0D, 100.0D);
/* 383 */     this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 60.0D, var8 / 80.0D);
/* 384 */     this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
/* 385 */     this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
/* 386 */     int var12 = 0;
/* 387 */     int var13 = 0;
/* 388 */     double[] var14 = new double[par6];
/*     */     
/*     */     int var15;
/* 391 */     for (var15 = 0; var15 < par6; var15++) {
/*     */       
/* 393 */       var14[var15] = Math.cos(var15 * Math.PI * 6.0D / par6) * 2.0D;
/* 394 */       double var16 = var15;
/*     */       
/* 396 */       if (var15 > par6 / 2)
/*     */       {
/* 398 */         var16 = (par6 - 1 - var15);
/*     */       }
/*     */       
/* 401 */       if (var16 < 4.0D) {
/*     */         
/* 403 */         var16 = 4.0D - var16;
/* 404 */         var14[var15] = var14[var15] - var16 * var16 * var16 * 10.0D;
/*     */       } 
/*     */     } 
/*     */     
/* 408 */     for (var15 = 0; var15 < par5; var15++) {
/*     */       
/* 410 */       for (int var36 = 0; var36 < par7; var36++) {
/*     */         
/* 412 */         double var17 = (this.noiseData4[var13] + 256.0D) / 512.0D;
/*     */         
/* 414 */         if (var17 > 1.0D)
/*     */         {
/* 416 */           var17 = 1.0D;
/*     */         }
/*     */         
/* 419 */         double var19 = 0.0D;
/* 420 */         double var21 = this.noiseData5[var13] / 8000.0D;
/*     */         
/* 422 */         if (var21 < 0.0D)
/*     */         {
/* 424 */           var21 = -var21;
/*     */         }
/*     */         
/* 427 */         var21 = var21 * 3.0D - 3.0D;
/*     */         
/* 429 */         if (var21 < 0.0D) {
/*     */           
/* 431 */           var21 /= 2.0D;
/*     */           
/* 433 */           if (var21 < -1.0D)
/*     */           {
/* 435 */             var21 = -1.0D;
/*     */           }
/*     */           
/* 438 */           var21 /= 1.4D;
/* 439 */           var21 /= 2.0D;
/* 440 */           var17 = 0.0D;
/*     */         }
/*     */         else {
/*     */           
/* 444 */           if (var21 > 1.0D)
/*     */           {
/* 446 */             var21 = 1.0D;
/*     */           }
/*     */           
/* 449 */           var21 /= 6.0D;
/*     */         } 
/*     */         
/* 452 */         var17 += 0.5D;
/* 453 */         var21 = var21 * par6 / 16.0D;
/* 454 */         var13++;
/*     */         
/* 456 */         for (int var23 = 0; var23 < par6; var23++) {
/*     */           
/* 458 */           double var24 = 0.0D;
/* 459 */           double var26 = var14[var23];
/* 460 */           double var28 = this.noiseData2[var12] / 512.0D;
/* 461 */           double var30 = this.noiseData3[var12] / 512.0D;
/* 462 */           double var32 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;
/*     */           
/* 464 */           if (var32 < 0.0D) {
/*     */             
/* 466 */             var24 = var28;
/*     */           }
/* 468 */           else if (var32 > 1.0D) {
/*     */             
/* 470 */             var24 = var30;
/*     */           }
/*     */           else {
/*     */             
/* 474 */             var24 = var28 + (var30 - var28) * var32;
/*     */           } 
/*     */           
/* 477 */           var24 -= var26;
/*     */ 
/*     */           
/* 480 */           if (var23 > par6 - 4) {
/*     */             
/* 482 */             double var34 = ((var23 - par6 - 4) / 3.0F);
/* 483 */             var24 = var24 * (1.0D - var34) + -10.0D * var34;
/*     */           } 
/*     */           
/* 486 */           if (var23 < var19) {
/*     */             
/* 488 */             double var34 = (var19 - var23) / 4.0D;
/*     */             
/* 490 */             if (var34 < 0.0D)
/*     */             {
/* 492 */               var34 = 0.0D;
/*     */             }
/*     */             
/* 495 */             if (var34 > 1.0D)
/*     */             {
/* 497 */               var34 = 1.0D;
/*     */             }
/*     */             
/* 500 */             var24 = var24 * (1.0D - var34) + -10.0D * var34;
/*     */           } 
/*     */           
/* 503 */           par1ArrayOfDouble[var12] = var24;
/* 504 */           var12++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 509 */     return par1ArrayOfDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int par1, int par2) {
/* 517 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/* 522 */     Minecraft.setErrorMessage("getChunkIfItExists: called for ChunkProviderUnderworld");
/* 523 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
/* 532 */     BlockFalling.fallInstantly = true;
/* 533 */     int var4 = par2 * 16;
/* 534 */     int var5 = par3 * 16;
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
/*     */     
/* 626 */     if (this.worldObj.underworld_y_offset != 0)
/*     */     {
/* 628 */       for (int i = 0; i < 16; i++) {
/*     */         
/* 630 */         int x = var4 + this.hellRNG.nextInt(16) + 8;
/* 631 */         int y = this.hellRNG.nextInt(32) + 20 + this.worldObj.underworld_y_offset;
/* 632 */         int z = var5 + this.hellRNG.nextInt(16) + 8;
/*     */         
/* 634 */         (new WorldGenDungeons()).generate(this.worldObj, this.hellRNG, x, y, z);
/*     */       } 
/*     */     }
/*     */     
/* 638 */     BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
/* 639 */     biome.decorate(this.worldObj, this.worldObj.rand, var4, var5);
/*     */     
/* 641 */     BlockFalling.fallInstantly = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
/* 650 */     return true;
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
/* 664 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 672 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 680 */     return "UnderworldRandomLevelSource";
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/* 701 */     BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
/* 702 */     return (var5 == null) ? null : var5.getSpawnableList(par1EnumCreatureType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
/* 710 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 715 */     return 0;
/*     */   }
/*     */   
/*     */   public void recreateStructures(int par1, int par2) {}
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkProviderUnderworld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */