/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Random;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class ChunkProviderGenerate
/*      */   implements IChunkProvider
/*      */ {
/*      */   private Random rand;
/*      */   private NoiseGeneratorOctaves noiseGen1;
/*      */   private NoiseGeneratorOctaves noiseGen2;
/*      */   private NoiseGeneratorOctaves noiseGen3;
/*      */   private NoiseGeneratorOctaves noiseGen4;
/*      */   public NoiseGeneratorOctaves noiseGen5;
/*      */   public NoiseGeneratorOctaves noiseGen6;
/*      */   public NoiseGeneratorOctaves mobSpawnerNoise;
/*      */   private World worldObj;
/*      */   private final boolean mapFeaturesEnabled;
/*      */   private double[] noiseArray;
/*   41 */   private double[] stoneNoise = new double[256];
/*   42 */   private MapGenBase caveGenerator = new MapGenCaves();
/*      */ 
/*      */   
/*   45 */   private MapGenStronghold strongholdGenerator = new MapGenStronghold();
/*      */ 
/*      */   
/*   48 */   private MapGenVillage villageGenerator = new MapGenVillage();
/*      */ 
/*      */   
/*   51 */   private MapGenMineshaft mineshaftGenerator = new MapGenMineshaft();
/*   52 */   private MapGenScatteredFeature scatteredFeatureGenerator = new MapGenScatteredFeature();
/*      */ 
/*      */   
/*   55 */   private MapGenBase ravineGenerator = new MapGenRavine();
/*      */   
/*   57 */   private MapGenCaveNetwork cave_network_generator = new MapGenCaveNetwork();
/*      */ 
/*      */   
/*      */   private BiomeGenBase[] biomesForGeneration;
/*      */ 
/*      */   
/*      */   double[] noise3;
/*      */ 
/*      */   
/*      */   double[] noise1;
/*      */ 
/*      */   
/*      */   double[] noise2;
/*      */ 
/*      */   
/*      */   double[] noise5;
/*      */ 
/*      */   
/*      */   double[] noise6;
/*      */ 
/*      */   
/*      */   float[] parabolicField;
/*      */ 
/*      */   
/*   81 */   int[][] field_73219_j = new int[32][32];
/*      */   
/*      */   private NoiseGeneratorOctaves noiseGen8;
/*      */   
/*   85 */   private double[] stone_noise_2 = new double[256];
/*   86 */   private double[] stone_noise_3 = new double[256];
/*      */ 
/*      */   
/*      */   public ChunkProviderGenerate(World par1World, long par2, boolean par4) {
/*   90 */     this.worldObj = par1World;
/*   91 */     this.mapFeaturesEnabled = par4;
/*   92 */     this.rand = new Random(par2);
/*   93 */     this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
/*   94 */     this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
/*   95 */     this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
/*   96 */     this.noiseGen4 = new NoiseGeneratorOctaves(this.rand, 4);
/*   97 */     this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
/*   98 */     this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
/*   99 */     this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
/*      */     
/*  101 */     this.noiseGen8 = new NoiseGeneratorOctaves(this.rand, 4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte) {
/*  110 */     byte var4 = 4;
/*  111 */     byte var5 = 16;
/*  112 */     byte var6 = 63;
/*  113 */     int var7 = var4 + 1;
/*  114 */     byte var8 = 17;
/*  115 */     int var9 = var4 + 1;
/*  116 */     this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, par1 * 4 - 2, par2 * 4 - 2, var7 + 5, var9 + 5);
/*  117 */     this.noiseArray = initializeNoiseField(this.noiseArray, par1 * var4, 0, par2 * var4, var7, var8, var9);
/*      */     
/*  119 */     for (int var10 = 0; var10 < var4; var10++) {
/*      */       
/*  121 */       for (int var11 = 0; var11 < var4; var11++) {
/*      */         
/*  123 */         for (int var12 = 0; var12 < var5; var12++) {
/*      */           
/*  125 */           double var13 = 0.125D;
/*  126 */           double var15 = this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 0];
/*  127 */           double var17 = this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 0];
/*  128 */           double var19 = this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 0];
/*  129 */           double var21 = this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 0];
/*  130 */           double var23 = (this.noiseArray[((var10 + 0) * var9 + var11 + 0) * var8 + var12 + 1] - var15) * var13;
/*  131 */           double var25 = (this.noiseArray[((var10 + 0) * var9 + var11 + 1) * var8 + var12 + 1] - var17) * var13;
/*  132 */           double var27 = (this.noiseArray[((var10 + 1) * var9 + var11 + 0) * var8 + var12 + 1] - var19) * var13;
/*  133 */           double var29 = (this.noiseArray[((var10 + 1) * var9 + var11 + 1) * var8 + var12 + 1] - var21) * var13;
/*      */           
/*  135 */           for (int var31 = 0; var31 < 8; var31++) {
/*      */             
/*  137 */             double var32 = 0.25D;
/*  138 */             double var34 = var15;
/*  139 */             double var36 = var17;
/*  140 */             double var38 = (var19 - var15) * var32;
/*  141 */             double var40 = (var21 - var17) * var32;
/*      */             
/*  143 */             for (int var42 = 0; var42 < 4; var42++) {
/*      */               
/*  145 */               int var43 = var42 + var10 * 4 << 11 | 0 + var11 * 4 << 7 | var12 * 8 + var31;
/*  146 */               short var44 = 128;
/*  147 */               var43 -= var44;
/*  148 */               double var45 = 0.25D;
/*  149 */               double var49 = (var36 - var34) * var45;
/*  150 */               double var47 = var34 - var49;
/*      */               
/*  152 */               for (int var51 = 0; var51 < 4; var51++) {
/*      */                 
/*  154 */                 if ((var47 += var49) > 0.0D) {
/*      */                   
/*  156 */                   par3ArrayOfByte[var43 += var44] = (byte)Block.stone.blockID;
/*      */                 }
/*  158 */                 else if (var12 * 8 + var31 < var6) {
/*      */                   
/*  160 */                   par3ArrayOfByte[var43 += var44] = (byte)Block.waterStill.blockID;
/*      */                 }
/*      */                 else {
/*      */                   
/*  164 */                   par3ArrayOfByte[var43 += var44] = 0;
/*      */                 } 
/*      */               } 
/*      */               
/*  168 */               var34 += var38;
/*  169 */               var36 += var40;
/*      */             } 
/*      */             
/*  172 */             var15 += var23;
/*  173 */             var17 += var25;
/*  174 */             var19 += var27;
/*  175 */             var21 += var29;
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void placeRandomCobwebs(int chunk_x, int chunk_z, byte[] block_ids, Random rand) {
/*  276 */     int random_number_index = rand.nextInt();
/*      */     
/*  278 */     byte web_block_id = (byte)Block.web.blockID;
/*  279 */     byte lava_still_block_id = (byte)Block.lavaStill.blockID;
/*  280 */     byte lava_moving_block_id = (byte)Block.lavaMoving.blockID;
/*  281 */     byte stone_block_id = (byte)Block.stone.blockID;
/*      */     
/*  283 */     int frequency = 128;
/*      */     
/*  285 */     for (int attempts = 0; attempts < frequency; attempts++) {
/*      */       
/*  287 */       int x = RNG.int_14_plus_1[++random_number_index & 0x7FFF];
/*  288 */       int y = RNG.int_126_plus_1[++random_number_index & 0x7FFF];
/*  289 */       int z = RNG.int_14_plus_1[++random_number_index & 0x7FFF];
/*      */       
/*  291 */       int index = (z * 16 + x) * 128 + y;
/*      */       
/*  293 */       if (block_ids[index] == 0) {
/*      */ 
/*      */         
/*  296 */         int block_id_above = block_ids[index + 1];
/*  297 */         int block_id_below = block_ids[index - 1];
/*  298 */         int block_id_front = block_ids[index + 128];
/*  299 */         int block_id_back = block_ids[index - 128];
/*  300 */         int block_id_right = block_ids[index + 2048];
/*  301 */         int block_id_left = block_ids[index - 2048];
/*      */         
/*  303 */         int solid_face_adjacent_blocks = 0;
/*      */         
/*  305 */         if (block_id_above != 0) {
/*  306 */           solid_face_adjacent_blocks++;
/*      */         }
/*  308 */         if (block_id_below != 0) {
/*  309 */           solid_face_adjacent_blocks++;
/*      */         }
/*  311 */         if (block_id_front != 0) {
/*  312 */           solid_face_adjacent_blocks++;
/*      */         }
/*  314 */         if (block_id_back != 0) {
/*  315 */           solid_face_adjacent_blocks++;
/*      */         }
/*  317 */         if (block_id_right != 0) {
/*  318 */           solid_face_adjacent_blocks++;
/*      */         }
/*  320 */         if (block_id_left != 0) {
/*  321 */           solid_face_adjacent_blocks++;
/*      */         }
/*  323 */         if (solid_face_adjacent_blocks >= 4)
/*      */         {
/*      */           
/*  326 */           if (block_id_below != lava_still_block_id && block_id_below != lava_moving_block_id)
/*      */           {
/*      */             
/*  329 */             if (block_id_above == stone_block_id || block_id_below == stone_block_id || block_id_front == stone_block_id || block_id_back == stone_block_id || block_id_right == stone_block_id || block_id_left == stone_block_id) {
/*      */ 
/*      */               
/*  332 */               block_ids[index] = web_block_id;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*  362 */               attempts -= frequency * 4;
/*      */             } 
/*      */           }
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
/*  371 */     boolean prevent_pockmarking = (this.worldObj.worldInfo.getEarliestMITEReleaseRunIn() >= 165);
/*  372 */     boolean use_improved_stone_exposing = (this.worldObj.worldInfo.getEarliestMITEReleaseRunIn() >= 168);
/*      */     
/*  374 */     byte var5 = 63;
/*  375 */     double var6 = 0.03125D;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  380 */     if (use_improved_stone_exposing) {
/*      */       
/*  382 */       this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);
/*  383 */       this.stone_noise_2 = this.noiseGen4.generateNoiseOctaves(this.stone_noise_2, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 16.0D, var6 * 16.0D, var6 * 16.0D);
/*  384 */       this.stone_noise_3 = this.noiseGen8.generateNoiseOctaves(this.stone_noise_3, par1 * 16, 0, par2 * 16, 16, 1, 16, var6 * 32.0D, var6 * 32.0D, var6 * 32.0D);
/*      */     }
/*      */     else {
/*      */       
/*  388 */       this.stoneNoise = this.noiseGen4.generateNoiseOctaves(this.stoneNoise, par1 * 16, par2 * 16, 0, 16, 16, 1, var6 * 2.0D, var6 * 2.0D, var6 * 2.0D);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  393 */     for (int var8 = 0; var8 < 16; var8++) {
/*      */       
/*  395 */       for (int var9 = 0; var9 < 16; var9++) {
/*      */         double var12_double;
/*  397 */         BiomeGenBase var10 = par4ArrayOfBiomeGenBase[var9 + var8 * 16];
/*  398 */         float var11 = var10.getFloatTemperature();
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  403 */         int local_xz_index = var8 + var9 * 16;
/*      */ 
/*      */ 
/*      */         
/*  407 */         if (use_improved_stone_exposing) {
/*  408 */           var12_double = this.stoneNoise[local_xz_index] / 3.0D + 3.0D - 0.5D;
/*      */         } else {
/*  410 */           var12_double = this.stoneNoise[local_xz_index] / 3.0D + 3.0D + this.rand.nextDouble() * 0.25D;
/*      */         } 
/*  412 */         int var12 = (int)var12_double;
/*      */ 
/*      */ 
/*      */         
/*  416 */         int var13 = -1;
/*  417 */         byte var14 = var10.topBlock;
/*  418 */         byte var15 = var10.fillerBlock;
/*      */         
/*  420 */         int threshold = use_improved_stone_exposing ? (int)(this.stone_noise_2[local_xz_index] / 3.0D + 1.0D) : 0;
/*      */         
/*  422 */         for (int var16 = 127; var16 >= 0; var16--) {
/*      */           
/*  424 */           int var17 = (var9 * 16 + var8) * 128 + var16;
/*      */           
/*  426 */           if (var16 <= 0 + this.rand.nextInt(5)) {
/*      */             
/*  428 */             par3ArrayOfByte[var17] = (byte)Block.bedrock.blockID;
/*      */           }
/*      */           else {
/*      */             
/*  432 */             byte var18 = par3ArrayOfByte[var17];
/*      */             
/*  434 */             if (var18 == 0) {
/*      */               
/*  436 */               var13 = -1;
/*      */             }
/*  438 */             else if (var18 == Block.stone.blockID) {
/*      */               
/*  440 */               if (var13 == -1) {
/*      */                 
/*  442 */                 boolean above_water_table = (var16 >= var5);
/*      */                 
/*  444 */                 if (var12 <= 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  449 */                   if (use_improved_stone_exposing) {
/*      */                     
/*  451 */                     var14 = (var12_double < 0.25D) ? 0 : ((threshold <= 0 && above_water_table) ? var10.topBlock : 0);
/*      */                     
/*  453 */                     if (var14 == 0 && above_water_table)
/*      */                     {
/*  455 */                       if (var12_double > 0.7D)
/*      */                       {
/*  457 */                         if (var12_double > 0.95D) {
/*  458 */                           var14 = var10.topBlock;
/*  459 */                         } else if (this.stone_noise_3[local_xz_index] < 1.0D) {
/*  460 */                           var14 = (byte)Block.stone.blockID;
/*      */                         } 
/*      */                       }
/*      */                     }
/*      */                   } else {
/*      */                     
/*  466 */                     var14 = prevent_pockmarking ? var10.topBlock : 0;
/*      */                   } 
/*      */                   
/*  469 */                   var15 = (byte)Block.stone.blockID;
/*      */                 }
/*  471 */                 else if (var16 >= var5 - 4 && var16 <= var5 + 1) {
/*      */                   
/*  473 */                   var14 = var10.topBlock;
/*  474 */                   var15 = var10.fillerBlock;
/*      */                 } 
/*      */ 
/*      */                 
/*  478 */                 if (!above_water_table && var14 == 0)
/*      */                 {
/*  480 */                   if (var11 < 0.15F) {
/*      */                     
/*  482 */                     var14 = (byte)Block.ice.blockID;
/*      */                   }
/*      */                   else {
/*      */                     
/*  486 */                     var14 = (byte)Block.waterStill.blockID;
/*      */                   } 
/*      */                 }
/*      */                 
/*  490 */                 var13 = var12;
/*      */                 
/*  492 */                 if (var16 >= var5 - 1)
/*      */                 {
/*  494 */                   par3ArrayOfByte[var17] = var14;
/*      */                 }
/*      */                 else
/*      */                 {
/*  498 */                   par3ArrayOfByte[var17] = var15;
/*      */                 }
/*      */               
/*  501 */               } else if (var13 > 0) {
/*      */                 
/*  503 */                 var13--;
/*  504 */                 par3ArrayOfByte[var17] = var15;
/*      */                 
/*  506 */                 if (var13 == 0 && var15 == Block.sand.blockID) {
/*      */                   
/*  508 */                   var13 = this.rand.nextInt(4);
/*  509 */                   var15 = (byte)Block.sandStone.blockID;
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk loadChunk(int par1, int par2) {
/*  524 */     return provideChunk(par1, par2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk provideChunk(int par1, int par2) {
/*  533 */     if (!this.worldObj.isChunkWithinBlockDomain(par1, par2)) {
/*      */       
/*  535 */       Chunk chunk = new Chunk(this.worldObj, par1, par2);
/*      */       
/*  537 */       chunk.generateSkylightMap(true);
/*      */       
/*  539 */       return chunk;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  546 */     this.rand.setSeed(par1 * 341873128712L + par2 * 132897987541L);
/*  547 */     byte[] var3 = new byte[32768];
/*      */     
/*  549 */     generateTerrain(par1, par2, var3);
/*  550 */     this.biomesForGeneration = this.worldObj.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
/*  551 */     replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
/*  552 */     this.caveGenerator.generate(this, this.worldObj, par1, par2, var3);
/*  553 */     this.ravineGenerator.generate(this, this.worldObj, par1, par2, var3);
/*      */     
/*  555 */     if (this.worldObj.worldInfo.getEarliestMITEReleaseRunIn() >= 139) {
/*  556 */       this.cave_network_generator.generate(this, this.worldObj, par1, par2, var3);
/*      */     }
/*  558 */     placeRandomCobwebs(par1, par2, var3, this.rand);
/*      */     
/*  560 */     if (this.mapFeaturesEnabled) {
/*      */       
/*  562 */       this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, var3);
/*  563 */       this.villageGenerator.generate(this, this.worldObj, par1, par2, var3);
/*  564 */       this.strongholdGenerator.generate(this, this.worldObj, par1, par2, var3);
/*  565 */       this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, var3);
/*      */     } 
/*      */     
/*  568 */     if (this.worldObj.pending_sand_falls != null && this.worldObj.worldInfo.getEarliestMITEReleaseRunIn() >= 189) {
/*      */       
/*  570 */       performSandFalls(this.worldObj.pending_sand_falls, var3);
/*  571 */       this.worldObj.pending_sand_falls = null;
/*      */     } 
/*      */     
/*  574 */     Chunk var4 = new Chunk(this.worldObj, var3, par1, par2);
/*  575 */     byte[] var5 = var4.getBiomeArray();
/*      */     
/*  577 */     for (int var6 = 0; var6 < var5.length; var6++)
/*      */     {
/*  579 */       var5[var6] = (byte)(this.biomesForGeneration[var6]).biomeID;
/*      */     }
/*      */ 
/*      */     
/*  583 */     var4.generateSkylightMap(true);
/*      */     
/*  585 */     if (this.worldObj.pending_sand_falls != null) {
/*      */       
/*  587 */       var4.pending_sand_falls = this.worldObj.pending_sand_falls;
/*  588 */       this.worldObj.pending_sand_falls = null;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  596 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
/*  605 */     if (par1ArrayOfDouble == null)
/*      */     {
/*  607 */       par1ArrayOfDouble = new double[par5 * par6 * par7];
/*      */     }
/*      */     
/*  610 */     if (this.parabolicField == null) {
/*      */       
/*  612 */       this.parabolicField = new float[25];
/*      */       
/*  614 */       for (int var8 = -2; var8 <= 2; var8++) {
/*      */         
/*  616 */         for (int var9 = -2; var9 <= 2; var9++) {
/*      */           
/*  618 */           float var10 = 10.0F / MathHelper.sqrt_float((var8 * var8 + var9 * var9) + 0.2F);
/*  619 */           this.parabolicField[var8 + 2 + (var9 + 2) * 5] = var10;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  624 */     double var44 = 684.412D;
/*  625 */     double var45 = 684.412D;
/*  626 */     this.noise5 = this.noiseGen5.generateNoiseOctaves(this.noise5, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
/*  627 */     this.noise6 = this.noiseGen6.generateNoiseOctaves(this.noise6, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
/*  628 */     this.noise3 = this.noiseGen3.generateNoiseOctaves(this.noise3, par2, par3, par4, par5, par6, par7, var44 / 80.0D, var45 / 160.0D, var44 / 80.0D);
/*  629 */     this.noise1 = this.noiseGen1.generateNoiseOctaves(this.noise1, par2, par3, par4, par5, par6, par7, var44, var45, var44);
/*  630 */     this.noise2 = this.noiseGen2.generateNoiseOctaves(this.noise2, par2, par3, par4, par5, par6, par7, var44, var45, var44);
/*  631 */     boolean var43 = false;
/*  632 */     boolean var42 = false;
/*  633 */     int var12 = 0;
/*  634 */     int var13 = 0;
/*      */     
/*  636 */     for (int var14 = 0; var14 < par5; var14++) {
/*      */       
/*  638 */       for (int var15 = 0; var15 < par7; var15++) {
/*      */         
/*  640 */         float var16 = 0.0F;
/*  641 */         float var17 = 0.0F;
/*  642 */         float var18 = 0.0F;
/*  643 */         byte var19 = 2;
/*  644 */         BiomeGenBase var20 = this.biomesForGeneration[var14 + 2 + (var15 + 2) * (par5 + 5)];
/*      */         
/*  646 */         for (int var21 = -var19; var21 <= var19; var21++) {
/*      */           
/*  648 */           for (int var22 = -var19; var22 <= var19; var22++) {
/*      */             
/*  650 */             BiomeGenBase var23 = this.biomesForGeneration[var14 + var21 + 2 + (var15 + var22 + 2) * (par5 + 5)];
/*  651 */             float var24 = this.parabolicField[var21 + 2 + (var22 + 2) * 5] / (var23.minHeight + 2.0F);
/*      */             
/*  653 */             if (var23.minHeight > var20.minHeight)
/*      */             {
/*  655 */               var24 /= 2.0F;
/*      */             }
/*      */             
/*  658 */             var16 += var23.maxHeight * var24;
/*  659 */             var17 += var23.minHeight * var24;
/*  660 */             var18 += var24;
/*      */           } 
/*      */         } 
/*      */         
/*  664 */         var16 /= var18;
/*  665 */         var17 /= var18;
/*  666 */         var16 = var16 * 0.9F + 0.1F;
/*  667 */         var17 = (var17 * 4.0F - 1.0F) / 8.0F;
/*  668 */         double var47 = this.noise6[var13] / 8000.0D;
/*      */         
/*  670 */         if (var47 < 0.0D)
/*      */         {
/*  672 */           var47 = -var47 * 0.3D;
/*      */         }
/*      */         
/*  675 */         var47 = var47 * 3.0D - 2.0D;
/*      */         
/*  677 */         if (var47 < 0.0D) {
/*      */           
/*  679 */           var47 /= 2.0D;
/*      */           
/*  681 */           if (var47 < -1.0D)
/*      */           {
/*  683 */             var47 = -1.0D;
/*      */           }
/*      */           
/*  686 */           var47 /= 1.4D;
/*  687 */           var47 /= 2.0D;
/*      */         }
/*      */         else {
/*      */           
/*  691 */           if (var47 > 1.0D)
/*      */           {
/*  693 */             var47 = 1.0D;
/*      */           }
/*      */           
/*  696 */           var47 /= 8.0D;
/*      */         } 
/*      */         
/*  699 */         var13++;
/*      */         
/*  701 */         for (int var46 = 0; var46 < par6; var46++) {
/*      */           
/*  703 */           double var48 = var17;
/*  704 */           double var26 = var16;
/*  705 */           var48 += var47 * 0.2D;
/*  706 */           var48 = var48 * par6 / 16.0D;
/*  707 */           double var28 = par6 / 2.0D + var48 * 4.0D;
/*  708 */           double var30 = 0.0D;
/*  709 */           double var32 = (var46 - var28) * 12.0D * 128.0D / 128.0D / var26;
/*      */           
/*  711 */           if (var32 < 0.0D)
/*      */           {
/*  713 */             var32 *= 4.0D;
/*      */           }
/*      */           
/*  716 */           double var34 = this.noise1[var12] / 512.0D;
/*  717 */           double var36 = this.noise2[var12] / 512.0D;
/*  718 */           double var38 = (this.noise3[var12] / 10.0D + 1.0D) / 2.0D;
/*      */           
/*  720 */           if (var38 < 0.0D) {
/*      */             
/*  722 */             var30 = var34;
/*      */           }
/*  724 */           else if (var38 > 1.0D) {
/*      */             
/*  726 */             var30 = var36;
/*      */           }
/*      */           else {
/*      */             
/*  730 */             var30 = var34 + (var36 - var34) * var38;
/*      */           } 
/*      */           
/*  733 */           var30 -= var32;
/*      */           
/*  735 */           if (var46 > par6 - 4) {
/*      */             
/*  737 */             double var40 = ((var46 - par6 - 4) / 3.0F);
/*  738 */             var30 = var30 * (1.0D - var40) + -10.0D * var40;
/*      */           } 
/*      */           
/*  741 */           par1ArrayOfDouble[var12] = var30;
/*  742 */           var12++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  747 */     return par1ArrayOfDouble;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean chunkExists(int par1, int par2) {
/*  755 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/*  760 */     Minecraft.setErrorMessage("getChunkIfItExists: called for ChunkProviderGenerate");
/*  761 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
/*  769 */     BlockFalling.fallInstantly = true;
/*  770 */     int var4 = par2 * 16;
/*  771 */     int var5 = par3 * 16;
/*  772 */     BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
/*  773 */     this.rand.setSeed(this.worldObj.getSeed());
/*  774 */     long var7 = this.rand.nextLong() / 2L * 2L + 1L;
/*  775 */     long var9 = this.rand.nextLong() / 2L * 2L + 1L;
/*  776 */     this.rand.setSeed(par2 * var7 + par3 * var9 ^ this.worldObj.getSeed());
/*  777 */     boolean var11 = false;
/*      */     
/*  779 */     if (this.mapFeaturesEnabled) {
/*      */       
/*  781 */       this.mineshaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
/*  782 */       var11 = this.villageGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
/*  783 */       this.strongholdGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
/*  784 */       this.scatteredFeatureGenerator.generateStructuresInChunk(this.worldObj, this.rand, par2, par3);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  811 */     for (int i = 0; i < 4; i++) {
/*      */       
/*  813 */       if (!var11 && this.rand.nextInt(6) == 0) {
/*      */         
/*  815 */         int liquid_block_id, k = var4 + this.rand.nextInt(16) + 8;
/*  816 */         int var13 = this.rand.nextInt(128);
/*  817 */         int var14 = var5 + this.rand.nextInt(16) + 8;
/*      */         
/*  819 */         for (int j = 1; j < i; j++) {
/*  820 */           if (var13 > 16) {
/*  821 */             var13 = this.rand.nextInt(var13);
/*      */           }
/*      */         } 
/*      */         
/*  825 */         if (Math.random() * 32.0D >= (var13 - 16)) {
/*      */           
/*  827 */           if (this.rand.nextInt(20) == 0) {
/*  828 */             liquid_block_id = Block.waterStill.blockID;
/*      */           } else {
/*  830 */             liquid_block_id = Block.lavaStill.blockID;
/*      */           } 
/*  832 */         } else if (var6 != BiomeGenBase.desert && var6 != BiomeGenBase.desertHills) {
/*      */           
/*  834 */           liquid_block_id = Block.waterStill.blockID;
/*      */         } else {
/*      */           continue;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  841 */         (new WorldGenLakes(liquid_block_id)).generate(this.worldObj, this.rand, k, var13, var14);
/*      */       }  continue;
/*      */     } 
/*      */     int var12;
/*  845 */     for (var12 = 0; var12 < 8; var12++) {
/*      */       
/*  847 */       int var13 = var4 + this.rand.nextInt(16) + 8;
/*  848 */       int var14 = this.rand.nextInt(128);
/*  849 */       int var15 = var5 + this.rand.nextInt(16) + 8;
/*      */       
/*  851 */       (new WorldGenDungeons()).generate(this.worldObj, this.rand, var13, var14, var15);
/*      */     } 
/*      */     
/*  854 */     var6.decorate(this.worldObj, this.rand, var4, var5);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  859 */     var4 += 8;
/*  860 */     var5 += 8;
/*      */     
/*  862 */     for (var12 = 0; var12 < 16; var12++) {
/*      */       
/*  864 */       for (int var13 = 0; var13 < 16; var13++) {
/*      */         
/*  866 */         int var14 = this.worldObj.getPrecipitationHeight(var4 + var12, var5 + var13);
/*      */         
/*  868 */         if (this.worldObj.isBlockFreezable(var12 + var4, var14 - 1, var13 + var5))
/*      */         {
/*  870 */           this.worldObj.setBlock(var12 + var4, var14 - 1, var13 + var5, Block.ice.blockID, 0, 2);
/*      */         }
/*      */         
/*  873 */         if (var14 > 63 && this.worldObj.isAirBlock(var12 + var4, 63, var13 + var5) && this.worldObj.isBlockFreezable(var12 + var4, 62, var13 + var5))
/*      */         {
/*  875 */           this.worldObj.setBlock(var12 + var4, 62, var13 + var5, Block.ice.blockID, 0, 2);
/*      */         }
/*      */         
/*  878 */         if (this.worldObj.canSnowAt(var12 + var4, var14, var13 + var5))
/*      */         {
/*  880 */           this.worldObj.setBlock(var12 + var4, var14, var13 + var5, Block.snow.blockID, 0, 2);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  885 */     SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, EnumCreatureType.animal, var4, var5, 16, 16, this.rand);
/*  886 */     SpawnerAnimals.performWorldGenSpawning(this.worldObj, var6, EnumCreatureType.aquatic, var4, var5, 16, 16, this.rand);
/*      */     
/*  888 */     BlockFalling.fallInstantly = false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
/*  897 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveExtraData() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean unloadQueuedChunks() {
/*  911 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean canSave() {
/*  919 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String makeString() {
/*  927 */     return "RandomLevelSource";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/*  935 */     BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
/*  936 */     return (var5 == null) ? null : ((par1EnumCreatureType == EnumCreatureType.monster && this.scatteredFeatureGenerator.func_143030_a(par2, par3, par4)) ? this.scatteredFeatureGenerator.getScatteredFeatureSpawnList() : var5.getSpawnableList(par1EnumCreatureType));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
/*  944 */     return ("Stronghold".equals(par2Str) && this.strongholdGenerator != null) ? this.strongholdGenerator.getNearestInstance(par1World, par3, par4, par5) : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getLoadedChunkCount() {
/*  949 */     return 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void recreateStructures(int par1, int par2) {
/*  954 */     if (this.mapFeaturesEnabled) {
/*      */       
/*  956 */       this.mineshaftGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
/*  957 */       this.villageGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
/*  958 */       this.strongholdGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
/*  959 */       this.scatteredFeatureGenerator.generate(this, this.worldObj, par1, par2, (byte[])null);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public MapGenCaveNetwork getMapGenCaveNetwork() {
/*  965 */     return this.cave_network_generator;
/*      */   }
/*      */ 
/*      */   
/*      */   private void performSandFalls(HashMap pending_sand_falls, byte[] block_ids) {
/*  970 */     byte block_id_sand = (byte)Block.sand.blockID;
/*  971 */     byte block_id_web = (byte)Block.web.blockID;
/*      */     
/*  973 */     Iterator<Map.Entry> i = pending_sand_falls.entrySet().iterator();
/*      */     
/*  975 */     while (i.hasNext()) {
/*      */       
/*  977 */       Map.Entry entry = i.next();
/*      */       
/*  979 */       int xz_index = ((Integer)entry.getKey()).intValue();
/*  980 */       int y = ((Integer)entry.getValue()).intValue();
/*      */       
/*  982 */       int local_x = xz_index % 16;
/*  983 */       int local_z = xz_index / 16;
/*      */       
/*  985 */       int index_at_y_equals_0 = local_x << 11 | local_z << 7;
/*  986 */       int index = index_at_y_equals_0 | y;
/*      */       
/*  988 */       if (block_ids[index] == block_id_sand) {
/*      */         
/*  990 */         int num_sand_blocks = 1;
/*      */         
/*  992 */         while (block_ids[++index] == block_id_sand) {
/*  993 */           num_sand_blocks++;
/*      */         }
/*  995 */         int max_y = y + num_sand_blocks - 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         while (true) {
/* 1022 */           int block_id = block_ids[index_at_y_equals_0 + y - 1];
/*      */           
/* 1024 */           if (block_id == 0 || block_id == block_id_web) {
/* 1025 */             y--;
/*      */             continue;
/*      */           } 
/*      */           break;
/*      */         } 
/* 1030 */         y--;
/*      */         
/* 1032 */         while (++y <= max_y)
/* 1033 */           block_ids[index_at_y_equals_0 + y] = (--num_sand_blocks < 0) ? 0 : block_id_sand; 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkProviderGenerate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */