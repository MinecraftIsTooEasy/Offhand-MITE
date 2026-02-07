/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
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
/*     */ public final class ChunkProviderClient
/*     */   implements IChunkProvider
/*     */ {
/*     */   public Chunk blankChunk;
/*  19 */   public LongHashMap chunkMapping = new LongHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  25 */   private List chunkListing = new ArrayList();
/*     */ 
/*     */   
/*     */   private World worldObj;
/*     */ 
/*     */   
/*     */   public ChunkProviderClient(World par1World) {
/*  32 */     this.blankChunk = new EmptyChunk(par1World, 0, 0);
/*  33 */     this.worldObj = par1World;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int par1, int par2) {
/*  41 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/*  46 */     Chunk var3 = (Chunk)this.chunkMapping.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(chunk_x, chunk_z));
/*  47 */     return (var3 == null) ? this.blankChunk : var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unloadChunk(int par1, int par2) {
/*  56 */     Chunk var3 = provideChunk(par1, par2);
/*     */     
/*  58 */     if (!var3.isEmpty())
/*     */     {
/*  60 */       var3.onChunkUnload();
/*     */     }
/*     */     
/*  63 */     this.chunkMapping.remove(ChunkCoordIntPair.chunkXZ2Int(par1, par2));
/*  64 */     this.chunkListing.remove(var3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk loadChunk(int par1, int par2) {
/*  72 */     Chunk var3 = new Chunk(this.worldObj, par1, par2);
/*  73 */     this.chunkMapping.add(ChunkCoordIntPair.chunkXZ2Int(par1, par2), var3);
/*  74 */     var3.isChunkLoaded = true;
/*  75 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int par1, int par2) {
/*  84 */     Chunk var3 = (Chunk)this.chunkMapping.getValueByKey(ChunkCoordIntPair.chunkXZ2Int(par1, par2));
/*  85 */     return (var3 == null) ? this.blankChunk : var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
/*  94 */     return true;
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
/* 108 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 116 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 129 */     return "MultiplayerChunkCache: " + this.chunkMapping.getNumHashElements();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
/* 145 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 150 */     return this.chunkListing.size();
/*     */   }
/*     */   
/*     */   public void recreateStructures(int par1, int par2) {}
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkProviderClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */