/*     */ package net.minecraft;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BiomeGenUnderworld
/*     */   extends BiomeGenBase
/*     */ {
/*     */   public BiomeGenUnderworld(int id) {
/*  12 */     super(id);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  19 */     removeEntityFromSpawnableLists(EntitySkeleton.class);
/*  20 */     removeEntityFromSpawnableLists(EntityZombie.class);
/*  21 */     removeEntityFromSpawnableLists(EntityGhoul.class);
/*  22 */     removeEntityFromSpawnableLists(EntityRevenant.class);
/*  23 */     removeEntityFromSpawnableLists(EntityBoneLord.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityCaveSpider.class, 40, 1, 2));
/*  30 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityLongdead.class, 40, 1, 2));
/*  31 */     this.spawnableMonsterList.add(new SpawnListEntry(EntityAncientBoneLord.class, 5, 1, 1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void decorate(World world, Random random, int chunk_origin_x, int chunk_origin_z) {
/*  36 */     placeMycelium(world, chunk_origin_x, chunk_origin_z);
/*     */     
/*  38 */     super.decorate(world, random, chunk_origin_x, chunk_origin_z);
/*     */   }
/*     */ 
/*     */   
/*     */   private void placeMycelium(World world, int chunk_origin_x, int chunk_origin_z) {
/*  43 */     ChunkPostField mycelium_posts = world.getMyceliumPostField();
/*     */     
/*  45 */     Random random = new Random();
/*     */     
/*  47 */     for (int x = chunk_origin_x; x < chunk_origin_x + 16; x++) {
/*     */       
/*  49 */       for (int z = chunk_origin_z; z < chunk_origin_z + 16; z++) {
/*     */         
/*  51 */         List<ChunkPost> posts = mycelium_posts.getNearbyPostsForBlockCoords(x, z);
/*     */         
/*  53 */         for (int i = 0; i < posts.size(); i++) {
/*     */           
/*  55 */           ChunkPost post = posts.get(i);
/*     */           
/*  57 */           if (post.getDistanceSqFromBlockCoords(x, z) <= (mycelium_posts.getPostMaxRadiusOfEffectSq() + 4)) {
/*     */ 
/*     */             
/*  60 */             random.setSeed(post.getSeed());
/*  61 */             random.nextInt();
/*     */             
/*  63 */             int y = random.nextInt(random.nextBoolean() ? 16 : 72) + 24;
/*     */             
/*  65 */             y += world.underworld_y_offset;
/*     */             
/*  67 */             int height = random.nextInt(5) + 1;
/*     */ 
/*     */ 
/*     */             
/*  71 */             for (int dy = 0; dy < height; dy++) {
/*     */               
/*  73 */               if (world.isAirBlock(x, y + 1, z)) {
/*     */                 
/*  75 */                 Block block = world.getBlock(x, y, z);
/*     */                 
/*  77 */                 if (block != null && block.isAlwaysSolidOpaqueStandardFormCube() && !(block instanceof BlockMushroomCap)) {
/*     */                   
/*  79 */                   block = world.getBlock(x, y - 1, z);
/*     */                   
/*  81 */                   if (block != null && block.isAlwaysSolidOpaqueStandardFormCube())
/*     */                   {
/*  83 */                     if (world.setBlock(x, y, z, Block.mycelium.blockID, 0, 2)) {
/*     */                       
/*  85 */                       world.getChunkFromBlockCoords(x, z).setHadNaturallyOccurringMycelium();
/*     */                       
/*  87 */                       random.setSeed(post.getSeed() + MathHelper.getIntPairHash(x, z));
/*     */                       
/*  89 */                       random.nextInt();
/*     */                       
/*  91 */                       if (random.nextInt(16) == 0)
/*     */                       {
/*  93 */                         if (!this.theBiomeDecorator.bigMushroomGen.generate(world, random, x, y + 1, z)) {
/*  94 */                           world.setBlock(x, y + 1, z, Block.mushroomBrown.blockID, 0, 2);
/*     */                         }
/*     */                       }
/*     */                     } 
/*     */                   }
/*     */                 } 
/*     */                 
/*     */                 break;
/*     */               } 
/* 103 */               y++;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\BiomeGenUnderworld.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */