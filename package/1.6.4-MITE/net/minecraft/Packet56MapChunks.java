/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Packet56MapChunks
/*     */   extends Packet
/*     */ {
/*     */   private int[] chunkPostX;
/*     */   private int[] chunkPosZ;
/*     */   public int[] field_73590_a;
/*     */   public int[] field_73588_b;
/*     */   private byte[] compressed_chunk_data;
/*     */   private byte[][] field_73584_f;
/*     */   private int compressed_chunk_data_length;
/*     */   private boolean skyLightSent;
/*  31 */   private static byte[] temp_buffer = new byte[0];
/*     */   
/*     */   private static boolean lock_temp_buffer;
/*     */   private static final int max_num_light_updates = 8192;
/*  35 */   private static final int[] update_light_coords = new int[24576];
/*  36 */   private static final int[] coords_to_remove = new int[update_light_coords.length];
/*     */   
/*  38 */   private static final boolean[] scratch = new boolean[36864];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int uncompressed_chunk_data_length;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int determineCandidateLightUpdateLocations(Chunk chunk) {
/*  60 */     int offset = -1;
/*  61 */     int max_offset = update_light_coords.length - 1;
/*     */     
/*  63 */     for (int local_x = 0; local_x < 16; local_x++) {
/*     */       
/*  65 */       for (int local_z = 0; local_z < 16; local_z++) {
/*     */         
/*  67 */         int y = chunk.getHeightValue(local_x, local_z);
/*     */         
/*  69 */         while (--y > 0) {
/*     */           
/*  71 */           int block_id = chunk.getBlockID(local_x, y, local_z);
/*     */           
/*  73 */           if (Block.lightOpacity[block_id] < 4) {
/*     */             
/*  75 */             update_light_coords[++offset] = local_x;
/*  76 */             update_light_coords[++offset] = y;
/*  77 */             update_light_coords[++offset] = local_z;
/*     */             
/*  79 */             if (offset >= max_offset) {
/*     */               
/*  81 */               Debug.println("determineCandidateLightUpdateLocations: Overflow for " + chunk);
/*  82 */               return (offset + 1) / 3;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  89 */     return (offset + 1) / 3;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int performLightUpdates(Chunk chunk, boolean allow_multiple) {
/* 141 */     if (chunk.has_had_lighting_checked && !allow_multiple) {
/* 142 */       Minecraft.setErrorMessage("performLightUpdates: chunk has already been done");
/*     */     }
/* 144 */     int num_candidates = determineCandidateLightUpdateLocations(chunk);
/*     */     
/* 146 */     if (num_candidates == 0) {
/* 147 */       return 0;
/*     */     }
/* 149 */     int num_performed = 0;
/*     */     
/* 151 */     World world = chunk.worldObj;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 156 */     Arrays.fill(scratch, false);
/*     */     
/* 158 */     int offset = -1;
/*     */     
/* 160 */     for (int i = 0; i < num_candidates; i++) {
/* 161 */       scratch[update_light_coords[++offset] + update_light_coords[++offset] * 256 + update_light_coords[++offset] * 16] = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     int num_removals = 0;
/*     */     
/* 199 */     offset = -1;
/* 200 */     int offset_for_removals = -1;
/*     */     int j;
/* 202 */     for (j = 0; j < num_candidates; j++) {
/*     */       
/* 204 */       int local_x = update_light_coords[++offset];
/* 205 */       int y = update_light_coords[++offset];
/* 206 */       int local_z = update_light_coords[++offset];
/*     */       
/* 208 */       if (y > 0 && y < 127 && y % 16 != 0) {
/*     */         
/* 210 */         int index = local_x + y * 256 + local_z * 16;
/*     */         
/* 212 */         if (scratch[index - 256] && scratch[index + 256]) {
/*     */           
/* 214 */           coords_to_remove[++offset_for_removals] = local_x;
/* 215 */           coords_to_remove[++offset_for_removals] = y;
/* 216 */           coords_to_remove[++offset_for_removals] = local_z;
/*     */           
/* 218 */           num_removals++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     offset = -1;
/*     */     
/* 225 */     for (j = 0; j < num_removals; j++) {
/* 226 */       scratch[coords_to_remove[++offset] + coords_to_remove[++offset] * 256 + coords_to_remove[++offset] * 16] = false;
/*     */     }
/* 228 */     offset = -1;
/*     */     
/* 230 */     for (j = 0; j < num_candidates; j++) {
/*     */       
/* 232 */       int local_x = update_light_coords[++offset];
/* 233 */       int y = update_light_coords[++offset];
/* 234 */       int local_z = update_light_coords[++offset];
/*     */       
/* 236 */       int index = local_x + y * 256 + local_z * 16;
/*     */       
/* 238 */       if (!scratch[index]) {
/*     */         continue;
/*     */       }
/* 241 */       if (local_x > 0 && local_x < 15)
/*     */       {
/* 243 */         if (scratch[index - 1] && scratch[index + 1]) {
/*     */           continue;
/*     */         }
/*     */       }
/* 247 */       if (local_z > 0 && local_z < 15)
/*     */       {
/* 249 */         if (scratch[index - 16] && scratch[index + 16]) {
/*     */           continue;
/*     */         }
/*     */       }
/* 253 */       int x = chunk.getNonLocalX(local_x);
/* 254 */       int z = chunk.getNonLocalZ(local_z);
/*     */ 
/*     */       
/* 257 */       world.updateLightByType(EnumSkyBlock.Sky, x, y, z, true, chunk);
/*     */       
/* 259 */       num_performed++;
/*     */       
/*     */       continue;
/*     */     } 
/*     */     
/* 264 */     return num_performed;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet56MapChunks() {}
/*     */ 
/*     */   
/*     */   public Packet56MapChunks(List<Chunk> par1List) {
/*     */     try {
/* 273 */       lockTempBuffer();
/*     */       
/* 275 */       int var2 = par1List.size();
/* 276 */       this.chunkPostX = new int[var2];
/* 277 */       this.chunkPosZ = new int[var2];
/* 278 */       this.field_73590_a = new int[var2];
/* 279 */       this.field_73588_b = new int[var2];
/* 280 */       this.field_73584_f = new byte[var2][];
/* 281 */       this.skyLightSent = (!par1List.isEmpty() && !((Chunk)par1List.get(0)).worldObj.provider.hasNoSky);
/* 282 */       int var3 = 0;
/*     */       
/* 284 */       for (int var4 = 0; var4 < var2; var4++) {
/*     */         
/* 286 */         Chunk var5 = par1List.get(var4);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 304 */         if (MITEConstant.preventLightingArtifacts() && !var5.has_had_lighting_checked) {
/* 305 */           Debug.setErrorMessage("Packet56MapChunks: Lighting was not checked for " + var5);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 331 */         Packet51MapChunkData var6 = Packet51MapChunk.getMapChunkData(var5, true, 65535);
/*     */         
/* 333 */         if (temp_buffer.length < var3 + var6.uncompressed_data.length) {
/*     */           
/* 335 */           byte[] var7 = new byte[var3 + var6.uncompressed_data.length];
/* 336 */           System.arraycopy(temp_buffer, 0, var7, 0, temp_buffer.length);
/* 337 */           temp_buffer = var7;
/*     */         } 
/*     */         
/* 340 */         System.arraycopy(var6.uncompressed_data, 0, temp_buffer, var3, var6.uncompressed_data.length);
/* 341 */         var3 += var6.uncompressed_data.length;
/*     */         
/* 343 */         this.chunkPostX[var4] = var5.xPosition;
/* 344 */         this.chunkPosZ[var4] = var5.zPosition;
/* 345 */         this.field_73590_a[var4] = var6.chunkExistFlag;
/* 346 */         this.field_73588_b[var4] = var6.chunkHasAddSectionFlag;
/* 347 */         this.field_73584_f[var4] = var6.uncompressed_data;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 366 */       this.uncompressed_chunk_data_length = var3;
/*     */     }
/*     */     finally {
/*     */       
/* 370 */       unlockTempBuffer();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void compressPayload() {
/* 376 */     CompressionResult result = tryCompress(temp_buffer, 0, this.uncompressed_chunk_data_length, -1, this);
/*     */     
/* 378 */     this.compressed_chunk_data = result.getOutput();
/* 379 */     this.compressed_chunk_data_length = result.getOutputSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void readPacketData(DataInput par1DataInput) throws IOException {
/*     */     try {
/* 389 */       lockTempBuffer();
/*     */       
/* 391 */       this.uncompressed_chunk_data_length = par1DataInput.readInt();
/* 392 */       short var2 = par1DataInput.readShort();
/* 393 */       this.compressed_chunk_data_length = par1DataInput.readInt();
/* 394 */       this.skyLightSent = par1DataInput.readBoolean();
/* 395 */       this.chunkPostX = new int[var2];
/* 396 */       this.chunkPosZ = new int[var2];
/* 397 */       this.field_73590_a = new int[var2];
/* 398 */       this.field_73588_b = new int[var2];
/* 399 */       this.field_73584_f = new byte[var2][];
/*     */       
/* 401 */       if (temp_buffer.length < this.compressed_chunk_data_length)
/*     */       {
/* 403 */         temp_buffer = new byte[this.compressed_chunk_data_length];
/*     */       }
/*     */       
/* 406 */       par1DataInput.readFully(temp_buffer, 0, this.compressed_chunk_data_length);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 424 */       DecompressionResult result = decompress(temp_buffer, 0, this.compressed_chunk_data_length, this.uncompressed_chunk_data_length, this);
/*     */       
/* 426 */       byte[] var3 = result.getOutput();
/*     */       
/* 428 */       int var5 = 0;
/*     */       
/* 430 */       for (int var6 = 0; var6 < var2; var6++) {
/*     */         
/* 432 */         this.chunkPostX[var6] = par1DataInput.readInt();
/* 433 */         this.chunkPosZ[var6] = par1DataInput.readInt();
/* 434 */         this.field_73590_a[var6] = par1DataInput.readShort();
/* 435 */         this.field_73588_b[var6] = par1DataInput.readShort();
/* 436 */         int var7 = 0;
/* 437 */         int var8 = 0;
/*     */         
/*     */         int var9;
/* 440 */         for (var9 = 0; var9 < 16; var9++) {
/*     */           
/* 442 */           var7 += this.field_73590_a[var6] >> var9 & 0x1;
/* 443 */           var8 += this.field_73588_b[var6] >> var9 & 0x1;
/*     */         } 
/*     */         
/* 446 */         var9 = 8192 * var7 + 256;
/* 447 */         var9 += 2048 * var8;
/*     */         
/* 449 */         if (this.skyLightSent) {
/*     */           
/* 451 */           var9 += 2048 * var7;
/*     */           
/* 453 */           var9 += 256;
/*     */         } 
/*     */         
/* 456 */         this.field_73584_f[var6] = new byte[var9];
/* 457 */         System.arraycopy(var3, var5, this.field_73584_f[var6], 0, var9);
/* 458 */         var5 += var9;
/*     */       } 
/*     */       
/* 461 */       if (var5 != this.uncompressed_chunk_data_length) {
/* 462 */         Debug.setErrorMessage("readPacketData: Bytes read discrepency " + var5 + " vs " + this.uncompressed_chunk_data_length);
/*     */       }
/*     */     } finally {
/*     */       
/* 466 */       unlockTempBuffer();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writePacketData(DataOutput par1DataOutput) throws IOException {
/* 475 */     par1DataOutput.writeInt(this.uncompressed_chunk_data_length);
/* 476 */     par1DataOutput.writeShort(this.chunkPostX.length);
/* 477 */     par1DataOutput.writeInt(this.compressed_chunk_data_length);
/* 478 */     par1DataOutput.writeBoolean(this.skyLightSent);
/* 479 */     par1DataOutput.write(this.compressed_chunk_data, 0, this.compressed_chunk_data_length);
/*     */     
/* 481 */     for (int var2 = 0; var2 < this.chunkPostX.length; var2++) {
/*     */       
/* 483 */       par1DataOutput.writeInt(this.chunkPostX[var2]);
/* 484 */       par1DataOutput.writeInt(this.chunkPosZ[var2]);
/* 485 */       par1DataOutput.writeShort((short)(this.field_73590_a[var2] & 0xFFFF));
/* 486 */       par1DataOutput.writeShort((short)(this.field_73588_b[var2] & 0xFFFF));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processPacket(NetHandler par1NetHandler) {
/* 495 */     par1NetHandler.handleMapChunks(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPacketSize() {
/* 503 */     return 10 + this.compressed_chunk_data_length + 12 * getNumberOfChunkInPacket();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChunkPosX(int par1) {
/* 508 */     return this.chunkPostX[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChunkPosZ(int par1) {
/* 513 */     return this.chunkPosZ[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNumberOfChunkInPacket() {
/* 518 */     return this.chunkPostX.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte[] getChunkCompressedData(int par1) {
/* 523 */     return this.field_73584_f[par1];
/*     */   }
/*     */ 
/*     */   
/*     */   private static void lockTempBuffer() {
/* 528 */     if (lock_temp_buffer) {
/* 529 */       Minecraft.setErrorMessage("lockTempBuffer: Already locked!");
/*     */     } else {
/* 531 */       lock_temp_buffer = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void unlockTempBuffer() {
/* 536 */     if (lock_temp_buffer) {
/* 537 */       lock_temp_buffer = false;
/*     */     } else {
/* 539 */       Minecraft.setErrorMessage("unlockTempBuffer: Already unlocked!");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void checkLighting(Chunk chunk) {
/* 545 */     chunk.performPendingSandFallsIfPossible();
/*     */     
/* 547 */     chunk.performPendingSkylightUpdatesIfPossible();
/* 548 */     chunk.performPendingBlocklightUpdatesIfPossible();
/*     */     
/* 550 */     if (chunk.has_had_lighting_checked) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 556 */     chunk.loadNeighboringChunks(MITEConstant.considerNeighboringChunksInLightingArtifactPrevention() ? 2 : 1);
/*     */     
/* 558 */     checkLighting8(chunk);
/* 559 */     chunk.setChunkModified();
/*     */ 
/*     */     
/* 562 */     chunk.has_had_lighting_checked = true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void checkNeighborLighting(Chunk chunk, int dx, int dz) {
/* 630 */     Chunk neighbor = chunk.getNeighboringChunk(dx, dz);
/*     */     
/* 632 */     neighbor.performPendingSkylightUpdatesIfPossible();
/* 633 */     neighbor.performPendingBlocklightUpdatesIfPossible();
/*     */     
/* 635 */     if (neighbor.hasSkylight() && !neighbor.has_had_lighting_checked) {
/*     */       
/* 637 */       neighbor.propagateSkylightOcclusion();
/* 638 */       neighbor.updateSkylight(true);
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
/*     */   private static void checkLighting8(Chunk chunk) {
/* 654 */     if (MITEConstant.considerNeighboringChunksInLightingArtifactPrevention()) {
/*     */       
/* 656 */       checkNeighborLighting(chunk, -1, -1);
/* 657 */       checkNeighborLighting(chunk, 1, -1);
/* 658 */       checkNeighborLighting(chunk, 1, 1);
/* 659 */       checkNeighborLighting(chunk, -1, 1);
/*     */       
/* 661 */       checkNeighborLighting(chunk, 0, -1);
/* 662 */       checkNeighborLighting(chunk, 1, 0);
/* 663 */       checkNeighborLighting(chunk, 0, 1);
/* 664 */       checkNeighborLighting(chunk, -1, 0);
/*     */     } 
/*     */     
/* 667 */     chunk.performPendingSkylightUpdatesIfPossible();
/* 668 */     chunk.performPendingBlocklightUpdatesIfPossible();
/*     */     
/* 670 */     if (chunk.hasSkylight()) {
/*     */       
/* 672 */       chunk.propagateSkylightOcclusion();
/* 673 */       chunk.updateSkylight(true);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\Packet56MapChunks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */