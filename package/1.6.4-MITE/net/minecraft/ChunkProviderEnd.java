/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public final class ChunkProviderEnd
/*     */   implements IChunkProvider
/*     */ {
/*     */   private Random endRNG;
/*     */   private NoiseGeneratorOctaves noiseGen1;
/*     */   private NoiseGeneratorOctaves noiseGen2;
/*     */   private NoiseGeneratorOctaves noiseGen3;
/*     */   public NoiseGeneratorOctaves noiseGen4;
/*     */   public NoiseGeneratorOctaves noiseGen5;
/*     */   private World endWorld;
/*     */   private double[] densities;
/*     */   private BiomeGenBase[] biomesForGeneration;
/*     */   double[] noiseData1;
/*     */   double[] noiseData2;
/*     */   double[] noiseData3;
/*     */   double[] noiseData4;
/*     */   double[] noiseData5;
/*  24 */   int[][] field_73203_h = new int[32][32];
/*     */ 
/*     */   
/*     */   public ChunkProviderEnd(World par1World, long par2) {
/*  28 */     this.endWorld = par1World;
/*  29 */     this.endRNG = new Random(par2);
/*  30 */     this.noiseGen1 = new NoiseGeneratorOctaves(this.endRNG, 16);
/*  31 */     this.noiseGen2 = new NoiseGeneratorOctaves(this.endRNG, 16);
/*  32 */     this.noiseGen3 = new NoiseGeneratorOctaves(this.endRNG, 8);
/*  33 */     this.noiseGen4 = new NoiseGeneratorOctaves(this.endRNG, 10);
/*  34 */     this.noiseGen5 = new NoiseGeneratorOctaves(this.endRNG, 16);
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateTerrain(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
/*  39 */     byte var5 = 2;
/*  40 */     int var6 = var5 + 1;
/*  41 */     byte var7 = 33;
/*  42 */     int var8 = var5 + 1;
/*  43 */     this.densities = initializeNoiseField(this.densities, par1 * var5, 0, par2 * var5, var6, var7, var8);
/*     */     
/*  45 */     for (int var9 = 0; var9 < var5; var9++) {
/*     */       
/*  47 */       for (int var10 = 0; var10 < var5; var10++) {
/*     */         
/*  49 */         for (int var11 = 0; var11 < 32; var11++) {
/*     */           
/*  51 */           double var12 = 0.25D;
/*  52 */           double var14 = this.densities[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 0];
/*  53 */           double var16 = this.densities[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 0];
/*  54 */           double var18 = this.densities[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 0];
/*  55 */           double var20 = this.densities[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 0];
/*  56 */           double var22 = (this.densities[((var9 + 0) * var8 + var10 + 0) * var7 + var11 + 1] - var14) * var12;
/*  57 */           double var24 = (this.densities[((var9 + 0) * var8 + var10 + 1) * var7 + var11 + 1] - var16) * var12;
/*  58 */           double var26 = (this.densities[((var9 + 1) * var8 + var10 + 0) * var7 + var11 + 1] - var18) * var12;
/*  59 */           double var28 = (this.densities[((var9 + 1) * var8 + var10 + 1) * var7 + var11 + 1] - var20) * var12;
/*     */           
/*  61 */           for (int var30 = 0; var30 < 4; var30++) {
/*     */             
/*  63 */             double var31 = 0.125D;
/*  64 */             double var33 = var14;
/*  65 */             double var35 = var16;
/*  66 */             double var37 = (var18 - var14) * var31;
/*  67 */             double var39 = (var20 - var16) * var31;
/*     */             
/*  69 */             for (int var41 = 0; var41 < 8; var41++) {
/*     */               
/*  71 */               int var42 = var41 + var9 * 8 << 11 | 0 + var10 * 8 << 7 | var11 * 4 + var30;
/*  72 */               short var43 = 128;
/*  73 */               double var44 = 0.125D;
/*  74 */               double var46 = var33;
/*  75 */               double var48 = (var35 - var33) * var44;
/*     */               
/*  77 */               for (int var50 = 0; var50 < 8; var50++) {
/*     */                 
/*  79 */                 int var51 = 0;
/*     */                 
/*  81 */                 if (var46 > 0.0D)
/*     */                 {
/*  83 */                   var51 = Block.whiteStone.blockID;
/*     */                 }
/*     */                 
/*  86 */                 par3ArrayOfByte[var42] = (byte)var51;
/*  87 */                 var42 += var43;
/*  88 */                 var46 += var48;
/*     */               } 
/*     */               
/*  91 */               var33 += var37;
/*  92 */               var35 += var39;
/*     */             } 
/*     */             
/*  95 */             var14 += var22;
/*  96 */             var16 += var24;
/*  97 */             var18 += var26;
/*  98 */             var20 += var28;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void replaceBlocksForBiome(int par1, int par2, byte[] par3ArrayOfByte, BiomeGenBase[] par4ArrayOfBiomeGenBase) {
/* 107 */     for (int var5 = 0; var5 < 16; var5++) {
/*     */       
/* 109 */       for (int var6 = 0; var6 < 16; var6++) {
/*     */         
/* 111 */         byte var7 = 1;
/* 112 */         int var8 = -1;
/* 113 */         byte var9 = (byte)Block.whiteStone.blockID;
/* 114 */         byte var10 = (byte)Block.whiteStone.blockID;
/*     */         
/* 116 */         for (int var11 = 127; var11 >= 0; var11--) {
/*     */           
/* 118 */           int var12 = (var6 * 16 + var5) * 128 + var11;
/* 119 */           byte var13 = par3ArrayOfByte[var12];
/*     */           
/* 121 */           if (var13 == 0) {
/*     */             
/* 123 */             var8 = -1;
/*     */           }
/* 125 */           else if (var13 == Block.stone.blockID) {
/*     */             
/* 127 */             if (var8 == -1) {
/*     */               
/* 129 */               if (var7 <= 0) {
/*     */                 
/* 131 */                 var9 = 0;
/* 132 */                 var10 = (byte)Block.whiteStone.blockID;
/*     */               } 
/*     */               
/* 135 */               var8 = var7;
/*     */               
/* 137 */               if (var11 >= 0)
/*     */               {
/* 139 */                 par3ArrayOfByte[var12] = var9;
/*     */               }
/*     */               else
/*     */               {
/* 143 */                 par3ArrayOfByte[var12] = var10;
/*     */               }
/*     */             
/* 146 */             } else if (var8 > 0) {
/*     */               
/* 148 */               var8--;
/* 149 */               par3ArrayOfByte[var12] = var10;
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
/*     */   public Chunk loadChunk(int par1, int par2) {
/* 162 */     return provideChunk(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int par1, int par2) {
/* 171 */     this.endRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
/* 172 */     byte[] var3 = new byte[32768];
/* 173 */     this.biomesForGeneration = this.endWorld.getWorldChunkManager().loadBlockGeneratorData(this.biomesForGeneration, par1 * 16, par2 * 16, 16, 16);
/* 174 */     generateTerrain(par1, par2, var3, this.biomesForGeneration);
/* 175 */     replaceBlocksForBiome(par1, par2, var3, this.biomesForGeneration);
/* 176 */     Chunk var4 = new Chunk(this.endWorld, var3, par1, par2);
/* 177 */     byte[] var5 = var4.getBiomeArray();
/*     */     
/* 179 */     for (int var6 = 0; var6 < var5.length; var6++)
/*     */     {
/* 181 */       var5[var6] = (byte)(this.biomesForGeneration[var6]).biomeID;
/*     */     }
/*     */ 
/*     */     
/* 185 */     var4.generateHeightMap(false);
/* 186 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private double[] initializeNoiseField(double[] par1ArrayOfDouble, int par2, int par3, int par4, int par5, int par6, int par7) {
/* 195 */     if (par1ArrayOfDouble == null)
/*     */     {
/* 197 */       par1ArrayOfDouble = new double[par5 * par6 * par7];
/*     */     }
/*     */     
/* 200 */     double var8 = 684.412D;
/* 201 */     double var10 = 684.412D;
/* 202 */     this.noiseData4 = this.noiseGen4.generateNoiseOctaves(this.noiseData4, par2, par4, par5, par7, 1.121D, 1.121D, 0.5D);
/* 203 */     this.noiseData5 = this.noiseGen5.generateNoiseOctaves(this.noiseData5, par2, par4, par5, par7, 200.0D, 200.0D, 0.5D);
/* 204 */     var8 *= 2.0D;
/* 205 */     this.noiseData1 = this.noiseGen3.generateNoiseOctaves(this.noiseData1, par2, par3, par4, par5, par6, par7, var8 / 80.0D, var10 / 160.0D, var8 / 80.0D);
/* 206 */     this.noiseData2 = this.noiseGen1.generateNoiseOctaves(this.noiseData2, par2, par3, par4, par5, par6, par7, var8, var10, var8);
/* 207 */     this.noiseData3 = this.noiseGen2.generateNoiseOctaves(this.noiseData3, par2, par3, par4, par5, par6, par7, var8, var10, var8);
/* 208 */     int var12 = 0;
/* 209 */     int var13 = 0;
/*     */     
/* 211 */     for (int var14 = 0; var14 < par5; var14++) {
/*     */       
/* 213 */       for (int var15 = 0; var15 < par7; var15++) {
/*     */         
/* 215 */         double var16 = (this.noiseData4[var13] + 256.0D) / 512.0D;
/*     */         
/* 217 */         if (var16 > 1.0D)
/*     */         {
/* 219 */           var16 = 1.0D;
/*     */         }
/*     */         
/* 222 */         double var18 = this.noiseData5[var13] / 8000.0D;
/*     */         
/* 224 */         if (var18 < 0.0D)
/*     */         {
/* 226 */           var18 = -var18 * 0.3D;
/*     */         }
/*     */         
/* 229 */         var18 = var18 * 3.0D - 2.0D;
/* 230 */         float var20 = (var14 + par2 - 0) / 1.0F;
/* 231 */         float var21 = (var15 + par4 - 0) / 1.0F;
/* 232 */         float var22 = 100.0F - MathHelper.sqrt_float(var20 * var20 + var21 * var21) * 8.0F;
/*     */         
/* 234 */         if (var22 > 80.0F)
/*     */         {
/* 236 */           var22 = 80.0F;
/*     */         }
/*     */         
/* 239 */         if (var22 < -100.0F)
/*     */         {
/* 241 */           var22 = -100.0F;
/*     */         }
/*     */         
/* 244 */         if (var18 > 1.0D)
/*     */         {
/* 246 */           var18 = 1.0D;
/*     */         }
/*     */         
/* 249 */         var18 /= 8.0D;
/* 250 */         var18 = 0.0D;
/*     */         
/* 252 */         if (var16 < 0.0D)
/*     */         {
/* 254 */           var16 = 0.0D;
/*     */         }
/*     */         
/* 257 */         var16 += 0.5D;
/* 258 */         var18 = var18 * par6 / 16.0D;
/* 259 */         var13++;
/* 260 */         double var23 = par6 / 2.0D;
/*     */         
/* 262 */         for (int var25 = 0; var25 < par6; var25++) {
/*     */           
/* 264 */           double var26 = 0.0D;
/* 265 */           double var28 = (var25 - var23) * 8.0D / var16;
/*     */           
/* 267 */           if (var28 < 0.0D)
/*     */           {
/* 269 */             var28 *= -1.0D;
/*     */           }
/*     */           
/* 272 */           double var30 = this.noiseData2[var12] / 512.0D;
/* 273 */           double var32 = this.noiseData3[var12] / 512.0D;
/* 274 */           double var34 = (this.noiseData1[var12] / 10.0D + 1.0D) / 2.0D;
/*     */           
/* 276 */           if (var34 < 0.0D) {
/*     */             
/* 278 */             var26 = var30;
/*     */           }
/* 280 */           else if (var34 > 1.0D) {
/*     */             
/* 282 */             var26 = var32;
/*     */           }
/*     */           else {
/*     */             
/* 286 */             var26 = var30 + (var32 - var30) * var34;
/*     */           } 
/*     */           
/* 289 */           var26 -= 8.0D;
/* 290 */           var26 += var22;
/* 291 */           byte var36 = 2;
/*     */ 
/*     */           
/* 294 */           if (var25 > par6 / 2 - var36) {
/*     */             
/* 296 */             double var37 = ((var25 - par6 / 2 - var36) / 64.0F);
/*     */             
/* 298 */             if (var37 < 0.0D)
/*     */             {
/* 300 */               var37 = 0.0D;
/*     */             }
/*     */             
/* 303 */             if (var37 > 1.0D)
/*     */             {
/* 305 */               var37 = 1.0D;
/*     */             }
/*     */             
/* 308 */             var26 = var26 * (1.0D - var37) + -3000.0D * var37;
/*     */           } 
/*     */           
/* 311 */           var36 = 8;
/*     */           
/* 313 */           if (var25 < var36) {
/*     */             
/* 315 */             double var37 = ((var36 - var25) / (var36 - 1.0F));
/* 316 */             var26 = var26 * (1.0D - var37) + -30.0D * var37;
/*     */           } 
/*     */           
/* 319 */           par1ArrayOfDouble[var12] = var26;
/* 320 */           var12++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 325 */     return par1ArrayOfDouble;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int par1, int par2) {
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/* 338 */     Minecraft.setErrorMessage("getChunkIfItExists: called for ChunkProviderEnd");
/* 339 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
/* 347 */     BlockFalling.fallInstantly = true;
/* 348 */     int var4 = par2 * 16;
/* 349 */     int var5 = par3 * 16;
/* 350 */     BiomeGenBase var6 = this.endWorld.getBiomeGenForCoords(var4 + 16, var5 + 16);
/* 351 */     var6.decorate(this.endWorld, this.endWorld.rand, var4, var5);
/* 352 */     BlockFalling.fallInstantly = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
/* 361 */     return true;
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
/* 375 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 383 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 391 */     return "RandomLevelSource";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/* 399 */     BiomeGenBase var5 = this.endWorld.getBiomeGenForCoords(par2, par4);
/* 400 */     return (var5 == null) ? null : var5.getSpawnableList(par1EnumCreatureType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
/* 408 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 413 */     return 0;
/*     */   }
/*     */   
/*     */   public void recreateStructures(int par1, int par2) {}
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkProviderEnd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */