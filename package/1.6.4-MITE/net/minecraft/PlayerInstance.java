/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class PlayerInstance
/*     */ {
/*     */   private final List playersInChunk;
/*     */   private final ChunkCoordIntPair chunkLocation;
/*     */   private short[] locationOfBlockChange;
/*     */   private int numberOfTilesToUpdate;
/*     */   private int flagsYAreasToUpdate;
/*     */   private long previousWorldTime;
/*     */   final PlayerManager thePlayerManager;
/*     */   
/*     */   public PlayerInstance(PlayerManager par1PlayerManager, int par2, int par3) {
/*  31 */     this.thePlayerManager = par1PlayerManager;
/*  32 */     this.playersInChunk = new ArrayList();
/*  33 */     this.locationOfBlockChange = new short[64];
/*  34 */     this.chunkLocation = new ChunkCoordIntPair(par2, par3);
/*  35 */     (par1PlayerManager.getWorldServer()).theChunkProviderServer.loadChunk(par2, par3);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPlayer(ServerPlayer par1EntityPlayerMP) {
/*  40 */     if (this.playersInChunk.contains(par1EntityPlayerMP))
/*     */     {
/*  42 */       throw new IllegalStateException("Failed to add player. " + par1EntityPlayerMP + " already is in chunk " + this.chunkLocation.chunkXPos + ", " + this.chunkLocation.chunkZPos);
/*     */     }
/*     */ 
/*     */     
/*  46 */     if (this.playersInChunk.isEmpty())
/*     */     {
/*  48 */       this.previousWorldTime = PlayerManager.getWorldServer(this.thePlayerManager).getTotalWorldTime();
/*     */     }
/*     */     
/*  51 */     this.playersInChunk.add(par1EntityPlayerMP);
/*  52 */     par1EntityPlayerMP.loadedChunks.add(this.chunkLocation);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void removePlayer(ServerPlayer par1EntityPlayerMP) {
/*  58 */     if (this.playersInChunk.contains(par1EntityPlayerMP)) {
/*     */       
/*  60 */       Chunk var2 = PlayerManager.getWorldServer(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos);
/*  61 */       par1EntityPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet51MapChunk(var2, true, 0));
/*  62 */       this.playersInChunk.remove(par1EntityPlayerMP);
/*  63 */       par1EntityPlayerMP.loadedChunks.remove(this.chunkLocation);
/*     */       
/*  65 */       if (this.playersInChunk.isEmpty()) {
/*     */         
/*  67 */         long var3 = this.chunkLocation.chunkXPos + 2147483647L | this.chunkLocation.chunkZPos + 2147483647L << 32L;
/*  68 */         increaseInhabitedTime(var2);
/*  69 */         PlayerManager.getChunkWatchers(this.thePlayerManager).remove(var3);
/*  70 */         PlayerManager.getChunkWatcherList(this.thePlayerManager).remove(this);
/*     */         
/*  72 */         if (this.numberOfTilesToUpdate > 0)
/*     */         {
/*  74 */           PlayerManager.getChunkWatchersWithPlayers(this.thePlayerManager).remove(this);
/*     */         }
/*     */         
/*  77 */         (this.thePlayerManager.getWorldServer()).theChunkProviderServer.unloadChunksIfNotNearSpawn(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processChunk() {
/*  87 */     increaseInhabitedTime(PlayerManager.getWorldServer(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void increaseInhabitedTime(Chunk par1Chunk) {
/*  95 */     par1Chunk.inhabitedTime += PlayerManager.getWorldServer(this.thePlayerManager).getTotalWorldTime() - this.previousWorldTime;
/*  96 */     this.previousWorldTime = PlayerManager.getWorldServer(this.thePlayerManager).getTotalWorldTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void flagChunkForUpdate(int par1, int par2, int par3) {
/* 101 */     if (this.numberOfTilesToUpdate == 0)
/*     */     {
/* 103 */       PlayerManager.getChunkWatchersWithPlayers(this.thePlayerManager).add(this);
/*     */     }
/*     */     
/* 106 */     this.flagsYAreasToUpdate |= 1 << par2 >> 4;
/*     */     
/* 108 */     if (this.numberOfTilesToUpdate < 64) {
/*     */       
/* 110 */       short var4 = (short)(par1 << 12 | par3 << 8 | par2);
/*     */       
/* 112 */       for (int var5 = 0; var5 < this.numberOfTilesToUpdate; var5++) {
/*     */         
/* 114 */         if (this.locationOfBlockChange[var5] == var4) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 120 */       this.locationOfBlockChange[this.numberOfTilesToUpdate++] = var4;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendToAllPlayersWatchingChunk(Packet par1Packet) {
/* 131 */     for (int var2 = 0; var2 < this.playersInChunk.size(); var2++) {
/*     */       
/* 133 */       ServerPlayer var3 = this.playersInChunk.get(var2);
/*     */       
/* 135 */       if (!var3.loadedChunks.contains(this.chunkLocation))
/*     */       {
/* 137 */         var3.playerNetServerHandler.sendPacketToPlayer(par1Packet);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendChunkUpdate() {
/* 144 */     if (MITEConstant.usePacket51ForLargePacket52s()) {
/*     */       
/* 146 */       sendChunkUpdateMITE();
/*     */       
/*     */       return;
/*     */     } 
/* 150 */     if (this.numberOfTilesToUpdate != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 156 */       if (this.numberOfTilesToUpdate == 1) {
/*     */         
/* 158 */         int var1 = this.chunkLocation.chunkXPos * 16 + (this.locationOfBlockChange[0] >> 12 & 0xF);
/* 159 */         int var2 = this.locationOfBlockChange[0] & 0xFF;
/* 160 */         int var3 = this.chunkLocation.chunkZPos * 16 + (this.locationOfBlockChange[0] >> 8 & 0xF);
/* 161 */         sendToAllPlayersWatchingChunk(new Packet53BlockChange(var1, var2, var3, PlayerManager.getWorldServer(this.thePlayerManager)));
/*     */         
/* 163 */         if (PlayerManager.getWorldServer(this.thePlayerManager).blockHasTileEntity(var1, var2, var3))
/*     */         {
/* 165 */           sendTileToAllPlayersWatchingChunk(PlayerManager.getWorldServer(this.thePlayerManager).getBlockTileEntity(var1, var2, var3));
/*     */ 
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/* 172 */       else if (this.numberOfTilesToUpdate == 64) {
/*     */         
/* 174 */         int var1 = this.chunkLocation.chunkXPos * 16;
/* 175 */         int var2 = this.chunkLocation.chunkZPos * 16;
/* 176 */         sendToAllPlayersWatchingChunk(new Packet51MapChunk(PlayerManager.getWorldServer(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos), false, this.flagsYAreasToUpdate));
/*     */         
/* 178 */         for (int var3 = 0; var3 < 16; var3++)
/*     */         {
/* 180 */           if ((this.flagsYAreasToUpdate & 1 << var3) != 0)
/*     */           {
/* 182 */             int var4 = var3 << 4;
/* 183 */             List<TileEntity> var5 = PlayerManager.getWorldServer(this.thePlayerManager).getAllTileEntityInBox(var1, var4, var2, var1 + 16, var4 + 16, var2 + 16);
/*     */             
/* 185 */             for (int var6 = 0; var6 < var5.size(); var6++)
/*     */             {
/* 187 */               sendTileToAllPlayersWatchingChunk(var5.get(var6));
/*     */             }
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 196 */         if (MITEConstant.usePacket97MultiBlockChange()) {
/* 197 */           sendToAllPlayersWatchingChunk(new Packet97MultiBlockChange(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos, this.locationOfBlockChange, this.numberOfTilesToUpdate, PlayerManager.getWorldServer(this.thePlayerManager)));
/*     */         } else {
/* 199 */           sendToAllPlayersWatchingChunk(new Packet52MultiBlockChange(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos, this.locationOfBlockChange, this.numberOfTilesToUpdate, PlayerManager.getWorldServer(this.thePlayerManager)));
/*     */         } 
/* 201 */         for (int var1 = 0; var1 < this.numberOfTilesToUpdate; var1++) {
/*     */           
/* 203 */           int var2 = this.chunkLocation.chunkXPos * 16 + (this.locationOfBlockChange[var1] >> 12 & 0xF);
/* 204 */           int var3 = this.locationOfBlockChange[var1] & 0xFF;
/* 205 */           int var4 = this.chunkLocation.chunkZPos * 16 + (this.locationOfBlockChange[var1] >> 8 & 0xF);
/*     */           
/* 207 */           if (PlayerManager.getWorldServer(this.thePlayerManager).blockHasTileEntity(var2, var3, var4))
/*     */           {
/* 209 */             sendTileToAllPlayersWatchingChunk(PlayerManager.getWorldServer(this.thePlayerManager).getBlockTileEntity(var2, var3, var4));
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 215 */       this.numberOfTilesToUpdate = 0;
/* 216 */       this.flagsYAreasToUpdate = 0;
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
/*     */   private void sendChunkUpdateMITE() {
/* 228 */     if (this.numberOfTilesToUpdate != 0) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 234 */       if (this.numberOfTilesToUpdate == 1) {
/*     */         
/* 236 */         int var1 = this.chunkLocation.chunkXPos * 16 + (this.locationOfBlockChange[0] >> 12 & 0xF);
/* 237 */         int var2 = this.locationOfBlockChange[0] & 0xFF;
/* 238 */         int var3 = this.chunkLocation.chunkZPos * 16 + (this.locationOfBlockChange[0] >> 8 & 0xF);
/* 239 */         sendToAllPlayersWatchingChunk(new Packet53BlockChange(var1, var2, var3, PlayerManager.getWorldServer(this.thePlayerManager)));
/*     */         
/* 241 */         if (PlayerManager.getWorldServer(this.thePlayerManager).blockHasTileEntity(var1, var2, var3))
/*     */         {
/* 243 */           sendTileToAllPlayersWatchingChunk(PlayerManager.getWorldServer(this.thePlayerManager).getBlockTileEntity(var1, var2, var3));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
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
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 332 */         WorldServer world_server = PlayerManager.getWorldServer(this.thePlayerManager);
/*     */         
/* 334 */         Packet51MapChunk packet_51 = null;
/* 335 */         Packet52MultiBlockChange packet_52 = null;
/*     */         
/* 337 */         boolean is_dedicated_server = MinecraftServer.getServer().isDedicatedServer();
/*     */         
/* 339 */         Iterator<ServerPlayer> i = this.playersInChunk.iterator();
/*     */         
/* 341 */         while (i.hasNext()) {
/*     */           
/* 343 */           ServerPlayer player = i.next();
/*     */           
/* 345 */           if (!player.loadedChunks.contains(this.chunkLocation)) {
/*     */             boolean prevent_packet_52;
/*     */ 
/*     */ 
/*     */             
/* 350 */             if (this.numberOfTilesToUpdate == 64 || this.numberOfTilesToUpdate <= 8) {
/*     */               
/* 352 */               prevent_packet_52 = false;
/*     */             }
/*     */             else {
/*     */               
/* 356 */               int x = this.chunkLocation.chunkXPos * 16 + 8;
/* 357 */               int z = this.chunkLocation.chunkZPos * 16 + 8;
/*     */               
/* 359 */               int dx = x - (int)player.posX;
/* 360 */               int dz = z - (int)player.posZ;
/*     */               
/* 362 */               long distance_sq_to_player = (dx * dx + dz * dz);
/*     */               
/* 364 */               prevent_packet_52 = (distance_sq_to_player > 16384L);
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 370 */             if (this.numberOfTilesToUpdate == 64 || prevent_packet_52) {
/*     */               
/* 372 */               int j = this.chunkLocation.chunkXPos * 16;
/* 373 */               int var2 = this.chunkLocation.chunkZPos * 16;
/*     */               
/* 375 */               if (packet_51 == null) {
/* 376 */                 packet_51 = new Packet51MapChunk(PlayerManager.getWorldServer(this.thePlayerManager).getChunkFromChunkCoords(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos), false, this.flagsYAreasToUpdate);
/*     */               }
/* 378 */               player.playerNetServerHandler.sendPacketToPlayer(packet_51);
/*     */               
/* 380 */               for (int var3 = 0; var3 < 16; var3++) {
/*     */                 
/* 382 */                 if ((this.flagsYAreasToUpdate & 1 << var3) != 0) {
/*     */                   
/* 384 */                   int var4 = var3 << 4;
/* 385 */                   List<TileEntity> var5 = world_server.getAllTileEntityInBox(j, var4, var2, j + 16, var4 + 16, var2 + 16);
/*     */                   
/* 387 */                   for (int var6 = 0; var6 < var5.size(); var6++) {
/* 388 */                     sendTileToPlayer(var5.get(var6), player);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */               continue;
/*     */             } 
/* 394 */             if (packet_52 == null) {
/* 395 */               packet_52 = new Packet52MultiBlockChange(this.chunkLocation.chunkXPos, this.chunkLocation.chunkZPos, this.locationOfBlockChange, this.numberOfTilesToUpdate, PlayerManager.getWorldServer(this.thePlayerManager));
/*     */             }
/* 397 */             player.playerNetServerHandler.sendPacketToPlayer(packet_52);
/*     */             
/* 399 */             for (int var1 = 0; var1 < this.numberOfTilesToUpdate; var1++) {
/*     */               
/* 401 */               int var2 = this.chunkLocation.chunkXPos * 16 + (this.locationOfBlockChange[var1] >> 12 & 0xF);
/* 402 */               int var3 = this.locationOfBlockChange[var1] & 0xFF;
/* 403 */               int var4 = this.chunkLocation.chunkZPos * 16 + (this.locationOfBlockChange[var1] >> 8 & 0xF);
/*     */               
/* 405 */               if (world_server.blockHasTileEntity(var2, var3, var4)) {
/* 406 */                 sendTileToPlayer(world_server.getBlockTileEntity(var2, var3, var4), player);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 415 */       this.numberOfTilesToUpdate = 0;
/* 416 */       this.flagsYAreasToUpdate = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendTileToAllPlayersWatchingChunk(TileEntity par1TileEntity) {
/* 422 */     if (par1TileEntity != null) {
/*     */       
/* 424 */       Packet var2 = par1TileEntity.getDescriptionPacket();
/*     */       
/* 426 */       if (var2 != null)
/*     */       {
/* 428 */         sendToAllPlayersWatchingChunk(var2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void sendTileToPlayer(TileEntity tile_entity, ServerPlayer player) {
/* 435 */     if (tile_entity == null) {
/*     */       return;
/*     */     }
/* 438 */     Packet packet = tile_entity.getDescriptionPacket();
/*     */     
/* 440 */     if (packet != null) {
/* 441 */       player.playerNetServerHandler.sendPacketToPlayer(packet);
/*     */     }
/*     */   }
/*     */   
/*     */   static ChunkCoordIntPair getChunkLocation(PlayerInstance par0PlayerInstance) {
/* 446 */     return par0PlayerInstance.chunkLocation;
/*     */   }
/*     */ 
/*     */   
/*     */   static List getPlayersInChunk(PlayerInstance par0PlayerInstance) {
/* 451 */     return par0PlayerInstance.playersInChunk;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\PlayerInstance.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */