/*      */ package net.minecraft;
/*      */ 
/*      */ import java.io.DataInputStream;
/*      */ import java.io.DataOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ 
/*      */ public final class AnvilChunkLoader
/*      */   implements IChunkLoader, IThreadedFileIO
/*      */ {
/*   18 */   private List chunksToRemove = new ArrayList();
/*   19 */   private Set pendingAnvilChunksCoordinates = new HashSet();
/*   20 */   private Object syncLockObject = new Object();
/*      */   
/*      */   private final File chunkSaveLocation;
/*      */   
/*      */   public static final String vanilla_blocks_tag = "Blocks";
/*      */   
/*      */   public static final String new_blocks_tag = "BlockData";
/*      */   
/*      */   public static final String vanilla_entities_tag = "Entities";
/*      */   
/*      */   public static final String new_entities_tag = "EntityData";
/*      */   
/*      */   public static final String vanilla_tile_entities_tag = "TileEntities";
/*      */   public static final String new_tile_entities_tag = "TileEntityData";
/*   34 */   private static final int[][] invalid_section_conversion_data = getInvalidSectionBlockConversionIdsOrMetadata();
/*      */ 
/*      */   
/*      */   public AnvilChunkLoader(File par1File) {
/*   38 */     this.chunkSaveLocation = par1File;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Chunk loadChunk(World par1World, int par2, int par3) throws IOException {
/*   46 */     NBTTagCompound var4 = null;
/*   47 */     ChunkCoordIntPair var5 = new ChunkCoordIntPair(par2, par3);
/*   48 */     Object var6 = this.syncLockObject;
/*      */     
/*   50 */     synchronized (this.syncLockObject) {
/*      */       
/*   52 */       if (this.pendingAnvilChunksCoordinates.contains(var5))
/*      */       {
/*   54 */         for (int var7 = 0; var7 < this.chunksToRemove.size(); var7++) {
/*      */           
/*   56 */           if (((AnvilChunkLoaderPending)this.chunksToRemove.get(var7)).chunkCoordinate.equals(var5)) {
/*      */             
/*   58 */             var4 = ((AnvilChunkLoaderPending)this.chunksToRemove.get(var7)).nbtTags;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*      */     } 
/*   65 */     if (var4 == null) {
/*      */       
/*   67 */       DataInputStream var10 = RegionFileCache.getChunkInputStream(this.chunkSaveLocation, par2, par3);
/*      */       
/*   69 */       if (var10 == null)
/*      */       {
/*   71 */         return null;
/*      */       }
/*      */       
/*   74 */       var4 = CompressedStreamTools.read(var10);
/*      */     } 
/*      */     
/*   77 */     return checkedReadChunkFromNBT(par1World, par2, par3, var4);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected Chunk checkedReadChunkFromNBT(World par1World, int par2, int par3, NBTTagCompound par4NBTTagCompound) {
/*   85 */     if (!par4NBTTagCompound.hasKey("Level")) {
/*      */       
/*   87 */       par1World.getWorldLogAgent().logSevere("Chunk file at " + par2 + "," + par3 + " is missing level data, skipping");
/*   88 */       return null;
/*      */     } 
/*   90 */     if (!par4NBTTagCompound.getCompoundTag("Level").hasKey("Sections")) {
/*      */       
/*   92 */       par1World.getWorldLogAgent().logSevere("Chunk file at " + par2 + "," + par3 + " is missing block data, skipping");
/*   93 */       return null;
/*      */     } 
/*      */ 
/*      */     
/*   97 */     Chunk var5 = readChunkFromNBT(par1World, par4NBTTagCompound.getCompoundTag("Level"));
/*      */ 
/*      */ 
/*      */     
/*  101 */     if (!var5.isAtLocation(par2, par3)) {
/*      */       
/*  103 */       int x_position_from_disk = var5.xPosition;
/*  104 */       int z_position_from_disk = var5.zPosition;
/*      */       
/*  106 */       par1World.getWorldLogAgent().logSevere("Chunk file at " + par2 + "," + par3 + " is in the wrong location; relocating. (Expected " + par2 + ", " + par3 + ", got " + var5.xPosition + ", " + var5.zPosition + ")");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  115 */       par4NBTTagCompound.getCompoundTag("Level").setInteger("xPos", par2);
/*  116 */       par4NBTTagCompound.getCompoundTag("Level").setInteger("zPos", par3);
/*      */       
/*  118 */       var5 = readChunkFromNBT(par1World, par4NBTTagCompound.getCompoundTag("Level"));
/*      */       
/*  120 */       if (!var5.isAtLocation(par2, par3)) {
/*  121 */         Minecraft.setErrorMessage("checkedReadChunkFromNBT: chunk relocation failed");
/*      */       } else {
/*  123 */         Minecraft.setErrorMessage("Warning: chunk was relocated from " + (x_position_from_disk * 16) + "," + (z_position_from_disk * 16) + " to " + (par2 * 16) + "," + (par3 * 16));
/*      */       } 
/*      */     } 
/*  126 */     return var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveChunk(World par1World, Chunk par2Chunk) throws MinecraftException, IOException {
/*  135 */     par1World.checkSessionLock();
/*      */ 
/*      */     
/*      */     try {
/*  139 */       int x_position_before = par2Chunk.xPosition;
/*  140 */       int z_position_before = par2Chunk.zPosition;
/*      */       
/*  142 */       NBTTagCompound var3 = new NBTTagCompound();
/*  143 */       NBTTagCompound var4 = new NBTTagCompound();
/*  144 */       var3.setTag("Level", var4);
/*  145 */       writeChunkToNBT(par2Chunk, par1World, var4);
/*  146 */       addChunkToPending(par2Chunk.getChunkCoordIntPair(), var3);
/*      */       
/*  148 */       if (par2Chunk.xPosition != x_position_before) {
/*  149 */         Minecraft.setErrorMessage("saveChunk: Discrepency condition 1");
/*      */       }
/*  151 */       if (par2Chunk.zPosition != z_position_before) {
/*  152 */         Minecraft.setErrorMessage("saveChunk: Discrepency condition 2");
/*      */       }
/*  154 */       if (var3.getCompoundTag("Level").getInteger("xPos") != par2Chunk.xPosition) {
/*  155 */         Minecraft.setErrorMessage("saveChunk: Discrepency condition 3");
/*      */       }
/*  157 */       if (var3.getCompoundTag("Level").getInteger("zPos") != par2Chunk.zPosition) {
/*  158 */         Minecraft.setErrorMessage("saveChunk: Discrepency condition 4");
/*      */       }
/*  160 */       if (var4.getInteger("xPos") != par2Chunk.xPosition) {
/*  161 */         Minecraft.setErrorMessage("saveChunk: Discrepency condition 5");
/*      */       }
/*  163 */       if (var4.getInteger("zPos") != par2Chunk.zPosition) {
/*  164 */         Minecraft.setErrorMessage("saveChunk: Discrepency condition 6");
/*      */       }
/*  166 */     } catch (Exception var5) {
/*      */       
/*  168 */       var5.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void addChunkToPending(ChunkCoordIntPair par1ChunkCoordIntPair, NBTTagCompound par2NBTTagCompound) {
/*  174 */     Object var3 = this.syncLockObject;
/*      */     
/*  176 */     synchronized (this.syncLockObject) {
/*      */       
/*  178 */       if (this.pendingAnvilChunksCoordinates.contains(par1ChunkCoordIntPair))
/*      */       {
/*  180 */         for (int var4 = 0; var4 < this.chunksToRemove.size(); var4++) {
/*      */           
/*  182 */           if (((AnvilChunkLoaderPending)this.chunksToRemove.get(var4)).chunkCoordinate.equals(par1ChunkCoordIntPair)) {
/*      */             
/*  184 */             this.chunksToRemove.set(var4, new AnvilChunkLoaderPending(par1ChunkCoordIntPair, par2NBTTagCompound));
/*      */             
/*      */             return;
/*      */           } 
/*      */         } 
/*      */       }
/*      */       
/*  191 */       this.chunksToRemove.add(new AnvilChunkLoaderPending(par1ChunkCoordIntPair, par2NBTTagCompound));
/*  192 */       this.pendingAnvilChunksCoordinates.add(par1ChunkCoordIntPair);
/*  193 */       ThreadedFileIOBase.threadedIOInstance.queueIO(this);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean writeNextIO() {
/*  201 */     AnvilChunkLoaderPending var1 = null;
/*  202 */     Object var2 = this.syncLockObject;
/*      */     
/*  204 */     synchronized (this.syncLockObject) {
/*      */       
/*  206 */       if (this.chunksToRemove.isEmpty())
/*      */       {
/*  208 */         return false;
/*      */       }
/*      */       
/*  211 */       var1 = this.chunksToRemove.remove(0);
/*      */       
/*  213 */       this.pendingAnvilChunksCoordinates.remove(var1.chunkCoordinate);
/*      */     } 
/*      */     
/*  216 */     if (var1 != null) {
/*      */       
/*      */       try {
/*      */         
/*  220 */         writeChunkNBTTags(var1);
/*      */       }
/*  222 */       catch (Exception var4) {
/*      */         
/*  224 */         var4.printStackTrace();
/*      */       } 
/*      */     }
/*      */     
/*  228 */     return true;
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
/*      */   private void writeChunkNBTTags(AnvilChunkLoaderPending par1AnvilChunkLoaderPending) throws IOException {
/*  240 */     DataOutputStream var2 = null;
/*      */ 
/*      */     
/*      */     try {
/*  244 */       var2 = RegionFileCache.getChunkOutputStream(this.chunkSaveLocation, par1AnvilChunkLoaderPending.chunkCoordinate.chunkXPos, par1AnvilChunkLoaderPending.chunkCoordinate.chunkZPos);
/*  245 */       CompressedStreamTools.write(par1AnvilChunkLoaderPending.nbtTags, var2);
/*      */     }
/*      */     finally {
/*      */       
/*  249 */       if (var2 != null) {
/*  250 */         var2.close();
/*      */       }
/*  252 */       RegionFileCache.deflater_output_stream_to_close.close();
/*  253 */       RegionFileCache.chunk_buffer_to_close.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveExtraChunkData(World par1World, Chunk par2Chunk) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void chunkTick() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveExtraData() {
/*  274 */     while (writeNextIO());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final int calcXZAndSeedChecksumComponent(Chunk chunk) {
/*  284 */     long effective_world_creation_time = chunk.worldObj.isOverworld() ? chunk.worldObj.worldInfo.getWorldCreationTime() : 0L;
/*      */     
/*  286 */     return chunk.xPosition * 73 + chunk.zPosition * 211 + (Integer.MAX_VALUE - (int)chunk.worldObj.getSeed()) * 301 + (int)effective_world_creation_time * 813;
/*      */   }
/*      */ 
/*      */   
/*      */   private final int calcSectionChecksum(int xz_and_seed_checksum_component, int section_y, byte[] bytes) {
/*  291 */     int checksum = xz_and_seed_checksum_component;
/*      */     
/*  293 */     checksum += section_y * 671;
/*      */     
/*  295 */     int index = bytes.length - 1;
/*      */     
/*  297 */     while (index >= 0) {
/*  298 */       checksum += index * bytes[index--];
/*      */     }
/*  300 */     return checksum;
/*      */   }
/*      */ 
/*      */   
/*      */   private final int calcEntityChecksum(Entity entity) {
/*  305 */     int checksum = 0;
/*      */     
/*  307 */     if (entity instanceof EntityItem) {
/*      */       
/*  309 */       ItemStack item_stack = ((EntityItem)entity).getEntityItem();
/*      */       
/*  311 */       if (item_stack != null) {
/*  312 */         checksum += item_stack.itemID * 113 * item_stack.stackSize;
/*      */       }
/*      */     } 
/*  315 */     return checksum;
/*      */   }
/*      */ 
/*      */   
/*      */   private final int calcTileEntityChecksum(TileEntity tile_entity) {
/*  320 */     int checksum = 0;
/*      */     
/*  322 */     if (tile_entity instanceof IInventory) {
/*      */       
/*  324 */       IInventory inventory = (IInventory)tile_entity;
/*      */       
/*  326 */       int inventory_size = inventory.getSizeInventory();
/*      */       
/*  328 */       for (int i = 0; i < inventory_size; i++) {
/*      */         
/*  330 */         ItemStack item_stack = inventory.getStackInSlot(i);
/*      */         
/*  332 */         if (item_stack != null)
/*      */         {
/*      */           
/*  335 */           checksum += item_stack.itemID * 113 * item_stack.stackSize;
/*      */         }
/*      */       } 
/*      */     } 
/*  339 */     return checksum;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void writeChunkToNBT(Chunk par1Chunk, World par2World, NBTTagCompound par3NBTTagCompound) {
/*  348 */     if (Minecraft.inDevMode() && (par1Chunk instanceof EmptyChunk || par1Chunk.isEmpty())) {
/*  349 */       Minecraft.setErrorMessage("writeChunkToNBT: trying to write empty chunk to disk");
/*      */     }
/*  351 */     par3NBTTagCompound.setInteger("xPos", par1Chunk.xPosition);
/*  352 */     par3NBTTagCompound.setInteger("zPos", par1Chunk.zPosition);
/*      */     
/*  354 */     int xz_and_seed_checksum_component = calcXZAndSeedChecksumComponent(par1Chunk);
/*      */     
/*  356 */     if (par1Chunk.invalidate_checksum)
/*      */     {
/*  358 */       xz_and_seed_checksum_component++;
/*      */     }
/*  360 */     par3NBTTagCompound.setLong("last_total_world_time", par1Chunk.last_total_world_time);
/*      */     
/*  362 */     par3NBTTagCompound.setInteger("animals_spawned", par1Chunk.animals_spawned);
/*      */     
/*  364 */     par3NBTTagCompound.setLong("LastUpdate", par2World.getTotalWorldTime());
/*  365 */     par3NBTTagCompound.setIntArray("HeightMap", par1Chunk.heightMap);
/*  366 */     par3NBTTagCompound.setBoolean("TerrainPopulated", par1Chunk.isTerrainPopulated);
/*  367 */     par3NBTTagCompound.setLong("InhabitedTime", par1Chunk.inhabitedTime);
/*  368 */     ExtendedBlockStorage[] var4 = par1Chunk.getBlockStorageArray();
/*  369 */     NBTTagList var5 = new NBTTagList("Sections");
/*  370 */     boolean var6 = !par2World.provider.hasNoSky;
/*  371 */     ExtendedBlockStorage[] var7 = var4;
/*  372 */     int var8 = var4.length;
/*      */ 
/*      */     
/*  375 */     for (int var9 = 0; var9 < var8; var9++) {
/*      */       
/*  377 */       ExtendedBlockStorage var10 = var7[var9];
/*      */       
/*  379 */       if (var10 != null) {
/*      */ 
/*      */         
/*  382 */         NBTTagCompound var11 = new NBTTagCompound();
/*  383 */         var11.setByte("Y", (byte)(var10.getYLocation() >> 4 & 0xFF));
/*      */         
/*  385 */         var11.setInteger("Blocks", calcSectionChecksum(xz_and_seed_checksum_component, var10.getYLocation() >> 4 & 0xFF, var10.getBlockLSBArray()));
/*  386 */         var11.setByteArray("BlockData", var10.getBlockLSBArray());
/*      */         
/*  388 */         if (var10.getBlockMSBArray() != null)
/*      */         {
/*  390 */           var11.setByteArray("Add", (var10.getBlockMSBArray()).data);
/*      */         }
/*      */         
/*  393 */         var11.setByteArray("Data", (var10.getMetadataArray()).data);
/*  394 */         var11.setByteArray("BlockLight", (var10.getBlocklightArray()).data);
/*      */         
/*  396 */         if (var6) {
/*      */           
/*  398 */           var11.setByteArray("SkyLight", (var10.getSkylightArray()).data);
/*      */         }
/*      */         else {
/*      */           
/*  402 */           var11.setByteArray("SkyLight", new byte[(var10.getBlocklightArray()).data.length]);
/*      */         } 
/*      */         
/*  405 */         var5.appendTag(var11);
/*      */       } 
/*      */     } 
/*      */     
/*  409 */     par3NBTTagCompound.setTag("Sections", var5);
/*  410 */     par3NBTTagCompound.setByteArray("Biomes", par1Chunk.getBiomeArray());
/*      */     
/*  412 */     NBTTagList var16 = new NBTTagList();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  420 */     int entities_checksum = xz_and_seed_checksum_component;
/*      */     
/*  422 */     List[] entity_lists = par1Chunk.getEntityListsForReadingOnly();
/*      */ 
/*      */     
/*  425 */     for (var8 = 0; var8 < entity_lists.length; var8++) {
/*      */ 
/*      */       
/*  428 */       Iterator<?> iterator = entity_lists[var8].iterator();
/*      */       
/*  430 */       while (iterator.hasNext()) {
/*      */         
/*  432 */         Entity var21 = (Entity)iterator.next();
/*      */         
/*  434 */         if (Minecraft.inDevMode())
/*      */         {
/*  436 */           if (par1Chunk.checkForEntityDuplicates(var21))
/*      */           {
/*  438 */             Minecraft.setErrorMessage("writeChunkToNBT: " + var21.getEntityName() + " was found in the chunk's entityLists more than once");
/*      */           }
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  447 */         if (!var21.isWrittenToChunkNBT()) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  452 */           if (Minecraft.inDevMode()) {
/*  453 */             System.out.println("writeChunkToNBT: skipping " + (var21.isDead ? "dead " : ((var21.riddenByEntity != null) ? "mounted " : "")) + var21.getEntityName() + " (UUID=" + var21.getUniqueID() + ") in " + par1Chunk.worldObj.provider.getDimensionName() + " because it is not supposed to be written to chunk NBT");
/*      */           }
/*      */           
/*      */           continue;
/*      */         } 
/*  458 */         if (var21.isDead) {
/*      */           
/*  460 */           if (var21.is_unwanted_duplicate) {
/*      */             continue;
/*      */           }
/*  463 */           Minecraft.setErrorMessage("Why is a dead " + var21.getEntityName() + " being written to the chunk? Skipping.");
/*      */ 
/*      */           
/*  466 */           if (!par1Chunk.worldObj.isEntityObjectInLoadedEntityList(var21))
/*      */           {
/*  468 */             System.out.println("Furthermore, the entity isn't in the world's loaded entity list");
/*      */           }
/*      */           
/*      */           continue;
/*      */         } 
/*      */         
/*  474 */         if (var21.last_chunk_saved_to != null)
/*      */         {
/*  476 */           if (var21.last_chunk_saved_to.xPosition != par1Chunk.xPosition || var21.last_chunk_saved_to.zPosition != par1Chunk.zPosition) {
/*      */ 
/*      */ 
/*      */             
/*  480 */             Chunk chunk = par2World.getChunkFromChunkCoords(var21.last_chunk_saved_to.xPosition, var21.last_chunk_saved_to.zPosition);
/*      */             
/*  482 */             if (chunk != null) {
/*      */               
/*  484 */               chunk.setChunkModified();
/*      */ 
/*      */ 
/*      */               
/*  488 */               if (chunk.doesEntityWithMatchingClassAndUUIDExistInEntityLists(var21)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  501 */                 String msg = "writeChunkToNBT: " + var21.getEntityName() + " was found in another chunk at the time of saving";
/*      */                 
/*  503 */                 if (Minecraft.inDevMode()) {
/*  504 */                   Minecraft.setErrorMessage(msg);
/*      */                 } else {
/*  506 */                   System.out.println(msg);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  515 */         if (var21.last_chunk_loaded_from != null)
/*      */         {
/*  517 */           if (var21.last_chunk_loaded_from.xPosition != par1Chunk.xPosition || var21.last_chunk_loaded_from.zPosition != par1Chunk.zPosition) {
/*      */ 
/*      */ 
/*      */             
/*  521 */             Chunk chunk = par2World.getChunkFromChunkCoords(var21.last_chunk_loaded_from.xPosition, var21.last_chunk_loaded_from.zPosition);
/*      */             
/*  523 */             if (chunk != null) {
/*      */               
/*  525 */               chunk.setChunkModified();
/*      */ 
/*      */ 
/*      */               
/*  529 */               if (chunk.doesEntityWithMatchingClassAndUUIDExistInEntityLists(var21)) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */                 
/*  542 */                 String msg = "writeChunkToNBT: " + var21.getEntityName() + " was found in another chunk at the time of saving (last_chunk_loaded_from)";
/*      */                 
/*  544 */                 if (Minecraft.inDevMode()) {
/*  545 */                   Minecraft.setErrorMessage(msg);
/*      */                 } else {
/*  547 */                   System.out.println(msg);
/*      */                 } 
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  558 */         entities_checksum += calcEntityChecksum(var21);
/*      */         
/*  560 */         NBTTagCompound var11 = new NBTTagCompound();
/*      */         
/*  562 */         if (var21.writeToNBTOptional(var11)) {
/*      */ 
/*      */           
/*  565 */           var16.appendTag(var11);
/*      */           
/*  567 */           var21.last_chunk_saved_to = par1Chunk;
/*  568 */           var21.last_chunk_saved_to_entity_list_index = var8;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */     
/*  573 */     par3NBTTagCompound.setInteger("Entities", entities_checksum);
/*      */     
/*  575 */     par3NBTTagCompound.setTag("EntityData", var16);
/*  576 */     NBTTagList var17 = new NBTTagList();
/*  577 */     Iterator<TileEntity> var18 = par1Chunk.chunkTileEntityMap.values().iterator();
/*      */     
/*  579 */     int tile_entities_checksum = xz_and_seed_checksum_component;
/*      */     
/*  581 */     while (var18.hasNext()) {
/*      */       
/*  583 */       TileEntity var22 = var18.next();
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  588 */       tile_entities_checksum += calcTileEntityChecksum(var22);
/*      */       
/*  590 */       NBTTagCompound var11 = new NBTTagCompound();
/*  591 */       var22.writeToNBT(var11);
/*  592 */       var17.appendTag(var11);
/*      */     } 
/*      */     
/*  595 */     par3NBTTagCompound.setInteger("TileEntities", tile_entities_checksum);
/*      */     
/*  597 */     par3NBTTagCompound.setTag("TileEntityData", var17);
/*  598 */     List var20 = par2World.getPendingBlockUpdates(par1Chunk, false);
/*      */     
/*  600 */     if (var20 != null) {
/*      */       
/*  602 */       long var19 = par2World.getTotalWorldTime();
/*  603 */       NBTTagList var12 = new NBTTagList();
/*  604 */       Iterator<NextTickListEntry> var13 = var20.iterator();
/*      */       
/*  606 */       while (var13.hasNext()) {
/*      */         
/*  608 */         NextTickListEntry var14 = var13.next();
/*  609 */         NBTTagCompound var15 = new NBTTagCompound();
/*  610 */         var15.setInteger("i", var14.blockID);
/*  611 */         var15.setInteger("x", var14.xCoord);
/*  612 */         var15.setInteger("y", var14.yCoord);
/*  613 */         var15.setInteger("z", var14.zCoord);
/*  614 */         var15.setInteger("t", (int)(var14.scheduledTime - var19));
/*  615 */         var15.setInteger("p", var14.priority);
/*  616 */         var12.appendTag(var15);
/*      */       } 
/*      */       
/*  619 */       par3NBTTagCompound.setTag("TileTicks", var12);
/*      */     } 
/*      */     
/*  622 */     if (par1Chunk.has_had_lighting_checked) {
/*  623 */       par3NBTTagCompound.setBoolean("has_had_lighting_checked", true);
/*      */     }
/*  625 */     if (par1Chunk.isGapLightingUpdated) {
/*  626 */       par3NBTTagCompound.setBoolean("isGapLightingUpdated", true);
/*      */     }
/*  628 */     byte[] update_skylight_columns = new byte[par1Chunk.updateSkylightColumns.length];
/*      */     
/*  630 */     for (int i = 0; i < update_skylight_columns.length; i++) {
/*  631 */       update_skylight_columns[i] = (byte)(par1Chunk.updateSkylightColumns[i] ? -1 : 0);
/*      */     }
/*  633 */     par3NBTTagCompound.setByteArray("update_skylight_columns", update_skylight_columns);
/*      */ 
/*      */ 
/*      */     
/*  637 */     par3NBTTagCompound.setIntArray("skylight_bottom", par1Chunk.skylight_bottom);
/*      */     
/*  639 */     if (par2World.hasSkylight()) {
/*      */       
/*  641 */       if (par1Chunk.num_pending_skylight_updates > 0) {
/*      */         
/*  643 */         byte[] pending_skylight_update_coords = new byte[par1Chunk.num_pending_skylight_updates * 2];
/*  644 */         System.arraycopy(par1Chunk.pending_skylight_update_coords, 0, pending_skylight_update_coords, 0, pending_skylight_update_coords.length);
/*      */         
/*  646 */         par3NBTTagCompound.setByteArray("pending_skylight_update_coords", pending_skylight_update_coords);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  656 */       byte[] pending_skylight_updates = new byte[par1Chunk.pending_skylight_updates.length];
/*      */       
/*  658 */       for (int k = 0; k < pending_skylight_updates.length; k++) {
/*  659 */         pending_skylight_updates[k] = (byte)(par1Chunk.pending_skylight_updates[k] ? -1 : 0);
/*      */       }
/*  661 */       par3NBTTagCompound.setByteArray("pending_skylight_updates", pending_skylight_updates);
/*      */     } 
/*      */     
/*  664 */     if (par1Chunk.num_pending_blocklight_updates > 0) {
/*      */       
/*  666 */       byte[] pending_blocklight_update_coords = new byte[par1Chunk.num_pending_blocklight_updates * 2];
/*  667 */       System.arraycopy(par1Chunk.pending_blocklight_update_coords, 0, pending_blocklight_update_coords, 0, pending_blocklight_update_coords.length);
/*      */       
/*  669 */       par3NBTTagCompound.setByteArray("pending_blocklight_update_coords", pending_blocklight_update_coords);
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  677 */     byte[] pending_blocklight_updates = new byte[par1Chunk.pending_blocklight_updates.length];
/*      */     
/*  679 */     for (int j = 0; j < pending_blocklight_updates.length; j++) {
/*  680 */       pending_blocklight_updates[j] = (byte)(par1Chunk.pending_blocklight_updates[j] ? -1 : 0);
/*      */     }
/*  682 */     par3NBTTagCompound.setByteArray("pending_blocklight_updates", pending_blocklight_updates);
/*      */     
/*  684 */     if (par1Chunk.getHadNaturallyOccurringMycelium()) {
/*  685 */       par3NBTTagCompound.setBoolean("had_naturally_occurring_mycelium", true);
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
/*  699 */     if (par1Chunk.pending_sand_falls != null && par1Chunk.pending_sand_falls.size() > 0) {
/*      */       
/*  701 */       int[] pending_sand_falls = new int[par1Chunk.pending_sand_falls.size() * 2];
/*      */       
/*  703 */       int index = -1;
/*      */       
/*  705 */       Iterator<Map.Entry> iterator = par1Chunk.pending_sand_falls.entrySet().iterator();
/*      */       
/*  707 */       while (iterator.hasNext()) {
/*      */         
/*  709 */         Map.Entry entry = iterator.next();
/*      */         
/*  711 */         pending_sand_falls[++index] = ((Integer)entry.getKey()).intValue();
/*  712 */         pending_sand_falls[++index] = ((Integer)entry.getValue()).intValue();
/*      */       } 
/*      */       
/*  715 */       par3NBTTagCompound.setIntArray("pending_sand_falls", pending_sand_falls);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Chunk readChunkFromNBT(World par1World, NBTTagCompound par2NBTTagCompound) {
/*  725 */     int var3 = par2NBTTagCompound.getInteger("xPos");
/*  726 */     int var4 = par2NBTTagCompound.getInteger("zPos");
/*  727 */     Chunk var5 = new Chunk(par1World, var3, var4);
/*      */     
/*  729 */     int xz_and_seed_checksum_component = calcXZAndSeedChecksumComponent(var5);
/*      */     
/*  731 */     var5.last_total_world_time = par2NBTTagCompound.getLong("last_total_world_time");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  738 */     var5.animals_spawned = par2NBTTagCompound.getInteger("animals_spawned");
/*      */     
/*  740 */     var5.heightMap = par2NBTTagCompound.getIntArray("HeightMap");
/*  741 */     var5.isTerrainPopulated = par2NBTTagCompound.getBoolean("TerrainPopulated");
/*  742 */     var5.inhabitedTime = par2NBTTagCompound.getLong("InhabitedTime");
/*  743 */     NBTTagList var6 = par2NBTTagCompound.getTagList("Sections");
/*  744 */     byte var7 = 16;
/*  745 */     ExtendedBlockStorage[] var8 = new ExtendedBlockStorage[var7];
/*  746 */     boolean var9 = !par1World.provider.hasNoSky;
/*      */     
/*  748 */     for (int var10 = 0; var10 < var6.tagCount(); var10++) {
/*      */       
/*  750 */       NBTTagCompound var11 = (NBTTagCompound)var6.tagAt(var10);
/*  751 */       byte var12 = var11.getByte("Y");
/*  752 */       ExtendedBlockStorage var13 = new ExtendedBlockStorage(var12 << 4, var9);
/*      */       
/*  754 */       var13.setBlockLSBArray(var11.getByteArray("BlockData"));
/*      */       
/*  756 */       if (var11.hasKey("Add"))
/*      */       {
/*      */         
/*  759 */         var13.setBlockMSBArray(new NibbleArray(var11.getByteArray("Add")));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  765 */       var13.setBlockMetadataArray(new NibbleArray(var11.getByteArray("Data")));
/*  766 */       var13.setBlocklightArray(new NibbleArray(var11.getByteArray("BlockLight")));
/*      */       
/*  768 */       if (var9)
/*      */       {
/*      */         
/*  771 */         var13.setSkylightArray(new NibbleArray(var11.getByteArray("SkyLight")));
/*      */       }
/*      */       
/*  774 */       if (calcSectionChecksum(xz_and_seed_checksum_component, var12, var13.getBlockLSBArray()) != var11.getInteger("Blocks"))
/*      */       {
/*      */ 
/*      */ 
/*      */         
/*  779 */         handleSectionChecksumFailure(var13);
/*      */       }
/*      */       
/*  782 */       var13.removeInvalidBlocks();
/*  783 */       var8[var12] = var13;
/*      */     } 
/*      */     
/*  786 */     var5.setStorageArrays(var8);
/*      */     
/*  788 */     if (par2NBTTagCompound.hasKey("Biomes"))
/*      */     {
/*  790 */       var5.setBiomeArray(par2NBTTagCompound.getByteArray("Biomes"));
/*      */     }
/*      */ 
/*      */     
/*  794 */     NBTTagList var18 = par2NBTTagCompound.getTagList("EntityData");
/*      */     
/*  796 */     if (var18 != null) {
/*      */       
/*  798 */       int entities_checksum = xz_and_seed_checksum_component;
/*      */       
/*  800 */       List<Entity> entities_to_load = new ArrayList();
/*      */       
/*  802 */       for (int var17 = 0; var17 < var18.tagCount(); var17++) {
/*      */         
/*  804 */         NBTTagCompound var19 = (NBTTagCompound)var18.tagAt(var17);
/*  805 */         Entity var25 = EntityList.createEntityFromNBT(var19, par1World);
/*      */ 
/*      */         
/*  808 */         if (var25 != null) {
/*      */           
/*  810 */           var25.last_chunk_loaded_from = var5;
/*  811 */           var25.last_chunk_loaded_from_entity_list_index = var25.getChunkCurrentlyInSectionIndex();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  820 */           entities_checksum += calcEntityChecksum(var25);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  826 */           Entity duplicate = World.getEntityWithSameClassAndUUIDInEntityList("readChunkFromNBT", var25, entities_to_load, true);
/*      */           
/*  828 */           if (duplicate != null) {
/*      */             
/*  830 */             if (Minecraft.inDevMode()) {
/*  831 */               Minecraft.setErrorMessage("readChunkFromNBT: A duplicate of " + var25.getEntityName() + " has already been read from the NBT. Skipping.");
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  838 */             duplicate = World.getEntityWithSameClassAndUUIDInEntityList("readChunkFromNBT", var25, var5.worldObj.loadedEntityList, true);
/*      */             
/*  840 */             if (duplicate != null && !var5.worldObj.unloadedEntityList.contains(duplicate)) {
/*      */               
/*  842 */               if (Minecraft.inDevMode())
/*      */               {
/*  844 */                 Minecraft.setErrorMessage("readChunkFromNBT: A duplicate of " + var25.getEntityName() + " already exists in the world. Skipping.");
/*  845 */                 System.out.println(" " + var25.getBlockPosString() + " vs " + duplicate.getBlockPosString());
/*      */               }
/*      */             
/*      */             }
/*      */             else {
/*      */               
/*  851 */               entities_to_load.add(var25);
/*      */ 
/*      */ 
/*      */               
/*  855 */               var5.addEntity(var25);
/*  856 */               Entity var14 = var25;
/*      */               
/*  858 */               for (NBTTagCompound var15 = var19; var15.hasKey("Riding"); var15 = var15.getCompoundTag("Riding")) {
/*      */                 
/*  860 */                 Entity var16 = EntityList.createEntityFromNBT(var15.getCompoundTag("Riding"), par1World);
/*      */                 
/*  862 */                 if (var16 != null) {
/*      */                   
/*  864 */                   var5.addEntity(var16);
/*  865 */                   var14.mountEntity(var16);
/*      */                 } 
/*      */                 
/*  868 */                 var14 = var16;
/*      */               } 
/*      */             } 
/*      */           } 
/*      */         } 
/*  873 */       }  if (entities_checksum != par2NBTTagCompound.getInteger("Entities")) {
/*  874 */         handleEntitiesChecksumFailure(var5);
/*      */       }
/*      */     } 
/*      */     
/*  878 */     NBTTagList var21 = par2NBTTagCompound.getTagList("TileEntityData");
/*      */     
/*  880 */     if (var21 != null) {
/*      */       
/*  882 */       int tile_entities_checksum = xz_and_seed_checksum_component;
/*      */       
/*  884 */       for (int var20 = 0; var20 < var21.tagCount(); var20++) {
/*      */         
/*  886 */         NBTTagCompound var22 = (NBTTagCompound)var21.tagAt(var20);
/*  887 */         TileEntity var27 = TileEntity.createAndLoadEntity(var22);
/*      */         
/*  889 */         if (var27 != null) {
/*      */           
/*  891 */           if (var27 instanceof IInventory) {
/*  892 */             tile_entities_checksum += calcTileEntityChecksum(var27);
/*      */           }
/*  894 */           var5.addTileEntity(var27);
/*      */         } 
/*      */       } 
/*      */       
/*  898 */       if (tile_entities_checksum != par2NBTTagCompound.getInteger("TileEntities")) {
/*  899 */         handleTileEntitiesChecksumFailure(var5);
/*      */       }
/*      */     } 
/*  902 */     if (par2NBTTagCompound.hasKey("TileTicks")) {
/*      */       
/*  904 */       NBTTagList var24 = par2NBTTagCompound.getTagList("TileTicks");
/*      */       
/*  906 */       if (var24 != null)
/*      */       {
/*  908 */         for (int var23 = 0; var23 < var24.tagCount(); var23++) {
/*      */           
/*  910 */           NBTTagCompound var26 = (NBTTagCompound)var24.tagAt(var23);
/*  911 */           par1World.scheduleBlockUpdateFromLoad(var26.getInteger("x"), var26.getInteger("y"), var26.getInteger("z"), var26.getInteger("i"), var26.getInteger("t"), var26.getInteger("p"));
/*      */         } 
/*      */       }
/*      */     } 
/*      */     
/*  916 */     var5.has_had_lighting_checked = par2NBTTagCompound.getBoolean("has_had_lighting_checked");
/*      */     
/*  918 */     var5.isGapLightingUpdated = par2NBTTagCompound.getBoolean("isGapLightingUpdated");
/*      */     
/*  920 */     byte[] update_skylight_columns = par2NBTTagCompound.getByteArray("update_skylight_columns");
/*      */     
/*  922 */     for (int i = 0; i < update_skylight_columns.length; i++) {
/*  923 */       var5.updateSkylightColumns[i] = (update_skylight_columns[i] != 0);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  930 */     if (par2NBTTagCompound.hasKey("skylight_bottom")) {
/*      */       
/*  932 */       int[] skylight_bottom = par2NBTTagCompound.getIntArray("skylight_bottom");
/*  933 */       System.arraycopy(skylight_bottom, 0, var5.skylight_bottom, 0, skylight_bottom.length);
/*      */     }
/*      */     else {
/*      */       
/*  937 */       var5.recalculateSkylightBottoms();
/*      */     } 
/*      */     
/*  940 */     if (par1World.hasSkylight()) {
/*      */       
/*  942 */       if (par2NBTTagCompound.hasKey("pending_skylight_update_coords")) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  950 */         var5.pending_skylight_update_coords = par2NBTTagCompound.getByteArray("pending_skylight_update_coords");
/*  951 */         var5.max_num_pending_skylight_updates = var5.num_pending_skylight_updates = var5.pending_skylight_update_coords.length / 2;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  961 */       byte[] pending_skylight_updates = par2NBTTagCompound.getByteArray("pending_skylight_updates");
/*      */       
/*  963 */       for (int k = 0; k < pending_skylight_updates.length; k++) {
/*  964 */         var5.pending_skylight_updates[k] = (pending_skylight_updates[k] != 0);
/*      */       }
/*      */     } 
/*  967 */     if (par2NBTTagCompound.hasKey("pending_blocklight_update_coords")) {
/*      */       
/*  969 */       var5.pending_blocklight_update_coords = par2NBTTagCompound.getByteArray("pending_blocklight_update_coords");
/*  970 */       var5.max_num_pending_blocklight_updates = var5.num_pending_blocklight_updates = var5.pending_blocklight_update_coords.length / 2;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  978 */     byte[] pending_blocklight_updates = par2NBTTagCompound.getByteArray("pending_blocklight_updates");
/*      */     
/*  980 */     for (int j = 0; j < pending_blocklight_updates.length; j++) {
/*  981 */       var5.pending_blocklight_updates[j] = (pending_blocklight_updates[j] != 0);
/*      */     }
/*  983 */     if (par2NBTTagCompound.getBoolean("had_naturally_occurring_mycelium")) {
/*  984 */       var5.setHadNaturallyOccurringMycelium();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  994 */     if (par2NBTTagCompound.hasKey("pending_sand_falls")) {
/*      */       
/*  996 */       int[] pending_sand_falls = par2NBTTagCompound.getIntArray("pending_sand_falls");
/*      */       
/*  998 */       int num_entries = pending_sand_falls.length / 2;
/*      */       
/* 1000 */       var5.pending_sand_falls = new HashMap<Object, Object>();
/*      */       
/* 1002 */       int index = -1;
/*      */       
/* 1004 */       while (++index < pending_sand_falls.length) {
/* 1005 */         var5.pending_sand_falls.put(Integer.valueOf(pending_sand_falls[index]), Integer.valueOf(pending_sand_falls[++index]));
/*      */       }
/*      */     } 
/* 1008 */     return var5;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void handleSectionChecksumFailure(ExtendedBlockStorage ebs) {
/* 1015 */     byte[] block_ids = ebs.getBlockLSBArray();
/* 1016 */     NibbleArray metadata_array = ebs.getMetadataArray();
/*      */     
/* 1018 */     int[] block_id_and_metadata = new int[2];
/*      */     
/* 1020 */     for (int x = 0; x < 16; x++) {
/*      */       
/* 1022 */       for (int y = 0; y < 16; y++) {
/*      */         
/* 1024 */         for (int z = 0; z < 16; z++) {
/*      */           
/* 1026 */           int index = y << 8 | z << 4 | x;
/*      */           
/* 1028 */           int block_id = block_ids[index];
/*      */           
/* 1030 */           if (block_id < 0) {
/* 1031 */             block_id += 256;
/*      */           }
/* 1033 */           if (block_id != 0 && block_id != Block.stone.blockID && block_id != Block.sand.blockID) {
/*      */ 
/*      */             
/* 1036 */             block_id_and_metadata[0] = block_id;
/*      */             
/* 1038 */             checkForConversion(block_id_and_metadata, block_ids, metadata_array);
/*      */             
/* 1040 */             block_id = block_id_and_metadata[0];
/*      */             
/* 1042 */             if (block_id >= 0) {
/* 1043 */               block_ids[index] = (byte)block_id;
/*      */             }
/* 1045 */             int metadata = (block_id == 0) ? 0 : block_id_and_metadata[1];
/*      */             
/* 1047 */             if (metadata >= 0) {
/* 1048 */               metadata_array.set(x, y, z, metadata);
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
/*      */   
/*      */   private void handleEntitiesChecksumFailure(Chunk chunk) {
/* 1062 */     if (!chunk.hasEntitiesForWritingToNBT()) {
/*      */       return;
/*      */     }
/* 1065 */     List[] entity_lists = chunk.getEntityListsForReadingOnly();
/*      */     
/* 1067 */     for (int i = 0; i < entity_lists.length; i++) {
/*      */       
/* 1069 */       List entities = entity_lists[i];
/*      */       
/* 1071 */       if (entities != null && entities.size() != 0) {
/*      */ 
/*      */         
/* 1074 */         Iterator<Entity> iterator = entities.iterator();
/*      */         
/* 1076 */         while (iterator.hasNext()) {
/*      */           
/* 1078 */           Entity entity = iterator.next();
/*      */           
/* 1080 */           if (entity instanceof EntityItem) {
/* 1081 */             entity.setDead();
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
/*      */   private void handleTileEntitiesChecksumFailure(Chunk chunk) {
/* 1093 */     Iterator<Map.Entry> iterator = chunk.chunkTileEntityMap.entrySet().iterator();
/*      */     
/* 1095 */     while (iterator.hasNext()) {
/*      */       
/* 1097 */       Map.Entry entry = iterator.next();
/*      */       
/* 1099 */       TileEntity tile_entity = (TileEntity)entry.getValue();
/*      */       
/* 1101 */       if (tile_entity instanceof IInventory) {
/*      */ 
/*      */         
/* 1104 */         IInventory inventory = (IInventory)tile_entity;
/*      */         
/* 1106 */         inventory.destroyInventory();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void checkForConversion(int[] block_id_and_metadata, byte[] block_ids, NibbleArray metadata_array) {
/* 1113 */     Block block = Block.getBlock(block_id_and_metadata[0]);
/*      */     
/* 1115 */     int block_id = -1;
/* 1116 */     int metadata = -1;
/*      */     
/* 1118 */     if (block == null) {
/*      */       
/* 1120 */       block_id_and_metadata[0] = 0;
/* 1121 */       block_id_and_metadata[1] = 0;
/*      */ 
/*      */       
/*      */       return;
/*      */     } 
/*      */ 
/*      */     
/* 1128 */     block_id = invalid_section_conversion_data[block.blockID][0];
/* 1129 */     metadata = invalid_section_conversion_data[block.blockID][1];
/*      */     
/* 1131 */     block_id_and_metadata[0] = block_id;
/* 1132 */     block_id_and_metadata[1] = metadata;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] same() {
/* 1137 */     return new int[] { -1, -1 };
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] air() {
/* 1142 */     return null;
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] stone() {
/* 1147 */     return new int[] { Block.stone.blockID, 0 };
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] dirt() {
/* 1152 */     return new int[] { Block.dirt.blockID, 0 };
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[] cobblestone() {
/* 1157 */     return new int[] { Block.cobblestone.blockID, 0 };
/*      */   }
/*      */ 
/*      */   
/*      */   private static int[][] getInvalidSectionBlockConversionIdsOrMetadata() {
/* 1162 */     int[][] array = new int[256][];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1167 */     array[Block.stone.blockID] = same();
/* 1168 */     array[Block.grass.blockID] = same();
/* 1169 */     array[Block.dirt.blockID] = same();
/* 1170 */     array[Block.cobblestone.blockID] = same();
/* 1171 */     array[Block.planks.blockID] = same();
/* 1172 */     array[Block.sapling.blockID] = same();
/* 1173 */     array[Block.bedrock.blockID] = same();
/* 1174 */     array[Block.waterMoving.blockID] = same();
/* 1175 */     array[Block.waterStill.blockID] = same();
/* 1176 */     array[Block.lavaMoving.blockID] = same();
/* 1177 */     array[Block.lavaStill.blockID] = same();
/* 1178 */     array[Block.sand.blockID] = same();
/* 1179 */     array[Block.gravel.blockID] = same();
/* 1180 */     array[Block.oreGold.blockID] = stone();
/* 1181 */     array[Block.oreIron.blockID] = stone();
/* 1182 */     array[Block.oreCoal.blockID] = stone();
/* 1183 */     array[Block.wood.blockID] = same();
/* 1184 */     array[Block.leaves.blockID] = same();
/* 1185 */     array[Block.sponge.blockID] = air();
/* 1186 */     array[Block.glass.blockID] = same();
/* 1187 */     array[Block.oreLapis.blockID] = stone();
/* 1188 */     array[Block.blockLapis.blockID] = air();
/* 1189 */     array[Block.dispenser.blockID] = air();
/* 1190 */     array[Block.sandStone.blockID] = same();
/* 1191 */     array[Block.music.blockID] = air();
/* 1192 */     array[Block.bed.blockID] = same();
/* 1193 */     array[Block.railPowered.blockID] = air();
/* 1194 */     array[Block.railDetector.blockID] = air();
/* 1195 */     array[Block.pistonStickyBase.blockID] = air();
/* 1196 */     array[Block.web.blockID] = same();
/* 1197 */     array[Block.tallGrass.blockID] = same();
/* 1198 */     array[Block.deadBush.blockID] = same();
/* 1199 */     array[Block.pistonBase.blockID] = air();
/* 1200 */     array[Block.pistonExtension.blockID] = air();
/* 1201 */     array[Block.cloth.blockID] = same();
/* 1202 */     array[Block.pistonMoving.blockID] = air();
/* 1203 */     array[Block.plantYellow.blockID] = same();
/* 1204 */     array[Block.plantRed.blockID] = same();
/* 1205 */     array[Block.mushroomBrown.blockID] = same();
/* 1206 */     array[Block.mushroomRed.blockID] = same();
/* 1207 */     array[Block.blockGold.blockID] = air();
/* 1208 */     array[Block.blockIron.blockID] = air();
/* 1209 */     array[Block.stoneDoubleSlab.blockID] = same();
/* 1210 */     array[Block.stoneSingleSlab.blockID] = same();
/* 1211 */     array[Block.brick.blockID] = same();
/* 1212 */     array[Block.tnt.blockID] = air();
/* 1213 */     array[Block.bookShelf.blockID] = air();
/* 1214 */     array[Block.cobblestoneMossy.blockID] = same();
/* 1215 */     array[Block.obsidian.blockID] = cobblestone();
/* 1216 */     array[Block.torchWood.blockID] = air();
/* 1217 */     array[Block.fire.blockID] = same();
/* 1218 */     array[Block.mobSpawner.blockID] = air();
/* 1219 */     array[Block.stairsWoodOak.blockID] = same();
/* 1220 */     array[Block.chest.blockID] = air();
/* 1221 */     array[Block.redstoneWire.blockID] = air();
/* 1222 */     array[Block.oreDiamond.blockID] = stone();
/* 1223 */     array[Block.blockDiamond.blockID] = air();
/* 1224 */     array[Block.workbench.blockID] = air();
/* 1225 */     array[Block.crops.blockID] = air();
/* 1226 */     array[Block.tilledField.blockID] = dirt();
/* 1227 */     array[Block.furnaceIdle.blockID] = air();
/* 1228 */     array[Block.furnaceBurning.blockID] = air();
/* 1229 */     array[Block.signPost.blockID] = same();
/* 1230 */     array[Block.doorWood.blockID] = air();
/* 1231 */     array[Block.ladder.blockID] = air();
/* 1232 */     array[Block.rail.blockID] = air();
/* 1233 */     array[Block.stairsCobblestone.blockID] = same();
/* 1234 */     array[Block.signWall.blockID] = same();
/* 1235 */     array[Block.lever.blockID] = air();
/* 1236 */     array[Block.pressurePlateStone.blockID] = air();
/* 1237 */     array[Block.doorIron.blockID] = air();
/* 1238 */     array[Block.pressurePlatePlanks.blockID] = air();
/* 1239 */     array[Block.oreRedstone.blockID] = stone();
/* 1240 */     array[Block.oreRedstoneGlowing.blockID] = stone();
/* 1241 */     array[Block.torchRedstoneIdle.blockID] = air();
/* 1242 */     array[Block.torchRedstoneActive.blockID] = air();
/* 1243 */     array[Block.stoneButton.blockID] = air();
/* 1244 */     array[Block.snow.blockID] = same();
/* 1245 */     array[Block.ice.blockID] = same();
/* 1246 */     array[Block.blockSnow.blockID] = same();
/* 1247 */     array[Block.cactus.blockID] = same();
/* 1248 */     array[Block.blockClay.blockID] = same();
/* 1249 */     array[Block.reed.blockID] = same();
/* 1250 */     array[Block.jukebox.blockID] = air();
/* 1251 */     array[Block.fence.blockID] = same();
/* 1252 */     array[Block.pumpkin.blockID] = air();
/* 1253 */     array[Block.netherrack.blockID] = same();
/* 1254 */     array[Block.slowSand.blockID] = same();
/* 1255 */     array[Block.glowStone.blockID] = same();
/* 1256 */     array[Block.portal.blockID] = same();
/* 1257 */     array[Block.pumpkinLantern.blockID] = air();
/* 1258 */     array[Block.cake.blockID] = air();
/* 1259 */     array[Block.redstoneRepeaterIdle.blockID] = air();
/* 1260 */     array[Block.redstoneRepeaterActive.blockID] = air();
/*      */     
/* 1262 */     array[Block.mantleOrCore.blockID] = same();
/* 1263 */     array[Block.trapdoor.blockID] = air();
/* 1264 */     array[Block.silverfish.blockID] = same();
/* 1265 */     array[Block.stoneBrick.blockID] = same();
/* 1266 */     array[Block.mushroomCapBrown.blockID] = same();
/* 1267 */     array[Block.mushroomCapRed.blockID] = same();
/* 1268 */     array[Block.fenceIron.blockID] = air();
/* 1269 */     array[Block.thinGlass.blockID] = same();
/* 1270 */     array[Block.melon.blockID] = air();
/* 1271 */     array[Block.pumpkinStem.blockID] = air();
/* 1272 */     array[Block.melonStem.blockID] = air();
/* 1273 */     array[Block.vine.blockID] = same();
/* 1274 */     array[Block.fenceGate.blockID] = same();
/* 1275 */     array[Block.stairsBrick.blockID] = same();
/* 1276 */     array[Block.stairsStoneBrick.blockID] = same();
/* 1277 */     array[Block.mycelium.blockID] = same();
/* 1278 */     array[Block.waterlily.blockID] = same();
/* 1279 */     array[Block.netherBrick.blockID] = same();
/* 1280 */     array[Block.netherFence.blockID] = same();
/* 1281 */     array[Block.stairsNetherBrick.blockID] = same();
/* 1282 */     array[Block.netherStalk.blockID] = air();
/* 1283 */     array[Block.enchantmentTable.blockID] = air();
/* 1284 */     array[Block.brewingStand.blockID] = air();
/* 1285 */     array[Block.cauldron.blockID] = air();
/* 1286 */     array[Block.endPortal.blockID] = same();
/* 1287 */     array[Block.endPortalFrame.blockID] = same();
/* 1288 */     array[Block.whiteStone.blockID] = same();
/* 1289 */     array[Block.dragonEgg.blockID] = same();
/* 1290 */     array[Block.redstoneLampIdle.blockID] = air();
/* 1291 */     array[Block.redstoneLampActive.blockID] = air();
/* 1292 */     array[Block.woodDoubleSlab.blockID] = same();
/* 1293 */     array[Block.woodSingleSlab.blockID] = same();
/* 1294 */     array[Block.cocoaPlant.blockID] = same();
/* 1295 */     array[Block.stairsSandStone.blockID] = same();
/* 1296 */     array[Block.oreEmerald.blockID] = stone();
/* 1297 */     array[Block.enderChest.blockID] = air();
/* 1298 */     array[Block.tripWireSource.blockID] = same();
/* 1299 */     array[Block.tripWire.blockID] = same();
/* 1300 */     array[Block.blockEmerald.blockID] = air();
/* 1301 */     array[Block.stairsWoodSpruce.blockID] = same();
/* 1302 */     array[Block.stairsWoodBirch.blockID] = same();
/* 1303 */     array[Block.stairsWoodJungle.blockID] = same();
/* 1304 */     array[Block.commandBlock.blockID] = air();
/* 1305 */     array[Block.beacon.blockID] = air();
/* 1306 */     array[Block.cobblestoneWall.blockID] = same();
/* 1307 */     array[Block.flowerPot.blockID] = same();
/* 1308 */     array[Block.carrot.blockID] = air();
/* 1309 */     array[Block.potato.blockID] = air();
/* 1310 */     array[Block.woodenButton.blockID] = air();
/* 1311 */     array[Block.skull.blockID] = air();
/* 1312 */     array[Block.anvil.blockID] = air();
/* 1313 */     array[Block.chestTrapped.blockID] = air();
/* 1314 */     array[Block.pressurePlateGold.blockID] = air();
/* 1315 */     array[Block.pressurePlateIron.blockID] = air();
/* 1316 */     array[Block.redstoneComparatorIdle.blockID] = air();
/* 1317 */     array[Block.redstoneComparatorActive.blockID] = air();
/* 1318 */     array[Block.daylightSensor.blockID] = air();
/* 1319 */     array[Block.blockRedstone.blockID] = air();
/* 1320 */     (new int[2])[0] = Block.netherrack.blockID; (new int[2])[1] = 0; array[Block.oreNetherQuartz.blockID] = new int[2];
/* 1321 */     array[Block.hopperBlock.blockID] = air();
/* 1322 */     array[Block.blockNetherQuartz.blockID] = air();
/* 1323 */     array[Block.stairsNetherQuartz.blockID] = same();
/* 1324 */     array[Block.railActivator.blockID] = air();
/* 1325 */     array[Block.dropper.blockID] = air();
/* 1326 */     array[Block.stainedClay.blockID] = same();
/* 1327 */     array[Block.hay.blockID] = air();
/* 1328 */     array[Block.carpet.blockID] = air();
/* 1329 */     array[Block.hardenedClay.blockID] = same();
/* 1330 */     array[Block.coalBlock.blockID] = air();
/* 1331 */     array[Block.runestoneAdamantium.blockID] = air();
/* 1332 */     array[Block.fenceAncientMetal.blockID] = air();
/* 1333 */     array[Block.oreCopper.blockID] = stone();
/* 1334 */     array[Block.oreSilver.blockID] = stone();
/* 1335 */     array[Block.oreMithril.blockID] = stone();
/* 1336 */     array[Block.oreAdamantium.blockID] = stone();
/* 1337 */     array[Block.blockCopper.blockID] = air();
/* 1338 */     array[Block.blockSilver.blockID] = air();
/* 1339 */     array[Block.blockMithril.blockID] = air();
/* 1340 */     array[Block.blockAdamantium.blockID] = air();
/* 1341 */     array[Block.doorCopper.blockID] = air();
/* 1342 */     array[Block.doorSilver.blockID] = air();
/* 1343 */     array[Block.doorGold.blockID] = air();
/* 1344 */     array[Block.doorMithril.blockID] = air();
/* 1345 */     array[Block.doorAdamantium.blockID] = air();
/* 1346 */     array[Block.fenceCopper.blockID] = air();
/* 1347 */     array[Block.fenceSilver.blockID] = air();
/* 1348 */     array[Block.fenceGold.blockID] = air();
/* 1349 */     array[Block.fenceMithril.blockID] = air();
/* 1350 */     array[Block.fenceAdamantium.blockID] = air();
/* 1351 */     array[Block.furnaceClayIdle.blockID] = air();
/* 1352 */     array[Block.furnaceClayBurning.blockID] = air();
/* 1353 */     array[Block.furnaceSandstoneIdle.blockID] = air();
/* 1354 */     array[Block.furnaceSandstoneBurning.blockID] = air();
/* 1355 */     array[Block.furnaceObsidianIdle.blockID] = air();
/* 1356 */     array[Block.furnaceObsidianBurning.blockID] = air();
/* 1357 */     array[Block.furnaceNetherrackIdle.blockID] = air();
/* 1358 */     array[Block.furnaceNetherrackBurning.blockID] = air();
/* 1359 */     array[Block.obsidianDoubleSlab.blockID] = air();
/* 1360 */     array[Block.obsidianSingleSlab.blockID] = air();
/* 1361 */     array[Block.stairsObsidian.blockID] = air();
/* 1362 */     array[Block.anvilCopper.blockID] = air();
/* 1363 */     array[Block.anvilSilver.blockID] = air();
/* 1364 */     array[Block.anvilGold.blockID] = air();
/* 1365 */     array[Block.anvilMithril.blockID] = air();
/* 1366 */     array[Block.anvilAdamantium.blockID] = air();
/* 1367 */     array[Block.onions.blockID] = air();
/* 1368 */     array[Block.cropsDead.blockID] = air();
/* 1369 */     array[Block.carrotDead.blockID] = air();
/* 1370 */     array[Block.potatoDead.blockID] = air();
/* 1371 */     array[Block.onionsDead.blockID] = air();
/* 1372 */     array[Block.chestCopper.blockID] = air();
/* 1373 */     array[Block.chestSilver.blockID] = air();
/* 1374 */     array[Block.chestGold.blockID] = air();
/* 1375 */     array[Block.chestIron.blockID] = air();
/* 1376 */     array[Block.chestMithril.blockID] = air();
/* 1377 */     array[Block.chestAdamantium.blockID] = air();
/* 1378 */     array[Block.enchantmentTableEmerald.blockID] = air();
/* 1379 */     array[Block.spark.blockID] = same();
/* 1380 */     array[Block.runestoneMithril.blockID] = air();
/* 1381 */     array[Block.flowerPotMulti.blockID] = same();
/* 1382 */     array[Block.bush.blockID] = air();
/* 1383 */     array[Block.furnaceHardenedClayIdle.blockID] = air();
/* 1384 */     array[Block.furnaceHardenedClayBurning.blockID] = air();
/* 1385 */     array[Block.blockAncientMetal.blockID] = air();
/* 1386 */     array[Block.doorAncientMetal.blockID] = air();
/* 1387 */     array[Block.anvilAncientMetal.blockID] = air();
/* 1388 */     array[Block.chestAncientMetal.blockID] = air();
/*      */     
/* 1390 */     for (int i = 0; i < array.length; i++) {
/*      */       
/* 1392 */       if (array[i] == null) {
/* 1393 */         (new int[2])[0] = 0; (new int[2])[1] = 0; array[i] = new int[2];
/*      */       } 
/*      */     } 
/* 1396 */     return array;
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\AnvilChunkLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */