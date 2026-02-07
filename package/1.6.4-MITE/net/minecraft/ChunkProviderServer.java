/*     */ package net.minecraft;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ChunkProviderServer
/*     */   implements IChunkProvider
/*     */ {
/*  18 */   private Set chunksToUnload = new HashSet();
/*     */ 
/*     */   
/*     */   public Chunk defaultEmptyChunk;
/*     */   
/*     */   private IChunkProvider currentChunkProvider;
/*     */   
/*     */   private IChunkLoader currentChunkLoader;
/*     */   
/*     */   public boolean loadChunkOnProvideRequest = true;
/*     */   
/*  29 */   public LongHashMap loadedChunkHashMap = new LongHashMap();
/*  30 */   private List loadedChunks = new ArrayList();
/*     */   
/*     */   public WorldServer worldObj;
/*     */ 
/*     */   
/*     */   public ChunkProviderServer(WorldServer par1WorldServer, IChunkLoader par2IChunkLoader, IChunkProvider par3IChunkProvider) {
/*  36 */     this.defaultEmptyChunk = new EmptyChunk(par1WorldServer, 0, 0);
/*  37 */     this.worldObj = par1WorldServer;
/*  38 */     this.currentChunkLoader = par2IChunkLoader;
/*  39 */     this.currentChunkProvider = par3IChunkProvider;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int par1, int par2) {
/*  47 */     return this.loadedChunkHashMap.containsItem(ChunkCoordIntPair.chunkXZ2Int(par1, par2));
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/*  52 */     return (Chunk)this.loadedChunkHashMap.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(chunk_x, chunk_z));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unloadChunksIfNotNearSpawn(int par1, int par2) {
/*  61 */     if (this.worldObj.provider.canRespawnHere()) {
/*     */       
/*  63 */       ChunkCoordinates var3 = this.worldObj.getSpawnPoint();
/*  64 */       int var4 = par1 * 16 + 8 - var3.posX;
/*  65 */       int var5 = par2 * 16 + 8 - var3.posZ;
/*  66 */       short var6 = 128;
/*     */       
/*  68 */       if (var4 < -var6 || var4 > var6 || var5 < -var6 || var5 > var6)
/*     */       {
/*  70 */         this.chunksToUnload.add(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(par1, par2)));
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/*  75 */       this.chunksToUnload.add(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(par1, par2)));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unloadAllChunks() {
/*  84 */     Iterator<Chunk> var1 = this.loadedChunks.iterator();
/*     */     
/*  86 */     while (var1.hasNext()) {
/*     */       
/*  88 */       Chunk var2 = var1.next();
/*  89 */       unloadChunksIfNotNearSpawn(var2.xPosition, var2.zPosition);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void finalCleanup() {
/*  95 */     this.loadedChunks.clear();
/*     */     
/*  97 */     this.chunksToUnload.clear();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     this.loadedChunkHashMap.hashArray = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk loadChunk(int par1, int par2) {
/* 116 */     long var3 = ChunkCoordIntPair.chunkXZ2Int(par1, par2);
/* 117 */     this.chunksToUnload.remove(Long.valueOf(var3));
/* 118 */     Chunk var5 = (Chunk)this.loadedChunkHashMap.getValueByKey(var3);
/*     */     
/* 120 */     if (var5 == null) {
/*     */       
/* 122 */       var5 = safeLoadChunk(par1, par2);
/*     */       
/* 124 */       if (var5 == null)
/*     */       {
/* 126 */         if (this.currentChunkProvider == null) {
/*     */           
/* 128 */           var5 = this.defaultEmptyChunk;
/*     */         } else {
/*     */ 
/*     */           
/*     */           try {
/*     */ 
/*     */ 
/*     */             
/* 136 */             var5 = this.currentChunkProvider.provideChunk(par1, par2);
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 141 */           catch (Throwable var9) {
/*     */             
/* 143 */             CrashReport var7 = CrashReport.makeCrashReport(var9, "Exception generating new chunk");
/* 144 */             CrashReportCategory var8 = var7.makeCategory("Chunk to be generated");
/* 145 */             var8.addCrashSection("Location", String.format("%d,%d", new Object[] { Integer.valueOf(par1), Integer.valueOf(par2) }));
/* 146 */             var8.addCrashSection("Position hash", Long.valueOf(var3));
/* 147 */             var8.addCrashSection("Generator", this.currentChunkProvider.makeString());
/* 148 */             throw new ReportedException(var7);
/*     */           } 
/*     */         } 
/*     */       }
/*     */       
/* 153 */       this.loadedChunkHashMap.add(var3, var5);
/* 154 */       this.loadedChunks.add(var5);
/*     */ 
/*     */ 
/*     */       
/* 158 */       if (var5 != null)
/*     */       {
/* 160 */         var5.onChunkLoad();
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 167 */       var5.populateChunk(this, this, par1, par2);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 174 */     return var5;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int par1, int par2) {
/* 183 */     Chunk var3 = (Chunk)this.loadedChunkHashMap.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(par1, par2));
/* 184 */     return (var3 == null) ? ((!this.worldObj.findingSpawnPoint && !this.loadChunkOnProvideRequest) ? this.defaultEmptyChunk : loadChunk(par1, par2)) : var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Chunk safeLoadChunk(int par1, int par2) {
/* 192 */     if (this.currentChunkLoader == null)
/*     */     {
/* 194 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 200 */       Chunk var3 = this.currentChunkLoader.loadChunk(this.worldObj, par1, par2);
/*     */       
/* 202 */       if (var3 != null) {
/*     */         
/* 204 */         var3.lastSaveTime = this.worldObj.getTotalWorldTime();
/*     */         
/* 206 */         if (this.currentChunkProvider != null)
/*     */         {
/* 208 */           this.currentChunkProvider.recreateStructures(par1, par2);
/*     */         }
/*     */       } 
/*     */       
/* 212 */       return var3;
/*     */     }
/* 214 */     catch (Exception var4) {
/*     */       
/* 216 */       var4.printStackTrace();
/* 217 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void safeSaveExtraChunkData(Chunk par1Chunk) {
/* 227 */     if (this.currentChunkLoader != null) {
/*     */       
/*     */       try {
/*     */         
/* 231 */         this.currentChunkLoader.saveExtraChunkData(this.worldObj, par1Chunk);
/*     */       }
/* 233 */       catch (Exception var3) {
/*     */         
/* 235 */         var3.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void safeSaveChunk(Chunk par1Chunk) {
/* 246 */     if (this.currentChunkLoader != null) {
/*     */       
/*     */       try {
/*     */         
/* 250 */         par1Chunk.lastSaveTime = this.worldObj.getTotalWorldTime();
/* 251 */         this.currentChunkLoader.saveChunk(this.worldObj, par1Chunk);
/*     */       }
/* 253 */       catch (IOException var3) {
/*     */         
/* 255 */         var3.printStackTrace();
/*     */       }
/* 257 */       catch (MinecraftException var4) {
/*     */         
/* 259 */         var4.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
/* 269 */     Chunk var4 = provideChunk(par2, par3);
/*     */     
/* 271 */     if (!var4.isTerrainPopulated) {
/*     */       
/* 273 */       var4.isTerrainPopulated = true;
/*     */       
/* 275 */       if (this.currentChunkProvider != null) {
/*     */         
/* 277 */         this.currentChunkProvider.populate(par1IChunkProvider, par2, par3);
/* 278 */         var4.setChunkModified();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
/* 289 */     int var3 = 0;
/*     */     
/* 291 */     for (int var4 = 0; var4 < this.loadedChunks.size(); var4++) {
/*     */       
/* 293 */       Chunk var5 = this.loadedChunks.get(var4);
/*     */       
/* 295 */       if (par1)
/*     */       {
/* 297 */         safeSaveExtraChunkData(var5);
/*     */       }
/*     */       
/* 300 */       if (var5.needsSaving(par1)) {
/*     */         
/* 302 */         safeSaveChunk(var5);
/* 303 */         var5.isModified = false;
/*     */         
/* 305 */         var5.should_be_saved_once_time_forwarding_is_completed = false;
/* 306 */         var3++;
/*     */         
/* 308 */         if (var3 == 24 && !par1)
/*     */         {
/* 310 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 315 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveExtraData() {
/* 324 */     if (this.currentChunkLoader != null)
/*     */     {
/* 326 */       this.currentChunkLoader.saveExtraData();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean unloadQueuedChunks() {
/* 335 */     if (!this.worldObj.canNotSave) {
/*     */       
/* 337 */       for (int var1 = 0; var1 < 100; var1++) {
/*     */         
/* 339 */         if (!this.chunksToUnload.isEmpty()) {
/*     */           
/* 341 */           Long var2 = this.chunksToUnload.iterator().next();
/* 342 */           Chunk var3 = (Chunk)this.loadedChunkHashMap.getValueByKey(var2.longValue());
/* 343 */           var3.onChunkUnload();
/*     */ 
/*     */ 
/*     */           
/* 347 */           if (!MinecraftServer.treachery_detected) {
/*     */             
/* 349 */             safeSaveChunk(var3);
/* 350 */             safeSaveExtraChunkData(var3);
/*     */           } 
/*     */           
/* 353 */           this.chunksToUnload.remove(var2);
/* 354 */           this.loadedChunkHashMap.remove(var2.longValue());
/* 355 */           this.loadedChunks.remove(var3);
/*     */         } 
/*     */       } 
/*     */       
/* 359 */       if (this.currentChunkLoader != null)
/*     */       {
/* 361 */         this.currentChunkLoader.chunkTick();
/*     */       }
/*     */     } 
/*     */     
/* 365 */     return this.currentChunkProvider.unloadQueuedChunks();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 373 */     return !this.worldObj.canNotSave;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 381 */     return "ServerChunkCache: " + this.loadedChunkHashMap.getNumHashElements() + " Drop: " + this.chunksToUnload.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/* 389 */     return this.currentChunkProvider.getPossibleCreatures(par1EnumCreatureType, par2, par3, par4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
/* 397 */     return this.currentChunkProvider.findClosestStructure(par1World, par2Str, par3, par4, par5);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 402 */     return this.loadedChunkHashMap.getNumHashElements();
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(int par1, int par2) {}
/*     */   
/*     */   public IChunkProvider getChunkProvider() {
/* 409 */     return this.currentChunkProvider;
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkProviderServer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */