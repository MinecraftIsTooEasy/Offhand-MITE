/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkPostField
/*     */ {
/*     */   public static final int TYPE_FOG_DENSITY = 0;
/*     */   public static final int TYPE_UNDERWORLD_MYCELIUM = 1;
/*     */   private Random random;
/*     */   private final int type;
/*     */   private final long hashed_type;
/*     */   private final long hashed_world_seed;
/*     */   private final int post_max_radius_of_effect;
/*     */   private final int post_max_radius_of_effect_sq;
/*     */   private final int field_chunk_range;
/*     */   private final int field_size_in_chunks;
/*     */   private final int field_num_chunks;
/*     */   private final float chance_of_post_per_chunk;
/*     */   private int last_post_field_generation_origin_chunk_x;
/*     */   private int last_post_field_generation_origin_chunk_z;
/*     */   private List post_list;
/*     */   
/*     */   public ChunkPostField(int type, long hashed_world_seed, int post_max_radius_of_effect, float chance_of_post_per_chunk) {
/*  39 */     this.random = new Random(type);
/*     */     
/*  41 */     this.type = type;
/*     */     
/*  43 */     this.random.nextInt();
/*     */     
/*  45 */     this.hashed_type = this.random.nextLong();
/*     */     
/*  47 */     this.hashed_world_seed = hashed_world_seed;
/*     */     
/*  49 */     this.post_max_radius_of_effect = post_max_radius_of_effect;
/*  50 */     this.post_max_radius_of_effect_sq = post_max_radius_of_effect * post_max_radius_of_effect;
/*     */     
/*  52 */     this.field_chunk_range = (post_max_radius_of_effect < 1) ? 0 : ((post_max_radius_of_effect - 1) / 16 + 1);
/*     */     
/*  54 */     this.field_size_in_chunks = this.field_chunk_range * 2 + 1;
/*  55 */     this.field_num_chunks = this.field_size_in_chunks * this.field_size_in_chunks;
/*     */     
/*  57 */     this.chance_of_post_per_chunk = chance_of_post_per_chunk;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean doesPostFieldRequireRegeneration(int chunk_x, int chunk_z) {
/*  62 */     return (this.post_list == null || this.last_post_field_generation_origin_chunk_x != chunk_x || this.last_post_field_generation_origin_chunk_z != chunk_z);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkPostField generate(int chunk_x, int chunk_z) {
/*  67 */     generateFieldIfRequired(chunk_x, chunk_z);
/*  68 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void generateFieldIfRequired(int chunk_x, int chunk_z) {
/*  73 */     if (!doesPostFieldRequireRegeneration(chunk_x, chunk_z)) {
/*     */       return;
/*     */     }
/*  76 */     this.last_post_field_generation_origin_chunk_x = chunk_x;
/*  77 */     this.last_post_field_generation_origin_chunk_z = chunk_z;
/*     */     
/*  79 */     if (this.post_list == null) {
/*  80 */       this.post_list = new ArrayList();
/*     */     } else {
/*  82 */       this.post_list.clear();
/*     */     } 
/*  84 */     for (int chunk_dx = -this.field_chunk_range; chunk_dx <= this.field_chunk_range; chunk_dx++) {
/*     */       
/*  86 */       for (int chunk_dz = -this.field_chunk_range; chunk_dz <= this.field_chunk_range; chunk_dz++) {
/*     */         
/*  88 */         chunk_x = this.last_post_field_generation_origin_chunk_x + chunk_dx;
/*  89 */         chunk_z = this.last_post_field_generation_origin_chunk_z + chunk_dz;
/*     */         
/*  91 */         long seed = Chunk.getChunkCoordsHash(chunk_x, chunk_z) * this.hashed_type * this.hashed_world_seed;
/*     */         
/*  93 */         this.random.setSeed(seed);
/*     */         
/*  95 */         this.random.nextInt();
/*     */         
/*  97 */         if (this.random.nextFloat() < this.chance_of_post_per_chunk) {
/*  98 */           this.post_list.add(new ChunkPost(chunk_x, chunk_z, this.random.nextInt(16) * 1, this.random.nextInt(16) * 1, seed));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPostMaxRadiusOfEffect() {
/* 107 */     return this.post_max_radius_of_effect;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPostMaxRadiusOfEffectSq() {
/* 112 */     return this.post_max_radius_of_effect_sq;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getNearbyPosts(int chunk_x, int chunk_z) {
/* 117 */     generateFieldIfRequired(chunk_x, chunk_z);
/* 118 */     return this.post_list;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getNearbyPostsForBlockCoords(int x, int z) {
/* 123 */     return getNearbyPosts(Chunk.getChunkCoordFromBlockCoord(x), Chunk.getChunkCoordFromBlockCoord(z));
/*     */   }
/*     */ 
/*     */   
/*     */   public ChunkPost getNearestPostTo(double pos_x, double pos_z) {
/* 128 */     List posts = getNearbyPosts(Chunk.getChunkCoordFromBlockCoord(MathHelper.floor_double(pos_x)), Chunk.getChunkCoordFromBlockCoord(MathHelper.floor_double(pos_z)));
/*     */     
/* 130 */     ChunkPost nearest_post = null;
/* 131 */     double shortest_distance_sq_to_post = 0.0D;
/*     */     
/* 133 */     Iterator<ChunkPost> i = posts.iterator();
/*     */     
/* 135 */     while (i.hasNext()) {
/*     */       
/* 137 */       ChunkPost post = i.next();
/*     */       
/* 139 */       double distance_sq = post.getDistanceSqFromPosXZ(pos_x, pos_z);
/*     */       
/* 141 */       if (distance_sq > this.post_max_radius_of_effect_sq) {
/*     */         continue;
/*     */       }
/* 144 */       if (nearest_post == null || distance_sq < shortest_distance_sq_to_post) {
/*     */         
/* 146 */         nearest_post = post;
/* 147 */         shortest_distance_sq_to_post = distance_sq;
/*     */       } 
/*     */     } 
/*     */     
/* 151 */     return nearest_post;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public double getDistanceToNearestPost(double pos_x, double pos_z) {
/* 157 */     ChunkPost nearest_post = getNearestPostTo(pos_x, pos_z);
/*     */     
/* 159 */     return (nearest_post == null) ? -1.0D : nearest_post.getDistanceFromPosXZ(pos_x, pos_z);
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\ChunkPostField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */