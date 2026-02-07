/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ public class ChunkProviderFlat
/*     */   implements IChunkProvider {
/*     */   private World worldObj;
/*     */   private Random random;
/*  13 */   private final byte[] cachedBlockIDs = new byte[256];
/*  14 */   private final byte[] cachedBlockMetadata = new byte[256];
/*     */   private final FlatGeneratorInfo flatWorldGenInfo;
/*  16 */   private final List structureGenerators = new ArrayList();
/*     */   
/*     */   private final boolean hasDecoration;
/*     */   private final boolean hasDungeons;
/*     */   private WorldGenLakes waterLakeGenerator;
/*     */   private WorldGenLakes lavaLakeGenerator;
/*     */   
/*     */   public ChunkProviderFlat(World par1World, long par2, boolean par4, String par5Str) {
/*  24 */     this.worldObj = par1World;
/*  25 */     this.random = new Random(par2);
/*  26 */     this.flatWorldGenInfo = FlatGeneratorInfo.createFlatGeneratorFromString(par5Str);
/*     */     
/*  28 */     if (par4) {
/*     */       
/*  30 */       Map var6 = this.flatWorldGenInfo.getWorldFeatures();
/*     */       
/*  32 */       if (var6.containsKey("village")) {
/*     */         
/*  34 */         Map<String, String> var7 = (Map)var6.get("village");
/*     */         
/*  36 */         if (!var7.containsKey("size"))
/*     */         {
/*  38 */           var7.put("size", "1");
/*     */         }
/*     */         
/*  41 */         this.structureGenerators.add(new MapGenVillage(var7));
/*     */       } 
/*     */       
/*  44 */       if (var6.containsKey("biome_1"))
/*     */       {
/*  46 */         this.structureGenerators.add(new MapGenScatteredFeature((Map)var6.get("biome_1")));
/*     */       }
/*     */       
/*  49 */       if (var6.containsKey("mineshaft"))
/*     */       {
/*  51 */         this.structureGenerators.add(new MapGenMineshaft((Map)var6.get("mineshaft")));
/*     */       }
/*     */       
/*  54 */       if (var6.containsKey("stronghold"))
/*     */       {
/*  56 */         this.structureGenerators.add(new MapGenStronghold((Map)var6.get("stronghold")));
/*     */       }
/*     */     } 
/*     */     
/*  60 */     this.hasDecoration = this.flatWorldGenInfo.getWorldFeatures().containsKey("decoration");
/*     */     
/*  62 */     if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lake"))
/*     */     {
/*  64 */       this.waterLakeGenerator = new WorldGenLakes(Block.waterStill.blockID);
/*     */     }
/*     */     
/*  67 */     if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lava_lake"))
/*     */     {
/*  69 */       this.lavaLakeGenerator = new WorldGenLakes(Block.lavaStill.blockID);
/*     */     }
/*     */     
/*  72 */     this.hasDungeons = this.flatWorldGenInfo.getWorldFeatures().containsKey("dungeon");
/*  73 */     Iterator<FlatLayerInfo> var9 = this.flatWorldGenInfo.getFlatLayers().iterator();
/*     */     
/*  75 */     while (var9.hasNext()) {
/*     */       
/*  77 */       FlatLayerInfo var10 = var9.next();
/*     */       
/*  79 */       for (int var8 = var10.getMinY(); var8 < var10.getMinY() + var10.getLayerCount(); var8++) {
/*     */         
/*  81 */         this.cachedBlockIDs[var8] = (byte)(var10.getFillBlock() & 0xFF);
/*  82 */         this.cachedBlockMetadata[var8] = (byte)var10.getFillBlockMeta();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk loadChunk(int par1, int par2) {
/*  92 */     return provideChunk(par1, par2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Chunk provideChunk(int par1, int par2) {
/* 101 */     Chunk var3 = new Chunk(this.worldObj, par1, par2);
/*     */     
/* 103 */     for (int var4 = 0; var4 < this.cachedBlockIDs.length; var4++) {
/*     */       
/* 105 */       int var5 = var4 >> 4;
/* 106 */       ExtendedBlockStorage var6 = var3.getBlockStorageArray()[var5];
/*     */       
/* 108 */       if (var6 == null) {
/*     */         
/* 110 */         var6 = new ExtendedBlockStorage(var4, !this.worldObj.provider.hasNoSky);
/* 111 */         var3.getBlockStorageArray()[var5] = var6;
/*     */       } 
/*     */       
/* 114 */       for (int var7 = 0; var7 < 16; var7++) {
/*     */         
/* 116 */         for (int var8 = 0; var8 < 16; var8++) {
/*     */           
/* 118 */           var6.setExtBlockID(var7, var4 & 0xF, var8, this.cachedBlockIDs[var4] & 0xFF);
/* 119 */           var6.setExtBlockMetadata(var7, var4 & 0xF, var8, this.cachedBlockMetadata[var4]);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 125 */     var3.generateSkylightMap(true);
/* 126 */     BiomeGenBase[] var9 = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[])null, par1 * 16, par2 * 16, 16, 16);
/* 127 */     byte[] var10 = var3.getBiomeArray();
/*     */     
/* 129 */     for (int var11 = 0; var11 < var10.length; var11++)
/*     */     {
/* 131 */       var10[var11] = (byte)(var9[var11]).biomeID;
/*     */     }
/*     */     
/* 134 */     Iterator<MapGenStructure> var12 = this.structureGenerators.iterator();
/*     */     
/* 136 */     while (var12.hasNext()) {
/*     */       
/* 138 */       MapGenStructure var13 = var12.next();
/* 139 */       var13.generate(this, this.worldObj, par1, par2, (byte[])null);
/*     */     } 
/*     */ 
/*     */     
/* 143 */     var3.generateSkylightMap(true);
/* 144 */     return var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean chunkExists(int par1, int par2) {
/* 152 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunkIfItExists(int chunk_x, int chunk_z) {
/* 157 */     Minecraft.setErrorMessage("getChunkIfItExists: called for ChunkProviderFlat");
/* 158 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) {
/* 166 */     int var4 = par2 * 16;
/* 167 */     int var5 = par3 * 16;
/* 168 */     BiomeGenBase var6 = this.worldObj.getBiomeGenForCoords(var4 + 16, var5 + 16);
/* 169 */     boolean var7 = false;
/* 170 */     this.random.setSeed(this.worldObj.getSeed());
/* 171 */     long var8 = this.random.nextLong() / 2L * 2L + 1L;
/* 172 */     long var10 = this.random.nextLong() / 2L * 2L + 1L;
/* 173 */     this.random.setSeed(par2 * var8 + par3 * var10 ^ this.worldObj.getSeed());
/* 174 */     Iterator<MapGenStructure> var12 = this.structureGenerators.iterator();
/*     */     
/* 176 */     while (var12.hasNext()) {
/*     */       
/* 178 */       MapGenStructure var13 = var12.next();
/* 179 */       boolean var14 = var13.generateStructuresInChunk(this.worldObj, this.random, par2, par3);
/*     */       
/* 181 */       if (var13 instanceof MapGenVillage)
/*     */       {
/* 183 */         var7 |= var14;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     if (this.waterLakeGenerator != null && !var7 && this.random.nextInt(4) == 0) {
/*     */       
/* 193 */       int var16 = var4 + this.random.nextInt(16) + 8;
/* 194 */       int var17 = this.random.nextInt(128);
/* 195 */       int var18 = var5 + this.random.nextInt(16) + 8;
/* 196 */       this.waterLakeGenerator.generate(this.worldObj, this.random, var16, var17, var18);
/*     */     } 
/*     */     
/* 199 */     if (this.lavaLakeGenerator != null && !var7 && this.random.nextInt(8) == 0) {
/*     */       
/* 201 */       int var16 = var4 + this.random.nextInt(16) + 8;
/* 202 */       int var17 = this.random.nextInt(this.random.nextInt(120) + 8);
/* 203 */       int var18 = var5 + this.random.nextInt(16) + 8;
/*     */       
/* 205 */       if (var17 < 63 || this.random.nextInt(10) == 0)
/*     */       {
/* 207 */         this.lavaLakeGenerator.generate(this.worldObj, this.random, var16, var17, var18);
/*     */       }
/*     */     } 
/*     */     
/* 211 */     if (this.hasDungeons)
/*     */     {
/* 213 */       for (int var16 = 0; var16 < 8; var16++) {
/*     */         
/* 215 */         int var17 = var4 + this.random.nextInt(16) + 8;
/* 216 */         int var18 = this.random.nextInt(128);
/* 217 */         int var15 = var5 + this.random.nextInt(16) + 8;
/* 218 */         (new WorldGenDungeons()).generate(this.worldObj, this.random, var17, var18, var15);
/*     */       } 
/*     */     }
/*     */     
/* 222 */     if (this.hasDecoration)
/*     */     {
/* 224 */       var6.decorate(this.worldObj, this.random, var4, var5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate) {
/* 234 */     return true;
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
/* 248 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSave() {
/* 256 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String makeString() {
/* 264 */     return "FlatLevelSource";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4) {
/* 272 */     BiomeGenBase var5 = this.worldObj.getBiomeGenForCoords(par2, par4);
/* 273 */     return (var5 == null) ? null : var5.getSpawnableList(par1EnumCreatureType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition findClosestStructure(World par1World, String par2Str, int par3, int par4, int par5) {
/* 281 */     if ("Stronghold".equals(par2Str)) {
/*     */       
/* 283 */       Iterator<MapGenStructure> var6 = this.structureGenerators.iterator();
/*     */       
/* 285 */       while (var6.hasNext()) {
/*     */         
/* 287 */         MapGenStructure var7 = var6.next();
/*     */         
/* 289 */         if (var7 instanceof MapGenStronghold)
/*     */         {
/* 291 */           return var7.getNearestInstance(par1World, par3, par4, par5);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 296 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLoadedChunkCount() {
/* 301 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recreateStructures(int par1, int par2) {
/* 306 */     Iterator<MapGenStructure> var3 = this.structureGenerators.iterator();
/*     */     
/* 308 */     while (var3.hasNext()) {
/*     */       
/* 310 */       MapGenStructure var4 = var3.next();
/* 311 */       var4.generate(this, this.worldObj, par1, par2, (byte[])null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkProviderFlat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */