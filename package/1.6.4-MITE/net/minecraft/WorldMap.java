/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.nio.channels.FileChannel;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WorldMap
/*     */ {
/*     */   private WorldServer world;
/*     */   private ILogAgent log_agent;
/*     */   public int size;
/*     */   public int size_divided_by_2;
/*     */   public int size_squared;
/*  23 */   public final int bytes_per_pixel = 4;
/*     */   
/*     */   public int bytes_in_map;
/*     */   
/*     */   protected ByteBuffer data;
/*     */   
/*     */   private byte[] copy_of_data;
/*     */   
/*     */   private String progressive_filename;
/*     */   
/*     */   private RandomAccessFile progressive_file;
/*     */   
/*  35 */   private final int ticks_between_progressive_writes = 1200;
/*  36 */   private int progressive_write_counter = -1200;
/*     */   private int bytes_written_to_temporary_file;
/*  38 */   private final int bytes_to_write_to_temporary_file_per_tick = 1024;
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldMap(WorldServer world) {
/*  43 */     this.world = world;
/*  44 */     this.log_agent = MinecraftServer.getServer().getLogAgent();
/*     */ 
/*     */     
/*     */     try {
/*  48 */       readFromFile();
/*     */     }
/*  50 */     catch (IOException e) {
/*     */ 
/*     */ 
/*     */       
/*  54 */       this.size = (MinecraftServer.getServer()).default_world_map_size;
/*  55 */       this.size_divided_by_2 = this.size / 2;
/*  56 */       this.size_squared = this.size * this.size;
/*  57 */       this.bytes_in_map = this.size_squared * 4;
/*     */ 
/*     */       
/*  60 */       this.data = ByteBuffer.allocateDirect(this.bytes_in_map);
/*     */       
/*  62 */       clearBiomeData();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getFilename(World world) {
/*     */     String dimension_name;
/*  70 */     if (world.provider.dimensionId == -2) {
/*  71 */       dimension_name = "underworld";
/*  72 */     } else if (world.provider.dimensionId == -1) {
/*  73 */       dimension_name = "nether";
/*  74 */     } else if (world.provider.dimensionId == 0) {
/*  75 */       dimension_name = "overworld";
/*     */     } else {
/*  77 */       dimension_name = "unknown-dimension";
/*     */     } 
/*  79 */     return world.worldInfo.getWorldName().replaceAll(" ", "-").toLowerCase() + "-" + dimension_name + ".map";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void clearBiomeData() {
/*  85 */     for (int i = 3; i < this.bytes_in_map; i += 4)
/*     */     {
/*  87 */       this.data.put(i, (byte)63);
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
/*     */   public void markPixelDirty(int x, int z) {
/* 106 */     if (isXZOnMap(x, z)) {
/*     */       
/* 108 */       int data_offset = getDataOffsetForPixel(x, z) + 3;
/* 109 */       this.data.put(data_offset, (byte)(this.data.get(data_offset) & Byte.MAX_VALUE));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getPixelIndex(int x, int z) {
/* 116 */     return x + this.size_divided_by_2 + (z + this.size_divided_by_2) * this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int getDataOffsetForPixel(int x, int z) {
/* 122 */     return (x + this.size_divided_by_2 + (z + this.size_divided_by_2) * this.size) * 4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getMinMapXZ() {
/* 133 */     return -this.size_divided_by_2;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getMaxMapXZ() {
/* 138 */     return this.size_divided_by_2 - 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isXZOnMap(int x_or_z) {
/* 143 */     return (x_or_z >= -this.size_divided_by_2 && x_or_z < this.size_divided_by_2);
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean isXZOnMap(int x, int z) {
/* 148 */     return (x >= -this.size_divided_by_2 && x < this.size_divided_by_2 && z >= -this.size_divided_by_2 && z < this.size_divided_by_2);
/*     */   }
/*     */ 
/*     */   
/*     */   private void readFromFile() throws IOException {
/* 153 */     FileInputStream fis = new FileInputStream(new File(getFilename(this.world)));
/* 154 */     FileChannel channel = fis.getChannel();
/*     */     
/* 156 */     this.bytes_in_map = (int)channel.size();
/* 157 */     this.size_squared = this.bytes_in_map / 4;
/* 158 */     this.size = (int)MathHelper.sqrt_double(this.size_squared);
/* 159 */     this.size_divided_by_2 = this.size / 2;
/*     */     
/* 161 */     if (this.data == null || this.bytes_in_map > this.data.capacity()) {
/*     */       
/* 163 */       this.data = ByteBuffer.allocateDirect(this.bytes_in_map);
/*     */     }
/*     */     else {
/*     */       
/* 167 */       this.data.clear();
/*     */     } 
/*     */     
/* 170 */     channel.read(this.data);
/* 171 */     this.data.flip();
/*     */     
/* 173 */     fis.close();
/*     */     
/* 175 */     this.log_agent.logInfo("Map loaded, size is " + this.size + "x" + this.size + " (" + (this.bytes_in_map / 1024 / 1024) + "MB of memory)");
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
/*     */   private void writeMapColorsToFile() {
/*     */     try {
/* 202 */       FileWriter fw = new FileWriter("map_colors.txt");
/*     */       
/* 204 */       StringBuffer sb = new StringBuffer();
/*     */       
/* 206 */       for (int i = 0; i < 256; i++) {
/*     */         
/* 208 */         Block block = Block.blocksList[i];
/*     */ 
/*     */ 
/*     */         
/* 212 */         if (i > 0) {
/* 213 */           sb.append('\n');
/*     */         }
/* 215 */         if (block == null || block.blockMaterial == null || block.blockMaterial.map_color == null) {
/*     */           
/* 217 */           sb.append(i + ",0,0,0");
/*     */         }
/*     */         else {
/*     */           
/* 221 */           MapColor map_color = block.blockMaterial.map_color;
/* 222 */           sb.append(i);
/* 223 */           sb.append(",");
/* 224 */           sb.append(map_color.colorValue >> 16 & 0xFF);
/* 225 */           sb.append(",");
/* 226 */           sb.append(map_color.colorValue >> 8 & 0xFF);
/* 227 */           sb.append(",");
/* 228 */           sb.append(map_color.colorValue & 0xFF);
/*     */         } 
/*     */       } 
/*     */       
/* 232 */       fw.write(sb.toString());
/*     */       
/* 234 */       fw.close();
/*     */     }
/* 236 */     catch (Exception e) {}
/*     */   }
/*     */ 
/*     */   
/*     */   private void updateEmbeddedData() {
/* 241 */     int length_of_data_to_embed = 0;
/* 242 */     byte[] data_to_embed = new byte[64];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 257 */     int day_of_world = this.world.getDayOfWorld();
/* 258 */     int tick_of_day = this.world.getAdjustedTimeOfDay();
/*     */ 
/*     */ 
/*     */     
/* 262 */     data_to_embed[length_of_data_to_embed++] = (byte)(day_of_world & 0xFF);
/* 263 */     data_to_embed[length_of_data_to_embed++] = (byte)(day_of_world >> 8 & 0xFF);
/* 264 */     data_to_embed[length_of_data_to_embed++] = (byte)(day_of_world >> 16 & 0xFF);
/* 265 */     data_to_embed[length_of_data_to_embed++] = (byte)(day_of_world >> 24 & 0xFF);
/* 266 */     data_to_embed[length_of_data_to_embed++] = (byte)(tick_of_day & 0xFF);
/* 267 */     data_to_embed[length_of_data_to_embed++] = (byte)(tick_of_day >> 8 & 0xFF);
/*     */     
/* 269 */     int index_of_next_byte_to_embed = -1;
/* 270 */     int offset_of_last_hosting_pixel = -4;
/*     */     
/*     */     while (true) {
/*     */       int block_id;
/* 274 */       if (++index_of_next_byte_to_embed == length_of_data_to_embed) {
/*     */         return;
/*     */       }
/*     */       
/*     */       do {
/* 279 */         offset_of_last_hosting_pixel += 4;
/*     */         
/* 281 */         if (offset_of_last_hosting_pixel >= this.bytes_in_map) {
/*     */           return;
/*     */         }
/* 284 */         block_id = this.data.get(offset_of_last_hosting_pixel);
/*     */       }
/* 286 */       while (block_id != 0 && block_id != Block.sand.blockID);
/*     */       
/* 288 */       this.data.put(offset_of_last_hosting_pixel + 1, data_to_embed[index_of_next_byte_to_embed]);
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
/*     */   public void writeToFile() {
/* 302 */     if (!writeToFileProgressively(true)) {
/*     */ 
/*     */       
/*     */       try {
/* 306 */         updateEmbeddedData();
/*     */         
/* 308 */         File temporary = new File(getFilename(this.world) + ".tmp");
/*     */         
/* 310 */         FileOutputStream fos = new FileOutputStream(temporary);
/* 311 */         FileChannel fc = fos.getChannel();
/*     */         
/* 313 */         this.data.rewind();
/*     */         
/* 315 */         fc.write(this.data);
/*     */         
/* 317 */         fos.flush();
/*     */         
/* 319 */         fos.close();
/*     */         
/* 321 */         File file = new File(getFilename(this.world));
/*     */         
/* 323 */         if (file.exists() && !file.delete()) {
/* 324 */           this.log_agent.logWarning("writeToFile: Wasn't able to delete " + file.getName());
/*     */         }
/* 326 */         if (!temporary.renameTo(file)) {
/* 327 */           this.log_agent.logWarning("writeToFile: Wasn't able to rename " + temporary.getName() + " to " + file.getName());
/*     */         }
/* 329 */       } catch (IOException e) {
/*     */         
/* 331 */         this.log_agent.logWarning("writeToFile: Exception occured while trying to write world map to file");
/*     */       } 
/*     */       
/* 334 */       writeMapColorsToFile();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void resetProgressiveFile() {
/* 340 */     this.progressive_filename = null;
/*     */     
/* 342 */     if (this.progressive_file != null) {
/*     */ 
/*     */       
/*     */       try {
/* 346 */         this.progressive_file.close();
/*     */       }
/* 348 */       catch (IOException e) {}
/*     */       
/* 350 */       this.progressive_file = null;
/*     */     } 
/*     */     
/* 353 */     this.progressive_write_counter = -1200;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean writeToFileProgressively(boolean flush) {
/* 360 */     this.progressive_write_counter++;
/*     */     
/* 362 */     if (this.progressive_write_counter == 0) {
/*     */       
/* 364 */       if (this.copy_of_data == null || this.copy_of_data.length != this.data.capacity())
/*     */       {
/*     */         
/* 367 */         this.copy_of_data = new byte[this.data.capacity()];
/*     */       }
/*     */     }
/* 370 */     else if (this.progressive_write_counter == 1) {
/*     */       
/* 372 */       updateEmbeddedData();
/*     */       
/* 374 */       this.data.rewind();
/* 375 */       this.data.get(this.copy_of_data);
/*     */       
/* 377 */       if (this.progressive_file != null) {
/*     */         
/*     */         try {
/*     */           
/* 381 */           this.progressive_file.close();
/*     */         }
/* 383 */         catch (IOException e) {}
/*     */       }
/*     */ 
/*     */       
/*     */       try {
/* 388 */         this.progressive_filename = getFilename(this.world) + ".unfinished";
/*     */         
/* 390 */         this.progressive_file = new RandomAccessFile(this.progressive_filename, "rw");
/* 391 */         this.progressive_file.setLength(0L);
/*     */         
/* 393 */         this.bytes_written_to_temporary_file = 0;
/*     */       }
/* 395 */       catch (IOException e) {
/*     */         
/* 397 */         this.log_agent.logWarning("writeToFileProgressively: Exception occured while trying to write world map to file (Stage 1)");
/*     */         
/* 399 */         resetProgressiveFile();
/*     */       }
/*     */     
/* 402 */     } else if (this.progressive_write_counter == 2 && this.bytes_written_to_temporary_file < this.bytes_in_map) {
/*     */       
/* 404 */       this.progressive_write_counter--;
/*     */ 
/*     */       
/*     */       try {
/* 408 */         int bytes_to_write = flush ? (this.bytes_in_map - this.bytes_written_to_temporary_file) : Math.min(1024, this.bytes_in_map - this.bytes_written_to_temporary_file);
/*     */ 
/*     */         
/* 411 */         this.progressive_file.write(this.copy_of_data, this.bytes_written_to_temporary_file, bytes_to_write);
/*     */         
/* 413 */         this.bytes_written_to_temporary_file += bytes_to_write;
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 418 */       catch (IOException e) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 432 */         resetProgressiveFile();
/*     */       } 
/*     */       
/* 435 */       if (flush) {
/* 436 */         writeToFileProgressively(false);
/*     */       }
/* 438 */     } else if (this.progressive_write_counter == 2) {
/*     */ 
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 449 */         this.progressive_file.close();
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 454 */         File src = new File(this.progressive_filename);
/* 455 */         File dest = new File(getFilename(this.world));
/*     */ 
/*     */ 
/*     */         
/* 459 */         if (dest.exists() && !dest.delete()) {
/*     */           
/* 461 */           this.log_agent.logWarning("writeToFileProgressively: Wasn't able to delete " + dest.getName());
/* 462 */           resetProgressiveFile();
/* 463 */           return false;
/*     */         } 
/*     */         
/* 466 */         if (!src.renameTo(dest)) {
/*     */           
/* 468 */           this.log_agent.logWarning("writeToFileProgressively: Wasn't able to rename " + src.getName() + " to " + dest.getName());
/* 469 */           resetProgressiveFile();
/* 470 */           return false;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 475 */         writeMapColorsToFile();
/*     */ 
/*     */       
/*     */       }
/* 479 */       catch (IOException e) {
/*     */         
/* 481 */         this.log_agent.logWarning("writeToFileProgressively: Exception occured while trying to write world map to file (Stage 3)");
/* 482 */         resetProgressiveFile();
/* 483 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 487 */       resetProgressiveFile();
/*     */ 
/*     */       
/* 490 */       return true;
/*     */     } 
/*     */     
/* 493 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int addSurvey(WorldServer world, int center_x, int center_z, int radius, boolean done_by_map) {
/* 498 */     int minus_radius = -radius;
/* 499 */     int radius_times_2 = radius * 2;
/* 500 */     int radius_sq = radius * radius;
/*     */     
/* 502 */     int radius_minus_2 = radius - 2;
/* 503 */     int radius_minus_2_sq = radius_minus_2 * radius_minus_2;
/*     */     
/* 505 */     int min_XZ = getMinMapXZ();
/* 506 */     int max_XZ = getMaxMapXZ();
/*     */     
/* 508 */     int blocks_surveyed = 0;
/*     */     
/* 510 */     for (int dx = minus_radius; dx <= radius; dx++) {
/*     */       
/* 512 */       int x = center_x + dx;
/*     */       
/* 514 */       if (x >= min_XZ) {
/*     */         
/* 516 */         if (x > max_XZ) {
/*     */           break;
/*     */         }
/* 519 */         for (int dz = minus_radius; dz <= radius; dz++) {
/*     */           
/* 521 */           int z = center_z + dz;
/*     */           
/* 523 */           if (z >= min_XZ) {
/*     */             
/* 525 */             if (z > max_XZ) {
/*     */               break;
/*     */             }
/* 528 */             int distance_sq = dx * dx + dz * dz;
/*     */             
/* 530 */             if (done_by_map || (distance_sq < radius_sq && (distance_sq < radius_minus_2_sq || (x + z) % 2 != 0))) {
/*     */ 
/*     */               
/* 533 */               int data_offset = getDataOffsetForPixel(x, z);
/*     */               
/* 535 */               byte biome_byte = this.data.get(data_offset + 3);
/*     */               
/* 537 */               boolean has_been_surveyed_by_map = ((biome_byte & 0x40) != 0);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 542 */               if ((biome_byte & 0x80) == 0 || (done_by_map && !has_been_surveyed_by_map)) {
/*     */ 
/*     */                 
/* 545 */                 Chunk chunk = world.getChunkFromBlockCoords(x, z);
/*     */                 
/* 547 */                 if (!chunk.isEmpty())
/*     */                 
/*     */                 { 
/* 550 */                   int depth, x_in_chunk = x & 0xF;
/* 551 */                   int z_in_chunk = z & 0xF;
/*     */                   
/* 553 */                   int y = chunk.getHeightValue(x_in_chunk, z_in_chunk) + 1 - 2;
/* 554 */                   int height_on_map = y;
/*     */                   
/* 556 */                   int block_id = chunk.getBlockID(x_in_chunk, y, z_in_chunk);
/*     */                   
/*     */                   int block_id_above;
/*     */                   
/* 560 */                   while ((block_id_above = chunk.getBlockID(x_in_chunk, y + 1, z_in_chunk)) != 0) {
/*     */                     
/* 562 */                     y++;
/*     */                     
/* 564 */                     Block block_above = Block.blocksList[block_id_above];
/*     */                     
/* 566 */                     if (block_above.blockMaterial.map_color != MapColor.airColor) {
/*     */ 
/*     */                       
/* 569 */                       height_on_map = y;
/*     */                       
/* 571 */                       block_id = block_id_above;
/*     */                     } 
/*     */                   } 
/*     */ 
/*     */ 
/*     */ 
/*     */                   
/* 578 */                   Block block = Block.blocksList[block_id];
/*     */                   
/* 580 */                   if (block != null && block.blockMaterial.isLiquid()) {
/*     */                     
/* 582 */                     depth = 1;
/*     */                     
/* 584 */                     int y_below = y;
/*     */                     
/* 586 */                     while (--y_below >= 0)
/*     */                     {
/* 588 */                       int block_id_below = chunk.getBlockID(x_in_chunk, y_below, z_in_chunk);
/*     */                       
/* 590 */                       Block block_below = Block.blocksList[block_id_below];
/*     */                       
/* 592 */                       if (block_below != null && block_below.blockMaterial.isLiquid()) {
/* 593 */                         depth++;
/*     */                       }
/*     */                     }
/*     */                   
/*     */                   }
/*     */                   else {
/*     */                     
/* 600 */                     depth = 0;
/*     */                   } 
/*     */                   
/* 603 */                   this.data.put(data_offset, (byte)block_id);
/* 604 */                   this.data.put(data_offset + 1, (byte)(world.getBlockMetadata(x, y, z) | depth << 4));
/* 605 */                   this.data.put(data_offset + 2, (byte)height_on_map);
/*     */ 
/*     */                   
/* 608 */                   if ((biome_byte & 0x3F) == 63) {
/* 609 */                     biome_byte = (byte)(world.getBiomeGenForCoords(x, z)).biomeID;
/*     */                   }
/* 611 */                   biome_byte = (byte)(biome_byte | 0x80);
/*     */                   
/* 613 */                   if (has_been_surveyed_by_map || done_by_map) {
/* 614 */                     biome_byte = (byte)(biome_byte | 0x40);
/*     */                   }
/* 616 */                   this.data.put(data_offset + 3, biome_byte);
/*     */ 
/*     */ 
/*     */                   
/* 620 */                   if (++blocks_surveyed >= 1024)
/* 621 */                     return blocks_surveyed;  } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 627 */     }  return blocks_surveyed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXaddSurvey(WorldServer world, int center_x, int center_z, int radius) {
/* 632 */     if (world.provider.dimensionId != 0) {
/*     */       return;
/*     */     }
/* 635 */     byte day_of_world_mod_128 = (byte)(world.getDayOfWorld() % 128);
/*     */     
/* 637 */     int mapping_radius = world.provider.hasNoSky ? (radius / 2) : radius;
/* 638 */     int mapping_radius_times_2 = mapping_radius * 2;
/* 639 */     int mapping_radius_sq = mapping_radius * mapping_radius;
/*     */     
/* 641 */     int[] var24 = new int[256];
/*     */     
/* 643 */     int min_XZ = getMinMapXZ();
/* 644 */     int max_XZ = getMaxMapXZ();
/*     */     
/* 646 */     for (int raster_x = 1; raster_x < mapping_radius_times_2; raster_x++) {
/*     */       
/* 648 */       double var16 = 0.0D;
/*     */       
/* 650 */       int dx = raster_x - mapping_radius;
/*     */       
/* 652 */       int x = center_x + dx;
/*     */       
/* 654 */       if (x >= min_XZ) {
/*     */         
/* 656 */         if (x > max_XZ) {
/*     */           break;
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
/* 678 */         int smallest_raster_z_that_is_due_for_update = mapping_radius_times_2;
/*     */ 
/*     */         
/* 681 */         for (int raster_z = 1; raster_z < mapping_radius_times_2; raster_z++) {
/*     */           
/* 683 */           int z = center_z + raster_z - mapping_radius;
/*     */           
/* 685 */           if (z >= min_XZ && z <= max_XZ)
/*     */           {
/*     */ 
/*     */ 
/*     */             
/* 690 */             if ((this.data.get(getDataOffsetForPixel(x, z) + 3) & 0x80) == 0) {
/*     */ 
/*     */               
/* 693 */               smallest_raster_z_that_is_due_for_update = raster_z;
/*     */               break;
/*     */             } 
/*     */           }
/*     */         } 
/* 698 */         if (smallest_raster_z_that_is_due_for_update != mapping_radius_times_2) {
/*     */ 
/*     */           
/* 701 */           int first_raster_z = smallest_raster_z_that_is_due_for_update - 1;
/*     */           
/* 703 */           int largest_raster_z_that_is_due_for_update = -1;
/*     */           
/* 705 */           for (int i = mapping_radius_times_2 - 1; i >= 0; i--) {
/*     */             
/* 707 */             int z = center_z + i - mapping_radius;
/*     */             
/* 709 */             if (z >= min_XZ && z <= max_XZ)
/*     */             {
/*     */ 
/*     */ 
/*     */               
/* 714 */               if ((this.data.get(getDataOffsetForPixel(x, z) + 3) & 0x80) == 0) {
/*     */ 
/*     */                 
/* 717 */                 largest_raster_z_that_is_due_for_update = i;
/*     */                 break;
/*     */               } 
/*     */             }
/*     */           } 
/* 722 */           if (largest_raster_z_that_is_due_for_update != -1) {
/*     */ 
/*     */             
/* 725 */             int last_raster_z = largest_raster_z_that_is_due_for_update;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 732 */             for (int j = first_raster_z; j <= last_raster_z; j++) {
/*     */               
/* 734 */               int dz = j - mapping_radius;
/*     */               
/* 736 */               int z = center_z + dz;
/*     */               
/* 738 */               if (z >= min_XZ - 1) {
/*     */                 
/* 740 */                 if (z > max_XZ) {
/*     */                   break;
/*     */                 }
/* 743 */                 int dx_sq = dx * dx;
/* 744 */                 int dz_sq = dz * dz;
/*     */                 
/* 746 */                 boolean at_edge_of_survey = (dx_sq + dz_sq > (mapping_radius - 2) * (mapping_radius - 2));
/*     */                 
/* 748 */                 int x_plus_z_and_1 = x + z & 0x1;
/*     */                 
/* 750 */                 if (dz <= 0 || dx_sq + dz_sq < mapping_radius_sq) {
/*     */ 
/*     */                   
/* 753 */                   for (int k = 0; k < var24.length; k++) {
/* 754 */                     var24[k] = 0;
/*     */                   }
/* 756 */                   Chunk chunk = world.getChunkFromBlockCoords(x, z);
/*     */                   
/* 758 */                   if (!chunk.isEmpty()) {
/*     */                     
/* 760 */                     int block_id = -1;
/* 761 */                     int metadata = -1;
/* 762 */                     int brightness = -1;
/* 763 */                     int depth = -1;
/* 764 */                     int height = -1;
/*     */                     
/* 766 */                     int x_in_chunk = x & 0xF;
/* 767 */                     int z_in_chunk = z & 0xF;
/* 768 */                     int var28 = 0;
/* 769 */                     double var29 = 0.0D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 775 */                     if (world.provider.hasNoSky) {
/*     */                       
/* 777 */                       int var31 = x + z * 231871;
/* 778 */                       var31 = var31 * var31 * 31287121 + var31 * 11;
/*     */                       
/* 780 */                       if ((var31 >> 20 & 0x1) == 0) {
/*     */                         
/* 782 */                         var24[Block.dirt.blockID] = var24[Block.dirt.blockID] + 10;
/*     */                       }
/*     */                       else {
/*     */                         
/* 786 */                         var24[Block.stone.blockID] = var24[Block.stone.blockID] + 10;
/*     */                       } 
/*     */                       
/* 789 */                       var29 = 100.0D;
/*     */                     }
/*     */                     else {
/*     */                       
/* 793 */                       int var31 = 0;
/* 794 */                       int var32 = 0;
/*     */                       
/* 796 */                       int var33 = chunk.getHeightValue(var31 + x_in_chunk, var32 + z_in_chunk) + 1;
/* 797 */                       height = var33 - 1;
/* 798 */                       int var34 = 0;
/*     */                       
/* 800 */                       if (var33 > 1) {
/*     */                         boolean var35;
/*     */ 
/*     */ 
/*     */                         
/*     */                         do {
/* 806 */                           var35 = true;
/* 807 */                           var34 = chunk.getBlockID(var31 + x_in_chunk, var33 - 1, var32 + z_in_chunk);
/*     */                           
/* 809 */                           if (var34 == 0) {
/*     */                             
/* 811 */                             var35 = false;
/*     */                           }
/* 813 */                           else if (var33 > 0 && var34 > 0 && (Block.blocksList[var34]).blockMaterial.map_color == MapColor.airColor) {
/*     */                             
/* 815 */                             var35 = false;
/*     */                           } 
/*     */                           
/* 818 */                           if (var35)
/*     */                             continue; 
/* 820 */                           var33--;
/*     */                           
/* 822 */                           if (var33 <= 0) {
/*     */                             break;
/*     */                           }
/*     */ 
/*     */                           
/* 827 */                           var34 = chunk.getBlockID(var31 + x_in_chunk, var33 - 1, var32 + z_in_chunk);
/* 828 */                           height = var33 - 1;
/*     */                         
/*     */                         }
/* 831 */                         while (var33 > 0 && !var35);
/*     */                         
/* 833 */                         if (var33 > 0 && var34 != 0 && (Block.blocksList[var34]).blockMaterial.isLiquid()) {
/*     */                           
/* 835 */                           int var43, var36 = var33 - 1;
/* 836 */                           boolean var37 = false;
/*     */ 
/*     */ 
/*     */                           
/*     */                           do {
/* 841 */                             var43 = chunk.getBlockID(var31 + x_in_chunk, var36--, var32 + z_in_chunk);
/* 842 */                             var28++;
/*     */                           }
/* 844 */                           while (var36 > 0 && var43 != 0 && (Block.blocksList[var43]).blockMaterial.isLiquid());
/*     */                           
/* 846 */                           depth = var28 - 1;
/*     */ 
/*     */                           
/* 849 */                           if (depth > 15) {
/* 850 */                             depth = 15;
/*     */                           }
/*     */                         } else {
/*     */                           
/* 854 */                           depth = 0;
/*     */                         } 
/*     */                       } 
/*     */                       
/* 858 */                       var29 += var33;
/* 859 */                       var24[var34] = var24[var34] + 1;
/*     */                     } 
/*     */                     
/* 862 */                     int m = 0;
/* 863 */                     block_id = -1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 874 */                     for (int n = 0; n < 256; n++) {
/*     */                       
/* 876 */                       if (var24[n] > m) {
/*     */                         
/* 878 */                         block_id = n;
/* 879 */                         m = var24[n];
/*     */                       } 
/*     */                     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 890 */                     if (block_id == 0) {
/*     */                       
/* 892 */                       metadata = 0;
/* 893 */                       brightness = 0;
/* 894 */                       depth = 0;
/* 895 */                       height = 0;
/*     */                     }
/* 897 */                     else if (block_id > 0) {
/*     */ 
/*     */                       
/* 900 */                       if ((Block.blocksList[block_id]).blockMaterial.map_color == MapColor.waterColor) {
/*     */ 
/*     */                         
/* 903 */                         double var40 = var28 * 0.1D + x_plus_z_and_1 * 0.2D;
/* 904 */                         brightness = (var40 > 0.9D) ? 0 : ((var40 < 0.5D) ? 2 : 1);
/*     */                       }
/*     */                       else {
/*     */                         
/* 908 */                         double var40 = (var29 - var16) * 4.0D / 5.0D + (x_plus_z_and_1 - 0.5D) * 0.4D;
/* 909 */                         brightness = (var40 < -0.6D) ? 0 : ((var40 > 0.6D) ? 2 : 1);
/*     */                       } 
/*     */                     } 
/*     */                     
/* 913 */                     var16 = var29;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/* 926 */                     if (j <= first_raster_z || dx_sq + dz_sq >= mapping_radius_sq || (at_edge_of_survey && x_plus_z_and_1 == 0)) {
/* 927 */                       block_id = -1;
/*     */                     }
/* 929 */                     if (block_id >= 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                       
/* 940 */                       int offset_for_pixel = getDataOffsetForPixel(x, z);
/*     */                       
/* 942 */                       byte biome_byte = this.data.get(offset_for_pixel + 3);
/*     */                       
/* 944 */                       if ((biome_byte >> 7 & 0x1) == 0) {
/*     */                         
/* 946 */                         this.data.put(offset_for_pixel, (byte)block_id);
/*     */                         
/* 948 */                         this.data.put(offset_for_pixel + 1, (byte)(world.getBlockMetadata(x, height, z) | depth << 4));
/* 949 */                         this.data.put(offset_for_pixel + 2, (byte)height);
/*     */                         
/* 951 */                         if ((biome_byte & Byte.MAX_VALUE) == 127) {
/* 952 */                           biome_byte = (byte)(world.getBiomeGenForCoords(x, z)).biomeID;
/*     */                         }
/* 954 */                         this.data.put(offset_for_pixel + 3, (byte)(biome_byte | 0x80));
/*     */                         
/* 956 */                         Debug.general_counter++;
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 966 */     }  } public String getPixelInfo(int x, int z) { if (!isXZOnMap(x, z)) {
/* 967 */       return "Off-map";
/*     */     }
/* 969 */     int offset = getDataOffsetForPixel(x, z);
/*     */     
/* 971 */     int block_id = this.data.get(offset);
/* 972 */     int complex_byte = this.data.get(offset + 1);
/* 973 */     int metadata = complex_byte & 0xF;
/* 974 */     int depth = complex_byte >> 4 & 0xF;
/* 975 */     int height = this.data.get(offset + 2);
/* 976 */     int biome_id = this.data.get(offset + 3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 984 */     return "block_id=" + block_id + " metadata=" + metadata + " depth=" + depth + " height=" + height + " biome_id=" + biome_id; }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void deleteMapFile(World world) {
/* 989 */     String filename = getFilename(world);
/*     */     
/* 991 */     File file = new File(filename);
/*     */     
/* 993 */     if (file.exists() && !file.delete())
/* 994 */       world.getWorldLogAgent().logWarning("Unable to delete previous world map file " + filename); 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\WorldMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */