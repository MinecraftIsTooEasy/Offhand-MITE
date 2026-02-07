/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ public abstract class MapGenStructure
/*     */   extends MapGenBase
/*     */ {
/*     */   private MapGenStructureData field_143029_e;
/*  14 */   protected Map structureMap = new HashMap<Object, Object>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String func_143025_a();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected final void recursiveGenerate(World world, int i, int j, int k, int l, byte[] bs) {
/*  25 */     func_143027_a(world);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  31 */     if (this.structureMap.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)))) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/*  36 */     this.rand.nextInt();
/*     */     
/*     */     try {
/*  39 */       if (canSpawnStructureAtCoords(i, j)) {
/*     */         
/*  41 */         StructureStart structureStart = getStructureStart(i, j);
/*  42 */         this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)), structureStart);
/*  43 */         func_143026_a(i, j, structureStart);
/*     */       } 
/*  45 */     } catch (Throwable throwable) {
/*  46 */       CrashReport crashReport = CrashReport.makeCrashReport(throwable, "Exception preparing structure feature");
/*  47 */       CrashReportCategory crashReportCategory = crashReport.makeCategory("Feature being prepared");
/*     */       
/*  49 */       crashReportCategory.addCrashSectionCallable("Is feature chunk", new CallableIsFeatureChunk(this, i, j));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  56 */       crashReportCategory.addCrashSection("Chunk location", String.format("%d,%d", new Object[] { Integer.valueOf(i), Integer.valueOf(j) }));
/*     */       
/*  58 */       crashReportCategory.addCrashSectionCallable("Chunk pos hash", new CallableChunkPosHash(this, i, j));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  65 */       crashReportCategory.addCrashSectionCallable("Structure type", new CallableStructureType(this));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  72 */       throw new ReportedException(crashReport);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean generateStructuresInChunk(World world, Random random, int i, int j) {
/*  78 */     func_143027_a(world);
/*     */     
/*  80 */     int k = (i << 4) + 8;
/*  81 */     int m = (j << 4) + 8;
/*     */     
/*  83 */     boolean bool = false;
/*  84 */     for (StructureStart structureStart : this.structureMap.values()) {
/*  85 */       if (structureStart.isSizeableStructure() && 
/*  86 */         structureStart.getBoundingBox().intersectsWith(k, m, k + 15, m + 15)) {
/*  87 */         structureStart.generateStructure(world, random, new StructureBoundingBox(k, m, k + 15, m + 15));
/*  88 */         bool = true;
/*     */ 
/*     */         
/*  91 */         func_143026_a(structureStart.func_143019_e(), structureStart.func_143018_f(), structureStart);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  96 */     return bool;
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
/*     */   public boolean hasStructureAt(int i, int j, int k) {
/* 130 */     func_143027_a(this.worldObj);
/* 131 */     return (func_143028_c(i, j, k) != null);
/*     */   }
/*     */   
/*     */   protected StructureStart func_143028_c(int i, int j, int k) {
/* 135 */     for (StructureStart structureStart : this.structureMap.values()) {
/* 136 */       if (structureStart.isSizeableStructure() && 
/* 137 */         structureStart.getBoundingBox().intersectsWith(i, k, i, k)) {
/*     */         
/* 139 */         Iterator<StructureComponent> iterator = structureStart.getComponents().iterator();
/* 140 */         while (iterator.hasNext()) {
/* 141 */           StructureComponent structureComponent = iterator.next();
/* 142 */           if (structureComponent.getBoundingBox().isVecInside(i, j, k)) {
/* 143 */             return structureStart;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 149 */     return null;
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
/*     */   public boolean func_142038_b(int i, int j, int k) {
/* 163 */     func_143027_a(this.worldObj);
/*     */     
/* 165 */     for (StructureStart structureStart : this.structureMap.values()) {
/* 166 */       if (structureStart.isSizeableStructure()) {
/* 167 */         return structureStart.getBoundingBox().intersectsWith(i, k, i, k);
/*     */       }
/*     */     } 
/* 170 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ChunkPosition getNearestInstance(World world, int i, int j, int k) {
/* 177 */     this.worldObj = world;
/*     */     
/* 179 */     func_143027_a(world);
/*     */     
/* 181 */     this.rand.setSeed(world.getSeed());
/* 182 */     long l1 = this.rand.nextLong();
/* 183 */     long l2 = this.rand.nextLong();
/* 184 */     long l3 = (i >> 4) * l1;
/* 185 */     long l4 = (k >> 4) * l2;
/* 186 */     this.rand.setSeed(l3 ^ l4 ^ world.getSeed());
/*     */     
/* 188 */     recursiveGenerate(world, i >> 4, k >> 4, 0, 0, (byte[])null);
/*     */     
/* 190 */     double d = Double.MAX_VALUE;
/* 191 */     ChunkPosition chunkPosition = null;
/*     */     
/* 193 */     for (StructureStart structureStart : this.structureMap.values()) {
/* 194 */       if (structureStart.isSizeableStructure()) {
/*     */         
/* 196 */         StructureComponent structureComponent = structureStart.getComponents().get(0);
/* 197 */         ChunkPosition chunkPosition1 = structureComponent.getCenter();
/*     */         
/* 199 */         int m = chunkPosition1.x - i;
/* 200 */         int n = chunkPosition1.y - j;
/* 201 */         int i1 = chunkPosition1.z - k;
/* 202 */         double d1 = (m * m + n * n + i1 * i1);
/*     */         
/* 204 */         if (d1 < d) {
/* 205 */           d = d1;
/* 206 */           chunkPosition = chunkPosition1;
/*     */         } 
/*     */       } 
/*     */     } 
/* 210 */     if (chunkPosition != null) {
/* 211 */       return chunkPosition;
/*     */     }
/* 213 */     List list = getCoordList();
/* 214 */     if (list != null) {
/* 215 */       ChunkPosition chunkPosition1 = null;
/* 216 */       for (ChunkPosition chunkPosition2 : list) {
/*     */         
/* 218 */         int m = chunkPosition2.x - i;
/* 219 */         int n = chunkPosition2.y - j;
/* 220 */         int i1 = chunkPosition2.z - k;
/* 221 */         double d1 = (m * m + n * n + i1 * i1);
/*     */         
/* 223 */         if (d1 < d) {
/* 224 */           d = d1;
/* 225 */           chunkPosition1 = chunkPosition2;
/*     */         } 
/*     */       } 
/* 228 */       return chunkPosition1;
/*     */     } 
/*     */     
/* 231 */     return null;
/*     */   }
/*     */   
/*     */   protected List getCoordList() {
/* 235 */     return null;
/*     */   }
/*     */   
/*     */   private void func_143027_a(World world) {
/* 239 */     if (this.field_143029_e == null) {
/* 240 */       this.field_143029_e = (MapGenStructureData)world.loadItemData(MapGenStructureData.class, func_143025_a());
/*     */       
/* 242 */       if (this.field_143029_e == null) {
/* 243 */         this.field_143029_e = new MapGenStructureData(func_143025_a());
/* 244 */         world.setItemData(func_143025_a(), this.field_143029_e);
/*     */       } else {
/* 246 */         NBTTagCompound nBTTagCompound = this.field_143029_e.func_143041_a();
/*     */         
/* 248 */         for (NBTBase nBTBase : nBTTagCompound.getTags()) {
/* 249 */           if (nBTBase.getId() == 10) {
/* 250 */             NBTTagCompound nBTTagCompound1 = (NBTTagCompound)nBTBase;
/*     */             
/* 252 */             if (nBTTagCompound1.hasKey("ChunkX") && nBTTagCompound1.hasKey("ChunkZ")) {
/* 253 */               int i = nBTTagCompound1.getInteger("ChunkX");
/* 254 */               int j = nBTTagCompound1.getInteger("ChunkZ");
/*     */               
/* 256 */               StructureStart structureStart = MapGenStructureIO.func_143035_a(nBTTagCompound1, world);
/*     */               
/* 258 */               this.structureMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(i, j)), structureStart);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void func_143026_a(int i, int j, StructureStart structureStart) {
/* 267 */     this.field_143029_e.func_143043_a(structureStart.func_143021_a(i, j), i, j);
/* 268 */     this.field_143029_e.markDirty();
/*     */   }
/*     */   
/*     */   protected abstract boolean canSpawnStructureAtCoords(int paramInt1, int paramInt2);
/*     */   
/*     */   protected abstract StructureStart getStructureStart(int paramInt1, int paramInt2);
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenStructure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */