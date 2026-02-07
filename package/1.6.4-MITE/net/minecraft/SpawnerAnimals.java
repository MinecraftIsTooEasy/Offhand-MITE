/*      */ package net.minecraft;
/*      */ 
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class SpawnerAnimals
/*      */ {
/*   12 */   private HashMap eligibleChunksForSpawning = new HashMap<Object, Object>();
/*      */   
/*   14 */   private Random random = new Random();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static ChunkPosition getRandomSpawningPointInChunk(World par0World, int par1, int par2) {
/*   21 */     Chunk var3 = par0World.getChunkFromChunkCoords(par1, par2);
/*   22 */     int var4 = par1 * 16 + par0World.rand.nextInt(16);
/*   23 */     int var5 = par2 * 16 + par0World.rand.nextInt(16);
/*      */     
/*   25 */     int var6 = par0World.rand.nextInt((var3 == null) ? par0World.getActualHeight() : (var3.getTopFilledSegment() + 16 - 0));
/*   26 */     return new ChunkPosition(var4, var6, var5);
/*      */   }
/*      */ 
/*      */   
/*      */   private void setEligibleChunksForSpawning(WorldServer world, boolean for_hostile_mobs) {
/*   31 */     this.eligibleChunksForSpawning.clear();
/*      */     
/*   33 */     boolean is_daytime = world.isDaytime();
/*   34 */     boolean is_full_moon = world.isFullMoon();
/*   35 */     boolean is_blood_moon = world.isBloodMoon(false);
/*      */     
/*   37 */     for (int i = 0; i < world.playerEntities.size(); i++) {
/*      */       
/*   39 */       EntityPlayer player = world.playerEntities.get(i);
/*      */       
/*   41 */       if (player.isGhost() || player.isZevimrgvInTournament()) {
/*      */         continue;
/*      */       }
/*   44 */       if (player.isDead || player.getHealth() <= 0.0F) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*   50 */       if (for_hostile_mobs) {
/*      */ 
/*      */ 
/*      */         
/*   54 */         if (world.provider.dimensionId == 0) {
/*      */           
/*   56 */           density_limit = (int)(8.0F * (1.0F + (64 - player.getFootBlockPosY()) / 32.0F));
/*      */           
/*   58 */           int minimum_density_limit = 8;
/*      */           
/*   60 */           if (!is_daytime)
/*      */           {
/*      */             
/*   63 */             if (is_blood_moon) {
/*   64 */               minimum_density_limit = minimum_density_limit * 3 / 2;
/*   65 */             } else if (is_full_moon) {
/*   66 */               minimum_density_limit = minimum_density_limit * 5 / 4;
/*      */             } 
/*      */           }
/*   69 */           if (density_limit < minimum_density_limit) {
/*   70 */             density_limit = minimum_density_limit;
/*      */           }
/*      */         } else {
/*      */           
/*   74 */           density_limit = 8;
/*      */         } 
/*      */ 
/*      */         
/*   78 */         int density_limit = (int)(density_limit + density_limit * world.getStrongholdProximity(player.getBlockPosX(), player.getBlockPosZ()));
/*      */ 
/*      */ 
/*      */         
/*   82 */         if (world.getEntitiesWithinAABB(IMob.class, player.boundingBox.expand(32.0D, 8.0D, 32.0D)).size() > density_limit) {
/*      */           continue;
/*      */         }
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*   92 */       int chunk_x = player.getChunkPosX();
/*   93 */       int chunk_z = player.getChunkPosZ();
/*      */       
/*   95 */       byte radius = 8;
/*      */ 
/*      */       
/*   98 */       for (int delta_chunk_x = -8; delta_chunk_x <= 8; delta_chunk_x++) {
/*      */         
/*  100 */         for (int delta_chunk_z = -8; delta_chunk_z <= 8; delta_chunk_z++) {
/*      */           
/*  102 */           boolean is_at_edge = (delta_chunk_x == -8 || delta_chunk_x == 8 || delta_chunk_z == -8 || delta_chunk_z == 8);
/*  103 */           ChunkCoordIntPair chunk_coord = new ChunkCoordIntPair(chunk_x + delta_chunk_x, chunk_z + delta_chunk_z);
/*      */           
/*  105 */           if (!is_at_edge) {
/*  106 */             this.eligibleChunksForSpawning.put(chunk_coord, Boolean.valueOf(false));
/*  107 */           } else if (!this.eligibleChunksForSpawning.containsKey(chunk_coord)) {
/*  108 */             this.eligibleChunksForSpawning.put(chunk_coord, Boolean.valueOf(true));
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       continue;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Class getSubstituteClassToSpawn(World world, int y, Class<EntityVampireBat> suitable_creature_class) {
/*  123 */     if (suitable_creature_class == EntityVampireBat.class && world.isUnderworld() && world.rand.nextInt(6) == 0) {
/*  124 */       return EntityGiantVampireBat.class;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  129 */     if (suitable_creature_class == EntityLongdead.class && world.rand.nextInt(6) == 0) {
/*  130 */       return EntityLongdeadGuardian.class;
/*      */     }
/*  132 */     return suitable_creature_class;
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
/*      */   
/*      */   private float tryHangBatFromCeiling(World world, EntityBat bat, int x, int y, int z, float pos_x, float pos_y, float pos_z) {
/*  181 */     int dy = 0;
/*      */     
/*  183 */     while (++dy < 16) {
/*      */       
/*  185 */       if (world.isAirBlock(x, y + dy, z)) {
/*      */         continue;
/*      */       }
/*  188 */       if (bat.canHangFromBlock(x, y + dy, z)) {
/*      */         
/*  190 */         bat.posX = pos_x;
/*  191 */         bat.posY = ((y + dy) - bat.height - 0.01F);
/*  192 */         bat.posZ = pos_z;
/*      */         
/*  194 */         bat.setBlockHangingFromY(y + dy);
/*  195 */         bat.setIsBatHanging(true);
/*      */         
/*  197 */         bat.setInitialHangAttempted();
/*      */         
/*  199 */         pos_y = (float)bat.posY;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  205 */     return pos_y;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int trySpawningHostileMobs(WorldServer world, boolean deep_only) {
/*  211 */     EnumCreatureType creature_type = EnumCreatureType.monster;
/*      */     
/*  213 */     boolean is_overworld = world.isOverworld();
/*      */     
/*  215 */     boolean is_new_moon = world.isNewMoon();
/*  216 */     boolean is_full_moon = world.isFullMoon();
/*  217 */     boolean is_blood_moon = world.isBloodMoon(false);
/*  218 */     boolean is_blue_moon = world.isBlueMoon(false);
/*      */     
/*  220 */     float min_distance_from_players = 24.0F;
/*  221 */     float min_distance_from_spawn_sq = 576.0F;
/*      */     
/*  223 */     boolean is_daytime = world.isDaytime();
/*      */     
/*  225 */     int creature_limit = creature_type.getMaxNumberOfCreature() * this.eligibleChunksForSpawning.size() / 256;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  230 */     if (deep_only) {
/*  231 */       creature_limit *= 2;
/*      */     }
/*  233 */     if (deep_only) {
/*  234 */       world.last_mob_spawn_limit_under_60 = creature_limit;
/*      */     } else {
/*  236 */       world.last_mob_spawn_limit_at_60_or_higher = creature_limit;
/*      */     } 
/*  238 */     if (world.countMobs(deep_only, !deep_only) >= creature_limit) {
/*  239 */       return 0;
/*      */     }
/*  241 */     boolean try_to_hang_bats_from_ceiling = world.rand.nextBoolean();
/*      */     
/*  243 */     int total_spawned = 0;
/*      */     
/*  245 */     ChunkCoordinates spawn_point = world.getSpawnPoint();
/*      */     
/*  247 */     Iterator<ChunkCoordIntPair> eligible_chunk_iterator = this.eligibleChunksForSpawning.keySet().iterator();
/*      */ 
/*      */     
/*  250 */     label150: while (eligible_chunk_iterator.hasNext()) {
/*      */       
/*  252 */       ChunkCoordIntPair chunk_coord = eligible_chunk_iterator.next();
/*      */       
/*  254 */       if (((Boolean)this.eligibleChunksForSpawning.get(chunk_coord)).booleanValue()) {
/*      */         continue;
/*      */       }
/*  257 */       ChunkPosition chunk_pos = getRandomSpawningPointInChunk(world, chunk_coord.chunkXPos, chunk_coord.chunkZPos);
/*      */       
/*  259 */       if (deep_only && chunk_pos.y >= 60) {
/*  260 */         chunk_pos = getRandomSpawningPointInChunk(world, chunk_coord.chunkXPos, chunk_coord.chunkZPos);
/*      */       }
/*  262 */       int x = chunk_pos.x;
/*  263 */       int y = chunk_pos.y;
/*  264 */       int z = chunk_pos.z;
/*      */       
/*  266 */       if (world.isOverworld() && y == 63 && world.rand.nextInt(4) > 0 && world.getBlock(x, y - 1, z) == Block.ice) {
/*      */         continue;
/*      */       }
/*  269 */       if (world.getClosestPlayer(x, y, z, 48.0D, true) == null && world.rand.nextInt(2) == 0) {
/*      */         continue;
/*      */       }
/*  272 */       if (deep_only) {
/*      */         
/*  274 */         if (y >= 60 || world.countMobs(true, false) >= creature_limit)
/*      */         {
/*      */           continue;
/*      */         }
/*      */       }
/*  279 */       else if (y < 60 || world.countMobs(false, true) >= creature_limit) {
/*      */         continue;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  290 */       if (!canCreatureTypeSpawnAtLocation(creature_type, world, x, y, z, false, null)) {
/*      */         continue;
/*      */       }
/*  293 */       int num_spawned_below_60 = 0;
/*  294 */       int num_spawned_at_60_or_higher = 0;
/*      */       
/*  296 */       int var18 = 0;
/*      */       
/*  298 */       while (var18 < 3) {
/*      */         
/*  300 */         int x_with_random_offset = x;
/*  301 */         int z_with_random_offset = z;
/*      */         
/*  303 */         byte random_offset_range = 6;
/*      */         
/*  305 */         Class<EntityGhast> suitable_creature_class = null;
/*  306 */         EntityLivingData entity_living_data = null;
/*      */         
/*  308 */         int var25 = 0;
/*      */         
/*  310 */         int max_spawn_attempts = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  315 */         while (var25 < max_spawn_attempts) {
/*      */           EntityLiving entity_living;
/*      */ 
/*      */           
/*  319 */           x_with_random_offset += world.rand.nextInt(random_offset_range) - world.rand.nextInt(random_offset_range);
/*  320 */           z_with_random_offset += world.rand.nextInt(random_offset_range) - world.rand.nextInt(random_offset_range);
/*      */           
/*  322 */           double[] resulting_y_pos = new double[1];
/*      */           
/*  324 */           if (!canCreatureTypeSpawnAtLocation(creature_type, world, x_with_random_offset, y, z_with_random_offset, false, resulting_y_pos)) {
/*      */             
/*  326 */             var25++;
/*      */             
/*      */             continue;
/*      */           } 
/*  330 */           float pos_x = x_with_random_offset + 0.5F;
/*  331 */           float pos_y = y;
/*  332 */           float pos_z = z_with_random_offset + 0.5F;
/*      */           
/*  334 */           pos_y = (float)resulting_y_pos[0];
/*      */           
/*  336 */           boolean can_spawn_close_to_player = ((world.isOverworld() || world.isUnderworld()) && world.getClosestPlayer(pos_x, pos_y, pos_z, 24.0D, true) != null && world.getBlockLightValue(x_with_random_offset, MathHelper.floor_float(pos_y), z_with_random_offset) == 0 && world.getBlockLightValue(x_with_random_offset, MathHelper.floor_float(pos_y) + 1, z_with_random_offset) == 0);
/*      */           
/*  338 */           if (can_spawn_close_to_player) {
/*      */             
/*  340 */             if (world.getClosestPlayer(pos_x, pos_y, pos_z, 8.0D, false) != null) {
/*      */               
/*  342 */               var25++;
/*      */ 
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*  348 */           } else if (world.getClosestPlayer(pos_x, pos_y, pos_z, 24.0D, false) != null) {
/*      */             
/*  350 */             var25++;
/*      */             
/*      */             continue;
/*      */           } 
/*  354 */           float delta_x = pos_x - spawn_point.posX;
/*  355 */           float delta_y = pos_y - spawn_point.posY;
/*  356 */           float delta_z = pos_z - spawn_point.posZ;
/*      */           
/*  358 */           float distance_from_spawn_point_sq = delta_x * delta_x + delta_y * delta_y + delta_z * delta_z;
/*      */           
/*  360 */           if (distance_from_spawn_point_sq < 576.0F) {
/*      */             
/*  362 */             var25++;
/*      */             
/*      */             continue;
/*      */           } 
/*  366 */           if (suitable_creature_class == null) {
/*      */             
/*  368 */             suitable_creature_class = world.getSuitableCreature(creature_type, x_with_random_offset, y, z_with_random_offset);
/*      */             
/*  370 */             if (suitable_creature_class == null) {
/*      */               break;
/*      */             }
/*      */           } 
/*  374 */           if (suitable_creature_class == EntityGhast.class)
/*      */           {
/*  376 */             if (world.getClosestPlayer(pos_x, pos_y, pos_z, 48.0D, false) != null) {
/*      */ 
/*      */ 
/*      */               
/*  380 */               var25++;
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
/*      */               continue;
/*      */             } 
/*      */           }
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
/*      */           try {
/*  407 */             entity_living = getSubstituteClassToSpawn(world, y, suitable_creature_class).getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*      */           }
/*  409 */           catch (Exception e) {
/*      */             
/*  411 */             e.printStackTrace();
/*  412 */             return total_spawned;
/*      */           } 
/*      */           
/*  415 */           if (entity_living instanceof EntityBat && try_to_hang_bats_from_ceiling) {
/*  416 */             pos_y = tryHangBatFromCeiling(world, (EntityBat)entity_living, x_with_random_offset, y, z_with_random_offset, pos_x, pos_y, pos_z);
/*      */           }
/*  418 */           if (is_overworld && Entity.isClass(entity_living, EntityPhaseSpider.class)) {
/*  419 */             max_spawn_attempts = 64;
/*      */           } else {
/*  421 */             max_spawn_attempts = 4;
/*      */           } 
/*  423 */           entity_living.setLocationAndAngles(pos_x, pos_y, pos_z, world.rand.nextFloat() * 360.0F, 0.0F);
/*      */           
/*  425 */           if (world.isOverworld() && world.isBlueMoonNight() && world.getSkyBlockTypeBrightness(EnumSkyBlock.Sky, entity_living.getBlockPosX(), entity_living.getEyeBlockPosY(), entity_living.getBlockPosZ()) > 0) {
/*      */ 
/*      */ 
/*      */             
/*  429 */             var25++;
/*      */             
/*      */             continue;
/*      */           } 
/*  433 */           if (entity_living.width < 1.0F) {
/*      */             
/*  435 */             if (!entity_living.getCanSpawnHere(true)) {
/*      */               
/*  437 */               var25++;
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */           } else {
/*  445 */             boolean can_spawn_here = entity_living.getCanSpawnHere(true);
/*      */             
/*  447 */             if (!can_spawn_here) {
/*      */               
/*  449 */               entity_living.setLocationAndAngles((pos_x - 0.5F), pos_y, pos_z, world.rand.nextFloat() * 360.0F, 0.0F);
/*  450 */               can_spawn_here = entity_living.getCanSpawnHere(true);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  456 */             if (!can_spawn_here) {
/*      */               
/*  458 */               entity_living.setLocationAndAngles((pos_x + 0.5F), pos_y, pos_z, world.rand.nextFloat() * 360.0F, 0.0F);
/*  459 */               can_spawn_here = entity_living.getCanSpawnHere(true);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  465 */             if (!can_spawn_here) {
/*      */               
/*  467 */               entity_living.setLocationAndAngles(pos_x, pos_y, (pos_z - 0.5F), world.rand.nextFloat() * 360.0F, 0.0F);
/*  468 */               can_spawn_here = entity_living.getCanSpawnHere(true);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  474 */             if (!can_spawn_here) {
/*      */               
/*  476 */               entity_living.setLocationAndAngles(pos_x, pos_y, (pos_z + 0.5F), world.rand.nextFloat() * 360.0F, 0.0F);
/*  477 */               can_spawn_here = entity_living.getCanSpawnHere(true);
/*      */             } 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  483 */             if (!can_spawn_here) {
/*      */               
/*  485 */               var25++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */               
/*      */               continue;
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  499 */           if (is_daytime) {
/*      */             
/*  501 */             if (entity_living.isEntityUndead() && world.isOutdoors(MathHelper.floor_double(pos_x), MathHelper.floor_double(pos_y), MathHelper.floor_double(pos_z)))
/*      */             {
/*      */               continue label150;
/*      */             
/*      */             }
/*      */           
/*      */           }
/*      */           else {
/*      */             
/*  510 */             int chance_of_skipping = is_blue_moon ? 54 : (is_blood_moon ? 2 : (is_full_moon ? 3 : (is_new_moon ? 6 : 4)));
/*      */             
/*  512 */             if (world.rand.nextInt(chance_of_skipping) != 0 && world.isOutdoors(MathHelper.floor_double(pos_x), MathHelper.floor_double(pos_y), MathHelper.floor_double(pos_z))) {
/*      */               continue label150;
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  521 */           if (y < 60) {
/*  522 */             num_spawned_below_60++;
/*      */           } else {
/*  524 */             num_spawned_at_60_or_higher++;
/*      */           } 
/*      */           
/*  527 */           entity_living_data = entity_living.onSpawnWithEgg(entity_living_data);
/*  528 */           world.spawnEntityInWorld(entity_living);
/*      */           
/*  530 */           total_spawned++;
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  535 */           if (y < 60) {
/*      */             
/*  537 */             if (num_spawned_below_60 >= entity_living.getMaxSpawnedInChunk())
/*      */             {
/*      */               continue label150;
/*      */             }
/*      */           }
/*  542 */           else if (num_spawned_at_60_or_higher >= entity_living.getMaxSpawnedInChunk()) {
/*      */             continue label150;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  551 */           var25++;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  556 */         var18++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  562 */     return total_spawned;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public int trySpawningPeacefulMobs(WorldServer world, EnumCreatureType creature_type) {
/*  568 */     boolean is_blue_moon_animal_spawning_period = isBlueMoonAnimalSpawningPeriod(world);
/*      */     
/*  570 */     float min_distance_from_players = 24.0F;
/*  571 */     float min_distance_from_spawn_sq = 576.0F;
/*      */     
/*  573 */     int creature_limit = creature_type.getMaxNumberOfCreature() * this.eligibleChunksForSpawning.size() / 256;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  578 */     if (creature_type == EnumCreatureType.animal && DedicatedServer.isTournamentThatAllowsAnimalSpawning()) {
/*  579 */       creature_limit *= 2;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  584 */     if (world.countEntities(creature_type.getCreatureClass()) >= creature_limit) {
/*  585 */       return 0;
/*      */     }
/*  587 */     boolean try_to_hang_bats_from_ceiling = world.rand.nextBoolean();
/*      */     
/*  589 */     int total_spawned = 0;
/*      */     
/*  591 */     ChunkCoordinates spawn_point = world.getSpawnPoint();
/*      */     
/*  593 */     Iterator<ChunkCoordIntPair> eligible_chunk_iterator = this.eligibleChunksForSpawning.keySet().iterator();
/*      */ 
/*      */     
/*  596 */     label70: while (eligible_chunk_iterator.hasNext()) {
/*      */       
/*  598 */       ChunkCoordIntPair chunk_coord = eligible_chunk_iterator.next();
/*      */       
/*  600 */       if (((Boolean)this.eligibleChunksForSpawning.get(chunk_coord)).booleanValue()) {
/*      */         continue;
/*      */       }
/*  603 */       ChunkPosition chunk_pos = getRandomSpawningPointInChunk(world, chunk_coord.chunkXPos, chunk_coord.chunkZPos);
/*      */       
/*  605 */       int x = chunk_pos.x;
/*  606 */       int y = chunk_pos.y;
/*  607 */       int z = chunk_pos.z;
/*      */       
/*  609 */       if (creature_type == EnumCreatureType.animal && DedicatedServer.tournament_type != null && !world.isWithinTournamentArena(x, z)) {
/*      */         continue;
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  618 */       if (!canCreatureTypeSpawnAtLocation(creature_type, world, x, y, z, false, null)) {
/*      */         continue;
/*      */       }
/*  621 */       int num_spawned = 0;
/*      */       
/*  623 */       int var18 = 0;
/*      */       
/*  625 */       while (var18 < 3) {
/*      */         
/*  627 */         int x_with_random_offset = x;
/*  628 */         int z_with_random_offset = z;
/*      */         
/*  630 */         byte random_offset_range = 6;
/*      */         
/*  632 */         Class<EntityVampireBat> suitable_creature_class = null;
/*  633 */         EntityLivingData entity_living_data = null;
/*      */         
/*  635 */         int var25 = 0;
/*      */         
/*  637 */         int max_num_spawned = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  642 */         while (var25 < max_num_spawned) {
/*      */           EntityLiving entity_living;
/*      */ 
/*      */           
/*  646 */           x_with_random_offset += world.rand.nextInt(random_offset_range) - world.rand.nextInt(random_offset_range);
/*  647 */           z_with_random_offset += world.rand.nextInt(random_offset_range) - world.rand.nextInt(random_offset_range);
/*      */           
/*  649 */           double[] resulting_y_pos = new double[1];
/*      */           
/*  651 */           if (!canCreatureTypeSpawnAtLocation(creature_type, world, x_with_random_offset, y, z_with_random_offset, false, resulting_y_pos)) {
/*      */             
/*  653 */             var25++;
/*      */             
/*      */             continue;
/*      */           } 
/*  657 */           float pos_x = x_with_random_offset + 0.5F;
/*  658 */           float pos_y = y;
/*  659 */           float pos_z = z_with_random_offset + 0.5F;
/*      */           
/*  661 */           pos_y = (float)resulting_y_pos[0];
/*      */           
/*  663 */           if (world.getClosestPlayer(pos_x, pos_y, pos_z, 24.0D, false) != null) {
/*      */             
/*  665 */             var25++;
/*      */             
/*      */             continue;
/*      */           } 
/*  669 */           float delta_x = pos_x - spawn_point.posX;
/*  670 */           float delta_y = pos_y - spawn_point.posY;
/*  671 */           float delta_z = pos_z - spawn_point.posZ;
/*      */           
/*  673 */           float distance_from_spawn_point_sq = delta_x * delta_x + delta_y * delta_y + delta_z * delta_z;
/*      */           
/*  675 */           if (distance_from_spawn_point_sq < 576.0F) {
/*      */             
/*  677 */             var25++;
/*      */             
/*      */             continue;
/*      */           } 
/*  681 */           if (suitable_creature_class == null) {
/*      */             
/*  683 */             suitable_creature_class = world.getSuitableCreature(creature_type, x_with_random_offset, y, z_with_random_offset);
/*      */             
/*  685 */             if (suitable_creature_class == null) {
/*      */               break;
/*      */             }
/*  688 */             if (suitable_creature_class == EntityVampireBat.class && world.rand.nextInt(4) == 0) {
/*  689 */               max_num_spawned = 4 + world.rand.nextInt(5);
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*      */           try {
/*  698 */             entity_living = getSubstituteClassToSpawn(world, y, suitable_creature_class).getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*      */           }
/*  700 */           catch (Exception e) {
/*      */             
/*  702 */             e.printStackTrace();
/*  703 */             return total_spawned;
/*      */           } 
/*      */           
/*  706 */           if (entity_living instanceof EntityBat && try_to_hang_bats_from_ceiling) {
/*  707 */             pos_y = tryHangBatFromCeiling(world, (EntityBat)entity_living, x_with_random_offset, y, z_with_random_offset, pos_x, pos_y, pos_z);
/*      */           }
/*      */ 
/*      */ 
/*      */           
/*  712 */           entity_living.setLocationAndAngles(pos_x, pos_y, pos_z, world.rand.nextFloat() * 360.0F, 0.0F);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  717 */           if (!entity_living.getCanSpawnHere((creature_type != EnumCreatureType.animal || !is_blue_moon_animal_spawning_period))) {
/*      */             
/*  719 */             var25++;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             continue;
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  731 */           num_spawned++;
/*  732 */           total_spawned++;
/*      */ 
/*      */           
/*  735 */           entity_living_data = entity_living.onSpawnWithEgg(entity_living_data);
/*  736 */           world.spawnEntityInWorld(entity_living);
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  741 */           if (num_spawned >= entity_living.getMaxSpawnedInChunk()) {
/*      */             continue label70;
/*      */           }
/*  744 */           var25++;
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  749 */         var18++;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  755 */     return total_spawned;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean isBlueMoonAnimalSpawningPeriod(World world) {
/*  761 */     return world.isBlueMoon(true);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public float calcEffectiveHostileMobSpawningRateModifier(WorldServer world) {
/*  767 */     if (world.provider.dimensionId != 0) {
/*  768 */       return 0.25F;
/*      */     }
/*  770 */     float hostile_mob_spawning_rate_modifier = 1.0F;
/*      */     
/*  772 */     if (world.decreased_hostile_mob_spawning_counter > 0) {
/*      */       
/*  774 */       world.decreased_hostile_mob_spawning_counter--;
/*  775 */       hostile_mob_spawning_rate_modifier *= 0.5F;
/*      */     }
/*  777 */     else if (this.random.nextInt(24000) == 0) {
/*      */       
/*  779 */       world.decreased_hostile_mob_spawning_counter = this.random.nextInt(4000) + 1;
/*      */     } 
/*      */     
/*  782 */     if (world.increased_hostile_mob_spawning_counter > 0) {
/*      */       
/*  784 */       world.increased_hostile_mob_spawning_counter--;
/*  785 */       hostile_mob_spawning_rate_modifier *= 2.0F;
/*      */     }
/*  787 */     else if (this.random.nextInt(24000) == 0) {
/*      */       
/*  789 */       world.increased_hostile_mob_spawning_counter = this.random.nextInt(2000);
/*      */     } 
/*      */     
/*  792 */     if (world.no_hostile_mob_spawning_counter > 0) {
/*      */       
/*  794 */       world.no_hostile_mob_spawning_counter--;
/*  795 */       hostile_mob_spawning_rate_modifier = 0.0F;
/*      */     }
/*  797 */     else if (this.random.nextInt(24000) == 0) {
/*      */       
/*  799 */       world.no_hostile_mob_spawning_counter = this.random.nextInt(2000) + this.random.nextInt(2000);
/*      */     } 
/*      */     
/*  802 */     if (hostile_mob_spawning_rate_modifier < 1.0F && (world.isBloodMoon(false) || world.isThundering(true))) {
/*  803 */       hostile_mob_spawning_rate_modifier = 1.0F;
/*      */     }
/*  805 */     return hostile_mob_spawning_rate_modifier;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void performRandomLivingEntitySpawning(WorldServer world) {
/*  811 */     if (CommandHandler.spawning_disabled) {
/*      */       return;
/*      */     }
/*  814 */     if (world.playerEntities.size() == 0) {
/*      */       return;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  860 */     float hostile_mob_spawning_rate_modifier = calcEffectiveHostileMobSpawningRateModifier(world);
/*      */     
/*  862 */     float effective_hostile_mob_spawning_rate_below_60 = 0.1F * hostile_mob_spawning_rate_modifier;
/*  863 */     float effective_hostile_mob_spawning_rate_at_60_or_higher = 0.17F * hostile_mob_spawning_rate_modifier;
/*      */ 
/*      */ 
/*      */     
/*  867 */     boolean spawn_hostile_mobs_below_60 = (world.spawnHostileMobs && Math.random() < effective_hostile_mob_spawning_rate_below_60);
/*  868 */     boolean spawn_hostile_mobs_at_60_or_higher = (world.spawnHostileMobs && Math.random() < effective_hostile_mob_spawning_rate_at_60_or_higher);
/*      */     
/*  870 */     boolean spawn_peaceful_mobs = (world.spawnPeacefulMobs && world.getTimeOfDay() % 400 == 0);
/*      */     
/*  872 */     boolean spawn_animals = (DedicatedServer.tournament_type == EnumTournamentType.score && world.getTimeOfDay() % 400 == 0);
/*      */     
/*  874 */     if (!spawn_animals) {
/*  875 */       spawn_animals = (isBlueMoonAnimalSpawningPeriod(world) && world.getTimeOfDay() % 400 == 0);
/*      */     }
/*  877 */     if (!spawn_hostile_mobs_below_60 && !spawn_hostile_mobs_at_60_or_higher && !spawn_peaceful_mobs && !spawn_animals) {
/*      */       return;
/*      */     }
/*  880 */     setEligibleChunksForSpawning(world, true);
/*      */     
/*  882 */     if (spawn_hostile_mobs_below_60) {
/*  883 */       trySpawningHostileMobs(world, true);
/*      */     }
/*  885 */     if (spawn_hostile_mobs_at_60_or_higher) {
/*  886 */       trySpawningHostileMobs(world, false);
/*      */     }
/*  888 */     setEligibleChunksForSpawning(world, false);
/*      */     
/*  890 */     if (spawn_peaceful_mobs) {
/*      */       
/*  892 */       trySpawningPeacefulMobs(world, EnumCreatureType.ambient);
/*  893 */       trySpawningPeacefulMobs(world, EnumCreatureType.aquatic);
/*      */     } 
/*      */     
/*  896 */     if (spawn_animals) {
/*  897 */       trySpawningPeacefulMobs(world, EnumCreatureType.animal);
/*      */     }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean canCreatureTypeSpawnOn(EnumCreatureType creature_type, Block block, int metadata, boolean initial_spawn) {
/* 1159 */     if (creature_type == EnumCreatureType.ambient) {
/* 1160 */       return true;
/*      */     }
/* 1162 */     if (block == null) {
/* 1163 */       return false;
/*      */     }
/* 1165 */     Material creature_spawn_material = creature_type.getCreatureMaterial();
/*      */     
/* 1167 */     if (creature_spawn_material == Material.water) {
/* 1168 */       return (block.blockMaterial == Material.water);
/*      */     }
/* 1170 */     if (block == Block.bedrock || block == Block.mantleOrCore) {
/* 1171 */       return false;
/*      */     }
/* 1173 */     if (block == Block.mycelium || block instanceof BlockMushroomCap) {
/* 1174 */       return false;
/*      */     }
/* 1176 */     if (creature_type == EnumCreatureType.animal) {
/* 1177 */       return (block == Block.grass);
/*      */     }
/* 1179 */     return (block == Block.snow || block == Block.waterlily || block instanceof BlockSlab || block instanceof BlockStairs || block.isTopFlatAndSolid(metadata));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static final boolean canCreatureTypeSpawnAtLocation(EnumCreatureType creature_type, World world, int x, int y, int z, boolean initial_spawn, double[] resulting_y_pos) {
/* 1185 */     if (resulting_y_pos != null) {
/* 1186 */       resulting_y_pos[0] = y;
/*      */     }
/* 1188 */     if (y < 1) {
/* 1189 */       return false;
/*      */     }
/* 1191 */     Block block = world.getBlock(x, y, z);
/*      */     
/* 1193 */     if (Block.isAlwaysSolidStandardFormCube(block)) {
/* 1194 */       return false;
/*      */     }
/* 1196 */     Material block_material = (block == null) ? Material.air : block.blockMaterial;
/*      */     
/* 1198 */     if (block_material == Material.lava) {
/* 1199 */       return false;
/*      */     }
/* 1201 */     if (creature_type.getCreatureMaterial() == Material.water) {
/*      */       
/* 1203 */       if (block_material != Material.water) {
/* 1204 */         return false;
/*      */       }
/* 1206 */       return (resulting_y_pos == null || world.getBlockMaterial(x, y - 1, z) == Material.water);
/*      */     } 
/* 1208 */     if (creature_type == EnumCreatureType.ambient)
/*      */     {
/* 1210 */       return (block_material == Material.air);
/*      */     }
/*      */ 
/*      */     
/* 1214 */     if (world.isBlockSolid(block, x, y, z) || block == Block.snow || block == Block.waterlily) {
/*      */       
/* 1216 */       if (resulting_y_pos == null)
/*      */       {
/* 1218 */         return true;
/*      */       }
/*      */ 
/*      */       
/* 1222 */       block.setBlockBoundsBasedOnStateAndNeighbors(world, x, y, z);
/*      */       
/* 1224 */       resulting_y_pos[0] = y + block.maxY[Minecraft.getThreadIndex()] + 0.005D;
/*      */       
/* 1226 */       if (block == Block.snow) {
/* 1227 */         return canCreatureTypeSpawnOn(creature_type, world.getBlock(x, y - 1, z), world.getBlockMetadata(x, y - 1, z), initial_spawn);
/*      */       }
/* 1229 */       return canCreatureTypeSpawnOn(creature_type, block, world.getBlockMetadata(x, y, z), initial_spawn);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1234 */     Block block_below = world.getBlock(x, y - 1, z);
/*      */ 
/*      */     
/* 1237 */     if (block_below == null || (!world.isBlockSolid(block_below, x, y - 1, z) && block_below != Block.snow && block_below != Block.waterlily)) {
/* 1238 */       return false;
/*      */     }
/* 1240 */     if (resulting_y_pos == null)
/*      */     {
/* 1242 */       return true;
/*      */     }
/*      */ 
/*      */     
/* 1246 */     block_below.setBlockBoundsBasedOnStateAndNeighbors(world, x, y - 1, z);
/*      */     
/* 1248 */     resulting_y_pos[0] = (y - 1) + block_below.maxY[Minecraft.getThreadIndex()] + 0.005D;
/*      */     
/* 1250 */     return canCreatureTypeSpawnOn(creature_type, block_below, world.getBlockMetadata(x, y - 1, z), initial_spawn);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void performWorldGenSpawning(World world, BiomeGenBase biome, EnumCreatureType creature_type, int min_x, int min_z, int range_x, int range_z, Random random) {
/* 1328 */     List creatures = biome.getSpawnableList(creature_type);
/*      */     
/* 1330 */     if (creatures.isEmpty()) {
/*      */       return;
/*      */     }
/* 1333 */     while (random.nextFloat() < biome.getSpawningChance()) {
/*      */       
/* 1335 */       SpawnListEntry entry = (SpawnListEntry)WeightedRandom.getRandomItem(world.rand, creatures);
/*      */       
/* 1337 */       if (entry.entityClass == EntityHorse.class) {
/*      */         
/* 1339 */         if (random.nextFloat() < 0.8F) {
/*      */           continue;
/*      */         }
/* 1342 */       } else if (entry.entityClass == EntityWolf.class) {
/*      */         
/* 1344 */         if (random.nextFloat() < 0.5F) {
/*      */           continue;
/*      */         }
/*      */       } 
/* 1348 */       EntityLivingData entity_living_data = null;
/*      */       
/* 1350 */       int group_size = entry.minGroupCount + random.nextInt(1 + entry.maxGroupCount - entry.minGroupCount);
/* 1351 */       int x = min_x + random.nextInt(range_x);
/* 1352 */       int z = min_z + random.nextInt(range_z);
/* 1353 */       int initial_x = x;
/* 1354 */       int initial_z = z;
/*      */ 
/*      */ 
/*      */       
/* 1358 */       boolean is_animal = false;
/*      */       
/* 1360 */       if (entry.entityClass != EntityHellhound.class) {
/*      */         
/* 1362 */         Class<EntityAnimal> super_class = entry.entityClass.getSuperclass();
/*      */         
/* 1364 */         while (super_class != null) {
/*      */           
/* 1366 */           if (super_class == EntityAnimal.class) {
/*      */             
/* 1368 */             is_animal = true;
/*      */             
/*      */             break;
/*      */           } 
/* 1372 */           super_class = (Class)super_class.getSuperclass();
/*      */         } 
/*      */       } 
/*      */       
/* 1376 */       int group_size_spawned = 0;
/*      */ 
/*      */ 
/*      */       
/* 1380 */       for (int i = 0; i < group_size; i++) {
/*      */         
/* 1382 */         boolean spawn_successful = false;
/*      */         
/* 1384 */         for (int attempt = 0; !spawn_successful && attempt < 4; attempt++) {
/*      */           
/* 1386 */           int y = world.getTopSolidOrLiquidBlock(x, z);
/*      */           
/* 1388 */           if (creature_type == EnumCreatureType.aquatic)
/*      */           {
/* 1390 */             if (world.getBlockMaterial(x, y, z) == Material.water) {
/*      */               
/* 1392 */               int highest_water_y = world.getTopSolidOrLiquidBlockMITE(x, z, true);
/*      */               
/* 1394 */               if (highest_water_y - y > 3)
/*      */               {
/*      */                 
/* 1397 */                 y = random.nextInt(highest_water_y - y - 3) + y + 2;
/*      */               }
/*      */             } 
/*      */           }
/*      */ 
/*      */ 
/*      */           
/* 1404 */           double[] resulting_y_pos = new double[1];
/*      */           
/* 1406 */           if (canCreatureTypeSpawnAtLocation(creature_type, world, x, y, z, true, resulting_y_pos)) {
/*      */             EntityLiving var22;
/*      */ 
/*      */             
/* 1410 */             Chunk chunk = world.getChunkFromBlockCoords(x, z);
/*      */             
/* 1412 */             if (is_animal)
/*      */             {
/* 1414 */               if (chunk.animals_spawned < 4) {
/* 1415 */                 chunk.animals_spawned++;
/*      */               } else {
/*      */                 continue;
/*      */               } 
/*      */             }
/*      */ 
/*      */             
/* 1422 */             float pos_x = x + 0.5F;
/* 1423 */             float pos_y = y;
/* 1424 */             float pos_z = z + 0.5F;
/*      */             
/* 1426 */             pos_y = (float)resulting_y_pos[0];
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*      */             try {
/* 1432 */               var22 = entry.entityClass.getConstructor(new Class[] { World.class }).newInstance(new Object[] { world });
/*      */             }
/* 1434 */             catch (Exception var24) {
/*      */               
/* 1436 */               var24.printStackTrace();
/*      */             } 
/*      */ 
/*      */             
/* 1440 */             int blocks_high = (int)var22.height + 1;
/*      */             
/* 1442 */             boolean placement_prevented = false;
/*      */             
/* 1444 */             for (int dy = 1; dy < blocks_high; dy++) {
/*      */               
/* 1446 */               if (world.isBlockSolidStandardFormCube(x, y + dy, z)) {
/*      */                 
/* 1448 */                 placement_prevented = true;
/*      */                 
/*      */                 break;
/*      */               } 
/*      */             } 
/* 1453 */             if (placement_prevented) {
/*      */               
/* 1455 */               if (Minecraft.inDevMode()) {
/* 1456 */                 System.out.println("Prevented placement of " + var22 + " at " + StringHelper.getCoordsAsString(x, y, z));
/*      */               }
/*      */             } else {
/*      */               
/* 1460 */               var22.setLocationAndAngles(pos_x, pos_y, pos_z, random.nextFloat() * 360.0F, 0.0F);
/*      */               
/* 1462 */               entity_living_data = var22.onSpawnWithEgg(entity_living_data);
/* 1463 */               world.spawnEntityInWorld(var22);
/* 1464 */               spawn_successful = true;
/*      */               
/* 1466 */               group_size_spawned++;
/*      */               
/* 1468 */               if (var22 instanceof EntityWaterMob) {
/* 1469 */                 var22.refreshDespawnCounter(-9600);
/*      */               }
/*      */             } 
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/* 1476 */           x += random.nextInt(5) - random.nextInt(5);
/*      */           
/* 1478 */           for (z += random.nextInt(5) - random.nextInt(5); x < min_x || x >= min_x + range_x || z < min_z || z >= min_z + range_x; z = initial_z + random.nextInt(5) - random.nextInt(5))
/*      */           {
/* 1480 */             x = initial_x + random.nextInt(5) - random.nextInt(5);
/*      */           }
/*      */           continue;
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\DELL\Desktop\minecraft-merged-676ab05589-1.6.4-MITE-loom.mappings.1_6_4_MITE.layered+hash.2143599255-v2.jar!\net\minecraft\SpawnerAnimals.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */