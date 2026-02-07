/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapGenCaveNetwork
/*     */   extends MapGenBase
/*     */ {
/*  11 */   protected int a = 3;
/*     */ 
/*     */   
/*     */   private CaveNetworkStub stub;
/*     */   
/*  16 */   public final HashMap cached_stubs = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*  19 */   public final HashMap cached_cave_network_generators = new HashMap<Object, Object>();
/*     */ 
/*     */   
/*     */   public void generate(IChunkProvider chunk_provider, World world, int chunk_x, int chunk_z, byte[] block_ids) {
/*  23 */     CaveNetworkStub stub = getCaveNetworkStubAt(world, chunk_x, chunk_z);
/*     */     
/*  25 */     if (stub == null) {
/*     */       return;
/*     */     }
/*  28 */     generateCaveNetwork(chunk_x, chunk_z, block_ids, stub);
/*     */   }
/*     */ 
/*     */   
/*     */   private void generateCaveNetwork(int chunk_x, int chunk_z, byte[] block_ids, CaveNetworkStub stub) {
/*  33 */     int hash = stub.getOriginChunkCoordsHash();
/*     */     
/*  35 */     CaveNetworkGenerator cg = (CaveNetworkGenerator)this.cached_cave_network_generators.get(Integer.valueOf(hash));
/*     */     
/*  37 */     if (cg == null || cg.getOriginChunkX() != stub.getOriginChunkX() || cg.getOriginChunkZ() != stub.getOriginChunkZ()) {
/*     */       
/*  39 */       if (cg != null) {
/*  40 */         Debug.setErrorMessage("generateCaveNetwork: hash collision");
/*     */       }
/*  42 */       this.cached_cave_network_generators.put(Integer.valueOf(hash), cg = new CaveNetworkGenerator(stub));
/*     */     } 
/*     */     
/*  45 */     cg.apply(chunk_x, chunk_z, stub.getOriginChunkX(), stub.getOriginChunkZ(), block_ids);
/*     */   }
/*     */ 
/*     */   
/*     */   private CaveNetworkStub getOrCreateCaveNetworkStub(World world, int origin_chunk_x, int origin_chunk_z, double distance_from_world_origin) {
/*  50 */     int hash = Chunk.getChunkCoordsHash(origin_chunk_x, origin_chunk_z);
/*     */     
/*  52 */     CaveNetworkStub stub = (CaveNetworkStub)this.cached_stubs.get(Integer.valueOf(hash));
/*     */     
/*  54 */     if (stub == null || stub.getOriginChunkX() != origin_chunk_x || stub.getOriginChunkZ() != origin_chunk_z) {
/*     */       
/*  56 */       if (stub != null) {
/*  57 */         Debug.setErrorMessage("getOrCreateCaveNetworkStub: hash collision");
/*     */       }
/*  59 */       boolean has_mycelium = (distance_from_world_origin >= 1500.0D && this.rand.nextInt(8) == 0);
/*     */       
/*  61 */       long seed = this.rand.nextLong();
/*     */       
/*  63 */       if (world.getSeed() == 1L && origin_chunk_x == -14 && origin_chunk_z == 29) {
/*  64 */         seed = 2617667064333438329L;
/*     */       }
/*  66 */       this.cached_stubs.put(Integer.valueOf(hash), stub = new CaveNetworkStub(origin_chunk_x, origin_chunk_z, 64, 48, 64, seed, has_mycelium, (this.rand.nextInt(3) > 0), (this.rand.nextInt(3) > 0)));
/*     */     } 
/*     */     
/*  69 */     return stub;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOriginOfCaveNetwork(World world, int chunk_x, int chunk_z) {
/*  75 */     if (!isGenAllowedInChunk(world, chunk_x, chunk_z)) {
/*  76 */       return false;
/*     */     }
/*  78 */     this.worldObj = world;
/*  79 */     this.rand.setSeed(world.getSeed());
/*     */     
/*  81 */     long seed_a = this.rand.nextLong();
/*  82 */     long seed_b = this.rand.nextLong();
/*     */     
/*  84 */     long seed_c = chunk_x * seed_a;
/*  85 */     long seed_d = chunk_z * seed_b;
/*     */     
/*  87 */     long chunk_seed = seed_c ^ seed_d ^ world.getSeed();
/*     */     
/*  89 */     this.rand.setSeed(chunk_seed);
/*  90 */     this.random_number_index = (int)chunk_seed & 0x7FFF;
/*     */     
/*  92 */     double distance_from_world_origin = world.getDistanceFromWorldOrigin(chunk_x * 16, chunk_z * 16);
/*     */     
/*  94 */     if (world.getSeed() == 1L && chunk_x == -14 && chunk_z == 29) {
/*     */       
/*  96 */       this.stub = getOrCreateCaveNetworkStub(world, chunk_x, chunk_z, distance_from_world_origin);
/*  97 */       return true;
/*     */     } 
/*     */     
/* 100 */     if (distance_from_world_origin >= 1000.0D && this.rand.nextInt(200) == 0) {
/*     */       
/* 102 */       Random rand = this.rand;
/* 103 */       this.rand = new Random();
/*     */       
/* 105 */       for (int origin_chunk_x = chunk_x + 1; origin_chunk_x <= chunk_x + this.a; origin_chunk_x++) {
/*     */         
/* 107 */         for (int origin_chunk_z = chunk_z + 1; origin_chunk_z <= chunk_z + this.a; origin_chunk_z++) {
/*     */           
/* 109 */           if (!isGenAllowedInChunk(world, origin_chunk_x, origin_chunk_z)) {
/* 110 */             return false;
/*     */           }
/* 112 */           if (isOriginOfCaveNetwork(world, origin_chunk_x, origin_chunk_z))
/*     */           {
/*     */             
/* 115 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 120 */       this.rand = rand;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 138 */       this.stub = getOrCreateCaveNetworkStub(world, chunk_x, chunk_z, distance_from_world_origin);
/*     */       
/* 140 */       return true;
/*     */     } 
/*     */     
/* 143 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CaveNetworkStub getCaveNetworkStubAt(World world, int chunk_x, int chunk_z) {
/* 149 */     if (!isGenAllowedInChunk(world, chunk_x, chunk_z)) {
/* 150 */       return null;
/*     */     }
/* 152 */     for (int origin_chunk_x = chunk_x - this.a; origin_chunk_x <= chunk_x; origin_chunk_x++) {
/*     */       
/* 154 */       for (int origin_chunk_z = chunk_z - this.a; origin_chunk_z <= chunk_z; origin_chunk_z++) {
/*     */         
/* 156 */         if (isOriginOfCaveNetwork(world, origin_chunk_x, origin_chunk_z)) {
/* 157 */           return this.stub;
/*     */         }
/*     */       } 
/*     */     } 
/* 161 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGenAllowedInBiome(BiomeGenBase biome) {
/* 166 */     return (biome != BiomeGenBase.ocean);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\MapGenCaveNetwork.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */