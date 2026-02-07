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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeDecorator
/*     */ {
/*     */   protected World currentWorld;
/*     */   protected Random randomGenerator;
/*     */   protected int chunk_X;
/*     */   protected int chunk_Z;
/*     */   protected BiomeGenBase biome;
/*  23 */   protected WorldGenerator clayGen = new WorldGenClay(4);
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenerator sandGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenerator gravelAsSandGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable dirtGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable gravelGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable coalGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable ironGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable copperGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable silverGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable mithrilGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable adamantiteGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable goldGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable redstoneGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable diamondGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable lapisGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenMinable silverfishGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenerator plantYellowGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenFlowers plantRedGen;
/*     */ 
/*     */ 
/*     */   
/*     */   protected WorldGenerator mushroomBrownGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator mushroomRedGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator bigMushroomGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator reedGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator cactusGen;
/*     */ 
/*     */   
/*     */   protected WorldGenerator waterlilyGen;
/*     */ 
/*     */   
/*     */   protected int waterlilyPerChunk;
/*     */ 
/*     */   
/*     */   protected int treesPerChunk;
/*     */ 
/*     */   
/*     */   protected int flowersPerChunk;
/*     */ 
/*     */   
/*     */   protected int grassPerChunk;
/*     */ 
/*     */   
/*     */   protected int deadBushPerChunk;
/*     */ 
/*     */   
/*     */   protected int surface_mushrooms_per_chunk;
/*     */ 
/*     */   
/*     */   protected int reedsPerChunk;
/*     */ 
/*     */   
/*     */   protected int cactiPerChunk;
/*     */ 
/*     */   
/*     */   protected int sandPerChunk;
/*     */ 
/*     */   
/*     */   protected int sandPerChunk2;
/*     */ 
/*     */   
/*     */   protected int clayPerChunk;
/*     */ 
/*     */   
/*     */   protected int bigMushroomsPerChunk;
/*     */ 
/*     */   
/*     */   protected int bush_patches_per_chunk_tenths;
/*     */ 
/*     */   
/*     */   public boolean generateLakes;
/*     */ 
/*     */   
/*     */   protected WorldGenPlants bush_gen;
/*     */ 
/*     */ 
/*     */   
/*     */   public BiomeDecorator(BiomeGenBase par1BiomeGenBase) {
/* 160 */     this.sandGen = new WorldGenSand(7, Block.sand.blockID);
/* 161 */     this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 171 */     this.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
/* 172 */     this.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
/* 173 */     this.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
/* 174 */     this.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
/*     */     
/* 176 */     this.bigMushroomGen = new WorldGenBigMushroom();
/* 177 */     this.reedGen = new WorldGenReed();
/* 178 */     this.cactusGen = new WorldGenCactus();
/* 179 */     this.waterlilyGen = new WorldGenWaterlily();
/* 180 */     this.flowersPerChunk = 2;
/* 181 */     this.grassPerChunk = 1;
/* 182 */     this.sandPerChunk = 1;
/* 183 */     this.sandPerChunk2 = 3;
/* 184 */     this.clayPerChunk = 1;
/* 185 */     this.generateLakes = true;
/* 186 */     this.biome = par1BiomeGenBase;
/*     */ 
/*     */ 
/*     */     
/* 190 */     this.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
/* 191 */     this.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
/* 192 */     this.coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
/* 193 */     this.copperGen = new WorldGenMinable(Block.oreCopper.blockID, 6);
/* 194 */     this.silverGen = new WorldGenMinable(Block.oreSilver.blockID, 6);
/* 195 */     this.goldGen = new WorldGenMinable(Block.oreGold.blockID, 4);
/* 196 */     this.ironGen = new WorldGenMinable(Block.oreIron.blockID, 6);
/* 197 */     this.mithrilGen = new WorldGenMinable(Block.oreMithril.blockID, 3);
/* 198 */     this.adamantiteGen = new WorldGenMinable(Block.oreAdamantium.blockID, 3);
/* 199 */     this.redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 5);
/* 200 */     this.diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 3);
/* 201 */     this.lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 3);
/*     */     
/* 203 */     this.silverfishGen = new WorldGenMinable(Block.silverfish.blockID, 3);
/*     */     
/* 205 */     this.bush_gen = new WorldGenPlants(Block.bush);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void decorate(World par1World, Random par2Random, int par3, int par4) {
/* 215 */     if (this.currentWorld != null)
/*     */     {
/* 217 */       throw new RuntimeException("Already decorating!!");
/*     */     }
/*     */ 
/*     */     
/* 221 */     this.currentWorld = par1World;
/* 222 */     this.randomGenerator = par2Random;
/* 223 */     this.randomGenerator.setSeed((par3 + par4 * 65536) + par1World.getSeed() * 4294967296L);
/* 224 */     this.chunk_X = par3;
/* 225 */     this.chunk_Z = par4;
/* 226 */     decorate();
/* 227 */     this.currentWorld = null;
/* 228 */     this.randomGenerator = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void decorate() {
/* 237 */     this.currentWorld.decorating = true;
/*     */     
/* 239 */     generateOres();
/*     */ 
/*     */     
/*     */     int var1;
/*     */     
/* 244 */     for (var1 = 0; var1 < this.sandPerChunk2; var1++) {
/*     */       
/* 246 */       int i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 247 */       int var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 249 */       this.sandGen.generate(this.currentWorld, this.randomGenerator, i, this.currentWorld.getTopSolidOrLiquidBlock(i, var3), var3);
/*     */     } 
/*     */     
/* 252 */     for (var1 = 0; var1 < this.clayPerChunk; var1++) {
/*     */       
/* 254 */       int i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 255 */       int var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 257 */       this.clayGen.generate(this.currentWorld, this.randomGenerator, i, this.currentWorld.getTopSolidOrLiquidBlock(i, var3), var3);
/*     */     } 
/*     */     
/* 260 */     for (var1 = 0; var1 < this.sandPerChunk; var1++) {
/*     */       
/* 262 */       int i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 263 */       int var3 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 265 */       this.sandGen.generate(this.currentWorld, this.randomGenerator, i, this.currentWorld.getTopSolidOrLiquidBlock(i, var3), var3);
/*     */     } 
/*     */     
/* 268 */     var1 = this.treesPerChunk;
/*     */     
/* 270 */     if (this.randomGenerator.nextInt(10) == 0)
/*     */     {
/* 272 */       var1++;
/*     */     }
/*     */     
/*     */     int var2;
/*     */     
/* 277 */     for (var2 = 0; var2 < var1; var2++) {
/*     */       
/* 279 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 280 */       int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 282 */       WorldGenerator var5 = this.biome.getRandomWorldGenForTrees(this.randomGenerator);
/* 283 */       var5.setScale(1.0D, 1.0D, 1.0D);
/* 284 */       var5.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
/*     */     } 
/*     */     
/* 287 */     if (this.biome == BiomeGenBase.plains && this.randomGenerator.nextInt(400) == 0) {
/*     */       
/* 289 */       int previous_height_limit = this.biome.worldGeneratorBigTree.heightLimit;
/*     */       
/* 291 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 292 */       int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 294 */       this.biome.worldGeneratorBigTree.setHeightLimit(10 + this.randomGenerator.nextInt(5));
/*     */       
/* 296 */       WorldGenerator var5 = this.biome.worldGeneratorBigTree;
/* 297 */       var5.setScale(1.0D, 1.0D, 1.0D);
/* 298 */       var5.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
/*     */       
/* 300 */       this.biome.worldGeneratorBigTree.setHeightLimit(previous_height_limit);
/*     */     } 
/*     */     
/* 303 */     for (var2 = 0; var2 < this.bigMushroomsPerChunk; var2++) {
/*     */       
/* 305 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 306 */       int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 308 */       this.bigMushroomGen.generate(this.currentWorld, this.randomGenerator, var3, this.currentWorld.getHeightValue(var3, var4), var4);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 313 */     for (var2 = 0; var2 < this.flowersPerChunk; var2++) {
/*     */       
/* 315 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 316 */       int var4 = this.randomGenerator.nextInt(128);
/* 317 */       int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 319 */       this.plantYellowGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
/*     */ 
/*     */       
/* 322 */       if (this.randomGenerator.nextInt(this.biome.isSwampBiome() ? 3 : 2) == 0) {
/*     */         
/* 324 */         var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 325 */         var4 = this.randomGenerator.nextInt(128);
/* 326 */         var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
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
/* 345 */         int subtype = Block.plantRed.getRandomSubtypeForBiome(this.randomGenerator, this.biome);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 350 */         if (subtype >= 0) {
/*     */           
/* 352 */           this.plantRedGen.setMetadata(subtype);
/* 353 */           this.plantRedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 358 */     for (var2 = 0; var2 < this.grassPerChunk; var2++) {
/*     */       
/* 360 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 361 */       int var4 = this.randomGenerator.nextInt(128);
/* 362 */       int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 364 */       WorldGenerator var6 = this.biome.getRandomWorldGenForGrass(this.randomGenerator);
/* 365 */       var6.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
/*     */     } 
/*     */     
/* 368 */     for (var2 = 0; var2 < this.deadBushPerChunk; var2++) {
/*     */       
/* 370 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 371 */       int var4 = this.randomGenerator.nextInt(128);
/* 372 */       int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 374 */       (new WorldGenDeadBush(Block.deadBush.blockID)).generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
/*     */     } 
/*     */     
/* 377 */     for (var2 = 0; var2 < this.waterlilyPerChunk; var2++) {
/*     */       
/* 379 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 380 */       int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       int var7;
/* 382 */       for (var7 = this.randomGenerator.nextInt(128); var7 > 0 && this.currentWorld.getBlockId(var3, var7 - 1, var4) == 0; var7--);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 387 */       this.waterlilyGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
/*     */     } 
/*     */ 
/*     */     
/* 391 */     for (var2 = 0; var2 < this.surface_mushrooms_per_chunk; var2++) {
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
/* 403 */       if (this.randomGenerator.nextInt(6) == 0) {
/*     */         
/* 405 */         int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 406 */         int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */         
/* 408 */         int var7 = this.randomGenerator.nextInt(128);
/* 409 */         this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
/*     */       } 
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
/*     */ 
/*     */     
/* 423 */     if (this.randomGenerator.nextInt(6) == 0) {
/*     */       
/* 425 */       var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 426 */       int var3 = this.randomGenerator.nextInt(128);
/* 427 */       int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 429 */       this.mushroomRedGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
/*     */     } 
/*     */     
/* 432 */     for (int subterranean_mushrooms = 0; subterranean_mushrooms < 4; subterranean_mushrooms++) {
/*     */       
/* 434 */       if (this.currentWorld.isUnderworld()) {
/*     */         
/* 436 */         if (this.randomGenerator.nextInt(4) == 0) {
/*     */           
/* 438 */           var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 439 */           int var3 = this.randomGenerator.nextInt(128);
/* 440 */           int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */           
/* 442 */           this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
/*     */         } 
/*     */         
/*     */         break;
/*     */       } 
/*     */       
/* 448 */       if (this.randomGenerator.nextInt(4) == 0) {
/*     */         
/* 450 */         var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 451 */         int var3 = this.randomGenerator.nextInt(32) + 48;
/* 452 */         int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */         
/* 454 */         this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
/*     */       } 
/*     */       
/* 457 */       if (this.randomGenerator.nextInt(4) == 0) {
/*     */         
/* 459 */         var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 460 */         int var3 = this.randomGenerator.nextInt(128);
/* 461 */         int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */         
/* 463 */         this.mushroomBrownGen.generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
/*     */       } 
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
/* 494 */     if (this.biome.temperature >= 0.3F) {
/*     */       
/* 496 */       for (var2 = 0; var2 < this.reedsPerChunk; var2++) {
/*     */         
/* 498 */         int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 499 */         int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/* 500 */         int var7 = this.randomGenerator.nextInt(128);
/*     */         
/* 502 */         this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var7, var4);
/*     */       } 
/*     */       
/* 505 */       for (var2 = 0; var2 < 10; var2++) {
/*     */         
/* 507 */         int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 508 */         int var4 = this.randomGenerator.nextInt(128);
/* 509 */         int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */         
/* 511 */         this.reedGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
/*     */       } 
/*     */     } 
/*     */     
/* 515 */     if (this.randomGenerator.nextInt(32) == 0) {
/*     */       
/* 517 */       var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 518 */       int var3 = this.randomGenerator.nextInt(128);
/* 519 */       int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/* 520 */       (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
/*     */     } 
/*     */     
/* 523 */     for (var2 = 0; var2 < this.cactiPerChunk; var2++) {
/*     */       
/* 525 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 526 */       int var4 = this.randomGenerator.nextInt(128);
/* 527 */       int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/* 528 */       this.cactusGen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
/*     */     } 
/*     */     
/* 531 */     int bush_patches_per_chunk = this.bush_patches_per_chunk_tenths / 10 + ((this.randomGenerator.nextInt(10) < this.bush_patches_per_chunk_tenths % 10) ? 1 : 0);
/*     */     
/* 533 */     for (var2 = 0; var2 < bush_patches_per_chunk; var2++) {
/*     */       
/* 535 */       int var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 536 */       int var4 = this.randomGenerator.nextInt(128);
/* 537 */       int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */       
/* 539 */       this.bush_gen.setMetadata(BlockBush.getMetadataForBushWithBerries(0));
/* 540 */       this.bush_gen.generate(this.currentWorld, this.randomGenerator, var3, var4, var7);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 545 */     if (this.generateLakes) {
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
/* 565 */       for (int chunk_dx = -1; chunk_dx <= 1; chunk_dx++) {
/*     */         
/* 567 */         for (int chunk_dz = -1; chunk_dz <= 1; chunk_dz++) {
/*     */           
/* 569 */           Chunk chunk = this.currentWorld.getChunkFromBlockCoordsIfItExists(this.chunk_X + chunk_dx * 16, this.chunk_Z + chunk_dz * 16);
/*     */           
/* 571 */           if (chunk != null && chunk.getHadNaturallyOccurringMycelium()) {
/*     */             
/* 573 */             this.currentWorld.decorating = false;
/*     */ 
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 581 */       for (var2 = 0; var2 < 70; var2++) {
/*     */         
/* 583 */         int liquid_block_id, var3 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
/* 584 */         int var4 = this.randomGenerator.nextInt(this.randomGenerator.nextInt(120) + 8);
/* 585 */         int var7 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 591 */         CaveNetworkStub stub = this.currentWorld.getAsWorldServer().getCaveNetworkStubAt(var3 >> 4, var7 >> 4);
/*     */         
/* 593 */         boolean prevent_water = false;
/* 594 */         boolean prevent_lava = false;
/*     */         
/* 596 */         if (stub != null) {
/*     */           
/* 598 */           if (stub.hasMycelium() || stub.preventsAllLiquids() || this.randomGenerator.nextFloat() < 0.67F) {
/*     */             continue;
/*     */           }
/* 601 */           prevent_water = !stub.allowsWater();
/* 602 */           prevent_lava = !stub.allowsLava();
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 607 */         if (this.randomGenerator.nextInt(32) + 16 < var4) {
/*     */ 
/*     */           
/* 610 */           if (prevent_water) {
/*     */             continue;
/*     */           }
/* 613 */           liquid_block_id = Block.waterMoving.blockID;
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 618 */         else if (this.randomGenerator.nextFloat() < 0.95F) {
/*     */           
/* 620 */           if (prevent_lava) {
/*     */             continue;
/*     */           }
/* 623 */           liquid_block_id = Block.lavaMoving.blockID;
/*     */         }
/*     */         else {
/*     */           
/* 627 */           if (prevent_water) {
/*     */             continue;
/*     */           }
/* 630 */           liquid_block_id = Block.waterMoving.blockID;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 635 */         WorldGenLiquids.generate(this.currentWorld, this.randomGenerator, liquid_block_id, var3, var4 + this.currentWorld.underworld_y_offset, var7);
/*     */         continue;
/*     */       } 
/*     */     } 
/* 639 */     this.currentWorld.decorating = false;
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
/*     */   protected void genMinable(int frequency, WorldGenMinable world_gen_minable) {
/* 683 */     genMinable(frequency, world_gen_minable, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void genMinable(int frequency, WorldGenMinable world_gen_minable, boolean vein_size_increases_with_depth) {
/* 688 */     int resource_multiplier = 1;
/*     */     
/* 690 */     frequency *= resource_multiplier;
/*     */     
/* 692 */     if (this.currentWorld.underworld_y_offset != 0 && world_gen_minable != this.gravelGen) {
/*     */       
/* 694 */       frequency *= 8;
/*     */       
/* 696 */       if (world_gen_minable == this.adamantiteGen) {
/* 697 */         frequency *= 2;
/*     */       }
/*     */     } 
/* 700 */     while (frequency-- > 0) {
/*     */ 
/*     */       
/* 703 */       if (this.randomGenerator.nextInt(10) == 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 709 */         int x = this.chunk_X + this.randomGenerator.nextInt(16);
/* 710 */         int y = world_gen_minable.getRandomVeinHeight(this.currentWorld, this.randomGenerator);
/* 711 */         int z = this.chunk_Z + this.randomGenerator.nextInt(16);
/*     */         
/* 713 */         if (y >= 0) {
/* 714 */           world_gen_minable.generate(this.currentWorld, this.randomGenerator, x, y, z, vein_size_increases_with_depth);
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
/*     */   protected void generateOres() {
/* 737 */     if (this.currentWorld.isOverworld()) {
/*     */       
/* 739 */       genMinable(200, this.dirtGen);
/* 740 */       genMinable(200, this.gravelGen);
/*     */       
/* 742 */       genMinable(50, this.coalGen);
/*     */       
/* 744 */       genMinable(40, this.copperGen, true);
/* 745 */       genMinable(10, this.silverGen, true);
/* 746 */       genMinable(20, this.goldGen, true);
/* 747 */       genMinable(60, this.ironGen, true);
/* 748 */       genMinable(10, this.mithrilGen, true);
/* 749 */       genMinable(5, this.silverfishGen, true);
/*     */       
/* 751 */       genMinable(10, this.redstoneGen);
/* 752 */       genMinable(5, this.diamondGen);
/* 753 */       genMinable(5, this.lapisGen);
/*     */     }
/* 755 */     else if (this.currentWorld.isUnderworld()) {
/*     */       
/* 757 */       genMinable(300, this.gravelGen);
/*     */       
/* 759 */       genMinable(40, this.copperGen, true);
/* 760 */       genMinable(10, this.silverGen, true);
/* 761 */       genMinable(20, this.goldGen, true);
/* 762 */       genMinable(60, this.ironGen, true);
/* 763 */       genMinable(10, this.mithrilGen, true);
/* 764 */       genMinable(5, this.adamantiteGen, true);
/*     */       
/* 766 */       genMinable(10, this.redstoneGen);
/* 767 */       genMinable(5, this.diamondGen);
/* 768 */       genMinable(5, this.lapisGen);
/*     */       
/* 770 */       if (this.currentWorld.underworld_y_offset != 0) {
/* 771 */         genMinable(50, this.silverfishGen);
/*     */       }
/* 773 */     } else if (!this.currentWorld.isTheEnd()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 779 */       Minecraft.setErrorMessage("generateOres: don't know how to handle world " + this.currentWorld);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */