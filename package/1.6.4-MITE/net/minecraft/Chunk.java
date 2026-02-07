/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
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
/*      */ public class Chunk
/*      */ {
/*      */   public static boolean isLit;
/*      */   public ExtendedBlockStorage[] storageArrays;
/*      */   private byte[] blockBiomeArray;
/*      */   public int[] precipitationHeightMap;
/*      */   public boolean[] updateSkylightColumns;
/*      */   public boolean isChunkLoaded;
/*      */   public World worldObj;
/*      */   public int[] heightMap;
/*      */   public final int xPosition;
/*      */   public final int zPosition;
/*      */   public boolean isGapLightingUpdated;
/*      */   public Map chunkTileEntityMap;
/*      */   public static final int num_entity_lists = 16;
/*      */   private List[] entityLists;
/*      */   public boolean isTerrainPopulated;
/*      */   public boolean isModified;
/*      */   public long lastSaveTime;
/*      */   public boolean sendUpdates;
/*      */   public final int[] skylight_bottom;
/*   94 */   public static final int skylight_bottom_initial_value = World.getMaxBlockY() + 1;
/*      */ 
/*      */   
/*      */   public long inhabitedTime;
/*      */ 
/*      */   
/*      */   private int queuedLightChecks;
/*      */ 
/*      */   
/*      */   public long last_total_world_time;
/*      */ 
/*      */   
/*      */   public int animals_spawned;
/*      */ 
/*      */   
/*      */   public boolean should_be_saved_once_time_forwarding_is_completed;
/*      */ 
/*      */   
/*      */   private final boolean is_empty;
/*      */   
/*      */   public boolean invalidate_checksum;
/*      */   
/*      */   public boolean has_had_lighting_checked;
/*      */   
/*      */   private boolean has_initial_heightmap_been_generated;
/*      */   
/*      */   private boolean has_initial_skymap_been_generated;
/*      */   
/*      */   public byte[] pending_skylight_update_coords;
/*      */   
/*  124 */   public int max_num_pending_skylight_updates = 256;
/*      */   
/*      */   public int num_pending_skylight_updates;
/*      */   
/*      */   public final boolean[] pending_skylight_updates;
/*  129 */   private static final boolean[] skylight_propagation_from_neighbor_to_nw = generateSkylightPropagationForNeighbor(-1, -1);
/*  130 */   private static final boolean[] skylight_propagation_from_neighbor_to_ne = generateSkylightPropagationForNeighbor(1, -1);
/*  131 */   private static final boolean[] skylight_propagation_from_neighbor_to_se = generateSkylightPropagationForNeighbor(1, 1);
/*  132 */   private static final boolean[] skylight_propagation_from_neighbor_to_sw = generateSkylightPropagationForNeighbor(-1, 1);
/*      */   
/*      */   public byte[] pending_blocklight_update_coords;
/*      */   
/*  136 */   public int max_num_pending_blocklight_updates = 256;
/*      */ 
/*      */   
/*      */   public int num_pending_blocklight_updates;
/*      */ 
/*      */   
/*      */   public final boolean[] pending_blocklight_updates;
/*      */   
/*      */   public final boolean has_skylight;
/*      */   
/*      */   private final boolean is_within_block_domain;
/*      */   
/*      */   private boolean had_naturally_occurring_mycelium;
/*      */   
/*      */   public HashMap pending_sand_falls;
/*      */   
/*  152 */   private static final byte sand_block_id = (byte)Block.sand.blockID;
/*  153 */   private static final byte water_still_block_id = (byte)Block.waterStill.blockID;
/*      */ 
/*      */   
/*      */   public Chunk(World par1World, int par2, int par3) {
/*  157 */     this.is_empty = this instanceof EmptyChunk;
/*      */     
/*  159 */     this.is_within_block_domain = par1World.isChunkWithinBlockDomain(par2, par3);
/*      */     
/*  161 */     this.has_skylight = par1World.hasSkylight();
/*      */ 
/*      */     
/*  164 */     this.storageArrays = this.is_empty ? null : new ExtendedBlockStorage[16];
/*  165 */     this.blockBiomeArray = new byte[256];
/*  166 */     this.precipitationHeightMap = new int[256];
/*  167 */     this.updateSkylightColumns = new boolean[256];
/*  168 */     this.chunkTileEntityMap = new HashMap<Object, Object>();
/*  169 */     this.queuedLightChecks = 4096;
/*      */     
/*  171 */     this.entityLists = new List[16];
/*  172 */     this.worldObj = par1World;
/*  173 */     this.xPosition = par2;
/*  174 */     this.zPosition = par3;
/*  175 */     this.heightMap = new int[256];
/*      */     
/*  177 */     for (int var4 = 0; var4 < this.entityLists.length; var4++)
/*      */     {
/*  179 */       this.entityLists[var4] = new ArrayList();
/*      */     }
/*      */     
/*  182 */     Arrays.fill(this.precipitationHeightMap, -999);
/*  183 */     Arrays.fill(this.blockBiomeArray, (byte)-1);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  188 */     if (this.is_empty) {
/*      */       
/*  190 */       this.skylight_bottom = null;
/*  191 */       this.pending_skylight_update_coords = null;
/*  192 */       this.pending_skylight_updates = null;
/*      */       
/*  194 */       this.pending_blocklight_update_coords = null;
/*  195 */       this.pending_blocklight_updates = null;
/*      */     }
/*      */     else {
/*      */       
/*  199 */       this.skylight_bottom = new int[this.heightMap.length];
/*  200 */       Arrays.fill(this.skylight_bottom, skylight_bottom_initial_value);
/*      */       
/*  202 */       this.pending_skylight_update_coords = par1World.hasSkylight() ? new byte[this.max_num_pending_skylight_updates * 2] : null;
/*  203 */       this.pending_skylight_updates = par1World.hasSkylight() ? new boolean[65536] : null;
/*      */       
/*  205 */       this.pending_blocklight_update_coords = new byte[this.max_num_pending_blocklight_updates * 2];
/*  206 */       this.pending_blocklight_updates = new boolean[65536];
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private double getMax(double a, double b) {
/*  214 */     return (a > b) ? a : b;
/*      */   }
/*      */ 
/*      */   
/*      */   public Chunk(World par1World, byte[] par2ArrayOfByte, int par3, int par4) {
/*  219 */     this(par1World, par3, par4);
/*  220 */     int var5 = par2ArrayOfByte.length / 256;
/*      */     
/*  222 */     if (par1World.provider.isHellWorld) {
/*      */       
/*  224 */       int base_x = this.xPosition * 16;
/*  225 */       int base_z = this.zPosition * 16;
/*      */       
/*  227 */       for (int var6 = 0; var6 < 16; var6++) {
/*      */         
/*  229 */         for (int var7 = 0; var7 < 16; var7++) {
/*      */           
/*  231 */           for (int var8 = 0; var8 < var5; var8++) {
/*      */             
/*  233 */             byte var9 = par2ArrayOfByte[var6 << 11 | var7 << 7 | var8];
/*      */             
/*  235 */             if (var9 != 0) {
/*      */               
/*  237 */               if (var9 == Block.lavaStill.blockID) {
/*      */                 
/*  239 */                 if (var8 == 31 && par2ArrayOfByte[var6 << 11 | var7 << 7 | var8 + 1] == 0) {
/*  240 */                   addPendingBlocklightUpdate(base_x + var6, var8, base_z + var7);
/*      */                 }
/*  242 */                 if (var8 > 31) {
/*      */                   
/*  244 */                   int x = base_x + var6;
/*  245 */                   int z = base_z + var7;
/*      */                   
/*  247 */                   par1World.scheduleBlockChange(x, var8, z, var9, var9 - 1, 0, 10);
/*      */                 } 
/*      */               } 
/*      */ 
/*      */               
/*  252 */               int var10 = var8 >> 4;
/*      */               
/*  254 */               if (this.storageArrays[var10] == null)
/*      */               {
/*  256 */                 this.storageArrays[var10] = new ExtendedBlockStorage(var10 << 4, !par1World.provider.hasNoSky);
/*      */               }
/*      */               
/*  259 */               this.storageArrays[var10].setExtBlockID(var6, var8 & 0xF, var7, var9);
/*      */               
/*  261 */               if (var9 == Block.gravel.blockID) {
/*      */                 
/*  263 */                 this.storageArrays[var10].setExtBlockMetadata(var6, var8 & 0xF, var7, 2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               }
/*  273 */               else if (var9 == Block.mantleOrCore.blockID) {
/*      */                 
/*  275 */                 addPendingBlocklightUpdate(base_x + var6, var8, base_z + var7);
/*      */                 
/*  277 */                 if (var8 < 1) {
/*  278 */                   this.storageArrays[var10].setExtBlockMetadata(var6, var8 & 0xF, var7, 1);
/*      */                 }
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*  285 */     } else if (par1World.isUnderworld()) {
/*      */       
/*  287 */       int base_x = this.xPosition * 16;
/*  288 */       int base_z = this.zPosition * 16;
/*      */       
/*  290 */       Random random = new Random(par1World.getHashedSeed() * getChunkCoordsHash(this.xPosition, this.zPosition));
/*      */       
/*  292 */       int y_offset = par1World.underworld_y_offset;
/*      */ 
/*      */ 
/*      */       
/*  296 */       double scale_xz = 0.015625D;
/*  297 */       double scale_y = 0.03125D;
/*      */       
/*  299 */       ChunkProviderUnderworld.bedrock_strata_1a_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1a.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1a_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 2.0D, scale_y * 2.0D, scale_xz * 2.0D);
/*  300 */       ChunkProviderUnderworld.bedrock_strata_1b_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1b.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1b_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 2.0D, scale_y * 2.0D, scale_xz * 2.0D);
/*  301 */       ChunkProviderUnderworld.bedrock_strata_2_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_2.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_2_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0D, scale_y * 2.0D, scale_xz * 4.0D);
/*  302 */       ChunkProviderUnderworld.bedrock_strata_3_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_3.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_3_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0D, scale_y * 2.0D, scale_xz * 4.0D);
/*  303 */       ChunkProviderUnderworld.bedrock_strata_4_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_4.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_4_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0D, scale_y * 2.0D, scale_xz * 4.0D);
/*      */       
/*  305 */       scale_xz = 0.25D;
/*      */       
/*  307 */       ChunkProviderUnderworld.bedrock_strata_1a_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1a_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1a_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 0.5D, scale_y * 2.0D, scale_xz * 0.5D);
/*  308 */       ChunkProviderUnderworld.bedrock_strata_1b_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1b_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1b_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 1.0D, scale_y * 2.0D, scale_xz * 1.0D);
/*  309 */       ChunkProviderUnderworld.bedrock_strata_1c_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_1c_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_1c_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 2.0D, scale_y * 2.0D, scale_xz * 2.0D);
/*  310 */       ChunkProviderUnderworld.bedrock_strata_2_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_2_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_2_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0D, scale_y * 2.0D, scale_xz * 4.0D);
/*  311 */       ChunkProviderUnderworld.bedrock_strata_3_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_3_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_3_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0D, scale_y * 2.0D, scale_xz * 4.0D);
/*  312 */       ChunkProviderUnderworld.bedrock_strata_4_bump_noise = ChunkProviderUnderworld.noise_gen_bedrock_strata_4_bump.generateNoiseOctaves(ChunkProviderUnderworld.bedrock_strata_4_bump_noise, base_x, 0, base_z, 16, 1, 16, scale_xz * 4.0D, scale_y * 2.0D, scale_xz * 4.0D);
/*      */       
/*  314 */       int rng_index = this.xPosition * 2653 + this.zPosition * 6714631;
/*      */ 
/*      */ 
/*      */       
/*  318 */       for (int var6 = 0; var6 < 16; var6++) {
/*      */         
/*  320 */         for (int var7 = 0; var7 < 16; ) {
/*      */           
/*  322 */           int num_bedrock_blocks = (y_offset == 0) ? 0 : (random.nextInt(3) + 1);
/*      */ 
/*      */           
/*  325 */           int var8 = 0 - y_offset; for (;; var7++) { if (var8 < ((y_offset == 0) ? var5 : (256 - y_offset))) {
/*      */               int index;
/*      */               
/*      */               byte var9;
/*      */               
/*  330 */               if (var8 < 0 || var8 > 127) {
/*      */                 
/*  332 */                 index = -1;
/*      */                 
/*  334 */                 var8 += y_offset;
/*      */ 
/*      */ 
/*      */                 
/*  338 */                 if (var8 < num_bedrock_blocks) {
/*      */                   
/*  340 */                   var9 = (byte)Block.mantleOrCore.blockID;
/*      */                 }
/*  342 */                 else if (var8 > 255 - num_bedrock_blocks) {
/*      */                   
/*  344 */                   var9 = (byte)Block.bedrock.blockID;
/*      */                 }
/*      */                 else {
/*      */                   
/*  348 */                   var9 = (byte)Block.stone.blockID;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                   
/*  354 */                   int block_bedrock_id = (byte)Block.bedrock.blockID;
/*      */                   
/*  356 */                   int local_xz_index = var7 + var6 * 16;
/*      */ 
/*      */ 
/*      */                   
/*  360 */                   double bedrock_noise = getMax(ChunkProviderUnderworld.bedrock_strata_1a_noise[local_xz_index], ChunkProviderUnderworld.bedrock_strata_1b_noise[local_xz_index]);
/*      */                   
/*  362 */                   int dy = var8 - 3;
/*      */                   
/*  364 */                   double bump_noise = ChunkProviderUnderworld.bedrock_strata_1a_bump_noise[local_xz_index];
/*      */                   
/*  366 */                   if (bump_noise > 0.0D) {
/*  367 */                     bedrock_noise += bump_noise * 0.25D;
/*      */                   }
/*  369 */                   bump_noise = ChunkProviderUnderworld.bedrock_strata_1b_bump_noise[local_xz_index];
/*      */                   
/*  371 */                   if (bump_noise > 0.0D) {
/*  372 */                     bedrock_noise += bump_noise * 0.125D;
/*      */                   }
/*  374 */                   bump_noise = ChunkProviderUnderworld.bedrock_strata_1c_bump_noise[local_xz_index];
/*      */                   
/*  376 */                   if (bump_noise > 0.0D) {
/*  377 */                     bedrock_noise += bump_noise * 0.125D;
/*      */                   }
/*  379 */                   bump_noise = ChunkProviderUnderworld.bedrock_strata_4_bump_noise[local_xz_index];
/*      */                   
/*  381 */                   if (bump_noise > 0.0D) {
/*  382 */                     bedrock_noise += bump_noise * 0.09375D + 0.125D;
/*      */                   }
/*  384 */                   if (bedrock_noise > 0.0D)
/*      */                   {
/*  386 */                     if (dy <= bedrock_noise * 7.0D) {
/*  387 */                       var9 = (byte)Block.bedrock.blockID;
/*      */                     }
/*      */                   }
/*  390 */                   if (var9 != block_bedrock_id) {
/*      */                     
/*  392 */                     dy = var8 - 32;
/*      */                     
/*  394 */                     bedrock_noise = ChunkProviderUnderworld.bedrock_strata_2_noise[local_xz_index] - bedrock_noise * 1.5D;
/*      */                     
/*  396 */                     if (bedrock_noise > 0.0D) {
/*      */ 
/*      */ 
/*      */                       
/*  400 */                       if (dy > 0) {
/*      */                         
/*  402 */                         bump_noise = ChunkProviderUnderworld.bedrock_strata_2_bump_noise[local_xz_index];
/*      */                         
/*  404 */                         if (bump_noise > 0.0D) {
/*  405 */                           bedrock_noise += bump_noise * 0.25D + 0.25D;
/*      */                         }
/*      */                       } 
/*  408 */                       if (dy < 0) {
/*      */                         
/*  410 */                         if (RNG.chance_in_2[++rng_index & 0x7FFF]) {
/*  411 */                           dy++;
/*      */                         }
/*  413 */                         dy = -dy;
/*      */                       } 
/*      */                       
/*  416 */                       if (dy <= bedrock_noise * 2.0D) {
/*  417 */                         var9 = (byte)Block.bedrock.blockID;
/*      */                       }
/*      */                     } 
/*      */                   } 
/*  421 */                   if (var9 != block_bedrock_id) {
/*      */                     
/*  423 */                     dy = var8 - 72;
/*      */                     
/*  425 */                     bedrock_noise = ChunkProviderUnderworld.bedrock_strata_3_noise[local_xz_index] - ChunkProviderUnderworld.bedrock_strata_4_noise[local_xz_index] * 0.375D;
/*      */                     
/*  427 */                     bedrock_noise += 0.5D;
/*      */                     
/*  429 */                     if (bedrock_noise > 0.0D) {
/*      */ 
/*      */ 
/*      */                       
/*  433 */                       if (dy > 0) {
/*      */                         
/*  435 */                         bump_noise = ChunkProviderUnderworld.bedrock_strata_3_bump_noise[local_xz_index];
/*      */                         
/*  437 */                         if (bump_noise > 0.0D) {
/*  438 */                           bedrock_noise += bump_noise * 0.25D + 0.25D;
/*      */                         }
/*      */                       } 
/*  441 */                       if (dy < 0) {
/*      */                         
/*  443 */                         if (RNG.chance_in_2[++rng_index & 0x7FFF]) {
/*  444 */                           dy++;
/*      */                         }
/*  446 */                         dy = -dy;
/*      */                       } 
/*      */                       
/*  449 */                       if (dy <= bedrock_noise * 2.0D) {
/*  450 */                         var9 = (byte)Block.bedrock.blockID;
/*      */                       }
/*      */                     } 
/*      */                   } 
/*  454 */                   if (var9 != block_bedrock_id)
/*      */                   {
/*  456 */                     dy = var8 - 96;
/*      */                     
/*  458 */                     bedrock_noise = ChunkProviderUnderworld.bedrock_strata_4_noise[local_xz_index] - ChunkProviderUnderworld.bedrock_strata_3_noise[local_xz_index] * 0.375D;
/*      */                     
/*  460 */                     bedrock_noise += 0.5D;
/*      */                     
/*  462 */                     if (bedrock_noise > 0.0D)
/*      */                     {
/*      */ 
/*      */                       
/*  466 */                       if (dy > 0) {
/*      */                         
/*  468 */                         bump_noise = ChunkProviderUnderworld.bedrock_strata_4_bump_noise[local_xz_index];
/*      */                         
/*  470 */                         if (bump_noise > 0.0D) {
/*  471 */                           bedrock_noise += bump_noise * 0.25D + 0.25D;
/*      */                         }
/*      */                       } 
/*  474 */                       if (dy < 0) {
/*      */                         
/*  476 */                         if (RNG.chance_in_2[++rng_index & 0x7FFF]) {
/*  477 */                           dy++;
/*      */                         }
/*  479 */                         dy = -dy;
/*      */                       } 
/*      */                       
/*  482 */                       if (dy <= bedrock_noise * 2.0D) {
/*  483 */                         var9 = (byte)Block.bedrock.blockID;
/*      */                       }
/*      */                     }
/*      */                   
/*      */                   }
/*      */                 
/*      */                 } 
/*      */               } else {
/*      */                 
/*  492 */                 index = var6 << 11 | var7 << 7 | var8;
/*      */                 
/*  494 */                 var9 = (index < 0 || index >= par2ArrayOfByte.length) ? (byte)Block.stone.blockID : par2ArrayOfByte[index];
/*      */                 
/*  496 */                 var8 += y_offset;
/*      */               } 
/*      */               
/*  499 */               if (var9 != 0) {
/*      */                 
/*  501 */                 int var10 = var8 >> 4;
/*      */                 
/*  503 */                 if (this.storageArrays[var10] == null)
/*      */                 {
/*  505 */                   this.storageArrays[var10] = new ExtendedBlockStorage(var10 << 4, !par1World.provider.hasNoSky);
/*      */                 }
/*      */                 
/*  508 */                 this.storageArrays[var10].setExtBlockID(var6, var8 & 0xF, var7, var9);
/*      */                 
/*  510 */                 if (Block.lightValue[var9] > 0) {
/*      */                   
/*  512 */                   this.storageArrays[var10].setExtBlocklightValue(var6, var8 & 0xF, var7, Block.lightValue[var9]);
/*      */                   
/*  514 */                   if (par2ArrayOfByte[index + 1] == 0) {
/*  515 */                     addPendingBlocklightUpdate(base_x + var6, var8, base_z + var7);
/*      */                   }
/*      */                 } 
/*      */               } 
/*  519 */               var8 -= y_offset; var8++;
/*      */               continue;
/*      */             }  }
/*      */         
/*      */         } 
/*      */       } 
/*      */     } else {
/*  526 */       Chunk chunk_west = getNeighboringChunkIfItExists(-1, 0);
/*  527 */       Chunk chunk_east = getNeighboringChunkIfItExists(1, 0);
/*  528 */       Chunk chunk_north = getNeighboringChunkIfItExists(0, -1);
/*  529 */       Chunk chunk_south = getNeighboringChunkIfItExists(0, 1);
/*      */       
/*  531 */       int base_x = this.xPosition * 16;
/*  532 */       int base_z = this.zPosition * 16;
/*      */       
/*  534 */       for (int var6 = 0; var6 < 16; var6++) {
/*      */         
/*  536 */         for (int var7 = 0; var7 < 16; var7++) {
/*      */           
/*  538 */           for (int var8 = 0; var8 < var5; var8++) {
/*      */ 
/*      */ 
/*      */             
/*  542 */             int index = var6 << 11 | var7 << 7 | var8;
/*  543 */             byte var9 = par2ArrayOfByte[index];
/*      */             
/*  545 */             if (var9 != 0) {
/*      */               
/*  547 */               int var10 = var8 >> 4;
/*      */               
/*  549 */               if (this.storageArrays[var10] == null)
/*      */               {
/*  551 */                 this.storageArrays[var10] = new ExtendedBlockStorage(var10 << 4, !par1World.provider.hasNoSky);
/*      */               }
/*      */               
/*  554 */               this.storageArrays[var10].setExtBlockID(var6, var8 & 0xF, var7, var9);
/*      */               
/*  556 */               if (Block.lightValue[var9] > 0) {
/*      */                 
/*  558 */                 this.storageArrays[var10].setExtBlocklightValue(var6, var8 & 0xF, var7, Block.lightValue[var9]);
/*      */                 
/*  560 */                 if (par2ArrayOfByte[var6 << 11 | var7 << 7 | var8 + 1] == 0)
/*  561 */                   addPendingBlocklightUpdate(base_x + var6, var8, base_z + var7);  continue;
/*      */               } 
/*  563 */               if (var9 == Block.waterStill.blockID) {
/*      */                 
/*  565 */                 if (var8 > 0 && par2ArrayOfByte[index - 1] == 0) {
/*      */                   
/*  567 */                   par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10); continue;
/*      */                 } 
/*  569 */                 if (var8 == 62) {
/*      */                   
/*  571 */                   if (var6 == 0) {
/*      */                     
/*  573 */                     if (chunk_west != null && chunk_west.getBlockIDOptimized(0xF | var7 << 4, var8) == 0) {
/*      */                       
/*  575 */                       par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                       
/*      */                       continue;
/*      */                     } 
/*  579 */                   } else if (par2ArrayOfByte[index - 2048] == 0) {
/*      */                     
/*  581 */                     par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                     
/*      */                     continue;
/*      */                   } 
/*  585 */                   if (var6 == 15) {
/*      */                     
/*  587 */                     if (chunk_east != null && chunk_east.getBlockIDOptimized(0x0 | var7 << 4, var8) == 0) {
/*      */                       
/*  589 */                       par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                       
/*      */                       continue;
/*      */                     } 
/*  593 */                   } else if (par2ArrayOfByte[index + 2048] == 0) {
/*      */                     
/*  595 */                     par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                     
/*      */                     continue;
/*      */                   } 
/*  599 */                   if (var7 == 0) {
/*      */                     
/*  601 */                     if (chunk_north != null && chunk_north.getBlockIDOptimized(var6 | 0xF0, var8) == 0) {
/*      */                       
/*  603 */                       par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                       
/*      */                       continue;
/*      */                     } 
/*  607 */                   } else if (par2ArrayOfByte[index - 128] == 0) {
/*      */                     
/*  609 */                     par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                     
/*      */                     continue;
/*      */                   } 
/*  613 */                   if (var7 == 15) {
/*      */                     
/*  615 */                     if (chunk_south != null && chunk_south.getBlockIDOptimized(var6 | 0x0, var8) == 0)
/*      */                     {
/*  617 */                       par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                     
/*      */                     }
/*      */                   }
/*  621 */                   else if (par2ArrayOfByte[index + 128] == 0) {
/*      */                     
/*  623 */                     par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7, var9, var9 - 1, 0, 10);
/*      */                   } 
/*      */                 } 
/*      */               } 
/*      */               
/*      */               continue;
/*      */             } 
/*      */             
/*  631 */             if (var8 == 62)
/*      */             {
/*  633 */               if (var6 == 0 && chunk_west != null && chunk_west.getBlockIDOptimized(0xF | var7 << 4, var8) == water_still_block_id) {
/*      */                 
/*  635 */                 par1World.scheduleBlockChange(base_x + var6 - 1, var8, base_z + var7, water_still_block_id, water_still_block_id - 1, 0, 10);
/*      */ 
/*      */               
/*      */               }
/*  639 */               else if (var6 == 15 && chunk_east != null && chunk_east.getBlockIDOptimized(0x0 | var7 << 4, var8) == water_still_block_id) {
/*      */                 
/*  641 */                 par1World.scheduleBlockChange(base_x + var6 + 1, var8, base_z + var7, water_still_block_id, water_still_block_id - 1, 0, 10);
/*      */ 
/*      */               
/*      */               }
/*  645 */               else if (var7 == 0 && chunk_north != null && chunk_north.getBlockIDOptimized(var6 | 0xF0, var8) == water_still_block_id) {
/*      */                 
/*  647 */                 par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7 - 1, water_still_block_id, water_still_block_id - 1, 0, 10);
/*      */ 
/*      */               
/*      */               }
/*  651 */               else if (var7 == 15 && chunk_south != null && chunk_south.getBlockIDOptimized(var6 | 0x0, var8) == water_still_block_id) {
/*      */                 
/*  653 */                 par1World.scheduleBlockChange(base_x + var6, var8, base_z + var7 + 1, water_still_block_id, water_still_block_id - 1, 0, 10);
/*      */               } 
/*      */             }
/*      */             continue;
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
/*      */   public final boolean isAtLocation(int par1, int par2) {
/*  670 */     return (par1 == this.xPosition && par2 == this.zPosition);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getHeightValue(int par1, int par2) {
/*  678 */     return this.is_empty ? 0 : this.heightMap[par2 << 4 | par1];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getTopFilledSegment() {
/*  687 */     if (this.is_empty) {
/*  688 */       return 0;
/*      */     }
/*  690 */     for (int var1 = this.storageArrays.length - 1; var1 >= 0; var1--) {
/*      */       
/*  692 */       if (this.storageArrays[var1] != null)
/*      */       {
/*  694 */         return this.storageArrays[var1].getYLocation();
/*      */       }
/*      */     } 
/*      */     
/*  698 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final ExtendedBlockStorage[] getBlockStorageArray() {
/*  707 */     return this.storageArrays;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int calcHeightMapValue(int local_x, int top_filled_segment_plus_15, int local_z) {
/*  713 */     int index = local_x + local_z * 16;
/*      */     
/*  715 */     for (int y = top_filled_segment_plus_15; y >= 0; y--) {
/*      */ 
/*      */       
/*  718 */       int block_id = getBlockIDOptimized(index, y);
/*      */       
/*  720 */       if (block_id > 0 && Block.lightOpacity[block_id] > 0) {
/*  721 */         return y + 1;
/*      */       }
/*      */     } 
/*  724 */     return 0;
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
/*      */   public final void generateHeightMap(boolean allow_overwrite) {
/*  745 */     if (this.is_empty) {
/*      */       
/*  747 */       Minecraft.setErrorMessage("generateHeightMapMITE: called for empty chunk on " + this.worldObj.getClientOrServerString());
/*      */       
/*      */       return;
/*      */     } 
/*  751 */     if (this.has_initial_heightmap_been_generated && !allow_overwrite) {
/*      */       
/*  753 */       Minecraft.setErrorMessage("generateHeightMapMITE: initial heightmap has already been generated for " + this);
/*  754 */       Debug.printStackTrace();
/*      */       
/*      */       return;
/*      */     } 
/*  758 */     this.has_initial_heightmap_been_generated = true;
/*      */ 
/*      */ 
/*      */     
/*  762 */     int top_filled_segment_plus_15 = getTopFilledSegment() + 15;
/*      */     
/*  764 */     for (int local_x = 0; local_x < 16; local_x++) {
/*      */       
/*  766 */       for (int local_z = 0; local_z < 16; local_z++) {
/*      */         
/*  768 */         int index = local_x + local_z * 16;
/*      */         
/*  770 */         this.precipitationHeightMap[index] = -999;
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
/*  782 */         setHeightMap(index, calcHeightMapValue(local_x, top_filled_segment_plus_15, local_z));
/*      */       } 
/*      */     } 
/*      */     
/*  786 */     this.isModified = true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void generateSkylightColumn(int local_x, int local_z, int top_segment_plus_16, int new_heightmap_value, boolean mark_blocks_for_render_update) {
/*  888 */     propagateSkylightOcclusion(local_x, local_z);
/*      */     
/*  890 */     int index = local_x + local_z * 16;
/*      */     
/*  892 */     this.skylight_bottom[index] = skylight_bottom_initial_value;
/*      */     
/*  894 */     for (int y = new_heightmap_value; y < top_segment_plus_16; y++) {
/*      */       
/*  896 */       ExtendedBlockStorage ebs = this.storageArrays[y >> 4];
/*      */       
/*  898 */       if (ebs != null)
/*      */       {
/*      */ 
/*      */         
/*  902 */         ebs.setExtSkylightValue(index, y, 15, this);
/*      */       }
/*      */     } 
/*  905 */     int brightness = 15;
/*      */     
/*  907 */     for (int i = new_heightmap_value - 1; i >= 0 && brightness > 0; i--) {
/*      */       
/*  909 */       int opacity = Block.lightOpacity[getBlockID(local_x, i, local_z)];
/*      */       
/*  911 */       if (opacity > 0 && new_heightmap_value - 1 > i) {
/*  912 */         opacity++;
/*      */       }
/*  914 */       if (opacity == 0) {
/*  915 */         opacity = 1;
/*      */       }
/*  917 */       if (brightness > 0 && brightness - opacity <= 0) {
/*  918 */         this.skylight_bottom[index] = i + 1;
/*      */       }
/*  920 */       brightness -= opacity;
/*      */       
/*  922 */       if (brightness < 0) {
/*  923 */         brightness = 0;
/*      */       }
/*  925 */       int ebs_index = i >> 4;
/*      */       
/*  927 */       ExtendedBlockStorage var10 = this.storageArrays[ebs_index];
/*      */       
/*  929 */       if (var10 == null) {
/*      */         
/*  931 */         var10 = new ExtendedBlockStorage(i >> 4 << 4, !this.worldObj.provider.hasNoSky);
/*  932 */         this.storageArrays[ebs_index] = var10;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  938 */       var10.setExtSkylightValue(index, i, brightness, this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void generateSkylightMap(boolean generate_heightmap) {
/*  947 */     if (this.is_empty) {
/*      */       
/*  949 */       Minecraft.setErrorMessage("generateSkylightMapMITE: called for empty chunk on " + this.worldObj.getClientOrServerString());
/*      */       
/*      */       return;
/*      */     } 
/*  953 */     if (this.worldObj.isRemote) {
/*      */       
/*  955 */       Minecraft.setErrorMessage("generateSkylightMapMITE: called on client?");
/*      */       
/*      */       return;
/*      */     } 
/*  959 */     if (!hasSkylight()) {
/*  960 */       Debug.setErrorMessage("generateSkylightMapMITE: called for world without skylight?");
/*      */     }
/*  962 */     if (this.has_initial_skymap_been_generated) {
/*      */       
/*  964 */       Minecraft.setErrorMessage("generateSkylightMapMITE: already called for " + this);
/*  965 */       Debug.printStackTrace();
/*      */     } 
/*      */ 
/*      */     
/*  969 */     this.has_initial_skymap_been_generated = true;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  975 */     if (generate_heightmap) {
/*  976 */       generateHeightMap(false);
/*      */     }
/*  978 */     int top_filled_segment_plus_16 = getTopFilledSegment() + 16;
/*      */     
/*  980 */     if (hasSkylight())
/*      */     {
/*  982 */       for (int local_x = 0; local_x < 16; local_x++) {
/*      */         
/*  984 */         for (int local_z = 0; local_z < 16; local_z++) {
/*      */           
/*  986 */           int index = local_x + local_z * 16;
/*      */ 
/*      */           
/*  989 */           generateSkylightColumn(local_x, local_z, top_filled_segment_plus_16, this.heightMap[index], false);
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
/*      */   private final void propagateSkylightOcclusion(int par1, int par2) {
/* 1026 */     this.updateSkylightColumns[par1 + par2 * 16] = true;
/* 1027 */     this.isGapLightingUpdated = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void propagateSkylightOcclusion() {
/* 1032 */     Arrays.fill(this.updateSkylightColumns, true);
/* 1033 */     this.isGapLightingUpdated = true;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean[] generateSkylightPropagationForNeighbor(int neighbor_dx, int neighbor_dz) {
/* 1038 */     boolean[] update_skylight_columns = new boolean[256];
/*      */     
/* 1040 */     if (neighbor_dx == -1 && neighbor_dz == -1) {
/*      */       
/* 1042 */       for (int x = 1; x < 16; x++) {
/*      */         
/* 1044 */         for (int z = 16 - x; z < 16; z++) {
/* 1045 */           update_skylight_columns[x + z * 16] = true;
/*      */         }
/*      */       } 
/* 1048 */     } else if (neighbor_dx == 1 && neighbor_dz == -1) {
/*      */       
/* 1050 */       for (int x = 0; x < 15; x++) {
/*      */         
/* 1052 */         for (int z = x + 1; z < 16; z++) {
/* 1053 */           update_skylight_columns[x + z * 16] = true;
/*      */         }
/*      */       } 
/* 1056 */     } else if (neighbor_dx == 1 && neighbor_dz == 1) {
/*      */       
/* 1058 */       for (int x = 0; x < 15; x++) {
/*      */         
/* 1060 */         for (int z = 0; z < 15 - x; z++) {
/* 1061 */           update_skylight_columns[x + z * 16] = true;
/*      */         }
/*      */       } 
/* 1064 */     } else if (neighbor_dx == -1 && neighbor_dz == 1) {
/*      */       
/* 1066 */       for (int x = 1; x < 16; x++) {
/*      */         
/* 1068 */         for (int z = 0; z < x; z++) {
/* 1069 */           update_skylight_columns[x + z * 16] = true;
/*      */         }
/*      */       } 
/*      */     } else {
/*      */       
/* 1074 */       Minecraft.setErrorMessage("generateSkylightPropagationForNeighbor: unhandled case");
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
/* 1087 */     return update_skylight_columns;
/*      */   }
/*      */ 
/*      */   
/*      */   private static boolean[] getSkylightPropagationForNeighbor(int neighbor_dx, int neighbor_dz) {
/* 1092 */     if (neighbor_dx == -1 && neighbor_dz == -1)
/* 1093 */       return skylight_propagation_from_neighbor_to_nw; 
/* 1094 */     if (neighbor_dx == 1 && neighbor_dz == -1)
/* 1095 */       return skylight_propagation_from_neighbor_to_ne; 
/* 1096 */     if (neighbor_dx == 1 && neighbor_dz == 1)
/* 1097 */       return skylight_propagation_from_neighbor_to_se; 
/* 1098 */     if (neighbor_dx == -1 && neighbor_dz == 1) {
/* 1099 */       return skylight_propagation_from_neighbor_to_sw;
/*      */     }
/* 1101 */     Minecraft.setErrorMessage("getSkylightPropagationForNeighbor: unhandled neighbor");
/* 1102 */     return null;
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
/*      */   public void propagateSkylightFromNeighbor(int neighbor_dx, int neighbor_dz) {
/* 1126 */     boolean[] update_skylight_columns = getSkylightPropagationForNeighbor(neighbor_dx, neighbor_dz);
/*      */     
/* 1128 */     for (int i = 0; i < update_skylight_columns.length; i++) {
/*      */       
/* 1130 */       if (update_skylight_columns[i]) {
/* 1131 */         this.updateSkylightColumns[i] = true;
/*      */       }
/*      */     } 
/* 1134 */     this.isGapLightingUpdated = true;
/*      */     
/* 1136 */     updateSkylight(true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private final int getSkylightBottom(int local_x, int local_z) {
/* 1142 */     return this.skylight_bottom[local_x + local_z * 16];
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getLowestSkylightBottom(int var1, int var2) {
/* 1148 */     int min_y = getSkylightBottom(var1, var2);
/*      */     
/* 1150 */     if (var1 == 0) {
/* 1151 */       min_y = Math.min(min_y, this.worldObj.getChunkFromChunkCoords(this.xPosition - 1, this.zPosition).getSkylightBottom(15, var2));
/*      */     } else {
/* 1153 */       min_y = Math.min(min_y, getSkylightBottom(var1 - 1, var2));
/*      */     } 
/* 1155 */     if (var1 == 15) {
/* 1156 */       min_y = Math.min(min_y, this.worldObj.getChunkFromChunkCoords(this.xPosition + 1, this.zPosition).getSkylightBottom(0, var2));
/*      */     } else {
/* 1158 */       min_y = Math.min(min_y, getSkylightBottom(var1 + 1, var2));
/*      */     } 
/* 1160 */     if (var2 == 0) {
/* 1161 */       min_y = Math.min(min_y, this.worldObj.getChunkFromChunkCoords(this.xPosition, this.zPosition - 1).getSkylightBottom(var1, 15));
/*      */     } else {
/* 1163 */       min_y = Math.min(min_y, getSkylightBottom(var1, var2 - 1));
/*      */     } 
/* 1165 */     if (var2 == 15) {
/* 1166 */       min_y = Math.min(min_y, this.worldObj.getChunkFromChunkCoords(this.xPosition, this.zPosition + 1).getSkylightBottom(var1, 0));
/*      */     } else {
/* 1168 */       min_y = Math.min(min_y, getSkylightBottom(var1, var2 + 1));
/*      */     } 
/* 1170 */     return min_y;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean updateSkylight_do(boolean trusted_xz_for_this_chunk_and_immediate_neighbors) {
/*      */     boolean update_occured;
/* 1179 */     this.worldObj.theProfiler.startSection("recheckGaps");
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1184 */     if (trusted_xz_for_this_chunk_and_immediate_neighbors || this.worldObj.canUpdateLightByType(this.xPosition * 16, this.zPosition * 16)) {
/*      */       
/* 1186 */       for (int var1 = 0; var1 < 16; var1++) {
/*      */         
/* 1188 */         for (int var2 = 0; var2 < 16; var2++) {
/*      */           
/* 1190 */           if (this.updateSkylightColumns[var1 + var2 * 16]) {
/*      */             
/* 1192 */             this.updateSkylightColumns[var1 + var2 * 16] = false;
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1197 */             int var3 = getHeightValue(var1, var2);
/* 1198 */             int var4 = this.xPosition * 16 + var1;
/* 1199 */             int var5 = this.zPosition * 16 + var2;
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
/* 1220 */             int min_y = getLowestSkylightBottom(var1, var2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/* 1229 */             this; checkSkylightNeighborHeight(var4 - 1, var5, var3, (var1 > 0) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition - 1, this.zPosition), trusted_xz_for_this_chunk_and_immediate_neighbors);
/* 1230 */             this; checkSkylightNeighborHeight(var4 + 1, var5, var3, (var1 < 15) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition + 1, this.zPosition), trusted_xz_for_this_chunk_and_immediate_neighbors);
/* 1231 */             this; checkSkylightNeighborHeight(var4, var5 - 1, var3, (var2 > 0) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition, this.zPosition - 1), trusted_xz_for_this_chunk_and_immediate_neighbors);
/* 1232 */             this; checkSkylightNeighborHeight(var4, var5 + 1, var3, (var2 < 15) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition, this.zPosition + 1), trusted_xz_for_this_chunk_and_immediate_neighbors);
/*      */ 
/*      */             
/* 1235 */             checkSkylightNeighborHeight(var4, var5, min_y, this, true);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/* 1240 */       this.isGapLightingUpdated = false;
/*      */       
/* 1242 */       update_occured = true;
/*      */     }
/*      */     else {
/*      */       
/* 1246 */       update_occured = false;
/*      */       
/* 1248 */       Debug.setErrorMessage("updateSkylight_do: wasn't able to update");
/*      */     } 
/*      */     
/* 1251 */     this.worldObj.theProfiler.endSection();
/*      */     
/* 1253 */     return update_occured;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final void checkSkylightNeighborHeight(int par1, int par2, int par3, Chunk chunk, boolean trusted_xz) {
/* 1264 */     int var4 = chunk.heightMap[(par1 & 0xF) + (par2 & 0xF) * 16];
/*      */     
/* 1266 */     if (var4 > par3) {
/*      */ 
/*      */       
/* 1269 */       updateSkylightNeighborHeight(par1, par2, par3, var4, chunk, trusted_xz);
/*      */     }
/* 1271 */     else if (var4 < par3) {
/*      */ 
/*      */       
/* 1274 */       updateSkylightNeighborHeight(par1, par2, var4, par3, chunk, trusted_xz);
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
/*      */   private static final void updateSkylightNeighborHeight(int par1, int par2, int par3, int par4, Chunk chunk, boolean trusted_xz) {
/* 1293 */     if (chunk.isEmpty()) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1300 */     if (par4 >= par3) {
/*      */       
/* 1302 */       if (trusted_xz || chunk.worldObj.canUpdateLightByType(par1, par2)) {
/*      */         
/* 1304 */         for (int var5 = par3; var5 <= par4; var5++) {
/* 1305 */           chunk.worldObj.updateLightByType(EnumSkyBlock.Sky, par1, var5, par2, true, chunk);
/*      */         }
/*      */       } else {
/*      */         
/* 1309 */         for (int var5 = par3; var5 <= par4; var5++) {
/* 1310 */           chunk.addPendingSkylightUpdate(par1, var5, par2);
/*      */         }
/*      */       } 
/*      */       
/* 1314 */       chunk.isModified = true;
/*      */     }
/*      */     else {
/*      */       
/* 1318 */       Debug.setErrorMessage("updateSkylightNeighborHeight: min_y was higher than max_y");
/* 1319 */       Debug.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private int getResultingHeightmapValue(int local_x, int y, int local_z, int opacity, int previous_heightmap_value, boolean update_skylight_above) {
/* 1326 */     int previous_y = previous_heightmap_value - 1;
/*      */     
/* 1328 */     if (y < previous_y) {
/* 1329 */       return previous_heightmap_value;
/*      */     }
/* 1331 */     if (opacity == 0) {
/*      */       
/* 1333 */       if (y == previous_y) {
/*      */         
/* 1335 */         int index = local_x + local_z * 16;
/*      */         
/* 1337 */         if (update_skylight_above) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1342 */           this.storageArrays[y >> 4].setExtSkylightValue(index, y, 15, this);
/*      */ 
/*      */           
/* 1345 */           while (--y >= 0 && Block.lightOpacity[getBlockIDOptimized(index, y)] == 0)
/*      */           {
/* 1347 */             ExtendedBlockStorage ebs = this.storageArrays[y >> 4];
/*      */             
/* 1349 */             if (ebs == null) {
/* 1350 */               ebs = this.storageArrays[y >> 4] = new ExtendedBlockStorage(y >> 4 << 4, hasSkylight());
/*      */             }
/*      */             
/* 1353 */             ebs.setExtSkylightValue(index, y, 15, this);
/*      */           }
/*      */         
/*      */         }
/*      */         else {
/*      */           
/* 1359 */           while (--y >= 0 && Block.lightOpacity[getBlockIDOptimized(index, y)] == 0);
/*      */         } 
/*      */         
/* 1362 */         return y + 1;
/*      */       } 
/*      */       
/* 1365 */       return previous_heightmap_value;
/*      */     } 
/*      */     
/* 1368 */     return y + 1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void relightBlock(int local_x, int y, int local_z, int previous_opacity, int new_opacity) {
/* 1376 */     int original_y = y;
/*      */     
/* 1378 */     int x = getNonLocalX(local_x);
/* 1379 */     int z = getNonLocalZ(local_z);
/*      */     
/* 1381 */     int index = local_x + local_z * 16;
/*      */     
/* 1383 */     int previous_heightmap_value = this.heightMap[index];
/* 1384 */     int previous_skylight_bottom = this.skylight_bottom[index];
/*      */     
/* 1386 */     int new_heightmap_value = getResultingHeightmapValue(local_x, y, local_z, new_opacity, previous_heightmap_value, hasSkylight());
/*      */     
/* 1388 */     setHeightMap(index, new_heightmap_value);
/*      */     
/* 1390 */     if (hasSkylight()) {
/*      */       int brightness;
/* 1392 */       y++;
/*      */ 
/*      */ 
/*      */       
/* 1396 */       if (y >= new_heightmap_value) {
/*      */         
/* 1398 */         brightness = 15;
/*      */       }
/*      */       else {
/*      */         
/* 1402 */         ExtendedBlockStorage ebs = this.storageArrays[y >> 4];
/*      */         
/* 1404 */         brightness = (ebs == null) ? 15 : ebs.getExtSkylightValue(index, y & 0xF);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       do {
/* 1413 */         int opacity = Block.lightOpacity[getBlockIDOptimized(index, --y)];
/*      */         
/* 1415 */         if (opacity > 0) {
/*      */           
/* 1417 */           if (new_heightmap_value - 1 > y) {
/* 1418 */             opacity++;
/*      */           }
/*      */         } else {
/*      */           
/* 1422 */           opacity = 1;
/*      */         } 
/*      */         
/* 1425 */         brightness -= opacity;
/*      */         
/* 1427 */         if (brightness < 0) {
/* 1428 */           brightness = 0;
/*      */         }
/* 1430 */         int ebs_index = y >> 4;
/*      */         
/* 1432 */         ExtendedBlockStorage var10 = this.storageArrays[ebs_index];
/*      */         
/* 1434 */         if (var10 == null) {
/*      */           
/* 1436 */           this.storageArrays[ebs_index] = var10 = new ExtendedBlockStorage(ebs_index << 4, !this.worldObj.provider.hasNoSky);
/* 1437 */           var10.fillSkylightValues(15);
/*      */         } 
/*      */ 
/*      */         
/* 1441 */         var10.setExtSkylightValue(index, y, brightness, this);
/* 1442 */       } while (y > 0 && (y > previous_skylight_bottom || brightness > 0));
/*      */       
/* 1444 */       if (new_opacity < previous_opacity && new_heightmap_value < y) {
/* 1445 */         y = new_heightmap_value;
/* 1446 */       } else if (y < original_y && getBlockLightOpacity(local_x, y, local_z) > 14) {
/* 1447 */         y++;
/*      */       } 
/* 1449 */       this.worldObj.markBlockRangeForRenderUpdate(x, y, z, x, original_y, z);
/*      */       
/* 1451 */       updateSkylightNeighborHeight(x - 1, z, y, original_y, (local_x > 0) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition - 1, this.zPosition), false);
/* 1452 */       updateSkylightNeighborHeight(x + 1, z, y, original_y, (local_x < 15) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition + 1, this.zPosition), false);
/* 1453 */       updateSkylightNeighborHeight(x, z - 1, y, original_y, (local_z > 0) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition, this.zPosition - 1), false);
/* 1454 */       updateSkylightNeighborHeight(x, z + 1, y, original_y, (local_z < 15) ? this : this.worldObj.getChunkFromChunkCoords(this.xPosition, this.zPosition + 1), false);
/*      */       
/* 1456 */       updateSkylightNeighborHeight(x, z, y, original_y, this, false);
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
/* 1468 */     this.isModified = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockLightOpacity(int par1, int par2, int par3) {
/* 1475 */     return this.is_empty ? 255 : Block.lightOpacity[getBlockID(par1, par2, par3)];
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
/*      */   public final int getBlockID(int par1, int par2, int par3) {
/* 1497 */     if (this.is_empty) {
/* 1498 */       return 0;
/*      */     }
/* 1500 */     int par2_shifted = par2 >> 4;
/*      */     
/* 1502 */     if (par2_shifted < this.storageArrays.length) {
/*      */       
/* 1504 */       ExtendedBlockStorage extended_block_storage = this.storageArrays[par2_shifted];
/*      */       
/* 1506 */       if (extended_block_storage != null) {
/*      */ 
/*      */         
/* 1509 */         int par2_and_15 = par2 & 0xF;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1516 */         int var4_2 = extended_block_storage.blockLSBArray[par2_and_15 << 8 | par3 << 4 | par1] & 0xFF;
/*      */         
/* 1518 */         if (extended_block_storage.blockMSBArray != null) {
/*      */ 
/*      */ 
/*      */           
/* 1522 */           int var7_2 = par2_and_15 << 8 | par3 << 4 | par1;
/* 1523 */           int var5_2 = var7_2 >> 1;
/*      */           
/* 1525 */           return ((var7_2 & 0x1) == 0) ? (extended_block_storage.blockMSBArray.data[var5_2] & 0xF) : (extended_block_storage.blockMSBArray.data[var5_2] >> 4 & 0xF);
/*      */         } 
/*      */ 
/*      */         
/* 1529 */         return var4_2;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/* 1534 */     return 0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockIDOptimized(int xz_index, int y) {
/* 1540 */     ExtendedBlockStorage ebs = this.storageArrays[y >> 4];
/* 1541 */     return (ebs == null) ? 0 : (ebs.blockLSBArray[(y & 0xF) << 8 | xz_index] & 0xFF);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockMetadata(int par1, int par2, int par3) {
/* 1550 */     if (this.is_empty) {
/* 1551 */       return 0;
/*      */     }
/* 1553 */     if (par2 >> 4 >= this.storageArrays.length)
/*      */     {
/* 1555 */       return 0;
/*      */     }
/*      */ 
/*      */     
/* 1559 */     ExtendedBlockStorage var4 = this.storageArrays[par2 >> 4];
/* 1560 */     return (var4 != null) ? var4.getExtBlockMetadata(par1, par2 & 0xF, par3) : 0;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setHeightMap(int index, int value) {
/* 1567 */     this.heightMap[index] = value;
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
/*      */   public final boolean setBlockIDWithMetadata(int par1, int par2, int par3, int par4, int par5, int block_id_before) {
/* 1579 */     if (this.is_empty || !this.is_within_block_domain) {
/* 1580 */       return false;
/*      */     }
/* 1582 */     int var6 = par3 << 4 | par1;
/*      */     
/* 1584 */     if (par2 >= this.precipitationHeightMap[var6] - 1)
/*      */     {
/* 1586 */       this.precipitationHeightMap[var6] = -999;
/*      */     }
/*      */     
/* 1589 */     int var7 = this.heightMap[var6];
/*      */ 
/*      */ 
/*      */     
/* 1593 */     int var9 = getBlockMetadata(par1, par2, par3);
/*      */ 
/*      */ 
/*      */     
/* 1597 */     int var8 = block_id_before;
/*      */     
/* 1599 */     if (var8 == par4 && var9 == par5)
/*      */     {
/* 1601 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1605 */     ExtendedBlockStorage var10 = this.storageArrays[par2 >> 4];
/* 1606 */     boolean var11 = false;
/*      */     
/* 1608 */     if (var10 == null) {
/*      */       
/* 1610 */       if (par4 == 0)
/*      */       {
/* 1612 */         return false;
/*      */       }
/*      */       
/* 1615 */       var10 = this.storageArrays[par2 >> 4] = new ExtendedBlockStorage(par2 >> 4 << 4, !this.worldObj.provider.hasNoSky);
/* 1616 */       var11 = (par2 >= var7);
/*      */     } 
/*      */     
/* 1619 */     int var12 = this.xPosition * 16 + par1;
/* 1620 */     int var13 = this.zPosition * 16 + par3;
/*      */     
/* 1622 */     if (var8 != 0 && !this.worldObj.isRemote)
/*      */     {
/* 1624 */       Block.blocksList[var8].onBlockPreDestroy(this.worldObj, var12, par2, var13, var9);
/*      */     }
/*      */     
/* 1627 */     var10.setExtBlockID(par1, par2 & 0xF, par3, par4);
/*      */ 
/*      */     
/* 1630 */     if (var8 != 0)
/*      */     {
/* 1632 */       if (!this.worldObj.isRemote) {
/*      */         
/* 1634 */         Block.blocksList[var8].breakBlock(this.worldObj, var12, par2, var13, var8, var9);
/*      */       }
/* 1636 */       else if (Block.blocksList[var8] instanceof ITileEntityProvider && var8 != par4) {
/*      */         
/* 1638 */         this.worldObj.removeBlockTileEntity(var12, par2, var13);
/*      */       } 
/*      */     }
/*      */     
/* 1642 */     if (var10.getExtBlockID(par1, par2 & 0xF, par3) != par4)
/*      */     {
/* 1644 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1648 */     var10.setExtBlockMetadata(par1, par2 & 0xF, par3, par5);
/*      */     
/* 1650 */     if (var11 && hasSkylight()) {
/* 1651 */       var10.fillSkylightValues(15);
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
/*      */     
/* 1679 */     int previous_opacity = Block.lightOpacity[var8];
/* 1680 */     int new_opacity = Block.lightOpacity[par4];
/*      */     
/* 1682 */     if (new_opacity != previous_opacity)
/*      */     {
/* 1684 */       if (new_opacity > 0 || par2 < var7)
/*      */       {
/* 1686 */         relightBlock(par1, par2, par3, previous_opacity, new_opacity);
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1693 */     if (par4 != 0) {
/*      */ 
/*      */ 
/*      */       
/* 1697 */       if (!this.worldObj.isRemote || par4 == Block.runestoneMithril.blockID || par4 == Block.runestoneAdamantium.blockID)
/*      */       {
/* 1699 */         Block.blocksList[par4].onBlockAdded(this.worldObj, var12, par2, var13);
/*      */       }
/*      */       
/* 1702 */       if (Block.blocksList[par4] instanceof ITileEntityProvider)
/*      */       {
/* 1704 */         TileEntity var14 = getChunkBlockTileEntity(par1, par2, par3);
/*      */         
/* 1706 */         if (var14 == null) {
/*      */           
/* 1708 */           var14 = ((ITileEntityProvider)Block.blocksList[par4]).createNewTileEntity(this.worldObj);
/* 1709 */           this.worldObj.setBlockTileEntity(var12, par2, var13, var14);
/*      */         } 
/*      */         
/* 1712 */         if (var14 != null)
/*      */         {
/* 1714 */           var14.updateContainingBlockInfo();
/*      */         }
/*      */       }
/*      */     
/* 1718 */     } else if (var8 > 0 && Block.blocksList[var8] instanceof ITileEntityProvider) {
/*      */       
/* 1720 */       TileEntity var14 = getChunkBlockTileEntity(par1, par2, par3);
/*      */       
/* 1722 */       if (var14 != null)
/*      */       {
/* 1724 */         var14.updateContainingBlockInfo();
/*      */       }
/*      */     } 
/*      */     
/* 1728 */     this.isModified = true;
/*      */     
/* 1730 */     this.worldObj.markWorldMapPixelDirty(var12, var13);
/*      */     
/* 1732 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean setBlockMetadata(int par1, int par2, int par3, int par4) {
/* 1743 */     if (this.is_empty) {
/* 1744 */       return false;
/*      */     }
/* 1746 */     if (!this.worldObj.isRemote) {
/*      */       
/* 1748 */       int xz = par3 << 4 | par1;
/*      */       
/* 1750 */       if (par2 >= this.precipitationHeightMap[xz] - 1) {
/* 1751 */         this.precipitationHeightMap[xz] = -999;
/*      */       }
/*      */     } 
/* 1754 */     ExtendedBlockStorage var5 = this.storageArrays[par2 >> 4];
/*      */     
/* 1756 */     if (var5 == null)
/*      */     {
/* 1758 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1762 */     int var6 = var5.getExtBlockMetadata(par1, par2 & 0xF, par3);
/*      */     
/* 1764 */     if (var6 == par4)
/*      */     {
/* 1766 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 1770 */     this.isModified = true;
/* 1771 */     var5.setExtBlockMetadata(par1, par2 & 0xF, par3, par4);
/* 1772 */     int var7 = var5.getExtBlockID(par1, par2 & 0xF, par3);
/*      */     
/* 1774 */     if (var7 > 0 && Block.blocksList[var7] instanceof ITileEntityProvider) {
/*      */       
/* 1776 */       TileEntity var8 = getChunkBlockTileEntity(par1, par2, par3);
/*      */       
/* 1778 */       if (var8 != null) {
/*      */         
/* 1780 */         var8.updateContainingBlockInfo();
/* 1781 */         var8.blockMetadata = par4;
/*      */       } 
/*      */     } 
/*      */     
/* 1785 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getSavedLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/* 1796 */     if (this.is_empty) {
/* 1797 */       return 0;
/*      */     }
/*      */     
/* 1800 */     ExtendedBlockStorage var5 = this.storageArrays[par3 >> 4];
/* 1801 */     return (var5 == null) ? (canBlockSeeTheSky(par2, par3, par4) ? par1EnumSkyBlock.defaultLightValue : 0) : ((par1EnumSkyBlock == EnumSkyBlock.Sky) ? (this.worldObj.provider.hasNoSky ? 0 : var5.getExtSkylightValue(par2, par3 & 0xF, par4)) : ((par1EnumSkyBlock == EnumSkyBlock.Block) ? var5.getExtBlocklightValue(par2, par3 & 0xF, par4) : par1EnumSkyBlock.defaultLightValue));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getSavedSkylightValue(int par2, int par3, int par4) {
/* 1807 */     if (this.is_empty) {
/* 1808 */       return 0;
/*      */     }
/* 1810 */     ExtendedBlockStorage var5 = this.storageArrays[par3 >> 4];
/*      */     
/* 1812 */     if (var5 == null) {
/* 1813 */       return canBlockSeeTheSkyForNonEmptyChunk(par2, par3, par4) ? 15 : 0;
/*      */     }
/* 1815 */     return var5.getExtSkylightValue(par2, par3 & 0xF, par4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getSavedBlocklightValue(int par2, int par3, int par4) {
/* 1821 */     if (this.is_empty) {
/* 1822 */       return 0;
/*      */     }
/* 1824 */     ExtendedBlockStorage var5 = this.storageArrays[par3 >> 4];
/* 1825 */     return (var5 == null) ? 0 : var5.getExtBlocklightValue(par2, par3 & 0xF, par4);
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
/*      */   public final int getSavedLightValueForNonEmptyChunk(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4) {
/* 1849 */     ExtendedBlockStorage var5 = this.storageArrays[par3 >> 4];
/* 1850 */     return (var5 == null) ? (canBlockSeeTheSkyForNonEmptyChunk(par2, par3, par4) ? par1EnumSkyBlock.defaultLightValue : 0) : ((par1EnumSkyBlock == EnumSkyBlock.Sky) ? (this.worldObj.provider.hasNoSky ? 0 : var5.getExtSkylightValue(par2, par3 & 0xF, par4)) : ((par1EnumSkyBlock == EnumSkyBlock.Block) ? var5.getExtBlocklightValue(par2, par3 & 0xF, par4) : par1EnumSkyBlock.defaultLightValue));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getSavedSkylightValueForNonEmptyChunk(int par2, int par3, int par4) {
/* 1856 */     ExtendedBlockStorage var5 = this.storageArrays[par3 >> 4];
/*      */     
/* 1858 */     if (var5 == null) {
/* 1859 */       return canBlockSeeTheSkyForNonEmptyChunk(par2, par3, par4) ? 15 : 0;
/*      */     }
/* 1861 */     return var5.getExtSkylightValue(par2, par3 & 0xF, par4);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getSavedBlocklightValueForNonEmptyChunk(int par2, int par3, int par4) {
/* 1867 */     ExtendedBlockStorage var5 = this.storageArrays[par3 >> 4];
/* 1868 */     return (var5 == null) ? 0 : var5.getExtBlocklightValue(par2, par3 & 0xF, par4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setLightValue(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4, int par5) {
/* 1878 */     if (this.is_empty) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1884 */     ExtendedBlockStorage var6 = this.storageArrays[par3 >> 4];
/*      */     
/* 1886 */     if (var6 == null) {
/*      */       
/* 1888 */       var6 = this.storageArrays[par3 >> 4] = new ExtendedBlockStorage(par3 >> 4 << 4, !this.worldObj.provider.hasNoSky);
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
/* 1905 */       if (hasSkylight()) {
/* 1906 */         var6.fillSkylightValues(EnumSkyBlock.Sky.defaultLightValue);
/*      */       }
/*      */     } 
/* 1909 */     this.isModified = true;
/*      */     
/* 1911 */     if (par1EnumSkyBlock == EnumSkyBlock.Sky) {
/*      */       
/* 1913 */       if (!this.worldObj.provider.hasNoSky)
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1924 */         var6.setExtSkylightValue(par2 + par4 * 16, par3, par5, this);
/*      */       
/*      */       }
/*      */     }
/*      */     else {
/*      */       
/* 1930 */       var6.setExtBlocklightValue(par2, par3 & 0xF, par4, par5);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public final void setSkylightValue(int par2, int par3, int par4, int par5) {
/* 1936 */     if (this.is_empty) {
/*      */       return;
/*      */     }
/* 1939 */     if (!hasSkylight()) {
/* 1940 */       Debug.setErrorMessage("setSkylightValue: chunk does not have skylight");
/*      */     }
/* 1942 */     ExtendedBlockStorage var6 = this.storageArrays[par3 >> 4];
/*      */     
/* 1944 */     if (var6 == null) {
/*      */       
/* 1946 */       var6 = this.storageArrays[par3 >> 4] = new ExtendedBlockStorage(par3 >> 4 << 4, true);
/* 1947 */       var6.fillSkylightValues(15);
/*      */     } 
/*      */     
/* 1950 */     var6.setExtSkylightValue(par2 + par4 * 16, par3, par5, this);
/*      */     
/* 1952 */     this.isModified = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public final void setBlocklightValue(int par2, int par3, int par4, int par5) {
/* 1957 */     if (this.is_empty) {
/*      */       return;
/*      */     }
/* 1960 */     ExtendedBlockStorage var6 = this.storageArrays[par3 >> 4];
/*      */     
/* 1962 */     if (var6 == null)
/*      */     {
/* 1964 */       if (hasSkylight()) {
/*      */         
/* 1966 */         var6 = this.storageArrays[par3 >> 4] = new ExtendedBlockStorage(par3 >> 4 << 4, true);
/* 1967 */         var6.fillSkylightValues(15);
/*      */       }
/*      */       else {
/*      */         
/* 1971 */         var6 = this.storageArrays[par3 >> 4] = new ExtendedBlockStorage(par3 >> 4 << 4, false);
/*      */       } 
/*      */     }
/*      */     
/* 1975 */     var6.setExtBlocklightValue(par2, par3 & 0xF, par4, par5);
/*      */     
/* 1977 */     this.isModified = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getNonLocalX(int local_x) {
/* 1982 */     return (this.xPosition << 4) + local_x;
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getNonLocalZ(int local_z) {
/* 1987 */     return (this.zPosition << 4) + local_z;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int getBlockLightValue(int par1, int par2, int par3, int par4) {
/* 1996 */     if (this.is_empty) {
/* 1997 */       return 0;
/*      */     }
/* 1999 */     ExtendedBlockStorage var5 = this.storageArrays[par2 >> 4];
/*      */     
/* 2001 */     if (var5 == null)
/*      */     {
/* 2003 */       return (!this.worldObj.provider.hasNoSky && par4 < EnumSkyBlock.Sky.defaultLightValue) ? (EnumSkyBlock.Sky.defaultLightValue - par4) : 0;
/*      */     }
/*      */ 
/*      */     
/* 2007 */     int var6 = this.worldObj.provider.hasNoSky ? 0 : var5.getExtSkylightValue(par1, par2 & 0xF, par3);
/*      */     
/* 2009 */     if (var6 > 0)
/*      */     {
/* 2011 */       isLit = true;
/*      */     }
/*      */     
/* 2014 */     var6 -= par4;
/* 2015 */     int var7 = var5.getExtBlocklightValue(par1, par2 & 0xF, par3);
/*      */     
/* 2017 */     if (var7 > var6)
/*      */     {
/* 2019 */       var6 = var7;
/*      */     }
/*      */     
/* 2022 */     return var6;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void addEntity(Entity par1Entity) {
/* 2032 */     if (this.is_empty) {
/*      */       return;
/*      */     }
/* 2035 */     if (par1Entity.isDead) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2041 */     if (par1Entity.isAddedToAChunk()) {
/*      */       
/* 2043 */       Minecraft.setErrorMessage("addEntity: " + par1Entity.getEntityName() + " already belongs to a different chunk!");
/*      */ 
/*      */ 
/*      */       
/* 2047 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/* 2050 */     if (Minecraft.inDevMode())
/*      */     {
/* 2052 */       if (doesEntityObjectExistInEntityLists(par1Entity)) {
/*      */         
/* 2054 */         System.out.println("addEntity: " + par1Entity.getEntityName() + " is already in the entityLists!");
/*      */         
/* 2056 */         (new Exception()).printStackTrace();
/*      */       }
/* 2058 */       else if (par1Entity.isExpectedToHaveUUID() && doesEntityWithMatchingClassAndUUIDExistInEntityLists(par1Entity)) {
/*      */         
/* 2060 */         System.out.println("addEntity: " + par1Entity.getEntityName() + " is already in the entityLists! (UUID match: " + par1Entity.getUniqueID() + " in " + this.worldObj.getDimensionName() + ")");
/*      */         
/* 2062 */         (new Exception()).printStackTrace();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/* 2067 */     int var2 = MathHelper.floor_double(par1Entity.posX / 16.0D);
/* 2068 */     int var3 = MathHelper.floor_double(par1Entity.posZ / 16.0D);
/*      */     
/* 2070 */     if (var2 != this.xPosition || var3 != this.zPosition) {
/*      */       
/* 2072 */       this.worldObj.getWorldLogAgent().logSevere("Wrong location! " + par1Entity);
/* 2073 */       Minecraft.setErrorMessage("addEntity: chunk position is " + this.xPosition + "," + this.zPosition + " but entity's chunk position is " + var2 + "," + var3 + ", block pos is " + par1Entity.posX + "," + par1Entity.posZ);
/* 2074 */       Thread.dumpStack();
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
/* 2091 */     int var4 = par1Entity.getChunkCurrentlyInSectionIndex();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2098 */     par1Entity.setChunkAddedToUnchecked(this, var4);
/*      */ 
/*      */ 
/*      */     
/* 2102 */     if (!this.entityLists[var4].add(par1Entity)) {
/* 2103 */       Minecraft.setErrorMessage("addEntity: was not able to add " + par1Entity.getEntityName() + " to entityLists!");
/*      */     }
/* 2105 */     setChunkModified();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void removeEntity(Entity par1Entity) {
/* 2114 */     if (this.is_empty) {
/*      */       return;
/*      */     }
/*      */     
/* 2118 */     removeEntityThoroughly(par1Entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean removeEntityThoroughly(Entity entity) {
/* 2123 */     int RL = 196;
/*      */     
/* 2125 */     if (Minecraft.inDevMode() && 196 > RL) {
/* 2126 */       System.out.println("Reminder: removeEntityThoroughly is still being used");
/*      */     }
/* 2128 */     if (this.is_empty) {
/* 2129 */       return false;
/*      */     }
/* 2131 */     Chunk chunk = entity.getChunkAddedTo();
/*      */     
/* 2133 */     if (chunk == null) {
/* 2134 */       Minecraft.setErrorMessage("removeEntityThoroughly: entity's chunk_added_to was null");
/* 2135 */     } else if (chunk.xPosition != this.xPosition || chunk.zPosition != this.zPosition) {
/* 2136 */       Minecraft.setErrorMessage("removeEntityThoroughly: entity's chunk_added_to was not this one");
/*      */     } 
/* 2138 */     int num_removals = 0;
/*      */     
/* 2140 */     for (int i = 0; i < 16; i++) {
/*      */       
/* 2142 */       List entity_list = this.entityLists[i];
/*      */       
/* 2144 */       Iterator<Entity> iterator = entity_list.iterator();
/*      */       
/* 2146 */       while (iterator.hasNext()) {
/*      */         
/* 2148 */         Entity entity_in_list = iterator.next();
/*      */         
/* 2150 */         if (entity_in_list == entity) {
/*      */           
/* 2152 */           iterator.remove();
/* 2153 */           num_removals++;
/*      */           continue;
/*      */         } 
/* 2156 */         if (entity.isExpectedToHaveUUID() && entity_in_list.isExpectedToHaveUUID() && entity_in_list.getClass() == entity.getClass() && entity_in_list.getUniqueID().equals(entity.getUniqueID())) {
/*      */           
/* 2158 */           iterator.remove();
/* 2159 */           num_removals++;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 2164 */     if (num_removals > 1) {
/* 2165 */       System.out.println("removeEntityThoroughly: " + entity.getEntityName() + " was removed " + num_removals + " times from chunk's entityLists (UUID=" + entity.getUniqueID() + " in " + this.worldObj.getDimensionName() + ")");
/*      */     }
/* 2167 */     boolean was_removed = (num_removals > 0);
/*      */     
/* 2169 */     if (was_removed) {
/*      */       
/* 2171 */       entity.setChunkAddedToUnchecked(null, -1);
/* 2172 */       setChunkModified();
/*      */     }
/*      */     else {
/*      */       
/* 2176 */       System.out.println("removeEntityThoroughly: was not able to remove " + entity.getEntityName() + " from chunk's entityLists");
/*      */       
/* 2178 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/* 2181 */     return was_removed;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean removeEntityAtIndex(Entity par1Entity, int par2) {
/* 2190 */     if (this.is_empty) {
/* 2191 */       return false;
/*      */     }
/* 2193 */     if (par2 < 0)
/*      */     {
/* 2195 */       par2 = 0;
/*      */     }
/*      */     
/* 2198 */     if (par2 >= this.entityLists.length)
/*      */     {
/* 2200 */       par2 = this.entityLists.length - 1;
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
/* 2214 */     Chunk chunk = par1Entity.getChunkAddedTo();
/*      */     
/* 2216 */     if (chunk == null) {
/* 2217 */       Minecraft.setErrorMessage("removeEntityAtIndex: entity's chunk_added_to was null");
/* 2218 */     } else if (chunk.xPosition != this.xPosition || chunk.zPosition != this.zPosition) {
/* 2219 */       Minecraft.setErrorMessage("removeEntityAtIndex: entity's chunk_added_to was not this one");
/* 2220 */     } else if (par1Entity.chunk_added_to_section_index != par2) {
/* 2221 */       Minecraft.setErrorMessage("removeEntityAtIndex: entity's chunk_added_to_section_index was different from the param received");
/*      */     } 
/* 2223 */     boolean was_removed = this.entityLists[par2].remove(par1Entity);
/*      */     
/* 2225 */     if (was_removed) {
/*      */       
/* 2227 */       par1Entity.setChunkAddedToUnchecked(null, -1);
/* 2228 */       setChunkModified();
/*      */     }
/*      */     else {
/*      */       
/* 2232 */       System.out.println("removeEntityAtIndex: was not able to remove " + par1Entity.getEntityName() + " from chunk's entityLists");
/*      */       
/* 2234 */       (new Exception()).printStackTrace();
/*      */     } 
/*      */     
/* 2237 */     return was_removed;
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
/*      */   public final boolean canBlockSeeTheSky(int par1, int par2, int par3) {
/* 2251 */     return this.is_empty ? false : ((par2 >= this.heightMap[par3 << 4 | par1]));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean canBlockSeeTheSkyForNonEmptyChunk(int par1, int par2, int par3) {
/* 2257 */     return (par2 >= this.heightMap[par3 << 4 | par1]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TileEntity getChunkBlockTileEntity(int par1, int par2, int par3) {
/* 2265 */     ChunkPosition var4 = new ChunkPosition(par1, par2, par3);
/* 2266 */     TileEntity var5 = (TileEntity)this.chunkTileEntityMap.get(var4);
/*      */     
/* 2268 */     if (var5 == null) {
/*      */       
/* 2270 */       int var6 = getBlockID(par1, par2, par3);
/*      */       
/* 2272 */       if (var6 <= 0 || !Block.blocksList[var6].hasTileEntity())
/*      */       {
/* 2274 */         return null;
/*      */       }
/*      */       
/* 2277 */       if (var5 == null) {
/*      */         
/* 2279 */         var5 = ((ITileEntityProvider)Block.blocksList[var6]).createNewTileEntity(this.worldObj);
/* 2280 */         this.worldObj.setBlockTileEntity(this.xPosition * 16 + par1, par2, this.zPosition * 16 + par3, var5);
/*      */       } 
/*      */       
/* 2283 */       var5 = (TileEntity)this.chunkTileEntityMap.get(var4);
/*      */     } 
/*      */     
/* 2286 */     if (var5 != null && var5.isInvalid()) {
/*      */       
/* 2288 */       this.chunkTileEntityMap.remove(var4);
/* 2289 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 2293 */     return var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addTileEntity(TileEntity par1TileEntity) {
/* 2302 */     int var2 = par1TileEntity.xCoord - this.xPosition * 16;
/* 2303 */     int var3 = par1TileEntity.yCoord;
/* 2304 */     int var4 = par1TileEntity.zCoord - this.zPosition * 16;
/* 2305 */     setChunkBlockTileEntity(var2, var3, var4, par1TileEntity);
/*      */     
/* 2307 */     if (this.isChunkLoaded)
/*      */     {
/* 2309 */       this.worldObj.loadedTileEntityList.add(par1TileEntity);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setChunkBlockTileEntity(int par1, int par2, int par3, TileEntity par4TileEntity) {
/* 2318 */     ChunkPosition var5 = new ChunkPosition(par1, par2, par3);
/* 2319 */     par4TileEntity.setWorldObj(this.worldObj);
/* 2320 */     par4TileEntity.xCoord = this.xPosition * 16 + par1;
/* 2321 */     par4TileEntity.yCoord = par2;
/* 2322 */     par4TileEntity.zCoord = this.zPosition * 16 + par3;
/*      */     
/* 2324 */     if (getBlockID(par1, par2, par3) != 0 && Block.blocksList[getBlockID(par1, par2, par3)] instanceof ITileEntityProvider) {
/*      */       
/* 2326 */       if (this.chunkTileEntityMap.containsKey(var5))
/*      */       {
/* 2328 */         ((TileEntity)this.chunkTileEntityMap.get(var5)).invalidate();
/*      */       }
/*      */       
/* 2331 */       par4TileEntity.validate();
/* 2332 */       this.chunkTileEntityMap.put(var5, par4TileEntity);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeChunkBlockTileEntity(int par1, int par2, int par3) {
/* 2341 */     ChunkPosition var4 = new ChunkPosition(par1, par2, par3);
/*      */     
/* 2343 */     if (this.isChunkLoaded) {
/*      */       
/* 2345 */       TileEntity var5 = (TileEntity)this.chunkTileEntityMap.remove(var4);
/*      */       
/* 2347 */       if (var5 != null)
/*      */       {
/* 2349 */         var5.invalidate();
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
/*      */   public void onChunkLoad() {
/* 2361 */     this.isChunkLoaded = true;
/* 2362 */     this.worldObj.addTileEntity(this.chunkTileEntityMap.values());
/*      */ 
/*      */ 
/*      */     
/* 2366 */     List<Entity> list = new ArrayList();
/*      */     
/* 2368 */     for (int var1 = 0; var1 < this.entityLists.length; var1++) {
/*      */       
/* 2370 */       Iterator<?> var2 = this.entityLists[var1].iterator();
/*      */       
/* 2372 */       while (var2.hasNext()) {
/*      */         
/* 2374 */         Entity var3 = (Entity)var2.next();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2380 */         if (Minecraft.inDevMode()) {
/*      */           
/* 2382 */           Entity duplicate = World.getEntityWithSameClassAndUUIDInEntityList("onChunkLoad", var3, list, true);
/*      */           
/* 2384 */           if (duplicate != null) {
/*      */             
/* 2386 */             Minecraft.setErrorMessage("onChunkLoad: A duplicate of " + var3.getEntityName() + " is already being loaded from the chunk. Skipping.");
/*      */             
/*      */             continue;
/*      */           } 
/* 2390 */           duplicate = this.worldObj.getEntityWithSameClassAndUUIDInLoadedEntityList(var3, true);
/*      */           
/* 2392 */           if (duplicate != null && !this.worldObj.isEntityObjectInUnloadedEntityList(duplicate)) {
/*      */             
/* 2394 */             Minecraft.setErrorMessage("onChunkLoad: A duplicate of " + var3.getEntityName() + " already exists in the world. Skipping.");
/*      */             
/*      */             continue;
/*      */           } 
/*      */         } 
/* 2399 */         list.add(var3);
/*      */ 
/*      */ 
/*      */         
/* 2403 */         var3.onChunkLoad();
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2409 */     this.worldObj.addLoadedEntities(list);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void onChunkUnload() {
/* 2419 */     this.isChunkLoaded = false;
/* 2420 */     Iterator<TileEntity> var1 = this.chunkTileEntityMap.values().iterator();
/*      */     
/* 2422 */     while (var1.hasNext()) {
/*      */       
/* 2424 */       TileEntity var2 = var1.next();
/* 2425 */       this.worldObj.markTileEntityForDespawn(var2);
/*      */     } 
/*      */     
/* 2428 */     for (int var3 = 0; var3 < this.entityLists.length; var3++)
/*      */     {
/* 2430 */       this.worldObj.unloadEntities(this.entityLists[var3]);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setChunkModified() {
/* 2440 */     if (this.is_empty) {
/*      */       return;
/*      */     }
/* 2443 */     this.isModified = true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void getEntitiesWithinAABBForEntity(Entity par1Entity, AxisAlignedBB par2AxisAlignedBB, List<Entity> par3List, IEntitySelector par4IEntitySelector) {
/* 2452 */     int var5 = MathHelper.floor_double((par2AxisAlignedBB.minY - 2.0D) / 16.0D);
/* 2453 */     int var6 = MathHelper.floor_double((par2AxisAlignedBB.maxY + 2.0D) / 16.0D);
/*      */     
/* 2455 */     if (var5 < 0) {
/*      */       
/* 2457 */       var5 = 0;
/* 2458 */       var6 = Math.max(var5, var6);
/*      */     } 
/*      */     
/* 2461 */     if (var6 >= this.entityLists.length) {
/*      */       
/* 2463 */       var6 = this.entityLists.length - 1;
/* 2464 */       var5 = Math.min(var5, var6);
/*      */     } 
/*      */     
/* 2467 */     for (int var7 = var5; var7 <= var6; var7++) {
/*      */       
/* 2469 */       List<Entity> var8 = this.entityLists[var7];
/*      */       
/* 2471 */       for (int var9 = 0; var9 < var8.size(); var9++) {
/*      */         
/* 2473 */         Entity var10 = var8.get(var9);
/*      */         
/* 2475 */         if (var10 != par1Entity && var10.boundingBox.intersectsWith(par2AxisAlignedBB) && (par4IEntitySelector == null || par4IEntitySelector.isEntityApplicable(var10))) {
/*      */           
/* 2477 */           par3List.add(var10);
/* 2478 */           Entity[] var11 = var10.getParts();
/*      */           
/* 2480 */           if (var11 != null)
/*      */           {
/* 2482 */             for (int var12 = 0; var12 < var11.length; var12++) {
/*      */               
/* 2484 */               var10 = var11[var12];
/*      */               
/* 2486 */               if (var10 != par1Entity && var10.boundingBox.intersectsWith(par2AxisAlignedBB) && (par4IEntitySelector == null || par4IEntitySelector.isEntityApplicable(var10)))
/*      */               {
/* 2488 */                 par3List.add(var10);
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
/*      */   public void getEntitiesOfTypeWithinAAAB(Class par1Class, AxisAlignedBB par2AxisAlignedBB, List<Entity> par3List, IEntitySelector par4IEntitySelector) {
/* 2502 */     int var5 = MathHelper.floor_double((par2AxisAlignedBB.minY - 2.0D) / 16.0D);
/* 2503 */     int var6 = MathHelper.floor_double((par2AxisAlignedBB.maxY + 2.0D) / 16.0D);
/*      */     
/* 2505 */     if (var5 < 0) {
/*      */       
/* 2507 */       var5 = 0;
/*      */     }
/* 2509 */     else if (var5 >= this.entityLists.length) {
/*      */       
/* 2511 */       var5 = this.entityLists.length - 1;
/*      */     } 
/*      */     
/* 2514 */     if (var6 >= this.entityLists.length) {
/*      */       
/* 2516 */       var6 = this.entityLists.length - 1;
/*      */     }
/* 2518 */     else if (var6 < 0) {
/*      */       
/* 2520 */       var6 = 0;
/*      */     } 
/*      */     
/* 2523 */     for (int var7 = var5; var7 <= var6; var7++) {
/*      */       
/* 2525 */       List<Entity> var8 = this.entityLists[var7];
/*      */       
/* 2527 */       for (int var9 = 0; var9 < var8.size(); var9++) {
/*      */         
/* 2529 */         Entity var10 = var8.get(var9);
/*      */         
/* 2531 */         if (par1Class.isAssignableFrom(var10.getClass()) && var10.boundingBox.intersectsWith(par2AxisAlignedBB) && (par4IEntitySelector == null || par4IEntitySelector.isEntityApplicable(var10)))
/*      */         {
/* 2533 */           par3List.add(var10);
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
/*      */   public final boolean needsSaving(boolean par1) {
/* 2547 */     if (this.is_empty) {
/* 2548 */       return false;
/*      */     }
/* 2550 */     if (this.isModified) {
/* 2551 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 2556 */     if (this.should_be_saved_once_time_forwarding_is_completed && this.worldObj.getTotalWorldTime() - this.last_total_world_time < 100L) {
/* 2557 */       return true;
/*      */     }
/* 2559 */     if (par1) {
/*      */ 
/*      */ 
/*      */       
/* 2563 */       if (this.worldObj.getTotalWorldTime() != this.lastSaveTime && hasEntitiesForWritingToNBT())
/*      */       {
/* 2565 */         return true;
/*      */       
/*      */       }
/*      */     }
/* 2569 */     else if (this.worldObj.getTotalWorldTime() >= this.lastSaveTime + 600L && hasEntitiesForWritingToNBT()) {
/*      */       
/* 2571 */       return true;
/*      */     } 
/*      */     
/* 2574 */     return this.isModified;
/*      */   }
/*      */ 
/*      */   
/*      */   public Random getRandomWithSeed(long par1) {
/* 2579 */     return new Random(this.worldObj.getSeed() + (this.xPosition * this.xPosition * 4987142) + (this.xPosition * 5947611) + (this.zPosition * this.zPosition) * 4392871L + (this.zPosition * 389711) ^ par1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isEmpty() {
/* 2589 */     return this.is_empty;
/*      */   }
/*      */ 
/*      */   
/*      */   public void populateChunk(IChunkProvider par1IChunkProvider, IChunkProvider par2IChunkProvider, int par3, int par4) {
/* 2594 */     if (!this.isTerrainPopulated && par1IChunkProvider.chunkExists(par3 + 1, par4 + 1) && par1IChunkProvider.chunkExists(par3, par4 + 1) && par1IChunkProvider.chunkExists(par3 + 1, par4))
/*      */     {
/* 2596 */       par1IChunkProvider.populate(par2IChunkProvider, par3, par4);
/*      */     }
/*      */     
/* 2599 */     if (par1IChunkProvider.chunkExists(par3 - 1, par4) && !(par1IChunkProvider.provideChunk(par3 - 1, par4)).isTerrainPopulated && par1IChunkProvider.chunkExists(par3 - 1, par4 + 1) && par1IChunkProvider.chunkExists(par3, par4 + 1) && par1IChunkProvider.chunkExists(par3 - 1, par4 + 1))
/*      */     {
/* 2601 */       par1IChunkProvider.populate(par2IChunkProvider, par3 - 1, par4);
/*      */     }
/*      */     
/* 2604 */     if (par1IChunkProvider.chunkExists(par3, par4 - 1) && !(par1IChunkProvider.provideChunk(par3, par4 - 1)).isTerrainPopulated && par1IChunkProvider.chunkExists(par3 + 1, par4 - 1) && par1IChunkProvider.chunkExists(par3 + 1, par4 - 1) && par1IChunkProvider.chunkExists(par3 + 1, par4))
/*      */     {
/* 2606 */       par1IChunkProvider.populate(par2IChunkProvider, par3, par4 - 1);
/*      */     }
/*      */     
/* 2609 */     if (par1IChunkProvider.chunkExists(par3 - 1, par4 - 1) && !(par1IChunkProvider.provideChunk(par3 - 1, par4 - 1)).isTerrainPopulated && par1IChunkProvider.chunkExists(par3, par4 - 1) && par1IChunkProvider.chunkExists(par3 - 1, par4))
/*      */     {
/* 2611 */       par1IChunkProvider.populate(par2IChunkProvider, par3 - 1, par4 - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public final int getPrecipitationHeightNew(int par1, int par2) {
/* 2617 */     if (this.is_empty) {
/* 2618 */       return 0;
/*      */     }
/* 2620 */     int var3 = par1 | par2 << 4;
/* 2621 */     int var4 = this.precipitationHeightMap[var3];
/*      */     
/* 2623 */     if (var4 == -999) {
/*      */       
/* 2625 */       int var5 = getTopFilledSegment() + 15;
/* 2626 */       var4 = -1;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 2631 */       while (var5 > 0) {
/*      */ 
/*      */         
/* 2634 */         int block_id = getBlockIDOptimized(var3, var5);
/*      */         
/* 2636 */         if (block_id == 0) {
/*      */           
/* 2638 */           var5--;
/*      */           
/*      */           continue;
/*      */         } 
/* 2642 */         Block block = Block.getBlock(block_id);
/*      */         
/* 2644 */         if (block.always_blocks_precipitation) {
/*      */           
/* 2646 */           var4 = var5 + 1;
/*      */           break;
/*      */         } 
/* 2649 */         if (block.never_blocks_precipitation) {
/*      */           
/* 2651 */           var5--;
/*      */           continue;
/*      */         } 
/* 2654 */         if (block.blocksPrecipitation(getBlockMetadata(par1, var5, par2))) {
/*      */           
/* 2656 */           var4 = var5 + 1;
/*      */           
/*      */           break;
/*      */         } 
/*      */         
/* 2661 */         var5--;
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 2666 */       this.precipitationHeightMap[var3] = var4;
/*      */     } 
/*      */     
/* 2669 */     return var4;
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
/*      */   public final int getPrecipitationHeight(int par1, int par2) {
/* 2681 */     if (MITEConstant.useNewPrecipitationHeightDetermination()) {
/* 2682 */       return getPrecipitationHeightNew(par1, par2);
/*      */     }
/* 2684 */     int var3 = par1 | par2 << 4;
/* 2685 */     int var4 = this.precipitationHeightMap[var3];
/*      */     
/* 2687 */     if (var4 == -999) {
/*      */       
/* 2689 */       int var5 = getTopFilledSegment() + 15;
/* 2690 */       var4 = -1;
/*      */       
/* 2692 */       while (var5 > 0 && var4 == -1) {
/*      */         boolean is_solid_or_liquid_block;
/* 2694 */         int var6 = getBlockID(par1, var5, par2);
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 2699 */         if (var6 == 0) {
/*      */           
/* 2701 */           is_solid_or_liquid_block = false;
/*      */         }
/*      */         else {
/*      */           
/* 2705 */           Block block = Block.getBlock(var6);
/*      */           
/* 2707 */           if (block.isLiquid()) {
/* 2708 */             is_solid_or_liquid_block = true;
/*      */           } else {
/* 2710 */             is_solid_or_liquid_block = block.isAlwaysSolid() ? true : (block.isNeverSolid() ? false : block.isSolid(getBlockMetadata(par1, var5, par2)));
/*      */           } 
/*      */         } 
/*      */         
/* 2714 */         if (!is_solid_or_liquid_block) {
/*      */           
/* 2716 */           var5--;
/*      */           
/*      */           continue;
/*      */         } 
/* 2720 */         var4 = var5 + 1;
/*      */       } 
/*      */ 
/*      */       
/* 2724 */       this.precipitationHeightMap[var3] = var4;
/*      */     } 
/*      */     
/* 2727 */     return var4;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean updateSkylight(boolean trusted_xz_for_this_chunk_and_immediate_neighbors) {
/* 2736 */     if (this.isGapLightingUpdated && !this.worldObj.provider.hasNoSky)
/*      */     {
/*      */       
/* 2739 */       return updateSkylight_do(trusted_xz_for_this_chunk_and_immediate_neighbors);
/*      */     }
/*      */     
/* 2742 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final ChunkCoordIntPair getChunkCoordIntPair() {
/* 2751 */     return new ChunkCoordIntPair(this.xPosition, this.zPosition);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean getAreLevelsEmpty(int par1, int par2) {
/* 2761 */     if (this.is_empty) {
/* 2762 */       return true;
/*      */     }
/* 2764 */     if (par1 < 0)
/*      */     {
/* 2766 */       par1 = 0;
/*      */     }
/*      */     
/* 2769 */     if (par2 >= 256)
/*      */     {
/* 2771 */       par2 = 255;
/*      */     }
/*      */     
/* 2774 */     for (int var3 = par1; var3 <= par2; var3 += 16) {
/*      */       
/* 2776 */       ExtendedBlockStorage var4 = this.storageArrays[var3 >> 4];
/*      */       
/* 2778 */       if (var4 != null && !var4.isEmpty())
/*      */       {
/* 2780 */         return false;
/*      */       }
/*      */     } 
/*      */     
/* 2784 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setStorageArrays(ExtendedBlockStorage[] par1ArrayOfExtendedBlockStorage) {
/* 2789 */     this.storageArrays = par1ArrayOfExtendedBlockStorage;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void fillChunk(byte[] par1ArrayOfByte, int par2, int par3, boolean par4) {
/* 2797 */     int var5 = 0;
/* 2798 */     boolean var6 = !this.worldObj.provider.hasNoSky;
/*      */     
/*      */     int var7;
/* 2801 */     for (var7 = 0; var7 < this.storageArrays.length; var7++) {
/*      */       
/* 2803 */       if ((par2 & 1 << var7) != 0) {
/*      */         
/* 2805 */         if (this.storageArrays[var7] == null)
/*      */         {
/* 2807 */           this.storageArrays[var7] = new ExtendedBlockStorage(var7 << 4, var6);
/*      */         }
/*      */         
/* 2810 */         byte[] var8 = this.storageArrays[var7].getBlockLSBArray();
/* 2811 */         System.arraycopy(par1ArrayOfByte, var5, var8, 0, var8.length);
/* 2812 */         var5 += var8.length;
/*      */       }
/* 2814 */       else if (par4 && this.storageArrays[var7] != null) {
/*      */         
/* 2816 */         this.storageArrays[var7] = null;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 2822 */     for (var7 = 0; var7 < this.storageArrays.length; var7++) {
/*      */       
/* 2824 */       if ((par2 & 1 << var7) != 0 && this.storageArrays[var7] != null) {
/*      */         
/* 2826 */         NibbleArray var9 = this.storageArrays[var7].getMetadataArray();
/* 2827 */         System.arraycopy(par1ArrayOfByte, var5, var9.data, 0, var9.data.length);
/* 2828 */         var5 += var9.data.length;
/*      */       } 
/*      */     } 
/*      */     
/* 2832 */     for (var7 = 0; var7 < this.storageArrays.length; var7++) {
/*      */       
/* 2834 */       if ((par2 & 1 << var7) != 0 && this.storageArrays[var7] != null) {
/*      */         
/* 2836 */         NibbleArray var9 = this.storageArrays[var7].getBlocklightArray();
/* 2837 */         System.arraycopy(par1ArrayOfByte, var5, var9.data, 0, var9.data.length);
/* 2838 */         var5 += var9.data.length;
/*      */       } 
/*      */     } 
/*      */     
/* 2842 */     if (var6)
/*      */     {
/* 2844 */       for (var7 = 0; var7 < this.storageArrays.length; var7++) {
/*      */         
/* 2846 */         if ((par2 & 1 << var7) != 0 && this.storageArrays[var7] != null) {
/*      */           
/* 2848 */           NibbleArray var9 = this.storageArrays[var7].getSkylightArray();
/* 2849 */           System.arraycopy(par1ArrayOfByte, var5, var9.data, 0, var9.data.length);
/* 2850 */           var5 += var9.data.length;
/*      */         } 
/*      */       } 
/*      */     }
/*      */     
/* 2855 */     for (var7 = 0; var7 < this.storageArrays.length; var7++) {
/*      */       
/* 2857 */       if ((par3 & 1 << var7) != 0) {
/*      */         
/* 2859 */         if (this.storageArrays[var7] == null)
/*      */         {
/* 2861 */           var5 += 2048;
/*      */         }
/*      */         else
/*      */         {
/* 2865 */           NibbleArray var9 = this.storageArrays[var7].getBlockMSBArray();
/*      */           
/* 2867 */           if (var9 == null)
/*      */           {
/* 2869 */             var9 = this.storageArrays[var7].createBlockMSBArray();
/*      */           }
/*      */           
/* 2872 */           System.arraycopy(par1ArrayOfByte, var5, var9.data, 0, var9.data.length);
/* 2873 */           var5 += var9.data.length;
/*      */         }
/*      */       
/* 2876 */       } else if (par4 && this.storageArrays[var7] != null && this.storageArrays[var7].getBlockMSBArray() != null) {
/*      */         
/* 2878 */         this.storageArrays[var7].clearMSBArray();
/*      */       } 
/*      */     } 
/*      */     
/* 2882 */     if (par4) {
/*      */       
/* 2884 */       System.arraycopy(par1ArrayOfByte, var5, this.blockBiomeArray, 0, this.blockBiomeArray.length);
/*      */       
/* 2886 */       var5 += this.blockBiomeArray.length;
/*      */     } 
/*      */ 
/*      */     
/* 2890 */     if (hasSkylight())
/*      */     {
/* 2892 */       for (int i = 0; i < this.skylight_bottom.length; i++) {
/* 2893 */         this.skylight_bottom[i] = par1ArrayOfByte[var5 + i] & 0xFF;
/*      */       }
/*      */     }
/* 2896 */     for (var7 = 0; var7 < this.storageArrays.length; var7++) {
/*      */       
/* 2898 */       if (this.storageArrays[var7] != null && (par2 & 1 << var7) != 0)
/*      */       {
/* 2900 */         this.storageArrays[var7].removeInvalidBlocks();
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 2905 */     generateHeightMap(true);
/*      */     
/* 2907 */     Iterator<TileEntity> var10 = this.chunkTileEntityMap.values().iterator();
/*      */     
/* 2909 */     while (var10.hasNext()) {
/*      */       
/* 2911 */       TileEntity var11 = var10.next();
/* 2912 */       var11.updateContainingBlockInfo();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final BiomeGenBase getBiomeGenForWorldCoords(int par1, int par2, WorldChunkManager par3WorldChunkManager) {
/* 2922 */     int var4 = this.blockBiomeArray[par2 << 4 | par1] & 0xFF;
/*      */     
/* 2924 */     if (var4 == 255) {
/*      */       
/* 2926 */       BiomeGenBase var5 = par3WorldChunkManager.getBiomeGenAt((this.xPosition << 4) + par1, (this.zPosition << 4) + par2);
/* 2927 */       var4 = var5.biomeID;
/* 2928 */       this.blockBiomeArray[par2 << 4 | par1] = (byte)(var4 & 0xFF);
/*      */     } 
/*      */     
/* 2931 */     return (BiomeGenBase.biomeList[var4] == null) ? BiomeGenBase.plains : BiomeGenBase.biomeList[var4];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final byte[] getBiomeArray() {
/* 2940 */     return this.blockBiomeArray;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setBiomeArray(byte[] par1ArrayOfByte) {
/* 2950 */     this.blockBiomeArray = par1ArrayOfByte;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void resetRelightChecks() {
/* 2958 */     this.queuedLightChecks = 0;
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
/*      */   public boolean hasEntitiesForWritingToNBT() {
/* 3068 */     for (int i = 0; i < 16; i++) {
/*      */       
/* 3070 */       List<Entity> entity_list = this.entityLists[i];
/*      */       
/* 3072 */       for (int j = 0; j < entity_list.size(); j++) {
/*      */         
/* 3074 */         Entity entity = entity_list.get(j);
/*      */         
/* 3076 */         if (entity.isWrittenToChunkNBT()) {
/* 3077 */           return true;
/*      */         }
/*      */       } 
/*      */     } 
/* 3081 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkForEntityDuplicates(Entity var21) {
/* 3087 */     int num_object_matches = 0;
/* 3088 */     int num_UUID_matches = 0;
/*      */     
/* 3090 */     boolean[] list_occupied = new boolean[16];
/*      */     
/* 3092 */     for (int i = 0; i < this.entityLists.length; i++) {
/*      */       
/* 3094 */       for (int j = 0; j < this.entityLists[i].size(); j++) {
/*      */         
/* 3096 */         Entity entity = this.entityLists[i].get(j);
/*      */         
/* 3098 */         if (entity == var21) {
/*      */           
/* 3100 */           num_object_matches++;
/* 3101 */           list_occupied[i] = true;
/*      */         }
/* 3103 */         else if (entity.getClass() == var21.getClass() && entity.getUniqueID().equals(var21.getUniqueID())) {
/*      */           
/* 3105 */           num_UUID_matches++;
/* 3106 */           list_occupied[i] = true;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/* 3111 */     int num_matches = num_object_matches + num_UUID_matches;
/*      */ 
/*      */     
/* 3114 */     if (num_object_matches != 1 || num_UUID_matches != 0) {
/*      */       
/* 3116 */       int num_lists_occupied = 0;
/*      */       
/* 3118 */       for (int j = 0; j < 16; j++) {
/*      */         
/* 3120 */         if (list_occupied[j]) {
/* 3121 */           num_lists_occupied++;
/*      */         }
/*      */       } 
/* 3124 */       String msg = "writeChunkToNBT: " + var21.getEntityName() + " duplicated in entityLists " + (num_matches - 1) + " time(s), " + num_object_matches + " object match(es) and " + num_UUID_matches + " UUID match(es), lists occupied:" + num_lists_occupied;
/*      */       
/* 3126 */       if (Minecraft.inDevMode()) {
/* 3127 */         Minecraft.setErrorMessage(msg);
/*      */       } else {
/* 3129 */         System.out.println(msg);
/*      */       } 
/* 3131 */       return true;
/*      */     } 
/*      */     
/* 3134 */     return false;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean doesEntityObjectExistInEntityList(Entity entity, int index) {
/* 3141 */     List<Entity> entity_list = this.entityLists[index];
/*      */     
/* 3143 */     for (int i = 0; i < entity_list.size(); i++) {
/*      */       
/* 3145 */       if (entity_list.get(i) == entity) {
/* 3146 */         return true;
/*      */       }
/*      */     } 
/* 3149 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean doesEntityObjectExistInEntityLists(Entity entity) {
/* 3154 */     int i = 0; this; for (; i < 16; i++) {
/*      */       
/* 3156 */       if (doesEntityObjectExistInEntityList(entity, i)) {
/* 3157 */         return true;
/*      */       }
/*      */     } 
/* 3160 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean doesEntityWithMatchingClassAndUUIDExistInEntityList(Entity entity, int index) {
/* 3165 */     if (this.worldObj.isRemote) {
/* 3166 */       Minecraft.setErrorMessage("doesEntityWithMatchingClassAndUUIDExistInEntityList: Why calling this on client?");
/*      */     }
/* 3168 */     if (!entity.isExpectedToHaveUUID()) {
/* 3169 */       Minecraft.setErrorMessage("doesEntityWithMatchingClassAndUUIDExistInEntityList: entity is not expected to have UUID " + entity);
/*      */     }
/* 3171 */     List<Entity> entity_list = this.entityLists[index];
/*      */     
/* 3173 */     for (int i = 0; i < entity_list.size(); i++) {
/*      */       
/* 3175 */       Entity entity_in_list = entity_list.get(i);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3180 */       if (entity_in_list == entity) {
/* 3181 */         return true;
/*      */       }
/* 3183 */       if (entity_in_list.isExpectedToHaveUUID())
/*      */       {
/*      */         
/* 3186 */         if (entity_in_list.getClass() == entity.getClass() && entity_in_list.getUniqueID().equals(entity.getUniqueID()))
/* 3187 */           return true; 
/*      */       }
/*      */     } 
/* 3190 */     return false;
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
/*      */   public boolean doesEntityWithMatchingClassAndUUIDExistInEntityLists(Entity entity) {
/* 3210 */     int i = 0; this; for (; i < 16; i++) {
/*      */       
/* 3212 */       if (doesEntityWithMatchingClassAndUUIDExistInEntityList(entity, i)) {
/* 3213 */         return true;
/*      */       }
/*      */     } 
/* 3216 */     return false;
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
/*      */   public List[] getEntityListsForReadingOnly() {
/* 3233 */     return this.entityLists;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinBlockX() {
/* 3238 */     return this.xPosition * 16;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMinBlockZ() {
/* 3243 */     return this.zPosition * 16;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxBlockX() {
/* 3248 */     return getMinBlockX() + 15;
/*      */   }
/*      */ 
/*      */   
/*      */   public int getMaxBlockZ() {
/* 3253 */     return getMinBlockZ() + 15;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getChunkCoordFromBlockCoord(int block_coord) {
/* 3259 */     return block_coord >> 4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getChunkCoordFromDouble(double pos) {
/* 3265 */     return MathHelper.floor_double(pos) >> 4;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean doAllNeighborsExist(int range, boolean check_this_chunk, boolean include_empty_chunks) {
/* 3271 */     for (int dx = -range; dx <= range; dx++) {
/*      */       
/* 3273 */       for (int dz = -range; dz <= range; dz++) {
/*      */         
/* 3275 */         if (check_this_chunk || dx != 0 || dz != 0) {
/*      */ 
/*      */           
/* 3278 */           int chunk_x = this.xPosition + dx;
/* 3279 */           int chunk_z = this.zPosition + dz;
/*      */           
/* 3281 */           if (!this.worldObj.chunkExists(chunk_x, chunk_z)) {
/* 3282 */             return false;
/*      */           }
/* 3284 */           if (!include_empty_chunks && this.worldObj.getChunkFromChunkCoords(chunk_x, chunk_z).isEmpty())
/* 3285 */             return false; 
/*      */         } 
/*      */       } 
/*      */     } 
/* 3289 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPendingSkylightUpdate(int x, int y, int z) {
/* 3295 */     if (this.num_pending_skylight_updates == this.max_num_pending_skylight_updates) {
/*      */       
/* 3297 */       this.max_num_pending_skylight_updates *= 2;
/*      */       
/* 3299 */       byte[] pending_skylight_update_coords = new byte[this.max_num_pending_skylight_updates * 2];
/* 3300 */       System.arraycopy(this.pending_skylight_update_coords, 0, pending_skylight_update_coords, 0, this.pending_skylight_update_coords.length);
/*      */ 
/*      */ 
/*      */       
/* 3304 */       this.pending_skylight_update_coords = pending_skylight_update_coords;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3311 */     int local_x = x & 0xF;
/* 3312 */     int local_z = z & 0xF;
/*      */     
/* 3314 */     int index = local_x + local_z * 16 + y * 256;
/*      */     
/* 3316 */     if (this.pending_skylight_updates[index]) {
/*      */       return;
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
/* 3329 */     this.pending_skylight_updates[index] = true;
/*      */     
/* 3331 */     int offset = this.num_pending_skylight_updates * 2;
/*      */     
/* 3333 */     this.pending_skylight_update_coords[offset] = (byte)(local_x | local_z << 4);
/* 3334 */     this.pending_skylight_update_coords[offset + 1] = (byte)y;
/*      */     
/* 3336 */     this.num_pending_skylight_updates++;
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
/*      */   public boolean performPendingSkylightUpdatesIfPossible() {
/* 3352 */     if (this.num_pending_skylight_updates < 1 || !this.worldObj.canUpdateLightByType(this.xPosition * 16, this.zPosition * 16)) {
/* 3353 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 3357 */     int num_pending_skylight_updates_to_perform = this.worldObj.isRemote ? Math.min(this.num_pending_skylight_updates, 64) : this.num_pending_skylight_updates;
/* 3358 */     int num_updates_performed = 0;
/*      */     
/* 3360 */     this.num_pending_skylight_updates = -this.num_pending_skylight_updates;
/*      */ 
/*      */     
/* 3363 */     while (num_updates_performed < num_pending_skylight_updates_to_perform) {
/*      */ 
/*      */       
/* 3366 */       int offset = num_updates_performed * 2;
/*      */       
/* 3368 */       byte local_xz = this.pending_skylight_update_coords[offset];
/*      */       
/* 3370 */       int local_x = local_xz & 0xF;
/* 3371 */       int local_z = local_xz >> 4 & 0xF;
/* 3372 */       int y = this.pending_skylight_update_coords[offset + 1] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3377 */       int index = local_x + local_z * 16 + y * 256;
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
/* 3392 */       this.worldObj.updateLightByType(EnumSkyBlock.Sky, getNonLocalX(local_x), y, getNonLocalZ(local_z), true, this);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3397 */       this.pending_skylight_updates[index] = false;
/*      */       
/* 3399 */       num_updates_performed++;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3406 */     if (num_updates_performed > 63) {
/* 3407 */       setChunkModified();
/*      */     }
/*      */     
/* 3410 */     this.num_pending_skylight_updates = -this.num_pending_skylight_updates - num_updates_performed;
/*      */     
/* 3412 */     if (this.num_pending_skylight_updates == 0) {
/*      */       
/* 3414 */       if (this.max_num_pending_skylight_updates > 256)
/*      */       {
/*      */ 
/*      */         
/* 3418 */         this.max_num_pending_skylight_updates = 256;
/* 3419 */         this.pending_skylight_update_coords = new byte[this.max_num_pending_skylight_updates * 2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3439 */       int num_bytes_processed = num_updates_performed * 2;
/* 3440 */       int num_bytes_remaining = this.num_pending_skylight_updates * 2;
/*      */       
/* 3442 */       for (int i = 0; i < num_bytes_remaining; i++) {
/* 3443 */         this.pending_skylight_update_coords[i] = this.pending_skylight_update_coords[i + num_bytes_processed];
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3454 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void addPendingBlocklightUpdate(int x, int y, int z) {
/* 3460 */     if (this.num_pending_blocklight_updates == this.max_num_pending_blocklight_updates) {
/*      */       
/* 3462 */       this.max_num_pending_blocklight_updates *= 2;
/*      */       
/* 3464 */       byte[] pending_blocklight_update_coords = new byte[this.max_num_pending_blocklight_updates * 2];
/* 3465 */       System.arraycopy(this.pending_blocklight_update_coords, 0, pending_blocklight_update_coords, 0, this.pending_blocklight_update_coords.length);
/*      */ 
/*      */ 
/*      */       
/* 3469 */       this.pending_blocklight_update_coords = pending_blocklight_update_coords;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3476 */     int local_x = x & 0xF;
/* 3477 */     int local_z = z & 0xF;
/*      */     
/* 3479 */     int index = local_x + local_z * 16 + y * 256;
/*      */     
/* 3481 */     if (this.pending_blocklight_updates[index]) {
/*      */       return;
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
/* 3494 */     this.pending_blocklight_updates[index] = true;
/*      */     
/* 3496 */     int offset = this.num_pending_blocklight_updates * 2;
/*      */     
/* 3498 */     this.pending_blocklight_update_coords[offset] = (byte)(local_x | local_z << 4);
/* 3499 */     this.pending_blocklight_update_coords[offset + 1] = (byte)y;
/*      */     
/* 3501 */     this.num_pending_blocklight_updates++;
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
/*      */   public boolean performPendingBlocklightUpdatesIfPossible() {
/* 3517 */     if (this.num_pending_blocklight_updates < 1 || !this.worldObj.canUpdateLightByType(this.xPosition * 16, this.zPosition * 16)) {
/* 3518 */       return false;
/*      */     }
/*      */ 
/*      */     
/* 3522 */     int num_pending_blocklight_updates_to_perform = this.worldObj.isRemote ? Math.min(this.num_pending_blocklight_updates, 64) : this.num_pending_blocklight_updates;
/* 3523 */     int num_updates_performed = 0;
/*      */     
/* 3525 */     this.num_pending_blocklight_updates = -this.num_pending_blocklight_updates;
/*      */ 
/*      */     
/* 3528 */     while (num_updates_performed < num_pending_blocklight_updates_to_perform) {
/*      */ 
/*      */       
/* 3531 */       int offset = num_updates_performed * 2;
/*      */       
/* 3533 */       byte local_xz = this.pending_blocklight_update_coords[offset];
/*      */       
/* 3535 */       int local_x = local_xz & 0xF;
/* 3536 */       int local_z = local_xz >> 4 & 0xF;
/* 3537 */       int y = this.pending_blocklight_update_coords[offset + 1] & 0xFF;
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3542 */       int index = local_x + local_z * 16 + y * 256;
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
/* 3557 */       this.worldObj.updateLightByType(EnumSkyBlock.Block, getNonLocalX(local_x), y, getNonLocalZ(local_z), true, this);
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3562 */       this.pending_blocklight_updates[index] = false;
/*      */       
/* 3564 */       num_updates_performed++;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3571 */     if (num_updates_performed > 63) {
/* 3572 */       setChunkModified();
/*      */     }
/*      */     
/* 3575 */     this.num_pending_blocklight_updates = -this.num_pending_blocklight_updates - num_updates_performed;
/*      */     
/* 3577 */     if (this.num_pending_blocklight_updates == 0) {
/*      */       
/* 3579 */       if (this.max_num_pending_blocklight_updates > 256)
/*      */       {
/*      */ 
/*      */         
/* 3583 */         this.max_num_pending_blocklight_updates = 256;
/* 3584 */         this.pending_blocklight_update_coords = new byte[this.max_num_pending_blocklight_updates * 2];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 3604 */       int num_bytes_processed = num_updates_performed * 2;
/* 3605 */       int num_bytes_remaining = this.num_pending_blocklight_updates * 2;
/*      */       
/* 3607 */       for (int i = 0; i < num_bytes_remaining; i++) {
/* 3608 */         this.pending_blocklight_update_coords[i] = this.pending_blocklight_update_coords[i + num_bytes_processed];
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 3619 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void loadNeighboringChunks(int radius) {
/* 3625 */     for (int dx = -radius; dx <= radius; dx++) {
/*      */       
/* 3627 */       for (int dz = -radius; dz <= radius; dz++) {
/*      */         
/* 3629 */         if (dx != 0 || dz != 0) {
/*      */ 
/*      */           
/* 3632 */           Chunk neighbor = this.worldObj.getChunkFromChunkCoords(this.xPosition + dx, this.zPosition + dz);
/*      */           
/* 3634 */           if (neighbor.isEmpty()) {
/* 3635 */             Debug.setErrorMessage("checkLighting: chunk is empty?");
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public Chunk getNeighboringChunk(int dx, int dz) {
/* 3643 */     if (dx == 0 && dz == 0) {
/* 3644 */       return this;
/*      */     }
/* 3646 */     return this.worldObj.getChunkFromChunkCoords(this.xPosition + dx, this.zPosition + dz);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk getNeighboringChunkIfItExists(int dx, int dz) {
/* 3652 */     if (dx == 0 && dz == 0) {
/* 3653 */       return this;
/*      */     }
/* 3655 */     return this.worldObj.getChunkIfItExists(this.xPosition + dx, this.zPosition + dz);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int recalculateSkylightBottom(int index, int lowest_y) {
/* 3662 */     int ebs_index = lowest_y >> 4;
/*      */     
/* 3664 */     for (int i = ebs_index; i < this.storageArrays.length; i++) {
/*      */       
/* 3666 */       if (this.storageArrays[i] != null) {
/*      */         
/* 3668 */         lowest_y = i * 16 + (lowest_y & 0xF);
/*      */         
/*      */         break;
/*      */       } 
/* 3672 */       if (i == this.storageArrays.length - 1) {
/* 3673 */         return lowest_y;
/*      */       }
/*      */     } 
/* 3676 */     for (int y = lowest_y; y < skylight_bottom_initial_value; y++) {
/*      */       
/* 3678 */       ExtendedBlockStorage ebs = this.storageArrays[y >> 4];
/*      */ 
/*      */       
/* 3681 */       if (ebs != null && ebs.getExtSkylightValue(index, y & 0xF) > 0) {
/* 3682 */         return y;
/*      */       }
/*      */     } 
/* 3685 */     return skylight_bottom_initial_value;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final void recalculateSkylightBottoms() {
/* 3691 */     if (isEmpty()) {
/*      */       return;
/*      */     }
/* 3694 */     for (int local_x = 0; local_x < 16; local_x++) {
/*      */       
/* 3696 */       for (int local_z = 0; local_z < 16; local_z++) {
/*      */ 
/*      */ 
/*      */         
/* 3700 */         int index = local_x + local_z * 16;
/* 3701 */         this.skylight_bottom[index] = recalculateSkylightBottom(index, 0);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean hasSkylight() {
/* 3708 */     return this.has_skylight;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean hasCoords(int chunk_x, int chunk_z) {
/* 3717 */     return (chunk_x == this.xPosition && chunk_z == this.zPosition);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public final boolean isWithinBlockDomain() {
/* 3723 */     if (this.is_empty) {
/* 3724 */       Minecraft.setErrorMessage("isWithinBlockDomain: chunk is empty so it will always return true");
/*      */     }
/* 3726 */     return this.is_within_block_domain;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static int getChunkCoordsHash(int chunk_x, int chunk_z) {
/* 3732 */     int hash = 17;
/*      */     
/* 3734 */     hash = hash * 31 + chunk_x;
/* 3735 */     hash = hash * 31 + chunk_z;
/*      */     
/* 3737 */     return hash;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setHadNaturallyOccurringMycelium() {
/* 3742 */     this.had_naturally_occurring_mycelium = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean getHadNaturallyOccurringMycelium() {
/* 3747 */     return this.had_naturally_occurring_mycelium;
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
/*      */   public boolean performPendingSandFallsIfPossible() {
/* 3769 */     if (this.pending_sand_falls == null || !doAllNeighborsExist(1, false, false)) {
/* 3770 */       return false;
/*      */     }
/* 3772 */     Iterator<Map.Entry> i = this.pending_sand_falls.entrySet().iterator();
/*      */     
/* 3774 */     while (i.hasNext()) {
/*      */       
/* 3776 */       Map.Entry entry = i.next();
/*      */       
/* 3778 */       int xz_index = ((Integer)entry.getKey()).intValue();
/* 3779 */       int y = ((Integer)entry.getValue()).intValue();
/*      */       
/* 3781 */       if (getBlockIDOptimized(xz_index, y) == sand_block_id) {
/*      */         
/* 3783 */         int local_x = xz_index % 16;
/* 3784 */         int local_z = xz_index / 16;
/*      */         
/* 3786 */         int x = getNonLocalX(local_x);
/* 3787 */         int z = getNonLocalZ(local_z);
/*      */         
/* 3789 */         int num_sand_blocks = 1;
/*      */         
/* 3791 */         while (getBlockIDOptimized(xz_index, y + num_sand_blocks) == sand_block_id) {
/* 3792 */           num_sand_blocks++;
/*      */         }
/* 3794 */         int max_y = y + num_sand_blocks - 1;
/*      */         
/* 3796 */         int above_y = max_y;
/*      */         
/* 3798 */         boolean dead_bush = false;
/*      */ 
/*      */         
/*      */         while (true) {
/* 3802 */           int block_id = getBlockIDOptimized(xz_index, ++above_y);
/*      */           
/* 3804 */           if (block_id == 0) {
/*      */             break;
/*      */           }
/* 3807 */           Block block = Block.getBlock(block_id);
/*      */           
/* 3809 */           if (block.isAlwaysLegal()) {
/*      */             break;
/*      */           }
/* 3812 */           if (block.isLegalOn(getBlockMetadata(local_x, above_y, local_z), null, 0)) {
/*      */             break;
/*      */           }
/* 3815 */           setBlockIDWithMetadata(local_x, above_y, local_z, 0, 0, block_id);
/* 3816 */           this.worldObj.markBlockForUpdate(x, above_y, z);
/*      */           
/* 3818 */           if (block == Block.deadBush) {
/*      */             
/* 3820 */             dead_bush = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/* 3825 */         while (getBlockIDOptimized(xz_index, y - 1) == 0) {
/* 3826 */           y--;
/*      */         }
/* 3828 */         int dead_bush_y = dead_bush ? (y + num_sand_blocks) : 0;
/*      */         
/* 3830 */         y--;
/*      */         
/* 3832 */         while (++y <= max_y) {
/* 3833 */           this.worldObj.setBlock(x, y, z, (--num_sand_blocks < 0) ? 0 : sand_block_id);
/*      */         }
/* 3835 */         if (dead_bush) {
/* 3836 */           this.worldObj.setBlock(x, dead_bush_y, z, Block.deadBush.blockID);
/*      */         }
/*      */       } 
/*      */     } 
/* 3840 */     this.pending_sand_falls = null;
/*      */     
/* 3842 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 3847 */     return "Chunk [" + this.xPosition + "," + this.zPosition + "] (block range " + getMinBlockX() + "," + getMinBlockZ() + " to " + getMaxBlockX() + "," + getMaxBlockZ() + ") dimension " + this.worldObj.getDimensionId() + " on " + this.worldObj.getClientOrServerString();
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Chunk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */